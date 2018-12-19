/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2012-5-24
 */
package com.banger.mobile.dao;

import java.util.Date;
import java.util.HashMap;

import com.banger.mobile.dao.customer.AdvancedCustomerDao;
import com.banger.mobile.dao.sysWebFlowControl.SysWebFlowControlDao;
import com.banger.mobile.domain.model.sysWebFlowControl.SysWebFlowControl;

/**
 * @author liyb
 * @version $Id: AdvancedCustomerTest.java,v 0.1 2012-5-24 下午04:39:07 liyb Exp $
 */
public class SysWebFlowControlTest extends BaseDaoTestCase {
    private SysWebFlowControlDao sysWebFlowControlDao;

    public void setSysWebFlowControlDao(SysWebFlowControlDao sysWebFlowControlDao) {
        this.sysWebFlowControlDao = sysWebFlowControlDao;
    }

    /**
     * 测试dao是否为空
     * @throws Exception
     */
//    public void testDaoNotNull() {
//        assertNotNull(sysWebFlowControlDao);
//    }
//    public void testAdd(){
//        SysWebFlowControl flow=new SysWebFlowControl();
//        flow.setDeptId(1);
//        flow.setIsActive(1);
//        flow.setMaxBps(100);
//        this.sysWebFlowControlDao.insertFlowControl(flow);
//    }
    public void testUpd(){
        SysWebFlowControl flow=new SysWebFlowControl();
        flow.setDeptId(3);
        flow.setIsActive(0);
        flow.setMaxBps(1000);
        this.sysWebFlowControlDao.updateFlowControl(flow);
    }
    
    
}
