/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款任务报表bean
 * Author     :liyb
 * Create Date:2013-12-26
 */
package com.banger.mobile.domain.model.microReport;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author liyb
 * @version $Id: LoanTaskReportBean.java,v 0.1 2013-12-26 上午09:51:06 liyb Exp $
 */
public class LoanTaskReportBean implements Serializable {

    private static final long serialVersionUID = 6823367797883475367L;
    private Integer           deptId;                                 //机构ID
    private String            deptName;                               //机构名称
    private Integer           userId;                                 //人员ID
    private String            userName;                               //人员姓名
    private Integer           taskId;                                 //任务ID
    private String            taskTitle;                              //任务名称
    private Integer           taskTarget;                             //任务目标
    private Integer           finishCount;                            //已完成数
    private BigDecimal        comPercent;                             //完成比
    private Integer           DCcount;                                //调查
    private Integer           SPcount;                                //审批
    private Integer           FDcount;                                //放贷
    
    private Integer           taskType;                               //任务类型

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Integer getTaskTarget() {
        return taskTarget;
    }

    public void setTaskTarget(Integer taskTarget) {
        this.taskTarget = taskTarget;
    }

    public Integer getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Integer finishCount) {
        this.finishCount = finishCount;
    }

    public Integer getDCcount() {
        return DCcount;
    }

    public void setDCcount(Integer dCcount) {
        DCcount = dCcount;
    }

    public Integer getSPcount() {
        return SPcount;
    }

    public void setSPcount(Integer sPcount) {
        SPcount = sPcount;
    }

    public Integer getFDcount() {
        return FDcount;
    }

    public void setFDcount(Integer fDcount) {
        FDcount = fDcount;
    }

    public BigDecimal getComPercent() {
        return comPercent;
    }

    public void setComPercent(BigDecimal comPercent) {
        this.comPercent = comPercent;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }
}
