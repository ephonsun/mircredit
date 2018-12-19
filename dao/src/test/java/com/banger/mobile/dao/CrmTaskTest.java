/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务管理Dao测试
 * Author     :liyb
 * Create Date:2012-5-25
 */
package com.banger.mobile.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.dao.task.CrmTaskDao;
import com.banger.mobile.domain.model.task.CrmTask;

/**
 * @author liyb
 * @version $Id: CrmTaskTest.java,v 0.1 2012-5-25 上午11:07:53 liyb Exp $
 */
public class CrmTaskTest extends BaseDaoTestCase {
    private CrmTaskDao crmTaskDao;
    
    public void setCrmTaskDao(CrmTaskDao crmTaskDao) {
        this.crmTaskDao = crmTaskDao;
    }
    
    public CrmTaskDao getCrmTaskDao() {
        return crmTaskDao;
    }

    /**
     * 测试dao是否为空
     * @throws Exception
     */
    public void testDaoNotNull() throws Exception {
        assertNotNull(crmTaskDao);
    }
    
//    public void testGetCrmTaskPage(){
//        Map<String,Object> map=new HashMap<String, Object>();
//        map.put("isFinish", 0);//未完成
//        map.put("customerName", "xuhj");
//        crmTaskDao.getCrmTaskPage(map, null);
//    }
//    
//    public void testInsertTask(){
//        CrmTask task=new CrmTask();
//        task.setTaskTitle("需求讨论");
//        task.setExecuteDate(new Date());
//        task.setExecuteUser(346);
//        task.setRemark("需求有变更,需再次讨论!");
//        task.setAssignUser(1);
//        task.setIsFinish(0);
//        task.setCreateDate(new Date());
//        task.setCreateUser(1);
//        Integer taskId=crmTaskDao.insertTask(task);
//        System.err.println(taskId);
//    }
}
