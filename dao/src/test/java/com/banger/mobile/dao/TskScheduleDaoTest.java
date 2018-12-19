/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通话配置dao测试类
 * Author     :yujh
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.dao;

import com.banger.mobile.dao.microTask.TskScheduleDao;
import com.banger.mobile.domain.model.microTask.TskSchedule;

import java.util.*;

/**
 * @author chengjw
 * @version $Id: PhoneConfigDaoTest.java,v 0.1 Jun 1, 2012 10:34:04 AM Administrator Exp $
 */
public class TskScheduleDaoTest  extends BaseDaoTestCase{
    private TskScheduleDao tskScheduleDao;

//    public void testDaoNotNull(){
//        assertNotNull(tskScheduleDao);
//    }
  
//    public void testInsert(){
//    	TskSchedule ts = new TskSchedule();
//    	
//    	ts.setUserId(30);
//    	ts.setCustomerId(5);
//    	ts.setContactDate(new Date());
//    	ts.setContactType(1);
//    	ts.setRemark("测试备注");
//    	//addDate
//    	ts.setCommProgressId(5);
//    	ts.setStatus(0);
//    	
//    	ts.setCreateUser(30);
//    	ts.setUpdateUser(30);
//    	tskScheduleDao.addNewSchedule(ts);
//    }
    
//    public void testUpdate(){
//    	TskSchedule ts = new TskSchedule();
//    	ts.setRemark("更新测试");
//    	ts.setContactType(555);
//    	ts.setScheduleId(3);
//    	tskScheduleDao.updateSchedule(ts);
//    }
//    
//    public void testGet(){
//    	TskSchedule ts = tskScheduleDao.getScheduleById(3);
//    	assertNotNull(ts);
//    	System.out.println("查找结果："+ts.getContactType()+ ts.getRemark() +ts.getContactDate() +ts.getStatus() + ts.getScheduleId());
//    }
//    
//    
//    
    public void testList(){
        Map<String , Object> parameterMap = new HashMap<String , Object>();
        parameterMap.put("userId" ,30);
        List<TskSchedule> list = tskScheduleDao.getScheduleListByPage(parameterMap, null);
        System.out.println("list大小="+list.size());
      
    }
//    public void testGetCount(){
//        Map<String , Object> map = new HashMap<String , Object>();
//        map.put("userId", 10000020);
//        map.put("customerId", 100);
//        
//        int a = tskScheduleDao.getCustomerScheduleCount(map);
//        
//        System.out.println(a);
//    }
//    public void testUpdate(){
//        TskSchedule ts = new TskSchedule();
//        ts.setUserId(10000020);
//        ts.setCustomerId(100);
//        ts.setRemark("这里是测试");
//        
//        tskScheduleDao.updateSchedule(ts);
//    }
//    public void testGetCustomerList(){
//        Map<String , Object> map = new HashMap<String , Object>();
//        map.put("userId", 30);
//        
//        List<TskSchedule> list = tskScheduleDao.getCustomerList(map, null);
//        System.out.println(list.size());
//    }
//    public void testGetCustomerType(){
//        List list = tskScheduleDao.getCustomerTypeList();
//        System.out.println(list.size()+"-----------------");
//    }
//    public void testGetCustomerDetail(){
//        Map<String , Object> map = new HashMap<String , Object>();
//        map.put("scheduleId", 101);
//        TskSchedule ts = tskScheduleDao.getCustomerDetail(map);
//        System.out.println(ts);
//    }
    
    
	public void setTskScheduleDao(TskScheduleDao tskScheduleDao) {
		this.tskScheduleDao = tskScheduleDao;
	}

    public void testGetScheduleCusIds(){
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerIds","33,44");
        List<Integer> scheduleCusIdList = tskScheduleDao.getScheduleCusIds(paramMap);
        System.out.println(scheduleCusIdList.size());
    }

    public void testinsertTskSchedule() throws Exception{
        TskSchedule tskSchedule = new TskSchedule();
        tskSchedule.setScheduleId(4);
        tskSchedule.setUserId(11);
        tskSchedule.setCustomerId(502);
        tskSchedule.setContactDate(new Date());
        tskSchedule.setContactType(1);
        tskSchedule.setRemark("jjjj");
        tskSchedule.setAddDate(Calendar.getInstance().getTime());
        tskSchedule.setStatus(0);// 0未完成
        tskSchedule.setCreateDate(Calendar.getInstance().getTime());
        tskSchedule.setCreateUser(11);
        //默认选择第一条进度
//        Integer no1CommProgressId = tskScheduleService.getNo1CommProgressId();
//                tskSchedule.setCommProgressId(no1CommProgressId);
        tskSchedule.setCommProgressId(1);
        tskScheduleDao.insertTskSchedule(tskSchedule);
    }
	
}
