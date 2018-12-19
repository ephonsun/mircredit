/*
 * banger Inc.
 * Copyright (c) 2009-2013 All Rights Reserved.
 * ToDo       :模块数据授权实体基类
 * Author     :yangy
 * Create Date:2012-6-1
 */
package com.banger.mobile.domain.model.base.specialData;

import com.banger.mobile.domain.model.base.BaseObject;

import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yangy
 * Date: 13-6-18
 * Time: 下午1:25
 * To change this template use File | Settings | File Templates.
 */
public class BaseSpecialDataAuth extends BaseObject implements java.io.Serializable {

    private static final long serialVersionUID = -6569368672213948099L;
    private Integer dataAuthId;        //数据权限ID
    private Integer roleId;            //角色ID
    private Integer dataId;            //数据ID
    private Date createDate;           //创建时间
    private Date updateDate;           //更新时间
    private Integer createUser;        //创建用户
    private Integer updateUser;        //更新用户

    public Integer getDataAuthId() {
        return dataAuthId;
    }

    public void setDataAuthId(Integer dataAuthId) {
        this.dataAuthId = dataAuthId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseSpecialDataAuth that = (BaseSpecialDataAuth) o;

        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (createUser != null ? !createUser.equals(that.createUser) : that.createUser != null) return false;
        if (dataAuthId != null ? !dataAuthId.equals(that.dataAuthId) : that.dataAuthId != null) return false;
        if (dataId != null ? !dataId.equals(that.dataId) : that.dataId != null) return false;
        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;
        if (updateDate != null ? !updateDate.equals(that.updateDate) : that.updateDate != null) return false;
        if (updateUser != null ? !updateUser.equals(that.updateUser) : that.updateUser != null) return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dataAuthId != null ? dataAuthId.hashCode() : 0);
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        result = 31 * result + (dataId != null ? dataId.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (updateDate != null ? updateDate.hashCode() : 0);
        result = 31 * result + (createUser != null ? createUser.hashCode() : 0);
        result = 31 * result + (updateUser != null ? updateUser.hashCode() : 0);
        return result;
    }

    public String toString() {
        return "BaseSpecialDataAuth{" +
                "dataAuthId=" + dataAuthId +
                ", roleId=" + roleId +
                ", dataId=" + dataId +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", createUser=" + createUser +
                ", updateUser=" + updateUser +
                '}';
    }
}
