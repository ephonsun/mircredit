package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnBalance;

import java.math.BigDecimal;

public class LnBalanceAccount extends BaseLnBalance {

    private String itemName; //供应商
    private BigDecimal amount; // AMOUNT
    private String settlingType; //SETTLING_TYPE
    private String accountAge; //账龄
    private String bizRenew; //业务关系存续
    private String payMethod; //付款方式

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSettlingType() {
        return settlingType;
    }

    public void setSettlingType(String settlingType) {
        this.settlingType = settlingType;
    }

    public String getAccountAge() {
        return accountAge;
    }

    public void setAccountAge(String accountAge) {
        this.accountAge = accountAge;
    }

    public String getBizRenew() {
        return bizRenew;
    }

    public void setBizRenew(String bizRenew) {
        this.bizRenew = bizRenew;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
}