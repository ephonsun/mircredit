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
import com.banger.mobile.dao.microTask.TskMicroTaskExecuteDao;
import com.banger.mobile.dao.microTask.TskMicroTaskTargetDao;
import com.banger.mobile.domain.model.microTask.TskMicroTaskTarget;

/**
 * @author liyb
 * @version $Id: AdvancedCustomerTest.java,v 0.1 2012-5-24 下午04:39:07 liyb Exp $
 */
public class TskMicroTaskExecuteDaoTest extends BaseDaoTestCase {
	private TskMicroTaskExecuteDao tskMicroTaskExecuteDao;

	public void setTskMicroTaskExecuteDao(
			TskMicroTaskExecuteDao tskMicroTaskExecuteDao) {
		this.tskMicroTaskExecuteDao = tskMicroTaskExecuteDao;
	}

	/**
	 * 测试dao是否为空
	 * 
	 * @throws Exception
	 */
	public void testDaoNotNull() throws Exception {
		assertNotNull(tskMicroTaskExecuteDao);
	}

	public void testGetTaskByTaskId() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskId", 22);
		int[]as=new int[2];
		as[0]=22;
		as[1]=17;
		map.put("userIds", as);
		List<Integer> list=tskMicroTaskExecuteDao.getIsAllocateTaskByUserIds(map);
		System.out.println(list.toString()+"wo shi result...");
	}

}
