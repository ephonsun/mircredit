package com.banger.mobile.service;

import com.banger.mobile.domain.Enum.finance.EnumFinance;
import com.banger.mobile.domain.model.finance.FeUserRelation;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.finance.FeUserRelationService;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 8, 2012 11:27:27 AM
 * 类说明
 */
public class FeUserRelationServiceTest extends BaseServiceTestCase {
	private FeUserRelationService feUserRelationService;

	public FeUserRelationService getFeUserRelationService() {
		return feUserRelationService;
	}

	public void setFeUserRelationService(FeUserRelationService feUserRelationService) {
		this.feUserRelationService = feUserRelationService;
	}
	
	public void testServiceIsNotNull() throws Exception{
		assertNotNull(feUserRelationService);
	}
	
	public void testuserRelationOperate() throws Exception{
		FeUserRelation feUserRelation = new FeUserRelation();
		feUserRelation.setFeId(1);
		feUserRelation.setRelationType(0);
		feUserRelation.setUserId(1);
		feUserRelation.setIsDiscess(1);
		boolean bool = feUserRelationService.userRelationOperate(feUserRelation, EnumFinance.FE_DISCESS);
		assertEquals(true, bool);
		setComplete();
		endTransaction();
	}
	
	public void testisArticleReaded() throws Exception{
		boolean bool = feUserRelationService.isArticleReaded(7);
		assertEquals(false, bool);
	}
}
 


