package com.banger.mobile.dao.finance.ibatis;

import java.util.List;

import com.banger.mobile.dao.finance.FeArticleReplyDao;
import com.banger.mobile.domain.model.finance.FeArticleReply;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 10, 2012 9:19:28 AM
 * 类说明
 */
public class FeArticleReplyiBatis extends GenericDaoiBatis implements FeArticleReplyDao{

	public FeArticleReplyiBatis() {
		super(FeArticleReply.class);
		// TODO Auto-generated constructor stub
	}
	
	public FeArticleReplyiBatis(Class persistentClass) {
		super(FeArticleReply.class);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 发表评论回复
	 * @param feArticleReply
	 * @return
	 */
	public boolean addReply(FeArticleReply feArticleReply){
		try {
			this.getSqlMapClientTemplate().insert("addDiscessReply",feArticleReply);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}
	
	/**
	 * 通过评论id获取所有的回复列表
	 * @param discessId
	 * @return List<FeArticleReply>
	 */
	public List<FeArticleReply> getReplyListByDiscessId(Integer discessId){
		return this.getSqlMapClientTemplate().queryForList("getDiscessReply",discessId);
	}

}



