/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :TaskTarget任务目标实体基类
 * Author     :liyb
 * Create Date:2012-5-25
 */
package com.banger.mobile.domain.model.base.task;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author liyb
 * @version $Id: BaseTaskTarget.java,v 0.1 2012-5-25 下午01:09:06 liyb Exp $
 */
public class BaseTaskTarget extends BaseObject implements Serializable {

    private static final long serialVersionUID = 1181498318992806575L;
    private Integer           taskTargetId;                           //主键ID
    private Integer           taskId;                                 //任务ID
    private Integer           customerId;                             //客户ID
    private Integer           isFinish;                               //完成情况'0:未完成  1:完成'
    private Integer           sortNo;                                 //排序号
    private Integer           isDel;                                  //是否删除
    private Date              createDate;                             //创建时间
    private Date              updateDate;                             //更新时间
    private Integer           createUser;                             //创建用户
    private Integer           updateUser;                             //更新用户

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

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
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
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1156778969, 831014115).appendSuper(super.hashCode()).append(
            this.taskId).append(this.createUser).append(this.isDel).append(this.customerId).append(
            this.isFinish).append(this.sortNo).append(this.createDate).append(this.updateDate)
            .append(this.taskTargetId).append(this.updateUser).toHashCode();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseTaskTarget)) {
            return false;
        }
        BaseTaskTarget rhs = (BaseTaskTarget) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.taskId, rhs.taskId).append(this.createUser, rhs.createUser).append(
                this.isDel, rhs.isDel).append(this.customerId, rhs.customerId).append(
                this.isFinish, rhs.isFinish).append(this.sortNo, rhs.sortNo).append(
                this.createDate, rhs.createDate).append(this.updateDate, rhs.updateDate).append(
                this.taskTargetId, rhs.taskTargetId).append(this.updateUser, rhs.updateUser)
            .isEquals();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("taskId", this.taskId).append("createDate",
            this.createDate).append("taskTargetId", this.taskTargetId).append("updateDate",
            this.updateDate).append("customerId", this.customerId).append("isDel", this.isDel)
            .append("isFinish", this.isFinish).append("endRow", this.getEndRow()).append(
                "createUser", this.createUser).append("updateUser", this.updateUser).append(
                "sortNo", this.sortNo).append("startRow", this.getStartRow()).toString();
    }

}
