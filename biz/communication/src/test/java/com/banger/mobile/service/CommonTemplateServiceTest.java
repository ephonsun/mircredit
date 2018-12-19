package com.banger.mobile.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.communication.CommTemplate;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.communication.CommonTemplateService;

public class CommonTemplateServiceTest extends BaseServiceTestCase{
	private CommonTemplateService  commonTemplateService;

	public void setCommonTemplateService(CommonTemplateService commonTemplateService) {
		this.commonTemplateService = commonTemplateService;
	}
	
	public void testgetCommTemplateListBypid(){
		List<CommTemplate> list =	commonTemplateService.getCommTemplateListByPid(1);
		assertTrue(list.size() >0);
	}
	public void atestgetCommTemplateList(){
		Page page = new Page();
		Map<String, Object> map = new HashMap<String, Object>();
		PageUtil<CommTemplate> list = commonTemplateService.getCommTemplateList(map, page);
		assertTrue(list.getItems().size() > 0 );
	}
	
	public void atestgetCommTemplate(){
		CommTemplate c = commonTemplateService.getCommTemplate(2);
		assertNotNull(c);
	}
	public void atestmoveUpTemplate(){
		CommTemplate c = new CommTemplate();
		c.setTemplateId(3);
		c.setUpdateDate(new Timestamp(new Date().getTime()));
		c.setUpdateUser(1);
		commonTemplateService.moveUpTemplate(c);
		setComplete();
		endTransaction();
	}
	public void atestdeleteTemplate(){
		CommTemplate c = new CommTemplate();
		c.setTemplateId(1);
		c.setUpdateDate(new Timestamp(new Date().getTime()));
		c.setUpdateUser(1);
		commonTemplateService.deleteTemplate(c);
		setComplete();
		endTransaction();
	}
	public void atestinsertTemplate(){
		CommTemplate c = new CommTemplate();
		c.setPartitionId(2);
		c.setTemplateState(1);
		c.setCreateDate(new Timestamp(new Date().getTime()));
		c.setCreateUser(1);
		c.setIsBuiltin(0);
		c.setIsDel(0);
		c.setTemplateDescription("2222");
		c.setTemplateName("1113");
		int id = commonTemplateService.insertTemplate(c);
		assertTrue(id > 0);
		setComplete();
		endTransaction();
	}
	
	public void atestupdateTemplate(){
		CommTemplate c = new CommTemplate();
		c.setTemplateId(1);
		c.setPartitionId(3);
		c.setTemplateState(1);
		c.setCreateDate(new Timestamp(new Date().getTime()));
		c.setCreateUser(1);
		c.setIsBuiltin(0);
		c.setIsDel(0);
		c.setTemplateDescription("22221");
		c.setTemplateName("1113");
		c.setUpdateDate(new Timestamp(new Date().getTime()));
		c.setUpdateUser(1);
		Integer id = commonTemplateService.updateTemplate(c);
		assertTrue(id >0);
		setComplete();
		endTransaction();
	}
}
