package com.banger.mobile.facade.finance;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.finance.FeKnowledgebaseContent;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 6, 2012 1:34:49 PM
 * 类说明
 */
public interface FeKnowLedgeContentService {
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
	public PageUtil<FeKnowledgebaseContent> getKnowLedgeContentList(Map<String, Object> map, Page page);
	
	/**
	 * 删除收藏的内容
	 * @param feKnowledgebaseContent
	 */
	public boolean deleteKnowLedgeContent(FeKnowledgebaseContent feKnowledgebaseContent);

	/**
	 * 编辑收藏内容
	 * @param feKnowledgebaseContent
	 */
	public boolean editKnowLedgeContent(FeKnowledgebaseContent feKnowledgebaseContent);	
	
	/**
	 * 通过主键获取FeKnowledgebaseContent实体
	 * @param contentId
	 * @return
	 */
	public FeKnowledgebaseContent getKnowContentById(Integer contentId);

}



