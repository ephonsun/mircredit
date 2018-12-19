/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2012-5-24
 */
package com.banger.mobile.dao;

import java.util.Date;
import java.util.HashMap;

import com.banger.mobile.dao.crmRecordRemind.CrmRecordRemindDao;
import com.banger.mobile.dao.customer.AdvancedCustomerDao;
import com.banger.mobile.domain.model.crmRecordRemind.CrmRecordRemind;

/**
 * @author liyb
 * @version $Id: AdvancedCustomerTest.java,v 0.1 2012-5-24 下午04:39:07 liyb Exp $
 */
public class CrmRecordRemindTest extends BaseDaoTestCase {
    private CrmRecordRemindDao crmRecordRemindDao;

    public void setCrmRecordRemindDao(CrmRecordRemindDao crmRecordRemindDao) {
        this.crmRecordRemindDao = crmRecordRemindDao;
    }
//    public void testNotNull(){
//        assertNotNull(crmRecordRemindDao);
//    }
//    public void testInsert(){
//        CrmRecordRemind crr = new CrmRecordRemind();
//        crr.setCreateDate(new Date());
//        crr.setFilePath("aaaa");
//        crr.setUserId(1);
//        crr.setUpdateDate(new Date());
//        this.crmRecordRemindDao.insertCrmRecordRemind(crr);
//    }
    public void testUpdate(){
        CrmRecordRemind crr = new CrmRecordRemind();
        crr.setUserId(1);
        crr.setFilePath("bbbb");
        this.crmRecordRemindDao.updateCrmRecordRemind(crr);
    }
}
