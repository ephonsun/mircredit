package com.banger.mobile.facade.impl.finance;

import java.util.Date;
import java.util.List;

import com.banger.mobile.dao.finance.FeArticleReplyDao;
import com.banger.mobile.domain.Enum.finance.EnumFinance;
import com.banger.mobile.domain.model.finance.FeArticleDiscess;
import com.banger.mobile.domain.model.finance.FeArticleReply;
import com.banger.mobile.domain.model.finance.FeUserRelation;
import com.banger.mobile.facade.finance.FeArticleDiscessService;
import com.banger.mobile.facade.finance.FeArticleReplyService;
import com.banger.mobile.facade.finance.FeUserRelationService;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 10, 2012 9:28:56 AM
 * 类说明
 */
public class FeArticleReplyServiceImpl implements FeArticleReplyService{
	
	private FeArticleReplyDao          feArticleReplyDao;
	private FeUserRelationService      feUserRelationService;
	private FeArticleDiscessService    feArticleDiscessService;
	
	/**
	 * 发表评论回复
	 * @param feArticleReply
	 * @return
	 */
	public boolean addReply(FeArticleReply feArticleReply){
		try{
			feArticleReply.setCreateDate(new Date());
			FeUserRelation feUserRelation = new FeUserRelation();
			FeArticleDiscess feArticleDiscess = new FeArticleDiscess();
			feUserRelation.setIsReply(1);
			feUserRelation.setUserId(feArticleReply.getCreateUser());
			feUserRelation.setRelationType(1);
			feUserRelation.setFeId(feArticleReply.getDiscessId());		
			feArticleDiscess.setDiscessId(feArticleReply.getDiscessId());
			feArticleDiscess.setUpdateUser(feArticleReply.getCreateUser());
			feArticleReplyDao.addReply(feArticleReply);
			feUserRelationService.userRelationOperate(feUserRelation, EnumFinance.FE_REPLY);
			feArticleDiscessService.replyDiscess(feArticleDiscess);
			return true;
		}catch (Exception e) {
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
		return feArticleReplyDao.getReplyListByDiscessId(discessId);
	}

	public FeArticleReplyDao getFeArticleReplyDao() {
		return feArticleReplyDao;
	}

	public void setFeArticleReplyDao(FeArticleReplyDao feArticleReplyDao) {
		this.feArticleReplyDao = feArticleReplyDao;
	}

	public FeUserRelationService getFeUserRelationService() {
		return feUserRelationService;
	}

	public void setFeUserRelationService(FeUserRelationService feUserRelationService) {
		this.feUserRelationService = feUserRelationService;
	}

	public FeArticleDiscessService getFeArticleDiscessService() {
		return feArticleDiscessService;
	}

	public void setFeArticleDiscessService(
			FeArticleDiscessService feArticleDiscessService) {
		this.feArticleDiscessService = feArticleDiscessService;
	}
}



