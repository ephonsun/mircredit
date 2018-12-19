package com.banger.mobile.domain.model.tskContact;

import com.banger.mobile.domain.model.base.tskContact.BaseTskContactExecute;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 24, 2012 11:31:10 AM
 * 类说明
 */
public class TskExecuteUser extends BaseTskContactExecute{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4681924655716042950L;

	private String                    userName;
	private String                    deptName;
	private String                    account;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}



