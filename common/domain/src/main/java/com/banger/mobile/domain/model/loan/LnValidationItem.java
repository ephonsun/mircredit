package com.banger.mobile.domain.model.loan;

import java.math.BigDecimal;
import java.util.Date;

public class LnValidationItem {

    private Integer loanId; //
    private Integer id; //

    private String itemName;//项目名
    private String itemRemark;//描述
    private Double itemRate;//偏差率
    private String type;//1销售额检验 2毛利检验 3净利检验 4收入检验
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemRemark() {
        return itemRemark;
    }

    public void setItemRemark(String itemRemark) {
        this.itemRemark = itemRemark;
    }

    public Double getItemRate() {
        return itemRate;
    }

    public void setItemRate(Double itemRate) {
        this.itemRate = itemRate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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