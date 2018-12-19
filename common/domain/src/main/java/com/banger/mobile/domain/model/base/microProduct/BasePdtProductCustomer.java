/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品意向客户-Domain
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.domain.model.base.microProduct;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author QianJie
 * @version $Id: BasePdtProductCustomer.java,v 0.1 Nov 12, 2012 4:04:38 PM QianJie Exp $
 */
public class BasePdtProductCustomer extends BaseObject implements Serializable {

    private static final long serialVersionUID = 7844503977309664892L;

    private Integer           productCustomerId;                      //主键
    private Integer           productId;                              //产品ID
    private Integer           customerId;                             //客户ID
    private String            remark;                                 //意向说明
    private Integer           userId;                                 //用户ID
    private Integer           isDel;                                  //是否删除
    private Date              createDate;                             //创建时间
    private Date              updateDate;                             //更新时间
    private Integer           createUser;                             //创建用户
    private Integer           updateUser;                             //更新用户
    
    public BasePdtProductCustomer() {
        super();
    }

    public BasePdtProductCustomer(Integer productCustomerId, Integer productId, Integer customerId,
                                  String remark, Integer userId, Integer isDel, Date createDate,
                                  Date updateDate, Integer createUser, Integer updateUser) {
        super();
        this.productCustomerId = productCustomerId;
        this.productId = productId;
        this.customerId = customerId;
        this.remark = remark;
        this.userId = userId;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    public Integer getProductCustomerId() {
        return productCustomerId;
    }

    public void setProductCustomerId(Integer productCustomerId) {
        this.productCustomerId = productCustomerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
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
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BasePdtProductCustomer)) {
            return false;
        }
        BasePdtProductCustomer rhs = (BasePdtProductCustomer) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.isDel, rhs.isDel)
            .append(this.userId, rhs.userId).append(this.productId, rhs.productId).append(
                this.createDate, rhs.createDate).append(this.remark, rhs.remark).append(
                this.createUser, rhs.createUser).append(this.updateDate, rhs.updateDate).append(
                this.productCustomerId, rhs.productCustomerId).append(this.updateUser,
                rhs.updateUser).append(this.customerId, rhs.customerId).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1175384013, 84006111).appendSuper(super.hashCode()).append(
            this.isDel).append(this.userId).append(this.productId).append(this.createDate).append(
            this.remark).append(this.createUser).append(this.updateDate).append(
            this.productCustomerId).append(this.updateUser).append(this.customerId).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("startRow", this.getStartRow()).append(
            "customerId", this.customerId).append("updateDate", this.updateDate).append("endRow",
            this.getEndRow()).append("updateUser", this.updateUser).append("createUser",
            this.createUser).append("productId", this.productId).append("productCustomerId",
            this.productCustomerId).append("isDel", this.isDel).append("remark", this.remark)
            .append("userId", this.userId).append("createDate", this.createDate).toString();
    }
    
    
}
