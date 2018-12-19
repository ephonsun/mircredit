/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :TaskTarget任务目标Dao接口
 * Author     :liyb
 * Create Date:2012-5-30
 */
package com.banger.mobile.dao.task;

import java.util.List;

import com.banger.mobile.domain.model.task.TaskTarget;

/**
 * @author liyb
 * @version $Id: TaskTargetDao.java,v 0.1 2012-5-30 上午11:54:02 liyb Exp $
 */
public interface TaskTargetDao {
    
    /**
     * 添加任务目标
     * @param target
     */
    public void insertTaskTarget(TaskTarget target);
    
    /**
     * 删除指定任务对应的任务目标
     * @param target
     */
    public void deleteTaskTarget(Integer taskId);
    
    /**
     * 根据任务ID查询对应的任务目标
     * @return
     */
    public List<TaskTarget> getTargetCustomerList(Integer taskId);
    
    /**
     * 更改任务的客户联系情况
     * @param target
     */
    public void updateIsFinish(TaskTarget target);
    
    /**
     * 根据任务ID和客户ID删除任务目标
     * @param target
     */
    public void deleteTaskTargetMsg(TaskTarget target);
}
