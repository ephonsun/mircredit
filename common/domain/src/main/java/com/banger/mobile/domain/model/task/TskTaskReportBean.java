/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务完成统计报表
 * Author     :liyb
 * Create Date:2012-9-5
 */
package com.banger.mobile.domain.model.task;

import java.io.Serializable;

/**
 * @author liyb
 * @version $Id: TskTaskReportBean.java,v 0.1 2012-9-5 下午05:38:58 liyb Exp $
 */
public class TskTaskReportBean implements Serializable {

    private static final long serialVersionUID = -1815669384992295023L;
    private Integer           userId;                                   //ID
    private String            userName;                                 //名称
    private Integer           taskCount;                                //总量
    private Integer           finishCount;                              //完成量
    private String            finishRate;                               //完成占比
    private Integer           unFinishCount;                            //未完成量
    private String            unFinishRate;                             //未完成占比
    private Integer           expiredCount;                             //过期量
    private String            expiredRate;                              //过期占比
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
    public Integer getTaskCount() {
        return taskCount;
    }
    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }
    public Integer getFinishCount() {
        return finishCount;
    }
    public void setFinishCount(Integer finishCount) {
        this.finishCount = finishCount;
    }
    public String getFinishRate() {
        return finishRate;
    }
    public void setFinishRate(String finishRate) {
        this.finishRate = finishRate;
    }
    public Integer getUnFinishCount() {
        return unFinishCount;
    }
    public void setUnFinishCount(Integer unFinishCount) {
        this.unFinishCount = unFinishCount;
    }
    public String getUnFinishRate() {
        return unFinishRate;
    }
    public void setUnFinishRate(String unFinishRate) {
        this.unFinishRate = unFinishRate;
    }
    public Integer getExpiredCount() {
        return expiredCount;
    }
    public void setExpiredCount(Integer expiredCount) {
        this.expiredCount = expiredCount;
    }
    public String getExpiredRate() {
        return expiredRate;
    }
    public void setExpiredRate(String expiredRate) {
        this.expiredRate = expiredRate;
    }

}
