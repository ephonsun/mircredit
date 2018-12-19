/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :数据权限实体类
 * Author     :cheny
 * Create Date:2012-3-28
 */
package com.banger.mobile.domain.model.base.dataAuth;

import java.sql.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author cheny
 * @version $Id: ,v 0.1 2012-3-28 下午1:07:59 cheny Exp $
 */

public class BaseSysDataAuth extends BaseObject implements java.io.Serializable {

	private static final long serialVersionUID = -2321966424970119130L;
    // Fields

	
    private Integer dataId;                     //ID
	private Integer roleId;                     //角色ID
	private String dataAuthCode;                //数据权限代码
	private Date createDate;                    //创建时间
	private Date updateDate;                    //更新时间
	private Integer createUser;                 //创建用户
	private Integer updateUser;                 //更新用户
	
	
	
    /**
     * 
     */
    public BaseSysDataAuth() {
        super();
    }
    
    
    /**
     * @param dataId
     * @param roleId
     * @param dataAuthCode
     * @param createDate
     * @param updateDate
     * @param createUser
     * @param updateUser
     */
    public BaseSysDataAuth(Integer dataId, Integer roleId, String dataAuthCode, Date createDate,
                           Date updateDate, Integer createUser, Integer updateUser) {
        super();
        this.dataId = dataId;
        this.roleId = roleId;
        this.dataAuthCode = dataAuthCode;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }


    public Integer getDataId() {
        return dataId;
    }
    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public String getDataAuthCode() {
        return dataAuthCode;
    }
    public void setDataAuthCode(String dataAuthCode) {
        this.dataAuthCode = dataAuthCode;
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
        if (!(object instanceof BaseSysDataAuth)) {
            return false;
        }
        BaseSysDataAuth rhs = (BaseSysDataAuth) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.createUser, rhs.createUser).append(this.dataAuthCode, rhs.dataAuthCode)
            .append(this.dataId, rhs.dataId).append(this.createDate, rhs.createDate)
            .append(this.updateDate, rhs.updateDate).append(this.updateUser, rhs.updateUser)
            .append(this.roleId, rhs.roleId).isEquals();
    }


    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1020492141, 1078015609).appendSuper(super.hashCode())
            .append(this.createUser).append(this.dataAuthCode).append(this.dataId)
            .append(this.createDate).append(this.updateDate).append(this.updateUser)
            .append(this.roleId).toHashCode();
    }


    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("dataId", this.dataId)
            .append("createDate", this.createDate).append("dataAuthCode", this.dataAuthCode)
            .append("updateDate", this.updateDate).append("roleId", this.roleId)
            .append("endRow", this.getEndRow()).append("createUser", this.createUser)
            .append("updateUser", this.updateUser).append("startRow", this.getStartRow())
            .toString();
    }


	
}