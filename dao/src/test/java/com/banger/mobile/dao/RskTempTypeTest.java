/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.dao;

import com.banger.mobile.dao.RskTempType.RskTempTypeDao;
import com.banger.mobile.domain.model.rskTempType.RskTempType;

/**
 * @author Administrator
 * @version $Id: RskTempTypeTest.java,v 0.1 Jul 16, 2012 11:36:02 AM Administrator Exp $
 */
public class RskTempTypeTest extends BaseDaoTestCase {
    private     RskTempTypeDao rskTempTypeDao;

    public void setRskTempTypeDao(RskTempTypeDao rskTempTypeDao) {
        this.rskTempTypeDao = rskTempTypeDao;
    }
    public void testDaoNotNull(){
        assertNotNull(rskTempTypeDao);
    }
    public void testAdd(){
        RskTempType rsk= new RskTempType();
        rsk.setTempTypeName("类型1");
        rsk.setSortNo(1);
        rskTempTypeDao.addRskTempType(rsk);
    }
//    public void testDelete(){
//        rskTempTypeDao.deleteRskTempType(1);
//    }
    public void testUpd(){
        RskTempType rsk = new RskTempType();
        rsk.setTempTypeId(2);
        rsk.setSortNo(11);
        rsk.setTempTypeName("xiugai");
        rskTempTypeDao.updRskTempType(rsk);
    }
}
