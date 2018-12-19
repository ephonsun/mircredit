/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2013-1-10
 */
package com.banger.mobile.facade.tskMarketing;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.domain.model.tskMarketing.TskMarketingPlanReport;

/**
 * @author yuanme
 * @version $Id: TskMarketingPlanReportService.java,v 0.1 2013-1-10 下午2:52:16 Administrator Exp $
 */
public interface TskMarketingPlanReportService {

    /**
     * 计划报表导出
     * @param list
     * @param userName
     * @return
     */
    HSSFWorkbook exportPlanReportExcel(List<TskMarketingPlanReport> list, String userName);

}
