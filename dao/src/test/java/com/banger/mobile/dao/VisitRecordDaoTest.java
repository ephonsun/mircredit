/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :全局参数dao测试类
 * Author     :yujh
 * Create Date:May 25, 2012
 */
package com.banger.mobile.dao;

import java.util.Date;

import com.banger.mobile.dao.visitRecord.VisitRecordDao;
import com.banger.mobile.domain.model.visitRecord.VisitRecord;

/**
 * @author yujh
 * @version $Id: SysParamDaoTest.java,v 0.1 May 25, 2012 4:28:33 PM Administrator Exp $
 */
public class VisitRecordDaoTest extends BaseDaoTestCase{
    private VisitRecordDao visitRecordDao;

    public void setVisitRecordDao(VisitRecordDao visitRecordDao) {
        this.visitRecordDao = visitRecordDao;
    }
//    public void testDaoNotNull() throws Exception{
//        assertNotNull(visitRecordDao);
//    }
//    public void testAddVisitRecord(){
//        VisitRecord record = new VisitRecord();
//        record.setBizType("测试");
//        record.setCommProgress(11111);
//        record.setCustomerId(11111);
//        record.setRemark("测试");
//        record.setStartDate(new Date());
//        record.setUserId(123321);
//        visitRecordDao.addVisitRecord(record);
//    }
//    public void testupdateVisitRecord() {
//        VisitRecord record = new VisitRecord();
//        record.setCustomerId(11111);
//        record.setBizType("测试");
//        record.setCommProgress(1010);
//        record.setRemark("测试测试测试");
//        record.setStartDate(new Date());
//        visitRecordDao.updateVisitRecord(record);
//    }
    
}
