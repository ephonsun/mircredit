package com.banger.mobile.dao.finance;

import java.util.List;

import com.banger.mobile.domain.model.finance.FeArticleReply;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 10, 2012 9:15:57 AM
 * 类说明
 */
public interface FeArticleReplyDao {
	
	/**
	 * 发表评论回复
	 * @param feArticleReply
	 * @return
	 */
	public boolean addReply(FeArticleReply feArticleReply);
	
	/**
	 * 通过评论id获取所有的回复列表
	 * @param discessId
	 * @return List<FeArticleReply>
	 */
	public List<FeArticleReply> getReplyListByDiscessId(Integer discessId);

}



