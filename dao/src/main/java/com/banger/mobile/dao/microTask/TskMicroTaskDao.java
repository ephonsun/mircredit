/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务-Dao-接口
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.dao.microTask;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.microTask.TskMicroTask;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskDao.java,v 0.1 Mar 2, 2013 11:11:01 AM QianJie Exp $
 */
public interface TskMicroTaskDao {
    
    /**
     * 删除任务
     * @param valueOf
     */
    public void delTskMicroTask(Integer taskId);

    /**
     * 中止任务
     * @param valueOf
     */
    public void stopTskMicroTask(Integer taskId);

    /**
     * 重启任务
     * @param valueOf
     */
    public void restartTskMicroTask(Integer taskId);

    /**
     * 分页查询
     * @param parameterMap
     * @param page
     * @return
     */
    public PageUtil<TskMicroTask> getTaskListPage(Map<String, Object> parameterMap, Page page);
    
    public PageUtil<TskMicroTask> getMyTaskListPage(Map<String, Object> parameterMap, Page page);

    /**
     * 添加任务
     * @param tskMicroTask
     */
    public void addTskMicroTask(TskMicroTask tskMicroTask);


    /**
     * 批量添加任务
     * @param tskMicroTaskList
     */
    public void addTskMicroTaskBatch(List<TskMicroTask> tskMicroTaskList);


    /**
     * 编辑任务
     * @param tskMicroTask
     */
    public void editTskMicroTask(TskMicroTask tskMicroTask);
    
    /**
     * 查询单个任务数据
     * @param taskId
     * @return
     */
    public TskMicroTask getTskMicroTaskById(int taskId);
    
    /**
     * 查询所有任务数据
     * @param conds
     * @return
     */
    public List<TskMicroTask> getAllTskMicroTaskByConds(Map<String, Object> conds);

    /**
     * 查看任务进度
     * @param param
     * @return
     */
    public List<TskMicroTask> getTaskByUserId(Map<String, Object> param);
}
