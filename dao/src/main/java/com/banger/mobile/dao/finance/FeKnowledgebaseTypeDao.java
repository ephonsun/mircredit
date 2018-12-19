package com.banger.mobile.dao.finance;

import java.util.Map;

import com.banger.mobile.domain.model.finance.FeKnowledgebaseType;
import java.util.List;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 4, 2012 10:42:11 AM
 * 类说明
 */
public interface FeKnowledgebaseTypeDao {
	
	/**
	 * 新建知识库分类
	 * @param feKnowledgebaseType
	 * @return 主键
	 */
	public Integer addKnowledgeType(FeKnowledgebaseType feKnowledgebaseType);
	
	/**
	 * 编辑知识库分类
	 * @param feKnowledgebaseType
	 */
	public void updateKnowledgeType(FeKnowledgebaseType feKnowledgebaseType);
	
	/**
	 * 删除知识库分类
	 * @param feKnowledgebaseType
	 */
	public void deleteKnowledgeType(FeKnowledgebaseType feKnowledgebaseType);
	
	
	/**
	 * 搜索个人的知识库分类列表
	 * @param userId
	 * @return
	 */
	public List<FeKnowledgebaseType> getSelfKnowledgeTypeList(Map<String, Object> map);
	/**
	 * 通过主键ID获取FeKnowledgebaseType实体
	 * @param id
	 * @return
	 */
	public FeKnowledgebaseType getFeKnowledgebaseTypeById(int id);
	
	/**
	 * 获取要交换位置的目标FeKnowledgebaseType实体
	 * @param map
	 * @return
	 */
	public FeKnowledgebaseType getDesKnowledgeType(Map<String, Object> map);
	
	/**
	 * 根据FeKnowledgebaseType 获取 FeKnowledgebaseType实体
	 * @param feKnowledgebaseType
	 * @return
	 */
	public FeKnowledgebaseType getFeKnowledgebaseType(FeKnowledgebaseType feKnowledgebaseType);
}



