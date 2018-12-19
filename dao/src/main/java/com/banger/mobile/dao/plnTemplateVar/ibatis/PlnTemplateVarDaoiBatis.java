/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划模版变量
 * Author     :cheny
 * Create Date:2012-7-19
 */
package com.banger.mobile.dao.plnTemplateVar.ibatis;

import java.util.List;

import com.banger.mobile.dao.plnTemplateVar.PlnTemplateVarDao;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnReportTemplateVar;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnVarType;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnVarTypeSub;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author cheny
 * @version $Id: PlnTemplateVarDaoiBatis.java,v 0.1 2012-7-19 下午6:20:42 cheny Exp $
 */
public class PlnTemplateVarDaoiBatis extends GenericDaoiBatis implements PlnTemplateVarDao{

    public PlnTemplateVarDaoiBatis() {
        super(PlnReportTemplateVar.class);
    }
    /**
     * @param persistentClass
     */
    public PlnTemplateVarDaoiBatis(Class persistentClass) {
        super(PlnReportTemplateVar.class);
    }
    
    /**
     * 查询所有的变量
     */
    public List<PlnReportTemplateVar> getAllVarList(){
        return this.getSqlMapClientTemplate().queryForList("getPlnVar");
    }
    /**
     * 查询所有的二级分类
     */
    public List<PlnVarTypeSub> getTwoSubList(){
        return this.getSqlMapClientTemplate().queryForList("getVarTypeSub");
    }
    /**
     * 查询所有的一级分类
     */
    public List<PlnVarType> getOneSubList(){
        return this.getSqlMapClientTemplate().queryForList("getAllPlnVarType");
    }
}
