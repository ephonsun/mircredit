package com.banger.mobile.dao.finance;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.finance.FeArticle;

public interface FeArticleDao {
	
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
	
	Integer selectActicle(Map<String,Object> map);
	
	/**
	 * 搜索所有文章
	 * @param parameters
	 * @param page
	 * @return
	 */
	PageUtil<FeArticle> getArticlePageList(Map<String, Object> parameters, Page page);
	
//	根据栏目id查询文章数量
	public Integer getArticleByColumnId(Map map);
	
	/**
	 * 阅读文章列表 
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
	 * 今日文章总数
	 * @return
	 */
	Integer getToadyFinanceCount(Map<String, Object> parameters);
	
	/**
	 * 今日文章未读 包含必读总数
	 * @return
	 */
	Integer getMustReadInUnReadCount(Map<String, Object> parameters);
	
	/**
	 * 今日文章已读 总数
	 * @return
	 */
	Integer getReadedCount(Map<String, Object> parameters);
	
	/**
	 * 今日文章已读 包含必读总数
	 * @return
	 */
	Integer getMustReadInReadCount(Map<String, Object> parameters);
	
	/**
	 * 今日文章 包含附件总数
	 * @return
	 */
	Integer getAttachmentCount(Map<String, Object> parameters);
	
	/**
	 * 更新参与数，收藏数，评论数
	 * @param map
	 */
	public void updateArticleCount(Map<String, Object> map);
}
