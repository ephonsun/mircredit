/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :hk
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.domain.model.product;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.banger.mobile.domain.model.base.product.BaseProduct;


/**
 * @author hk
 * @version $Id: Product.java,v 0.1 Jul 16, 2012 10:43:50 AM hk Exp $
 */
public class ProductBean extends BaseProduct{

    private static final long serialVersionUID = 3137029795999960464L;
    
    public ProductBean(){}
    
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-730528716, -106810363).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
