package com.banger.mobile.domain.model.loan;

import java.io.Serializable;
import java.util.Date;

public class LnDeviationAnalsysis implements Serializable {


	private Integer id;//主键
	private Integer loanId;
	private Integer loanMaritalStatus ;//婚姻状况
	private Integer loanAge ;//年龄
	private Integer loanWorkStatus ;//工作情况
	private Integer loanBizYear ;//经营年限
	private Integer loanLocalYear ;//居住年限
	private Integer loanFinanceStatus ;//财产状况
	private Integer loanCreditStatus ;//信用记录
	private Integer loanChildStatus;//子女
	private Integer loanSpouseStatus ;//配偶
	private Integer guMaritalStatus ;//婚姻状况
	private Integer guAge ;//年龄
	private Integer guWorkStatus ;//工作情况
	private Integer guBizYear ;//经营年限
	private Integer guFinanceStatus ;//财产状况
	private Integer guCreditStatus ;//信用记录
	private String remark ;//备注
	private Integer createUser ;
	private Date createDate ;
	private Integer updateUser ;
	private Date updateDate ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public Integer getLoanMaritalStatus() {
		return loanMaritalStatus;
	}

	public void setLoanMaritalStatus(Integer loanMaritalStatus) {
		this.loanMaritalStatus = loanMaritalStatus;
	}

	public Integer getLoanAge() {
		return loanAge;
	}

	public void setLoanAge(Integer loanAge) {
		this.loanAge = loanAge;
	}

	public Integer getLoanWorkStatus() {
		return loanWorkStatus;
	}

	public void setLoanWorkStatus(Integer loanWorkStatus) {
		this.loanWorkStatus = loanWorkStatus;
	}

	public Integer getLoanBizYear() {
		return loanBizYear;
	}

	public void setLoanBizYear(Integer loanBizYear) {
		this.loanBizYear = loanBizYear;
	}

	public Integer getLoanLocalYear() {
		return loanLocalYear;
	}

	public void setLoanLocalYear(Integer loanLocalYear) {
		this.loanLocalYear = loanLocalYear;
	}

	public Integer getLoanFinanceStatus() {
		return loanFinanceStatus;
	}

	public void setLoanFinanceStatus(Integer loanFinanceStatus) {
		this.loanFinanceStatus = loanFinanceStatus;
	}

	public Integer getLoanCreditStatus() {
		return loanCreditStatus;
	}

	public void setLoanCreditStatus(Integer loanCreditStatus) {
		this.loanCreditStatus = loanCreditStatus;
	}

	public Integer getLoanChildStatus() {
		return loanChildStatus;
	}

	public void setLoanChildStatus(Integer loanChildStatus) {
		this.loanChildStatus = loanChildStatus;
	}

	public Integer getLoanSpouseStatus() {
		return loanSpouseStatus;
	}

	public void setLoanSpouseStatus(Integer loanSpouseStatus) {
		this.loanSpouseStatus = loanSpouseStatus;
	}

	public Integer getGuMaritalStatus() {
		return guMaritalStatus;
	}

	public void setGuMaritalStatus(Integer guMaritalStatus) {
		this.guMaritalStatus = guMaritalStatus;
	}

	public Integer getGuAge() {
		return guAge;
	}

	public void setGuAge(Integer guAge) {
		this.guAge = guAge;
	}

	public Integer getGuWorkStatus() {
		return guWorkStatus;
	}

	public void setGuWorkStatus(Integer guWorkStatus) {
		this.guWorkStatus = guWorkStatus;
	}

	public Integer getGuBizYear() {
		return guBizYear;
	}

	public void setGuBizYear(Integer guBizYear) {
		this.guBizYear = guBizYear;
	}

	public Integer getGuFinanceStatus() {
		return guFinanceStatus;
	}

	public void setGuFinanceStatus(Integer guFinanceStatus) {
		this.guFinanceStatus = guFinanceStatus;
	}

	public Integer getGuCreditStatus() {
		return guCreditStatus;
	}

	public void setGuCreditStatus(Integer guCreditStatus) {
		this.guCreditStatus = guCreditStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
