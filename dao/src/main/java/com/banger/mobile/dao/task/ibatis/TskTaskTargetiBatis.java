/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务目标客户Dao实现类
 * Author     :liyb
 * Create Date:2012-7-16
 */
package com.banger.mobile.dao.task.ibatis;

import java.util.ArrayList;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.task.TskTaskTargetDao;
import com.banger.mobile.domain.model.task.TaskTargetCustomer;
import com.banger.mobile.domain.model.task.TskTaskTarget;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author liyb
 * @version $Id: TskTaskTargetiBatis.java,v 0.1 2012-7-16 下午03:14:24 liyb Exp $
 */
public class TskTaskTargetiBatis extends GenericDaoiBatis implements TskTaskTargetDao {

    public TskTaskTargetiBatis(){
        super(TskTaskTarget.class);
    }

    /**
     * 查询任务对应的目标客户
     */
    public PageUtil<TaskTargetCustomer> getTaskTargetCustomer(Map<String, Object> parameters,Page page) {
        ArrayList<TaskTargetCustomer> list = (ArrayList<TaskTargetCustomer>) this.findQueryPage(
                "getTskTaskTargetCustomerMap", "getTskTaskTargetCustomerCount", parameters, page);
        if (list == null) {
            list = new ArrayList<TaskTargetCustomer>();
        }
        return new PageUtil<TaskTargetCustomer>(list, page);
    }

    /**
     * 添加任务联系客户
     * @param target
     */
    public void insertTskTaskTarget(TskTaskTarget target) {
        this.getSqlMapClientTemplate().insert("InsertTskTaskTarget",target);
    }

    /**
     * 删除目标客户
     */
    public void delTsakTargetCustomer(TskTaskTarget target) {
        this.getSqlMapClientTemplate().delete("DelTsakTargetCustomer",target);
    }

    /**
     * 目标联系客户联系情况变更
     * @param target
     */
    public void isCheckFinishTarget(TskTaskTarget target) {
        this.getSqlMapClientTemplate().update("IsCheckFinishTarget",target);
    }

    /**
     * 批量修改执行者
     * @param parameters
     * @return
     */
    public Integer updateExcuteUser(Map<String, Object> parameters) {
        return this.getSqlMapClientTemplate().update("UpdateExcuteUser",parameters);
    }

    /**
     * 返回任务执行者对应的联系客户数
     */
    public Integer getExcUserCustomerCount(Map<String, Object> parameters) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("GetExcUserCustomerCount",parameters);
    }

    /**
     * 根据任务ID和客户ID返回任务目标客户数
     * @param target
     * @return
     */
    public Integer getByTaskCustomerCount(TskTaskTarget target) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("GetByTaskCustomerCount",target);
    }

    /**
     * 返回当前登录人所执行的未完成的联系客户数
     * @param parameters
     * @return
     */
    public Integer getByTskTargetNotFinish(Map<String, Object> parameters) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getByTskTargetNotFinish",parameters);
    }

    /**
     * 修改沟通进度
     */
    public Integer updateCommProgress(TskTaskTarget target) {
        return this.getSqlMapClientTemplate().update("UpdateCommProgress",target);
    }

    /**
     * 查询下次联系任务的目标客户
     * @return
     */
    public TaskTargetCustomer getTaskTargetCustomerById(Integer taskId) {
        return (TaskTargetCustomer) this.getSqlMapClientTemplate().queryForObject("GetTaskTargetCustomerById",taskId);
    }

    /**
     * 通话动态改变客户的下次联系任务
     * @param parameters
     * @return
     */
    public Integer updataNextTaskCustoemr(Map<String, Object> parameters) {
        return this.getSqlMapClientTemplate().update("UpdataNextTaskCustoemr",parameters);
    }

    /**
     * 返回任务对应的联系客户的分配数
     * @param parameters
     * @return
     */
    public Integer getTaskTargetCustomerCount(Map<String, Object> parameters) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("GetTaskTargetCustomerCount",parameters);
    }

    /**********************联系任务自动完成部分**************************/
    /**
     * 根据客户ID查询此客户的联系记录的时间是否在任务的时间范围内
     * @param parameters
     * @return
     */
    public Integer getTaskTargetDateCustomer(Map<String, Object> parameters) {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("GetTaskTargetDateCustomerCount",parameters);
    }

    /**
     * 根据客户ID修改客户的联系记录时间在任务的时间范围内的联系状态
     * @param parameters
     * @return
     */
    public Integer updateTargetCustomerById(Map<String, Object> parameters) {
        return this.getSqlMapClientTemplate().update("UpdateTargetCustomerById",parameters);
    }

    /**
     * 根据客户ID修改客户的联系记录时间在任务的时间范围内的子任务完成状态
     * @param parameters
     * @return
     */
    public Integer updateParentTaskCustomerById(Map<String, Object> parameters) {
        return this.getSqlMapClientTemplate().update("UpdateParentTaskCustomerById",parameters);
    }

    /**
     * 根据客户ID修改客户的联系记录时间在任务的时间范围内的主(父)任务完成状态
     * @param parameters
     * @return
     */
    public Integer updateFatherTaskCustomerById(Map<String, Object> parameters) {
        return this.getSqlMapClientTemplate().update("UpdateFatherTaskCustomerById",parameters);
    }

}
