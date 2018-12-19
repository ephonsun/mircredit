/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-7-16
 */
package com.banger.mobile.service;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnReportTemplate;
import com.banger.mobile.domain.model.PlnReportTemplate.PlnReportTemplateBean;
import com.banger.mobile.facade.BaseServiceTestCase;
import com.banger.mobile.facade.plnReportTemplate.PlnReportTemplateService;

/**
 * @author cheny
 * @version $Id: PlnReportTemplateServiceTest.java,v 0.1 2012-7-16 上午11:20:42 cheny Exp $
 */
public class PlnReportTemplateServiceTest extends BaseServiceTestCase{

    private PlnReportTemplateService plnReportTemplateService;

    public void setPlnReportTemplateService(PlnReportTemplateService plnReportTemplateService) {
        this.plnReportTemplateService = plnReportTemplateService;
    }
    /**
     * 测试是否为空
     * @throws Exception
     */
    public void testServiceNotNull() throws Exception{
        assertNotNull(plnReportTemplateService);
    }
    /**
     * 测试分页
     * @throws Exception
     */
    public void testgetPlnReportTemplatePage() throws Exception{
        PlnReportTemplate plnReportTemplate = new PlnReportTemplate();
        Page page = new Page();
        PageUtil<PlnReportTemplateBean> List = plnReportTemplateService.getPlnReportTemplatePage(plnReportTemplate, page);
        log.info("total count======:" +  List.getPage().getTotalRowsAmount());
        log.info("total page======:" + List.getPage().getTotalPages());
        log.info("page size======:" + List.getItems().size());
    }
    
}
