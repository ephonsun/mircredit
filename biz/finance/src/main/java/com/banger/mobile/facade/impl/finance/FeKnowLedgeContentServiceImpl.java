package com.banger.mobile.facade.impl.finance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.finance.FeKnowLedgeContentDao;
import com.banger.mobile.domain.Enum.finance.EnumFinance;
import com.banger.mobile.domain.model.finance.FeKnowledgebaseContent;
import com.banger.mobile.domain.model.finance.FeUserRelation;
import com.banger.mobile.facade.finance.FeKnowLedgeContentService;
import com.banger.mobile.facade.finance.FeUserRelationService;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 6, 2012 1:35:24 PM
 * 类说明
 */
public class FeKnowLedgeContentServiceImpl implements FeKnowLedgeContentService{

	private FeKnowLedgeContentDao    feKnowLedgeContentDao;
	private FeUserRelationService    feUserRelationService;

	/**
	 * 添加收藏内容
	 * @param feKnowledgebaseContent
	 * @return
	 */
	public Integer addKnowLedgeContent(FeKnowledgebaseContent feKnowledgebaseContent){
		feKnowledgebaseContent.setCreateDate(new Date());
		FeUserRelation feUserRelation = new FeUserRelation();
		feUserRelation.setRelationType(0);
		feUserRelation.setIsCollect(1);
		feUserRelation.setFeId(feKnowledgebaseContent.getArticleId());
		feUserRelation.setUserId(feKnowledgebaseContent.getUserId());
		int contentId = feKnowLedgeContentDao.addKnowLedgeContent(feKnowledgebaseContent);
		feUserRelationService.userRelationOperate(feUserRelation, EnumFinance.FE_COLLECT);
		return contentId;
	}


	/**
	 * 获取知识库收藏内容
	 * @param map
	 * @param page
	 * @return
	 */
	public PageUtil<FeKnowledgebaseContent> getKnowLedgeContentList(Map<String, Object> map, Page page){
		List<FeKnowledgebaseContent> list = feKnowLedgeContentDao.getKnowLedgeContentList(map, page);
		if(list==null){
			list = new ArrayList<FeKnowledgebaseContent>();
		}
		for (FeKnowledgebaseContent content:list) {
			if(content.getKnowledgebaseTitle().length()>15){
				content.setKnowledgebaseTitle(content.getKnowledgebaseTitle().substring(0, 15)+"...");
			}
			if(content.getKnowledgebaseNote().length()>80){
				content.setKnowledgebaseNote(content.getKnowledgebaseNote().substring(0, 80)+"...");
			}
		}
		return new PageUtil<FeKnowledgebaseContent>(list,page);
	}

	/**
	 * 删除收藏的内容
	 * @param feKnowledgebaseContent
	 */
	public boolean deleteKnowLedgeContent(FeKnowledgebaseContent feKnowledgebaseContent){
		try {
			feKnowledgebaseContent.setUpdateDate(new Date());
			FeKnowledgebaseContent content2 = this.getKnowContentById(feKnowledgebaseContent.getKnowledgebaseContentId());
			FeUserRelation feUserRelation = new FeUserRelation();
			feUserRelation.setRelationType(0);
			feUserRelation.setIsCollect(0);
			feUserRelation.setFeId(content2.getArticleId());
			feUserRelation.setUserId(content2.getUserId());
			feKnowLedgeContentDao.deleteKnowLedgeContent(feKnowledgebaseContent);
			feUserRelationService.userRelationOperate(feUserRelation, EnumFinance.FE_COLLECT);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 编辑收藏内容
	 * @param feKnowledgebaseContent
	 */
	public boolean editKnowLedgeContent(FeKnowledgebaseContent feKnowledgebaseContent){
		try {
			feKnowledgebaseContent.setUpdateDate(new Date());
			feKnowLedgeContentDao.editKnowLedgeContent(feKnowledgebaseContent);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 通过主键获取FeKnowledgebaseContent实体
	 * @param contentId
	 * @return
	 */
	public FeKnowledgebaseContent getKnowContentById(Integer contentId){
		return feKnowLedgeContentDao.getKnowContentById(contentId);
	}

	public FeKnowLedgeContentDao getFeKnowLedgeContentDao() {
		return feKnowLedgeContentDao;
	}


	public void setFeKnowLedgeContentDao(FeKnowLedgeContentDao feKnowLedgeContentDao) {
		this.feKnowLedgeContentDao = feKnowLedgeContentDao;
	}


	public FeUserRelationService getFeUserRelationService() {
		return feUserRelationService;
	}


	public void setFeUserRelationService(FeUserRelationService feUserRelationService) {
		this.feUserRelationService = feUserRelationService;
	}

}



