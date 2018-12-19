/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :SysRole角色实体基类
 * Author     :liyb
 * Create Date:2012-5-22
 */
package com.banger.mobile.domain.model.base.role;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author liyb
 * @version $Id: BaseSysRole.java,v 0.1 2012-5-22 下午01:16:24 liyb Exp $
 */
public class BaseSysRole extends BaseObject {

    private static final long serialVersionUID = -7829776945673981559L;
    private Integer           roleId;                                  //角色ID
    private Integer           roleTypeId;                                //角色类型
    private String            roleName;                                //角色名称
    private String            remark;                                  //备注
    private Date              createDate;                              //创建时间
    private Date              updateDate;                              //更新时间
    private Integer           createUser;                              //创建用户
    private Integer           updateUser;                              //更新用户
    private Integer           isDel;                                   //是否删除    0：未删除  1：已删除
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Integer getRoleTypeId() {
        return roleTypeId;
    }
    public void setRoleTypeId(Integer roleTypeId) {
        this.roleTypeId = roleTypeId;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
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
    public Integer getIsDel() {
        return isDel;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseSysRole)) {
            return false;
        }
        BaseSysRole rhs = (BaseSysRole) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.isDel, rhs.isDel)
            .append(this.createUser, rhs.createUser).append(this.remark, rhs.remark).append(
                this.roleTypeId, rhs.roleTypeId).append(this.roleName, rhs.roleName).append(
                this.createDate, rhs.createDate).append(this.updateDate, rhs.updateDate).append(
                this.updateUser, rhs.updateUser).append(this.roleId, rhs.roleId).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-176237215, -1490059547).appendSuper(super.hashCode()).append(
            this.isDel).append(this.createUser).append(this.remark).append(this.roleTypeId).append(
            this.roleName).append(this.createDate).append(this.updateDate).append(this.updateUser)
            .append(this.roleId).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("remark", this.remark).append("roleName",
            this.roleName).append("createDate", this.createDate).append("roleTypeId", this.roleTypeId)
            .append("updateDate", this.updateDate).append("roleId", this.roleId).append("endRow",
                this.getEndRow()).append("createUser", this.createUser).append("updateUser",
                this.updateUser).append("isDel", this.isDel).append("startRow", this.getStartRow())
            .toString();
    }
    
    
}
