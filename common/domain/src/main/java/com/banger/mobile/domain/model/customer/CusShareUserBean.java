/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :部门实体
 * Author     :cheny
 * Create Date:2012-3-28
 */
package com.banger.mobile.domain.model.customer;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;



public class CusShareUserBean extends BaseObject implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6602729427929040079L;
	private Integer userId;                     //人员ID
    private String userName;                	//人员名称
    private String deptName;					//机构名
    private String account;						//登录名
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public CusShareUserBean() {
		super();
	}
	public CusShareUserBean(Integer userId, String userName, String deptName,
			String account) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.deptName = deptName;
		this.account = account;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof CusShareUserBean)) {
			return false;
		}
		CusShareUserBean rhs = (CusShareUserBean) object;
		return new EqualsBuilder().appendSuper(super.equals(object))
				.append(this.deptName, rhs.deptName)
				.append(this.userId, rhs.userId)
				.append(this.account, rhs.account)
				.append(this.userName, rhs.userName).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-317282169, 431772395)
				.appendSuper(super.hashCode()).append(this.deptName)
				.append(this.userId).append(this.account).append(this.userName)
				.toHashCode();
	}
}