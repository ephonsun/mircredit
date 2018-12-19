/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务-Dao-接口实现
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.dao.microTask.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.microTask.TskMicroTaskDao;
import com.banger.mobile.domain.model.microTask.TskMicroTask;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskDaoiBatis.java,v 0.1 Mar 2, 2013 11:11:27 AM QianJie Exp $
 */
public class TskMicroTaskDaoiBatis extends GenericDaoiBatis implements TskMicroTaskDao {

    public TskMicroTaskDaoiBatis() {
        super(TskMicroTask.class);
    }

    public TskMicroTaskDaoiBatis(Class persistentClass) {
        super(TskMicroTask.class);
    }

    /**
     * 删除任务
     * @param taskId
     */
    public void delTskMicroTask(Integer taskId) {
        this.getSqlMapClientTemplate().update("delTskMicroTask", taskId);
    }

    /**
     * 中止任务
     * @param taskId
     */
    public void stopTskMicroTask(Integer taskId) {
        this.getSqlMapClientTemplate().update("stopTskMicroTask", taskId);
    }

    /**
     * 重启任务
     * @param taskId
     */
    public void restartTskMicroTask(Integer taskId) {
        this.getSqlMapClientTemplate().update("restartTskMicroTask", taskId);
    }

    /**
     * 分页查询
     * @param parameterMap
     * @param page
     * @return
     * @see com.banger.mobile.dao.microTask.TskMicroTaskDao#getTaskListPage(java.util.Map, com.banger.mobile.Page)
     */
    public PageUtil<TskMicroTask> getTaskListPage(Map<String, Object> parameterMap, Page page) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : parameterMap.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<TskMicroTask> list = (List<TskMicroTask>) this.findQueryPage("getTaskListPage",
                "getTaskListPageCount", fConds, page);
        return new PageUtil<TskMicroTask>(list, page);
    }

    public PageUtil<TskMicroTask> getMyTaskListPage(Map<String, Object> parameterMap, Page page) {
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : parameterMap.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<TskMicroTask> list = (List<TskMicroTask>) this.findQueryPage("getMyTaskListPage",
                "getMyTaskListPageCount", fConds, page);
        return new PageUtil<TskMicroTask>(list, page);
    }

    /**
     * 添加任务
     * @param tskMicroTask
     * @see com.banger.mobile.dao.microTask.TskMicroTaskDao#addTskMicroTask(com.banger.mobile.domain.model.microTask.TskMicroTask)
     */
    public void addTskMicroTask(TskMicroTask tskMicroTask) {
        this.getSqlMapClientTemplate().insert("addTskMicroTask",tskMicroTask);
    }

    public void addTskMicroTaskBatch(List<TskMicroTask> tskMicroTaskList) {
        this.exectuteBatchInsert("addTskMicroTaskBatch",tskMicroTaskList);
    }

    /**
     * 编辑任务
     * @param tskMicroTask
     * @see com.banger.mobile.dao.microTask.TskMicroTaskDao#editTskMicroTask(com.banger.mobile.domain.model.microTask.TskMicroTask)
     */
    public void editTskMicroTask(TskMicroTask tskMicroTask) {
        this.getSqlMapClientTemplate().update("editTskMicroTask",tskMicroTask);
    }

    /**
     * 查询单个任务数据
     * @param taskId
     * @return
     * @see com.banger.mobile.dao.microTask.TskMicroTaskDao#getTskMicroTaskById(int)
     */
    public TskMicroTask getTskMicroTaskById(int taskId) {
        return (TskMicroTask)this.getSqlMapClientTemplate().queryForObject("getTskMicroTaskById",taskId);
    }

    /**
     * 查询所有任务数据
     * @param conds
     * @return
     * @see com.banger.mobile.dao.microTask.TskMicroTaskDao#getAllTskMicroTaskByConds(java.util.Map)
     */
    public List<TskMicroTask> getAllTskMicroTaskByConds(Map<String, Object> conds){
        Map<String,Object> fConds = new HashMap<String, Object>();
        for(Map.Entry<String, Object> entry : conds.entrySet())
        {
            if(entry.getValue() instanceof String){
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil.ReplaceSQLChar(entry.getValue().toString())));
            }else{
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        return this.getSqlMapClientTemplate().queryForList("getAllTskMicroTaskByConds",fConds);
    }

    /**
     * 查看任务进度
     * @param param
     * @return
     */
    public List<TskMicroTask> getTaskByUserId(Map<String, Object> param){
        return this.getSqlMapClientTemplate().queryForList("getTaskByUserId",param) ;
    }
}
