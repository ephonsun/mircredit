package com.banger.mobile.facade.impl.finance;

import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.finance.FeArticleDao;
import com.banger.mobile.domain.Enum.finance.EnumArticle;
import com.banger.mobile.domain.model.finance.FeArticle;
import com.banger.mobile.facade.finance.FeArticleService;
import com.banger.mobile.util.StringUtil;

public class FeArticleServiceImpl implements FeArticleService {

	private FeArticleDao feArticleDao;

	public Integer insertActicle(FeArticle feArticle) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", feArticle.getArticleTitle());
		if (feArticleDao.selectActicle(map) > 0) {
			return -2;
		} else {
			return feArticleDao.insertActicle(feArticle);
		}
	}

	public Integer updateActicle(FeArticle feArticle) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", feArticle.getArticleTitle());
		map.put("articleId", feArticle.getArticleId());
		if (feArticleDao.selectActicle(map) > 0) {
			return -2;
		} else {
			return feArticleDao.updateActicle(feArticle);
		}
	}
	
	

	public Integer deleteActicle(Integer acticleId) {
		return feArticleDao.deleteActicle(acticleId);
	}

	public Integer publishActicle(String acticleIds) {
		return feArticleDao.publishActicle(acticleIds);
	}

	public Integer classifyActicles(String ids, String columnId) {
		return feArticleDao.classifyActicles(ids, columnId);
	}

	public FeArticle getArticle(Integer acticleId) {
		return feArticleDao.getArticle(acticleId);
	}

	public PageUtil<FeArticle> getArticlePageList(
			Map<String, Object> parameters, Page page) {
		return feArticleDao.getArticlePageList(parameters, page);
	}

	public FeArticleDao getFeArticleDao() {
		return feArticleDao;
	}

	public void setFeArticleDao(FeArticleDao feArticleDao) {
		this.feArticleDao = feArticleDao;
	}

    public Integer getArticleNum(Map map) {
        Integer count = feArticleDao.getArticleByColumnId(map);
        return count;
    }

	public PageUtil<FeArticle> showFinanceMainPage(
			Map<String, Object> parameters, Page page) {
		PageUtil<FeArticle> pageUtils = feArticleDao.showFinanceMainPage(
				parameters, page);
		removeHTML(pageUtils);
		return pageUtils;
	}

	public Map<String, Integer> getTodayFinanceCount(Map<String, Object> parameters) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Integer todayCount = feArticleDao.getToadyFinanceCount(parameters);
		map.put("todayCount", todayCount);
		Integer readedCount = feArticleDao.getReadedCount(parameters);
		map.put("readedCount", readedCount);
		map.put("unReadedCount", todayCount - readedCount);
		map.put("mustReadInUnReadCount",
				feArticleDao.getMustReadInUnReadCount(parameters));
		map.put("mustReadInReadCount",
				feArticleDao.getMustReadInReadCount(parameters));
		map.put("attachmentCount", feArticleDao.getAttachmentCount(parameters));
		return map;
	}
	
	/**
	 * 文章的操作，更新文章表计数
	 * @param articleId
	 * @param enumArticle
	 * @return
	 */
	public void articleOperation(Integer articleId,EnumArticle enumArticle){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("articleId", articleId);
		switch (enumArticle) {
		case ART_ADD_COLLECT:
			map.put("addCollect", "1");
			break;
		case ART_DEL_COLLECT:
			map.put("delCollect", "1");
			break;
		case ART_DISCESS:
			map.put("discess", "1");
			break;
		case ART_PARTAKE:
			map.put("partake", "1");
			break;
		default:
			break;
		}
		feArticleDao.updateArticleCount(map);
	}
	public PageUtil<FeArticle> showInChargeOfFinanceMainPage(
			Map<String, Object> parameters, Page page) {
		PageUtil<FeArticle> pageUtils = feArticleDao.showInChargeOfFinanceMainPage(
				parameters, page);
		removeHTML(pageUtils);
		return pageUtils;
	}
	
	private void removeHTML(PageUtil<FeArticle> pageUtils){
		for (FeArticle feArticle : pageUtils.getItems()) {
			String content = StringUtil.removeHTML(feArticle
					.getArticleContent());
			if(content.length() > 150){
				content = content.substring(0, 150)+"...";
			}
			feArticle.setArticleContent(content);
		}
	}

}
