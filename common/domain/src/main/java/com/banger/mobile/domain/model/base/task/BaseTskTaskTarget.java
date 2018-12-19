/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :TskTaskTarget任务目标客户基类
 * Author     :liyb
 * Create Date:2012-7-16
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
 * @version $Id: BaseTskTaskTarget.java,v 0.1 2012-7-16 下午12:56:30 liyb Exp $
 */
public class BaseTskTaskTarget extends BaseObject implements Serializable {

    private static final long serialVersionUID = 105082886472598037L;
    private Integer           taskTargetId;                           //主键ID
    private Integer           taskId;                                 //任务ID
    private Integer           customerId;                             //客户ID
    private Integer           isFinish;                               //是否已联系'如果是联系任务，如果勾选联系情况，则表示已完成。 1:已联系0:未联系'
    private Integer           commProgressId;                         //跟踪进度ID
    private Date              createDate;                             //创建时间
    private Date              updateDate;                             //更新时间
    private Integer           createUser;                             //创建用户
    private Integer           updateUser;                             //更新用户
    private Integer           deptId;                                 //任务归属部门
    public Integer getTaskTargetId() {
        return taskTargetId;
    }
    public void setTaskTargetId(Integer taskTargetId) {
        this.taskTargetId = taskTargetId;
    }
    public Integer getTaskId() {
        return taskId;
    }
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getIsFinish() {
        return isFinish;
    }
    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }
    public Integer getCommProgressId() {
        return commProgressId;
    }
    public void setCommProgressId(Integer commProgressId) {
        this.commProgressId = commProgressId;
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
    public Integer getDeptId() {
        return deptId;
    }
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseTskTaskTarget)) {
            return false;
        }
        BaseTskTaskTarget rhs = (BaseTskTaskTarget) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.taskId, rhs.taskId).append(this.createUser, rhs.createUser).append(
                this.customerId, rhs.customerId).append(this.isFinish, rhs.isFinish).append(
                this.commProgressId, rhs.commProgressId).append(this.createDate, rhs.createDate)
            .append(this.updateDate, rhs.updateDate).append(this.taskTargetId, rhs.taskTargetId)
            .append(this.updateUser, rhs.updateUser).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1673758913, -2025279781).appendSuper(super.hashCode()).append(
            this.taskId).append(this.createUser).append(this.customerId).append(this.isFinish)
            .append(this.commProgressId).append(this.createDate).append(this.updateDate).append(
                this.taskTargetId).append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("isFinish", this.isFinish).append("taskId",
            this.taskId).append("createDate", this.createDate).append("taskTargetId",
            this.taskTargetId).append("updateDate", this.updateDate).append("endRow",
            this.getEndRow()).append("createUser", this.createUser).append("customerId",
            this.customerId).append("updateUser", this.updateUser).append("commProgressId",
            this.commProgressId).append("startRow", this.getStartRow()).toString();
    }
   
}
