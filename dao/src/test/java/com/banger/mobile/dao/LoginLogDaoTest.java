/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:May 23, 2012
 */
package com.banger.mobile.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.dao.loginLog.LoginLogDao;
import com.banger.mobile.domain.model.log.LoginLog;

/**
 * @author Administrator
 * @version $Id: LoginLogDaoTest.java,v 0.1 May 23, 2012 9:37:51 AM Administrator Exp $
 */
public class LoginLogDaoTest extends BaseDaoTestCase{
    private LoginLogDao loginLogDao;

    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }
    public void testDaoNotNull() throws Exception{
        assertNotNull(loginLogDao);
    }
    public void testAddLoginLog() throws Exception{
//        LoginLog log= new LoginLog();
//        log.setLogDate(new Date());
//        log.setLoginDevice(1);
//        log.setLoginLogID(1010);
//        log.setLogIp("127.0.0.1");
//        log.setLogType(1);
//        log.setUserId(100);
        loginLogDao.addLoginLog(100,"127.0.0.1", 1, 1);
    }
    public void testgetLoginLogPage() throws Exception{
        Map<String,Object> map= new HashMap<String ,Object>();
        map.put("startLogDate", "2012-1-20");
        map.put("endLogDate", "2012-5-23");
        loginLogDao.getLoginLogPage(map, null);
    }
}
