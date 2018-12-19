/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.service;

import com.banger.mobile.domain.model.remindConfig.RemindConfig;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.remindConfig.RemindConfigService;

/**
 * @author Administrator
 * @version $Id: RemindConfigServiceTest.java,v 0.1 Jun 1, 2012 4:37:39 PM Administrator Exp $
 */
public class RemindConfigServiceTest extends BaseServiceTestCase{
    private RemindConfigService remindConfigService;

    public void setRemindConfigService(RemindConfigService remindConfigService) {
        this.remindConfigService = remindConfigService;
    }
//    public void testServiceNotNull(){
//        assertNotNull(remindConfigService);
//    }
//    public void testQuery(){
//        System.out.print(this.remindConfigService.queryRemindConfig());
//    }
//      public void testUpdate(){
//          RemindConfig remindConfig = new RemindConfig();
//          remindConfig.setIsCallRemind(2);
//          remindConfig.setIsMessageRemind(2);
//          this.remindConfigService.updateRemindConfig(remindConfig);
//      }
      public void testAdd(){
          RemindConfig remindConfig = new RemindConfig();
          this.remindConfigService.addRemindConfig(3);
          
      }
}
