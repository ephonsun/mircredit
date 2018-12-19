/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-12-29
 */
package com.banger.mobile.facade.tskMarketing;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.tskMarketing.TskMarketing;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingBean;
import com.banger.mobile.domain.model.tskMarketing.TskMarketingReportBean;

/**
 * @author yuanme
 * @version $Id: TskMarketingService.java,v 0.1 2012-12-29 上午10:49:52 Administrator Exp $
 */
public interface TskMarketingService {

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
     * @param conds
     * @return
     */
    public List<TskMarketing> getAllTskMarketingByConds(Map<String, Object> conds);

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
     * 查询营销情况完成统计表
     * @param parameters
     * @param searchType
     * @return
     */
    public List<TskMarketingReportBean>  getTskMarketingFinishReportList(Map<String, Object> parameters);
    
    
    /**
     * 数据表转化为Excel格式
     * @param list
     * @param userName
     * @return
     */
    public HSSFWorkbook exportTskMarketingFinishReportExcel(List<TskMarketingReportBean> list, String userName);
    
    /**
     * 查询营销情况完成统计表任务详情
     * @param conds
     * @param page
     * @return
     */
    public PageUtil<TskMarketingBean> GetTskMarketingFinishReportDetail(Map<String, Object> conds, Page page);

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
     * 主界面--任务提醒--执行中的营销任务
     * @param userId
     * @return
     */
    public Integer getMainMarketExecuteCount(Integer userId);
    
    /**
     * 主界面--任务提醒--未分配的任务
     * @param userId
     * @return
     */
    public Integer getMainMarketUnAssignCount(Integer userId);
    
    /**
     * 根据营销任务ID查询任务的销售额
     * @param marketingId
     * @return
     */
    public BigDecimal getMarketingSaleMoney(Integer marketingId);
    
}
