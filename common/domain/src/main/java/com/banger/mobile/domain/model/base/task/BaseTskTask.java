/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :TskTask任务实体基类
 * Author     :liyb
 * Create Date:2012-7-16
 */
package com.banger.mobile.domain.model.base.task;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author liyb
 * @version $Id: BaseTskTask.java,v 0.1 2012-7-16 上午09:43:57 liyb Exp $
 */
public class BaseTskTask extends BaseObject implements Serializable {

    private static final long serialVersionUID = -5154162968439870174L;
    private Integer           taskId;                                  //主键ID
    private Integer           parentTaskId;                            //主任务ID
    private Integer           taskType;                                //任务类型 '1:联系任务2:营销任务'
    private String            taskTitle;                               //任务标题
    private Date              startDate;                               //开始时间
    private Date              endDate;                                 //结束时间
    private Integer           productId;                               //营销产品ID
    private BigDecimal        targetMoney;                             //目标营销额
    private String            remark;                                  //任务备注
    private Integer           assignUserId;                            //分配者
    private Integer           executeDeptId;                           //执行机构
    private Integer           executeUserId;                           //执行人员
    private Date              finishDate;                              //任务完成时间
    private Integer           taskStatus;                              //任务状态'0:未完成1:完成2:已中止'
    private Integer           isNextContact;                           //是否下次联系'0:不是1:下次联系'
    private Integer           isDel;                                   //是否删除'0:未删除  1:已删除'
    private Date              createDate;                              //创建时间
    private Date              updateDate;                              //更新时间
    private Integer           createUser;                              //创建用户
    private Integer           updateUser;                              //更新用户
    private BigDecimal            deptTargetMoney;                     //机构任务目标

    private String            executeName;                             //执行者
    private String            assignName;                              //分配者    
    private String            excUsersIds;                             //执行者ID数组
    private String            productName;                             //产品名称
    private Double            raiseUpperLimit;                         //募集上限
    private String            moneyUnitName;                           //募集单位名称
    
    private Double            finishCount;                             //总营销任务已完成总额
    private String            finishRate;                              //总营销任务完成百分比
    
    private Double            userFinishCount;                         //个人营销任务目标额
    private String            userFinishRate;                          //个人营销任务完成百分比
    public Integer getTaskId() {
        return taskId;
    }
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    public Integer getParentTaskId() {
        return parentTaskId;
    }
    public void setParentTaskId(Integer parentTaskId) {
        this.parentTaskId = parentTaskId;
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
    public Integer getProductId() {
        return productId;
    }
    public void setProductId(Integer productId) {
        this.productId = productId;
    }
    public BigDecimal getTargetMoney() {
        return targetMoney;
    }
    public void setTargetMoney(BigDecimal targetMoney) {
        this.targetMoney = targetMoney;
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
    public Integer getExecuteDeptId() {
        return executeDeptId;
    }
    public void setExecuteDeptId(Integer executeDeptId) {
        this.executeDeptId = executeDeptId;
    }
    public Integer getExecuteUserId() {
        return executeUserId;
    }
    public void setExecuteUserId(Integer executeUserId) {
        this.executeUserId = executeUserId;
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
    public Integer getIsNextContact() {
        return isNextContact;
    }
    public void setIsNextContact(Integer isNextContact) {
        this.isNextContact = isNextContact;
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
    public String getExcUsersIds() {
        return excUsersIds;
    }
    public void setExcUsersIds(String excUsersIds) {
        this.excUsersIds = excUsersIds;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Double getRaiseUpperLimit() {
        return raiseUpperLimit;
    }
    public void setRaiseUpperLimit(Double raiseUpperLimit) {
        this.raiseUpperLimit = raiseUpperLimit;
    }
    public String getMoneyUnitName() {
        return moneyUnitName;
    }
    public void setMoneyUnitName(String moneyUnitName) {
        this.moneyUnitName = moneyUnitName;
    }
    public Double getFinishCount() {
        return finishCount;
    }
    public void setFinishCount(Double finishCount) {
        this.finishCount = finishCount;
    }
    public String getFinishRate() {
        return finishRate;
    }
    public void setFinishRate(String finishRate) {
        this.finishRate = finishRate;
    }
    public Double getUserFinishCount() {
        return userFinishCount;
    }
    public void setUserFinishCount(Double userFinishCount) {
        this.userFinishCount = userFinishCount;
    }
    public String getUserFinishRate() {
        return userFinishRate;
    }
    public void setUserFinishRate(String userFinishRate) {
        this.userFinishRate = userFinishRate;
    }
    public BigDecimal getDeptTargetMoney() {
        return deptTargetMoney;
    }
    public void setDeptTargetMoney(BigDecimal deptTargetMoney) {
        this.deptTargetMoney = deptTargetMoney;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseTskTask)) {
            return false;
        }
        BaseTskTask rhs = (BaseTskTask) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.startDate,
            rhs.startDate).append(this.remark, rhs.remark).append(this.taskType, rhs.taskType)
            .append(this.isNextContact, rhs.isNextContact).append(this.endDate, rhs.endDate)
            .append(this.assignUserId, rhs.assignUserId).append(this.isDel, rhs.isDel).append(
                this.excUsersIds, rhs.excUsersIds).append(this.executeName, rhs.executeName)
            .append(this.raiseUpperLimit, rhs.raiseUpperLimit).append(this.executeUserId,
                rhs.executeUserId).append(this.assignName, rhs.assignName).append(this.createDate,
                rhs.createDate).append(this.parentTaskId, rhs.parentTaskId).append(
                this.moneyUnitName, rhs.moneyUnitName).append(this.taskId, rhs.taskId).append(
                this.finishDate, rhs.finishDate).append(this.executeDeptId, rhs.executeDeptId)
            .append(this.updateDate, rhs.updateDate).append(this.productId, rhs.productId).append(
                this.taskTitle, rhs.taskTitle).append(this.targetMoney, rhs.targetMoney).append(
                this.createUser, rhs.createUser).append(this.taskStatus, rhs.taskStatus).append(
                this.productName, rhs.productName).append(this.updateUser, rhs.updateUser)
            .isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1132133963, 1649913447).appendSuper(super.hashCode()).append(
            this.startDate).append(this.remark).append(this.taskType).append(this.isNextContact)
            .append(this.endDate).append(this.assignUserId).append(this.isDel).append(
                this.excUsersIds).append(this.executeName).append(this.raiseUpperLimit).append(
                this.executeUserId).append(this.assignName).append(this.createDate).append(
                this.parentTaskId).append(this.moneyUnitName).append(this.taskId).append(
                this.finishDate).append(this.executeDeptId).append(this.updateDate).append(
                this.productId).append(this.taskTitle).append(this.targetMoney).append(
                this.createUser).append(this.taskStatus).append(this.productName).append(
                this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("parentTaskId", this.parentTaskId).append(
            "productId", this.productId).append("taskId", this.taskId).append("updateDate",
            this.updateDate).append("assignUserId", this.assignUserId).append("executeDeptId",
            this.executeDeptId).append("endDate", this.endDate).append("moneyUnitName",
            this.moneyUnitName).append("executeUserId", this.executeUserId).append("startDate",
            this.startDate).append("excUsersIds", this.excUsersIds).append("endRow",
            this.getEndRow()).append("taskType", this.taskType).append("createDate",
            this.createDate).append("raiseUpperLimit", this.raiseUpperLimit).append("productName",
            this.productName).append("isNextContact", this.isNextContact).append("taskTitle",
            this.taskTitle).append("finishDate", this.finishDate).append("isDel", this.isDel)
            .append("assignName", this.assignName).append("remark", this.remark).append(
                "targetMoney", this.targetMoney).append("createUser", this.createUser).append(
                "updateUser", this.updateUser).append("startRow", this.getStartRow()).append(
                "taskStatus", this.taskStatus).append("executeName", this.executeName).toString();
    }
    
}
