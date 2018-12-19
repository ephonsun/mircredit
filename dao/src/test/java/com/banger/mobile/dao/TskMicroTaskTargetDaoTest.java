/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2012-5-24
 */
package com.banger.mobile.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.customer.AdvancedCustomerDao;
import com.banger.mobile.dao.microTask.TskMicroTaskTargetDao;
import com.banger.mobile.domain.model.microTask.TskMicroTaskTarget;

/**
 * @author liyb
 * @version $Id: AdvancedCustomerTest.java,v 0.1 2012-5-24 下午04:39:07 liyb Exp $
 */
public class TskMicroTaskTargetDaoTest extends BaseDaoTestCase {
	private TskMicroTaskTargetDao tskMicroTaskTargetDao;

	public void setTskMicroTaskTargetDao(
			TskMicroTaskTargetDao tskMicroTaskTargetDao)
	{
		this.tskMicroTaskTargetDao = tskMicroTaskTargetDao;
	}

	/**
	 * 测试dao是否为空
	 * 
	 * @throws Exception
	 */
	public void testDaoNotNull() throws Exception
	{
		assertNotNull(tskMicroTaskTargetDao);
	}

	//public void testGetTaskByTaskId()
	//{
	//}

	// public void testGetAdvancedCustomer(){
	// List<TskMicroTaskTarget> list = new ArrayList<TskMicroTaskTarget>();
	// TskMicroTaskTarget tskMicroTaskTarget=new TskMicroTaskTarget();
	// tskMicroTaskTarget.setPhoneNumber("12121211111");
	// tskMicroTaskTarget.setTaskId(82);
	// list = tskMicroTaskTargetDao.queryByPhoneNoAndTaskId(tskMicroTaskTarget);
	// System.out.println(list.size());
	// }
	//public void testAddBatch()
	//{
		// TskMicroTaskTarget tskMicroTaskTarget = new TskMicroTaskTarget();
		// tskMicroTaskTarget.setPhoneNumber("15168366960");
		// tskMicroTaskTarget.setTaskId(122);
		// // tskMicroTaskTarget.setUpdateUser(1);
		// tskMicroTaskTargetDao.getMicroTaskTargetEqualsTarget(tskMicroTaskTarget);
	//}

	//public void testAutoFinish()
	//{
		// Date currentDate = Calendar.getInstance().getTime();
		//
		// Map<String, Object> params = new HashMap<String, Object>();
		// params.put("phoneNumber", "2");
		// params.put("cusId", 14);
		// params.put("userId", 14);
		// params.put("currentDate", currentDate);
		// params.put("recordInfoId", 1044);
		// tskMicroTaskTargetDao.autoFinish(params);
	//}

	// 通过号码和姓名查询
	public void testQueryByNamesAndPhones()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		TskMicroTaskTarget t = new TskMicroTaskTarget();
		t.setTaskId(267);
		t.setUserId(15);
		t.setPhoneNumber("1");
		t.setAge(15);
		List<TskMicroTaskTarget> list = new ArrayList<TskMicroTaskTarget>();
		list.add(t);// /list.add(t1);
		map.put("tskTargetList", list);
		tskMicroTaskTargetDao.getTskMicTargetsByPhonesAndNames(map);
	}
}
