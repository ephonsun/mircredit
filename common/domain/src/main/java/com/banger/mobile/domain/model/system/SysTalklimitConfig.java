package com.banger.mobile.domain.model.system;

import com.banger.mobile.domain.model.base.system.BaseSysTalklimitConfig;

public class SysTalklimitConfig extends BaseSysTalklimitConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2672578377856429992L;
	
	private String userName;
	
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

	private String deptName;

	public SysTalklimitConfig() {
		super();
	}
	
	
	
}
