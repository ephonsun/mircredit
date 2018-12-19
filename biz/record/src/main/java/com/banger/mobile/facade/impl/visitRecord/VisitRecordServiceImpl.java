/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :拜访记录service实现类
 * Author     :yujh
 * Create Date:May 29, 2012
 */
package com.banger.mobile.facade.impl.visitRecord;

import com.banger.mobile.dao.visitRecord.VisitRecordDao;
import com.banger.mobile.domain.model.visitRecord.VisitRecord;
import com.banger.mobile.domain.model.visitRecord.VisitRecordInfo;
import com.banger.mobile.facade.visitRecord.VisitRecordService;

/**
 * @author yujh
 * @version $Id: VisitRecordServiceImpl.java,v 0.1 May 29, 2012 3:05:44 PM Administrator Exp $
 */
public class VisitRecordServiceImpl implements VisitRecordService{
    
    private VisitRecordDao visitRecordDao;

    public void setVisitRecordDao(VisitRecordDao visitRecordDao) {
        this.visitRecordDao = visitRecordDao;
    }

    /**
     * 新增拜访记录
     * @param visitRecord
     * @see com.banger.mobile.facade.visitRecord.VisitRecordService#addVisitRecord(com.banger.mobile.domain.model.visitRecord.VisitRecord)
     */
    public void addVisitRecord(VisitRecord visitRecord) {
        this.visitRecordDao.addVisitRecord(visitRecord);
    }

    /**
     * 查看拜访记录
     */
    public VisitRecordInfo getVisitRecord(int id) {
        return this.visitRecordDao.getVisitRecord(id);
    }

    /**
     * 编辑&更新拜访记录
     * @param visitReord
     * @see com.banger.mobile.facade.visitRecord.VisitRecordService#updateVisitRecord(com.banger.mobile.domain.model.visitRecord.VisitRecord)
     */
    public void updateVisitRecord(VisitRecord visitReord) {
        this.visitRecordDao.updateVisitRecord(visitReord);
    }

}
