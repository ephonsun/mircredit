/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.dao;

import com.banger.mobile.dao.remindConfig.RemindConfigDao;
import com.banger.mobile.domain.model.remindConfig.RemindConfig;

/**
 * @author Administrator
 * @version $Id: RemindConfigDaoTest.java,v 0.1 Jun 1, 2012 4:18:56 PM Administrator Exp $
 */
public class RemindConfigDaoTest extends BaseDaoTestCase{
    private RemindConfigDao remindConfigDao;

    public void setRemindConfigDao(RemindConfigDao remindConfigDao) {
        this.remindConfigDao = remindConfigDao;
    }
//    public void testDaoNotNull(){
//        assertNotNull(remindConfigDao);
//    }
//    public void testQuery(){
//        System.out.print(this.remindConfigDao.queryRemindConfig(1));
//    }
//      public void testUpdate(){
//          RemindConfig remindConfig = new RemindConfig();
//          remindConfig.setIsMessageRemind(0);
//          remindConfig.setIsCallRemind(0);
//          remindConfig.setUserId(1);
//          this.remindConfigDao.updateRemindConfig(remindConfig);
//      }
    public void testAdd(){
        RemindConfig remindConfig = new RemindConfig();
        remindConfig.setIsMessageRemind(0);
        remindConfig.setIsCallRemind(0);
        remindConfig.setIsAssignTask(0);
        remindConfig.setIsOverDueTask(0);
        remindConfig.setIsSendMms(0);
        remindConfig.setIsSendSms(0);
        remindConfig.setIsSmsRemind(0);
        remindConfig.setUserId(95);
        this.remindConfigDao.addRemindConfig(remindConfig);
    }
}
