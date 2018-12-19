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
import com.banger.mobile.dao.crmRecordSetting.CrmRecordSettingDao;
import com.banger.mobile.dao.customer.AdvancedCustomerDao;
import com.banger.mobile.domain.model.crmRecordRemind.CrmRecordRemind;
import com.banger.mobile.domain.model.crmRecordSetting.CrmRecordSetting;

/**
 * @author liyb
 * @version $Id: AdvancedCustomerTest.java,v 0.1 2012-5-24 下午04:39:07 liyb Exp $
 */
public class CrmRecordSettingTest extends BaseDaoTestCase {
    private CrmRecordSettingDao crmRecordSettingDao;

    public void setCrmRecordSettingDao(CrmRecordSettingDao crmRecordSettingDao) {
        this.crmRecordSettingDao = crmRecordSettingDao;
    }
//    public void testNotNull(){
//        assertNotNull(crmRecordSettingDao);
//    }
    public void testInsert(){
        CrmRecordSetting crs= new CrmRecordSetting();
        crs.setUserId(22);
        crs.setIsAutoPlay(0);
        crs.setIsAutoRecord(0);
        this.crmRecordSettingDao.insertCrmRecordSetting(crs);
    }
    

}
