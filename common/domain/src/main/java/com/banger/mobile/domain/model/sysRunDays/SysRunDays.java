/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-9-6
 */
package com.banger.mobile.domain.model.sysRunDays;

import com.banger.mobile.domain.model.base.sysRunDays.BaseSysRunDays;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: SysRunDays.java,v 0.1 2012-9-6 下午1:26:54 cheny Exp $
 */
public class SysRunDays extends BaseSysRunDays{

    private static final long serialVersionUID = 1915267004851055245L;

    /**
     * 
     */
    public SysRunDays() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(53542555, -1668705563).appendSuper(super.hashCode())
            .toHashCode();
    }

    
}
