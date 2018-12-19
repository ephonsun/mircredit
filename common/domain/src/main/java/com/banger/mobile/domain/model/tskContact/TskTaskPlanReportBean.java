/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :计划执行情况统计报表Bean
 * Author     :liyb
 * Create Date:2012-11-21
 */
package com.banger.mobile.domain.model.tskContact;

import java.io.Serializable;

/**
 * @author liyb
 * @version $Id: TskTaskPlanReportBean.java,v 0.1 2012-11-21 下午04:33:37 liyb Exp $
 */
public class TskTaskPlanReportBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer           executeUserId;         //执行者ID
    private String            executeUserName;       //执行者名称
    private Integer           planCount;             //计划联系量
    private Integer           planFinishCount;       //有效联系总量
    private Integer           planFinishLevelCount;  //有效联系完成量
    private Integer           planLevelCount;        //有效联系难以完成量
    private String            planRate;              //有效占计划比
    private Integer           planTaskCount;         //有效关联任务数
    
    public Integer getExecuteUserId() {
        return executeUserId;
    }
    public void setExecuteUserId(Integer executeUserId) {
        this.executeUserId = executeUserId;
    }
    public String getExecuteUserName() {
        return executeUserName;
    }
    public void setExecuteUserName(String executeUserName) {
        this.executeUserName = executeUserName;
    }
    public Integer getPlanCount() {
        return planCount;
    }
    public void setPlanCount(Integer planCount) {
        this.planCount = planCount;
    }
    public Integer getPlanFinishCount() {
        return planFinishCount;
    }
    public void setPlanFinishCount(Integer planFinishCount) {
        this.planFinishCount = planFinishCount;
    }
    public Integer getPlanFinishLevelCount() {
        return planFinishLevelCount;
    }
    public void setPlanFinishLevelCount(Integer planFinishLevelCount) {
        this.planFinishLevelCount = planFinishLevelCount;
    }
    public Integer getPlanLevelCount() {
        return planLevelCount;
    }
    public void setPlanLevelCount(Integer planLevelCount) {
        this.planLevelCount = planLevelCount;
    }
    public String getPlanRate() {
        return planRate;
    }
    public void setPlanRate(String planRate) {
        this.planRate = planRate;
    }
    public Integer getPlanTaskCount() {
        return planTaskCount;
    }
    public void setPlanTaskCount(Integer planTaskCount) {
        this.planTaskCount = planTaskCount;
    }
}
