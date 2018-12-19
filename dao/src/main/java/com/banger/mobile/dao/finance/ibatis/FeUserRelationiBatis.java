package com.banger.mobile.dao.finance.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.dao.finance.FeUserRelationDao;
import com.banger.mobile.domain.model.finance.FeFinanceUser;
import com.banger.mobile.domain.model.finance.FeUserRelation;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 8, 2012 10:51:53 AM
 * 类说明
 */
public class FeUserRelationiBatis extends GenericDaoiBatis implements FeUserRelationDao {

	public FeUserRelationiBatis() {
		super(FeUserRelation.class);
		// TODO Auto-generated constructor stub
	}
	public FeUserRelationiBatis(Class persistentClass) {
		super(FeUserRelation.class);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 新增userRelation
	 * @param feUserRelation
	 */
	public void insertUserRelation(FeUserRelation feUserRelation){
		this.getSqlMapClientTemplate().insert("insertUserRelation",feUserRelation);
	}
	
	/**
	 * 更新userRelation
	 * @param feUserRelation
	 */
	public void updateUserRelation(FeUserRelation feUserRelation){
		this.getSqlMapClientTemplate().insert("updateUserRelation",feUserRelation);
	}
	
	/**
	 * 查询
	 * @param map
	 * @return
	 */
	public FeUserRelation getUserRelation(FeUserRelation feUserRelation){
		return (FeUserRelation)this.getSqlMapClientTemplate().queryForObject("getUserRelation", feUserRelation);
	}
	
	/**
	 * 查看已阅读/已收藏客户经理
	 * @param map
	 * @param page
	 * @return
	 */
	public List<FeFinanceUser> getReadAndCollectUser(Map<String, Object> map,Page page){
		return (ArrayList<FeFinanceUser>)this.findQueryPage("getReadAndCollectUser","getReadAndCollectUserCount",map,page);
	}
	
	/**
	 * 查看未阅读客户经理
	 * @param map
	 * @param page
	 * @return
	 */
	public List<FeFinanceUser> getUnReadUser(Map<String, Object> map,Page page){
		return (ArrayList<FeFinanceUser>)this.findQueryPage("getUnReadUser","getUnReadUserCount",map,page);
	}
	
	/**
	 * 判断文章是否被阅读过
	 * @param articleId
	 * @return
	 */
	public boolean isArticleReaded(Integer articleId){
		FeUserRelation feUserRelation = new FeUserRelation();
		feUserRelation.setFeId(articleId);
		feUserRelation.setRelationType(0);
		feUserRelation.setIsRead(1);
		List<FeUserRelation> feList = (ArrayList<FeUserRelation>)this.getSqlMapClientTemplate().queryForList("getReadUserRelation", feUserRelation);
		if(feList==null || feList.size()==0){
			return false;
		}else {
			return true;
		}
	}
}



