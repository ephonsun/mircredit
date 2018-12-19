/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2012-5-24
 */
package com.banger.mobile.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.dao.customer.AdvancedCustomerDao;
import com.banger.mobile.dao.microTask.TskMicroTaskDao;
import com.banger.mobile.dao.microTask.TskMicroTaskTargetDao;
import com.banger.mobile.domain.model.microTask.TskMicroTaskTarget;

/**
 * @author liyb
 * @version $Id: AdvancedCustomerTest.java,v 0.1 2012-5-24 下午04:39:07 liyb Exp $
 */
public class TskMicroTaskDaoTest extends BaseDaoTestCase {
	private TskMicroTaskDao tskMicroTaskDao;

	public void setTskMicroTaskTargetDao(
			TskMicroTaskDao tskMicroTaskDao) {
		this.tskMicroTaskDao = tskMicroTaskDao;
	}

	/**
	 * 测试dao是否为空
	 * 
	 * @throws Exception
	 */
	public void testDaoNotNull() throws Exception {
		assertNotNull(tskMicroTaskDao);
	}

	public void testGetTaskByTaskId(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userId",22);
		Page page=new Page();
		tskMicroTaskDao.getMyTaskListPage(map,page);
	}
	
//	 public void testGetAdvancedCustomer(){
//	 List<TskMicroTaskTarget> list = new ArrayList<TskMicroTaskTarget>();
//	 TskMicroTaskTarget tskMicroTaskTarget=new TskMicroTaskTarget();
//	 tskMicroTaskTarget.setPhoneNumber("12121211111");
//	 tskMicroTaskTarget.setTaskId(82);
//	 list = tskMicroTaskTargetDao.queryByPhoneNoAndTaskId(tskMicroTaskTarget);
//	 System.out.println(list.size());
//	 }
//	public void testAddBatch() {
//		TskMicroTaskTarget tskMicroTaskTarget = new TskMicroTaskTarget();
//		tskMicroTaskTarget.setPhoneNumber("15168366960");
//		tskMicroTaskTarget.setTaskId(122);
////		tskMicroTaskTarget.setUpdateUser(1);
//        tskMicroTaskTargetDao.getMicroTaskTargetEqualsTarget(tskMicroTaskTarget);
//	}
}
