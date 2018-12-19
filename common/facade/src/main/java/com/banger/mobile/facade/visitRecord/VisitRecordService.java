/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :拜访客户service
 * Author     :yujh
 * Create Date:May 29, 2012
 */
package com.banger.mobile.facade.visitRecord;

import com.banger.mobile.domain.model.visitRecord.VisitRecord;
import com.banger.mobile.domain.model.visitRecord.VisitRecordInfo;

/**
 * @author yujh
 * @version $Id: VisitRecord.java,v 0.1 May 29, 2012 2:57:32 PM Administrator Exp $
 */
public interface VisitRecordService {
    /**
     * 添加拜访记录
     * @param visitRecord
     */
    public void addVisitRecord(VisitRecord visitRecord);
    /**
     * 更新
     */
    public void updateVisitRecord(VisitRecord visitReord);
    /**
     * 查询
     */
    public VisitRecordInfo getVisitRecord(int id);
}
