package com.banger.mobile.domain.model.base.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 下午1:59
 * To change this template use File | Settings | File Templates.
 *
 * 还款计划
 */
public class BaseLnRepaymentPlan extends BaseObject implements Serializable {
    private Integer repaymentPlanId; //主键ID
    private Integer loanId;          //贷款ID
    private Date planDate;           //还款时间
    private BigDecimal principal;    //应还本金
    private BigDecimal interest;     //应还利息
    private BigDecimal remaining;    //贷款余额
    private BigDecimal accountRemaining;    //账户余额
    private Date createDate;         //创建时间
    private Date updateDate;         //更新时间
    private Integer createUser;      //创建用户
    private Integer updateUser;      //更新用户
    private Integer sortno;          //期号
    private BigDecimal paidPrincipal;    //已还本金
    private BigDecimal paidInterest;     //已还利息
    private String paidTag; //还款状态
    
    
    public String getPaidTag() {
		return paidTag;
	}

	public void setPaidTag(String paidTag) {
		this.paidTag = paidTag;
	}

	public BigDecimal getAccountRemaining() {
		return accountRemaining;
	}

	public void setAccountRemaining(BigDecimal accountRemaining) {
		this.accountRemaining = accountRemaining;
	}

	public BaseLnRepaymentPlan(){
    }

    public BaseLnRepaymentPlan(Integer repaymentPlanId){
        this.repaymentPlanId = repaymentPlanId;
    }

    public BigDecimal getRemaining() {
        return remaining;
    }

    public void setRemaining(BigDecimal remaining) {
        this.remaining = remaining;
    }

    public Integer getRepaymentPlanId() {
        return repaymentPlanId;
    }

    public void setRepaymentPlanId(Integer repaymentPlanId) {
        this.repaymentPlanId = repaymentPlanId;
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

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
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
    
}
