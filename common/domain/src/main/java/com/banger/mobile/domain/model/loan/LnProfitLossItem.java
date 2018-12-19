package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.data.BaseDatCustomerData;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/8/2.
 */
public class LnProfitLossItem extends BaseDatCustomerData implements Serializable {
    private static final long serialVersionUID = -2000L;
    private Integer itemId;  
    private String itemName;
    private BigDecimal lastYearAmount;
    private BigDecimal currYearAmount;
    private BigDecimal totalAmount;
    private BigDecimal averageAmount;
    private String remark;
    private String type;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getLastYearAmount() {
        return lastYearAmount;
    }

    public void setLastYearAmount(BigDecimal lastYearAmount) {
        this.lastYearAmount = lastYearAmount;
    }

    public BigDecimal getCurrYearAmount() {
        return currYearAmount;
    }

    public void setCurrYearAmount(BigDecimal currYearAmount) {
        this.currYearAmount = currYearAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getAverageAmount() {
        return averageAmount;
    }

    public void setAverageAmount(BigDecimal averageAmount) {
        this.averageAmount = averageAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
