/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2013-1-28
 */
package com.banger.mobile.facade.microTask;

import com.banger.mobile.domain.model.microTask.TaskProgress;

/**
 * @author yuanme
 * @version $Id: TskMicroTaskProgressService.java,v 0.1 2013-1-28 上午11:27:07 Administrator Exp $
 */
public interface TskMicroTaskProgressService {
    
    /**
     * 贷款任务  进度 by user
     * @param taskId 任务Id
     * @param loanStatus 1已完成，2待分配，3带调查，4待审批，5待放贷
     * @param userId 用户Id
     * @return
     */
    public TaskProgress getLoanTaskCountByUser(Integer taskId, Integer loanStatus, Integer userId);
    
    /**
     * 贷款任务  进度 by dept
     * @param taskId 任务Id
     * @param loanStatus 1已完成，2待分配，3带调查，4待审批，5待放贷
     * @param deptId 部门Id
     * @return
     */
    public TaskProgress getLoanTaskCountByDept(Integer taskId, Integer loanStatus, Integer deptId);
    
    /**
     * 营销任务-已完成 进度 by user
     * @param taskId 任务Id
     * @param userId 用户Id
     * @return
     */
    public TaskProgress getCompleteMarketTaskByUser(Integer taskId, Integer userId);
    
    /**
     * 营销任务-已完成 进度 by dept
     * @param taskId 任务Id
     * @param deptId 部门Id
     * @return
     */
    public TaskProgress getCompleteMarketTaskByDept(Integer taskId, Integer deptId);

    /**
     * 获取任务进度
     * @param userId
     * @return
     */
    public String getTaskScheduleByUserId(Integer userId) ;

}
