/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务管理service
 * Author     :liyb
 * Create Date:2012-5-25
 */
package com.banger.mobile.facade.task;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.task.CrmTask;
import com.banger.mobile.domain.model.task.TaskCustomer;

/**
 * @author liyb
 * @version $Id: CrmTaskService.java,v 0.1 2012-5-25 上午11:13:36 liyb Exp $
 */
public interface CrmTaskService {
    /**
     * 任务管理列表分业务
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CrmTask> getCrmTaskPage(Map<String, Object> parameters, Page page);
    
    /**
     * 添加任务
     * @param task
     * @return
     */
    public Integer insertTask(CrmTask task);
    
    /**
     * 删除任务
     * @param taskId
     */
    public Integer deleteTask(Integer taskId);
    
    /**
     * 返回CrmTask信息
     * @param task
     * @return
     */
    public CrmTask getCrmTaskById(Integer taskId);
    
    /**
     * 修改完成情况状态
     * @param taskId
     */
    public void changeIsFinsish(CrmTask task);
    
    /**
     * 编辑任务
     * @param task
     */
    public Integer updateTask(CrmTask task);
    
    /**
     * 返回下次联系的任务信息
     * @param taskId
     * @return
     */
    public TaskCustomer getNextTask(Integer taskId);
    
    /**
     * 返回客户对应的任务信息
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CrmTask> getCustomerTaskPage(Map<String, Object> parameters, Page page);
    
    /**
     * 初始化客户对应的下次联系记录
     */
    public List<TaskCustomer> getInitNextCustomerTask(Integer customerId);
    
    /**
     * 返回任务主键序列
     * @return
     */
    public Integer getTaskKeyId();
}
