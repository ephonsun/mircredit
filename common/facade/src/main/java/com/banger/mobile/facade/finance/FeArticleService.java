package com.banger.mobile.facade.finance;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.finance.EnumArticle;
import com.banger.mobile.domain.model.finance.FeArticle;

public interface FeArticleService {
	
	
	
	/**
	 * 新建文章
	 * @param feArticle
	 * @return
	 */
	Integer insertActicle(FeArticle feArticle);
	
	/**
	 * 编辑文章
	 * @param feArticle
	 * @return
	 */
	Integer updateActicle(FeArticle feArticle);
	
	/**
	 * 删除文章
	 * @param acticleId
	 * @return
	 */
	Integer deleteActicle(Integer acticleId);
	
	/**
	 * 发布文章
	 * @param acticleId
	 * @return
	 */
	Integer publishActicle(String acticleIds);
	
	
	/**
	 * 文章批量分类
	 * @param list
	 * @return
	 */
	Integer classifyActicles(String ids,String columnId);
	
	/**
	 * 获取文章
	 * @param acticleId
	 * @return
	 */
	FeArticle getArticle(Integer acticleId);
	
	/**
	 * 搜索所有文章
	 * @param parameters
	 * @param page
	 * @return
	 */
	PageUtil<FeArticle> getArticlePageList(Map<String, Object> parameters, Page page);
	
	/**
	 * 根据栏目搜索文章数量
	 */
	public Integer getArticleNum(Map map);
	/**
	 * 客户经理阅读财经列表
	 * @param parameters
	 * @param page
	 * @return
	 */
	PageUtil<FeArticle> showFinanceMainPage(Map<String, Object> parameters, Page page);
	
	/**
	 * 业务主管阅读财经列表
	 * @param parameters
	 * @param page
	 * @return
	 */
	PageUtil<FeArticle> showInChargeOfFinanceMainPage(Map<String, Object> parameters, Page page);
	
	/**
	 * 今日财经数目
	 * @param parameters
	 * @return
	 */
	Map<String,Integer> getTodayFinanceCount(Map<String, Object> parameters);
	
	/**
	 * 文章的操作，更新文章表计数
	 * @param articleId
	 * @param enumArticle
	 * @return
	 */
	public void articleOperation(Integer articleId,EnumArticle enumArticle);
}
