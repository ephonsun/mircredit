/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :拜访记录dao
 * Author     :yujh
 * Create Date:May 29, 2012
 */
package com.banger.mobile.dao.visitRecord;

import com.banger.mobile.domain.model.visitRecord.VisitRecord;
import com.banger.mobile.domain.model.visitRecord.VisitRecordInfo;

/**
 * @author yujh
 * @version $Id: VisitRecordDao.java,v 0.1 May 29, 2012 1:58:25 PM Administrator Exp $
 */
public interface VisitRecordDao {
    /**
     * 添加拜访记录
     */
    public void addVisitRecord(VisitRecord visitReord);
    /**
     * 更新
     */
    public void updateVisitRecord(VisitRecord visitReord);
    /**
     * 查询
     */
    public VisitRecordInfo getVisitRecord(int id);
}
