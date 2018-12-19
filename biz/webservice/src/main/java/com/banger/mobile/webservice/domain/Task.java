/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-7-20
 */
package com.banger.mobile.webservice.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author yuanme
 * @version Product.java,v 0.1 2012-7-20 上午11:32:49
 */
public class Task implements Serializable {

    private static final long serialVersionUID = 3220851069143683114L;

    private Integer           taskId;
    private String            taskName;
    private String            startDate;
    private String            endDate;
    private Integer           taskType;
    private String            taskTypeName;
    private String            remark;
    private Integer           status;
    private String            createUser;
    private Integer           myTaskTarget;
    private Integer           myFinishCount;
    private Integer           myGroupTaskTarget;
    private Integer           myGroupFinishCount;
    private List<UserTask>    myManageUserList;
    private String            newCustomerPercent;                     // 新客户比例
    private String            oldCustomerPercent;                     // 老客户比例
    private Integer executable;

    public Integer getExecutable() {
        return executable;
    }

    public void setExecutable(Integer executable) {
        this.executable = executable;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getMyTaskTarget() {
        return myTaskTarget;
    }

    public void setMyTaskTarget(Integer myTaskTarget) {
        this.myTaskTarget = myTaskTarget;
    }

    public Integer getMyFinishCount() {
        return myFinishCount;
    }

    public void setMyFinishCount(Integer myFinishCount) {
        this.myFinishCount = myFinishCount;
    }

    public Integer getMyGroupTaskTarget() {
        return myGroupTaskTarget;
    }

    public void setMyGroupTaskTarget(Integer myGroupTaskTarget) {
        this.myGroupTaskTarget = myGroupTaskTarget;
    }

    public Integer getMyGroupFinishCount() {
        return myGroupFinishCount;
    }

    public void setMyGroupFinishCount(Integer myGroupFinishCount) {
        this.myGroupFinishCount = myGroupFinishCount;
    }

    public List<UserTask> getMyManageUserList() {
        return myManageUserList;
    }

    public void setMyManageUserList(List<UserTask> myManageUserList) {
        this.myManageUserList = myManageUserList;
    }

    public String getNewCustomerPercent() {
        return newCustomerPercent;
    }

    public void setNewCustomerPercent(String newCustomerPercent) {
        this.newCustomerPercent = newCustomerPercent;
    }

    public String getOldCustomerPercent() {
        return oldCustomerPercent;
    }

    public void setOldCustomerPercent(String oldCustomerPercent) {
        this.oldCustomerPercent = oldCustomerPercent;
    }
}
