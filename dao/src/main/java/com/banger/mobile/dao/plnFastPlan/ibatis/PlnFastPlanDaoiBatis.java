/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :快速规划dao
 * Author     :Administrator
 * Create Date:Jul 18, 2012
 */
package com.banger.mobile.dao.plnFastPlan.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.plnFastPlan.PlnFastPlanDao;
import com.banger.mobile.domain.model.plnFastPlan.PlnFastPlan;
import com.banger.mobile.domain.model.plnFastPlan.PlnFastPlanInfo;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author Administrator
 * @version $Id: PlnFastPlanDaoiBatis.java,v 0.1 Jul 18, 2012 9:19:34 AM Administrator Exp $
 */
public class PlnFastPlanDaoiBatis extends GenericDaoiBatis implements PlnFastPlanDao{

    public PlnFastPlanDaoiBatis(Class persistentClass) {
        super(PlnFastPlan.class);
    }
    public PlnFastPlanDaoiBatis(){
        super(PlnFastPlan.class);
    }
    /**
     * 快速规划列表
     * @param map
     * @param page
     * @return
     * @see com.banger.mobile.dao.plnFastPlan.PlnFastPlanDao#getFastPlan(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<PlnFastPlanInfo> getFastPlan(Map<String, Object> map, Page page) {
        List<PlnFastPlanInfo> list =(List<PlnFastPlanInfo>)this.findQueryPage("getAllFastPlanInfo", "getAllFastPlanInfoCount", map, page);
        if(list==null){
            list = new ArrayList<PlnFastPlanInfo>();
        }
        return new PageUtil<PlnFastPlanInfo>(list,page);
    }
    /**
     * 删除快速规划
     * @param id
     * @see com.banger.mobile.dao.plnFastPlan.PlnFastPlanDao#deletePlnFastPlan(int)
     */
    public void deletePlnFastPlan(String id) {
        this.getSqlMapClientTemplate().update("deletePlnFastPlan",id);
    }
    /**
     * 根据用户id查询快速规划
     * @param id
     * @return
     * @see com.banger.mobile.dao.plnFastPlan.PlnFastPlanDao#getPlnFastPlanByUserId(int)
     */
    public List<PlnFastPlan> getPlnFastPlanByUserId(int id) {
        return this.getSqlMapClientTemplate().queryForList("getPlnFastPlanByUserId", id);
    }

    /**
     *
     * @param map
     * @return
     * @see com.banger.mobile.dao.plnFastPlan.PlnFastPlanDao#findPlanForPad(java.util.Map)
     */
    public List<PlnFastPlan> findPlanForPad(Map<String, Object> map) {
        return this.getSqlMapClientTemplate().queryForList("findPlanForPad", map);
    }

    /**
     *
     * @param plan
     * @return
     * @see com.banger.mobile.dao.plnFastPlan.PlnFastPlanDao#insertPlanFromPad(com.banger.mobile.domain.model.plnFastPlan.PlnFastPlan)
     */
    public Integer insertPlanFromPad(PlnFastPlan plan) {
        return (Integer) this.getSqlMapClientTemplate().insert("insertPlanFromPad", plan);
    }

    /**
     *
     * @param plan
     * @see com.banger.mobile.dao.plnFastPlan.PlnFastPlanDao#updatePlanFromPad(com.banger.mobile.domain.model.plnFastPlan.PlnFastPlan)
     */
    public void updatePlanFromPad(PlnFastPlan plan) {
        this.getSqlMapClientTemplate().update("updatePlanFromPad", plan);
    }
    /**
     * 根据id查询快速规划
     * @param id
     * @return
     * @see com.banger.mobile.dao.plnFastPlan.PlnFastPlanDao#getPlnFastPlanById(int)
     */
    public PlnFastPlan getPlnFastPlanById(int id) {
        return (PlnFastPlan)this.getSqlMapClientTemplate().queryForObject("getPlnFastPlanById", id);
    }
    /**
     * web端新增理财规划
     * @param plan
     * @return
     * @see com.banger.mobile.dao.plnFastPlan.PlnFastPlanDao#insertPlanFromWeb(com.banger.mobile.domain.model.plnFastPlan.PlnFastPlan)
     */
    public Integer insertPlanFromWeb(PlnFastPlan plan) {
        return (Integer)this.getSqlMapClientTemplate().insert("insertPlanFromWeb", plan);
    }
    /**
     * 便捷快速规划
     * @param plan
     * @see com.banger.mobile.dao.plnFastPlan.PlnFastPlanDao#updatePlanByWeb(com.banger.mobile.domain.model.plnFastPlan.PlnFastPlan)
     */
    public void updatePlanByWeb(PlnFastPlan plan) {
        this.getSqlMapClientTemplate().update("updatePlanByWeb", plan);
    }


}
