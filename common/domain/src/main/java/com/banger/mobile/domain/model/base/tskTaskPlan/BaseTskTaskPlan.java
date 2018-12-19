/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-11-6
 */
package com.banger.mobile.domain.model.base.tskTaskPlan;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author cheny
 * @version $Id: BaseTskTaskPlan.java,v 0.1 2012-11-6 下午1:26:37 cheny Exp $
 */
public class BaseTskTaskPlan extends BaseObject implements java.io.Serializable{

    private static final long serialVersionUID = 1784111050911520436L;
    
    private Integer taskPlanId;         //联系计划id
    private String tableName;           //表名
    private Integer executeUserId;      //执行者
    private Date taskPlanDate;          //执行日期
    private Date createDate;            //创建时间
    private Date updateDate;            //更新时间
    private Integer createUser;         //创建用户
    private Integer updateUser;         //更新用户
    /**
     * 
     */
    public BaseTskTaskPlan() {
        super();
    }
    /**
     * @param taskPlanId
     * @param tableName
     * @param executeUserID
     * @param taskPlanDate
     * @param createDate
     * @param updateDate
     * @param createUser
     * @param updateUser
     */
    public BaseTskTaskPlan(Integer taskPlanId, String tableName, Integer executeUserID,
                           Date taskPlanDate, Date createDate, Date updateDate, Integer createUser,
                           Integer updateUser) {
        super();
        this.taskPlanId = taskPlanId;
        this.tableName = tableName;
        this.executeUserId = executeUserId;
        this.taskPlanDate = taskPlanDate;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }
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
   
    public Integer getExecuteUserId() {
        return executeUserId;
    }
    public void setExecuteUserId(Integer executeUserId) {
        this.executeUserId = executeUserId;
    }
    public Date getTaskPlanDate() {
        return taskPlanDate;
    }
    public void setTaskPlanDate(Date taskPlanDate) {
        this.taskPlanDate = taskPlanDate;
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
        if (!(object instanceof BaseTskTaskPlan)) {
            return false;
        }
        BaseTskTaskPlan rhs = (BaseTskTaskPlan) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.executeUserId, rhs.executeUserId).append(this.createUser, rhs.createUser)
            .append(this.tableName, rhs.tableName).append(this.taskPlanDate, rhs.taskPlanDate)
            .append(this.createDate, rhs.createDate).append(this.taskPlanId, rhs.taskPlanId)
            .append(this.updateDate, rhs.updateDate).append(this.updateUser, rhs.updateUser)
            .isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(267796675, 1688681671).appendSuper(super.hashCode())
            .append(this.executeUserId).append(this.createUser).append(this.tableName)
            .append(this.taskPlanDate).append(this.createDate).append(this.taskPlanId)
            .append(this.updateDate).append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("taskPlanDate", this.taskPlanDate)
            .append("createDate", this.createDate).append("executeUserID", this.executeUserId)
            .append("updateDate", this.updateDate).append("endRow", this.getEndRow())
            .append("tableName", this.tableName).append("createUser", this.createUser)
            .append("updateUser", this.updateUser).append("taskPlanId", this.taskPlanId)
            .append("startRow", this.getStartRow()).toString();
    }

    
    
    
}
