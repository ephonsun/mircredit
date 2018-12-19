package com.banger.mobile.dao.finance.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.dao.finance.FeKnowLedgeContentDao;
import com.banger.mobile.domain.model.finance.FeKnowledgebaseContent;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 6, 2012 1:20:50 PM
 * 类说明
 */
public class FeKnowLedgeContentiBatis extends GenericDaoiBatis implements FeKnowLedgeContentDao {

	public FeKnowLedgeContentiBatis() {
		super(FeKnowledgebaseContent.class);
		// TODO Auto-generated constructor stub
	}
	public FeKnowLedgeContentiBatis(Class persistentClass) {
		super(FeKnowledgebaseContent.class);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 添加收藏内容
	 * @param feKnowledgebaseContent
	 * @return
	 */
	public Integer addKnowLedgeContent(FeKnowledgebaseContent feKnowledgebaseContent){
		return (Integer)this.getSqlMapClientTemplate().insert("addKnowledgeContent", feKnowledgebaseContent);
	}
	
	/**
	 * 获取知识库收藏内容
	 * @param map
	 * @param page
	 * @return
	 */
	public List<FeKnowledgebaseContent> getKnowLedgeContentList(Map<String, Object> map, Page page){
		return (ArrayList<FeKnowledgebaseContent>)this.findQueryPage(
				"getKnowledgeContent","getKnowledgeContentCount",map,page);
	}
	/**
	 * 删除收藏的内容
	 * @param feKnowledgebaseContent
	 */
	public void deleteKnowLedgeContent(FeKnowledgebaseContent feKnowledgebaseContent){
		this.getSqlMapClientTemplate().update("deleteKnowLedgContent",feKnowledgebaseContent);
	}
	/**
	 * 编辑收藏内容
	 * @param feKnowledgebaseContent
	 */
	public void editKnowLedgeContent(FeKnowledgebaseContent feKnowledgebaseContent){
		this.getSqlMapClientTemplate().update("updateKnowLedgContent",feKnowledgebaseContent);
	}
	
	
	/**
	 * 通过主键获取FeKnowledgebaseContent实体
	 * @param contentId
	 * @return
	 */
	public FeKnowledgebaseContent getKnowContentById(Integer contentId){
		return (FeKnowledgebaseContent)this.getSqlMapClientTemplate().queryForObject("getContentById",contentId);
	}
}



