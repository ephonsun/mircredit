package com.banger.mobile.facade.finance;

import java.util.Date;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.finance.EnumFinance;
import com.banger.mobile.domain.model.finance.FeArticleDiscess;
import com.banger.mobile.domain.model.finance.FeDiscessWithReply;
import com.banger.mobile.domain.model.finance.FeUserRelation;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 10, 2012 9:27:39 AM
 * 类说明
 */
public interface FeArticleDiscessService {
	
	
	/**
	 * 发表评论
	 * @param feArticleDiscess
	 * @return
	 */
	public Integer addDiscess(FeArticleDiscess feArticleDiscess);
	
	
	/**
	 * 获取评论列表
	 * @param map
	 * @param page
	 * @return
	 */
	public PageUtil<FeDiscessWithReply> getArticleDiscessPage(Map<String, Object> map, Page page);
	
	/**
	 * 支持评论
	 * @param feArticleDiscess updateUser articleId 必需 
	 * @return
	 */
	public boolean supportDiscess(FeArticleDiscess feArticleDiscess);
	
	/**
	 * 增加回复数
	 * @param discessId
	 * @return
	 */
	public boolean replyDiscess(FeArticleDiscess feArticleDiscess);
}



