package com.banger.mobile.domain.model.pad;

import java.io.Serializable;

import com.banger.mobile.domain.model.base.loan.BaseLnLoanInfo;
import com.banger.mobile.domain.model.loan.LnLoanInfo;

public class PadLoanInfo extends LnLoanInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2853764480198325130L;
	
	private String appLoanTypeName;     				//贷款类型名称
    private String cusIdtypeName;						//证件类型名称
    private String appGuarantorWayName; 				//担保方式
    private String appMortgageName;						//抵押
    private String appPledgeName;						//质押
    private String bizOrgName;							//组织形式
    private String bizPlaceName;						//经营场所
    private String bizLawFormName;						//法律形式
    private String cusLivingStatusName;					//申请人居住状况
    private String cusLivingTypeName;					//申请人居住场所类型
    private String cusHouseDecorateName;				//申请人住房装修情况
    private String cusMarriageName;						//申请人婚姻状况
    private String cusMateMonthlyIncomingName;			//配偶月收入水平
    private String cusEducationName;					//申请人教育程度
    private String companyMonthlyIncomingValue;			//月收入水平
    private String registerMicroBizCenterName;			//微贷事业中心
    private String registerRecommendBankName;			//推荐支行
    
	public String getAppLoanTypeName() {
		return appLoanTypeName;
	}
	public void setAppLoanTypeName(String appLoanTypeName) {
		this.appLoanTypeName = appLoanTypeName;
	}
	public String getCusIdtypeName() {
		return cusIdtypeName;
	}
	public void setCusIdtypeName(String cusIdtypeName) {
		this.cusIdtypeName = cusIdtypeName;
	}
	public String getAppGuarantorWayName() {
		return appGuarantorWayName;
	}
	public void setAppGuarantorWayName(String appGuarantorWayName) {
		this.appGuarantorWayName = appGuarantorWayName;
	}
	public String getAppMortgageName() {
		return appMortgageName;
	}
	public void setAppMortgageName(String appMortgageName) {
		this.appMortgageName = appMortgageName;
	}
	public String getAppPledgeName() {
		return appPledgeName;
	}
	public void setAppPledgeName(String appPledgeName) {
		this.appPledgeName = appPledgeName;
	}
	public String getBizOrgName() {
		return bizOrgName;
	}
	public void setBizOrgName(String bizOrgName) {
		this.bizOrgName = bizOrgName;
	}
	public String getBizPlaceName() {
		return bizPlaceName;
	}
	public void setBizPlaceName(String bizPlaceName) {
		this.bizPlaceName = bizPlaceName;
	}
	public String getBizLawFormName() {
		return bizLawFormName;
	}
	public void setBizLawFormName(String bizLawFormName) {
		this.bizLawFormName = bizLawFormName;
	}
	public String getCusLivingStatusName() {
		return cusLivingStatusName;
	}
	public void setCusLivingStatusName(String cusLivingStatusName) {
		this.cusLivingStatusName = cusLivingStatusName;
	}
	public String getCusLivingTypeName() {
		return cusLivingTypeName;
	}
	public void setCusLivingTypeName(String cusLivingTypeName) {
		this.cusLivingTypeName = cusLivingTypeName;
	}
	public String getCusHouseDecorateName() {
		return cusHouseDecorateName;
	}
	public void setCusHouseDecorateName(String cusHouseDecorateName) {
		this.cusHouseDecorateName = cusHouseDecorateName;
	}
	public String getCusMarriageName() {
		return cusMarriageName;
	}
	public void setCusMarriageName(String cusMarriageName) {
		this.cusMarriageName = cusMarriageName;
	}
	public String getCusMateMonthlyIncomingName() {
		return cusMateMonthlyIncomingName;
	}
	public void setCusMateMonthlyIncomingName(String cusMateMonthlyIncomingName) {
		this.cusMateMonthlyIncomingName = cusMateMonthlyIncomingName;
	}
	public String getCusEducationName() {
		return cusEducationName;
	}
	public void setCusEducationName(String cusEducationName) {
		this.cusEducationName = cusEducationName;
	}
	public String getCompanyMonthlyIncomingValue() {
		return companyMonthlyIncomingValue;
	}
	public void setCompanyMonthlyIncomingValue(String companyMonthlyIncomingValue) {
		this.companyMonthlyIncomingValue = companyMonthlyIncomingValue;
	}
	public String getRegisterMicroBizCenterName() {
		return registerMicroBizCenterName;
	}
	public void setRegisterMicroBizCenterName(String registerMicroBizCenterName) {
		this.registerMicroBizCenterName = registerMicroBizCenterName;
	}
	
	public String getRegisterRecommendBankName() {
		return registerRecommendBankName;
	}
	public void setRegisterRecommendBankName(String registerRecommendBankName) {
		this.registerRecommendBankName = registerRecommendBankName;
	}
	
}
