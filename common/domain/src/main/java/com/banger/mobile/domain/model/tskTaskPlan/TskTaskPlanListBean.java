/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :所以联系计划列表bean
 * Author     :yujh
 * Create Date:2012-11-6
 */
package com.banger.mobile.domain.model.tskTaskPlan;


import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.banger.mobile.domain.model.base.tskTaskPlan.BaseTskTaskPlan;

/**
 * @author yujh
 * @version $Id: TskTaskPlan.java,v 0.1 2012-11-6 下午1:48:40 cheny Exp $
 */
public class TskTaskPlanListBean extends BaseTskTaskPlan{
    private static final long serialVersionUID = 781241543882122953L;
    private Integer     taskPlanId;         //编号
    private String      tableName;          //表名
    private String      executeUser;        //执行者name
    private Integer     execUserId;         //执行者id
    private Integer     totalConnect;       //总联系量
    private Integer     finishConnect;      //有效联系量
    private Date        taskPlanDate;       //执行日期
    public Integer getTaskPlanId() {
        return taskPlanId;
    }
    public void setTaskPlanId(Integer taskPlanId) {
        this.taskPlanId = taskPlanId;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public String getExecuteUser() {
        return executeUser;
    }
    public void setExecuteUser(String executeUser) {
        this.executeUser = executeUser;
    }
    public Integer getExecUserId() {
        return execUserId;
    }
    public void setExecUserId(Integer execUserId) {
        this.execUserId = execUserId;
    }
    public Integer getTotalConnect() {
        return totalConnect;
    }
    public void setTotalConnect(Integer totalConnect) {
        this.totalConnect = totalConnect;
    }
    public Integer getFinishConnect() {
        return finishConnect;
    }
    public void setFinishConnect(Integer finishConnect) {
        this.finishConnect = finishConnect;
    }
    public Date getTaskPlanDate() {
        return taskPlanDate;
    }
    public void setTaskPlanDate(Date taskPlanDate) {
        this.taskPlanDate = taskPlanDate;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof TskTaskPlanListBean)) {
            return false;
        }
        TskTaskPlanListBean rhs = (TskTaskPlanListBean) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.taskPlanDate,
            rhs.taskPlanDate).append(this.execUserId, rhs.execUserId).append(this.executeUser,
            rhs.executeUser).append(this.taskPlanId, rhs.taskPlanId).append(this.tableName,
            rhs.tableName).append(this.finishConnect, rhs.finishConnect).append(this.totalConnect,
            rhs.totalConnect).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1796613393, 913337551).appendSuper(super.hashCode()).append(
            this.taskPlanDate).append(this.execUserId).append(this.executeUser).append(
            this.taskPlanId).append(this.tableName).append(this.finishConnect).append(
            this.totalConnect).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("startRow", this.getStartRow()).append(
            "taskPlanId", this.taskPlanId).append("execUserId", this.execUserId).append(
            "updateDate", this.getUpdateDate()).append("endRow", this.getEndRow()).append(
            "updateUser", this.getUpdateUser()).append("createUser", this.getCreateUser()).append(
            "executeUserID", this.getExecuteUserId()).append("finishConnect", this.finishConnect)
            .append("totalConnect", this.totalConnect).append("tableName", this.tableName).append(
                "taskPlanDate", this.taskPlanDate).append("executeUser", this.executeUser).append(
                "createDate", this.getCreateDate()).toString();
    }
    
}
