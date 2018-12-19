/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :操作日志dao测试类
 * Author     :yujh
 * Create Date:May 23, 2012
 */
package com.banger.mobile.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.dao.opeventLog.OpeventLogDao;
import com.banger.mobile.domain.model.log.OpeventLog;

/**
 * @author yujh
 * @version $Id: OpeventDaoTest.java,v 0.1 May 23, 2012 2:42:54 PM Administrator Exp $
 */
public class OpeventDaoTest extends BaseDaoTestCase{
    private OpeventLogDao opeventLogDao;

    public void setOpeventLogDao(OpeventLogDao opeventLogDao) {
        this.opeventLogDao = opeventLogDao;
    }
    /**
     * 非空测试
     * @throws Exception
     */
    public void testDaoNotNull()throws Exception{
        assertNotNull(opeventLogDao);
    }
    /**
     * 分页&查询测试
     * @throws Exception
     */
    public void testGetOpeventLogPage()throws Exception{
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("startLogDate", "2012-1-01");
        map.put("endLogDate", "2012-5-23");
        opeventLogDao.getOpeventLogPage(map, null);
    }
}
