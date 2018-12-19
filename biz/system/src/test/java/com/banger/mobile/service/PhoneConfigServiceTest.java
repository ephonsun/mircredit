/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通话配置service测试类
 * Author     :yujh
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.service;

import com.banger.mobile.domain.model.phoneConfig.PhoneConfig;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.phoneConfig.PhoneConfigService;

/**
 * @author yujh
 * @version $Id: PhoneConfigServiceTest.java,v 0.1 Jun 1, 2012 11:06:00 AM Administrator Exp $
 */
public class PhoneConfigServiceTest extends BaseServiceTestCase{
    private PhoneConfigService phoneConfigService;

    public void setPhoneConfigService(PhoneConfigService phoneConfigService) {
        this.phoneConfigService = phoneConfigService;
    }
//    public void testServiceNotNull(){
//        assertNotNull(phoneConfigService);
//    }
//    public void testQuery(){
//        System.out.print(this.phoneConfigService.query(1));
//    }
//      public void testUpdate(){
//          PhoneConfig phoneConfig = new PhoneConfig();
//          phoneConfig.setCallOverPopUp(22);
//          phoneConfig.setCityCode("22");
//          phoneConfig.setFlashBreakTime(22);
//          phoneConfig.setInsiseExtLength("22");
//          phoneConfig.setIpNumber("22");
//          phoneConfig.setIsIpCall(22);
//          phoneConfig.setIsPopUp(22);
//          phoneConfig.setOutDelay(2.0);
//          phoneConfig.setOutNumber("22");
//          phoneConfig.setOutsideCallCode("22");
//          phoneConfig.setUserId(2);
//          this.phoneConfigService.update(phoneConfig);
//      }
//      public void testAdd(){
//          this.phoneConfigService.addPhoneConfig(2);
//      }
        public void testGetCityCode(){
            this.phoneConfigService.getCityCodeByUserId(500000);
        }
}
