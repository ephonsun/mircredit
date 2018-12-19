/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :CrmTask任务实体基类
 * Author     :liyb
 * Create Date:2012-5-25
 */
package com.banger.mobile.domain.model.base.task;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author liyb
 * @version $Id: BaseTask.java,v 0.1 2012-5-25 上午10:32:08 liyb Exp $
 */
public class BaseCrmTask extends BaseObject implements Serializable {

    private static final long serialVersionUID = -7387564409990462891L;
    private Integer           taskId;                                  //主键ID
    private String            taskTitle;                               //任务标题
    private Date              executeDate;                             //执行日期
    private Integer           executeUser;                             //执行者
    private String            remark;                                  //任务备注
    private Integer           assignUser;                              //分配者
    private Integer           isFinish;                                //完成情况'0:未完成  1:完成'
    private Integer           isDel;                                   //是否删除'0:未删除  1:已删除'
    private Date              createDate;                              //创建时间
    private Date              updateDate;                              //更新时间
    private Integer           createUser;                              //创建用户
    private Integer           updateUser;                              //更新用户
    private Integer           taskType;                                //任务类型 '0:正常 1:下次联系'

    private String            executeName;
    private String            assignName;

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

    public void setExecuteDate(Date executeDate) {
        this.executeDate = executeDate;
    }

    public Integer getExecuteUser() {
        return executeUser;
    }

    public void setExecuteUser(Integer executeUser) {
        this.executeUser = executeUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAssignUser() {
        return assignUser;
    }

    public void setAssignUser(Integer assignUser) {
        this.assignUser = assignUser;
    }

    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public String getExecuteName() {
        return executeName;
    }

    public void setExecuteName(String executeName) {
        this.executeName = executeName;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseCrmTask)) {
            return false;
        }
        BaseCrmTask rhs = (BaseCrmTask) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.taskId, rhs.taskId).append(this.executeDate, rhs.executeDate).append(
                this.remark, rhs.remark).append(this.taskType, rhs.taskType).append(
                this.assignUser, rhs.assignUser).append(this.updateDate, rhs.updateDate).append(
                this.taskTitle, rhs.taskTitle).append(this.executeUser, rhs.executeUser).append(
                this.isDel, rhs.isDel).append(this.createUser, rhs.createUser).append(
                this.executeName, rhs.executeName).append(this.isFinish, rhs.isFinish).append(
                this.assignName, rhs.assignName).append(this.createDate, rhs.createDate).append(
                this.updateUser, rhs.updateUser).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1356881411, 1696383897).appendSuper(super.hashCode()).append(
            this.taskId).append(this.executeDate).append(this.remark).append(this.taskType).append(
            this.assignUser).append(this.updateDate).append(this.taskTitle)
            .append(this.executeUser).append(this.isDel).append(this.createUser).append(
                this.executeName).append(this.isFinish).append(this.assignName).append(
                this.createDate).append(this.updateUser).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("taskType", this.taskType).append("taskId",
            this.taskId).append("createDate", this.createDate)
            .append("updateDate", this.updateDate).append("executeDate", this.executeDate).append(
                "taskTitle", this.taskTitle).append("isDel", this.isDel).append("assignName",
                this.assignName).append("remark", this.remark).append("isFinish", this.isFinish)
            .append("endRow", this.getEndRow()).append("assignUser", this.assignUser).append(
                "createUser", this.createUser).append("updateUser", this.updateUser).append(
                "executeUser", this.executeUser).append("executeName", this.executeName).append(
                "startRow", this.getStartRow()).toString();
    }

}
