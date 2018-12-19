/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划模版类型dao
 * Author     :cheny
 * Create Date:2012-7-17
 */
package com.banger.mobile.dao.plnPlanType;

import java.util.List;

import com.banger.mobile.domain.model.PlnReportTemplate.PlnPlanType;

/**
 * @author cheny
 * @version $Id: PlnPlanTypeDao.java,v 0.1 2012-7-17 上午10:28:18 cheny Exp $
 */
public interface PlnPlanTypeDao {

    /**
     * 获得所有模板类型
     */
    public List<PlnPlanType> getAllPlnPlanType();
    
    /***
     * 根据Id查询模板类型
     */
    public PlnPlanType getPlnPlanTypeById(Integer id);
       
}
