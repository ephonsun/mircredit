package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnApproveLimitUser;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-27
 * Time: 下午3:21
 * To change this template use File | Settings | File Templates.
 *
 * 用户审批额度设置表
 */
public class LnApproveLimitUser extends BaseLnApproveLimitUser {
	private String userName; //姓名
	private String account; //用户名
	
	
	
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LnApproveLimitUser other = (LnApproveLimitUser) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	public LnApproveLimitUser(String userName, String account) {
		super();
		this.userName = userName;
		this.account = account;
	}
	public LnApproveLimitUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
