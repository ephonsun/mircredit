/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划模板Dao
 * Author     :cheny
 * Create Date:2012-7-16
 */
package com.banger.mobile.dao.PlnReportTemplate;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnReportTemplate;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnReportTemplateBean;

/**
 * @author cheny
 * @version $Id: PlnReportTemplateDao.java,v 0.1 2012-7-16 上午10:00:30 cheny Exp $
 */
public interface PlnReportTemplateDao {

    /**
     * 理财规划报告模板 分页
     */
    public PageUtil<PlnReportTemplateBean> getPlnReportTemplatePage(Map<String, Object> parameters, Page page);
    
    /**
     * 新增理财规划报告模板
     */
    public Integer insertTemplate(PlnReportTemplate plnReportTemplate);
    /**
     * 查询单个模板
     */
    public PlnReportTemplate getPlanTemplate(Map<String,Object> map);
    /**
     * 查询模板集合
     */
    public List<PlnReportTemplate> getPlanTemplateList(Map<String,Object> map);
    /**
     * 编辑模板
     */
    public void updatePlanTemplate(PlnReportTemplate plnReportTemplate);
    /**
     * 删除模板
     */
    public void delPlanTemplateById(Integer tempId);
    /**
     * 启用停用模板
     */
    public void activePlanTemplate(Map<String,Object> map);
    
    /**
     * 编辑模板验证
     */
    public PlnReportTemplate updatePlanTemplateValidate(Map<String,Object> map);
}
