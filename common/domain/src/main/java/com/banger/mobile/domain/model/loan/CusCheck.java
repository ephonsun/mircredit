/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.domain.model.loan;

import java.util.Date;

/**
 * 
 * @author White Wolf
 * @version $Id: CusCheck.java, v 0.1 2016-1-27 上午9:45:40 White Wolf Exp $
 */
public class CusCheck {
	
	private String userName;  //姓名
	
	private String idCard;	  //身份证号
	
	private Date applyDate;   //申请时间
	
	private String applyBank; //申请银行
	
	private String director;  //负责人
	
	private String identity;  //身份
	
	private String loanStatus; //贷款状态
	
	private String rejectContent; //拒绝原因

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getApplyBank() {
		return applyBank;
	}

	public void setApplyBank(String applyBank) {
		this.applyBank = applyBank;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public String getRejectContent() {
		return rejectContent;
	}

	public void setRejectContent(String rejectContent) {
		this.rejectContent = rejectContent;
	}
	
}
