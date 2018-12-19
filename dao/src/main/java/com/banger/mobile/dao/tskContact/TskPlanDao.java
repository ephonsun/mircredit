/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-11-6
 */
package com.banger.mobile.dao.tskContact;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.tskContact.TskPlan;
import com.banger.mobile.domain.model.tskContact.TskPlanCustListBean;
import com.banger.mobile.domain.model.tskContact.TskPlanListBean;
import com.banger.mobile.domain.model.tskContact.TskPlanSelectBean;
import com.banger.mobile.domain.model.tskContact.TskTaskPlanReportBean;
import com.banger.mobile.domain.model.tskTaskPlan.TskTaskPlanListBean;


/**
 * @author cheny
 * @version $Id: TskTaskPlanDao.java,v 0.1 2012-11-6 下午2:17:40 cheny Exp $
 */
public interface TskPlanDao {
	/**
     * 全景展示所有的联系计划
     */
    public List<TskTaskPlanListBean> showTaskPlanViewList(Map<String, Object> map);
    
	/**
     * 所有的联系计划
     */
    public PageUtil<TskPlanListBean> getAllTaskPlan(Map<String, Object> map, Page page);
    /**
     * 查询联系计划对象
     */
    public TskPlan getPlanByTskPlan(TskPlan plan);
    /**
     * 新增计划
     */
    public int insertTskPlan(TskPlan plan);
    /**
     * 选择客户列表分页
     */
    public PageUtil<TskPlanSelectBean> getSelectCustList(Map<String, Object> parameters,Page page);
    /**
     * 制定计划客户列表分页
     */
    public PageUtil<TskPlanCustListBean> getTskPlanCustBeanList(Map<String, Object> parameters,Page page);
    /**
     * 计划的有效联系量
     */
    public int getTaskPlanActiveCount(int planId);
    /**
     * 任务的联系量
     */
    public Integer getTaskConnCount(Map<String,Object> map);
    /**
     * 计划执行情况统计报表
     */
    public List<TskTaskPlanReportBean> getTaskPlanReportList(Map<String, Object> parameters);
    
    /**
     * 选择全部联系客户
     */
    public List<String> getTskPlanSelectCustIds(Map<String, Object> parameters);
}
