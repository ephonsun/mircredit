/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :积分商城中我的客户实体...
 * Author     :yangy
 * Create Date:Aug 15, 2012
 */
package com.banger.mobile.domain.model.points;

import java.io.Serializable;
import java.util.Date;

/**
 * User: yangy
 * Date: 13-8-21
 * Time: 下午4:25
 * To change this template use File | Settings | File Templates.
 */
public class JfMyCustomer implements Serializable {

    private static final long serialVersionUID = -8496856554919572139L;
    private Integer cusId;          //客户ID
    private String customerCode;    //客户编号
    private String customerName;     //客户姓名
    private String belongToDept;     //归属机构
    private String idCard;          //身份证
    private String phoneNumber1;    //电话
    private String aumValue;
    private Integer userId;         //归属人员
    private Date updateDate;        //更新时间表

    public Integer getCusId() {
        return cusId;
    }

    public void setCusId(Integer cusId) {
        this.cusId = cusId;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBelongToDept() {
        return belongToDept;
    }

    public void setBelongToDept(String belongToDept) {
        this.belongToDept = belongToDept;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JfMyCustomer that = (JfMyCustomer) o;

        if (belongToDept != null ? !belongToDept.equals(that.belongToDept) : that.belongToDept != null) return false;
        if (cusId != null ? !cusId.equals(that.cusId) : that.cusId != null) return false;
        if (customerCode != null ? !customerCode.equals(that.customerCode) : that.customerCode != null) return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;
        if (idCard != null ? !idCard.equals(that.idCard) : that.idCard != null) return false;
        if (phoneNumber1 != null ? !phoneNumber1.equals(that.phoneNumber1) : that.phoneNumber1 != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cusId != null ? cusId.hashCode() : 0;
        result = 31 * result + (customerCode != null ? customerCode.hashCode() : 0);
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (belongToDept != null ? belongToDept.hashCode() : 0);
        result = 31 * result + (idCard != null ? idCard.hashCode() : 0);
        result = 31 * result + (phoneNumber1 != null ? phoneNumber1.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JfMyCustomer{" +
                "cusId=" + cusId +
                ", customerCode='" + customerCode + '\'' +
                ", customerName='" + customerName + '\'' +
                ", belongToDept='" + belongToDept + '\'' +
                ", idCard='" + idCard + '\'' +
                ", phoneNumber1='" + phoneNumber1 + '\'' +
                ", userId=" + userId +
                ", updateDate=" + updateDate +
                '}';
    }


    public String getAumValue() {
        return aumValue;
    }

    public void setAumValue(String aumValue) {
        this.aumValue = aumValue;
    }
}
