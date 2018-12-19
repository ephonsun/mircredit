package com.banger.mobile.service;

import com.banger.mobile.domain.model.finance.FeArticleReply;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.finance.FeArticleReplyService;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 10, 2012 2:21:53 PM
 * 类说明
 */
public class FeArticleReplyServiceTest extends BaseServiceTestCase {
	private FeArticleReplyService feArticleReplyService;

	public FeArticleReplyService getFeArticleReplyService() {
		return feArticleReplyService;
	}

	public void setFeArticleReplyService(FeArticleReplyService feArticleReplyService) {
		this.feArticleReplyService = feArticleReplyService;
	}
	
	public void testServiceNotNull() throws Exception{
		assertNotNull(feArticleReplyService);
	}

	public void testaddDiscessReply() throws Exception{
		FeArticleReply feArticleReply = new FeArticleReply();
		feArticleReply.setCreateUser(8);
		feArticleReply.setDiscessId(1);
		feArticleReply.setReplyContent("啊哈哈哈");
		boolean bool =  feArticleReplyService.addReply(feArticleReply);
		assertEquals(true, bool);
		setComplete();
		endTransaction();
	}

}



