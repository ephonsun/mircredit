package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnBalance;

import java.math.BigDecimal;

public class LnBalanceFixed extends BaseLnBalance {

    private String itemType; //来源
    private String itemName; //名称
    private Integer itemNum; //数量
    private BigDecimal originalValue; // 原值
    private BigDecimal depreciationValue  ; // 折旧金额
    private BigDecimal presentValue    ; // 现值
    private BigDecimal presentAmount    ; // 金额（元）
    private BigDecimal depreciationRate    ; // 折旧率

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    public BigDecimal getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(BigDecimal originalValue) {
        this.originalValue = originalValue;
    }

    public BigDecimal getDepreciationValue() {
        return depreciationValue;
    }

    public void setDepreciationValue(BigDecimal depreciationValue) {
        this.depreciationValue = depreciationValue;
    }

    public BigDecimal getPresentValue() {
        return presentValue;
    }

    public void setPresentValue(BigDecimal presentValue) {
        this.presentValue = presentValue;
    }

    public BigDecimal getPresentAmount() {
        return presentAmount;
    }

    public void setPresentAmount(BigDecimal presentAmount) {
        this.presentAmount = presentAmount;
    }

    public BigDecimal getDepreciationRate() {
        return depreciationRate;
    }

    public void setDepreciationRate(BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
    }
}