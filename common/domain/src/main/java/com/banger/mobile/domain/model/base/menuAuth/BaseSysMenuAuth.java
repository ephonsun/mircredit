package com.banger.mobile.domain.model.base.menuAuth;

import java.sql.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SysMenuAuth entity. @author MyEclipse Persistence Tools
 */

public class BaseSysMenuAuth extends BaseObject implements java.io.Serializable {

	private static final long serialVersionUID = 4193943319131138738L;
    // Fields


    private Integer menuAuthId;           //角色权限ID
	private Integer roleId;               //角色ID
	private Integer menuId;               //菜单ID
	private Date createDate;              //创建时间
    private Date updateDate;              //更新时间
    private Integer createUser;           //创建用户
    private Integer updateUser;           //更新用户
    /**
     * 
     */
    public BaseSysMenuAuth() {
        super();
    }
    /**
     * @param menuAuthId
     * @param roleId
     * @param menuId
     * @param createDate
     * @param updateDate
     * @param createUser
     * @param updateUser
     */
    public BaseSysMenuAuth(Integer menuAuthId, Integer roleId, Integer menuId, Date createDate,
                           Date updateDate, Integer createUser, Integer updateUser) {
        super();
        this.menuAuthId = menuAuthId;
        this.roleId = roleId;
        this.menuId = menuId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }
    public Integer getMenuAuthId() {
        return menuAuthId;
    }
    public void setMenuAuthId(Integer menuAuthId) {
        this.menuAuthId = menuAuthId;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public Integer getMenuId() {
        return menuId;
    }
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
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
        if (!(object instanceof BaseSysMenuAuth)) {
            return false;
        }
        BaseSysMenuAuth rhs = (BaseSysMenuAuth) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.createUser, rhs.createUser).append(this.menuId, rhs.menuId)
            .append(this.menuAuthId, rhs.menuAuthId).append(this.createDate, rhs.createDate)
            .append(this.updateDate, rhs.updateDate).append(this.updateUser, rhs.updateUser)
            .append(this.roleId, rhs.roleId).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(108356719, 1403275687).appendSuper(super.hashCode())
            .append(this.createUser).append(this.menuId).append(this.menuAuthId)
            .append(this.createDate).append(this.updateDate).append(this.updateUser)
            .append(this.roleId).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("createDate", this.createDate)
            .append("updateDate", this.updateDate).append("roleId", this.roleId)
            .append("endRow", this.getEndRow()).append("createUser", this.createUser)
            .append("updateUser", this.updateUser).append("menuId", this.menuId)
            .append("menuAuthId", this.menuAuthId).append("startRow", this.getStartRow())
            .toString();
    }

	
}