package com.banger.mobile.domain.model.loan;

import java.util.Date;

public class LnLoanHistory {

    private Integer  historyId;
	private Integer  loanId;
	private String  workHistory;
	private String   specialExplanation;
	private String   otherConditions;
	private String   familyInfo;
	private String   guaranteeInfo;
	private String   explanationAmount;
	private String   flowPrinciple;
	private Date createDate;
	private Date updateDate;
	
	public Integer getHistoryId() {
		return historyId;
	}
	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}
	public Integer getLoanId() {
		return loanId;
	}
	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}
	
	public String getWorkHistory() {
		return workHistory;
	}
	public void setWorkHistory(String workHistory) {
		this.workHistory = workHistory;
	}
	public String getSpecialExplanation() {
		return specialExplanation;
	}
	public void setSpecialExplanation(String specialExplanation) {
		this.specialExplanation = specialExplanation;
	}
	public String getOtherConditions() {
		return otherConditions;
	}
	public void setOtherConditions(String otherConditions) {
		this.otherConditions = otherConditions;
	}
	public String getFamilyInfo() {
		return familyInfo;
	}
	public void setFamilyInfo(String familyInfo) {
		this.familyInfo = familyInfo;
	}
	public String getGuaranteeInfo() {
		return guaranteeInfo;
	}
	public void setGuaranteeInfo(String guaranteeInfo) {
		this.guaranteeInfo = guaranteeInfo;
	}
	public String getExplanationAmount() {
		return explanationAmount;
	}
	public void setExplanationAmount(String explanationAmount) {
		this.explanationAmount = explanationAmount;
	}
	public String getFlowPrinciple() {
		return flowPrinciple;
	}
	public void setFlowPrinciple(String flowPrinciple) {
		this.flowPrinciple = flowPrinciple;
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
	
	
	
	
}
