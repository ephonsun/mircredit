/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :快速规划dao
 * Author     :yujh
 * Create Date:Jul 17, 2012
 */
package com.banger.mobile.dao.plnFastPlan;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.plnFastPlan.PlnFastPlan;
import com.banger.mobile.domain.model.plnFastPlan.PlnFastPlanInfo;

/**
 * @author yujh
 * @version $Id: PlnFastPlanDao.java,v 0.1 Jul 17, 2012 6:28:29 PM Administrator Exp $
 */
public interface PlnFastPlanDao {
    /**
     * 快速规划列表
     * @param map
     * @param page
     * @return
     */
    public PageUtil<PlnFastPlanInfo> getFastPlan(Map<String,Object> map,Page page);
    
    /**
     * 删除快速规划
     * @param id
     */
    public void deletePlnFastPlan(String id);
    /**
     * 根据用户id查询快速规划
     * @param id
     * @return
     */
    public List<PlnFastPlan> getPlnFastPlanByUserId(int id);

    /**
     * PAD 搜索规划
     * @param map
     * @return
     */
    public List<PlnFastPlan> findPlanForPad(Map<String, Object> map);

    /**
     * PAD 新增规划
     * @param plan
     * @return
     */
    public Integer insertPlanFromPad(PlnFastPlan plan);

    /**
     * PAD 编辑规划
     * @param plan
     */
    public void updatePlanFromPad(PlnFastPlan plan);
    
    /**
     * 根据id查询快速理财规划
     * @param id
     * @return
     */
    public PlnFastPlan getPlnFastPlanById(int id);
    
    /**
     * 服务端新增快速规划
     * @param plan
     * @return
     */
    public Integer insertPlanFromWeb(PlnFastPlan plan);
    
    /**
     * 编辑规划
     * @param plan
     */
    public void updatePlanByWeb(PlnFastPlan plan);
    
}
