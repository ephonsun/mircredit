/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划报告模板
 * Author     :cheny
 * Create Date:2012-7-16
 */
package com.banger.mobile.facade.plnReportTemplate;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnPlanType;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnReportTemplate;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnReportTemplateBean;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnReportTemplateVar;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnVarType;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnVarTypeSub;
import com.banger.mobile.domain.model.customer.CrmCustomer;
import com.banger.mobile.domain.model.plnFastPlan.PlnFastPlan;
import com.banger.mobile.domain.model.user.SysUser;

/**
 * @author cheny
 * @version $Id: PlnReportTemplateService.java,v 0.1 2012-7-16 上午11:11:05 cheny Exp $
 */
public interface PlnReportTemplateService {

    /**
     * 理财规划模板分页
     */
    public PageUtil<PlnReportTemplateBean> getPlnReportTemplatePage(PlnReportTemplate plnReportTemplate, Page page);
    /**
     * 查询所有规划模板类型
     */
    public List<PlnPlanType> getAllPlnPlanType();
    
    /**
     * 根据Id查询规划模板类型
     */
    public PlnPlanType getPlnPlanTypeById(Integer id);
    
    /**
     * 查询单个模板
     */
    public PlnReportTemplate getPlanTemplate(Map<String,Object> map);
    
    /**
     * 编辑模板验证
     */
    public PlnReportTemplate updatePlanTemplateValidate(Map<String,Object> map);
    /**
     * 查询模板集合
     */
    public List<PlnReportTemplate> getPlanTemplateList(Map<String,Object> map);
    
    /**
     * 新增理财规划模板
     */
    public Integer insertPlanTemplate(PlnReportTemplate plnReportTemplate);
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
    
    /**
     * 模板内容解析
     */
    public String replaceContent(String templateContent,SysUser user,PlnFastPlan fastPlan,String valTypeName,List<PlnReportTemplateVar> templateVarList);
    
}
