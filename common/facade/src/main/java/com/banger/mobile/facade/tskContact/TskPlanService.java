/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-17
 */
package com.banger.mobile.facade.tskContact;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import net.sf.json.JSONArray;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.tskContact.TskPlan;
import com.banger.mobile.domain.model.tskContact.TskPlanCustListBean;
import com.banger.mobile.domain.model.tskContact.TskPlanDateTarget;
import com.banger.mobile.domain.model.tskContact.TskPlanListBean;
import com.banger.mobile.domain.model.tskContact.TskPlanSelectBean;
import com.banger.mobile.domain.model.tskContact.TskTaskPlanReportBean;
import com.banger.mobile.domain.model.tskTaskPlan.TalkTaskPlan;
import com.banger.mobile.domain.model.tskTaskPlan.TskTaskPlanListBean;

/**
 * @author cheny
 * @version $Id: TskPlanService.java,v 0.1 2012-12-17 上午10:06:44 cheny Exp $
 */
public interface TskPlanService {
	
    /**
    * 全景展示所有的联系计划
    * @param map
    * @return
    */
    public List<TskTaskPlanListBean> showTaskPlanViewList(Map<String, Object> map);
   
	
    /**
     * 所有联系计划列表
     * @param map
     * @param page
     * @return
     * @see com.banger.mobile.facade.task.TskTaskPlanService#getAllTaskPlan(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<TskPlanListBean> getAllTaskPlan(Map<String, Object> map, Page page);
	
    /**
     * 查询联系计划对象
     */
    public TskPlan getPlanByTskPlan(TskPlan plan);
    /**
     * 新增计划
     */
    public int insertTskPlan(TskPlan plan);
    /**
     *  选择关联客户 任务树
     *  flag == 'detail' 时 为查看任务树
     */
    public JSONArray getTskTreeJson(String planDate,String flag);
    /**
     * 制定计划客户列表分页
     */
    public PageUtil<TskPlanCustListBean> getTskPlanCustBeanList(int planId,int userId,Page page);
    /**
     * 计划的有效联系量
     */
    public int getTaskPlanActiveCount(int planId);
    /**
     * 选择客户列表分页
     */
    public PageUtil<TskPlanSelectBean> getSelectCustList(Map<String, Object> parameters,Page page);
    /**
     * 任务联系量统计
     */
    public Map<String,Object> getConnCountForPlan(String tskIds);

    /**
     * 添加任务客户
     */
    public void addTaskCustForPlan(int planId,String targetIds);
    /**
     * 添加非任务任务客户
     */
    public void addNoTaskCustForPlan(int planId,String customerIds);
    /**
     * 移除客户
     */
    public void delCustInPlan(String targetIds);
    /**
     * 查询计划客户实体对象(未完成)
     */
    public TskPlanDateTarget getPlanTargetByCustId(Integer customerId);
    /**
     * 通话窗口 新建下次联系计划
     */
    public void insertTalkTaskPlan(TalkTaskPlan taskPlan,Integer customerId);
    /**
     * 完成计划(内部客户)
     */
    public void finishPlanInTalk(Integer customerId,int finishLevel);
    /**
     * 完成计划(外部客户)
     */
    public void finishPlanInTalk(String phoneNo,int finishLevel);
    /**
     * 工作台 当天联系计划 客户数
     */
    public int getMainPlanCount();
    /**
     * 计划执行情况统计报表
     */
    public List<TskTaskPlanReportBean> getTaskPlanReportList(Map<String, Object> parameters);
    
    /**
     * 计划执行情况数据表转化为Excel格式
     */
    public HSSFWorkbook exportTaskPlanReportExcel(List<TskTaskPlanReportBean> list,String userName);
    
    /**
     * 批量添加非任务任务客户
     */
    public void addBatchNoTaskCustForPlan(int planId,List<Integer> ids);
    /**
     * 选择全部联系客户
     */
    public List<String> getTskPlanSelectCustIds(Map<String, Object> parameters);
    /**
     * 批量添加任务客户
     */
    public void addBatchTaskCustForPlan(int planId,List<String> targetIds);
    /**
     * 根据计划时间获得planId
     */
    public int getPlanIdByPlanDate(String planDate);
    /**
     * 联系记录
     * 完成计划(内部客户)
     * recordDate:联系记录 yyyy-MM-dd
     */
    public void finishPlanInTalk(Integer customerId,String recordDate,int userId);
    /**
     * 联系记录
     * 完成计划(外部客户)
     * recordDate:联系记录 yyyy-MM-dd
     */
    public void finishPlanInTalk(String phoneNo,String recordDate,int userId);
    

}
