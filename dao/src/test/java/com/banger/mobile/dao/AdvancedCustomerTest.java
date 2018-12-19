/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2012-5-24
 */
package com.banger.mobile.dao;

import java.util.HashMap;

import com.banger.mobile.dao.customer.AdvancedCustomerDao;

/**
 * @author liyb
 * @version $Id: AdvancedCustomerTest.java,v 0.1 2012-5-24 下午04:39:07 liyb Exp $
 */
public class AdvancedCustomerTest extends BaseDaoTestCase {
    private AdvancedCustomerDao advancedCustomerDao;

    public void setAdvancedCustomerDao(AdvancedCustomerDao advancedCustomerDao) {
        this.advancedCustomerDao = advancedCustomerDao;
    }
    /**
     * 测试dao是否为空
     * @throws Exception
     */
    public void testDaoNotNull() throws Exception {
        assertNotNull(advancedCustomerDao);
    }
    
    public void testGetAdvancedCustomer(){
        advancedCustomerDao.getAdvancedCustomerPage(new HashMap<String,Object>(), null);
    }
    
}
