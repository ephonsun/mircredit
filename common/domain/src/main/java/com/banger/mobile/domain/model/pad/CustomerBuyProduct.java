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
 * @version CustomerBuyProduct.java,v 0.1 2012-7-23 上午11:18:48
 */
public class CustomerBuyProduct implements Serializable{
    
    private static final long serialVersionUID = 6071639609620541424L;
    private Integer customerId;
    private Integer productId;
    private String  productName;
    private Double  profitRateMin;
    private Double  profitRateMax;
    private Date    buyDate;
    private Double  buyMoney;
    private String  moneyUnit;      //募集单位
    private Double  raiseUpperLimit;//募集上限
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Double getProfitRateMin() {
        return profitRateMin;
    }
    public void setProfitRateMin(Double profitRateMin) {
        this.profitRateMin = profitRateMin;
    }
    public Double getProfitRateMax() {
        return profitRateMax;
    }
    public void setProfitRateMax(Double profitRateMax) {
        this.profitRateMax = profitRateMax;
    }
    public Date getBuyDate() {
        return buyDate;
    }
    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }
    public Double getBuyMoney() {
        return buyMoney;
    }
    public void setBuyMoney(Double buyMoney) {
        this.buyMoney = buyMoney;
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
        result = prime * result + ((buyDate == null) ? 0 : buyDate.hashCode());
        result = prime * result + ((buyMoney == null) ? 0 : buyMoney.hashCode());
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        result = prime * result + ((moneyUnit == null) ? 0 : moneyUnit.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((productName == null) ? 0 : productName.hashCode());
        result = prime * result + ((profitRateMax == null) ? 0 : profitRateMax.hashCode());
        result = prime * result + ((profitRateMin == null) ? 0 : profitRateMin.hashCode());
        result = prime * result + ((raiseUpperLimit == null) ? 0 : raiseUpperLimit.hashCode());
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
        final CustomerBuyProduct other = (CustomerBuyProduct) obj;
        if (buyDate == null) {
            if (other.buyDate != null)
                return false;
        } else if (!buyDate.equals(other.buyDate))
            return false;
        if (buyMoney == null) {
            if (other.buyMoney != null)
                return false;
        } else if (!buyMoney.equals(other.buyMoney))
            return false;
        if (customerId == null) {
            if (other.customerId != null)
                return false;
        } else if (!customerId.equals(other.customerId))
            return false;
        if (moneyUnit == null) {
            if (other.moneyUnit != null)
                return false;
        } else if (!moneyUnit.equals(other.moneyUnit))
            return false;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!productId.equals(other.productId))
            return false;
        if (productName == null) {
            if (other.productName != null)
                return false;
        } else if (!productName.equals(other.productName))
            return false;
        if (profitRateMax == null) {
            if (other.profitRateMax != null)
                return false;
        } else if (!profitRateMax.equals(other.profitRateMax))
            return false;
        if (profitRateMin == null) {
            if (other.profitRateMin != null)
                return false;
        } else if (!profitRateMin.equals(other.profitRateMin))
            return false;
        if (raiseUpperLimit == null) {
            if (other.raiseUpperLimit != null)
                return false;
        } else if (!raiseUpperLimit.equals(other.raiseUpperLimit))
            return false;
        return true;
    }
    public CustomerBuyProduct(Integer customerId, Integer productId, String productName,
                              Double profitRateMin, Double profitRateMax, Date buyDate,
                              Double buyMoney, String moneyUnit, Double raiseUpperLimit) {
        super();
        this.customerId = customerId;
        this.productId = productId;
        this.productName = productName;
        this.profitRateMin = profitRateMin;
        this.profitRateMax = profitRateMax;
        this.buyDate = buyDate;
        this.buyMoney = buyMoney;
        this.moneyUnit = moneyUnit;
        this.raiseUpperLimit = raiseUpperLimit;
    }
    public CustomerBuyProduct() {
        super();
    }
    
    
}
