/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务-Service-接口实现
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.facade.impl.task.microTask;

import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.microTask.TskMicroTaskDao;
import com.banger.mobile.domain.model.microTask.TskMicroTask;
import com.banger.mobile.facade.microTask.TskMicroTaskService;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskServiceImpl.java,v 0.1 Mar 2, 2013 11:52:50 AM QianJie Exp $
 */
public class TskMicroTaskServiceImpl implements TskMicroTaskService {

    private TskMicroTaskDao tskMicroTaskDao;

    public void setTskMicroTaskDao(TskMicroTaskDao tskMicroTaskDao) {
        this.tskMicroTaskDao = tskMicroTaskDao;
    }
    
    /**
     * 删除任务
     * @param valueOf
     */
    public void delTskMicroTask(Integer taskId) {
        tskMicroTaskDao.delTskMicroTask(taskId);
    }
    
    /**
     * 中止任务
     * @param valueOf
     */
    public void stopTskMicroTask(Integer taskId) {
        tskMicroTaskDao.stopTskMicroTask(taskId);
    }
    
    /**
     * 重启任务
     * @param valueOf
     */
    public void restartTskMicroTask(Integer taskId) {
        tskMicroTaskDao.restartTskMicroTask(taskId);
    }

    /**
     * 分页查询
     * @param parameterMap
     * @param page
     * @return
     * @see com.banger.mobile.facade.microTask.TskMicroTaskService#getTaskListPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<TskMicroTask> getTaskListPage(Map<String, Object> parameterMap, Page page) {
        return tskMicroTaskDao.getTaskListPage(parameterMap, page);
    }
    
    /**
     * 分页查询
     * @param parameterMap
     * @param page
     * @return
     * @see com.banger.mobile.facade.microTask.TskMicroTaskService#getTaskListPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<TskMicroTask> getMyTaskListPage(Map<String, Object> parameterMap, Page page) {
        return tskMicroTaskDao.getMyTaskListPage(parameterMap, page);
    }

    /**
     * 添加任务
     * @param tskMicroTask
     * @see com.banger.mobile.facade.microTask.TskMicroTaskService#addTskMicroTask(com.banger.mobile.domain.model.microTask.TskMicroTask)
     */
    public void addTskMicroTask(TskMicroTask tskMicroTask) {
        tskMicroTaskDao.addTskMicroTask(tskMicroTask);
    }

    public void addTskMicroTaskBatch(List<TskMicroTask> tskMicroTaskList) {
        tskMicroTaskDao.addTskMicroTaskBatch(tskMicroTaskList);
    }

    /**
     * 编辑任务
     * @param tskMicroTask
     * @see com.banger.mobile.facade.microTask.TskMicroTaskService#editTskMicroTask(com.banger.mobile.domain.model.microTask.TskMicroTask)
     */
    public void editTskMicroTask(TskMicroTask tskMicroTask) {
        tskMicroTaskDao.editTskMicroTask(tskMicroTask);
    }

    /**
     * 查询单个任务数据
     * @param taskId
     * @return
     * @see com.banger.mobile.facade.microTask.TskMicroTaskService#getTskMicroTaskById(int)
     */
    public TskMicroTask getTskMicroTaskById(int taskId) {
        return tskMicroTaskDao.getTskMicroTaskById(taskId);
    }
    
    /**
     * 查询所有任务数据
     * @param conds
     * @return
     * @see com.banger.mobile.facade.microTask.TskMicroTaskService#getAllTskMicroTaskByConds(java.util.Map)
     */
    public List<TskMicroTask> getAllTskMicroTaskByConds(Map<String, Object> conds){
        return tskMicroTaskDao.getAllTskMicroTaskByConds(conds);
    }

    /**
     * 查看任务进度
     * @param param
     * @return
     */
    public List<TskMicroTask> getTaskByUserId(Map<String, Object> param){
        return tskMicroTaskDao.getTaskByUserId(param);
    }
    
}
