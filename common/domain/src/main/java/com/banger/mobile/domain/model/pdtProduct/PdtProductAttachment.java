/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       : 产品附件
 * Author     :yujh
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.domain.model.pdtProduct;

import com.banger.mobile.domain.model.base.product.BasePdtProductAttachment;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: PdtProductAttachment.java,v 0.1 2012-12-24 上午9:48:43 cheny Exp $
 */

public class PdtProductAttachment extends BasePdtProductAttachment {

    private static final long serialVersionUID = -1636892201362944545L;

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1959909381, 633194153).appendSuper(super.hashCode())
            .toHashCode();
    }

    
}