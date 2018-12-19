package com.banger.mobile.service;

import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.finance.FeArticleDiscess;
import com.banger.mobile.domain.model.finance.FeDiscessWithReply;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.finance.FeArticleDiscessService;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 10, 2012 10:56:04 AM
 * 类说明
 */
public class FeArticleDiscessServiceTest extends BaseServiceTestCase {
	
	private FeArticleDiscessService feArticleDiscessService;

	public FeArticleDiscessService getFeArticleDiscessService() {
		return feArticleDiscessService;
	}

	public void setFeArticleDiscessService(
			FeArticleDiscessService feArticleDiscessService) {
		this.feArticleDiscessService = feArticleDiscessService;
	}
	
	public void testServiceIsNotNull() throws Exception{
		assertNotNull(feArticleDiscessService);
	}
	
	public void testaddDiscess() throws Exception{
		FeArticleDiscess feArticleDiscess = new FeArticleDiscess();
		feArticleDiscess.setArticleId(1);
		feArticleDiscess.setCreateUser(5);
		feArticleDiscess.setDiscessContent("12312312312");
		Integer id =  feArticleDiscessService.addDiscess(feArticleDiscess);		
		assertNotNull(id);
//		setComplete();
//		endTransaction();
	}
	
	public void testsupportDiscess() throws Exception{
		FeArticleDiscess feArticleDiscess = new FeArticleDiscess();
		feArticleDiscess.setUpdateUser(6);
		feArticleDiscess.setDiscessId(1);
		feArticleDiscess.setDiscessSupportCount(1);
		boolean bool =  feArticleDiscessService.supportDiscess(feArticleDiscess);
		assertEquals(true, bool);
//		setComplete();
//		endTransaction();
	}
	
	
	public void testgetArticleDiscessPage() throws Exception{
		Page page = new Page();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("articleId", 1);
		map.put("userId", 8);
		PageUtil<FeDiscessWithReply> list = feArticleDiscessService.getArticleDiscessPage(map, page);
		assertNotNull(list);
	}
}



