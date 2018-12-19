package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 下午2:06
 * To change this template use File | Settings | File Templates.
 *
 * 异常还款计划
 */
public class BaseLnExceptionRepaymentPlan extends BaseObject implements Serializable {
    private Integer exceptionRepaymentPlanId; //主键id
    private Integer loanId;                 //贷款id
    private Date planDate;                  //还款时间
    private BigDecimal owedPrincipal;           //应还本金
    private BigDecimal owedInterest;            //应还利息
    private BigDecimal paidPrincipal;           //已还本金
    private BigDecimal paidInterest;            //已还利息
    private BigDecimal remaining;           //贷款余额
    private Date createDate;                //创建时间
    private Date updateDate;                //更新时间
    private Integer createUser;             //创建用户
    private Integer updateUser;             //更新用户
    private Integer sortno;                  //期号
    private Integer status;                 //状态

    public BaseLnExceptionRepaymentPlan(){
    }

    public BaseLnExceptionRepaymentPlan(Integer exceptionRepaymentPlanId){
        this.exceptionRepaymentPlanId = exceptionRepaymentPlanId;
    }

    public Integer getExceptionRepaymentPlanId() {
        return exceptionRepaymentPlanId;
    }

    public void setExceptionRepaymentPlanId(Integer exceptionRepaymentPlanId) {
        this.exceptionRepaymentPlanId = exceptionRepaymentPlanId;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
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

    public BigDecimal getRemaining() {
        return remaining;
    }

    public void setRemaining(BigDecimal remaining) {
        this.remaining = remaining;
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

    public Integer getSortno() {
        return sortno;
    }

    public void setSortno(Integer sortno) {
        this.sortno = sortno;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
