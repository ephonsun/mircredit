/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-7-16
 */
package com.banger.mobile.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.PlnReportTemplate.PlnReportTemplateDao;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnReportTemplate;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnReportTemplateBean;

/**
 * @author cheny
 * @version $Id: PlnReportTemplateDaoTest.java,v 0.1 2012-7-16 上午11:04:08 cheny Exp $
 */
public class PlnReportTemplateDaoTest extends BaseDaoTestCase{

    private PlnReportTemplateDao plnReportTemplateDao;

    public void setPlnReportTemplateDao(PlnReportTemplateDao plnReportTemplateDao) {
        this.plnReportTemplateDao = plnReportTemplateDao;
    }
    
    /**
     * 测试dao是否为空
     * @throws Exception
     */
    public void testDaoNotNull() throws Exception {
        assertNotNull(plnReportTemplateDao);
    }
    /**
     * 测试分页
     * @throws Exception
     */
    public void testgetPlnReportTemplatePage() throws Exception{
      Map<String, Object> parameterMap = new HashMap<String, Object>();
      Page page = new Page();
      PageUtil<PlnReportTemplateBean> List = plnReportTemplateDao.getPlnReportTemplatePage(parameterMap, page);
      log.info("total count======:" +  List.getPage().getTotalRowsAmount());
      log.info("total page======:" + List.getPage().getTotalPages());
      log.info("page size======:" + List.getItems().size());
    }
    /**
     * 新增理财规划报告模板
     */
    public void testinsertTemplate(){
        PlnReportTemplate temp = new PlnReportTemplate();
        temp.setTemplateName("模板三");
        temp.setTemplateNo("000000");
        temp.setIntervalTypeId(1);
        temp.setPlanTypeId(1);
        temp.setIsActived(0);
        temp.setContent("adfa");
        temp.setIsDel(0);
        temp.setCreateUser(1);
//        plnReportTemplateDao.insertTemplate(temp);
        log.info("insert Temp id===="+temp.getTemplateId());
 
    }
    
    public void testgetPlanTemplate(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("intervalTypeId", "1");
//        PlnReportTemplate temp = plnReportTemplateDao.getPlanTemplate(map);
//        log.info("***************"+temp.getTemplateName());
    }
    public void testgetPlanTemplateList(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("intervalTypeId", "1");
        List<PlnReportTemplate> list= plnReportTemplateDao.getPlanTemplateList(map);
        log.info("=============="+list.size());
    }
}
