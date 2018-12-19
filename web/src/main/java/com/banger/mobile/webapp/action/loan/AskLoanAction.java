/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.webapp.action.loan;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import com.banger.mobile.domain.model.customer.CrmExportBean;
import com.banger.mobile.domain.model.loan.*;
import com.banger.mobile.facade.loan.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.banger.mobile.domain.Enum.user.EnumUserType;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.customer.CustomerExtendFieldBean;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.data.Form;
import com.banger.mobile.domain.model.data.LoanData;
import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.role.SysRole;
import com.banger.mobile.domain.model.system.SysTeam;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.domain.model.user.SysDeptAuth;
import com.banger.mobile.domain.model.user.SysRoleAuth;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataFormService;
import com.banger.mobile.facade.data.DataPhotoService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.impl.data.CaseHelperServiceImpl;
import com.banger.mobile.facade.impl.system.user.SysDeptAuthServiceImpl;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.role.SysRoleService;
import com.banger.mobile.facade.rolemember.SysRoleMemberService;
import com.banger.mobile.facade.system.team.SysTeamService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.facade.user.SysDeptAuthService;
import com.banger.mobile.facade.user.SysRoleAuthService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.importUtil.ImportUtil;
import com.banger.mobile.util.FileType;
import com.banger.mobile.util.IDCardUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.util.VmHelper;
import com.banger.mobile.webapp.action.BaseAction;
import org.apache.velocity.runtime.log.Log;
import org.springframework.util.CollectionUtils;

/**
 * @author QianJie
 * @version $Id: AskLoanAction.java,v 0.1 Feb 17, 2013 4:23:11 PM QianJie Exp $
 */
public class AskLoanAction extends BaseAction {

    //private static final long serialVersionUID = -7458269709136501326L;
    private static Logger logger = Logger.getLogger(AskLoanAction.class);

    /**
     * Service
     */
    private LnLoanService lnLoanService;
    private LnLoanDetailService lnLoanDetailService;
    private LnLoanTypeService lnLoanTypeService;
    private LnLoanCoBorrowerService lnLoanCoBorrowerService;
    private LnLoanGuarantorService lnLoanGuarantorService;
    private SysParamService sysParamService;
    private CustomerDataService customerDataService;
    private DataFormService dataFormService;
    private SysRoleAuthService      sysRoleAuthService;                                     //可管理角色
    private SysRoleService          sysRoleService;                                         //角色service
    private DataPhotoService dataPhotoService;
    private LnOpHistoryService lnOpHistoryService;
    private CrmCustomerService crmCustomerService;
    private SysUploadFileService sysUploadFileService; //文件上传service
    private SysDeptAuthService      sysDeptAuthService;                                     //可管理机构
    private CaseHelperServiceImpl caseHelperService;
    private LnLoanInfoService lnLoanInfoService;
    private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
    private LnCreditHistoryService lnCreditHistoryService;
    private SysUserService sysUserService;
    private SysTeamService sysTeamService;
    private DeptService             deptService;                                            //机构service
    private DeptFacadeService       deptFacadeService;
    private SysRoleMemberService sysRoleMemberService;
    private LnProfitLossProdService lnProfitLossProdService;
    private LnProfitLossBaseService lnProfitLossBaseService;




    /**
     * 参数
     */
    private static final int BUFFERED_SIZE = 200;//200kb  (5 * 1024)5M
    private File logoImage;                  //贷款资料图片
    private LnLoan lnLoan;
    private LnLoanInfo lnLoanInfo;
    private LnCreditHistory lnCreditHistory;
    private List<LnLoanCoBorrowerBean> loanCoBorrowerList;       //共同借贷人列表
    private List<LnLoanGuarantorBean> loanGuarantorList;        //担保人列表
    private List<LnProfitLossProd> lnProfitLossProdList;
    private List<LnCreditHistory> lnCreditHistoryList;
    private List<LnLoanType> loanTypeList;
    private JSONArray               deptJson;                                               //机构树json
    private JSONArray               deptManagerJson;
    private List<SysRoleAuth>       sysRoleAuthList  = new ArrayList<SysRoleAuth>();
    private Integer loanId;
    private Integer customerId;
//    private String photo1;
//    private String photo2;
//    private String photo3;
    private String applyRemark;
    private Integer loanTypeId;
    private Integer loanSubTypeId;
    private BigDecimal loanMoney;
    private Integer coId;
    private Integer guId;
    private Integer chId;
    private Integer assignUserId;
//    private LnProfitLossItem lnProfitLossItem;
    //贷款申请数据
    //消费类贷款数据

    //经营类贷款数据

    //授信类贷款数据

    //客户新增参数
    private String customerName;
    private String phone;
    private String idCard;
    private Integer credentialTypeId;  
    private String company;
    
    private Integer profitLossId;



    public Integer getProfitLossId() {
		return profitLossId;
	}

	public void setProfitLossId(Integer profitLossId) {
		this.profitLossId = profitLossId;
	}

	/**
     * 跳转到申请贷款页面
     *
     * @return
     */
    public String toAskLoanpage() {
        //获取贷款下拉框信息
        // DKLX=贷款类型   DY=抵押      FLXS=法律形式  HYZK=婚姻状况  JYCD=教育程度    JYCS=经营场所 JZCSLX=居住场所类型 JZZK=居住状况   XXLY=信息来源
        //YSRSP=月收入水平   ZJLX=证件类型 ZXQK=装修情况 ZY=质押  ZZXS=组织形式 DBFS=担保方式 ZY=质押FKFS=放款方式   	HKFS=还款方式

    	HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("DKLX","DBFS","DY","ZY","FLXS","HYZK","JYCD","JYCS","JZCSLX","JZZK","XXLY","YSRSP","ZJLX","ZXQK","ZY","ZZXS","HKFS","FKFS");
    	request.setAttribute("checkBoxMessage", checkBoxMessageMap);
    	initParameter();
    	List<SysTeam> sysTeamList = sysTeamService.getSysTeamList();
    	request.setAttribute("sysTeamList", sysTeamList);
    	//assignUserId
    	List<Integer> userIdList = sysRoleMemberService.getUserIdByRole("5","");	
    	request.setAttribute("assignUserList", getAssignUserList(userIdList));
        return SUCCESS;
    }
    
    private List<SysUser> getAssignUserList(List<Integer> userIdList){
    	List<SysUser> returnList = new ArrayList<SysUser>();
    	
    	for (int i=0;i<userIdList.size();i++){
			SysUser user = sysUserService.getSysUserById(userIdList.get(i));
			returnList.add(user);
		}		
    	return returnList;
    }
    /**
     * 初始化信息
     */
    public void initParameter() {
        deptJson = deptService.getAllDeptJson();
        deptManagerJson = deptFacadeService.getInChargeOfDeptJson();
        getDeptAdminDeptJsonRemRoot(1);
    }
    /**
     * 机构管理员   (不 添加默认根节点) json树 
     * @return
     */
    public void getDeptAdminDeptJsonRemRoot(Integer userId) {
        try {

            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("userId", userId);
            Map<String, Integer> admintree = new HashMap<String, Integer>();//机构管理员管理机构
            Map<String, Integer> businesstree = new HashMap<String, Integer>();//业务主管管理机构
            List<SysDeptAuth> sysDeptAuthList = sysDeptAuthService.getSysDeptAuthList(parameters);
            int i =0;
            for (SysDeptAuth sysDeptAuth : sysDeptAuthList) {
                if (sysDeptAuth.getRoleId().equals(2)) {
                    admintree.put("" + sysDeptAuth.getDeptId(), sysDeptAuth.getDeptId());
                }
                if (sysDeptAuth.getRoleId().equals(3)) {
                    businesstree.put("" + sysDeptAuth.getDeptId(), sysDeptAuth.getDeptId());
                }
            }
            List<SysDept> depts = deptService.getUserJsonTree();
            Set<Integer> deptNameList = new HashSet<Integer>();
            for (SysDept sysDept : depts) {
                deptNameList.add(sysDept.getDeptId());
                if(!deptNameList.contains(sysDept.getDeptParentId())) i++;//控制节点展开
            }
            
            Map<String, Object> map = new HashMap<String, Object>();
            Map<String, Object> Vmap = new HashMap<String, Object>();
            JSONArray jsonArray = new JSONArray();
            JSONArray adminArray = new JSONArray();
            if (depts.size() > 0) {
                for (SysDept dept : depts) {
                    map.put("id", dept.getDeptId());
                    Vmap.put("id", dept.getDeptId());
                    if (!deptNameList.contains(dept.getDeptParentId())) {
                        map.put("pId", 2);
                        Vmap.put("pId", 2);
                        if(i==1){
                            map.put("open", true);
                            Vmap.put("open", true);
                        }
                    } else {
                        map.put("pId", dept.getDeptParentId());
                        Vmap.put("pId", dept.getDeptParentId());
                        map.put("open", false);
                        Vmap.put("open", false);
                    }
                    map.put("name", dept.getDeptName());
                    Vmap.put("name", dept.getDeptName());
                    if (admintree.get("" + dept.getDeptId()) != null) {
                        map.put("bool", true);
                    } else {
                        map.put("bool", false);
                    }
                    if (businesstree.get("" + dept.getDeptId()) != null) {
                        Vmap.put("bool", true);
                    } else {
                        Vmap.put("bool", false);
                    }
                    adminArray.add(map);
                    jsonArray.add(Vmap);
                }
            }
            request.setAttribute("adminJsonArray", adminArray);//机构管理员 树
            request.setAttribute("businessJsonArray", jsonArray);//业务主管 树
        } catch (Exception e) {
        }
    }
    /**
     * 获取贷款所需要的下拉框信息
     * 
     * @param param
     * @return
     */
    private HashMap<String,Object> buildLoanCheckBoxMessage(String...param){
    	
    	HashMap<String,Object> paramMap = new HashMap<String,Object>();
    	HashMap<String,Object> resultMap = new HashMap<String,Object>();
    	for (int i = 0; i < param.length; i++) {
    		paramMap.put("dictionaryName", param[i]);
    		List<LnLoanInfoDictionary> lnLoanInfoDictionaryList = lnLoanInfoDictionaryService.selectLoanInfoDictionaryList(paramMap);
    		resultMap.put(param[i], lnLoanInfoDictionaryList);
    		paramMap.remove("dictionaryKey");
		}
    	return resultMap;
    }
    
    
    /**
     * 跳转到编辑申请贷款页面
     *
     * @return
     */
    public String toEditLoanPage() {
        logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入贷款申请编辑页面");
       try{
	        //所有资料
	        Map<String,Object> param = new HashMap<String, Object>();
	        param.put("loanId", loanId);
	        lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
	        
	        //查询历史操作记录
	        List<LnOpHistory> hisList = lnOpHistoryService.getAllOpHistoryListByLoanId(loanId);
	        request.setAttribute("hisList", hisList);
	        //所有资料
	        Map<String,Object> photoMap = new HashMap<String, Object>();
	        photoMap.put("loanId",loanId);
	        photoMap.put("eventId",LoanConstants.LOAN_APPLY_EVENT);
	        photoMap.put("statistics",1);
	        List<LoanData> dataList = customerDataService.getAllLoanDataById(photoMap);
	        List<Photo> photoList = new ArrayList<Photo>();
	        //申请人照片资料
	        for (LoanData d : dataList) {
	            if (d.getDataType().equals(1)){
	                if (d.getCustomerId().equals(lnLoanInfo.getCustomerId())){
	                    String photoUrl = d.getFilePath() + "/" + d.getFileName();
	                    Photo photo = new Photo();
	                    photo.setFileId(d.getFileId());
	                    photo.setPhotoId(d.getDataId());
	                    photo.setFilePath(photoUrl);
	
	                    photoList.add(photo);
	                }
	            }
	        }
	        request.setAttribute("photoList",photoList);
	        
	        HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("DKLX","DBFS","DY","ZY","FLXS","HYZK","JYCD","JYCS","JZCSLX","JZZK","XXLY","YSRSP","ZJLX","ZXQK","ZY","ZZXS","HKFS","FKFS");
	    	request.setAttribute("checkBoxMessage", checkBoxMessageMap);
	    	List<SysTeam> sysTeamList = sysTeamService.getSysTeamList();
	    	request.setAttribute("sysTeamList", sysTeamList);
	        // 共同借贷人
	        loanCoBorrowerList = lnLoanDetailService.getLoanCoList(loanId, dataList);
	        // 担保人
	        loanGuarantorList = lnLoanDetailService.getLoanGuList(loanId, dataList);

	        //信贷历史
	        lnCreditHistoryList = lnCreditHistoryService.getCreditHistoryByLoanId(loanId);
	        
 	        String[] sourcesIds = lnLoanInfo.getRegisterInfoSourceIds().split(",");
 	       HashMap<String,Integer> sourcesIdMap = new HashMap<String,Integer>();
 	        for (String string : sourcesIds) {
				sourcesIdMap.put(string, 1);
			}
	        request.setAttribute("soucesIdMap", sourcesIdMap);
	        request.setAttribute("isEdit","yes");
	        Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("eventId", LoanConstants.LOAN_APPLY_EVENT); //贷后流程
            paramMap.put("loanId",loanId);
            //贷款表单附件信息
            List<Form> formAttachmentList = dataFormService.selectFormAttachment(paramMap);
            request.setAttribute("formAttachmentList",formAttachmentList);
            
            //树形控件
           
            initParameter();
            
            List<Integer> userIdList = sysRoleMemberService.getUserIdByRole("5","");	
        	request.setAttribute("assignUserList", getAssignUserList(userIdList));
            
	        return SUCCESS;
      	}catch(Exception e){
      		e.printStackTrace();
        	return ERROR;
        }
    }
    
        
    
    /**
     * 删除贷款
     */
    public void deleteLoan() {
        try {
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 删除贷款...");
            LnLoan ln = lnLoanService.getLnLoanById(loanId);
            if (ln.getLoanStatusId() == 1) {
                logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 贷款id:"+loanId);
                lnLoanService.deleteLoan(loanId);
            }
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 删除成功");
            PrintWriter out = this.getResponse().getWriter();
            out.print("success");
        } catch (Exception e) {
            logger.error("deleteLoan action error:", e);
        }
    }

    /**
     * 验证申请贷款数据是否有效
     */
    public void vLoanData() {
        PrintWriter out = null;
        try {
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 验证客户是否符合贷款要求...");
            out = this.getResponse().getWriter();
            String msg = "SUCCESS";

            if(customerId == null || customerId == 0){
                //新客户无需验证

            }else{
                SimpleDateFormat sf  =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                Calendar calendar=Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(2, -3);
                String monthDate = sf.format(calendar.getTime());//从今天开始算三个月前的时间
                Map<String, Object> conds = new HashMap<String, Object>();
                conds.put("approveDate", monthDate);
                conds.put("customerId", customerId);
//                List<LnLoan> approveFailList = lnLoanService.getAllLoanByConds(conds);
                Boolean isApproveFail = lnLoanService.isApproveFail(conds);
                if(isApproveFail){
                    msg = "客户"+customerName+"在最近三个月有贷款审批失败，不允许重新申请贷款！";
                }else{
                    conds.clear();
                    conds.put("customerId", customerId);
                    conds.put("isNoGood", 1);//系统不良客户
                    List<LnLoan> noGoodList = lnLoanService.getAllLoanByConds(conds);
                    if(noGoodList.size() > 0){
                        msg = "客户“"+customerName+"”为系统不良客户，不允许申请贷款！";
                    }
                }
            }
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 验证完毕!");
            out.print(msg);
        } catch (Exception e) {
            logger.error("vLoanData action error:", e);
        }
    }

    /**
     * 保存贷款
     */
    public void addLoan() {
        try {
            logger.info("保存申请贷款...");
            int loanId = (Integer) saveLoanInfo().get("loanId");
            PrintWriter out = this.getResponse().getWriter();
            logger.info("保存贷款成功!");
            out.print(loanId);
        } catch (Exception e) {
            //log.error("addLoan action error:", e);
        	e.printStackTrace();
        }
    }

    /**
     * 编辑贷款
     */
    public void editLoan() {
        PrintWriter out =null;
        try {
            out = this.getResponse().getWriter();

        }catch (Exception e){
            out.print("error");
            log.error("editLoan action error:", e);
        }
        try {
            updateLoanInfo(false);

            out.print(lnLoanInfo.getLoanId());
        } catch (Exception e) {
//            if(null!=out){
//                out.print(lnLoanInfo.getLoanId());
//            }


//            }
            out.print("error");
            log.error("editLoan action error:", e);
        }
    }

    /**
     * 新增客户
     */
    public void addCustomer() {
        try {
        	
        	Map<String,Object> param = new HashMap<String,Object>();
        	param.put("idCard", idCard);

        	List<BaseCrmCustomer> baseCrmCustomerList = crmCustomerService.getCrmCustomerByIdCard(param);

        	CrmCustomer crmCustomer = new CrmCustomer(); //保存新客户
	    	if(baseCrmCustomerList != null && baseCrmCustomerList.size() >= 1){//说明当前用户已经存在
//	    		baseCrmCustomerList.get(0).getCustomerId()
                crmCustomer = crmCustomerService.getCustomerInfo(baseCrmCustomerList.get(0).getCustomerId());

	    	}
            crmCustomer.setIsReceiveSms(1);
            crmCustomer.setCustomerName(customerName);
            crmCustomer.setIdCard(idCard);
            if (phone != null && phone.length() == 11 && phone.startsWith("1")){
                crmCustomer.setMobilePhone1(phone);
                crmCustomer.setDefaultPhoneType(1);
            }else {
                crmCustomer.setPhone(phone);
                crmCustomer.setDefaultPhoneType(3);
            }
            if(StringUtils.isNotBlank(company)){
            	crmCustomer.setCompany(company);
            }
            crmCustomer.setCredentialTypeId(credentialTypeId);
            crmCustomer.setBelongUserId(this.getLoginInfo().getUserId());
            crmCustomer.setBelongDeptId(this.getLoginInfo().getDeptId());
            CustomerExtendFieldBean exField = new CustomerExtendFieldBean();
            Map<String,Object> paramMao = new HashMap<String,Object>();
            paramMao.put("loanId", loanId);
            if(loanId!=null) {
                CrmCustomer myboss = new CrmExportBean();
                LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(paramMao).get(0);
                if (lnLoanInfo != null) {
                    myboss = crmCustomerService.getCustomerInfo(lnLoanInfo.getCustomerId());
                    if (StringUtils.isBlank(myboss.getCustomerNo())) {
                        crmCustomerService.saveCustomer(crmCustomer, exField);
                    }
                }
            }else{
                crmCustomerService.saveCustomer(crmCustomer, exField);
            }
            crmCustomer.getCustomerId();
            PrintWriter out = this.getResponse().getWriter();
            out.print(crmCustomer.getCustomerId());

        } catch (Exception e) {
            log.error("addLoan action error:", e);
        }
    }

    /**
     * 新增客户
     */
    public void updateCustomer() {
        try {
            BaseCrmCustomer crmCustomer = crmCustomerService.getCrmCustomerById(customerId);
            this.matchCustomerInfo(crmCustomer);
            crmCustomerService.updateCrmCustomer(crmCustomer);
            crmCustomer.getCustomerId();
            PrintWriter out = this.getResponse().getWriter();
            out.print(crmCustomer.getCustomerId());
        } catch (Exception e) {
            log.error("addLoan action error:", e);
        }
    }

    /**
     * 当客户基本信息被更改时，对客户的拼音、性别、称呼、年龄、生日等信息，做一些匹配
     * @param crmCustomer
     */
    private void matchCustomerInfo(BaseCrmCustomer crmCustomer) {
        if (StringUtils.isNotEmpty(customerName) && !customerName.trim().equals("")) {
            //如果名字改变了，就要自动生成拼音，并把性别、称呼置空
            if (!customerName.equals(crmCustomer.getCustomerName())){
                crmCustomer.setCustomerName(customerName);
                crmCustomer.setCustomerNamePinyin(ImportUtil.getPinYinHeadChar(crmCustomer.getCustomerName()));
                crmCustomerService.setCustomerNickName(crmCustomer);
//                if (StringUtils.isNotEmpty(crmCustomer.getSex())){
//                    if (crmCustomer.getSex().equals("男")){
//                        crmCustomer.setCustomerTitle(crmCustomer.getCustomerName()+"先生");
//                    }else if (crmCustomer.getSex().equals("女")){
//                        crmCustomer.setCustomerTitle(crmCustomer.getCustomerName()+"女士");
//                    }
//                }
//                crmCustomer.setSex("");
//                crmCustomer.setCustomerTitle("");
            }
        }
        if (StringUtils.isNotEmpty(idCard) && !idCard.trim().equals("")) {
            if (crmCustomer.getIdCard() == null || !idCard.equals(crmCustomer.getIdCard())){
                if (IDCardUtil.validateCard(idCard) ) {//生日的特殊处理。
                    crmCustomer.setBirthday(ImportUtil.parseStringToDateBeforeToday(IDCardUtil.getBirthByIdCard(idCard)));
                }
                crmCustomer.setIdCard(idCard);
            }
        }
        if (StringUtils.isNotEmpty(phone)) {
            String mobilePhone1 = crmCustomer.getMobilePhone1();
            String mobilePhone2 = crmCustomer.getMobilePhone2();
            String fixedPhone = crmCustomer.getPhone();
            String fax = crmCustomer.getFax();
            if ((mobilePhone1 == null || !mobilePhone1.equals(phone)) && (mobilePhone2 == null || !mobilePhone2.equals(phone))
                    && (fixedPhone == null || !fixedPhone.equals(phone))
                    && (fax == null || !fax.equals(phone))){
                //不等于客户原联系方式中的任何一个
                Integer defaultPhoneType = crmCustomer.getDefaultPhoneType();
                if (phone.length() == 11 && phone.startsWith("1")){
                    //手机
                    if (mobilePhone1 == null || mobilePhone1.trim().equals("")){
                        //如果手机1为空，则插入到手机1，设置手机1为默认号码
                        crmCustomer.setMobilePhone1(phone);
                        crmCustomer.setDefaultPhoneType(1);
                    }else if (mobilePhone2 == null || mobilePhone2.trim().equals("")){
                        //如果手机2为空，则插入到手机2，设置手机2为默认号码
                        crmCustomer.setMobilePhone2(phone);
                        crmCustomer.setDefaultPhoneType(2);
                    }else {
                        //如果手机1为空，手机2不为空，则更新手机1的号码，并且随着手机1为默认号码
                        crmCustomer.setMobilePhone1(phone);
                        crmCustomer.setDefaultPhoneType(1);
                    }
                }else {
                    //固话
                    if (fixedPhone == null || fixedPhone.trim().equals("")){
                        //如果固话为空，则插入到固话
                        crmCustomer.setPhone(phone);
                    }else {
                        //如果固话不为空，则更新固话
                        crmCustomer.setPhone(phone);
                    }
                }
            }
        }
        
        
        if(StringUtils.isNotBlank(company)){
        	crmCustomer.setCompany(company);
        }
    }

    /**
     * 跳转到选择更新方式页面
     */
    public String toChooseAddUpdate() {
        try {
            if (StringUtils.isNotEmpty(request.getParameter("chooseType"))) {
                request.setAttribute("type", Integer.valueOf(request.getParameter("chooseType")));
            }
            return SUCCESS;
        } catch (Exception e) {
            log.error("toChooseAddUpdate action error:", e);
            return ERROR;
        }
    }

    /**
     * 提交贷款
     */
    public void submitLoan() {
        try {
            PrintWriter out = this.getResponse().getWriter();
            //首先判断贷款是否有录音
            Boolean hasAudio = this.hasAudio(loanId,customerId);
            /*if (!hasAudio){
                out.print("false");
            }else {*/
            	List<SysUser> sysUserChiefList = sysUserService.getSysUserTeamChiefByUserId(this.getLoginInfo().getUserId());
            	if(sysUserChiefList == null || sysUserChiefList.size() <= 0){
            		out.print("当前客户经理所在组织不存团队主管，无法进行提交分配！");
            		out.close();
            		return;
            	}
            	
                //更改贷款状态
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("loanId", loanId);
                //paramMap.put("applyUserId", this.getLoginInfo().getUserId());
                paramMap.put("applySubmitDate", Calendar.getInstance().getTime());
                paramMap.put("loanStatusId", 2); // 2 待分配
                paramMap.put("eventId", 2);
                if(assignUserId.equals(0)){
                	paramMap.put("assignUserId", sysUserChiefList.get(0).getUserId());  //分配人员Id
                }else{
                	paramMap.put("assignUserId",assignUserId);
                }
                
                lnLoanService.updateLnLoanById(paramMap);
                
                //贷款历史操作
                LnOpHistory lnOpHistory = new LnOpHistory();
                lnOpHistory.setLoanId(loanId);
                lnOpHistory.setOpHistoryDate(Calendar.getInstance().getTime());
                lnOpHistory.setUserId(this.getLoginInfo().getUserId());
                lnOpHistory.setContent("贷款提交");
                lnOpHistory.setRemark(applyRemark);
                lnOpHistory.setBeforeStatusId(1);
                lnOpHistory.setAfterStatusId(2);
                lnOpHistoryService.insertLnOpHistory(lnOpHistory);
                
                //备份所有的申请资料信息
                lnLoanInfoService.bakAppForm(loanId);
                out.print("success");
          //  }
            out.close();
        } catch (Exception e) {
        	e.printStackTrace();	
            log.error("addLoan action error:", e);
        }
    }



    public void jytj(){
        Map<String,Object> param = new HashMap<String,Object>();
        try {
            PrintWriter out = this.getResponse().getWriter();

        param.put("loanId", loanId);
        CrmCustomer myboss= new CrmExportBean();
        LnLoanInfo  lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
            Integer jisu=0;
        List<LnLoanGuarantorBean> guarantorList = lnLoanGuarantorService.getAllLnLoanGuarantorBeanByConds(param);
           if(lnLoanInfo!=null){
               myboss=crmCustomerService.getCustomerInfo(lnLoanInfo.getCustomerId());
               if(StringUtils.isBlank(myboss.getCustomerNo())){
                   jisu++;
               }
           }
            if(guarantorList.size()!=0){
                for (int i = 0; i < guarantorList.size(); i++) {
                    myboss=crmCustomerService.getCustomerInfo(guarantorList.get(i).getCustomerId());
                    if(StringUtils.isBlank(myboss.getCustomerNo())){
                        jisu++;
                    }
                }
            }
            if(jisu!=0){
                out.print("false");
            }else{
                out.print("success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 是否有录音
     */
    private Boolean hasAudio(Integer loanId,Integer customerId){
        try {
            //得到当前贷款相当的所有资料
            Map<String,Object> photoMap = new HashMap<String, Object>();
            photoMap.put("loanId",loanId);
            photoMap.put("statistics",1);
            List<LoanData> loanDataList = customerDataService.getAllLoanDataById(photoMap);
            Integer hasAudioInt = this.processLoanPersonData(loanDataList,customerId);
            if (hasAudioInt.equals(1)){
                //没有录音，不能提交申请
                return false;
            }else if (hasAudioInt.equals(0)){
                //有录音，可以提交申请
                return true;
            }
            return false;
        }catch (Exception e){
            log.error("AskLoanAction % hasAudio",e);
            return false;
        }
    }

    /**
     * 判断贷款人是否有满足最低贷款要求的‘调查录音’和‘相关照片’
     * @param loanDataList
     * @param loanCusId
     * @return
     */
    private Integer processLoanPersonData(List<LoanData> loanDataList,Integer loanCusId){
        // 是否存在贷款人的录音资料 0、不存在;1、存在
        int hasAudioForLoanPerson = 0;
        for (LoanData loanData : loanDataList) {
            Integer dataType = loanData.getDataType();
            Integer queryCusId = loanData.getCustomerId();
            if (!loanCusId.equals(queryCusId)){
                continue;
            }
            if (dataType != null && dataType == 2) {
                // 录音
                // 找到了贷款人的录音资料
                if (hasAudioForLoanPerson != 1) {
                    hasAudioForLoanPerson = 1;
                }
            }
        }
        // 判断贷款人是否有完整的资料
        if (hasAudioForLoanPerson == 0) {
            // 贷款人没有调查录音资料
            return 1;// 提示：贷款审批至少需有一段调查录音！
        }
        return 0;
    }

    /**
     * 编辑状态下提交贷款
     */
    public void submitLoanEdit() {
        try {
            PrintWriter out = this.getResponse().getWriter();
            //首先判断贷款是否有录音
            /*Boolean hasAudio = this.hasAudio(loanId,customerId);
            if (!hasAudio){
                out.print("false");
            }else {*/
	        	List<SysUser> sysUserChiefList = sysUserService.getSysUserTeamChiefByUserId(this.getLoginInfo().getUserId());
	        	if(sysUserChiefList == null || sysUserChiefList.size() <= 0){
	        		out.print("当前客户经理所在组织不存团队主管，无法进行提交分配！");
	        		out.close();
	        		return;
	        	}
                //更改贷款状态
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("loanId", loanId);
                //paramMap.put("applyUserId", this.getLoginInfo().getUserId());
                paramMap.put("applySubmitDate", Calendar.getInstance().getTime());
                paramMap.put("loanStatusId", 2); // 2 待分配
                paramMap.put("eventId", 2);
                if(assignUserId.equals(0)){
                	paramMap.put("assignUserId", sysUserChiefList.get(0).getUserId());  //分配人员Id
                }else{
                	paramMap.put("assignUserId",assignUserId);
                }
                //paramMap.put("assignUserId", sysUserChiefList.get(0).getUserId());  //分配人员Id
                lnLoanService.updateLnLoanById(paramMap);

               
                //贷款历史操作
                LnOpHistory lnOpHistory = new LnOpHistory();
                lnOpHistory.setLoanId(loanId);
                lnOpHistory.setOpHistoryDate(Calendar.getInstance().getTime());
                lnOpHistory.setUserId(this.getLoginInfo().getUserId());
                lnOpHistory.setContent("贷款编辑提交");
                lnOpHistory.setRemark(applyRemark);
                lnOpHistory.setBeforeStatusId(1);
                lnOpHistory.setAfterStatusId(2);
                lnOpHistoryService.insertLnOpHistory(lnOpHistory);

                //备份申请信息
                lnLoanInfoService.bakAppForm(loanId);
                
                out.print("success");

            //}
            out.close();
        } catch (Exception e) {
        	e.printStackTrace();
            log.error("addLoan action error:", e);
        }
    }

    //保存贷款信息
    private HashMap<String,Object> saveLoanInfo() {
    	
    	HashMap<String,Object> result = new HashMap<String,Object>();

    	//开始插入贷款主表
    	LnLoan loan = new LnLoan();
    	loan.setLoanId(lnLoanService.getNextLoanId());
    	loan.setLoanStatusId(1);
    	loan.setEventId(1);
    	loan.setCreateDate(new Date());
    	loan.setCreateUser(this.getLoginInfo().getUserId());
    	loan.setApplyUserId(this.getLoginInfo().getUserId());
    	loan.setApplySubmitDate(loan.getCreateDate());
    	lnLoanService.insertLoan(loan);
    	
    	logger.info("插入贷款基本数据...");
        //贷款基本信息
    	lnLoanInfo.setLoanId(loan.getLoanId());
    	lnLoanInfoService.insertLnLoanInfo(lnLoanInfo);
        logger.info("贷款基本数据插入成功!");

        //贷款资料
        saveLoanData(loan.getLoanId(),lnLoanInfo.getCustomerId());
        logger.info("保存贷款资料完成！");

        //贷款历史操作
        LnOpHistory lnOpHistory = new LnOpHistory();
        lnOpHistory.setLoanId(loan.getLoanId());
        lnOpHistory.setOpHistoryDate(Calendar.getInstance().getTime());
        lnOpHistory.setUserId(this.getLoginInfo().getUserId());
        lnOpHistory.setContent("新建贷款");
        lnOpHistory.setRemark(applyRemark);
        lnOpHistory.setAfterStatusId(1);
        logger.info("插入申请贷款历史记录...");
        lnOpHistoryService.insertLnOpHistory(lnOpHistory);
        logger.info("插入申请贷款历史记录完成!");
        
        //将主表id返回
        result.put("loanId", loan.getLoanId());
        return result;
    }

    //编辑贷款信息
    private void updateLoanInfo(Boolean isSubmit) {
    	Boolean updateCustomerDataTag=false;
    	Integer oldCustomerId=0;
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("loanId", lnLoanInfo.getLoanId());
    	LnLoanInfo oldLnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
    	if(!oldLnLoanInfo.getCustomerId().equals(lnLoanInfo.getCustomerId())){
    		updateCustomerDataTag=true;
    		oldCustomerId = oldLnLoanInfo.getCustomerId();
    	}
        logger.info("修改贷款基本信息...");
        lnLoanInfoService.updateLnLoanInfo(lnLoanInfo);
        //同步的更新我的客户信息
        if(lnLoanInfo.getCustomerId()!=null){
            SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
            CrmCustomer customer = crmCustomerService.getCustomerInfo(lnLoanInfo.getCustomerId());
            if(customer!=null){
                try {
                    customer.setSex(getTextValue(customer.getSex(),"男".equals(lnLoanInfo.getCusSex())?"男":"女"));
                    customer.setCustomerTitle("男".equals(customer.getSex()) ? customer.getCustomerName().substring(0, 1) + "先生" : customer.getCustomerName().substring(0, 1) + "女士");
                    customer.setCustomerTypeId(Integer.valueOf(getTextValue(customer.getCustomerTypeId(), 2)));//客户类型
//                    customer.setBirthday(sf1.parse(lnLoanInfo.getCusBirthday()));
                    customer.setCustomerIndustryId(Integer.valueOf(getTextValue(customer.getCustomerIndustryId(), 5)));
                    customer.setEducationalHistoryId(Integer.valueOf(getTextValue(customer.getEducationalHistoryId(),lnLoanInfo.getCusEducationId()==null?4:lnLoanInfo.getCusEducationId().substring(1))));//学历
                    customer.setCensusRegister(getTextValue(StringUtil.isEmpty(customer.getCensusRegister())?null:customer.getCensusRegister(),StringUtil.isEmpty(lnLoanInfo.getCusAddress())?"广东省中山市":lnLoanInfo.getCusAddress()));//户籍所在地
                    customer.setHouseatt(getTextValue(customer.getHouseatt(),lnLoanInfo.getCusLivingStatusId()==null?6:lnLoanInfo.getCusLivingStatusId().substring(1)));//住宅属性
                    customer.setAddress(getTextValue(StringUtil.isEmpty(customer.getAddress())?null:customer.getAddress(),StringUtil.isEmpty(lnLoanInfo.getCusLivingAddress())?"广东省中山市":lnLoanInfo.getCusLivingAddress()));//户籍地址
                    customer.setLivingConditionId(Integer.valueOf(getTextValue(customer.getLivingConditionId(),lnLoanInfo.getCusLivingStatusId()==null?8:lnLoanInfo.getCusLivingStatusId().substring(1))));//居住状况
                    customer.setDwellCode(getTextValue(customer.getDwellCode(),"528400"));//邮编
                    customer.setLivingAddress(getTextValue(StringUtil.isEmpty(customer.getLivingAddress())?null:customer.getLivingAddress(),StringUtil.isEmpty(lnLoanInfo.getCusLivingAddress())?"广东省中山市":lnLoanInfo.getCusLivingAddress()));//居住地址
                    customer.setCompany(getTextValue(StringUtil.isEmpty(customer.getCompany())?null:customer.getCompany(),StringUtil.isEmpty(lnLoanInfo.getCompanyName())?"广东省中山市":lnLoanInfo.getCompanyName()));//工作单位
                    customer.setUnitProperty(getTextValue(StringUtil.isEmpty(lnLoanInfo.getCompanyNature())?null:lnLoanInfo.getCompanyNature(),StringUtil.isEmpty(customer.getUnitProperty())?"私企":customer.getUnitProperty()));//单位性质
                    customer.setPosition(getTextValue(customer.getPosition(),4));//工作岗位
                    customer.setWorkingSeniority(getTextValue(StringUtil.isEmpty(customer.getWorkingSeniority())?null:customer.getWorkingSeniority(),StringUtil.isEmpty(lnLoanInfo.getCompanyWorkYear())?3:lnLoanInfo.getCompanyWorkYear()));//工作年限
                    customer.setRank(getTextValue(StringUtil.isEmpty(customer.getRank()) ? null : customer.getRank(), 1));//职称
                    customer.setOccupation(getTextValue(StringUtil.isEmpty(customer.getOccupation())?null:customer.getOccupation(),11));//职业
                    customer.setBgnyear(getTextValue(StringUtil.isEmpty(customer.getBgnyear())?null:customer.getBgnyear(),2017));//工作年限
                    customer.setRemark(getTextValue(StringUtil.isEmpty(customer.getRemark())?null:customer.getRemark(),"客户良好!"));
                    if("05".equals(lnLoanInfo.getCusMarriage())) {
                        customer.setMaritalStatusId(Integer.valueOf(getTextValue(customer.getMaritalStatusId(),8)));
                    }else{
                        customer.setMaritalStatusId(Integer.valueOf(getTextValue(customer.getMaritalStatusId(),lnLoanInfo.getCusMarriage()==null?8:lnLoanInfo.getCusMarriage().substring(1))));
                    }
                    if("02".equals(lnLoanInfo.getCusMarriage())) {
                        //添加客户配偶信息
                        customer.setSpouseName(getTextValue(customer.getSpouseName(), lnLoanInfo.getCusMateName()));//配偶姓名
                        customer.setSpouseSex(customer.getSex().equals("男")?"女":"男");//配偶性别
                        customer.setSpouseIdCard(getTextValue(customer.getSpouseIdCard(),lnLoanInfo.getCusMateIdcard()));//配偶证件号
                        customer.setSpouseMobilePhone(getTextValue(customer.getSpouseMobilePhone(),lnLoanInfo.getCusMateMobilePhone()));//配偶手机号
                        customer.setSpousePhone(getTextValue(customer.getSpousePhone(),lnLoanInfo.getCusMatePhone()));//配偶电话
                        customer.setSpouseCompany(getTextValue(customer.getSpouseCompany(),lnLoanInfo.getCusCompanyName()));//配偶工作单位
                        customer.setSpouseUnitProperty(getTextValue(customer.getSpouseUnitProperty(),lnLoanInfo.getCusCompanyNature()));//单位性质
                        customer.setSpouseWorkingSeniority(getTextValue(customer.getSpouseWorkingSeniority(),lnLoanInfo.getCusMateWorkYear()));//工作年限
                        customer.setSpouseCompanyPhone(getTextValue(customer.getSpouseCompanyPhone(),lnLoanInfo.getCusMateCompanyPhone()));//单位电话
                        customer.setSpouseCompanyAddress(getTextValue(customer.getSpouseCompanyAddress(),lnLoanInfo.getCusMateAddress()));//单位地址
                        customer.setSpousePosition(getTextValue(customer.getSpousePosition(),6));//职位
                        customer.setSpouseRank(getTextValue(customer.getSpouseRank(),1));//职务
                        Map<String,Object> map = new HashMap<String, Object>();
                        if(StringUtils.isNotEmpty(lnLoanInfo.getCusMateIdcard())) {
                            map.put("idCard", lnLoanInfo.getCusMateIdcard());
                        }
                        if(StringUtils.isEmpty(lnLoanInfo.getCusMateName())){
                            map.put("customerName",lnLoanInfo.getCusMateName());
                        }
//                        map.put("credentialTypeId", 1);
                        List<BaseCrmCustomer> matelist = crmCustomerService.getCrmCustomerByIdCard(map);
                        if(matelist.isEmpty()){
                            CrmCustomer crmCustomer = new CrmCustomer();
                            crmCustomer.setIsReceiveSms(1);
                            crmCustomer.setCustomerName(lnLoanInfo.getCusMateName());
                            crmCustomer.setIdCard(lnLoanInfo.getCusMateIdcard());

                            crmCustomer.setCredentialTypeId(1);
                            writeCustomerInfo(customer,lnLoanInfo,crmCustomer);
                            CustomerExtendFieldBean exField = new CustomerExtendFieldBean();
                            Map<String,Object> paramMao = new HashMap<String,Object>();
                            paramMao.put("loanId", lnLoanInfo.getLoanId());
                            if(lnLoanInfo.getLoanId()!=null) {
                                CrmCustomer myboss = new CrmExportBean();
                                LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(paramMao).get(0);
                                if (lnLoanInfo != null) {
                                    myboss = crmCustomerService.getCustomerInfo(lnLoanInfo.getCustomerId());
                                    if (StringUtils.isBlank(myboss.getCustomerNo())) {
                                        crmCustomerService.saveCustomer(crmCustomer, exField);
                                    }
                                }
                            }else{
                                crmCustomerService.saveCustomer(crmCustomer, exField);
                            }

                        }else{
                            for(BaseCrmCustomer crmCustomer:matelist) {
//                                crmCustomerService.updateCrmCustomer(crmCustomer);
                                CrmCustomer crmCustomerInfo = crmCustomerService.getCustomerInfo(crmCustomer.getCustomerId());
                                writeCustomerInfo(customer, lnLoanInfo, crmCustomerInfo);
                                crmCustomerService.updateCrmCustomer(crmCustomerInfo);
                            }
                        }

                    }

                crmCustomerService.updateCrmCustomer(customer);




                }catch (Exception e){
                    logger.error("",e);
                }


            }
        }
        //同步更新我的客户信息结束
        logger.info("修改贷款基本信息完成！");

        //贷款历史操作
        if (!isSubmit && StringUtils.isNotEmpty(applyRemark) && !applyRemark.trim().equals("")){
            //编辑并直接提交，此时不需要记录添加备注的历史记录
            LnOpHistory lnOpHistory = new LnOpHistory();
            lnOpHistory.setLoanId(loanId);
            lnOpHistory.setOpHistoryDate(Calendar.getInstance().getTime());
            lnOpHistory.setUserId(this.getLoginInfo().getUserId());
            lnOpHistory.setContent("添加备注");
            lnOpHistory.setRemark(applyRemark);
            lnOpHistory.setAfterStatusId(1);
            lnOpHistoryService.insertLnOpHistory(lnOpHistory);
        }
        logger.info("保存贷款申请资料...");
        //申请人资料
        if(updateCustomerDataTag){
        	updateLoanData(lnLoanInfo.getLoanId(),oldCustomerId,lnLoanInfo.getCustomerId());
        }
        //saveLoanData(lnLoanInfo.getCustomerId());
        logger.info("保存贷款申请资料完成!");
   	}
    private void writeCustomerInfo(CrmCustomer customer,LnLoanInfo lnloaninfo,BaseCrmCustomer crmCustomer){
        if (lnLoanInfo.getCusMateMobilePhone() != null &&lnLoanInfo.getCusMateMobilePhone().length() == 11 && lnLoanInfo.getCusMateMobilePhone().startsWith("1")){
            crmCustomer.setMobilePhone1(lnLoanInfo.getCusMateMobilePhone());
            crmCustomer.setDefaultPhoneType(1);
        }else {
            crmCustomer.setPhone(lnLoanInfo.getCusMatePhone());
            crmCustomer.setDefaultPhoneType(3);
        }
        crmCustomer.setBelongUserId(this.getLoginInfo().getUserId());
        crmCustomer.setBelongDeptId(this.getLoginInfo().getDeptId());
        crmCustomer.setSex("男".equals(customer.getSex()) ? "女" : "男");
        crmCustomer.setCustomerTitle("男".equals(crmCustomer.getSex()) ? lnLoanInfo.getCusMateName().substring(0, 1) + "先生" : lnLoanInfo.getCusMateName().substring(0, 1) + "女士");
        crmCustomer.setCustomerTypeId(Integer.valueOf(getTextValue(crmCustomer.getCustomerTypeId(), 2)));//客户类型
        crmCustomer.setCustomerIndustryId(5);
        crmCustomer.setEducationalHistoryId(Integer.valueOf(getTextValue(crmCustomer.getEducationalHistoryId(), 4)));//学历
        crmCustomer.setCensusRegister(StringUtil.isEmpty(crmCustomer.getCensusRegister()) ? "广东省中山市" : crmCustomer.getCensusRegister());//户籍所在地
        crmCustomer.setHouseatt(StringUtil.isEmpty(crmCustomer.getHouseatt()) ? "6" : crmCustomer.getHouseatt());//住宅属性
        crmCustomer.setAddress(StringUtil.isEmpty(crmCustomer.getAddress()) ? "广东省中山市" : crmCustomer.getAddress());//户籍地址
        crmCustomer.setLivingConditionId(Integer.valueOf(getTextValue(crmCustomer.getLivingConditionId(), 8)));//居住状况
        crmCustomer.setDwellCode("528400");//邮编
        crmCustomer.setLivingAddress(StringUtil.isEmpty(crmCustomer.getLivingAddress()) ? (StringUtil.isEmpty(crmCustomer.getLivingAddress()) ? "广东省中山市" : crmCustomer.getLivingAddress()) : crmCustomer.getLivingAddress());//居住地址
        crmCustomer.setCompany(StringUtil.isEmpty(crmCustomer.getCompany()) ? "广东省中山市" : crmCustomer.getCompany());//工作单位
        crmCustomer.setUnitProperty(StringUtil.isEmpty(crmCustomer.getUnitProperty()) ? "私企" : crmCustomer.getUnitProperty());//单位性质
        crmCustomer.setPosition(StringUtil.isEmpty(crmCustomer.getPosition()) ? "4" : crmCustomer.getPosition());//工作岗位
        crmCustomer.setWorkingSeniority(StringUtil.isEmpty(crmCustomer.getWorkingSeniority()) ? "3" : crmCustomer.getWorkingSeniority());//工作年限
        crmCustomer.setRank(StringUtil.isEmpty(crmCustomer.getRank())?"1":crmCustomer.getRank());//职称 1
        crmCustomer.setOccupation(StringUtil.isEmpty(crmCustomer.getOccupation()) ? "11" : crmCustomer.getOccupation());//职业 11
        crmCustomer.setBgnyear(StringUtil.isEmpty(crmCustomer.getBgnyear()) ? "2017" : crmCustomer.getBgnyear());//工作年限 2017
        crmCustomer.setRemark(StringUtil.isEmpty(crmCustomer.getRemark()) ? "配偶客户,客户良好!":crmCustomer.getRemark());
        crmCustomer.setMaritalStatusId(2);
        crmCustomer.setSpouseName(customer.getCustomerName());//配偶姓名
        crmCustomer.setSpouseSex(customer.getSex().equals("男")?"男":"女");
        crmCustomer.setSpouseIdCard(customer.getIdCard());
        crmCustomer.setSpouseMobilePhone(lnLoanInfo.getCusMobilePhone());
        crmCustomer.setSpousePhone(lnLoanInfo.getCusPhone());
        crmCustomer.setSpouseCompany(customer.getCompany());
        crmCustomer.setSpouseUnitProperty(customer.getUnitProperty());
        crmCustomer.setSpouseWorkingSeniority(customer.getWorkingSeniority());
        crmCustomer.setSpouseCompanyAddress(getTextValue(customer.getCompany(),lnLoanInfo.getCompanyAddress()));
        crmCustomer.setSpousePosition(getTextValue(customer.getPosition(),6));
        crmCustomer.setSpouseRank(getTextValue(customer.getSpouseRank(),1));
    }

    private String getTextValue(Object value1, Object value2){
        try{
            StringBuffer value = new StringBuffer("");
            if (null != value1) {
                value.append(value1);
            } else if (null != value2) {
                value.append(value2);
            }
            return value.toString();
        }catch (Exception e){
            return "";
        }
    }
    //保存申请人贷款资料
    private void saveLoanData(Integer loanId ,Integer oldCustomerId) {
    	 for (int i = 1; i <=7; i++) {
             CustomerData data = new CustomerData();
             data.setLoanId(loanId);
             data.setCustomerId(oldCustomerId);
             data.setEventId(i);
             data.setUploadUserId(this.getLoginInfo().getUserId());
 
             customerDataService.addNewCustomerData(data);
         }
    }
    private void updateLoanData(Integer loanId ,Integer oldCustomerId,Integer customerId) {
    	    	
    	Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loanId",loanId);
        paramMap.put("customerId",oldCustomerId);
        //移除DAT_CUSTOMER_DATA(资料表)表中的相关记录
        customerDataService.delCustomerData(paramMap);
        
        saveLoanData(loanId,customerId);
    }
    /**
     * 根据客户信息查询所有客户
     */
    public void initFindCustomerByConds() {
        PrintWriter out = null;
        try {
            out = this.getResponse().getWriter();
            String customerIds = request.getParameter("customerIds");
            customerIds = URLDecoder.decode(customerIds,"UTF-8");
            String customerName = request.getParameter("customerName");
            //log.error("1.initFindCustomerByConds: [customerIds]:"+customerIds+"  [customerName]:"+customerName);
            if(!StringUtil.isBlank(customerName)){
                customerName=URLDecoder.decode(customerName,"UTF-8");
            }
            String idCard = request.getParameter("idCard");
            String cphNumber = request.getParameter("cphNumber");
            String filter = request.getParameter("filter");
            Map<String, Object> conds = new HashMap<String, Object>();
            conds.put("fetchRows", "5");//取前五条
            conds.put("customerIds", customerIds);
            conds.put("customerName", customerName);
            conds.put("idCard", idCard);
            conds.put("cphNumber", cphNumber);
            if (StringUtils.isNotEmpty(filter)){
                //过滤掉非当前用户的归属客户
                conds.put("filter",filter);
                conds.put("userId",this.getLoginInfo().getUserId());

            }
            //过滤掉不良客户与三个月内有贷款审批失败的客户：开始
            conds.put("isNogood",0);
            SimpleDateFormat sf  =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//            Calendar calendar=Calendar.getInstance();
//            calendar.setTime(new Date());
//            calendar.add(2, -3);
//            String monthDate = sf.format(calendar.getTime());//从今天开始算三个月前的时间
//            conds.put("approveFailDate", monthDate);
            //过滤掉不良客户与三个月内有贷款审批失败的客户：结束

            //log.error("2.initFindCustomerByConds: [customerIds]:"+customerIds+"  [customerName]:"+customerName);
            List<LnLoanCustomerBean> listBean = lnLoanService.getAllCustomerBeanByConds(conds);

            JSONArray array = new JSONArray();
            for (LnLoanCustomerBean lnLoanCustomerBean : listBean) {
                JSONObject obj = new JSONObject();
                obj.put("customerId", lnLoanCustomerBean.getCustomerId());
                obj.put("customerName", lnLoanCustomerBean.getCustomerName());
                obj.put("replaceNumber", VmHelper.getHidePhoneNumber(lnLoanCustomerBean.getCphNumber()));
                obj.put("cphNumber", lnLoanCustomerBean.getCphNumber());
                obj.put("idCard", lnLoanCustomerBean.getIdCard());
                obj.put("replaceIDCard", VmHelper.getHideIdCard(lnLoanCustomerBean.getIdCard()));
                obj.put("credentialTypeId", lnLoanCustomerBean.getCredentialTypeId());
                obj.put("company", lnLoanCustomerBean.getCompany());
                array.add(obj);
            }
            out.write(array.toString());
        } catch (Exception e) {
            out.write("");
            log.error("initFindCustomerByConds error:", e);
        }
    }

    /**
     * 查询贷款子类型
     */
    public void getLoanSubTypeJson() {
        try {
            String loanTypeId = this.request.getParameter("loanTypeId");
            List<LnLoanSubType> cList = lnLoanTypeService.getLoanSubTypeList(Integer.valueOf(loanTypeId));

            this.getResponse().setContentType("text/html;charset=utf-8");
            PrintWriter out = this.getResponse().getWriter();
            Map<String, Object> map = new HashMap<String, Object>();
            JSONArray ja = new JSONArray();
            for (LnLoanSubType c : cList) {
                map.put("code", c.getLoanSubTypeId());
                map.put("name", c.getLoanSubTypeName());
                ja.add(map);
            }
            out.print(ja.toString());
        } catch (Exception e) {
            log.error("getLoanSubTypeJson action error:", e);
        }
    }

    /**
     * 上传贷款需要图片
     */
    public void upLoadLoanPhotoFile() {
    	//TODO:修改
    	
    	
        if (logoImage != null){
            String type = FileType.getFileByFile(logoImage);
            Map<String, String> map = new HashMap<String, String>();
            try {
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");

                PrintWriter out = response.getWriter();
                if (type == null || (!type.toLowerCase().equals("jpg") && !type.toLowerCase().equals("jpeg")
                        && !type.toLowerCase().equals("gif") && !type.toLowerCase().equals("png")
                        && !type.toLowerCase().equals("bmp"))) {
                    map.put("error", "目前只支持bmp、jpg、jpeg、gif、png图像文件格式上传！");
                    out.append(JSONObject.fromObject(map).toString());
                    return;
                }
                String fileName = logoImage.getName();
                String fileInputName = fileName.substring(0,fileName.lastIndexOf(".")+1);
                String strLoanId = request.getParameter("loanId");
                String caseNo = "";
                CustomerData customerData = new CustomerData();
                if (StringUtils.isNotEmpty(strLoanId)){
                    Integer loanId = Integer.parseInt(strLoanId);
                    customerData.setLoanId(loanId);
                    customerData.setCreateUser(this.getLoginInfo().getUserId());
                    customerData.setUploadUserId(this.getLoginInfo().getUserId());
                    LnLoan cusLoan = lnLoanService.getLnLoanById(loanId);
                    if (cusLoan != null){
                        customerData.setCustomerId(cusLoan.getCustomerId());
                    }
                    caseNo = caseHelperService.getCaseNo(customerData);
                }
                Integer fileId = sysUploadFileService.saveFile(logoImage,fileInputName+type,this.getLoginInfo().getUserId(),true,caseNo,customerData);
                //根据文件fileId找到文件的路径
                SysUploadFile sysUploadFile = sysUploadFileService.getSysUploadFileById(fileId);
                map.put("fileId",fileId+"");
                map.put("fullPath",sysUploadFile.getFilePath()+File.separator+sysUploadFile.getFileName());

                //处理完之后删除logoImage文件

            /*if (logoImage.length() > BUFFERED_SIZE * 1024) {
                map.put("error", "上传照片不能超过200K，请重新选择！");
                out.append(JSONObject.fromObject(map).toString());
                return;
            }
                String now = DateUtil.convertDateToString("yyyyMMdd", new Date());
                String savePath = sysParamService.getRecordPath() + "/loan/photo/" + now;
                File file = new File(savePath);
                if (!file.exists()) {// 文件不存在则创建
                    file.mkdirs();
                }

                // 生成新 文件名 pic_123_12312312312132.jpg
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
               String newFileName = "pic_0_" + Calendar.getInstance().getTimeInMillis() + "." + type;

                // 将文件上传到服务器
                File imageFile = new File(savePath + "/" + newFileName);
               copy(logoImage, imageFile);

                // 得到图片长宽

                BufferedImage buff = ImageIO.read(logoImage);

                // 判断图片长宽是否符合标准            if(buff.getWidth() > 500 || buff.getHeight() > 800){
                map.put("error", "上传照片尺寸须小于800X500，请重新选择！");
                out.append(JSONObject.fromObject(map).toString());
                return;
            }
                map.put("width", buff.getWidth() + "");
                map.put("height", buff.getHeight() + "");

                // 返回成功信息
                map.put("fname", newFileName);
                map.put("folder", now);
              map.put("fsize", logoImage.length() + "");
                map.put("fullPath", savePath + "/" + newFileName);*/
                out.append(JSONObject.fromObject(map).toString());
            } catch (Exception e) {
                try {
                    HttpServletResponse response = ServletActionContext.getResponse();
                    response.setCharacterEncoding("UTF-8");
                    PrintWriter out = response.getWriter();
                    map.put("error", "上传失败");
                    out.append(JSONObject.fromObject(map).toString());
                } catch (IOException e1) {
                    log.error(e1);
                }
                log.error("AskLoanAction % upLoadLoanPhotoFile",e);
            }
        }
    }
    

    //拒绝贷款
    public String refuseLoan(){
    	
    	String loanId = request.getParameter("loanId");
    	String refuseReasonTypeId = request.getParameter("refuseReasonTypeId");
    	
    	if(refuseReasonTypeId.equals("1")){//银行拒绝
    		request.setAttribute("refuseReasonSubTypeId",buildLoanCheckBoxMessage("YHJJ"));
    		request.setAttribute("title", "银行拒绝");
    		request.setAttribute("code", "YHJJ");
    	}else{//客户拒绝
    		request.setAttribute("refuseReasonSubTypeId",buildLoanCheckBoxMessage("KHJJ"));
    		request.setAttribute("title", "客户拒绝");
    		request.setAttribute("code", "KHJJ");
    	}
    	request.setAttribute("refuseReasonTypeId", refuseReasonTypeId);
    	request.setAttribute("loanId", loanId);
    	return SUCCESS;
    	
    }
    //拒绝贷款提交
    public void submitRefuseLoan(){
    	PrintWriter out = null;
    	try{
    		out = this.getResponse().getWriter();
    		Integer loanId = Integer.parseInt(request.getParameter("loanId"));
    		LnLoan lnLoan = lnLoanService.selectLoanById(loanId);
    		lnLoan.setLoanRejectUserId(this.getLoginInfo().getUserId());
    		lnLoan.setLoanRejectDate(new Date());
    		
    		String refuseReasonTypeId = request.getParameter("refuseReasonTypeId");
    		String refuseReasonSubTypeId = request.getParameter("refuseReasonSubTypeId");
    		String refuseContent = request.getParameter("refuseContent");
    		String refuseNote = request.getParameter("refuseNote");
    		LnLoanInfo lnLoanInfo = new LnLoanInfo();
    		lnLoanInfo.setLoanId(loanId);
    		lnLoanInfo.setRefuseLoanStateId(lnLoan.getLoanStatusId() + "");
    		lnLoanInfo.setRefuseReasonTypeId(refuseReasonTypeId);
    		lnLoanInfo.setRefuseReasonSubTypeId(refuseReasonSubTypeId);
    		lnLoanInfo.setRefuseContent(refuseContent);
    		
    		//贷款历史操作
            LnOpHistory lnOpHistory = new LnOpHistory();
            lnOpHistory.setLoanId(loanId);
            lnOpHistory.setOpHistoryDate(Calendar.getInstance().getTime());
            lnOpHistory.setUserId(this.getLoginInfo().getUserId());
            if(refuseReasonTypeId.equals("1")){
            	lnOpHistory.setContent("银行拒绝");
            	refuseNote = "银行拒绝理由：" + refuseNote;
            }else{
            	lnOpHistory.setContent("客户拒绝");
            	refuseNote = "客户拒绝理由：" + refuseNote;
            }
            lnOpHistory.setRemark(refuseNote);
            lnOpHistory.setBeforeStatusId(lnLoan.getLoanStatusId());
            lnOpHistory.setAfterStatusId(10 + lnLoan.getLoanStatusId());
    		
            //更改贷款状态
            lnLoan.setLoanStatusId(10 + lnLoan.getLoanStatusId());
            
            lnLoanService.submitApproveLoan(lnLoan,lnLoanInfo,lnOpHistory);
    		
    		out.print("success");
    	}catch(Exception e){
    		e.printStackTrace();
    		out.print("拒绝贷款异常，请刷新后重试或联系管理员！");
    	}
    }
    public String getJydBaseInfo(){
        lnProfitLossProdList = lnProfitLossProdService.selectProfitLossProdByPrimary(loanId);
        this.request.setAttribute("lnProfitLossProdList", lnProfitLossProdList);
        Double proRate = 0.00;

        for (LnProfitLossProd lnProfitLossProd : lnProfitLossProdList) {
            proRate += lnProfitLossProd.getSellingRate() * lnProfitLossProd.getGrossRate() / 100;
        }
        this.request.setAttribute("proRate", String.format("%.2f", proRate));
        //损益基本信息
        LnProfitLossBase lnProfitLossBase = lnProfitLossBaseService.selectProfitLossBaseByPrimary(loanId);
        if(lnProfitLossBase!=null) {

            SimpleDateFormat sim = new SimpleDateFormat("yyyy年MM月");
            String baseBeginTime = sim.format(lnProfitLossBase.getBeginTime());
            String baseEndTime = sim.format(lnProfitLossBase.getEndTime());
            this.request.setAttribute("baseBeginTime", baseBeginTime);
            this.request.setAttribute("baseEndTime", baseEndTime);
        }
        this.request.setAttribute("lnProfitLossBaseData", lnProfitLossBase);
        this.request.setAttribute("write", request.getParameter("write"));
        return SUCCESS;
    }

    //损益 基本信息 保存
    public void saveProLossBase(){
        //
        LnProfitLossBase lnProfitLossBase = new LnProfitLossBase();
        lnProfitLossBase.setLoanId(Integer.parseInt(request.getParameter("loanId")));

        try {
            if("1".equals(request.getParameter("applyId"))) {
                DateFormat fmt = new SimpleDateFormat("yyyy年MM月");
                lnProfitLossBase.setBeginTime(fmt.parse(request.getParameter("baseStartMonth")));
                lnProfitLossBase.setEndTime(fmt.parse(request.getParameter("baseEndMonth")));
                lnProfitLossBase.setMonthNumber(Integer.valueOf(request.getParameter("monthNumber")));
                lnProfitLossBase.setLastMonthRate(Integer.valueOf(request.getParameter("lastMonthRate")));
                lnProfitLossBase.setGrossRateRemark(request.getParameter("grossRateRemark"));
            }else{
                lnProfitLossBase.setMonthNumber(Integer.valueOf(request.getParameter("xfd_monthNum")));
            }
            lnProfitLossBase.setUpdateUser(this.getLoginInfo().getUserId());
            lnProfitLossBase.setUpdateDate(new Date());
            lnProfitLossBaseService.updateLnProfitLossBase(lnProfitLossBase);

            lnProfitLossProdService.deleteLnProfitLossItems(Integer.parseInt(request.getParameter("loanId")));
            lnProfitLossProdService.deleteDetailsByLoanId(Integer.parseInt(request.getParameter("loanId")));
            this.getResponse().setContentType("text/html;charset=utf-8");
            PrintWriter out = this.getResponse().getWriter();
            out.print("success");
        }catch (Exception e){
            e.printStackTrace();
            log.error("addPro action error",e);
        }

    }
    //损益 基本信息 添加
    public void addProLossBase(){
        String applyId = request.getParameter("applyId");
        Integer loanId = Integer.parseInt(request.getParameter("loanId"));
        LnProfitLossBase lnProfitLossBase = new LnProfitLossBase();
        lnProfitLossBase.setLoanId(loanId);
        lnProfitLossProdService.deleteDetailsByLoanId(loanId);
        lnProfitLossProdService.deleteLnProfitLossItems(loanId);
        if("1".equals(applyId)) {
            try {
                DateFormat fmt = new SimpleDateFormat("yyyy年MM月");
                lnProfitLossBase.setBeginTime(fmt.parse(request.getParameter("baseStartMonth")));
                lnProfitLossBase.setEndTime(fmt.parse(request.getParameter("baseEndMonth")));
            } catch (Exception e) {
                log.error("addProLossBase ---日期格式转换错误", e);
            }
            lnProfitLossBase.setMonthNumber(Integer.parseInt(request.getParameter("lastMonthRate")));
            lnProfitLossBase.setLastMonthRate(Integer.parseInt(request.getParameter("lastMonthRate")));
            lnProfitLossBase.setGrossRateRemark(request.getParameter("grossRateRemark"));
            lnProfitLossBase.setCreateUser(this.getLoginInfo().getUserId());
            lnProfitLossBase.setCreateDate(new Date());
        }else{
            lnProfitLossBase.setMonthNumber(Integer.valueOf(request.getParameter("xfd_monthNum")));
            lnProfitLossBase.setCreateUser(this.getLoginInfo().getUserId());
            lnProfitLossBase.setCreateDate(new Date());
        }
        lnProfitLossBaseService.insertLnProfitLossBase(lnProfitLossBase);
        this.getResponse().setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = this.getResponse().getWriter();
            out.print("success");
        }catch (Exception e){
            e.printStackTrace();
            log.error("addPro action error",e);
        }

    }

	 // 移除加权损益率
	public void removePro(){
		 try {
//             System.out.println("hello remove" );
//			System.out.println("removePro() lnProfitLossProdService :"+lnProfitLossProdService);
//			System.out.println("removePro() lnProfitLossProdList :"+lnProfitLossProdList);
//			System.out.println("removePro() profitLossId :"+profitLossId);
//			System.out.println("removePro() loanId :"+loanId);
            lnProfitLossProdService.deleteProd(profitLossId,loanId);
            lnProfitLossProdList = lnProfitLossProdService.selectProfitLossProdByPrimary(loanId);
             System.out.println("removePro--  ----lnProfitLossProdList"+lnProfitLossProdList);
            this.getResponse().setContentType("text/html;charset=utf-8");
            PrintWriter out = this.getResponse().getWriter();
             request.setAttribute("lnProfitLossProdList",lnProfitLossProdList);
             out.print("success");
        } catch (Exception e) {
            log.error("removePro action error:", e);
            e.printStackTrace();
        }
		
	}
    //损益查询
    public String queryPro(){
        lnProfitLossProdList = lnProfitLossProdService.selectProfitLossProdByPrimary(loanId);
        Double proRate = 0.00;
        for(LnProfitLossProd lnProfitLossProd : lnProfitLossProdList){
            proRate += lnProfitLossProd.getGrossRate()*lnProfitLossProd.getSellingRate()/100;
        }
        System.out.println("queryPro()   -----proRate = " + proRate);
        request.setAttribute("write",request.getParameter("write"));
        request.setAttribute("lnProfitLossProdList", lnProfitLossProdList);
        request.setAttribute("proRate",String.format("%.2f",proRate));
        return SUCCESS;
    }
    //损益修改
    public void editPro() {
        Integer loanId = Integer.parseInt(request.getParameter("loanId"));
        LnProfitLossProd lnProfitLossProd = new LnProfitLossProd();
        String productName = request.getParameter("productName");
        lnProfitLossProd.setProductName(productName);
        lnProfitLossProd.setLoanId(loanId);
        lnProfitLossProd.setProfitLossId( Integer.parseInt(request.getParameter("profitLossId")));
        System.out.println(request.getParameter("sellingPrice"));
        lnProfitLossProd.setSellingPrice(new BigDecimal(request.getParameter("sellingPrice")));
        String costPrice = request.getParameter("costPrice");
        System.out.println("costPrice......" + costPrice);
        lnProfitLossProd.setCostPrice(new BigDecimal(request.getParameter("costPrice")));
        lnProfitLossProd.setGrossValue(new BigDecimal(request.getParameter("grossValue")));
        lnProfitLossProd.setGrossRate(Double.parseDouble(request.getParameter("grossRate")));
        lnProfitLossProd.setSellingRate(Double.parseDouble(request.getParameter("sellingRate")));
        lnProfitLossProd.setRemark(request.getParameter("remark"));
        lnProfitLossProd.setUpdateDate(Calendar.getInstance().getTime());
        lnProfitLossProd.setUpdateUser(this.getLoginInfo().getUserId());
        lnProfitLossProdService.updateProd(lnProfitLossProd);
        this.getResponse().setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = this.getResponse().getWriter();
            out.print("success");
            out.close();
        }catch (Exception e){
            e.printStackTrace();
            log.error("editPro action error",e);
        }
    }
    // 跳转到添加加权损益率页面
    public void addPro() {
        Integer loanId = Integer.parseInt(request.getParameter("loanId"));
        System.out.println("addPro()-----------------------------------------"+loanId);
        LnProfitLossProd lnProfitLossProd = new LnProfitLossProd();
        lnProfitLossProd.setProductName(request.getParameter("productName"));
        lnProfitLossProd.setLoanId(loanId);
        lnProfitLossProd.setSellingPrice(new BigDecimal(request.getParameter("sellingPrice")));
        lnProfitLossProd.setCostPrice(new BigDecimal(request.getParameter("costPrice")));
        lnProfitLossProd.setGrossValue(new BigDecimal(request.getParameter("grossValue")));
        lnProfitLossProd.setGrossRate(Double.parseDouble(request.getParameter("grossRate")));
        lnProfitLossProd.setSellingRate(Double.parseDouble(request.getParameter("sellingRate")));
        lnProfitLossProd.setRemark(request.getParameter("remark"));
        lnProfitLossProd.setCreateDate(Calendar.getInstance().getTime());
        lnProfitLossProd.setCreateUser(this.getLoginInfo().getUserId());
        lnProfitLossProdService.insertProd(lnProfitLossProd);
        this.getResponse().setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = this.getResponse().getWriter();
            out.print("success");
            out.close();
        }catch (Exception e){
            e.printStackTrace();
            log.error("addPro action error",e);
        }
    }
	 // 跳转到添加加权损益率页面
    public String toAddPro() {
        Integer loanId = Integer.parseInt(request.getParameter("loanId"));
        request.setAttribute("loanId", loanId);
        System.out.println("-----------------------------------------" + loanId);
        return SUCCESS;
    }

    //修改加权
    public String toEditPro(){
//        System.out.print("toEditPro()..............");
        Integer loanId = Integer.parseInt(request.getParameter("loanId"));
        Integer profitLossId=Integer.parseInt(request.getParameter("profitLossId"));
        LnProfitLossProd ln = new LnProfitLossProd();
        ln.setLoanId(loanId);
        ln.setProfitLossId(profitLossId);
        List<LnProfitLossProd> listPro =lnProfitLossProdService.selectProfitLossProdByPrimary(ln);
        request.setAttribute("listProd",listPro.get(0));
//        System.out.println("toEditProd().......getProductName()......." + listPro.get(0).getProductName());
       return  SUCCESS;
    }
    // 跳转到添加共同借贷人
    public String toAddCo() {
        request.setAttribute("customerIds", request.getParameter("customerIds"));
        request.setAttribute("applyCusIdCard",request.getParameter("applyCusIdCard"));
        request.setAttribute("guCusIdCards",request.getParameter("guCusIdCards"));
        request.setAttribute("coCustomerIdCards",request.getParameter("coCustomerIdCards"));
        return SUCCESS;
    }

    //添加借贷人
    public void addCo() {
        try {
        	
	    	 this.getResponse().setContentType("text/html;charset=utf-8");
	         PrintWriter out = this.getResponse().getWriter();
	         String msg = checkCoBorrowerOrGuVaild();
	         if(!msg.equals("null")){//说明当前用户已存在
	        	 out.write(msg);
	        	 return;
	         }
            CustomerData data = new CustomerData();
            data.setLoanId(loanId);
            data.setCustomerId(customerId);
            data.setEventId(3);//调查事件中
            data.setUploadUserId(this.getLoginInfo().getUserId());
            data.setCreateDate(Calendar.getInstance().getTime());
            data.setCreateUser(this.getLoginInfo().getUserId());
            customerDataService.addNewCustomerData(data);

            //同步共同借款人信息
            CrmCustomer customer = crmCustomerService.getCustomerInfo(customerId);
            if(customer!=null){
                writeCustomerData(customer);
                crmCustomerService.updateCrmCustomer(customer);
            }

            LnLoanCoBorrower co = new LnLoanCoBorrower();
            co.setLoanId(loanId);
            co.setCustomerId(customerId);
            co.setCompanyAddress(request.getParameter("companyAddress"));
            co.setPetitionerRelate(request.getParameter("petitionerRelate"));
            lnLoanCoBorrowerService.addLnLoanCoBorrower(co);

            out.print("success");
        } catch (Exception e) {
        	e.printStackTrace();
            log.error("addCo action error:", e);
        }
    }

    /**
     * 编辑共同借贷人
     */
    public void editCo(){
     try{
         this.getResponse().setContentType("text/html;charset=utf-8");
         PrintWriter out = this.getResponse().getWriter();
         String company = request.getParameter("company");
         String companyAddress = request.getParameter("companyAddressNew");
         String petitionerRelate = request.getParameter("petitionerRelate");
         //更新借款人信息
         Map<String,Object> map = new HashMap<String, Object>();
         if(StringUtils.isNotEmpty(companyAddress) && !companyAddress.trim().equals("")){
             map.put("companyAddress",companyAddress);
         }
         if(StringUtils.isNotEmpty(petitionerRelate) && !petitionerRelate.trim().equals("")){
             map.put("petitionerRelate",petitionerRelate);
         }

         map.put("loanId",loanId);
         map.put("oldCustomerId",customerId);
         lnLoanCoBorrowerService.updateCoBorrower(map);
         //更新客户信息
         CrmCustomer crmCustomer = new CrmCustomer();
         if(StringUtils.isNotEmpty(company) && !company.trim().equals("")){
             crmCustomer.setCompany(company);
         }
         crmCustomer.setCustomerId(customerId);
         crmCustomerService.updateCustomerRandom(crmCustomer);

         out.print("success");


     }catch (Exception e){
         e.printStackTrace();
         log.error("editCo action error:", e);
     }
   }
    //给我的客户填充默认数据
    private void writeCustomerData(CrmCustomer customer){
        customer.setSex(StringUtil.isEmpty(customer.getSex())?"男":customer.getSex());
        customer.setCredentialTypeId(customer.getCustomerTypeId()==null?1:customer.getCustomerTypeId());
        customer.setCustomerTitle(customer.getCustomerName().substring(0,1) + (customer.getSex().equals("男") ? "先生" : "女士"));
        customer.setCustomerTypeId(Integer.valueOf(getTextValue(customer.getCustomerTypeId(), 2)));//客户类型
        customer.setCustomerIndustryId(Integer.valueOf(getTextValue(customer.getCustomerIndustryId(), 5)));
        customer.setEducationalHistoryId(Integer.valueOf(getTextValue(customer.getEducationalHistoryId(), 4)));//学历
        customer.setCensusRegister(getTextValue(customer.getCensusRegister(), "东省中山市"));//户籍所在地
        customer.setHouseatt(getTextValue(customer.getHouseatt(),"6"));//住宅属性
        customer.setAddress(getTextValue(customer.getAddress(),"广东省中山市"));//户籍地址
        customer.setLivingConditionId(Integer.valueOf(getTextValue(customer.getLivingConditionId(),8)));//居住状况
        customer.setDwellCode("528400");//邮编
        customer.setLivingAddress(getTextValue(customer.getLivingAddress(),"广东省中山市"));//居住地址
        customer.setCompany(getTextValue(customer.getCompany(),"广东省中山市"));//工作单位
        customer.setUnitProperty(getTextValue(customer.getUnitProperty(),"私企"));//单位性质
        customer.setPosition(getTextValue(customer.getPosition(),"4"));//工作岗位
        customer.setWorkingSeniority(getTextValue(customer.getWorkingSeniority(),"3"));//工作年限
        customer.setRank(getTextValue(customer.getRank(),"1"));//职称 1
        customer.setOccupation(getTextValue(customer.getOccupation(),"11"));//职业 11
        customer.setBgnyear(getTextValue(customer.getBgnyear(),"2017"));//工作年限 2017
        customer.setRemark(getTextValue(customer.getRemark(),"客户良好!"));
        customer.setMaritalStatusId(Integer.valueOf(getTextValue(customer.getMaritalStatusId(),1)));
    }

    
    //判断当前共同担保人、借款人，不属于同一用户
    private String checkCoBorrowerOrGuVaild(){
    	
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("loanId", loanId);
        LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
        if(lnLoanInfo.getCustomerId().equals(customerId)){
        	return "当前客户为主借款人！";
        }
        List<LnLoanCoBorrowerBean> lnLoanCoBorrowerBeanList = lnLoanCoBorrowerService.getAllLnLoanCoBorrowerBeanByConds(param);
        for (LnLoanCoBorrowerBean lnLoanCoBorrowerBean : lnLoanCoBorrowerBeanList) {
			if(lnLoanCoBorrowerBean.getCustomerId() != null && lnLoanCoBorrowerBean.getCustomerId().equals(customerId)){// customerId.intValue()){
				return "当前用户已存在于共同借款人中！";
			}
		}
        
        List<LnLoanGuarantorBean> lnLoanGuarantorBeanList = lnLoanGuarantorService.getAllLnLoanGuarantorBeanByConds(param);
        for (LnLoanGuarantorBean lnLoanGuarantorBean : lnLoanGuarantorBeanList) {
        	if(lnLoanGuarantorBean.getCustomerId() != null && lnLoanGuarantorBean.getCustomerId().equals(customerId)){//intValue() == customerId.intValue()){
				return "当前用户已存在于担保人中！";
			}
		}
    	return "null";
    }


    
    
    //移除借贷人
    public void removeCo() {
        try {
            lnLoanService.rmCoBorrower(coId,loanId,customerId);
            this.getResponse().setContentType("text/html;charset=utf-8");
            PrintWriter out = this.getResponse().getWriter();
            out.print("success");
        } catch (Exception e) {
            log.error("addCo action error:", e);
            e.printStackTrace();
        }
    }

    //查找共同借贷人
    public String queryLoanCo() {
        try {
            Map<String,Object> photoMap = new HashMap<String, Object>();
            photoMap.put("loanId",loanId);
            photoMap.put("statistics",1);
            List<LoanData> list = customerDataService.getAllLoanDataById(photoMap);
            loanCoBorrowerList = lnLoanDetailService.getLoanCoList(loanId, list);
            Map<String,Integer> cusDataOnLoanPerson = new HashMap<String, Integer>();
            for (LoanData loanData : list){
                String key = "loanData"+loanData.getCustomerId()+loanData.getEventId()+loanData.getDataType();
                if (cusDataOnLoanPerson.containsKey(key)){
                    cusDataOnLoanPerson.put(key,cusDataOnLoanPerson.get(key)+1);
                }else {
                    cusDataOnLoanPerson.put(key,1);
                }
            }
            request.setAttribute("cusDataOnLoanPerson",cusDataOnLoanPerson);
            return SUCCESS;
        } catch (Exception e) {
            log.error("queryLoanCo action error:", e);
            return ERROR;
        }
    }

    // 跳转到添加担保人
    public String toAddGu() {
        request.setAttribute("customerIds", request.getParameter("customerIds"));
        request.setAttribute("applyCusIdCard",request.getParameter("applyCusIdCard"));
        request.setAttribute("coCusIdCards",request.getParameter("coCusIdCards"));
        request.setAttribute("guCustomerIdCards",request.getParameter("guCustomerIdCards"));
        return SUCCESS;
    }

    //添加担保人
    public void addGu() {
        try {
        	 this.getResponse().setContentType("text/html;charset=utf-8");
             PrintWriter out = this.getResponse().getWriter();
             String msg = checkCoBorrowerOrGuVaild();
	         if(!msg.equals("null")){//说明当前用户已存在
	        	 out.write(msg);
	        	 return;
	         }

            CustomerData data = new CustomerData();
            data.setLoanId(loanId);
            data.setCustomerId(customerId);
            data.setEventId(3);//调查事件中
            data.setUploadUserId(this.getLoginInfo().getUserId());
            customerDataService.addNewCustomerData(data);
            //同步担保人信息
            CrmCustomer customer = crmCustomerService.getCustomerInfo(customerId);
            if(customer!=null){
                writeCustomerData(customer);
                crmCustomerService.updateCrmCustomer(customer);
            }

            LnLoanGuarantor gu = new LnLoanGuarantor();
            gu.setLoanId(loanId);
            gu.setCustomerId(customerId);
            gu.setCompanyAddress(request.getParameter("companyAddress"));
            gu.setPetitionerRelate(request.getParameter("petitionerRelate"));
            lnLoanGuarantorService.addLnLoanGuarantor(gu);

            out.print("success");
        } catch (Exception e) {
            log.error("addCo action error:", e);
        }
    }

    /**
     * 编辑担保人
     */
    public void editGu(){
        try{
            this.getResponse().setContentType("text/html;charset=utf-8");
            PrintWriter out = this.getResponse().getWriter();
            String company = request.getParameter("company");
            String companyAddress = request.getParameter("companyAddressNew");
            String petitionerRelate = request.getParameter("petitionerRelate");
            //更新借款人信息
            Map<String,Object> map = new HashMap<String, Object>();
            if(StringUtils.isNotEmpty(companyAddress) && !companyAddress.trim().equals("")){
                map.put("companyAddress",companyAddress);
            }
            if(StringUtils.isNotEmpty(petitionerRelate) && !petitionerRelate.trim().equals("")){
                map.put("petitionerRelate",petitionerRelate);
            }

            map.put("loanId",loanId);
            map.put("oldCustomerId",customerId);
            map.put("updateDate",new Date());
            map.put("updateUser",this.getLoginInfo().getUserId());
            lnLoanGuarantorService.updateLoanGuarantor(map);
            //更新客户信息
//            CrmCustomer crmCustomer = new CrmCustomer();
            CrmCustomer crmCustomer = crmCustomerService.getCustomerInfo(customerId);
            if(StringUtils.isNotEmpty(company) && !company.trim().equals("")){
                crmCustomer.setCompany(company);
            }
            crmCustomer.setCustomerId(customerId);
            crmCustomerService.updateCustomerRandom(crmCustomer);

            out.print("success");


        }catch (Exception e){
            e.printStackTrace();
            log.error("editGu action error:", e);
        }
    }

    //移除担保人
    public void removeGu() {
        try {
            lnLoanService.rmGuarantor(guId,loanId,customerId);

            this.getResponse().setContentType("text/html;charset=utf-8");
            PrintWriter out = this.getResponse().getWriter();
            out.print("success");
        } catch (Exception e) {
            log.error("addCo action error:", e);
        }
    }

    //查找担保人
    public String queryLoanGu() {
        try {
            Map<String,Object> photoMap = new HashMap<String, Object>();
            photoMap.put("loanId",loanId);
            photoMap.put("statistics",1);
            List<LoanData> list = customerDataService.getAllLoanDataById(photoMap);
            loanGuarantorList = lnLoanDetailService.getLoanGuList(loanId, list);
            Map<String,Integer> cusDataOnLoanPerson = new HashMap<String, Integer>();
            for (LoanData loanData : list){
                String key = "loanData"+loanData.getCustomerId()+loanData.getEventId()+loanData.getDataType();
                if (cusDataOnLoanPerson.containsKey(key)){
                    cusDataOnLoanPerson.put(key,cusDataOnLoanPerson.get(key)+1);
                }else {
                    cusDataOnLoanPerson.put(key,1);
                }
            }
            request.setAttribute("cusDataOnLoanPerson",cusDataOnLoanPerson);
            return SUCCESS;
        } catch (Exception e) {
            log.error("queryLoanGu action error:", e);
            return ERROR;
        }
    }
    
    //添加信贷历史初始化
    public String toAddCh(){
    	request.setAttribute("lnLoanId",request.getParameter("lnLoanId"));
    	request.setAttribute("customerId", request.getParameter("customerId"));
    	return SUCCESS;
    }
    
    public void addCh(){
    	try{
    		lnCreditHistory.setCreateDate(new Date());
    		lnCreditHistoryService.insertCreditHistory(lnCreditHistory);
    		 this.getResponse().setContentType("text/html;charset=utf-8");
             PrintWriter out = this.getResponse().getWriter();
             out.print("success");
    	}catch (Exception e){
    		e.printStackTrace();
    	}
    	
    }

    public void editCh(){
        try{
            this.getResponse().setContentType("text/html;charset=utf-8");
            PrintWriter out = this.getResponse().getWriter();
            String  finaceSource = request.getParameter("finaceSource");
            String  loanPurpose = request.getParameter("loanPurpose");
            String  loanMoney = request.getParameter("loanMoney");
            String  loanDate = request.getParameter("loanDate");
            String  loanLimitYear = request.getParameter("loanLimitYear");
            String  repayMonth = request.getParameter("repayMonth");
            String  overdueInformation = request.getParameter("overdueInformation");
            String  balanceMoney = request.getParameter("balanceMoney");
            Integer  creditHistoryId = Integer.parseInt(request.getParameter("chId"))  ;
            logger.info("idwei_____"+creditHistoryId);

            LnCreditHistory lnCreditHistory1 = new LnCreditHistory();
            lnCreditHistory1.setCreditHistoryId(creditHistoryId);
            if(StringUtils.isNotEmpty(balanceMoney) && !balanceMoney.trim().equals("")){
                lnCreditHistory1.setBalanceMoney(balanceMoney);
            }
            if(StringUtils.isNotEmpty(finaceSource) && !finaceSource.trim().equals("")){
                lnCreditHistory1.setFinaceSource(finaceSource);
            }
            if(StringUtils.isNotEmpty(loanDate) && !loanDate.trim().equals("")){
                lnCreditHistory1.setLoanDate(loanDate);
            }
            if(StringUtils.isNotEmpty(loanLimitYear) && !loanLimitYear.trim().equals("")){
                lnCreditHistory1.setLoanLimitYear(loanLimitYear);
            }
            if(StringUtils.isNotEmpty(loanMoney) && !loanMoney.trim().equals("")){
                lnCreditHistory1.setLoanMoney(loanMoney);
            }
            if(StringUtils.isNotEmpty(loanPurpose) && !loanPurpose.trim().equals("")){
                lnCreditHistory1.setLoanPurpose(loanPurpose);
            }
            if(StringUtils.isNotEmpty(overdueInformation) && !overdueInformation.trim().equals("")){
                lnCreditHistory1.setOverdueInformation(overdueInformation);
            }
            if(StringUtils.isNotEmpty(repayMonth) && !repayMonth.trim().equals("")){
                lnCreditHistory1.setRepayMonth(repayMonth);
            }

            lnCreditHistoryService.updateCreditHistory(lnCreditHistory1);
            out.print("success");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

  //移除信贷历史
    public void removeCh() {
        try {
            this.getResponse().setContentType("text/html;charset=utf-8");
            PrintWriter out = this.getResponse().getWriter();
        	if(chId == null){
        		out.print("fail");
        	}
        	lnCreditHistoryService.rmCreditHistory(chId);
            out.print("success");
        } catch (Exception e) {
            log.error("addCo action error:", e);
        }
    }
    public String queryLoanCh(){
    	try{
            List<LnCreditHistory> LnCreditHistoryList = lnCreditHistoryService.getCreditHistoryByLoanId(loanId);
            request.setAttribute("lnCreditHistoryList", LnCreditHistoryList);
    		
    		return SUCCESS;
    	}catch(Exception e){
    		return ERROR;
    	}
    }
    /**
     * 验证客户三个月内是否有贷款记录，并且审批失败
     */
    public void isLoanFail(){
        HttpServletResponse response = null;
        PrintWriter out = null;
        try {
            response = this.getResponse();
            out = response.getWriter();
            String cusIdStr = request.getParameter("customerId");
            if (StringUtils.isNotEmpty(cusIdStr)){
                Map<String,Object> paramMap = new HashMap<String, Object>();
                Calendar nowCal = Calendar.getInstance();
                nowCal.add(Calendar.MONTH,-3);
                Date beforeThreeMonth = nowCal.getTime();
                paramMap.put("customerId",Integer.parseInt(cusIdStr));
                paramMap.put("approveDate",beforeThreeMonth);
                Boolean isApproveFail = lnLoanService.isApproveFail(paramMap);
                if (isApproveFail){
                    //三个月内有贷款审批失败的记录,不能再次申请贷款
                    out.print("1");
                }else {
                    //三个月内没有贷款审批失败的记录，可以申请贷款
                    out.print("0");
                }
            }
            if (out != null){
                out.close();
            }
        }catch (Exception e){
            log.error("AskLoanAction % isLoanFail",e);
            if (out != null){
                out.close();
            }
        }
    }

    /**
     * 验证客户三个月内是否有贷款记录，并且审批失败
     */
    public void isNogood(){
        HttpServletResponse response = null;
        PrintWriter out = null;
        try {
            response = this.getResponse();
            out = response.getWriter();
            String cusIdStr = request.getParameter("customerId");
            if (StringUtils.isNotEmpty(cusIdStr)){
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("customerId",Integer.parseInt(cusIdStr));
                Boolean isNogood = crmCustomerService.checkIsNogoodCus(paramMap);
                if(isNogood){
                    //不良客户
                    out.print("1");
                }else {
                    //非不良客户
                    out.print("0");
                }
            }
            if (out != null){
                out.close();
            }
        }catch (Exception e){
            log.error("AskLoanAction % isNogood",e);
            if (out != null){
                out.close();
            }
        }
    }

    //复制文件
    private void copy(File src, File target) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(src), BUFFERED_SIZE);
            out = new BufferedOutputStream(new FileOutputStream(target), BUFFERED_SIZE);
            byte[] bs = new byte[BUFFERED_SIZE];
            int i;
            while ((i = in.read(bs)) > 0) {
                out.write(bs, 0, i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void delPhotoById(){
        try {
            String photoIdStr = request.getParameter("photoId");
            if (!StringUtil.isNullOrEmpty(photoIdStr)){
                Integer photoId = Integer.parseInt(photoIdStr);
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("photoId",photoId);
                paramMap.put("isDel",1);

                //伪删除
                dataPhotoService.updatePhotoById(paramMap);
                //真删除
//                dataPhotoService.delPhotoById(paramMap);
            }
        }catch (Exception e){
            log.error("AskLoanAction % delPhotoById",e);
        }
    }

    public String generatorPhotoName(String customerName,String eventName){
        String photoName = "";
        if (StringUtils.isNotEmpty(customerName)){
            photoName += customerName;
        }
        if (StringUtils.isNotEmpty(eventName)){
            photoName += "_" + eventName;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd(HHmmss)");
        String dateStr = format.format(new Date());
        photoName += "_" + dateStr;
        return photoName;
    }
    
    
    public String cusCheck(){
    	
    	try{
    		
    		// 共同借贷人
	        loanCoBorrowerList = lnLoanDetailService.getLoanCoList(loanId, null);
	        // 担保人
	        loanGuarantorList = lnLoanDetailService.getLoanGuList(loanId, null);
	        
	        String cusIds = request.getParameter("customerId");
	        for(int i = 0;i< loanCoBorrowerList.size(); i++){
	        	cusIds += "," + loanCoBorrowerList.get(i).getCustomerId(); 
	        }
	        
	        for(int i = 0;i< loanGuarantorList.size(); i++){
	        	cusIds += "," + loanGuarantorList.get(i).getCustomerId(); 
	        }
    		
    		List<CusCheck> cusCheckList = lnLoanService.cusCheck(cusIds,loanId);
    		request.setAttribute("cusCheckList", cusCheckList);
    		return SUCCESS;
    	}catch(Exception e){
    		e.printStackTrace();
    		return ERROR;
    	}
    	
    }
    //分配回退
    public void assignTheallback(){
		HttpServletResponse response = this.getResponse();
		PrintWriter out=null;
		try{
			out = response.getWriter();
			response.setContentType("text/html");
    		
    	Integer loanId = Integer.parseInt(request.getParameter("loanId"));
    	lnLoanService.updateAssign(loanId);
			out.println("success");
			             
		} catch (Exception e) {
			System.out.println(e.getMessage());
			out.println("error");  
		}finally {

			out.flush();
			out.close();

		}
	}
    //新 损益页面
    public String getLnProfitLossProdTab(){

        logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入新 损益贷页面");
        try{
            String loanId =  request.getParameter("loanId");
            String appLoanTypeId =  request.getParameter("appLoanTypeId");
            request.setAttribute("loanId", loanId);
            request.setAttribute("appLoanTypeId", appLoanTypeId);
            request.setAttribute("write",request.getParameter("write"));
            //开始月份
            if("1".equals(appLoanTypeId)) {//经营贷
                String begin = request.getParameter("beginTime");
                //结束月份
                String end = request.getParameter("endTime");

                request.setAttribute("beginTime", begin);
                request.setAttribute("endTime", end);
                request.setAttribute("monthNumber", request.getParameter("monthNumber"));
            }if("2".equals(appLoanTypeId)){//消费贷
                request.setAttribute("xfdNum",request.getParameter("xfdNum"));
            }

            return SUCCESS;
        }catch(Exception e){
            e.printStackTrace();
            return ERROR;
        }
    }

    public String getProfitTable(){
        try{
//			request.setAttribute("hisList", hisList);
            String loanId =  request.getParameter("loanId");
            String flag =  request.getParameter("flag");
            String type =  request.getParameter("type");
            String div =  request.getParameter("div");
            BigDecimal totalAmount = new BigDecimal(0);


            if(StringUtils.isNotBlank(loanId)&&StringUtils.isNumeric(loanId)&&StringUtils.isNotBlank(flag)&&StringUtils.isNotBlank(type)){
                request.setAttribute("pagename", getHeadName(flag, type));

                if(flag.equals("prod1")){
//                    List<LnBalanceCash> balanceList = lnBalanceService.getLnBalanceCashByLoanId(Integer.valueOf(loanId), type);
                    List<LnProfitLossItem> lnProfitLossItemList = lnProfitLossProdService.selectItemListByType(Integer.valueOf(loanId),type);
                    String begin = request.getParameter("beginTime");
                    String year = begin.split("年")[0];
                    String month = begin.substring(begin.indexOf("年")+1,begin.indexOf("年")+3);

                    if(!CollectionUtils.isEmpty(lnProfitLossItemList)){
                        for (LnProfitLossItem lnProfitLossItem : lnProfitLossItemList) {
                            if(null!=lnProfitLossItem.getTotalAmount()){
                                totalAmount=totalAmount.add(lnProfitLossItem.getTotalAmount());
                            }
                        }
                    }
                        request.setAttribute("lnProfitLossItemList", lnProfitLossItemList);
                        request.setAttribute("year",year);
                        request.setAttribute("month",month);
                        request.setAttribute("monthNumber",request.getParameter("monthNumber"));

                }else if(flag.equals("prod2")){
                    List<LnProfitLossItem> lnProfitLossItemList = lnProfitLossProdService.selectItemListByType(Integer.valueOf(loanId), type);
                    if(!CollectionUtils.isEmpty(lnProfitLossItemList)){
                        for (LnProfitLossItem lnProfitLossItem : lnProfitLossItemList) {
                            if(null!=lnProfitLossItem.getTotalAmount()){
                                totalAmount=totalAmount.add(lnProfitLossItem.getTotalAmount());
                            }
                        }
                    }
                        request.setAttribute("lnProfitLossItemList", lnProfitLossItemList);
                        request.setAttribute("xfdNum",request.getParameter("xfdNum"));

                }
            }
            HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("prod11","prod13","prod14","prod15","prod26","prod27","prod28");
            request.setAttribute("checkBoxMessage", checkBoxMessageMap);
            request.setAttribute("totalAmount", totalAmount);
            request.setAttribute("flag", flag);
            request.setAttribute("type", type);
            request.setAttribute("div", div);
            request.setAttribute("write",request.getParameter("write"));
            return SUCCESS;
        }catch(Exception e){
            e.printStackTrace();
            return ERROR;
        }
    }

    private String getHeadName(String flag,String type){
        String pageName = "";
        if(flag.equals("prod1")) {
            if(type.equals("1")){
                pageName = "营业收入";
            }else if(type.equals("2")){
                pageName = "固定成本支出";
            }else if(type.equals("3")){
                pageName = "所得税支出";
            }else if(type.equals("4")){
                pageName = "其他收入";
            }else if(type.equals("5")){
                pageName = "其他支出";
            }
        }else if(flag.equals("prod2")) {
            if(type.equals("4")){
                pageName = "其他收入";
            }else if(type.equals("5")){
                pageName = "其他支出";
            }else if(type.equals("6")){
                pageName = "家庭收入";
            }else if(type.equals("7")){
                pageName = "固定支出";
            }else if(type.equals("8")){
                pageName = "个人所得税";
            }
        }
        return pageName;
    }
    //跳转添加页面
    public String getSaveProfitPage() {
        try{
            String loanId =  request.getParameter("loanId");
            String flag =  request.getParameter("flag");
            String type =  request.getParameter("type");
            String id =  request.getParameter("itemId");
            if(StringUtils.isNotBlank(flag)&&StringUtils.isNotBlank(type)){
                request.setAttribute("pagename", getHeadName(flag, type));

                if(flag.equals("prod1")){

                    if(StringUtils.isNotBlank(id)&&StringUtils.isNumeric(id)){
                        LnProfitLossItem lnProfitLossItem = lnProfitLossProdService.selectOneItemById(Integer.valueOf(id));
                        request.setAttribute("lnProfitLossItem", lnProfitLossItem);
                    }
                } else if(flag.equals("prod2")){
                    if(StringUtils.isNotBlank(id)&&StringUtils.isNumeric(id)){
                        LnProfitLossItem lnProfitLossItem = lnProfitLossProdService.selectOneItemById(Integer.valueOf(id));
                        request.setAttribute("lnProfitLossItem", lnProfitLossItem);
                    }
                }
            }
            HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("prod11","prod13","prod14","prod15","prod26","prod27","prod28");
            request.setAttribute("checkBoxMessage", checkBoxMessageMap);
            request.setAttribute("flag", flag);
            request.setAttribute("type", type);
            request.setAttribute("id", id);
            request.setAttribute("loanId", loanId);
            request.setAttribute("year",request.getParameter("year"));
            request.setAttribute("month",request.getParameter("month"));
            request.setAttribute("num",request.getParameter("num"));
            return SUCCESS;
        }catch(Exception e){
            e.printStackTrace();
            return ERROR;
        }
    }

    //更新页面
    public void saveProfit(){
        try{

            this.getResponse().setContentType("text/html;charset=utf-8");
            PrintWriter out = this.getResponse().getWriter();
            String loanId =  request.getParameter("loanId");
            String flag =  request.getParameter("flag");
            String type =  request.getParameter("type");
            String id =  request.getParameter("oldId");
            String itemName = request.getParameter("itemName");
            String remark = request.getParameter("remark");

            out.print("success");
            LnProfitLossItem lnProfitLossItem = new LnProfitLossItem();

            if(StringUtils.isNotBlank(flag)&&StringUtils.isNotBlank(type)){
//                if(flag.equals("prod1")){
                    if(StringUtils.isNotBlank(id)&&StringUtils.isNumeric(id)&&!id.equals("0")){
                        //修改
                        lnProfitLossItem.setUpdateDate(new Date());
                        lnProfitLossItem.setUpdateUser(this.getLoginInfo().getUserId());
                        lnProfitLossItem.setItemId(Integer.valueOf(id));
                        lnProfitLossItem.setItemName(itemName);
                        lnProfitLossItem.setRemark(remark);
                        lnProfitLossProdService.updateLnProfitLossItem(lnProfitLossItem);
                    }else if(StringUtils.isNotBlank(loanId)&&StringUtils.isNumeric(loanId)&&!loanId.equals("0")){
                        // 新增
                        lnProfitLossItem.setCreateDate(new Date());
                        lnProfitLossItem.setCreateUser(this.getLoginInfo().getUserId());
                        lnProfitLossItem.setLoanId(Integer.valueOf(loanId));
                        lnProfitLossItem.setType(type);
                        lnProfitLossItem.setItemName(itemName);


                        lnProfitLossItem.setRemark(remark);
                        lnProfitLossProdService.insertLnProfitLossItem(lnProfitLossItem);

//                        for(int i=1;i<=Integer.parseInt(num);i++){
//                            LnProfitLossDetail lnProfitLossDetail = new LnProfitLossDetail();
//                            lnProfitLossDetail.setCreateDate(new Date());
//                            lnProfitLossDetail.setCreateUser(this.getLoginInfo().getUserId());
//                            lnProfitLossDetail.setItemId(Integer.valueOf(id));
//                            lnProfitLossDetail.setLoanId(Integer.valueOf(loanId));
//                            lnProfitLossDetail.setSortNo(i);
//                            lnProfitLossDetail.setMonth(monList.get(i-1));
//                            lnProfitLossProdService.insertDetails(lnProfitLossDetail);
//                        }
                    }else{
                        out.print("error");
                    }
                }
//            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void removeProfit(){
        try{
            this.getResponse().setContentType("text/html;charset=utf-8");
            PrintWriter out = this.getResponse().getWriter();

            String flag =  request.getParameter("flag");
            String id =  request.getParameter("id");
            out.print("success");

            if(StringUtils.isNotBlank(flag)&&StringUtils.isNotBlank(id)&&StringUtils.isNumeric(id)){
                    // 删除
                    lnProfitLossProdService.deleteLnProfitLossItem(Integer.valueOf(id));
                    lnProfitLossProdService.deleteDetailsByItemId(Integer.valueOf(id));
                }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //查询数据库
    public void queryProLossBaseInfo(){
        try {
            this.getResponse().setContentType("text/html;charset=utf-8");
            PrintWriter out = this.getResponse().getWriter();

            String loanId = request.getParameter("loanId");

            if(StringUtils.isNotBlank(loanId)){

                LnProfitLossBase lnProfitLossBase = lnProfitLossBaseService.selectProfitLossBaseByPrimary(Integer.valueOf(loanId));

                if(lnProfitLossBase!=null){
                    //存在
                    if("1".equals(request.getParameter("applyId"))) {
                        JSONObject obj = new JSONObject();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
                        String beginTime = sdf.format(lnProfitLossBase.getBeginTime());
                        String endTime = sdf.format(lnProfitLossBase.getEndTime());
                        obj.put("beginTime", beginTime);
                        obj.put("endTime", endTime);
                        obj.put("lastMonthRate", lnProfitLossBase.getLastMonthRate());
                        obj.put("monthNumber", lnProfitLossBase.getMonthNumber());
                        obj.put("grossRateRemarks", lnProfitLossBase.getGrossRateRemark());
                        out.print(obj.toString());
                    }else{
                        out.print(lnProfitLossBase.getMonthNumber());
                    }

                }else{
                    out.print("");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String showIetmIfo(){

        List<LnProfitLossDetail> detailList =lnProfitLossProdService.selectDetailsByItemId(Integer.valueOf(request.getParameter("id")));

        request.setAttribute("loanId",request.getParameter("loanId"));
        request.setAttribute("flag",request.getParameter("flag"));
        request.setAttribute("type",request.getParameter("type"));
        request.setAttribute("itemId",request.getParameter("id"));
        request.setAttribute("num",request.getParameter("num"));
        if("prod1".equals(request.getParameter("flag"))) {
            Integer itemId = Integer.valueOf(request.getParameter("id"));
            if (detailList.isEmpty()) {
                String year = request.getParameter("year");
                String month = request.getParameter("month");
                String num = request.getParameter("num");
                List<String> monList = new ArrayList<String>();
                int a = Integer.parseInt(year);
                int b = Integer.parseInt(request.getParameter("month"));
                for (int i = 0; i <= Integer.parseInt
                        (num); i++) {
                    String s = "";
                    if (b > 12) {
                        b = 1;
                        a++;
                    }
                    if (b >= 10) {
                        s = a + "年" + b + "月";
                    } else {
                        s = a + "年0" + b + "月";
                    }
                    b++;
                    monList.add(s);
                }
                for (int i = 1; i <= Integer.parseInt(num); i++) {
                    LnProfitLossDetail lnProfitLossDetail = new LnProfitLossDetail();
                    lnProfitLossDetail.setCreateDate(new Date());
                    lnProfitLossDetail.setCreateUser(this.getLoginInfo().getUserId());
                    lnProfitLossDetail.setItemId(Integer.valueOf(itemId));
                    lnProfitLossDetail.setLoanId(Integer.valueOf(request.getParameter("loanId")));
                    lnProfitLossDetail.setSortNo(i);
                    lnProfitLossDetail.setMonth(monList.get(i - 1));
                    lnProfitLossProdService.insertDetails(lnProfitLossDetail);
                    detailList.add(lnProfitLossDetail);
                }

            }
        }
        if("prod2".equals(request.getParameter("flag"))) {
            if (detailList.isEmpty()) {
                Integer itemId = Integer.valueOf(request.getParameter("id"));
                Integer num = Integer.valueOf(request.getParameter("num"));
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                List<String> monList = new ArrayList<String>();
                for (int i = 0; i < num; i++) {
                    month--;
                    String s = "";
                    if (month < 1) {
                        month = 12;
                        year--;
                    }
                    if (month >= 10) {
                        s = year + "年" + month + "月";
                    } else {
                        s = year + "年0" + month + "月";
                    }
                    monList.add(s);
                }
                Collections.reverse(monList);
                for (int i = 1; i <= num; i++) {
                    LnProfitLossDetail lnProfitLossDetail = new LnProfitLossDetail();
                    lnProfitLossDetail.setCreateDate(new Date());
                    lnProfitLossDetail.setCreateUser(this.getLoginInfo().getUserId());
                    lnProfitLossDetail.setItemId(Integer.valueOf(itemId));
                    lnProfitLossDetail.setLoanId(Integer.valueOf(request.getParameter("loanId")));
                    lnProfitLossDetail.setSortNo(i);
                    lnProfitLossDetail.setMonth(monList.get(i - 1));
                    lnProfitLossProdService.insertDetails(lnProfitLossDetail);
                    detailList.add(lnProfitLossDetail);
                }
            }
        }
            request.setAttribute("detailList",detailList);
        return SUCCESS;
    }

    public void updatDetails(){
        try {
            this.getResponse().setContentType("text/html;charset=utf-8");
            PrintWriter out = this.getResponse().getWriter();
            String loanId = request.getParameter("loanId");
            String itemId = request.getParameter("itemId");
            out.print("success");
            List<LnProfitLossDetail> detatilList = lnProfitLossProdService.selectDetailsByItemId(Integer.valueOf(itemId));
            String num = request.getParameter("num");
            BigDecimal totalAmount = new BigDecimal(0);
            BigDecimal lastYearAmount = new BigDecimal(0);
            BigDecimal currYearAmount = new BigDecimal(0);
            int m = Calendar.getInstance().get(Calendar.MONTH);
            for(int i=1;i<=Integer.valueOf(num);i++){
                LnProfitLossDetail detail = detatilList.get(i-1);
                BigDecimal amount = new BigDecimal(request.getParameter("amount"+i));
                String remark = request.getParameter("remark"+i);
                detail.setAmount(amount);
                detail.setRemark(remark);
                detail.setUpdateDate(new Date());
                detail.setUpdateUser(this.getLoginInfo().getUserId());
                totalAmount = totalAmount.add(amount);
                if(i>Integer.valueOf(num)-m) {
                    currYearAmount = currYearAmount.add(amount);
                }else{
                    lastYearAmount = lastYearAmount.add(amount);
                }
                lnProfitLossProdService.updateDetails(detail);
            }
            LnProfitLossItem item = new LnProfitLossItem();
            item.setUpdateUser(this.getLoginInfo().getUserId());
            item.setUpdateDate(new Date());
            item.setTotalAmount(totalAmount);
            item.setCurrYearAmount(currYearAmount);
            item.setLastYearAmount(lastYearAmount);
            item.setAverageAmount(totalAmount.divide(new BigDecimal(num), 2, BigDecimal.ROUND_UP));
            item.setItemId(Integer.valueOf(itemId));
            lnProfitLossProdService.updateLnProfitLossItem(item);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /**
     * getter setter *
     */
    public LnLoanService getLnLoanService() {
        return lnLoanService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public LnLoanCoBorrowerService getLnLoanCoBorrowerService() {
        return lnLoanCoBorrowerService;
    }

    public void setLnLoanCoBorrowerService(LnLoanCoBorrowerService lnLoanCoBorrowerService) {
        this.lnLoanCoBorrowerService = lnLoanCoBorrowerService;
    }

    public LnLoanGuarantorService getLnLoanGuarantorService() {
        return lnLoanGuarantorService;
    }

    public void setLnLoanGuarantorService(LnLoanGuarantorService lnLoanGuarantorService) {
        this.lnLoanGuarantorService = lnLoanGuarantorService;
    }

    public File getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(File logoImage) {
        this.logoImage = logoImage;
    }
    public LnProfitLossBaseService getLnProfitLossBaseService() {
        return lnProfitLossBaseService;
    }

    public void setLnProfitLossBaseService(LnProfitLossBaseService lnProfitLossBaseService) {
        this.lnProfitLossBaseService = lnProfitLossBaseService;
    }
    public LnLoan getLnLoan() {
        return lnLoan;
    }

    public void setLnLoan(LnLoan lnLoan) {
        this.lnLoan = lnLoan;
    }

    public LnLoanTypeService getLnLoanTypeService() {
        return lnLoanTypeService;
    }

    public void setLnLoanTypeService(LnLoanTypeService lnLoanTypeService) {
        this.lnLoanTypeService = lnLoanTypeService;
    }

    public List<LnLoanType> getLoanTypeList() {
        return loanTypeList;
    }

    public void setLoanTypeList(List<LnLoanType> loanTypeList) {
        this.loanTypeList = loanTypeList;
    }

    public SysParamService getSysParamService() {
        return sysParamService;
    }

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

//    public String getPhoto3() {
//        return photo3;
//    }
//
//    public void setPhoto3(String photo3) {
//        this.photo3 = photo3;
//    }
//
//    public String getPhoto2() {
//        return photo2;
//    }
//
//    public void setPhoto2(String photo2) {
//        this.photo2 = photo2;
//    }
//
//    public String getPhoto1() {
//        return photo1;
//    }
//
//    public void setPhoto1(String photo1) {
//        this.photo1 = photo1;
//    }
//
//    //获取字典数据
//    private Map<String,Object> jsonMap=new HashMap<String, Object>();
//    private String typeName;
//    public String getZiDian(){
//        Map<String,Object> map=new HashMap<String, Object>();
//        map.put("type",typeName);
//        List<BaseSDic> sDicList=sDicService.queryDicByType(map);
//        for (BaseSDic sDic:sDicList){
//            jsonMap.put(sDic.getEnname(),sDic.getCnname());
//        }
//        return "success";
//    }
//
//    public void setTypeName(String typeName) {
//        this.typeName = typeName;
//    }
//
//    public Map<String, Object> getJsonMap() {
//        return jsonMap;
//    }
//
//    public void setJsonMap(Map<String, Object> jsonMap) {
//        this.jsonMap = jsonMap;
//    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setCustomerDataService(CustomerDataService customerDataService) {
        this.customerDataService = customerDataService;
    }

    public void setDataPhotoService(DataPhotoService dataPhotoService) {
        this.dataPhotoService = dataPhotoService;
    }

    public List<LnLoanCoBorrowerBean> getLoanCoBorrowerList() {
        return loanCoBorrowerList;
    }

    public void setLoanCoBorrowerList(List<LnLoanCoBorrowerBean> loanCoBorrowerList) {
        this.loanCoBorrowerList = loanCoBorrowerList;
    }

    public List<LnLoanGuarantorBean> getLoanGuarantorList() {
        return loanGuarantorList;
    }

    public void setLoanGuarantorList(List<LnLoanGuarantorBean> loanGuarantorList) {
        this.loanGuarantorList = loanGuarantorList;
    }

    public String getApplyRemark() {
        return applyRemark;
    }

    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark;
    }

    public Integer getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(Integer loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public Integer getLoanSubTypeId() {
        return loanSubTypeId;
    }

    public void setLoanSubTypeId(Integer loanSubTypeId) {
        this.loanSubTypeId = loanSubTypeId;
    }

    public BigDecimal getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(BigDecimal loanMoney) {
        this.loanMoney = loanMoney;
    }

    public Integer getCoId() {
        return coId;
    }

    public void setCoId(Integer coId) {
        this.coId = coId;
    }

    public Integer getGuId() {
        return guId;
    }

    public void setGuId(Integer guId) {
        this.guId = guId;
    }

    public void setLnOpHistoryService(LnOpHistoryService lnOpHistoryService) {
        this.lnOpHistoryService = lnOpHistoryService;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
        this.crmCustomerService = crmCustomerService;
    }

    public void setLnLoanDetailService(LnLoanDetailService lnLoanDetailService) {
        this.lnLoanDetailService = lnLoanDetailService;
    }

    public SysUploadFileService getSysUploadFileService() {
        return sysUploadFileService;
    }

    public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
        this.sysUploadFileService = sysUploadFileService;
    }

    public CaseHelperServiceImpl getCaseHelperService() {
        return caseHelperService;
    }

    public void setCaseHelperService(CaseHelperServiceImpl caseHelperService) {
        this.caseHelperService = caseHelperService;
    }

    public void setLnLoanInfo(LnLoanInfo lnLoanInfo) {
        this.lnLoanInfo = lnLoanInfo;
    }

    public LnLoanInfo getLnLoanInfo() {
        return lnLoanInfo;
    }

    public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
        this.lnLoanInfoService = lnLoanInfoService;
    }

	public LnLoanInfoDictionaryService getLnLoanInfoDictionaryService() {
		return lnLoanInfoDictionaryService;
	}

	public void setLnLoanInfoDictionaryService(
			LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
		this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
	}

	public LnCreditHistory getLnCreditHistory() {
		return lnCreditHistory;
	}

	public void setLnCreditHistory(LnCreditHistory lnCreditHistory) {
		this.lnCreditHistory = lnCreditHistory;
	}

	public List<LnCreditHistory> getLnCreditHistoryList() {
		return lnCreditHistoryList;
	}

	public void setLnCreditHistoryList(List<LnCreditHistory> lnCreditHistoryList) {
		this.lnCreditHistoryList = lnCreditHistoryList;
	}

	public void setLnCreditHistoryService(
			LnCreditHistoryService lnCreditHistoryService) {
		this.lnCreditHistoryService = lnCreditHistoryService;
	}

	public Integer getCredentialTypeId() {
		return credentialTypeId;
	}

	public void setCredentialTypeId(Integer credentialTypeId) {
		this.credentialTypeId = credentialTypeId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getChId() {
		return chId;
	}

	public void setChId(Integer chId) {
		this.chId = chId;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public void setSysTeamService(SysTeamService sysTeamService) {
		this.sysTeamService = sysTeamService;
	}

	public DataFormService getDataFormService() {
		return dataFormService;
	}

	public void setDataFormService(DataFormService dataFormService) {
		this.dataFormService = dataFormService;
	}
	public DeptService getDeptService() {
		return deptService;
	}
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	public DeptFacadeService getDeptFacadeService() {
		return deptFacadeService;
	}
	public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
		this.deptFacadeService = deptFacadeService;
	}
	public JSONArray getDeptJson() {
		return deptJson;
	}
	public void setDeptJson(JSONArray deptJson) {
		this.deptJson = deptJson;
	}
	public JSONArray getDeptManagerJson() {
		return deptManagerJson;
	}
	public void setDeptManagerJson(JSONArray deptManagerJson) {
		this.deptManagerJson = deptManagerJson;
	}
	public SysDeptAuthService getSysDeptAuthService() {
		return sysDeptAuthService;
	}
	public void setSysDeptAuthService(SysDeptAuthService sysDeptAuthService) {
		this.sysDeptAuthService = sysDeptAuthService;
	}
	public SysRoleMemberService getSysRoleMemberService() {
		return sysRoleMemberService;
	}
	public void setSysRoleMemberService(SysRoleMemberService sysRoleMemberService) {
		this.sysRoleMemberService = sysRoleMemberService;
	}

	public Integer getAssignUserId() {
		return assignUserId;
	}

	public void setAssignUserId(Integer assignUserId) {
		this.assignUserId = assignUserId;
	}

	public LnProfitLossProdService getLnProfitLossProdService() {
		return lnProfitLossProdService;
	}

	public void setLnProfitLossProdService(
			LnProfitLossProdService lnProfitLossProdService) {
		this.lnProfitLossProdService = lnProfitLossProdService;
	}

	public List<LnProfitLossProd> getLnProfitLossProdList() {
		return lnProfitLossProdList;
	}

	public void setLnProfitLossProdList(List<LnProfitLossProd> lnProfitLossProdList) {
		this.lnProfitLossProdList = lnProfitLossProdList;
	}
	
	
}
