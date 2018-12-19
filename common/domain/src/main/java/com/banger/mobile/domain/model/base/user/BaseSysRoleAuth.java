/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :可管理角色...
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
 * @version $Id: BaseSysRoleAuth.java,v 0.1 2012-8-13 下午1:36:16 yangyong Exp $
 */
@SuppressWarnings("rawtypes")
public class BaseSysRoleAuth extends BaseObject implements Comparable,  Serializable{

  
    private static final long serialVersionUID = 1257381473947282539L;
    private Integer roleAuthId;                      //编号
    private Integer userId;                          //用户编号
    private Integer roleId;                          //部门编号
    private Date createDate;                         //创建时间
    private Date updateDate;                         //更新时间
    private Integer   createUser;                    //创建用户
    private Integer   updateUser;                    //更新用户
    public Integer getRoleAuthId() {
        return roleAuthId;
    }
    public void setRoleAuthId(Integer roleAuthId) {
        this.roleAuthId = roleAuthId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
        BaseSysRoleAuth myClass = (BaseSysRoleAuth) object;
        return new CompareToBuilder().append(this.createUser, myClass.createUser)
            .append(this.roleAuthId, myClass.roleAuthId).append(this.userId, myClass.userId)
            .append(this.createDate, myClass.createDate)
            .append(this.updateDate, myClass.updateDate)
            .append(this.updateUser, myClass.updateUser).append(this.roleId, myClass.roleId)
            .toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseSysRoleAuth)) {
            return false;
        }
        BaseSysRoleAuth rhs = (BaseSysRoleAuth) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.createUser, rhs.createUser).append(this.roleAuthId, rhs.roleAuthId)
            .append(this.userId, rhs.userId).append(this.createDate, rhs.createDate)
            .append(this.updateDate, rhs.updateDate).append(this.updateUser, rhs.updateUser)
            .append(this.roleId, rhs.roleId).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-529026805, -1438454191).appendSuper(super.hashCode())
            .append(this.createUser).append(this.roleAuthId).append(this.userId)
            .append(this.createDate).append(this.updateDate).append(this.updateUser)
            .append(this.roleId).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("userId", this.userId)
            .append("roleAuthId", this.roleAuthId).append("createDate", this.createDate)
            .append("updateDate", this.updateDate).append("roleId", this.roleId)
            .append("endRow", this.getEndRow()).append("createUser", this.createUser)
            .append("updateUser", this.updateUser).append("startRow", this.getStartRow())
            .toString();
    }
    
}
