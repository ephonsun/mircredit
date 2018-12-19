/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :hk
 * Create Date:Aug 13, 2012
 */
package com.banger.mobile.domain.model.product;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hk
 * @version $Id: ProductDetail.java,v 0.1 Aug 13, 2012 11:50:52 AM hk Exp $
 */
public class ProductDetail implements Serializable{
    
    private static final long serialVersionUID = 4250110475741361757L;
    private Integer productId;  //产品id
    private String productCode; //产品编号
    private String productName; //产品名称
    private Double profitRateMin;   //最小收益率
    private Double profitRateMax;   //最大收益率
    private Integer duration;  //理财时长
    private Date expireDate;    //到期日
    private Integer expireDays;     //剩余到期天数
    private Integer isDealCount;    //未处理客户数
    private Double buyMoneyTotal;    //总销售额
    private String moneyUnitName;   //单位
    private Integer buyCustomerCount;   //销售客户数
    private Double buyMoney;    //销售额
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public String getProductCode() {
        return productCode;
    }
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public double getProfitRateMin() {
        return profitRateMin;
    }
    public void setProfitRateMin(double profitRateMin) {
        this.profitRateMin = profitRateMin;
    }
    public double getProfitRateMax() {
        return profitRateMax;
    }
    public void setProfitRateMax(double profitRateMax) {
        this.profitRateMax = profitRateMax;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public Date getExpireDate() {
        return expireDate;
    }
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
    public Integer getExpireDays() {
        return expireDays;
    }
    public void setExpireDays(Integer expireDays) {
        this.expireDays = expireDays;
    }
    public Integer getIsDealCount() {
        return isDealCount;
    }
    public void setIsDealCount(Integer isDealCount) {
        this.isDealCount = isDealCount;
    }
    public double getBuyMoneyTotal() {
        return buyMoneyTotal;
    }
    public void setBuyMoneyTotal(double buyMoneyTotal) {
        this.buyMoneyTotal = buyMoneyTotal;
    }
    public String getMoneyUnitName() {
        return moneyUnitName;
    }
    public void setMoneyUnitName(String moneyUnitName) {
        this.moneyUnitName = moneyUnitName;
    }
    public Integer getBuyCustomerCount() {
        return buyCustomerCount;
    }
    public void setBuyCustomerCount(Integer buyCustomerCount) {
        this.buyCustomerCount = buyCustomerCount;
    }
    public double getBuyMoney() {
        return buyMoney;
    }
    public void setBuyMoney(double buyMoney) {
        this.buyMoney = buyMoney;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((buyCustomerCount == null) ? 0 : buyCustomerCount.hashCode());
        long temp;
        temp = Double.doubleToLongBits(buyMoney);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(buyMoneyTotal);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((duration == null) ? 0 : duration.hashCode());
        result = prime * result + ((expireDate == null) ? 0 : expireDate.hashCode());
        result = prime * result + ((expireDays == null) ? 0 : expireDays.hashCode());
        result = prime * result + ((isDealCount == null) ? 0 : isDealCount.hashCode());
        result = prime * result + ((moneyUnitName == null) ? 0 : moneyUnitName.hashCode());
        result = prime * result + ((productCode == null) ? 0 : productCode.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((productName == null) ? 0 : productName.hashCode());
        temp = Double.doubleToLongBits(profitRateMax);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(profitRateMin);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        final ProductDetail other = (ProductDetail) obj;
        if (buyCustomerCount == null) {
            if (other.buyCustomerCount != null)
                return false;
        } else if (!buyCustomerCount.equals(other.buyCustomerCount))
            return false;
        if (Double.doubleToLongBits(buyMoney) != Double.doubleToLongBits(other.buyMoney))
            return false;
        if (Double.doubleToLongBits(buyMoneyTotal) != Double.doubleToLongBits(other.buyMoneyTotal))
            return false;
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
        if (expireDays == null) {
            if (other.expireDays != null)
                return false;
        } else if (!expireDays.equals(other.expireDays))
            return false;
        if (isDealCount == null) {
            if (other.isDealCount != null)
                return false;
        } else if (!isDealCount.equals(other.isDealCount))
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
        if (Double.doubleToLongBits(profitRateMax) != Double.doubleToLongBits(other.profitRateMax))
            return false;
        if (Double.doubleToLongBits(profitRateMin) != Double.doubleToLongBits(other.profitRateMin))
            return false;
        return true;
    }
    public ProductDetail(Integer productId, String productCode, String productName,
                         double profitRateMin, double profitRateMax, Integer duration,
                         Date expireDate, Integer expireDays, Integer isDealCount,
                         double buyMoneyTotal, String moneyUnitName, Integer buyCustomerCount,
                         double buyMoney) {
        super();
        this.productId = productId;
        this.productCode = productCode;
        this.productName = productName;
        this.profitRateMin = profitRateMin;
        this.profitRateMax = profitRateMax;
        this.duration = duration;
        this.expireDate = expireDate;
        this.expireDays = expireDays;
        this.isDealCount = isDealCount;
        this.buyMoneyTotal = buyMoneyTotal;
        this.moneyUnitName = moneyUnitName;
        this.buyCustomerCount = buyCustomerCount;
        this.buyMoney = buyMoney;
    }
    public ProductDetail() {
        super();
    }
    
    
}
