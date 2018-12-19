/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       : 投资偏好
 * Author     :yujh
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.domain.model.rskIntervalType;

import com.banger.mobile.domain.model.base.rskIntervalType.BaseRskIntervalType;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: RskIntervalType.java,v 0.1 Jul 16, 2012 3:04:31 PM Administrator Exp $
 */
public class RskIntervalType extends BaseRskIntervalType{

    private static final long serialVersionUID = 8663490248783693701L;
    
    public RskIntervalType(){
        
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(2070084033, 1570979559).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
