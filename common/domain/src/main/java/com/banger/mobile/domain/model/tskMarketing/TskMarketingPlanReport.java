/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2013-1-10
 */
package com.banger.mobile.domain.model.tskMarketing;

import java.math.BigDecimal;

/**
 * @author yuanme
 * @version $Id: TskMarketingPlanReport.java,v 0.1 2013-1-10 上午9:47:24 Administrator Exp $
 */
public class TskMarketingPlanReport {
    private Integer    executeUserId;
    private String     executeUserName;
    private String     productTarget;
    private BigDecimal marketingTarget;
    private BigDecimal planTarget;
    private BigDecimal saleMoney;
    private BigDecimal percent;
    private Integer    taskCount;
    private Integer    sortNo;

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

    public String getProductTarget() {
        return productTarget;
    }

    public void setProductTarget(String productTarget) {
        this.productTarget = productTarget;
    }

    public BigDecimal getMarketingTarget() {
        return marketingTarget;
    }

    public void setMarketingTarget(BigDecimal marketingTarget) {
        this.marketingTarget = marketingTarget;
    }

    public BigDecimal getSaleMoney() {
        return saleMoney;
    }

    public void setSaleMoney(BigDecimal saleMoney) {
        this.saleMoney = saleMoney;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public BigDecimal getPlanTarget() {
        return planTarget;
    }

    public void setPlanTarget(BigDecimal planTarget) {
        this.planTarget = planTarget;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}
