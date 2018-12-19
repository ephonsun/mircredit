/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :工作托管...
 * Author     :yangy
 * Create Date:2012-8-13
 */
package com.banger.mobile.domain.model.base.user;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yangyang
 * @version $Id: BaseSysWorkDelegate.java,v 0.1 2012-8-13 下午5:25:16 yangyong Exp $
 */
@SuppressWarnings("rawtypes")
public class BaseSysWorkDelegate extends BaseObject implements Comparable,  Serializable{

    private static final long serialVersionUID = 6669185280389481160L;
    private Integer workDelegateId;                  //编号
    private Integer userId;                          //被托管用户ID
    private Integer delegateUserId;                  //托管用户ID
    private Integer managerUserId;                   //执行主管用户ID
    private Date delegateDate;                       //托管时间
    private Integer isCancel;                        //是否撤销
    private Date createDate;                         //创建时间
    private Date updateDate;                         //更新时间
    private Integer   createUser;                    //创建用户
    private Integer   updateUser;                    //更新用户
    public Integer getWorkDelegateId() {
        return workDelegateId;
    }
    public void setWorkDelegateId(Integer workDelegateId) {
        this.workDelegateId = workDelegateId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getDelegateUserId() {
        return delegateUserId;
    }
    public void setDelegateUserId(Integer delegateUserId) {
        this.delegateUserId = delegateUserId;
    }
    public Integer getManagerUserId() {
        return managerUserId;
    }
    public void setManagerUserId(Integer managerUserId) {
        this.managerUserId = managerUserId;
    }
    public Date getDelegateDate() {
        return delegateDate;
    }
    public void setDelegateDate(Date delegateDate) {
        this.delegateDate = delegateDate;
    }
    public Integer getIsCancel() {
        return isCancel;
    }
    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
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
        BaseSysWorkDelegate myClass = (BaseSysWorkDelegate) object;
        return new CompareToBuilder().append(this.delegateDate, myClass.delegateDate)
            .append(this.delegateUserId, myClass.delegateUserId)
            .append(this.createUser, myClass.createUser).append(this.isCancel, myClass.isCancel)
            .append(this.userId, myClass.userId)
            .append(this.workDelegateId, myClass.workDelegateId)
            .append(this.managerUserId, myClass.managerUserId)
            .append(this.createDate, myClass.createDate)
            .append(this.updateDate, myClass.updateDate)
            .append(this.updateUser, myClass.updateUser).toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseSysWorkDelegate)) {
            return false;
        }
        BaseSysWorkDelegate rhs = (BaseSysWorkDelegate) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.delegateDate, rhs.delegateDate)
            .append(this.delegateUserId, rhs.delegateUserId)
            .append(this.createUser, rhs.createUser).append(this.isCancel, rhs.isCancel)
            .append(this.userId, rhs.userId).append(this.workDelegateId, rhs.workDelegateId)
            .append(this.managerUserId, rhs.managerUserId).append(this.createDate, rhs.createDate)
            .append(this.updateDate, rhs.updateDate).append(this.updateUser, rhs.updateUser)
            .isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1789311243, -674808201).appendSuper(super.hashCode())
            .append(this.delegateDate).append(this.delegateUserId).append(this.createUser)
            .append(this.isCancel).append(this.userId).append(this.workDelegateId)
            .append(this.managerUserId).append(this.createDate).append(this.updateDate)
            .append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("managerUserId", this.managerUserId)
            .append("userId", this.userId).append("createDate", this.createDate)
            .append("delegateDate", this.delegateDate).append("updateDate", this.updateDate)
            .append("workDelegateId", this.workDelegateId)
            .append("delegateUserId", this.delegateUserId).append("isCancel", this.isCancel)
            .append("endRow", this.getEndRow()).append("createUser", this.createUser)
            .append("updateUser", this.updateUser).append("startRow", this.getStartRow())
            .toString();
    }
    
}
