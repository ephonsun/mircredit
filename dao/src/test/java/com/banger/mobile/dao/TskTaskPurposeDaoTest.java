/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       : 联系目的dao测试类
 * Author     :yujh
 * Create Date:Jun 4, 2012
 */
package com.banger.mobile.dao;

import com.banger.mobile.dao.tskTaskPurpose.TskTaskPurposeDao;
import com.banger.mobile.domain.model.tskTaskPurpose.TskTaskPurpose;

/**
 * @author yujh
 * @version $Id: AnswerConfigDaoTest.java,v 0.1 Jun 4, 2012 4:35:22 PM Administrator Exp $
 */
public class TskTaskPurposeDaoTest extends BaseDaoTestCase{
    private TskTaskPurposeDao tskTaskPurposeDao;

    public void setTskTaskPurposeDao(TskTaskPurposeDao tskTaskPurposeDao) {
        this.tskTaskPurposeDao = tskTaskPurposeDao;
    }
    public void testNotNull(){
        assertNotNull(tskTaskPurposeDao);
    }
    public void testgetAll(){
        this.tskTaskPurposeDao.getAllTskTaskPurpose(true);
    }
    public void testgetById(){
        this.tskTaskPurposeDao.getTskTaskPurposeById(1);
    }
    public void testgetBySameName(){
        TskTaskPurpose tskTaskPurpose = new TskTaskPurpose();
        tskTaskPurpose.setTaskPurposeName("第一次联系");
        this.tskTaskPurposeDao.getTskTaskPurposeBySameName(tskTaskPurpose);
    }
    public void testUpd(){
        TskTaskPurpose tskTaskPurpose = new TskTaskPurpose();
        tskTaskPurpose.setTaskPurposeName("一次联系");
        tskTaskPurpose.setTaskPurposeId(1);
        tskTaskPurpose.setIsDel(0);
        tskTaskPurpose.setSortNo(1);
        this.tskTaskPurposeDao.updateTskTaskPurpose(tskTaskPurpose);
    }
    
}
