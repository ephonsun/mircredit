package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnBalance;

import java.math.BigDecimal;
import java.util.Date;

public class LnBalanceDebt extends BaseLnBalance {

    private String debtSource;//来源
    private BigDecimal amount;//金额（元）
    private String limitYear;//期限
    private Date beginDate;//用途
    private BigDecimal loanBalance;//余额（元）
    private String guarantyType;//保证方式
    private String loanPurpose;//用途

    public String getDebtSource() {
        return debtSource;
    }

    public void setDebtSource(String debtSource) {
        this.debtSource = debtSource;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getLimitYear() {
        return limitYear;
    }

    public void setLimitYear(String limitYear) {
        this.limitYear = limitYear;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public BigDecimal getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(BigDecimal loanBalance) {
        this.loanBalance = loanBalance;
    }

    public String getGuarantyType() {
        return guarantyType;
    }

    public void setGuarantyType(String guarantyType) {
        this.guarantyType = guarantyType;
    }
}