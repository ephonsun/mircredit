/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务计划dao 实现类
 * Author     :yuanme
 * Create Date:2012-12-26
 */
package com.banger.mobile.dao.tskMarketing.iBatis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.tskMarketing.TskMarketingPlanDetailDao;
import com.banger.mobile.domain.model.pdtProduct.PdtProduct;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingPlanDetail;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author yuanme
 * @version $Id: TskMarketingPlanDetailDaoiBatis.java,v 0.1 2012-12-26 下午5:16:15 Administrator Exp $
 */
public class TskMarketingPlanDetailDaoiBatis extends GenericDaoiBatis implements
        TskMarketingPlanDetailDao {

    public TskMarketingPlanDetailDaoiBatis() {
        super(TskMarketingPlanDetail.class);
    }

    /**
     *
     * @param parameterMap
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingPlanDetailDao#getMarketingPlanDetail(java.util.Map)
     */
    public List<TskMarketingPlanDetail> getMarketingPlanDetail(Map<String, Object> parameterMap) {
        return this.getSqlMapClientTemplate().queryForList("getMarketingPlanDetail", parameterMap);
    }

    /**
     * 批量更新计划详情
     * @param list
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingPlanDetailDao#batchUpdate(java.util.List)
     */
    public void batchUpdate(List<TskMarketingPlanDetail> list) {
        this.executeBatchUpdate("updateTskMarketingPlanDetail", list);
    }

    /**
     *
     * @param parameterMap
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingPlanDetailDao#getMarketingPlanProductPage(java.util.Map)
     */
    public PageUtil<PdtProduct> getMarketingPlanProductPage(Map<String, Object> parameterMap,
                                                            Page page) {
        ArrayList<PdtProduct> list = (ArrayList<PdtProduct>) this.findQueryPage(
                "getMarketingPlanProductPage", "getMarketingPlanProductPageCount", parameterMap, page);
        return new PageUtil<PdtProduct>(list, page);
    }

    /**
     *
     * @param pMap
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingPlanDetailDao#getMarketingProductSumByUserId(java.util.Map)
     */
    public BigDecimal getMarketingProductSumByUserId(Map<String, Object> pMap) {
        return (BigDecimal) this.getSqlMapClientTemplate().queryForObject("getMarketingProductSumByUserId", pMap);
    }

    /**
     * 批量新增
     * @param list
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingPlanDetailDao#batchInsert(java.util.List)
     */
    public void batchInsert(List<TskMarketingPlanDetail> list) {
        this.exectuteBatchInsert("insertTskMarketingPlanDetail", list);
    }

    /**
     *
     * @param marketingPlanDetailId
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingPlanDetailDao#getMarketingPlanDetailById(java.lang.Integer)
     */
    public TskMarketingPlanDetail getMarketingPlanDetailById(Integer marketingPlanDetailId) {
        return (TskMarketingPlanDetail) this.getSqlMapClientTemplate().queryForObject("getMarketingPlanDetailById", marketingPlanDetailId);
    }

    /**
     * 用户当天产品销售额
     * @param parameterMap
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingPlanDetailDao#getTodaySaleMoney(java.util.Map)
     */
    public BigDecimal getTodaySaleMoney(Map<String, Object> parameterMap) {
        return (BigDecimal) this.getSqlMapClientTemplate().queryForObject("getTodaySaleMoney", parameterMap);
    }

    public BigDecimal getTodayMarkettingProductSaleMoney(
            Map<String, Object> parameterMap) {
        return (BigDecimal) this.getSqlMapClientTemplate().queryForObject("getTodayMarkettingProductSaleMoney", parameterMap);
    }

}
