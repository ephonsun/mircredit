/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务主表-Dao-接口实现
 * Author     :QianJie
 * Create Date:Dec 27, 2012
 */
package com.banger.mobile.dao.tskMarketing.iBatis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.tskMarketing.TskMarketingDao;
import com.banger.mobile.domain.model.tskMarketing.TskMarketing;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingBean;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingReportBean;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

/**
 * @author QianJie
 * @version $Id: TskMarketingDaoiBatis.java,v 0.1 Dec 27, 2012 11:43:38 AM QianJie Exp $
 */
public class TskMarketingDaoiBatis extends GenericDaoiBatis implements TskMarketingDao {

    public TskMarketingDaoiBatis() {
        super(TskMarketing.class);
    }

    public TskMarketingDaoiBatis(Class persistentClass) {
        super(TskMarketing.class);
    }

    /**
     * 查询所有营销任务
     * @param parameterMap
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingDao#getAllTskMarketingByConds(java.util.Map)
     */
    public List<TskMarketing> getAllTskMarketingByConds(Map<String, Object> parameterMap) {
        return this.getSqlMapClientTemplate().queryForList("getAllTskMarketingByConds",
                parameterMap);
    }

    /**
     * 根据ID查找营销任务对象
     * @param marketingId
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingDao#getTskMarketingById(java.lang.Integer)
     */
    public TskMarketing getTskMarketingById(Integer marketingId) {
        return (TskMarketing) this.getSqlMapClientTemplate().queryForObject("getTskMarketingById",
                marketingId);
    }

    /**
     * 添加营销任务
     * @param tskMarketing
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingDao#addTskMarketing(com.banger.mobile.domain.model.tskMarketing.TskMarketing)
     */
    public void addTskMarketing(TskMarketing tskMarketing) {
        this.getSqlMapClientTemplate().insert("addTskMarketing",tskMarketing);
    }

    /**
     * 删除营销任务
     * @param marketingId
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingDao#delTskMarketing(java.lang.Integer)
     */
    public void delTskMarketingById(Integer marketingId,Integer isDel) {
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("marketingId", marketingId);
        map.put("isDel", isDel);
        this.getSqlMapClientTemplate().update("delTskMarketingById",map);
    }

    /**
     * 编辑营销任务
     * @param tskMarketing
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingDao#editTskMarketing(com.banger.mobile.domain.model.tskMarketing.TskMarketing)
     */
    public void editTskMarketing(TskMarketing tskMarketing) {
        this.getSqlMapClientTemplate().update("editTskMarketing",tskMarketing);
    }

    /**
     * 重启营销任务
     * @param marketingId
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingDao#reStartTskMarketingById(java.lang.Integer)
     */
    public void reStartTskMarketingById(Integer marketingId) {
        this.getSqlMapClientTemplate().update("reStartTskMarketingById",marketingId);
    }

    /**
     * 中止营销任务
     * @param marketingId
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingDao#stopTskMarketingById(java.lang.Integer)
     */
    public void stopTskMarketingById(Integer marketingId) {
        this.getSqlMapClientTemplate().update("stopTskMarketingById",marketingId);
    }

    /**
     * 查询营销任务分页
     * @param conds
     * @param page
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingDao#GetAllTskMarketingPageByConds(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<TskMarketingBean> GetAllTskMarketingPageByConds(Map<String, Object> conds,
                                                                    Page page) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        String startDate="";
        String endDate="";
        if(conds.get("startDate")!=null){
            startDate=conds.get("startDate").toString();
        }
        if(conds.get("endDate")!=null){
            endDate=conds.get("endDate").toString();
        }
        /**
         * 执行日期搜索
         */
        String sql = "";
        if (!StringUtil.isBlank(startDate) && StringUtil.isBlank(endDate)) {
            sql = " TO_DATE('"+ startDate+"', 'yyyy-MM-dd')<= T.END_DATE ";
            fConds.put("executeDateSearch", sql);
        } else if (StringUtil.isBlank(startDate) && !StringUtil.isBlank(endDate)) {
            sql = " TO_DATE('"+endDate+"', 'yyyy-MM-dd')>= T.START_DATE ";
            fConds.put("executeDateSearch", sql);
        } else if (!StringUtil.isBlank(startDate) && !StringUtil.isBlank(endDate)) {
            sql = " (T.START_DATE<=TO_DATE('"+endDate+"', 'yyyy-MM-dd') and T.END_DATE>=TO_DATE('"+startDate+"', 'yyyy-MM-dd')) ";
            fConds.put("executeDateSearch", sql);
        }
        ArrayList<TskMarketingBean> list=(ArrayList<TskMarketingBean>) this.findQueryPage("getAllTskMarketingPageByConds", "getAllTskMarketingPageCountByConds",fConds, page);
        return new PageUtil<TskMarketingBean>(list, page);
    }

    /**
     * 查询营销任务分页
     * @param conds
     * @param page
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingDao#GetAllTskMarketingPageByConds(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<TskMarketingBean> GetAllTskMarketingPageByCondsMyExecute(Map<String, Object> conds,
                                                                             Page page) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        String startDate="";
        String endDate="";
        if(conds.get("startDate")!=null){
            startDate=conds.get("startDate").toString();
        }
        if(conds.get("endDate")!=null){
            endDate=conds.get("endDate").toString();
        }
        /**
         * 执行日期搜索
         */
        String sql = "";
        if (!StringUtil.isBlank(startDate) && StringUtil.isBlank(endDate)) {
            sql = " TO_DATE('"+ startDate+"', 'yyyy-MM-dd')<= T.END_DATE ";
            fConds.put("executeDateSearch", sql);
        } else if (StringUtil.isBlank(startDate) && !StringUtil.isBlank(endDate)) {
            sql = " TO_DATE('"+endDate+"', 'yyyy-MM-dd')>= T.START_DATE ";
            fConds.put("executeDateSearch", sql);
        } else if (!StringUtil.isBlank(startDate) && !StringUtil.isBlank(endDate)) {
            sql = " (T.START_DATE<=TO_DATE('"+endDate+"', 'yyyy-MM-dd') and T.END_DATE>=TO_DATE('"+startDate+"', 'yyyy-MM-dd')) ";
            fConds.put("executeDateSearch", sql);
        }
        ArrayList<TskMarketingBean> list=(ArrayList<TskMarketingBean>) this.findQueryPage("getAllTskMarketingPageByCondsMyExecute", "getAllTskMarketingPageCountByCondsMyExecute",fConds, page);
        return new PageUtil<TskMarketingBean>(list, page);
    }

    /**
     * 查询营销情况完成统计表 用户
     * @param parameters
     * @param searchType
     * @return
     */
    public List<TskMarketingReportBean> getTskMarketingFinishReportByUser(Map<String, Object> parameters) {
        return this.getSqlMapClientTemplate().queryForList("getTskMarketingFinishReportByUser", parameters);
    }

    /**
     * 查询营销情况完成统计表 部门
     * @param parameters
     * @param searchType
     * @return
     */
    public List<TskMarketingReportBean> getTskMarketingFinishReportByDept(Map<String, Object> parameters) {
        return this.getSqlMapClientTemplate().queryForList("getTskMarketingFinishReportByDept", parameters);
    }

    /**
     * 查询营销情况完成统计表任务详情
     * @param conds
     * @param page
     * @return
     */
    public PageUtil<TskMarketingBean> GetTskMarketingFinishReportDetail(
            Map<String, Object> conds, Page page) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        String startDate="";
        String endDate="";
        if(conds.get("startDate")!=null){
            startDate=conds.get("startDate").toString();
        }
        if(conds.get("endDate")!=null){
            endDate=conds.get("endDate").toString();
        }
        /**
         * 执行日期搜索
         */
        String sql = "";
        if (!StringUtil.isBlank(startDate) && StringUtil.isBlank(endDate)) {
            sql = " and TO_DATE('"+ startDate+"', 'yyyy-MM-dd')<= T.END_DATE ";
            fConds.put("executeDateSearch", sql);
        } else if (StringUtil.isBlank(startDate) && !StringUtil.isBlank(endDate)) {
            sql = " AND TO_DATE('"+endDate+"', 'yyyy-MM-dd')>= T.START_DATE ";
            fConds.put("executeDateSearch", sql);
        } else if (!StringUtil.isBlank(startDate) && !StringUtil.isBlank(endDate)) {
            sql = " AND (T.START_DATE<=TO_DATE('"+endDate+"', 'yyyy-MM-dd') and T.END_DATE>=TO_DATE('"+startDate+"', 'yyyy-MM-dd')) ";
            fConds.put("executeDateSearch", sql);
        }
        ArrayList<TskMarketingBean> list=(ArrayList<TskMarketingBean>) this.findQueryPage("getTskMarketingFinishReportDetail", "getTskMarketingFinishReportDetailCount",fConds, page);
        return new PageUtil<TskMarketingBean>(list, page);
    }


    /**
     * 查询营销任务(扩展)数据
     * @param parameters
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingDao#getTskMarketingBeanById(java.util.Map)
     */
    public TskMarketingBean getTskMarketingBeanById(Map<String, Object> parameters){
        return (TskMarketingBean) this.getSqlMapClientTemplate().queryForObject("getTskMarketingBeanById",
                parameters);
    }

    /**
     * 已完成销售额
     * @param parameters
     * @return
     * @see com.banger.mobile.dao.tskMarketing.TskMarketingDao#getSaleMoneyByConds(java.util.Map)
     */
    public BigDecimal getSaleMoneyByConds(Map<String, Object> parameters){
        return (BigDecimal)this.getSqlMapClientTemplate().queryForObject("getSaleMoneyByConds", parameters);
    }

    /**
     * 主界面--任务提醒--所有营销任务，未分配的营销任务
     * @param userId
     * @return
     */
    public Integer getMainAllMarketExecuteCount(Map<String, Object> map){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getAllTskMarketingPageCountByConds",map);
    }

    /**
     * 主界面--任务提醒--我执行中的营销任务
     * @param userId
     * @return
     */
    public Integer getMainMyMarketExecuteCount(Map<String, Object> map){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getAllTskMarketingPageCountByCondsMyExecute",map);
    }

    /**
     * 根据营销任务ID查询任务的销售额
     * @param marketingId
     * @return
     */
    public BigDecimal getMarketingSaleMoney(Integer marketingId) {
        return (BigDecimal) this.getSqlMapClientTemplate().queryForObject("GetMarketingSaleMoney",marketingId);
    }

}
