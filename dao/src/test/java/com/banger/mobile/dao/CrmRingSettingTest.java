/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2012-5-24
 */
package com.banger.mobile.dao;

import java.util.HashMap;

import com.banger.mobile.dao.crmRingSetting.CrmRingSettingDao;
import com.banger.mobile.dao.customer.AdvancedCustomerDao;
import com.banger.mobile.domain.model.crmRingSetting.CrmRingSetting;

/**
 * @author yujh
 * @version $Id: AdvancedCustomerTest.java,v 0.1 2012-5-24 下午04:39:07 liyb Exp $
 */
public class CrmRingSettingTest extends BaseDaoTestCase {
    private CrmRingSettingDao crmRingSettingDao;

    public void setCrmRingSettingDao(CrmRingSettingDao crmRingSettingDao) {
        this.crmRingSettingDao = crmRingSettingDao;
    }
//    public void testNotNull(){
//        assertNotNull(crmRingSettingDao);
//    }
//    public void testAdd(){
//        CrmRingSetting crs= new CrmRingSetting();
//        crs.setUserId(1);
//        crs.setFilePath("aaaa");
//        this.crmRingSettingDao.insertCrmRingSetting(crs);
//    }
    public void testUpd(){
        CrmRingSetting crs= new CrmRingSetting();
        crs.setUserId(1);
        crs.setFilePath("bbbb");
        this.crmRingSettingDao.updateCrmRingSetting(crs);
    }
}
