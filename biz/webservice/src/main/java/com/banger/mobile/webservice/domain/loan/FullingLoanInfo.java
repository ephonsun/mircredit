/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :huangk
 * Create Date:2013-3-22
 */
package com.banger.mobile.webservice.domain.loan;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class FullingLoanInfo {

    private Integer loanId;
    private String cusName;
    private String cusPhoneNum;
    private BigDecimal loanBalance;
    private BigDecimal accountBalance;
    private BigDecimal nextAmount;
    private Date planDate;
    private Integer repaymentPlanId;
    private Boolean isEnough;
    private BigDecimal lessAmount;

    public Boolean getIsEnough() {

        if(getLessAmount().compareTo(new BigDecimal(0))==1){
            setIsEnough(false);//不购
        }else{
            setIsEnough(true);//够
        }
        return isEnough;
    }

    public void setIsEnough(Boolean isEnough) {

        this.isEnough = isEnough;
    }

    public BigDecimal getLessAmount() {

        if(null==getNextAmount()){
            setNextAmount(new BigDecimal(0));
        }
        if(null==getAccountBalance()){
            setAccountBalance(new BigDecimal(0));
        }
        if(getNextAmount().compareTo(getAccountBalance())==1){
            setLessAmount(getNextAmount().subtract(getAccountBalance()));
        }else{
            setLessAmount(new BigDecimal(0));
        }

        return lessAmount;
    }

    public void setLessAmount(BigDecimal lessAmount) {
        this.lessAmount = lessAmount;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusPhoneNum() {
        return cusPhoneNum;
    }

    public void setCusPhoneNum(String cusPhoneNum) {
        this.cusPhoneNum = cusPhoneNum;
    }

    public BigDecimal getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(BigDecimal loanBalance) {
        this.loanBalance = loanBalance;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public BigDecimal getNextAmount() {
        return nextAmount;
    }

    public void setNextAmount(BigDecimal nextAmount) {
        this.nextAmount = nextAmount;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public Integer getRepaymentPlanId() {
        return repaymentPlanId;
    }

    public void setRepaymentPlanId(Integer repaymentPlanId) {
        this.repaymentPlanId = repaymentPlanId;
    }
}
