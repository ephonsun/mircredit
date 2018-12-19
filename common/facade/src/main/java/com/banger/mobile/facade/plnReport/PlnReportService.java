/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划报告service
 * Author     :yujh
 * Create Date:Jul 25, 2012
 */
package com.banger.mobile.facade.plnReport;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.plnReport.PlnReport;
import com.banger.mobile.domain.model.plnReport.PlnReportInfo;

/**
 * @author yujh
 * @version $Id: PlnReportService.java,v 0.1 Jul 25, 2012 2:07:33 PM Administrator Exp $
 */
public interface PlnReportService {
    /**
     * 添加报告
     * @param report
     */
    public int addPlnReport(PlnReport report);
    /**
     * 删除报告
     * @param id
     */
    public void deletePlnReport(String id);
    /**
     * 分页查询
     * @param map
     * @param page
     * @return
     */
    public PageUtil<PlnReportInfo> getAllReport(Map<String,Object>map,Page page);
    /**
     * id查询
     * @param id
     * @return
     */
    public PlnReport getReportById(int id);

    /**
     * 根据报告ID查询规划报告
     */
    public PlnReportInfo getPlnReportInfoById(Integer reportId);
    
    /**
     * 根据规划模板ID查询规划报告成生数量
     */
    public Integer getPlnReportByPlanId(Integer planId);
    
    
}
