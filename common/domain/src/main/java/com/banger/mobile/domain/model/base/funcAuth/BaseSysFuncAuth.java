package com.banger.mobile.domain.model.base.funcAuth;

import java.sql.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SysFuncAuth entity. @author MyEclipse Persistence Tools
 */

public class BaseSysFuncAuth extends BaseObject implements java.io.Serializable {

	private static final long serialVersionUID = 3161504437598642671L;
    // Fields


    private Integer funcAuthId;        //功能权限ID
	private Integer roleId;            //角色ID
	private Integer funcId;            //功能ID
	private Date createDate;           //创建时间
	private Date updateDate;           //更新时间
	private Integer createUser;        //创建用户
	private Integer updateUser;        //更新用户
    /**
     * 
     */
    public BaseSysFuncAuth() {
        super();
    }
    /**
     * @param funcAuthId
     * @param roleId
     * @param funcId
     * @param createDate
     * @param updateDate
     * @param createUser
     * @param updateUser
     */
    public BaseSysFuncAuth(Integer funcAuthId, Integer roleId, Integer funcId, Date createDate,
                           Date updateDate, Integer createUser, Integer updateUser) {
        super();
        this.funcAuthId = funcAuthId;
        this.roleId = roleId;
        this.funcId = funcId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }
    public Integer getFuncAuthId() {
        return funcAuthId;
    }
    public void setFuncAuthId(Integer funcAuthId) {
        this.funcAuthId = funcAuthId;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Integer getFuncId() {
        return funcId;
    }
    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
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
        if (!(object instanceof BaseSysFuncAuth)) {
            return false;
        }
        BaseSysFuncAuth rhs = (BaseSysFuncAuth) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.funcId, rhs.funcId).append(this.createUser, rhs.createUser)
            .append(this.createDate, rhs.createDate).append(this.updateDate, rhs.updateDate)
            .append(this.updateUser, rhs.updateUser).append(this.roleId, rhs.roleId)
            .append(this.funcAuthId, rhs.funcAuthId).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(355276191, -1927768375).appendSuper(super.hashCode())
            .append(this.funcId).append(this.createUser).append(this.createDate)
            .append(this.updateDate).append(this.updateUser).append(this.roleId)
            .append(this.funcAuthId).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("createDate", this.createDate)
            .append("updateDate", this.updateDate).append("roleId", this.roleId)
            .append("endRow", this.getEndRow()).append("createUser", this.createUser)
            .append("updateUser", this.updateUser).append("funcId", this.funcId)
            .append("funcAuthId", this.funcAuthId).append("startRow", this.getStartRow())
            .toString();
    }

	
    

}