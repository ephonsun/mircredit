package com.banger.mobile.dao;

import java.util.List;

import com.banger.mobile.dao.system.CommProgressDao;
import com.banger.mobile.domain.model.system.CommProgress;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：May 21, 2012 11:39:05 AM
 * 类说明
 */
public class CommProgressTest  extends BaseDaoTestCase{
	
	private CommProgressDao commProgressDao;	

	public void setCommProgressDao(CommProgressDao commProgressDao) {
		this.commProgressDao = commProgressDao;
	}
	
	/**
	 * 测试commProgressDao 注入是否成功
	 * @throws Exception
	 */
	
	public void testDaoNotNull()throws Exception {
	    assertNotNull(commProgressDao);
	}
	
	public void testinsertCommProgpress() throws Exception {
		CommProgress commProgress = new CommProgress();
		commProgress.setCommProgressName("121212");
		commProgress.setCreateUser(1);
		commProgress.setUpdateUser(1);
		commProgress.setIsDel(0);		
		commProgressDao.insertCommProgress(commProgress);		
		assertNotNull(commProgress);
		log.info("id====:" + commProgress.getCommProgressId());
	}
	
//	public void testupdateCommProgress() throws Exception{
//		CommProgress commProgress = new CommProgress();
//		commProgress.setCommProgressId(1);
//		commProgress.setCommProgressName("wumh_ceshi");
//		commProgress.setUpdateUser(2);
//		commProgressDao.updateCommProgress(commProgress);
//	}
//	
//	public void testgetCommProgress() throws Exception{
//		CommProgress commProgress = new CommProgress();
//		commProgress = commProgressDao.getCommProgressById(1);
//		assertEquals("wumh_ceshi", commProgress.getCommProgressName());
//	}
//	
//	public void testdeleteCommProgress() throws Exception {
//		commProgressDao.deleteCommProgress(6);	
//		assertEquals("1", commProgressDao.getCommProgressById(6).getIsDel().toString());
//	}
//
//	public void testgetCommProgressList() throws Exception {
//		List<CommProgress> list = commProgressDao.getCommProgressList();
//		log.info("total count======:"+ list.size());
//		assertNotNull(list);
//	}	
//	
//	public void testgetMaxSortNo() throws Exception{
//		assertEquals("17", commProgressDao.getMaxSortNo().toString());
//	}
//	
//	public void testgetMinSortNo() throws Exception{
//		assertEquals("2", commProgressDao.getMinSortNo().toString());
//	}
//	
//	public void  testmoveUpCommProgress()  throws Exception{
//		CommProgress commProgress = new CommProgress();
//		commProgress.setCommProgressId(50);
//		commProgress.setSortNo(2);
//		commProgressDao.moveUpCommProgress(commProgress);
//		assertEquals("1", commProgressDao.getCommProgressById(50).getSortNo().toString());
//	}
//	
//	public void testmoveDownCommProgress() throws Exception {
//		CommProgress commProgress = new CommProgress();
//		commProgress.setCommProgressId(51);
//		commProgress.setSortNo(1);
//		commProgressDao.moveDownCommProgress(commProgress);
//		assertEquals("2", commProgressDao.getCommProgressById(51).getSortNo().toString());		
//	}
}

