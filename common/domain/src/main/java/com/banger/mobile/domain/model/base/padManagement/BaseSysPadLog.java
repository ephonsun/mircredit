/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD使用记录
 * Author     :liyb
 * Create Date:2013-6-19
 */
package com.banger.mobile.domain.model.base.padManagement;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author liyb
 * @version $Id: BaseSysPadLog.java,v 0.1 2013-6-19 上午10:05:21 liyb Exp $
 */
public class BaseSysPadLog extends BaseObject implements Serializable {

    private static final long serialVersionUID = 6859666319322437600L;

    private Integer           padLogId;                                //主键ID
    private Integer           padInfoId;                               //pad设备ID
    private Integer           userId;                                  //操作用户
    private Integer           loginType;                               //登陆还是登出1:登录2:登出
    private Date              loginDate;                               //登录时间
    private Date              createDate;                             //创建时间
    private Date              updateDate;                             //更新时间
    private Integer           createUser;                             //创建用户
    private Integer           updateUser;                             //更新用户
    
    public Integer getPadLogId() {
        return padLogId;
    }
    public void setPadLogId(Integer padLogId) {
        this.padLogId = padLogId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getLoginType() {
        return loginType;
    }
    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }
    public Date getLoginDate() {
        return loginDate;
    }
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
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
    public Integer getPadInfoId() {
        return padInfoId;
    }
    public void setPadInfoId(Integer padInfoId) {
        this.padInfoId = padInfoId;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseSysPadLog)) {
            return false;
        }
        BaseSysPadLog rhs = (BaseSysPadLog) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.createUser,
            rhs.createUser).append(this.userId, rhs.userId).append(this.loginDate, rhs.loginDate)
            .append(this.padInfoId, rhs.padInfoId).append(this.padLogId, rhs.padLogId).append(
                this.createDate, rhs.createDate).append(this.updateDate, rhs.updateDate).append(
                this.loginType, rhs.loginType).append(this.updateUser, rhs.updateUser).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1825401201, 801164895).appendSuper(super.hashCode()).append(
            this.createUser).append(this.userId).append(this.loginDate).append(this.padInfoId)
            .append(this.padLogId).append(this.createDate).append(this.updateDate).append(
                this.loginType).append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("userId", this.userId).append("padLogId",
            this.padLogId).append("padInfoId", this.padInfoId)
            .append("createDate", this.createDate).append("updateDate", this.updateDate).append(
                "endRow", this.getEndRow()).append("createUser", this.createUser).append(
                "loginType", this.loginType).append("updateUser", this.updateUser).append(
                "loginDate", this.loginDate).append("startRow", this.getStartRow()).toString();
    }
    
}
