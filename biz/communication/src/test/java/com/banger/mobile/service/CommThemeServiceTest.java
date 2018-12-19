/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :交流主题service测试类
 * Author     :liyb
 * Create Date:2012-12-24
 */
package com.banger.mobile.service;

import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.communication.CommThemeService;

/**
 * @author liyb
 * @version $Id: CommThemeServiceTest.java,v 0.1 2012-12-24 下午03:45:38 liyb Exp $
 */
public class CommThemeServiceTest extends BaseServiceTestCase {

    private CommThemeService commThemeService;

    public void setCommThemeService(CommThemeService commThemeService) {
        this.commThemeService = commThemeService;
    }
    
    /**
     * 测试Manager是否为空
     * @throws Exception
     */
    public void testServiceNotNull() throws Exception {
        assertNotNull(commThemeService);
    }
}
