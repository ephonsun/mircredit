/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :答录配置service测试类
 * Author     :yujh
 * Create Date:Jun 4, 2012
 */
package com.banger.mobile.service;

import com.banger.mobile.domain.model.answerConfig.AnswerConfig;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.answerConfig.AnswerConfigService;
import com.banger.mobile.facade.crmCounterUser.CrmCounterUserService;
import com.banger.mobile.facade.crmVasSetting.CrmVasSettingService;

/**
 * @author yujh
 * @version $Id: AnswerConfigServiceTest.java,v 0.1 Jun 4, 2012 4:50:21 PM Administrator Exp $
 */
public class CrmCounterUserServiceTest extends BaseServiceTestCase{
    private CrmCounterUserService crmCounterUserService;
    

    public void setCrmCounterUserService(CrmCounterUserService crmCounterUserService) {
        this.crmCounterUserService = crmCounterUserService;
    }
//    public void testNotNull(){
//        assertNotNull(crmCounterUserService);
//    }
    public void testGetAll(){
        this.crmCounterUserService.getAllCrmCounterIds(3,11);
    }
}
