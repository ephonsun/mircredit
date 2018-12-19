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

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yangyang
 * @version $Id: BaseSysUserOnlineMaxSYS_USER_ONLINE_MAX.java,v 0.1 2012-8-13 下午5:11:04 yangyong Exp $
 */
@SuppressWarnings("rawtypes")
public class BaseSysUserOnlineMax extends BaseObject implements Comparable,  Serializable{

    private static final long serialVersionUID = -7481495210491354123L;

    private Integer userOnlineMaxId;                 //编号
    private Integer maxNum;                          //峰值用户数
    private Date  maxDate;                           //峰值时间
    private Date createDate;                         //创建时间
    private Date updateDate;                         //更新时间
    private Integer   createUser;                    //创建用户
    private Integer   updateUser;                    //更新用户
    public Integer getUserOnlineMaxId() {
        return userOnlineMaxId;
    }
    public void setUserOnlineMaxId(Integer userOnlineMaxId) {
        this.userOnlineMaxId = userOnlineMaxId;
    }
    public Integer getMaxNum() {
        return maxNum;
    }
    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }
    public Date getMaxDate() {
        return maxDate;
    }
    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
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
        BaseSysUserOnlineMax myClass = (BaseSysUserOnlineMax) object;
        return new CompareToBuilder().append(this.createUser, myClass.createUser)
            .append(this.maxDate, myClass.maxDate).append(this.createDate, myClass.createDate)
            .append(this.maxNum, myClass.maxNum).append(this.updateDate, myClass.updateDate)
            .append(this.updateUser, myClass.updateUser)
            .append(this.userOnlineMaxId, myClass.userOnlineMaxId).toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseSysUserOnlineMax)) {
            return false;
        }
        BaseSysUserOnlineMax rhs = (BaseSysUserOnlineMax) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.createUser, rhs.createUser).append(this.maxDate, rhs.maxDate)
            .append(this.createDate, rhs.createDate).append(this.maxNum, rhs.maxNum)
            .append(this.updateDate, rhs.updateDate).append(this.updateUser, rhs.updateUser)
            .append(this.userOnlineMaxId, rhs.userOnlineMaxId).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(405305875, 294506423).appendSuper(super.hashCode())
            .append(this.createUser).append(this.maxDate).append(this.createDate)
            .append(this.maxNum).append(this.updateDate).append(this.updateUser)
            .append(this.userOnlineMaxId).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("maxDate", this.maxDate)
            .append("createDate", this.createDate).append("updateDate", this.updateDate)
            .append("endRow", this.getEndRow()).append("createUser", this.createUser)
            .append("maxNum", this.maxNum).append("updateUser", this.updateUser)
            .append("userOnlineMaxId", this.userOnlineMaxId).append("startRow", this.getStartRow())
            .toString();
    }
    
}
