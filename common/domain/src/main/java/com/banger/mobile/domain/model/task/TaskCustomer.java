/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务目标客户Bean
 * Author     :liyb
 * Create Date:2012-6-6
 */
package com.banger.mobile.domain.model.task;

import java.io.Serializable;
import java.util.Date;

/**
 * @author liyb
 * @version $Id: TaskCustomer.java,v 0.1 2012-6-6 上午10:19:03 liyb Exp $
 */
public class TaskCustomer implements Serializable {
    private static final long serialVersionUID = 3004261261147211806L;
    private Integer           taskId;                                 //主键ID
    private String            taskTitle;                              //任务标题
    private Date              executeDate;                            //执行日期
    private String            remark;                                 //任务备注
    private Integer           isFinish;                               //完成情况'0:未完成  1:完成'
    private Integer           taskType;                               //任务类型 '0:正常 1:下次联系'
    private Integer           customrtId;                             //客户ID
    private String            customerName;                           //客户姓名
    private Integer           executeUser;                            //执行者

    public Integer getExecuteUser() {
        return executeUser;
    }

    public void setExecuteUser(Integer executeUser) {
        this.executeUser = executeUser;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Date getExecuteDate() {
        return executeDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getCustomrtId() {
        return customrtId;
    }

    public void setCustomrtId(Integer customrtId) {
        this.customrtId = customrtId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setExecuteDate(Date executeDate) {
        this.executeDate = executeDate;
    }

    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

}
