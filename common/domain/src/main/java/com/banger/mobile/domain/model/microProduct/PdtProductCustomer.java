/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品意向客户-Domain-扩展1
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.domain.model.microProduct;

import com.banger.mobile.domain.model.base.microProduct.BasePdtProductCustomer;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: PdtProductCustomer.java,v 0.1 Nov 12, 2012 4:08:48 PM QianJie Exp $
 */
public class PdtProductCustomer extends BasePdtProductCustomer {

    private static final long serialVersionUID = -3167156220108461731L;

    public PdtProductCustomer() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(728507929, -1108671061).appendSuper(super.hashCode())
            .toHashCode();
    }

    
}
