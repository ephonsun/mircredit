package com.banger.mobile.webservice.domain.loan;

import java.io.Serializable;

import com.banger.mobile.domain.model.base.loan.BaseLnLoanInfo;
import com.banger.mobile.domain.model.pad.PadLoanInfo;

/**
 * 申请信息
 * @author summerxll
 *
 */
public class ApplicationInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6557188183533578155L;
	private String appLoanTypeId; // 贷款类型
	private String appLoanTypeName;
	private Double appMoney; // 申请金额
	private String appLimitYear; // 申请期限
	private String appRepaymentMonth; // 月还款能力
	private String appLoanPurpose; // 贷款用途
	private String appGuarantorWayId; // 担保方式
	private String appGuarantorWayName;
	private String isMortgageSelected;
	private String appMortgageId; // 抵押
	private String appMortgageName;
	private String isPledgeSelected;
	private String appPledgeId;// 质押
	private String appPledgeName;
	private String appMaxInstallmentMoney; // 最大分期还款额
	private String appTotalProjectCost; // 总项目成本
	private String appUserDetail; // 使用明细
	
	public String getAppLoanTypeId() {
		return appLoanTypeId==null?"":appLoanTypeId;
	}
	public void setAppLoanTypeId(String appLoanTypeId) {
		this.appLoanTypeId = appLoanTypeId==null?"":appLoanTypeId;
	}
	
	public String getAppLoanTypeName() {
		return appLoanTypeName==null?"":appLoanTypeName;
	}
	public void setAppLoanTypeName(String appLoanTypeName) {
		this.appLoanTypeName = appLoanTypeName==null?"":appLoanTypeName;
	}
	
	public Double getAppMoney() {
		return appMoney==null ? 0:appMoney;
	}
	public void setAppMoney(Double appMoney) {
		this.appMoney = appMoney==null ? 0:appMoney;
	}

	public String getAppLimitYear() {
		return appLimitYear==null ? "":appLimitYear;
	}
	public void setAppLimitYear(String appLimitYear) {
		this.appLimitYear = appLimitYear==null ? "":appLimitYear;;
	}

	public String getAppRepaymentMonth() {
		return appRepaymentMonth==null ? "":appRepaymentMonth;
	}
	public void setAppRepaymentMonth(String appRepaymentMonth) {
		this.appRepaymentMonth = appRepaymentMonth==null ? "":appRepaymentMonth;
	}

	public String getAppLoanPurpose() {
		return appLoanPurpose==null ? "":appLoanPurpose;
	}
	public void setAppLoanPurpose(String appLoanPurpose) {
		this.appLoanPurpose = appLoanPurpose==null ? "":appLoanPurpose;;
	}

	public String getAppGuarantorWayId() {
		return appGuarantorWayId==null ? "":appGuarantorWayId;
	}
	public void setAppGuarantorWayId(String appGuarantorWayId) {
		this.appGuarantorWayId = appGuarantorWayId==null ? "":appGuarantorWayId;
	}

	public String getAppGuarantorWayName() {
		return appGuarantorWayName==null ? "":appGuarantorWayName;
	}
	public void setAppGuarantorWayName(String appGuarantorWayName) {
		this.appGuarantorWayName = appGuarantorWayName==null ? "":appGuarantorWayName;
	}

	public String getIsMortgageSelected() {
		return isMortgageSelected==null ? "":isMortgageSelected;
	}
	public void setIsMortgageSelected(String isMortgageSelected) {
		this.isMortgageSelected = isMortgageSelected==null ? "":isMortgageSelected;
	}
	
	public String getAppMortgageId() {
		return appMortgageId==null ? "":appMortgageId;
	}
	public void setAppMortgageId(String appMortgageId) {
		this.appMortgageId = appMortgageId==null ? "":appMortgageId;
	}

	public String getAppMortgageName() {
		return appMortgageName==null ? "": appMortgageName;
	}
	public void setAppMortgageName(String appMortgageName) {
		this.appMortgageName = appMortgageName==null ? "": appMortgageName;
	}

	public String getIsPledgeSelected() {
		return isPledgeSelected==null ? "":isPledgeSelected;
	}
	public void setIsPledgeSelected(String isPledgeSelected) {
		this.isPledgeSelected = isPledgeSelected==null ? "":isPledgeSelected;;
	}
	
	public String getAppPledgeId() {
		return appPledgeId==null ? "":appPledgeId;
	}
	public void setAppPledgeId(String appPledgeId) {
		this.appPledgeId = appPledgeId==null ? "":appPledgeId;;
	}

	public String getAppPledgeName() {
		return appPledgeName==null ? "": appPledgeName;
	}
	public void setAppPledgeName(String appPledgeName) {
		this.appPledgeName = appPledgeName==null ? "": appPledgeName;
	}

	public String getAppMaxInstallmentMoney() {
		return appMaxInstallmentMoney==null ? "":appMaxInstallmentMoney;
	}
	public void setAppMaxInstallmentMoney(String appMaxInstallmentMoney) {
		this.appMaxInstallmentMoney = appMaxInstallmentMoney==null ? "":appMaxInstallmentMoney;
	}

	public String getAppTotalProjectCost() {
		return appTotalProjectCost==null ? "":appTotalProjectCost;
	}
	public void setAppTotalProjectCost(String appTotalProjectCost) {
		this.appTotalProjectCost =  appTotalProjectCost==null ? "":appTotalProjectCost;
	}

	public String getAppUserDetail() {
		return appUserDetail==null ? "":appUserDetail;
	}
	public void setAppUserDetail(String appUserDetail) {
		this.appUserDetail = appUserDetail==null ? "":appUserDetail;
	}

	public ApplicationInfo(){
		
	}
	public ApplicationInfo(PadLoanInfo info){		
		this.setAppLoanTypeId(info.getAppLoanTypeId());
		this.setAppLoanTypeName(info.getAppLoanTypeName());
		this.setAppMoney(info.getAppMoney());
		this.setAppLimitYear(info.getAppLimitYear());
		this.setAppRepaymentMonth(info.getAppRepaymentMonth());
		this.setAppLoanPurpose(info.getAppLoanPurpose());
		this.setAppGuarantorWayId(info.getAppGuarantorWayId());
		this.setAppGuarantorWayName(info.getAppGuarantorWayName());
		this.setIsMortgageSelected(info.getAppGuarantorWayId()=="01"?"Y":"N");
		this.setAppMortgageId(info.getAppMortgageId());
		this.setAppMortgageName(info.getAppMortgageName());
		this.setIsPledgeSelected(info.getAppGuarantorWayId()=="02"?"Y":"N");
		this.setAppPledgeId(info.getAppPledgeId());
		this.setAppPledgeName(info.getAppPledgeName());
		this.setAppMaxInstallmentMoney(info.getAppMaxInstallmentMoney());
		this.setAppTotalProjectCost(info.getAppTotalProjectCost());
		this.setAppUserDetail(info.getAppUserDetail());	
	}
	
	public void setlnLoanInfo(BaseLnLoanInfo lnLoanInfo){
    	lnLoanInfo.setAppLoanTypeId(this.getAppLoanTypeId());
    	lnLoanInfo.setAppMoney(this.getAppMoney());
    	lnLoanInfo.setAppLimitYear(this.getAppLimitYear());
    	lnLoanInfo.setAppRepaymentMonth(this.getAppRepaymentMonth());
    	lnLoanInfo.setAppLoanPurpose(this.getAppLoanPurpose());
    	lnLoanInfo.setAppGuarantorWayId(this.getAppGuarantorWayId());
    	lnLoanInfo.setAppMortgageId(this.getAppMortgageId());
    	lnLoanInfo.setAppPledgeId(this.getAppPledgeId())	;
    	lnLoanInfo.setAppMaxInstallmentMoney(this.getAppMaxInstallmentMoney());
    	lnLoanInfo.setAppTotalProjectCost(this.getAppTotalProjectCost());
    	lnLoanInfo.setAppUserDetail(this.getAppUserDetail());
    	lnLoanInfo.setApplicationInfo("Y");
    }
	
}
