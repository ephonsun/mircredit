package com.banger.mobile.domain.model.communication;

import com.banger.mobile.domain.model.base.communication.BaseCommPartition;

public class CommPartition extends BaseCommPartition {

	/**
	 * 分区
	 */
	private static final long serialVersionUID = 4408247004747619255L;
	
	private String userName;//用户名

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
