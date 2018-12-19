/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :快速规划service
 * Author     :yujh
 * Create Date:Jul 18, 2012
 */
package com.banger.mobile.facade.impl.plnFastPlan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.plnFastPlan.PlnFastPlanDao;
import com.banger.mobile.domain.model.plnFastPlan.PlnFastPlan;
import com.banger.mobile.domain.model.plnFastPlan.PlnFastPlanInfo;
import com.banger.mobile.facade.plnFastPlan.PlnFastPlanService;

/**
 * @author yujh
 * @version $Id: PlnFastPlanServiceImpl.java,v 0.1 Jul 18, 2012 1:15:52 PM Administrator Exp $
 */
public class PlnFastPlanServiceImpl implements PlnFastPlanService{
    private     PlnFastPlanDao      plnFastPlanDao;
    public void setPlnFastPlanDao(PlnFastPlanDao plnFastPlanDao) {
        this.plnFastPlanDao = plnFastPlanDao;
    }
    /**
     * 快速规划列表
     * @param map
     * @param page
     * @return
     * @see com.banger.mobile.facade.plnFastPlan.PlnFastPlanService#getAllPlnFastPlanInfo(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<PlnFastPlanInfo> getAllPlnFastPlanInfo(Map<String, Object> map, Page page) {
        return this.plnFastPlanDao.getFastPlan(map, page);
    }
    /**
     * 删除快速规划
     * @param id
     * @see com.banger.mobile.facade.plnFastPlan.PlnFastPlanService#deletePlnFastPlan(int)
     */
    public void deletePlnFastPlan(String id) {
        this.plnFastPlanDao.deletePlnFastPlan(id);
    }
    /**
     * 根据用户id查询快速规划
     * @param id
     * @return
     * @see com.banger.mobile.facade.plnFastPlan.PlnFastPlanService#getPlnFastPlanByUserId(int)
     */
    public List<PlnFastPlan> getPlnFastPlanByUserId(int id) {
        return this.plnFastPlanDao.getPlnFastPlanByUserId(id);
    }
    
    /**
     * 
     * @param userId
     * @param input
     * @param planDateStart
     * @param planDateEnd
     * @return
     * @see com.banger.mobile.facade.plnFastPlan.PlnFastPlanService#findPlanForPad(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
     */
    public List<PlnFastPlan> findPlanForPad(Integer userId, String input, String planDateStart,
                                            String planDateEnd) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("input", input);
        map.put("planDateStart", planDateStart);
        map.put("planDateEnd", planDateEnd);
        return this.plnFastPlanDao.findPlanForPad(map);
    }

    /**
     * 
     * @param plan
     * @return
     * @see com.banger.mobile.facade.plnFastPlan.PlnFastPlanService#insertPlanFromPad(com.banger.mobile.domain.model.plnFastPlan.PlnFastPlan)
     */
    public Integer insertPlanFromPad(PlnFastPlan plan) {
        return plnFastPlanDao.insertPlanFromPad(plan);
    }

    /**
     * 
     * @param plan
     * @see com.banger.mobile.facade.plnFastPlan.PlnFastPlanService#updatePlanFromPad(com.banger.mobile.domain.model.plnFastPlan.PlnFastPlan)
     */
    public void updatePlanFromPad(PlnFastPlan plan) {
        plnFastPlanDao.updatePlanFromPad(plan);
    }
    /**
     * 根据id查询快速理财规划
     * @return
     * @see com.banger.mobile.facade.plnFastPlan.PlnFastPlanService#getPlnFastPlanById()
     */
    public PlnFastPlan getPlnFastPlanById(int id) {
        return this.plnFastPlanDao.getPlnFastPlanById(id);
    }
    /**
     * 通过web端新建快速规划
     * @return
     * @see com.banger.mobile.facade.plnFastPlan.PlnFastPlanService#insertFastPlanFromWeb()
     */
    public Integer insertFastPlanFromWeb(PlnFastPlan plan) {
        return this.plnFastPlanDao.insertPlanFromWeb(plan);
    }
    /**
     * 编辑
     * @param plan
     * @see com.banger.mobile.facade.plnFastPlan.PlnFastPlanService#updateFastPlanByWeb(com.banger.mobile.domain.model.plnFastPlan.PlnFastPlan)
     */
    public void updateFastPlanByWeb(PlnFastPlan plan) {
        this.plnFastPlanDao.updatePlanByWeb(plan);
    }

}

