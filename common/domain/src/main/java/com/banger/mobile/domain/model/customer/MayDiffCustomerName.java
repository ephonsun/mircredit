/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-8-16
 */
package com.banger.mobile.domain.model.customer;

/**
 * @author yuanme
 * @version MayDiffCustomer.java,v 0.1 2012-8-16 下午2:13:19
 */
public class MayDiffCustomerName implements java.io.Serializable {
    private static final long serialVersionUID = 1076573736478447197L;
    private Integer           countNumber;
    private String            customerName;
    
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public Integer getCountNumber() {
        return countNumber;
    }
    public void setCountNumber(Integer countNumber) {
        this.countNumber = countNumber;
    }

}
