package com.banger.mobile.domain.model.system;

import com.banger.mobile.domain.model.base.system.BaseSysTeam;

public class SysTeamInfo extends BaseSysTeam {



	/**
	 * 
	 */
	private static final long serialVersionUID = -5406790657176516506L;

	public SysTeamInfo(){
		super();
	}
	
	private String userName;				//用户名
	private String roleName;			//用户类型
	private String roleId;				//类型id

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
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	
}
