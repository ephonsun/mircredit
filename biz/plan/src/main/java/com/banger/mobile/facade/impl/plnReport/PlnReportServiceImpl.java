/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划报告service
 * Author     :yujh
 * Create Date:Jul 25, 2012
 */
package com.banger.mobile.facade.impl.plnReport;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.plnReport.PlnReportDao;
import com.banger.mobile.domain.model.plnReport.PlnReport;
import com.banger.mobile.domain.model.plnReport.PlnReportInfo;
import com.banger.mobile.facade.plnReport.PlnReportService;

/**
 * @author yujh
 * @version $Id: PlnReportServiceImpl.java,v 0.1 Jul 25, 2012 2:14:13 PM Administrator Exp $
 */
public class PlnReportServiceImpl implements PlnReportService{
    private PlnReportDao    plnReportDao;
    
    public void setPlnReportDao(PlnReportDao plnReportDao) {
        this.plnReportDao = plnReportDao;
    }

    /**
     * 添加报告
     * @param report
     * @see com.banger.mobile.facade.plnReport.PlnReportService#addPlnReport(com.banger.mobile.domain.model.plnReport.PlnReport)
     */
    public int addPlnReport(PlnReport report) {
        report.setPlanTypeId(1);
        String content = report.getContent();
        String subContent1 = "<?xml:namespace prefix = o ns = \"urn:schemas-microsoft-com:office:office\" />";
        String subContent2 = "<?xml:namespace prefix = v ns = \"urn:schemas-microsoft-com:vml\" />";
        String subContent3 ="FONT-FAMILY: '宋体,Verdana,Arial', 'serif'";
        if (content.contains(subContent1)&&content.contains(subContent2)&&content.contains(subContent3)) {
            report.setContent(content.replace(subContent1, "&nbsp;").replace(subContent2, "&nbsp;").replaceAll(subContent3,""));
        }
        if(content.contains(subContent1)&&!content.contains(subContent2)){
            report.setContent(content.replace(subContent1, "&nbsp;").replaceAll(subContent3,""));
        }
        if(content.contains(subContent2)&&!content.contains(subContent1)){
            report.setContent(content.replace(subContent2, "&nbsp;").replaceAll(subContent3, ""));
        }

       return this.plnReportDao.addReport(report);
    }

    /**
     * 删除报告
     * @param id
     * @see com.banger.mobile.facade.plnReport.PlnReportService#deletePlnReport(java.lang.String)
     */
    public void deletePlnReport(String id) {
        this.plnReportDao.deleteReport(id);
    }


    /**
     * 分页查询
     * @param map
     * @param page
     * @return
     * @see com.banger.mobile.facade.plnReport.PlnReportService#getAllReport(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<PlnReportInfo> getAllReport(Map<String,Object>map,Page page) {
        return this.plnReportDao.getAllReport(map, page);
    }


    /**
     * id查询
     * @param id
     * @return
     * @see com.banger.mobile.facade.plnReport.PlnReportService#getReportById(int)
     */
    public PlnReport getReportById(int id) {
        return this.plnReportDao.getReportById(id);
    }

    /**
     * 根据报告ID查询规划报告
     */
    public PlnReportInfo getPlnReportInfoById(Integer reportId) {
        return this.plnReportDao.getPlnReportInfoById(reportId);
    }
    /**
     * 根据规划模板ID查询规划报告成生数量
     * plnReportDao
     * @param planId
     * @return
     * @see com.banger.mobile.facade.plnReport.PlnReportService#getPlnReportByPlanId(java.lang.Integer)
     */
    public Integer getPlnReportByPlanId(Integer planId) {
        return plnReportDao.getPlnReportByPlanId(planId);
    }

}
