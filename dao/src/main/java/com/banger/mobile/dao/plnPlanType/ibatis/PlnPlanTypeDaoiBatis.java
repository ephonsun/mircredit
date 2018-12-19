/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划模版类型dao
 * Author     :cheny
 * Create Date:2012-7-17
 */
package com.banger.mobile.dao.plnPlanType.ibatis;

import java.util.List;

import com.banger.mobile.dao.plnPlanType.PlnPlanTypeDao;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnPlanType;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author cheny
 * @version $Id: PlnPlanTypeiBatis.java,v 0.1 2012-7-17 上午9:57:18 cheny Exp $
 */
public class PlnPlanTypeDaoiBatis extends GenericDaoiBatis implements PlnPlanTypeDao{

    /**
     * 
     */
    public PlnPlanTypeDaoiBatis() {
        super(PlnPlanType.class);
    }
    /**
     * @param persistentClass
     */
    public PlnPlanTypeDaoiBatis(Class persistentClass) {
        super(PlnPlanType.class);
    }
    
    /**
     * 获得所有模板类型
     */
    public List<PlnPlanType> getAllPlnPlanType(){
        return this.getSqlMapClientTemplate().queryForList("getAllPlnPlanType");
    }
    
    /***
     * 根据Id查询模板类型
     */
    public PlnPlanType getPlnPlanTypeById(Integer id){
        return (PlnPlanType) this.getSqlMapClientTemplate().queryForObject("getPlanTypeById",id);
    }
    
}