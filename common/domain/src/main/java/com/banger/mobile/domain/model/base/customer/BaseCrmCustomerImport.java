/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户导入覆盖字段
 * Author     :yangy
 * Create Date:2012-6-8
 */
package com.banger.mobile.domain.model.base.customer;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yangyang
 * @version $Id: BaseCrmCustomerImport.java,v 0.1 2012-6-8 下午4:46:05 yangyong Exp $
 */
public class BaseCrmCustomerImport extends BaseObject implements Comparable, java.io.Serializable{

    private static final long serialVersionUID = 2109310158459869833L;

    private Integer customerImportId;       //客户导入ID
    private String userId;                  //使用人员
    private String customerFieldName;        //覆盖字段
    private Integer  isOverWrite;            //是否覆盖            
    private Date createDate;                //创建时间
    private Date updateDate;                //更新时间
    private Integer createUser;             //新建者
    private Integer updateUser;             //修改者
    public Integer getCustomerImportId() {
        return customerImportId;
    }
    public void setCustomerImportId(Integer customerImportId) {
        this.customerImportId = customerImportId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getCustomerFieldName() {
        return customerFieldName;
    }
    public void setCustomerFieldName(String customerFieldName) {
        this.customerFieldName = customerFieldName;
    }
    public Integer getIsOverWrite() {
        return isOverWrite;
    }
    public void setIsOverWrite(Integer isOverWrite) {
        this.isOverWrite = isOverWrite;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public Integer getCreateUser() {
        return createUser;
    }
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }
    public Integer getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        BaseCrmCustomerImport myClass = (BaseCrmCustomerImport) object;
        return new CompareToBuilder().append(this.createUser, myClass.createUser)
            .append(this.customerFieldName, myClass.customerFieldName)
            .append(this.userId, myClass.userId).append(this.isOverWrite, myClass.isOverWrite)
            .append(this.createDate, myClass.createDate)
            .append(this.customerImportId, myClass.customerImportId)
            .append(this.updateDate, myClass.updateDate)
            .append(this.updateUser, myClass.updateUser).toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseCrmCustomerImport)) {
            return false;
        }
        BaseCrmCustomerImport rhs = (BaseCrmCustomerImport) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.createUser, rhs.createUser)
            .append(this.customerFieldName, rhs.customerFieldName).append(this.userId, rhs.userId)
            .append(this.isOverWrite, rhs.isOverWrite).append(this.createDate, rhs.createDate)
            .append(this.customerImportId, rhs.customerImportId)
            .append(this.updateDate, rhs.updateDate).append(this.updateUser, rhs.updateUser)
            .isEquals();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("userId", this.userId)
            .append("createDate", this.createDate).append("updateDate", this.updateDate)
            .append("endRow", this.getEndRow()).append("isOverWrite", this.isOverWrite)
            .append("customerImportId", this.customerImportId)
            .append("createUser", this.createUser).append("updateUser", this.updateUser)
            .append("customerFieldName", this.customerFieldName)
            .append("startRow", this.getStartRow()).toString();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-63514723, -597960723).appendSuper(super.hashCode())
            .append(this.createUser).append(this.customerFieldName).append(this.userId)
            .append(this.isOverWrite).append(this.createDate).append(this.customerImportId)
            .append(this.updateDate).append(this.updateUser).toHashCode();
    }
    
    
}
