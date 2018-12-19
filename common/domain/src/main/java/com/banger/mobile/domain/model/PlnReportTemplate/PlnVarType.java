/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-7-17
 */
package com.banger.mobile.domain.model.PlnReportTemplate;

import com.banger.mobile.domain.model.base.PlnReportTemplate.BasePlnVarType;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: PlnVarType.java,v 0.1 2012-7-17 下午4:43:15 cheny Exp $
 */
public class PlnVarType extends BasePlnVarType{

    private static final long serialVersionUID = 5463227892033998513L;

    /**
     * 
     */
    public PlnVarType() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-535011171, 2108043305).appendSuper(super.hashCode())
            .toHashCode();
    }

    
}
