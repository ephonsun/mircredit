/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-8-13
 */
package com.banger.mobile.domain.model.base.user;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author yangyang
 * @version $Id: BaseSysUserOnline.java,v 0.1 2012-8-13 下午1:39:41 yangyong Exp $
 */
@SuppressWarnings("rawtypes")
public class BaseSysUserOnline extends BaseObject implements Comparable,   Serializable{

    private static final long serialVersionUID = 2471799644267228410L;
    private Integer userOnlineId;                    //编号
    private Integer userId;                          //用户编号
    private String  userSessionId;                   //用户SESSION
    private Integer onlineStatusId;                  //登陆状态ID
    private Date loginDate;                          //上次登陆时间
    private Date createDate;                         //创建时间
    private Date updateDate;                         //更新时间
    private Integer   createUser;                    //创建用户
    private Integer   updateUser;                    //更新用户
    public Integer getUserOnlineId() {
        return userOnlineId;
    }
    public void setUserOnlineId(Integer userOnlineId) {
        this.userOnlineId = userOnlineId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUserSessionId() {
        return userSessionId;
    }
    public void setUserSessionId(String userSessionId) {
        this.userSessionId = userSessionId;
    }
    public Integer getOnlineStatusId() {
        return onlineStatusId;
    }
    public void setOnlineStatusId(Integer onlineStatusId) {
        this.onlineStatusId = onlineStatusId;
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
    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        BaseSysUserOnline myClass = (BaseSysUserOnline) object;
        return new CompareToBuilder().append(this.createUser, myClass.createUser)
            .append(this.userSessionId, myClass.userSessionId)
            .append(this.userOnlineId, myClass.userOnlineId).append(this.userId, myClass.userId)
            .append(this.onlineStatusId, myClass.onlineStatusId)
            .append(this.loginDate, myClass.loginDate).append(this.createDate, myClass.createDate)
            .append(this.updateDate, myClass.updateDate)
            .append(this.updateUser, myClass.updateUser).toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseSysUserOnline)) {
            return false;
        }
        BaseSysUserOnline rhs = (BaseSysUserOnline) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.createUser, rhs.createUser).append(this.userSessionId, rhs.userSessionId)
            .append(this.userOnlineId, rhs.userOnlineId).append(this.userId, rhs.userId)
            .append(this.onlineStatusId, rhs.onlineStatusId).append(this.loginDate, rhs.loginDate)
            .append(this.createDate, rhs.createDate).append(this.updateDate, rhs.updateDate)
            .append(this.updateUser, rhs.updateUser).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-560455899, 443718317).appendSuper(super.hashCode())
            .append(this.createUser).append(this.userSessionId).append(this.userOnlineId)
            .append(this.userId).append(this.onlineStatusId).append(this.loginDate)
            .append(this.createDate).append(this.updateDate).append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("userId", this.userId)
            .append("createDate", this.createDate).append("userOnlineId", this.userOnlineId)
            .append("onlineStatusId", this.onlineStatusId).append("updateDate", this.updateDate)
            .append("endRow", this.getEndRow()).append("userSessionId", this.userSessionId)
            .append("createUser", this.createUser).append("updateUser", this.updateUser)
            .append("loginDate", this.loginDate).append("startRow", this.getStartRow()).toString();
    }
    
}
