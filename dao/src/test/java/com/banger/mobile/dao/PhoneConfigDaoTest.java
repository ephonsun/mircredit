/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通话配置dao测试类
 * Author     :yujh
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.dao;

import com.banger.mobile.dao.phoneConfig.PhoneConfigDao;
import com.banger.mobile.domain.model.phoneConfig.PhoneConfig;

/**
 * @author yujh
 * @version $Id: PhoneConfigDaoTest.java,v 0.1 Jun 1, 2012 10:34:04 AM Administrator Exp $
 */
public class PhoneConfigDaoTest  extends BaseDaoTestCase{
    private PhoneConfigDao phoneConfigDao;

    public void setPhoneConfigDao(PhoneConfigDao phoneConfigDao) {
        this.phoneConfigDao = phoneConfigDao;
    }
    public void testDaoNotNull(){
        assertNotNull(phoneConfigDao);
    }
//    public void testQuery(){
//        System.out.print(this.phoneConfigDao.query(1));
//    }

//    public void testAdd(){
//        PhoneConfig phoneConfig = new PhoneConfig();
//        phoneConfig.setCallOverPopUp(2);
//        phoneConfig.setCityCode("2");
//        phoneConfig.setFlashBreakTime(2);
//        phoneConfig.setInsiseExtLength("2");
//        phoneConfig.setIpNumber("2");
//        phoneConfig.setIsIpCall(2);
//        phoneConfig.setIsPopUp(2);
//        phoneConfig.setOutDelay(2.0);
//        phoneConfig.setOutNumber("2");
//        phoneConfig.setOutsideCallCode("2");
//        phoneConfig.setUserId(2);
//        this.phoneConfigDao.addPhoneConfig(phoneConfig);
//    }
    public void testUpdate(){
        PhoneConfig phoneConfig = new PhoneConfig();
        phoneConfig.setCallOverPopUp(2);
        phoneConfig.setCityCode("2");
        phoneConfig.setFlashBreakTime(2);
        phoneConfig.setInsiseExtLength("2");
        phoneConfig.setIpNumber("2");
        phoneConfig.setIsIpCall(2);
        phoneConfig.setIsPopUp(2);
        phoneConfig.setOutDelay(2.0);
        phoneConfig.setOutNumber("2");
        phoneConfig.setOutsideCallCode("2");
        phoneConfig.setUserId(2);
        this.phoneConfigDao.update(phoneConfig);
        
    }
}
