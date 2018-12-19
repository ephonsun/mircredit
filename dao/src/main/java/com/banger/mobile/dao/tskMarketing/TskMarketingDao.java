/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务主表-Dao-接口
 * Author     :QianJie
 * Create Date:Dec 27, 2012
 */
package com.banger.mobile.dao.tskMarketing;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.tskMarketing.TskMarketing;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingBean;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingReportBean;

/**
 * @author QianJie
 * @version $Id: TskMarketingDao.java,v 0.1 Dec 27, 2012 11:43:14 AM QianJie Exp $
 */
public interface TskMarketingDao {

    /**
     * 添加营销任务
     * @param tskMarketing
     */
    public void addTskMarketing(TskMarketing tskMarketing);
    
    
    /**
     * 编辑营销任务
     * @param tskMarketing
     */
    public void editTskMarketing(TskMarketing tskMarketing);
    
    /**
     * 删除营销任务
     * @param marketingId
     * @param isDel
     */
    public void delTskMarketingById(Integer marketingId,Integer isDel);
    
    /**
     * 中止营销任务
     * @param marketingId
     */
    public void stopTskMarketingById(Integer marketingId);
    
    /**
     * 重启营销任务
     * @param marketingId
     */
    public void reStartTskMarketingById(Integer marketingId);
    
    /**
     * 查询所有营销任务
     * @param parameterMap
     * @return
     */
    public List<TskMarketing> getAllTskMarketingByConds(Map<String, Object> parameterMap);

    /**
     * 根据ID查找营销任务对象
     * @param marketingId
     * @return
     */
    public TskMarketing getTskMarketingById(Integer marketingId);
    
    /**
     * 查询营销任务分页
     * @param conds
     * @param page
     * @return
     */
    public PageUtil<TskMarketingBean> GetAllTskMarketingPageByConds(Map<String, Object> conds, Page page);
    
    /**
     * 查询营销任务分页
     * @param conds
     * @param page
     * @return
     */
    public PageUtil<TskMarketingBean> GetAllTskMarketingPageByCondsMyExecute(Map<String, Object> conds, Page page);

    
    /**
     * 查询营销情况完成统计表 部门
     * @param parameters
     * @param searchType
     * @return
     */
	public List<TskMarketingReportBean> getTskMarketingFinishReportByDept(Map<String, Object> parameters);
	/**
	 * 查询营销情况完成统计表 用户
	 * @param parameters
	 * @param searchType
	 * @return
	 */
	public List<TskMarketingReportBean> getTskMarketingFinishReportByUser(Map<String, Object> parameters);
	
	/**
     * 查询营销情况完成统计表任务详情
     * @param conds
     * @param page
     * @return
     */
	public PageUtil<TskMarketingBean> GetTskMarketingFinishReportDetail(
			Map<String, Object> conds, Page page);

    
    /**
     * 查询营销任务(扩展)数据
     * @param parameters
     * @return
     */
    public TskMarketingBean getTskMarketingBeanById(Map<String, Object> parameters);

    /**
     * 已完成销售额
     * @param parameters
     * @return
     */
    public BigDecimal getSaleMoneyByConds(Map<String, Object> parameters);
    
    /**
     * 主界面--任务提醒--所有营销任务，未分配的营销任务
     * @param userId
     * @return
     */
    public Integer getMainAllMarketExecuteCount(Map<String, Object> map);
    
    /**
     * 主界面--任务提醒--我执行中的营销任务
     * @param userId
     * @return
     */
    public Integer getMainMyMarketExecuteCount(Map<String, Object> map);
    
    /**
     * 根据营销任务ID查询任务的销售额
     * @param marketingId
     * @return
     */
    public BigDecimal getMarketingSaleMoney(Integer marketingId);
    
}
