/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :安全日志service测试类
 * Author     :yujh
 * Create Date:May 22, 2012
 */
package com.banger.mobile.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.domain.model.log.LoginLog;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.log.LoginLogService;

/**
 * @author yujh
 * @version $Id: LoginLogServiceTest.java,v 0.1 May 22, 2012 6:30:41 PM Administrator Exp $
 */
public class LoginLogServiceTest extends BaseServiceTestCase {
    private LoginLogService loginLogService;

    public void setLoginLogService(LoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }
    
    public void testServiceNotNull() throws Exception{
        assertNotNull(loginLogService);
    }
    /**
     * 添加测试
     * @throws Exception
     */
    public void testAddLoginLog()throws Exception{
        loginLogService.addLoginLog(111, "192.168.1.1", 1, 1);
    }
    public void testgetLoginLogPage() throws Exception{
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("startLogDate", "2012-02-02");
        map.put("endLogDate", "2012-02-12");
        loginLogService.getLoginLogPage(map, null);
    }
}
