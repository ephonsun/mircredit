/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划报告模板
 * Author     :cheny
 * Create Date:2012-7-16
 */
package com.banger.mobile.domain.model.PlnReportTemplate;

import com.banger.mobile.domain.model.base.PlnReportTemplate.BasePlnReportTemplate;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: PlnReportTemplate.java,v 0.1 2012-7-16 上午9:55:35 cheny Exp $
 */
public class PlnReportTemplate extends BasePlnReportTemplate{

    private static final long serialVersionUID = 4207783634196178112L;
    
    /**
     * 
     */
    public PlnReportTemplate() {
        
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1194425563, 1829771819).appendSuper(super.hashCode())
            .toHashCode();
    }

    
}
