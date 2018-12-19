/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-11-6
 */
package com.banger.mobile.dao.tskContact.iBatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.tskContact.TskPlanDao;
import com.banger.mobile.domain.model.tskContact.TskPlan;
import com.banger.mobile.domain.model.tskContact.TskPlanCustListBean;
import com.banger.mobile.domain.model.tskContact.TskPlanListBean;
import com.banger.mobile.domain.model.tskContact.TskPlanSelectBean;
import com.banger.mobile.domain.model.tskContact.TskTaskListBean;
import com.banger.mobile.domain.model.tskContact.TskTaskPlanReportBean;
import com.banger.mobile.domain.model.tskTaskPlan.TskTaskPlanListBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;



/**
 * @author cheny
 * @version $Id: TskTaskPlanDaoiBatis.java,v 0.1 2012-11-6 下午2:18:25 cheny Exp $
 */
public class TskPlanDaoiBatis extends GenericDaoiBatis implements TskPlanDao{

    public TskPlanDaoiBatis() {
        super(TskPlan.class);
    }

    /**
     *
     * @param persistentClass
     */
    public TskPlanDaoiBatis(Class persistentClass) {
        super(TskPlan.class);
    }

    /**
     * 全景展示所有的联系计划
     * @param map
     * @return
     */
    public List<TskTaskPlanListBean> showTaskPlanViewList(Map<String, Object> map){
        this.callProducure(map);//调用存储过程
        return this.getSqlMapClientTemplate().queryForList("getAllTaskPlan", map);
    }


    /**
     * 所有的联系计划
     * @param map
     * @param page
     * @return
     * @see com.banger.mobile.dao.tskTaskPlan.TskTaskPlanDao#getAllTaskPlan(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<TskPlanListBean> getAllTaskPlan(Map<String, Object> map, Page page) {
        this.callProducure(map);//调用存储过程
        ArrayList<TskPlanListBean> list = (ArrayList<TskPlanListBean>) this.findQueryPage(
                "getAllTaskPlan", "getAllTaskPlanCount", map, page);
        if (list == null) {
            list = new ArrayList<TskPlanListBean>();
        }
        return new PageUtil<TskPlanListBean>(list, page);
    }
    /**
     * 调用存储过程
     *
     * @see com.banger.mobile.dao.tskTaskPlan.TskTaskPlanDao#callProducure()
     */
    public void callProducure(Map<String,Object> map) {
        this.getSqlMapClientTemplate().queryForList("callProducure",map);
    }

    /**
     * 查询联系计划对象
     */
    public TskPlan getPlanByTskPlan(TskPlan plan){
        return (TskPlan) this.getSqlMapClientTemplate().queryForObject("getPlanByTskPlan",plan);
    }

    /**
     * 新增计划
     */
    public int insertTskPlan(TskPlan plan){
        return (Integer) this.getSqlMapClientTemplate().insert("insertTskPlan",plan);
    }
    /**
     * 选择客户列表分页
     */
    public PageUtil<TskPlanSelectBean> getSelectCustList(Map<String, Object> parameters,Page page) {
        ArrayList<TskPlanSelectBean> list = (ArrayList<TskPlanSelectBean>) this.findQueryPage(
                "getTskPlanSelectCustList", "getTskPlanSelectCustCount", parameters, page);
        if (list == null) {
            list = new ArrayList<TskPlanSelectBean>();
        }
        return new PageUtil<TskPlanSelectBean>(list, page);
    }
    /**
     * 制定计划客户列表分页
     */
    public PageUtil<TskPlanCustListBean> getTskPlanCustBeanList(Map<String, Object> parameters,Page page){
        ArrayList<TskPlanCustListBean> list = (ArrayList<TskPlanCustListBean>) this.findQueryPage(
                "getPlanCustBeanList", "getPlanCustBeanCount", parameters, page);
        if (list == null) {
            list = new ArrayList<TskPlanCustListBean>();
        }
        return new PageUtil<TskPlanCustListBean>(list, page);
    }
    /**
     * 计划的有效联系量
     */
    public int getTaskPlanActiveCount(int planId){
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getActivedContactCount",planId);
    }
    /**
     * 任务的联系量
     */
    public Integer getTaskConnCount(Map<String,Object> map){
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getTaskContactCount",map);
    }
    /**
     * 计划执行情况统计报表
     */
    public List<TskTaskPlanReportBean> getTaskPlanReportList(Map<String, Object> parameters) {
        return this.getSqlMapClientTemplate().queryForList("GetTaskPlanReportList",parameters);
    }
    /**
     * 选择全部联系客户
     */
    public List<String> getTskPlanSelectCustIds(Map<String, Object> parameters){
        return this.getSqlMapClientTemplate().queryForList("getTskPlanSelectCustIds",parameters);
    }
}
