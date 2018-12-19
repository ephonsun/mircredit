/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-5-31
 */
package com.banger.mobile.domain.model.funcAuth;

import com.banger.mobile.domain.model.base.funcAuth.BaseSysFuncAuth;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: SysFuncAuth.java,v 0.1 2012-5-31 上午11:41:17 cheny Exp $
 */
public class SysFuncAuth extends BaseSysFuncAuth{

    private static final long serialVersionUID = -7369982997580719760L;
    
    public SysFuncAuth(){
        
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1548266949, -1570886179).appendSuper(super.hashCode())
            .toHashCode();
    }
    
    
}
