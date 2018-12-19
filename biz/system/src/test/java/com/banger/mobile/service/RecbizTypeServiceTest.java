/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :业务类型service测试类
 * Author     :liyb
 * Create Date:2012-5-17
 */
package com.banger.mobile.service;

import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.domain.model.recbistype.RecbizType;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.recbiztype.RecbizTypeService;

/**
 * @author liyb
 * @version $Id: RecbizTypeServiceTest.java,v 0.1 2012-5-17 下午05:21:29 liyb Exp $
 */
public class RecbizTypeServiceTest extends BaseServiceTestCase {
    private RecbizTypeService recbizTypeService;
    
    public void setRecbizTypeService(RecbizTypeService recbizTypeService) {
        this.recbizTypeService = recbizTypeService;
    }
    
    /**
     * 测试Manager是否为空
     * @throws Exception
     */
    public void testServiceNotNull() throws Exception {
        assertNotNull(recbizTypeService);
    }
    

}
