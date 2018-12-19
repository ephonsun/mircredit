/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品类型模版下拉字段项
 * Author     :yujh
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.domain.model.pdtProduct;

import com.banger.mobile.domain.model.base.product.BasePdtFieldCodedata;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: PdtFieldCodedata.java,v 0.1 2012-12-24 上午9:48:43 cheny Exp $
 */

public class PdtFieldCodedata extends BasePdtFieldCodedata{

    private static final long serialVersionUID = 1577715083404653247L;

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(616805729, 1547975067).appendSuper(super.hashCode())
            .toHashCode();
    }

    
}