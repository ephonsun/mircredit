package com.banger.mobile.domain.model.contract;

import java.io.Serializable;
import java.util.Date;

/**
 * 集合
 */
public class RepaymentInfo implements Serializable {
    private static final long serialVersionUID = 1269170432131108007L;
    private String  planDate;//还款计划.还款日期
    private String  planAmount;//还款计划.金额
    private String  repaymentDate;//还款情况登记.日期
    private String  repaymentPrincipal;//还款情况登记.还本金额
    private String  repaymentRemaining;//还款情况登记.结欠本金
    private String  userName;//还款情况登记.经办员



    public RepaymentInfo() {

    }



    public String getPlanAmount() {
        return planAmount;
    }

    public void setPlanAmount(String planAmount) {
        this.planAmount = planAmount;
    }

    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public String getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public String getRepaymentPrincipal() {
        return repaymentPrincipal;
    }

    public void setRepaymentPrincipal(String repaymentPrincipal) {
        this.repaymentPrincipal = repaymentPrincipal;
    }

    public String getRepaymentRemaining() {
        return repaymentRemaining;
    }

    public void setRepaymentRemaining(String repaymentRemaining) {
        this.repaymentRemaining = repaymentRemaining;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
