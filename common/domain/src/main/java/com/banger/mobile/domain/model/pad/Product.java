/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-7-20
 */
package com.banger.mobile.domain.model.pad;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yuanme
 * @version Product.java,v 0.1 2012-7-20 上午11:32:49
 */
public class Product implements Serializable {
    private static final long serialVersionUID = -6978511862291378175L;

    private Integer productId;      //产品id
    private String  productName;    //产品名称
    private String  productCode;    //产品编号
    private Integer productTypeId;  //产品类型id
    private Integer profitTypeId;   //收益类型id
    private Double  profitRateMin;  //最小收益率
    private Double  profitRateMax;  //最大收益率
    private Date    saleStartDate;  //发售期始
    private Date    saleEndDate;    //发售期终
    private Integer duration;       //理财时长
    private Date    expireDate;     //到期日
    private Double  raiseUpperLimit;//募集上限
    private Integer moneyUnitId;    //募集上线单位
    private Integer remindDays;     //到期提醒日
    private Integer isDel;          //是否删除
    private Date    createDate;
    private Date    updateDate;
    private Integer createUser;
    private Integer updateUser;
    private String  moneyUnit;      //募集单位
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
    public Integer getProductTypeId() {
        return productTypeId;
    }
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }
    public Integer getProfitTypeId() {
        return profitTypeId;
    }
    public void setProfitTypeId(Integer profitTypeId) {
        this.profitTypeId = profitTypeId;
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
    public Date getSaleStartDate() {
        return saleStartDate;
    }
    public void setSaleStartDate(Date saleStartDate) {
        this.saleStartDate = saleStartDate;
    }
    public Date getSaleEndDate() {
        return saleEndDate;
    }
    public void setSaleEndDate(Date saleEndDate) {
        this.saleEndDate = saleEndDate;
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
    public Double getRaiseUpperLimit() {
        return raiseUpperLimit;
    }
    public void setRaiseUpperLimit(Double raiseUpperLimit) {
        this.raiseUpperLimit = raiseUpperLimit;
    }
    public Integer getMoneyUnitId() {
        return moneyUnitId;
    }
    public void setMoneyUnitId(Integer moneyUnitId) {
        this.moneyUnitId = moneyUnitId;
    }
    public Integer getRemindDays() {
        return remindDays;
    }
    public void setRemindDays(Integer remindDays) {
        this.remindDays = remindDays;
    }
    public Integer getIsDel() {
        return isDel;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public Integer getCreateUser() {
        return createUser;
    }
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }
    public Integer getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
    public String getMoneyUnit() {
        return moneyUnit;
    }
    public void setMoneyUnit(String moneyUnit) {
        this.moneyUnit = moneyUnit;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = prime * result + ((createUser == null) ? 0 : createUser.hashCode());
        result = prime * result + ((duration == null) ? 0 : duration.hashCode());
        result = prime * result + ((expireDate == null) ? 0 : expireDate.hashCode());
        result = prime * result + ((isDel == null) ? 0 : isDel.hashCode());
        result = prime * result + ((moneyUnit == null) ? 0 : moneyUnit.hashCode());
        result = prime * result + ((moneyUnitId == null) ? 0 : moneyUnitId.hashCode());
        result = prime * result + ((productCode == null) ? 0 : productCode.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((productName == null) ? 0 : productName.hashCode());
        result = prime * result + ((productTypeId == null) ? 0 : productTypeId.hashCode());
        result = prime * result + ((profitRateMax == null) ? 0 : profitRateMax.hashCode());
        result = prime * result + ((profitRateMin == null) ? 0 : profitRateMin.hashCode());
        result = prime * result + ((profitTypeId == null) ? 0 : profitTypeId.hashCode());
        result = prime * result + ((raiseUpperLimit == null) ? 0 : raiseUpperLimit.hashCode());
        result = prime * result + ((remindDays == null) ? 0 : remindDays.hashCode());
        result = prime * result + ((saleEndDate == null) ? 0 : saleEndDate.hashCode());
        result = prime * result + ((saleStartDate == null) ? 0 : saleStartDate.hashCode());
        result = prime * result + ((updateDate == null) ? 0 : updateDate.hashCode());
        result = prime * result + ((updateUser == null) ? 0 : updateUser.hashCode());
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
        final Product other = (Product) obj;
        if (createDate == null) {
            if (other.createDate != null)
                return false;
        } else if (!createDate.equals(other.createDate))
            return false;
        if (createUser == null) {
            if (other.createUser != null)
                return false;
        } else if (!createUser.equals(other.createUser))
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
        if (isDel == null) {
            if (other.isDel != null)
                return false;
        } else if (!isDel.equals(other.isDel))
            return false;
        if (moneyUnit == null) {
            if (other.moneyUnit != null)
                return false;
        } else if (!moneyUnit.equals(other.moneyUnit))
            return false;
        if (moneyUnitId == null) {
            if (other.moneyUnitId != null)
                return false;
        } else if (!moneyUnitId.equals(other.moneyUnitId))
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
        if (profitTypeId == null) {
            if (other.profitTypeId != null)
                return false;
        } else if (!profitTypeId.equals(other.profitTypeId))
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
        if (updateDate == null) {
            if (other.updateDate != null)
                return false;
        } else if (!updateDate.equals(other.updateDate))
            return false;
        if (updateUser == null) {
            if (other.updateUser != null)
                return false;
        } else if (!updateUser.equals(other.updateUser))
            return false;
        return true;
    }
    public Product(Integer productId, String productName, String productCode,
                   Integer productTypeId, Integer profitTypeId, Double profitRateMin,
                   Double profitRateMax, Date saleStartDate, Date saleEndDate, Integer duration,
                   Date expireDate, Double raiseUpperLimit, Integer moneyUnitId,
                   Integer remindDays, Integer isDel, Date createDate, Date updateDate,
                   Integer createUser, Integer updateUser, String moneyUnit) {
        super();
        this.productId = productId;
        this.productName = productName;
        this.productCode = productCode;
        this.productTypeId = productTypeId;
        this.profitTypeId = profitTypeId;
        this.profitRateMin = profitRateMin;
        this.profitRateMax = profitRateMax;
        this.saleStartDate = saleStartDate;
        this.saleEndDate = saleEndDate;
        this.duration = duration;
        this.expireDate = expireDate;
        this.raiseUpperLimit = raiseUpperLimit;
        this.moneyUnitId = moneyUnitId;
        this.remindDays = remindDays;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.moneyUnit = moneyUnit;
    }
    public Product() {
        super();
    }
    
    
}
