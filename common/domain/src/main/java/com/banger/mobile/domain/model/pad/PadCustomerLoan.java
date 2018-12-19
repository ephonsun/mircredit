package com.banger.mobile.domain.model.pad;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA. User: yuanme Date: 13-3-1 Time: 下午2:29 To change
 * this template use File | Settings | File Templates.
 */
public class PadCustomerLoan implements Serializable {
    private Integer customerId;
    private String  customerName;
    private String  phone;
    private String  address;
    private String  idCard;
    private Integer isNoGood;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsNoGood() {
        return this.isNoGood;
    }

    public void setIsNoGood(Integer isNoGood) {
        this.isNoGood = isNoGood;
    }
}
