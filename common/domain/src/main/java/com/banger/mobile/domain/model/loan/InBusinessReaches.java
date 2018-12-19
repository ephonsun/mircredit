package com.banger.mobile.domain.model.loan;

import java.io.Serializable;
import java.util.Date;

public class InBusinessReaches implements Serializable {

	private Integer id ;
	private Integer loanId;
	private String businessCustomer;
	private Integer businessRate;
	private Integer businessYear;
	private String businessRemark;
	private Integer reachesType;

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

	public String getBusinessCustomer() {
		return businessCustomer;
	}

	public void setBusinessCustomer(String businessCustomer) {
		this.businessCustomer = businessCustomer;
	}

	public Integer getBusinessRate() {
		return businessRate;
	}

	public void setBusinessRate(Integer businessRate) {
		this.businessRate = businessRate;
	}

	public Integer getBusinessYear() {
		return businessYear;
	}

	public void setBusinessYear(Integer businessYear) {
		this.businessYear = businessYear;
	}

	public String getBusinessRemark() {
		return businessRemark;
	}

	public void setBusinessRemark(String businessRemark) {
		this.businessRemark = businessRemark;
	}

	public Integer getReachesType() {
		return reachesType;
	}

	public void setReachesType(Integer reachesType) {
		this.reachesType = reachesType;
	}
}
