/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务目标接口实现类
 * Author     :liyb
 * Create Date:2012-5-30
 */
package com.banger.mobile.dao.task.ibatis;

import java.util.List;

import com.banger.mobile.dao.task.TaskTargetDao;
import com.banger.mobile.domain.model.task.TaskTarget;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author liyb
 * @version $Id: TaskTargetDaoiBatis.java,v 0.1 2012-5-30 上午11:57:02 liyb Exp $
 */
public class TaskTargetDaoiBatis extends GenericDaoiBatis implements TaskTargetDao {

    public TaskTargetDaoiBatis(){
        super(TaskTarget.class);
    }
    
    /**
     * 添加任务目标
     * @param target
     */
    public void insertTaskTarget(TaskTarget target) {
        this.getSqlMapClientTemplate().insert("InsertTaskTarget",target);
    }
    
    /**
     * 删除指定任务对应的任务目标
     * @param target
     */
    public void deleteTaskTarget(Integer taskId){
        this.getSqlMapClientTemplate().update("DeleteTaskTarget",taskId);
    }

    /**
     * 根据任务ID查询对应的任务目标
     * @return
     */
    public List<TaskTarget> getTargetCustomerList(Integer taskId) {
        return this.getSqlMapClientTemplate().queryForList("GetTargetCustomerList",taskId);
    }

    /**
     * 更改任务的客户联系情况
     * @param target
     */
    public void updateIsFinish(TaskTarget target) {
        this.getSqlMapClientTemplate().update("UpdateIsFinish",target);
    }

    /**
     * 根据任务ID和客户ID删除任务目标
     * @param target
     */
    public void deleteTaskTargetMsg(TaskTarget target) {
        this.getSqlMapClientTemplate().delete("DeleteTaskTargetMsg",target);
    }
}
