package com.banger.mobile.webservice.domain.loan;
import com.banger.mobile.domain.model.base.loan.BaseLnLoanInfo;
import com.banger.mobile.domain.model.pad.PadLoanInfo;

import java.io.Serializable;

/**
 * 家庭信息
 * @author summerxll
 *
 */
public class FamilyInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1057882257450596807L;
	private String cusLivingStatusId; // 申请人居住状况
	private String cusLivingStatusName;
	private String cusLivingTypeId; // 申请人居住场所类型
	private String cusLivingTypeName;
	private String cusLivingAddress;//申请人现居住地址
	private String cusAddress; // 申请人家庭住址
	private String cusLivingStartDate; // 申请人居住起始年月
	private String cusHousePattern; // 申请人住房格局
	private String cusHouseArea; // 申请人住房面积
	private String cusHouseDecorateId; // 申请人住房装修情况
	private String cusHouseDecorateName;
	private String cusEconomicState; // 申请人经济网状态
	private String cusNote; // 申请人个人信息备注	
	private String cusMarriage; // 申请人婚姻状况
	private String cusMarriageName;
	private String cusMateName; // 配偶姓名
	private String cusMateIdcard; // 配偶身份证号
	private String cusMateMobilePhone; // 配偶手机号
	private String cusMatePhone; // 配偶固定电话
	private String cusCompanyName; // 配偶单位名称
	private String cusMateCompanyPhone; // 配偶单位电话
	private String cusCompanyNature; // 配偶单位性质
	private String cusMateJob; // 配偶工作岗位
	private String cusMateWorkYear; // 配偶工作年限
	private String cusMateMonthlyIncomingId; // 配偶月收入水平
	private String cusMateMonthlyIncomingName;
	private String cusMateAddress; // 配偶单位地址
	private String cusChdInfo; // 子女情况
	private String cusParentInfo; // 父母情况
	
	private String cusLocalYear; //本地居住年限
	
	public String getCusLivingStatusId() {
		return cusLivingStatusId==null ? "": cusLivingStatusId;
	}
	public void setCusLivingStatusId(String cusLivingStatusId) {
		this.cusLivingStatusId = cusLivingStatusId==null ? "": cusLivingStatusId;
	}
	public String getCusLivingStatusName() {
		return cusLivingStatusName==null ? "":cusLivingStatusName;
	}
	public void setCusLivingStatusName(String cusLivingStatusName) {
		this.cusLivingStatusName = cusLivingStatusName==null ? "":cusLivingStatusName;
	}
	public String getCusLivingTypeId() {
		return cusLivingTypeId==null ?"":cusLivingTypeId;
	}
	public void setCusLivingTypeId(String cusLivingTypeId) {
		this.cusLivingTypeId = cusLivingTypeId==null ?"":cusLivingTypeId;
	}
	public String getCusLivingTypeName() {
		return cusLivingTypeName==null?"":cusLivingTypeName;
	}
	public void setCusLivingTypeName(String cusLivingTypeName) {
		this.cusLivingTypeName = cusLivingTypeName==null?"":cusLivingTypeName;
	}
	public String getCusLivingAddress() {
		return cusLivingAddress;
	}
	public void setCusLivingAddress(String cusLivingAddress) {
		this.cusLivingAddress = cusLivingAddress;
	}
	public String getCusAddress() {
		return cusAddress==null ? "":cusAddress;
	}
	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress==null ? "":cusAddress;
	}
	public String getCusLivingStartDate() {
		return cusLivingStartDate==null?"":cusLivingStartDate;
	}
	public void setCusLivingStartDate(String cusLivingStartDate) {
		this.cusLivingStartDate = cusLivingStartDate==null?"":cusLivingStartDate;
	}
	public String getCusHousePattern() {
		return cusHousePattern==null ?"":cusHousePattern;
	}
	public void setCusHousePattern(String cusHousePattern) {
		this.cusHousePattern = cusHousePattern==null ?"":cusHousePattern;
	}
	public String getCusHouseArea() {
		return cusHouseArea==null ?"":cusHouseArea;
	}
	public void setCusHouseArea(String cusHouseArea) {
		this.cusHouseArea = cusHouseArea==null ?"":cusHouseArea;
	}
	public String getCusHouseDecorateId() {
		return cusHouseDecorateId==null ?"":cusHouseDecorateId;
	}
	public void setCusHouseDecorateId(String cusHouseDecorateId) {
		this.cusHouseDecorateId = cusHouseDecorateId==null ?"":cusHouseDecorateId;
	}
	public String getCusHouseDecorateName() {
		return cusHouseDecorateName==null ?"":cusHouseDecorateName;
	}
	public void setCusHouseDecorateName(String cusHouseDecorateName) {
		this.cusHouseDecorateName = cusHouseDecorateName==null ?"":cusHouseDecorateName;
	}
	public String getCusEconomicState() {
		return cusEconomicState==null ? "":cusEconomicState;
	}
	public void setCusEconomicState(String cusEconomicState) {
		this.cusEconomicState = cusEconomicState==null ? "":cusEconomicState;
	}
	public String getCusNote() {
		return cusNote==null ?"":cusNote;
	}
	public void setCusNote(String cusNote) {
		this.cusNote = cusNote==null ?"":cusNote;
	}
	public String getCusMarriage() {
		return cusMarriage==null ?"":cusMarriage;
	}
	public void setCusMarriage(String cusMarriage) {
		this.cusMarriage = cusMarriage==null ?"":cusMarriage;
	}
	public String getCusMarriageName() {
		return cusMarriageName==null ? "":cusMarriageName;
	}
	public void setCusMarriageName(String cusMarriageName) {
		this.cusMarriageName = cusMarriageName==null ? "":cusMarriageName;
	}
	public String getCusMateName() {
		return cusMateName==null ?"":cusMateName;
	}
	public void setCusMateName(String cusMateName) {
		this.cusMateName = cusMateName==null ?"":cusMateName;
	}
	public String getCusMateIdcard() {
		return cusMateIdcard==null ? "":cusMateIdcard;
	}
	public void setCusMateIdcard(String cusMateIdcard) {
		this.cusMateIdcard = cusMateIdcard==null ? "":cusMateIdcard;
	}
	public String getCusMateMobilePhone() {
		return cusMateMobilePhone==null ?"":cusMateMobilePhone;
	}
	public void setCusMateMobilePhone(String cusMateMobilePhone) {
		this.cusMateMobilePhone = cusMateMobilePhone==null ?"":cusMateMobilePhone;
	}
	public String getCusMatePhone() {
		return cusMatePhone== null ?"":cusMatePhone;
	}
	public void setCusMatePhone(String cusMatePhone) {
		this.cusMatePhone = cusMatePhone== null ?"":cusMatePhone;
	}
	public String getCusCompanyName() {
		return cusCompanyName==null ?"":cusCompanyName;
	}
	public void setCusCompanyName(String cusCompanyName) {
		this.cusCompanyName = cusCompanyName==null ?"":cusCompanyName;
	}
	public String getCusMateCompanyPhone() {
		return cusMateCompanyPhone==null ?"":cusMateCompanyPhone;
	}
	public void setCusMateCompanyPhone(String cusMateCompanyPhone) {
		this.cusMateCompanyPhone = cusMateCompanyPhone==null ?"":cusMateCompanyPhone;
	}
	public String getCusCompanyNature() {
		return cusCompanyNature==null ?"":cusCompanyNature;
	}
	public void setCusCompanyNature(String cusCompanyNature) {
		this.cusCompanyNature = cusCompanyNature==null ?"":cusCompanyNature;
	}
	public String getCusMateJob() {
		return cusMateJob==null ?"":cusMateJob;
	}
	public void setCusMateJob(String cusMateJob) {
		this.cusMateJob = cusMateJob==null ?"":cusMateJob;
	}
	public String getCusMateWorkYear() {
		return cusMateWorkYear==null?"":cusMateWorkYear;
	}
	public void setCusMateWorkYear(String cusMateWorkYear) {
		this.cusMateWorkYear = cusMateWorkYear==null?"":cusMateWorkYear;
	}
	public String getCusMateMonthlyIncomingId() {
		return cusMateMonthlyIncomingId==null ?"":cusMateMonthlyIncomingId;
	}
	public void setCusMateMonthlyIncomingId(String cusMateMonthlyIncomingId) {
		this.cusMateMonthlyIncomingId = cusMateMonthlyIncomingId==null ?"":cusMateMonthlyIncomingId;
	}
	public String getCusMateMonthlyIncomingName() {
		return cusMateMonthlyIncomingName==null ?"":cusMateMonthlyIncomingName;
	}
	public void setCusMateMonthlyIncomingName(String cusMateMonthlyIncomingName) {
		this.cusMateMonthlyIncomingName = cusMateMonthlyIncomingName==null ?"":cusMateMonthlyIncomingName;
	}
	public String getCusMateAddress() {
		return cusMateAddress==null?"":cusMateAddress;
	}
	public void setCusMateAddress(String cusMateAddress) {
		this.cusMateAddress = cusMateAddress==null?"":cusMateAddress;
	}
	public String getCusChdInfo() {
		return cusChdInfo==null?"":cusChdInfo;
	}
	public void setCusChdInfo(String cusChdInfo) {
		this.cusChdInfo = cusChdInfo==null?"":cusChdInfo;
	}
	public String getCusParentInfo() {
		return cusParentInfo==null?"":cusParentInfo;
	}
	public void setCusParentInfo(String cusParentInfo) {
		this.cusParentInfo = cusParentInfo==null?"":cusParentInfo;
	}
	
	
	public String getCusLocalYear() {
		return cusLocalYear;
	}
	public void setCusLocalYear(String cusLocalYear) {
		this.cusLocalYear = cusLocalYear;
	}
	
	public FamilyInfo(){
		
	}
	public FamilyInfo(PadLoanInfo info){		
		this.setCusLivingStatusId(info.getCusLivingStatusId());
		this.setCusLivingStatusName(info.getCusLivingStatusName());
		this.setCusLivingTypeId(info.getCusLivingTypeId());
		this.setCusLivingTypeName(info.getCusLivingTypeName());
		this.setCusLivingAddress(info.getCusLivingAddress());
		this.setCusAddress(info.getCusAddress());
		this.setCusLivingStartDate(info.getCusLivingStartDate());
		this.setCusHousePattern(info.getCusHousePattern());
		this.setCusHouseArea(info.getCusHouseArea());
		this.setCusHouseDecorateId(info.getCusHouseDecorateId());
		this.setCusHouseDecorateName(info.getCusHouseDecorateName());
		this.setCusEconomicState(info.getCusEconomicState());
		this.setCusNote(info.getCusNote());
		this.setCusMarriage(info.getCusMarriage());
		this.setCusMarriageName(info.getCusMarriageName());
		this.setCusMateName(info.getCusMateName());
		this.setCusMateIdcard(info.getCusMateIdcard());
		this.setCusMateMobilePhone(info.getCusMateMobilePhone());
		this.setCusMatePhone(info.getCusMatePhone());
		this.setCusCompanyName(info.getCusCompanyName());
		this.setCusMateCompanyPhone(info.getCusMateCompanyPhone());
		this.setCusCompanyNature(info.getCusCompanyNature());
		this.setCusMateJob(info.getCusMateJob());
		this.setCusMateWorkYear(info.getCusMateWorkYear());
		this.setCusMateMonthlyIncomingId(info.getCusMateMonthlyIncomingId());
		this.setCusMateMonthlyIncomingName(info.getCusMateMonthlyIncomingName());
		this.setCusMateAddress(info.getCusMateAddress());
		this.setCusChdInfo(info.getCusChdInfo());
		this.setCusParentInfo(info.getCusParentInfo());		
		this.setCusLocalYear(info.getCusLocalYear());
	}
	
	public void setlnLoanInfo(BaseLnLoanInfo lnLoanInfo){
		lnLoanInfo.setCusLivingStatusId(this.getCusLivingStatusId());
		lnLoanInfo.setCusLivingTypeId(this.getCusLivingTypeId());
		lnLoanInfo.setCusLivingAddress(this.getCusLivingAddress());
		lnLoanInfo.setCusAddress(this.getCusAddress());
		lnLoanInfo.setCusLivingStartDate(this.getCusLivingStartDate());
		lnLoanInfo.setCusHousePattern(this.getCusHousePattern());
		lnLoanInfo.setCusHouseArea(this.getCusHouseArea());
		lnLoanInfo.setCusHouseDecorateId(this.getCusHouseDecorateId());
		lnLoanInfo.setCusEconomicState(this.getCusEconomicState());
		lnLoanInfo.setCusNote(this.getCusNote());
		lnLoanInfo.setCusMarriage(this.getCusMarriage());
		lnLoanInfo.setCusMateName(this.getCusMateName());
		lnLoanInfo.setCusMateIdcard(this.getCusMateIdcard());
		lnLoanInfo.setCusMateMobilePhone(this.getCusMateMobilePhone());
		lnLoanInfo.setCusMatePhone(this.getCusMatePhone());
		lnLoanInfo.setCusCompanyName(this.getCusCompanyName());
		lnLoanInfo.setCusMateCompanyPhone(this.getCusMateCompanyPhone());
		lnLoanInfo.setCusCompanyNature(this.getCusCompanyNature());
		lnLoanInfo.setCusMateJob(this.getCusMateJob());
		lnLoanInfo.setCusMateWorkYear(this.getCusMateWorkYear());
		lnLoanInfo.setCusMateMonthlyIncomingId(this.getCusMateMonthlyIncomingId());
		lnLoanInfo.setCusMateAddress(this.getCusMateAddress());
		lnLoanInfo.setCusChdInfo(this.getCusChdInfo());
		lnLoanInfo.setCusParentInfo(this.getCusParentInfo());
		lnLoanInfo.setCusLocalYear(this.getCusLocalYear());
		lnLoanInfo.setFamilyInfo("Y");
	}
}
