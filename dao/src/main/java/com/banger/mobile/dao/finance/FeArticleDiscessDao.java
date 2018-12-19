package com.banger.mobile.dao.finance;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.finance.FeArticleDiscess;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 10, 2012 9:15:32 AM
 * 类说明
 */
public interface FeArticleDiscessDao {
	
	
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
	public PageUtil<FeArticleDiscess> getArticleDiscessPage(Map<String, Object> map, Page page);
	
	/**
	 * 支持评论
	 * @param feArticleDiscess
	 * @return
	 */
	public boolean supportDiscess(FeArticleDiscess feArticleDiscess);
	
	
	/**
	 * 更新discess
	 * @param feArticleDiscess
	 * @return
	 */
	public boolean replyDiscess(FeArticleDiscess feArticleDiscess);

}



