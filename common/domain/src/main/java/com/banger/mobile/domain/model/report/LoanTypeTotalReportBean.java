package com.banger.mobile.domain.model.report;

import java.math.BigDecimal;

/**
 * Created by ygb on 2017/9/21.
 */
public class LoanTypeTotalReportBean {
    private Integer loanTotal;  //贷款笔数
    private BigDecimal totalAmount; //贷款总金额
    private BigDecimal lonAmount; //余额

    public Integer getLoanTotal() {
        return loanTotal;
    }

    public void setLoanTotal(Integer loanTotal) {

        this.loanTotal = loanTotal;

    }

    public BigDecimal getTotalAmount() {

        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount==null?new BigDecimal(0):totalAmount;
    }

    public BigDecimal getLonAmount() {
        return lonAmount;
    }

    public void setLonAmount(BigDecimal lonAmount) {
        this.lonAmount = lonAmount==null?new BigDecimal(0):lonAmount;
    }
}
