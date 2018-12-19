/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-5-17
 */
package com.banger.mobile.domain.model.user;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * @author Administrator
 * @version $Id: SysUserBean.java,v 0.1 2012-5-17 下午3:35:33 Administrator Exp $
 */
public class SysUserBean  implements Comparable, Serializable{

    private static final long serialVersionUID = 2864671287297301074L;
    private Integer   userId;                        //用户编号
    private Integer   deptId;                        //部门ID
    private String    account;                       //帐号
    private String  roleNames;                     //角色名称集合       
    private String    userName;                      //用户姓名
    private Integer   isActived;                     //是否启用(1:启用 0:不启用)
    private String    remark;                        //备注
    private String    state;                         //操作状态  
    private String    deptName;                      //部门名称   
    private String    deptParentId;                  //部门父ID   
    
    
    public String getDeptParentId() {
        return deptParentId;
    }
    public void setDeptParentId(String deptParentId) {
        this.deptParentId = deptParentId;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
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
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    
    public String getRoleNames() {
        return roleNames;
    }
    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Integer getIsActived() {
        return isActived;
    }
    public void setIsActived(Integer isActived) {
        this.isActived = isActived;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public SysUserBean(Integer userId, Integer deptId, String account, String roleNames,
                       String userName, Integer isActived, String remark) {
        super();
        this.userId = userId;
        this.deptId = deptId;
        this.account = account;
        this.roleNames = roleNames;
        this.userName = userName;
        this.isActived = isActived;
        this.remark = remark;
    }
    public SysUserBean() {
        super();
    }
    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        SysUserBean myClass = (SysUserBean) object;
        return new CompareToBuilder().append(this.deptName, myClass.deptName)
            .append(this.remark, myClass.remark).append(this.userId, myClass.userId)
            .append(this.state, myClass.state).append(this.userName, myClass.userName)
            .append(this.account, myClass.account).append(this.isActived, myClass.isActived)
            .append(this.deptId, myClass.deptId).append(this.roleNames, myClass.roleNames)
            .toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof SysUserBean)) {
            return false;
        }
        SysUserBean rhs = (SysUserBean) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.deptName, rhs.deptName).append(this.remark, rhs.remark)
            .append(this.userId, rhs.userId).append(this.state, rhs.state)
            .append(this.userName, rhs.userName).append(this.account, rhs.account)
            .append(this.isActived, rhs.isActived).append(this.deptId, rhs.deptId)
            .append(this.roleNames, rhs.roleNames).isEquals();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("remark", this.remark).append("state", this.state)
            .append("userId", this.userId).append("deptName", this.deptName)
            .append("deptId", this.deptId).append("isActived", this.isActived)
            .append("account", this.account).append("roleNames", this.roleNames)
            .append("userName", this.userName).toString();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1047805575, -1563105375).appendSuper(super.hashCode())
            .append(this.deptName).append(this.remark).append(this.userId).append(this.state)
            .append(this.userName).append(this.account).append(this.isActived).append(this.deptId)
            .append(this.roleNames).toHashCode();
    }
    
    
    

}
