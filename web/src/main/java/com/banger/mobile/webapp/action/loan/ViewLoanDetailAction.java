package com.banger.mobile.webapp.action.loan;

import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
import com.banger.mobile.domain.model.data.*;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.loan.*;
import com.banger.mobile.domain.model.system.SysTeam;
import com.banger.mobile.domain.model.user.SysDeptAuth;
import com.banger.mobile.domain.model.user.SysRoleAuth;
import com.banger.mobile.domain.model.user.SysUser;
import com.banger.mobile.facade.customer.CrmCustomerService;
import com.banger.mobile.facade.data.CustomerDataService;
import com.banger.mobile.facade.data.DataAudioService;
import com.banger.mobile.facade.data.DataFormService;
import com.banger.mobile.facade.data.DataVideoService;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.dept.DeptService;
import com.banger.mobile.facade.loan.*;
import com.banger.mobile.facade.param.SysParamService;
import com.banger.mobile.facade.record.RecordInfoService;
import com.banger.mobile.facade.system.team.SysTeamService;
import com.banger.mobile.facade.user.SysDeptAuthService;
import com.banger.mobile.facade.user.SysUserService;
import com.banger.mobile.webapp.action.BaseAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA. User: yuanme Date: 13-2-5 Time: 下午4:13 To change
 * this template use File | Settings | File Templates.
 * <p/>
 * 贷款详情 Action
 */
public class ViewLoanDetailAction extends BaseAction {

	private static Logger logger = Logger.getLogger(ViewLoanDetailAction.class);

	private LnLoanService lnLoanService;
	private LnLoanDetailService lnLoanDetailService;
	private CustomerDataService customerDataService;
	private LnOpHistoryService lnOpHistoryService;
	private LnVerifyHistoryService lnVerifyHistoryService;
	private LnExceptionDunLogService lnExceptionDunLogService;
	private LnDunLogService lnDunLogService;
	private CrmCustomerService crmCustomerService;
	private DataAudioService dataAudioService;
	private DataVideoService dataVideoService;
	private DataFormService dataFormService;
	private RecordInfoService recordInfoService;
	private LnRepaymentPlanService lnRepaymentPlanService; // 还款计划service
	private LnExceptionRepaymentPlanService lnExceptionRepaymentPlanService; // 异常催收还款计划service
	private LnLoanInfoService lnLoanInfoService;
	private LnCreditHistoryService lnCreditHistoryService;
	private LnLoanInfoDictionaryService lnLoanInfoDictionaryService;
	private LnLoanPledgeService lnLoanPledgeService;
	private InBusinessReaches  inBusinessReaches;
	private SysTeamService sysTeamService;
	private SysUserService sysUserService;
	private SysParamService sysParamService;
	private SysDeptAuthService sysDeptAuthService; // 可管理机构
	

	private DeptService deptService; // 机构service
	private DeptFacadeService deptFacadeService;
	// 查询条件
	private Integer loanId;
	private Integer reachesType;
	//private LnLoanInfo lnLoanInfo;
	//private LnLoan lnLoan;
	private LnCreditHistory lnCreditHistory;
	private List<LnLoanCoBorrowerBean> loanCoBorrowerList; // 共同借贷人列表
	private List<LnLoanGuarantorBean> loanGuarantorList; // 担保人列表
	private List<LnProfitLossProd> lnProfitLossProdList;
	private List<LnCreditHistory> lnCreditHistoryList;
	private List<LnPledge> lnPledgeList;
	private List<InBusinessReaches> listInBusinessReaches;//上下游
	private List<InBusinessReaches> listInBusinessReaches1;//上下游
	private LnDeviationAnalsysis lnDeviationAnalsysis;//偏差分析
	
	private JSONArray deptJson; // 机构树json
	private JSONArray deptManagerJson;
	private List<SysRoleAuth> sysRoleAuthList = new ArrayList<SysRoleAuth>();

	private LnBusinessModelService lnBusinessmodelService;
	private LnLoanHistoryService lnLoanHistoryService;
	private LnRecordInterviewService lnRecordInterviewService;
	private LnLoanBalanceService lnLoanBalanceService;
	private LnLoanBalanceAssetService lnLoanBalanceAssetService;
	private LnLoanBalanceDebtService  lnLoanBalanceDebtService;
	private LnLoanBalanceVehicleService lnLoanBalanceVehicleService;
	private LnLoanBalanceOtherService lnLoanBalanceOtherService;
	private LnLoanBalanceHousingService lnLoanBalanceHousingService;
	private LnLoanBalanceReceivableService lnLoanBalanceReceivableService;
	private LnLoanProfitandlossService lnLoanProfitandlossService;
	private LnLoanProfitandlossJyDetailService lnLoanProfitandlossJyDetailService;
	private LnLoanProfitandlossXfDetailService  lnLoanProfitandlossXfDetailService;
	private InBusinessReachesService inBusinessReachesService;
	private LnDeviationAnalsysisService lnDeviationAnalsysisService;
	private LnProfitLossProdService lnProfitLossProdService;
	private LnProfitLossBaseService lnProfitLossBaseService;

	public LnLoanService getLnLoanService() {
		return lnLoanService;
	}

	public LnLoanDetailService getLnLoanDetailService() {
		return lnLoanDetailService;
	}

	public CustomerDataService getCustomerDataService() {
		return customerDataService;
	}

	public LnExceptionDunLogService getLnExceptionDunLogService() {
		return lnExceptionDunLogService;
	}

	public LnDunLogService getLnDunLogService() {
		return lnDunLogService;
	}

	public LnCreditHistoryService getLnCreditHistoryService() {
		return lnCreditHistoryService;
	}

	public LnLoanInfoDictionaryService getLnLoanInfoDictionaryService() {
		return lnLoanInfoDictionaryService;
	}

	public LnLoanPledgeService getLnLoanPledgeService() {
		return lnLoanPledgeService;
	}

	public InBusinessReaches getInBusinessReaches() {
		return inBusinessReaches;
	}

	public void setInBusinessReaches(InBusinessReaches inBusinessReaches) {
		this.inBusinessReaches = inBusinessReaches;
	}

	public SysTeamService getSysTeamService() {
		return sysTeamService;
	}

	public SysUserService getSysUserService() {
		return sysUserService;
	}

	public SysParamService getSysParamService() {
		return sysParamService;
	}

	public Integer getReachesType() {
		return reachesType;
	}

	public void setReachesType(Integer reachesType) {
		this.reachesType = reachesType;
	}

	public List<LnProfitLossProd> getLnProfitLossProdList() {
		return lnProfitLossProdList;
	}

	public void setLnProfitLossProdList(List<LnProfitLossProd> lnProfitLossProdList) {
		this.lnProfitLossProdList = lnProfitLossProdList;
	}

	public LnProfitLossBaseService getLnProfitLossBaseService() {
		return lnProfitLossBaseService;
	}

	public void setLnProfitLossBaseService(LnProfitLossBaseService lnProfitLossBaseService) {
		this.lnProfitLossBaseService = lnProfitLossBaseService;
	}

	public LnDeviationAnalsysisService getLnDeviationAnalsysisService() {
		return lnDeviationAnalsysisService;
	}

	public void setLnDeviationAnalsysisService(LnDeviationAnalsysisService lnDeviationAnalsysisService) {
		this.lnDeviationAnalsysisService = lnDeviationAnalsysisService;
	}

	public LnDeviationAnalsysis getLnDeviationAnalsysis() {
		return lnDeviationAnalsysis;
	}

	public void setLnDeviationAnalsysis(LnDeviationAnalsysis lnDeviationAnalsysis) {
		this.lnDeviationAnalsysis = lnDeviationAnalsysis;
	}

	public List<InBusinessReaches> getListInBusinessReaches1() {
		return listInBusinessReaches1;
	}

	public void setListInBusinessReaches1(List<InBusinessReaches> listInBusinessReaches1) {
		this.listInBusinessReaches1 = listInBusinessReaches1;
	}

	public InBusinessReachesService getInBusinessReachesService() {
		return inBusinessReachesService;
	}

	public void setInBusinessReachesService(InBusinessReachesService inBusinessReachesService) {
		this.inBusinessReachesService = inBusinessReachesService;
	}

	public List<InBusinessReaches> getListInBusinessReaches() {
		return listInBusinessReaches;
	}

	public void setListInBusinessReaches(List<InBusinessReaches> listInBusinessReaches) {
		this.listInBusinessReaches = listInBusinessReaches;
	}

	public LnLoanProfitandlossJyDetailService getLnLoanProfitandlossJyDetailService() {
		return lnLoanProfitandlossJyDetailService;
	}

	public void setLnLoanProfitandlossJyDetailService(
			LnLoanProfitandlossJyDetailService lnLoanProfitandlossJyDetailService) {
		this.lnLoanProfitandlossJyDetailService = lnLoanProfitandlossJyDetailService;
	}

	public LnLoanProfitandlossXfDetailService getLnLoanProfitandlossXfDetailService() {
		return lnLoanProfitandlossXfDetailService;
	}

	public void setLnLoanProfitandlossXfDetailService(
			LnLoanProfitandlossXfDetailService lnLoanProfitandlossXfDetailService) {
		this.lnLoanProfitandlossXfDetailService = lnLoanProfitandlossXfDetailService;
	}

	public LnLoanProfitandlossService getLnLoanProfitandlossService() {
		return lnLoanProfitandlossService;
	}

	public void setLnLoanProfitandlossService(
			LnLoanProfitandlossService lnLoanProfitandlossService) {
		this.lnLoanProfitandlossService = lnLoanProfitandlossService;
	}

	public LnLoanBalanceVehicleService getLnLoanBalanceVehicleService() {
		return lnLoanBalanceVehicleService;
	}

	public void setLnLoanBalanceVehicleService(
			LnLoanBalanceVehicleService lnLoanBalanceVehicleService) {
		this.lnLoanBalanceVehicleService = lnLoanBalanceVehicleService;
	}

	public LnLoanBalanceOtherService getLnLoanBalanceOtherService() {
		return lnLoanBalanceOtherService;
	}

	public void setLnLoanBalanceOtherService(
			LnLoanBalanceOtherService lnLoanBalanceOtherService) {
		this.lnLoanBalanceOtherService = lnLoanBalanceOtherService;
	}

	public LnLoanBalanceHousingService getLnLoanBalanceHousingService() {
		return lnLoanBalanceHousingService;
	}

	public void setLnLoanBalanceHousingService(
			LnLoanBalanceHousingService lnLoanBalanceHousingService) {
		this.lnLoanBalanceHousingService = lnLoanBalanceHousingService;
	}

	public LnLoanBalanceReceivableService getLnLoanBalanceReceivableService() {
		return lnLoanBalanceReceivableService;
	}

	public void setLnLoanBalanceReceivableService(
			LnLoanBalanceReceivableService lnLoanBalanceReceivableService) {
		this.lnLoanBalanceReceivableService = lnLoanBalanceReceivableService;
	}

	public LnLoanBalanceDebtService getLnLoanBalanceDebtService() {
		return lnLoanBalanceDebtService;
	}

	public void setLnLoanBalanceDebtService(
			LnLoanBalanceDebtService lnLoanBalanceDebtService) {
		this.lnLoanBalanceDebtService = lnLoanBalanceDebtService;
	}

	public LnLoanBalanceAssetService getLnLoanBalanceAssetService() {
		return lnLoanBalanceAssetService;
	}

	public void setLnLoanBalanceAssetService(
			LnLoanBalanceAssetService lnLoanBalanceAssetService) {
		this.lnLoanBalanceAssetService = lnLoanBalanceAssetService;
	}

	public LnLoanBalanceService getLnLoanBalanceService() {
		return lnLoanBalanceService;
	}

	public void setLnLoanBalanceService(LnLoanBalanceService lnLoanBalanceService) {
		this.lnLoanBalanceService = lnLoanBalanceService;
	}

	public LnRecordInterviewService getLnRecordInterviewService() {
		return lnRecordInterviewService;
	}

	public void setLnRecordInterviewService(
			LnRecordInterviewService lnRecordInterviewService) {
		this.lnRecordInterviewService = lnRecordInterviewService;
	}

	public LnBusinessModelService getLnBusinessmodelService() {
		return lnBusinessmodelService;
	}

	public void setLnBusinessmodelService(
			LnBusinessModelService lnBusinessmodelService) {
		this.lnBusinessmodelService = lnBusinessmodelService;
	}

	public LnOpHistoryService getLnOpHistoryService() {
		return lnOpHistoryService;
	}

	public LnLoanHistoryService getLnLoanHistoryService() {
		return lnLoanHistoryService;
	}

	public void setLnLoanHistoryService(LnLoanHistoryService lnLoanHistoryService) {
		this.lnLoanHistoryService = lnLoanHistoryService;
	}

	public List<LnPledge> getLnPledgeList() {
		return lnPledgeList;
	}

	public void setLnPledgeList(List<LnPledge> lnPledgeList) {
		this.lnPledgeList = lnPledgeList;
	}

	public void setLnLoanPledgeService(LnLoanPledgeService lnLoanPledgeService) {
		this.lnLoanPledgeService = lnLoanPledgeService;
	}

	/**
	 * 贷款详情
	 *
	 * @return
	 */
	public String vLoan() {
		try {
			/*
			 * String confirm = request.getParameter("confirm"); String dun =
			 * request.getParameter("dun"); String isLoan =
			 * request.getParameter("isLoan"); String loanPhase =
			 * request.getParameter("loanPhase");
			 * //表示贷款阶段的（0表示所有贷款，其他的值与贷款状态值一样） if (confirm != null &&
			 * confirm.equals("confirm")) { request.setAttribute("confirm",
			 * confirm); } if (dun != null && dun.equals("dun")) {
			 * request.setAttribute("dun", dun); } if (isLoan != null &&
			 * !isLoan.equals("")){ request.setAttribute("isLoan",isLoan); } if
			 * (loanPhase != null && loanPhase.equals("0")){
			 * request.setAttribute("isAllLoan","1"); }
			 */
			return SUCCESS;
		} catch (Exception e) {
			logger.error("vLoan", e);
			return ERROR;
		}
	}

	public String vLoanView() {

		try {
			String isEdit = request.getParameter("isEdit"); // 是否可编辑
			request.setAttribute("isEdit", isEdit);
			String isAllotLoan = request.getParameter("isAllotLoan");// 是否是分配
			request.setAttribute("isAllotLoan", isAllotLoan);
			String isSurvey = request.getParameter("isSurvey");// 是否是调查
			request.setAttribute("isSurvey", isSurvey);
			String isApprove = request.getParameter("isApprove");// 是否是审核
			request.setAttribute("isApprove", isApprove);
			String isApproveRole = request.getParameter("isApproveRole"); // 当前审批的角色
			request.setAttribute("isApproveRole", isApproveRole);
			String isLend = request.getParameter("isLend"); // 是否需要放贷
			request.setAttribute("isLend", isLend);
			String approveReject = request.getParameter("approveReject");// 是否为驳回
			request.setAttribute("approveReject", approveReject);
			String isUpload = request.getParameter("isUpload"); // 是否上传
			request.setAttribute("isUpload", isUpload);
			// 所有资料

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("loanId", loanId);
			LnLoanInfo lnLoanInfo = lnLoanInfoService.selectLoanInfoList(param).get(0);

			//审批默认值 chief
			if(StringUtils.isNotBlank(isApprove)&&isApprove.equals("chief")){
				if(StringUtils.isBlank(lnLoanInfo.getResultMoney())&&StringUtils.isNotBlank(lnLoanInfo.getAdviceMoney())){
					lnLoanInfo.setResultMoney(lnLoanInfo.getAdviceMoney());
				}
				if(StringUtils.isBlank(lnLoanInfo.getResultLimitYear())&&StringUtils.isNotBlank(lnLoanInfo.getAdviceLimitYear())){
					lnLoanInfo.setResultLimitYear(lnLoanInfo.getAdviceLimitYear());
				}
				if(StringUtils.isBlank(lnLoanInfo.getResultRate())&&StringUtils.isNotBlank(lnLoanInfo.getAdviceRate())){
					lnLoanInfo.setResultRate(lnLoanInfo.getAdviceRate());
				}
				if(StringUtils.isBlank(lnLoanInfo.getResultRepayDate())&&StringUtils.isNotBlank(lnLoanInfo.getAdviceRepayDate())){
					lnLoanInfo.setResultRepayDate(lnLoanInfo.getAdviceRepayDate());
				}
				if((StringUtils.isBlank(lnLoanInfo.getResultRepayWayId())||!StringUtils.isNumeric(lnLoanInfo.getResultRepayWayId()))&&StringUtils.isNotBlank(lnLoanInfo.getAdviceRepayWayId())){
					lnLoanInfo.setResultRepayWayId(lnLoanInfo.getAdviceRepayWayId());
				}

			}

			request.setAttribute("lnLoanInfo",lnLoanInfo);
			logger.info("客户ID为"+lnLoanInfo.getCustomerId());
			request.setAttribute("customerId",lnLoanInfo.getLoanId());
			// 查询陪调人员
			if (lnLoanInfo.getTogetherSurveyUserId() != null && lnLoanInfo.getTogetherSurveyUserId() > 0) {
				SysUser sysUser = sysUserService.getSysUserById(lnLoanInfo.getTogetherSurveyUserId());
				request.setAttribute("togetherSurveyUsername", sysUser.getUserName());
			}

			LnLoan lnLoan = lnLoanService.selectLoanById(loanId);
			request.setAttribute("lnLoan",lnLoan);
			request.setAttribute("eventId", lnLoan.getLoanStatusId());
			request.setAttribute("loanStatusId", lnLoan.getLoanStatusId());
			// 获取最终审批金额最大值
			if (lnLoan.getLoanStatusId() == 4) {// 当审批的情况下获取
				request.setAttribute("maxAppLoanMoney",	sysParamService.getMaxAppLoanMoney());

				if(isDoubleApproval(lnLoanInfo.getAppLoanTypeId())){
					request.setAttribute("isDoubleApproval","1");
				}else{
					request.setAttribute("isDoubleApproval","0");
				}
			}

			// 查询历史操作记录
			List<LnOpHistory> hisList = lnOpHistoryService.getAllOpHistoryListByLoanId(loanId);
			request.setAttribute("hisList", hisList);
			// 所有资料
			Map<String, Object> photoMap = new HashMap<String, Object>();
			photoMap.put("loanId", loanId);
			photoMap.put("eventId", LoanConstants.LOAN_APPLY_EVENT);
			photoMap.put("statistics", 1);
			List<LoanData> dataList = customerDataService.getAllLoanDataById(photoMap);
			List<Photo> photoList = new ArrayList<Photo>();
			// 申请人照片资料
			for (LoanData d : dataList) {
				if (d.getDataType().equals(1)) {
					if (d.getCustomerId().equals(lnLoanInfo.getCustomerId())) {
						String photoUrl = d.getFilePath() + "/" + d.getFileName();
						Photo photo = new Photo();
						photo.setFileId(d.getFileId());
						photo.setPhotoId(d.getDataId());
						photo.setFilePath(photoUrl);

						photoList.add(photo);
					}
				}
			}
			request.setAttribute("photoList", photoList);

			HashMap<String, Object> checkBoxMessageMap = buildLoanCheckBoxMessage("SDHJY","DKYT",
					"DKLX", "DBFS", "DY", "ZY", "FLXS", "HYZK", "JYCD", "JYCS",
					"JZCSLX", "JZZK", "XXLY", "YSRSP", "ZJLX", "ZXQK", "ZY",
					"ZZXS", "HKFS", "FKFS", "APPROVE_PROCESS");
			request.setAttribute("checkBoxMessage", checkBoxMessageMap);
			// 共同借贷人
			loanCoBorrowerList = lnLoanDetailService.getLoanCoList(loanId,
					dataList);
			// 担保人
			loanGuarantorList = lnLoanDetailService.getLoanGuList(loanId,
					dataList);
			// 信贷历史
			lnCreditHistoryList = lnCreditHistoryService.getCreditHistoryByLoanId(loanId);

			// 抵质押物
			lnPledgeList = lnLoanPledgeService.getLnLoanPledgeByLoanId(loanId);
			//上下游
			listInBusinessReaches=inBusinessReachesService.selectReachesType(1,loanId);
			listInBusinessReaches1=inBusinessReachesService.selectReachesType(0,loanId);

			//lnDeviationAnalsysis=lnDeviationAnalsysisService.selectById(loanId);
			String cusIds = lnLoan.getApplyUserId() + "";
			for (int i = 0; i < loanCoBorrowerList.size(); i++) {
				cusIds += "," + loanCoBorrowerList.get(i).getCustomerId();
			}

			for (int i = 0; i < loanGuarantorList.size(); i++) {
				cusIds += "," + loanGuarantorList.get(i).getCustomerId();
			}

			request.setAttribute("cusIds", cusIds);
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("eventId", lnLoan.getLoanStatusId());
			paramMap.put("loanId", lnLoan.getLoanId());
			// 贷款表单附件信息
			List<Form> formAttachmentList = dataFormService.selectFormAttachment(paramMap);
			request.setAttribute("formAttachmentList", formAttachmentList);
			// 判断是否已经上传附件
			request.setAttribute("isExcitForm4", formAttachmentList.size());
			// request.setAttribute("isExcitForm4", "true");
			List<SysTeam> sysTeamList = sysTeamService.getSysTeamList();
			request.setAttribute("sysTeamList", sysTeamList);
			String[] sourcesIds = lnLoanInfo.getRegisterInfoSourceIds().split(",");
			HashMap<String, Integer> sourcesIdMap = new HashMap<String, Integer>();
			for (String string : sourcesIds) {
				sourcesIdMap.put(string, 1);
			}
			request.setAttribute("soucesIdMap", sourcesIdMap);
			request.setAttribute("ctx", "..");

			// 树形控件
			initParameter();

			if (lnLoan.getLoanStatusId() > 2) {
				String appLoanBak = lnLoanInfo.getAppFormBak();
				if(appLoanBak != null && !appLoanBak.equals("")){
					JSONObject jsonObject = JSONObject.fromObject(appLoanBak);
					//	String[] dateFormats = new String[]{"yyyy-MM-dd HH:mm:ss"};
					//   JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
					LnLoanAppFormBak lnLoanAppFormBak = (LnLoanAppFormBak) JSONObject.toBean(jsonObject, LnLoanAppFormBak.class);
					request.setAttribute("lnLoanInfoBak", lnLoanAppFormBak);
					request.setAttribute("showBak", "true");

					String[] sourcesIdsBak = lnLoanAppFormBak.getLnLoanInfo().getRegisterInfoSourceIds().split(",");
					HashMap<String, Integer> sourcesIdMapBak = new HashMap<String, Integer>();
					for (String string : sourcesIdsBak) {
						sourcesIdMapBak.put(string, 1);
					}
					request.setAttribute("sourcesIdsBak", sourcesIdMapBak);
				}
				//经营贷
				if("1".equals(lnLoanInfo.getAppLoanTypeId())){
					//经营模式
					LnBusinessModel lnBusinessModel=lnBusinessmodelService.selectByPrimary(loanId);
					//软信息
					if(lnBusinessModel!=null&&!StringUtils.isBlank(lnBusinessModel.getSorftInfo())){
						String[] sorftInfoIds = lnBusinessModel.getSorftInfo().split(",");
						HashMap<String, Integer> sorftInfoIdsMap = new HashMap<String, Integer>();
						for (String string : sorftInfoIds) {
							sorftInfoIdsMap.put(string, 1);
						}
						request.setAttribute("sorftInfoMap", sorftInfoIdsMap);

					}
					request.setAttribute("lnBusinessModel", lnBusinessModel);

				}

				//发展历史，访谈记录
				if("2".equals(lnLoanInfo.getAppLoanTypeId())){
					//发展历史
					LnLoanHistory loanHistory=lnLoanHistoryService.selectByPrimary(loanId);
					//家庭软信息
					if(loanHistory!=null&&!StringUtils.isBlank(loanHistory.getFamilyInfo())){
						String[] familyInfoIds = loanHistory.getFamilyInfo().split(",");
						HashMap<String, Integer> familyInfoIdsMap = new HashMap<String, Integer>();
						for (String string : familyInfoIds) {
							familyInfoIdsMap.put(string, 1);
						}
						request.setAttribute("familyInfoIdsMap", familyInfoIdsMap);
					}
					//但保软信息
					if(loanHistory!=null&&!StringUtils.isBlank(loanHistory.getGuaranteeInfo())){
						String[] guaranteeIds = loanHistory.getGuaranteeInfo().split(",");
						HashMap<String, Integer> guaranteeIdsMap = new HashMap<String, Integer>();
						for (String string : guaranteeIds) {
							guaranteeIdsMap.put(string, 1);
						}
						request.setAttribute("guaranteeIdsMap", guaranteeIdsMap);

					}
					request.setAttribute("lnLoanHistory", loanHistory);
					//访谈记录
					LnRecordInterview lnRecordInterview=lnRecordInterviewService.selectByPrimary(loanId);
					request.setAttribute("lnRecordInterview", lnRecordInterview);
				}

//				//资产负债表
//				LnLoanBalance lnLoanBalance=lnLoanBalanceService.selectBalanceByPrimary(loanId);
//
//				request.setAttribute("lnLoanBalance", lnLoanBalance);
//
//				if(lnLoanBalance!=null){
//					Integer loanBalanceId= lnLoanBalance.getLoanBalanceId();
//					//经营贷
//					if("1".equals(lnLoanInfo.getAppLoanTypeId())){
//						//房产
//						List<LnLoanBalanceHousing> housingList = lnLoanBalanceHousingService.selectHousingByPrimary(loanBalanceId);
//						this.getRequest().setAttribute("lnLoanBalanceHousingList", housingList);
//
//						//机动车
//						List<LnLoanBalanceVehicle> vehicleList = lnLoanBalanceVehicleService.selectVehicleByPrimary(loanBalanceId);
//
//						this.getRequest().setAttribute("lnLoanBalanceVehicleList", vehicleList);
//
//						//应收账明细
//						List<LnLoanBalanceReceivable> receivableList = lnLoanBalanceReceivableService.selectReceivableByPrimary(loanBalanceId);
//
//						this.getRequest().setAttribute("lnLoanBalanceReceivableList", receivableList);
//						//可变成本及其他交叉检验
//						List<LnLoanBalanceOther> otherList = lnLoanBalanceOtherService.selectOtherByPrimary(loanBalanceId);
//
//						this.getRequest().setAttribute("lnLoanBalanceOtherList", otherList);
//					}
//
//					//资产负债表资产
//					List<LnLoanBalanceAsset> assetList = lnLoanBalanceAssetService.selectAssetByPrimary(loanBalanceId);
//
//					this.getRequest().setAttribute("lnLoanBalanceAssetList", assetList);
//
//					//负债
//					List<LnLoanBalanceDebt> debtList = lnLoanBalanceDebtService.selectDebtByPrimary(loanBalanceId);
//
//					this.getRequest().setAttribute("lnLoanBalanceDedtList", debtList);
//
//				}
//
//				//损益表
//				List<LnLoanProfitandloss> loanProfitandlossList=lnLoanProfitandlossService.selectProfitandlossByPrimary(loanId);
//
//
//				if(loanProfitandlossList!=null&&loanProfitandlossList.size()>0){
//					LnLoanProfitandloss lnLoanProfitandloss	= loanProfitandlossList.get(0);
//					//经营贷
//					if("1".equals(lnLoanInfo.getAppLoanTypeId())){
//
//						Map<String,Object> profitandlossJyDetailMap=lnLoanProfitandlossJyDetailService.profitandlossJyDetail(lnLoanProfitandloss.getLoanProfitandlossId());
//						this.getRequest().setAttribute("profitandlossJyDetailMap", profitandlossJyDetailMap);
//					}
//
//					//消费贷
//					if("2".equals(lnLoanInfo.getAppLoanTypeId())){
//
//						Map<String,Object> profitandlossXfDetailMap=lnLoanProfitandlossXfDetailService.profitandlossXfDetail(lnLoanProfitandloss.getLoanProfitandlossId());
//						this.getRequest().setAttribute("profitandlossXfDetailMap", profitandlossXfDetailMap);
//					}
//					this.getRequest().setAttribute("lnLoanProfitandloss", lnLoanProfitandloss);
//				}

				//调查基本信息权限
				if(lnLoan.getLoanStatusId()==3&&getLoginInfo().getUserId().equals(lnLoan.getSurveyUserId())){
					request.setAttribute("write","1");
					request.setAttribute("isSurvey", "true");
				}
				String[] roles = this.getLoginInfo().getRoles();
				request.setAttribute("roles",roles[0]);
				if(roles[0].equals("1")){
					request.setAttribute("write","1");
				}

			}
			return SUCCESS;
		} catch (Exception e) {
			log.info("viewLoan:" + e.getLocalizedMessage());
			e.printStackTrace();
			return ERROR;
		}
	}


	/***
	 * 判断是否需要双人审批
	 * @param appLoanTypeId
	 * @return
	 */
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
		//TODO
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
	 * 机构管理员 (不 添加默认根节点) json树
	 *
	 * @return
	 */
	public void getDeptAdminDeptJsonRemRoot(Integer userId) {
		try {

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("userId", userId);
			Map<String, Integer> admintree = new HashMap<String, Integer>();// 机构管理员管理机构
			Map<String, Integer> businesstree = new HashMap<String, Integer>();// 业务主管管理机构
			List<SysDeptAuth> sysDeptAuthList = sysDeptAuthService
					.getSysDeptAuthList(parameters);
			int i = 0;
			for (SysDeptAuth sysDeptAuth : sysDeptAuthList) {
				if (sysDeptAuth.getRoleId().equals(2)) {
					admintree.put("" + sysDeptAuth.getDeptId(),
							sysDeptAuth.getDeptId());
				}
				if (sysDeptAuth.getRoleId().equals(3)) {
					businesstree.put("" + sysDeptAuth.getDeptId(),
							sysDeptAuth.getDeptId());
				}
			}
			List<SysDept> depts = deptService.getUserJsonTree();
			Set<Integer> deptNameList = new HashSet<Integer>();
			for (SysDept sysDept : depts) {
				deptNameList.add(sysDept.getDeptId());
				if (!deptNameList.contains(sysDept.getDeptParentId()))
					i++;// 控制节点展开
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
						if (i == 1) {
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
			request.setAttribute("adminJsonArray", adminArray);// 机构管理员 树
			request.setAttribute("businessJsonArray", jsonArray);// 业务主管 树
		} catch (Exception e) {
		}
	}

	/**
	 * 获取贷款所需要的下拉框信息
	 *
	 * @param param
	 * @return
	 */
	private HashMap<String, Object> buildLoanCheckBoxMessage(String... param) {

		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		for (int i = 0; i < param.length; i++) {
			paramMap.put("dictionaryName", param[i]);
			List<LnLoanInfoDictionary> lnLoanInfoDictionaryList = lnLoanInfoDictionaryService
					.selectLoanInfoDictionaryList(paramMap);
			resultMap.put(param[i], lnLoanInfoDictionaryList);
			paramMap.remove("dictionaryKey");
		}
		return resultMap;
	}

	public String vLoanViewBak() {
		// TODO:修改
		return SUCCESS;
		/*
		 * try { long startTime = Calendar.getInstance().getTimeInMillis(); //
		 * 贷款基本信息 LnLoan loan = lnLoanService.getLnLoanById(loanId); // 已优化
		 * request.setAttribute("loan", loan); //主要区分是贷款管理还是审计管理 String isLoan =
		 * request.getParameter("isLoan"); if (isLoan != null &&
		 * !isLoan.equals("") && loan.getLoanStatusId().equals(2)){
		 * request.setAttribute("isLoan",isLoan); }
		 * 
		 * String edit = request.getParameter("edit");
		 * request.setAttribute("edit",edit);
		 * 
		 * String vVerifyView = request.getParameter("vVerifyView");
		 * request.setAttribute("vVerifyView",vVerifyView);
		 * 
		 * String loanVerifyView = request.getParameter("loanVerifyView");
		 * request.setAttribute("loanVerifyView",loanVerifyView);
		 * 
		 * String isAllLoan = request.getParameter("isAllLoan"); if
		 * (StringUtils.isNotEmpty(isAllLoan) && isAllLoan.equals("1")){
		 * //所有贷款中点击查看或编辑 request.setAttribute("isAllLoan",1); }
		 * 
		 * //需求变更后
		 * 
		 * // 所有资料 Map<String,Object> photoMap = new HashMap<String, Object>();
		 * photoMap.put("loanId",loanId); photoMap.put("statistics",1); //
		 * List<LoanData> dataList =
		 * customerDataService.getAllLoanDataById(photoMap); List<LoanData>
		 * dataList = customerDataService.getAllLoanDataByIdOptimize(photoMap);
		 * //已优化
		 * 
		 * //eventId&dataType --> count Map Map<String,Integer>
		 * cusDataOnLoanPerson = new HashMap<String, Integer>(); for (LoanData
		 * loanData : dataList){ String key =
		 * "loanData"+loanData.getCustomerId()
		 * +loanData.getEventId()+loanData.getDataType(); if
		 * (cusDataOnLoanPerson.containsKey(key)){
		 * cusDataOnLoanPerson.put(key,cusDataOnLoanPerson.get(key)+1); }else {
		 * cusDataOnLoanPerson.put(key,1); } }
		 * request.setAttribute("cusDataOnLoanPerson",cusDataOnLoanPerson);
		 * 
		 * // // 共同借贷人 // List<LnLoanCoBorrowerBean> loanCoBorrowerList =
		 * lnLoanDetailService.getLoanCoList( // loanId, dataList); //
		 * request.setAttribute("loanCoBorrowerList", loanCoBorrowerList);
		 * 
		 * // 担保人 List<LnLoanGuarantorBean> loanGuarantorList =
		 * lnLoanDetailService.getLoanGuList(loanId, dataList); //已优化
		 * request.setAttribute("loanGuarantorList", loanGuarantorList);
		 * 
		 * // 资料管理 List<LoanData> eventList3 = new ArrayList<LoanData>();//
		 * 审批贷款list List<LoanData> eventList4 = new ArrayList<LoanData>();//
		 * 落实贷款list
		 * 
		 * // 审批贷款list List<LoanData> eventList3Self = new
		 * ArrayList<LoanData>();// 贷款人list for (LoanData d : eventList3) { if
		 * (d.getCustomerId().equals(loan.getCustomerId())) {
		 * eventList3Self.add(d); } } request.setAttribute("eventList3Count",
		 * lnLoanDetailService.getLoanDataListMaxRowNum(eventList3));
		 * request.setAttribute("eventList3Self", eventList3Self);
		 * 
		 * // 落实贷款list List<LoanData> eventList4Self = new
		 * ArrayList<LoanData>();// 贷款人list for (LoanData d : eventList4) { if
		 * (d.getCustomerId().equals(loan.getCustomerId())) {
		 * eventList4Self.add(d); } } request.setAttribute("eventList4Count",
		 * lnLoanDetailService.getLoanDataListMaxRowNum(eventList4));
		 * request.setAttribute("eventList4Self", eventList4Self);
		 * 
		 * // 贷款催收 // 催收记录资料(包括异常催收) // 判断催收形式：正常和异常 int iiiii = 0; //
		 * 用于图片数量，页面上可以上下翻页 Integer isNogood = loan.getIsNogood(); List<Integer>
		 * customerDataIdList = new ArrayList<Integer>();
		 * List<LnExceptionDunLog> lnExceptionDunLogList = null; List<LnDunLog>
		 * lnDunLogList = null; if (isNogood != null && isNogood == 1) { //
		 * 该贷款客户为不良客户，为异常催收 lnExceptionDunLogList =
		 * lnExceptionDunLogService.getExpDunLogByLoanId(loanId); //已优化 for
		 * (LnExceptionDunLog lnExceptionDunLog : lnExceptionDunLogList) {
		 * //web端电话催收特殊处理 if (lnExceptionDunLog.getUpdateUser() != null) {
		 * RecordDetail d =
		 * recordInfoService.getRecordInfoById(lnExceptionDunLog
		 * .getUpdateUser()); if (d != null) {
		 * lnExceptionDunLog.setRemark(d.getRemark()); } }
		 * 
		 * if (lnExceptionDunLog.getCustomerDataId() != null &&
		 * !lnExceptionDunLog.getCustomerDataId().equals(0)){
		 * customerDataIdList.add(lnExceptionDunLog.getCustomerDataId()); } }
		 * request.setAttribute("lnExceptionDunLogList",lnExceptionDunLogList);
		 * } else { // 正常催收 lnDunLogList =
		 * lnDunLogService.getDunLogByLoanId(loanId); //已优化 for (LnDunLog
		 * lnDunLog : lnDunLogList) { //web端电话催收特殊处理 if
		 * (lnDunLog.getUpdateUser() != null) { RecordDetail d =
		 * recordInfoService.getRecordInfoById(lnDunLog.getUpdateUser()); if (d
		 * != null) { lnDunLog.setRemark(d.getRemark()); } }
		 * 
		 * if (lnDunLog.getCustomerDataId() != null &&
		 * !lnDunLog.getCustomerDataId().equals(0)){
		 * customerDataIdList.add(lnDunLog.getCustomerDataId()); } }
		 * request.setAttribute("lnDunLogList",lnDunLogList); } // 查询催收资料
		 * Map<String, Object> paramMap = new HashMap<String, Object>();
		 * List<PadLoanData> pLoanDataList = null; if (customerDataIdList.size()
		 * > 0) { paramMap.put("customerDataIds", customerDataIdList);
		 * pLoanDataList = customerDataService.getDunDataById(paramMap); } //
		 * 得到所有的催收资料 Map<String,Integer> dunDataCountMap = new HashMap<String,
		 * Integer>(); if (pLoanDataList != null && pLoanDataList.size() > 0){
		 * for (PadLoanData padLoanData : pLoanDataList){ String key =
		 * String.valueOf
		 * (padLoanData.getCustomerDataId())+String.valueOf(padLoanData
		 * .getDataType()); if (dunDataCountMap.containsKey(key)){
		 * dunDataCountMap.put(key,dunDataCountMap.get(key)+1); }else {
		 * dunDataCountMap.put(key,1); } } }
		 * request.setAttribute("dunDataCountMap",dunDataCountMap);
		 * 
		 * // 贷款历史操作 List<LnOpHistory> hisList =
		 * lnOpHistoryService.getAllOpHistoryListByLoanId(loanId); //已优化 for
		 * (LnOpHistory his : hisList) { if (his.getAfterStatusName() != null &&
		 * his.getBeforeStatusName() != null &&
		 * his.getAfterStatusName().equals(his.getBeforeStatusName())) { //
		 * 如果状态相同，在值显示after的状态 his.setBeforeStatusName(""); } if
		 * (StringUtils.isNotEmpty(his.getRemark())){ String[] remarks =
		 * his.getRemark().split("\\|"); if (remarks.length == 2){
		 * his.setPreRemark(remarks[0]); his.setSufRemark(remarks[1]); }else if
		 * (remarks.length == 1){ his.setSufRemark(remarks[0]); } } }
		 * request.setAttribute("hisList", hisList);
		 * 
		 * // 审计历史操作 List<LnVerifyHistory> vHisList = lnVerifyHistoryService
		 * .getLnVerifyHistoryByLoanId(loanId); //已优化
		 * request.setAttribute("vHisList", vHisList);
		 * 
		 * // 判断是否为审计模块贷款 String isVerify = request.getParameter("isVerify");
		 * request.setAttribute("isVerify", isVerify);
		 * 
		 * // 贷款落实与贷款催收区分(贷款状态都为6) String confirm =
		 * request.getParameter("confirm"); if (confirm != null) {
		 * request.setAttribute("confirm", confirm); } String dun =
		 * request.getParameter("dun"); if (dun != null) {
		 * request.setAttribute("dun", dun); }else { if
		 * (loan.getLoanStatusId().equals(LoanConstants.LOAN_LENDED_STATUS) ||
		 * loan.getLoanStatusId().equals(LoanConstants.LOAN_PAYED_STATUS)){
		 * request.setAttribute("dun","dun"); } }
		 * 
		 * //判断是查看时的详情还是编辑时的详情 String isEdit = request.getParameter("isEdit");
		 * request.setAttribute("isEdit",isEdit);
		 * 
		 * //判断贷款类型 if (loan.getLoanTypeId() != null){ if
		 * (loan.getLoanTypeId().equals(LoanConstants.LOAN_CONSUME_TYPE_ID)){
		 * //消费类型 request.setAttribute("consume","true"); }else if
		 * (loan.getLoanTypeId().equals(LoanConstants.LOAN_ENGAGE_TYPE_ID)){
		 * //经营类型 request.setAttribute("engage","true"); } }
		 * 
		 * //当贷款处于放贷状态时，点击查看详情，不应该出现还款计划列表，通过以下设置参数去除还款计划列表 if
		 * (loan.getLoanStatusId().equals(LoanConstants.LOAN_LENDING_STATUS)){
		 * request.setAttribute("isLending","true"); }
		 * 
		 * //添加还款计划列表 List<LnRepaymentPlan> queryList=
		 * lnRepaymentPlanService.queryLnRepaymentPlan(loanId); // 已优化
		 * request.setAttribute("lnRepaymentPlanList",queryList);
		 * 
		 * //添加异常还款计划表 List<LnExceptionRepaymentPlan> exceptionRepaymentPlanList
		 * =
		 * lnExceptionRepaymentPlanService.queryLnExceptionRepaymentPlan(loanId
		 * ); if (exceptionRepaymentPlanList != null &&
		 * exceptionRepaymentPlanList.size() > 0){
		 * request.setAttribute("exceptionRepaymentPlanList"
		 * ,exceptionRepaymentPlanList); }
		 * 
		 * //贷款申请信息 Map<String, Object> param =new HashMap<String, Object>();
		 * param.put("loanId",loanId); List<LnLoanInfo> loanInfoList =
		 * lnLoanInfoService.selectLoanInfoList(param); //已优化 if(loanInfoList !=
		 * null &&loanInfoList.size()>0){ LnLoanInfo lnLoanInfo =
		 * loanInfoList.get(0); request.setAttribute("lnLoanInfo",lnLoanInfo); }
		 * if(loan.getLoanTypeId() != null && loan.getLoanTypeId()==2){
		 * request.setAttribute("consume","true"); } long endTime =
		 * Calendar.getInstance().getTimeInMillis();
		 * log.info("查看贷款详情所花时间："+(endTime - startTime)); return SUCCESS; }
		 * catch (Exception e) { logger.error("vLoanView", e); return ERROR; }
		 */}

	/**
	 * 刷新资料管理页面列表
	 *
	 * @return
	 */
	public String flashDataManagerView() {
		try {
			// 贷款基本信息
			LnLoan loan = lnLoanService.getLnLoanById(loanId);
			request.setAttribute("loan", loan);

			// 所有资料
			Map<String, Object> photoMap = new HashMap<String, Object>();
			photoMap.put("loanId", loanId);
			photoMap.put("statistics", 1);
			List<LoanData> dataList = customerDataService
					.getAllLoanDataById(photoMap);

			// eventId&dataType --> count Map
			Map<String, Integer> cusDataOnLoanPerson = new HashMap<String, Integer>();
			for (LoanData loanData : dataList) {
				String key = "loanData" + loanData.getCustomerId()
						+ loanData.getEventId() + loanData.getDataType();
				if (cusDataOnLoanPerson.containsKey(key)) {
					cusDataOnLoanPerson.put(key,
							cusDataOnLoanPerson.get(key) + 1);
				} else {
					cusDataOnLoanPerson.put(key, 1);
				}
			}
			request.setAttribute("cusDataOnLoanPerson", cusDataOnLoanPerson);

			// 共同借贷人
			List<LnLoanCoBorrowerBean> loanCoBorrowerList = lnLoanDetailService
					.getLoanCoList(loanId, dataList);
			request.setAttribute("loanCoBorrowerList", loanCoBorrowerList);

			// 担保人
			List<LnLoanGuarantorBean> loanGuarantorList = lnLoanDetailService
					.getLoanGuList(loanId, dataList);
			request.setAttribute("loanGuarantorList", loanGuarantorList);
			//损益
			request.setAttribute("lnProfitLossProdList", lnProfitLossProdList);

			return SUCCESS;
		} catch (Exception e) {
			logger.error("vLoanView", e);
			return ERROR;
		}

	}

	public String openCusDataListTableView() {
		try {
			String customerIdStr = request.getParameter("customerId");
			String dataType = request.getParameter("dataType");
			String loanIdStr = request.getParameter("loanId");
			String eventIdStr = request.getParameter("eventId");
			String customerDataIdStr = request.getParameter("customerDataId");
			if (StringUtils.isNotEmpty(customerIdStr)
					&& StringUtils.isNotEmpty(loanIdStr)
					&& StringUtils.isNotEmpty(dataType)
					&& StringUtils.isNotEmpty(eventIdStr)) {
				BaseCrmCustomer crmCustomer = crmCustomerService
						.getCrmCustomerById(Integer.parseInt(customerIdStr));

				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("loanId", Integer.parseInt(loanIdStr));
				paramMap.put("customerId", Integer.parseInt(customerIdStr));
				paramMap.put("eventId", Integer.parseInt(eventIdStr));

				if (customerDataIdStr != null
						&& !customerDataIdStr.trim().equals("")) {
					paramMap.put("customerDataId",
							Integer.parseInt(customerDataIdStr));
				}

				if (dataType.equals("2")) {
					// 录音
					List<Audio> audioList = dataAudioService
							.getAudioOnLoanFlow(paramMap);
					for (Audio audio : audioList) {
						if (audio.getRecordLength() != null) {
							audio.setRecordTime(recordInfoService
									.changTime(String.valueOf(audio
											.getRecordLength())));
						}
					}
					request.setAttribute("dataList", audioList);
				} else if (dataType.equals("3")) {
					// 视频
					List<Video> videoList = dataVideoService
							.getVideoOnLoanFlow(paramMap);
					for (Video video : videoList) {
						if (video.getRecordLength() != null) {
							video.setRecordTime(recordInfoService
									.changTime(String.valueOf(video
											.getRecordLength())));
						}
					}
					request.setAttribute("dataList", videoList);
				} else if (dataType.equals("4")) {
					// 表单
					List<Form> formList = dataFormService
							.getFormOnLoanFlow(paramMap);
					request.setAttribute("dataList", formList);
				}
				request.setAttribute("dataType", dataType);
				request.setAttribute("title", crmCustomer.getCustomerName());
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("ViewLoanDetailAction % openCusDataListTableView", e);
			return ERROR;
		}
	}

	/**
	 * getter setter *
	 */
	public void setLnLoanService(LnLoanService lnLoanService) {
		this.lnLoanService = lnLoanService;
	}

	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public void setLoanId(String loanId) {
		if(StringUtils.isNotBlank(loanId)&&StringUtils.isNumeric(loanId)){
			this.loanId = Integer.valueOf(loanId);
		}
	}

	public void setCustomerDataService(CustomerDataService customerDataService) {
		this.customerDataService = customerDataService;
	}

	public void setLnOpHistoryService(LnOpHistoryService lnOpHistoryService) {
		this.lnOpHistoryService = lnOpHistoryService;
	}

	public void setLnLoanDetailService(LnLoanDetailService lnLoanDetailService) {
		this.lnLoanDetailService = lnLoanDetailService;
	}

	public LnVerifyHistoryService getLnVerifyHistoryService() {
		return lnVerifyHistoryService;
	}

	public void setLnVerifyHistoryService(
			LnVerifyHistoryService lnVerifyHistoryService) {
		this.lnVerifyHistoryService = lnVerifyHistoryService;
	}

	public void setLnExceptionDunLogService(
			LnExceptionDunLogService lnExceptionDunLogService) {
		this.lnExceptionDunLogService = lnExceptionDunLogService;
	}

	public CrmCustomerService getCrmCustomerService() {
		return crmCustomerService;
	}

	public void setCrmCustomerService(CrmCustomerService crmCustomerService) {
		this.crmCustomerService = crmCustomerService;
	}

	public void setLnDunLogService(LnDunLogService lnDunLogService) {
		this.lnDunLogService = lnDunLogService;
	}

	public DataAudioService getDataAudioService() {
		return dataAudioService;
	}

	public void setDataAudioService(DataAudioService dataAudioService) {
		this.dataAudioService = dataAudioService;
	}

	public DataVideoService getDataVideoService() {
		return dataVideoService;
	}

	public void setDataVideoService(DataVideoService dataVideoService) {
		this.dataVideoService = dataVideoService;
	}

	public DataFormService getDataFormService() {
		return dataFormService;
	}

	public void setDataFormService(DataFormService dataFormService) {
		this.dataFormService = dataFormService;
	}

	public RecordInfoService getRecordInfoService() {
		return recordInfoService;
	}

	public void setRecordInfoService(RecordInfoService recordInfoService) {
		this.recordInfoService = recordInfoService;
	}

	public LnRepaymentPlanService getLnRepaymentPlanService() {
		return lnRepaymentPlanService;
	}

	public void setLnRepaymentPlanService(
			LnRepaymentPlanService lnRepaymentPlanService) {
		this.lnRepaymentPlanService = lnRepaymentPlanService;
	}

	public LnLoanInfoService getLnLoanInfoService() {
		return lnLoanInfoService;
	}

	public void setLnLoanInfoService(LnLoanInfoService lnLoanInfoService) {
		this.lnLoanInfoService = lnLoanInfoService;
	}

	public LnExceptionRepaymentPlanService getLnExceptionRepaymentPlanService() {
		return lnExceptionRepaymentPlanService;
	}

	public void setLnExceptionRepaymentPlanService(
			LnExceptionRepaymentPlanService lnExceptionRepaymentPlanService) {
		this.lnExceptionRepaymentPlanService = lnExceptionRepaymentPlanService;
	}

	/*
	public LnLoanInfo getLnLoanInfo() {
		return lnLoanInfo;
	}

	public void setLnLoanInfo(LnLoanInfo lnLoanInfo) {
		this.lnLoanInfo = lnLoanInfo;
	}
	 */
	public LnCreditHistory getLnCreditHistory() {
		return lnCreditHistory;
	}

	public void setLnCreditHistory(LnCreditHistory lnCreditHistory) {
		this.lnCreditHistory = lnCreditHistory;
	}

	public List<LnLoanCoBorrowerBean> getLoanCoBorrowerList() {
		return loanCoBorrowerList;
	}

	public void setLoanCoBorrowerList(
			List<LnLoanCoBorrowerBean> loanCoBorrowerList) {
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

	public void setLnCreditHistoryService(
			LnCreditHistoryService lnCreditHistoryService) {
		this.lnCreditHistoryService = lnCreditHistoryService;
	}

	public void setLnLoanInfoDictionaryService(
			LnLoanInfoDictionaryService lnLoanInfoDictionaryService) {
		this.lnLoanInfoDictionaryService = lnLoanInfoDictionaryService;
	}
	/*
        public LnLoan getLnLoan() {
            return lnLoan;
        }

        public void setLnLoan(LnLoan lnLoan) {
            this.lnLoan = lnLoan;
        }
    */
	public void setSysTeamService(SysTeamService sysTeamService) {
		this.sysTeamService = sysTeamService;
	}

	public void setSysUserService(SysUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public void setSysParamService(SysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

	public SysDeptAuthService getSysDeptAuthService() {
		return sysDeptAuthService;
	}

	public void setSysDeptAuthService(SysDeptAuthService sysDeptAuthService) {
		this.sysDeptAuthService = sysDeptAuthService;
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

	public List<SysRoleAuth> getSysRoleAuthList() {
		return sysRoleAuthList;
	}

	public void setSysRoleAuthList(List<SysRoleAuth> sysRoleAuthList) {
		this.sysRoleAuthList = sysRoleAuthList;
	}

	public LnProfitLossProdService getLnProfitLossProdService() {
		return lnProfitLossProdService;
	}

	public void setLnProfitLossProdService(LnProfitLossProdService lnProfitLossProdService) {
		this.lnProfitLossProdService = lnProfitLossProdService;
	}

}
