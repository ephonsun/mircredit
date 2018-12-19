package com.banger.mobile.facade.unreadMessage;

import java.util.Map;

public interface MessageNoticeService  {
	/**
	 * 统计消息数量
	 * @param account	要统计的帐号
	 * @param recount	要重新统计的项
	 * @return
	 */
	public Map<String,Integer> getMessageCounts(String account);
	
	/**
	 * 统计消息数量
	 */
	public Map<String, Integer> getMessageCounts(int userId);
}
