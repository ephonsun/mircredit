/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-12-26
 */
package com.banger.mobile.domain.model.tskMarketing;

import java.math.BigDecimal;

import com.banger.mobile.domain.model.base.tskMarketing.BaseTskMarketingPlan;

/**
 * @author yuanme
 * @version $Id: TskMarketingPlan.java,v 0.1 2012-12-26 下午4:58:51 Administrator Exp $
 */
public class TskMarketingPlan extends BaseTskMarketingPlan {

    private static final long serialVersionUID = 1696646917493769935L;

    private String            executeUserName;
    private BigDecimal        planTotalTarget;                        //计划营销额
    private BigDecimal        saleTotalTarget;                        //实际营销额
    private String            gradeName;
    private String            marketingTitle;

    public String getExecuteUserName() {
        return executeUserName;
    }

    public void setExecuteUserName(String executeUserName) {
        this.executeUserName = executeUserName;
    }

    public BigDecimal getPlanTotalTarget() {
        return planTotalTarget;
    }

    public void setPlanTotalTarget(BigDecimal planTotalTarget) {
        this.planTotalTarget = planTotalTarget;
    }

    public BigDecimal getSaleTotalTarget() {
        return saleTotalTarget;
    }

    public void setSaleTotalTarget(BigDecimal saleTotalTarget) {
        this.saleTotalTarget = saleTotalTarget;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getMarketingTitle() {
        return marketingTitle;
    }

    public void setMarketingTitle(String marketingTitle) {
        this.marketingTitle = marketingTitle;
    }

}
