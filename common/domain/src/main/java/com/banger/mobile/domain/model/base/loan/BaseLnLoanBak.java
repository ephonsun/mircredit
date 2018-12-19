package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 下午1:33
 * To change this template use File | Settings | File Templates.
 * <p/>
 * 贷款主表
 */
public class BaseLnLoanBak extends BaseObject implements Serializable {
    private Integer loanId;       //主键id
    private Integer customerId;   //客户id
    private Integer loanTypeId;      //贷款类型
    private Integer loanSubTypeId;   //贷款子类型
    private String applyRemark;      //申请备注
    private Integer applyUserId;     //申请用户id
    private Date applyDate;          //申请时间
    private String assignRemark;     //分配备注
    private Integer assignUserId;    //分配用户id
    private Date assignDate;         //分配时间
    private Integer surveyUserId;    //调查用户id
    private String submitRemark;     //调查备注
    private Integer submitUserId;    //调查递交用户id
    private Date submitDate;         //调查递交时间
    private Integer approveUserId1;  //审批用户1id
    private Integer approveUserId2;  //审批用户2id
    private Date approvePassDate1;   //用户1审批通过时间
    private Date approvePassDate2;   //用户2审批通过时间
    private Date approveRejectDate1; //用户1审批驳回时间
    private Date approveRejectDate2; //用户2审批驳回时间
    private Date approveFailDate1;   //用户1审批失败时间
    private Date approveFailDate2;   //用户2审批失败时间
    private Integer isReject;        //是否被驳回
    private String approveRemark;    //审批说明
    private Date lastSyncDate;       //上次同步时间
    private Integer lastSyncUserId;  //上次同步用户ID
    private Date lendDate;           //放贷时间
    private Integer checkoutUserId;  //落实用户ID
    private Date checkoutDate;       //落实时间
    private Integer isCheckout;      //是否落实
    private String nogoodRemark;     //不良客户备注
    private Integer isNogood;        //是否是不良客户
    private Integer dunUserId;       //催收用户ID
    private Integer cancelUserId;    //申请撤销用户ID
    private Date cancelDate;         //申请撤销时间
    private Integer cancelReasonId;  //申请撤销原因ID
    private String cancelReasonOther;  //申请撤销原因其他
    private Integer confirmCancelUserId; //确认撤销用户ID
    private String confirmCancelRemark;  //确认撤销备注
    private Date confirmCancelDate;      //确认撤销时间
    private Integer verifyUserId;        //审计人员
    private Date verifyDate;             //审计日期
    private String verifyRemark;         //审计备注
    private Integer isVerify;            //是否已审计
    private Integer isVerifyPass;        //审计是否合规
    private Integer eventId;             //事件ID
    private Integer loanStatusId;        //贷款状态ID
    private Date createDate;             //创建时间
    private Date updateDate;             //更新时间
    private Integer createUser;          //创建用户
    private Integer updateUser;          //更新用户
    private BigDecimal loanMoney;        //申请金额
    private BigDecimal accountRemaining; //账户余额
    private BigDecimal loanRemaining;
    private Integer repaymentStatus;
    private BigDecimal currentNeedRepay;
    private Date repaymentDate;
    private Integer sortno;
    private BigDecimal overduePrincipal;  //逾期本金
    private BigDecimal overdueInterest;   //逾期利息
    private BigDecimal owedPrincipal;     //本期应还本金
    private BigDecimal owedInterest;      //本期应还利息
    private BigDecimal paidPrincipal;     //本期已还本金
    private BigDecimal paidInterest;      //本期已还利息
    private Integer isOldCustomer;   //是否是老客户贷款
    private Date lastClientQueryDate;  //客户端上次查询时间
    private String lendRemark;
    private Integer isFull;       //余额已足

    public Date getLastClientQueryDate() {
        return lastClientQueryDate;
    }

    public void setLastClientQueryDate(Date lastClientQueryDate) {
        this.lastClientQueryDate = lastClientQueryDate;
    }

    public BaseLnLoanBak() {
    }

    public BaseLnLoanBak(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getSortno() {
        return sortno;
    }

    public void setSortno(Integer sortno) {
        this.sortno = sortno;
    }

    public Integer getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(Integer loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public Integer getLoanSubTypeId() {
        return loanSubTypeId;
    }

    public void setLoanSubTypeId(Integer loanSubTypeId) {
        this.loanSubTypeId = loanSubTypeId;
    }

    public String getApplyRemark() {
        return applyRemark;
    }

    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark;
    }

    public Integer getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Integer applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getAssignRemark() {
        return assignRemark;
    }

    public void setAssignRemark(String assignRemark) {
        this.assignRemark = assignRemark;
    }

    public Integer getAssignUserId() {
        return assignUserId;
    }

    public void setAssignUserId(Integer assignUserId) {
        this.assignUserId = assignUserId;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Integer getSurveyUserId() {
        return surveyUserId;
    }

    public void setSurveyUserId(Integer surveyUserId) {
        this.surveyUserId = surveyUserId;
    }

    public String getSubmitRemark() {
        return submitRemark;
    }

    public void setSubmitRemark(String submitRemark) {
        this.submitRemark = submitRemark;
    }

    public Integer getSubmitUserId() {
        return submitUserId;
    }

    public void setSubmitUserId(Integer submitUserId) {
        this.submitUserId = submitUserId;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Integer getApproveUserId1() {
        return approveUserId1;
    }

    public void setApproveUserId1(Integer approveUserId1) {
        this.approveUserId1 = approveUserId1;
    }

    public Integer getApproveUserId2() {
        return approveUserId2;
    }

    public void setApproveUserId2(Integer approveUserId2) {
        this.approveUserId2 = approveUserId2;
    }

    public Date getApprovePassDate1() {
        return approvePassDate1;
    }

    public void setApprovePassDate1(Date approvePassDate1) {
        this.approvePassDate1 = approvePassDate1;
    }

    public Date getApprovePassDate2() {
        return approvePassDate2;
    }

    public void setApprovePassDate2(Date approvePassDate2) {
        this.approvePassDate2 = approvePassDate2;
    }

    public Date getApproveRejectDate1() {
        return approveRejectDate1;
    }

    public void setApproveRejectDate1(Date approveRejectDate1) {
        this.approveRejectDate1 = approveRejectDate1;
    }

    public Date getApproveRejectDate2() {
        return approveRejectDate2;
    }

    public void setApproveRejectDate2(Date approveRejectDate2) {
        this.approveRejectDate2 = approveRejectDate2;
    }

    public Date getApproveFailDate1() {
        return approveFailDate1;
    }

    public void setApproveFailDate1(Date approveFailDate1) {
        this.approveFailDate1 = approveFailDate1;
    }

    public Date getApproveFailDate2() {
        return approveFailDate2;
    }

    public void setApproveFailDate2(Date approveFailDate2) {
        this.approveFailDate2 = approveFailDate2;
    }

    public Integer getIsReject() {
        return isReject;
    }

    public void setIsReject(Integer isReject) {
        this.isReject = isReject;
    }

    public String getApproveRemark() {
        return approveRemark;
    }

    public void setApproveRemark(String approveRemark) {
        this.approveRemark = approveRemark;
    }

    public Date getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(Date lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }

    public Integer getLastSyncUserId() {
        return lastSyncUserId;
    }

    public void setLastSyncUserId(Integer lastSyncUserId) {
        this.lastSyncUserId = lastSyncUserId;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public Integer getCheckoutUserId() {
        return checkoutUserId;
    }

    public void setCheckoutUserId(Integer checkoutUserId) {
        this.checkoutUserId = checkoutUserId;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Integer getIsCheckout() {
        return isCheckout;
    }

    public void setIsCheckout(Integer isCheckout) {
        this.isCheckout = isCheckout;
    }

    public String getNogoodRemark() {
        return nogoodRemark;
    }

    public void setNogoodRemark(String nogoodRemark) {
        this.nogoodRemark = nogoodRemark;
    }

    public Integer getIsNogood() {
        return isNogood;
    }

    public void setIsNogood(Integer isNogood) {
        this.isNogood = isNogood;
    }

    public Integer getDunUserId() {
        return dunUserId;
    }

    public void setDunUserId(Integer dunUserId) {
        this.dunUserId = dunUserId;
    }

    public Integer getCancelUserId() {
        return cancelUserId;
    }

    public void setCancelUserId(Integer cancelUserId) {
        this.cancelUserId = cancelUserId;
    }

    public Date getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(Date cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Integer getCancelReasonId() {
        return cancelReasonId;
    }

    public void setCancelReasonId(Integer cancelReasonId) {
        this.cancelReasonId = cancelReasonId;
    }

    public String getCancelReasonOther() {
        return cancelReasonOther;
    }

    public void setCancelReasonOther(String cancelReasonOther) {
        this.cancelReasonOther = cancelReasonOther;
    }

    public Integer getConfirmCancelUserId() {
        return confirmCancelUserId;
    }

    public void setConfirmCancelUserId(Integer confirmCancelUserId) {
        this.confirmCancelUserId = confirmCancelUserId;
    }

    public String getConfirmCancelRemark() {
        return confirmCancelRemark;
    }

    public void setConfirmCancelRemark(String confirmCancelRemark) {
        this.confirmCancelRemark = confirmCancelRemark;
    }

    public Date getConfirmCancelDate() {
        return confirmCancelDate;
    }

    public void setConfirmCancelDate(Date confirmCancelDate) {
        this.confirmCancelDate = confirmCancelDate;
    }

    public Integer getVerifyUserId() {
        return verifyUserId;
    }

    public void setVerifyUserId(Integer verifyUserId) {
        this.verifyUserId = verifyUserId;
    }

    public Date getVerifyDate() {
        return verifyDate;
    }

    public void setVerifyDate(Date verifyDate) {
        this.verifyDate = verifyDate;
    }

    public String getVerifyRemark() {
        return verifyRemark;
    }

    public void setVerifyRemark(String verifyRemark) {
        this.verifyRemark = verifyRemark;
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    public Integer getIsVerifyPass() {
        return isVerifyPass;
    }

    public void setIsVerifyPass(Integer isVerifyPass) {
        this.isVerifyPass = isVerifyPass;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getLoanStatusId() {
        return loanStatusId;
    }

    public void setLoanStatusId(Integer loanStatusId) {
        this.loanStatusId = loanStatusId;
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

    public BigDecimal getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(BigDecimal loanMoney) {
        this.loanMoney = loanMoney;
    }

    public BigDecimal getAccountRemaining() {
        return accountRemaining;
    }

    public void setAccountRemaining(BigDecimal accountRemaining) {
        this.accountRemaining = accountRemaining;
    }

    public BigDecimal getLoanRemaining() {
        return loanRemaining;
    }

    public void setLoanRemaining(BigDecimal loanRemaining) {
        this.loanRemaining = loanRemaining;
    }

    public Integer getRepaymentStatus() {
        return repaymentStatus;
    }

    public void setRepaymentStatus(Integer repaymentStatus) {
        this.repaymentStatus = repaymentStatus;
    }

    public BigDecimal getCurrentNeedRepay() {
        return currentNeedRepay;
    }

    public void setCurrentNeedRepay(BigDecimal currentNeedRepay) {
        this.currentNeedRepay = currentNeedRepay;
    }

    public Date getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public BigDecimal getOverduePrincipal() {
        return overduePrincipal;
    }

    public void setOverduePrincipal(BigDecimal overduePrincipal) {
        this.overduePrincipal = overduePrincipal;
    }

    public BigDecimal getOverdueInterest() {
        return overdueInterest;
    }

    public void setOverdueInterest(BigDecimal overdueInterest) {
        this.overdueInterest = overdueInterest;
    }

    public BigDecimal getOwedPrincipal() {
        return owedPrincipal;
    }

    public void setOwedPrincipal(BigDecimal owedPrincipal) {
        this.owedPrincipal = owedPrincipal;
    }

    public BigDecimal getOwedInterest() {
        return owedInterest;
    }

    public void setOwedInterest(BigDecimal owedInterest) {
        this.owedInterest = owedInterest;
    }

    public BigDecimal getPaidPrincipal() {
        return paidPrincipal;
    }

    public void setPaidPrincipal(BigDecimal paidPrincipal) {
        this.paidPrincipal = paidPrincipal;
    }

    public BigDecimal getPaidInterest() {
        return paidInterest;
    }

    public void setPaidInterest(BigDecimal paidInterest) {
        this.paidInterest = paidInterest;
    }

    public Integer getIsOldCustomer() {
        return isOldCustomer;
    }

    public void setIsOldCustomer(Integer isOldCustomer) {
        this.isOldCustomer = isOldCustomer;
    }

    public String getLendRemark() {
        return lendRemark;
    }

    public void setLendRemark(String lendRemark) {
        this.lendRemark = lendRemark;
    }

    public Integer getIsFull() {
        return isFull;
    }

    public void setIsFull(Integer isFull) {
        this.isFull = isFull;
    }
}
