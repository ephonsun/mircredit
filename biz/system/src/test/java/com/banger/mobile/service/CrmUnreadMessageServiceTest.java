/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-7-3
 */
package com.banger.mobile.service;

import com.banger.mobile.domain.model.unreadMessage.CrmUnreadMessage;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.unreadMessage.CrmUnreadMessageService;

/**
 * @author yangyang
 * @version $Id: CrmUnreadMessageServiceTest.java,v 0.1 2012-7-3 下午5:29:59 yangyong Exp $
 */
public class CrmUnreadMessageServiceTest extends BaseServiceTestCase {

    private CrmUnreadMessageService    crmUnreadMessageService;

    public CrmUnreadMessageService getCrmUnreadMessageService() {
        return crmUnreadMessageService;
    }

    public void setCrmUnreadMessageService(CrmUnreadMessageService crmUnreadMessageService) {
        this.crmUnreadMessageService = crmUnreadMessageService;
    }

    /**
     * 测试Manager是否为空
     * @throws Exception
     */
    public void testServiceNotNull() throws Exception {
        assertNotNull(crmUnreadMessageService);
    }
    
    
    /**
     * 
     * @throws Exception
     */
    public void testQueryCrmUnreadMessage() throws Exception {
        CrmUnreadMessage cm=crmUnreadMessageService.queryCrmUnreadMessage(596);
        log.info("MissingCall size====:" +cm.getUnreadMessage()+"sdfdsf:"+cm.getUnreadMessage());
    }
    
    
    
    
  
}
