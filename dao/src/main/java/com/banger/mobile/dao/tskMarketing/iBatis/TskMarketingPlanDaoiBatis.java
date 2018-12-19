/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务计划 dao 实现类
 * Author     :yuanme
 * Create Date:2012-12-26
 */
package com.banger.mobile.dao.tskMarketing.iBatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.tskMarketing.TskMarketingPlanDao;
import com.banger.mobile.domain.model.tskMarketing.TskMarketing;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingPlan;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingPlanReport;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yuanme
 * @version $Id: TskMarketingPlanDaoiBatis.java,v 0.1 2012-12-26 下午5:14:57 Administrator Exp $
 */
public class TskMarketingPlanDaoiBatis extends GenericDaoiBatis implements TskMarketingPlanDao {

    public TskMarketingPlanDaoiBatis() {
        super(TskMarketingPlan.class);
    }

    /**
     * 根据条件查找计划主表
     * @param parameterMap
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingPlanDao#getTskMarketingPlanListByMap(java.util.Map)
     */
    public List<TskMarketingPlan> getTskMarketingPlanListByMap(Map<String, Object> parameterMap) {
        return this.getSqlMapClientTemplate().queryForList("getTskMarketingPlanListByMap",
                parameterMap);
    }

    /**
     * 新增计划主表
     * @param plan
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingPlanDao#addTskMarketingPlan(com.banger.mobile.domain.model.tskMarketing.TskMarketingPlan)
     */
    public void addTskMarketingPlan(TskMarketingPlan plan) {
        this.getSqlMapClientTemplate().insert("insertTskMarketingPlan", plan);
    }

    /**
     * 根据ID查找计划主表
     * @param planId
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingPlanDao#getTskMarketingPlanById(java.lang.Integer)
     */
    public TskMarketingPlan getTskMarketingPlanById(Integer planId) {
        return (TskMarketingPlan) this.getSqlMapClientTemplate().queryForObject(
                "getTskMarketingPlanById", planId);
    }

    /**
     * 分页查询
     * @param parameterMap
     * @param page
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingPlanDao#getMarketingPlanPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<TskMarketingPlan> getMarketingPlanPage(Map<String, Object> parameterMap,
                                                           Page page) {
        ArrayList<TskMarketingPlan> list = (ArrayList<TskMarketingPlan>) this.findQueryPage(
                "getMarketingPlanPage", "getMarketingPlanPageCount", parameterMap, page);
        return new PageUtil<TskMarketingPlan>(list, page);
    }

    /**
     * 根据条件查找计划内包含产品数量
     * @param parameterMap
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingPlanDao#getProductCountByMap(java.util.Map)
     */
    public Integer getProductCountByMap(Map<String, Object> parameterMap) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getProductCountByMap",
                parameterMap);
    }

    /**
     * 报表数据
     * @param parameterMap
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingPlanDao#getMarketingPlanReport(java.util.Map)
     */
    public List<TskMarketingPlanReport> getMarketingPlanReport(Map<String, Object> parameterMap) {
        return this.getSqlMapClientTemplate().queryForList("getMarketingPlanReport", parameterMap);
    }

    /**
     * 报表关联任务
     * @param parameterMap
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingPlanDao#getReportRelativeMarketing(java.util.Map)
     */
    public List<TskMarketing> getReportRelativeMarketing(Map<String, Object> parameterMap) {
        return this.getSqlMapClientTemplate().queryForList("getReportRelativeMarketing", parameterMap);
    }

    /**
     * 查询营销计划产品是否存在
     * @param marketingId
     * @param productId
     * @return
     */
    public Integer getTaskPlanProductCount(Integer marketingId,Integer productId) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("planId", marketingId);
        map.put("productId", productId);
        return (Integer) this.getSqlMapClientTemplate().queryForObject("GetTaskPlanProductCount",map);
    }

    /**
     * 查询营销任务是否存在营销计划
     * @param marketingId
     * @return
     */
    public Integer getTskPlanByMarketingId(Integer marketingId) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("marketingId", marketingId);
        return (Integer) this.getSqlMapClientTemplate().queryForObject("GetTskPlanByMarketingId",map);
    }
}
