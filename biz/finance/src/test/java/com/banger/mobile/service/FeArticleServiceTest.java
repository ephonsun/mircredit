package com.banger.mobile.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.Enum.finance.EnumArticle;
import com.banger.mobile.domain.model.finance.FeArticle;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.finance.FeArticleService;

public class FeArticleServiceTest extends BaseServiceTestCase{
	private FeArticleService feArticleService;

	public void setFeArticleService(FeArticleService feArticleService) {
		this.feArticleService = feArticleService;
	}
	
	public void testgetArticlePageList(){
		Map<String, Object> parameters = new HashMap<String, Object>();
		Page page = new Page();
		
		page.setPageSize(20);
		page.setCurrentPage(1);
		
		PageUtil<FeArticle> list = feArticleService.getArticlePageList(parameters, page);
		assertNotNull(list);
	}
	public void atestdeleteActicle(){
		feArticleService.deleteActicle(13);
		setComplete();
		endTransaction();
	}
	public void atestInsertActicle(){
		FeArticle feArticle = new FeArticle();
		feArticle.setIsMastread(0);
		feArticle.setIsPublish(1);
		feArticle.setArticleColumnId(2);
		feArticle.setArticleAddress("123");
		feArticle.setArticleCollectCount(3);
		feArticle.setArticleContent("content");
		feArticle.setArticleDiscessCount(4);
		feArticle.setArticleId(5);
		feArticle.setArticlePartakeCount(6);
		feArticle.setArticleReadCount(7);
		feArticle.setArticleReadtimeBegin(new Date());
		feArticle.setArticleReadtimeEnd(new Date());
		feArticle.setArticleSource("source");
		feArticle.setArticleTitle("articleTitle");
		feArticle.setCreateDate(new Date());
		feArticle.setUpdateDate(new Date());
		feArticle.setCreateUser(2);
		feArticle.setIsDel(0);
		assertNotSame(feArticleService.insertActicle(feArticle),0);
		setComplete();
		endTransaction();
	}
	
	public void atestgetArticle(){
		FeArticle feArticle = feArticleService.getArticle(12);
		assertNotNull(feArticle);
	}
	
	public void atestupdateActicle(){
		FeArticle feArticle = feArticleService.getArticle(12);
		feArticle.setIsMastread(1);
		feArticle.setUpdateUser(2);
		feArticleService.updateActicle(feArticle);
		setComplete();
		endTransaction();
	}
	
	public void testArticleOprelation() throws Exception{
		feArticleService.articleOperation(118, EnumArticle.ART_PARTAKE);
	}
}
