/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :service测试类
 * Author     :yujh
 * Create Date:Jun 4, 2012
 */
package com.banger.mobile.service;

import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.crmRecordRemind.CrmRecordRemindService;

/**
 * @author yujh
 * @version $Id: AnswerConfigServiceTest.java,v 0.1 Jun 4, 2012 4:50:21 PM Administrator Exp $
 */
public class CrmRecordRemindServiceTest extends BaseServiceTestCase{
    private CrmRecordRemindService  crmRecordRemindService;

    public void setCrmRecordRemindService(CrmRecordRemindService crmRecordRemindService) {
        this.crmRecordRemindService = crmRecordRemindService;
    }
    public void testNotNull(){
        assertNotNull(crmRecordRemindService);
    }
}
