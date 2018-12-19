package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ouyl
 * @version $Id: BaseLnLoanInfo.java,v 0.1 13-6-25 ouyl Exp $
 */
public class BaseLnLoanInfo extends BaseObject implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6985732263304459739L;
	private Integer loanInfoId; // 主键ID
	private Integer loanId; // 贷款ID
	private Integer customerId; // 客户ID
	private String refuseLoanStateId; // 拒绝时贷款状态
	private String refuseReasonTypeId; // 拒绝原因大类
	private String refuseReasonSubTypeId; // 拒绝原因小类
	private String refuseContent; // 拒绝内容
	private Integer togetherSurveyUserId;// 陪调人员编号
	private Date togetherSurveyDate;// 陪调人员时间
	private String assignRemark; // 分配备注
	private Integer registerMicroBizCenter; // 微贷事业中心
	private String registerRecommendBank; // 推荐支行
	private Date registerApplyDate; // 申请日期
	private Date registerLoanDate; // 需要贷款日期
	private String registerInfoSourceIds; // 信息来源编号
	private String registerDirectMarket; // 直接营销
	private String registerExistCusIntroduct; // 现有客户介绍
	private String registerInfoSourceOther; // 信息来源其他

	//REGISTER_SOURCE_FFKKHJS --非放款客户/朋友/同学介绍
	private String registerSourceFFKKHJS;
	//REGISTER_SOURCE_WX --微信
	private String registerSourceWX;
	//REGISTER_SOURCE_MM --陌陌
	private String registerSourceMM;
	//REGISTER_SOURCE_CCK  --插车卡
	private String registerSourceCCK;
	//REGISTER_SOURCE_HNYGJS   --行内员工介绍
	private String registerSourceHNYGJS;
	//REGISTER_SOURCE_DHYX  --电话营销
	private String registerSourceDHYX;
	//REGISTER_SOURCE_HZSHTJ  --合作商户推荐
	private String registerSourceHZSHTJ;
	//REGISTER_SOURCE_ZXSQ   --在线申请
	private String registerSourceZXSQ;

	private String appLoanTypeId; // 贷款类型
	private String appLoanSubTypeId; // 贷款 子类型
	private Double appMoney; // 申请金额
	private String appLimitYear; // 申请期限
	private String appRepaymentMonth; // 月还款能力
	private String appLoanPurpose; // 贷款用途
	private String appGuarantorWayId; // 担保方式
	private String appMortgageId; // 抵押
	private String appPledgeId;// 质押
	private String appMortgageOther; // 抵押其他
	private String appPledgeOther;// 质押其他
	private String appMaxInstallmentMoney; // 最大分期还款额
	private String appTotalProjectCost; // 总项目成本
	private String appUserDetail; // 使用明细
	private String cusName; // 申请人姓名
	private String cusBirthday; // 申请人出生日期
	private String cusSex; // 申请人性别
	private String cusIdtypeId; // 申请人证件类型
	private String cusIdcard; // 申请人证件号码
	private String cusMobilePhone; // 申请人手机号
	private String cusPhone; // 申请人固定电话
	private String cusEducationId; // 申请人教育程度
	private String cusLivingStatusId; // 申请人居住状况
	private String cusAddress; // 申请人家庭住址
	private String cusMarriage; // 申请人婚姻状况
	private String cusRegisteredResidence; // 申请人户口
	private String cusLivingTypeId; // 申请人居住场所类型
	private String cusLivingAddress;// 申请人现居住地址
	private String cusLivingStartDate; // 申请人居住起始年月
	private String cusHousePattern; // 申请人住房格局
	private String cusHouseArea; // 申请人住房面积
	private String cusHouseDecorateId; // 申请人住房装修情况
	private String cusEconomicState; // 申请人经济网状态
	private String cusNote; // 申请人个人信息备注
	private String cusNetAge; // 申请人网龄
	private String cusCommonOperaterName; // 共同经营者姓名
	private String cusCommonOperaterIdcard; // 共同经营者身份证号
	private String cusCommonOperaterMobilePhone; // 共同经营者手机号
	private String cusFirstImpress; // 第一印象
	private String cusMateName; // 配偶姓名
	private String cusMateIdcard; // 配偶身份证号
	private String cusCompanyName; // 配偶单位名称
	private String cusMateMobilePhone; // 配偶手机号
	private String cusMatePhone; // 配偶固定电话
	private String cusMateCompanyPhone; // 配偶单位电话
	private String cusCompanyNature; // 配偶单位性质
	private String cusMateJob; // 配偶工作岗位
	private String cusMateWorkYear; // 配偶工作年限
	private String cusMateAddress; // 配偶单位地址
	private String cusMateMonthlyIncomingId; // 配偶月收入水平
	private String cusChdInfo; // 子女情况
	private String cusFamilyNum; // 家庭人数
	private String cusParentInfo; // 父母情况
	private String cusLocalYear; // 本地居住年限
	private String companyName; // 工作单位
	private String companyNature; // 单位性质
	private String companyJob; // 工作岗位
	private String companyWorkYear; // 工作年限
	private String companyMonthlyIncoming; // 月收入水平
	private String companyAddress; // 单位地址
	private String bizCompany; // 企业名称
	private String bizScope; // 经营范围
	private String bizOrgId; // 组织形式
	private String bizOrgCode; // 组织机构代码
	private String bizLicenseNum; // 营业执照号码
	private String bizSocialCreditNum; // 社会信用号码
	private String bizActualOperator; // 实际经营者
	private String bizAddress; // 实际经营地址
	private String bizShareholderProportion; // 股东占比情况
	private String bizPlaceId; // 经营场所
	private String bizArea; // 经营场所面积
	private String bizLawFormId; // 法律形式
	private String bizLeagl; // 法人代表
	private String bizLeaglMobilePhone; // 电话号码
	private String bizEmployeeNum; // 雇员人数
	private String bizOpenDate; // 开业日期
	private String bizIsBelongMybank; // 是否我行客户
	private String isLocal;//是否为本地居民
	private String bizMybankNo; // 我行的卡号
	private String bizNote; // 生意信息备注
	private String cafMonthlySales; // 月销售额
	private String cafBusySeason; // 旺季
	private String cafLowSeason; // 淡季
	private String isSeason; //是否有淡旺季
	private String seasonRemark; // 淡旺季描述

	private String cafReceivableMoney; // 应收账款
	private String cafGrossMargin; // 毛利润
	private String cafNetMargin;// 净利润率
	private String cafInventory; // 存货
	private String cafDebt; // 负债
	private String cafTotalMoney; // 总资产
	private String cafOther; // 其它收入
	private String cafTotalCreditCard; // 信用卡总数
	private String cafTotalCreditMoney; // 授信总金额
	private String cafTotalOverdraftMoney; // 透支总金额
	private String cafOverdueSituation; // 逾期情况
	private String cafLoanCard; // 贷款卡
	private String cafBorrowerCreidtDesc; // 借款人征信说明
	private String cafGuarantorCreidtDesc; // 担保人征信说明
	private String adviceMoney; // 建议金额
	private String adviceLimitYear; // 建议期限
	private String adviceRate; // 建议利率
	private String adviceRepayDate; // 建议还款日
	private String adviceLendWayId; // 放款方式
	private String adviceRepayWayId; // 还款方式
	private String adviceOther; // 其他
	private String adviceNote; // 调查备注
	private String adviceVerdict; // 调查结论 1通过，0拒绝
	private String resultMoney; // 决议金额
	private String resultLimitYear; // 决议期限
	private String resultRate; // 决议利率
	private String resultRepayDate; // 决议还款日
	private String resultInstalRepayMoney; // 分期还款额
	private String resultRiskWarm; // 风险提示
	private String resultSurveyExisProblems; // 调查资料存在问题
	private String resultNote; // 审批备注
	private String resultVerdict;//审贷会决议
	private String resultRepayWayId;//还款方式N
	private String resultPurpose;//贷款用途
	private String isCrmTask;//是否算作任务
	private String advanceRepay;//提前还款?
	private String lnMode;//贷款模式 1新增 2转贷
	private String lnBenefit;//利率优惠？
	private String originalRate;//原利率
	private String resultLnPremiss;//放款条件
	private String lendContractNum; // 合同号
	private String lendConfrimContractNum; // 确认合同号
	private String lendNote; // 放贷备注
	private String registerSaveStatus; // 等级信息保存状态
	private String applicationInfo; // 申请信息保存状态
	private String personalInfo; // 个人信息保存状态
	private String familyInfo; // 家庭信息保存状态
	private String businessInfo; // 经营信息保存状态
	private String signInfo; // 客户签字保存状态
	private String signInfoPath; // 客户签名路径
	private int isNoGood; // 是否为不良用户
	private String appFormBak; //申请备份信息
	private Date createDate;// 创建时间
	private Date updateDate;// 修改时间

	private String guarantyIncomeCheck;//担保人收入校验
	private String guarantyDebtRemark;//负债担保说明
	private String guarantyOtherRemark;//其他

	public String getAppLoanSubTypeId() {
		return appLoanSubTypeId;
	}

	public void setAppLoanSubTypeId(String appLoanSubTypeId) {
		this.appLoanSubTypeId = appLoanSubTypeId;
	}

	public String getIsSeason() {
		return isSeason;
	}

	public void setIsSeason(String isSeason) {
		this.isSeason = isSeason;
	}

	public String getSeasonRemark() {
		return seasonRemark;
	}

	public void setSeasonRemark(String seasonRemark) {
		this.seasonRemark = seasonRemark;
	}

	public String getGuarantyIncomeCheck() {
		return guarantyIncomeCheck;
	}

	public void setGuarantyIncomeCheck(String guarantyIncomeCheck) {
		this.guarantyIncomeCheck = guarantyIncomeCheck;
	}

	public String getGuarantyDebtRemark() {
		return guarantyDebtRemark;
	}

	public void setGuarantyDebtRemark(String guarantyDebtRemark) {
		this.guarantyDebtRemark = guarantyDebtRemark;
	}

	public String getGuarantyOtherRemark() {
		return guarantyOtherRemark;
	}

	public void setGuarantyOtherRemark(String guarantyOtherRemark) {
		this.guarantyOtherRemark = guarantyOtherRemark;
	}


	public String getResultVerdict() {
		return resultVerdict;
	}

	public void setResultVerdict(String resultVerdict) {
		this.resultVerdict = resultVerdict;
	}

	public String getResultRepayWayId() {
		return resultRepayWayId;
	}

	public void setResultRepayWayId(String resultRepayWayId) {
		this.resultRepayWayId = resultRepayWayId;
	}

	public String getResultPurpose() {
		return resultPurpose;
	}

	public void setResultPurpose(String resultPurpose) {
		this.resultPurpose = resultPurpose;
	}

	public String getIsCrmTask() {
		return isCrmTask;
	}

	public void setIsCrmTask(String isCrmTask) {
		this.isCrmTask = isCrmTask;
	}

	public String getAdvanceRepay() {
		return advanceRepay;
	}

	public void setAdvanceRepay(String advanceRepay) {
		this.advanceRepay = advanceRepay;
	}

	public String getLnMode() {
		return lnMode;
	}

	public void setLnMode(String lnMode) {
		this.lnMode = lnMode;
	}

	public String getLnBenefit() {
		return lnBenefit;
	}

	public void setLnBenefit(String lnBenefit) {
		this.lnBenefit = lnBenefit;
	}

	public String getOriginalRate() {
		return originalRate;
	}

	public void setOriginalRate(String originalRate) {
		this.originalRate = originalRate;
	}

	public String getResultLnPremiss() {
		return resultLnPremiss;
	}

	public void setResultLnPremiss(String resultLnPremiss) {
		this.resultLnPremiss = resultLnPremiss;
	}

	public String getAdviceVerdict() {
		return adviceVerdict;
	}

	public void setAdviceVerdict(String adviceVerdict) {
		this.adviceVerdict = adviceVerdict;
	}

	public String getAppFormBak() {
		return appFormBak;
	}

	public void setAppFormBak(String appFormBak) {
		this.appFormBak = appFormBak;
	}

	public String getBizMybankNo() {
		return bizMybankNo;
	}

	public void setBizMybankNo(String bizMybankNo) {
		this.bizMybankNo = bizMybankNo;
	}

	public String getCafNetMargin() {
		return cafNetMargin;
	}

	public void setCafNetMargin(String cafNetMargin) {
		this.cafNetMargin = cafNetMargin;
	}
	public BaseLnLoanInfo() {
	}

	public BaseLnLoanInfo(Integer loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

	public String getCusLivingAddress() {
		return cusLivingAddress;
	}

	public void setCusLivingAddress(String cusLivingAddress) {
		this.cusLivingAddress = cusLivingAddress;
	}

	public Integer getLoanInfoId() {
		return loanInfoId;
	}

	public void setLoanInfoId(Integer loanInfoId) {
		this.loanInfoId = loanInfoId;
	}

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

	public String getRefuseLoanStateId() {
		return refuseLoanStateId;
	}

	public void setRefuseLoanStateId(String refuseLoanStateId) {
		this.refuseLoanStateId = refuseLoanStateId;
	}

	public String getRefuseReasonTypeId() {
		return refuseReasonTypeId;
	}

	public void setRefuseReasonTypeId(String refuseReasonTypeId) {
		this.refuseReasonTypeId = refuseReasonTypeId;
	}

	public String getRefuseReasonSubTypeId() {
		return refuseReasonSubTypeId;
	}

	public void setRefuseReasonSubTypeId(String refuseReasonSubTypeId) {
		this.refuseReasonSubTypeId = refuseReasonSubTypeId;
	}

	public String getRefuseContent() {
		return refuseContent;
	}

	public void setRefuseContent(String refuseContent) {
		this.refuseContent = refuseContent;
	}

	public Integer getRegisterMicroBizCenter() {
		return registerMicroBizCenter;
	}

	public void setRegisterMicroBizCenter(Integer registerMicroBizCenter) {
		this.registerMicroBizCenter = registerMicroBizCenter;
	}

	public String getRegisterRecommendBank() {
		return registerRecommendBank;
	}

	public void setRegisterRecommendBank(String registerRecommendBank) {
		this.registerRecommendBank = registerRecommendBank;
	}

	public Date getRegisterApplyDate() {
		return registerApplyDate;
	}

	public void setRegisterApplyDate(Date registerApplyDate) {
		this.registerApplyDate = registerApplyDate;
	}

	public Date getRegisterLoanDate() {
		return registerLoanDate;
	}

	public void setRegisterLoanDate(Date registerLoanDate) {
		this.registerLoanDate = registerLoanDate;
	}

	public String getRegisterInfoSourceIds() {
		return registerInfoSourceIds == null ? "" : registerInfoSourceIds;
	}

	public void setRegisterInfoSourceIds(String registerInfoSourceIds) {
		this.registerInfoSourceIds = registerInfoSourceIds == null ? ""
				: registerInfoSourceIds;
	}

	public String getRegisterDirectMarket() {
		return registerDirectMarket;
	}

	public void setRegisterDirectMarket(String registerDirectMarket) {
		this.registerDirectMarket = registerDirectMarket;
	}

	public String getRegisterExistCusIntroduct() {
		return registerExistCusIntroduct;
	}

	public void setRegisterExistCusIntroduct(String registerExistCusIntroduct) {
		this.registerExistCusIntroduct = registerExistCusIntroduct;
	}

	public String getRegisterInfoSourceOther() {
		return registerInfoSourceOther;
	}

	public void setRegisterInfoSourceOther(String registerInfoSourceOther) {
		this.registerInfoSourceOther = registerInfoSourceOther;
	}

	public String getAppLoanTypeId() {
		return appLoanTypeId == null ? "0" : appLoanTypeId;
	}

	public void setAppLoanTypeId(String appLoanTypeId) {
		this.appLoanTypeId = appLoanTypeId == null ? "0" : appLoanTypeId;
	}

	public Double getAppMoney() {
		return appMoney;
	}

	public void setAppMoney(Double appMoney) {
		this.appMoney = appMoney;
	}

	public String getAppLimitYear() {
		return appLimitYear;
	}

	public void setAppLimitYear(String appLimitYear) {
		this.appLimitYear = appLimitYear;
	}

	public String getAppRepaymentMonth() {
		return appRepaymentMonth;
	}

	public void setAppRepaymentMonth(String appRepaymentMonth) {
		this.appRepaymentMonth = appRepaymentMonth;
	}

	public String getAppLoanPurpose() {
		return appLoanPurpose;
	}

	public void setAppLoanPurpose(String appLoanPurpose) {
		this.appLoanPurpose = appLoanPurpose;
	}

	public String getAppGuarantorWayId() {
		return appGuarantorWayId == null ? "" : appGuarantorWayId;
	}

	public void setAppGuarantorWayId(String appGuarantorWayId) {
		this.appGuarantorWayId = appGuarantorWayId == null ? ""
				: appGuarantorWayId;
	}

	public String getAppMortgageId() {
		return appMortgageId;
	}

	public void setAppMortgageId(String appMortgageId) {
		this.appMortgageId = appMortgageId;
	}

	public String getAppPledgeId() {
		return appPledgeId;
	}

	public void setAppPledgeId(String appPledgeId) {
		this.appPledgeId = appPledgeId;
	}

	public String getAppMaxInstallmentMoney() {
		return appMaxInstallmentMoney;
	}

	public void setAppMaxInstallmentMoney(String appMaxInstallmentMoney) {
		this.appMaxInstallmentMoney = appMaxInstallmentMoney;
	}

	public String getAppTotalProjectCost() {
		return appTotalProjectCost;
	}

	public void setAppTotalProjectCost(String appTotalProjectCost) {
		this.appTotalProjectCost = appTotalProjectCost;
	}

	public String getAppUserDetail() {
		return appUserDetail;
	}

	public void setAppUserDetail(String appUserDetail) {
		this.appUserDetail = appUserDetail;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusBirthday() {
		return cusBirthday;
	}

	public void setCusBirthday(String cusBirthday) {
		this.cusBirthday = cusBirthday;
	}

	public String getCusSex() {
		return cusSex;
	}

	public void setCusSex(String cusSex) {
		this.cusSex = cusSex;
	}

	public String getCusIdtypeId() {
		return cusIdtypeId == null ? "-1" : cusIdtypeId;
	}

	public void setCusIdtypeId(String cusIdtypeId) {
		this.cusIdtypeId = cusIdtypeId == null ? "-1" : cusIdtypeId;
	}

	public String getCusIdcard() {
		return cusIdcard;
	}

	public void setCusIdcard(String cusIdcard) {
		this.cusIdcard = cusIdcard;
	}

	public String getCusMobilePhone() {
		return cusMobilePhone;
	}

	public void setCusMobilePhone(String cusMobilePhone) {
		this.cusMobilePhone = cusMobilePhone;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public String getCusEducationId() {
		return cusEducationId;
	}

	public void setCusEducationId(String cusEducationId) {
		this.cusEducationId = cusEducationId;
	}

	public String getCusLivingStatusId() {
		return cusLivingStatusId;
	}

	public void setCusLivingStatusId(String cusLivingStatusId) {
		this.cusLivingStatusId = cusLivingStatusId;
	}

	public String getCusAddress() {
		return cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	public String getCusMarriage() {
		return cusMarriage;
	}

	public void setCusMarriage(String cusMarriage) {
		this.cusMarriage = cusMarriage;
	}

	public String getCusRegisteredResidence() {
		return cusRegisteredResidence;
	}

	public void setCusRegisteredResidence(String cusRegisteredResidence) {
		this.cusRegisteredResidence = cusRegisteredResidence;
	}

	public String getCusLivingTypeId() {
		return cusLivingTypeId;
	}

	public void setCusLivingTypeId(String cusLivingTypeId) {
		this.cusLivingTypeId = cusLivingTypeId;
	}

	public String getCusLivingStartDate() {
		return cusLivingStartDate;
	}

	public void setCusLivingStartDate(String cusLivingStartDate) {
		this.cusLivingStartDate = cusLivingStartDate;
	}

	public String getCusHousePattern() {
		return cusHousePattern;
	}

	public void setCusHousePattern(String cusHousePattern) {
		this.cusHousePattern = cusHousePattern;
	}

	public String getCusHouseArea() {
		return cusHouseArea;
	}

	public void setCusHouseArea(String cusHouseArea) {
		this.cusHouseArea = cusHouseArea;
	}

	public String getCusHouseDecorateId() {
		return cusHouseDecorateId;
	}

	public void setCusHouseDecorateId(String cusHouseDecorateId) {
		this.cusHouseDecorateId = cusHouseDecorateId;
	}

	public String getCusEconomicState() {
		return cusEconomicState;
	}

	public void setCusEconomicState(String cusEconomicState) {
		this.cusEconomicState = cusEconomicState;
	}

	public String getCusNote() {
		return cusNote;
	}

	public void setCusNote(String cusNote) {
		this.cusNote = cusNote;
	}

	public String getCusNetAge() {
		return cusNetAge;
	}

	public void setCusNetAge(String cusNetAge) {
		this.cusNetAge = cusNetAge;
	}

	public String getCusCommonOperaterName() {
		return cusCommonOperaterName;
	}

	public void setCusCommonOperaterName(String cusCommonOperaterName) {
		this.cusCommonOperaterName = cusCommonOperaterName;
	}

	public String getCusCommonOperaterIdcard() {
		return cusCommonOperaterIdcard;
	}

	public void setCusCommonOperaterIdcard(String cusCommonOperaterIdcard) {
		this.cusCommonOperaterIdcard = cusCommonOperaterIdcard;
	}

	public String getCusCommonOperaterMobilePhone() {
		return cusCommonOperaterMobilePhone;
	}

	public void setCusCommonOperaterMobilePhone(
			String cusCommonOperaterMobilePhone) {
		this.cusCommonOperaterMobilePhone = cusCommonOperaterMobilePhone;
	}

	public String getCusFirstImpress() {
		return cusFirstImpress;
	}

	public void setCusFirstImpress(String cusFirstImpress) {
		this.cusFirstImpress = cusFirstImpress;
	}

	public String getCusMateName() {
		return cusMateName;
	}

	public void setCusMateName(String cusMateName) {
		this.cusMateName = cusMateName;
	}

	public String getCusMateIdcard() {
		return cusMateIdcard;
	}

	public void setCusMateIdcard(String cusMateIdcard) {
		this.cusMateIdcard = cusMateIdcard;
	}

	public String getCusCompanyName() {
		return cusCompanyName;
	}

	public void setCusCompanyName(String cusCompanyName) {
		this.cusCompanyName = cusCompanyName;
	}

	public String getCusMateMobilePhone() {
		return cusMateMobilePhone;
	}

	public void setCusMateMobilePhone(String cusMateMobilePhone) {
		this.cusMateMobilePhone = cusMateMobilePhone;
	}

	public String getCusMatePhone() {
		return cusMatePhone;
	}

	public void setCusMatePhone(String cusMatePhone) {
		this.cusMatePhone = cusMatePhone;
	}

	public String getCusMateCompanyPhone() {
		return cusMateCompanyPhone;
	}

	public void setCusMateCompanyPhone(String cusMateCompanyPhone) {
		this.cusMateCompanyPhone = cusMateCompanyPhone;
	}

	public String getCusCompanyNature() {
		return cusCompanyNature;
	}

	public void setCusCompanyNature(String cusCompanyNature) {
		this.cusCompanyNature = cusCompanyNature;
	}

	public String getCusMateJob() {
		return cusMateJob;
	}

	public void setCusMateJob(String cusMateJob) {
		this.cusMateJob = cusMateJob;
	}

	public String getCusMateWorkYear() {
		return cusMateWorkYear;
	}

	public void setCusMateWorkYear(String cusMateWorkYear) {
		this.cusMateWorkYear = cusMateWorkYear;
	}

	public String getCusMateAddress() {
		return cusMateAddress;
	}

	public void setCusMateAddress(String cusMateAddress) {
		this.cusMateAddress = cusMateAddress;
	}

	public String getCusMateMonthlyIncomingId() {
		return cusMateMonthlyIncomingId;
	}

	public void setCusMateMonthlyIncomingId(String cusMateMonthlyIncomingId) {
		this.cusMateMonthlyIncomingId = cusMateMonthlyIncomingId;
	}

	public String getCusChdInfo() {
		return cusChdInfo;
	}

	public void setCusChdInfo(String cusChdInfo) {
		this.cusChdInfo = cusChdInfo;
	}



	public String getCusParentInfo() {
		return cusParentInfo;
	}

	public void setCusParentInfo(String cusParentInfo) {
		this.cusParentInfo = cusParentInfo;
	}

	public String getCusLocalYear() {
		return cusLocalYear;
	}

	public void setCusLocalYear(String cusLocalYear) {
		this.cusLocalYear = cusLocalYear;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyNature() {
		return companyNature;
	}

	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}

	public String getCompanyJob() {
		return companyJob;
	}

	public void setCompanyJob(String companyJob) {
		this.companyJob = companyJob;
	}

	public String getCompanyWorkYear() {
		return companyWorkYear;
	}

	public void setCompanyWorkYear(String companyWorkYear) {
		this.companyWorkYear = companyWorkYear;
	}

	public String getCompanyMonthlyIncoming() {
		return companyMonthlyIncoming;
	}

	public void setCompanyMonthlyIncoming(String companyMonthlyIncoming) {
		this.companyMonthlyIncoming = companyMonthlyIncoming;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getBizCompany() {
		return bizCompany;
	}

	public void setBizCompany(String bizCompany) {
		this.bizCompany = bizCompany;
	}

	public String getBizScope() {
		return bizScope;
	}

	public void setBizScope(String bizScope) {
		this.bizScope = bizScope;
	}

	public String getBizOrgId() {
		return bizOrgId;
	}

	public void setBizOrgId(String bizOrgId) {
		this.bizOrgId = bizOrgId;
	}

	public String getBizOrgCode() {
		return bizOrgCode;
	}

	public void setBizOrgCode(String bizOrgCode) {
		this.bizOrgCode = bizOrgCode;
	}

	public String getBizLicenseNum() {
		return bizLicenseNum;
	}

	public void setBizLicenseNum(String bizLicenseNum) {
		this.bizLicenseNum = bizLicenseNum;
	}

	public String getBizSocialCreditNum() {
		return bizSocialCreditNum;
	}

	public void setBizSocialCreditNum(String bizSocialCreditNum) {
		this.bizSocialCreditNum = bizSocialCreditNum;
	}

	public String getBizActualOperator() {
		return bizActualOperator;
	}

	public void setBizActualOperator(String bizActualOperator) {
		this.bizActualOperator = bizActualOperator;
	}

	public String getBizAddress() {
		return bizAddress;
	}

	public void setBizAddress(String bizAddress) {
		this.bizAddress = bizAddress;
	}

	public String getBizShareholderProportion() {
		return bizShareholderProportion;
	}

	public void setBizShareholderProportion(String bizShareholderProportion) {
		this.bizShareholderProportion = bizShareholderProportion;
	}

	public String getBizPlaceId() {
		return bizPlaceId;
	}

	public void setBizPlaceId(String bizPlaceId) {
		this.bizPlaceId = bizPlaceId;
	}

	public String getBizArea() {
		return bizArea;
	}

	public void setBizArea(String bizArea) {
		this.bizArea = bizArea;
	}

	public String getBizLawFormId() {
		return bizLawFormId;
	}

	public void setBizLawFormId(String bizLawFormId) {
		this.bizLawFormId = bizLawFormId;
	}

	public String getBizLeagl() {
		return bizLeagl;
	}

	public void setBizLeagl(String bizLeagl) {
		this.bizLeagl = bizLeagl;
	}

	public String getBizLeaglMobilePhone() {
		return bizLeaglMobilePhone;
	}

	public void setBizLeaglMobilePhone(String bizLeaglMobilePhone) {
		this.bizLeaglMobilePhone = bizLeaglMobilePhone;
	}



	public String getBizEmployeeNum() {
		return bizEmployeeNum;
	}

	public void setBizEmployeeNum(String bizEmployeeNum) {
		this.bizEmployeeNum = bizEmployeeNum;
	}


	public String getBizOpenDate() {
		return bizOpenDate;
	}

	public void setBizOpenDate(String bizOpenDate) {
		this.bizOpenDate = bizOpenDate;
	}

	public String getBizIsBelongMybank() {
		return bizIsBelongMybank;
	}

	public void setBizIsBelongMybank(String bizIsBelongMybank) {
		this.bizIsBelongMybank = bizIsBelongMybank;
	}


	public String getIsLocal() {
		return isLocal;
	}

	public void setIsLocal(String isLocal) {
		this.isLocal = isLocal;
	}

	public String getBizNote() {
		return bizNote;
	}

	public void setBizNote(String bizNote) {
		this.bizNote = bizNote;
	}

	public String getCafMonthlySales() {
		return cafMonthlySales;
	}

	public void setCafMonthlySales(String cafMonthlySales) {
		this.cafMonthlySales = cafMonthlySales;
	}

	public String getCafBusySeason() {
		return cafBusySeason;
	}

	public void setCafBusySeason(String cafBusySeason) {
		this.cafBusySeason = cafBusySeason;
	}

	public String getCafLowSeason() {
		return cafLowSeason;
	}

	public void setCafLowSeason(String cafLowSeason) {
		this.cafLowSeason = cafLowSeason;
	}

	public String getCafReceivableMoney() {
		return cafReceivableMoney;
	}

	public void setCafReceivableMoney(String cafReceivableMoney) {
		this.cafReceivableMoney = cafReceivableMoney;
	}

	public String getCafGrossMargin() {
		return cafGrossMargin;
	}

	public void setCafGrossMargin(String cafGrossMargin) {
		this.cafGrossMargin = cafGrossMargin;
	}

	public String getCafInventory() {
		return cafInventory;
	}

	public void setCafInventory(String cafInventory) {
		this.cafInventory = cafInventory;
	}

	public String getCafDebt() {
		return cafDebt;
	}

	public void setCafDebt(String cafDebt) {
		this.cafDebt = cafDebt;
	}

	public String getCafTotalMoney() {
		return cafTotalMoney;
	}

	public void setCafTotalMoney(String cafTotalMoney) {
		this.cafTotalMoney = cafTotalMoney;
	}

	public String getCafOther() {
		return cafOther;
	}

	public void setCafOther(String cafOther) {
		this.cafOther = cafOther;
	}

	public String getCafTotalCreditCard() {
		return cafTotalCreditCard;
	}

	public void setCafTotalCreditCard(String cafTotalCreditCard) {
		this.cafTotalCreditCard = cafTotalCreditCard;
	}

	public String getCafTotalCreditMoney() {
		return cafTotalCreditMoney;
	}

	public void setCafTotalCreditMoney(String cafTotalCreditMoney) {
		this.cafTotalCreditMoney = cafTotalCreditMoney;
	}

	public String getCafTotalOverdraftMoney() {
		return cafTotalOverdraftMoney;
	}

	public void setCafTotalOverdraftMoney(String cafTotalOverdraftMoney) {
		this.cafTotalOverdraftMoney = cafTotalOverdraftMoney;
	}

	public String getCafOverdueSituation() {
		return cafOverdueSituation;
	}

	public void setCafOverdueSituation(String cafOverdueSituation) {
		this.cafOverdueSituation = cafOverdueSituation;
	}

	public String getCafLoanCard() {
		return cafLoanCard;
	}

	public void setCafLoanCard(String cafLoanCard) {
		this.cafLoanCard = cafLoanCard;
	}

	public String getCafBorrowerCreidtDesc() {
		return cafBorrowerCreidtDesc;
	}

	public void setCafBorrowerCreidtDesc(String cafBorrowerCreidtDesc) {
		this.cafBorrowerCreidtDesc = cafBorrowerCreidtDesc;
	}

	public String getCafGuarantorCreidtDesc() {
		return cafGuarantorCreidtDesc;
	}

	public void setCafGuarantorCreidtDesc(String cafGuarantorCreidtDesc) {
		this.cafGuarantorCreidtDesc = cafGuarantorCreidtDesc;
	}

	public String getAdviceMoney() {
		return adviceMoney == null ? "" : adviceMoney;
	}

	public void setAdviceMoney(String adviceMoney) {
		this.adviceMoney = adviceMoney == null ? "" : adviceMoney;
	}

	public String getAdviceLimitYear() {
		return adviceLimitYear == null ? "" : adviceLimitYear;
	}

	public void setAdviceLimitYear(String adviceLimitYear) {
		this.adviceLimitYear = adviceLimitYear == null ? "" : adviceLimitYear;
	}

	public String getAdviceRate() {
		return adviceRate == null ? "" : adviceRate;
	}

	public void setAdviceRate(String adviceRate) {
		this.adviceRate = adviceRate == null ? "" : adviceRate;
	}

	public String getAdviceRepayDate() {
		return adviceRepayDate == null ? "" : adviceRepayDate;
	}

	public void setAdviceRepayDate(String adviceRepayDate) {
		this.adviceRepayDate = adviceRepayDate == null ? "" : adviceRepayDate;
	}

	public String getAdviceLendWayId() {
		return adviceLendWayId == null ? "" : adviceLendWayId;
	}

	public void setAdviceLendWayId(String adviceLendWayId) {
		this.adviceLendWayId = adviceLendWayId == null ? "" : adviceLendWayId;
	}

	public String getAdviceRepayWayId() {
		return adviceRepayWayId == null ? "" : adviceRepayWayId;
	}

	public void setAdviceRepayWayId(String adviceRepayWayId) {
		this.adviceRepayWayId = adviceRepayWayId == null ? ""
				: adviceRepayWayId;
	}

	public String getAdviceOther() {
		return adviceOther == null ? "" : adviceOther;
	}

	public void setAdviceOther(String adviceOther) {
		this.adviceOther = adviceOther == null ? "" : adviceOther;
	}

	public String getAdviceNote() {
		return adviceNote == null ? "" : adviceNote;
	}

	public void setAdviceNote(String adviceNote) {
		this.adviceNote = adviceNote == null ? "" : adviceNote;
	}

	public String getResultMoney() {
		return resultMoney;
	}

	public void setResultMoney(String resultMoney) {
		this.resultMoney = resultMoney;
	}

	public String getResultLimitYear() {
		return resultLimitYear;
	}

	public void setResultLimitYear(String resultLimitYear) {
		this.resultLimitYear = resultLimitYear;
	}

	public String getResultRate() {
		return resultRate;
	}

	public void setResultRate(String resultRate) {
		this.resultRate = resultRate;
	}

	public String getResultRepayDate() {
		return resultRepayDate;
	}

	public void setResultRepayDate(String resultRepayDate) {
		this.resultRepayDate = resultRepayDate;
	}

	public String getResultInstalRepayMoney() {
		return resultInstalRepayMoney;
	}

	public void setResultInstalRepayMoney(String resultInstalRepayMoney) {
		this.resultInstalRepayMoney = resultInstalRepayMoney;
	}

	public String getResultRiskWarm() {
		return resultRiskWarm;
	}

	public void setResultRiskWarm(String resultRiskWarm) {
		this.resultRiskWarm = resultRiskWarm;
	}

	public String getResultSurveyExisProblems() {
		return resultSurveyExisProblems;
	}

	public void setResultSurveyExisProblems(String resultSurveyExisProblems) {
		this.resultSurveyExisProblems = resultSurveyExisProblems;
	}

	public String getResultNote() {
		return resultNote;
	}

	public void setResultNote(String resultNote) {
		this.resultNote = resultNote;
	}

	public String getLendContractNum() {
		return lendContractNum;
	}

	public void setLendContractNum(String lendContractNum) {
		this.lendContractNum = lendContractNum;
	}

	public String getLendConfrimContractNum() {
		return lendConfrimContractNum;
	}

	public void setLendConfrimContractNum(String lendConfrimContractNum) {
		this.lendConfrimContractNum = lendConfrimContractNum;
	}

	public String getLendNote() {
		return lendNote;
	}

	public void setLendNote(String lendNote) {
		this.lendNote = lendNote;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getAppMortgageOther() {
		return appMortgageOther;
	}

	public void setAppMortgageOther(String appMortgageOther) {
		this.appMortgageOther = appMortgageOther;
	}

	public String getAppPledgeOther() {
		return appPledgeOther;
	}

	public void setAppPledgeOther(String appPledgeOther) {
		this.appPledgeOther = appPledgeOther;
	}

	public String getRegisterSaveStatus() {
		return registerSaveStatus;
	}

	public void setRegisterSaveStatus(String registerSaveStatus) {
		this.registerSaveStatus = registerSaveStatus;
	}

	public String getApplicationInfo() {
		return applicationInfo;
	}

	public void setApplicationInfo(String applicationInfo) {
		this.applicationInfo = applicationInfo;
	}

	public String getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(String personalInfo) {
		this.personalInfo = personalInfo;
	}

	public String getFamilyInfo() {
		return familyInfo;
	}

	public void setFamilyInfo(String familyInfo) {
		this.familyInfo = familyInfo;
	}

	public String getBusinessInfo() {
		return businessInfo;
	}

	public void setBusinessInfo(String businessInfo) {
		this.businessInfo = businessInfo;
	}

	public String getSignInfo() {
		return signInfo;
	}

	public void setSignInfo(String signInfo) {
		this.signInfo = signInfo;
	}

	public int getIsNoGood() {
		return isNoGood;
	}

	public void setIsNoGood(int isNoGood) {
		this.isNoGood = isNoGood;
	}

	public String getSignInfoPath() {
		return signInfoPath;
	}

	public void setSignInfoPath(String signInfoPath) {
		this.signInfoPath = signInfoPath;
	}

	public String getAssignRemark() {
		return assignRemark;
	}

	public void setAssignRemark(String assignRemark) {
		this.assignRemark = assignRemark;
	}

	public Integer getTogetherSurveyUserId() {
		return togetherSurveyUserId == null ? 0 : togetherSurveyUserId;
	}

	public void setTogetherSurveyUserId(Integer togetherSurveyUserId) {
		this.togetherSurveyUserId = togetherSurveyUserId == null ? 0
				: togetherSurveyUserId;
	}

	public Date getTogetherSurveyDate() {
		return togetherSurveyDate;
	}

	public void setTogetherSurveyDate(Date togetherSurveyDate) {
		this.togetherSurveyDate = togetherSurveyDate;
	}

	public String getCusFamilyNum() {
		return cusFamilyNum;
	}

	public void setCusFamilyNum(String cusFamilyNum) {
		this.cusFamilyNum = cusFamilyNum;
	}

	public String getRegisterSourceFFKKHJS() {
		return registerSourceFFKKHJS;
	}

	public void setRegisterSourceFFKKHJS(String registerSourceFFKKHJS) {
		this.registerSourceFFKKHJS = registerSourceFFKKHJS;
	}

	public String getRegisterSourceWX() {
		return registerSourceWX;
	}

	public void setRegisterSourceWX(String registerSourceWX) {
		this.registerSourceWX = registerSourceWX;
	}

	public String getRegisterSourceMM() {
		return registerSourceMM;
	}

	public void setRegisterSourceMM(String registerSourceMM) {
		this.registerSourceMM = registerSourceMM;
	}

	public String getRegisterSourceCCK() {
		return registerSourceCCK;
	}

	public void setRegisterSourceCCK(String registerSourceCCK) {
		this.registerSourceCCK = registerSourceCCK;
	}

	public String getRegisterSourceHNYGJS() {
		return registerSourceHNYGJS;
	}

	public void setRegisterSourceHNYGJS(String registerSourceHNYGJS) {
		this.registerSourceHNYGJS = registerSourceHNYGJS;
	}

	public String getRegisterSourceDHYX() {
		return registerSourceDHYX;
	}

	public void setRegisterSourceDHYX(String registerSourceDHYX) {
		this.registerSourceDHYX = registerSourceDHYX;
	}

	public String getRegisterSourceHZSHTJ() {
		return registerSourceHZSHTJ;
	}

	public void setRegisterSourceHZSHTJ(String registerSourceHZSHTJ) {
		this.registerSourceHZSHTJ = registerSourceHZSHTJ;
	}

	public String getRegisterSourceZXSQ() {
		return registerSourceZXSQ;
	}

	public void setRegisterSourceZXSQ(String registerSourceZXSQ) {
		this.registerSourceZXSQ = registerSourceZXSQ;
	}

}
