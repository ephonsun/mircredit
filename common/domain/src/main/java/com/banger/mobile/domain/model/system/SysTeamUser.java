package com.banger.mobile.domain.model.system;

import com.banger.mobile.domain.model.base.system.BaseSysTeamUser;

public class SysTeamUser extends BaseSysTeamUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5750830748617211649L;

	public SysTeamUser(){
		super();
	}
	
	private String userName;		//姓名名称
	private String roleName;		//角色名称
	private String deptName;		//机构名称

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}	
}
