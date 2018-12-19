/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :工作移交记录...
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
 * @version $Id: BaseSysWorkTransfer.java,v 0.1 2012-8-13 下午4:51:30 yangyong Exp $
 */
@SuppressWarnings("rawtypes")
public class BaseSysWorkTransfer extends BaseObject implements Comparable,  Serializable{

    private static final long serialVersionUID = 2711811907170510733L;
    private Integer workTransferId;                  //编号
    private Integer userId;                          //被移交用户ID
    private Integer transferUserId;                  //移交用户ID   
    private Integer managerUserId;                   //执行主管用户ID
    private Integer isTransferOver;                  //移交状态
    private Date transferDate;                       //移交时间
    private Date createDate;                         //创建时间
    private Date updateDate;                         //更新时间
    private Integer   createUser;                    //创建用户
    private Integer   updateUser;                    //更新用户
    public Integer getWorkTransferId() {
        return workTransferId;
    }
    public void setWorkTransferId(Integer workTransferId) {
        this.workTransferId = workTransferId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getTransferUserId() {
        return transferUserId;
    }
    public void setTransferUserId(Integer transferUserId) {
        this.transferUserId = transferUserId;
    }
    public Integer getManagerUserId() {
        return managerUserId;
    }
    public void setManagerUserId(Integer managerUserId) {
        this.managerUserId = managerUserId;
    }
    public Integer getIsTransferOver() {
        return isTransferOver;
    }
    public void setIsTransferOver(Integer isTransferOver) {
        this.isTransferOver = isTransferOver;
    }
    public Date getTransferDate() {
        return transferDate;
    }
    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
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
        BaseSysWorkTransfer myClass = (BaseSysWorkTransfer) object;
        return new CompareToBuilder().append(this.createUser, myClass.createUser)
            .append(this.workTransferId, myClass.workTransferId)
            .append(this.transferDate, myClass.transferDate).append(this.userId, myClass.userId)
            .append(this.isTransferOver, myClass.isTransferOver)
            .append(this.managerUserId, myClass.managerUserId)
            .append(this.transferUserId, myClass.transferUserId)
            .append(this.createDate, myClass.createDate)
            .append(this.updateDate, myClass.updateDate)
            .append(this.updateUser, myClass.updateUser).toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseSysWorkTransfer)) {
            return false;
        }
        BaseSysWorkTransfer rhs = (BaseSysWorkTransfer) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.createUser, rhs.createUser)
            .append(this.workTransferId, rhs.workTransferId)
            .append(this.transferDate, rhs.transferDate).append(this.userId, rhs.userId)
            .append(this.isTransferOver, rhs.isTransferOver)
            .append(this.managerUserId, rhs.managerUserId)
            .append(this.transferUserId, rhs.transferUserId)
            .append(this.createDate, rhs.createDate).append(this.updateDate, rhs.updateDate)
            .append(this.updateUser, rhs.updateUser).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-64314159, 1759759179).appendSuper(super.hashCode())
            .append(this.createUser).append(this.workTransferId).append(this.transferDate)
            .append(this.userId).append(this.isTransferOver).append(this.managerUserId)
            .append(this.transferUserId).append(this.createDate).append(this.updateDate)
            .append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("managerUserId", this.managerUserId)
            .append("userId", this.userId).append("transferUserId", this.transferUserId)
            .append("createDate", this.createDate).append("updateDate", this.updateDate)
            .append("isTransferOver", this.isTransferOver)
            .append("transferDate", this.transferDate)
            .append("workTransferId", this.workTransferId).append("endRow", this.getEndRow())
            .append("createUser", this.createUser).append("updateUser", this.updateUser)
            .append("startRow", this.getStartRow()).toString();
    }
    
    

}
