/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销计划service接口
 * Author     :yuanme
 * Create Date:2012-12-27
 */
package com.banger.mobile.facade.tskMarketing;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.tskMarketing.TskMarketing;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingPlan;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingPlanReport;

/**
 * @author yuanme
 * @version $Id: TskMarketingPlanService.java,v 0.1 2012-12-27 上午10:11:20 Administrator Exp $
 */
public interface TskMarketingPlanService {

    /**
     * 根据条件查找计划
     * @param parameterMap
     * @return
     */
    List<TskMarketingPlan> getTskMarketingPlanListByMap(Map<String, Object> parameterMap);

    /**
     * 新增计划主表
     * @param plan
     */
    void addTskMarketingPlan(TskMarketingPlan plan);

    /**
     * 根据Id查找计划
     * @param planId
     * @return
     */
    TskMarketingPlan getTskMarketingPlanById(Integer planId);

    /**
     * 分页查询
     * @param parameterMap
     * @param page
     * @return
     */
    PageUtil<TskMarketingPlan> getMarketingPlanPage(Map<String, Object> parameterMap, Page page);

    /**
     * 根据条件查找计划内包含产品数量
     * @param parameterMap
     * @return
     */
    Integer getProductCountByMap(Map<String, Object> parameterMap);

    /**
     * 计划报表
     * @param parameterMap
     * @return
     */
    List<TskMarketingPlanReport> getMarketingPlanReport(Map<String, Object> parameterMap);

    /**
     * 报表相关任务
     * @param parameterMap
     * @return
     */
    List<TskMarketing> getReportRelativeMarketing(Map<String, Object> parameterMap);

    /**
     * 查询营销计划产品是否存在
     * @param marketingId
     * @param productId
     * @return
     */
    public Integer getTaskPlanProductCount(Integer marketingId,Integer productId);
    
    /**
     * 查询营销任务是否存在营销计划
     * @param marketingId
     * @return
     */
    public Integer getTskPlanByMarketingId(Integer marketingId);
}
