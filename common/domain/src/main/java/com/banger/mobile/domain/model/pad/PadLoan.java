/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-22
 */
package com.banger.mobile.domain.model.pad;

import java.io.Serializable;
import java.util.Date;
import com.banger.mobile.domain.model.base.loan.BaseLnLoan;

/**
 * @author yuanme 
 * 2016-1-6 修改  xiall
 * @version $Id: PadLoan.java,v 0.1 2012-11-22 上午10:03:41 Administrator Exp $
 */
public class PadLoan extends BaseLnLoan implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -6466757816992461194L;
	
	private Integer loanId; 					// 主键ID		主表
	private Integer loanStatusId; 				// 贷款状态ID 	主表
	private String loanStatusName; 				// 贷款状态名称 	主表
	private Integer customerId; 				// 客户ID   	申请信息表
	private Integer belongUserId;				// 客户归属用户ID 客户表
	private String customerName;				// 客户名称	客户表
    private String customerTitle;				// 客户称谓	客户表    
	private String idCard;						// 证件号码	客户表
    private String phone;						// 联系电话	客户表
    private Date createDate;      				// 创建时间	主表
	private Integer applyUserId; 				// 申请用户ID  主表	
	private String applyUserName; 				// 申请提交人员    人员表
	private Date applySubmitDate; 				// 申请提交时间     主表
	private Integer assignUserId; 				// 分配用户ID	主表
	private String assignUserName; 				// 分配提交人员    人员表
	private Date assignSubmitDate; 				// 分配提交时间	主表
	private Integer surveyUserId; 				// 调查用户ID	主表
	private String surveyUserName; 				// 调查提交人员    人员表
	private Date surveySubmitDate; 				// 调查提交时间	主表
	private Integer approveProcessId; 			// 提交流程ID	主表
	private Integer approveDirectorUserId; 		// 主管审批	主表	
	private String approveDirectorUserName; 	// 主管审批	人员表
	private Date approveDirectorPassDate; 		// 主管审批通过时间	主表
	private Integer approveCenterUserId1; 		// 审批中心审批人员1	主表
	private String approveCenterUserName1; 		// 审批中心审批人员1	人员表
	private Integer approveCenterUserId2; 		// 审批中心审批人员2	主表
	private String approveCenterUserName2; 		// 审批中心审批人员2	人员表
	private Date approveCenterPassDate; 		// 审批中心通过时间	主表
	private Integer approveBackerUserId; 		// 待审会后台审批人员	主表
	private String approveBackerUserName; 		// 待审会后台审批人员	人员表
	private Date approveBackerPassDate; 		// 待审会后台审批通过时间	主表	
	private Integer lendUserId; 				// 放款人员ID	主表
	private String lendUserName; 				// 放款人员	人员表
	private Date lendSaveDate; 					// 放款保存时间	主表
	private Date lendContractCheckDate; 		// 放款合同号校验时间	主表
	private Integer lendContractCheckStatusId; 	// 放款合同校验状态	主表
	private Integer eventId;
	private Integer isReject; 		//是否驳回
	private String resultNote;

	//*********************************以上贷前***********************************
	
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
	public String getLoanStatusName() {
		return loanStatusName;
	}
	public void setLoanStatusName(String loanStatusName) {
		this.loanStatusName = loanStatusName;
	}		
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}		
	public Integer getBelongUserId() {
		return belongUserId;
	}
	public void setBelongUserId(Integer belongUserId) {
		this.belongUserId = belongUserId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerTitle() {
		return customerTitle;
	}
	public void setCustomerTitle(String customerTitle) {
		this.customerTitle = customerTitle;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getApplyUserId() {
		return applyUserId;
	}
	public void setApplyUserId(Integer applyUserId) {
		this.applyUserId = applyUserId;
	}
	public String getApplyUserName() {
		return applyUserName;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
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
	public String getAssignUserName() {
		return assignUserName;
	}
	public void setAssignUserName(String assignUserName) {
		this.assignUserName = assignUserName;
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
	public String getSurveyUserName() {
		return surveyUserName;
	}
	public void setSurveyUserName(String surveyUserName) {
		this.surveyUserName = surveyUserName;
	}
	public Date getSurveySubmitDate() {
		return surveySubmitDate;
	}
	public void setSurveySubmitDate(Date surveySubmitDate) {
		this.surveySubmitDate = surveySubmitDate;
	}
	public Integer getApproveProcessId() {
		return approveProcessId==null?0:approveProcessId;
	}
	public void setApproveProcessId(Integer approveProcessId) {
		this.approveProcessId = approveProcessId==null?0:approveProcessId;
	}
	public Integer getApproveDirectorUserId() {
		return approveDirectorUserId;
	}
	public void setApproveDirectorUserId(Integer approveDirectorUserId) {
		this.approveDirectorUserId = approveDirectorUserId;
	}
	public String getApproveDirectorUserName() {
		return approveDirectorUserName;
	}
	public void setApproveDirectorUserName(String approveDirectorUserName) {
		this.approveDirectorUserName = approveDirectorUserName;
	}
	public Date getApproveDirectorPassDate() {
		return approveDirectorPassDate;
	}
	public void setApproveDirectorPassDate(Date approveDirectorPassDate) {
		this.approveDirectorPassDate = approveDirectorPassDate;
	}
	public Integer getApproveCenterUserId1() {
		return approveCenterUserId1;
	}
	public void setApproveCenterUserId1(Integer approveCenterUserId1) {
		this.approveCenterUserId1 = approveCenterUserId1;
	}
	public String getApproveCenterUserName1() {
		return approveCenterUserName1;
	}
	public void setApproveCenterUserName1(String approveCenterUserName1) {
		this.approveCenterUserName1 = approveCenterUserName1;
	}
	public Integer getApproveCenterUserId2() {
		return approveCenterUserId2;
	}
	public void setApproveCenterUserId2(Integer approveCenterUserId2) {
		this.approveCenterUserId2 = approveCenterUserId2;
	}
	public String getApproveCenterUserName2() {
		return approveCenterUserName2;
	}
	public void setApproveCenterUserName2(String approveCenterUserName2) {
		this.approveCenterUserName2 = approveCenterUserName2;
	}
	public Date getApproveCenterPassDate() {
		return approveCenterPassDate;
	}
	public void setApproveCenterPassDate(Date approveCenterPassDate) {
		this.approveCenterPassDate = approveCenterPassDate;
	}
	public Integer getApproveBackerUserId() {
		return approveBackerUserId;
	}
	public void setApproveBackerUserId(Integer approveBackerUserId) {
		this.approveBackerUserId = approveBackerUserId;
	}
	public String getApproveBackerUserName() {
		return approveBackerUserName;
	}
	public void setApproveBackerUserName(String approveBackerUserName) {
		this.approveBackerUserName = approveBackerUserName;
	}
	public Date getApproveBackerPassDate() {
		return approveBackerPassDate;
	}
	public void setApproveBackerPassDate(Date approveBackerPassDate) {
		this.approveBackerPassDate = approveBackerPassDate;
	}
	public Integer getLendUserId() {
		return lendUserId;
	}
	public void setLendUserId(Integer lendUserId) {
		this.lendUserId = lendUserId;
	}
	public String getLendUserName() {
		return lendUserName;
	}
	public void setLendUserName(String lendUserName) {
		this.lendUserName = lendUserName;
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
	public Integer getEventId() {
		return eventId==null?0:eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId==null?0:eventId;
	}
	public Integer getIsReject() {
		return isReject==null ? 0:isReject;
	}
	public void setIsReject(Integer isReject) {
		this.isReject = isReject==null ? 0:isReject;
	}
	public String getResultNote() {
		return resultNote;
	}
	public void setResultNote(String resultNote) {
		this.resultNote = resultNote;
	}
	
	
	
	/*
	private Integer loanId;        //贷款ID
    private Integer loanStatusId;  //贷款状态
    private Integer customerId;    //客户ID
    private String IdCard;		   //客户身份证号码
    private String customerName;
    private String customerTitle;
    private String phone;
    private Integer createUserId;
    private String applyDate;
    private Integer applyUserId;
    private String applyUserName;
    private String submitDate;
    private Integer submitUserId;
    private String submitUserName;
    private String assignDate;
    private String lendDate;
    private String repaymentDate;
    private Integer repaymentStatus;
    private String needRepay;
    private String accountRemaining;
    private Integer isFinishDun;
    private String overduePrincipal;
    private String overdueInterest;
    private String owedPrincipal;
    private String owedInterest;
    private String paidPrincipal;

    private String approvalDate;

    //新增加的属性字段

    private Integer createUser; //创建人Id
    private String createUserName;  //创建人姓名
    private String createDate;      //创建时间
    private Integer isCheckout; //0未落实，1已落实

    private Integer isNoGood; //是否为不良客户

    private Integer isFull;  //是否余额已足
    private String paidInterest;

    //客户归属用户
    private Integer belongUserId;

    private Integer loanInfoId; //申请表主键

    public Integer getLoanInfoId() {
        return loanInfoId;
    }

    public void setLoanInfoId(Integer loanInfoId) {
        this.loanInfoId = loanInfoId;
    }

    public Integer getBelongUserId() {
        return belongUserId;
    }

    public void setBelongUserId(Integer belongUserId) {
        this.belongUserId = belongUserId;
    }

    public Integer getIsFull(){
        return isFull;
    }

    public void setIsFull(Integer isFull){
        this.isFull = isFull;
    }

    public Integer getIsNoGood() {
        return isNoGood;
    }

    public void setIsNoGood(Integer noGood) {
        isNoGood = noGood;
    }

    public String getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Integer getFinishDun() {
        return isFinishDun;
    }

    public void setFinishDun(Integer finishDun) {
        isFinishDun = finishDun;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Integer getIsCheckout() {
        return isCheckout;
    }

    public void setIsCheckout(Integer checkout) {
        isCheckout = checkout;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String idCard) {
        IdCard = idCard;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Integer applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public Integer getSubmitUserId() {
        return submitUserId;
    }

    public void setSubmitUserId(Integer submitUserId) {
        this.submitUserId = submitUserId;
    }

    public String getSubmitUserName() {
        return submitUserName;
    }

    public void setSubmitUserName(String submitUserName) {
        this.submitUserName = submitUserName;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public String getLendDate() {
        return lendDate;
    }

    public void setLendDate(String lendDate) {
        this.lendDate = lendDate;
    }

    public String getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public Integer getRepaymentStatus() {
        return repaymentStatus;
    }

    public void setRepaymentStatus(Integer repaymentStatus) {
        this.repaymentStatus = repaymentStatus;
    }

    public String getNeedRepay() {
        return needRepay;
    }

    public void setNeedRepay(String needRepay) {
        this.needRepay = needRepay;
    }

    public String getAccountRemaining() {
        return accountRemaining;
    }

    public void setAccountRemaining(String accountRemaining) {
        this.accountRemaining = accountRemaining;
    }

    public Integer getIsFinishDun() {
        return isFinishDun;
    }

    public void setIsFinishDun(Integer finishDun) {
        isFinishDun = finishDun;
    }

    public String getOverduePrincipal() {
        return overduePrincipal;
    }

    public void setOverduePrincipal(String overduePrincipal) {
        this.overduePrincipal = overduePrincipal;
    }

    public String getOverdueInterest() {
        return overdueInterest;
    }

    public void setOverdueInterest(String overdueInterest) {
        this.overdueInterest = overdueInterest;
    }

    public String getOwedPrincipal() {
        return owedPrincipal;
    }

    public void setOwedPrincipal(String owedPrincipal) {
        this.owedPrincipal = owedPrincipal;
    }

    public String getOwedInterest() {
        return owedInterest;
    }

    public void setOwedInterest(String owedInterest) {
        this.owedInterest = owedInterest;
    }

    public String getPaidPrincipal() {
        return paidPrincipal;
    }

    public void setPaidPrincipal(String paidPrincipal) {
        this.paidPrincipal = paidPrincipal;
    }

    public String getPaidInterest() {
        return paidInterest;
    }

    public void setPaidInterest(String paidInterest) {
        this.paidInterest = paidInterest;
    }
*/

}
