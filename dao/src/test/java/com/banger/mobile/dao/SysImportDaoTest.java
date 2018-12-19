/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-8-27
 */
package com.banger.mobile.dao;

import java.util.Date;

import com.banger.mobile.dao.user.ObjectDao;
import com.banger.mobile.domain.model.sysImport.SysImport;

/**
 * @author yangyang
 * @version $Id: SysImportDaoTest.java,v 0.1 2012-8-27 上午11:51:35 yangyong Exp $
 */
public class SysImportDaoTest extends BaseDaoTestCase {
    
    private ObjectDao SysImportDao;

    public void setSysImportDao(ObjectDao sysImportDao) {
        SysImportDao = sysImportDao;
    }
    /**
     * 测试dao是否为空
     * @throws Exception
     */
    public void testDaoNotNull() throws Exception {
        assertNotNull(SysImportDao);
    }

    public void testAddSysImport(){
        SysImport s=new SysImport();
        s.setFailNum(1);
        s.setImportDate(new Date());
        s.setImportNo("0011");
        s.setSuccessNum(50);
        s.setTotalNum(100);
        s.setCreateUser(1);
        s.setUpdateUser(1);
       // SysImportDao.addObject(s);
    }
}
