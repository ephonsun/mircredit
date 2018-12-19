/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务管理实体数据接口
 * Author     :liyb
 * Create Date:2012-7-16
 */
package com.banger.mobile.dao.task;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.task.AssignTaskBean;
import com.banger.mobile.domain.model.task.CustomerAttrPlugin;
import com.banger.mobile.domain.model.task.TskTask;
import com.banger.mobile.domain.model.task.TskTaskReportBean;

/**
 * @author liyb
 * @version $Id: TskTaskDao.java,v 0.1 2012-7-16 上午09:59:28 liyb Exp $
 */
public interface TskTaskDao {
    
    /**
     * 任务管理列表分业务
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<TskTask> getTskTaskPage(Map<String, Object> parameters, Page page);
    
    /**
     * 查询所有的子任务列表
     * @param parameters
     * @return
     */
    public List<TskTask> getParentTskTaskList(Map<String, Object> parameters);
    
    /**
     * 新建任务联系任务
     */
    public Integer insertTskTaskContact(TskTask tskTask);
    
    /**
     * 插入任务执行者(即主任务下插入子任务)
     */
    public void saveTaskExecuteUser(TskTask tskTask);
    
    /**
     * 查询指定的任务基本信息
     */
    public TskTask getTskTaskById(TskTask tskTask);
    
    /**
     * 任务完成状态变更
     */
    public void changeTaskFinish(TskTask tskTask);
    
    /**
     * 编辑联系任务
     * @param tskTask
     */
    public void updateTskTaskContact(TskTask tskTask);
    
    /**
     * 删除任务
     * @param taskId
     * @return
     */
    public Integer deleteTskTask(Integer taskId);
    
    /**
     * 任务中止、重启
     * @param tskTask
     */
    public Integer taskStopRestart(String sqlId,TskTask tskTask);
    
    /**
     * 定时变更过期任务状态,状态改为已过期
     */
    public void taskExpiredChange();
    
    /**
     * 执行联系任务(修改日期、备注)
     * @param tskTask
     * @return
     */
    public Integer executeTaskMsg(TskTask tskTask);
    
    /**
     * 查询组织机构以及用户树
     * @return
     */
    public List<CustomerAttrPlugin> getCustomerAttrPluginList(Map<String, Object> parameters);
    
    /**
     * 组织机构用户树附客户分配数
     * @param parameters
     * @return
     */
    public List<CustomerAttrPlugin> getCustomerAttrPluginCount(Map<String, Object> parameters);
    
    /**
     * 查询目标客户数
     * @param TaskId
     * @return
     */
    public Integer getUnallocatedCount(Map<String, Object> parameters);
    
    /**
     * 根据任务ID和执行者ID查询是否存在
     * @param tskTask
     * @return
     */
    public Integer searchTaskExecute(TskTask tskTask);
    
    /**
     * 删除不在此执行者ID数组中的子任务
     * @param tskTask
     * @return
     */
    public Integer deleteTaskByExecuteId(Map<String, Object> parameters);
    
    /**
     * 根据任务ID和执行者ID返回子任务ID
     * @return
     */
    public Integer getParentTaskId(TskTask tskTask);
    
    /**
     * 根据任务ID和执行者ID返回子任务的完成状态
     */
    public Integer getParentTskStatus(Map<String, Object> parameters);
    
    /**
     * 修改子任务的任务状态
     * @param parameters
     * @return
     */
    public Integer changeParentTskStatus(Map<String, Object> parameters);
    
    /**
     * 根据主任务ID查询子任务未完成的数量
     * @param taskId
     * @return
     */
    public Integer getParentTskNotFinishCount(Integer taskId);
    
    /**
     * 根据客户ID查询客户对应的下次联系任务
     * @param customerId
     * @return
     */
    public TskTask getCustomerNextTaskById(Map<String,Object> map);
    
    /**
     * 返回无权限范围的执行者ID集合
     * @param map
     * @return
     */
    public String getNoPermissionUserIds(Map<String,Object> map);
    
    
    /**********************************************************
     * 营销任务部分
     **********************************************************/
    /**
     * 添加营销任务
     * @param tskTask
     * @return
     */
    public Integer insertTaskMarket(TskTask task);
    
    /**
     * 根据产品ID查询产品对应的营销任务量
     * @param productId
     * @return
     */
    public String getByTaskProductCount(Integer productId);
    
    /**
     * 编辑营销任务
     * @param task
     * @return
     */
    public Integer updateTaskMarket(TskTask task);
    
    /**
     * 分配营销任务统计
     * @param parameters
     * @return
     */
    public List<AssignTaskBean> getAssignTaskList(Map<String, Object> parameters);
    
    /**
     * 编辑任务目标额(针对客户经理)
     * @param parameters
     */
    public void updateTargetAmount(Map<String, Object> parameters);
    
    /**
     * 编辑任务目标额(针对机构)
     * @param parameters
     */
    public void updateTargetDept(Map<String, Object> parameters);
    
    /**
     * 查询所有过期任务的数量
     * @return
     */
    public Integer getAllTaskExpiredCount();
    
    /**
     * 任务完成统计报表
     * @param parameters
     * @return
     */
    public List<TskTaskReportBean> getTaskReportList(Map<String, Object> parameters);
    
    /**
     * 任务完成统计报表明细列表
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<TskTask> getTaskReportDetailPage(Map<String, Object> parameters, Page page);
    
    /**
     * 修改下次联系任务的删除状态
     * @param TaskId
     */
    public void updateNextTaskIsDel(Integer TaskId);
    
    /**
     * 根据产品ID返回产品对应的营销任务量
     * @param productId
     * @return
     */
    public Integer getProductTaskCount(Integer productId);
    
    /**
     * 判断任务是否中止
     * @return
     */
    public Integer isStop(Integer taskId);
    
    /**
     * 查询待分配的营销任务量
     */
    public Integer getUnallocatedMarketCount(Map<String, Object> parameters);
    
    /**
     * 查询过期的任务量
     */
    public Integer getExpiredTaskCount(Map<String, Object> parameters);
    
    /**
     * 根据不同的用户返回营销任务目标
     * @param parameters
     * @return
     */
    public Double getTaskTargetMoney(Map<String, Object> parameters);
    
    /**
     * 根据执行者ID和产品ID查询用户的营销任务目标
     * @param parameters
     * @return
     */
    public Double getUserTaskTargetMoneySUM(Map<String, Object> parameters);
    
    /**
     * 判断此产品的所有人员的营销任务是否大于总任务目标,返回1表示大于(总营销任务状态已完成)、返回0表示小于
     */
    public Integer isFinishMarketTask(Map<String, Object> parameters);
    
    /**
     * 根据产品购买记录自动修改营销任务状态
     * @param sqlId
     * @param parameters
     * @return
     */
    public Integer updateMarketTaskStatus(String sqlId,Map<String, Object> parameters);
    
    /**
     * 根据产品ID查询营销任务目标
     * @return
     */
    public Double getProductTargetMoneySUM(String productName);
    
    /**
     * 批量插入任务执行者(即主任务下插入子任务)
     * @param tskList
     * @return
     */
    public Integer insertTskTaskBatch(List<TskTask> tskList);
}
