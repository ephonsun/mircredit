/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :操作日志service测试类
 * Author     :yujh
 * Create Date:May 23, 2012
 */
package com.banger.mobile.service;

import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.log.OpeventLogService;

/**
 * @author yujh
 * @version $Id: OpeventLogServiceTest.java,v 0.1 May 23, 2012 2:53:16 PM Administrator Exp $
 */
public class OpeventLogServiceTest extends BaseServiceTestCase {
    
    private OpeventLogService opeventLogService;

    public void setOpeventLogService(OpeventLogService opeventLogService) {
        this.opeventLogService = opeventLogService;
    }
    /**
     * 非空测试
     * @throws Exception
     */
    public void testServiceNotNull()throws Exception{
        super.assertNotNull(opeventLogService);
    }
    /**
     * 分页&查询测试
     * @throws Exception
     */
    public void testGetOpeventLogPage()throws Exception{
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("startLogDate", "2012-5-23");
        map.put("endLogDate", "2012-5-23");
        opeventLogService.getOpeventLogPage(map,null);
    }
    /**
     * 添加测试
     * @throws Exception
     */
    public void testAddOpeventLog()throws Exception{
        opeventLogService.addOpeventLog("测试", "测试", 1, "测试");
    }

}
