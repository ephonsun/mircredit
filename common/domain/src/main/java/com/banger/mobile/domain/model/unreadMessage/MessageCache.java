package com.banger.mobile.domain.model.unreadMessage;

import java.util.Date;

public class MessageCache {
	private Date refreshTime;						//刷新时间
	private Integer messageCount;					//刷新缓存的局部内容
	private Integer userId;
	
	public void setRefreshTime(Integer second)
	{
		this.refreshTime = new Date(new Date().getTime()+second*1000);
	}
	
	public Date getRefreshTime()
	{
		return this.refreshTime;
	}
	
	/**
	 * 是否到了缓存失效时间
	 * @return
	 */
	public boolean isRefreshTime()
	{
		return new Date().getTime()>this.refreshTime.getTime();
	}
	
	public Integer getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(Integer messageCount) {
		this.messageCount = messageCount;
	}
	
}
