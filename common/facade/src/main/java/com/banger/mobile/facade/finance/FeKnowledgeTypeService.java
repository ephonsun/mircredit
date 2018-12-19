package com.banger.mobile.facade.finance;

import net.sf.json.JSONArray;

import com.banger.mobile.domain.model.finance.FeKnowledgebaseType;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 4, 2012 11:00:07 AM
 * 类说明
 */
public interface FeKnowledgeTypeService {
	
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
	public boolean updateKnowledgeType(FeKnowledgebaseType feKnowledgebaseType);
	
	/**
	 * 上移知识库分类
	 * @param feKnowledgebaseType
	 */
	public boolean moveUpKnowledgeType(FeKnowledgebaseType feKnowledgebaseType);
	
	/**
	 * 下移知识库分类
	 * @param feKnowledgebaseType
	 */
	public boolean moveDownKnowledgeType(FeKnowledgebaseType feKnowledgebaseType);
	
	/**
	 * 删除知识库分类
	 * @param feKnowledgebaseType
	 */
	public boolean deleteKnowledgeType(FeKnowledgebaseType feKnowledgebaseType);
	
	
	/**
	 * 搜索个人的知识库分类树
	 * @param userId
	 * @return
	 */
	public JSONArray getSelfKnowledgeTypeTree(Integer userId);
	
	/**
	 * 通过主键ID获取FeKnowledgebaseType实体
	 * @param id
	 * @return
	 */
	public FeKnowledgebaseType getFeKnowledgebaseTypeById(int id);
	
	/**
	 * 新建用户时插入知识库分类的根节点
	 * @return
	 */
	public boolean insertRootKnowBaseType(Integer userId);

	/**
	 * 验证父ID是否为自身或者自己的子节点
	 * @param feKnowledgebaseType
	 * @return bool
	 */
	public boolean parentIsSelfChildren(FeKnowledgebaseType feKnowledgebaseType);
	
	/**
	 * 判断同级下面是否存在同名的分类
	 * @param feKnowledgebaseType
	 * @return
	 */
	public boolean isExistSameNameInSameLevel(FeKnowledgebaseType feKnowledgebaseType);
}



