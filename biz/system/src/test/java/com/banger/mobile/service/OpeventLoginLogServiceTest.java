/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :操作日志service测试类
 * Author     :yujh
 * Create Date:May 23, 2012
 */
package com.banger.mobile.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.domain.model.log.OpeventLoginLog;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.log.OpeventLoginLogService;

/**
 * @author yujh
 * @version $Id: OpeventLogServiceTest.java,v 0.1 May 23, 2012 2:53:16 PM Administrator Exp $
 */
public class OpeventLoginLogServiceTest extends BaseServiceTestCase {
    
    private OpeventLoginLogService opeventLoginLogService;

    public void setOpeventLoginLogService(OpeventLoginLogService opeventLoginLogService) {
        this.opeventLoginLogService = opeventLoginLogService;
    }
//    public void testNotNull(){
//        assertNotNull(opeventLoginLogService);
//    }
//    public void testAdd(){
//        for(int i=0;i<100;i++){
//           this.opeventLoginLogService.addLog(2, 2, "222", "222.22.222.2", 2, 2, "2");
//        }
//    }
    public void testPage(){
        Map<String,Object> map = new HashMap<String,Object>();
        
        this.opeventLoginLogService.getLoginLogPage(map, null);
    }

}
