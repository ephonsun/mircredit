/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :收益类型
 * Author     :yujh
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.domain.model.system;

import com.banger.mobile.domain.model.base.system.BaseProfitType;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: ProfitType.java,v 0.1 Aug 14, 2012 3:29:48 PM Administrator Exp $
 */
public class ProfitType extends BaseProfitType {

    private static final long serialVersionUID = 1217466592951141310L;
    
    public ProfitType(){
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1776651333, -2039319519).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
