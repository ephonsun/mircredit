/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-11-27
 */
package com.banger.mobile.domain.model.tskContact;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author cheny
 * @version $Id: TskImportBean.java,v 0.1 2012-11-27 上午11:17:23 cheny Exp $
 */
public class TskImportBean extends BaseObject implements java.io.Serializable{

    private static final long serialVersionUID = -2903537411602778880L;
    private Integer contactTargetId;
    private Integer executeId;
    private Integer contactId;
    private Integer customerId;
    private String phoneNo;
    private String customerName;
    private String account;
    private String customerNo;
    private Date  createDate;
    private Integer createUser;

    
    
    
    public String getCustomerNo() {
        return customerNo;
    }
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    public Integer getContactTargetId() {
        return contactTargetId;
    }
    public void setContactTargetId(Integer contactTargetId) {
        this.contactTargetId = contactTargetId;
    }
    public Integer getExecuteId() {
        return executeId;
    }
    public void setExecuteId(Integer executeId) {
        this.executeId = executeId;
    }
    public Integer getContactId() {
        return contactId;
    }
    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Integer getCreateUser() {
        return createUser;
    }
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }
    /**
     * 
     */
    public TskImportBean() {
        super();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof TskImportBean)) {
            return false;
        }
        TskImportBean rhs = (TskImportBean) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.customerName, rhs.customerName).append(this.phoneNo, rhs.phoneNo)
            .append(this.createUser, rhs.createUser).append(this.customerId, rhs.customerId)
            .append(this.contactId, rhs.contactId).append(this.executeId, rhs.executeId)
            .append(this.contactTargetId, rhs.contactTargetId)
            .append(this.customerNo, rhs.customerNo).append(this.account, rhs.account)
            .append(this.createDate, rhs.createDate).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-209705085, 1664519609).appendSuper(super.hashCode())
            .append(this.customerName).append(this.phoneNo).append(this.createUser)
            .append(this.customerId).append(this.contactId).append(this.executeId)
            .append(this.contactTargetId).append(this.customerNo).append(this.account)
            .append(this.createDate).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("executeId", this.executeId)
            .append("contactId", this.contactId).append("createDate", this.createDate)
            .append("account", this.account).append("phoneNo", this.phoneNo)
            .append("customerId", this.customerId).append("customerNo", this.customerNo)
            .append("endRow", this.getEndRow()).append("contactTargetId", this.contactTargetId)
            .append("customerName", this.customerName).append("createUser", this.createUser)
            .append("startRow", this.getStartRow()).toString();
    }
  
    

}
