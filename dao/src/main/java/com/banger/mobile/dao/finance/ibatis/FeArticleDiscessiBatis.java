package com.banger.mobile.dao.finance.ibatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.finance.FeArticleDiscessDao;
import com.banger.mobile.domain.model.finance.FeArticleDiscess;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 10, 2012 9:23:02 AM
 * 类说明
 */
public class FeArticleDiscessiBatis extends GenericDaoiBatis implements FeArticleDiscessDao{

	public FeArticleDiscessiBatis() {
		super(FeArticleDiscess.class);
		// TODO Auto-generated constructor stub
	}

	public FeArticleDiscessiBatis(Class persistentClass) {
		super(FeArticleDiscess.class);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 发表评论
	 * @param feArticleDiscess
	 * @return
	 */
	public Integer addDiscess(FeArticleDiscess feArticleDiscess){
		return (Integer)this.getSqlMapClientTemplate().insert("addArticleDiscess",feArticleDiscess);
	}


	/**
	 * 获取评论列表
	 * @param map
	 * @param page
	 * @return
	 */
	public PageUtil<FeArticleDiscess> getArticleDiscessPage(Map<String, Object> map, Page page){
		ArrayList<FeArticleDiscess> list = (ArrayList<FeArticleDiscess>) this.findQueryPage(
				"getArticleDiscessMap", "getArticleDiscessMapCount", map, page);
		if(list == null){
			list = new ArrayList<FeArticleDiscess>();
		}
		return new PageUtil<FeArticleDiscess>(list,page);
	}

	/**
	 * 支持评论
	 * @param feArticleDiscess
	 * @return
	 */
	public boolean supportDiscess(FeArticleDiscess feArticleDiscess){
		feArticleDiscess.setDiscessSupportCount(1);
		return this.updateDiscess(feArticleDiscess);
	}


	/**
	 * 更新discess
	 * @param feArticleDiscess
	 * @return
	 */
	public boolean replyDiscess(FeArticleDiscess feArticleDiscess){
		feArticleDiscess.setDiscessReplyCount(1);
		return updateDiscess(feArticleDiscess);
	}

	private boolean updateDiscess(FeArticleDiscess feArticleDiscess){
		try {
			feArticleDiscess.setUpdateDate(new Date());
			this.getSqlMapClientTemplate().update("updateArticleDiscess",feArticleDiscess);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}



