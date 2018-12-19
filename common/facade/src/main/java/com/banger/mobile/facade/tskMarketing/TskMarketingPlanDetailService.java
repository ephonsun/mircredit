/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销计划详情service接口
 * Author     :yuanme
 * Create Date:2012-12-27
 */
package com.banger.mobile.facade.tskMarketing;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.pdtProduct.PdtProduct;
import com.banger.mobile.domain.model.tskMarketing.TskMarketing;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingPlanDetail;

/**
 * @author yuanme
 * @version $Id: TskMarketingPlanDetailService.java,v 0.1 2012-12-27 上午10:11:58 Administrator Exp $
 */
public interface TskMarketingPlanDetailService {

    /**
     * 查询营销计划详情 by id
     * @param parameterMap
     * @return
     */
    TskMarketingPlanDetail getMarketingPlanDetailById(Integer marketingPlanDetailId);

    /**
     * 查询营销计划详情
     * @param parameterMap
     * @return
     */
    List<TskMarketingPlanDetail> getMarketingPlanDetail(Map<String, Object> parameterMap);

    /**
     * 批量更新计划详情
     * @param list
     */
    void batchUpdate(List<TskMarketingPlanDetail> list);

    /**
     * 分页任务对应产品列表
     * @param parameterMap
     * @return
     */
    PageUtil<PdtProduct> getMarketingPlanProductPage(Map<String, Object> parameterMap, Page page);

    /**
     * 统计用户任务销售额
     * @param pMap
     * @return
     */
    BigDecimal getMarketingProductSumByUserId(Map<String, Object> pMap);

    /**
     * 批量新增
     * @param list
     */
    void batchInsert(List<TskMarketingPlanDetail> list);

    /**
     * 根据任务取得产品指标
     * @param t
     * @return
     */
    String getProductTarget(TskMarketing t);

    /**
     * 根据任务查询营销目标
     * @param conds
     * @return
     */
    BigDecimal getMarketingTarget(Integer userId, Integer marketingId);

    /**
     *  根据任务得到 日均剩余营销额=剩余营销目标/剩余日期
     * @param marketingTarget
     * @param t
     * @return
     */
    BigDecimal getDaysRemainTarget(Integer userId, BigDecimal marketingTarget, TskMarketing t,
                                   BigDecimal saleMoney);

    /**
     * 产品已销售额
     * @param userId
     * @param marketingId
     * @return
     */
    BigDecimal getSaleMoney(Integer userId, TskMarketing t);

    /**
     * 拟营销额
     * @param userId
     * @param t
     * @return
     */
    BigDecimal getPlanedTarget(Integer userId, TskMarketing t);

    /**
     * 用户当天产品销售额
     * @param productId
     * @param executeUserId
     * @return
     */
    BigDecimal getTodaySaleMoney(Integer productId, Integer executeUserId);
    
    BigDecimal getTodaySaleMoney(Integer productId, Integer executeUserId,Date date);

    /**
     * 根据任务得到任务提示
     * @param d
     * @return
     */
    TskMarketingPlanDetail getTipsByMarketing(TskMarketingPlanDetail d);

    /**
     * 工作台--任务提醒--今天营销计划
     * @return
     */
    public Integer getTodayPlanCount(Integer userId);
}
