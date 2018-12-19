/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品知识库-Domain-扩展1
 * Author     :QianJie
 * Create Date:Nov 12, 2012
 */
package com.banger.mobile.domain.model.microProduct;

import com.banger.mobile.domain.annotation.EscapeClassFilter;
import com.banger.mobile.domain.model.base.microProduct.BasePdtLibrary;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * @author QianJie
 * @version $Id: PdtLibrary.java,v 0.1 Nov 12, 2012 3:37:14 PM QianJie Exp $
 */
@EscapeClassFilter
public class PdtLibrary extends BasePdtLibrary {

    private static final long serialVersionUID = 8106797071475020393L;

    public PdtLibrary() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(385763863, -1646656271).appendSuper(super.hashCode())
            .toHashCode();
    }   
}
