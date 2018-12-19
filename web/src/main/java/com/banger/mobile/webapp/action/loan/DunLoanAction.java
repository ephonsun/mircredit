/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款模块dunLoanAction
 * Author     :ouyl
 * Create Date:2013-3-14
 */
package com.banger.mobile.webapp.action.loan;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;
import org.jfree.data.DataUtilities;

import com.banger.mobile.domain.model.base.loan.BaseLnLoanMonitor;
import com.banger.mobile.domain.model.data.Form;
import com.banger.mobile.domain.model.data.LoanData;
import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.loan.LnCreditHistory;
import com.banger.mobile.domain.model.loan.LnDunLog;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnLoanCoBorrowerBean;
import com.banger.mobile.domain.model.loan.LnLoanGuarantorBean;
import com.banger.mobile.domain.model.loan.LnLoanInfo;
import com.banger.mobile.domain.model.loan.LnLoanInfoDictionary;
import com.banger.mobile.domain.model.loan.LnLoanMonitor;
import com.banger.mobile.domain.model.loan.LnLoanType;
import com.banger.mobile.domain.model.loan.LnOpHistory;
import com.banger.mobile.domain.model.loan.LnPledge;
import com.banger.mobile.domain.model.loan.LnRepaymentPlan;
import com.banger.mobile.domain.model.loan.LoanConstants;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataFormService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.LnCreditHistoryService;
import com.banger.mobile.facade.loan.LnDunLogService;
import com.banger.mobile.facade.loan.LnLoanDetailService;
import com.banger.mobile.facade.loan.LnLoanInfoDictionaryService;
import com.banger.mobile.facade.loan.LnLoanInfoService;
import com.banger.mobile.facade.loan.LnLoanMonitorService;
import com.banger.mobile.facade.loan.LnLoanPledgeService;
import com.banger.mobile.facade.loan.LnLoanService;
import com.banger.mobile.facade.loan.LnLoanTypeService;
import com.banger.mobile.facade.loan.LnOpHistoryService;
import com.banger.mobile.facade.loan.LnRepaymentPlanService;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.system.team.SysTeamUserService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.webapp.action.BaseAction;
import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.ctc.wstx.util.DataUtil;

/**
 * @author ouyl
 * @version $Id: DunLoanAction.java v 0.1 ${} 上午11:42 ouyl Exp $
 *
 * 催收贷款Action
 */

public class DunLoanAction extends BaseAction {
    private static Logger logger = Logger.getLogger(DunLoanAction.class);
    private SysTeamUserService sysTeamUserService;
    private LnLoanDetailService lnLoanDetailService;
    private LnOpHistoryService lnOpHistoryService;
    private LnLoanMonitorService lnLoanMonitorService;
    private SysUploadFileService sysUploadFileService;
    private LnLoanTypeService lnLoanTypeService;
    private LnLoanService lnLoanService;
    private SysParamService sysParamService;
    private DataFormService dataFormService; //表单service
    private LnRepaymentPlanService lnRepaymentPlanService;
    private LnDunLogService          lnDunLogService;
    private DeptFacadeService deptFacadeService;
    private String customer;
    private Integer loanType;
    private Integer loanId;
    private Date repaymentStartDate;
    private Date repaymentEndDate;
    private Date monDate;
    private Integer status;
    private List<LnLoanMonitor> lnLoanMonitorList;//贷后监控
    private LnLoanInfoService lnLoanInfoService;
    private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
    private LnCreditHistoryService lnCreditHistoryService;
    private LnLoanInfo lnLoanInfo;
    private List<LnLoanCoBorrowerBean> loanCoBorrowerList;       //共同借贷人列表
    private List<LnLoanGuarantorBean> loanGuarantorList;        //担保人列表
    private List<LnCreditHistory> lnCreditHistoryList;
    private CustomerDataService customerDataService; //贷款资料service
    private BaseLnLoanMonitor lnLoanMonitor;
    private LnLoanPledgeService lnLoanPledgeService;
    public String dunLoanList(){
    	//TODO:修改
    	//return SUCCESS;
    	
    	
        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入催收贷款菜单列表...");
          //贷款类型
            List<LnLoanType> typeList = lnLoanTypeService.getLoanTypeList();
            Map<String, Object> conds = new HashMap<String, Object>();
            
            
            // 是否是业务主管
            //  Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
              String belongUserIds = "";
              String[] roles = this.getLoginInfo().getRoles();
              boolean isInChargeOf = false;
              for (String roleId : roles) {
  				if(roleId.equals("5") || roleId.equals("6")){
  					isInChargeOf = true;
  					break;
  				}
  			}
              if (isInChargeOf) {
                  // 当前用户的团队底下的成员
              	List<String> belongUserId = sysTeamUserService.getUserIdsByChiefUserId(this.getLoginInfo().getUserId());
              	int i = 0;
              	for (String str : belongUserId) {
              		if(i == 0){
              			belongUserIds +=  str;
              			i=1;
              		}else{
              			belongUserIds += "," + str;
              		}
  				}
              	conds.put("belongUserIds", belongUserIds); // 当前用户所管理的提交申请用户
              } else {
              	
              	String role = roles[0];
              	if(role.equals("7")){  // 客户经理
              		conds.put("surveyUserId", this.getLoginInfo().getUserId());
              	}else if(role.equals("4")){
              		conds.put("approveBackerUserId", this.getLoginInfo().getUserId());
              	}
              }
            
            conds.put("loanStatusId",6);   // 6表示还款中
            conds.put("customer", customer);
            conds.put("loanTypeId", loanType);
            //默认之后3天显示
            if(repaymentStartDate==null&&repaymentEndDate==null){
            	repaymentStartDate =org.apache.poi.ss.usermodel.DateUtil.parseYYYYMMDDDate(DateUtil.formatDate(new Date(),"yyyy-MM-dd"));
            	repaymentEndDate = org.apache.commons.lang.time.DateUtils.addDays(repaymentStartDate, sysParamService.getMaxDunLoanTime()!=null?Integer.parseInt(sysParamService.getMaxDunLoanTime()):3);
            }
            conds.put("repaymentStartDate", repaymentStartDate);
            repaymentEndDate = lnLoanService.addSecondsForDate(repaymentEndDate,59);
            conds.put("repaymentEndDate", repaymentEndDate);
            //conds.put("loanIsCheckout", loanIsCheckout);
            conds.put("isConfirm","isConfirm");
            conds.put("isNogood",0) ;
            conds.put("loanStatusId",6);
            conds.put("isDun","isDun");
          //未还款
            conds.put("isPaidTag","0");
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 开始搜索初始化状态下催收贷款...");
            PageUtil<LnLoan> dataList = lnLoanService.getMakeLoanPage(conds, this.getPage());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索催收贷款完成，总共搜索出"+dataList.getItems().size()+"条贷款");
            HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("DKLX","DBFS","DY","ZY","FLXS","HYZK","JYCD","JYCS","JZCSLX","JZZK","XXLY","YSRSP","ZJLX","ZXQK","ZY","ZZXS","JKLX","HFLX","CSFS");
	    	request.setAttribute("checkBoxMessage", checkBoxMessageMap);
            if(dataList != null && dataList.getItems() != null){
                for (LnLoan lnLoan : dataList.getItems()) {
                    Date repaymentDate = lnLoan.getApplySubmitDate();
                    Date nowDate = new Date();
                    if(repaymentDate!=null){
                        if((nowDate.getTime() - repaymentDate.getTime()) >= 0){
                            //超过还款时间的,前端在高亮显示
                            lnLoan.setIsWillPast(1);
                        }
                    }
                }
            }
            request.setAttribute("dataList",dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
            request.setAttribute("typeList",typeList);
            request.setAttribute("sysUserId",this.getLoginInfo().getUserId());
            return SUCCESS;
        }catch (Exception e){
            logger.error("dunLoanList",e);
            return ERROR;
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
    public String dunLoanListQuery(){
    	 try{
             logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入催收贷款菜单列表...");
           //贷款类型
             List<LnLoanType> typeList = lnLoanTypeService.getLoanTypeList();
             Map<String, Object> conds = new HashMap<String, Object>();
             // 是否是业务主管
             //  Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
               String belongUserIds = "";
               String[] roles = this.getLoginInfo().getRoles();
               boolean isInChargeOf = false;
               for (String roleId : roles) {
   				if(roleId.equals("5") || roleId.equals("6")){
   					isInChargeOf = true;
   					break;
   				}
   			}
               if (isInChargeOf) {
                   // 当前用户的团队底下的成员
               	List<String> belongUserId = sysTeamUserService.getUserIdsByChiefUserId(this.getLoginInfo().getUserId());
               	int i = 0;
               	for (String str : belongUserId) {
               		if(i == 0){
               			belongUserIds +=  str;
               			i=1;
               		}else{
               			belongUserIds += "," + str;
               		}
   				}
               	conds.put("belongUserIds", belongUserIds); // 当前用户所管理的提交申请用户
               } else {
               	
               	String role = roles[0];
               	if(role.equals("7")){  // 客户经理
               		conds.put("surveyUserId", this.getLoginInfo().getUserId());
               	}else if(role.equals("4")){
               		conds.put("approveBackerUserId", this.getLoginInfo().getUserId());
               	}
               }
             
             conds.put("loanStatusId",6);   // 6表示还款中
             conds.put("customer", customer);
             conds.put("loanTypeId", loanType);
             //默认之后3天显示
             if(repaymentStartDate==null&&repaymentEndDate==null){
            	 repaymentStartDate =org.apache.poi.ss.usermodel.DateUtil.parseYYYYMMDDDate(DateUtil.formatDate(new Date(),"yyyy-MM-dd"));
             	repaymentEndDate = org.apache.commons.lang.time.DateUtils.addDays(repaymentStartDate, sysParamService.getMaxDunLoanTime()!=null?Integer.parseInt(sysParamService.getMaxDunLoanTime()):3);
             }
             conds.put("repaymentStartDate", repaymentStartDate);
             repaymentEndDate = lnLoanService.addSecondsForDate(repaymentEndDate,59);
             conds.put("repaymentEndDate", repaymentEndDate);
             //conds.put("loanIsCheckout", loanIsCheckout);
             conds.put("isConfirm","isConfirm");
             conds.put("isNogood",0) ;
             conds.put("loanStatusId",6);
             conds.put("isDun","isDun");
           //未还款
             conds.put("isPaidTag","0");
             logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 开始搜索初始化状态下催收贷款...");
             PageUtil<LnLoan> dataList = lnLoanService.getMakeLoanPage(conds, this.getPage());
             logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索催收贷款完成，总共搜索出"+dataList.getItems().size()+"条贷款");
             HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("DKLX","DBFS","DY","ZY","FLXS","HYZK","JYCD","JYCS","JZCSLX","JZZK","XXLY","YSRSP","ZJLX","ZXQK","ZY","ZZXS","JKLX","HFLX","CSFS");
 	    	request.setAttribute("checkBoxMessage", checkBoxMessageMap);
             if(dataList != null && dataList.getItems() != null){
                 for (LnLoan lnLoan : dataList.getItems()) {
                     Date repaymentDate = lnLoan.getApplySubmitDate();
                     Date nowDate = new Date();
                     if(repaymentDate!=null){
                         if((nowDate.getTime() - repaymentDate.getTime()) >= 0){
                             //超过还款时间的,前端在高亮显示
                             lnLoan.setIsWillPast(1);
                         }
                     }
                 }
             }
             request.setAttribute("dataList",dataList.getItems());
             request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
             request.setAttribute("typeList",typeList);
             request.setAttribute("sysUserId",this.getLoginInfo().getUserId());
             return SUCCESS;
         }catch (Exception e){
             logger.error("dunLoanList",e);
             return ERROR;
         }
    }

    public String loanRepaymentPlan(){
    	//TODO:修改
    	return SUCCESS;
    	/*
        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- [DunLoanAction % loanRepaymentPlan]开始");
            Map<String, Object> conds = new HashMap<String, Object>();
            String strLoanId = request.getParameter("loanId");
            Integer loanId= Integer.parseInt(strLoanId.trim());
            conds.put("loanId",loanId);
            Page pg = this.getPage();
            pg.setPageSize(4);
            PageUtil<LnRepaymentPlan> data = lnRepaymentPlanService.queryLnRepaymentPlanListPage(conds,pg);
            LnLoan loan = lnLoanService.getLnLoanById(loanId);
            request.setAttribute("queryList",data.getItems());
            request.setAttribute("sortno",loan.getSortno());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- [DunLoanAction % loanRepaymentPlan]完成");
            return SUCCESS;
        }catch (Exception e){
            log.error("loanRepaymentPlan",e);
            return ERROR;
        }
    */}
    // 跳转到编辑贷后监控页面
    public String toAddLM() {
    	
        request.setAttribute("loanId", request.getParameter("loanId"));
    	HashMap<String,Object> checkBoxMessageMap =
                buildLoanCheckBoxMessage("DKLX","DBFS","DY","ZY","FLXS","HYZK","JYCD","JYCS","JZCSLX","JZZK","XXLY","YSRSP","ZJLX","ZXQK","ZY","ZZXS","JKLX","HFLX","CSFS","KHJBQK","SCJYQK","CWQK","BZDB","DYDB");
	    request.setAttribute("checkBoxMessage", checkBoxMessageMap);
	    if (request.getParameter("loanLmId") != null) {
	    	lnLoanMonitor =lnLoanMonitorService.getLoanMonitor(Integer.parseInt(request.getParameter("loanLmId")));
    	}else{
    		LnLoanMonitor lnLoanMonitor2 = new LnLoanMonitor();
    		lnLoanMonitor2.setMonDate(new Date());
    		lnLoanMonitor =lnLoanMonitor2;
    		 request.setAttribute("isAdd", "true");
    	}
        return SUCCESS;
    }
    
    
    // 跳转到关联附件页面
    public String toUnionLM() {
        request.setAttribute("loanId", request.getParameter("loanId"));
        List<SysUploadFile>  list=  sysUploadFileService.queryForUploadFormList(request.getParameter("loanId"));
        request.setAttribute("fileList", list);
        request.setAttribute("loanLmId", request.getParameter("loanLmId"));
        return SUCCESS;
    }
  //编辑贷后监控
    public void addLM() {
        try {
            this.getResponse().setContentType("text/html;charset=utf-8");
            PrintWriter out = this.getResponse().getWriter();
            BaseLnLoanMonitor lanMonitor = new BaseLnLoanMonitor();
            lanMonitor.setMonUserId(this.getLoginInfo().getUserId());

            lanMonitor.setFileIds(request.getParameter("fildIds") == null ? "" : request.getParameter("fildIds"));


            if (request.getParameter("monType") != null) {
                lanMonitor.setMonType(Integer.parseInt(request.getParameter("monType")));
            }
            if (request.getParameter("revisitType") != null) {
                lanMonitor.setRevisitType(Integer.parseInt(request.getParameter("revisitType")));
            }
            if (StringUtils.isNotBlank(request.getParameter("customerStatus"))) {
                lanMonitor.setCustomerStatus(request.getParameter("customerStatus"));
            }
            if (StringUtils.isNotBlank(request.getParameter("bizStatus"))) {
               lanMonitor.setBizStatus(request.getParameter("bizStatus"));
            }
            if (StringUtils.isNotBlank(request.getParameter("financeStatus"))) {
                lanMonitor.setFinanceStatus(request.getParameter("financeStatus"));
            }
            if (StringUtils.isNotBlank(request.getParameter("guarantorStatus"))) {
                lanMonitor.setGuarantorStatus(request.getParameter("guarantorStatus"));
            }
            if (StringUtils.isNotBlank(request.getParameter("ledgeStatus"))) {
                lanMonitor.setLedgeStatus(request.getParameter("ledgeStatus"));
            }
            lanMonitor.setUserResult(request.getParameter("userResult"));

            if (request.getParameter("loanLmId") != null && StringUtils.isNotEmpty(request.getParameter("loanLmId"))) {
                lanMonitor.setLoanMonId(Integer.parseInt(request
                        .getParameter("loanLmId")));
                lanMonitor.setUpdateUser(this.getLoginInfo().getUserId());
                if (request.getParameter("flag") != null && request.getParameter("flag").equals("flag")) {
                    lnLoanMonitorService.updateLoanMonitorfile(lanMonitor);
                } else {
                    lnLoanMonitorService.updateLoanMonitor(lanMonitor);
                }
            } else {
                lanMonitor.setMonDate(new Date());
                lanMonitor.setLoanId(Integer.parseInt(request.getParameter("loanId")));
                lanMonitor.setCreateUser(this.getLoginInfo().getUserId());
                lnLoanMonitorService.createLoanMonitor(lanMonitor);
            }
            out.print("success");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("addCo action error:", e);
        }
    }
    
    
    public void unionLM(){
    	try{
	    	this.getResponse().setContentType("text/html;charset=utf-8");
	        PrintWriter out = this.getResponse().getWriter();
	        if (request.getParameter("loanLmId") != null&&StringUtils.isNotEmpty(request.getParameter("loanLmId"))){
	        	BaseLnLoanMonitor lanMonitor = lnLoanMonitorService.getLoanMonitor(Integer.parseInt(request.getParameter("loanLmId")));
	        	lanMonitor.setUpdateUser(this.getLoginInfo().getUserId());
	        	String strFileIds = lanMonitor.getFileIds();
	        	String addFileIds = request.getParameter("fildIds")==null?"":request.getParameter("fildIds");
	        	if(strFileIds==null || strFileIds.equals("")){
	        		lanMonitor.setFileIds(addFileIds);
	        	}else{
	        		if(!addFileIds.equals("")){
	        			String[] addFileIdList= addFileIds.split(",");  
	        			String[] strFileIdList = strFileIds.split(",");
	        			String fileIds  = strFileIds;
	        			for(int i=0;i<addFileIdList.length;i++){
	        				int isExist = 0;  
	        				for (int j=0 ; j<strFileIdList.length;j++){	        					
	        					if(addFileIdList[i].equals(strFileIdList[j])){
	        						isExist =1;
	        						continue;
	        					}	        					
	        				}
	        				if(isExist==0){
	        					fileIds = fileIds+","+ addFileIdList[i];
	        				}
	        			}
	        			lanMonitor.setFileIds(fileIds);
	        		}
	        	}
	        	lnLoanMonitorService.updateLoanMonitorfile(lanMonitor);
	        }
	        out.print("success");
    	}catch (Exception e) {
        	e.printStackTrace();
            log.error("addCo action error:", e);
        }
    }
    /**
     * 检查
     */
    public void updateReadTag(){
    	 try {
			this.getResponse().setContentType("text/html;charset=utf-8");
			PrintWriter out = this.getResponse().getWriter();			
			String strLoanMonitorId = request.getParameter("loanMonitorId");
            String remak = request.getParameter("remak");
            Map<String ,Object> map = new HashMap<String, Object>();
			if(strLoanMonitorId !=null){
                map.put("loanMonId",Integer.parseInt(strLoanMonitorId));
            }
            if(remak !=null){
                map.put("remak",remak);
            }

			lnLoanMonitorService.updateReadTag(map);
			out.print("success");
    	 } catch (Exception e) {
			e.printStackTrace();
			log.error("addCo action error:", e);
    	 }
    }
    /**
     * 编辑已落实贷款
     *
     * @return
     */
    public String dunLoanEdit(){
    	//TODO:修改
    	 try{
             logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入催收贷款菜单列表...");
             //贷款类型
             String strLoanId = request.getParameter("loanId");
             String isConfirm =request.getParameter("isConfirm");
             Integer loanId= Integer.parseInt(strLoanId.trim());
             LnLoan lnLoan = lnLoanService.getLnLoanById(loanId);
          /*   Integer isNogood =lnLoan.getIsNogood();
             String nogoodRemark =lnLoan.getNogoodRemark();*/
             List<LnRepaymentPlan> queryList= lnRepaymentPlanService.queryLnRepaymentPlan(loanId);
             LnRepaymentPlan queryPlan= lnRepaymentPlanService.selectCurLnRepaymentPlanById(loanId);
             //查询贷款余额
             request.setAttribute("queryPlan", queryPlan);
             Map<String,Object> paramMap = new HashMap<String, Object>();
             paramMap.put("eventId", LoanConstants.LOAN_DUN_EVENT); //贷后流程
             paramMap.put("loanId",lnLoan.getLoanId());
             //贷款表单附件信息
             List<Form> formAttachmentList = dataFormService.selectFormAttachment(paramMap);
             request.setCharacterEncoding("UTF-8");
             request.setAttribute("lnRepaymentPlanList", queryList);
             request.setAttribute("loanId", loanId);
            /* request.setAttribute("isNogood",isNogood);
             request.setAttribute("nogoodRemark",nogoodRemark);*/
             request.setAttribute("loan",lnLoan);
             request.setAttribute("formAttachmentList",formAttachmentList);
             request.setAttribute("isConfirm",isConfirm);
             List<LnDunLog> lnDunLogList = null;
             // 正常催收
             lnDunLogList = lnDunLogService.getDunLogByLoanId(loanId);  //已优化
             /* for (LnDunLog lnDunLog : lnDunLogList) {
                 //web端电话催收特殊处理
                 if (lnDunLog.getUpdateUser() != null) {
                     RecordDetail d = recordInfoService.getRecordInfoById(lnDunLog.getUpdateUser());
                     if (d != null) {
                         lnDunLog.setRemark(d.getRemark());
                     }
                 }

                 if (lnDunLog.getCustomerDataId() != null && !lnDunLog.getCustomerDataId().equals(0)){
                     customerDataIdList.add(lnDunLog.getCustomerDataId());
                 }
             }*/
             request.setAttribute("lnDunLogList",lnDunLogList);
             
             //所有资料
 	        Map<String,Object> param = new HashMap<String, Object>();
 	        param.put("loanId", loanId);
 	        lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
 	        //查询贷后监控记录
 	        lnLoanMonitorList= lnLoanMonitorService.getLoanMonitorList(loanId);
 	       for(LnLoanMonitor lm:lnLoanMonitorList){
 	    	  if(StringUtils.isNotEmpty(lm.getFileIds())){
	        		List<SysUploadFile>  fileIdList = new ArrayList<SysUploadFile> ();
	        		SysUploadFile flie = new SysUploadFile();
	        		if(lm.getFileIds().contains(",")){
	        			for(String id:lm.getFileIds().split(",")){
	        				flie= sysUploadFileService.getSysUploadFileById(Integer.parseInt(id));
	        				fileIdList.add(flie);
	        			}
	        		}else{
	        			flie= sysUploadFileService.getSysUploadFileById(Integer.parseInt(lm.getFileIds()));
       				fileIdList.add(flie);
	        		}
	        		lm.setFileIdList(fileIdList);
	        	}
	        }
 	        request.setAttribute("lnLoanMonitorList", lnLoanMonitorList);
 	        
 	        //查询历史操作记录
 	        List<LnOpHistory> hisList = lnOpHistoryService.getAllOpHistoryListByLoanId(loanId);
 	        request.setAttribute("hisList", hisList);
 	        //抵押之物
 	        List<LnPledge> lnPledgeList = lnLoanPledgeService.getLnLoanPledgeByLoanId(loanId);
			request.setAttribute("lnPledgeList", lnPledgeList);

 	        //所有资料
 	        Map<String,Object> photoMap = new HashMap<String, Object>();
 	        photoMap.put("loanId",loanId);
 	        photoMap.put("eventId",LoanConstants.LOAN_DUN_EVENT);
 	        photoMap.put("statistics",1);
 	        List<LoanData> dataList = customerDataService.getAllLoanDataById(photoMap);
 	        List<Photo> photoList = new ArrayList<Photo>();
 	        //申请人照片资料
 	        for (LoanData d : dataList) {
 	            if (d.getDataType().equals(1)){
 	                if (d.getCustomerId().equals(lnLoan.getCustomerId())){
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
 	        
 	        HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("DKLX","DBFS","DY","ZY","FLXS","HYZK","JYCD","JYCS","JZCSLX","JZZK","XXLY","YSRSP","ZJLX","ZXQK","ZY","ZZXS","JKLX","HFLX","CSFS");
 	    	request.setAttribute("checkBoxMessage", checkBoxMessageMap);
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
             return SUCCESS;
         }catch (Exception e){
             logger.error("dunLoanList",e);
             return ERROR;
         }

    }
    /**
     * 编辑已落实贷款
     *
     * @return
     */
    public String loanMonitorList(){
    	//TODO:修改
    	 try{
             logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入催收贷款菜单列表...");
             //贷款类型
             String strLoanId = request.getParameter("loanId");
             String isConfirm =request.getParameter("isConfirm");
             Integer loanId= Integer.parseInt(strLoanId.trim());
             LnLoan lnLoan = lnLoanService.getLnLoanById(loanId);
          /*   Integer isNogood =lnLoan.getIsNogood();
             String nogoodRemark =lnLoan.getNogoodRemark();*/
             List<LnRepaymentPlan> queryList= lnRepaymentPlanService.queryLnRepaymentPlan(loanId);

             Map<String,Object> paramMap = new HashMap<String, Object>();
             paramMap.put("eventId", LoanConstants.LOAN_DUN_EVENT); //贷后流程
             paramMap.put("loanId",lnLoan.getLoanId());
             //贷款表单附件信息
             List<Form> formAttachmentList = dataFormService.selectFormAttachment(paramMap);
             request.setCharacterEncoding("UTF-8");
             request.setAttribute("lnRepaymentPlanList", queryList);
             request.setAttribute("loanId", loanId);
            /* request.setAttribute("isNogood",isNogood);
             request.setAttribute("nogoodRemark",nogoodRemark);*/
             request.setAttribute("loan",lnLoan);
             request.setAttribute("formAttachmentList",formAttachmentList);
             request.setAttribute("isConfirm",isConfirm);
             List<LnDunLog> lnDunLogList = null;
             // 正常催收
             lnDunLogList = lnDunLogService.getDunLogByLoanId(loanId);  //已优化
             /* for (LnDunLog lnDunLog : lnDunLogList) {
                 //web端电话催收特殊处理
                 if (lnDunLog.getUpdateUser() != null) {
                     RecordDetail d = recordInfoService.getRecordInfoById(lnDunLog.getUpdateUser());
                     if (d != null) {
                         lnDunLog.setRemark(d.getRemark());
                     }
                 }

                 if (lnDunLog.getCustomerDataId() != null && !lnDunLog.getCustomerDataId().equals(0)){
                     customerDataIdList.add(lnDunLog.getCustomerDataId());
                 }
             }*/
             request.setAttribute("lnDunLogList",lnDunLogList);
             
             //所有资料
 	        Map<String,Object> param = new HashMap<String, Object>();
 	        param.put("loanId", loanId);
 	        lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
 	        //查询贷后监控记录
 	        lnLoanMonitorList= lnLoanMonitorService.getLoanMonitorList(loanId);
 	       for(LnLoanMonitor lm:lnLoanMonitorList){
 	    	  if(StringUtils.isNotEmpty(lm.getFileIds())){
	        		List<SysUploadFile>  fileIdList = new ArrayList<SysUploadFile> ();
	        		SysUploadFile flie = new SysUploadFile();
	        		if(lm.getFileIds().contains(",")){
	        			for(String id:lm.getFileIds().split(",")){
	        				flie= sysUploadFileService.getSysUploadFileById(Integer.parseInt(id));
	        				fileIdList.add(flie);
	        			}
	        		}else{
	        			flie= sysUploadFileService.getSysUploadFileById(Integer.parseInt(lm.getFileIds()));
       				fileIdList.add(flie);
	        		}
	        		lm.setFileIdList(fileIdList);
	        	}
	        }
 	        request.setAttribute("lnLoanMonitorList", lnLoanMonitorList);
 	        String[] roles = this.getLoginInfo().getRoles();
            boolean isCustomerManage = false; //是否为客户经理标志
            for (String roleId : roles) {
				if(roleId.equals("7") ){
					isCustomerManage = true;
					break;
				}
			}
            request.setAttribute("isCustomerManage", isCustomerManage);
            
 	        //查询历史操作记录
 	        List<LnOpHistory> hisList = lnOpHistoryService.getAllOpHistoryListByLoanId(loanId);
 	        request.setAttribute("hisList", hisList);
 	        
 	        //所有资料
 	        Map<String,Object> photoMap = new HashMap<String, Object>();
 	        photoMap.put("loanId",loanId);
 	        photoMap.put("eventId",LoanConstants.LOAN_DUN_EVENT);
 	        photoMap.put("statistics",1);
 	        List<LoanData> dataList = customerDataService.getAllLoanDataById(photoMap);
 	        List<Photo> photoList = new ArrayList<Photo>();
 	        //申请人照片资料
 	        for (LoanData d : dataList) {
 	            if (d.getDataType().equals(1)){
 	                if (d.getCustomerId().equals(lnLoan.getCustomerId())){
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
 	        
 	        HashMap<String,Object> checkBoxMessageMap = buildLoanCheckBoxMessage("DKLX","DBFS","DY","ZY","FLXS","HYZK","JYCD","JYCS","JZCSLX","JZZK","XXLY","YSRSP","ZJLX","ZXQK","ZY","ZZXS","JKLX","HFLX","CSFS");
 	    	request.setAttribute("checkBoxMessage", checkBoxMessageMap);
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
             return SUCCESS;
         }catch (Exception e){
             logger.error("dunLoanList",e);
             return ERROR;
         }

    }

    /**
     * 设置不良客户
     *
     */
    public void noGoodCustomer(){
        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- [DunLoanAction % noGoodCustomer]开始");
            String strLoanId = request.getParameter("loanId");
            String nogoodRemark = request.getParameter("nogoodRemark");
            Integer loanId = Integer.parseInt(strLoanId.trim());
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("isNogood",1);
            paramMap.put("loanId",loanId);
            paramMap.put("nogoodRemark",nogoodRemark);
            paramMap.put("isCheckout",1);
            paramMap.put("sortno",null);
            paramMap.put("repaymentDate",null);
            paramMap.put("currentNeedRepay",null);
            paramMap.put("repaymentStatus",null);

            //插入历史记录
            LnOpHistory lnOpHistory = new LnOpHistory();
            lnOpHistory.setUserId(this.getLoginInfo().getUserId());
            lnOpHistory.setOpHistoryDate(new Date());
            lnOpHistory.setBeforeStatusId(6);  //待撤销
            lnOpHistory.setAfterStatusId(6);   //撤销成功
            lnOpHistory.setContent("设置客户为不良客户");
            lnOpHistory.setRemark(nogoodRemark);
            lnOpHistory.setLoanId(Integer.parseInt(strLoanId.trim()));

            //更新客户表为不良客户
            Integer customerId =lnLoanService.getLnLoanById(loanId).getCrmCustomer().getCustomerId();
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("isNogood",1);
            param.put("customerId",customerId) ;
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- [DunLoanAction % noGoodCustomer]完成");
            lnLoanService.setNogoodCustomer(paramMap, lnOpHistory, param);
        }catch (Exception e){
            logger.error("noGoodCustomer", e);
        }
    }

    public void remainFull(){
         try {
             logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- [DunLoanAction % remainFull]开始");
             String flagStr = request.getParameter("flag");
             Integer flag = Integer.parseInt(flagStr);
             String loanIdStr= request.getParameter("loanId");
             Integer loanId = Integer.parseInt(loanIdStr);
             Map<String, Object> param = new HashMap<String, Object>();
             if(flag ==1){
                 param.put("loanId",loanId);
                 param.put("isFull",1);
                 lnLoanService.updateLnLoanById(param);
             }else{
                 param.put("loanId",loanId);
                 param.put("isFull",0);
                 lnLoanService.updateLnLoanById(param);
             }
             logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- [DunLoanAction % remainFull]完成");
         }catch (Exception e){
             logger.error("remainFull",e);
         }
    }

    /**
     * 查询催收记录
     *
     */
    public void queryLnRepaymentPlan(){
        try{
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- [DunLoanAction % queryLnRepaymentPlan]开始");
            String strLoanId = request.getParameter("loanId");
            List<LnRepaymentPlan> queryList= lnRepaymentPlanService.queryLnRepaymentPlan(Integer.parseInt(strLoanId.trim()));
            request.setAttribute("queryList",queryList);
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- [DunLoanAction % queryLnRepaymentPlan]完成");
        }catch (Exception e){
            logger.error("queryLnRepaymentPlan", e);
        }
    }

    /**
     * 当前用户的下属用户
     *
     * @return
     */
    private List<Integer> getSysUserIds() {
        //当前用户的下属用户
//        List<SysUser> sysUsers = deptFacadeService.getBusinessManagerInCharegeOfUsers();
//        List<Integer> applyUserIds = new ArrayList<Integer>();
//        for (SysUser sysUser : sysUsers) {
//            applyUserIds.add(sysUser.getUserId());
//        }
//        return applyUserIds;
        List<Integer> applyUserIds = new ArrayList<Integer>();
        String belongUserIds = deptFacadeService.getUserIdsBelongToManager(this.getLoginInfo().getRoles(),"loanInfo");
        if (StringUtils.isNotEmpty(belongUserIds)){
            String[] belongUserIdArr = belongUserIds.split(",");
            if (belongUserIdArr != null && belongUserIdArr.length > 0){
                for (String belongUserId : belongUserIdArr){
                    applyUserIds.add(Integer.parseInt(belongUserId));
                }
            }
        }
        return applyUserIds;
    }



    public LnLoanTypeService getLnLoanTypeService() {
        return lnLoanTypeService;
    }

    public void setLnLoanTypeService(LnLoanTypeService lnLoanTypeService) {
        this.lnLoanTypeService = lnLoanTypeService;
    }

    public LnLoanService getLnLoanService() {
        return lnLoanService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public LnRepaymentPlanService getLnRepaymentPlanService() {
        return lnRepaymentPlanService;
    }

    public void setLnRepaymentPlanService(LnRepaymentPlanService lnRepaymentPlanService) {
        this.lnRepaymentPlanService = lnRepaymentPlanService;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public Date getRepaymentStartDate() {
        return repaymentStartDate;
    }

    public void setRepaymentStartDate(Date repaymentStartDate) {
        this.repaymentStartDate = repaymentStartDate;
    }

    public Date getRepaymentEndDate() {
        return repaymentEndDate;
    }

    public void setRepaymentEndDate(Date repaymentEndDate) {
        this.repaymentEndDate = repaymentEndDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }
	public static Logger getLogger() {
		return logger;
	}
	public static void setLogger(Logger logger) {
		DunLoanAction.logger = logger;
	}
	public LnLoanDetailService getLnLoanDetailService() {
		return lnLoanDetailService;
	}
	public void setLnLoanDetailService(LnLoanDetailService lnLoanDetailService) {
		this.lnLoanDetailService = lnLoanDetailService;
	}
	public LnOpHistoryService getLnOpHistoryService() {
		return lnOpHistoryService;
	}
	public void setLnOpHistoryService(LnOpHistoryService lnOpHistoryService) {
		this.lnOpHistoryService = lnOpHistoryService;
	}
	public LnLoanMonitorService getLnLoanMonitorService() {
		return lnLoanMonitorService;
	}
	public void setLnLoanMonitorService(LnLoanMonitorService lnLoanMonitorService) {
		this.lnLoanMonitorService = lnLoanMonitorService;
	}
	public DataFormService getDataFormService() {
		return dataFormService;
	}
	public void setDataFormService(DataFormService dataFormService) {
		this.dataFormService = dataFormService;
	}
	public LnDunLogService getLnDunLogService() {
		return lnDunLogService;
	}
	public void setLnDunLogService(LnDunLogService lnDunLogService) {
		this.lnDunLogService = lnDunLogService;
	}
	public List<LnLoanMonitor> getLnLoanMonitorList() {
		return lnLoanMonitorList;
	}
	public void setLnLoanMonitorList(List<LnLoanMonitor> lnLoanMonitorList) {
		this.lnLoanMonitorList = lnLoanMonitorList;
	}
	public LnLoanInfoService getLnLoanInfoService() {
		return lnLoanInfoService;
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
	public LnCreditHistoryService getLnCreditHistoryService() {
		return lnCreditHistoryService;
	}
	public void setLnCreditHistoryService(
			LnCreditHistoryService lnCreditHistoryService) {
		this.lnCreditHistoryService = lnCreditHistoryService;
	}
	public LnLoanInfo getLnLoanInfo() {
		return lnLoanInfo;
	}
	public void setLnLoanInfo(LnLoanInfo lnLoanInfo) {
		this.lnLoanInfo = lnLoanInfo;
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
	public List<LnCreditHistory> getLnCreditHistoryList() {
		return lnCreditHistoryList;
	}
	public void setLnCreditHistoryList(List<LnCreditHistory> lnCreditHistoryList) {
		this.lnCreditHistoryList = lnCreditHistoryList;
	}
	public CustomerDataService getCustomerDataService() {
		return customerDataService;
	}
	public void setCustomerDataService(CustomerDataService customerDataService) {
		this.customerDataService = customerDataService;
	}
	public Integer getLoanId() {
		return loanId;
	}
	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}
	public Date getMonDate() {
		return monDate;
	}
	public void setMonDate(Date monDate) {
		this.monDate = monDate;
	}
	public BaseLnLoanMonitor getLnLoanMonitor() {
		return lnLoanMonitor;
	}
	public void setLnLoanMonitor(BaseLnLoanMonitor lnLoanMonitor) {
		this.lnLoanMonitor = lnLoanMonitor;
	}
	public LnLoanPledgeService getLnLoanPledgeService() {
		return lnLoanPledgeService;
	}
	public void setLnLoanPledgeService(LnLoanPledgeService lnLoanPledgeService) {
		this.lnLoanPledgeService = lnLoanPledgeService;
	}
	public SysUploadFileService getSysUploadFileService() {
		return sysUploadFileService;
	}
	public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
		this.sysUploadFileService = sysUploadFileService;
	}
	public SysParamService getSysParamService() {
		return sysParamService;
	}
	public void setSysParamService(SysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}
	public SysTeamUserService getSysTeamUserService() {
		return sysTeamUserService;
	}
	public void setSysTeamUserService(SysTeamUserService sysTeamUserService) {
		this.sysTeamUserService = sysTeamUserService;
	}

    
}
