/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :快速规划service
 * Author     :Administrator
 * Create Date:Jul 18, 2012
 */
package com.banger.mobile.facade.plnFastPlan;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.plnFastPlan.PlnFastPlanInfo;
import com.banger.mobile.domain.model.plnFastPlan.PlnFastPlan;

/**
 * @author Administrator
 * @version $Id: PlnFastPlanService.java,v 0.1 Jul 18, 2012 1:12:52 PM Administrator Exp $
 */
public interface PlnFastPlanService {

	/**
	 * 快速规划列表
	 * @param map
	 * @param page
	 * @return
	 */
    public PageUtil<PlnFastPlanInfo> getAllPlnFastPlanInfo(Map<String, Object> map, Page page);

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
     * @param userId
     * @param input
     * @param planDateStart
     * @param planDateEnd
     * @return
     */
    public List<PlnFastPlan> findPlanForPad(Integer userId, String input, String planDateStart,
                                            String planDateEnd);

    /**
     * PAD 保存规划
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
     * @return
     */
    public PlnFastPlan getPlnFastPlanById(int id);

    /**
     * 通过web端新建规划
     * @return
     */
    public Integer insertFastPlanFromWeb(PlnFastPlan plan);
    /**
     * web端编辑快速规划
     * @param plan
     */
    public void updateFastPlanByWeb(PlnFastPlan plan);
}
