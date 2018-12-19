package com.banger.mobile.domain.model.system;

import com.banger.mobile.domain.model.base.system.BaseSysTeam;

public class SysTeam extends BaseSysTeam {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1801447971779013079L;

	public SysTeam(){
		super();
	}
	
	private String backgroundUserNames;				//后台人员s
	private String clientManageUserNames;			//客户经理s
	private String teamLeaderUserName;				//团队主管
	private String userName;				//用户名

	public String getBackgroundUserNames() {
		return backgroundUserNames;
	}
	public void setBackgroundUserNames(String backgroundUserNames) {
		this.backgroundUserNames = backgroundUserNames;
	}
	public String getClientManageUserNames() {
		return clientManageUserNames;
	}
	public void setClientManageUserNames(String clientManageUserNames) {
		this.clientManageUserNames = clientManageUserNames;
	}
	public String getTeamLeaderUserName() {
		return teamLeaderUserName;
	}
	public void setTeamLeaderUserName(String teamLeaderUserName) {
		this.teamLeaderUserName = teamLeaderUserName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
