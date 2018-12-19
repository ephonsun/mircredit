/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务管理接口实现类
 * Author     :liyb
 * Create Date:2012-5-25
 */
package com.banger.mobile.dao.task.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.task.CrmTaskDao;
import com.banger.mobile.domain.model.task.CrmTask;
import com.banger.mobile.domain.model.task.TaskCustomer;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author liyb
 * @version $Id: CrmTaskDaoiBatis.java,v 0.1 2012-5-25 上午10:51:58 liyb Exp $
 */
public class CrmTaskDaoiBatis extends GenericDaoiBatis implements CrmTaskDao {

    public CrmTaskDaoiBatis(){
        super(CrmTask.class);
    }

    /**
     * 任务管理列表分业务
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CrmTask> getCrmTaskPage(Map<String, Object> parameters, Page page) {
        ArrayList<CrmTask> list = (ArrayList<CrmTask>) this.findQueryPage(
                "getTaskPageMap", "getTaskCount", parameters, page);
        if (list == null) {
            list = new ArrayList<CrmTask>();
        }
        return new PageUtil<CrmTask>(list, page);
    }

    /**
     * 添加任务
     * @param task
     * @return
     */
    public Integer insertTask(CrmTask task) {
        return (Integer) this.getSqlMapClientTemplate().insert("InsertTask",task);
    }

    /**
     * 删除任务
     * @param taskId
     */
    public void deleteTask(Integer taskId) {
        this.getSqlMapClientTemplate().update("DeleteTask",taskId);
    }

    /**
     * 返回CrmTask信息
     * @param task
     * @return
     */
    public CrmTask getCrmTaskById(Integer taskId) {
        return (CrmTask) this.getSqlMapClientTemplate().queryForObject("GetCrmTaskById",taskId);
    }

    /**
     * 修改完成情况状态
     * @param taskId
     */
    public void changeIsFinsish(CrmTask task) {
        this.getSqlMapClientTemplate().update("ChangeIsFinsish",task);
    }

    /**
     * 编辑任务
     * @param task
     */
    public void updateTask(CrmTask task) {
        this.getSqlMapClientTemplate().update("UpdateTask",task);
    }

    /**
     * 返回下次联系的任务信息
     * @param taskId
     * @return
     */
    public TaskCustomer getNextTask(Integer taskId) {
        return (TaskCustomer) this.getSqlMapClientTemplate().queryForObject("GetNextTask",taskId);
    }

    /**
     * 返回客户对应的任务信息
     * @param parameters
     * @param page
     * @return
     */
    public PageUtil<CrmTask> getCustomerTaskPage(Map<String, Object> parameters, Page page) {
        ArrayList<CrmTask> list = (ArrayList<CrmTask>) this.findQueryPage(
                "GetCustomerTaskPage", "GetCustomerTaskCount", parameters, page);
        if (list == null) {
            list = new ArrayList<CrmTask>();
        }
        return new PageUtil<CrmTask>(list, page);
    }

    /**
     * 初始化客户对应的下次联系记录
     */
    public List<TaskCustomer> getInitNextCustomerTask(Integer customerId) {
        return this.getSqlMapClientTemplate().queryForList("GetInitNextCustomerTask",customerId);
    }

    /**
     * 返回任务主键序列
     * @return
     */
    public Integer getTaskKeyId() {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("GetTaskKeyId");
    }

}
