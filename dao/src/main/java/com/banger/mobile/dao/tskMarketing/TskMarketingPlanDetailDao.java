/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-12-26
 */
package com.banger.mobile.dao.tskMarketing;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.pdtProduct.PdtProduct;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingPlanDetail;

/**
 * @author yuanme
 * @version $Id: TskMarketingPlanDetailDao.java,v 0.1 2012-12-26 下午5:10:54 Administrator Exp $
 */
public interface TskMarketingPlanDetailDao {

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
     * 分页查询任务对应产品列表
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
     * 查询营销计划详情 by id
     * @param marketingPlanDetailId
     * @return
     */
    TskMarketingPlanDetail getMarketingPlanDetailById(Integer marketingPlanDetailId);

    /**
     * 用户当天产品销售额
     * @param parameterMap
     * @return
     */
    BigDecimal getTodaySaleMoney(Map<String, Object> parameterMap);
    
    BigDecimal getTodayMarkettingProductSaleMoney(Map<String, Object> parameterMap);

}
