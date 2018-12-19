/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品类型
 * Author     :yujh
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.domain.model.system;

import com.banger.mobile.domain.model.base.system.BaseProductType;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author yujh
 * @version $Id: ProductType.java,v 0.1 Aug 14, 2012 3:25:38 PM Administrator Exp $
 */
public class ProductType extends BaseProductType {

    private static final long serialVersionUID = 7874992253650635113L;
    
    public ProductType(){
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(759315229, -789953697).appendSuper(super.hashCode())
            .toHashCode();
    }
    
}
