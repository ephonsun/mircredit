package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnBalance;

import java.math.BigDecimal;
import java.util.Date;

public class LnBalanceOff extends BaseLnBalance {

    private BigDecimal balanceAmount; //表外资产
    private BigDecimal debtAmount; // 表外负债
    private Date balanceAdviceTime; // 调查日期
    private String financialAnalysis; //财务分析及建议

    public String getFinancialAnalysis() {
        return financialAnalysis;
    }

    public void setFinancialAnalysis(String financialAnalysis) {
        this.financialAnalysis = financialAnalysis;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public BigDecimal getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(BigDecimal debtAmount) {
        this.debtAmount = debtAmount;
    }

    public Date getBalanceAdviceTime() {
        return balanceAdviceTime;
    }

    public void setBalanceAdviceTime(Date balanceAdviceTime) {
        this.balanceAdviceTime = balanceAdviceTime;
    }
}