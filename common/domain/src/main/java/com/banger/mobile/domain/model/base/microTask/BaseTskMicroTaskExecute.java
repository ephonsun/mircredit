/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :Domain-任务执行明细
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.domain.model.base.microTask;

import java.io.Serializable;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author QianJie
 * @version $Id: BaseTskMicroTaskExecute.java,v 0.1 Mar 2, 2013 10:53:22 AM QianJie Exp $
 */
public class BaseTskMicroTaskExecute extends BaseObject implements Serializable {

    private static final long serialVersionUID = 1730995262712598792L;

    private Integer           taskExecuteId;                          //主键
    private Integer           taskId;                                 //任务ID
    private Integer           deptId;                                 //部门ID
    private Integer           userId;                                 //人员ID  执行者ID
    private Integer           targetUser;                             //人员营销目标
    private Integer           targetDept;                             //机构营销目标
    private Integer           targetDeptUnassign;                     //机构未分配

    public BaseTskMicroTaskExecute() {
        super();
    }

    public BaseTskMicroTaskExecute(Integer taskExecuteId, Integer taskId, Integer deptId,
                                   Integer userId, Integer targetUser, Integer targetDept,
                                   Integer targetDeptUnassign) {
        super();
        this.taskExecuteId = taskExecuteId;
        this.taskId = taskId;
        this.deptId = deptId;
        this.userId = userId;
        this.targetUser = targetUser;
        this.targetDept = targetDept;
        this.targetDeptUnassign = targetDeptUnassign;
    }

    public Integer getTaskExecuteId() {
        return taskExecuteId;
    }

    public void setTaskExecuteId(Integer taskExecuteId) {
        this.taskExecuteId = taskExecuteId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(Integer targetUser) {
        this.targetUser = targetUser;
    }

    public Integer getTargetDept() {
        return targetDept;
    }

    public void setTargetDept(Integer targetDept) {
        this.targetDept = targetDept;
    }

    public Integer getTargetDeptUnassign() {
        return targetDeptUnassign;
    }

    public void setTargetDeptUnassign(Integer targetDeptUnassign) {
        this.targetDeptUnassign = targetDeptUnassign;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseTskMicroTaskExecute)) {
            return false;
        }
        BaseTskMicroTaskExecute rhs = (BaseTskMicroTaskExecute) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.targetUser,
            rhs.targetUser).append(this.taskId, rhs.taskId).append(this.targetDeptUnassign,
            rhs.targetDeptUnassign).append(this.userId, rhs.userId).append(this.taskExecuteId,
            rhs.taskExecuteId).append(this.deptId, rhs.deptId).append(this.targetDept,
            rhs.targetDept).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-236325895, -937632803).appendSuper(super.hashCode()).append(
            this.targetUser).append(this.taskId).append(this.targetDeptUnassign)
            .append(this.userId).append(this.taskExecuteId).append(this.deptId).append(
                this.targetDept).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("targetDeptUnassign", this.targetDeptUnassign)
            .append("deptId", this.deptId).append("startRow", this.getStartRow()).append(
                "targetDept", this.targetDept).append("taskExecuteId", this.taskExecuteId).append(
                "targetUser", this.targetUser).append("endRow", this.getEndRow()).append("taskId",
                this.taskId).append("userId", this.userId).toString();
    }

}
