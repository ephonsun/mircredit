/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-7-17
 */
package com.banger.mobile.dao;

import java.util.List;

import com.banger.mobile.dao.plnPlanType.PlnPlanTypeDao;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnPlanType;

/**
 * @author cheny
 * @version $Id: PlnPlanTypeDaoTest.java,v 0.1 2012-7-17 上午11:12:05 cheny Exp $
 */
public class PlnPlanTypeDaoTest extends BaseDaoTestCase{
 
    private PlnPlanTypeDao plnPlanTypeDao;

    public void setPlnPlanTypeDao(PlnPlanTypeDao plnPlanTypeDao) {
        this.plnPlanTypeDao = plnPlanTypeDao;
    }
    
    /**
     * 测试dao是否为空
     * @throws Exception
     */
    public void testDaoNotNull() throws Exception {
        assertNotNull(plnPlanTypeDao);
    }
    
    /**
     * 获得所有模板类型
     */
    public void testgetAllPlnPlanType(){
        List<PlnPlanType>  list= plnPlanTypeDao.getAllPlnPlanType();
        log.info(list.size());
    }
}
