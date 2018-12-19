/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :来电转接实体
 * Author     :zsw
 * Create Date:2012-5-17
 */
package com.banger.mobile.domain.model.user;

/**
 * @author zsw
 * @version
 */
public class SysTalkUserBean extends SysUser {
	private String deptName;			//机构名称
	
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}



	private static final long serialVersionUID = 6360998077267018663L;

}
