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
public class ProductCustomer implements Serializable {
    private static final long serialVersionUID = 2233619631092835398L;
    private Integer           customerId;
    private String            customerNo;
    private String            customerName;
    private String            customerTitle;
    private String            addTime;
    private String            addPerson;
    private String            belongTo;
    private String            contactTel;
    private String            intentProduct;
    private String            intentState;
    private String            address;
    private String            lnglat;
    private String            email;
    private Integer           isNoGood; //是否为不良客户
    private Integer           belongUserId;//客户归属id

    public Integer getBelongUserId() {
        return belongUserId;
    }

    public void setBelongUserId(Integer belongUserId) {
        this.belongUserId = belongUserId;
    }

    public Integer getIsNoGood() {
        return isNoGood;
    }

    public void setIsNoGood(Integer nogood) {
        isNoGood = nogood;
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getAddPerson() {
        return addPerson;
    }

    public void setAddPerson(String addPerson) {
        this.addPerson = addPerson;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getIntentProduct() {
        return intentProduct;
    }

    public void setIntentProduct(String intentProduct) {
        this.intentProduct = intentProduct;
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

    public String getLnglat() {
        return lnglat;
    }

    public void setLnglat(String lnglat) {
        this.lnglat = lnglat;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
