/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:Jul 25, 2012
 */
package com.banger.mobile.service;

import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.plnReport.PlnReportService;

/**
 * @author Administrator
 * @version $Id: PlnReportServiceTest.java,v 0.1 Jul 25, 2012 2:42:09 PM Administrator Exp $
 */
public class PlnReportServiceTest extends BaseServiceTestCase {
    private     PlnReportService    plnReportService;

    public void setPlnReportService(PlnReportService plnReportService) {
        this.plnReportService = plnReportService;
    }
    public void testNotNull(){
        assertNotNull(plnReportService);
    }
    
}
