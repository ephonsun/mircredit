/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:Jul 25, 2012
 */
package com.banger.mobile.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.dao.plnReport.PlnReportDao;
import com.banger.mobile.domain.model.plnReport.PlnReport;

/**
 * @author Administrator
 * @version $Id: PlnReportDaoTest.java,v 0.1 Jul 25, 2012 1:58:03 PM Administrator Exp $
 */
public class PlnReportDaoTest extends BaseDaoTestCase {
    private PlnReportDao plnReportDao;

    public void setPlnReportDao(PlnReportDao plnReportDao) {
        this.plnReportDao = plnReportDao;
    }
//    public void testNotNull(){
//        assertNotNull(plnReportDao);
//    }
//    public void testAdd(){
//        PlnReport report = new PlnReport();
//        report.setContent("200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字" +
//        		"200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字" +
//        		"200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字" +
//        		"200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字200字");
//        report.setPlanId(1);
//        report.setTargetDate(new Date());
//        this.plnReportDao.addReport(report);
//    }
//     public void testGet(){
//         Map<String,Object> map = new HashMap<String,Object>();
//         Page page= new Page();
//         this.plnReportDao.getAllReport(map, page);
//     }
     public void testQuery(){
         this.plnReportDao.getReportById(1);
     }
}
