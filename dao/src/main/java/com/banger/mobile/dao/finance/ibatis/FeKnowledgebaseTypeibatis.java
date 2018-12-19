package com.banger.mobile.dao.finance.ibatis;

import java.util.Date;
import java.util.Map;

import com.banger.mobile.dao.finance.FeKnowledgebaseTypeDao;
import com.banger.mobile.domain.model.finance.FeKnowledgebaseType;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import java.util.List;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 4, 2012 10:45:28 AM
 * 类说明
 */
public class FeKnowledgebaseTypeibatis extends GenericDaoiBatis implements FeKnowledgebaseTypeDao{
	
	
	public FeKnowledgebaseTypeibatis() {
		super(FeKnowledgebaseType.class);
		// TODO Auto-generated constructor stub
	}
	public FeKnowledgebaseTypeibatis(Class persistentClass) {
		super(FeKnowledgebaseType.class);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 新建知识库分类
	 * @param feKnowledgebaseType
	 * @return 主键
	 */
	public Integer addKnowledgeType(FeKnowledgebaseType feKnowledgebaseType){
		feKnowledgebaseType.setCreateDate(new Date());
		return (Integer)this.getSqlMapClientTemplate().insert("addKnowledgeType",feKnowledgebaseType);
	}
	
	/**
	 * 编辑知识库分类
	 * @param feKnowledgebaseType
	 */
	public void updateKnowledgeType(FeKnowledgebaseType feKnowledgebaseType){
		feKnowledgebaseType.setUpdateDate(new Date());
		this.getSqlMapClientTemplate().update("updateKnowledgeType",feKnowledgebaseType);
	}
	
	/**
	 * 删除知识库分类
	 * @param feKnowledgebaseType
	 */
	public void deleteKnowledgeType(FeKnowledgebaseType feKnowledgebaseType){
		feKnowledgebaseType.setUpdateDate(new Date());
		this.getSqlMapClientTemplate().update("deleteKnowledgeType", feKnowledgebaseType);		
	}
	
	
	/**
	 * 搜索个人的知识库分类列表
	 * @param userId
	 * @return
	 */
	public List<FeKnowledgebaseType> getSelfKnowledgeTypeList(Map<String, Object> map){
		return this.getSqlMapClientTemplate().queryForList("getSelfKnowledgeTypeList", map);
	}	
	
	/**
	 * 通过主键ID获取FeKnowledgebaseType实体
	 * @param id
	 * @return
	 */
	public FeKnowledgebaseType getFeKnowledgebaseTypeById(int id){
		return (FeKnowledgebaseType)this.getSqlMapClientTemplate().queryForObject("getKnowledgeTypeById", id);
	}
	
	
	/**
	 * 获取要交换位置的目标FeKnowledgebaseType实体
	 * @param map
	 * @return
	 */
	public FeKnowledgebaseType getDesKnowledgeType(Map<String, Object> map){
		return (FeKnowledgebaseType)this.getSqlMapClientTemplate().queryForObject("getDesKnowledgeType",map);
	}
	
	
	/**
	 * 根据FeKnowledgebaseType 获取 FeKnowledgebaseType实体
	 * @param feKnowledgebaseType
	 * @return
	 */
	public FeKnowledgebaseType getFeKnowledgebaseType(FeKnowledgebaseType feKnowledgebaseType){
		return (FeKnowledgebaseType)this.getSqlMapClientTemplate().queryForObject("getKnowledgeType",feKnowledgebaseType);
	}
}



