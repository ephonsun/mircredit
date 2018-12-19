/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-10-29
 */
package com.banger.mobile.domain.model.user;

import java.io.Serializable;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author cheny
 * @version $Id: UserRoleName.java,v 0.1 2012-10-29 下午1:54:17 cheny Exp $
 */
public class UserRoleName extends BaseObject implements Serializable{

    private static final long serialVersionUID = 8117841444132403000L;
    
    private Integer userId;

    private Integer roleId;

    private String roleName;

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 
     */
    public UserRoleName() {
        super();
    }

    /**
     * @param userId
     * @param roleName
     */
    public UserRoleName(Integer userId, String roleName) {
        super();
        this.userId = userId;
        this.roleName = roleName;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof UserRoleName)) {
            return false;
        }
        UserRoleName rhs = (UserRoleName) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.userId, rhs.userId).append(this.roleName, rhs.roleName).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(2064480353, -1945979335).appendSuper(super.hashCode())
            .append(this.userId).append(this.roleName).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("roleName", this.roleName)
            .append("userId", this.userId).append("endRow", this.getEndRow())
            .append("startRow", this.getStartRow()).toString();
    }
    
    
    
    
}
