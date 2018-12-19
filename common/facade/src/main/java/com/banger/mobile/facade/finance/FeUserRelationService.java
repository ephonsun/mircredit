package com.banger.mobile.facade.finance;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.finance.EnumFinance;
import com.banger.mobile.domain.model.finance.FeFinanceUser;
import com.banger.mobile.domain.model.finance.FeUserRelation;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 8, 2012 11:10:12 AM
 * 类说明
 */
public interface FeUserRelationService {
	
	/**
	 * 财经要点用户操作
	 * @param feUserRelation
	 * @param enumFinance
	 * @return
	 */
	public boolean userRelationOperate(FeUserRelation feUserRelation,EnumFinance enumFinance);
	
	/**
	 * 用户是否已收藏文章
	 * @param userId
	 * @param articleId
	 * @return
	 */
	public boolean isUserCollectArticle(Integer userId, Integer articleId);
	
	
	/**
	 * 查看已阅读/已收藏客户经理
	 * @param map
	 * @param page
	 * @return
	 */
	public PageUtil<FeFinanceUser> getReadAndCollectUser(Map<String, Object> map,Page page);
	
	/**
	 * 查看未阅读客户经理
	 * @param map
	 * @param page
	 * @return
	 */
	public PageUtil<FeFinanceUser> getUnReadUser(Map<String, Object> map,Page page);
	
	/**
	 * 判断文章是否被阅读过
	 * @param articleId
	 * @return
	 */
	public boolean isArticleReaded(Integer articleId);
}



