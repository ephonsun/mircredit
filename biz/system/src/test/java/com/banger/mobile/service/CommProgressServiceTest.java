package com.banger.mobile.service;

import java.util.List;

import com.banger.mobile.domain.model.system.CommProgress;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.system.CommProgressService;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：May 21, 2012 4:58:00 PM
 * 类说明
 */
public class CommProgressServiceTest extends BaseServiceTestCase {
	
	private CommProgressService commProgressService;

	public void setCommProgressService(CommProgressService commProgressService) {
		this.commProgressService = commProgressService;
	}
	
	public void testDaoNotNull()throws Exception {
	    assertNotNull(commProgressService);
	}
	
//	public void testinsertCommProgpress() throws Exception {
//		CommProgress commProgress = new CommProgress();
//		commProgress.setCommProgressName("test1");
//		commProgress.setCreateUser(1);
//		commProgress.setUpdateUser(1);
//		commProgress.setIsDel(0);
//		commProgress.setSortNo(2);	
//		commProgressService.insertCommProgress(commProgress);		
//      assertNotNull(commProgress);
//      log.info("id====:" + commProgress.getCommProgressId());
//	}
//	
//	public void testupdateCommProgress() throws Exception{
//		CommProgress commProgress = new CommProgress();
//		commProgress.setCommProgressId(1);
//		commProgress.setCommProgressName("wumh_ceshi");
//		commProgress.setUpdateUser(2);
//		commProgressService.updateCommProgress(commProgress);
//	}
//	
//	public void testgetCommProgress() throws Exception{
//		CommProgress commProgress = new CommProgress();
//		commProgress = commProgressService.getCommProgressById(1);
//		assertEquals("wumh_ceshi", commProgress.getCommProgressName());
//	}
//	
//	public void testdeleteCommProgress() throws Exception {
//		CommProgress commProgress = new CommProgress();
//		commProgress.setCommProgressId(6);
//		commProgress.setUpdateUser(1);
//		commProgressService.deleteCommProgress(commProgress);	
//		assertEquals("1", commProgressService.getCommProgressById(6).getIsDel().toString());
//	}
//
//	public void testgetCommProgressList() throws Exception {
//		List<CommProgress> list = commProgressService.getCommProgressList();
//		log.info("total count======:"+ list.size());
//		assertNotNull(list);
//	}	

}



