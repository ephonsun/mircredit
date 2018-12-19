/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-7-17
 */
package com.banger.mobile.domain.model.PlnReportTemplate;

import com.banger.mobile.domain.model.base.PlnReportTemplate.BasePlnVarTypeSub;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: PlnVarTypeSub.java,v 0.1 2012-7-17 下午4:44:09 cheny Exp $
 */
public class PlnVarTypeSub extends BasePlnVarTypeSub{

    private static final long serialVersionUID = 3273707395719738495L;

    /**
     * 
     */
    public PlnVarTypeSub() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1879256139, -978866179).appendSuper(super.hashCode())
            .toHashCode();
    }

    
}
