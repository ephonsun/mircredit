/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务-Domain
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.domain.model.base.microTask;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author QianJie
 * @version $Id: BaseTskMicroTask.java,v 0.1 Mar 2, 2013 10:33:46 AM QianJie Exp $
 */
public class BaseTskMicroTask extends BaseObject implements Serializable {

    private static final long serialVersionUID = 2897247249969267331L;

    private Integer           taskId;                                 //主键
    private Integer           taskType;                               //任务类型 1:贷款任务    2:电话营销任务    3:实地营销任务
    private String            taskTitle;                              //任务标题
    private Date              startDate;                              //开始日期
    private Date              endDate;                                //结束日期
    private Integer           taskTarget;                             //任务目标
    private BigDecimal        newCustomerPercent;                     //新客户比例
    private BigDecimal        oldCustomerPercent;                     //老客户比例
    private String            remark;                                 //任务备注
    private Integer           assignUserId;                           //分配者
    private Date              finishDate;                             //任务完成时间
    private Integer           taskStatus;                             //任务状态'0:未完成1:完成2:已中止'
    private Integer           isDel;                                  //是否删除'0:未删除  1:已删除'
    private Date              createDate;                             //创建时间
    private Date              updateDate;                             //更新时间
    private Integer           createUser;                             //创建用户
    private Integer           updateUser;                             //更新用户
    
    
    public BaseTskMicroTask() {
        super();
    }
    
    public BaseTskMicroTask(Integer taskId, Integer taskType, String taskTitle, Date startDate,
                            Date endDate, Integer taskTarget, BigDecimal newCustomerPercent,
                            BigDecimal oldCustomerPercent, String remark, Integer assignUserId,
                            Date finishDate, Integer taskStatus, Integer isDel, Date createDate,
                            Date updateDate, Integer createUser, Integer updateUser) {
        super();
        this.taskId = taskId;
        this.taskType = taskType;
        this.taskTitle = taskTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.taskTarget = taskTarget;
        this.newCustomerPercent = newCustomerPercent;
        this.oldCustomerPercent = oldCustomerPercent;
        this.remark = remark;
        this.assignUserId = assignUserId;
        this.finishDate = finishDate;
        this.taskStatus = taskStatus;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }


    public Integer getTaskId() {
        return taskId;
    }
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    public Integer getTaskType() {
        return taskType;
    }
    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }
    public String getTaskTitle() {
        return taskTitle;
    }
    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Integer getTaskTarget() {
        return taskTarget;
    }
    public void setTaskTarget(Integer taskTarget) {
        this.taskTarget = taskTarget;
    }
    public BigDecimal getNewCustomerPercent() {
        return newCustomerPercent;
    }
    public void setNewCustomerPercent(BigDecimal newCustomerPercent) {
        this.newCustomerPercent = newCustomerPercent;
    }
    public BigDecimal getOldCustomerPercent() {
        return oldCustomerPercent;
    }
    public void setOldCustomerPercent(BigDecimal oldCustomerPercent) {
        this.oldCustomerPercent = oldCustomerPercent;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getAssignUserId() {
        return assignUserId;
    }
    public void setAssignUserId(Integer assignUserId) {
        this.assignUserId = assignUserId;
    }
    public Date getFinishDate() {
        return finishDate;
    }
    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
    public Integer getTaskStatus() {
        return taskStatus;
    }
    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
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

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseTskMicroTask)) {
            return false;
        }
        BaseTskMicroTask rhs = (BaseTskMicroTask) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.taskId, rhs.taskId).append(this.startDate, rhs.startDate).append(
                this.taskType, rhs.taskType).append(this.finishDate, rhs.finishDate).append(
                this.taskStatus, rhs.taskStatus).append(this.taskTitle, rhs.taskTitle).append(
                this.oldCustomerPercent, rhs.oldCustomerPercent).append(this.taskTarget,
                rhs.taskTarget).append(this.remark, rhs.remark).append(this.updateDate,
                rhs.updateDate).append(this.assignUserId, rhs.assignUserId).append(this.isDel,
                rhs.isDel).append(this.newCustomerPercent, rhs.newCustomerPercent).append(
                this.createDate, rhs.createDate).append(this.endDate, rhs.endDate).append(
                this.createUser, rhs.createUser).append(this.updateUser, rhs.updateUser).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-287492663, -1033631557).appendSuper(super.hashCode()).append(
            this.taskId).append(this.startDate).append(this.taskType).append(this.finishDate)
            .append(this.taskStatus).append(this.taskTitle).append(this.oldCustomerPercent).append(
                this.taskTarget).append(this.remark).append(this.updateDate).append(
                this.assignUserId).append(this.isDel).append(this.newCustomerPercent).append(
                this.createDate).append(this.endDate).append(this.createUser).append(
                this.updateUser).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("taskType", this.taskType).append(
            "newCustomerPercent", this.newCustomerPercent).append("taskStatus", this.taskStatus)
            .append("startRow", this.getStartRow()).append("updateDate", this.updateDate).append(
                "taskTitle", this.taskTitle).append("endRow", this.getEndRow()).append(
                "updateUser", this.updateUser).append("createUser", this.createUser).append(
                "finishDate", this.finishDate).append("endDate", this.endDate).append(
                "oldCustomerPercent", this.oldCustomerPercent).append("assignUserId",
                this.assignUserId).append("startDate", this.startDate)
            .append("taskId", this.taskId).append("taskTarget", this.taskTarget).append("isDel",
                this.isDel).append("createDate", this.createDate).append("remark", this.remark)
            .toString();
    }
    
    
}
