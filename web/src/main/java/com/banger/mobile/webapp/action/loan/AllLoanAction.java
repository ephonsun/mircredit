package com.banger.mobile.webapp.action.loan;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.data.LoanData;
import com.banger.mobile.domain.model.data.Photo;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.loan.*;
import com.banger.mobile.domain.model.system.SysTeam;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.domain.model.user.SysUserBean;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataPhotoService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.loan.*;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.specialDataAuth.SpecialDataAuthService;
import com.banger.mobile.facade.system.team.SysTeamService;
import com.banger.mobile.facade.system.team.SysTeamUserService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.util.FileUtil;
import com.banger.mobile.util.ServerRealPathUtil;
import com.banger.mobile.util.StringUtil;
import com.banger.mobile.webapp.action.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA. User: yuanme Date: 13-2-5 Time: 下午4:13 To change
 * this template use File | Settings | File Templates.
 * <p/>
 * 所有贷款 Action
 */
public class AllLoanAction extends BaseAction {

    private static Logger         logger = Logger.getLogger(AllLoanAction.class);

    // services
    private LnLoanService         lnLoanService;                                 // 贷款service
    private DeptFacadeService     deptFacadeService;                             // 部门组织结构service
    private LnLoanTypeService     lnLoanTypeService;                             // 贷款类型service
    private LnLoanStatusService   lnLoanStatusService;
    private LnCancelReasonService lnCancelReasonService;                         // 撤销贷款原因service
    private LnOpHistoryService    lnOpHistoryService;                            // 贷款操作历史记录service
    private DataPhotoService	  dataPhotoService;
    private LnLoanDetailService   lnLoanDetailService;
    private CrmCustomerService	  crmCustomerService;
    private CustomerDataService customerDataService;
    private SpecialDataAuthService specialDataAuthService;
    private SysParamService sysParamService;
    private LnLoanInfoService lnLoanInfoService;
    private SysTeamUserService sysTeamUserService;
    private SysTeamService sysTeamService;
    private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
    private LnLoanGuarantorService lnLoanGuarantorService;
    private List<LnCreditHistory> lnCreditHistoryList;
    private LnCreditHistoryService lnCreditHistoryService;

    // 查询条件
    private String                customer;
    private Integer               loanStatus;
    private Date                  startDate;
    private Date                  endDate;
    private Integer               checkoutStatus;
    private String                userIds;
    private Date                  startCreateDate;
    private Date                  endCreateDate;
    private String 				  surveyUser;
    private String 				  isReject;
    private String 				  loanTypeId;
    private String 				  teamId;
    private String 				  resultRepayDate;
    private String 				  monitored;
    private String 				  monIsCheck;
    private String nomontype;
    private SysUserService sysUserService;

	private List<LnLoan> lnLoans;	//贷款list
    private List<LoanData> loanDatas;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private LnLoanPledgeService lnLoanPledgeService;


    
    
    /**
     * 首页列表
     * 
     * @return
     */
    public String allLoanList() {
        try {
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 列表初次进入所有贷款，开始查询...");
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            // 是否是业务主管
          //  Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
            String belongUserIds = "";
            String[] roles = this.getLoginInfo().getRoles();
            boolean isInChargeOf = false;
            for (String roleId : roles) {
				if(roleId.equals("5")){
					isInChargeOf = true;
					break;
				 }
			}
            logger.info("是否为团队主管"+isInChargeOf);
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
                parameterMap.put("belongUserIds", belongUserIds); // 当前用户所管理的提交申请用户
            } else {
            	String role = roles[0];
            	if(role.equals("7")){  // 客户经理
            		parameterMap.put("surveyUserId", this.getLoginInfo().getUserId());
            	}else if(role.equals("4")){
            		parameterMap.put("approveBackerUserId", this.getLoginInfo().getUserId());
            	}
            }
            parameterMap.put("allLoanOrderBy", 1);
            String isWBApproveLoan = request.getParameter("isWBApproveLoan");
            if (StringUtils.isNotEmpty(isWBApproveLoan)){
                parameterMap.put("loanStatusId",LoanConstants.LOAN_APPROVE_EVENT);
                request.setAttribute("isWBApproveLoan",isWBApproveLoan);
            }
            if(roles[0].equals("1")){
                parameterMap.put("loanStatusId",6+","+5);
            }
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索条件的权限用户为："+parameterMap.get("belongUserIds")==null?parameterMap.get("belongUserId"):belongUserIds);
            // 分页查询
            PageUtil<LnLoan> dataList = lnLoanService.getAllLoanByPage(parameterMap,
                this.getPage());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 总共搜索出"+dataList.getItems().size()+"条贷款");

            //处理贷款客户的权限
//            this.proCusAuthority(isInChargeOf, belongUserIds, dataList);
         //   lnLoanService.proCusAuthority(isInChargeOf,belongUserIds,dataList,this.getLoginInfo());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索完毕！");
            // 贷款状态
            List<LnLoanStatus> statusList = lnLoanStatusService.getLoanStatusList();

            request.setAttribute("isInChargeOf", isInChargeOf);
            request.setAttribute("statusList", statusList);
            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
            request.setAttribute("roles",roles[0]);
            request.setAttribute("userId",this.getLoginInfo().getUserId());//用户ID
            request.setAttribute("userName",this.getLoginInfo().getUserName());
            request.setAttribute("dataCode", this.getLoginInfo().getDataCompetence()); //用户数据权限
            List<SysTeam> sysTeamList = sysTeamService.getSysTeamList();
        	request.setAttribute("sysTeamList", sysTeamList);
        	
            return SUCCESS;
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("allLoanList", e);
            return ERROR;
        }
    }

    /**
     * 处理贷款客户的权限
     * 
     * @param inChargeOf
     * @param belongUserIds
     * @param dataList
     */
    private void proCusAuthority(Boolean inChargeOf, String belongUserIds, PageUtil<LnLoan> dataList) {
        if (dataList != null && dataList.getItems().size() > 0){
            if (inChargeOf){
                List<Integer> inChargeDepts = new ArrayList<Integer>();
                String roleIds= StringUtil.getIdsString(getLoginInfo().getRoles());
                boolean flag=specialDataAuthService.getSysDataAuthCondition(roleIds,"loanInfo");
                if(flag){
                    inChargeDepts.add(this.getLoginInfo().getDeptId());
                }else{
                    Integer[] inChargeDeptsIntegers = deptFacadeService.getInChargeOfDeptIds();
                    for (Integer deptId : inChargeDeptsIntegers) {
                        inChargeDepts.add(deptId);
                    }
                }
                List<Integer> belongUserIdList = new ArrayList<Integer>();
                if (belongUserIds.length() > 0){
                    String[] belongUserIdArr = belongUserIds.split(",");
                    for (String belongUserId : belongUserIdArr){
                        belongUserIdList.add(Integer.parseInt(belongUserId));
                    }
                }
                for (LnLoan lnLoan : dataList.getItems()){
                    Integer belongUserId = lnLoan.getCrmCustomer().getBelongUserId();
                    Integer belongDeptId = lnLoan.getCrmCustomer().getBelongDeptId();
                    if (belongUserId != null && belongUserId != 0){
                        //当前贷款客户存在归属
                        if (belongUserIdList.contains(belongUserId)){
                            lnLoan.setHasPermission(1);
                        }else {
                            lnLoan.setHasPermission(0);
                        }
                    }else if (belongDeptId != null && belongDeptId != 0){
                        //不存在归属人员，只归属于机构
                        if (inChargeDepts.contains(belongDeptId)){
                            lnLoan.setHasPermission(1);
                        }else {
                            lnLoan.setHasPermission(0);
                        }
                    }
                }
            }
        }
    }

    /**
     * 查询列表
     * 
     * @return
     */
    public String allLoanListQuery() {
        try {
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 条件搜索，开始查询所有贷款...");
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            // 是否是业务主管
            String belongUserIds = "";
            String[] roles = this.getLoginInfo().getRoles();
            boolean isInChargeOf = false;
            for (String roleId : roles) {
				if(roleId.equals("5")){
					isInChargeOf = true;
					break;
				}
			}
            logger.info("是否为团队主管"+isInChargeOf);
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
                parameterMap.put("belongUserIds", belongUserIds); // 当前用户所管理的提交申请用户
            } else {
            	String role = roles[0];
            	if(role.equals("7")){  // 客户经理
            		parameterMap.put("surveyUserId", this.getLoginInfo().getUserId());
            	}else if(role.equals("4")){
            		parameterMap.put("approveBackerUserId", this.getLoginInfo().getUserId());
            	}
            }
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 以下为搜索条件：");
            if (loanStatus != null) {
                parameterMap.put("loanStatusId", loanStatus); // 贷款状态
                logger.info("贷款状态："+loanStatus);
            } else {
                parameterMap.put("allLoanOrderBy", 1);
            }

            parameterMap.put("surveyUser", surveyUser);
            if (surveyUser != null && !surveyUser.equals("")){
                logger.info("调查人员："+surveyUser);
            }
            
            if (isReject != null && !isReject.equals("")){
            	parameterMap.put("isReject", isReject);
            }
            
            if (loanTypeId != null && !loanTypeId.equals("")){
            	parameterMap.put("loanTypeId", loanTypeId);
            }
            if (teamId != null && !teamId.equals("")){
            	parameterMap.put("teamId", teamId);
            }
            if (resultRepayDate != null && !resultRepayDate.equals("")){
                parameterMap.put("resultRepayDate", resultRepayDate);
                logger.info("决议还款日："+resultRepayDate);
            }
            if (nomontype != null && !nomontype.equals("")){
                logger.info("未完成监控类型："+nomontype);
                parameterMap.put("nomontype", nomontype);
            }
            parameterMap.put("customer", customer);
            if (customer != null && !customer.equals("")){
                logger.info("客户："+customer);
            }
            parameterMap.put("loanIsCheckout", checkoutStatus); //近期以监控
            if (checkoutStatus != null && !checkoutStatus.equals("")){
                logger.info("贷后状态："+checkoutStatus);
            }
            parameterMap.put("startCreateDate", startCreateDate);
            if (startCreateDate != null){
                logger.info("创建时间的开始时间："+startCreateDate);
            }
            endCreateDate = lnLoanService.addSecondsForDate(endCreateDate,59);
            if (endCreateDate != null){
                logger.info("创建时间的结束时间："+endCreateDate);
            }
            parameterMap.put("endCreateDate", endCreateDate);
            parameterMap.put("applyStartDate", startDate);
            if (startDate != null){
                logger.info("提交时间的开始时间："+startDate);
            }
            endDate = lnLoanService.addSecondsForDate(endDate,59);
            if (endDate != null){
                logger.info("提交时间的结束时间："+endDate);
            }
            
            parameterMap.put("applyEndDate", endDate);
            if (monitored != null && !monitored.equals("")){
            	
            		 parameterMap.put("monitored", monitored);
            }
            if (monIsCheck != null && !monIsCheck.equals("")){
            	
       		 parameterMap.put("monIsCheck", monIsCheck);
            }
            
            parameterMap.put("hasAuthUserId", this.getLoginInfo().getUserId());

            //贷款归属
            String belongToType = request.getParameter("BelongToType");
            if (StringUtils.isNotEmpty(belongToType)){
                if (belongToType.equals("brAll")){
                    //所有，包括下属用户和下属机构
//                    parameterMap.put("cusBelongUserIds",belongUserIds);
                }else if (belongToType.equals("brMine")){
                    //我的
                    parameterMap.put("cusBelongUserIds",this.getLoginInfo().getUserId().toString());
                    logger.info("归属人员(我的)："+this.getLoginInfo().getUserId());
                }else if (belongToType.equals("brUser")){
                    //下属用户
                    parameterMap.put("cusBelongUserIds",userIds);
                    logger.info("归属人员(下属)："+userIds);
                }else if (belongToType.equals("brDept")){
                    //机构的
                    String deptIds = request.getParameter("deptIds");
                    parameterMap.put("cusBelongDeptIds",deptIds);
                    logger.info("归属人员(机构)："+deptIds);
                }else if (belongToType.equals("brUnAllocate")){
                    //未分配
                    parameterMap.put("unAllocate","1");
                    logger.info("归属人员(未分配)");
                }
            }
            if(roles[0].equals("1")){
                parameterMap.put("loanStatusId",6+","+5);
            }
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 开始搜索");
//            parameterMap.put("cusBelongUserIds",userIds);

            PageUtil<LnLoan> dataList = lnLoanService.getAllLoanByPage(parameterMap,
                this.getPage());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索到"+dataList.getItems().size()+"条符合条件的贷款");
            //处理贷款客户的权限
         //   lnLoanService.proCusAuthority(isInChargeOf,belongUserIds,dataList,this.getLoginInfo());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索完毕");

            request.setAttribute("dataList", dataList.getItems());
            request.setAttribute("recordCount", dataList.getPage().getTotalRowsAmount());
            request.setAttribute("roles",roles[0]);

            return SUCCESS;
        } catch (Exception e) {
            logger.error("allLoanListQuery", e);
            e.printStackTrace();
            return ERROR;
        }
    }
    
    public String lnLoanShow(){
        Map<String, Object> param = new HashMap<String, Object>();
        String loanId =  request.getParameter("loanId");
        param.put("loanId", loanId);
        LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);
        request.setAttribute("loanId", request.getParameter("loanId"));
        request.setAttribute("customerId",lnLoanInfo.getCustomerId());
        String loanStatusId = request.getParameter("loanStatusId");
        String loanStatusInfo = "";
        if(!StringUtil.isBlank(loanStatusId)&&!"undefined".equals(loanStatusId)){
            loanStatusInfo = lnLoanStatusService.getLoanStatusName(loanStatusId);
        }
        request.setAttribute("loanStatusInfo",loanStatusInfo);
    	 return SUCCESS;

    }

    /**
     * 小頁卡--贷款信息(查看)
     * 
     * @return
     */
    public String getAllLoanByCusId() {
        try {
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 小页卡贷款信息查询...");
            String customerIdStr = request.getParameter("customerId");
            if (StringUtils.isNotEmpty(customerIdStr)) {
                Integer customerId = Integer.parseInt(customerIdStr.trim());
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("customerId", customerId);
                logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索条件为当前客户，且当前客户customerId:"+customerId);
                PageUtil<LnLoan> list = lnLoanService.getAllLoanByCusId(paramMap, this.getPage());
                logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 小页卡贷款信息查询完毕,总共查询出"+list.getItems().size()+"条符合条件的贷款");
                request.setAttribute("dataList", list.getItems());
                request.setAttribute("customerId", customerId);
                String needLine = request.getParameter("needLine");
//                request.setAttribute("needLine", needLine==null?1:needLine);
                request.setAttribute("needLine", needLine);
            }
            return SUCCESS;
        } catch (Exception e) {
            logger.error("AllLoanAction % getAllLoanByCusId", e);
            return ERROR;
        }
    }

    /**
     * 小頁卡--贷款信息(查询)
     * 
     * @return
     */
    public String queryAllLoanByCusId() {
        try {
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 小页卡贷款信息查询...");
            String customerIdStr = request.getParameter("customerId");
            if (StringUtils.isNotEmpty(customerIdStr)) {
                Integer customerId = Integer.parseInt(customerIdStr.trim());
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("customerId", customerId);
                logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索条件为当前客户，且当前客户customerId:"+customerId);
                PageUtil<LnLoan> list = lnLoanService.getAllLoanByCusId(paramMap, this.getPage());
                logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 小页卡贷款信息查询完毕,总共查询出"+list.getItems().size()+"条符合条件的贷款");
                request.setAttribute("dataList", list.getItems());
                request.setAttribute("customerId", customerId);
                String needLine = request.getParameter("needLine");
//                request.setAttribute("needLine", needLine==null?1:needLine);
                request.setAttribute("needLine", needLine);
            }
            return SUCCESS;
        } catch (Exception e) {
            logger.error("AllLoanAction % getAllLoanByCusId", e);
            return ERROR;
        }
    }

    /**
     * 当前用户的下属用户
     * 
     * @return
     */
    private List<Integer> getSysUserIds() {
        // 当前用户的下属用户
        List<SysUser> sysUsers = deptFacadeService.getBusinessManagerInCharegeOfUsers();
        List<Integer> applyUserIds = new ArrayList<Integer>();
        for (SysUser sysUser : sysUsers) {
            applyUserIds.add(sysUser.getUserId());
        }
        return applyUserIds;
    }

    /**
     * 获取查询条件时的申请人id(提交人id)列表
     * 
     * @param applyUser
     * @return
     */
    private List<Integer> stringToList(String applyUser) {
        if (StringUtils.isNotEmpty(applyUser)) {
            String[] paramArray = applyUser.split(",");
            List<Integer> resultList = new ArrayList<Integer>();
            for (String str : paramArray) {
                resultList.add(Integer.parseInt(str));
            }
            return resultList;
        }
        return null;
    }

    /**
     * 工作台 贷款提醒
     * @return
     */
    public String getLoanToWorkPlace(){
		try {
			logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount()
					+ "----- 得到工作台下的贷款提醒的所有贷款状态的各自数量情况...");
			Map<Integer, Integer> map = lnLoanService
					.getLoanCountByLoanStatus(this.getLoginInfo().getUserId());

			Map<String, Object> parameterMap = new HashMap<String, Object>();
			// 是否是业务主管
			// Boolean isInChargeOf =
			// deptFacadeService.isInChargeOfDepartment();
			String belongUserIds = "";
			String[] roles = this.getLoginInfo().getRoles();
			boolean isInChargeOf = false;
			for (String roleId : roles) {
				if (roleId.equals("5")||roleId.equals("6")) {
					isInChargeOf = true;
					break;
				}
			}
			if (isInChargeOf) {
				// 当前用户的团队底下的成员
				List<String> belongUserId = sysTeamUserService
						.getUserIdsByChiefUserId(this.getLoginInfo()
								.getUserId());
				int i = 0;
				for (String str : belongUserId) {
					if (i == 0) {
						belongUserIds += str;
						i = 1;
					} else {
						belongUserIds += "," + str;
					}
				}
				parameterMap.put("belongUserIds", belongUserIds); // 当前用户所管理的提交申请用户
			} else {

				String role = roles[0];
				if (role.equals("7")) { // 客户经理
					parameterMap.put("surveyUserId", this.getLoginInfo()
							.getUserId());
				} else if ( role.equals("4")) {
					parameterMap.put("approveBackerUserId", this.getLoginInfo()
							.getUserId());
				}
			}
			parameterMap.put("allLoanOrderBy", 1);
			/*
			 * String isWBApproveLoan = request.getParameter("isWBApproveLoan");
			 * if (StringUtils.isNotEmpty(isWBApproveLoan)){
			 * parameterMap.put("loanStatusId"
			 * ,LoanConstants.LOAN_APPROVE_EVENT);
			 * request.setAttribute("isWBApproveLoan",isWBApproveLoan); }
			 */
			logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount()
					+ "----- 搜索条件的权限用户为：" + parameterMap.get("belongUserIds") == null ? parameterMap
					.get("belongUserId") : belongUserIds);
			parameterMap.put("startRow", 1);
			parameterMap.put("endRow", 10000);
			// 分页查询
			PageUtil<LnLoan> dataList = lnLoanService.getAllLoanByPage(
					parameterMap, this.getPage());
			lnLoans = dataList.getItems();
			logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount()
					+ "----- 总共搜索出" + dataList.getItems().size() + "条贷款");
			request.setAttribute("needSubmitCount", map.get(1));
			request.setAttribute("needAssignCount", map.get(2));
			request.setAttribute("unExamCount", map.get(3));
			request.setAttribute("unApproveCount", map.get(4));
			request.setAttribute("unMakeCount", map.get(5));
			request.setAttribute("repaymentCount", map.get(6));
			request.setAttribute("status", 1);
			logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount()
					+ "----- 具体数量情况如下：");
			logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount()
					+ "----- 申请-->" + map.get(1) + ";分配-->" + map.get(2)
					+ ";调查-->" + map.get(3) + ";审批-->" + map.get(4) + ";放贷-->"
					+ map.get(5) + ";催收-->" + map.get(6));
			logger.info("-----当前登录用户：" + this.getLoginInfo().getAccount()
					+ "----- 工作台贷款数量情况搜索完毕！");
			if (deptFacadeService.isInChargeOfDepartment())
				request.setAttribute("ismanage", 1);
			else if (deptFacadeService.isCommon())
				request.setAttribute("ismanage", 0);
			return SUCCESS;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("AllLoanAction: getLoanToWorkPlace error:", e);
            return ERROR;
		}
    }
    
    /**
     * 工作台 根据类型查询贷款详情
     * @return
     */
    public String getLoanToWPbyType(){
    	try {
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 点击查看工作台各状态下的贷款提醒情况...");
        	String type = request.getParameter("type");
        	 Map<String, Object> parameterMap = new HashMap<String, Object>();
        	if(type.equals("1")){//全部状态
        		parameterMap.put("allLoanOrderBy", 1);
            }else if(type.equals("2")){
            	 parameterMap.put("loanStatusId", 1); // 贷款状态
                 logger.info("贷款状态："+loanStatus);
        	}else if(type.equals("3")){
              	 parameterMap.put("loanStatusId", 2); // 贷款状态
                 logger.info("贷款状态："+loanStatus);
            }
            else if(type.equals("4")){
           	 parameterMap.put("loanStatusId", 3); // 贷款状态
             logger.info("贷款状态："+loanStatus);
        	}
        	else if(type.equals("5")){
           	 parameterMap.put("loanStatusId", 4); // 贷款状态
                logger.info("贷款状态："+loanStatus);
        	}else if(type.equals("6")){
              	 parameterMap.put("loanStatusId", 5); // 贷款状态
                 logger.info("贷款状态："+loanStatus);
         	}else if(type.equals("7")){
              	 parameterMap.put("loanStatusId", 6); // 贷款状态
                 logger.info("贷款状态："+loanStatus);
         	}
        	
        	
             // 是否是业务主管
           //  Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
             String belongUserIds = "";
             String[] roles = this.getLoginInfo().getRoles();
             boolean isInChargeOf = false;
             for (String roleId : roles) {
 				if(roleId.equals("5")||roleId.equals("6")){
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
                 parameterMap.put("belongUserIds", belongUserIds); // 当前用户所管理的提交申请用户
             } else {
             	
             	String role = roles[0];
             	if(role.equals("7")){  // 客户经理
             		parameterMap.put("surveyUserId", this.getLoginInfo().getUserId());
             	}else if(role.equals("4")){
             		parameterMap.put("approveBackerUserId", this.getLoginInfo().getUserId());
             	}
             }
            /* String isWBApproveLoan = request.getParameter("isWBApproveLoan");
             if (StringUtils.isNotEmpty(isWBApproveLoan)){
                 parameterMap.put("loanStatusId",LoanConstants.LOAN_APPROVE_EVENT);
                 request.setAttribute("isWBApproveLoan",isWBApproveLoan);
             }*/
             logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索条件的权限用户为："+parameterMap.get("belongUserIds")==null?parameterMap.get("belongUserId"):belongUserIds);
             parameterMap.put("startRow",1);
             parameterMap.put("endRow",10000);
             // 分页查询
             PageUtil<LnLoan> dataList = lnLoanService.getAllLoanByPage(parameterMap,this.getPage());
             lnLoans = dataList.getItems();
             logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 总共搜索出"+dataList.getItems().size()+"条贷款");
            Integer count = 0;
            //得到各个状态的贷款数
            Map<Integer, Integer> map = lnLoanService.getLoanCountByLoanStatus(this.getLoginInfo().getUserId());
            if (type.equals("2")) {
                if (map.get(1) != null){
                    count = map.get(1);
                }
            }else if (type.equals("3")){
                if (map.get(2) != null){
                    count = map.get(2);
                }
            }else if (type.equals("4")){
                if (map.get(3) != null){
                    count = map.get(3);
                }
            }else if (type.equals("5")){
                if (map.get(4) != null){
                    count = map.get(4);
                }
            }else if (type.equals("6")){
                if (map.get(5) != null){
                    count = map.get(5);
                }
            }else if (type.equals("7")){
                if (map.get(6) != null){
                    count = map.get(6);
                }
            }
        	request.setAttribute("status", type);
//        	request.setAttribute("count", lnLoans.size());
        	request.setAttribute("count", count);
			return SUCCESS;
		} catch (Exception e) {
			logger.error("AllLoanAction: getLoanToWPbyType error:", e);
            return ERROR;
		}
    }

    private void createAndClearDir(String dir){
        try {
            File fileDir = new File(dir);
            //删除一个小时之前的文件3600000
            if(fileDir.exists()&&fileDir.isDirectory()){
                File[] files = fileDir.listFiles();
                if(null!=files){
                    for (int i = 0; i < files.length; i++) {
                        if(files[i].exists()&&files[i].isFile()&&(System.currentTimeMillis()-files[i].lastModified()>3600000)){
                            files[i].delete();
                        }
                    }
                }
            }else{
                fileDir.mkdirs();
            }
        }catch (Exception e){
            logger.error("AllLoanAction: createAndClearDir error:", e);
        }

    }

    /**
     * 查看客户资料照片详情层
     * @return
     */
    public String queryPhotoDetail(){
        try {
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 开始查询某客户的照片资料...");
        	String photoLoanId = request.getParameter("photoLoanId");
        	String photoEventId = request.getParameter("photoEventId");
        	String customerId = request.getParameter("customerId");
        	String photoTypeId = request.getParameter("photoTypeId");
        	if(photoTypeId==null||"".equals(photoTypeId)) photoTypeId="0";
        	String photoRowNum = request.getParameter("photoRowNum");
        	String systemPath = this.getRootPath()+File.separator+"Records";

            createAndClearDir(systemPath);

        	String Num = request.getParameter("Num");
        	int num=0;
        	int num1=0;
        	if(Num != null && !Num.equals("")){
        		num=Integer.parseInt(Num)-1;


        	if(StringUtils.isNotBlank(photoTypeId)){
        		 num1 =lnLoanDetailService.getLoanDetailPhotoDataByEventCount(Integer.parseInt(photoLoanId), Integer.parseInt(photoEventId),Integer.parseInt(customerId),Integer.parseInt(photoTypeId));
        	}else{
        		 num1 =lnLoanDetailService.getLoanDetailPhotoDataByEventCount(Integer.parseInt(photoLoanId), Integer.parseInt(photoEventId),Integer.parseInt(customerId));
        	}


        	}
        	 int t=Integer.parseInt(photoTypeId)-1;
			 photoTypeId =String.valueOf(t);
			 int num2=0;
				if(StringUtils.isNotBlank(photoTypeId)){
					 num2 =lnLoanDetailService.getLoanDetailPhotoDataByEventCount(Integer.parseInt(photoLoanId), Integer.parseInt(photoEventId),Integer.parseInt(customerId),Integer.parseInt(photoTypeId));

	        	}else{
	        		 num2 =lnLoanDetailService.getLoanDetailPhotoDataByEventCount(Integer.parseInt(photoLoanId), Integer.parseInt(photoEventId),Integer.parseInt(customerId));

	        	}

				 photoRowNum =String.valueOf(num1-(num2+num));
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索条件为，贷款id:"+photoLoanId+",事件id:"+photoEventId+",客户id:"+customerId);
        	if(StringUtils.isNotBlank(photoTypeId)){
        		loanDatas = lnLoanDetailService.getLoanDetailPhotoDataByEvent(Integer.parseInt(photoLoanId), Integer.parseInt(photoEventId),Integer.parseInt(customerId),Integer.parseInt(photoTypeId));
        	}else{
        		loanDatas = lnLoanDetailService.getLoanDetailPhotoDataByEvent(Integer.parseInt(photoLoanId), Integer.parseInt(photoEventId),Integer.parseInt(customerId));
        	}
        	
            int i;

        	for(i = 0 ; i < loanDatas.size() ; i ++){
        		if(photoRowNum != null && !photoRowNum.equals("")){

        			if(loanDatas.get(i).getRowNum().compareTo(Integer.parseInt(photoRowNum)-1) == 0)break;

            	}else{
            		photoRowNum="1";
        		if(loanDatas.get(i).getRowNum().compareTo(0) == 0)break;
        	}}
            Integer photoId = Integer.valueOf(Integer.valueOf(request.getParameter("photoId")));
            Photo photo = dataPhotoService.getPhotoById(photoId);
        	request.setAttribute("photoName", photo.getPhotoName());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 得到当前显示的照片："+loanDatas.get(i).getDataName());

        	String copyFileName = customerDataService.readFile(photo.getFileId()+"", systemPath);
            request.setAttribute("file", copyFileName.substring(copyFileName.lastIndexOf(File.separator)+1,copyFileName.length()));
        	request.setAttribute("pid", request.getParameter("pid"));
        	if(Num != null && !Num.equals("")){
        		request.setAttribute("photoCount1",num2);
        		request.setAttribute("photoCount",num1);
        	}else{
        	request.setAttribute("photoCount",loanDatas.size());
        	request.setAttribute("photoCount1",0);
        	}

        	request.setAttribute("photoLoanId", photoLoanId);
        	request.setAttribute("photoEventId", photoEventId);
        	request.setAttribute("photoTypeId", Integer.parseInt(photoTypeId)+1);
        	request.setAttribute("Num", Num);
//        	request.setAttribute("photoRowNum", Integer.parseInt(photoRowNum)-1);
            request.setAttribute("photoRowNum", photo.getNumRow()-1);
        	request.setAttribute("customerName", crmCustomerService.getCrmCustomerById(Integer.parseInt(customerId)).getCustomerName());
//        	request.setAttribute("uploadUserName", loanDatas.get(i).getUploadUserName());
            request.setAttribute("uploadUserName", photo.getUploadUserName());
//        	request.setAttribute("uploadDate", df.format(loanDatas.get(i).getUploadDate()));
            request.setAttribute("uploadDate", df.format(photo.getUpdateDate()));
//        	if(loanDatas.get(i).getRecordDate()!=null){
//        		request.setAttribute("recordDate", df.format(loanDatas.get(i).getRecordDate()));
//        	}
            if(photo.getRecordDate()!=null){
                request.setAttribute("recordDate", df.format(photo.getRecordDate()));
            }
//        	request.setAttribute("photoId", loanDatas.get(i).getDataId());
            request.setAttribute("photoId", photo.getPhotoId());
//        	request.setAttribute("remark", loanDatas.get(i).getRemark());
            request.setAttribute("remark", photo.getRemark());
            request.setAttribute("customerId", customerId);
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 查询某客户的照片资料完毕");
            
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AllLoanAction queryPhotoDetail error:" + e.getMessage());
            return ERROR;
        }
    }
    public String queryPhotoAll(){
        try {
        logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 开始查询某客户的照片资料...");
        String photoLoanId = request.getParameter("photoLoanId");
        String photoEventId = request.getParameter("photoEventId");
        String customerId = request.getParameter("customerId");
        String photoTypeId = request.getParameter("photoTypeId");
        if(photoTypeId==null) photoTypeId="0";
        String photoRowNum = request.getParameter("photoRowNum");
        String systemPath = this.getRootPath()+File.separator+"Records";

        createAndClearDir(systemPath);

        logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索条件为，贷款id:"+photoLoanId+",事件id:"+photoEventId+",客户id:"+customerId);
        if(StringUtils.isNotBlank(photoTypeId)){
            loanDatas = lnLoanDetailService.getLoanDetailPhotoDataByEvent(Integer.parseInt(photoLoanId), Integer.parseInt(photoEventId),Integer.parseInt(customerId),Integer.parseInt(photoTypeId));
        }else{
            loanDatas = lnLoanDetailService.getLoanDetailPhotoDataByEvent(Integer.parseInt(photoLoanId), Integer.parseInt(photoEventId),Integer.parseInt(customerId));
        }

//        Integer photoId = Integer.valueOf(Integer.valueOf(request.getParameter("photoId")));
//        Photo photo = dataPhotoService.getPhotoById(photoId);
        request.setAttribute("photoName", loanDatas.get(0).getDataName());
        logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 得到当前显示的照片："+loanDatas.get(0).getDataName());

        String copyFileName = customerDataService.readFile(loanDatas.get(0).getFileId()+"", systemPath);
        request.setAttribute("file", copyFileName.substring(copyFileName.lastIndexOf(File.separator)+1,copyFileName.length()));
        request.setAttribute("pid", request.getParameter("pid"));
        request.setAttribute("photoLoanId", photoLoanId);
        request.setAttribute("photoEventId", photoEventId);
//        request.setAttribute("photoTypeId", Integer.parseInt(photoTypeId)+1);
//        	request.setAttribute("photoRowNum", Integer.parseInt(photoRowNum)-1);
//        request.setAttribute("photoRowNum", photo.getNumRow()-1);
        request.setAttribute("customerName", crmCustomerService.getCrmCustomerById(Integer.parseInt(customerId)).getCustomerName());
        	request.setAttribute("uploadUserName", loanDatas.get(0).getUploadUserName());
//        request.setAttribute("uploadUserName", photo.getUploadUserName());
        	request.setAttribute("uploadDate", df.format(loanDatas.get(0).getUploadDate()));
//        request.setAttribute("uploadDate", df.format(photo.getUpdateDate()));
        		request.setAttribute("recordDate", df.format(loanDatas.get(0).getRecordDate()));
        	request.setAttribute("photoId", loanDatas.get(0).getDataId());
//        request.setAttribute("photoId", photo.getPhotoId());
        	request.setAttribute("remark", loanDatas.get(0).getRemark());
//        request.setAttribute("remark", photo.getRemark());
        request.setAttribute("customerId", customerId);
            request.setAttribute("isAll",request.getParameter("isAll"));
        logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 查询某客户的照片资料完毕");
        return SUCCESS;
    } catch (Exception e) {
        e.printStackTrace();
        log.error("AllLoanAction queryPhotoDetail error:" + e.getMessage());
        return ERROR;
    }
    }


    private SysUploadFileService sysUploadFileService;

    public SysUploadFileService getSysUploadFileService() {
        return sysUploadFileService;
    }

    public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
        this.sysUploadFileService = sysUploadFileService;
    }


    /**
     * 贷后管理照片详情层
     * @return
     */
    public String queryAfterImageDetail(){
        try {
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 开始查询某客户的照片资料...");
            String systemPath = this.getRootPath()+File.separator+"Records";
            String fileId = request.getParameter("fileId");
            createAndClearDir(systemPath);

            String copyFileName = customerDataService.readFile(fileId, systemPath);


            SysUploadFile sysUploadFile = new SysUploadFile();
            sysUploadFile = sysUploadFileService.getSysUploadFileById(Integer.parseInt(fileId));
            Date index =null;
            String index2 =null;
            String index3 =null;

            index2=sysUserService.getSysUserById(sysUploadFile.getUploadUserId()).getUserName();
            index3=sysUploadFile.getUploadFileName();
            index =sysUploadFile.getCreateDate();
            String timePath = new SimpleDateFormat("yyyy-MM-dd").format(index);



            request.setAttribute("file", copyFileName.substring(copyFileName.lastIndexOf(File.separator)+1,copyFileName.length()));
            request.setAttribute("uploadDate",  timePath);
            request.setAttribute("uploadUserName",  index2);
            request.setAttribute("customerName",  index3);

            return SUCCESS;
        } catch (Exception e) {
            log.error("AllLoanAction queryPhotoDetail error:" + e.getMessage());
            return ERROR;
        }
    }
    
    /**
     * 切换照片
     * @return
     */
    public void changeLoanPhoto(){
        logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 切换照片资料...");
    	HttpServletResponse response = ServletActionContext.getResponse();
    	response.setContentType("text/html;charset=utf-8");
        try {
         	PrintWriter out = response.getWriter();
         	String photoTypeId = request.getParameter("photoTypeId");
         	String photoLoanId = request.getParameter("photoLoanId");
			String photoEventId = request.getParameter("photoEventId");
			String systemPath = this.getRootPath()+File.separator+"Records";
			String photoRowNum = request.getParameter("photoRowNum");
			String customerId = request.getParameter("customerId");
			String Num = request.getParameter("Num");
        	Integer photoId = Integer.valueOf(request.getParameter("photoId"));
        	Integer flag = Integer.valueOf(request.getParameter("flag"));
            String isAll = request.getParameter("isAll");
            Photo p = dataPhotoService.getPhotoByPhotoId(photoId);

			int i;
			if(Num != null && !Num.equals("")){
				int	num =0;
			if(StringUtils.isNotBlank(photoTypeId)){
				num =lnLoanDetailService.getLoanDetailPhotoDataByEventCount(Integer.parseInt(photoLoanId), Integer.parseInt(photoEventId),Integer.parseInt(customerId),Integer.parseInt(photoTypeId));
        	
        	}else{
        		num =lnLoanDetailService.getLoanDetailPhotoDataByEventCount(Integer.parseInt(photoLoanId), Integer.parseInt(photoEventId),Integer.parseInt(customerId));
            
        	}
			
			 int t=Integer.parseInt(photoTypeId)-1;
			 photoTypeId =String.valueOf(t);
			 int num2=0;
				if(StringUtils.isNotBlank(photoTypeId)){
					num2 = lnLoanDetailService.getLoanDetailPhotoDataByEventCount(Integer.parseInt(photoLoanId), Integer.parseInt(photoEventId),Integer.parseInt(customerId),Integer.parseInt(photoTypeId));
	        	}else{
	        		num2 =lnLoanDetailService.getLoanDetailPhotoDataByEventCount(Integer.parseInt(photoLoanId), Integer.parseInt(photoEventId),Integer.parseInt(customerId));	         
	        	}
				
				if(StringUtils.isNotBlank(photoTypeId)){
	        		loanDatas = lnLoanDetailService.getLoanDetailPhotoDataByEvent(Integer.parseInt(photoLoanId), Integer.parseInt(photoEventId),Integer.parseInt(customerId),Integer.parseInt(photoTypeId));
	        	}else{
	        		loanDatas = lnLoanDetailService.getLoanDetailPhotoDataByEvent(Integer.parseInt(photoLoanId), Integer.parseInt(photoEventId),Integer.parseInt(customerId));
	        	}
			for(i = num2 ; i < num ; i ++){
					if(loanDatas.get(i).getRowNum().compareTo(Integer.parseInt(photoRowNum)) == 0)break;
				}	
			}else{
				if(StringUtils.isNotBlank(photoTypeId)){
	        		loanDatas = lnLoanDetailService.getLoanDetailPhotoDataByEvent(Integer.parseInt(photoLoanId), Integer.parseInt(photoEventId),Integer.parseInt(customerId),Integer.parseInt(photoTypeId));
	        	}else{
	            loanDatas = lnLoanDetailService.getLoanDetailPhotoDataByEvent(Integer.parseInt(photoLoanId), Integer.parseInt(photoEventId),Integer.parseInt(customerId));
	        	}
				
//			for(i = 0 ; i < loanDatas.size() ; i ++){
//				if(loanDatas.get(i).getRowNum().compareTo(Integer.parseInt(photoRowNum)) == 0)break;
//			}
			}
            Map<String, Integer> map = new HashMap<String, Integer>();
            if(StringUtil.isBlank(isAll)){
                map.put("typeId", p.getPhotoTypeId());
            }
            map.put("customerId", p.getCustomerDataId());

            List<Photo> photoList = dataPhotoService.getPhotoByTypeId(map);
            int index = 0;
            for(int k= 0;k<photoList.size();k++){
                if(p.getPhotoId().equals(photoList.get(k).getPhotoId())){
                    index = k;
                    break;
                }
            }
                index = index + flag;
            if(index==photoList.size()){
                index = index - flag;
            }
            if(index<0){
                index = index - flag;
            }
//			JSONObject jobj = JSONObject.fromObject(loanDatas.get(i));
            JSONObject jobj = JSONObject.fromObject(photoList.get(index));
//			String copyFileName = customerDataService.readFile(loanDatas.get(i).getFileId()+"", systemPath);
            String copyFileName = customerDataService.readFile(photoList.get(index).getFileId()+"", systemPath);
			jobj.put("file", copyFileName.substring(copyFileName.lastIndexOf(File.separator)+1,copyFileName.length()));

//			jobj.put("photoName", loanDatas.get(i).getDataName());
            jobj.put("photoName", photoList.get(index).getPhotoName());
//        	jobj.put("uploadUserName", loanDatas.get(i).getUploadUserName());
            jobj.put("uploadUserName", photoList.get(index).getUploadUserName());
//        	jobj.put("uploadDate", df.format(loanDatas.get(i).getUploadDate()));
//            jobj.put("uploadDate", df.format(photoList.get(index).getUploadDate()));
//        	if(loanDatas.get(i).getRecordDate()!=null){
//              	jobj.put("recordDate", df.format(loanDatas.get(i).getRecordDate()));
//            }else{
//              	jobj.put("recordDate","");
//            }
            if(photoList.get(index).getRecordDate()!=null){
                jobj.put("recordDate", df.format(photoList.get(index).getRecordDate()));
            }else{
                jobj.put("recordDate","");
            }

        	jobj.put("photoId", photoList.get(index).getPhotoId());
        	jobj.put("remark", photoList.get(index).getRemark());


            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 切换到照片："+photoList.get(index).getPhotoName());
			out.print(jobj.toString());
            out.flush();
			out.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AllLoanAction changeLoanPhoto error:" + e.getMessage());
        }
    }

    /**
     * 贷款移交页面
     */
    public String moveLoan(){
        try {
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 进入移交贷款页面...");

            String loanId = request.getParameter("loanId");
            if(loanId != null && !loanId.trim().equals("")){
                LnLoan lnLoan = lnLoanService.selectLoanById(Integer.parseInt(loanId));
                request.setAttribute("applyUserId", lnLoan.getApplyUserId());
            }


            String loans = request.getParameter("loans");
            String belongDeptId = this.getLoginInfo().getDeptId().toString();
            String belongUserId = this.getLoginInfo().getUserId().toString();
            String belongUserName = this.getLoginInfo().getUserName();

            //贷款id和客户id
            String[] loanIdAndCusIdArray = loans.split(",");
            if(loans != null && !loans.equals("")){
                String applyUserId = "";
                int i =0;
                for (String loanIdCursId : loanIdAndCusIdArray) {
                    LnLoan lnLoan = lnLoanService.selectLoanById(Integer.parseInt(loanIdCursId.split("@")[0]));
                    if(i == 0){
                        applyUserId += lnLoan.getApplyUserId();
                        i = 1;
                    }else{
                        applyUserId += "," + lnLoan.getApplyUserId();
                    }

                }
                request.setAttribute("applyUserId", applyUserId);
            }


            List<SysDept> list = deptFacadeService.getBusinessManagerInCharegeDeptTreeList();

            JSONArray deptArray = this.toTreeJson(list, "insert", "");

            if(loanIdAndCusIdArray != null){
                request.setAttribute("loanCount",loanIdAndCusIdArray.length);
            }
            request.setAttribute("belongDeptId", belongDeptId);
            request.setAttribute("belongUserId", belongUserId);
            request.setAttribute("belongUserName", belongUserName);
            request.setAttribute("belongUserAccount",this.getLoginInfo().getAccount());
            request.setAttribute("deptArray", deptArray);

            return SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("AllotingLoanAction % allotLoanPage",e);
            return ERROR;
        }

    }

    //查询用户
    public String moveSearchUser() {
        try {
            String[] roles = this.getLoginInfo().getRoles();
            logger.info("查询开始"+this.getLoginInfo().getUserId()+"角色"+roles[0]);
            if("1".equals(roles[0])){
                Page page  = new Page();
                Map<String ,Object> map = new HashMap<String, Object>();
                map.put("isActived",1);
                map.put("roleId",7);
                PageUtil<SysUserBean> sysUserList = sysUserService.getSysUserPage(map,page);
                request.setAttribute("userList", sysUserList.getItems());
            }else  if ("5".equals(roles[0])) {
                    List<Integer> list = sysUserService.getReadomTeamManager(this.getLoginInfo().getUserId());
                    List<SysUser> sysUserList = new ArrayList<SysUser>();
                    for (Integer i = 0; i < list.size(); i++) {
                        SysUser sysUser = sysUserService.getSysUserById(list.get(i));
                        sysUserList.add(sysUser);
                    }
                    request.setAttribute("userList", sysUserList);
            }


            log.info("查询用户结束");
            return SUCCESS;
        } catch (Exception e) {
            return ERROR;
        }
    }


    /**
     * 根据account查询用户
     * @return
     */
    public  String searchUserByAccount(){
        try{
            String account = request.getParameter("account");
            logger.info("用户为"+account);
            String[] roles = this.getLoginInfo().getRoles();
            Map<String,Object> map = new HashMap<String, Object>();
            //若不输入任何参数这刷新列表
            if(account.equals("请输入用户名或姓名")){

                logger.info("查询开始"+this.getLoginInfo().getUserId()+"角色"+roles[0]);
                if("1".equals(roles[0])){
                    Page page  = new Page();
                    map.put("isActived",1);
                    map.put("roleId",7);
                    PageUtil<SysUserBean> sysUserList = sysUserService.getSysUserPage(map,page);
                    request.setAttribute("userList", sysUserList.getItems());
                }else  if ("5".equals(roles[0])) {
                    List<Integer> list = sysUserService.getReadomTeamManager(this.getLoginInfo().getUserId());
                    List<SysUser> sysUserList = new ArrayList<SysUser>();
                    for (Integer i = 0; i < list.size(); i++) {
                        SysUser sysUser = sysUserService.getSysUserById(list.get(i));
                        sysUserList.add(sysUser);
                    }
                    request.setAttribute("userList", sysUserList);
                }
            }else{
                //判断是管理员还是主管 以取对应的用户
                if("1".equals(roles[0])){
                    map.put("roleId",7);
                }else if("5".equals(roles[0])){
                    map.put("roleId",6);
                    map.put("userId",this.getLoginInfo().getUserId());
                }
                //判断account是数字还是其他字符
                if(account!=null&&account.matches("^[0-9]+$")){
                    map.put("account",account);
                }else{
                    map.put("userName",account);
                }
                List<SysUser> sysUser = sysUserService.getAllUserByAccountMap(map);
                logger.info("客户为："+sysUser);
                request.setAttribute("userList",sysUser);
            }

                return SUCCESS;
        }catch (Exception e){
            return ERROR;
        }
    }

    /**
     * 移交贷款
     */
    public void moveLoanDetil(){
        try {
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 贷款移交...");
            String loans = request.getParameter("loans");
            String belongDeptId = request.getParameter("belongDeptId");
            String belongUserId = request.getParameter("belongUserId");
            String belongUserName = request.getParameter("belongUserName");
            String assignRemark = request.getParameter("assignRemark");
            List<Map<String,Integer>> idList = this.stringToMapList(loans);
            String[] roles = this.getLoginInfo().getRoles();
            if(idList != null && idList.size() > 0){
                    Map<String,Integer> idMap = idList.get(0);
                    Integer loanId = idMap.get("loanId");
                    Integer cusId = idMap.get("cusId");
                    LnLoan lnLoan  = lnLoanService.getLnLoanById(loanId);
                    Integer loanStatusId = lnLoan.getLoanStatusId();
                    logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 贷款id为"+loanId+",贷款客户id为"+cusId+",移交给用户”"+belongUserName+"“");
                    Map<String,Object> loanParamMap = new HashMap<String, Object>();
                    loanParamMap.put("loanId",loanId);

                    logger.info("贷款状态为："+loanStatusId);
                    LnOpHistory lnOpHistory = new LnOpHistory();
                if(roles[0].equals("1")){
                    SysUser sysUser = sysUserService.getSysUserById(lnLoan.getSurveyUserId());
                    SysUser sysUser1 = sysUserService.getSysUserById(Integer.parseInt(belongUserId));
                    String username =sysUser.getUserName();
                    loanParamMap.put("surveyUserId", Integer.parseInt(belongUserId));

                    loanParamMap.put("assignUserId", Integer.parseInt(belongUserId));
                    lnOpHistory.setContent(username+"("+sysUser.getAccount()+")" + "贷款移交给" + belongUserName + "("+sysUser1.getAccount()+")");
                }else if(roles[0].equals("5")){
                     SysUser sysUser = sysUserService.getSysUserById(lnLoan.getLendUserId());
                     SysUser sysUser1 = sysUserService.getSysUserById(Integer.parseInt(belongUserId));
                     String  username =sysUser.getUserName();
                     loanParamMap.put("lendUserId",Integer.parseInt(belongUserId));
                    lnOpHistory.setContent(username+"("+sysUser.getAccount()+")" + "贷款调整给" + belongUserName + "("+sysUser1.getAccount()+")");
                }


                    //更改客户表，将该贷款所关联的客户归属到被移交者
                    CrmCustomer crmCustomer = new CrmCustomer();
                    crmCustomer.setBelongUserId(Integer.parseInt(belongUserId));
                    crmCustomer.setBelongDeptId(Integer.parseInt(belongDeptId));
                    crmCustomer.setCustomerId(cusId);

                    //插入贷款历史操作记录

                    lnOpHistory.setUserId(this.getLoginInfo().getUserId());
                    lnOpHistory.setOpHistoryDate(new Date());

                    lnOpHistory.setRemark(assignRemark);   //历史记录备注
                    lnOpHistory.setLoanId(loanId);
                    logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 贷款开始移交...");
                    //移交
                    lnLoanService.allotLoanSingle(loanParamMap,crmCustomer,lnOpHistory);
                    logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 贷款移交完成");


            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("AllLoanAction % moveLoanDetil",e);
        }

    }

    /**
     * 拆分loans为元素为Map的List
     * @param loans
     * @return
     */
    private List<Map<String,Integer>> stringToMapList(String loans){
        List<Map<String,Integer>> idList = new ArrayList<Map<String, Integer>>();
        //贷款id和客户id
        if(StringUtils.isNotEmpty(loans)){
            String[] loanIdAndCusIdArray = loans.split(",");
            for (String cusStr : loanIdAndCusIdArray){
                String[] ids = cusStr.split("@");
                Integer loanId = Integer.parseInt(ids[0]);
                Integer cusId = Integer.parseInt(ids[1]);

                Map<String,Integer> loanIdCusId = new HashMap<String, Integer>();
                loanIdCusId.put("loanId",loanId);
                loanIdCusId.put("cusId",cusId);

                idList.add(loanIdCusId);
            }
        }
        return idList;
    }

    private JSONArray toTreeJson( List<SysDept> depts, String flag, String belongDeptId){
        try {
            Set<Integer> deptSet = new HashSet<Integer>();
            int ii = 0;
            for (SysDept sysDept : depts) {
                deptSet.add(sysDept.getDeptId());
            }
            for(SysDept sysDept : depts){
                if(!deptSet.contains(sysDept.getDeptParentId())){
                    ii++;
                }
            }

            JSONArray jsonArray = new JSONArray();
            if(depts.size()>0){
                for (SysDept dept : depts) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("id", dept.getDeptId());
                    if(!deptSet.contains(dept.getDeptParentId())){
                        map.put("pId", 0);
                        if(ii==1){
                            map.put("open", true);
                        }
                    }else{
                        map.put("pId", dept.getDeptParentId());
                    }
                    map.put("name", dept.getDeptName());
                    if(flag.equals("modify") && dept.getDeptId().toString().equals(belongDeptId)){
                        map.put("selected", true);
                    }
//                    map.put("icon", "../images/deptTreeImage/1.png");
                    jsonArray.add(map);
                }
            }
            return jsonArray;
        } catch (Exception e) {
            //add by zhangxiang 2012-08-09
            log.error("CustomerBelongToAction toTreeJson action error:"+e.getMessage());
            return null;
        }
    }

    /**
     * 贷款回退
     */
    public void backLoan(){
    	//TODO:修改
    	
        try {
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 贷款回退...");
             //* 一、将贷款状态修改为前一个状态
             //* 二、记录回退历史记录
            String loanIdStr = request.getParameter("loanId");
            String backRemark = request.getParameter("backRemark");
            String curLoanStatus = request.getParameter("curLoanStatus");
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 贷款id："+loanIdStr+",回退备注："+backRemark+",当前贷款状态："+curLoanStatus);
            if (StringUtils.isNotEmpty(loanIdStr) && StringUtils.isNotEmpty(curLoanStatus)){
                LnLoan lnLoan = lnLoanService.getLnLoanById(Integer.parseInt(loanIdStr));
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("loanId",Integer.parseInt(loanIdStr));

//                Integer curLoanStatusId = Integer.parseInt(curLoanStatus);
                Integer curLoanStatusId = null;
                if (lnLoan != null){
                    curLoanStatusId = lnLoan.getLoanStatusId();
                }

                if (curLoanStatusId != null){
                    LnOpHistory lnOpHistory = new LnOpHistory();
                    lnOpHistory.setUserId(this.getLoginInfo().getUserId());
                    lnOpHistory.setOpHistoryDate(new Date());
                    lnOpHistory.setBeforeStatusId(curLoanStatusId);  //拒绝前状态
                    //调用接口往信贷发送
                    Map<String,Object> map=new HashMap<String, Object>();
                    map.put("loanId",lnLoan.getLoanId());
                    //LnLoanInfo loanInfo=lnLoanInfoService.selectLoanInfoList(map).get(0);
                    //String djSerno=loanInfo.getSerialNumber();
                    if (curLoanStatusId.equals(LoanConstants.LOAN_ASSIGN_STATUS)){
                        //贷款分配-->贷款申请
                        lnOpHistory.setAfterStatusId(LoanConstants.LOAN_ASK_STATUS);
                        paramMap.put("loanStatusId",LoanConstants.LOAN_ASK_STATUS);
                    }else if (curLoanStatusId.equals(LoanConstants.LOAN_EXAM_STATUS)) {
                        //贷款调查-->贷款分配
                        lnOpHistory.setAfterStatusId(LoanConstants.LOAN_ASSIGN_STATUS);
                        paramMap.put("loanStatusId",LoanConstants.LOAN_ASSIGN_STATUS);

                    }else if (curLoanStatusId.equals(LoanConstants.LOAN_APPROVAL_STATUS)){
                        //贷款审批-->贷款调查
                        lnOpHistory.setAfterStatusId(LoanConstants.LOAN_EXAM_STATUS);
                        paramMap.put("loanStatusId",LoanConstants.LOAN_EXAM_STATUS);

                    }else if (curLoanStatusId.equals(LoanConstants.LOAN_LENDING_STATUS)){
                        //贷款放贷-->贷款审批
                        lnOpHistory.setAfterStatusId(LoanConstants.LOAN_APPROVAL_STATUS);
                        paramMap.put("loanStatusId",LoanConstants.LOAN_APPROVAL_STATUS);
                    }else if (curLoanStatusId.equals(LoanConstants.LOAN_ASK_REFUSE_STATUS)){
                        //申请拒绝-->贷款申请
                        lnOpHistory.setAfterStatusId(LoanConstants.LOAN_ASK_STATUS);
                        paramMap.put("loanStatusId",LoanConstants.LOAN_ASK_STATUS);
                        //调用接口往信贷发送
                        //sendMessageService.refuseLoan(djSerno,lnLoan,"1");
                    }else if (curLoanStatusId.equals(LoanConstants.LOAN_ASSIGN_REFUSE_STATUS)){
                        //分配拒绝-->贷款分配
                        lnOpHistory.setAfterStatusId(LoanConstants.LOAN_ASSIGN_STATUS);
                        paramMap.put("loanStatusId",LoanConstants.LOAN_ASSIGN_STATUS);
                        //调用接口往信贷发送
                        //sendMessageService.refuseLoan(djSerno,lnLoan,"2");
                    }else if (curLoanStatusId.equals(LoanConstants.LOAN_EXAM_REFUSE_STATUS)){
                        //调查拒绝-->贷款调查
                        lnOpHistory.setAfterStatusId(LoanConstants.LOAN_EXAM_STATUS);
                        paramMap.put("loanStatusId",LoanConstants.LOAN_EXAM_STATUS);
                        //调用接口往信贷发送
                        //sendMessageService.refuseLoan(djSerno,lnLoan,"3");
                    }else if (curLoanStatusId.equals(LoanConstants.LOAN_APPROVAL_REFUSE_STATUS)){
                        //审批拒绝-->贷款审批
                        lnOpHistory.setAfterStatusId(LoanConstants.LOAN_APPROVAL_STATUS);
                        paramMap.put("loanStatusId",LoanConstants.LOAN_APPROVAL_STATUS);
                    }
                    lnOpHistory.setContent("贷款状态回退");
                    lnOpHistory.setLoanId(Integer.parseInt(loanIdStr));
                    lnOpHistory.setRemark(backRemark);
                    logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 开始回退，并插入历史记录");
                    lnLoanService.backLoan(paramMap,lnOpHistory);
                    logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 贷款回退完毕");
                }
            }
        }catch (Exception e){
            log.error("AllLoanAction % backLoan",e);
        }
    }

    /**
     * 检查是否有资料可导出
     */
    public void hasLoanDatas(){
        logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- AllLoanAction.hasLoanDatas开始");
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String loanIdStr = request.getParameter("loanId");
            Integer loanId = Integer.parseInt(loanIdStr);
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("loanId",loanId);
            Integer count = customerDataService.getFileIdCountByLoan(paramMap);
            if (count > 0){
                out.write("1");
            }else {
                out.write("0");
            }
            out.flush();
            out.close();
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- AllLoanAction.hasLoanDatas完成");
        }catch (Exception e){
            logger.error("AllLoanAction % hasLoanDatas",e);
        }finally {
            if (out != null)
                out.close();
        }
    }

    /**
     * 导出贷款资料
     */
    public void exportLoanData(){
        logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- AllLoanAction.exportLoanData开始");
        // 往ie输出文件流
        try {
            String loanIdStr = request.getParameter("loanId");
            Integer loanId = Integer.parseInt(loanIdStr);
            String curDownFilePath = sysParamService.getRecordPath()+File.separator+"LoanExportData"+File.separator+loanId+".tar.gz";
            File downFile = new File(curDownFilePath);
            if (downFile == null || !downFile.exists()){
                Map<String,Object> paramMap = new HashMap<String, Object>();
                paramMap.put("loanId",loanId);
                synchronized (this){
                    downFile = lnLoanService.getLoanExportDataFile(paramMap,loanId);
                }
            }
            if (downFile != null && downFile.exists()){
                synchronized (this){
                    FileInputStream fis = new FileInputStream(downFile);// 服务器文件路径
                    ServletOutputStream output = this.getResponse().getOutputStream();
                    this.getResponse().addHeader("Content-Disposition", "attachment;filename="
                            + new String(downFile.getName().getBytes("gbk"),
                            "iso8859-1"));
//                    getResponse().addHeader("Content-Length", "" + downFile.length());

//                    getResponse().setContentType("application/octet-stream"); // 设置返回的文件类型
                    BufferedInputStream bis = new BufferedInputStream(fis);// 输入缓冲流
                    BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流

                    byte buff[] = new byte[4096];// 缓冲字节数

                    int bytesRead;
                    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                        bos.write(buff, 0, bytesRead);
                    }
                    if (bis != null){
                        bis.close();
                    }
                    if (bos != null){
                        bos.flush();// 清空输出缓冲流
                        bos.close();
                    }
                    if (output != null){
                        output.close();
                    }
                    FileUtil.deleteFile(downFile.getAbsolutePath());
                }
            }
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- AllLoanAction.exportLoanData完成");
        } catch (Exception e) {
            logger.error("AllLoanAction % exportLoanData",e);
        }
    }

    /**
     *导出贷款资料（照片、视频、附件、录音、视频）
     */
    public void downLoanData() throws Exception{
        Integer loanId = Integer.valueOf(this.request.getParameter("loanId"));
        String zipName = lnLoanInfoService.getPanLoanInfoById(loanId).getCusName();
        String fileRootPath = getRootPath();
        File downFile = new File(fileRootPath+"\\"+zipName+"_贷款资料.zip");
        if(!downFile.exists()){
            downFile.createNewFile();
        }
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(downFile));
        Map<String,Object> condition = new HashMap<String,Object>();
        condition.put("loanId",loanId);
        //图片
//        condition.put("dataType","photo");
//        List<String> dataList = customerDataService.getFileByDatType(condition);
//        if(dataList.size()>0&&!dataList.isEmpty()){
//            for(String fileName:dataList){
//                File file = new File(fileName);
//                if(file.exists()){
//                    downLoadFilesUtil(file,zipName+"_贷款资料/图片/",out);
//                }
//            }
//        }
        downFileByDataType(condition,"photo",zipName,out);
        downFileByDataType(condition,"video",zipName,out);
        downFileByDataType(condition,"audio",zipName,out);
        downFileByDataType(condition,"form",zipName,out);
        out.close();
        this.downFile(this.getResponse(), fileRootPath+"\\"+zipName+"_贷款资料.zip");
        if(downFile.exists()){
            downFile.delete();
        }



    }
    private void downFile(HttpServletResponse response, String str) {
        try {
            String path = str;
            String zipName =str.substring(str.lastIndexOf("\\")+1);
            File file = new File(path);
            if (file.exists()) {
                InputStream ins = new FileInputStream(path);
                BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面
                OutputStream outs = response.getOutputStream();// 获取文件输出IO流
                BufferedOutputStream bouts = new BufferedOutputStream(outs);
                response.setContentType("application/x-download");// 设置response内容的类型
                response.setHeader(
                        "Content-disposition",
                        "attachment;filename="
                                + URLEncoder.encode(zipName, "UTF-8"));// 设置头部信息
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                //开始向网络传输文件流
                while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
                    bouts.write(buffer, 0, bytesRead);
                    bouts.flush();// 这里一定要调用flush()方法
                }

                ins.close();
                bins.close();
                outs.close();
                bouts.close();
            }
        } catch (IOException e) {
            log.error("文件下载出错", e);
        }
    }

    private void downFileByDataType(Map condition,String dataType,String zipName,ZipOutputStream out) throws Exception{
        if("photo".equals(dataType)){
            condition.put("dataType","photo");
            zipName = zipName+"_贷款资料/图片/";
        }else if("video".equals(dataType)){
            condition.put("dataType","video");
            zipName = zipName+"_贷款资料/视频/";
        }else if("audio".equals(dataType)){
            condition.put("dataType","audio");
            zipName = zipName+"_贷款资料/录音/";
        }else{
            condition.put("dataType","form");
            zipName = zipName+"_贷款资料/附件/";
        }
        List<String> dataList = customerDataService.getFileByDatType(condition);
        if(dataList.size()>0&&!dataList.isEmpty()){
            for(String fileName:dataList){
                File file = new File(fileName);
                if(file.exists()){
                    downLoadFilesUtil(file,zipName,out);
                }
            }
        }
    }

    private void downLoadFilesUtil(File file,String dirPath ,ZipOutputStream out)throws IOException{
        try {
            FileInputStream fis = new FileInputStream(file);
            String dir=dirPath+'/'+file.getName();
            out.putNextEntry(new ZipEntry(dir));
            //设置压缩文件内的字符编码，不然会变成乱码
            out.setEncoding("GBK");
            int len;
            byte[] buffer = new byte[1024];
            // 读入需要下载的文件的内容，打包到zip文件
            while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.closeEntry();
            fis.close();

        } catch (Exception e) {
            log.error("文件下载出错", e);
        }
    }



    /**
     * 下载Excel模板
     *  申请表
     * @return
     */
    public void downRapplyExcel() {
        try {
            //贷款id
            String loanId =  request.getParameter("loanId");

            String timePath = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
            String path = FileUtil.contact(this.getSession().getServletContext().getRealPath("/"), "temp_file/export/贷款申请表" + timePath + ".xls");
            String dir = path.substring(0, path.lastIndexOf("/")); // 取出目录
            File file = new File(dir);
            // 检查文件是否存在
            File obj = new File(path);
            if (!file.exists()) { // 目录不存在，则创建相应的目录
                file.mkdirs();
                if (!obj.exists()) // 接下来创建具体文件
                    obj.createNewFile(); // 就是在这个点抛出异常
            }
            getResolutionTableExportFile(loanId , path);
            // 写流文件到前端浏览器
            ServletOutputStream out = this.getResponse().getOutputStream();
            this.getResponse().setHeader("Content-Disposition",
                    "attachment; filename=" + java.net.URLEncoder.encode("贷款申请表.xls", "UTF-8"));
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            bis = new BufferedInputStream(new FileInputStream(path));
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
            obj.delete();
        } catch (Exception e) {
            log.error("", e);
        }

    }
    public void getResolutionTableExportFile(String loanId ,String path){
        try{
            //取得贷款信息
            Map<String,Object> param = new HashMap<String, Object>();
            param.put("loanId", loanId);
            LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);

            LnLoan lnLoan = lnLoanService.getLnLoanById(Integer.parseInt(loanId));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

            String rtefPath = request.getSession().getServletContext().getResource("/ini").getFile();
            if (!FileUtil.isExistFilePath(rtefPath)) {
                rtefPath = ServerRealPathUtil.getServerRealPath() + File.separator + "ini";
            }
            if (lnLoanInfo.getAppLoanTypeId().equals("1")){
                rtefPath = rtefPath + File.separator + "jydsqb.xls";
            }
            if (lnLoanInfo.getAppLoanTypeId().equals("2")) {
                rtefPath = rtefPath + File.separator + "xfdsqb.xls";
            }
            //取Excel模板
            POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(rtefPath));
            HSSFWorkbook book = new HSSFWorkbook(fs);

            //填充第一个工作表
            HSSFSheet sheet = book.getSheetAt(0);


            //填充第三行数据
            HSSFRow row2 = sheet.getRow(2);
            row2.getCell(1).setCellValue(lnLoanInfo.getRegisterLoanDate());//需要贷款日期


            //填充第四行数据
            HSSFRow row3 = sheet.getRow(3);
            row3.getCell(1).setCellValue(lnLoanInfo.getRegisterRecommendBank());
            row3.getCell(9).setCellValue(lnLoanInfo.getLoanId());//客户号



            //填充第五行数据//日期。贷款号
            HSSFRow row4 = sheet.getRow(4);
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            String date =format.format(new Date());
            row4.getCell(1).setCellValue(date);

            /*个人信息*/



            if (lnLoanInfo.getAppLoanTypeId().equals("1")){
                HSSFRow row6 = sheet.getRow(6);
                row6.getCell(1).setCellValue(lnLoanInfo.getCusName());
                row6.getCell(9).setCellValue(lnLoanInfo.getCusBirthday());
                if (lnLoanInfo.getCusSex().equals("男")){
                    row6.getCell(18).setCellValue("男 √  女 □ ");
                }
                if (lnLoanInfo.getCusSex().equals("女")) {
                    row6.getCell(18).setCellValue("男 □  女 √ ");
                }

                HSSFRow row7 = sheet.getRow(7);
                if(lnLoanInfo.getCusIdtypeId().equals("1")){
                    row7.getCell(1).setCellValue("身份证 √ 户口本 □");
                }
                if(lnLoanInfo.getCusIdtypeId().equals("2")){
                    row7.getCell(1).setCellValue("身份证 □ 户口本 √");
                }
                row7.getCell(18).setCellValue(lnLoanInfo.getCusIdcard());

                HSSFRow row8 = sheet.getRow(8);
                if(lnLoanInfo.getCusMarriage().equals("01")){
                    row8.getCell(1).setCellValue("单身 √  已婚 □ 离异 □ ");
                }if(lnLoanInfo.getCusMarriage().equals("02")){
                    row8.getCell(1).setCellValue("单身 □  已婚 √ 离异 □ ");
                }if(lnLoanInfo.getCusMarriage().equals("04")){
                    row8.getCell(1).setCellValue("单身 □  已婚 □ 离异 √ ");
                }
                row8.getCell(18).setCellValue(lnLoanInfo.getCusLocalYear());

                HSSFRow row10 = sheet.getRow(10);
                row10.getCell(1).setCellValue(lnLoanInfo.getCusMateName());
                row10.getCell(9).setCellValue(lnLoanInfo.getCusMateIdcard());
                row10.getCell(17).setCellValue(lnLoanInfo.getCusMateJob());

                HSSFRow row11 = sheet.getRow(11);
                row11.getCell(1).setCellValue(lnLoanInfo.getCusAddress());//家庭住址
                row11.getCell(11).setCellValue(lnLoanInfo.getCusFamilyNum());//家庭人数

                HSSFRow row12 =sheet.getRow(12);
                row12.getCell(1).setCellValue(lnLoanInfo.getCusPhone());//电话
                row12.getCell(11).setCellValue(lnLoanInfo.getCusMobilePhone());//手机号

                HSSFRow row13 =sheet.getRow(13);
                if (lnLoanInfo.getCusLivingStatusId().equals("01")){
                    row13.getCell(1).setCellValue(" 自有住房 √   抵押 □     租住 □     亲属住房 □      其它 □:_______ ");
                }
                if (lnLoanInfo.getCusLivingStatusId().equals("02")){
                    row13.getCell(1).setCellValue(" 自有住房 □   抵押 √     租住 □     亲属住房 □      其它 □:_______ ");
                }
                if (lnLoanInfo.getCusLivingStatusId().equals("06")){
                    row13.getCell(1).setCellValue(" 自有住房 □   抵押 □     租住 √     亲属住房 □      其它 □:_______ ");
                }
                if (lnLoanInfo.getCusLivingStatusId().equals("03")){
                    row13.getCell(1).setCellValue(" 自有住房 □   抵押 □     租住 □     亲属住房 √      其它 □:_______ ");
                }


                HSSFRow row14 =sheet.getRow(14);
                if (lnLoanInfo.getCusEducationId().equals("05")){
                    row14.getCell(1).setCellValue(" 大学 √    大专 □   技校 □    中专  □   高中 □  初中 □   小学 □   其它□    ");
                }
                if (lnLoanInfo.getCusEducationId().equals("04")){
                    row14.getCell(1).setCellValue(" 大学 □    大专 √   技校 □    中专  □   高中 □  初中 □   小学 □   其它□    ");
                }
                if (lnLoanInfo.getCusEducationId().equals("03")){
                    row14.getCell(1).setCellValue(" 大学 □    大专 □   技校 □    中专  □   高中 √  初中 □   小学 □   其它□    ");
                }
                if (lnLoanInfo.getCusEducationId().equals("02")){
                    row14.getCell(1).setCellValue(" 大学 □    大专 □   技校 □    中专  □   高中 □  初中 √   小学 □   其它□    ");
                }
                if (lnLoanInfo.getCusEducationId().equals("01")){
                    row14.getCell(1).setCellValue(" 大学 □    大专 □   技校 □    中专  □   高中 □  初中 □   小学 √   其它□    ");
                }
                HSSFRow row15 =sheet.getRow(15);
                row15.getCell(1).setCellValue(lnLoanInfo.getBizCompany());
                row15.getCell(14).setCellValue(lnLoanInfo.getCusAddress());

                HSSFRow row16 =sheet.getRow(16);
                row16.getCell(1).setCellValue(lnLoanInfo.getCompanyJob());

            /*生意信息*/

                HSSFRow row18 =sheet.getRow(18);
                row18.getCell(1).setCellValue(lnLoanInfo.getBizCompany());
                row18.getCell(12).setCellValue(lnLoanInfo.getBizScope());

                HSSFRow row19 =sheet.getRow(19);
                row19.getCell(1).setCellValue(lnLoanInfo.getBizAddress());
                row19.getCell(12).setCellValue(lnLoanInfo.getBizOpenDate());

                HSSFRow row20 =sheet.getRow(20);

               if(StringUtils.isNotBlank(lnLoanInfo.getBizOrgId())){
                   if(lnLoanInfo.getBizOrgId().equals("01")){
                       row20.getCell(1).setCellValue("个体户 √ 法律实体 □");
                   }
                   if(lnLoanInfo.getBizOrgId().equals("02")){
                       row20.getCell(1).setCellValue("个体户 □ 法律实体 √");
                   }
               }
                row20.getCell(17).setCellValue(lnLoanInfo.getBizOrgCode());


                HSSFRow row21 =sheet.getRow(21);
                row21.getCell(1).setCellValue(lnLoanInfo.getBizEmployeeNum());
                //  row21.getCell(8).setCellValue(lnLoanInfo.getBizCompany());

                HSSFRow row22 =sheet.getRow(22);
                if(StringUtils.isNotBlank(lnLoanInfo.getBizPlaceId())){
                    if(lnLoanInfo.getBizPlaceId().equals("01")){
                        row22.getCell(1).setCellValue(" 自有 √ 租赁 □ 其它 □________");
                    }
                    if(lnLoanInfo.getBizPlaceId().equals("02")){
                        row22.getCell(1).setCellValue(" 自有 □ 租赁 √ 其它 □________");
                    }
                }
                else{
                    row22.getCell(1).setCellValue(" 自有 □ 租赁 □ 其它 □________");
                }
                row22.getCell(8).setCellValue(lnLoanInfo.getBizArea());
                //开户机构   活期账户号



                //借款卡号   电话
                HSSFRow row25=sheet.getRow(25);
                row25.getCell(1).setCellValue(lnLoanInfo.getBizLeaglMobilePhone());
                //电子邮箱

                HSSFRow row26 =sheet.getRow(26);
                row26.getCell(1).setCellValue(lnLoanInfo.getBizLeagl());
                row26.getCell(12).setCellValue(lnLoanInfo.getCusCommonOperaterIdcard());
                //身证份号码   法人代表


                HSSFRow row28 =sheet.getRow(28);
                row28.getCell(1).setCellValue(lnLoanInfo.getAppMoney());
                row28.getCell(8).setCellValue(lnLoanInfo.getAppLoanPurpose());
                row28.getCell(16).setCellValue(lnLoanInfo.getAppLimitYear());


                HSSFRow row29 =sheet.getRow(29);
                row29.getCell(1).setCellValue(lnLoanInfo.getAdviceRepayDate());
                row29.getCell(8).setCellValue(lnLoanInfo.getAppRepaymentMonth());
                // row29.getCell(16).setCellValue("还款来源");


                //担保人
                HSSFRow row30 =sheet.getRow(30);
                Map<String,Object> condition = new HashMap<String, Object>();
                condition.put("loanId",loanId);
                List<LnLoanGuarantorBean> guarantorList = lnLoanGuarantorService.getAllLnLoanGuarantorBeanByConds(condition);
                if(guarantorList.size()!=0){
                    row30.getCell(1).setCellValue(guarantorList.get(0).getCustomerName());
                    row30.getCell(13).setCellValue(guarantorList.get(0).getIdCard());

                    HSSFRow row31 =sheet.getRow(31);
                    row31.getCell(1).setCellValue(guarantorList.get(0).getCphNumber());
                    row31.getCell(13).setCellValue(guarantorList.get(0).getPetitionerRelate());
                }





                HSSFRow row32 =sheet.getRow(32);
                List<LnPledge> pledgeList = lnLoanPledgeService.getLnLoanPledgeByLoanId(Integer.valueOf(loanId));
              if(pledgeList.size()!=0){
                  for(int i=0;i<pledgeList.size();i++){
                      row32.getCell(i+1).setCellValue(pledgeList.get(i).getGoods());
                  }
              }

                HSSFRow row34 =sheet.getRow(34);
                row34.getCell(1).setCellValue(lnLoanInfo.getCafMonthlySales());//月销售额

               /* HSSFRow row35 =sheet.getRow(35);
                if (lnLoanInfo.getCafGrossMargin()!=null){
                    row35.getCell(0).setCellValue("毛利润 √  净利润 □");//毛利润
                }
                 if (lnLoanInfo.getCafNetMargin()!=null){
                    row35.getCell(10).setCellValue("毛利润 □  净利润 √");//毛利润
                }*/



                HSSFRow row37 =sheet.getRow(37);
                row37.getCell(1).setCellValue(lnLoanInfo.getCafInventory());//存货
                row37.getCell(12).setCellValue(lnLoanInfo.getCafReceivableMoney());//应收账款

                HSSFRow row38 =sheet.getRow(38);
                row38.getCell(1).setCellValue(lnLoanInfo.getCafDebt());//负债
                row38.getCell(11).setCellValue(lnLoanInfo.getCafTotalMoney());//应收账款

                HSSFRow row39 =sheet.getRow(39);
                row39.getCell(1).setCellValue(lnLoanInfo.getCafOther());

                HSSFRow row41 =sheet.getRow(41);
                lnCreditHistoryList = lnCreditHistoryService.getCreditHistoryByLoanId(Integer.parseInt(loanId));
                if (lnCreditHistoryList.size()!=0 ){
                    row41.getCell(8).setCellValue("有 √ 无 □ ");
                }
                else{
                    row41.getCell(8).setCellValue("有 □ 无 √ ");
                }


                Map<String,Object> pam = new HashMap<String, Object>();
                condition.put("loanId",loanId);

                List<LnCreditHistory> lisy = lnCreditHistoryService.getCreditHistoryByLoanId(Integer.parseInt(loanId));
                if(CollectionUtils.isNotEmpty(lisy)){
                    int rowNum = 43;
                    for (LnCreditHistory guarantor : lisy ) {
                        HSSFRow row = sheet.getRow(rowNum);
                        row.getCell(0).setCellValue(guarantor.getFinaceSource());
                        row.getCell(3).setCellValue(guarantor.getLoanPurpose());
                        row.getCell(6).setCellValue(guarantor.getLoanMoney());
                        row.getCell(8).setCellValue(guarantor.getLoanDate());
                        row.getCell(12).setCellValue(guarantor.getLoanLimitYear());
                        row.getCell(15).setCellValue(lnLoanInfo.getAdviceRepayDate());
                        row.getCell(17).setCellValue(guarantor.getRepayMonth());
                        row.getCell(20).setCellValue(guarantor.getBalanceMoney());
                        rowNum++;
                    }
                }

                HSSFRow row54 =sheet.getRow(54);
                if(StringUtils.isNotBlank(lnLoanInfo.getCafGrossMargin())){
                    row34.getCell(10).setCellValue(lnLoanInfo.getCafGrossMargin());//毛利润
                }
                if(StringUtils.isNotBlank(lnLoanInfo.getCafNetMargin())){
                    row34.getCell(13).setCellValue(lnLoanInfo.getCafNetMargin());//净利润
                }


                HSSFRow row61 = sheet.getRow(61);
                row61.getCell(0).setCellValue(lnLoanInfo.getCusFirstImpress());//第一印象


            }
            if (lnLoanInfo.getAppLoanTypeId().equals("2")) {


                HSSFRow row6 = sheet.getRow(6);
                row6.getCell(1).setCellValue(lnLoanInfo.getCusName());
                row6.getCell(9).setCellValue(lnLoanInfo.getCusBirthday());
                if (lnLoanInfo.getCusSex().equals("男")){
                    row6.getCell(17).setCellValue("男 √  女 □ ");
                }
                if (lnLoanInfo.getCusSex().equals("女")) {
                    row6.getCell(17).setCellValue("男 □  女 √ ");
                }

                HSSFRow row7 = sheet.getRow(7);
                if(lnLoanInfo.getCusIdtypeId().equals("1")){
                    row7.getCell(1).setCellValue("身份证 √ 户口本 □");
                }
                if(lnLoanInfo.getCusIdtypeId().equals("2")){
                    row7.getCell(1).setCellValue("身份证 □ 户口本 √");
                }
                row7.getCell(17).setCellValue(lnLoanInfo.getCusIdcard());

                HSSFRow row8 = sheet.getRow(8);
                if(lnLoanInfo.getCusMarriage()!=null) {
                    if (lnLoanInfo.getCusMarriage().equals("01")) {
                        row8.getCell(1).setCellValue("单身 √  已婚 □ 离异 □ ");
                    }
                    if (lnLoanInfo.getCusMarriage().equals("02")) {
                        row8.getCell(1).setCellValue("单身 □  已婚 √ 离异 □ ");
                    }
                    if (lnLoanInfo.getCusMarriage().equals("04")) {
                        row8.getCell(1).setCellValue("单身 □  已婚 □ 离异 √ ");
                    }
                }
                row8.getCell(17).setCellValue(lnLoanInfo.getCusLocalYear());

                HSSFRow row10 = sheet.getRow(10);
                row10.getCell(1).setCellValue(lnLoanInfo.getCusMatePhone());//电话
                row10.getCell(11).setCellValue(lnLoanInfo.getCusMateMobilePhone());//手机号

                HSSFRow row9 = sheet.getRow(9);
                row9.getCell(1).setCellValue(lnLoanInfo.getCusAddress());//家庭住址
                row9.getCell(17).setCellValue(lnLoanInfo.getCusFamilyNum());//家庭人数

                HSSFRow row11 =sheet.getRow(11);

                if(lnLoanInfo.getCusLivingStatusId()!=null) {
                    if (lnLoanInfo.getCusLivingStatusId().equals("01")) {
                        row11.getCell(1).setCellValue(" 自有住房 √   抵押 □     租住 □     亲属住房 □      其它 □:_______ ");
                    }
                    if (lnLoanInfo.getCusLivingStatusId().equals("02")) {
                        row11.getCell(1).setCellValue(" 自有住房 □   抵押 √     租住 □     亲属住房 □      其它 □:_______ ");
                    }
                    if (lnLoanInfo.getCusLivingStatusId().equals("06")) {
                        row11.getCell(1).setCellValue(" 自有住房 □   抵押 □     租住 √     亲属住房 □      其它 □:_______ ");
                    }
                    if (lnLoanInfo.getCusLivingStatusId().equals("03")) {
                        row11.getCell(1).setCellValue(" 自有住房 □   抵押 □     租住 □     亲属住房 √      其它 □:_______ ");
                    }
                }

                HSSFRow row12 =sheet.getRow(12);
                if(lnLoanInfo.getCusEducationId()!=null) {
                    if (lnLoanInfo.getCusEducationId().equals("05")) {
                        row12.getCell(1).setCellValue(" 大学 √    大专 □   技校 □    中专  □   高中 □  初中 □   小学 □   其它□    ");
                    }
                    if (lnLoanInfo.getCusEducationId().equals("04")) {
                        row12.getCell(1).setCellValue(" 大学 □    大专 √   技校 □    中专  □   高中 □  初中 □   小学 □   其它□    ");
                    }
                    if (lnLoanInfo.getCusEducationId().equals("03")) {
                        row12.getCell(1).setCellValue(" 大学 □    大专 □   技校 □    中专  □   高中 √  初中 □   小学 □   其它□    ");
                    }
                    if (lnLoanInfo.getCusEducationId().equals("02")) {
                        row12.getCell(1).setCellValue(" 大学 □    大专 □   技校 □    中专  □   高中 □  初中 √   小学 □   其它□    ");
                    }
                    if (lnLoanInfo.getCusEducationId().equals("01")) {
                        row12.getCell(1).setCellValue(" 大学 □    大专 □   技校 □    中专  □   高中 □  初中 □   小学 √   其它□    ");
                    }

                }
                HSSFRow row13 =sheet.getRow(13);
                row13.getCell(1).setCellValue(lnLoanInfo.getCompanyAddress());
                row13.getCell(14).setCellValue(lnLoanInfo.getCompanyAddress());


                HSSFRow row14 =sheet.getRow(14);
                row14.getCell(1).setCellValue(lnLoanInfo.getCompanyMonthlyIncoming());
                row14.getCell(14).setCellValue(lnLoanInfo.getCompanyAddress());

                HSSFRow row16 =sheet.getRow(16);
                row16.getCell(1).setCellValue(lnLoanInfo.getCusMateName());
                row16.getCell(7).setCellValue(lnLoanInfo.getCusMateIdcard());
                row16.getCell(15).setCellValue(lnLoanInfo.getCusMateJob());
                row16.getCell(19).setCellValue(lnLoanInfo.getCusMateWorkYear());


                HSSFRow row17 =sheet.getRow(17);
                row17.getCell(1).setCellValue(lnLoanInfo.getCusMatePhone());//电话
                row17.getCell(14).setCellValue(lnLoanInfo.getCusMateMobilePhone());//手机号

                HSSFRow row18 =sheet.getRow(18);
                row18.getCell(1).setCellValue(lnLoanInfo.getCusMateAddress());//配偶工作单位地址
                row18.getCell(14).setCellValue(lnLoanInfo.getCusMateJob());//工作岗位

                HSSFRow row19 =sheet.getRow(19);//月工资水平
                if(lnLoanInfo.getCusMateMonthlyIncomingId()!=null) {
                    if (lnLoanInfo.getCusMateMonthlyIncomingId().equals("01")) {
                        row19.getCell(1).setCellValue("500-1000 □   1000-2000√   2000-5000 □   5000以上 □：         ");
                    }
                    if (lnLoanInfo.getCusMateMonthlyIncomingId().equals("02") || lnLoanInfo.getCusMateMonthlyIncomingId().equals("03") || lnLoanInfo.getCusMateMonthlyIncomingId().equals("04")) {
                        row19.getCell(1).setCellValue("500-1000 □   1000-2000□   2000-5000 √   5000以上 □：         ");
                    }
                    if (lnLoanInfo.getCusMateMonthlyIncomingId().equals("05")) {
                        row19.getCell(1).setCellValue("500-1000 □   1000-2000□   2000-5000 □   5000以上 √：         ");
                    }
                }
                HSSFRow row21 =sheet.getRow(21);
                row21.getCell(1).setCellValue(lnLoanInfo.getCusChdInfo());
                HSSFRow row22 =sheet.getRow(22);
                row22.getCell(1).setCellValue(lnLoanInfo.getCusParentInfo());

                HSSFRow row24 =sheet.getRow(24);
                row24.getCell(1).setCellValue(lnLoanInfo.getAppMoney());
                row24.getCell(8).setCellValue(lnLoanInfo.getAppLoanPurpose());
                row24.getCell(16).setCellValue(lnLoanInfo.getAppLimitYear());


                HSSFRow row25 =sheet.getRow(25);
                row25.getCell(1).setCellValue(lnLoanInfo.getAdviceRepayDate());
                row25.getCell(8).setCellValue(lnLoanInfo.getAppRepaymentMonth());
                // row25.getCell(16).setCellValue("还款来源");


                //担保人
                HSSFRow row26 =sheet.getRow(26);
                Map<String,Object> condition = new HashMap<String, Object>();
                condition.put("loanId",loanId);
                List<LnLoanGuarantorBean> guarantorList = lnLoanGuarantorService.getAllLnLoanGuarantorBeanByConds(condition);
                if(guarantorList.size()!=0){
                    row26.getCell(1).setCellValue(guarantorList.get(0).getCustomerName());
                    row26.getCell(13).setCellValue(guarantorList.get(0).getIdCard());
                    HSSFRow row27 =sheet.getRow(27);
                    row27.getCell(1).setCellValue(guarantorList.get(0).getCompany());
                    row27.getCell(13).setCellValue(guarantorList.get(0).getCompanyAddress());

                    HSSFRow row28 =sheet.getRow(28);
                    row28.getCell(1).setCellValue(guarantorList.get(0).getCompany());
              /*  row28.getCell(13).setCellValue(guarantorList.get(0).getCompanyAddress());*/

                    HSSFRow row30 =sheet.getRow(30);
                    row30.getCell(1).setCellValue(guarantorList.get(0).getCphNumber());
                    row30.getCell(13).setCellValue(guarantorList.get(0).getPetitionerRelate());
                }




                HSSFRow row36 =sheet.getRow(36);
                lnCreditHistoryList = lnCreditHistoryService.getCreditHistoryByLoanId(Integer.parseInt(loanId));
                if (lnCreditHistoryList!=null ){
                    row36.getCell(8).setCellValue("有 √ 无 □ ");
                }
                else{
                    row36.getCell(8).setCellValue("有 □ 无 √ ");
                }

                List<LnCreditHistory> lisy = lnCreditHistoryService.getCreditHistoryByLoanId(Integer.parseInt(loanId));
                if(CollectionUtils.isNotEmpty(lisy)){
                    int rowNum =38;
                    for (LnCreditHistory guarantor : lisy ) {
                        HSSFRow row = sheet.getRow(rowNum);
                        row.getCell(0).setCellValue(guarantor.getFinaceSource());
                        row.getCell(3).setCellValue(guarantor.getLoanPurpose());
                        row.getCell(6).setCellValue(guarantor.getLoanMoney());
                        row.getCell(8).setCellValue(guarantor.getLoanDate());
                        row.getCell(11).setCellValue(guarantor.getLoanLimitYear());
                        row.getCell(15).setCellValue(lnLoanInfo.getAdviceRepayDate());
                        row.getCell(17).setCellValue(guarantor.getRepayMonth());
                        row.getCell(20).setCellValue(guarantor.getBalanceMoney());
                        rowNum++;
                    }
                }
                HSSFRow row56 =sheet.getRow(56);
                row56.getCell(0).setCellValue(lnLoanInfo.getCusFirstImpress());//第一印象
            }

            FileOutputStream os = new FileOutputStream(new File(path));
            book.write(os);
            os.close();
        }catch(Exception e){
            logger.error("", e);
        }
    }



    /**
     * 导出贷款资料
     */
    public void exportLoanList(){
        logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- AllLoanAction.exportLoanList开始");
        // 往ie输出文件流
        try {
	            Map<String, Object> parameterMap = new HashMap<String, Object>();
	            // 是否是业务主管
	            Boolean isInChargeOf = deptFacadeService.isInChargeOfDepartment();
	            String belongUserIds = "";
	            if (isInChargeOf) {
                // 当前用户的下属用户
                belongUserIds = deptFacadeService.getUserIdsBelongToManager(getLoginInfo().getRoles(),"loanInfo");
                parameterMap.put("belongUserIds", belongUserIds); // 当前用户所管理的提交申请用户
            } else {
                // 客户经理
                parameterMap.put("belongUserId", this.getLoginInfo().getUserId());
            }
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 以下为搜索条件：");
            if (loanStatus != null) {
                parameterMap.put("loanStatusId", loanStatus); // 贷款状态
                logger.info("贷款状态："+loanStatus);
            } else {
                parameterMap.put("allLoanOrderBy", 1);
            }

            parameterMap.put("customer", customer);
            if (customer != null && !customer.equals("")){
                logger.info("客户："+customer);
            }
            parameterMap.put("loanIsCheckout", checkoutStatus);
            if (checkoutStatus != null && !checkoutStatus.equals("")){
                logger.info("贷后状态："+checkoutStatus);
            }
            parameterMap.put("startCreateDate", startCreateDate);
            if (startCreateDate != null){
                logger.info("创建时间的开始时间："+startCreateDate);
            }
            endCreateDate = lnLoanService.addSecondsForDate(endCreateDate,59);
            if (endCreateDate != null){
                logger.info("创建时间的结束时间："+endCreateDate);
            }
            parameterMap.put("endCreateDate", endCreateDate);
            parameterMap.put("applyStartDate", startDate);
            if (startDate != null){
                logger.info("提交时间的开始时间："+startDate);
            }
            endDate = lnLoanService.addSecondsForDate(endDate,59);
            if (endDate != null){
                logger.info("提交时间的结束时间："+endDate);
            }
            parameterMap.put("applyEndDate", endDate);
            parameterMap.put("hasAuthUserId", this.getLoginInfo().getUserId());

            //贷款归属
            String belongToType = request.getParameter("BelongToType");
            if (StringUtils.isNotEmpty(belongToType)){
                if (belongToType.equals("brAll")){
                    //所有，包括下属用户和下属机构
//                    parameterMap.put("cusBelongUserIds",belongUserIds);
                }else if (belongToType.equals("brMine")){
                    //我的
                    parameterMap.put("cusBelongUserIds",this.getLoginInfo().getUserId().toString());
                    logger.info("归属人员(我的)："+this.getLoginInfo().getUserId());
                }else if (belongToType.equals("brUser")){
                    //下属用户
                    parameterMap.put("cusBelongUserIds",userIds);
                    logger.info("归属人员(下属)："+userIds);
                }else if (belongToType.equals("brDept")){
                    //机构的
                    String deptIds = request.getParameter("deptIds");
                    parameterMap.put("cusBelongDeptIds",deptIds);
                    logger.info("归属人员(机构)："+deptIds);
                }else if (belongToType.equals("brUnAllocate")){
                    //未分配
                    parameterMap.put("unAllocate","1");
                    logger.info("归属人员(未分配)");
                }
            }
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 开始搜索");
//            parameterMap.put("cusBelongUserIds",userIds);
            List<LnLoan> dataList = lnLoanService.getAllLoanNoPage(parameterMap);
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索到"+dataList.size()+"条符合条件的贷款");
            //处理贷款客户的权限
            lnLoanService.proCusAuthority(isInChargeOf,belongUserIds,dataList,this.getLoginInfo());
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- 搜索完毕");


            Long times = Calendar.getInstance().getTimeInMillis();
            String dir = sysParamService.getRecordPath()+File.separator+"LoanExportList";
            String curDownFilePath = dir + File.separator+times+".xls";
            File f = new File(dir);
            if (!f.exists()) {
                f.mkdirs();
            }
            lnLoanService.getLoanExportListFile(dataList, curDownFilePath);
            File downFile = new File(curDownFilePath);
            if (downFile.exists()){
                synchronized (this){
                    FileInputStream fis = new FileInputStream(downFile);// 服务器文件路径
                    ServletOutputStream output = this.getResponse().getOutputStream();
                    this.getResponse().addHeader("Content-Disposition", "attachment;filename="
                            + new String(downFile.getName().getBytes("gbk"),
                            "iso8859-1"));
                    BufferedInputStream bis = new BufferedInputStream(fis);// 输入缓冲流
                    BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流
                    byte buff[] = new byte[4096];// 缓冲字节数
                    int bytesRead;
                    while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                        bos.write(buff, 0, bytesRead);
                    }
                    if (bis != null){
                        bis.close();
                    }
                    if (bos != null){
                        bos.flush();// 清空输出缓冲流
                        bos.close();
                    }
                    if (output != null){
                        output.close();
                    }
                    FileUtil.deleteFile(downFile.getAbsolutePath());
                }
            }
            logger.info("-----当前登录用户："+this.getLoginInfo().getAccount()+"----- AllLoanAction.exportLoanData完成");
        } catch (Exception e) {
            logger.error("AllLoanAction % exportLoanData",e);
        }
    }

    public String getExecAllTimeTaskPage(){
        try {
            return SUCCESS;
        }catch (Exception e){
            logger.error("AllLoanAction % getExecAllTimeTaskPage",e);
            return ERROR;
        }
    }

    public void execAllTimeTask(){
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            try {
                out = response.getWriter();
                logger.info("AllLoanAction % execAllTimeTask,开始执行跑批任务...");
                //autoImportCrmJob.autoImport();
                out.write("ok");
                logger.info("AllLoanAction % execAllTimeTask,执行跑批任务成功！");
            }finally {
                if(out != null){
                    out.flush();
                    out.close();
                }
            }
        }catch (Exception e){
            logger.error("AllLoanAction % execAllTimeTask",e);
        }
    }

    /**
     * 当前用户有权限的 机构ids
     *
     * @return
     */
    private String getCurrentUserInChargeOfDeptIds() {
        String deptIds = "";
        Integer[] arr = deptFacadeService.getInChargeOfDeptIds();
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (deptIds.equals(""))
                    deptIds = arr[i].toString();
                else
                    deptIds = deptIds + "," + arr[i];
            }
        }
        return deptIds;
    }

    /**
     * 获取当前用户有权限的 用户ids
     *
     * @return
     */
    private String getCurrentInChargeUserIds() {
        String userIds = "";
        Integer[] arr = deptFacadeService.getInChargeOfDeptUserIds();
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                if (userIds.equals(""))
                    userIds = arr[i].toString();
                else
                    userIds = userIds + "," + arr[i];
            }
        }
        if (userIds.equals("")) {
            userIds = this.getLoginInfo().getUserId().toString();
        } else {
            userIds = userIds + "," + this.getLoginInfo().getUserId();
        }
        return userIds;
    }

    public SysParamService getSysParamService() {
        return sysParamService;
    }

    public void setSysParamService(SysParamService sysParamService) {
        this.sysParamService = sysParamService;
    }

    public LnLoanService getLnLoanService() {
        return lnLoanService;
    }

    public void setLnLoanService(LnLoanService lnLoanService) {
        this.lnLoanService = lnLoanService;
    }

    public DeptFacadeService getDeptFacadeService() {
        return deptFacadeService;
    }

    public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
        this.deptFacadeService = deptFacadeService;
    }

    public LnLoanTypeService getLnLoanTypeService() {
        return lnLoanTypeService;
    }

    public void setLnLoanTypeService(LnLoanTypeService lnLoanTypeService) {
        this.lnLoanTypeService = lnLoanTypeService;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public LnCancelReasonService getLnCancelReasonService() {
        return lnCancelReasonService;
    }

    public void setLnCancelReasonService(LnCancelReasonService lnCancelReasonService) {
        this.lnCancelReasonService = lnCancelReasonService;
    }

    public LnOpHistoryService getLnOpHistoryService() {
        return lnOpHistoryService;
    }

    public void setLnOpHistoryService(LnOpHistoryService lnOpHistoryService) {
        this.lnOpHistoryService = lnOpHistoryService;
    }

    public void setLnLoanStatusService(LnLoanStatusService lnLoanStatusService) {
        this.lnLoanStatusService = lnLoanStatusService;
    }

    public void setLoanStatus(Integer loanStatus) {
        this.loanStatus = loanStatus;
    }

    public void setCheckoutStatus(Integer checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public Date getEndCreateDate() {
        return endCreateDate;
    }

    public void setEndCreateDate(Date endCreateDate) {
        this.endCreateDate = endCreateDate;
    }

    public Date getStartCreateDate() {
        return startCreateDate;
    }

    public void setStartCreateDate(Date startCreateDate) {
        this.startCreateDate = startCreateDate;
    }

	public List<LnLoan> getLnLoans() {
		return lnLoans;
	}

	public void setLnLoans(List<LnLoan> lnLoans) {
		this.lnLoans = lnLoans;
	}

	public void setDataPhotoService(DataPhotoService dataPhotoService) {
		this.dataPhotoService = dataPhotoService;
	}

	public void setLnLoanDetailService(LnLoanDetailService lnLoanDetailService) {
		this.lnLoanDetailService = lnLoanDetailService;
	}

	public List<LoanData> getLoanDatas() {
		return loanDatas;
	}

	public void setLoanDatas(List<LoanData> loanDatas) {
		this.loanDatas = loanDatas;
	}

	public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
		this.crmCustomerService = crmCustomerService;
	}

	public void setCustomerDataService(CustomerDataService customerDataService) {
		this.customerDataService = customerDataService;
	}

    public void setSpecialDataAuthService(SpecialDataAuthService specialDataAuthService) {
        this.specialDataAuthService = specialDataAuthService;
    }

    public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
        this.lnLoanInfoService = lnLoanInfoService;
    }

	public void setSysTeamUserService(SysTeamUserService sysTeamUserService) {
		this.sysTeamUserService = sysTeamUserService;
	}

	public String getSurveyUser() {
		return surveyUser;
	}

	public void setSurveyUser(String surveyUser) {
		this.surveyUser = surveyUser;
	}

	public String getIsReject() {
		return isReject;
	}

	public void setIsReject(String isReject) {
		this.isReject = isReject;
	}

	public String getLoanTypeId() {
		return loanTypeId;
	}

	public void setLoanTypeId(String loanTypeId) {
		this.loanTypeId = loanTypeId;
	}

	public SysTeamService getSysTeamService() {
		return sysTeamService;
	}

	public void setSysTeamService(SysTeamService sysTeamService) {
		this.sysTeamService = sysTeamService;
	}

	public SysTeamUserService getSysTeamUserService() {
		return sysTeamUserService;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
    
	  public String getResultRepayDate() {
			return resultRepayDate;
		}

		public String getMonitored() {
		return monitored;
	}

	public void setMonitored(String monitored) {
		this.monitored = monitored;
	}

		public String getMonIsCheck() {
		return monIsCheck;
	}

	public void setMonIsCheck(String monIsCheck) {
		this.monIsCheck = monIsCheck;
	}

		public void setResultRepayDate(String resultRepayDate) {
			this.resultRepayDate = resultRepayDate;
		}

    public LnLoanStatusService getLnLoanStatusService() {
        return lnLoanStatusService;
    }

    public DataPhotoService getDataPhotoService() {
        return dataPhotoService;
    }

    public LnLoanDetailService getLnLoanDetailService() {
        return lnLoanDetailService;
    }

    public CrmCustomerService getCrmCustomerService() {
        return crmCustomerService;
    }

    public CustomerDataService getCustomerDataService() {
        return customerDataService;
    }

    public SpecialDataAuthService getSpecialDataAuthService() {
        return specialDataAuthService;
    }

    public LnLoanInfoService getLnLoanInfoService() {
        return lnLoanInfoService;
    }

    public Integer getLoanStatus() {
        return loanStatus;
    }

    public Integer getCheckoutStatus() {
        return checkoutStatus;
    }

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    public String getNomontype() {
        return nomontype;
    }

    public void setNomontype(String nomontype) {
        this.nomontype = nomontype;
    }
    public LnLoanPledgeService getLnLoanPledgeService() {
        return lnLoanPledgeService;
    }

    public void setLnLoanPledgeService(LnLoanPledgeService lnLoanPledgeService) {
        this.lnLoanPledgeService = lnLoanPledgeService;
    }
    public LnLoanGuarantorService getLnLoanGuarantorService() {
        return lnLoanGuarantorService;
    }

    public void setLnLoanGuarantorService(LnLoanGuarantorService lnLoanGuarantorService) {
        this.lnLoanGuarantorService = lnLoanGuarantorService;
    }

    public LnLoanInfoDictionaryService getLnLoanInfoDictionaryService() {
        return lnLoanInfoDictionaryService;
    }

    public void setLnLoanInfoDictionaryService(LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
        this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
    }

    public LnCreditHistoryService getLnCreditHistoryService() {
        return lnCreditHistoryService;
    }

    public void setLnCreditHistoryService(LnCreditHistoryService lnCreditHistoryService) {
        this.lnCreditHistoryService = lnCreditHistoryService;
    }
}
