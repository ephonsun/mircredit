/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :hk
 * Create Date:Aug 15, 2012
 */
package com.banger.mobile.domain.model.product;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hk
 * @version $Id: ProductObj.java,v 0.1 Aug 15, 2012 10:39:18 AM hk Exp $
 */
public class ProductObj implements Serializable{
    private static final long serialVersionUID = 6030277524096932264L;
    private Integer productId;      //ID
    private String productName;     //产品名称
    private String productCode;     //产品编号
    private String productTypeName; //产品类型名称
    private String profitTypeName;    //产品收益名称
    private Double profitRateMin;   //预期收益率最小值
    private Double profitRateMax;   //预期收益率最大值
    private String saleStartDate;     //发售期起
    private String saleEndDate;       //发售期终
    private String expireDate;        //到期日
    private Integer duration;       //理财时长
    private Double raiseUpperLimit; //募集上限
    private String moneyUnitName;   //募集单位名称
    private String remindDays;      //到期提醒天数
    private Integer productTypeId;   //产品类型id
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
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public String getProductTypeName() {
        return productTypeName;
    }
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
    public String getProfitTypeName() {
        return profitTypeName;
    }
    public void setProfitTypeName(String profitTypeName) {
        this.profitTypeName = profitTypeName;
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
    public String getSaleStartDate() {
        return saleStartDate;
    }
    public void setSaleStartDate(String saleStartDate) {
        this.saleStartDate = saleStartDate;
    }
    public String getSaleEndDate() {
        return saleEndDate;
    }
    public void setSaleEndDate(String saleEndDate) {
        this.saleEndDate = saleEndDate;
    }
    public String getExpireDate() {
        return expireDate;
    }
    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public Double getRaiseUpperLimit() {
        return raiseUpperLimit;
    }
    public void setRaiseUpperLimit(Double raiseUpperLimit) {
        this.raiseUpperLimit = raiseUpperLimit;
    }
    public String getMoneyUnitName() {
        return moneyUnitName;
    }
    public void setMoneyUnitName(String moneyUnitName) {
        this.moneyUnitName = moneyUnitName;
    }
    public String getRemindDays() {
        return remindDays;
    }
    public void setRemindDays(String remindDays) {
        this.remindDays = remindDays;
    }
    public Integer getProductTypeId() {
        return productTypeId;
    }
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((duration == null) ? 0 : duration.hashCode());
        result = prime * result + ((expireDate == null) ? 0 : expireDate.hashCode());
        result = prime * result + ((moneyUnitName == null) ? 0 : moneyUnitName.hashCode());
        result = prime * result + ((productCode == null) ? 0 : productCode.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((productName == null) ? 0 : productName.hashCode());
        result = prime * result + ((productTypeId == null) ? 0 : productTypeId.hashCode());
        result = prime * result + ((productTypeName == null) ? 0 : productTypeName.hashCode());
        result = prime * result + ((profitRateMax == null) ? 0 : profitRateMax.hashCode());
        result = prime * result + ((profitRateMin == null) ? 0 : profitRateMin.hashCode());
        result = prime * result + ((profitTypeName == null) ? 0 : profitTypeName.hashCode());
        result = prime * result + ((raiseUpperLimit == null) ? 0 : raiseUpperLimit.hashCode());
        result = prime * result + ((remindDays == null) ? 0 : remindDays.hashCode());
        result = prime * result + ((saleEndDate == null) ? 0 : saleEndDate.hashCode());
        result = prime * result + ((saleStartDate == null) ? 0 : saleStartDate.hashCode());
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
        final ProductObj other = (ProductObj) obj;
        if (duration == null) {
            if (other.duration != null)
                return false;
        } else if (!duration.equals(other.duration))
            return false;
        if (expireDate == null) {
            if (other.expireDate != null)
                return false;
        } else if (!expireDate.equals(other.expireDate))
            return false;
        if (moneyUnitName == null) {
            if (other.moneyUnitName != null)
                return false;
        } else if (!moneyUnitName.equals(other.moneyUnitName))
            return false;
        if (productCode == null) {
            if (other.productCode != null)
                return false;
        } else if (!productCode.equals(other.productCode))
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
        if (productTypeId == null) {
            if (other.productTypeId != null)
                return false;
        } else if (!productTypeId.equals(other.productTypeId))
            return false;
        if (productTypeName == null) {
            if (other.productTypeName != null)
                return false;
        } else if (!productTypeName.equals(other.productTypeName))
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
        if (profitTypeName == null) {
            if (other.profitTypeName != null)
                return false;
        } else if (!profitTypeName.equals(other.profitTypeName))
            return false;
        if (raiseUpperLimit == null) {
            if (other.raiseUpperLimit != null)
                return false;
        } else if (!raiseUpperLimit.equals(other.raiseUpperLimit))
            return false;
        if (remindDays == null) {
            if (other.remindDays != null)
                return false;
        } else if (!remindDays.equals(other.remindDays))
            return false;
        if (saleEndDate == null) {
            if (other.saleEndDate != null)
                return false;
        } else if (!saleEndDate.equals(other.saleEndDate))
            return false;
        if (saleStartDate == null) {
            if (other.saleStartDate != null)
                return false;
        } else if (!saleStartDate.equals(other.saleStartDate))
            return false;
        return true;
    }
    public ProductObj(Integer productId, String productName, String productCode,
                      String productTypeName, String profitTypeName, Double profitRateMin,
                      Double profitRateMax, String saleStartDate, String saleEndDate,
                      String expireDate, Integer duration, Double raiseUpperLimit,
                      String moneyUnitName, String remindDays, Integer productTypeId) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productCode = productCode;
        this.productTypeName = productTypeName;
        this.profitTypeName = profitTypeName;
        this.profitRateMin = profitRateMin;
        this.profitRateMax = profitRateMax;
        this.saleStartDate = saleStartDate;
        this.saleEndDate = saleEndDate;
        this.expireDate = expireDate;
        this.duration = duration;
        this.raiseUpperLimit = raiseUpperLimit;
        this.moneyUnitName = moneyUnitName;
        this.remindDays = remindDays;
        this.productTypeId = productTypeId;
    }
    public ProductObj() {
        super();
    }
            
}
