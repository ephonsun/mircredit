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

import com.banger.mobile.dao.log.OpeventLoginLogDao;
import com.banger.mobile.domain.model.log.OpeventLoginLog;

/**
 * @author yujh
 * @version $Id: OpeventDaoTest.java,v 0.1 May 23, 2012 2:42:54 PM Administrator Exp $
 */
public class OpeventLoginLogDaoTest extends BaseDaoTestCase{
    private OpeventLoginLogDao opeventLoginLogDao;

    public void setOpeventLoginLogDao(OpeventLoginLogDao opeventLoginLogDao) {
        this.opeventLoginLogDao = opeventLoginLogDao;
    }
//    public void testNotNull(){
//        assertNotNull(opeventLoginLogDao);
//    }
    public void testInsert(){
        OpeventLoginLog log = new OpeventLoginLog();
        log.setClientTypeId(1);
        log.setOpeventAction("1111");
        log.setOpeventDate(new Date());
        log.setOpeventIp("127.0.0.1");
        log.setOpeventObjectId(1);
        log.setRemark("remark");
        log.setState(1);
        log.setUserId(1);
        for(int i=0;i<100;i++){
            this.opeventLoginLogDao.addLog(log);
        }
    }
//    public void testPage(){
//        Map<String,Object> map= new HashMap<String,Object>();
//        map.put("startLogDate", "2012-1-01");
//  //      map.put("endLogDate", "2012-5-23");
//        this.opeventLoginLogDao.getLogPage(map, null);
//    }
}
