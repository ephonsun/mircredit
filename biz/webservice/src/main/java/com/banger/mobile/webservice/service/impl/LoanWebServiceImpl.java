/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * Author     :zhangfp
 * Create Date:2013-3-14
 */
package com.banger.mobile.webservice.service.impl;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.constants.TransportConstants;
import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.base.loan.BaseLnLoanInfo;
import com.banger.mobile.domain.model.base.loan.BaseLnLoanMonitor;
import com.banger.mobile.domain.model.base.system.BaseSysTeam;
import com.banger.mobile.domain.model.crRequest.CrRequest;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.data.*;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.feedback.FeedBack;
import com.banger.mobile.domain.model.loan.*;
import com.banger.mobile.domain.model.pad.*;
import com.banger.mobile.domain.model.pad.smallPageCard.PadLoanInfoCard;
import com.banger.mobile.domain.model.pad.smallPageCard.PadLoanInfoPageCard;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.crRequest.CrRequestService;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataAudioService;
import com.banger.mobile.facade.data.DataPhotoService;
import com.banger.mobile.facade.data.DataVideoService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.feedBack.FeedBackService;
import com.banger.mobile.facade.impl.system.team.SysTeamServiceImpl;
import com.banger.mobile.facade.loan.*;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.rolemember.SysRoleMemberService;
import com.banger.mobile.facade.system.team.SysTeamUserService;
import com.banger.mobile.facade.uploadFile.SysUploadFileService;
import com.banger.mobile.facade.uploadFile.UploadFileToCMS;
import com.banger.mobile.facade.user.SysDeptAuthService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.facade.webservice.LoanWebService;
import com.banger.mobile.util.*;
import com.banger.mobile.webservice.domain.loan.*;
import com.banger.mobile.webservice.domain.loan.PadLoanPageList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.util.CollectionUtils;

import javax.jws.WebService;
import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhangfp
 * @version $Id: LoanWebServiceImpl.java v 0.1 ${} 上午11:40 Administrator Exp $
 */
@WebService(serviceName = "BangerCrmLoanService", endpointInterface = "com.banger.mobile.facade.webservice.LoanWebService")
public class LoanWebServiceImpl implements LoanWebService {

	private static final Logger logger = Logger
			.getLogger(LoanWebServiceImpl.class);

	private SysUserService sysUserService; // 系统用户service
	private LnLoanService lnLoanService; // 贷款主表service
	private DeptFacadeService deptFacadeService; // 用户部门service
	private LnRepaymentPlanService lnRepaymentPlanService; // 还款计划service
	private LnExceptionRepaymentPlanService lnExceptionRepaymentPlanService; // 异常还款计划service
	private CustomerDataService customerDataService; // 客户资料service
	private LnLoanDetailService lnLoanDetailService; // 贷款详情service
	private LnOpHistoryService lnOpHistoryService; // 贷款历史操作记录service
	private LnLoanTypeService lnLoanTypeService; // 贷款类型service
	private LnLoanSubTypeService lnLoanSubTypeService; // 贷款子类型service
	private LnDunLogService lnDunLogService; // 贷款催收日志service
	private LnExceptionDunLogService lnExceptionDunLogService; // 贷款异常催收日志service
	private LnCancelReasonService lnCancelReasonService; // 贷款撤销原因service
	private CrmCustomerService crmCustomerService; // 客户service
	private DataPhotoService dataPhotoService;
	private DataAudioService dataAudioService;
	private DataVideoService dataVideoService;
	private LnLoanGuarantorService lnLoanGuarantorService; // 担保人
	private LnLoanInfoService lnLoanInfoService; // 贷款信息service
	private LnVerifyHistoryService lnVerifyHistoryService; // 贷款审计历史记录service
	private UploadFileToCMS uploadFileToCMS;
	private LnRejectCustomerService lnRejectCustomerService;
	private SysParamService sysParamService;
	private CrRequestService crRequestService;
	private FeedBackService feedBackService;// 在线反馈
	private LnLoanCoBorrowerService lnLoanCoBorrowerService;
	private SDicService sDicService;
	private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
	private LnLoanPledgeService lnLoanPledgeService;
	private LnCreditHistoryService lnCreditHistoryService;
	private SysRoleMemberService sysRoleMemberService;
	private SysTeamServiceImpl sysTeamService;
	private SysTeamUserService sysTeamUserService;
	private SysUploadFileService sysUploadFileService;
	private LnLoanMonitorService lnLoanMonitorService;
	private LnBalanceService lnBalanceService;

	public LnBalanceService getLnBalanceService() {
		return lnBalanceService;
	}

	public void setLnBalanceService(LnBalanceService lnBalanceService) {
		this.lnBalanceService = lnBalanceService;
	}

	private String docType1;
	private String docType2;
	private String docType3;
	private String docType4;
	private String docType5;
	private String docType6;
	private String docType7;
	private String docType8;
	private String docType9;
	private String docType10;
	private String docType11;
	private String docType12;
	private String docType13;
	private String docType14;
	private String docType15;
	private String docType16;
	private String docType20;
	private String docType21;
	private String docType22;
	private String docType23;
	private String feedBackFileUrl;

	private String approveUser;// 配置文件的信贷操作号
	private String url;



	public LnLoanMonitorService getLnLoanMonitorService() {
		return lnLoanMonitorService;
	}

	public void setLnLoanMonitorService(LnLoanMonitorService lnLoanMonitorService) {
		this.lnLoanMonitorService = lnLoanMonitorService;
	}

	public SysUploadFileService getSysUploadFileService() {
		return sysUploadFileService;
	}

	public void setSysUploadFileService(SysUploadFileService sysUploadFileService) {
		this.sysUploadFileService = sysUploadFileService;
	}

	public void setSysRoleMemberService(SysRoleMemberService sysRoleMemberService) {
		this.sysRoleMemberService = sysRoleMemberService;
	}

	public void setSysTeamUserService(SysTeamUserService sysTeamUserService) {
		this.sysTeamUserService = sysTeamUserService;
	}

	public SysTeamServiceImpl getSysTeamService() {
		return sysTeamService;
	}

	public void setSysTeamService(SysTeamServiceImpl sysTeamService) {
		this.sysTeamService = sysTeamService;
	}

	public LnCreditHistoryService getLnCreditHistoryService() {
		return lnCreditHistoryService;
	}

	public void setLnCreditHistoryService(
			LnCreditHistoryService lnCreditHistoryService) {
		this.lnCreditHistoryService = lnCreditHistoryService;
	}

	public LnLoanPledgeService getLnLoanPledgeService() {
		return lnLoanPledgeService;
	}

	public void setLnLoanPledgeService(LnLoanPledgeService lnLoanPledgeService) {
		this.lnLoanPledgeService = lnLoanPledgeService;
	}

	public LnLoanInfoDictionaryService getLnLoanInfoDictionaryService() {
		return lnLoanInfoDictionaryService;
	}

	public void setLnLoanInfoDictionaryService(
			LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
		this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
	}

	public void setLnLoanCoBorrowerService(
			LnLoanCoBorrowerService lnLoanCoBorrowerService) {
		this.lnLoanCoBorrowerService = lnLoanCoBorrowerService;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setCrRequestService(CrRequestService crRequestService) {
		this.crRequestService = crRequestService;
	}

	public void setSysParamService(SysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	public void setApproveUser(String approveUser) {
		this.approveUser = approveUser;
	}

	public String getApproveUser() {
		return approveUser;
	}

	public void setFeedBackService(FeedBackService feedBackService) {
		this.feedBackService = feedBackService;
	}

	public void setLnRejectCustomerService(
			LnRejectCustomerService lnRejectCustomerService) {
		this.lnRejectCustomerService = lnRejectCustomerService;
	}

	public void setDocType1(String docType1) {
		this.docType1 = docType1;
	}

	public void setDocType2(String docType2) {
		this.docType2 = docType2;
	}

	public void setDocType3(String docType3) {
		this.docType3 = docType3;
	}

	public void setDocType4(String docType4) {
		this.docType4 = docType4;
	}

	public void setDocType5(String docType5) {
		this.docType5 = docType5;
	}

	public void setDocType6(String docType6) {
		this.docType6 = docType6;
	}

	public void setDocType7(String docType7) {
		this.docType7 = docType7;
	}

	public void setDocType8(String docType8) {
		this.docType8 = docType8;
	}

	public void setDocType9(String docType9) {
		this.docType9 = docType9;
	}

	public void setDocType10(String docType10) {
		this.docType10 = docType10;
	}

	public void setDocType11(String docType11) {
		this.docType11 = docType11;
	}

	public void setDocType12(String docType12) {
		this.docType12 = docType12;
	}

	public void setDocType13(String docType13) {
		this.docType13 = docType13;
	}

	public void setDocType14(String docType14) {
		this.docType14 = docType14;
	}

	public void setDocType15(String docType15) {
		this.docType15 = docType15;
	}

	public void setDocType16(String docType16) {
		this.docType16 = docType16;
	}

	public void setDocType20(String docType20) {
		this.docType20 = docType20;
	}

	public void setDocType21(String docType21) {
		this.docType21 = docType21;
	}

	public void setDocType22(String docType22) {
		this.docType22 = docType22;
	}

	public void setDocType23(String docType23) {
		this.docType23 = docType23;
	}

	public void setFeedBackFileUrl(String feedBackFileUrl) {
		this.feedBackFileUrl = feedBackFileUrl;
	}

	public void setUploadFileToCMS(UploadFileToCMS uploadFileToCMS) {
		this.uploadFileToCMS = uploadFileToCMS;
	}

	public LnVerifyHistoryService getLnVerifyHistoryService() {
		return lnVerifyHistoryService;
	}

	public void setLnVerifyHistoryService(
			LnVerifyHistoryService lnVerifyHistoryService) {
		this.lnVerifyHistoryService = lnVerifyHistoryService;
	}

	public void setDataAudioService(DataAudioService dataAudioService) {
		this.dataAudioService = dataAudioService;
	}

	public void setDataVideoService(DataVideoService dataVideoService) {
		this.dataVideoService = dataVideoService;
	}

	public LnLoanInfoService getLnLoanInfoService() {
		return lnLoanInfoService;
	}

	public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
		this.lnLoanInfoService = lnLoanInfoService;
	}

	public LnLoanGuarantorService getLnLoanGuarantorService() {
		return lnLoanGuarantorService;
	}

	public void setLnLoanGuarantorService(
			LnLoanGuarantorService lnLoanGuarantorService) {
		this.lnLoanGuarantorService = lnLoanGuarantorService;
	}

	public void setDataPhotoService(DataPhotoService dataPhotoService) {
		this.dataPhotoService = dataPhotoService;
	}

	public CrmCustomerService getCrmCustomerService() {
		return crmCustomerService;
	}

	public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
		this.crmCustomerService = crmCustomerService;
	}

	public void setLnCancelReasonService(
			LnCancelReasonService lnCancelReasonService) {
		this.lnCancelReasonService = lnCancelReasonService;
	}

	public LnDunLogService getLnDunLogService() {
		return lnDunLogService;
	}

	public void setLnDunLogService(LnDunLogService lnDunLogService) {
		this.lnDunLogService = lnDunLogService;
	}

	public LnExceptionDunLogService getLnExceptionDunLogService() {
		return lnExceptionDunLogService;
	}

	public void setLnExceptionDunLogService(
			LnExceptionDunLogService lnExceptionDunLogService) {
		this.lnExceptionDunLogService = lnExceptionDunLogService;
	}

	public LnExceptionRepaymentPlanService getLnExceptionRepaymentPlanService() {
		return lnExceptionRepaymentPlanService;
	}

	public void setLnExceptionRepaymentPlanService(
			LnExceptionRepaymentPlanService lnExceptionRepaymentPlanService) {
		this.lnExceptionRepaymentPlanService = lnExceptionRepaymentPlanService;
	}

	public LnLoanTypeService getLnLoanTypeService() {
		return lnLoanTypeService;
	}

	public void setLnLoanTypeService(LnLoanTypeService lnLoanTypeService) {
		this.lnLoanTypeService = lnLoanTypeService;
	}

	public LnLoanSubTypeService getLnLoanSubTypeService() {
		return lnLoanSubTypeService;
	}

	public void setLnLoanSubTypeService(
			LnLoanSubTypeService lnLoanSubTypeService) {
		this.lnLoanSubTypeService = lnLoanSubTypeService;
	}

	public SysUserService getSysUserService() {
		return sysUserService;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
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

	public LnRepaymentPlanService getLnRepaymentPlanService() {
		return lnRepaymentPlanService;
	}

	public void setLnRepaymentPlanService(
			LnRepaymentPlanService lnRepaymentPlanService) {
		this.lnRepaymentPlanService = lnRepaymentPlanService;
	}

	public CustomerDataService getCustomerDataService() {
		return customerDataService;
	}

	public void setCustomerDataService(CustomerDataService customerDataService) {
		this.customerDataService = customerDataService;
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

	public SDicService getsDicService() {
		return sDicService;
	}

	public void setsDicService(SDicService sDicService) {
		this.sDicService = sDicService;
	}

	/**
	 *
	 * @param paderrorResult
	 * @return
	 */
	private String getErrorResult(PadErrorResult paderrorResult) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		String result = JSONArray.fromObject(paderrorResult, jsonConfig)
				.toString();
		return result;
	}

	/**
	 * 所有贷款列表
	 *
	 * @param account
	 *            用户账号
	 * @param input
	 *            搜索条件(客户姓名/联系电话/身份证号)
	 * @param loanStatusId
	 *            贷款状态ID
	 * @param pageNumber
	 *            当前页面
	 * @return
	 * @author xiall
	 */
	public String getAllLoanList(String account, String input,
								 Integer loanStatusId, Integer pageNumber) {

		try {
			logger.info("pad端开始查找所有贷款列表,当前登录用户为“" + account + "”,客户相关查询条件为“"
					+ input + "”,列表当前查询页为“" + pageNumber + "”,贷款状态为“"
					+ loanStatusId + "”");
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());

			SysUser user = sysUserService.getAllUserByAccount(account);
			Page page = new Page();
			page.setCurrentPage(pageNumber);

			// 查询条件
			Map<String, Object> conds = new HashMap<String, Object>();
			conds.put("customerInfo", input);
			conds.put("curUserId", user.getUserId()); // 当前登陆用户
			conds.put("userRole",
					sysUserService.getRoleByUserId(user.getUserId())); // 获取用户角色
			if(loanStatusId!=-1){
				conds.put("loanStatusId", loanStatusId);
			}
			// 列表数据
			PageUtil<PadLoan> list = lnLoanService
					.getPadLoanByPage(conds, page);

			PadLoanPageList padLoanPageList = new PadLoanPageList();

			List<BaseLoanListInfo> plist = new ArrayList<BaseLoanListInfo>();

			for (PadLoan padLond : list.getItems()) {
				plist.add(new AllLoanListInfo(padLond));
			}

			padLoanPageList.setDataList(plist);

			padLoanPageList.setPageCount(list.getPage().getTotalRowsAmount());

			String result = JSONArray.fromObject(padLoanPageList, jsonConfig)
					.toString();

			logger.info("pad端完成查找所有贷款列表,当前登录用户为“" + account + "”,客户相关查询条件为“"
					+ input + "”,列表当前查询页为“" + pageNumber + "”,贷款状态为“"
					+ loanStatusId + "”");
			return result;

		} catch (Exception e) {
			logger.error("getAllLoanList", e);
			return getErrorResult(new PadErrorResult("unknow",
					"getAllLoanList", "未知错误"));
		}
	}

	/**
	 * 待提交贷款列表 NeedSubmitLoanListInfo
	 *
	 * @param account
	 * @param input
	 * @param pageNumber
	 * @return
	 */
	public String getNeedSubmitLoanList(String account, String input,
										Integer pageNumber) {
		try {
			logger.info("pad端开始查找申请贷款列表,当前登录用户为“" + account + "”,客户相关查询条件为“"
					+ input + "”,列表当前查询页为“" + pageNumber + "”");
			SysUser user = sysUserService.getAllUserByAccount(account);

			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());

			Page page = new Page();
			page.setCurrentPage(pageNumber);

			// 列表数据
			Map<String, Object> conds = new HashMap<String, Object>();

			// 只显示自己负责的贷款申请
			conds.put("createUserId", user.getUserId());
			conds.put("loanStatusId", 1); // 1表示待提交
			conds.put("customerInfo", input);

			PageUtil<PadLoan> list = lnLoanService
					.getPadLoanByPage(conds, page);

			PadLoanPageList padLoanPageList = new PadLoanPageList();

			List<BaseLoanListInfo> plist = new ArrayList<BaseLoanListInfo>();

			for (PadLoan padLond : list.getItems()) {
				plist.add(new NeedSubmitLoanListInfo(padLond));
			}

			padLoanPageList.setDataList(plist);

			padLoanPageList.setPageCount(list.getPage().getTotalRowsAmount());

			String result = JSONArray.fromObject(padLoanPageList, jsonConfig)
					.toString();
			logger.info("pad端完成查找申请贷款列表,当前登录用户为“" + account + "”,客户相关查询条件为“"
					+ input + "”,列表当前查询页为“" + pageNumber + "”");
			return result;

		} catch (Exception e) {
			logger.error("getNeedSubmitLoanList", e);
			return null;
		}
	}

	/**
	 * 待分配贷款列表 NeedAssignLoanListInfo
	 *
	 * @param account
	 *            用户账号
	 * @param input
	 *            搜索条件(客户姓名/联系电话/身份证号)
	 * @param submitUserId
	 *            提交人员
	 * @param pageNumber
	 *            当前显示页
	 * @return
	 * @author zhangfp
	 */
	public String getNeedAssignLoanList(String account, String input,
										Integer submitUserId, Integer pageNumber) {
		try {
			logger.info("pad端开始查找分配贷款列表,当前登录用户为“" + account + "”,客户相关查询条件为“"
					+ input + "”,申请提交人员“" + submitUserId + "”,列表当前查询页为“"
					+ pageNumber + "”");

			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());

			Page page = new Page();
			page.setCurrentPage(pageNumber);

			SysUser user = sysUserService.getAllUserByAccount(account);
			// 查询条件
			Map<String, Object> conds = new HashMap<String, Object>();
			conds.put("loanStatusId", 2);
			conds.put("customerInfo", input);
			conds.put("assignUserId", user.getUserId());
			if (submitUserId > 0) {
				conds.put("applyUserId", submitUserId);
			}

			PageUtil<PadLoan> list = lnLoanService
					.getPadLoanByPage(conds, page);

			PadLoanPageList padLoanPageList = new PadLoanPageList();

			List<BaseLoanListInfo> plist = new ArrayList<BaseLoanListInfo>();

			for (PadLoan padLond : list.getItems()) {
				plist.add(new NeedAssignLoanListInfo(padLond));
			}

			padLoanPageList.setDataList(plist);

			padLoanPageList.setPageCount(list.getPage().getTotalRowsAmount());

			String result = JSONArray.fromObject(padLoanPageList, jsonConfig)
					.toString();
			logger.info("pad端开始查找分配贷款列表,当前登录用户为“" + account + "”,客户相关查询条件为“"
					+ input + "”,申请提交人员“" + submitUserId + "”,列表当前查询页为“"
					+ pageNumber + "”");
			return result;

		} catch (Exception e) {
			logger.error("getNeedAssignLoanList", e);
			return null;
		}
	}

	/**
	 * 待调查贷款列表 NeedResearchLoanListInfo
	 *
	 * @param account
	 *            用户账号
	 * @param input
	 *            搜索条件(客户姓名/联系电话/身份证号)
	 * @param pageNumber
	 *            当前显示页
	 * @return
	 * @author zhangfp
	 */
	public String getNeedResearchLoanList(String account, String input,
										  Integer pageNumber) {
		try {

			logger.info("pad端开始查找调查贷款列表,当前登录用户为“" + account + "”,客户相关查询条件为“"
					+ input + "”,列表当前查询页为“" + pageNumber + "”");

			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			Page page = new Page();
			page.setCurrentPage(pageNumber);
			SysUser user = sysUserService.getAllUserByAccount(account);
			// 查询条件
			Map<String, Object> conds = new HashMap<String, Object>();
			// 只显示我负责的调查贷款
			conds.put("surveyUserId", user.getUserId());
			conds.put("loanStatusId", 3);
			conds.put("customerInfo", input);

			// 列表数据
			PageUtil<PadLoan> list = lnLoanService
					.getPadLoanByPage(conds, page);

			PadLoanPageList padLoanPageList = new PadLoanPageList();

			List<BaseLoanListInfo> plist = new ArrayList<BaseLoanListInfo>();

			for (PadLoan padLond : list.getItems()) {
				plist.add(new NeedResearchLoanListInfo(padLond));
			}

			padLoanPageList.setDataList(plist);

			padLoanPageList.setPageCount(list.getPage().getTotalRowsAmount());

			String result = JSONArray.fromObject(padLoanPageList, jsonConfig)
					.toString();

			logger.info("pad端完成查找调查贷款列表,当前登录用户为“" + account + "”,客户相关查询条件为“"
					+ input + "”,列表当前查询页为“" + pageNumber + "”");
			return result;

		} catch (Exception e) {
			logger.error("RecordWebServicesImpl % getNeedResearchLoanList", e);
			return null;
		}
	}

	/**
	 * 贷款待审批列表 ApprovalLoanListInfo
	 *
	 * @param account
	 * @param input
	 * @param page
	 * @return
	 */
	public String getApprovalLoanList(String account, String input,
									  Integer pageNumber) {
		try {
			logger.info("pad端贷款接口getApprovalLoanList开始，当前登录用户为“" + account
					+ "”,客户相关的查询条件“" + input + "”,当前查询页“" + pageNumber + "”");

			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			Page page = new Page();
			page.setCurrentPage(pageNumber);
			SysUser user = sysUserService.getAllUserByAccount(account);

			Map<String, Object> conds = new HashMap<String, Object>();

			conds.put("approveUserId", user.getUserId());
			conds.put("loanStatusId", 4); // 4表示待审批
			conds.put("customerInfo", input);

			// 列表数据
			PageUtil<PadLoan> list = lnLoanService
					.getPadLoanByPage(conds, page);

			PadLoanPageList padLoanPageList = new PadLoanPageList();

			List<BaseLoanListInfo> plist = new ArrayList<BaseLoanListInfo>();

			for (PadLoan padLond : list.getItems()) {
				plist.add(new ApprovalLoanListInfo(padLond));
			}

			padLoanPageList.setDataList(plist);

			padLoanPageList.setPageCount(list.getPage().getTotalRowsAmount());

			String result = JSONArray.fromObject(padLoanPageList, jsonConfig)
					.toString();

			logger.info("pad端贷款接口getApprovalLoanList完成，当前登录用户为“" + account
					+ "”,客户相关的查询条件“" + input + "”,当前查询页“" + page + "”");
			return result;

		} catch (Exception e) {
			logger.error("getApprovalLoanList", e);
			return null;
		}
	}

	/**
	 * 贷款放贷列表 NeedLendingLoanListInfo
	 *
	 * @param account
	 * @param input
	 * @param page
	 * @return
	 */
	public String getLendingLoanList(String account, String input,
									 Integer pageNumber) {
		try {
			logger.info("pad端贷款接口getLendingLoanList开始，当前登录用户为“" + account
					+ "”,客户相关的查询条件“" + input + "”,当前查询页“" + pageNumber + "”");
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			Page page = new Page();
			page.setCurrentPage(pageNumber);
			SysUser user = sysUserService.getAllUserByAccount(account);

			Map<String, Object> conds = new HashMap<String, Object>();

			conds.put("lendUserId", user.getUserId());
			conds.put("loanStatusId", 5); // 5表示未放贷
			conds.put("customerInfo", input);

			// 列表数据
			PageUtil<PadLoan> list = lnLoanService
					.getPadLoanByPage(conds, page);

			PadLoanPageList padLoanPageList = new PadLoanPageList();

			List<BaseLoanListInfo> plist = new ArrayList<BaseLoanListInfo>();

			for (PadLoan padLond : list.getItems()) {
				plist.add(new NeedLendingLoanListInfo(padLond));
			}

			padLoanPageList.setDataList(plist);

			padLoanPageList.setPageCount(list.getPage().getTotalRowsAmount());

			String result = JSONArray.fromObject(padLoanPageList, jsonConfig)
					.toString();

			logger.info("pad端贷款接口getLendingLoanList完成，当前登录用户为“" + account
					+ "”,客户相关的查询条件“" + input + "”,当前查询页“" + pageNumber + "”");
			return result;
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % getLendingLoanList", e);
			return null;
		}
	}

	/**
	 * 贷后管理数据列表 NeedCheckoutLoanListInfo TODO
	 *
	 * @param account
	 *            用户账号
	 * @param input
	 *            搜索条件(客户姓名/联系电话/身份证号)
	 * @param pageNumber
	 *            当前显示页
	 *
	 * @param           resultRepayDate 0,1,2,3,4,5 还款期日
	 *                                 所有，5号，10号，15号，20号，25号
	 * @param           date 0,1,2,3,4,5离放款时间
	 * 						所有，一个月内，3个月内，半年内，一年内，一年外
	 * @param           notMonitoredType  未完成监控类型：首次监控、常规监控、异常监控、半年监控 （1，2，3，4）
	 * @return
	 * @author zhangfp
	 */
	public String getNeedCheckoutLoanList(String account, String input,Integer pageNumber,Integer appLoanTypeId,Integer date,Integer notMonitoredType) {
		try {
			logger.info("pad端开始查找贷后贷款列表,当前登录用户为“" + account + "”,客户相关查询条件为“"
					+ input + "”,列表当前查询页为“" + pageNumber + "”");
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());

			Page page = new Page();
			page.setCurrentPage(pageNumber);

			SysUser user = sysUserService.getAllUserByAccount(account);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// 是否是业务主管
			String belongUserIds = "";

			List<Integer> roles = sysRoleMemberService.getRoleIdByUserIds(user
					.getUserId() + "");
			boolean isInChargeOf = false;
			for (Integer roleId : roles) {
				if (roleId.intValue() == 5 || roleId.intValue() == 6) {
					isInChargeOf = true;
					break;
				}
			}
			if (isInChargeOf) {
				// 当前用户的团队底下的成员
				List<String> belongUserId = sysTeamUserService
						.getUserIdsByChiefUserId(user.getUserId());
				int i = 0;
				for (String str : belongUserId) {
					if (i == 0) {
						belongUserIds += str;
						i = 1;
					} else {
						belongUserIds += "," + str;
					}
				}
				paramMap.put("belongUserIds", belongUserIds); // 当前用户所管理的提交申请用户
			} else {

				int role = roles.get(0);
				if (role == 7) { // 客户经理
					paramMap.put("surveyUserId", user.getUserId());
				} else if (role == 4) {
					paramMap.put("approveBackerUserId", user.getUserId());
				}
			}


			// 只显示自己负责的落实贷款
			//paramMap.put("lendUserId", user.getUserId());
			paramMap.put("loanStatusId", "6,7");
			paramMap.put("loanIsCheckout", 0);
			paramMap.put("customer", input);
//			if(resultRepayDate==1){
//				paramMap.put("resultRepayDate", "5");
//			}else if (resultRepayDate ==2){
//				paramMap.put("resultRepayDate", "10");
//			}else if (resultRepayDate ==3){
//				paramMap.put("resultRepayDate", "15");
//			}else if (resultRepayDate ==4){
//				paramMap.put("resultRepayDate", "20");
//			}else if (resultRepayDate ==5){
//				paramMap.put("resultRepayDate", "25");
//			}

			Calendar curDate = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			if(date==1){
				curDate.add(Calendar.MONTH, -1);
				paramMap.put("AfterDateTag", 1);
				paramMap.put("contractCheckDate", dateFormat.format(curDate.getTime()));
			}else if (date==2){
				curDate.add(Calendar.MONTH, -3);
				paramMap.put("AfterDateTag", 1);
				paramMap.put("contractCheckDate", dateFormat.format(curDate.getTime()));
			}else if (date==3){
				curDate.add(Calendar.MONTH, -6);
				paramMap.put("AfterDateTag", 1);
				paramMap.put("contractCheckDate", dateFormat.format(curDate.getTime()));
			}else if (date==4){
				curDate.add(Calendar.MONTH, -12);
				paramMap.put("AfterDateTag", 1);
				paramMap.put("contractCheckDate", dateFormat.format(curDate.getTime()));
			}else if (date==5){
				curDate.add(Calendar.MONTH, -12);
				paramMap.put("AfterDateTag", 0);
				paramMap.put("contractCheckDate", dateFormat.format(curDate.getTime()));
			}
			//区分经营贷（1）和消费贷（2），不选默认为0
			if(appLoanTypeId!=0&&appLoanTypeId!=-1) {
				paramMap.put("appLoanTypeId", appLoanTypeId);
			}
			//筛选未完成的监控类型 notMonitoredType 未完成监控类型：首次监控、常规监控、异常监控、半年监控 （1，2，3，4）
			if(notMonitoredType!=0&&notMonitoredType!=-1){
				paramMap.put("notMonitoredType",notMonitoredType);
			}


			// 列表数据
			PageUtil<LnLoan> dataList = lnLoanService.getMakeLoanPage(paramMap,
					page);

			PadLoanPageList padLoanPageList = new PadLoanPageList();

			List<BaseLoanListInfo> plist = new ArrayList<BaseLoanListInfo>();

			for (LnLoan lnLoan : dataList.getItems()) {
				BaseLoanListInfo baseLoanListInfo = new BaseLoanListInfo(lnLoan);
				PadLoanInfo padLoanInfo = lnLoanInfoService.getPanLoanInfoById(lnLoan.getLoanId());
				baseLoanListInfo.setResultMoney(padLoanInfo.getResultMoney());
				baseLoanListInfo.setResultLimitYear(padLoanInfo.getResultLimitYear());
				baseLoanListInfo.setResultRate(padLoanInfo.getResultRate());
				baseLoanListInfo.setResultRepayWayId(lnLoanInfoDictionaryService.getDictionaryValue("HKFS", padLoanInfo.getResultRepayWayId()));
				baseLoanListInfo.setResultPurpose(lnLoanInfoDictionaryService.getDictionaryValue("DKYT",padLoanInfo.getResultPurpose()));

				plist.add(baseLoanListInfo);

			}

			padLoanPageList.setDataList(plist);

			padLoanPageList.setPageCount(dataList.getPage()
					.getTotalRowsAmount());

			String result = JSONArray.fromObject(padLoanPageList, jsonConfig)
					.toString();

			logger.info("pad端完成查找贷后贷款列表,当前登录用户为“" + account + "”,客户相关查询条件为“"
					+ input + "”,列表当前查询页为“" + pageNumber + "”");
			return result;
		} catch (Exception e) {
			logger.error("RecordWebServicesImpl % getNeedCheckoutLoanList", e);
			return null;
		}
	}

	/**
	 * 贷款催收数据列表 getNeedRepaymentLoanList
	 *
	 * @param account
	 *            用户账号
	 * @param input
	 *            搜索条件(客户姓名/联系电话/身份证号)
	 * @param repaymentStatus
	 *            还款状态
	 * @param pageNumber
	 *            当前显示页
	 * @return
	 * @author zhangfp
	 */
	public String getNeedRepaymentLoanList(String account, String input,
										   Integer repaymentStatus, Integer pageNumber) {
		try {
			logger.info("pad端开始查找催收贷款列表,当前登录用户为“" + account + "”,客户相关查询条件为“"
					+ input + "”,列表当前查询页为“" + pageNumber + "”,还款状态为“"
					+ repaymentStatus + "”");
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());

			Page page = new Page();
			page.setCurrentPage(pageNumber);

			SysUser user = sysUserService.getAllUserByAccount(account);
			// 贷款类型
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// 是否是业务主管
			String belongUserIds = "";

			List<Integer> roles = sysRoleMemberService.getRoleIdByUserIds(user
					.getUserId() + "");
			boolean isInChargeOf = false;
			for (Integer roleId : roles) {
				if (roleId.intValue() == 5 || roleId.intValue() == 6) {
					isInChargeOf = true;
					break;
				}
			}
			if (isInChargeOf) {
				// 当前用户的团队底下的成员
				List<String> belongUserId = sysTeamUserService
						.getUserIdsByChiefUserId(user.getUserId());
				int i = 0;
				for (String str : belongUserId) {
					if (i == 0) {
						belongUserIds += str;
						i = 1;
					} else {
						belongUserIds += "," + str;
					}
				}
				paramMap.put("belongUserIds", belongUserIds); // 当前用户所管理的提交申请用户
			} else {

				int role = roles.get(0);
				if (role == 7) { // 客户经理
					paramMap.put("surveyUserId", user.getUserId());
				} else if (role == 4) {
					paramMap.put("approveBackerUserId", user.getUserId());
				}
			}

			paramMap.put("customer", input);
			if (repaymentStatus != null && repaymentStatus != -1) {
				paramMap.put("status", repaymentStatus);
			}
			paramMap.put("loanStatusId", 6);
			paramMap.put("isNogood", 0); // 排除异常催收情况

			// 默认之后3天显示
			Date repaymentStartDate = org.apache.poi.ss.usermodel.DateUtil
					.parseYYYYMMDDDate(DateUtil.getDateToString(new Date()));
			Date repaymentEndDate = org.apache.commons.lang.time.DateUtils
					.addDays(
							repaymentStartDate,
							sysParamService.getMaxDunLoanTime() != null ? Integer
									.parseInt(sysParamService
											.getMaxDunLoanTime()) : 3);
			paramMap.put("repaymentStartDate", repaymentStartDate);
			repaymentEndDate = lnLoanService.addSecondsForDate(
					repaymentEndDate, 59);
			paramMap.put("repaymentEndDate", repaymentEndDate);
			// 未还款
			paramMap.put("isPaidTag", "0");

			// 列表数据
			PageUtil<LnLoan> dataList = lnLoanService.getMakeLoanPage(paramMap,
					page);
			PadLoanPageList padLoanPageList = new PadLoanPageList();

			List<BaseLoanListInfo> plist = new ArrayList<BaseLoanListInfo>();

			for (LnLoan lnLoan : dataList.getItems()) {
				plist.add(new BaseLoanListInfo(lnLoan));
			}

			padLoanPageList.setDataList(plist);

			padLoanPageList.setPageCount(dataList.getPage()
					.getTotalRowsAmount());

			String result = JSONArray.fromObject(padLoanPageList, jsonConfig)
					.toString();
			logger.info("pad端完成查找催收贷款列表,当前登录用户为“" + account + "”,客户相关查询条件为“"
					+ input + "”,列表当前查询页为“" + pageNumber + "”,还款状态为“"
					+ repaymentStatus + "”");
			return result;
		} catch (Exception e) {
			logger.error("RecordWebServicesImpl % getNeedRepaymentLoanList", e);
			return null;
		}
	}

	/**
	 * 异常催收贷款列表 getExceptionDunLoanList TODO
	 *
	 * @param account
	 *            用户账号
	 * @param input
	 *            搜索条件(客户姓名/联系电话/身份证号)
	 * @param pageNumber
	 *            当前显示页
	 * @return
	 * @author zhangfp
	 */
	public String getExceptionDunLoanList(String account, String input,
										  Integer pageNumber) {

		try {
			logger.info( "pad端开始查找异常催收贷款列表,当前登录用户为“" + account + "”,客户相关查询条件为“"
					+ input + "”,列表当前查询页为“" + pageNumber + "”");
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			Page page = new Page();
			page.setCurrentPage(pageNumber);
			SysUser user = sysUserService.getAllUserByAccount(account);
			// 贷款类型
			Map<String, Object> conds = new HashMap<String, Object>();
			// 是否是业务主管
			String belongUserIds = "";

			List<Integer> roles = sysRoleMemberService.getRoleIdByUserIds(user
					.getUserId() + "");
			boolean isInChargeOf = false;
			for (Integer roleId : roles) {
				if (roleId.intValue() == 5 || roleId.intValue() == 6) {
					isInChargeOf = true;
					break;
				}
			}
			if (isInChargeOf) {
				// 当前用户的团队底下的成员
				List<String> belongUserId = sysTeamUserService
						.getUserIdsByChiefUserId(user.getUserId());
				int i = 0;
				for (String str : belongUserId) {
					if (i == 0) {
						belongUserIds += str;
						i = 1;
					} else {
						belongUserIds += "," + str;
					}
				}
				conds.put("belongUserIds", belongUserIds); // 当前用户所管理的提交申请用户
			} else {

				int role = roles.get(0);
				if (role == 7) { // 客户经理
					conds.put("surveyUserId", user.getUserId());
				} else if (role == 4) {
					conds.put("approveBackerUserId", user.getUserId());
				}
			}

			conds.put("loanStatusId", 6); // 6表示还款中
			conds.put("customer", input);
			conds.put("isConfirm", "isConfirm");
			conds.put("isNogood", 0);
			conds.put("loanStatusId", 6);
			conds.put("isDun", "isDun");
			// 未还款
			conds.put("isPaidTag", "0");
			PageUtil<LnLoan> dataList = lnLoanService.getMakeExLoanPage(conds,
					page);
			PadLoanPageList padLoanPageList = new PadLoanPageList();

			List<BaseLoanListInfo> plist = new ArrayList<BaseLoanListInfo>();

			for (LnLoan lnLoan : dataList.getItems()) {
				plist.add(new BaseLoanListInfo(lnLoan));
			}

			padLoanPageList.setDataList(plist);

			padLoanPageList.setPageCount(dataList.getPage()
					.getTotalRowsAmount());

			String result = JSONArray.fromObject(padLoanPageList, jsonConfig)
					.toString();
			logger.info("pad端完成查找异常催收贷款列表,当前登录用户为“" + account + "”,客户相关查询条件为“"
					+ input + "”,列表当前查询页为“" + pageNumber + "”");
			return result;
		} catch (Exception e) {
			logger.error("ExceptionDunLoanList", e);
			return null;
		}

	}

	/**
	 * 获取不同状态下贷款总数
	 *
	 * @param account
	 * @return
	 */
	public String getStatusLoanCount(String account) {
		logger.info("pad端开始获取各贷款状态下的贷款数量，当前登录用户为“" + account + "”");
		try {
			SysUser user = sysUserService.getAllUserByAccount(account);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			Map<String, Object> resultMap = new HashMap<String, Object>();

			// 贷款申请列表数量---------------------------------------------------------------------------------
			paramMap.clear();
			paramMap.put("createUserId", user.getUserId());
			paramMap.put("loanStatusId", 1);
			resultMap.put("submit",
					lnLoanService.getPadLoanCountByLoanStatus(paramMap));
			// 贷款分配列表数量---------------------------------------------------------------------------------
			paramMap.clear();
			paramMap.put("loanStatusId", 2);
			paramMap.put("assignUserId", user.getUserId());
			resultMap.put("distribute",
					lnLoanService.getPadLoanCountByLoanStatus(paramMap));
			// 贷款调查列表数量---------------------------------------------------------------------------------
			paramMap.clear();
			paramMap.put("surveyUserId", user.getUserId());
			paramMap.put("loanStatusId", 3);
			resultMap.put("survey",
					lnLoanService.getPadLoanCountByLoanStatus(paramMap));
			// 贷款审批列表数量---------------------------------------------------------------------------------
			paramMap.clear();
			paramMap.put("approveUserId", user.getUserId());
			paramMap.put("loanStatusId", 4);
			resultMap.put("approval",
					lnLoanService.getPadLoanCountByLoanStatus(paramMap));
			// 待放贷贷款列表数量--------------------------------------------------------------------------------
			paramMap.clear();
			//paramMap.put("lendUserId", user.getUserId());
			paramMap.put("loanStatusId", 5);
			resultMap.put("lending",
					lnLoanService.getPadLoanCountByLoanStatus(paramMap));
			// 待落实贷款列表数量(瑞丰没有这个列表)--------------------------------------------------------------------
			resultMap.put("execute", 0);
			// 贷后管理列表数量  ----------------------------------------------------------------------------------
			paramMap.clear();
			//数据权限 
			paramMap.putAll(this.setUserRole(user.getUserId()));
			paramMap.put("lendUserId", user.getUserId());
			paramMap.put("loanStatusId", "6,7");
			paramMap.put("loanIsCheckout", 0);
			int refund = lnLoanService.getMakeLoanCount(paramMap);

			resultMap.put("refund", refund);// lnLoanService.getPadLoanCountByLoanStatus(paramMap));
			// 贷款催收列表数量----------------------------------------------------------------------------------
			paramMap.clear();
			paramMap.putAll(this.setUserRole(user.getUserId()));
			paramMap.put("loanStatusId", 6);
			paramMap.put("isNogood", 0); // 排除异常催收情况

			// 默认之后3天显示
			Date repaymentStartDate = org.apache.poi.ss.usermodel.DateUtil
					.parseYYYYMMDDDate(DateUtil.getDateToString(new Date()));
			Date repaymentEndDate = org.apache.commons.lang.time.DateUtils
					.addDays(
							repaymentStartDate,
							sysParamService.getMaxDunLoanTime() != null ? Integer
									.parseInt(sysParamService
											.getMaxDunLoanTime()) : 3);
			paramMap.put("repaymentStartDate", repaymentStartDate);
			repaymentEndDate = lnLoanService.addSecondsForDate(
					repaymentEndDate, 59);
			paramMap.put("repaymentEndDate", repaymentEndDate);
			// 未还款
			paramMap.put("isPaidTag", "0");
			int refundCollection = lnLoanService.getMakeLoanCount(paramMap);
			resultMap.put("refundCollection", refundCollection);// lnLoanService.getPadLoanCountByLoanStatus(paramMap));
			// 异常催收列表数量 ----------------------------------------------------------------------------------
			paramMap.clear();
			paramMap.putAll(this.setUserRole(user.getUserId()));
			paramMap.put("loanStatusId", 6); // 6表示还款中
			paramMap.put("isConfirm", "isConfirm");
			paramMap.put("isNogood", 0);
			paramMap.put("loanStatusId", 6);
			paramMap.put("isDun", "isDun"); // 未还款
			paramMap.put("isPaidTag", "0");
			int unusualCollection = lnLoanService.getMakExloanCont(paramMap);
			resultMap.put("unusualCollection", unusualCollection);// lnLoanService.getPadLoanCountByLoanStatus(paramMap));
			// 所有贷款列表数量----------------------------------------------------------------------------------
			paramMap.clear();
			paramMap.put("curUserId", user.getUserId()); // 当前登陆用户
			paramMap.put("userRole",
					sysUserService.getRoleByUserId(user.getUserId())); // 获取用户角色
			resultMap.put("lending",
					lnLoanService.getPadLoanCountByLoanStatus(paramMap));
			// ********************************************************************************************
			String result = JSONArray.fromObject(resultMap).toString();

			logger.info("pad端完成获取各贷款状态下的贷款数量，当前登录用户为“" + account + "”");
			return result;
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % getStatusLoanCount", e);
			return "false";
		}
	}


	private Map<String, Object> setUserRole(int userId){
		Map<String, Object> paramMap = new HashMap<String,Object>();
		List<Integer> roles = sysRoleMemberService.getRoleIdByUserIds(userId + "");
		String belongUserIds = "";
		boolean isInChargeOf = false;
		for (Integer roleId : roles) {
			if (roleId.intValue() == 5 || roleId.intValue() == 6) {
				isInChargeOf = true;
				break;
			}
		}
		if (isInChargeOf) {
			// 当前用户的团队底下的成员
			List<String> belongUserId = sysTeamUserService
					.getUserIdsByChiefUserId(userId);
			int i = 0;
			for (String str : belongUserId) {
				if (i == 0) {
					belongUserIds += str;
					i = 1;
				} else {
					belongUserIds += "," + str;
				}
			}
			paramMap.put("belongUserIds", belongUserIds); // 当前用户所管理的提交申请用户
		} else {

			int role = roles.get(0);
			if (role == 7) { // 客户经理
				paramMap.put("surveyUserId", userId);
			} else if (role == 4) {
				paramMap.put("approveBackerUserId", userId);
			}
		}

		return paramMap;
	}
	/**
	 * 系统验证该客户在三个月内有其他贷款申请，并且状态为审批失败
	 *
	 * @param account
	 * @param customerId
	 * @return
	 */
	public String isLoanFail(String account, Integer customerId) {
		return "false";
	}

	/**
	 * 贷款保存前 检查客户是否可以贷款(瑞丰版本，去掉限制)
	 *
	 * @param account
	 * @param loanType
	 * @param cusIdType
	 * @param cusIdCard
	 * @return
	 */
	@Override
	public String checkCustomer(String account, String loanType, String cusIdType, String cusIdCard) {
		JsonConfig jsonConfig = new JsonConfig();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("requestResult", "true");
		map1.put("failReason", "");
		return JSONArray.fromObject(map1, jsonConfig).toString();
	}

	/**
	 * 获取预生成贷款ID
	 */
	public Integer getNewLoanId() {
		try {
			return lnLoanService.getNextLoanId();
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % getNewLoanId", e);
			return null;
		}
	}

	/**
	 * 获取贷款类型
	 *
	 * @param account
	 * @return
	 */
	public String getLoanType(String account) {
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("dictionaryName", "DKLX");
			// 贷款类型*******************************
			List<LnLoanInfoDictionary> baseList = lnLoanInfoDictionaryService
					.selectLoanInfoDictionaryList(param);

			List<PadLoanType> padLoanTypeList = new ArrayList<PadLoanType>();
			for (LnLoanInfoDictionary lnLoanType : baseList) {
				PadLoanType loanType = this.loanTypeToPadLoanType(lnLoanType);
				padLoanTypeList.add(loanType);
			}

			// 贷款子类*******************************
			List<LnLoanSubType> loanSubTypeList = lnLoanSubTypeService.getLnLoanSubTypes();

			List<PadLoanSubType> padLoanSubTypeList = new ArrayList<PadLoanSubType>();
			if(!CollectionUtils.isEmpty(loanSubTypeList)){
				PadLoanSubType object ;
				for(LnLoanSubType subType :loanSubTypeList ){
					object = new PadLoanSubType();
					object.setLoanTypeId(subType.getLoanTypeId());
					object.setLoanSubTypeId(subType.getLoanSubTypeId());
					object.setLoanSubTypeName(subType.getLoanSubTypeName());
					padLoanSubTypeList.add(object);
				}
			}

			// 返回对象*******************************
			PadLoanTypePage padLoanType = new PadLoanTypePage();
			padLoanType.setLoanTypeList(padLoanTypeList);
			padLoanType.setLoanSubTypeList(padLoanSubTypeList);

			// 序列化转JSON *******************************
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());

			String result = JSONArray.fromObject(padLoanType, jsonConfig)
					.toString();
			return result;
		} catch (Exception e) {
			logger.error("RecordWebServicesImpl % getLoanType", e);
			return null;
		}
	}

	/**
	 * 将LnLoanType转换成PadLoanType
	 *
	 * @param lnLoanType
	 * @return
	 */
	private PadLoanType loanTypeToPadLoanType(LnLoanInfoDictionary lnLoanType) {
		PadLoanType object = new PadLoanType();
		if (lnLoanType != null) {
			object.setLoanTypeId(Integer.parseInt(lnLoanType.getDictionaryKey()));
			object.setLoanTypeName(lnLoanType.getDictionaryValue());
		}
		return object;
	}

	/**
	 * 贷款新增
	 *
	 * @param account
	 *            用户账号
	 * @param loanJsonString
	 *            添加的json数据
	 * @return 当前添加贷款ID
	 */
	public String addLoan(String account, String loanJsonString) {
		try {
			logger.info("pad端开始贷款新增,当前登录用户为“" + account + "”,新增贷款数据为“"
					+ loanJsonString + "”");
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			JSONObject jsonObject = JSONObject.fromObject(loanJsonString,
					jsonConfig);
			PadLoanAdd padLoanAdd = (PadLoanAdd) JSONObject.toBean(jsonObject,
					PadLoanAdd.class);

			SysUser user = sysUserService.getAllUserByAccount(account);
			// 贷款
			LnLoan lnLoan = new LnLoan();
			LnLoanInfo lnLoanInfo = new LnLoanInfo();

			lnLoan.setLoanId(padLoanAdd.getLoanId());
			lnLoan.setCreateUser(user.getUserId());
			lnLoan.setApplyUserId(user.getUserId());
			lnLoan.setCreateDate(Calendar.getInstance().getTime());
			lnLoan.setLoanStatusId(1);
			lnLoan.setEventId(1);

			lnLoanInfo.setLoanId(padLoanAdd.getLoanId());
			lnLoanInfo.setCustomerId(padLoanAdd.getCustomerId());
			lnLoanInfo.setAppLoanTypeId(padLoanAdd.getLoanTypeId());
			lnLoanInfo.setAppLoanSubTypeId(padLoanAdd.getLoanSubTypeId());
			CrmCustomer crmCustomer = (CrmCustomer) crmCustomerService
					.getCrmCustomerById(padLoanAdd.getCustomerId());
			lnLoanInfo.setCusIdcard(crmCustomer.getIdCard());
			if (crmCustomer.getCredentialTypeId() == null) {
				lnLoanInfo.setCusIdtypeId("1");
			} else {
				lnLoanInfo.setCusIdtypeId(crmCustomer.getCredentialTypeId()
						.toString());
			}
			lnLoanInfo.setCusMobilePhone(crmCustomer.getMobilePhone1());
			lnLoanInfo.setCusName(crmCustomer.getCustomerName());
			lnLoanInfo.setCusSex(crmCustomer.getSex());

			// 插入贷款历史操作记录
			LnOpHistory lnOpHistory = new LnOpHistory();
			lnOpHistory.setUserId(user.getUserId());
			lnOpHistory.setOpHistoryDate(new Date());
			lnOpHistory.setAfterStatusId(1); // 待提交
			lnOpHistory.setContent("新建贷款_手机");
			lnOpHistory.setLoanId(padLoanAdd.getLoanId());

			lnLoanService.addLoanForPad(lnLoan, lnLoanInfo, lnOpHistory);
			logger.info("pad端完成贷款新增,当前登录用户为“" + account + "”,新增贷款数据为“"
					+ loanJsonString + "”");
			return padLoanAdd.getLoanId() + "";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % addLoan", e);
			return null;
		}
	}

	/**
	 * 贷款编辑
	 *
	 * @param account
	 *            用户账号
	 * @param loanJsonString
	 *            传入的json数据
	 * @return
	 */
	public String editLoan(String account, String loanJsonString) {
		try {
			logger.info("pad端开始编辑贷款,当前登录用户为“" + account + "”,编辑贷款数据为“"
					+ loanJsonString + "”");
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			JSONObject jsonObject = JSONObject.fromObject(loanJsonString,
					jsonConfig);
			PadLoanAdd padLoanAdd = (PadLoanAdd) JSONObject.toBean(jsonObject,
					PadLoanAdd.class);

			SysUser user = sysUserService.getAllUserByAccount(account);
			// 贷款

			LnLoanInfo lnLoanInfo = new LnLoanInfo();
			Map<String, Object> loanParamMap = new HashMap<String, Object>();
			loanParamMap.put("loanId", padLoanAdd.getLoanId());
			loanParamMap.put("loanTypeId", padLoanAdd.getLoanTypeId());

			lnLoanInfo.setLoanId(padLoanAdd.getLoanId());
			lnLoanInfo.setCustomerId(padLoanAdd.getCustomerId());
			lnLoanInfo.setAppLoanTypeId(padLoanAdd.getLoanTypeId());
			lnLoanInfo.setAppLoanSubTypeId(padLoanAdd.getLoanSubTypeId());

			// 插入贷款历史操作记录
			LnOpHistory lnOpHistory = new LnOpHistory();
			lnOpHistory.setUserId(user.getUserId());
			lnOpHistory.setOpHistoryDate(new Date());
			lnOpHistory.setAfterStatusId(1); // 待提交
			lnOpHistory.setContent("编辑贷款");
			lnOpHistory.setLoanId(padLoanAdd.getLoanId());

			logger.info("pad端完成编辑贷款,当前登录用户为“" + account + "”,编辑贷款数据为“"
					+ loanJsonString + "”");
			return padLoanAdd.getLoanId() + "";
			/*
			 * SysUser user = sysUserService.getAllUserByAccount(account); //
			 * 编辑贷款 Map<String, Object> loanParamMap = new HashMap<String,
			 * Object>(); loanParamMap.put("loanId", padLoanAdd.getLoanId());
			 * loanParamMap.put("customerId", padLoanAdd.getCustomerId());
			 * loanParamMap.put("loanTypeId", padLoanAdd.getLoanTypeId());
			 * loanParamMap.put("loanSubTypeId", padLoanAdd.getLoanSubTypeId());
			 * loanParamMap.put("loanStatusId", padLoanAdd.getLoanStatusId());
			 * // 贷款金额
			 * 
			 * //String loanMoney = padLoanAdd.getLoanMoney(); //if (loanMoney
			 * != null && !loanMoney.trim().equals("")) { //
			 * loanParamMap.put("loanMoney", stringToBigDecimal(loanMoney)); //}
			 * 
			 * List<LnLoanCoBorrower> coBorrowerList = new
			 * ArrayList<LnLoanCoBorrower>(); JSONArray coBorrowerJsonArray =
			 * (JSONArray) jsonObject.get("coBorrowerList"); int
			 * coBorrowerArrSize = coBorrowerJsonArray.size(); for (int i = 0; i
			 * < coBorrowerArrSize; i++) { JSONObject object = (JSONObject)
			 * coBorrowerJsonArray.get(i); LnLoanCoBorrower coBorrower =
			 * (LnLoanCoBorrower) JSONObject.toBean(object,
			 * LnLoanCoBorrower.class);
			 * coBorrower.setLoanId(padLoanAdd.getLoanId());
			 * coBorrowerList.add(coBorrower); }
			 * 
			 * // 担保人 List<LnLoanGuarantor> guarantorList = new
			 * ArrayList<LnLoanGuarantor>(); JSONArray guarantorJsonArray =
			 * (JSONArray) jsonObject.get("guarantorList"); int guarantorArrSize
			 * = guarantorJsonArray.size(); for (int i = 0; i <
			 * guarantorArrSize; i++) { JSONObject object = (JSONObject)
			 * guarantorJsonArray.get(i); LnLoanGuarantor guarantor =
			 * (LnLoanGuarantor) JSONObject.toBean(object,
			 * LnLoanGuarantor.class);
			 * guarantor.setLoanId(padLoanAdd.getLoanId());
			 * guarantorList.add(guarantor); } //验证当前编辑贷款是新建客户还是更新客户 Boolean
			 * isNew = false; //if
			 * (padLoanAdd.getCustomerId().equals(padLoanAdd.
			 * getOldCustomerId())) { // //更新客户 //} else { //
			 * //新建客户。这样的话，应该将原客户的所有资料归到当前新建的客户中 // isNew = true; //
			 * loanParamMap.put("oldCustomerId", padLoanAdd.getOldCustomerId());
			 * //}
			 * 
			 * lnLoanService.editLoanForPad(loanParamMap, coBorrowerList,
			 * guarantorList, isNew, user.getUserId());
			 * logger.info("pad端完成编辑贷款,当前登录用户为“" + account + "”,编辑贷款数据为“" +
			 * loanJsonString + "”"); return padLoanAdd.getLoanId() + "";
			 */
		} catch (Exception e) {
			logger.error("RecordWebServicesImpl % editLoan", e);
			return null;
		}

	}

	/**
	 * 贷款详情(OK)
	 *
	 * @param account
	 *            用户账号
	 * @param loanId
	 *            贷款ID
	 * @return
	 */
	public String getLoanDetail(String account, Integer loanId) {
		try {
			logger.info("pad端开始查看贷款详情,当前登录用户为“" + account + "”,贷款id为“" + loanId
					+ "”");
			// 贷款
			PadLoan padLoan = lnLoanService.getPanLoanById(loanId);
			PadLoanInfo padLoanInfo = lnLoanInfoService
					.getPanLoanInfoById(loanId);

			if (padLoan != null && padLoanInfo != null) {
				// 查询用条件Map
				Map<String, Object> cond = new HashMap<String, Object>();
				cond.put("loanId", loanId);
				// 贷款详情--------------------------------------------------------------------
				PadLoanDetail loanDetail = new PadLoanDetail();
				// 基本信息--------------------------------------------------------------------
				loanDetail.setLoanId(padLoan.getLoanId()); // 贷款ID
				loanDetail.setLoanStatusId(padLoan.getLoanStatusId()); // 贷款状态ID
				loanDetail.setLoanStatusName(padLoan.getLoanStatusName()); // 贷款状态名称
				loanDetail.setLoanInfoId(padLoanInfo.getLoanInfoId()); // 申请表ID
				if (padLoan.getLoanStatusId() == 1) { // 申请eventId =2
					loanDetail.setEventId(2);
				} else if (padLoan.getLoanStatusId() >= 3) {
					loanDetail.setEventId(padLoan.getLoanStatusId()); // 事件ID
				}

				loanDetail.setCustomerId(padLoan.getCustomerId()); // 客户ID
				loanDetail.setCustomerName(padLoan.getCustomerName()); // 客户姓名
				loanDetail.setCustomerTitle(padLoan.getCustomerTitle()); // 客户称呼
				loanDetail.setPhone(padLoan.getPhone()); // 电话
				loanDetail.setCusIdtypeId(padLoanInfo.getCusIdtypeId()); // 申请人证件类型
				loanDetail.setCancelReason(padLoan.getResultNote());
				// 申请人证件类型名称
				loanDetail.setCusIdtypeName(lnLoanInfoDictionaryService
						.getDictionaryValue("ZJLX",
								padLoanInfo.getCusIdtypeId()));
				loanDetail.setIdCard(padLoanInfo.getCusIdcard()); // 身份证
				// 贷款类型ID// 贷款类型名称
				loanDetail.setLoanTypeId(Integer.parseInt(padLoanInfo.getAppLoanTypeId()));
				loanDetail.setLoanTypeName(lnLoanInfoDictionaryService.getDictionaryValue("DKLX", padLoanInfo.getAppLoanTypeId().toString()));
				// 贷款子类型ID// 贷款子类型名称
				if(StringUtils.isNotBlank(padLoanInfo.getAppLoanSubTypeId())&&StringUtils.isNumeric(padLoanInfo.getAppLoanSubTypeId())){
					loanDetail.setLoanSubTypeId(Integer.parseInt(padLoanInfo.getAppLoanSubTypeId()));
					LnLoanSubType subType = lnLoanSubTypeService.getLnLoanSubTypeById(Integer.valueOf(padLoanInfo.getAppLoanSubTypeId()));
					if(null!=subType&&StringUtils.isNotBlank(subType.getLoanSubTypeName())){
						loanDetail.setLoanSubTypeName(subType.getLoanSubTypeName());
					}
				}

				loanDetail.setLoanMoney(padLoanInfo.getResultMoney()); // 贷款金额(审批决议金额)
				loanDetail.setAccountRemaining("0"); // 账户余额(接口数据) TODO
				loanDetail.setIsReject(padLoan.getIsReject()); // 是否驳回
				loanDetail.setBelongUserId(padLoan.getBelongUserId()); // 当前客户归属

				// adviceResult(调查建议内容)----------------------------------------------------
				if (padLoan.getLoanStatusId() >= 3) {
					setAdviceResult(loanDetail, padLoanInfo, padLoan);
				}
				// approveResult(审批决议内容)---------------------------------------------------
				if (padLoan.getLoanStatusId() >= 4) {
					setApproveResult(loanDetail, padLoanInfo, padLoan);
				}
				// 基础资料列表------------------------------------------------------------------
				setAllBaseDataList(loanDetail);

				// 贷款资料列表-------------------------------------------------------------------
				List<LoanData> dataList = customerDataService
						.getAllLoanDataById(cond);
				for (LoanData da : dataList) {
					if (da.getEventId() == 1) { // 申请eventId =2
						da.setEventId(2);
					}
				}
				this.addLoanDataListIntoLoanDetal(dataList, loanDetail);
				// 陪调人员
				if (padLoanInfo.getTogetherSurveyUserId() != null&& padLoanInfo.getTogetherSurveyUserId() > 0) {
					SysUser sysUser = sysUserService.getSysUserById(padLoanInfo.getTogetherSurveyUserId());
					loanDetail.setTogetherSurveyUserAccont(sysUser.getAccount());
				}

				// 操作历史列表------------------------------------------------------------------
				List<LnOpHistory> hisList = lnOpHistoryService.getAllOpHistoryListByLoanId(loanId);
				this.addLnOpHistoryListIntoLoanDetal(hisList, loanDetail);
				// 共同借款人列表------------------------------------------------------------------
				List<LnLoanCoBorrowerBean> loanCoBorrowerList = lnLoanCoBorrowerService
						.getAllLnLoanCoBorrowerBeanByConds(cond);
				loanDetail.setCoBorrowerList(loanCoBorrowerList);
				// 担保人列表---------------------------------------------------------------------
				List<LnLoanGuarantorBean> loanGuarantorList = lnLoanGuarantorService
						.getAllLnLoanGuarantorBeanByConds(cond);
				loanDetail.setGuarantorList(loanGuarantorList);
				// 还款计划列表-------------------------------------------------------------------
				List<LnRepaymentPlan> repaymentPlanList = lnRepaymentPlanService
						.queryLnRepaymentPlan(loanId);
				loanDetail.setRepaymentPlanList(repaymentPlanList);

				LnRepaymentPlan queryPlan= lnRepaymentPlanService.selectCurLnRepaymentPlanById(loanId);


				if(queryPlan !=null &&queryPlan.getPrincipal() != null && queryPlan.getInterest() != null){
					double needRepay = queryPlan.getPrincipal().doubleValue() + queryPlan.getInterest().doubleValue();
					loanDetail.setNeedRepay(needRepay); //本息合计
				}

				// 抵质押物列表---------------------------------------------------------------------
				List<LnPledge> pledgeList = lnLoanPledgeService
						.getLnLoanPledgeByLoanId(loanId);
				loanDetail.setPledgeList(pledgeList);
				// 信贷历史列表---------------------------------------------------------------------
				List<LnCreditHistory> creditHistoryList = lnCreditHistoryService.getCreditHistoryByLoanId(loanId);
				loanDetail.setCreditHistoryList(creditHistoryList);
				// 催收列表-----------------------------------------------------------------------
				// TODO:

				//异常还款计划

				// JSON序列号---------------------------------------------------------------------
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class,
						new JsonDateValueProcessor());
				String result = JSONObject.fromObject(loanDetail, jsonConfig)
						.toString();
				logger.info("pad端完成查看贷款详情,当前登录用户为“" + account + "”,贷款id为“"
						+ loanId + "”");
				return result;
				// **********************************以上重构完成**********************************
			}
			return "";
		} catch (Exception e) {
			logger.error("RecordWebServicesImpl % getLoanDetail", e);
			e.printStackTrace();
			return "";
		}
	}

	private void setAdviceResult(PadLoanDetail loanDetail,
								 PadLoanInfo padLoanInfo, PadLoan padLoan) {
		Map<String, Object> adviceResult = new HashMap<String, Object>();
		adviceResult.put("adviceMoney", padLoanInfo.getAdviceMoney()); // 建议金额
		adviceResult.put("adviceLimitYear", padLoanInfo.getAdviceLimitYear()); // 建议期限
		adviceResult.put("adviceRate", padLoanInfo.getAdviceRate()); // 建议利率
		adviceResult.put("adviceRepayDate", padLoanInfo.getAdviceRepayDate()); // 建议还款日
		adviceResult.put("adviceLendWayId", padLoanInfo.getAdviceLendWayId()); // 放款方式
		adviceResult.put("adviceLendWayName", lnLoanInfoDictionaryService.getDictionaryValue("FKFS", padLoanInfo.getAdviceLendWayId()));
		adviceResult.put("adviceRepayWayId", padLoanInfo.getAdviceRepayWayId()); // 还款方式
		adviceResult.put("adviceRepayWayName", lnLoanInfoDictionaryService.getDictionaryValue("HKFS", padLoanInfo.getAdviceLendWayId()));
		adviceResult.put("adviceOther", padLoanInfo.getAdviceOther()); // 其他
		adviceResult.put("adviceNote", padLoanInfo.getAdviceNote()); // 调查备注
		adviceResult.put("togetherSurveyUserId",padLoanInfo.getTogetherSurveyUserId());// 陪调人员
		SysUser user = sysUserService.getSysUserById(padLoanInfo.getTogetherSurveyUserId());
		String togetherSurveyUserName = "";
		if (user != null) {
			togetherSurveyUserName = sysUserService.getSysUserById( padLoanInfo.getTogetherSurveyUserId()).getUserName();
		}
		adviceResult.put("togetherSurveyUserName", togetherSurveyUserName);
		adviceResult.put("approveProcessId", padLoan.getApproveProcessId());
		adviceResult.put("approveProcessName", lnLoanInfoDictionaryService.getDictionaryValue("APPROVE_PROCESS", padLoan.getApproveProcessId().toString()));
		adviceResult.put("adviceVerdict", padLoanInfo.getAdviceVerdict());

		loanDetail.setAdviceResult(adviceResult);
	}

	private void setApproveResult(PadLoanDetail loanDetail,
								  PadLoanInfo padLoanInfo, PadLoan padLoan) {
		Map<String, Object> approveResult = new HashMap<String, Object>();
		if (padLoan.getApproveDirectorUserId() != null && padLoan.getApproveDirectorUserId() > 0) {
			approveResult.put("approveType", "spzx");
		} else if (padLoan.getApproveBackerUserId() != null && padLoan.getApproveBackerUserId() > 0) {
			approveResult.put("approveType", "dsh");
		}
		if(null==padLoan.getApproveDirectorPassDate()){
			approveResult.put("isShow", "0");
		}else{
			approveResult.put("isShow", "1");
		}
		if(StringUtils.isNotBlank(padLoanInfo.getResultMoney())){
			approveResult.put("resultMoney",padLoanInfo.getResultMoney());
		}else{
			approveResult.put("resultMoney",padLoanInfo.getAdviceMoney());
		}
		if(StringUtils.isNotBlank(padLoanInfo.getResultLimitYear())){
			approveResult.put("resultLimitYear",padLoanInfo.getResultLimitYear());
		}else{
			approveResult.put("resultLimitYear",padLoanInfo.getAdviceLimitYear());
		}
		if(StringUtils.isNotBlank(padLoanInfo.getResultRate())){
			approveResult.put("resultRate",padLoanInfo.getResultRate());
		}else{
			approveResult.put("resultRate",padLoanInfo.getAdviceRate());
		}
		if(StringUtils.isNotBlank(padLoanInfo.getResultRepayDate())){
			approveResult.put("resultRepayDate",padLoanInfo.getResultRepayDate());
		}else{
			approveResult.put("resultRepayDate",padLoanInfo.getAdviceRepayDate());
		}

		approveResult.put("resultInstalRepayMoney",padLoanInfo.getResultInstalRepayMoney());

		approveResult.put("resultVerdict", padLoanInfo.getResultVerdict());
		if(StringUtils.isNotBlank(padLoanInfo.getResultRepayWayId())&&StringUtils.isNumeric(padLoanInfo.getResultRepayWayId())){
			approveResult.put("resultRepayWayId",padLoanInfo.getResultRepayWayId());
		}else{
			approveResult.put("resultRepayWayId",padLoanInfo.getAdviceRepayWayId());
		}
		approveResult.put("resultPurpose",padLoanInfo.getResultPurpose());
		if(StringUtils.isNotBlank(padLoanInfo.getIsCrmTask())){
			approveResult.put("isCrmTask",padLoanInfo.getIsCrmTask());//是否算任务 1是 0否
		}
		if(StringUtils.isNotBlank(padLoanInfo.getAdvanceRepay())) {
			approveResult.put("advanceRepay",padLoanInfo.getAdvanceRepay());//1同意 0拒绝
		}
		if(StringUtils.isNotBlank(padLoanInfo.getLnMode())) {
			approveResult.put("lnMode",padLoanInfo.getLnMode());//1新增 2转贷
		}
		if(StringUtils.isNotBlank(padLoanInfo.getLnBenefit())) {
			approveResult.put("lnBenefit",padLoanInfo.getLnBenefit());//1是 0否
		}
		approveResult.put("originalRate",padLoanInfo.getOriginalRate());
		approveResult.put("resultNote",padLoanInfo.getResultNote());

		loanDetail.setApproveResult(approveResult);
	}

	private void setAllBaseDataList(PadLoanDetail loanDetail) {
		loanDetail.setFkfs(DictionaryIntoMap("FKFS")); // 放款方式
		loanDetail.setHkfs(DictionaryIntoMap("HKFS")); // 还款方式
		loanDetail.setSdhjy(DictionaryIntoMap("SDHJY")); // 审贷会决议
		loanDetail.setDkyt(DictionaryIntoMap("DKYT")); // 贷款用途
		loanDetail.setApproveProcess(DictionaryIntoMap("APPROVE_PROCESS")); // 审批类型
	}

	/**
	 * 往贷款详情实体中加入资料信息列表数据
	 *
	 * @param dataList
	 * @param loanDetail
	 */
	private void addLoanDataListIntoLoanDetal(List<LoanData> dataList,
											  PadLoanDetail loanDetail) {
		List<PadLoanData> padLoanDataList = new ArrayList<PadLoanData>();
		if (dataList != null && dataList.size() > 0) {
			for (LoanData loanData : dataList) {
				PadLoanData padLoanData = new PadLoanData();
				padLoanData.setCustomerId(loanData.getCustomerId());
				padLoanData.setDataType(loanData.getDataType());
				padLoanData.setPhotoType(loanData.getPhotoTypeId());
				padLoanData.setEventId(loanData.getEventId());
				padLoanData.setDataName(loanData.getDataName());
				padLoanData.setSubmitDate(loanData.getUploadDate());
				padLoanData.setCreateDate(loanData.getRecordDate());
				padLoanData.setUserName(loanData.getUploadUserName());
				padLoanData
						.setUrl(loanData.getFileId() + "&fullPath="
								+ loanData.getFilePath() + "/"
								+ loanData.getFileName());
				padLoanData.setFileName(loanData.getFileName());
				padLoanData.setUuid(loanData.getDatUuid());
				padLoanData.setRemark(loanData.getRemark());
				padLoanData.setAccount(loanData.getUploadUserAccount());
				if (loanData.getIsDel() != null) {
					padLoanData.setIsDel(loanData.getIsDel());
				} else {
					padLoanData.setIsDel(0);
				}
				padLoanData
						.setRecordLength(loanData.getRecordLength() == null ? 0
								: loanData.getRecordLength());

				padLoanDataList.add(padLoanData);
			}
		}
		loanDetail.setDataList(padLoanDataList);
	}

	/**
	 * 往贷款详情实体中加入历史操作列表数据
	 *
	 * @param hisList
	 * @param loanDetail
	 */
	private void addLnOpHistoryListIntoLoanDetal(List<LnOpHistory> hisList,
												 PadLoanDetail loanDetail) {
		List<PadLoanOpHistory> pHistoryList = new ArrayList<PadLoanOpHistory>();
		if (hisList != null && hisList.size() > 0) {
			for (LnOpHistory opHistory : hisList) {
				PadLoanOpHistory pHistory = new PadLoanOpHistory();
				pHistory.setOpHistoryDate(opHistory.getOpHistoryDate());
				pHistory.setAccount(opHistory.getAccount());
				pHistory.setUserName(opHistory.getUserName());
				pHistory.setStatusChangeBefore(opHistory.getBeforeStatusName());
				pHistory.setStatusChangeAfter(opHistory.getAfterStatusName());
				pHistory.setContent(opHistory.getContent());
				pHistory.setRemark(opHistory.getRemark());
				pHistoryList.add(pHistory);
			}
		}
		loanDetail.setOpHistoryList(pHistoryList);
	}

	/**
	 * 查看申请表
	 *
	 * @param account
	 * @param loanId
	 * @return
	 */
	public String getAppForm(String account, Integer loanId) {
		logger.info("pad端开始查看申请表，当前登录用户：" + account + ",贷款主表id：" + loanId);
		try {
			// ****************************************************************************
			PadLoanInfo padLoanInfo = lnLoanInfoService.getPanLoanInfoById(loanId);
			if (padLoanInfo != null) {
				PadLoanAppInfoDetail padLoanAppInfoDetail = new PadLoanAppInfoDetail();
				// 登记信息：RegisterInfo------------------------------------------------------
				padLoanAppInfoDetail.setRegisterInfo(new RegisterInfo(
						padLoanInfo));
				// 申请信息：ApplicationInfo---------------------------------------------------
				padLoanAppInfoDetail.setApplicationInfo(new ApplicationInfo(
						padLoanInfo));
				// 个人信息：PersonalInfo------------------------------------------------------
				padLoanAppInfoDetail.setPersonalInfo(new PersonalInfo(
						padLoanInfo));
				// 家庭信息：FamilyInfo--------------------------------------------------------
				padLoanAppInfoDetail.setFamilyInfo(new FamilyInfo(padLoanInfo));
				// 经营情况：BusinessInfo------------------------------------------------------
				padLoanAppInfoDetail.setBusinessInfo(new BusinessInfo(
						padLoanInfo));
				// 客户签字：SignInfo----------------------------------------------------------
				padLoanAppInfoDetail.setSignInfo(new SignInfo(padLoanInfo));
				// 保存状态：SaveStatusInfo----------------------------------------------------
				padLoanAppInfoDetail.setSaveStatusInfo(new SaveStatusInfo(
						padLoanInfo));
				// 数据字典：------------------------------------------------------------------
				setAllBaseDataList(padLoanAppInfoDetail);
				// 微贷事业中心----------------------------------------------------------------
				getRegisterMicroBizCenter(padLoanAppInfoDetail);
				// 推荐机构-------------------------------------------------------------------

				// JSon序列化-----------------------------------------------------------------
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class,
						new JsonDateValueProcessor());
				String result = JSONArray.fromObject(padLoanAppInfoDetail,
						jsonConfig).toString();
				logger.info("pad端结束查看申请表，当前登录用户：" + account + ",贷款主表id："
						+ loanId);
				return result;
			}
			return null;
		} catch (Exception e) {
			logger.error("getAppForm", e);
			return null;
		}
	}

	/**
	 * 数据字典列表
	 *
	 * @param list
	 */
	private void setAllBaseDataList(PadLoanAppInfoDetail padLoanAppInfoDetail) {
		padLoanAppInfoDetail.setDklx(DictionaryIntoMap("DKLX")); // 贷款类型
		padLoanAppInfoDetail.setDbfs(DictionaryIntoMap("DBFS")); // 担保方式
		padLoanAppInfoDetail.setDy(DictionaryIntoMap("DY")); // 抵押
		padLoanAppInfoDetail.setZy(DictionaryIntoMap("ZY")); // 质押
		padLoanAppInfoDetail.setFlxs(DictionaryIntoMap("FLXS")); // 法律形式
		padLoanAppInfoDetail.setHyzk(DictionaryIntoMap("HYZK")); // 婚姻状况
		padLoanAppInfoDetail.setJycd(DictionaryIntoMap("JYCD")); // 教育程度
		padLoanAppInfoDetail.setJycs(DictionaryIntoMap("JYCS")); // 经营场所
		padLoanAppInfoDetail.setJzcslx(DictionaryIntoMap("JZCSLX")); // 居住场所类型
		padLoanAppInfoDetail.setJzzk(DictionaryIntoMap("JZZK")); // 居住状况
		padLoanAppInfoDetail.setYsrsp(DictionaryIntoMap("YSRSP")); // 月收入水平
		padLoanAppInfoDetail.setZjlx(DictionaryIntoMap("ZJLX")); // 证件类型
		padLoanAppInfoDetail.setZxqk(DictionaryIntoMap("ZXQK")); // 装修情况
		padLoanAppInfoDetail.setZzxs(DictionaryIntoMap("ZZXS")); // 组织形式
	}

	private List<BaseData> DictionaryIntoMap(String type) {
		List<BaseData> baseDataList = new ArrayList<BaseData>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("dictionaryName", type);
		List<LnLoanInfoDictionary> baseList = lnLoanInfoDictionaryService
				.selectLoanInfoDictionaryList(param);
		for (LnLoanInfoDictionary obj : baseList) {
			BaseData baseData = new BaseData();
			baseData.setKey(obj.getDictionaryKey());
			baseData.setValue(obj.getDictionaryValue());
			if (type.equals("DBFS")) {// 担保方式
				if (obj.getDictionaryKey().equals("01")) {
					baseData.setNote("DY");// 有联动下拉框
				} else if (obj.getDictionaryKey().equals("02")) {
					baseData.setNote("ZY");// 有联动下拉框
				} else {
					baseData.setNote("");
				}
			} else {
				baseData.setNote("");
			}
			baseDataList.add(baseData);
		}
		return baseDataList;
	}

	private void getRegisterMicroBizCenter(
			PadLoanAppInfoDetail padLoanAppInfoDetail) {
		List<BaseData> returnObj = new ArrayList<BaseData>();
		List<BaseSysTeam> lTeam = sysTeamService.getBaseSysTeamList();
		for (BaseSysTeam team : lTeam) {
			BaseData data = new BaseData();
			data.setKey(team.getTeamId().toString());
			data.setValue(team.getTeamName());
			returnObj.add(data);
		}
		padLoanAppInfoDetail.setRegisterMicroBizCenterList(returnObj);
	}

	/**
	 * 修改或保存申请信息(调试OK)
	 *
	 * @author 夏亮亮
	 * @param account
	 *            登录用户账号
	 * @param type
	 *            登记信息：RegisterInfo|申请信息：ApplicationInfo|个人信息：PersonalInfo|家庭信息：
	 *            FamilyInfo|经营情况：BusinessInfo|客户签字：SignInfo
	 * @param appFormJson
	 */
	public String saveAppForm(String account, Integer loanId, String type,
							  String appFormJson) {
		logger.info("pad端开始新增修改申请表，当前登录用户：" + account + ",申请表信息appFormJson：" + appFormJson);
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			JSONObject jsonObject = JSONObject.fromObject(appFormJson,
					jsonConfig);

			BaseLnLoanInfo lnLoanInfo = new BaseLnLoanInfo();
			lnLoanInfo = (BaseLnLoanInfo) lnLoanInfoService
					.getPanLoanInfoById(loanId);
			if (lnLoanInfo != null) {
				if (type.equals("RegisterInfo")) { // 登记信息
					RegisterInfo registerInfo = (RegisterInfo) JSONObject
							.toBean(jsonObject, RegisterInfo.class);
					registerInfo.setlnLoanInfo(lnLoanInfo);
				} else if (type.equals("ApplicationInfo")) { // 申请信息
					ApplicationInfo applicationInfo = (ApplicationInfo) JSONObject
							.toBean(jsonObject, ApplicationInfo.class);
					applicationInfo.setlnLoanInfo(lnLoanInfo);
				} else if (type.equals("PersonalInfo")) {// 个人信息
					PersonalInfo personalInfo = (PersonalInfo) JSONObject
							.toBean(jsonObject, PersonalInfo.class);
					personalInfo.setlnLoanInfo(lnLoanInfo);
				} else if (type.equals("FamilyInfo")) {// 家庭信息
					FamilyInfo familyInfo = (FamilyInfo) JSONObject.toBean(
							jsonObject, FamilyInfo.class);
					familyInfo.setlnLoanInfo(lnLoanInfo);
					//配偶信息保存为客户
					if (StringUtils.isNotBlank(familyInfo.getCusMateName()) && StringUtils.isNotBlank(familyInfo.getCusMateIdcard())) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("idCard", familyInfo.getCusMateIdcard());
						map.put("credentialTypeId", 1);
						if (CollectionUtils.isEmpty(crmCustomerService.getCrmCustomerByIdCard(map))) {

							SysUser sysUser = sysUserService.getAllUserByAccount(account);
							Integer userId = 1;
							Integer deptId = 1;
							if (null != sysUser) {
								userId = sysUser.getUserId();
								deptId = sysUser.getDeptId();
							}

							CrmCustomer mateCustomer = new CrmCustomer();
							mateCustomer.setCustomerName(familyInfo.getCusMateName());
							mateCustomer.setCredentialTypeId(1);
							mateCustomer.setIdCard(familyInfo.getCusMateIdcard());
							mateCustomer.setDefaultPhoneType(1);
							mateCustomer.setMobilePhone1(StringUtils.isNotBlank(familyInfo.getCusMateMobilePhone()) ? familyInfo.getCusMateMobilePhone() : "");
							mateCustomer.setCompany(StringUtils.isNotBlank(familyInfo.getCusCompanyName()) ? familyInfo.getCusCompanyName() : "");
							mateCustomer.setPosition(StringUtils.isNotBlank(familyInfo.getCusMateJob()) ? familyInfo.getCusMateJob() : "");
							mateCustomer.setWorkingSeniority(StringUtils.isNotBlank(familyInfo.getCusMateWorkYear()) ? familyInfo.getCusMateWorkYear() : "");
							mateCustomer.setUnitProperty(StringUtils.isNotBlank(familyInfo.getCusCompanyNature()) ? familyInfo.getCusCompanyNature() : "");
							mateCustomer.setBelongDeptId(deptId);
							mateCustomer.setBelongUserId(userId);
							mateCustomer.setCreateUser(userId);
							mateCustomer.setCreateDate(new Date());
							mateCustomer.setIsDel(0);
							mateCustomer.setIsTrash(0);
							crmCustomerService.addCrmCustomer(mateCustomer);
						}
					}
				}else if (type.equals("BusinessInfo")) {// 经营情况
					BusinessInfo businessInfo = (BusinessInfo) JSONObject
							.toBean(jsonObject, BusinessInfo.class);
					businessInfo.setlnLoanInfo(lnLoanInfo);
				} else if (type.equals("SignInfo")) {// 客户签字
					SignInfo signInfo = (SignInfo) JSONObject.toBean(
							jsonObject, SignInfo.class);
					Uploader uplpader = new Uploader();
					String path = uplpader.uploadBase64(lnLoanInfo
							.getLoanInfoId().toString(), signInfo
							.getSignInfoPath());
					signInfo.setSignInfoPath(path);

					signInfo.setlnLoanInfo(lnLoanInfo);
				}
				lnLoanInfoService.updateLnLoanInfo((LnLoanInfo) lnLoanInfo);

				logger.info("pad端结束新增修改申请表，当前登录用户：" + account
						+ ",申请表信息appFormJson：" + appFormJson);
				return "true";
			}
			return "0";
		} catch (Exception e) {
			logger.error("saveAppForm", e);
			return "0";
		}
	}

	/**
	 * 添加信贷记录
	 *
	 * @param account
	 * @param loanId
	 * @param chJson
	 * @return
	 */
	public String addCh(String account, Integer loanId, String chJson) {
		logger.info("pad端开始新增修改申请表，当前登录用户：" + account + "贷款ID：" + loanId
				+ ",申请表信息chJson：" + chJson);
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			JSONObject jsonObject = JSONObject.fromObject(chJson, jsonConfig);
			LnCreditHistory creditHistory = (LnCreditHistory) JSONObject
					.toBean(jsonObject, LnCreditHistory.class);
			creditHistory.setLoanId(loanId);
			lnCreditHistoryService.insertCreditHistory(creditHistory);
			logger.info("pad端结束新增修改申请表，当前登录用户：" + account + "贷款ID：" + loanId
					+ ",申请表信息chJson：" + chJson);
			return "true";
		} catch (Exception e) {
			logger.error("addCh", e);
			return "";
		}
	}

	/**
	 * 修改信贷记录
	 *
	 * @param account
	 * @param loanId
	 * @param chJson
	 * @return
	 */
	public String editCh(String account, Integer loanId, String chJson) {
		logger.info("pad端开始新增修改申请表，当前登录用户：" + account + "贷款ID：" + loanId
				+ ",申请表信息chJson：" + chJson);
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			JSONObject jsonObject = JSONObject.fromObject(chJson, jsonConfig);
			LnCreditHistory creditHistory = (LnCreditHistory) JSONObject
					.toBean(jsonObject, LnCreditHistory.class);
			creditHistory.setUpdateDate(Calendar.getInstance().getTime());
			lnCreditHistoryService.updateCreditHistory(creditHistory);
			logger.info("pad端结束新增修改申请表，当前登录用户：" + account + "贷款ID：" + loanId
					+ ",申请表信息chJson：" + chJson);
			return "true";
		} catch (Exception e) {
			logger.error("editCh", e);
			return "";
		}
	}

	/**
	 * 删除信贷记录
	 *
	 * @param account
	 * @param loanId
	 * @param chId
	 * @return
	 */
	public String delCh(String account, Integer loanId, Integer chId) {
		try {
			lnCreditHistoryService.rmCreditHistory(chId);
			return "true";
		} catch (Exception e) {
			logger.error("delCh", e);
			return "";
		}
	}

	/**
	 * 查看信贷历史
	 */
	public String getChInfo(String account, Integer loanId) {
		logger.info("pad端开始查看信贷历史,当前登录用户为“" + account + "”,贷款id为“" + loanId
				+ "”");
		try {
			List<LnCreditHistory> creditHistoryList = lnCreditHistoryService
					.getCreditHistoryByLoanId(loanId);
			// JSON序列号---------------------------------------------------------------------
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			String result = JSONArray.fromObject(creditHistoryList, jsonConfig)
					.toString();
			logger.info("pad端完成信贷历史,当前登录用户为“" + account + "”,贷款id为“" + loanId
					+ "”");
			if (creditHistoryList != null && creditHistoryList.size() > 0) {
				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("getChInfo", e);
			return null;
		}
	}

	//查询用户
	public String searchUser(String account,Integer loanId){
		logger.info("pad端开始searchUser,当前登录用户为“" + account + "”,贷款id为“" + loanId+ "”");
		try{
			LnLoan lnLoan = lnLoanService.getLnLoanById(loanId);
			Integer applyUserId = lnLoan.getApplyUserId();

			SysUser user = sysUserService.getAllUserByAccount(account);
			List<SysUser> sysUserList = sysUserService.getUserListBelongToSysTeamByUserId(user.getUserId());


			List<Map<String, Object>> userMapList = new ArrayList<Map<String, Object>>();
			for (SysUser Suser : sysUserList){
//        		if(!Suser.getUserId().equals(applyUserId)){
				userMapList.add(this.userToMap(Suser));
//        		}
			}

			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			String result = JSONArray.fromObject(userMapList, jsonConfig).toString();

			logger.info("pad端完成searchUser,当前登录用户为“" + account + "”,贷款id为“" + loanId + "”");
			return result;
		}catch (Exception e) {
			logger.error("searchUser", e);
			return null;
		}
	}

	/**
	 * 获取拒绝原因列表
	 *
	 * @param account
	 * @param type
	 *            银行拒绝：YHJJ|客户拒绝：KHJJ
	 * @return
	 */
	public String getCancelReason(String account, String type) {
		logger.info("pad端开始获取拒绝原因列表，当前登录用户：" + account + ",资料类型：" + type);
		try {
			List<BaseData> listRefuseReason = DictionaryIntoMap(type);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			String result = JSONArray.fromObject(listRefuseReason, jsonConfig)
					.toString();
			logger.info("pad端结束获取拒绝原因列表，当前登录用户：" + account + ",资料类型：" + type);
			return result;
		} catch (Exception e) {
			logger.error("getCancelReason", e);
			return getErrorResult(new PadErrorResult("unknow",
					"getCancelReason", "未知错误"));
		}
	}

	/**
	 * 贷款拒绝(银行拒绝&客户拒绝)
	 *
	 * @param account
	 *            登陆用户
	 * @param loanId
	 *            贷款ID
	 * @param refuseType
	 *            拒绝类型：银行拒绝：YHJJ|客户拒绝：KHJJ
	 * @param refuseReason
	 *            拒绝原因
	 * @param reasonOtherValue
	 *            其他拒绝原因
	 * @return
	 */
	public String refuseLoan(String account, Integer loanId, String refuseType,
							 String refuseReason, String reasonOtherValue) {
		logger.info("pad端开始拒绝贷款，当前登录用户“" + account + "”,贷款id“" + loanId
				+ "”,拒绝大类“" + refuseType + "”,拒绝原因小类“" + refuseReason
				+ "”,其他拒绝原因“" + reasonOtherValue + "”");

		try {
			SysUser user = sysUserService.getAllUserByAccount(account);

			// ------------------------------------------------------------
			LnLoan lnLoan = lnLoanService.selectLoanById(loanId);
			int loanStatusId = lnLoan.getLoanStatusId();
			lnLoan.setLoanRejectUserId(user.getUserId());
			lnLoan.setLoanRejectDate(Calendar.getInstance().getTime());
			lnLoan.setLoanStatusId(loanStatusId + 10);
			// -------------------------------------------------------

			LnLoanInfo lnLoanInfo = new LnLoanInfo();
			lnLoanInfo.setLoanId(loanId);
			lnLoanInfo.setRefuseLoanStateId(loanStatusId + "");

			if (refuseType.equals("YHJJ")) {
				lnLoanInfo.setRefuseReasonTypeId("1");
			} else {
				lnLoanInfo.setRefuseReasonTypeId("2");
			}
			lnLoanInfo.setRefuseReasonSubTypeId(refuseReason);
			lnLoanInfo.setRefuseContent(reasonOtherValue);

			// -------------------------------------------------------------------
			LnOpHistory lnOpHistory = new LnOpHistory();
			lnOpHistory.setLoanId(loanId);
			lnOpHistory.setOpHistoryDate(Calendar.getInstance().getTime());
			lnOpHistory.setUserId(user.getUserId());
			if (refuseType.equals("YHJJ")) {
				lnOpHistory.setContent("银行拒绝");
			} else {
				lnOpHistory.setContent("客户拒绝");
			}
			lnOpHistory.setRemark("");
			lnOpHistory.setBeforeStatusId(loanStatusId);
			lnOpHistory.setAfterStatusId(loanStatusId + 10);
			// --------------------------------------------------------------------
			lnLoanService.submitApproveLoan(lnLoan, lnLoanInfo, lnOpHistory);

			logger.info("pad端结束拒绝贷款，当前登录用户“" + account + "”,贷款id“" + loanId
					+ "”,拒绝大类“" + refuseType + "”,拒绝原因小类“" + refuseReason
					+ "”,其他拒绝原因“" + reasonOtherValue + "”");
			return "true";
		} catch (Exception e) {
			logger.error("refuseLoan", e);
			return getErrorResult(new PadErrorResult("unknow", "refuseLoan",
					"未知错误"));
		}
	}

	/**
	 * 申请拒绝贷款
	 *
	 * @param account
	 *            登录用户账号
	 * @param loanId
	 *            贷款id
	 * @param remark
	 *            备注
	 * @param cancelReasonId
	 *            拒绝原因id
	 * @param cancelReasonOther
	 *            其他拒绝原因
	 * @return
	 */
	public String applyRefuse(String account, Integer loanId, String remark,
							  Integer cancelReasonId, String cancelReasonOther, String refuseType) {
		return refuseLoan(account, loanId, refuseType,
				cancelReasonId.toString(), cancelReasonOther);
	}

	/**
	 * 客户身份验证
	 *
	 * @param account
	 * @param loanId
	 * @return
	 */
	public String checkHistoryInfo(String account, Integer loanId) {
		logger.info("pad端开始客户身份验证，当前登录用户：" + account + ",贷款主表id：" + loanId);
		try {
			PadLoanInfo padLoanInfo = lnLoanInfoService
					.getPanLoanInfoById(loanId);
			// 共同借贷人
			List<LnLoanCoBorrowerBean> loanCoBorrowerList = lnLoanDetailService
					.getLoanCoList(loanId, null);
			// 担保人
			List<LnLoanGuarantorBean> loanGuarantorList = lnLoanDetailService
					.getLoanGuList(loanId, null);

			String cusIds = padLoanInfo.getCustomerId() + "";
			for (int i = 0; i < loanCoBorrowerList.size(); i++) {
				cusIds += "," + loanCoBorrowerList.get(i).getCustomerId();
			}

			for (int i = 0; i < loanGuarantorList.size(); i++) {
				cusIds += "," + loanGuarantorList.get(i).getCustomerId();
			}

			List<CusCheck> cusCheckList = lnLoanService.cusCheck(cusIds,loanId);

			// JSon序列化-----------------------------------------------------------------
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			String result = JSONArray.fromObject(cusCheckList, jsonConfig)
					.toString();
			logger.info("pad端结束客户身份验证，当前登录用户：" + account + ",贷款主表id：" + loanId);
			return result;

		} catch (Exception e) {
			logger.error("checkHistoryInfo", e);
			return "";
		}
	}

	/**
	 * 贷款管理--取得贷款备注列表
	 *
	 * @param account
	 * @param loanId
	 * @return
	 */
	@Override
	public String getLoanRemarkHistory(String account, Integer loanId) {
		logger.info("pad端开始取得贷款备注列表，account：" + account + ",loanId：" + loanId);
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			logger.info("开始查找 取得贷款备注列表");
			if (StringUtils.isNotEmpty(account)) {
				SysUser sysUser = sysUserService.getAllUserByAccount(account);
				if (sysUser != null) {
					List<LnOpHistory> hisList = lnOpHistoryService
							.getAllOpHistoryListByLoanId(loanId);
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					for (LnOpHistory lnOpHistory : hisList) {
						if (lnOpHistory.getRemark() != null
								&& lnOpHistory.getRemark().length() > 0) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("opHistoryDate",
									lnOpHistory.getOpHistoryDate());
							map.put("account", lnOpHistory.getAccount());
							map.put("userName", lnOpHistory.getUserName());
							map.put("statusChangeBefore",
									lnOpHistory.getBeforeStatusName());
							map.put("statusChangeAfter",
									lnOpHistory.getAfterStatusName());
							map.put("content", lnOpHistory.getContent());
							map.put("remark", lnOpHistory.getRemark());
							list.add(map);
						}
					}
					String result = JSONArray.fromObject(list, jsonConfig)
							.toString();
					logger.info("成功找到");
					return result;
				}
				logger.info("没有此客户");
			}
			return null;
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % getLoanRemarkHistory", e);
			return null;
		}
	}

	/**
	 * 申请提交分配贷款
	 *
	 * @param account
	 *            登录用户账号
	 * @param loanId
	 *            贷款ID号
	 * @param remark
	 *            提交贷款时的备注
	 * @return
	 */
	public String applyLoan(String account, Integer assignUserId,
							Integer loanId, String remark) {
		try {
			logger.info("pad端开始申请提交分配贷款，当前登录用户为“" + account + "”,贷款id为“"
					+ loanId + "”,备注为“" + remark + "”");

			SysUser user = sysUserService.getAllUserByAccount(account);
			if(assignUserId==null ||assignUserId.equals(0)){
				List<SysUser> sysUserChiefList = sysUserService
						.getSysUserTeamChiefByUserId(user.getUserId());
				assignUserId = (sysUserChiefList == null || sysUserChiefList.size() <= 0) ? null
						: sysUserChiefList.get(0).getUserId();
			}
			if (assignUserId != null) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("loanId", loanId);
				paramMap.put("assignUserId", assignUserId);
				paramMap.put("loanStatusId", 2);
				paramMap.put("applySubmitDate", Calendar.getInstance()
						.getTime());
				paramMap.put("eventId", 2);
				lnLoanService.updateLnLoanById(paramMap);
				// ----------------------------------------------
				LnOpHistory lnOpHistory = new LnOpHistory();
				lnOpHistory.setLoanId(loanId);
				lnOpHistory.setOpHistoryDate(Calendar.getInstance().getTime());
				lnOpHistory.setUserId(user.getUserId());
				lnOpHistory.setContent("贷款申请提交");
				lnOpHistory.setRemark(remark);
				lnOpHistory.setBeforeStatusId(1);
				lnOpHistory.setAfterStatusId(2);
				lnOpHistoryService.insertLnOpHistory(lnOpHistory);
				// ----------------------------------------------
				logger.info("pad端完成申请提交分配贷款，当前登录用户为“" + account + "”,贷款id为“"
						+ loanId + "”,备注为“" + remark + "”");
				// 申请资料备份
				lnLoanInfoService.bakAppForm(loanId);
				return "true";
			}
			return "fasle";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % applyLoan", e);
			return "false";
		}
	}

	// ************************************************************************
	/**
	 * 贷款调查添加担保人
	 *
	 * @param account
	 * @param loanId
	 * @param guaCustomerId
	 * @param oldGuaCustomerId
	 * @param isKownLoan
	 *            是否知晓贷款
	 * @param isException
	 *            有无异常
	 * @param checkMessage
	 *            软信息核实
	 * @param companyAddress
	 *            经营/单位地址
	 * @param petitionerRelate
	 *            软信息核实
	 * @return
	 */
	public String addGua(String account, Integer loanId, Integer guaCustomerId,
						 Integer oldGuaCustomerId, Integer isKownLoan, Integer isException,
						 String checkMessage, String companyAddress,
						 String petitionerRelate, String company) {
		try {
			logger.info("pad端贷款接口addGua开始，当前登录用户:" + account + ",loanId:"
					+ loanId + ",oldGuaCustomerId:" + oldGuaCustomerId
					+ ",guaCustomerId:" + guaCustomerId);
			SysUser user = sysUserService.getUserByAccount(account);
			CrmCustomer crmCustomer = new CrmCustomer();
			crmCustomer.setCompany(company);
			crmCustomer.setCustomerId(oldGuaCustomerId);
			List<CrmCustomer> customerList = new ArrayList<CrmCustomer>();
			customerList.add(crmCustomer);
			crmCustomerService.updateCustomerBatch(customerList);
			if (oldGuaCustomerId == -1) {
				// 增加担保人
				lnLoanService.addGu(loanId, guaCustomerId, user.getUserId(),
						isKownLoan, isException, checkMessage, companyAddress,
						petitionerRelate);
			} else if (oldGuaCustomerId != -1) {
				lnLoanService.updateGu(loanId, oldGuaCustomerId, guaCustomerId,
						3, user.getUserId(), isKownLoan, isException,
						checkMessage, companyAddress, petitionerRelate);
			}
			logger.info("pad端贷款接口addGua完成，当前登录用户:" + account + ",loanId:"
					+ loanId + ",oldGuaCustomerId:" + oldGuaCustomerId
					+ ",guaCustomerId:" + guaCustomerId);
			return "true";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % addGua", e);
			e.printStackTrace();
			return "false";
		}
	}

	/**
	 * 新建共同借款人
	 *
	 * @param account
	 * @param loanId
	 * @param cobCustomerId
	 * @param oldCobCustomerId
	 * @return
	 */
	@Override
	public String addCob(String account, Integer loanId, Integer cobCustomerId,
						 Integer oldCobCustomerId, Integer isKownLoan, Integer isException,
						 String checkMessage, String compayAddress, String petitionerRelate,
						 String company) {
		try {
			logger.info("pad端贷款接口addCob开始，当前登录用户:" + account + ",loanId:"
					+ loanId + ",cobCustomerId:" + cobCustomerId
					+ ",oldCobCustomerId:" + oldCobCustomerId);
			SysUser user = sysUserService.getUserByAccount(account);
			CrmCustomer crmCustomer = new CrmCustomer();
			crmCustomer.setCompany(company);
			crmCustomer.setCustomerId(cobCustomerId);
			List<CrmCustomer> customerList = new ArrayList<CrmCustomer>();
			customerList.add(crmCustomer);
			crmCustomerService.updateCustomerBatch(customerList);
			if (oldCobCustomerId == -1) {
				// 增加共同借款人
				lnLoanService.addCob(loanId, cobCustomerId, user.getUserId(),
						isKownLoan, isException, checkMessage, compayAddress,
						petitionerRelate);
			} else if (oldCobCustomerId != -1) {
				lnLoanService.updateCob(loanId, oldCobCustomerId,
						cobCustomerId, 3, user.getUserId(), isKownLoan,
						isException, checkMessage, compayAddress,
						petitionerRelate);
			}
			logger.info("pad端贷款接口addGua完成，当前登录用户:" + account + ",loanId:"
					+ loanId + ",cobCustomerId:" + cobCustomerId
					+ ",oldCobCustomerId:" + oldCobCustomerId);
			return "true";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % addCob", e);
			e.printStackTrace();
			return "false";
		}
	}

	/**
	 * 添加抵质押物
	 *
	 * @param pledgeId
	 * @param loanId
	 * @param goods
	 * @param goodsValue
	 * @param ownerName
	 * @param titleCertificate
	 * @see com.banger.mobile.facade.webservice.LoanWebService#addPledge(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	@Override
	public String addPledge(Integer pledgeId, Integer loanId, String goods,
							String goodsValue, String ownerName, String titleCertificate,String pledgeRate,String goodsAmount, String goodsRemark) {
		try {
			LnPledge lnPledge = new LnPledge();
			lnPledge.setGoods(goods);
			lnPledge.setGoodsValue(goodsValue);
			lnPledge.setLoanId(loanId);
			lnPledge.setOwnerName(ownerName);
			lnPledge.setTitleCertificate(titleCertificate);
			lnPledge.setPledgeRate(pledgeRate);
			lnPledge.setGoodsAmount(goodsAmount);
			lnPledge.setGoodsRemark(goodsRemark);
			if (pledgeId == null || pledgeId <= 0) { // 新增抵质押物
				lnLoanPledgeService.insertLnLoanPledge(lnPledge);
			} else { // 更新抵质押物
				lnPledge.setPledgeId(pledgeId);
				lnPledge.setUpdateDate(new Date());
				lnLoanPledgeService.updateLnLoanPledgeById(lnPledge);
			}
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

	/**
	 * 删除抵质押物
	 *
	 * @param pledgeId
	 * @return
	 */
	public String deletePledge(Integer pledgeId) {

		try {
			lnLoanPledgeService.rmLnLoanPledgeById(pledgeId);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

	/**
	 * 查看抵质押物列表
	 *
	 * @param loanId
	 */
	public String getPledgeList(Integer loanId) {
		try {
			List<LnPledge> lnPledgeList = lnLoanPledgeService
					.getLnLoanPledgeByLoanId(loanId);
			if (lnPledgeList != null && lnPledgeList.size() > 0) {
				// JSON序列号---------------------------------------------------------------------
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class,
						new JsonDateValueProcessor());
				String result = JSONArray.fromObject(lnPledgeList, jsonConfig)
						.toString();
				return result;
			}
			return null;
		} catch (Exception e) {
			logger.error("getChInfo", e);
			return null;
		}
	}

	/**
	 * 贷款调查删除担保人
	 *
	 * @param account
	 * @param loanId
	 * @param customerId
	 * @return
	 */
	public String deleteGua(String account, Integer loanId, Integer customerId) {
		try {
			logger.info("pad端贷款接口deleteGua开始，当前登录用户:" + account + ",loanId:"
					+ loanId + ",customerId:" + customerId);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loanId", loanId);
			paramMap.put("customerId", customerId);
			LnLoanGuarantor gu = lnLoanGuarantorService
					.selectGuarantorSingle(paramMap);
			lnLoanService.rmGuarantor(gu.getGuarantorId(), loanId, customerId);
			logger.info("pad端贷款接口deleteGua完成，当前登录用户:" + account + ",loanId:"
					+ loanId + ",customerId:" + customerId);
			return "true";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % deleteGua", e);
			return "false";
		}
	}

	/**
	 * 删除共同借款人
	 *
	 * @param account
	 * @param loanId
	 * @param customerId
	 * @return
	 */
	@Override
	public String deleteCob(String account, Integer loanId, Integer customerId) {
		try {
			logger.info("pad端贷款接口deleteCob开始，当前登录用户:" + account + ",loanId:"
					+ loanId + ",customerId:" + customerId);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loanId", loanId);
			paramMap.put("customerId", customerId);
			LnLoanCoBorrower cob = lnLoanCoBorrowerService
					.seleteCoBorrower(paramMap);
			lnLoanService.removeCob(cob.getLoanCoBorrowerId(), loanId,
					customerId);
			logger.info("pad端贷款接口deleteCob完成，当前登录用户:" + account + ",loanId:"
					+ loanId + ",customerId:" + customerId);
			return "true";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % deleteCob", e);
			return "false";
		}
	}

	/**
	 * 获取当前登录用户可分配下属列表
	 *
	 * @param account
	 * @param loanId
	 * @return
	 */
	public String getAssignUserList(String account, Integer loanId) {
		try {
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			PadLoan padLoan = lnLoanService.getPanLoanById(loanId);
			if (sysUser != null && padLoan != null) {
				List<SysUser> userList = sysUserService
						.getUserListBelongToSysTeamByUserId(sysUser.getUserId());
				List<Map<String, Object>> userMapList = new ArrayList<Map<String, Object>>();
				for (SysUser user : userList) {
					//if (padLoan.getApplyUserId() != user.getUserId()) {
					userMapList.add(this.userToMap(user));
					//}
				}
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class,
						new JsonDateValueProcessor());
				return JSONArray.fromObject(userMapList, jsonConfig).toString();
			}
			return getErrorResult(new PadErrorResult("unknow",
					"getAssignUserList", "未知错误"));
		} catch (Exception e) {
			logger.error("getAssignUserList", e);
			return getErrorResult(new PadErrorResult("unknow",
					"getAssignUserList", "未知错误"));
		}
	}

	/**
	 * 贷款调查陪调
	 *
	 * @param name
	 * @param password
	 * @param account
	 * @param loanId
	 * @return
	 */
	public String togetherSurveySubmit(String name, String password,
									   String account, Integer loanId) {
		try {
			SysUser sysUser = sysUserService.checkNameAndPasswordIsValid(name,
					password);
			if (sysUser == null) {
				return "用户名或密码错误，请重试";
			} else {
				// 判断当前用户是否与当前用户相同
				SysUser user = sysUserService.getAllUserByAccount(account);
				if (sysUser.getUserId().equals(user.getUserId())) {
					return "不能使用当前的用户作为陪调人员！";
				}
				// 更新贷款表加入陪调人员Id
				LnLoanInfo lnLoanInfo = new LnLoanInfo();
				lnLoanInfo.setLoanId(loanId);
				lnLoanInfo.setTogetherSurveyUserId(sysUser.getUserId());
				lnLoanInfo.setTogetherSurveyDate(new Date());
				lnLoanInfoService.updateLnLoanInfo(lnLoanInfo);
				return "true";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "系统异常，退出后重试或请联系管理员！";
		}

	}

	/**
	 * 贷款调查保存
	 *
	 * @param account
	 * @param loanId
	 * @param adviceMoney
	 * @param adviceLimitYear
	 * @param adviceRate
	 * @param adviceRepayDate
	 * @param adviceLendWayId
	 * @param adviceRepayWayId
	 * @param adviceOther
	 * @return
	 */
	public String saveSurveyForm(String account, Integer loanId,
								 String adviceMoney, String adviceLimitYear, String adviceRate,
								 String adviceRepayDate, String adviceLendWayId,
								 String adviceRepayWayId, String adviceOther, String adviceVerdict) {
		try {
			LnLoanInfo lnLoanInfo = new LnLoanInfo();
			lnLoanInfo.setLoanId(loanId);
			lnLoanInfo.setAdviceMoney(adviceMoney);
			lnLoanInfo.setAdviceLimitYear(adviceLimitYear);
			lnLoanInfo.setAdviceRate(adviceRate);
			lnLoanInfo.setAdviceRepayDate(adviceRepayDate);
			lnLoanInfo.setAdviceLendWayId(adviceLendWayId);
			lnLoanInfo.setAdviceRepayWayId(adviceRepayWayId);
			lnLoanInfo.setAdviceOther(adviceOther);
			lnLoanInfo.setAdviceVerdict(adviceVerdict);
			// lnLoanInfoService.updateLnLoanInfoWhitOutNull(lnLoanInfo);
			lnLoanInfoService.updateLnLoanInfo(lnLoanInfo);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}

	private Boolean isDoubleApproval(String appLoanTypeId){
		Boolean returnValue  = false;
		if(appLoanTypeId.equals("1")){//经营贷
			returnValue = sysParamService.getJYDDoubleApprovalTag().equals("0")?false:true;
		}else if (appLoanTypeId.equals("2")){//消费贷款
			returnValue = sysParamService.getXFDDoubleApprovalTag().equals("0")?false:true;
		}else{
			returnValue= true;
		}
		return returnValue;
	}

//	public String editCh(String account, Integer loanId, String chJson) {
//		logger.info("pad端开始新增修改申请表，当前登录用户：" + account + "贷款ID：" + loanId
//				+ ",申请表信息chJson：" + chJson);
//		try {
//			JsonConfig jsonConfig = new JsonConfig();
//			jsonConfig.registerJsonValueProcessor(Date.class,
//					new JsonDateValueProcessor());
//			JSONObject jsonObject = JSONObject.fromObject(chJson, jsonConfig);
//			LnCreditHistory creditHistory = (LnCreditHistory) JSONObject
//					.toBean(jsonObject, LnCreditHistory.class);
//			creditHistory.setUpdateDate(Calendar.getInstance().getTime());
//			lnCreditHistoryService.updateCreditHistory(creditHistory);
//			logger.info("pad端结束新增修改申请表，当前登录用户：" + account + "贷款ID：" + loanId
//					+ ",申请表信息chJson：" + chJson);
//			return "true";
//		} catch (Exception e) {
//			logger.error("editCh", e);
//			return "";
//		}
//	}

	/**
	 * 审批保存
	 *
	 * @param account
	 * @param loanId
	 * @return
	 */
	public String saveResultForm(String account, Integer loanId,String resultNote,String isPass,String isShow,String resultJson) {
		try {
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			JSONObject jsonResult = JSONObject.fromObject(resultJson);
			LnLoanInfo lnLoanInfo = (LnLoanInfo) JSONObject.toBean(jsonResult, LnLoanInfo.class);
//			LnLoanInfo lnLoanInfo = new LnLoanInfo();
			lnLoanInfo.setLoanId(loanId);
			lnLoanInfo.setResultNote(resultNote);
			if(isPass.equals("1")){

				if(isShow.equals("0")){
					Boolean isDoubleApproval = isDoubleApproval(lnLoanInfoService.getAppLoanTypeIdByLoanId(loanId));//是否双人审批
					LnLoan lnLoan = lnLoanService.selectLoanById(loanId);

					LnLoan lnLoanUpdate = new LnLoan();
					lnLoanUpdate.setLoanId(loanId);
					lnLoanUpdate.setApproveDirectorPassDate(new Date());

					if(lnLoan.getApproveProcessId()==1){ //线上审批
						if(lnLoan.getApproveBakMsg() != null && !lnLoan.getApproveBakMsg().trim().equals("")){
							String approveBakMsg = lnLoan.getApproveBakMsg();
							JSONObject jsonObject = JSONObject.fromObject(approveBakMsg);
							LnLoanApproveBakMsg loanApproveBakMsg = (LnLoanApproveBakMsg) JSONObject.toBean(jsonObject, LnLoanApproveBakMsg.class);

							lnLoanUpdate.setApproveCenterUserId1(loanApproveBakMsg.getApproveCenterUserId1());
							lnLoanUpdate.setApproveCenterUserId2(loanApproveBakMsg.getApproveCenterUserId2());

							if(loanApproveBakMsg.getApproveCenterUserId1() <= 0){ //说明当时是从主管驳回
								List<Integer> approveCenterUser = sysUserService.getReadomApproveUserId(isDoubleApproval,lnLoanInfoService.getAdviceMoneyByLoanId(lnLoanInfo.getLoanId()));
								if(approveCenterUser == null || approveCenterUser.size() == 0){
									return("提交失败，审批者人数不够，请增加审批人员！");
								}

								if(isDoubleApproval){//双人审批
									lnLoanUpdate.setApproveCenterUserId1(approveCenterUser.get(0));
									lnLoanUpdate.setApproveCenterUserId2(approveCenterUser.get(1));
								}else{
									lnLoanUpdate.setApproveCenterUserId1(approveCenterUser.get(0));
								}
							}

						}else{
							List<Integer> approveCenterUser = sysUserService.getReadomApproveUserId(isDoubleApproval,lnLoanInfoService.getAdviceMoneyByLoanId(lnLoanInfo.getLoanId()));
							if(approveCenterUser == null || approveCenterUser.size() == 0){
								return("提交失败，审批者人数不够，请增加审批人员！");

							}
							if(isDoubleApproval){
								lnLoanUpdate.setApproveCenterUserId1(approveCenterUser.get(0));
								lnLoanUpdate.setApproveCenterUserId2(approveCenterUser.get(1));
							}else{
								lnLoanUpdate.setApproveCenterUserId1(approveCenterUser.get(0));
							}
						}
					}else{
						int isReject =(lnLoan.getIsReject() == null ? 0 : lnLoan.getIsReject());
						Integer managerUserId = sysUserService.getReadomTeamManagerUserId(sysUser.getUserId(),"approveBackerUserId");
						if(isReject == 1){ //当前调查为驳回
							//引用之前的后台人员编号
							if(lnLoan.getApproveBackerUserId()==null ||lnLoan.getApproveBackerUserId().equals(0)){
								lnLoanUpdate.setApproveBackerUserId(managerUserId);
							}else{
								lnLoanUpdate.setApproveBackerUserId(lnLoan.getApproveBackerUserId());
							}
						}else{

							lnLoanUpdate.setApproveBackerUserId(managerUserId);
						}
					}

					LnOpHistory lnOpHistory = new LnOpHistory();
					lnOpHistory.setUserId(sysUser.getUserId());
					lnOpHistory.setOpHistoryDate(new Date());
					lnOpHistory.setBeforeStatusId(4);
					lnOpHistory.setAfterStatusId(4);
					lnOpHistory.setRemark(resultNote);
					lnOpHistory.setLoanId(Integer.valueOf(loanId));
					lnOpHistory.setContent("主管审批通过");
					lnLoanService.submitApproveLoan(lnLoanUpdate,lnLoanInfo,lnOpHistory);
				}else{

					LnLoan lnLoan = lnLoanService.getLnLoanById(lnLoanInfo.getLoanId());
					lnLoan.setApproveCenterPassDate(new Date());
					lnLoan.setLoanStatusId(5);
					lnLoan.setEventId(5);
					lnLoan.setLendUserId(sysUserService.getReadomTeamManagerUserId(lnLoan.getSurveyUserId(),"lendUserId"));
					LnOpHistory lnOpHistory = new LnOpHistory();
					lnOpHistory.setUserId(sysUser.getUserId());
					lnOpHistory.setOpHistoryDate(new Date());
					lnOpHistory.setBeforeStatusId(4);
					lnOpHistory.setAfterStatusId(5);
					lnOpHistory.setRemark(lnLoanInfo.getResultNote());
					lnOpHistory.setLoanId(lnLoanInfo.getLoanId());
					lnOpHistory.setContent("审批中心通过");

					lnLoanService.submitApproveLoan(lnLoan,lnLoanInfo,lnOpHistory);

				}

				// lnLoanInfoService.updateLnLoanInfoWhitOutNull(lnLoanInfo);
				lnLoanInfoService.updateLnLoanInfo(lnLoanInfo);
				return "true";
			}else if(isPass.equals("0")){

				LnLoan lnLoan = lnLoanService.selectLoanById(loanId);

				if (lnLoan.getApproveProcessId() == 1) { // 审批中心
					if (lnLoan.getApproveCenterUserId1() != null
							&& lnLoan.getApproveCenterUserId1() > 0) {// 已从主管审批交由审批中心
						lnLoan.setApproveCenterRejectDate(new Date());
					} else {
						lnLoan.setApproveDirectorRejectDate(new Date());
					}
				} else { // 贷审会
					lnLoan.setApproveBackerRejectDate(new Date());
				}
				lnLoan.setIsReject(1);


				// 贷款历史操作
				LnOpHistory lnOpHistory = new LnOpHistory();
				lnOpHistory.setLoanId(loanId);
				lnOpHistory.setOpHistoryDate(Calendar.getInstance().getTime());
				lnOpHistory.setUserId(sysUser.getUserId());
				lnOpHistory.setContent("审批驳回拒绝");
				lnOpHistory.setRemark(resultNote);
				lnOpHistory.setBeforeStatusId(4);
				lnOpHistory.setAfterStatusId(3);

				lnLoan.setLoanStatusId(3);
				lnLoan.setEventId(3);
				lnLoanService.submitApproveLoan(lnLoan, lnLoanInfo, lnOpHistory);

				return "true";
			}else{
				lnLoanInfoService.updateLnLoanInfo(lnLoanInfo);
				return "true";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}


	/**
	 * 提交审核
	 *
	 * @see com.banger.mobile.facade.webservice.LoanWebService#submitWaitApprove(java.lang.String,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public String submitWaitApprove(String account, Integer loanId,
									Integer approveProcessId) {
		try {
			LnLoan lnLoan = lnLoanService.selectLoanById(loanId);
			if (lnLoan.getLoanStatusId() != 3) {// 判断当前贷款状态是否满足
				return "当前贷款状态不是为调查状态！无法提交审批！";
			}

			String strApproveBakMag = lnLoan.getApproveBakMsg();
			Integer isReject = lnLoan.getIsReject();
			if(null!=isReject&&isReject == 1){ //当前调查为驳回
				if(lnLoan.getApproveBakMsg() == null||lnLoan.getApproveBakMsg().trim().equals("")){
					strApproveBakMag = lnLoanService.saveApproveDataBakMsg(loanId.toString());
					//lnLoanService.clearApproveDataAndBakMsg(loanId.toString());
				}else{
					JSONObject jsonObject = JSONObject.fromObject(lnLoan.getApproveBakMsg());
					LnLoanApproveBakMsg loanApproveBakMsg = (LnLoanApproveBakMsg) JSONObject.toBean(jsonObject, LnLoanApproveBakMsg.class);
					if(loanApproveBakMsg.getApproveCenterUserId1()==null || loanApproveBakMsg.getApproveCenterUserId1()==0){
						strApproveBakMag = lnLoanService.saveApproveDataBakMsg(loanId.toString());
						//lnLoanService.clearApproveDataAndBakMsg(loanId.toString());
					}
				}
				lnLoanService.clearApproveData(loanId);
				//lnLoanService.clearApproveDataAndBakMsg(loanId.toString());
			}

			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			lnLoan.setIsReject(0);
			lnLoan.setSurveySubmitDate(new Date());
			//------------------------------------------------------------------------------
			lnLoan.setApproveProcessId(approveProcessId);
			Integer directorUserId = sysUserService
					.getSysUserTeamChiefByUserId(sysUser.getUserId())
					.get(0).getUserId();
			lnLoan.setApproveDirectorUserId(directorUserId);
			lnLoan.setSurveySubmitDate(new Date());
			lnLoan.setLoanStatusId(4);
			//------------------------------------------------------------------------------
			/*
			if (approveProcessId == 1) { // 审批中心
				lnLoan.setApproveProcessId(approveProcessId);
				Integer directorUserId = sysUserService
						.getSysUserTeamChiefByUserId(sysUser.getUserId())
						.get(0).getUserId();
				lnLoan.setApproveDirectorUserId(directorUserId);
				lnLoan.setSurveySubmitDate(new Date());
				lnLoan.setLoanStatusId(4);
			} else if (approveProcessId == 2) {// 待审会
				lnLoan.setApproveProcessId(approveProcessId);
				lnLoan.setLoanStatusId(4);
				lnLoan.setSurveySubmitDate(new Date());
				Integer managerUserId = sysUserService.getReadomTeamManagerUserId(sysUser.getUserId(),"approveBackerUserId");				
				if(isReject == 1){ //当前调查为驳回
					if(lnLoan.getApproveBackerUserId()==null ||lnLoan.getApproveBackerUserId().equals(0)){
						lnLoan.setApproveBackerUserId(managerUserId);
					}else{
						lnLoan.setApproveBackerUserId(lnLoan.getApproveBackerUserId());
					}
				}else{
					
					lnLoan.setApproveBackerUserId(managerUserId);
				}
			}
			*/
			// 插入贷款历史操作记录
			lnLoan.setEventId(4);
			LnOpHistory lnOpHistory = new LnOpHistory();
			lnOpHistory.setUserId(sysUser.getUserId());
			lnOpHistory.setOpHistoryDate(new Date());
			lnOpHistory.setBeforeStatusId(3); // 待分配
			lnOpHistory.setAfterStatusId(4); // 待调查
			if (approveProcessId == 1) {
				lnOpHistory.setContent("贷款提交审批(审批中心)_手机");
			}else{
				lnOpHistory.setContent("贷款提交审批(审贷会)_手机");
			}
			lnOpHistory.setRemark(""); // 历史记录备注
			lnOpHistory.setLoanId(loanId);

			lnLoanService.submitApproval(lnLoan, lnOpHistory);
			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "系统异常，请关闭后重试或联系管理员！";
		}

	}

	/**
	 * 将用户信息转换成相应的Map形式
	 *
	 * @param user
	 * @return
	 */
	private Map<String, Object> userToMap(SysUser user) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", user.getUserId());
		paramMap.put("account", user.getAccount());
		paramMap.put("userName", user.getUserName());
		return paramMap;
	}

	/**
	 * 删除贷款
	 */
	public String deleteLoan(String account, Integer loanId) {
		try {
			logger.info("pad端开始册除贷款，当前登录用户为“" + account + "”,贷款id为“" + loanId
					+ "”");
			lnLoanService.deleteLoan(loanId);
			logger.info("pad端完成册除贷款，当前登录用户为“" + account + "”,贷款id为“" + loanId
					+ "”");
			return "true";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % deleteLoan", e);
			return "false";
		}
	}

	/**
	 * 审批驳回贷款(退回到调查)
	 *
	 * @param account
	 *            当前登录用户
	 * @param loanId
	 *            贷款id
	 * @param remark
	 *            审批备注
	 * @param otherAccount
	 *            共同审批用户
	 * @param approveUser1DateStr
	 *            审批人审批时间
	 * @return
	 */
	public String approvalReject(String account, Integer loanId, String remark,
								 String otherAccount, String approveUser1DateStr) {
		logger.info("pad端贷款接口approvalReject开始，当前登录用户:" + account + ",loanId:"
				+ loanId + ",remark:" + remark + ",otherAccount:"
				+ otherAccount + ",approveUser1DateStr:" + approveUser1DateStr);
		try {
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			Integer userId = sysUser.getUserId();
			String userName = sysUser.getUserName();

			// 共同审批人
			SysUser commApproveUser = sysUserService
					.getAllUserByAccount(otherAccount);
			Integer commApproveUserId = commApproveUser.getUserId();
			String commApproveUserName = commApproveUser.getUserName();

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loanId", loanId);
			paramMap.put("loanStatusId", 3); // 驳回调查
			// 主审批人ID
			paramMap.put("approveUserId1", userId);
			// 共同审批人ID
			paramMap.put("approveUserId2", commApproveUserId);
			// 审批备注
			paramMap.put("approveRemark", remark);
			// 主审批人审批驳回时间
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date approvaUserDate = format.parse(approveUser1DateStr);
			paramMap.put("approveRejectDate1", approvaUserDate);
			// 共同审批人审批驳回时间
			paramMap.put("approveRejectDate2", new Date());
			// 被驳回
			paramMap.put("isReject", 1);

			// 插入贷款历史操作记录
			LnOpHistory lnOpHistory = new LnOpHistory();
			lnOpHistory.setUserId(userId);
			lnOpHistory.setOpHistoryDate(new Date());
			lnOpHistory.setBeforeStatusId(4);
			lnOpHistory.setAfterStatusId(3);
			lnOpHistory.setContent("驳回重新调查，审批人" + userName + "，共同审批人"
					+ commApproveUserName);
			lnOpHistory.setRemark(remark);
			lnOpHistory.setLoanId(loanId);

			// 审批通过
			lnLoanService.approveLoan(paramMap, lnOpHistory);
			logger.info("pad端贷款接口approvalReject完成，当前登录用户:" + account
					+ ",loanId:" + loanId + ",remark:" + remark
					+ ",otherAccount:" + otherAccount + ",approveUser1DateStr:"
					+ approveUser1DateStr);
			return "true";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % approvalReject", e);
			return "false";
		}
	}

	/**
	 * 审批通过贷款(提交放款)
	 *
	 * @param account
	 *            当前登录用户
	 * @param loanId
	 *            贷款id
	 * @param remark
	 *            审批备注
	 * @param otherAccount
	 *            共同审批用户
	 * @param approvalUser1Date
	 *            审批人审批时间
	 * @return
	 */
	public String approvalPass(String account, Integer loanId, String remark,
							   String otherAccount, String approvalUser1Date) {
		logger.info("pad端贷款接口approvalPass开始，当前登录用户:" + account + ",loanId:"
				+ loanId + ",remark:" + remark + ",otherAccount:"
				+ otherAccount + ",approvalUser1Date:" + approvalUser1Date);
		try {
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			Integer userId = sysUser.getUserId();
			String userName = sysUser.getUserName();

			SysUser commApproveUser = sysUserService
					.getAllUserByAccount(otherAccount);
			Integer commApproveUserId = commApproveUser.getUserId();
			String commApproveUserName = commApproveUser.getUserName();

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loanId", loanId);
			paramMap.put("loanStatusId", LoanConstants.LOAN_LENDING_STATUS);
			// 主审批人ID
			paramMap.put("approveUserId1", userId);
			// 共同审批人ID
			paramMap.put("approveUserId2", commApproveUserId);
			// 审批备注
			paramMap.put("approveRemark", remark);
			// 主审批人审批通过时间
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date approvalUserDate = format.parse(approvalUser1Date);
			if (approvalUserDate != null) {
				paramMap.put("approvePassDate1", approvalUserDate);
			}
			// 共同审批人审批通过时间
			paramMap.put("approvePassDate2", new Date());
			// 插入贷款历史操作记录
			LnOpHistory lnOpHistory = new LnOpHistory();
			lnOpHistory.setUserId(userId);
			lnOpHistory.setOpHistoryDate(new Date());
			lnOpHistory.setBeforeStatusId(LoanConstants.LOAN_APPROVAL_STATUS);
			lnOpHistory.setAfterStatusId(LoanConstants.LOAN_LENDING_STATUS);
			lnOpHistory.setContent("贷款审批，审批人" + userName + "，共同审批人"
					+ commApproveUserName);
			lnOpHistory.setRemark(remark);
			lnOpHistory.setLoanId(loanId);

			// 审批通过
			lnLoanService.approveLoan(paramMap, lnOpHistory);
			logger.info("pad端贷款接口approvalPass完成，当前登录用户:" + account + ",loanId:"
					+ loanId + ",remark:" + remark + ",otherAccount:"
					+ otherAccount + ",approvalUser1Date:" + approvalUser1Date);
			return "true";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % approvalPass", e);
			return "false";
		}
	}

	/**
	 * 分配提交调查贷款
	 *
	 * @param account
	 *            用户账号
	 * @param loanId
	 *            贷款Id
	 * @param remark
	 *            分配贷款备注
	 * @param surveyUserId
	 *            调用用户Id
	 * @return
	 */
	public String assignLoan(String account, Integer loanId, String remark,
							 Integer surveyUserId) {
		try {
			logger.info("pad端开始贷款分配,当前登录用户为“" + account + "”,贷款id为“" + loanId
					+ "”,分配备注为“" + remark + "”,被分配用户id为“" + surveyUserId + "”");
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			SysUser surveyUser = sysUserService.getSysUserById(surveyUserId);

			PadLoanInfo padLoanInfo = lnLoanInfoService
					.getPanLoanInfoById(loanId);
			if (sysUser != null && padLoanInfo != null) {
				// 更新贷款参数map-------------------------------------------------------------------------------
				Map<String, Object> loanParamMap = new HashMap<String, Object>();
				loanParamMap.put("loanId", loanId);
				loanParamMap.put("surveyUserId", surveyUserId);
				loanParamMap.put("assignSubmitDate", Calendar.getInstance()
						.getTime());
				loanParamMap.put("loanStatusId", 3);
				loanParamMap.put("eventId", 3);
				lnLoanService.updateLnLoanById(loanParamMap);
				// 更改客户列表，将该贷款所关联的客户归属到被分配者-------------------------------------------------------------
				CrmCustomer crmCustomer = crmCustomerService
						.getCustomerById(padLoanInfo.getCustomerId());
				if (crmCustomer != null && surveyUser != null) {
					crmCustomer.setBelongUserId(surveyUserId);
					crmCustomer.setBelongDeptId(surveyUser.getDeptId());
					crmCustomerService.updateCrmCustomer(crmCustomer);
				}
				// 插入贷款历史操作记录----------------------------------------------------------------------------------
				LnOpHistory lnOpHistory = new LnOpHistory();
				lnOpHistory.setUserId(sysUser.getUserId());
				lnOpHistory.setOpHistoryDate(new Date());
				lnOpHistory.setBeforeStatusId(2); // 待分配
				lnOpHistory.setAfterStatusId(3); // 待调查
				lnOpHistory.setContent("贷款分配给" + surveyUser.getUserName());
				lnOpHistory.setRemark(remark); // 历史记录备注
				lnOpHistory.setLoanId(loanId);
				lnOpHistoryService.insertLnOpHistory(lnOpHistory);
				// ------------------------------------------------------------------------------------------------------
				logger.info("pad端完成贷款分配,当前登录用户为“" + account + "”,贷款id为“"
						+ loanId + "”,分配备注为“" + remark + "”,被分配用户id为“"
						+ surveyUserId + "”");
				return "true";
			}
			return getErrorResult(new PadErrorResult("unknow",
					"getAssignUserList", "未知错误"));
		} catch (Exception e) {
			logger.error("RecordWebServicesImpl % assignLoan", e);
			return getErrorResult(new PadErrorResult("unknow",
					"getAssignUserList", "未知错误"));
		}
	}


	/**
	 * 征信请求
	 *
	 * @param account
	 * @param loanId
	 * @param customerId
	 * @param customerType
	 * @param requestReason
	 * @param idauthFlag
	 * @param remark
	 * @return
	 */
	public String requestCreditRef(String account, Integer loanId,
								   Integer customerId, Integer customerType, String requestReason,
								   Integer idauthFlag, String remark) {
		logger.info("requestCreditRef，account：" + account + ",loanId：" + loanId
				+ ",customerId:" + customerId + ",customerType:" + customerType
				+ ",requestReason:" + requestReason + ",idauthFlag:"
				+ idauthFlag + ",remark:" + remark);
		try {
			SysUser sysUser = sysUserService.getAllUserByAccount(account);

			CrRequest crRequest =new CrRequest();
			crRequest.setLoanId(loanId);
			crRequest.setCustomerId(customerId);
			crRequest.setRemark(remark);
			crRequest.setRequestStatus(1);
			crRequest.setRequestDate(Calendar.getInstance().getTime());
			crRequest.setCreateDate(Calendar.getInstance().getTime());
			crRequest.setUpdateDate(Calendar.getInstance().getTime());
			crRequest.setRequestUser(sysUser.getUserId());
			crRequest.setCreateUser(sysUser.getUserId());
			crRequest.setUpdateUser(sysUser.getUserId());
			crRequest.setConductor(0);
			crRequestService.saveCrRequest(crRequest);
			return "true";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % updateApplyCusInfo", e);
			return "false";
		}
	}

	/**
	 * 征信查结果
	 *
	 * @param account
	 * @param loanId
	 * @param customerId
	 * @param customerType
	 * @return
	 */
	public String getRequestResult(String account, Integer loanId,	Integer customerId, Integer customerType) {
		logger.info("getRequestResult，account：" + account + ",loanId：" + loanId  + ",customerId:" + customerId + ",customerType" + customerType);
		try {
			CrRequest crRequest =crRequestService.getCrRequestByLoanIdAndCustomerId(loanId,customerId);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			if (crRequest != null ) {
				resultMap.put("requestResult", crRequest.getRequestStatus());
				resultMap.put("failReason", crRequest.getRemark());
			}else{
				resultMap.put("requestResult", 0);
				resultMap.put("failReason", "");
			}
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			String result = JSONArray.fromObject(resultMap, jsonConfig).toString();
			logger.info("getRequestResult 返回" + result);
			return result;
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % getRequestResult", e);
			return "false";
		}
	}

	/**
	 * 征信取报告
	 *
	 * @param account
	 * @param loanId
	 * @param customerId
	 * @return
	 */
	public String getRequestResultHtml(String account, Integer loanId,	Integer customerId) {
		logger.info("getRequestResultHtml，account：" + account + ",loanId："	+ loanId + ",customerId:" + customerId);
		try {
			CrRequest crRequest =crRequestService.getCrRequestByLoanIdAndCustomerId(loanId,customerId);
			if (crRequest != null) {

				Map<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("conductorName", crRequest.getConductorName()); //处理人
				resultMap.put("conductorDate", DateUtil.getDateToString(crRequest.getConductorDate())); //处理时间
				resultMap.put("remark", crRequest.getRemark()); 			  //处理备注
				List<String> fileUrls = new ArrayList<String>();
				List<String> fileNames = new ArrayList<String>();

				String fileListIds = crRequest.getRequestResult();

				if(fileListIds==null || fileListIds.equals("")){
					resultMap.put("fileUrls", "");
					resultMap.put("fileNames", "");
				}else{
					String[] fileIds = fileListIds.split(",");
					for(int i=0;i<fileIds.length;i++){
						String fileId = fileIds[i];
						SysUploadFile uploadfile = sysUploadFileService.getSysUploadFileById(Integer.parseInt(fileId));
						String fileUrl = fileId+"&fullPath="+uploadfile.getFilePath()+"/"+uploadfile.getFileName();
						String fileName = fileId+"&fileName="+uploadfile.getUploadFileName();
						fileUrls.add(fileUrl);
						fileNames.add(fileName);
					}
					resultMap.put("fileUrls", fileUrls);
					resultMap.put("fileNames", fileNames);
				}

				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
				String result = JSONArray.fromObject(resultMap, jsonConfig).toString();
				logger.info("getRequestResultHtml 返回" + result);
				return result;
			}
			return "false";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % getRequestResultHtml", e);
			return "false";
		}
	}

	/**
	 *
	 */
	public String getLoanMonitorList(String account,Integer loanId){
		logger.info("getLoanMonitorList，account：" + account + ",loanId：" + loanId );
		try {
			List<LnLoanMonitor> lnLoanMonitorList = lnLoanMonitorService.getLoanMonitorList(loanId);


			for (LnLoanMonitor lm : lnLoanMonitorList) {

				List<String> fileUrls = new ArrayList<String>();
				List<String> fileNames = new ArrayList<String>();

				if (StringUtils.isNotEmpty(lm.getFileIds())) {
					SysUploadFile flie = new SysUploadFile();
					if (lm.getFileIds().contains(",")) {
						for (String id : lm.getFileIds().split(",")) {
							flie = sysUploadFileService.getSysUploadFileById(Integer.parseInt(id));
							String fileUrl = flie.getFileId()+"&fullPath="+flie.getFilePath()+"/"+flie.getFileName();
							String fileName = flie.getFileId()+"&fileName="+flie.getUploadFileName();
							fileUrls.add(fileUrl);
							fileNames.add(fileName);
						}
					} else {
						flie = sysUploadFileService.getSysUploadFileById(Integer.parseInt(lm.getFileIds()));
						String fileUrl = flie.getFileId()+"&fullPath="+flie.getFilePath()+"/"+flie.getFileName();
						String fileName = flie.getFileId()+"&fileName="+flie.getUploadFileName();
						fileUrls.add(fileUrl);
						fileNames.add(fileName);
					}
					lm.setFileNames(fileNames);
					lm.setFileUrls(fileUrls);
				}

				if(StringUtils.isNotBlank(lm.getCustomerStatus())){
					lm.setCustomerStatus(lm.getCustomerStatus().toString());
				}
				if(StringUtils.isNotBlank(lm.getBizStatus())){
					lm.setBizStatus( lm.getBizStatus().toString());
				}
				if(StringUtils.isNotBlank(lm.getFinanceStatus())){
					lm.setFinanceStatus(lm.getFinanceStatus().toString());
				}
				if(StringUtils.isNotBlank(lm.getGuarantorStatus())){
					lm.setGuarantorStatus(lm.getGuarantorStatus().toString());
				}
				if(StringUtils.isNotBlank(lm.getLedgeStatus())){
					lm.setLedgeStatus(lm.getLedgeStatus().toString());
				}

			}
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			String result = JSONArray.fromObject(lnLoanMonitorList, jsonConfig).toString();
			logger.info("getRequestResultHtml 返回" + result);
			if(result==null){
				return  null;
			}
			return result;
		}catch (Exception e) {
			logger.error("LoanWebServiceImpl % getLoanMonitorList", e);
			return "false";
		}
	}


	/***
	 *
	 * @param account 用户
	 * @param loanMonId 主键(新增时为0)
	 * @param loanId(贷款ID)
	 * @param monType 回访类型
	 * @param revisitType 监控类型
	 * @param userResult 结果
	 * @return
	 */
	public String saveLoanMonitor(String account,Integer loanMonId,Integer loanId,Integer monType, Integer revisitType,String userResult,String customerStatus,String bizStatus,
	String financeStatus,String guarantorStatus,String ledgeStatus
	){
		logger.info("saveLoanMonitor，account：" + account + ",loanId：" + loanId );
		try {
			BaseLnLoanMonitor lnLoanMonitor = new LnLoanMonitor();
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			if(loanMonId.equals(0)){ //新建
				lnLoanMonitor.setLoanId(loanId);
				lnLoanMonitor.setMonType(monType);
				lnLoanMonitor.setRevisitType(revisitType);
				if(StringUtil.isBlank(userResult)) {
					userResult = "";
				}
				lnLoanMonitor.setUserResult(userResult);
				lnLoanMonitor.setMonDate(Calendar.getInstance().getTime());
				lnLoanMonitor.setMonUserId(sysUser.getUserId());
				lnLoanMonitor.setFileIds("");
				lnLoanMonitor.setCreateDate(Calendar.getInstance().getTime());
				lnLoanMonitor.setCreateUser(sysUser.getUserId());
				if(StringUtil.isBlank(bizStatus)) {
					bizStatus = "";
				}
				lnLoanMonitor.setBizStatus(bizStatus);
				if(StringUtil.isBlank(customerStatus)) {
					customerStatus = "";
				}
				lnLoanMonitor.setCustomerStatus(customerStatus);
				if(StringUtil.isBlank(guarantorStatus)) {
					guarantorStatus = "";
				}
				lnLoanMonitor.setGuarantorStatus(guarantorStatus);
				if(StringUtil.isBlank(financeStatus)) {
					financeStatus = "";
				}
				lnLoanMonitor.setFinanceStatus(financeStatus);
				if(StringUtil.isBlank(ledgeStatus)) {
					ledgeStatus = "";
				}
				lnLoanMonitor.setLedgeStatus(ledgeStatus);
				lnLoanMonitorService.createLoanMonitor(lnLoanMonitor);
			}else{//编辑
				lnLoanMonitor =  lnLoanMonitorService.getLoanMonitor(loanMonId);

				lnLoanMonitor.setMonUserId(sysUser.getUserId());
				lnLoanMonitor.setRevisitType(revisitType);
				if(StringUtil.isBlank(userResult)) {
					userResult = "";
				}
				lnLoanMonitor.setUserResult(userResult);
				if(StringUtil.isBlank(bizStatus)) {
					bizStatus = "";
				}
				lnLoanMonitor.setBizStatus(bizStatus);
				if(StringUtil.isBlank(customerStatus)) {
					customerStatus = "";
				}
				lnLoanMonitor.setCustomerStatus(customerStatus);
				if(StringUtil.isBlank(guarantorStatus)) {
					guarantorStatus = "";
				}
				lnLoanMonitor.setGuarantorStatus(guarantorStatus);
				if(StringUtil.isBlank(financeStatus)) {
					financeStatus = "";
				}
				lnLoanMonitor.setFinanceStatus(financeStatus);
				if(StringUtil.isBlank(ledgeStatus)) {
					ledgeStatus = "";
				}
				lnLoanMonitor.setLedgeStatus(ledgeStatus);
				lnLoanMonitor.setUpdateDate(Calendar.getInstance().getTime());
				lnLoanMonitor.setUpdateUser(sysUser.getUserId());
				lnLoanMonitorService.updateLoanMonitor(lnLoanMonitor);
			}
			return "true";
		}catch (Exception e) {
			logger.error("LoanWebServiceImpl % saveLoanMonitor", e);
			return "false";
		}
	}

	public String getAssignUserList(String account){
		logger.info("getAssignUserList，account：" + account );
		try {
			List<Integer> userIdList = sysRoleMemberService.getUserIdByRole("5","");
			List<Map<String, Object>> userMapList = new ArrayList<Map<String, Object>>();
			for (int i=0;i<userIdList.size();i++){
				SysUser user = sysUserService.getSysUserById(userIdList.get(i));
				userMapList.add(this.userToMap(user));
			}

			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
			String result = JSONArray.fromObject(userMapList, jsonConfig).toString();
			logger.info("getAssignUserList 返回" + result);

			return result;
		}catch (Exception e) {
			logger.error("LoanWebServiceImpl % getAssignUserList", e);
			return "false";
		}
	}



	//分配回退
	public String assignTheallback(String account, Integer loanId) {
		logger.info("assignTheallback，account：" + account +"loanId:"+loanId);
		try {
			lnLoanService.updateAssign(loanId);
			return "true";
		}catch (Exception e) {
			logger.error("LoanWebServiceImpl % assignTheallback", e);
			return "false";
		}
	}

	//调查回退
	public String goBackLoanSurvey(String account, Integer loanId) {

		logger.info("goBackLoanSurvey，account：" + account +"loanId:"+loanId);
		try {
			lnLoanService.updateGoBackLoanSurveyById(loanId);
			return "true";
		}catch (Exception e) {
			logger.error("LoanWebServiceImpl % goBackLoanSurvey", e);
			return "false";
		}
	}

	public String getIsCustomerManageTag(String account){
		SysUser sysUser = sysUserService.getAllUserByAccount(account);

		List<Integer> roles = sysRoleMemberService.getRoleIdByUserIds(sysUser.getUserId() + "");
		String isCustomerManage = "false";
		for (Integer roleId : roles) {
			if (roleId.intValue() == 7 ) {
				isCustomerManage = "true";
				break;
			}
		}
		return isCustomerManage;
	}


	// *****************************************************************************************************************TODO
	// *****************************************************************************************************************TODO
	// *****************************************************************************************************************TODO
	// *****************************************************************************************************************TODO
	// *************************************************以上调整完成****************************************************TODO
	// *****************************************************************************************************************TODO
	// *****************************************************************************************************************TODO
	// *****************************************************************************************************************TODO
	// *****************************************************************************************************************TODO

	/**
	 * 贷款分配拒绝
	 *
	 * @param account
	 * @param loanId
	 * @param remark
	 * @param cancelReasonId
	 * @param cancelReasonOther
	 * @return
	 */
	public String assignRefuseLoan(String account, Integer loanId,
								   String remark, Integer cancelReasonId, String cancelReasonOther) {
		// TODO:修改
		return null;
		/*
		 * try { logger.info("pad端开始贷款分配拒绝，当前登录用户为“" + account + "”,贷款id为“" +
		 * loanId + "”,拒绝备注为“" + remark + "”,拒绝原因id为“" + cancelReasonId +
		 * "”,其他拒绝原因为“" + cancelReasonOther + "”"); SysUser sysUser =
		 * sysUserService.getAllUserByAccount(account); LnLoan lnLoan =
		 * lnLoanService.getLnLoanById(loanId); // 更改当前贷款状态为8(待撤销) Map<String,
		 * Object> paramMap = new HashMap<String, Object>();
		 * 
		 * LnOpHistory lnOpHistory = new LnOpHistory();
		 * lnOpHistory.setUserId(sysUser.getUserId());
		 * lnOpHistory.setOpHistoryDate(new Date());
		 * lnOpHistory.setBeforeStatusId(lnLoan.getLoanStatusId()); // 待分配
		 * lnOpHistory.setAfterStatusId(12); // 待撤销
		 * lnOpHistory.setContent("贷款分配拒绝"); lnOpHistory.setLoanId(loanId);
		 * 
		 * if (cancelReasonOther != null && !cancelReasonOther.equals("")) {
		 * //点击了其他拒绝原因 paramMap.put("cancelReasonOther", cancelReasonOther); if
		 * (StringUtils.isNotEmpty(remark) && !remark.trim().equals("")) {
		 * lnOpHistory.setRemark(remark + "|" + cancelReasonOther); } else {
		 * lnOpHistory.setRemark(cancelReasonOther); } } else if (cancelReasonId
		 * != null && !cancelReasonId.equals(-1)) { LnCancelReason cancelReason
		 * = lnCancelReasonService.getCancelReasonById(cancelReasonId);
		 * //没有点击其他拒绝原因，使用系统预置拒绝原因 paramMap.put("cancelDate", new Date());
		 * paramMap.put("cancelReasonId", cancelReasonId); if
		 * (StringUtils.isNotEmpty(remark) && !remark.trim().equals("")) {
		 * lnOpHistory.setRemark(remark + "|" +
		 * cancelReason.getCancelReasonName()); } else {
		 * lnOpHistory.setRemark(cancelReason.getCancelReasonName()); } }
		 * 
		 * paramMap.put("cancelUserId", sysUser.getUserId());
		 * paramMap.put("loanStatusId", 12); paramMap.put("loanId", loanId);
		 * 
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("loanId", loanId); LnLoanInfo loanInfo =
		 * lnLoanInfoService.selectLoanInfoList(map).get(0); String djSerno =
		 * loanInfo.getSerialNumber(); return "true";
		 * 
		 * MessageResult messageResult = sendMessageService.refuseLoan(djSerno,
		 * lnLoan, "2"); if (messageResult != null) { // 撤销贷款
		 * lnLoanService.cancelLoan(paramMap, lnOpHistory);
		 * logger.info("pad端完成贷款分配拒绝，当前登录用户为“" + account + "”,贷款id为“" + loanId +
		 * "”,拒绝备注为“" + remark + "”,拒绝原因id为“" + cancelReasonId + "”,其他拒绝原因为“" +
		 * cancelReasonOther + "”"); return "true"; } else { return "false"; } }
		 * catch (Exception e) {
		 * logger.error("RecordWebServicesImpl % cancelLoan", e); return
		 * "false"; }
		 */}

	/**
	 * 3.77 落实贷款
	 *
	 * @param account
	 * @param loanId
	 * @param remark
	 * @return
	 */
	public String checkoutLoan(String account, Integer loanId, String remark) {
		try {
			logger.info("pad端开始贷款落实，当前登录用户为“" + account + "”,贷款id为“" + loanId
					+ "”,备注为“" + remark + "”");
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loanId", loanId);
			paramMap.put("isCheckout", 1);

			// 插入贷款历史操作记录
			LnOpHistory lnOpHistory = new LnOpHistory();
			lnOpHistory.setUserId(sysUser.getUserId());
			lnOpHistory.setOpHistoryDate(new Date());
			lnOpHistory.setBeforeStatusId(6); // 还款中
			lnOpHistory.setAfterStatusId(6); // 还款中
			lnOpHistory.setContent("贷款落实");
			lnOpHistory.setRemark(remark); // 历史记录备注
			lnOpHistory.setLoanId(loanId);
			// 落实贷款
			lnLoanService.checkoutLoan(paramMap, lnOpHistory);
			logger.info("pad端完成贷款落实，当前登录用户为“" + account + "”,贷款id为“" + loanId
					+ "”,备注为“" + remark + "”");
			return "true";
		} catch (Exception e) {
			logger.error("RecordWebServicesImpl % checkoutLoan", e);
			return "false";
		}
	}

	/**
	 * 3.78 设置为不良客户
	 *
	 * @param account
	 * @param loanId
	 * @return
	 */
	public String setNogoodCustomer(String account, Integer loanId,
									String remark) {
		try {
			logger.info("pad端开始将贷款客户设置为不良客户，当前登录用户为“" + account + "”,贷款id为“" + loanId + "”,备注为“" + remark + "”");
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("isNogood", 1);
			paramMap.put("loanId", loanId);
			paramMap.put("isCheckout", 1);
			paramMap.put("sortno", null);
			paramMap.put("repaymentDate", null);
			paramMap.put("currentNeedRepay", null);
			paramMap.put("repaymentStatus", null);
			paramMap.put("nogoodRemark", remark);
			// 设为不良用户
			// 插入贷款历史操作记录
			LnOpHistory lnOpHistory = new LnOpHistory();
			lnOpHistory.setUserId(sysUser.getUserId());
			lnOpHistory.setOpHistoryDate(new Date());
			lnOpHistory.setBeforeStatusId(6); // 还款中
			lnOpHistory.setAfterStatusId(6); // 还款中
			lnOpHistory.setContent("设置客户为不良客户");
			lnOpHistory.setRemark(remark);
			lnOpHistory.setLoanId(loanId);
			// 更新客户表 客户为不良客户
			Integer customerId = lnLoanService.getLnLoanById(loanId)
					.getCrmCustomer().getCustomerId();
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("isNogood", 1);
			param.put("customerId", customerId);

			// 设为不良客户
			lnLoanService.setNogoodCustomer(paramMap, lnOpHistory, param);
			logger.info("pad端完成将贷款客户设置为不良客户，当前登录用户为“" + account + "”,贷款id为“"
					+ loanId + "”,备注为“" + remark + "”");
			return "true";
		} catch (Exception e) {
			logger.error("RecordWebServicesImpl % setNogoodCustomer", e);
			return "false";
		}
	}

	/**
	 * 3.79 上门催收贷款
	 *
	 * @param account
	 * @param loanId
	 * @param remark
	 * @return
	 */
	public String dunLoan(String account, Integer loanId, Integer dunLogId,
						  String remark) {
		try {
			logger.info("pad端开始上门催收贷款，当前登录用户为“" + account + "”,贷款id为“" + loanId
					+ "”,备注为“" + remark + "”,催收日志id为“" + dunLogId + "”");
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loanId", loanId);
			paramMap.put("remark", remark);

			// 更新贷款催收记录表(给当前催收记录的备注字段插入值)
			paramMap.put("dunLogId", dunLogId);
			LnDunLog dunLog = lnDunLogService.getDunLogById(dunLogId);
			if (dunLog == null) {
				LnDunLog newLog = new LnDunLog();
				newLog.setDunLogId(dunLogId);
				newLog.setLoanId(loanId);
				newLog.setDunUserId(sysUser.getUserId());
				newLog.setDunDate(Calendar.getInstance().getTime());
				newLog.setDunType(3);// 实地
				newLog.setCreateUser(sysUser.getUserId());
				newLog.setCreateDate(Calendar.getInstance().getTime());
				newLog.setRemark(remark);
				lnDunLogService.addLnDunLog(newLog);
			} else {
				lnDunLogService.updateDunLogById(paramMap);
			}
			logger.info("pad端完成上门催收贷款，当前登录用户为“" + account + "”,贷款id为“" + loanId
					+ "”,备注为“" + remark + "”,催收日志id为“" + dunLogId + "”");
			return "true";
		} catch (Exception e) {
			logger.error("RecordWebServicesImpl % dunLoan", e);
			return "false";
		}
	}

	/**
	 * 3.79 上门异常催收贷款
	 *
	 * @param account
	 * @param loanId
	 * @param remark
	 * @return
	 */
	public String dunExceptionLoan(String account, Integer loanId,
								   Integer dunExceptionLogId, String remark) {
		try {
			logger.info("pad端开始上门异常催收贷款，当前登录用户为“" + account + "”,贷款id为“"
					+ loanId + "”,备注为“" + remark + "”,异常催收日志id为“"
					+ dunExceptionLogId + "”");
			LnRepaymentPlan repaymentPlan = lnRepaymentPlanService
					.selectCurLnRepaymentPlanById(loanId);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			// String curRepaymentDate =
			// format.format(repaymentPlan.getPlanDate());
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// paramMap.put("loanId", loanId);
			// paramMap.put("repaymentStatus", 1); // 待催收
			// paramMap.put("repaymentDate", repaymentPlan.getPlanDate());
			// paramMap.put("owedPrincipal", repaymentPlan.getPrincipal());
			// paramMap.put("ovedInterest", repaymentPlan.getInterest());
			// paramMap.put("sortno", repaymentPlan.getSortno());
			paramMap.put("remark", remark);
			// if (repaymentPlan.getPrincipal() != null &&
			// repaymentPlan.getInterest() != null) {
			// paramMap.put("currentNeedRepay",
			// repaymentPlan.getPrincipal().add(repaymentPlan.getInterest()));
			// }
			//
			// lnLoanService.updateLnLoanById(paramMap);
			// 更新贷款催收记录表(给当前催收记录的备注字段插入值)
			paramMap.put("exceptionDunLogId", dunExceptionLogId);
			// 是否有催收记录
			LnExceptionDunLog log = lnExceptionDunLogService
					.getExceptionDunLogById(dunExceptionLogId);
			if (log != null) {
				// 有的话，更新
				lnExceptionDunLogService.updateExceptionDunLogById(paramMap);
			} else {
				LnExceptionDunLog newLog = new LnExceptionDunLog();
				newLog.setExceptionDunLogId(dunExceptionLogId);
				newLog.setLoanId(loanId);
				newLog.setDunUserId(sysUser.getUserId());
				newLog.setDunDate(Calendar.getInstance().getTime());
				newLog.setDunType(3);// 实地
				newLog.setCreateUser(sysUser.getUserId());
				newLog.setCreateDate(Calendar.getInstance().getTime());
				newLog.setRemark(remark);
				lnExceptionDunLogService.addLnExceptionDunLog(newLog);
			}
			logger.info("pad端完成上门异常催收贷款，当前登录用户为“" + account + "”,贷款id为“"
					+ loanId + "”,备注为“" + remark + "”,异常催收日志id为“"
					+ dunExceptionLogId + "”");
			return "true";
		} catch (Exception e) {
			logger.error("RecordWebServicesImpl % dunExceptionLoan", e);
			return "false";
		}
	}

	/**
	 * 3.24 PAD客户贷款信息
	 *
	 * @param customerId
	 *            客户
	 * @param pageNumber
	 *            当前页
	 * @return
	 */
	public String getCustomerLoanList(Integer customerId, Integer pageNumber) {
		// TODO:修改
//		return null;
		try {
			logger.info("pad端开始获取客户的所有贷款信息，客户id为“" + customerId + "”,查询当前页为“" + pageNumber + "”");
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());

			Page page = new Page();
			page.setCurrentPage(pageNumber);

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("customerId", customerId);
			PageUtil<LnLoan> list = lnLoanService.getAllLoanByCusId(paramMap, page);
			if (list != null) {
				PadLoanInfoPageCard padLoanInfoPageCard = new PadLoanInfoPageCard();
				padLoanInfoPageCard.setTotalCount(list.getItems().size());
				List<PadLoanInfoCard> infoCardList = new ArrayList<PadLoanInfoCard>();
				for (LnLoan loan : list.getItems()) {
					PadLoanInfoCard padLoanInfoCard = new PadLoanInfoCard();
					padLoanInfoCard.setLoanId(loan.getLoanId());
					if (loan.getLnLoanTypeBean() != null) {
						padLoanInfoCard.setLoanType(loan.getLnLoanTypeBean().getLoanTypeName());
					}
					if (loan.getLnLoanStatus() != null) {
						padLoanInfoCard.setLoanStatus(loan.getLnLoanStatus().getLoanStatusName());
					}
					padLoanInfoCard.setLoanMoney(VmHelper.getDecimalWanMoney(loan.getAppMoney()));
					padLoanInfoCard.setCreateDate(DateUtil.convertDateToString
							("yyyy-MM-dd HH:mm", loan.getCreateDate()));
					if (loan.getSysUser() != null) {
						padLoanInfoCard.setApplyUserName(loan.getSysUser().getApplyUserName());
					}
					padLoanInfoCard.setLoanInfoId(loan.getLoanInfoId());
					infoCardList.add(padLoanInfoCard);
				}
				padLoanInfoPageCard.setDataList(infoCardList);

				String result = jsonObject.fromObject(padLoanInfoPageCard,
						jsonConfig).toString();
				logger.info("pad端完成获取客户的所有贷款信息，客户id为“" +
						customerId + "”,查询当前页为“" + pageNumber + "”");
				return result;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % getCustomerLoanList", e);
			return
					null;
		}
	}

	/**
	 * 得到撤销原因
	 *
	 * @param account
	 * @return
	 */
	public String getCancelReason(String account) {
		try {
			List<LnCancelReason> list = lnCancelReasonService
					.getCancelReasonList();

			String result = JSONArray.fromObject(list).toString();
			return result;
		} catch (Exception e) {
			logger.error("RecordWebServicesImpl % getCancelReason", e);
			return null;
		}
	}

	/**
	 * 删除未提交贷款图片
	 */
	public String deletePhoto(String account, String uuid) {
		try {
			logger.info("pad端开始删除贷款图片，当前登录用户为“" + account + "”,图片的uuid为“"
					+ uuid + "”");
			if (StringUtils.isNotEmpty(uuid)) {
				dataPhotoService.deletePhoto(uuid);
			}
			logger.info("pad端完成删除贷款图片，当前登录用户为“" + account + "”,图片的uuid为“"
					+ uuid + "”");
			return "true";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % deletePhoto", e);
			return "false";
		}
	}

	/*
	 * private BaseLoanListInfo lnLoanToBaseLoanListInfo(LnLoan loan) {
	 * BaseLoanListInfo object = new BaseLoanListInfo();
	 * object.setLoanId(loan.getLoanInfoId
	 * ())setLoanInfoId(loan.getLoanInfoId());
	 * object.setLoanId(loan.getLoanId());
	 * object.setLoanStatusId(loan.getLoanStatusId());
	 * object.setCustomerId(loan.getCustomerId()); if (loan.getCrmCustomer() !=
	 * null) { object.setIdCard(loan.getCrmCustomer().getIdCard());
	 * object.setCustomerName(loan.getCrmCustomer().getCustomerName());
	 * object.setCustomerTitle(loan.getCrmCustomer().getCustomerTitle());
	 * object.setIsNoGood(loan.getCrmCustomer().getIsNoGood());
	 * object.setBelongUserId(loan.getCrmCustomer().getBelongUserId()); }
	 * object.setPhone(loan.getCphNumber()); if (loan.getApplyDate() != null) {
	 * object.setApplyDate(DateUtil.convertDateToString("yyyy-MM-dd HH:mm",
	 * loan.getApplyDate())); } object.setApplyUserId(loan.getApplyUserId()); if
	 * (loan.getCreateDate() != null) {
	 * object.setCreateDate(DateUtil.convertDateToString("yyyy-MM-dd HH:mm",
	 * loan.getCreateDate())); } object.setCreateUserId(loan.getCreateUser());
	 * 
	 * LoanUser loanUser = loan.getSysUser(); if (loanUser != null) {
	 * object.setApplyUserName(loanUser.getApplyUserName());
	 * object.setSubmitUserName(loanUser.getSubmitUserName()); } if
	 * (loan.getSubmitDate() != null) {
	 * object.setSubmitDate(DateUtil.convertDateToString("yyyy-MM-dd HH:mm",
	 * loan.getSubmitDate())); } object.setSubmitUserId(loan.getSubmitUserId());
	 * if (loan.getAssignDate() != null) {
	 * object.setAssignDate(DateUtil.convertDateToString("yyyy-MM-dd HH:mm",
	 * loan.getAssignDate())); } if (loan.getLendDate() != null) { object
	 * .setLendDate(DateUtil.convertDateToString("yyyy-MM-dd HH:mm",
	 * loan.getLendDate())); } if (loan.getRepaymentDate() != null) {
	 * object.setRepaymentDate(DateUtil.convertDateToString("yyyy-MM-dd HH:mm",
	 * loan.getRepaymentDate())); } if (loan.getApprovePassDate2() != null) {
	 * object.setApprovalDate(DateUtil.convertDateToString("yyyy-MM-dd HH:mm",
	 * loan.getApprovePassDate2())); }
	 * object.setRepaymentStatus(loan.getRepaymentStatus());
	 * object.setNeedRepay(
	 * VmHelper.getDecimalWanMoney(loan.getCurrentNeedRepay()));
	 * object.setAccountRemaining
	 * (VmHelper.getDecimalWanMoney(loan.getAccountRemaining())); if
	 * (!(loan.getRepaymentStatus() != null &&
	 * loan.getRepaymentStatus().equals(2))) { // 没有催收 object.setIsFinishDun(0);
	 * } object.setOverduePrincipal(VmHelper.getDecimalWanMoney(loan.
	 * getOverduePrincipal())); // 逾期本金
	 * object.setOverdueInterest(VmHelper.getDecimalWanMoney
	 * (loan.getOverdueInterest())); // 逾期利息
	 * object.setOwedPrincipal(VmHelper.getDecimalWanMoney
	 * (loan.getOwedPrincipal())); // 本期应还本金
	 * object.setOwedInterest(VmHelper.getDecimalWanMoney
	 * (loan.getOwedInterest())); // 本期应还利息
	 * object.setPaidPrincipal(VmHelper.getDecimalWanMoney
	 * (loan.getPaidPrincipal())); // 本期已还本金
	 * object.setPaidInterest(VmHelper.getDecimalWanMoney
	 * (loan.getPaidInterest())); // 本期已还利息
	 * object.setNeedRepay(VmHelper.getDecimalMoney
	 * (loan.getCurrentNeedRepay())); //本期合计
	 * object.setAccountRemaining(VmHelper.
	 * getDecimalMoney(loan.getAccountRemaining())); //账户余额
	 * 
	 * // 新增字段设置 object.setCreateUser(loan.getCreateUser()); if
	 * (loan.getSysUser() != null) {
	 * object.setCreateUserName(loan.getSysUser().getCreateUserName()); }
	 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm"); if
	 * (loan.getCreateDate() != null) {
	 * object.setCreateDate(format.format(loan.getCreateDate())); }
	 * object.setIsCheckout(loan.getIsCheckout());
	 * object.setIsFull(loan.getIsFull());
	 * 
	 * return object; }
	 */

	/**
	 * 当前用户的下属用户
	 *
	 * @param userId
	 *            用户Id
	 * @return
	 * @author zhangfp
	 */
	private Integer[] getSysUserIds(Integer userId) {
		// 当前用户的下属用户
		Integer[] resultArr = null;
		Integer[] sysUserIds = deptFacadeService
				.getInChargeOfDeptUserIds(userId);
		if (sysUserIds != null) {
			resultArr = new Integer[sysUserIds.length + 1];
			for (int i = 0; i < sysUserIds.length; i++) {
				resultArr[i] = sysUserIds[i];
			}
			resultArr[sysUserIds.length] = userId;
		}
		return resultArr;
		// Integer[] sysUserIds = null;
		// String sysRoles = sysUserService.getRoleIds(userId);
		// String[] roleArr = null;
		// if (StringUtils.isNotEmpty(sysRoles)){
		// roleArr = sysRoles.split(",");
		// }
		// String belongUserIds =
		// deptFacadeService.getUserIdsBelongToManagerForPAD(roleArr,"loanInfo",
		// userId);
		// if (StringUtils.isNotEmpty(belongUserIds)){
		// String[] belongUserIdArr = belongUserIds.split(",");
		// if (belongUserIdArr != null && belongUserIdArr.length > 0){
		// sysUserIds = new Integer[belongUserIdArr.length];
		// for (int i = 0; i < belongUserIdArr.length; i++) {
		// sysUserIds[i] = Integer.parseInt(belongUserIdArr[i]);
		// }
		// }
		// }
		// return sysUserIds;
	}

	/**
	 * 判断当前贷款(状态：待调查)是否可以提交审批
	 *
	 * @param loanId
	 * @return
	 */
	private Map<String, Object> isSubmitable(Integer loanId, Integer customerId) {
		// 得到当前贷款相当的所有资料
		Map<String, Object> photoMap = new HashMap<String, Object>();
		photoMap.put("loanId", loanId);
		photoMap.put("statistics", 1);
		List<LoanData> loanDataList = customerDataService
				.getAllLoanDataById(photoMap);
		// 查看贷款人以及他的共同借贷人和担保人资料信息
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("loanId", loanId);
		List<LnLoan> lnLoanList = lnLoanService.queryLnLoanRelation(paramMap);
		// 所有共同借贷人
		// List<LnLoanCoBorrowerBean> coBorrowerCustomerList = new
		// ArrayList<LnLoanCoBorrowerBean>();
		// 所有担保人
		List<LnLoanGuarantorBean> guarantorCustomerList = new ArrayList<LnLoanGuarantorBean>();

		for (LnLoan lnLoan : lnLoanList) {
			// LnLoanCoBorrowerBean lnLoanCoBorrowerBean =
			// lnLoan.getLnLoanCoBorrowerBean();
			// if (lnLoanCoBorrowerBean.getCustomerId() != null) {
			// coBorrowerCustomerList.add(lnLoanCoBorrowerBean);
			// }
			LnLoanGuarantorBean lnLoanGuarantorBean = lnLoan
					.getLnLoanGuarantorBean();
			if (lnLoanGuarantorBean.getCustomerId() != null) {
				guarantorCustomerList.add(lnLoanGuarantorBean);
			}
		}

		Map<String, Object> retMap = new HashMap<String, Object>();

		// 验证贷款人资料
		Integer loanDataResult = this.processLoanPersonData(loanDataList,
				customerId);
		if (loanDataResult == null || loanDataResult.intValue() != 0) {
			retMap.put("promptType", loanDataResult);
			return retMap;
		}
		// 验证共同借贷人资料
		// if (coBorrowerCustomerList.size() > 0) {
		// Map<String, Object> coBorrowerDataResult =
		// this.processCoBorrowerData(loanDataList,
		// coBorrowerCustomerList);
		// Integer promptType = (Integer)
		// coBorrowerDataResult.get("promptType");
		// if (promptType == null || promptType.intValue() != 0) {
		// return coBorrowerDataResult;
		// }
		// }
		// 验证担保人资料
		if (guarantorCustomerList.size() > 0) {
			Map<String, Object> guarantorDataResult = this
					.processGuarantorData(loanDataList, guarantorCustomerList);
			Integer promptType = (Integer) guarantorDataResult
					.get("promptType");
			if (promptType == null || !promptType.equals(0)) {
				return guarantorDataResult;
			}
		}
		retMap.put("promptType", 0);
		return retMap;
	}

	/**
	 * 判断贷款人是否有满足最低贷款要求的‘调查录音’和‘相关照片’
	 *
	 * @param loanDataList
	 * @param loanCusId
	 * @return
	 */
	private Integer processLoanPersonData(List<LoanData> loanDataList,
										  Integer loanCusId) {
		// 是否存在贷款人的录音资料 0、不存在;1、存在
		int hasAudioForLoanPerson = 0;
		// 是否存在贷款人的附件资料 0表示不存在;1表示存在
		// int hasAttachmentForLoanPerson = 0;
		// // 户口簿照片张数(至少1张)
		// int householdPhotoCountForLoanPerson = 0;
		// // 家庭照片张数(至少3张)
		// int homePhotoCountForLoanPerson = 0;
		// // 经营地照片张数(至少3张)
		// int manageDirPhotoCountForLoanPerson = 0;
		for (LoanData loanData : loanDataList) {
			Integer dataType = loanData.getDataType();
			Integer queryCusId = loanData.getCustomerId();
			Integer eventId = loanData.getEventId();
			if (!loanCusId.equals(queryCusId)) {
				continue;
			}
			// Integer photoTypeId = loanData.getPhotoTypeId();
			// if (dataType != null && dataType == 1) {
			// // 照片
			// // 贷款人照片
			// if (photoTypeId != null && photoTypeId.intValue() == 4) {
			// // 户口簿照片(至少1张)
			// // if (householdPhotoCountForLoanPerson != 1) {
			// // householdPhotoCountForLoanPerson = 1;
			// // }
			// householdPhotoCountForLoanPerson += 1;
			// } else if (photoTypeId != null && photoTypeId.intValue() == 5) {
			// // 家庭照片(至少3张)
			// // if (homePhotoCountForLoanPerson != 3) {
			// // homePhotoCountForLoanPerson += 1;
			// // }
			// homePhotoCountForLoanPerson += 1;
			// } else if (photoTypeId != null && photoTypeId.intValue() == 6) {
			// // 经营地照片(至少3张)
			// // if (manageDirPhotoCountForLoanPerson != 3) {
			// // manageDirPhotoCountForLoanPerson += 1;
			// // }
			// manageDirPhotoCountForLoanPerson += 1;
			// }
			// } else
			if (dataType != null && dataType == 2 && eventId != null
					&& LoanConstants.LOAN_EXAM_EVENT.equals(eventId)) {
				// 录音
				// 找到了贷款人的录音资料
				if (hasAudioForLoanPerson != 1) {
					hasAudioForLoanPerson = 1;
				}
			}
			// if (dataType != null && dataType == 4 && eventId != null &&
			// LoanConstants.LOAN_EXAM_EVENT.equals(eventId)) {
			// // 录音
			// // 找到了贷款人的附件资料
			// if (hasAttachmentForLoanPerson != 1) {
			// hasAttachmentForLoanPerson = 1;
			// break;
			// }
			// }
		}
		// 判断贷款人是否有完整的资料
		if (hasAudioForLoanPerson == 0) {
			// 贷款人没有调查录音资料
			return 1;// 提示：贷款审批至少需有一段调查录音！
		}
		// if (hasAttachmentForLoanPerson == 0) {
		// return 7;
		// }
		// if ((householdPhotoCountForLoanPerson < 1) ||
		// (homePhotoCountForLoanPerson < 3)
		// || (manageDirPhotoCountForLoanPerson < 3)) {
		// return 2;// 提示：贷款审批至少需要一张户籍照片、三张家庭照片、三张经营地照片！
		// }
		return 0;
	}

	/**
	 * 判断共同借贷人是否有满足最低贷款要求的‘调查录音’和‘相关照片’
	 *
	 * @param loanDataList
	 *            该贷款相关的所有资料
	 * @param lnLoanCoBorrowerBeanList
	 *            所有共同借贷人
	 * @return
	 */
	private Map<String, Object> processCoBorrowerData(
			List<LoanData> loanDataList,
			List<LnLoanCoBorrowerBean> lnLoanCoBorrowerBeanList) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		for (LnLoanCoBorrowerBean borrowerBean : lnLoanCoBorrowerBeanList) {
			// 共同贷款人资料

			// 是否存在共同贷款人的录音资料
			int hasAudioForCommLoanPerson = 0;
			// // 户口簿照片张数(至少1张)
			// int householdPhotoCountForCommLoanPerson = 0;
			// // 家庭照片张数(至少3张)
			// int homePhotoCountForCommLoanPerson = 0;
			// // 头像照片张数
			// int headPhotoCountForCommLoanPerson = 0;
			// // 身份证正面张数
			// int cardPhotoPlusCountForCommLoanPerson = 0;
			// // 身份证背面张数
			// int cardPhotoMinusCountForCommLoanPerson = 0;

			Integer customerId = borrowerBean.getCustomerId();
			for (LoanData loanData : loanDataList) {
				Integer queryCusId = loanData.getCustomerId();
				if (!customerId.equals(queryCusId)) {
					continue;
				}
				Integer dataType = loanData.getDataType();
				// Integer photoTypeId = loanData.getPhotoTypeId();
				// if (dataType.intValue() == 1) {
				// // 照片
				// // 共同贷款人照片
				// if (photoTypeId.intValue() == 4) {
				// // 户口簿照片(至少1张)
				// // if (householdPhotoCountForCommLoanPerson != 1) {
				// // householdPhotoCountForCommLoanPerson = 1;
				// // }
				// householdPhotoCountForCommLoanPerson += 1;
				// } else if (photoTypeId.intValue() == 5) {
				// // 家庭照片(至少3张)
				// // if (homePhotoCountForCommLoanPerson != 3) {
				// // homePhotoCountForCommLoanPerson += 1;
				// // }
				// homePhotoCountForCommLoanPerson += 1;
				// } else if (photoTypeId.intValue() == 1) {
				// // 头像照片
				// // if (headPhotoCountForCommLoanPerson != 1) {
				// // headPhotoCountForCommLoanPerson = 1;
				// // }
				// headPhotoCountForCommLoanPerson += 1;
				// } else if (photoTypeId.intValue() == 2) {
				// // 身份证正面
				// // if (cardPhotoPlusCountForCommLoanPerson != 1) {
				// // cardPhotoPlusCountForCommLoanPerson = 1;
				// // }
				// cardPhotoPlusCountForCommLoanPerson += 1;
				// } else if (photoTypeId.intValue() == 3) {
				// // 身份证背面
				// // if (cardPhotoMinusCountForCommLoanPerson != 1) {
				// // cardPhotoMinusCountForCommLoanPerson = 1;
				// // }
				// cardPhotoMinusCountForCommLoanPerson += 1;
				// }
				// } else
				if (dataType.intValue() == 2) {
					// 录音
					// 找到了共同贷款人录音资料
					// if (hasAudioForCommLoanPerson != 1) {
					// hasAudioForCommLoanPerson = 1;
					// }
					hasAudioForCommLoanPerson += 1;
				} else if (dataType.intValue() > 2) {
					break;
				}
			}

			// 判断共同贷款人是否有完整的资料
			if (hasAudioForCommLoanPerson == 0) {
				retMap.put("promptType", 3); // 提示：贷款至少需有一段共同借贷人录音
				retMap.put("promptCustomerName", borrowerBean.getCustomerName());
				return retMap;
			}
			// if ((householdPhotoCountForCommLoanPerson == 0)
			// || (homePhotoCountForCommLoanPerson < 3) ||
			// (headPhotoCountForCommLoanPerson == 0)
			// || (cardPhotoPlusCountForCommLoanPerson == 0)
			// || (cardPhotoMinusCountForCommLoanPerson == 0)) {
			// retMap.put("promptType", 4); //
			// 提示：贷款审批至少需要共同借贷人三张身份证照片、一张户籍照片、三张家庭照片！
			// retMap.put("promptCustomerName", borrowerBean.getCustomerName());
			// return retMap;
			// }
		}
		retMap.put("promptType", 0);
		return retMap;
	}

	/**
	 * 判断担保人是否有满足最低贷款要求的‘调查录音’和‘相关照片’
	 *
	 * @param loanDataList
	 *            该贷款相关的所有资料
	 * @param lnLoanGuarantorBeanList
	 *            所有担保人
	 * @return
	 */
	private Map<String, Object> processGuarantorData(
			List<LoanData> loanDataList,
			List<LnLoanGuarantorBean> lnLoanGuarantorBeanList) {
		Map<String, Object> retMap = new HashMap<String, Object>();

		for (LnLoanGuarantorBean guarantorBean : lnLoanGuarantorBeanList) {
			// 担保人资料
			// 是否存在担保人的录音资料
			int hasAudioForGuarantor = 0;
			// // 户口簿照片(至少1张)
			// int householdPhotoCountForGuarantor = 0;
			// // 家庭照片(至少3张)
			// int homePhotoCountForGuarantor = 0;
			// // 经营地照片张数(至少3张)
			// int manageDirPhotoCountForLoanPerson = 0;
			// // 头像照片
			// int headPhotoCountForGuarantor = 0;
			// // 身份证正面
			// int cardPhotoPlusForGuarantor = 0;
			// // 身份证背面
			// int cardPhotoMinusForGuarantor = 0;
			Integer customerId = guarantorBean.getCustomerId();
			for (LoanData loanData : loanDataList) {
				Integer queryCusId = loanData.getCustomerId();
				if (!customerId.equals(queryCusId)) {
					continue;
				}
				Integer dataType = loanData.getDataType();
				// Integer photoTypeId = loanData.getPhotoTypeId();
				// if (dataType.intValue() == 1) {
				// // 照片
				// // 担保人照片
				// if (photoTypeId.intValue() == 4) {
				// // 户口簿照片(至少1张)
				// // if (householdPhotoCountForGuarantor != 1) {
				// // householdPhotoCountForGuarantor = 1;
				// // }
				// householdPhotoCountForGuarantor += 1;
				// } else if ((photoTypeId.intValue() == 5)) {
				// // 家庭照片or经营地照片(至少1张)
				// // if (homePhotoCountForGuarantor != 1) {
				// // homePhotoCountForGuarantor = 1;
				// // }
				// homePhotoCountForGuarantor += 1;
				// } else if (photoTypeId.intValue() == 6) {
				// manageDirPhotoCountForLoanPerson += 1;
				// } else if (photoTypeId.intValue() == 1) {
				// // 头像照片
				// // if (headPhotoCountForGuarantor != 1) {
				// // headPhotoCountForGuarantor = 1;
				// // }
				// headPhotoCountForGuarantor += 1;
				// } else if (photoTypeId.intValue() == 2) {
				// // 身份证正面
				// // if (cardPhotoPlusForGuarantor != 1) {
				// // cardPhotoPlusForGuarantor = 1;
				// // }
				// cardPhotoPlusForGuarantor += 1;
				// } else if (photoTypeId.intValue() == 3) {
				// // 身份证背面
				// // if (cardPhotoMinusForGuarantor != 1) {
				// // cardPhotoMinusForGuarantor = 1;
				// // }
				// cardPhotoMinusForGuarantor += 1;
				// }
				// } else
				if (dataType.intValue() == 2) {
					// 录音
					// 找到了担保人录音资料
					if (hasAudioForGuarantor != 1) {
						hasAudioForGuarantor = 1;
					}
				} else if (dataType.intValue() > 2) {
					break;
				}
			}
			// 判断担保人是否有完整的资料
			if (hasAudioForGuarantor != 1) {
				retMap.put("promptType", 5); // 提示：贷款至少需有一段担保人录音！
				retMap.put("promptCustomerName",
						guarantorBean.getCustomerName());
				return retMap;
			}
			// if ((householdPhotoCountForGuarantor == 0)
			// || (homePhotoCountForGuarantor < 3 &&
			// manageDirPhotoCountForLoanPerson < 3)
			// || (headPhotoCountForGuarantor == 0) ||
			// (cardPhotoPlusForGuarantor == 0)
			// || (cardPhotoMinusForGuarantor == 0)) {
			// retMap.put("promptType", 6); //
			// 提示：贷款审批至少需要担保人三张身份证照片、一张户籍照片、三张家庭照片或经营地照片！
			// retMap.put("promptCustomerName",
			// guarantorBean.getCustomerName());
			// return retMap;
			// }
		}
		retMap.put("promptType", 0);
		return retMap;
	}

	// 获取预生成催收日志ID
	public Integer getNewDunLogId() {
		try {
			Integer result = lnDunLogService.getNextDunLogId();
			return result;
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % getNewDunLogId", e);
			return null;
		}
	}

	// 获取预生成异常催收日志ID
	public Integer getNewExceptionDunLogId() {
		try {
			Integer result = lnExceptionDunLogService
					.getNextExceptionDunLogId();
			return result;
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % getNewDunLogId", e);
			return null;
		}
	}

	/**
	 * BigDecamal转换成String
	 *
	 * @param bigDecimal
	 * @return
	 */
	private String bigDecimalToString(BigDecimal bigDecimal) {
		String str = bigDecimal.toString();
		int index = str.indexOf(".");
		if (index != -1) {
			if (str.length() - index >= 2) {
				str = str.substring(0, index + 3);
			}
		}
		return str;
	}

	/**
	 * 把String转换成BigDecimal
	 *
	 * @param paramStr
	 * @return
	 */
	private BigDecimal stringToBigDecimal(String paramStr) {
		BigDecimal bigDecimal = new BigDecimal(paramStr);
		return bigDecimal;
	}

	/**
	 * 将得到的贷款列表转换为只带loanId的贷款的前端显示
	 *
	 * @param lnLoans
	 * @return
	 */
	private Map<String, Object> loanIdCountToMap(List<LnLoan> lnLoans) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (lnLoans != null && lnLoans.size() > 0) {
			List<Map<String, Integer>> loanIdList = new ArrayList<Map<String, Integer>>();
			for (LnLoan lnLoan : lnLoans) {
				Map<String, Integer> curMap = new HashMap<String, Integer>();
				curMap.put("loanId", lnLoan.getLoanId());
				loanIdList.add(curMap);
			}
			resultMap.put("loanList", loanIdList);
			resultMap.put("numCount", loanIdList.size());
		} else {
			resultMap.put("loanList", null);
			resultMap.put("numCount", 0);
		}

		return resultMap;
	}

	/**
	 * 验证客户为不良客户
	 *
	 * @param account
	 * @param customerId
	 * @return
	 */
	public String isNoGood(String account, Integer customerId) {
		try {
			logger.info("pad端开始验证客户是否为不良客户，当前登录用户为“" + account + "”,客户id为“"
					+ customerId + "”");
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("customerId", customerId);
			Boolean isNogood = crmCustomerService.checkIsNogoodCus(paramMap);
			if (isNogood) {
				return "true";
			}
			logger.info("pad端完成验证客户是否为不良客户，当前登录用户为“" + account + "”,客户id为“"
					+ customerId + "”");
			return "false";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % isNoGood", e);
			return "false";
		}
	}

	/**
	 * 贷款创建时间与提交时间超过time小时的贷款
	 *
	 * @param account
	 * @param time
	 *            贷款创建时间与提交时间不能超过time小时
	 * @param time1
	 *            贷款创建时间与提交时间未超过time1小时，不算time1时刻
	 * @return
	 */
	public String numNowNeedSubmitLoan(String account, Integer time,
									   Integer time1) {
		try {
			logger.info("pad端开始查询超时未操作贷款，当前登录用户为“" + account
					+ "”,贷款创建时间与提交时间不能超过“" + time + "”,贷款创建时间与提交时间未超过" + time1
					+ "”");
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// 判断用户是否为业务主管
			// boolean isInCharge =
			// deptFacadeService.isInChargeOfDepartment(String.valueOf(sysUser
			// .getUserId()));
			// if (isInCharge) {
			// // 业务主管
			// // 业务主管的下属人员id集合
			// Integer[] applyUserIds = this.getSysUserIds(sysUser.getUserId());
			// StringBuilder sb = new StringBuilder();
			// for (Integer userId : applyUserIds) {
			// sb.append(String.valueOf(userId));
			// sb.append(",");
			// }
			// String managerUserIds = null;
			// if (sb.length() > 0) {
			// managerUserIds = sb.substring(0, sb.length() - 1);
			// }
			// paramMap.put("userIds", managerUserIds);
			// } else {
			// // 客户经理
			// paramMap.put("userId", sysUser.getUserId());
			// }
			// 只显示自己负责的申请贷款
			paramMap.put("userId", sysUser.getUserId());

			paramMap.put("loanStatusId", 1);
			List<LnLoan> lnLoanList = lnLoanService.getNowNeedHandleLoanList(
					paramMap, time, time1);
			Map<String, Object> resultMap = this.loanIdCountToMap(lnLoanList);
			String result = JSONArray.fromObject(resultMap).toString();
			logger.info("pad端完成查询超时未操作贷款，当前登录用户为“" + account
					+ "”,贷款创建时间与提交时间不能超过“" + time + "”,贷款创建时间与提交时间未超过" + time1
					+ "”");
			return result;
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % numNowNeedSubmitLoan", e);
			return "false";
		}
	}

	/**
	 * 系统新增几笔贷款需该用户分配(主要查找该用户下的新增的待分配贷款)
	 *
	 * @param account
	 * @return
	 */
	public String numNewNeedAssignLoan(String account) {
		try {
			logger.info("pad端开始查询新增待分配贷款，当前登录用户为“" + account + "”");
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// 判断用户是否为业务主管
			boolean isInCharge = deptFacadeService
					.isInChargeOfDepartment(String.valueOf(sysUser.getUserId()));
			if (isInCharge) {
				// 业务主管
				// 业务主管的下属人员id集合
				Integer[] applyUserIds = this
						.getSysUserIds(sysUser.getUserId());
				StringBuilder sb = new StringBuilder();
				for (Integer userId : applyUserIds) {
					sb.append(String.valueOf(userId));
					sb.append(",");
				}
				String managerUserIds = null;
				if (sb.length() > 0) {
					managerUserIds = sb.substring(0, sb.length() - 1);
				}
				paramMap.put("userIds", managerUserIds);
			} else {
				// 客户经理
				// paramMap.put("userId",sysUser.getUserId());
				return "false";
			}
			paramMap.put("loanStatusId", 2);
			List<LnLoan> loanList = lnLoanService
					.getNewLoanForWarning(paramMap);
			// 批量更新贷款的LAST_CLIENT_QUERY_DATE
			StringBuilder sb = new StringBuilder();
			for (LnLoan lnLoan : loanList) {
				sb.append(String.valueOf(lnLoan.getLoanId() + ","));
			}
			String loanIds = null;
			if (sb.length() > 0) {
				loanIds = sb.substring(0, sb.length() - 1);
			}
			if (loanIds != null) {
				Map<String, Object> pMap = new HashMap<String, Object>();
				pMap.put("lastClientQueryDate", new Date());
				pMap.put("loanIds", loanIds);
				lnLoanService.updateLnLoanById(pMap);
			}
			Map<String, Object> resultMap = this.loanIdCountToMap(loanList);
			String result = JSONArray.fromObject(resultMap).toString();
			logger.info("pad端完成查询新增待分配贷款，当前登录用户为“" + account + "”");
			return result;
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % numNewNeedAssignLoan", e);
			return "false";
		}
	}

	/**
	 * 贷款提交分配后到贷款分配操作完成超过time小时的贷款
	 *
	 * @param account
	 * @param time
	 *            贷款提交分配后到贷款分配操作完成不能超过time小时
	 * @param time1
	 *            贷款提交分配后到贷款分配操作完成未超过time1小时，不算time1时刻
	 * @return
	 */
	public String numNowNeedAssignLoan(String account, Integer time,
									   Integer time1) {
		try {
			logger.info("pad端开始查询超时未分配贷款，当前登录用户为“" + account
					+ "”,贷款提交分配后到贷款分配操作完成不能超过“" + time
					+ "”,贷款提交分配后到贷款分配操作完成未超过“" + time1 + "”");
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// 判断用户是否为业务主管
			boolean isInCharge = deptFacadeService
					.isInChargeOfDepartment(String.valueOf(sysUser.getUserId()));
			if (isInCharge) {
				// 业务主管
				// 业务主管的下属人员id集合
				Integer[] applyUserIds = this
						.getSysUserIds(sysUser.getUserId());
				StringBuilder sb = new StringBuilder();
				for (Integer userId : applyUserIds) {
					sb.append(String.valueOf(userId));
					sb.append(",");
				}
				String managerUserIds = null;
				if (sb.length() > 0) {
					managerUserIds = sb.substring(0, sb.length() - 1);
				}
				paramMap.put("userIds", managerUserIds);
			} else {
				// 客户经理
				// paramMap.put("userId",sysUser.getUserId());
				return "false";
			}
			paramMap.put("loanStatusId", 2);
			List<LnLoan> lnLoanList = lnLoanService.getNowNeedHandleLoanList(
					paramMap, time, time1);
			Map<String, Object> resultMap = this.loanIdCountToMap(lnLoanList);
			String result = JSONArray.fromObject(resultMap).toString();
			logger.info("pad端完成查询超时未分配贷款，当前登录用户为“" + account
					+ "”,贷款提交分配后到贷款分配操作完成不能超过“" + time
					+ "”,贷款提交分配后到贷款分配操作完成未超过“" + time1 + "”");
			return result;
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % numNowNeedAssignLoan", e);
			return "false";
		}
	}

	/**
	 * 该用户新增几笔被分配的贷款
	 *
	 * @param account
	 * @return
	 */
	public String numNewBeenAssignedLoan(String account) {
		try {
			logger.info("pad端开始查询该用户新增几笔被分配的贷款，当前登录用户为“" + account + "”");
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// 判断用户是否为业务主管
			// boolean isInCharge =
			// deptFacadeService.isInChargeOfDepartment(String.valueOf(sysUser
			// .getUserId()));
			// if (isInCharge) {
			// // 业务主管
			// // 业务主管的下属人员id集合
			// Integer[] applyUserIds = this.getSysUserIds(sysUser.getUserId());
			// StringBuilder sb = new StringBuilder();
			// for (Integer userId : applyUserIds) {
			// sb.append(String.valueOf(userId));
			// sb.append(",");
			// }
			// String managerUserIds = null;
			// if (sb.length() > 0) {
			// managerUserIds = sb.substring(0, sb.length() - 1);
			// }
			// paramMap.put("userIds", managerUserIds);
			// } else {
			// // 客户经理
			// paramMap.put("userId",sysUser.getUserId());
			// }
			// 只显示自己负责的调查贷款
			paramMap.put("userId", sysUser.getUserId());

			paramMap.put("loanStatusId", 3);
			List<LnLoan> loanList = lnLoanService
					.getNewLoanForWarning(paramMap);
			// 批量更新贷款的LAST_CLIENT_QUERY_DATE
			StringBuilder sb = new StringBuilder();
			for (LnLoan lnLoan : loanList) {
				sb.append(String.valueOf(lnLoan.getLoanId() + ","));
			}
			String loanIds = null;
			if (sb.length() > 0) {
				loanIds = sb.substring(0, sb.length() - 1);
			}
			if (loanIds != null) {
				Map<String, Object> pMap = new HashMap<String, Object>();
				pMap.put("lastClientQueryDate", new Date());
				pMap.put("loanIds", loanIds);
				lnLoanService.updateLnLoanById(pMap);
			}
			Map<String, Object> resultMap = this.loanIdCountToMap(loanList);
			String result = JSONArray.fromObject(resultMap).toString();
			logger.info("pad端完成查询该用户新增几笔被分配的贷款，当前登录用户为“" + account + "”");
			return result;
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % numNewNeedAssignLoan", e);
			return "false";
		}
	}

	/**
	 * 贷款分配后到贷款调查完成提交审批时间超过time小时的贷款
	 *
	 * @param account
	 * @param time
	 *            贷款分配后到贷款调查完成提交审批不能超过time小时
	 * @param time1
	 *            贷款分配后到贷款调查完成提交审批未超过time1小时，不算time1时刻
	 * @return
	 */
	public String numNowNeedResearchLoan(String account, Integer time,
										 Integer time1) {
		try {
			logger.info("pad端开始查询超时未提交审批的贷款，当前登录用户为“" + account
					+ "”,贷款分配后到贷款调查完成提交审批不能超过“" + time
					+ "”,贷款分配后到贷款调查完成提交审批未超过“" + time1 + "”");
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// 判断用户是否为业务主管
			boolean isInCharge = deptFacadeService
					.isInChargeOfDepartment(String.valueOf(sysUser.getUserId()));
			if (isInCharge) {
				// 业务主管
				// 业务主管的下属人员id集合
				Integer[] applyUserIds = this
						.getSysUserIds(sysUser.getUserId());
				StringBuilder sb = new StringBuilder();
				for (Integer userId : applyUserIds) {
					sb.append(String.valueOf(userId));
					sb.append(",");
				}
				String managerUserIds = null;
				if (sb.length() > 0) {
					managerUserIds = sb.substring(0, sb.length() - 1);
				}
				paramMap.put("userIds", managerUserIds);
			} else {
				// 客户经理
				paramMap.put("userId", sysUser.getUserId());
			}
			paramMap.put("loanStatusId", 3);
			List<LnLoan> lnLoanList = lnLoanService.getNowNeedHandleLoanList(
					paramMap, time, time1);
			Map<String, Object> resultMap = this.loanIdCountToMap(lnLoanList);
			String result = JSONArray.fromObject(resultMap).toString();
			logger.info("pad端完成查询超时未提交审批的贷款，当前登录用户为“" + account
					+ "”,贷款分配后到贷款调查完成提交审批不能超过“" + time
					+ "”,贷款分配后到贷款调查完成提交审批未超过“" + time1 + "”");
			return result;
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % numNowNeedResearchLoan", e);
			return "false";
		}
	}

	/**
	 * 贷款放贷成功后到贷款完成落实时间超过time小时的贷款
	 *
	 * @param account
	 * @param time
	 *            贷款放贷成功后到贷款完成落实时间不能超过time小时
	 * @param time1
	 *            贷款放贷成功后到贷款完成落实时间未超过time1小时，不算time1时刻
	 * @return
	 */
	public String numNowNeedRepaymentLoan(String account, Integer time,
										  Integer time1) {
		try {
			logger.info("pad端开始查询超时未落实的贷款，当前登录用户为“" + account
					+ "”,贷款放贷成功后到贷款完成落实时间不能超过“" + time
					+ "”,贷款放贷成功后到贷款完成落实时间未超过“" + time1 + "”");
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// 判断用户是否为业务主管
			// boolean isInCharge =
			// deptFacadeService.isInChargeOfDepartment(String.valueOf(sysUser
			// .getUserId()));
			// if (isInCharge) {
			// // 业务主管
			// // 业务主管的下属人员id集合
			// Integer[] applyUserIds = this.getSysUserIds(sysUser.getUserId());
			// StringBuilder sb = new StringBuilder();
			// for (Integer userId : applyUserIds) {
			// sb.append(String.valueOf(userId));
			// sb.append(",");
			// }
			// String managerUserIds = null;
			// if (sb.length() > 0) {
			// managerUserIds = sb.substring(0, sb.length() - 1);
			// }
			// paramMap.put("userIds", managerUserIds);
			// } else {
			// // 客户经理
			// paramMap.put("userId", sysUser.getUserId());
			// }
			paramMap.put("userId", sysUser.getUserId());

			paramMap.put("loanStatusId", 6);
			List<LnLoan> lnLoanList = lnLoanService.getNowNeedHandleLoanList(
					paramMap, time, time1);
			Map<String, Object> resultMap = this.loanIdCountToMap(lnLoanList);
			String result = JSONArray.fromObject(resultMap).toString();
			logger.info("pad端完成查询超时未落实的贷款，当前登录用户为“" + account
					+ "”,贷款放贷成功后到贷款完成落实时间不能超过“" + time
					+ "”,贷款放贷成功后到贷款完成落实时间未超过“" + time1 + "”");
			return result;
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % numNowNeedRepaymentLoan", e);
			return null;
		}
	}

	/**
	 * 申请贷款中搜索所有客户，去权限
	 */
	public String queryCustomerByFilter(String account,String type,String idCard, String customerName, Integer pageNumber, String filter) {
		try {
			logger.info("pad端贷款接口queryCustomerByIdCardPhone开始，当前登录用户为“" + account + "”,身份证号“" + idCard + "”,客户名“" + customerName + "”,查询当前页“" + pageNumber + "”");

			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			if (sysUser == null) {
				logger.error("没找到此用户");
				return "false";
			}
			JSONArray jsonArray = new JSONArray();

			Page page = new Page();
			page.setCurrentPage(pageNumber);

			// 列表数据
			Map<String, Object> conds = new HashMap<String, Object>();
			conds.put("idCard", idCard);
			conds.put("customerName", customerName);
			if(!"0".equals(type)) {
				conds.put("cusType", type);
			}
			if(StringUtils.isNotEmpty(filter)&&filter.equals("true")){
				conds.put("filter", filter);
				conds.put("userId", sysUser.getUserId());
			}

			PageUtil<LnLoanCustomerBean> dataList = lnLoanService.getAllCustomerBeanByConds(conds, page);

			PadCustomerPage padCustomerPage = new PadCustomerPage();
			// List<PadCustomerLoan> pList = new ArrayList<PadCustomerLoan>();
			// for (LnLoanCustomerBean bean : dataList.getItems()) {
			// PadCustomerLoan object = new PadCustomerLoan();
			// object.setCustomerId(bean.getCustomerId());
			// object.setCustomerName(bean.getCustomerName());
			// object.setPhone(bean.getCphNumber());
			// object.setAddress(bean.getAddress());
			// object.setIdCard(bean.getIdCard());
			// object.setIsNoGood(bean.getIsNoGood());
			// pList.add(object);
			// }
			padCustomerPage.setDataList(dataList.getItems());
			padCustomerPage.setPageCount(dataList.getPage()
					.getTotalRowsAmount());
			String result = JSONArray.fromObject(padCustomerPage).toString();
			logger.info("pad端贷款接口queryCustomerByIdCardPhone完成，当前登录用户为“"
					+ account + "”,身份证号“" + idCard + "”,客户名“" + customerName
					+ "”,查询当前页“" + pageNumber + "”");
			return result;
		} catch (Exception e) {
			logger.error("queryCustomerByIdCardPhone", e);
			return null;
		}
	}

	/**
	 * 申请贷款中搜索所有客户，去权限
	 */
	public String queryCustomerByIdCardPhone(String account, String idCard,
											 String customerName, Integer pageNumber) {
		try {
			logger.info("pad端贷款接口queryCustomerByIdCardPhone开始，当前登录用户为“" + account + "”,身份证号“" + idCard + "”,客户名“" + customerName + "”,查询当前页“" + pageNumber + "”");

			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			if (sysUser == null) {
				logger.error("没找到此用户");
				return "false";
			}

			JSONArray jsonArray = new JSONArray();

			Page page = new Page();
			page.setCurrentPage(pageNumber);

			// 列表数据
			Map<String, Object> conds = new HashMap<String, Object>();
			conds.put("idCard", idCard);
			conds.put("customerName", customerName);

			PageUtil<LnLoanCustomerBean> dataList = lnLoanService.getAllCustomerBeanByConds(conds, page);

			PadCustomerPage padCustomerPage = new PadCustomerPage();
			// List<PadCustomerLoan> pList = new ArrayList<PadCustomerLoan>();
			// for (LnLoanCustomerBean bean : dataList.getItems()) {
			// PadCustomerLoan object = new PadCustomerLoan();
			// object.setCustomerId(bean.getCustomerId());
			// object.setCustomerName(bean.getCustomerName());
			// object.setPhone(bean.getCphNumber());
			// object.setAddress(bean.getAddress());
			// object.setIdCard(bean.getIdCard());
			// object.setIsNoGood(bean.getIsNoGood());
			// pList.add(object);
			// }
			padCustomerPage.setDataList(dataList.getItems());
			padCustomerPage.setPageCount(dataList.getPage()
					.getTotalRowsAmount());
			String result = JSONArray.fromObject(padCustomerPage).toString();
			logger.info("pad端贷款接口queryCustomerByIdCardPhone完成，当前登录用户为“"
					+ account + "”,身份证号“" + idCard + "”,客户名“" + customerName
					+ "”,查询当前页“" + pageNumber + "”");
			return result;
		} catch (Exception e) {
			logger.error("queryCustomerByIdCardPhone", e);
			return null;
		}
	}

	/**
	 * 更换贷款人-搜索客户（身份证&联系电话，姓名）
	 *
	 * @param account
	 * @param idCard
	 * @param customerName
	 * @param phone
	 * @param pageNumber
	 * @return
	 * @see com.banger.mobile.facade.webservice.LoanWebService#queryCustomerByIdCardPhoneName(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.Integer)
	 */
	public String queryCustomerByIdCardPhoneName(String account, String idCard,
												 String customerName, String phone, Integer pageNumber) {
		try {
			logger.info("pad端贷款接口queryCustomerByIdCardPhoneName开始，当前登录用户为“"
					+ account + "”,身份证号“" + idCard + "”,客户名“" + customerName
					+ "”,查询当前页“" + pageNumber + "”,电话号码“" + phone + "”");
			SysUser sysUser = sysUserService.getAllUserByAccount(account);

			JSONArray jsonArray = new JSONArray();

			Page page = new Page();
			page.setCurrentPage(pageNumber);

			// 列表数据
			Map<String, Object> conds = new HashMap<String, Object>();
			conds.put("idCard", idCard);
			conds.put("customerName", customerName);
			conds.put("cphNumber", phone);

			// 过滤掉不良客户、非当前用户的归属客户以及三个月内有审批失败的客户
			conds.put("filter", "filter");
			conds.put("userId", sysUser.getUserId());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(2, -3);
			String monthDate = sf.format(calendar.getTime());// 从今天开始算三个月前的时间
			conds.put("approveFailDate", monthDate);

			PageUtil<LnLoanCustomerBean> dataList = lnLoanService
					.getAllCustomerBeanByConds(conds, page);

			PadCustomerPage padCustomerPage = new PadCustomerPage();
			// List<PadCustomerLoan> pList = new ArrayList<PadCustomerLoan>();
			// for (LnLoanCustomerBean bean : dataList.getItems()) {
			// PadCustomerLoan object = new PadCustomerLoan();
			// object.setCustomerId(bean.getCustomerId());
			// object.setCustomerName(bean.getCustomerName());
			// object.setPhone(bean.getCphNumber());
			// object.setAddress(bean.getAddress());
			// object.setIdCard(bean.getIdCard());
			// object.setIsNoGood(bean.getIsNoGood());
			// pList.add(object);
			// }
			padCustomerPage.setDataList(dataList.getItems());
			padCustomerPage.setPageCount(dataList.getPage()
					.getTotalRowsAmount());
			String result = jsonArray.fromObject(padCustomerPage).toString();
			logger.info("pad端贷款接口queryCustomerByIdCardPhoneName完成，当前登录用户为“"
					+ account + "”,身份证号“" + idCard + "”,客户名“" + customerName
					+ "”,查询当前页“" + pageNumber + "”,电话号码“" + phone + "”");
			return result;
		} catch (Exception e) {
			logger.error("queryCustomerByIdCardPhone", e);
			return null;
		}
	}

	/**
	 * 贷款审批
	 *
	 * @param account
	 * @param condition
	 *            customerName：客户姓名 phone：联系电话 idCard：身份证 loanType：贷款类型
	 *            approvalStart：提交审批开始时间 approvalEnd：提交审批结束时间
	 *            submitStart：贷款提交开始时间 submitEnd：贷款提交结束时间
	 * @return
	 */
	public String searchApprovalLoan(String account, String condition) {
		// TODO:修改
		return null;
		/*
		 * try { logger.info("pad端贷款接口searchApprovalLoan开始，当前登录用户为“" + account +
		 * "”,搜索条件“" + condition + "”"); SysUser sysUser =
		 * sysUserService.getAllUserByAccount(account); JsonConfig jsonConfig =
		 * new JsonConfig(); jsonConfig.registerJsonValueProcessor(Date.class,
		 * new JsonDateValueProcessor()); JSONObject jsonObject =
		 * JSONObject.fromObject(condition, jsonConfig);
		 * 
		 * Map<String, Object> stringObjectMap = (Map<String, Object>)
		 * JSONObject.toBean(jsonObject, Map.class);
		 * 
		 * // PadApprovalLoan approvalLoan =
		 * (PadApprovalLoan)JSONObject.toBean(jsonObject,PadApprovalLoan.class);
		 * Map<String, Object> parameterMap = new HashMap<String, Object>();
		 * 
		 * // boolean isInCharge =
		 * deptFacadeService.isInChargeOfDepartment(String
		 * .valueOf(sysUser.getUserId())); // if (isInCharge){ // Integer[]
		 * belongDeptIds =
		 * deptFacadeService.getInChargeOfDeptIds(sysUser.getUserId()); //
		 * parameterMap.put("belongDeptIds",belongDeptIds); // }else { //
		 * parameterMap.put("belongDeptId",sysUser.getDeptId()); // }
		 * 
		 * // 判断用户是否为业务主管 // boolean isInCharge =
		 * deptFacadeService.isInChargeOfDepartment
		 * (String.valueOf(sysUser.getUserId())); // if (isInCharge) { // //
		 * 业务主管 // // 业务主管的下属人员id集合 // Integer[] applyUserIds =
		 * this.getSysUserIds(sysUser.getUserId()); // StringBuilder sb = new
		 * StringBuilder(); // for (Integer userId : applyUserIds) { //
		 * sb.append(String.valueOf(userId)); // sb.append(","); // } // String
		 * managerUserIds = null; // if (sb.length() > 0) { // managerUserIds =
		 * sb.substring(0, sb.length() - 1); // } //
		 * parameterMap.put("approvalUserIds", managerUserIds); // } else { //
		 * // 客户经理 // parameterMap.put("approvalDeptId", sysUser.getDeptId());
		 * // }
		 * 
		 * parameterMap.put("loanStatusId", 4); parameterMap.put("customerName",
		 * stringObjectMap.get("customerName")); parameterMap.put("idCard",
		 * stringObjectMap.get("idCard")); parameterMap.put("phone",
		 * stringObjectMap.get("phone")); Integer loanTypeId = (Integer)
		 * stringObjectMap.get("loanType"); if (loanTypeId != -1) {
		 * parameterMap.put("loanTypeId", stringObjectMap.get("loanType")); }
		 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		 * if (stringObjectMap.get("approvalStart") != null &&
		 * !stringObjectMap.get("approvalStart").equals("")) {
		 * parameterMap.put("submitApproveStartDate", format.parse((String)
		 * stringObjectMap.get("approvalStart"))); } if
		 * (stringObjectMap.get("approvalEnd") != null &&
		 * !stringObjectMap.get("approvalEnd").equals("")) {
		 * parameterMap.put("submitApproveEndDate", format.parse((String)
		 * stringObjectMap.get("approvalEnd"))); } if
		 * (stringObjectMap.get("submitStart") != null &&
		 * !stringObjectMap.get("submitStart").equals("")) {
		 * parameterMap.put("applyStartDate", format.parse((String)
		 * stringObjectMap.get("submitStart"))); } if
		 * (stringObjectMap.get("submitEnd") != null &&
		 * !stringObjectMap.get("submitEnd").equals("")) {
		 * parameterMap.put("applyEndDate", format.parse((String)
		 * stringObjectMap.get("submitEnd"))); }
		 * 
		 * //判断用户的贷款额度 Integer userId = sysUser.getUserId(); String roleIds =
		 * sysUserService.getRoleIds(userId); List<Integer> roleIdList = new
		 * ArrayList<Integer>(); if (roleIds != null && !roleIds.equals("")) {
		 * String[] roleIdArr = roleIds.split(","); for (String roleId :
		 * roleIdArr) { roleIdList.add(Integer.parseInt(roleId)); } }
		 * //得到用户所能审批的最大贷款金额(单位：万元) BigDecimal limitMoney =
		 * lnLoanService.approveLimitMoney(userId, roleIdList); if (limitMoney
		 * == null || limitMoney.compareTo(new BigDecimal(0)) == 0) {
		 * //没有找到该用户的贷款处理限制额度，即，没有审批权限 return -2 + ""; }
		 * 
		 * if (limitMoney.compareTo(new BigDecimal(5)) <= 0) {
		 * //您的审批金额小于等于5万元，不能作为主审人审批贷款！ return -1 + ""; } //查看符合条件的贷款的总数 Integer
		 * count = lnLoanService.selectLoanCount(parameterMap);
		 * 
		 * if (count.intValue() == 0) { //没有找到相关的贷款 return -5 + ""; } else if
		 * (count.intValue() > 1) { //查找到多笔贷款，请输入更准确的搜索条件查找您要审批的贷款 return -4 +
		 * ""; } else if (count.intValue() == 1) {
		 * 
		 * //得到当前用户搜索出来的唯一贷款 LnLoan lnLoan =
		 * lnLoanService.selectLoanList(parameterMap); LnOpHistory lnOpHistory =
		 * lnOpHistoryService.selectHistoryByLoanId(lnLoan.getLoanId()); if
		 * (lnLoan != null) { if (lnLoan.getSurveyUserId().equals(userId)) {
		 * //您不能审批自己负责的贷款！ return -3 + ""; } else if
		 * ((lnLoan.getIsReject().equals(1) ||
		 * lnOpHistory.getBeforeStatusId().equals
		 * (LoanConstants.LOAN_APPROVAL_REFUSE_STATUS)) &&
		 * (lnLoan.getApproveUserId1() == null ||
		 * !lnLoan.getApproveUserId1().equals(userId))) {
		 * //用户请求搜索的贷款为审批驳回后贷款负责人重新提交审批的贷款，并且当前用户不是第一次该贷款的用户，提示
		 * //提示：被驳回的贷款只能由原审批人员审批，您不能审批该贷款！ return -6 + ""; } //贷款金额(单位：元)
		 * BigDecimal loanMoney = lnLoan.getLoanMoney(); if (loanMoney != null)
		 * { BigDecimal lnMoney = loanMoney.divide(new BigDecimal(10000));
		 * //比较限制金额与搜索出来的贷款的金额的大小 int compVal = limitMoney.compareTo(lnMoney);
		 * if (compVal == -1) { //限制金额大于贷款金额，不能审批 // return lnMoney.toString();
		 * return limitMoney.toString(); } } PadLoanPage padLoanPage = new
		 * PadLoanPage(); List<PadLoan> pList = new ArrayList<PadLoan>();
		 * PadLoan padLoan = this.lnLoanToPadLoan(lnLoan); pList.add(padLoan);
		 * padLoanPage.setDataList(pList); padLoanPage.setPageCount(1);
		 * JSONArray jsonArray = new JSONArray(); String result =
		 * jsonArray.fromObject(padLoanPage, jsonConfig).toString(); return
		 * result; } }
		 * 
		 * // if(count == 0){ // //没有找到相关的贷款 // return "false"; // }else
		 * if(count > 1){ // //查找到多笔贷款，请输入更准确的搜索条件查找您要审批的贷款 // return "false";
		 * // }else if(count == 1){ // //判断用户的贷款额度 // Integer userId =
		 * sysUser.getUserId(); // String roleIds =
		 * sysUserService.getRoleIds(userId); // List<Integer> roleIdList = new
		 * ArrayList<Integer>(); // if (roleIds != null && !roleIds.equals("")){
		 * // String[] roleIdArr = roleIds.split(","); // for (String roleId :
		 * roleIdArr){ // roleIdList.add(Integer.parseInt(roleId)); // } // } //
		 * //得到用户所能审批的最大贷款金额 // BigDecimal limitMoney =
		 * lnLoanService.approveLimitMoney(userId, roleIdList); // if(limitMoney
		 * == null){ // //没有找到该用户的贷款处理限制额度，即，没有审批权限 // return null; // } //
		 * //得到当前用户搜索出来的唯一贷款 // LnLoan lnLoan =
		 * lnLoanService.selectLoanList(parameterMap); // // BigDecimal
		 * loanMoney = lnLoan.getLoanMoney(); // if (loanMoney != null){ //
		 * //比较限制金额与搜索出来的贷款的金额的大小 fds // int compVal =
		 * limitMoney.compareTo(loanMoney); // if(compVal > 1){ //
		 * //限制金额大于贷款金额，不能审批 // return VmHelper.getDecimalWanMoney(limitMoney);
		 * // } // } // //搜索出符合条件的贷款，并将其以字符串的形式返回给用户 // PadLoanPage padLoanPage
		 * = new PadLoanPage(); // List<PadLoan> pList = new
		 * ArrayList<PadLoan>(); // PadLoan padLoan =
		 * this.lnLoanToPadLoan(lnLoan); // pList.add(padLoan); //
		 * padLoanPage.setDataList(pList); // padLoanPage.setPageCount(1); //
		 * JSONArray jsonArray = new JSONArray(); // String result =
		 * jsonArray.fromObject(padLoanPage, jsonConfig).toString(); // return
		 * result; // } logger.info("pad端贷款接口searchApprovalLoan完成，当前登录用户为“" +
		 * account + "”,搜索条件“" + condition + "”"); return null; } catch
		 * (Exception e) { logger.error("LoanWebServiceImpl % getApprovalLoan",
		 * e); return null; }
		 */}

	/**
	 * 3.90 调查拒绝贷款
	 *
	 * @param account
	 *            登录用户账号
	 * @param loanId
	 *            贷款id
	 * @param remark
	 *            备注
	 * @param cancelReasonId
	 *            拒绝原因id
	 * @param cancelReasonOther
	 *            其他拒绝原因
	 * @return
	 */
	public String surveyRefuse(String account, Integer loanId, String remark,
							   Integer cancelReasonId, String cancelReasonOther) {
		// TODO:修改
		return null;
		/*
		 * try { Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("loanId", loanId); LnLoanInfo loanInfo =
		 * lnLoanInfoService.selectLoanInfoList(map).get(0); String djSerno =
		 * loanInfo.getSerialNumber(); LnLoan lnLoan =
		 * lnLoanService.getLnLoanById(loanId); return "true";
		 * 
		 * MessageResult messageResult = sendMessageService.refuseLoan(djSerno,
		 * lnLoan, "3"); if (messageResult != null) { this.refuseLoan(account,
		 * loanId, remark, cancelReasonId, cancelReasonOther, 3, 13, "贷款调查拒绝");
		 * return "true"; } else { return "false"; } } catch (Exception e) {
		 * logger.error("LoanWebServiceImpl % surveyRefuse", e); return "false";
		 * }
		 */}

	/**
	 * 审批拒绝贷款
	 *
	 * @param account
	 *            登录用户账号
	 * @param loanId
	 *            贷款id
	 * @param remark
	 *            备注
	 * @param cancelReasonId
	 *            拒绝原因id
	 * @param cancelReasonOther
	 *            其他拒绝原因
	 * @return
	 */
	public String approvalRefuse(String account, Integer loanId, String remark,
								 Integer cancelReasonId, String cancelReasonOther,
								 String otherAccount, String approvalTime) {
		logger.info("pad端贷款接口approvalRefuse开始，当前登录用户:" + account + ",loanId:"
				+ loanId + ",remark:" + remark + ",cancelReasonId:"
				+ cancelReasonId + ",cancelReasonOther:" + cancelReasonOther
				+ ",otherAccount:" + otherAccount + ",approvalTime:"
				+ approvalTime);
		try {
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			SysUser commApprovePerson = sysUserService
					.getUserByAccount(otherAccount);
			// 更改当前贷款状态为申请拒绝(11);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			LnOpHistory lnOpHistory = new LnOpHistory();
			lnOpHistory.setUserId(sysUser.getUserId());
			lnOpHistory.setOpHistoryDate(new Date());
			lnOpHistory.setBeforeStatusId(LoanConstants.LOAN_APPROVAL_STATUS);
			lnOpHistory
					.setAfterStatusId(LoanConstants.LOAN_APPROVAL_REFUSE_STATUS);
			lnOpHistory.setContent("贷款审批拒绝，审批人" + sysUser.getUserName()
					+ "，共同审批人" + commApprovePerson.getUserName());
			lnOpHistory.setLoanId(loanId);
			if (cancelReasonOther != null && !cancelReasonOther.equals("")) {
				// 点击了其他拒绝原因
				paramMap.put("cancelReasonOther", cancelReasonOther);
				if (StringUtils.isNotEmpty(remark)) {
					lnOpHistory.setRemark(remark + "|" + cancelReasonOther);
				} else {
					lnOpHistory.setRemark(cancelReasonOther);
				}
			} else {
				// 没有点击其他拒绝原因，使用系统预置拒绝原因
				paramMap.put("cancelDate", new Date());
				paramMap.put("cancelReasonId", cancelReasonId);
				LnCancelReason lnCancelReason = lnCancelReasonService
						.selectCancelReasonById(cancelReasonId);
				if (StringUtils.isNotEmpty(remark)) {
					if (lnCancelReason != null) {
						lnOpHistory.setRemark(remark + "|"
								+ lnCancelReason.getCancelReasonName());
					} else {
						lnOpHistory.setRemark(remark);
					}
				} else {
					if (lnCancelReason != null) {
						lnOpHistory.setRemark(lnCancelReason
								.getCancelReasonName());
					}
				}
			}
			paramMap.put("cancelUserId", sysUser.getUserId());
			paramMap.put(
					"loanStatusId",
					LoanConstants.LOAN_AFTER_REFUSE_STATUS[LoanConstants.LOAN_APPROVAL_STATUS - 1]);
			paramMap.put("loanId", loanId);
			paramMap.put("approveUserId2", commApprovePerson.getUserId());
			paramMap.put("approveUserId1", sysUser.getUserId());
			if (approvalTime != null && !approvalTime.equals("")) {
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm");
				Date approvalUserDate = format.parse(approvalTime);
				if (approvalUserDate != null) {
					paramMap.put("approveFailDate1", approvalUserDate);
				}
			}
			paramMap.put("approveFailDate2", new Date());
			paramMap.put("approveRemark", remark);

			// 申请拒绝贷款
			lnLoanService.cancelLoan(paramMap, lnOpHistory);
			// this.refuseLoan(account,loanId,remark,cancelReasonId,cancelReasonOther,otherAccount,approvalTime,4,14,"审批拒绝贷款");
			logger.info("pad端贷款接口approvalRefuse完成，当前登录用户:" + account
					+ ",loanId:" + loanId + ",remark:" + remark
					+ ",cancelReasonId:" + cancelReasonId
					+ ",cancelReasonOther:" + cancelReasonOther
					+ ",otherAccount:" + otherAccount + ",approvalTime:"
					+ approvalTime);
			return "true";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % approvalRefuse", e);
			return "false";
		}
	}

	/**
	 * 验证共同审批人
	 *
	 * @param account
	 *            用户名
	 * @param userPass
	 *            密码
	 * @param approvalAccount
	 *            共同审批人用户名
	 * @return
	 */
	public String getApprovalUser(String account, Integer loanId,
								  String approvalAccount, String userPass) {
		// TODO:修改
		return null;
		/*
		 * logger.info("pad端贷款接口getApprovalUser开始，当前登录用户:" + account +
		 * ",loanId:" + loanId + ",approvalAccount:" + approvalAccount +
		 * ",userPass:" + userPass); try { //当前登录用户 SysUser sysUser =
		 * sysUserService.getUserByAccount(account); //得到系统用户信息 //共同审批人 SysUser
		 * user = sysUserService.getUserByAccount(approvalAccount.trim());
		 * LnLoan lnLoan = lnLoanService.getLnLoanById(loanId); LnOpHistory
		 * lnOpHistory =
		 * lnOpHistoryService.selectHistoryByLoanId(lnLoan.getLoanId()); Integer
		 * isReject = lnLoan.getIsReject(); Integer errorCode = 0; //验证共同审批人
		 * 
		 * //首先验证输入的用户名是否存在 if (user == null) { //用户名不存在 //提示“用户“XXX”，不存在，请重新输入
		 * errorCode = -2; } else if (user.getIsActived().equals(0)) {
		 * //其次验证用户是否被禁用 //用户当前被禁用 //提示“您已被禁止登录，请联系系统管理员” errorCode = -3; } else
		 * { //然后验证共同审批并非是当前登录用户 if
		 * (user.getUserId().equals(sysUser.getUserId())) {
		 * //共同审批人就是当前登录用户，验证不通过 // out.print("-1"); errorCode = -4; } else if
		 * (((isReject != null && isReject.equals(1)) || (lnOpHistory != null &&
		 * lnOpHistory
		 * .getBeforeStatusId().equals(LoanConstants.LOAN_APPROVAL_REFUSE_STATUS
		 * ))) && (lnLoan.getApproveUserId2() == null ||
		 * !user.getUserId().equals(lnLoan.getApproveUserId2()))) {
		 * //验证这次的共同审批人与上次审批驳回时的共同审批人是否为同一人，如果不是同一人，则验证不通过，并返回提示信息：
		 * //被驳回的贷款只能由原审批人员审批，您填写的共同审批人与贷款第一次审批的共同审人不一致！ errorCode = -8; } else
		 * { String password = user.getPassword(); //最后验证共同审批人输入信息是否正确 String
		 * encryptPass = Md5Encrypt.md5(userPass.trim()); if
		 * (password.equals(encryptPass)) { //验证用户填写的共同审批人为当前贷款负责人 if
		 * (user.getUserId().equals(lnLoan.getSurveyUserId())) { //
		 * out.print("-5"); errorCode = -6; } else { //用户填写的共同审批人审批额度为0 Integer
		 * userId = user.getUserId(); String roleIds =
		 * sysUserService.getRoleIds(userId); List<Integer> roleIdList = new
		 * ArrayList<Integer>(); if (roleIds != null && !roleIds.equals("")) {
		 * String[] roleIdArr = roleIds.split(","); for (String roleId :
		 * roleIdArr) { roleIdList.add(Integer.parseInt(roleId)); } }
		 * //审批额度(单位：万元) BigDecimal limitMoney =
		 * lnLoanService.approveLimitMoney(userId, roleIdList); if
		 * (limitMoney.compareTo(new BigDecimal(0)) == 0) { // out.print("-6");
		 * errorCode = -7; } else { //验证正确 //输出共同审批人的ID //
		 * out.print(user.getUserId()); // flag = user.getUserId()+""; errorCode
		 * = -1; } }
		 * 
		 * } else { //验证失败 //提示“密码不正确，请输入正确密码” // out.print("-2"); //验证失败
		 * errorCode = -5; } } } logger.info("pad端贷款接口getApprovalUser完成，当前登录用户:"
		 * + account + ",loanId:" + loanId + ",approvalAccount:" +
		 * approvalAccount + ",userPass:" + userPass); return errorCode + ""; }
		 * catch (Exception e) {
		 * logger.error("LoanWebServiceImpl % getApprovalUser", e); return null;
		 * }
		 */}

	/**
	 * 更换贷款人
	 *
	 * @param account
	 * @param loanJsonString
	 * @return
	 */
	public String changeLoan(String account, String loanJsonString) {
		logger.info("pad端贷款接口changeLoan开始，当前登录用户:" + account
				+ ",loanJsonString:" + loanJsonString);
		try {
			SysUser sysUser = sysUserService.getUserByAccount(account);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			JSONObject jsonObject = JSONObject.fromObject(loanJsonString,
					jsonConfig);

			Map<String, Object> stringObjectMap = (Map<String, Object>) JSONObject
					.toBean(jsonObject, Map.class);
			Integer loanId = (Integer) stringObjectMap.get("loanId");
			Integer oldCustomerId = (Integer) stringObjectMap
					.get("oldCustomerId");
			Integer customerId = (Integer) stringObjectMap.get("customerId");

			LnOpHistory lnOpHistory = new LnOpHistory();
			lnOpHistory.setUserId(sysUser.getUserId());
			lnOpHistory.setOpHistoryDate(new Date());
			lnOpHistory.setBeforeStatusId(3); // 待调查
			lnOpHistory.setAfterStatusId(3); // 待调查
			// lnOpHistory.setContent("贷款人由" + loanCustomerName + "改为" +
			// changeLoanCustomerName);
			lnOpHistory.setLoanId(loanId);

			lnLoanService.changeLoanPerson(loanId, oldCustomerId, customerId,
					lnOpHistory);
			logger.info("pad端贷款接口changeLoan完成，当前登录用户:" + account
					+ ",loanJsonString:" + loanJsonString);
			return loanId + "";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % changeLoan", e);
			return null;
		}
	}

	/**
	 * 贷款调查落实保存记录操作历史
	 *
	 * @param account
	 * @param loanId
	 * @param loanStatusId
	 * @param remark
	 * @return
	 */
	public String writeLoanHistory(String account, Integer loanId,
								   Integer loanStatusId, String remark) {
		try {
			logger.info("pad端贷款接口writeLoanHistory开始，当前登录用户:" + account
					+ ",loanId:" + loanId + ",loanStatusId:" + loanStatusId
					+ ",remark:" + remark);
			SysUser user = sysUserService.getUserByAccount(account);
			LnOpHistory lnOpHistory = new LnOpHistory();
			lnOpHistory.setUserId(user.getUserId());
			lnOpHistory.setOpHistoryDate(new Date());
			lnOpHistory.setBeforeStatusId(loanStatusId);
			lnOpHistory.setLoanId(loanId);
			lnOpHistory.setRemark(remark);
			if (loanStatusId.equals(LoanConstants.LOAN_ASK_STATUS)) {
				// 申请状态保存时，需记录贷款历史操作记录，其中操作内容记为：贷款保存
				lnOpHistory.setAfterStatusId(LoanConstants.LOAN_ASK_STATUS); // 待提交
				lnOpHistory.setContent("添加备注");
				if (StringUtils.isNotEmpty(remark) && !remark.trim().equals("")) {
					lnOpHistoryService.insertLnOpHistory(lnOpHistory);
				}
			} else if (loanStatusId.equals(LoanConstants.LOAN_EXAM_STATUS)) {
				// 上传贷款调查资料，需记录贷款历史操作记录，其中操作内容记为：保存调查资料
				lnOpHistory.setAfterStatusId(LoanConstants.LOAN_EXAM_STATUS);
				lnOpHistory.setContent("保存调查资料");
				lnOpHistoryService.insertLnOpHistory(lnOpHistory);
			} else if (loanStatusId.equals(LoanConstants.LOAN_APPROVAL_STATUS)) {
				lnOpHistory
						.setAfterStatusId(LoanConstants.LOAN_APPROVAL_STATUS);
				lnOpHistory.setContent("上传资料");
				lnOpHistoryService.insertLnOpHistory(lnOpHistory);
			} else if (loanStatusId.equals(LoanConstants.LOAN_LENDING_STATUS)) {
				lnOpHistory.setAfterStatusId(LoanConstants.LOAN_LENDING_STATUS);
				lnOpHistory.setContent("上传资料");
				lnOpHistoryService.insertLnOpHistory(lnOpHistory);
			} else if (loanStatusId.equals(LoanConstants.LOAN_LENDED_STATUS)) {
				// 上传贷款落实资料，需记录贷款历史操作记录，其中操作内容记为：上传贷后资料
				lnOpHistory.setAfterStatusId(LoanConstants.LOAN_LENDED_STATUS);
				lnOpHistory.setContent("上传贷后资料");
				lnOpHistoryService.insertLnOpHistory(lnOpHistory);
			} else if (loanStatusId.equals(LoanConstants.LOAN_ASSIGN_STATUS)) {
				lnOpHistory.setAfterStatusId(LoanConstants.LOAN_ASSIGN_STATUS);
				lnOpHistory.setContent("贷款分配添加备注");
				lnOpHistoryService.insertLnOpHistory(lnOpHistory);
			}
			logger.info("pad端贷款接口writeLoanHistory完成，当前登录用户:" + account
					+ ",loanId:" + loanId + ",loanStatusId:" + loanStatusId
					+ ",remark:" + remark);
			return "true";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % writeLoanHistory", e);
			return "false";
		}
	}

	/**
	 * 修改贷款图片备注
	 *
	 * @param account
	 *            账号
	 * @param uuid
	 *            图片uuid，这个值是全局惟一的
	 * @param remark
	 *            备注
	 * @return
	 */
	public String addEditPhotoRemark(String account, String uuid, String remark) {
		try {
			logger.info("pad端贷款接口addEditPhotoRemark开始，当前登录用户:" + account
					+ ",uuid:" + uuid + ",remark:" + remark);
			if (StringUtils.isNotEmpty(uuid)) {
				Photo photo = new Photo();
				photo.setDatUuid(uuid);
				photo.setRemark(remark);
				dataPhotoService.updatePhotoRemark(photo);
				logger.info("pad端贷款接口addEditPhotoRemark完成，当前登录用户:" + account
						+ ",uuid:" + uuid + ",remark:" + remark);
				return "true";
			}
			logger.info("pad端贷款接口addEditPhotoRemark完成，当前登录用户:" + account
					+ ",uuid:" + uuid + ",remark:" + remark);
			return "false";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % addEditPhotoRemark", e);
			return "false";
		}
	}

	public String editRecordRemark(String account, String uuid, String remark) {
		try {
			logger.info("pad端贷款接口editRecordRemark开始，当前登录用户:" + account
					+ ",uuid:" + uuid + ",remark:" + remark);
			if (StringUtils.isNotEmpty(uuid)) {
				Audio a = new Audio();
				a.setDatUuid(uuid);
				a.setRemark(remark);
				dataAudioService.updateAudioRemark(a);
				logger.info("pad端贷款接口editRecordRemark完成，当前登录用户:" + account
						+ ",uuid:" + uuid + ",remark:" + remark);
				return "true";
			}
			logger.info("pad端贷款接口editRecordRemark完成，当前登录用户:" + account
					+ ",uuid:" + uuid + ",remark:" + remark);
			return "false";
		} catch (Exception e) {
			logger.error("editRecordRemark % editRecordRemark", e);
			return "false";
		}
	}

	public String editVideoRemark(String account, String uuid, String remark) {
		try {
			logger.info("pad端贷款接口editVideoRemark开始，当前登录用户:" + account
					+ ",uuid:" + uuid + ",remark:" + remark);
			if (StringUtils.isNotEmpty(uuid)) {
				Video v = new Video();
				v.setDatUuid(uuid);
				v.setRemark(remark);
				dataVideoService.updateVideoRemark(v);
				logger.info("pad端贷款接口editVideoRemark完成，当前登录用户:" + account
						+ ",uuid:" + uuid + ",remark:" + remark);
				return "true";
			}
			logger.info("pad端贷款接口editVideoRemark完成，当前登录用户:" + account
					+ ",uuid:" + uuid + ",remark:" + remark);
			return "false";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % addEditPhotoRemark", e);
			return "false";
		}
	}

	/**
	 * 修改贷款照片类别
	 *
	 * @param account
	 * @param uuid
	 * @param typeId
	 * @return
	 */
	public String addEditPhotoType(String account, String uuid, Integer typeId,
								   String customerName, Integer eventId) {
		try {
			logger.info("pad端贷款接口addEditPhotoType开始，当前登录用户:" + account
					+ ",uuid:" + uuid + ",typeId:" + typeId + ",customerName:"
					+ customerName + ",eventId:" + eventId);
			if (StringUtils.isNotEmpty(uuid)) {
				Photo photo = new Photo();
				photo.setDatUuid(uuid);
				photo = dataPhotoService.getPhotoByUUID(photo);
				photo.setPhotoTypeId(typeId);
				String photoName = "";
				if (StringUtils.isNotEmpty(customerName)) {
					photoName += customerName + "_";
				}
				if (eventId != null) {
					Event event = customerDataService
							.getEventTypeListById(eventId);
					if (event != null) {
						if (StringUtils.isNotEmpty(event.getEventName())) {
							photoName += event.getEventName() + "_";
						}
					}
				}
				String typeName = this.getPhoteType(String.valueOf(typeId));
				if (StringUtils.isNotEmpty(typeName)) {
					photoName += typeName + "_";
				}
				Date recordDate = photo.getRecordDate();
				if (recordDate != null) {
					photoName += DateUtil.convertDateToString(
							"yyyyMMdd(HHmmss)", recordDate);
				}
				photo.setPhotoName(photoName);
				dataPhotoService.updatePhotoRemark(photo);
				logger.info("pad端贷款接口addEditPhotoType完成，当前登录用户:" + account
						+ ",uuid:" + uuid + ",typeId:" + typeId
						+ ",customerName:" + customerName + ",eventId:"
						+ eventId);
				return "true";
			}
			logger.info("pad端贷款接口addEditPhotoType完成，当前登录用户:" + account
					+ ",uuid:" + uuid + ",typeId:" + typeId + ",customerName:"
					+ customerName + ",eventId:" + eventId);
			return "false";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % addEditPhotoType", e);
			return "false";
		}
	}

	/**
	 * 标记贷款余额已足
	 *
	 * @param account
	 * @param loanId
	 * @param isEnough
	 *            1表示标记，0表示取消标记
	 * @return
	 */
	public String markLoanRemainEnough(String account, Integer loanId,
									   Integer isEnough) {
		try {
			logger.info("pad端贷款接口markLoanRemainEnough开始，当前登录用户:" + account
					+ ",loanId:" + loanId + ",isEnough:" + isEnough);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("loanId", loanId);
			param.put("isFull", isEnough);
			lnLoanService.updateLnLoanById(param);
			logger.info("pad端贷款接口markLoanRemainEnough完成，当前登录用户:" + account
					+ ",loanId:" + loanId + ",isEnough:" + isEnough);
			return "true";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % markLoanRemainEnough", e);
			return "false";
		}
	}

	/**
	 * 获取贷款同步信息
	 *
	 * @param loanId
	 * @return
	 */
	public String getLoanInfos(Integer loanId) {
		try {
			logger.info("pad端贷款接口getLoanInfos开始，loanId:" + loanId);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("loanId", loanId);
			List<LnLoanInfo> loanInfoList = lnLoanInfoService
					.selectLoanInfoList(param); // 已优化
			if (loanInfoList != null && loanInfoList.size() > 0) {
				LnLoanInfo lnLoanInfo = loanInfoList.get(0);
				String result = JSONObject.fromObject(lnLoanInfo, jsonConfig)
						.toString();
				logger.info("pad端贷款接口getLoanInfos完成，loanId:" + loanId
						+ ",result:" + result);
				return result;
			}
			return "false";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % getLoanInfos", e);
			return "false";
		}
	}

	/**
	 * 拒绝贷款统一方法
	 *
	 * @param account
	 * @param loanId
	 * @param remark
	 * @param cancelReasonId
	 * @param cancelReasonOther
	 * @return
	 */
	private void refuseLoan(String account, Integer loanId, String remark,
							Integer cancelReasonId, String cancelReasonOther,
							Integer loanStatusId, Integer afterLoanStatusId, String content) {
		logger.info("pad端开始拒绝贷款，当前登录用户“" + account + "”,贷款id“" + loanId
				+ "”,备注“" + remark + "”,拒绝原因id“" + cancelReasonId + "”,其他拒绝原因“"
				+ cancelReasonOther + "”," + "当前贷款状态id“" + loanStatusId
				+ "”,拒绝后贷款状态id“" + afterLoanStatusId + "”,历史操作内容“" + content
				+ "”");
		SysUser sysUser = sysUserService.getAllUserByAccount(account);
		// 更改当前贷款状态为申请拒绝(11);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		LnOpHistory lnOpHistory = new LnOpHistory();
		lnOpHistory.setUserId(sysUser.getUserId());
		lnOpHistory.setOpHistoryDate(new Date());
		lnOpHistory.setBeforeStatusId(loanStatusId);
		lnOpHistory.setAfterStatusId(afterLoanStatusId);
		lnOpHistory.setContent(content);
		lnOpHistory.setLoanId(loanId);
		String historyRemark = "";
		if (cancelReasonOther != null && !cancelReasonOther.equals("")) {
			// 点击了其他拒绝原因
			paramMap.put("cancelReasonOther", cancelReasonOther);
			if (StringUtils.isNotEmpty(remark) && !remark.trim().equals("")) {
				historyRemark = remark + "|";
			}
			historyRemark += cancelReasonOther;
			lnOpHistory.setRemark(historyRemark);
		} else if (cancelReasonId != null && !cancelReasonId.equals(-1)) {
			LnCancelReason cancelReason = lnCancelReasonService
					.getCancelReasonById(cancelReasonId);
			// 没有点击其他拒绝原因，使用系统预置拒绝原因
			paramMap.put("cancelDate", new Date());
			paramMap.put("cancelReasonId", cancelReasonId);
			if (StringUtils.isNotEmpty(remark) && !remark.trim().equals("")) {
				historyRemark = remark + "|";
			}
			historyRemark = historyRemark + cancelReason.getCancelReasonName();
			lnOpHistory.setRemark(historyRemark);
		}
		paramMap.put("cancelUserId", sysUser.getUserId());
		paramMap.put("loanStatusId", afterLoanStatusId);
		paramMap.put("loanId", loanId);

		// 申请拒绝贷款
		lnLoanService.cancelLoan(paramMap, lnOpHistory);
		logger.info("pad端完成拒绝贷款，当前登录用户“" + account + "”,贷款id“" + loanId
				+ "”,备注“" + remark + "”,拒绝原因id“" + cancelReasonId + "”,其他拒绝原因“"
				+ cancelReasonOther + "”," + "当前贷款状态id“" + loanStatusId
				+ "”,拒绝后贷款状态id“" + afterLoanStatusId + "”,历史操作内容“" + content
				+ "”");
	}

	private String getPhoteType(String typeId) {
		// 1 家庭环境
		// 2 经营环境
		// 3 权属
		// 4 个人资产
		// 5 个人基本信息
		// 6 其他
		String result = "";
		if (typeId != null) {
			if (typeId.equals("1")) {
				result = "家庭环境";
			} else if (typeId.equals("2")) {
				result = "经营环境";
			} else if (typeId.equals("3")) {
				result = "权属";
			} else if (typeId.equals("4")) {
				result = "个人资产";
			} else if (typeId.equals("5")) {
				result = "个人基本信息";
			} else if (typeId.equals("6")) {
				result = "其他";
			}
		}
		return result;
	}

	/**
	 * 获取申请表字典信息
	 *
	 * @param type
	 * @return
	 */
	public String getAppFormDics(String type) {
		logger.info("pad端开始获取申请表字典信息，类型：" + type);
		try {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("type", type);
			
			/*
			 * List<BaseSDic> dataList = sDicService.queryDicByType(param);
			 * 
			 * String result = JSONArray.fromObject(dataList,
			 * jsonConfig).toString();
			 */
			return "";
		} catch (Exception e) {
			logger.error("getAppFormDics", e);
			return "false";
		}
	}

	/**
	 * 获取当前登录用户主管列表
	 *
	 * @param account
	 * @return
	 */
	public String getManagerUserList(String account) {
		logger.info("pad端开始获取当前登录用户主管列表，当前登录用户：" + account);
		try {
			JSONArray jsonArray = new JSONArray();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());

			if (StringUtils.isNotEmpty(account)) {
				SysUser sysUser = sysUserService.getAllUserByAccount(account);
				if (sysUser != null) {
					List<SysDept> detps = deptFacadeService
							.getExamineCommonList(sysUser);

					List<SysUser> userList = sysUserService.getAllManagerUser();
					List<Map<String, Object>> userMapList = new ArrayList<Map<String, Object>>();
					for (SysUser user : userList) {
						for (SysDept sysDept : detps) {
							if (user.getDeptId().equals(sysDept.getDeptId())) {
								userMapList.add(this.userToMap(user));
							}
						}
					}
					String result = jsonArray.fromObject(userMapList,
							jsonConfig).toString();
					return result;
				}
			}
			return null;
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % getManagerUserList", e);
			return null;
		}
	}

	/**
	 * 影像系统--修改未上传文件分类
	 *
	 * @param datUuid
	 * @param photoTypeId
	 * @return
	 */
	public String editCmsPhotoType(String datUuid, Integer photoTypeId) {
		logger.info("pad端开始影像系统--修改未上传文件分类，影像datUuid：" + datUuid
				+ ",photoTypeId：" + photoTypeId);
		try {
			if (datUuid.contains(",")) {
				String[] datUuids = datUuid.split(",");
				for (String uuid : datUuids) {
					Photo photo = new Photo();
					photo.setDatUuid(uuid);
					Photo p = dataPhotoService.getPhotoByUUID(photo);
					p.setPhotoTypeId(photoTypeId);
					dataPhotoService.updatePhotoRemark(p);
				}
			} else {
				Photo photo = new Photo();
				photo.setDatUuid(datUuid);
				Photo p = dataPhotoService.getPhotoByUUID(photo);
				p.setPhotoTypeId(photoTypeId);
				dataPhotoService.updatePhotoRemark(p);
			}
			logger.info("pad端完成影像系统--修改未上传文件分类，影像datUuid：" + datUuid
					+ ",photoTypeId：" + photoTypeId);
			return "true";
		} catch (Exception e) {
			logger.error("editCmsPhotoType", e);
			return "false";
		}
	}

	/**
	 * 3.15 影像系统--上传资料
	 *
	 * @param loanId
	 * @return
	 */
	public String uploadFileToCms(Integer loanId) {
		logger.info("pad端开始影像系统-上传资料，loanId：" + loanId);
		try {
			int b = uploadFileToCMS.upload(loanId);
			if (b == 1) {
				return "true";
			} else if (b == 0) {
				return "false";
			} else {
				return "noCase";
			}
		} catch (Exception e) {
			logger.error("editCmsPhotoType", e);
			return "false";
		}
	}

	/**
	 * 3.15 拒绝客户登记
	 *
	 * @return
	 */
	public String saveRejectCustomer(String account, String customerName,
									 String mobilePhone, String idCard, String useage, String remark) {
		logger.info("pad端开始拒绝客户登记，account：" + account + ",customerName："
				+ customerName + ",mobilePhone:" + mobilePhone + ",idCard:"
				+ idCard + ",useage:" + useage + ",remark:" + remark);
		try {
			SysUser sysUser = sysUserService.getAllUserByAccount(account);

			LnRejectCustomer lnRejectCustomer = new LnRejectCustomer();
			lnRejectCustomer.setCustomerName(customerName);
			lnRejectCustomer.setMobliePhone(mobilePhone);
			lnRejectCustomer.setIdCard(idCard);
			lnRejectCustomer.setUseage(useage);
			lnRejectCustomer.setRemark(remark);
			lnRejectCustomer.setUserId(sysUser.getUserId());
			lnRejectCustomer.setIsDel(0);
			lnRejectCustomer.setCreateDate(Calendar.getInstance().getTime());
			lnRejectCustomer.setCreateUser(sysUser.getUserId());
			lnRejectCustomerService.addLnRejectCustomer(lnRejectCustomer);
			return "true";

		} catch (Exception e) {
			logger.error("saveRejectCustomer", e);
			return "false";
		}
	}

	/**
	 * 贷款管理--保存决议信息
	 *
	 * @param account
	 * @param loanId
	 * @param resultLimitYear
	 * @param resultMoney
	 * @param resultRate
	 * @param resultPoint
	 * @param lowerRate
	 * @return
	 */
	@Override
	public String saveResultInfo(String account, Integer loanId,
								 String resultLimitYear, String resultMoney, String resultRate,
								 String resultPoint, String lowerRate) {
		// TODO:修改
		return null;
		/*
		 * logger.info("pad端开始保存决议信息，account：" + account + ",loanId：" + loanId +
		 * ",resultLimitYear:" + resultLimitYear + ",resultMoney:" + resultMoney
		 * + ",resultRate:" + resultRate + ",resultPoint:" + resultPoint +
		 * ",lowerRate:" + lowerRate); try { logger.info("开始保存决议信息"); if
		 * (StringUtils.isNotEmpty(account)) { SysUser sysUser =
		 * sysUserService.getAllUserByAccount(account); if (sysUser != null) {
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("loanId", loanId); List<LnLoanInfo> lnLoanInfos =
		 * lnLoanInfoService.selectLoanInfoList(map); if (lnLoanInfos != null &&
		 * lnLoanInfos.size() > 0) { LnLoanInfo lnLoanInfo = lnLoanInfos.get(0);
		 * lnLoanInfo.setResultLimtYear(resultLimitYear);
		 * lnLoanInfo.setResultPoint(resultPoint);
		 * lnLoanInfo.setLowerRate(lowerRate);
		 * lnLoanInfo.setResultMoney(resultMoney);
		 * lnLoanInfo.setResultRate(resultRate);
		 * lnLoanInfoService.updateLnLoanInfo(lnLoanInfo); LnLoan lnLoan =
		 * lnLoanService.getLnLoanById(loanId);
		 * 
		 * MessageResult messageResult = sendMessageService.addEditLoan(lnLoan,
		 * lnLoanInfo, "2"); if (messageResult == null) { return "false"; }
		 * return "true"; } logger.info("没有找到此贷款"); } logger.info("没有此客户"); }
		 * return "false"; } catch (Exception e) {
		 * logger.error("LoanWebServiceImpl % getEnlendHistoryList", e); return
		 * "false"; }
		 */}

	/**
	 * 贷款管理--取得决议信息
	 *
	 * @param account
	 * @param loanId
	 * @return
	 */
	@Override
	public String getResultInfo(String account, Integer loanId) {
		// TODO:修改
		return null;
		/*
		 * logger.info("pad端开始取得决议信息，account：" + account + ",loanId：" + loanId);
		 * try { JSONArray jsonArray = new JSONArray(); JsonConfig jsonConfig =
		 * new JsonConfig(); jsonConfig.registerJsonValueProcessor(Date.class,
		 * new JsonDateValueProcessor()); logger.info("开始取决议信息"); if
		 * (StringUtils.isNotEmpty(account)) { SysUser sysUser =
		 * sysUserService.getAllUserByAccount(account); if (sysUser != null) {
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("loanId", loanId); List<LnLoanInfo> lnLoanInfos =
		 * lnLoanInfoService.selectLoanInfoList(map); if (lnLoanInfos != null &&
		 * lnLoanInfos.size() > 0) { LnLoanInfo lnLoanInfo = lnLoanInfos.get(0);
		 * Map<String, Object> map1 = new HashMap<String, Object>();
		 * map1.put("resultLimitYear", lnLoanInfo.getResultLimtYear());
		 * map1.put("resultMoney", lnLoanInfo.getResultMoney());
		 * map1.put("resultRate", lnLoanInfo.getResultRate());
		 * map1.put("resultPoint", lnLoanInfo.getResultPoint());
		 * map1.put("lowerRate", lnLoanInfo.getLowerRate()); String result =
		 * jsonArray.fromObject(map1, jsonConfig).toString();
		 * logger.info("成功找到"); return result; } logger.info("没有找到此贷款"); }
		 * logger.info("没有此客户"); } return null; } catch (Exception e) {
		 * logger.error("LoanWebServiceImpl % getEnlendHistoryList", e); return
		 * null; }
		 */
	}

	/**
	 * 贷款管理-取得贷款申请表担保人信息
	 *
	 * @param account
	 * @param loanId
	 * @return
	 */
	@Override
	public String getAppFormGua(String account, Integer loanId) {
		// TODO:修改
		return null;
		/*
		 * logger.info("pad端开始取得贷款申请表担保人信息，account：" + account + ",loanId：" +
		 * loanId); try { JSONArray jsonArray = new JSONArray(); JsonConfig
		 * jsonConfig = new JsonConfig();
		 * jsonConfig.registerJsonValueProcessor(Date.class, new
		 * JsonDateValueProcessor()); logger.info("开始查找贷款申请表担保人信息"); if
		 * (StringUtils.isNotEmpty(account)) { SysUser sysUser =
		 * sysUserService.getAllUserByAccount(account); if (sysUser != null) {
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("loanId", loanId); List<LnLoanInfo> lnLoanInfos =
		 * lnLoanInfoService.selectLoanInfoList(map); if (lnLoanInfos == null)
		 * return null; LnLoanInfo lnLoanInfo = lnLoanInfos.get(0); Map<String,
		 * Object> map1 = new HashMap<String, Object>(); map1.put("loabInfoId",
		 * lnLoanInfo.getLoanInfoId()); map1.put("customerName",
		 * lnLoanInfo.getAppGnt()); map1.put("idcard",
		 * lnLoanInfo.getAppGntIdcard()); map1.put("phone",
		 * lnLoanInfo.getAppGntPhone()); String result =
		 * jsonArray.fromObject(map1, jsonConfig).toString();
		 * logger.info("成功找到"); return result; } logger.info("没有此客户"); } return
		 * null; } catch (Exception e) {
		 * logger.error("LoanWebServiceImpl % getLoanRemarkHistory", e); return
		 * null; }
		 */}

	/**
	 * 贷款管理-申请阶段更新贷款人信息到申请表 并同步到信贷
	 *
	 * @param account
	 * @param loanId
	 * @param customerName
	 * @param idcard
	 * @param phone
	 * @return
	 */
	@Override
	public String updateApplyCusInfo(String account, Integer loanId,
									 String customerName, String idcard, String phone) {
		logger.info("pad端开始贷款管理-申请阶段更新贷款人信息到申请表 并同步到信贷，account：" + account
				+ ",loanId：" + loanId + ",customerName:" + customerName
				+ ",idcard:" + idcard + ",phone:" + phone);
		try {
			JSONArray jsonArray = new JSONArray();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			if (StringUtils.isNotEmpty(account)) {
				SysUser sysUser = sysUserService.getAllUserByAccount(account);
				if (sysUser != null) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("loanId", loanId);
					List<LnLoanInfo> lnLoanInfos = lnLoanInfoService
							.selectLoanInfoList(map);
					if (lnLoanInfos == null)
						return "false";
					LnLoanInfo lnLoanInfo = lnLoanInfos.get(0);
					lnLoanInfo.setCusName(customerName);
					lnLoanInfo.setCusIdcard(idcard);
					lnLoanInfo.setCusMobilePhone(phone);
					LnLoan lnLoan = lnLoanService.getLnLoanById(loanId);
					/*
					 * MessageResult messageResult =
					 * sendMessageService.addEditLoan(lnLoan, lnLoanInfo, "2");
					 * if (messageResult == null) return "false";//同步失败返回false
					 */
					lnLoanInfoService.updateLnLoanInfo(lnLoanInfo);
					return "true";
				}
				logger.info("没有此客户");
			}
			return "false";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % updateApplyCusInfo", e);
			return "false";
		}
	}



	/**
	 * 在线反馈接口
	 *
	 * @param account
	 *            账户
	 * @param titleName
	 *            标题
	 * @param modular
	 *            模块
	 * @param description
	 *            描述
	 * @param filename
	 *            日志文件名
	 * @return
	 */
	@Override
	public String addFeedBack(String account, String titleName, String modular,
							  String description, String filename) {
		logger.info("addFeedBack，account：" + account + ",titleName："
				+ titleName + ",modular:" + modular + ",description:"
				+ description + ",filename:" + filename);
		try {
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			FeedBack feedBack = new FeedBack();
			feedBack.setTitleName(titleName);
			feedBack.setModular(modular);
			feedBack.setDescription(description);
			if (!StringUtil.isEmpty(filename)) {
				String fname = sysParamService.getRecordPath() + File.separator
						+ TransportConstants.UPLOAD_TEMP_DIR + File.separator
						+ filename;
				File file = new File(fname);
				if (!file.exists()) {
					logger.info("没有找到上传的文件");
					return "false";
				}
				String newFname = sysParamService.getRecordPath()
						+ File.separator + "feedBack" + File.separator
						+ filename;
				logger.info(fname);
				logger.info(newFname);
				FileUtil.copyFile(fname, newFname);
				logger.info("拷贝文件结束");
				if (FileUtil.deleteFile(fname))
					logger.info("删除文件成功" + fname);
				feedBack.setFileUrl(newFname);
			}
			feedBack.setStatus(0);
			feedBack.setCreateUser(sysUser.getUserId());
			feedBack.setCreateDate(Calendar.getInstance().getTime());
			feedBackService.insertFeedBack(feedBack);
			logger.info("在线反馈结束");
			return "true";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % addFeedBack", e);
			return "false";
		}
	}

	/**
	 * 删除缓存
	 *
	 * @param account
	 * @return
	 */
	@Override
	public String deleteFiles(String account) {
		logger.info("pad端开始查询可以删除缓存的贷款id，account：" + account);
		try {
			JSONArray jsonArray = new JSONArray();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			logger.info("开始查询可以删除缓存的贷款");
			if (StringUtils.isNotEmpty(account)) {
				SysUser sysUser = sysUserService.getAllUserByAccount(account);
				if (sysUser != null) {
					// 可以删除缓存的部分贷款id
					List<Integer> partList = lnLoanService
							.getPartDELLoanIdByMap(sysUser.getUserId());
					logger.info(partList.toString());
					StringBuffer partDeleteLoanIds = new StringBuffer();
					for (int i = 0; i < partList.size(); i++) {
						if (i == 0) {
							partDeleteLoanIds.append(partList.get(i));
						} else {
							partDeleteLoanIds.append(",").append(
									partList.get(i));
						}
					}
					logger.info(partDeleteLoanIds.toString());
					// 可以删除缓存的全部贷款id
					List<Integer> allList = lnLoanService
							.getAllDELLoanIdByMap(sysUser.getUserId());
					StringBuffer allDeleteLoanIds = new StringBuffer();
					for (int i = 0; i < allList.size(); i++) {
						if (i == 0) {
							allDeleteLoanIds.append(allList.get(i));
						} else {
							allDeleteLoanIds.append(",").append(allList.get(i));
						}
					}
					logger.info(allDeleteLoanIds.toString());
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("allDeleteLoanIds", partDeleteLoanIds.toString());
					map1.put("partDeleteLoanIds", allDeleteLoanIds.toString());
					String result = jsonArray.fromObject(map1, jsonConfig)
							.toString();
					logger.info("成功找到");
					return result;
				}
				logger.info("没有此客户");
			}
			return "false";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % deleteFiles", e);
			return "false";
		}
	}

	/**
	 * 修改密码
	 *
	 * @param account
	 * @param password
	 * @return
	 */
	@Override
	public String changePassword(String account, String password) {
		logger.info("changePassword，account：" + account + ",password："
				+ password);
		try {
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			sysUser.setPassword(Md5Encrypt.md5(password.trim()));
			sysUserService.updateSysUser(sysUser);
			return "true";
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % changePassword", e);
			return "false";
		}
	}

	/**
	 * 获取用户信贷机构信息
	 *
	 * @param account
	 * @return
	 */
	@Override
	public String getXDJG(String account) {
		logger.info("取用户信贷机构信息getXDJG，account：" + account);
		try {
			JSONObject jsonObject = new JSONObject();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			if (sysUser == null)
				return "false";
			Map<String, Map<String, String>> jgmap = sysUserService
					.getUserJGINFO(account);
			Map<String, String> result = new HashMap<String, String>();
			for (String key : jgmap.keySet()) {
				result = jgmap.get(key);
			}
			String info = jsonObject.fromObject(result, jsonConfig).toString();
			logger.info("取用户信贷机构信息getXDJG完成" + info);
			return info;
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % checkCustomer", e);
			return "false";
		}
	}

	@Override
	public String commonProblem(String account, String mobile) {
		logger.info("常见问题commonProblem，account：" + account);
		try {
			JSONArray jsonArray = new JSONArray();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class,
					new JsonDateValueProcessor());
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			if (sysUser == null)
				return "false";
			File file = new File("/data/banger/micro2/cjwt.xml");
			if (!file.exists()) {
				return "false";
			}
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(file);
			Element root = document.getRootElement();// 第一层根节点
			List<Element> root2 = root.getChildren();// 第二层根节点
			for (Element element : root2) {
				if (element.getName().equals(mobile)) {
					List<Element> root3 = element.getChildren();// 第3层根节点
					for (Element elm : root3) {
						Map<String, String> map = new HashMap<String, String>();
						map.put(elm.getChild("problemName").getText(), elm
								.getChild("solution").getText());// 问题，解决方案
						list.add(map);
					}
				}
			}
			String info = jsonArray.fromObject(list, jsonConfig).toString();
			logger.info("常见问题commonProblem" + info);
			return info;
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % commonProblem", e);
			return "false";
		}
	}

	/**
	 * 贷款状态同步
	 *
	 * @param account
	 * @param loanId
	 * @return
	 */
	@Override
	public String getXDStatus(String account, String loanId) {
		logger.info("贷款状态同步getXDStatus，account：" + account + ",LoanId："
				+ loanId);
		try {
			SysUser sysUser = sysUserService.getAllUserByAccount(account);
			if (sysUser == null)
				return "false";
			Integer id = Integer.parseInt(loanId);
			String result = "";// sendMessageService.getXDStatus(id);
			logger.info("贷款状态同步getXDStatus" + result);
			return result;
		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % checkCustomer", e);
			return "false";
		}
	}

	/**
	 * 还款计划列表
	 *
	 * @return
	 */
	@Override
	public String queryRepaymentPlanList(String loanId, String money, String month, String day, String rate, String type) {

		logger.info("约定还款列表queryRepaymentPlanList，loanId：" + loanId + ",money：" + money + ",month：" + month + ",day：" + day);
		try {
			List<LnRepaymentPlan> list = new ArrayList<LnRepaymentPlan>();
			//根据 loanid查询还款计划
			//if 存在还款计划
			/*
			1 等额本息  2 等额本金  3 按月还息 到期还本  4约定还款
			* */
			if(StringUtils.isNotBlank(loanId)&&StringUtils.isNumeric(loanId)){
				list = lnRepaymentPlanService.queryLnRepaymentPlan(Integer.valueOf(loanId));
				if((CollectionUtils.isEmpty(list)||list.size()!=Integer.valueOf(month))&&StringUtils.isNotBlank(month)&&StringUtils.isNumeric(month)&&StringUtils.isNotBlank(money)&&StringUtils.isNumeric(money)&&StringUtils.isNotBlank(rate)&&StringUtils.isNumeric(rate)){//else 不存在还款计划
					list.clear();
					List<LoanCalculatorBean>  lcb=new ArrayList<LoanCalculatorBean>();
					if(type.equals("2")){
						lcb=calculatorBeanList2(Double.parseDouble(money), Double.parseDouble(rate), Integer.parseInt(month));
					}
					if(type.equals("1")){
						lcb=calculatorBeanList(Double.parseDouble(money), Double.parseDouble(rate),Integer.parseInt(month) );
					}
					if(type.equals("3")){
						lcb=calculatorBeanList3(Double.parseDouble(money), Double.parseDouble(rate), Integer.parseInt(month));
					}
					if(type.equals("4")){
						lcb=calculatorBeanList2(Double.parseDouble(money), Double.parseDouble(rate), Integer.parseInt(month));
					}
					LnRepaymentPlan plan ;
					PadLoanInfo loanInfo = lnLoanInfoService.getPanLoanInfoById(Integer.valueOf(loanId));
					Date loanDate = new Date();
					Integer repayDate = 1;
					if(StringUtils.isNotBlank(day)&&StringUtils.isNumeric(day)){
						repayDate = Integer.valueOf(day);
					}
					if(null!=loanInfo&&null!=loanInfo.getRegisterLoanDate()){
						loanDate = loanInfo.getRegisterLoanDate();
					}
					for (int i = 0; i <Integer.valueOf(month); i++) {
						plan = new LnRepaymentPlan();
						plan.setSortno(i+1);
						plan.setLoanId(Integer.valueOf(loanId));
						plan.setPlanDate(loanDateCalender(loanDate, repayDate, Integer.valueOf(i + 1)));
						plan.setPrincipal(BigDecimal.valueOf(lcb.get(i).getNumMonthCapital()));
						plan.setInterest(BigDecimal.valueOf(lcb.get(i).getNumMonthInterest()));
						plan.setCreateDate(new Date());
						list.add(plan);
					}
					if(!CollectionUtils.isEmpty(list)){
						lnRepaymentPlanService.addRepaymentPlanBatch(list);
					}
				}
			}




			JSONArray jsonArray = new JSONArray();
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));
			String result = jsonArray.fromObject(list, jsonConfig).toString();
			return result;

		} catch (Exception e) {
			logger.error("LoanWebServiceImpl % queryRepaymentPlanList", e);
			return "false";
		}
	}

	/**
	 * 保存还款计划列表
	 *
	 * @return
	 */
	@Override
	public String saveRepaymentPlan(String loanIdStr, String dayStr, String account, String jsonString) {

		logger.info("约定还款列表queryRepaymentPlanList，loanId：" + loanIdStr + ",day：" + dayStr + ",jsonString：" + jsonString + ",account：" + account);
		try {
			Integer loanId = 0;
			if(StringUtils.isNotBlank(loanIdStr)&&StringUtils.isNumeric(loanIdStr)){
				loanId = Integer.valueOf(loanIdStr);
			}
			Integer repayDate = 1;
			if(StringUtils.isNotBlank(dayStr)&&StringUtils.isNumeric(dayStr)){
				repayDate = Integer.valueOf(dayStr);
			}

			PadLoanInfo loanInfo = lnLoanInfoService.getPanLoanInfoById(loanId);
			Date loanDate = null;
			if(null!=loanInfo){
				loanDate = loanInfo.getRegisterLoanDate();
			}
			if(StringUtils.isNotBlank(jsonString)&&loanId>0&&null!=loanDate){
				//删除该笔贷款还款计划
				List<Integer> loanIdList = new ArrayList<Integer>();
				loanIdList.add(loanId);
				lnRepaymentPlanService.deletePlanByLoanIdBatch(loanIdList);

				//计算贷款时间
				JSONObject jsonObject = JSONObject.fromObject(jsonString);
				Iterator<String> jsonIter = jsonObject.keys();//遍历JSON串，String-->jsopn
				LnRepaymentPlan plan;
				while(jsonIter.hasNext()){
					String key = jsonIter.next();
					String value =jsonObject.getString(key);
					plan = new LnRepaymentPlan();
					if(StringUtils.isNotBlank(value)){
						String values[] = jsonObject.getString(key).split(":");
						plan.setPrincipal(new BigDecimal(values[0]).setScale(3, BigDecimal.ROUND_HALF_UP));
						if(values.length==2){
							plan.setInterest(new BigDecimal(values[1]).setScale(3, BigDecimal.ROUND_HALF_UP));
						}
					}else{
						plan.setPrincipal(new BigDecimal(0));
						plan.setInterest(new BigDecimal(0));
					}
					plan.setLoanId(loanId);
					plan.setPlanDate(loanDateCalender(loanDate, repayDate, Integer.valueOf(key)));
					plan.setSortno(Integer.valueOf(key));
					plan.setPaidTag("0");
					plan.setCreateDate(new Date());
					SysUser sysUser = sysUserService.getAllUserByAccount(account);
					if (null!=sysUser)
						plan.setCreateUser(sysUser.getUserId());
					lnRepaymentPlanService.addRepaymentPlan(plan);
				}
			}
			return "true";

		} catch (Exception e) {
			logger.error("saveRepaymentPlan % saveRepaymentPlan", e);
			return "false";
		}
	}

	/**
	 * 约定还款列表
	 *
	 * @return
	 */
	@Override
	public String checkRepaymentPlan(String loanIdStr, String moneyStr, String monthStr, String dayStr) {
		logger.info("约定还款列表queryRepaymentPlanList，loanId：" + loanIdStr + ",moneyStr：" + moneyStr + ",monthStr：" + monthStr + ",dayStr：" + dayStr);
		try {
			Integer loanId = 0;
			if(StringUtils.isNotBlank(loanIdStr)&&StringUtils.isNumeric(loanIdStr)){
				loanId = Integer.valueOf(loanIdStr);
			}
			BigDecimal money = new BigDecimal(0.00);
			if(StringUtils.isNotBlank(moneyStr)&&StringUtils.isNumeric(moneyStr)){
				money = new BigDecimal(moneyStr).setScale(2,BigDecimal.ROUND_HALF_UP);
			}
			Integer month = 0;
			if(StringUtils.isNotBlank(monthStr)&&StringUtils.isNumeric(monthStr)){
				month = Integer.valueOf(monthStr);
			}
			Integer day = 1;
			if(StringUtils.isNotBlank(dayStr)&&StringUtils.isNumeric(dayStr)){
				day = Integer.valueOf(dayStr);
			}

//			List<Integer> loanIdList = new ArrayList<Integer>();
//			loanIdList.add(loanId);
//			lnRepaymentPlanService.deletePlanByLoanIdBatch(loanIdList);

			//总金额是否相等   期数是否相等   还款日是否相等（第一次还款时间）
			List<LnRepaymentPlan> list = lnRepaymentPlanService.queryLnRepaymentPlan(Integer.valueOf(loanId));
			BigDecimal totalAmount = new BigDecimal(0.00);
			Date firstDate = new Date();
			Date newDate  = new Date();
			PadLoanInfo loanInfo = lnLoanInfoService.getPanLoanInfoById(loanId);
			Date loanDate = null;
			if(null!=loanInfo){
				loanDate = loanInfo.getRegisterLoanDate();
			}
			newDate = loanDateCalender(loanDate, day, 1);
			if(!CollectionUtils.isEmpty(list)){
				for (int i = 0; i < list.size(); i++) {
					if(null!=list.get(i).getPrincipal()){
						totalAmount = totalAmount.add(list.get(i).getPrincipal());
					}
					if(i==0){
						if(null!=list.get(i).getPlanDate()){
							firstDate = list.get(i).getPlanDate();
						}
					}
				}
				totalAmount = totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				if(totalAmount.compareTo(money)==0&&list.size()==month&&sdf.format(firstDate).equals(sdf.format(newDate))){
					return "true";
				}

			}

			return "false";

		} catch (Exception e) {
			logger.error("saveRepaymentPlan % saveRepaymentPlan", e);
			return "false";
		}
	}

	private Date loanDateCalender(Date loanDate,Integer repayDate,Integer addMonth){

		if(null==loanDate){
			return new Date();
		}
		//贷款时间
		Calendar calender = Calendar.getInstance();
		calender.setTime(loanDate);
		int day = calender.get(Calendar.DAY_OF_MONTH);

		//
		if(repayDate<=day||(repayDate-day)<15){
			//第一次还款时间
			calender.add(Calendar.MONTH,1);
			calender.set(Calendar.DAY_OF_MONTH, repayDate);
			//
			Calendar calenderLoanDate = Calendar.getInstance();
			calenderLoanDate.setTime(loanDate);

			//
			if((calender.get(Calendar.DAY_OF_YEAR)-calenderLoanDate.get(Calendar.DAY_OF_YEAR)<15)){
				calender.add(Calendar.MONTH,1);
			}
		}else{
			calender.set(Calendar.DAY_OF_MONTH, repayDate);
		}

		calender.add(Calendar.MONTH,addMonth-1);


		return  calender.getTime();

	}

	@Override
	public String getFillingList(String account, Integer days, Integer sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEnlendTotal(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEnlendList(String account, String level,
								String manageLevel, String input, Integer page, Integer order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addEnlendHistory(String account, Integer enlendingId,
								   String operateWay, String afterStatus, String remark) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String quitEnlend(String account, Integer enlendingId,
							 String quitReason, String remark) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEnlendHistoryList(String account, Integer enlendingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCmsAllFileList(Integer loanId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOnLnFilling(String account, Integer fillingId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getLoanStatistics(String account) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		try {
			logger.info("pad贷款统计getLoanStatistics开始，account:" + account);
			SysUser sysUser = sysUserService.getAllUserByAccount(account);

			//申请
			Map<String,Object> map = sumLoanByStatus(1,null);
			resultMap.put("applyNum",map.get("count"));
			resultMap.put("applyAmount",map.get("amount"));

			//分配
			map = sumLoanByStatus(2,null);
			resultMap.put("allotNum",map.get("count"));
			resultMap.put("allotAmount",map.get("amount"));

			//调查
			map = sumLoanByStatus(3,null);
			resultMap.put("investigateNum",map.get("count"));
			resultMap.put("investigateAmount",map.get("amount"));

			//放贷
			map = sumLoanByStatus(5,null);
			resultMap.put("loanNum",map.get("count"));
			resultMap.put("loanAmount",map.get("amount"));

			//催收
			map = sumLoanByStatus(6,null);
			resultMap.put("urgeNum",map.get("count"));
			resultMap.put("urgeAmount",map.get("amount"));


			//结清
			map = sumLoanByStatus(7,null);
			resultMap.put("clearNum",map.get("count"));
			resultMap.put("clearAmount",map.get("amount"));

			logger.info("pad贷款统计getLoanStatistics完成，resultMap:" + resultMap.toString());
			return JSONObject.fromObject(resultMap).toString();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return JSONObject.fromObject(resultMap).toString();
		}
	}



//	public static void main(String[] agrs){
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap.put("applyNum",1);
//		resultMap.put("applyAmount", 2);
//
//
//
//
//		resultMap.put("allotNum",3);
//		resultMap.put("allotAmount",4);
//
//		resultMap.put("investigateNum",5);
//		resultMap.put("investigateAmount",6);
//
//
//		resultMap.put("loanNum",7);
//		resultMap.put("loanAmount",8);
//
//
//		resultMap.put("urgeNum",9);
//		resultMap.put("urgeAmount",10);
//
//
//
//		resultMap.put("clearNum",11);
//		resultMap.put("clearAmount",12);
//		System.out.println(JSONObject.fromObject(resultMap).toString());
//	}


	//	1	申请
	//	2	分配
	//	3	调查
	//	4	审批
	//	5	放贷
	//	6	催收
	//	7	结清
	private Map<String,Object> sumLoanByStatus(int loanStatusId,Integer surveyUserId){

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanStatusId", loanStatusId);
		if(null!=surveyUserId&&surveyUserId>0){
			map.put("surveyUserId", surveyUserId);
		}

		Integer count = 0;
		BigDecimal amount = new BigDecimal(0);
		List<LnLoanInfo> list = lnLoanService.getLoanByStatusId(map);
		if(!CollectionUtils.isEmpty(list)){
			for(LnLoanInfo loan : list){
				if(loanStatusId==1||loanStatusId==2){
					if(null!=loan.getAppMoney()){
						amount = amount.add(new BigDecimal(loan.getAppMoney()));
					}
				}else if(loanStatusId==3){
					if(StringUtils.isNotBlank(loan.getAdviceMoney())&&StringUtils.isNumeric(loan.getAdviceMoney())){
						amount = amount.add(new BigDecimal(loan.getAdviceMoney()));
					}else if(null!=loan.getAppMoney()){
						amount = amount.add(new BigDecimal(loan.getAppMoney()));
					}
				}else if(loanStatusId==5||loanStatusId==6||loanStatusId==7){
					if(StringUtils.isNotBlank(loan.getResultMoney())&&StringUtils.isNumeric(loan.getResultMoney())){
						amount = amount.add(new BigDecimal(loan.getResultMoney()));
					}
				}
			}
			count = list.size();

		}

		map.put("count",count);
		map.put("amount",amount.setScale(2,BigDecimal.ROUND_HALF_UP));

		return map;
	}



	//***************************************************************************//
	@Override
	public String getFullingLoanList(String account, String cusName, String contractNo) {

		logger.info("pad贷款统计getFullingLoanList开始，account:" + account);

		SysUser sysUser = sysUserService.getAllUserByAccount(account);
		if (sysUser == null)
			return "您还没有登录！";
		int roleId = sysRoleMemberService.getRoleIdByUserId(sysUser.getUserId());
		//查询催收阶段的贷款
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanStatusId", 6);
		if(roleId!=5) {
			map.put("surveyUserId", sysUser.getUserId());
		}
		List<LnLoanInfo> list = lnLoanService.getLoanByStatusId(map);

		FullingLoanList fulling = new FullingLoanList();

		if(!CollectionUtils.isEmpty(list)){
			FullingLoanInfo fullingInfo;
			LnRepaymentPlan plan ;
			for(LnLoanInfo loan : list){
				fullingInfo = new FullingLoanInfo();
				plan = lnRepaymentPlanService.getCurrentNoRepaymentPlan(loan.getLoanId());
				if(null != plan){

					BigDecimal principal = plan.getPrincipal();    //应还本金
					if(null==principal){
						principal = new BigDecimal(0);
					}
					BigDecimal interest = plan.getInterest();     //应还利息
					if(null==interest){
						interest = new BigDecimal(0);
					}
					BigDecimal paidPrincipal = plan.getPaidPrincipal();    //已还本金
					if(null==paidPrincipal){
						paidPrincipal = new BigDecimal(0);
					}
					BigDecimal paidInterest = plan.getPaidInterest();     //已还利息
					if(null==paidInterest){
						paidInterest = new BigDecimal(0);
					}
					fullingInfo.setNextAmount(principal.add(interest).subtract(paidPrincipal).subtract(paidInterest));

					fullingInfo.setAccountBalance(plan.getAccountRemaining());
					fullingInfo.setPlanDate(plan.getPlanDate());

				}

				TmpLoanAccount tmpAccount = lnBalanceService.getTmpLoanAccountByLoanId(loan.getLoanId());
				if(null!=tmpAccount&&null!=tmpAccount.getLonBal()){
					fullingInfo.setLoanBalance(tmpAccount.getLonBal());
					fullingInfo.setAccountBalance(tmpAccount.getAcctBal());
				}

				fullingInfo.setLoanId(loan.getLoanId());
				if(StringUtils.isNotBlank(loan.getCusName())){
					fullingInfo.setCusName(loan.getCusName());
				}
				if(StringUtils.isNotBlank(loan.getCusMobilePhone())){
					fullingInfo.setCusPhoneNum(loan.getCusMobilePhone());
				}else if(StringUtils.isNotBlank(loan.getCusPhone())){
					fullingInfo.setCusPhoneNum(loan.getCusPhone());
				}
				if(fullingInfo.getNextAmount().compareTo(fullingInfo.getAccountBalance())==1) {
					fulling.getFullingList().add(fullingInfo);
				}
				//
				if(!fullingInfo.getIsEnough()){
					fulling.setLessLoanCount(fulling.getLessLoanCount()+1);
					fulling.setLessLoanAmount(fulling.getLessLoanAmount().add(fullingInfo.getLessAmount()));
				}

			}

		}


//		JSONArray jsonArray = new JSONArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd"));
//		String result = jsonArray.fromObject(fulling, jsonConfig).toString();
		String result = JSONObject.fromObject(fulling, jsonConfig).toString();
		logger.info("pad贷款统计getFullingLoanList结束，result:" + result);

		return result;


	}


	@Override
	public String getCleanLoanList(String account, String cusName, String contractNo) {

		logger.info("pad贷款统计getCleanLoanList开始，account:" + account +",cusName:"+cusName);

		SysUser sysUser = sysUserService.getAllUserByAccount(account);
		if (sysUser == null)
			return "您还没有登录！";
		//查询结清阶段的贷款
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanStatusId", 7);
		map.put("surveyUserId", sysUser.getUserId());
		if(StringUtils.isNotBlank(cusName)){
			map.put("search", cusName);
		}
		List<LnLoanInfo> list = lnLoanService.getLoanByStatusId(map);

		List<CleanLoanInfo> cleanList = new ArrayList<CleanLoanInfo>();
		if(!CollectionUtils.isEmpty(list)){
			CleanLoanInfo cleanInfo ;
			List<LnRepaymentPlan> planList;
			for(LnLoanInfo loan : list){
				cleanInfo = new CleanLoanInfo();
				cleanInfo.setLoanId(loan.getLoanId());
				cleanInfo.setCustomerId(loan.getCustomerId());
				cleanInfo.setCusName(loan.getCusName());

				if(StringUtils.isNotBlank(loan.getCusMobilePhone())){
					cleanInfo.setCusPhoneNum(loan.getCusMobilePhone());
				}else if(StringUtils.isNotBlank(loan.getCusPhone())){
					cleanInfo.setCusPhoneNum(loan.getCusPhone());
				}

				//贷款金额
//				if(StringUtils.isNotBlank(loan.getResultMoney())&&StringUtils.isNumeric(loan.getResultMoney())){
//					cleanInfo.setLoanAmount(new BigDecimal(loan.getResultMoney()));
//				}

				planList = lnRepaymentPlanService.queryLnRepaymentPlan(loan.getLoanId());
				if(!CollectionUtils.isEmpty(planList)){
					cleanInfo.setAccountBalance(planList.get(0).getAccountRemaining());
				}

				cleanList.add(cleanInfo);

			}
		}

		JSONArray jsonArray = new JSONArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		String result = jsonArray.fromObject(cleanList, jsonConfig).toString();
		logger.info("pad贷款统计getCleanLoanList结束，result:" + result);

		return result;
	}

	@Override
	public String getLoanCustomerList(String account, String cusName, String level) {

		logger.info("pad转贷管理 --客户分级getLoanCustomerList开始，account:"+account+",cusName:"+cusName+",level:"+level);

		SysUser sysUser = sysUserService.getAllUserByAccount(account);
		if (sysUser == null)
			return "您还没有登录！";

		List<CleanLoanInfo> cleanList = new ArrayList<CleanLoanInfo>();


		if("A".equals(level)){
//		    //存量客户 催收 环节的贷款
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("loanStatusId", 6);
			map.put("surveyUserId", sysUser.getUserId());
			if(StringUtils.isNotBlank(cusName)){
				map.put("search", cusName);
			}

			List<LnLoanInfo> list = lnLoanService.getLoanByStatusId(map);
			if(!CollectionUtils.isEmpty(list)){
				CleanLoanInfo cleanInfo ;
				for(LnLoanInfo loan : list){
					cleanInfo = new CleanLoanInfo();
					cleanInfo.setLoanId(loan.getLoanId());
					cleanInfo.setCustomerId(loan.getCustomerId());
					cleanInfo.setCusName(loan.getCusName());

					if(StringUtils.isNotBlank(loan.getCusMobilePhone())){
						cleanInfo.setCusPhoneNum(loan.getCusMobilePhone());
					}else if(StringUtils.isNotBlank(loan.getCusPhone())){
						cleanInfo.setCusPhoneNum(loan.getCusPhone());
					}

					TmpLoanAccount tmpAccount = lnBalanceService.getTmpLoanAccountByLoanId(loan.getLoanId());
					if(null!=tmpAccount){
						cleanInfo.setLoanBalance(tmpAccount.getLonBal());
						cleanInfo.setAccountBalance(tmpAccount.getAcctBal());
					}

					cleanList.add(cleanInfo);

				}
			}

		}else if("B".equals(level)||"C".equals(level)){//未转贷客户
//		    全部贷款结清的用户  排除退出客户
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("minus", "true");
			map.put("BelongTo", "brMine");
			map.put("InChargeOfUserIds", sysUser.getUserId());
			if("B".equals(level)){
				map.put("isContinue", "1");
			}else if("C".equals(level)){
				map.put("isContinue", "0");
			}
			List<BaseCrmCustomer> list = crmCustomerService.getCusByConditionByMap(map);
			if(!CollectionUtils.isEmpty(list)){
				CleanLoanInfo cleanInfo ;
				for (BaseCrmCustomer customer : list){
					cleanInfo = new CleanLoanInfo();
					cleanInfo.setCustomerId(customer.getCustomerId());
					cleanInfo.setCusName(customer.getCustomerName());
					cleanInfo.setCusPhoneNum(customer.getFirstNotNullPhone());
					cleanList.add(cleanInfo);
				}
			}
		}

		JSONArray jsonArray = new JSONArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		String result = jsonArray.fromObject(cleanList, jsonConfig).toString();
		logger.info("pad转贷管理 --客户分级getLoanCustomerList结束，result:" + result);
		return result;
	}


	@Override
	public String getCustomerLevel(String account) {
		SysUser sysUser = sysUserService.getAllUserByAccount(account);
		if (sysUser == null)
			return "您还没有登录！";


		//催收
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String,Object> map = sumLoanByStatus(6,sysUser.getUserId());
		resultMap.put("aNumber",map.get("count"));
		resultMap.put("aLoanBalance",map.get("amount"));


//		全部贷款结清的用户  排除退出客户
		Map<String, Object> cond = new HashMap<String, Object>();
		cond.put("minus", "true");
		cond.put("BelongTo", "brMine");
		cond.put("InChargeOfUserIds", sysUser.getUserId());
		map.put("isContinue", "1");
		List<BaseCrmCustomer> list = crmCustomerService.getCusByConditionByMap(map);
		if(CollectionUtils.isEmpty(list)){
			resultMap.put("bNumber",0);
		}else{
			resultMap.put("bNumber",list.size());
		}


		map.put("isContinue", "0");
		list = crmCustomerService.getCusByConditionByMap(map);
		if(CollectionUtils.isEmpty(list)){
			resultMap.put("cNumber",0);
		}else{
			resultMap.put("cNumber",list.size());
		}

		logger.info("pad转贷管理 --客户分级 统计getCustomerLevel结束，resultMap:" + resultMap.toString());

		return JSONObject.fromObject(resultMap).toString();

	}

	/**
	 * 转贷管理 --客户分级 未转贷客户标记退出|已转贷
	 * @param customerId 客户id
	 * @param flag  0退出 1已转贷
	 * @return
	 */
	@Override
	public String setCustomerLevel(Integer customerId, String flag) {
		try {
			logger.info("setCustomerLevel, customerId：" + customerId + ",flag：" + flag);
			CrmCustomer crmCustomer = crmCustomerService.getCustomerById(customerId);
			if (!StringUtil.isBlank(flag)&&crmCustomer != null) {
				crmCustomer.setIsContinue(flag);
				crmCustomerService.updateCrmCustomer(crmCustomer);
			}
			logger.info("更新成功");
			return "true";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "false";
		}
	}

	/**
	 * 删除贷款
	 * @param loanId
	 * @return
	 */
	@Override
	public String delLoan(Integer loanId){

		try{
			logger.info("delLoan, loanId：" + loanId );
			LnLoan ln = lnLoanService.getLnLoanById(loanId);
			if (ln.getLoanStatusId() == 1) {
//				lnLoanService.deleteLoan(loanId);
				ln.setLoanStatusId(11);//申请拒绝
				lnLoanService.updateLoanByLoanId(ln);
			}
			logger.info("删除成功");
			return "1";
		} catch (Exception e){
			logger.error("删除贷款失败",e);
			return "0";
		}
	}

	/**
	 * 电视统计/按客户经理最近15天每天的贷款的笔数+金额
	 * @return
	 */
	@Override
	public String getLoanCountAndAmountList() {
		try {
			List<Integer> userIdList = sysRoleMemberService.getUserIdByRole("7", "");
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			List<Map> resultList = new ArrayList<Map>();
			for(Integer userId:userIdList){
				List<Map> dataList = new ArrayList<Map>();
				long d = new Date().getTime();
				BigDecimal totalAmount ;
				for(int i = 1;i<=15;i++){
					Map map = new HashMap();
					Date time = new Date(d - i * 24 * 60 * 60 * 1000);
					map.put("createDate",df.format(time));
					map.put("userId",userId);
					List<LnLoan> list = lnLoanService.selectListByUserAndDate(map);
					totalAmount = new BigDecimal(0);
					for(LnLoan lnLoan:list){
						Map m = new HashMap();
						m.put("status",lnLoan.getLoanStatusId());
						m.put("loanId",lnLoan.getLoanId());
						totalAmount = totalAmount.add(lnLoanService.selectAmountByStatus(m));
					}
					Map<String,Object> resultMap = new HashMap<String,Object>();
					resultMap.put("loanDay",df.format(time));
					resultMap.put("loanCount",list.size());
					resultMap.put("loanAmount",totalAmount);
					dataList.add(resultMap);
					}
				Map<String,Object> m2 = new HashMap<String, Object>();
				m2.put("userName",sysUserService.getSysUserById(userId).getAccount());
				m2.put("dataList",dataList);
				resultList.add(m2);
			}
			JSONArray jsonArray = JSONArray.fromObject(resultList);
			return jsonArray.toString();
		}catch(Exception e){
			logger.error("获取数据失败!",e);
			return "-1";
		}
	}

	//等额本息
	public  List<LoanCalculatorBean> calculatorBeanList(double max,double numInterest,int numPeriod){//本金    利息   期数
		double monthInterest=numInterest/12/100;
		double numMonthPay=0;
		DecimalFormat df   = new DecimalFormat("######0.00");
		DecimalFormat dpf   = new DecimalFormat("######0.000");
		List<LoanCalculatorBean>  list=new ArrayList<LoanCalculatorBean>();
		for (int i = 0; i <numPeriod ; i++) {
			LoanCalculatorBean bean=new LoanCalculatorBean();
			//月供
			if(numMonthPay==0){
				numMonthPay=Math.pow((1+monthInterest),numPeriod)*monthInterest/(Math.pow((1+monthInterest),numPeriod)-1)*max;
			}
			//月供利息
			double numMonthInterest=max*monthInterest;
			//月供本金
			double numMonthCapital=numMonthPay-numMonthInterest;
			//剩余本金
			max=max-numMonthCapital;
			//给相关的bean设置参数
			bean.setNumPeriod(i+1);
			bean.setNumMonthPay(numMonthPay);
			bean.setNumMonthCapital(Double.parseDouble(df.format(numMonthCapital)));
			bean.setNumMonthInterest(Double.parseDouble(dpf.format(numMonthInterest)));
			bean.setNumLeftCapital(max);
			list.add(bean);
				/*Log.e("zzzz", "calculatorBeanList: " + bean.toString());*/

		}
		return list;
	}
	//等额本金
	public  List<LoanCalculatorBean> calculatorBeanList2(double max,double numInterest,int numPeriod){
		double monthInterest=numInterest/12/100;
		//月供本金
		double numMonthCapital=0;
		DecimalFormat df   = new DecimalFormat("######0.00");
		DecimalFormat dpf   = new DecimalFormat("######0.000");
		List<LoanCalculatorBean>  list=new ArrayList<LoanCalculatorBean>();
		for (int i = 0; i <numPeriod ; i++) {
			LoanCalculatorBean bean=new LoanCalculatorBean();
			//月供本金只算一次
			if(numMonthCapital==0){
				numMonthCapital=max/numPeriod;
			}
			//月供利息
			double numMonthInterest=max*monthInterest;
			//月供金额
			double numMonthPay=numMonthCapital+numMonthInterest;

			//剩余本金
			max=max-numMonthCapital;
			//给相关的bean设置参数
			bean.setNumPeriod(i+1);
			bean.setNumMonthPay(numMonthPay);
			bean.setNumMonthCapital(Double.parseDouble(df.format(numMonthCapital)));
			bean.setNumMonthInterest(Double.parseDouble(dpf.format(numMonthInterest)));
			bean.setNumLeftCapital(max);
			list.add(bean);
				/*Log.e("zzzz", "calculatorBeanList: "+ bean.toString());*/

		}
		return list;
	}
	//按月还息，到期还本
	public  List<LoanCalculatorBean> calculatorBeanList3(double max,double numInterest,int numPeriod){
		double monthInterest=numInterest/12/100;//月利息
		//月供本金
		double numMonthCapital=0.0;
		DecimalFormat df   = new DecimalFormat("######0.00");
		DecimalFormat dpf   = new DecimalFormat("######0.000");
		List<LoanCalculatorBean>  list=new ArrayList<LoanCalculatorBean>();
		//月供利息
		double numMonthInterest=max*monthInterest;
		double numMonthPay=0.0;
		for (int i = 0; i <numPeriod ; i++) {
			LoanCalculatorBean bean=new LoanCalculatorBean();
			//月供本金只算一次
			//月供金额
			if(i==numPeriod-1){
				numMonthCapital=max;
			}

			//剩余本金
			max=max-numMonthCapital;
			//给相关的bean设置参数
			bean.setNumPeriod(i+1);
			bean.setNumMonthPay(numMonthPay);
			bean.setNumMonthCapital(Double.parseDouble(df.format(numMonthCapital)));
			bean.setNumMonthInterest(Double.parseDouble(dpf.format(numMonthInterest)));
			bean.setNumLeftCapital(max);
			list.add(bean);
					/*Log.e("zzzz", "calculatorBeanList: "+ bean.toString());*/

		}
		return list;
	}
	//约定还款
	public  List<LoanCalculatorBean> calculatorBeanList4(double max,double numInterest,int numPeriod){
		double monthInterest=numInterest/12/100;
		double numMonthPay=0;
		DecimalFormat df    = new DecimalFormat("######0.00");
		DecimalFormat dpf   = new DecimalFormat("######0.000");
		List<LoanCalculatorBean>  list=new ArrayList<LoanCalculatorBean>();
		for (int i = 0; i <numPeriod ; i++) {
			LoanCalculatorBean bean=new LoanCalculatorBean();
			//月供
			if(numMonthPay==0){
				numMonthPay=Math.pow((1+monthInterest),numPeriod)*monthInterest/(Math.pow((1+monthInterest),numPeriod)-1)*max;
			}
			//月供利息
			double numMonthInterest=max*monthInterest;
			//月供本金
			double numMonthCapital=numMonthPay-numMonthInterest;
			//剩余本金
			max=max-numMonthCapital;
			bean.setNumPeriod(i + 1);
			bean.setNumMonthPay(numMonthPay);
			bean.setNumMonthCapital(Double.parseDouble(df.format(numMonthCapital)));
			bean.setNumMonthInterest(Double.parseDouble(dpf.format(numMonthInterest)));
			bean.setNumLeftCapital(max);
			list.add(bean);
				/*Log.e("zzzz", "calculatorBeanList: " + bean.toString());*/
		}
		return list;
	}

	/**
	 * 下载审批表部分数据
	 * @param loanId 贷款id
	 * @return
	 */
	@Override
	public String getApprovalData(Integer loanId) {
		//取得贷款信息
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("loanId", loanId);
			List<LnLoanInfo> list = lnLoanInfoService.selectLoanInfoList(param);
			LnLoan lnLoan = lnLoanService.getLnLoanById(loanId);
			if(lnLoan==null||CollectionUtils.isEmpty(list)){
				return "0";
			}
			LnLoanInfo lnLoanInfo = list.get(0);
			Map<String, Object> map = new HashMap<String, Object>();
			//客户经理
			map.put("userName", sysUserService.getSysUserById(lnLoan.getApplyUserId()).getUserName());
			//申请人
			map.put("cusName", lnLoanInfo.getCusName());
			//批准金额
			map.put("resultMoney", lnLoanInfo.getResultMoney());
			//期限
			map.put("resultLimitYear", lnLoanInfo.getResultLimitYear());
			//原利率
			map.put("originalRate", lnLoanInfo.getOriginalRate());
			//优惠后利率
			map.put("resultRate", lnLoanInfo.getResultRate());
			JSONObject jsonString = JSONObject.fromObject(map);
			return jsonString.toString();
		}catch(Exception e){
			logger.error("下载审批数据失败",e);
			return "-1";
		}
	}


}
