/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :答录配置service测试类
 * Author     :yujh
 * Create Date:Jun 4, 2012
 */
package com.banger.mobile.service;

import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.sysWhiteList.SysWhiteListService;

/**
 * @author yujh
 * @version $Id: AnswerConfigServiceTest.java,v 0.1 Jun 4, 2012 4:50:21 PM Administrator Exp $
 */
public class SysWhiteListServiceTest extends BaseServiceTestCase{
    private SysWhiteListService sysWhiteListService;

    public void setSysWhiteListService(SysWhiteListService sysWhiteListService) {
        this.sysWhiteListService = sysWhiteListService;
    }
    public void testGetByPhoneNo(){
        this.sysWhiteListService.queryByPhoneNo("13526352127");
    }
}
