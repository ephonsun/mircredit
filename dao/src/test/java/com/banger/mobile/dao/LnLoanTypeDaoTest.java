/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2012-5-24
 */
package com.banger.mobile.dao;

import com.banger.mobile.dao.loan.LnCancelReasonDao;
import com.banger.mobile.domain.model.loan.LnCancelReason;

/**
 * @author liyb
 * @version $Id: AdvancedCustomerTest.java,v 0.1 2012-5-24 下午04:39:07 liyb Exp $
 */
public class LnLoanTypeDaoTest extends BaseDaoTestCase {
    private LnCancelReasonDao lnCancelReasonDao;

    public void setLnCancelReasonDao(LnCancelReasonDao lnCancelReasonDao) {
        this.lnCancelReasonDao = lnCancelReasonDao;
    }
    /**
     * 测试dao是否为空
     * @throws Exception
     */
    public void testDaoNotNull() throws Exception {
        assertNotNull(lnCancelReasonDao);
    }
    
    public void testLnLoanTypeDao(){
    	LnCancelReason lnCancelReason=new LnCancelReason ();
    	 lnCancelReason.setCancelReasonName("1564");
    	 lnCancelReason.setCreateUser(1);
    	lnCancelReasonDao.insertCancelReason(lnCancelReason);
    }

}
