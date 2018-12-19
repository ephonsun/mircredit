/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-7-17
 */
package com.banger.mobile.domain.model.PlnReportTemplate;

import com.banger.mobile.domain.model.base.PlnReportTemplate.BasePlnReportTemplateVar;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: PlnReportTemplateVar.java,v 0.1 2012-7-17 下午4:41:53 cheny Exp $
 */
public class PlnReportTemplateVar extends BasePlnReportTemplateVar{

    private static final long serialVersionUID = 1024814555280665463L;

    /**
     * 
     */
    public PlnReportTemplateVar() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1298226127, 1253204265).appendSuper(super.hashCode())
            .toHashCode();
    }

    
}
