/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:May 21, 2012
 */
package com.banger.mobile.domain.model.system;

import com.banger.mobile.domain.model.base.system.BaseCrmCustomerType;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: CrmCustomerType.java,v 0.1 May 21, 2012 10:28:39 AM QianJie Exp $
 */
public class CrmCustomerType extends BaseCrmCustomerType {

    private static final long serialVersionUID = 6745189119332716009L;

    public CrmCustomerType() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(749251585, -736774987).appendSuper(super.hashCode())
            .toHashCode();
    }

    
}
