/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品-Domain-扩展1
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.domain.model.microProduct;

import com.banger.mobile.domain.annotation.EscapeClassFilter;
import com.banger.mobile.domain.model.base.microProduct.BasePdtProduct;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: PdtProduct.java,v 0.1 Nov 12, 2012 4:03:12 PM QianJie Exp $
 */
@EscapeClassFilter
public class PdtProduct extends BasePdtProduct {

    private static final long serialVersionUID = 605980357646370226L;

    public PdtProduct() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1069929189, -796475343).appendSuper(super.hashCode())
            .toHashCode();
    }

}
