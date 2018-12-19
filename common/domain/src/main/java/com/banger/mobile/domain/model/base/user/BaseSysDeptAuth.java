/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :可管理部门...
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
 * @version $Id: BaseSysDeptAuth.java,v 0.1 2012-8-13 下午1:30:33 yangyong Exp $
 */
@SuppressWarnings("rawtypes")
public class BaseSysDeptAuth extends BaseObject implements Comparable,  Serializable{

    private static final long serialVersionUID = 6667445125997408759L;
    private Integer deptAuthId;                      //编号
    private Integer roleId;                          //角色编号
    private Integer userId;                          //用户编号
    private Integer deptId;                          //部门编号
    private Date createDate;                         //创建时间
    private Date updateDate;                         //更新时间
    private Integer   createUser;                    //创建用户
    private Integer   updateUser;                    //更新用户
    
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Integer getDeptAuthId() {
        return deptAuthId;
    }
    public void setDeptAuthId(Integer deptAuthId) {
        this.deptAuthId = deptAuthId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getDeptId() {
        return deptId;
    }
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
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
        BaseSysDeptAuth myClass = (BaseSysDeptAuth) object;
        return new CompareToBuilder().append(this.createUser, myClass.createUser)
            .append(this.userId, myClass.userId).append(this.deptAuthId, myClass.deptAuthId)
            .append(this.createDate, myClass.createDate).append(this.deptId, myClass.deptId)
            .append(this.updateDate, myClass.updateDate)
            .append(this.updateUser, myClass.updateUser).toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseSysDeptAuth)) {
            return false;
        }
        BaseSysDeptAuth rhs = (BaseSysDeptAuth) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.createUser, rhs.createUser).append(this.userId, rhs.userId)
            .append(this.deptAuthId, rhs.deptAuthId).append(this.createDate, rhs.createDate)
            .append(this.deptId, rhs.deptId).append(this.updateDate, rhs.updateDate)
            .append(this.updateUser, rhs.updateUser).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-287923939, -1528112411).appendSuper(super.hashCode())
            .append(this.createUser).append(this.userId).append(this.deptAuthId)
            .append(this.createDate).append(this.deptId).append(this.updateDate)
            .append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("userId", this.userId)
            .append("deptId", this.deptId).append("createDate", this.createDate)
            .append("updateDate", this.updateDate).append("endRow", this.getEndRow())
            .append("createUser", this.createUser).append("updateUser", this.updateUser)
            .append("deptAuthId", this.deptAuthId).append("startRow", this.getStartRow())
            .toString();
    }
    

}
