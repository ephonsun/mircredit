/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.domain.model.loan;

import java.util.Date;

/**
 * 
 * @author White Wolf
 * @version $Id: LnLoanApproveBakMsg.java, v 0.1 2016-2-23 下午9:29:51 White Wolf Exp $
 */
public class LnLoanApproveBakMsg {
	
	private Integer approveDirectorUserId; // 主管审批
	private Date approveDirectorPassDate; // 主管审批通过时间
	private Date approveDirectorRejectDate; // 主管审批驳回时间
	private Integer approveCenterUserId1; // 审批中心审批人员1
	private Integer approveCenterUserId2; // 审批中心审批人员2
	private Date approveCenterPassDate; // 审批中心通过时间
	private Date approveCenterRejectDate; // 审批中心驳回时间
	private Integer approveBackerUserId; // 待审会后台审批人员
	private Date approveBackerPassDate; // 待审会后台审批通过时间
	private Date approveBackerRejectDate; // 待审会后台审批驳回时间
	public Integer getApproveDirectorUserId() {
		return approveDirectorUserId;
	}
	public void setApproveDirectorUserId(Integer approveDirectorUserId) {
		this.approveDirectorUserId = approveDirectorUserId;
	}
	public Date getApproveDirectorPassDate() {
		return approveDirectorPassDate;
	}
	public void setApproveDirectorPassDate(Date approveDirectorPassDate) {
		this.approveDirectorPassDate = approveDirectorPassDate;
	}
	public Date getApproveDirectorRejectDate() {
		return approveDirectorRejectDate;
	}
	public void setApproveDirectorRejectDate(Date approveDirectorRejectDate) {
		this.approveDirectorRejectDate = approveDirectorRejectDate;
	}
	public Integer getApproveCenterUserId1() {
		return approveCenterUserId1;
	}
	public void setApproveCenterUserId1(Integer approveCenterUserId1) {
		this.approveCenterUserId1 = approveCenterUserId1;
	}
	public Integer getApproveCenterUserId2() {
		return approveCenterUserId2;
	}
	public void setApproveCenterUserId2(Integer approveCenterUserId2) {
		this.approveCenterUserId2 = approveCenterUserId2;
	}
	public Date getApproveCenterPassDate() {
		return approveCenterPassDate;
	}
	public void setApproveCenterPassDate(Date approveCenterPassDate) {
		this.approveCenterPassDate = approveCenterPassDate;
	}
	public Date getApproveCenterRejectDate() {
		return approveCenterRejectDate;
	}
	public void setApproveCenterRejectDate(Date approveCenterRejectDate) {
		this.approveCenterRejectDate = approveCenterRejectDate;
	}
	public Integer getApproveBackerUserId() {
		return approveBackerUserId;
	}
	public void setApproveBackerUserId(Integer approveBackerUserId) {
		this.approveBackerUserId = approveBackerUserId;
	}
	public Date getApproveBackerPassDate() {
		return approveBackerPassDate;
	}
	public void setApproveBackerPassDate(Date approveBackerPassDate) {
		this.approveBackerPassDate = approveBackerPassDate;
	}
	public Date getApproveBackerRejectDate() {
		return approveBackerRejectDate;
	}
	public void setApproveBackerRejectDate(Date approveBackerRejectDate) {
		this.approveBackerRejectDate = approveBackerRejectDate;
	}
	
	
}
