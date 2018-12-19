/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :拜访记录dao实现类
 * Author     :yujh
 * Create Date:May 29, 2012
 */
package com.banger.mobile.dao.visitRecord.ibatis;

import com.banger.mobile.dao.visitRecord.VisitRecordDao;
import com.banger.mobile.domain.model.visitRecord.VisitRecord;
import com.banger.mobile.domain.model.visitRecord.VisitRecordInfo;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yujh
 * @version $Id: VisitRecordDaoiBatis.java,v 0.1 May 29, 2012 2:09:30 PM Administrator Exp $
 */
public class VisitRecordDaoiBatis extends GenericDaoiBatis implements VisitRecordDao{

    public VisitRecordDaoiBatis(Class persistentClass) {
        super(persistentClass);
    }
    public VisitRecordDaoiBatis(){
        super(VisitRecord.class);
    }

    /**
     * 添加拜访记录
     */
    public void addVisitRecord(VisitRecord visitReord) {
        this.getSqlMapClientTemplate().insert("addVisitRecord",visitReord);
    }

    /**
     * 查询
     */
    public VisitRecordInfo getVisitRecord(int id) {
       return (VisitRecordInfo)this.getSqlMapClientTemplate().queryForObject("getVisitRecord", id);
    }

    /**
     * 更新
     */
    public void updateVisitRecord(VisitRecord visitReord) {
        this.getSqlMapClientTemplate().update("updateVisitRecord",visitReord);
    }

}
