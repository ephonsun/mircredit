package com.banger.mobile.dao.finance;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.domain.model.finance.FeKnowledgebaseContent;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 6, 2012 1:18:55 PM
 * 类说明
 */
public interface FeKnowLedgeContentDao {
	
	/**
	 * 添加收藏内容
	 * @param feKnowledgebaseContent
	 * @return
	 */
	public Integer addKnowLedgeContent(FeKnowledgebaseContent feKnowledgebaseContent);
	
	
	/**
	 * 获取知识库收藏内容
	 * @param map
	 * @param page
	 * @return
	 */
	public List<FeKnowledgebaseContent> getKnowLedgeContentList(Map<String, Object> map, Page page);
	
	/**
	 * 删除收藏的内容
	 * @param feKnowledgebaseContent
	 */
	public void deleteKnowLedgeContent(FeKnowledgebaseContent feKnowledgebaseContent);

	/**
	 * 编辑收藏内容
	 * @param feKnowledgebaseContent
	 */
	public void editKnowLedgeContent(FeKnowledgebaseContent feKnowledgebaseContent);	
	
	/**
	 * 通过主键获取FeKnowledgebaseContent实体
	 * @param contentId
	 * @return
	 */
	public FeKnowledgebaseContent getKnowContentById(Integer contentId);
	
}



