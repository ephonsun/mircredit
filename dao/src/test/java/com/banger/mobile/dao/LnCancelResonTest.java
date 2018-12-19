/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2012-5-24
 */
package com.banger.mobile.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.banger.mobile.dao.customer.AdvancedCustomerDao;
import com.banger.mobile.dao.loan.LnLoanTypeDao;
import com.banger.mobile.dao.microTask.TskMicroTaskTargetDao;
import com.banger.mobile.domain.model.microTask.TskMicroTaskTarget;

/**
 * @author liyb
 * @version $Id: AdvancedCustomerTest.java,v 0.1 2012-5-24 下午04:39:07 liyb Exp $
 */
public class LnCancelResonTest extends BaseDaoTestCase {
    private LnLoanTypeDao lnLoanTypeDao;

    public void setLnLoanTypeDao(LnLoanTypeDao lnLoanTypeDao) {
        this.lnLoanTypeDao = lnLoanTypeDao;
    }
    /**
     * 测试dao是否为空
     * @throws Exception
     */
    public void testDaoNotNull() throws Exception {
        assertNotNull(lnLoanTypeDao);
    }
    
    public void testLnLoanTypeDao(){
        lnLoanTypeDao.getLoanTypeByName("sdasd");
    }

}
