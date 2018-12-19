/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划报告模板
 * Author     :cheny
 * Create Date:2012-7-16
 */
package com.banger.mobile.domain.model.PlnReportTemplate;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: PlnReportTemplate.java,v 0.1 2012-7-16 上午9:55:35 cheny Exp $
 */
public class PlnReportTemplateBean extends PlnReportTemplate{

    private static final long serialVersionUID = 4207783634196178112L;
    
    private String planTypeName;      //规划类型名称

    private String intervalTypeName;  // 投资偏好
    
    public String getPlanTypeName() {
        return planTypeName;
    }

    public void setPlanTypeName(String planTypeName) {
        this.planTypeName = planTypeName;
    }
    
    

    public String getIntervalTypeName() {
        return intervalTypeName;
    }

    public void setIntervalTypeName(String intervalTypeName) {
        this.intervalTypeName = intervalTypeName;
    }

    /**
     * 
     */
    public PlnReportTemplateBean() {
        
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1194425563, 1829771819).appendSuper(super.hashCode())
            .toHashCode();
    }

    
}
