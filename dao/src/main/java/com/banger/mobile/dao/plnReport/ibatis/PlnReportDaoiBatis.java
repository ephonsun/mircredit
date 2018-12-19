/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划报告dao
 * Author     :yujh
 * Create Date:Jul 25, 2012
 */
package com.banger.mobile.dao.plnReport.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.plnReport.PlnReportDao;
import com.banger.mobile.domain.model.plnReport.PlnReport;
import com.banger.mobile.domain.model.plnReport.PlnReportInfo;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yujh
 * @version $Id: PlnReportDaoiBatis.java,v 0.1 Jul 25, 2012 11:10:24 AM Administrator Exp $
 */
public class PlnReportDaoiBatis extends GenericDaoiBatis implements PlnReportDao{


    public PlnReportDaoiBatis(Class persistentClass) {
        super(PlnReport.class);
    }
    public PlnReportDaoiBatis(){
        super(PlnReport.class);
    }

    /**
     * 添加报告
     * @param report
     */
    public int addReport(PlnReport report) {
        return (Integer) this.getSqlMapClientTemplate().insert("insertPlnReport", report);
    }

    /**
     * 删除报告
     * @param id
     */
    public void deleteReport(String id) {
        this.getSqlMapClientTemplate().delete("deletePlnReport", id);
    }

    /**
     * 分页查询
     * @param map
     * @param page
     * @return
     */
    public PageUtil<PlnReportInfo> getAllReport(Map<String, Object> map, Page page) {
        List<PlnReportInfo> list = (List<PlnReportInfo>)this.findQueryPage("getAllPlnReport", "getAllPlnReportCount", map, page);
        if(list==null){
            list=new ArrayList<PlnReportInfo>();
        }
        return new PageUtil<PlnReportInfo>(list,page);
    }
    /**
     * id查询
     * @return
     * @see com.banger.mobile.dao.plnReport.PlnReportDao#getReportById()
     */
    public PlnReport getReportById(int id) {
        return (PlnReport)this.getSqlMapClientTemplate().queryForObject("getReportById", id);
    }

    /**
     * 根据报告ID查询规划报告
     */
    public PlnReportInfo getPlnReportInfoById(Integer reportId) {
        return (PlnReportInfo) this.getSqlMapClientTemplate().queryForObject("GetPlnReportInfoById",reportId);
    }

    public Integer getPlnReportByPlanId(Integer planId) {
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getPlnReportByPlanId",planId);
    }

}
