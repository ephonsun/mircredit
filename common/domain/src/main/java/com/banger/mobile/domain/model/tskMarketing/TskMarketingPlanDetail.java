/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-12-26
 */
package com.banger.mobile.domain.model.tskMarketing;

import java.math.BigDecimal;

import com.banger.mobile.domain.model.base.tskMarketing.BaseTskMarketingPlanDetail;

/**
 * @author yuanme
 * @version $Id: TskMarketingPlan.java,v 0.1 2012-12-26 下午4:58:51 Administrator Exp $
 */
public class TskMarketingPlanDetail extends BaseTskMarketingPlanDetail {

    private static final long serialVersionUID = 5458151476623618891L;

    private String            templateName;
    private Double            templateRate;
    private String            productCode;
    private String            productName;
    private String            executeUserName;
    private Integer           executeUserId;                          //执行用户
    private BigDecimal        todaySaleMoney;                         //当天销售额

    //任务相关信息字段
    private String            productTarget;
    private BigDecimal        daysRemainTarget;
    private BigDecimal        marketingTarget;
    private BigDecimal        saleMoney;
    private BigDecimal        remainTarget;
    private BigDecimal        planedTarget;
    private BigDecimal        notPlanedTarget;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getExecuteUserName() {
        return executeUserName;
    }

    public void setExecuteUserName(String executeUserName) {
        this.executeUserName = executeUserName;
    }

    public Integer getExecuteUserId() {
        return executeUserId;
    }

    public void setExecuteUserId(Integer executeUserId) {
        this.executeUserId = executeUserId;
    }

    public BigDecimal getTodaySaleMoney() {
        return todaySaleMoney;
    }

    public void setTodaySaleMoney(BigDecimal todaySaleMoney) {
        this.todaySaleMoney = todaySaleMoney;
    }

    public Double getTemplateRate() {
        return templateRate;
    }

    public void setTemplateRate(Double templateRate) {
        this.templateRate = templateRate;
    }

    public String getProductTarget() {
        return productTarget;
    }

    public void setProductTarget(String productTarget) {
        this.productTarget = productTarget;
    }

    public BigDecimal getDaysRemainTarget() {
        return daysRemainTarget;
    }

    public void setDaysRemainTarget(BigDecimal daysRemainTarget) {
        this.daysRemainTarget = daysRemainTarget;
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

    public BigDecimal getRemainTarget() {
        return remainTarget;
    }

    public void setRemainTarget(BigDecimal remainTarget) {
        this.remainTarget = remainTarget;
    }

    public BigDecimal getPlanedTarget() {
        return planedTarget;
    }

    public void setPlanedTarget(BigDecimal planedTarget) {
        this.planedTarget = planedTarget;
    }

    public BigDecimal getNotPlanedTarget() {
        return notPlanedTarget;
    }

    public void setNotPlanedTarget(BigDecimal notPlanedTarget) {
        this.notPlanedTarget = notPlanedTarget;
    }
}
