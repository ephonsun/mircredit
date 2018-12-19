/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :用户角色编号中间表
 * Author     :yangy
 * Create Date:2012-5-21
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
 * @author yangy
 * @version $Id: BaseSysRoleMember.java,v 0.1 2012-5-21 上午11:07:12 Administrator Exp $
 */
public class BaseSysRoleMember extends BaseObject implements Comparable, Serializable{

    private static final long serialVersionUID = 8076861184240146999L;
    private Integer           roleMemberId;                            //角色成员ID
    private Integer           roleId;                                  //角色ID
    private Integer           userId;                                  //成员ID
    private Date          createDate;                              //创建时间
    private Date          updateDate;                              //更新时间
    private Integer           createUser;                              //创建用户
    private Integer           updateUser;                              //更新用户
    public Integer getRoleMemberId() {
        return roleMemberId;
    }
    public void setRoleMemberId(Integer roleMemberId) {
        this.roleMemberId = roleMemberId;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
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
        if (!(object instanceof BaseSysRoleMember)) {
            return false;
        }
        BaseSysRoleMember rhs = (BaseSysRoleMember) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.roleMemberId, rhs.roleMemberId).append(this.createUser, rhs.createUser)
            .append(this.userId, rhs.userId).append(this.createDate, rhs.createDate)
            .append(this.updateDate, rhs.updateDate).append(this.updateUser, rhs.updateUser)
            .append(this.roleId, rhs.roleId).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-319644193, 778421681).appendSuper(super.hashCode())
            .append(this.roleMemberId).append(this.createUser).append(this.userId)
            .append(this.createDate).append(this.updateDate).append(this.updateUser)
            .append(this.roleId).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("userId", this.userId)
            .append("createDate", this.createDate).append("updateDate", this.updateDate)
            .append("roleId", this.roleId).append("endRow", this.getEndRow())
            .append("createUser", this.createUser).append("updateUser", this.updateUser)
            .append("roleMemberId", this.roleMemberId).append("startRow", this.getStartRow())
            .toString();
    }
    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        BaseSysRoleMember myClass = (BaseSysRoleMember) object;
        return new CompareToBuilder().append(this.roleMemberId, myClass.roleMemberId)
            .append(this.createUser, myClass.createUser).append(this.userId, myClass.userId)
            .append(this.createDate, myClass.createDate)
            .append(this.updateDate, myClass.updateDate)
            .append(this.updateUser, myClass.updateUser).append(this.roleId, myClass.roleId)
            .toComparison();
    }
   
  
    

}
