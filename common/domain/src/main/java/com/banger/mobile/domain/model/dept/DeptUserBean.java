/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-5-17
 */
package com.banger.mobile.domain.model.dept;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author cheny
 * @version $Id: DeptUserBean.java,v 0.1 2012-5-17 下午1:12:35 cheny Exp $
 */
public class DeptUserBean implements Serializable {

    private static final long serialVersionUID = 8221356751207401376L;

    private String            account;                                //用户名
    private String            userName;                               //姓名
    private String            userCode;                               //人员编号
    private String            roleNames;                              //角色名称集合
    private Integer           isActived;                              //启用停用
    private Date              lastLoginDate;                          //最近一次登陆时间
    private String            deptName;                               //归属机构
    private String            state;                                  //状态
    private String            feiState;                               //操作状态启用停用
    private Integer           userId;

    
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Integer getIsActived() {
        return isActived;
    }

    public void setIsActived(Integer isActived) {
        this.isActived = isActived;
    }

    /**
     * @param account
     * @param userName
     * @param roleNames
     * @param isActived
     * @param lastLoginDate
     * @param deptName
     * @param state
     * @param feiState
     * @param userId
     */
    public DeptUserBean(String account, String userName, String roleNames, Integer isActived,
                        Date lastLoginDate, String deptName, String state, String feiState,
                        Integer userId) {
        super();
        this.account = account;
        this.userName = userName;
        this.roleNames = roleNames;
        this.isActived = isActived;
        this.lastLoginDate = lastLoginDate;
        this.deptName = deptName;
        this.state = state;
        this.feiState = feiState;
        this.userId = userId;
    }

    /**
     * 
     */
    public DeptUserBean() {
        super();
    }


    public String getAccount() {
        return account;
    }


    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getFeiState() {
        return feiState;
    }

    public void setFeiState(String feiState) {
        this.feiState = feiState;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof DeptUserBean)) {
            return false;
        }
        DeptUserBean rhs = (DeptUserBean) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.deptName, rhs.deptName).append(this.lastLoginDate, rhs.lastLoginDate)
            .append(this.userId, rhs.userId).append(this.state, rhs.state)
            .append(this.feiState, rhs.feiState).append(this.userName, rhs.userName)
            .append(this.account, rhs.account).append(this.isActived, rhs.isActived)
            .append(this.roleNames, rhs.roleNames).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-33154199, -1606673513).appendSuper(super.hashCode())
            .append(this.deptName).append(this.lastLoginDate).append(this.userId)
            .append(this.state).append(this.feiState).append(this.userName).append(this.account)
            .append(this.isActived).append(this.roleNames).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("state", this.state)
            .append("lastLoginDate", this.lastLoginDate).append("userId", this.userId)
            .append("deptName", this.deptName).append("feiState", this.feiState)
            .append("isActived", this.isActived).append("account", this.account)
            .append("roleNames", this.roleNames).append("userName", this.userName).toString();
    }

   

  

}
