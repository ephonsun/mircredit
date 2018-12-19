/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-7-23
 */
package com.banger.mobile.domain.model.pad;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yuanme
 * @version ProductBuyCustomer.java,v 0.1 2012-7-23 上午9:27:56
 */
public class ProductBuyCustomer implements Serializable{
    private static final long serialVersionUID = 2233619631092835398L;
    private Integer customerId;
    private String  customerNo;
    private String  customerName;
    private String  bankAccount;
    private Date    saleDate;
    private Double  saleMoney;
    private String  belongDept;
    private String  salesName;
    private String  moneyUnit;      //募集单位
    private Double  raiseUpperLimit;//募集上限
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public String getCustomerNo() {
        return customerNo;
    }
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getBankAccount() {
        return bankAccount;
    }
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
    public Date getSaleDate() {
        return saleDate;
    }
    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }
    public Double getSaleMoney() {
        return saleMoney;
    }
    public void setSaleMoney(Double saleMoney) {
        this.saleMoney = saleMoney;
    }
    public String getBelongDept() {
        return belongDept;
    }
    public void setBelongDept(String belongDept) {
        this.belongDept = belongDept;
    }
    public String getSalesName() {
        return salesName;
    }
    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }
    public String getMoneyUnit() {
        return moneyUnit;
    }
    public void setMoneyUnit(String moneyUnit) {
        this.moneyUnit = moneyUnit;
    }
    public Double getRaiseUpperLimit() {
        return raiseUpperLimit;
    }
    public void setRaiseUpperLimit(Double raiseUpperLimit) {
        this.raiseUpperLimit = raiseUpperLimit;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bankAccount == null) ? 0 : bankAccount.hashCode());
        result = prime * result + ((belongDept == null) ? 0 : belongDept.hashCode());
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((customerNo == null) ? 0 : customerNo.hashCode());
        result = prime * result + ((moneyUnit == null) ? 0 : moneyUnit.hashCode());
        result = prime * result + ((raiseUpperLimit == null) ? 0 : raiseUpperLimit.hashCode());
        result = prime * result + ((saleDate == null) ? 0 : saleDate.hashCode());
        result = prime * result + ((saleMoney == null) ? 0 : saleMoney.hashCode());
        result = prime * result + ((salesName == null) ? 0 : salesName.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ProductBuyCustomer other = (ProductBuyCustomer) obj;
        if (bankAccount == null) {
            if (other.bankAccount != null)
                return false;
        } else if (!bankAccount.equals(other.bankAccount))
            return false;
        if (belongDept == null) {
            if (other.belongDept != null)
                return false;
        } else if (!belongDept.equals(other.belongDept))
            return false;
        if (customerId == null) {
            if (other.customerId != null)
                return false;
        } else if (!customerId.equals(other.customerId))
            return false;
        if (customerName == null) {
            if (other.customerName != null)
                return false;
        } else if (!customerName.equals(other.customerName))
            return false;
        if (customerNo == null) {
            if (other.customerNo != null)
                return false;
        } else if (!customerNo.equals(other.customerNo))
            return false;
        if (moneyUnit == null) {
            if (other.moneyUnit != null)
                return false;
        } else if (!moneyUnit.equals(other.moneyUnit))
            return false;
        if (raiseUpperLimit == null) {
            if (other.raiseUpperLimit != null)
                return false;
        } else if (!raiseUpperLimit.equals(other.raiseUpperLimit))
            return false;
        if (saleDate == null) {
            if (other.saleDate != null)
                return false;
        } else if (!saleDate.equals(other.saleDate))
            return false;
        if (saleMoney == null) {
            if (other.saleMoney != null)
                return false;
        } else if (!saleMoney.equals(other.saleMoney))
            return false;
        if (salesName == null) {
            if (other.salesName != null)
                return false;
        } else if (!salesName.equals(other.salesName))
            return false;
        return true;
    }
    public ProductBuyCustomer(Integer customerId, String customerNo, String customerName,
                              String bankAccount, Date saleDate, Double saleMoney,
                              String belongDept, String salesName, String moneyUnit,
                              Double raiseUpperLimit) {
        super();
        this.customerId = customerId;
        this.customerNo = customerNo;
        this.customerName = customerName;
        this.bankAccount = bankAccount;
        this.saleDate = saleDate;
        this.saleMoney = saleMoney;
        this.belongDept = belongDept;
        this.salesName = salesName;
        this.moneyUnit = moneyUnit;
        this.raiseUpperLimit = raiseUpperLimit;
    }
    public ProductBuyCustomer() {
        super();
    }
    
    
}
