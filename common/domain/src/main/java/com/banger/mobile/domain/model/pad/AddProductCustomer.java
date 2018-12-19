/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-7-23
 */
package com.banger.mobile.domain.model.pad;

import java.io.Serializable;

/**
 * @author yuanme
 * @version ProductBuyCustomer.java,v 0.1 2012-7-23 上午9:27:56
 */
public class AddProductCustomer implements Serializable {
    private static final long serialVersionUID = 2233619631092835398L;
    private Integer           customerId;
    private String            customerNo;
    private String            customerName;
    private String            contactTel;
    private Integer            productId;
    private String            intentState;
    private String            address;
    private String            email;
    private String            remark;
    private Integer            contactWayType;
    private String            contactTime;
    private String            scheduleRemark;
    private String            account;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getContactWayType() {
        return contactWayType;
    }

    public void setContactWayType(Integer contactWayType) {
        this.contactWayType = contactWayType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContactTime() {
        return contactTime;
    }

    public void setContactTime(String contactTime) {
        this.contactTime = contactTime;
    }

    public String getScheduleRemark() {
        return scheduleRemark;
    }

    public void setScheduleRemark(String scheduleRemark) {
        this.scheduleRemark = scheduleRemark;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getIntentState() {
        return intentState;
    }

    public void setIntentState(String intentState) {
        this.intentState = intentState;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
}
