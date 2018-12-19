/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-6-11
 */
package com.banger.mobile.dao;

import com.banger.mobile.dao.unreadMessage.CrmUnreadMessageDao;
import com.banger.mobile.domain.model.unreadMessage.CrmUnreadMessage;

/**
 * @author yangyang
 * @version $Id: CrmUnreadMessageTest.java,v 0.1 2012-6-11 下午4:21:37 yangyong Exp $
 */
public class CrmUnreadMessageTest extends BaseDaoTestCase{
    
    private CrmUnreadMessageDao crmUnreadMessageDao;

    public void setCrmUnreadMessageDao(CrmUnreadMessageDao crmUnreadMessageDao) {
        this.crmUnreadMessageDao = crmUnreadMessageDao;
    }
    
    /**
     * 测试dao是否为空
     * @throws Exception
     */
    public void testDaoNotNull() throws Exception {
        assertNotNull(crmUnreadMessageDao);
    }
    
    public void testqueryCrmUnreadMessage(){
        CrmUnreadMessage cm=crmUnreadMessageDao.queryCrmUnreadMessage(100);
        System.out.println("未接来电:"+cm.getMissingCall());
    }
}
