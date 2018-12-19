package com.banger.mobile.domain.model.loan;

import java.util.Date;

public class LnBusinessModel {

	private Integer  businessId;
	private Integer  loanId;
	private String   upperReaches;
	private String   lowerReaches;
	private String   workFlow ;
	private String   change;
	private String   sorftInfo ;
	private String   officeLeaseContract;
	private String   other;
	private String   businessHistory;
	private Date createDate;
	private Date updateDate;
	private String businessOrg;
	private String businessFinance;
	
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	public Integer getLoanId() {
		return loanId;
	}
	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}
	public String getUpperReaches() {
		return upperReaches;
	}
	public void setUpperReaches(String upperReaches) {
		this.upperReaches = upperReaches;
	}
	public String getLowerReaches() {
		return lowerReaches;
	}
	public void setLowerReaches(String lowerReaches) {
		this.lowerReaches = lowerReaches;
	}
	public String getWorkFlow() {
		return workFlow;
	}
	public void setWorkFlow(String workFlow) {
		this.workFlow = workFlow;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public String getSorftInfo() {
		return sorftInfo;
	}
	public void setSorftInfo(String sorftInfo) {
		this.sorftInfo = sorftInfo;
	}
	public String getOfficeLeaseContract() {
		return officeLeaseContract;
	}
	public void setOfficeLeaseContract(String officeLeaseContract) {
		this.officeLeaseContract = officeLeaseContract;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getBusinessHistory() {
		return businessHistory;
	}
	public void setBusinessHistory(String businessHistory) {
		this.businessHistory = businessHistory;
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

	public String getBusinessOrg() {
		return businessOrg;
	}

	public void setBusinessOrg(String businessOrg) {
		this.businessOrg = businessOrg;
	}

	public String getBusinessFinance() {
		return businessFinance;
	}

	public void setBusinessFinance(String businessFinance) {
		this.businessFinance = businessFinance;
	}
}
