package com.banger.mobile.service;

import java.util.Date;

import net.sf.json.JSONArray;

import com.banger.mobile.domain.model.finance.FeKnowledgebaseType;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.finance.FeKnowledgeTypeService;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Dec 4, 2012 11:08:51 AM
 * 类说明
 */
public class FeKnowledgeTypeServiceTest extends BaseServiceTestCase{
	
	private FeKnowledgeTypeService feKnowledgeTypeService;
	
	public FeKnowledgeTypeService getFeKnowledgeTypeService() {
		return feKnowledgeTypeService;
	}

	public void setFeKnowledgeTypeService(
			FeKnowledgeTypeService feKnowledgeTypeService) {
		this.feKnowledgeTypeService = feKnowledgeTypeService;
	}

	public void testServiceIsNotNull() throws Exception {
		assertNotNull(feKnowledgeTypeService);
	}
	
//	public void testaddKnowledgeType() throws Exception{
//		FeKnowledgebaseType feKnowledgebaseType = new FeKnowledgebaseType();
//		feKnowledgebaseType.setKnowledgebaseTypeName("单元测试");
//		feKnowledgebaseType.setCreateUser(16);
//		feKnowledgebaseType.setParentId(0);
//		feKnowledgebaseType.setIsDel(0);
//		feKnowledgebaseType.setOrderId(1);
//		feKnowledgebaseType.setUserId(16);
//		feKnowledgebaseType.setCreateDate(new Date());
//		Integer index = feKnowledgeTypeService.addKnowledgeType(feKnowledgebaseType);
//		assertNotNull(index);
//	}
//	
//	public void testdeleteKnowledgeType() throws Exception{
//		FeKnowledgebaseType feKnowledgebaseType = new FeKnowledgebaseType();
//		feKnowledgebaseType.setKnowledgebaseTypeId(3);
//		boolean bool = feKnowledgeTypeService.deleteKnowledgeType(feKnowledgebaseType);
//		assertEquals(true, bool);
//	}
//	
//	public void testmoveUpKnowledgeType()throws Exception{
//		FeKnowledgebaseType feKnowledgebaseType = new FeKnowledgebaseType();
//		feKnowledgebaseType.setKnowledgebaseTypeId(3);
//		boolean bool = feKnowledgeTypeService.moveUpKnowledgeType(feKnowledgebaseType);
//		assertEquals(true, bool);
//	}
//	
//	public void testmoveDownKnowledgeType()throws Exception{
//		FeKnowledgebaseType feKnowledgebaseType = new FeKnowledgebaseType();
//		feKnowledgebaseType.setKnowledgebaseTypeId(3);
//		boolean bool = feKnowledgeTypeService.moveDownKnowledgeType(feKnowledgebaseType);
//		assertEquals(true, bool);
//	}
//	
//	public void testupdateKnowledgeType() throws Exception{
//		FeKnowledgebaseType feKnowledgebaseType = new FeKnowledgebaseType();
//		feKnowledgebaseType = feKnowledgeTypeService.getFeKnowledgebaseTypeById(2);
//		feKnowledgebaseType.setUpdateUser(15);
//		feKnowledgebaseType.setKnowledgebaseTypeName("rrrrrrrrrrrrrrr");
//		boolean bool = feKnowledgeTypeService.updateKnowledgeType(feKnowledgebaseType);
//		assertEquals(true, bool);
//	}
//	
//	public void testgetSelfKnowledgeTypeTree()throws Exception {
//		JSONArray typeJson = feKnowledgeTypeService.getSelfKnowledgeTypeTree(15);
//		System.out.println(typeJson);
//	}
//	
//	
//	public void testparentIsSelfChildren() throws Exception{
//		try{
//			FeKnowledgebaseType knowledgebaseType = new FeKnowledgebaseType();
//			knowledgebaseType.setKnowledgebaseTypeId(3);
//			knowledgebaseType.setParentId(4);
//			knowledgebaseType.setUserId(15);
//			boolean bool = feKnowledgeTypeService.parentIsSelfChildren(knowledgebaseType);
//			assertEquals(false, bool);
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	
	
	public void testisExistSameNameInSameLevel() throws Exception{
		FeKnowledgebaseType feKnowledgebaseType = new FeKnowledgebaseType();
		feKnowledgebaseType.setUserId(15);
		feKnowledgebaseType.setKnowledgebaseTypeName("单元测试");
		feKnowledgebaseType.setParentId(2);
		feKnowledgebaseType.setKnowledgebaseTypeId(4);
		boolean bool = feKnowledgeTypeService.isExistSameNameInSameLevel(feKnowledgebaseType);
		assertEquals(false, bool);
	}
}



