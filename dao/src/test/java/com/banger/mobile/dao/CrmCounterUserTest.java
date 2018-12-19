/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yujh
 * Create Date:2012-5-24
 */
package com.banger.mobile.dao;

import java.util.HashMap;
import java.util.List;

import com.banger.mobile.dao.crmCounterUser.CrmCounterUserDao;
import com.banger.mobile.dao.customer.AdvancedCustomerDao;
import com.banger.mobile.domain.model.crmCounterUser.CrmCounterUser;

/**
 * @author yujh
 * @version $Id: AdvancedCustomerTest.java,v 0.1 2012-5-24 下午04:39:07 liyb Exp $
 */
public class CrmCounterUserTest extends BaseDaoTestCase {
    private CrmCounterUserDao crmCounterUserDao;
    
    public void setCrmCounterUserDao(CrmCounterUserDao crmCounterUserDao) {
        this.crmCounterUserDao = crmCounterUserDao;
    }

    /**
     * 测试dao是否为空
     * @throws Exception
     */
    public void testDaoNotNull() throws Exception {
        assertNotNull(crmCounterUserDao);
    }
    
    
    /**
     * 测试dao是否为空
     * @throws Exception
     */
    public void testgetAllCrmCounterUser() throws Exception {
         crmCounterUserDao.getAllCrmCounterUser("3");
    }
    
    
}
