/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划模版变量
 * Author     :cheny
 * Create Date:2012-7-19
 */
package com.banger.mobile.dao.plnTemplateVar;

import java.util.List;

import com.banger.mobile.domain.model.PlnReportTemplate.PlnReportTemplateVar;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnVarType;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnVarTypeSub;

/**
 * @author cheny
 * @version $Id: plnTemplateVarDao.java,v 0.1 2012-7-19 下午6:20:01 cheny Exp $
 */
public interface PlnTemplateVarDao {

    /**
     * 查询所有的变量
     */
    public List<PlnReportTemplateVar> getAllVarList();
    /**
     * 查询所有的二级分类
     */
    public List<PlnVarTypeSub> getTwoSubList();
    /**
     * 查询所有的一级分类
     */
    public List<PlnVarType> getOneSubList();
    
}
