package com.banger.mobile.dao.finance;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.domain.model.finance.FeFinanceUser;
import com.banger.mobile.domain.model.finance.FeUserRelation;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 8, 2012 10:47:36 AM
 * 类说明
 */
public interface FeUserRelationDao {

	
	/**
	 * 新增userRelation
	 * @param feUserRelation
	 */
	public void insertUserRelation(FeUserRelation feUserRelation);
	
	/**
	 * 更新userRelation
	 * @param feUserRelation
	 */
	public void updateUserRelation(FeUserRelation feUserRelation);
	
	/**
	 * 查询
	 * @param map
	 * @return
	 */
	public FeUserRelation getUserRelation(FeUserRelation feUserRelation);
	
	/**
	 * 查看已阅读/已收藏客户经理
	 * @param map
	 * @param page
	 * @return
	 */
	public List<FeFinanceUser> getReadAndCollectUser(Map<String, Object> map,Page page);
	
	/**
	 * 查看未阅读客户经理
	 * @param map
	 * @param page
	 * @return
	 */
	public List<FeFinanceUser> getUnReadUser(Map<String, Object> map,Page page);
	
	/**
	 * 判断文章是否被阅读过
	 * @param articleId
	 * @return
	 */
	public boolean isArticleReaded(Integer articleId);
}



