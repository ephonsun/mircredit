package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: Zhangfp Date: 13-2-5 Time: 下午1:33 To change
 * this template use File | Settings | File Templates.
 * <p/>
 * 贷款主表
 */
public class BaseLnLoan extends BaseObject implements Serializable {

	private static final long serialVersionUID = 3415534696457498924L;
	
	private Integer loanId; // 主键ID
	private Integer loanStatusId; // 贷款状态ID
	private Integer repaymentStatus; // 还款状态
	private Date lastClientQueryDate; // 客户端上次查询时间
	private Integer loanRejectUserId; // 贷款拒绝人员
	private Date loanRejectDate; // 贷款拒绝时间
	private Integer verifyUserId; // 审计人员
	private Integer isVerify; // 是否已审计
	private Integer eventId;
	private Integer applyUserId; // 申请用户ID
	private Date applySubmitDate; // 申请提交时间
	private Integer assignUserId; // 分配用户ID
	private Date assignSubmitDate; // 分配提交时间
	private Integer surveyUserId; // 调查用户ID
	private Date surveySubmitDate; // 调查提交时间
	private Integer approveProcessId; // 提交流程ID
	private Integer isReject; 		//是否驳回
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
	private Integer lendUserId; // 放款人员ID
	private Date lendSaveDate; // 放款保存时间
	private Date lendContractCheckDate; // 放款合同号校验时间
	private Integer lendContractCheckStatusId; // 放款合同校验状态
	private Date createDate; // 创建时间
	private Date updateDate; // 更新时间
	private Integer createUser; // 创建用户
	private Integer updateUser; // 更新用户
	private String approveBakMsg; //审批备份
	private String loanAccount; //贷款账号

	public String getLoanAccount() {
		return loanAccount;
	}

	public void setLoanAccount(String loanAccount) {
		this.loanAccount = loanAccount;
	}

	public String getApproveBakMsg() {
		return approveBakMsg;
	}

	public void setApproveBakMsg(String approveBakMsg) {
		this.approveBakMsg = approveBakMsg;
	}

	public BaseLnLoan() {
	}

	public BaseLnLoan(Integer loanId) {
		this.loanId = loanId;
	}

	public Integer getLoanId() {
		return loanId;
	}

	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}

	public Integer getLoanStatusId() {
		return loanStatusId;
	}

	public void setLoanStatusId(Integer loanStatusId) {
		this.loanStatusId = loanStatusId;
	}

	public Integer getRepaymentStatus() {
		return repaymentStatus;
	}

	public void setRepaymentStatus(Integer repaymentStatus) {
		this.repaymentStatus = repaymentStatus;
	}

	public Date getLastClientQueryDate() {
		return lastClientQueryDate;
	}

	public void setLastClientQueryDate(Date lastClientQueryDate) {
		this.lastClientQueryDate = lastClientQueryDate;
	}

	public Integer getLoanRejectUserId() {
		return loanRejectUserId;
	}

	public void setLoanRejectUserId(Integer loanRejectUserId) {
		this.loanRejectUserId = loanRejectUserId;
	}

	public Date getLoanRejectDate() {
		return loanRejectDate;
	}

	public void setLoanRejectDate(Date loanRejectDate) {
		this.loanRejectDate = loanRejectDate;
	}

	public Integer getVerifyUserId() {
		return verifyUserId;
	}

	public void setVerifyUserId(Integer verifyUserId) {
		this.verifyUserId = verifyUserId;
	}

	public Integer getIsVerify() {
		return isVerify;
	}

	public void setIsVerify(Integer isVerify) {
		this.isVerify = isVerify;
	}

	public Integer getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(Integer applyUserId) {
		this.applyUserId = applyUserId;
	}

	public Date getApplySubmitDate() {
		return applySubmitDate;
	}

	public void setApplySubmitDate(Date applySubmitDate) {
		this.applySubmitDate = applySubmitDate;
	}

	public Integer getAssignUserId() {
		return assignUserId;
	}

	public void setAssignUserId(Integer assignUserId) {
		this.assignUserId = assignUserId;
	}

	public Date getAssignSubmitDate() {
		return assignSubmitDate;
	}

	public void setAssignSubmitDate(Date assignSubmitDate) {
		this.assignSubmitDate = assignSubmitDate;
	}

	public Integer getSurveyUserId() {
		return surveyUserId;
	}

	public void setSurveyUserId(Integer surveyUserId) {
		this.surveyUserId = surveyUserId;
	}

	public Date getSurveySubmitDate() {
		return surveySubmitDate;
	}

	public void setSurveySubmitDate(Date surveySubmitDate) {
		this.surveySubmitDate = surveySubmitDate;
	}

	public Integer getApproveProcessId() {
		return approveProcessId;
	}

	public void setApproveProcessId(Integer approveProcessId) {
		this.approveProcessId = approveProcessId;
	}

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

	public Integer getLendUserId() {
		return lendUserId;
	}

	public void setLendUserId(Integer lendUserId) {
		this.lendUserId = lendUserId;
	}

	public Date getLendSaveDate() {
		return lendSaveDate;
	}

	public void setLendSaveDate(Date lendSaveDate) {
		this.lendSaveDate = lendSaveDate;
	}

	public Date getLendContractCheckDate() {
		return lendContractCheckDate;
	}

	public void setLendContractCheckDate(Date lendContractCheckDate) {
		this.lendContractCheckDate = lendContractCheckDate;
	}

	public Integer getLendContractCheckStatusId() {
		return lendContractCheckStatusId;
	}

	public void setLendContractCheckStatusId(Integer lendContractCheckStatusId) {
		this.lendContractCheckStatusId = lendContractCheckStatusId;
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

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getIsReject() {
		return isReject;
	}

	public void setIsReject(Integer isReject) {
		this.isReject = isReject;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	
	
}
