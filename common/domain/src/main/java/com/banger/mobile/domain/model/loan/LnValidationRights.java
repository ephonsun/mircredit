package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnBalance;

import java.math.BigDecimal;
import java.util.Date;

public class LnValidationRights {

    private Integer loanId; //
    private Date rightsBeginDate;//初始权益日期
    private BigDecimal rightsBeginAmount;//初始权益（元）
    private String rightsBeginRemark;//初始权益明细
    private BigDecimal increaseAmount;//期间利润(期间收入)
    private BigDecimal increaseFinancing;//期间注资
    private BigDecimal  increaseValue;//期间升值
    private String increaseRemark;//期间收入说明
    private BigDecimal lessenAmount;//期间提取
    private BigDecimal lessenValue;//折旧/贬值
    private String lessenRemark;//期间提取说明
    private BigDecimal  rightsDueAmount;//应有权益
    private BigDecimal rightsRealAmount;//实际权益
    private BigDecimal rightsDepreciationValue;//差别
    private Double rightsDepreciationRate;//偏差率
    private Integer createUser;//
    private Date createDate;//
    private Integer updateUser;//
    private Date updateDate;//

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Date getRightsBeginDate() {
        return rightsBeginDate;
    }

    public void setRightsBeginDate(Date rightsBeginDate) {
        this.rightsBeginDate = rightsBeginDate;
    }

    public BigDecimal getRightsBeginAmount() {
        return rightsBeginAmount;
    }

    public void setRightsBeginAmount(BigDecimal rightsBeginAmount) {
        this.rightsBeginAmount = rightsBeginAmount;
    }

    public String getRightsBeginRemark() {
        return rightsBeginRemark;
    }

    public void setRightsBeginRemark(String rightsBeginRemark) {
        this.rightsBeginRemark = rightsBeginRemark;
    }

    public BigDecimal getIncreaseAmount() {
        return increaseAmount;
    }

    public void setIncreaseAmount(BigDecimal increaseAmount) {
        this.increaseAmount = increaseAmount;
    }

    public BigDecimal getIncreaseFinancing() {
        return increaseFinancing;
    }

    public void setIncreaseFinancing(BigDecimal increaseFinancing) {
        this.increaseFinancing = increaseFinancing;
    }

    public BigDecimal getIncreaseValue() {
        return increaseValue;
    }

    public void setIncreaseValue(BigDecimal increaseValue) {
        this.increaseValue = increaseValue;
    }

    public String getIncreaseRemark() {
        return increaseRemark;
    }

    public void setIncreaseRemark(String increaseRemark) {
        this.increaseRemark = increaseRemark;
    }

    public BigDecimal getLessenAmount() {
        return lessenAmount;
    }

    public void setLessenAmount(BigDecimal lessenAmount) {
        this.lessenAmount = lessenAmount;
    }

    public BigDecimal getLessenValue() {
        return lessenValue;
    }

    public void setLessenValue(BigDecimal lessenValue) {
        this.lessenValue = lessenValue;
    }

    public String getLessenRemark() {
        return lessenRemark;
    }

    public void setLessenRemark(String lessenRemark) {
        this.lessenRemark = lessenRemark;
    }

    public BigDecimal getRightsDueAmount() {
        return rightsDueAmount;
    }

    public void setRightsDueAmount(BigDecimal rightsDueAmount) {
        this.rightsDueAmount = rightsDueAmount;
    }

    public BigDecimal getRightsRealAmount() {
        return rightsRealAmount;
    }

    public void setRightsRealAmount(BigDecimal rightsRealAmount) {
        this.rightsRealAmount = rightsRealAmount;
    }

    public BigDecimal getRightsDepreciationValue() {
        return rightsDepreciationValue;
    }

    public void setRightsDepreciationValue(BigDecimal rightsDepreciationValue) {
        this.rightsDepreciationValue = rightsDepreciationValue;
    }

    public Double getRightsDepreciationRate() {
        return rightsDepreciationRate;
    }

    public void setRightsDepreciationRate(Double rightsDepreciationRate) {
        this.rightsDepreciationRate = rightsDepreciationRate;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}