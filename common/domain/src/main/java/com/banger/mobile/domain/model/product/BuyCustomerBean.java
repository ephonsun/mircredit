/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :hk
 * Create Date:Aug 16, 2012
 */
package com.banger.mobile.domain.model.product;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.banger.mobile.domain.model.base.product.BaseProductCustomer;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * @author hk
 * @version $Id: BuyCustomerBean.java,v 0.1 Aug 16, 2012 11:20:07 AM hk Exp $
 */
public class BuyCustomerBean extends BaseProductCustomer{

    private static final long serialVersionUID = 8388667574524796667L;
    
    private String lineNumber;          //行号
    private String customerNo;          //客户编号
    private String customerName;        //客户姓名
    private String account;             //营销人员
    private String phone;
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public BuyCustomerBean(){}

    

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

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1529566653, -809055361).appendSuper(super.hashCode())
            .append(this.customerName).append(this.phone).append(this.customerNo)
            .append(this.account).toHashCode();
    }

    
    
    


    
  
}
