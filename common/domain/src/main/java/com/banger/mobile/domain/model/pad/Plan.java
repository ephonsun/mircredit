/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-7-19
 */
package com.banger.mobile.domain.model.pad;

import com.banger.mobile.domain.model.plnFastPlan.PlnFastPlan;

/**
 * @author yuanme
 * @version Plan.java,v 0.1 2012-7-19 下午4:18:06
 */
public class Plan extends PlnFastPlan {
    private static final long serialVersionUID = -2768182506850178711L;
    
    private String customerTitle;
    private Integer planId;

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }
}
