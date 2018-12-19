/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-6-8
 */
package com.banger.mobile.domain.model.customer;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.banger.mobile.domain.model.base.customer.BaseCrmCustomerImport;

/**
 * @author yangyang
 * @version $Id: CrmCustomerImport.java,v 0.1 2012-6-8 下午4:53:19 yangyong Exp $
 */
public class CrmCustomerImport extends BaseCrmCustomerImport {

    private static final long serialVersionUID = -1153494423619564L;

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1767493753, -1616859145).appendSuper(super.hashCode())
            .toHashCode();
    }

   
    

}
