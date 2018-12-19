/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :liyb
 * Create Date:2012-10-19
 */
package com.banger.mobile.service;

import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.task.TskTaskTargetService;

/**
 * @author liyb
 * @version $Id: TskTaskTargetServiceTest.java,v 0.1 2012-10-19 上午11:19:44 liyb Exp $
 */
public class TskTaskTargetServiceTest extends BaseServiceTestCase {

    private TskTaskTargetService tskTaskTargetService;

    public void setTskTaskTargetService(TskTaskTargetService tskTaskTargetService) {
        this.tskTaskTargetService = tskTaskTargetService;
    }
    
    public void testGetTaskTargetDateCustomer(){
        Integer count=tskTaskTargetService.getTaskTargetDateCustomer(22);
        System.err.println(count);
        if(count>0){
//            System.err.println("修改："+tskTaskTargetService.updateTargetCustomerById(22, 33, 1));
            tskTaskTargetService.updateParentTaskCustomerById(22, 33,1,0);
        }
    }
}
