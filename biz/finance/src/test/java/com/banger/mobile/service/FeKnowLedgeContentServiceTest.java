package com.banger.mobile.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.finance.FeKnowledgebaseContent;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.finance.FeKnowLedgeContentService;
import com.banger.mobile.util.DateUtil;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 6, 2012 1:38:43 PM
 * 类说明
 */
public class FeKnowLedgeContentServiceTest extends BaseServiceTestCase{
	private FeKnowLedgeContentService feKnowLedgeContentService;

	public FeKnowLedgeContentService getFeKnowLedgeContentService() {
		return feKnowLedgeContentService;
	}

	public void setFeKnowLedgeContentService(
			FeKnowLedgeContentService feKnowLedgeContentService) {
		this.feKnowLedgeContentService = feKnowLedgeContentService;
	}
	
	public void testServiceIsNotNull() throws Exception{
		assertNotNull(feKnowLedgeContentService);
	}
	
	
	public void testaddKnowLedgeContent() throws Exception{
		FeKnowledgebaseContent content = new FeKnowledgebaseContent();
		content.setArticleId(1);
		content.setUserId(8);
		content.setIsDel(0);
		content.setKnowledgebaseNote("11111");
		content.setKnowledgebaseTitle("2222222222222");
		content.setCreateDate(new Date());
		content.setCreateUser(8);
		content.setKnowledgebaseTypeId(2);
		int i = feKnowLedgeContentService.addKnowLedgeContent(content);
		assertNotNull(i);	
		setComplete();
		endTransaction();
	}
	
	public void testgetKnowLedgeContentList() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		Page page = new Page();
		//map.put("typeId", 2);
		map.put("userId", 15);
		map.put("startDate", "2012-12-06");
		map.put("endDate", "2012-12-06 "+"23:59:59");
		//map.put("title", "u");
		//map.put("note", "元");
		PageUtil<FeKnowledgebaseContent> list =  feKnowLedgeContentService.getKnowLedgeContentList(map, page);
		assertNotNull(list.getItems().size());		
	}
	
	
	public void testdeleteKnowLedgeContent() throws Exception{
		FeKnowledgebaseContent content = new FeKnowledgebaseContent();
		content.setKnowledgebaseContentId(51);
		content.setUpdateDate(new Date());
		content.setUpdateUser(8);
		boolean bool = feKnowLedgeContentService.deleteKnowLedgeContent(content);
		assertEquals(true, bool);
		setComplete();
		endTransaction();
	}
	
	public void testeditKnowLedgeContent() throws Exception{
		FeKnowledgebaseContent content = new FeKnowledgebaseContent();
		content.setKnowledgebaseContentId(1);
		content.setUpdateDate(new Date());
		content.setUpdateUser(15);
		content.setKnowledgebaseNote("eeeeeeeeeeee");
		content.setKnowledgebaseTitle("wwwwwwww");
		content.setKnowledgebaseTypeId(3);
		boolean bool = feKnowLedgeContentService.editKnowLedgeContent(content);
		assertEquals(true, bool);		
	}
}



