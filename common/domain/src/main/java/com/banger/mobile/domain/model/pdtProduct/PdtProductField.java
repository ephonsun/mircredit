/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       : 产品自定义字段
 * Author     :yujh
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.domain.model.pdtProduct;

import com.banger.mobile.domain.model.base.product.BasePdtProductField;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: PdtProductField.java,v 0.1 2012-12-24 上午9:48:43 cheny Exp $
 */

public class PdtProductField extends BasePdtProductField {

    private static final long serialVersionUID = -5065600909603675701L;
    
    private String lineNumber;          //行号
    
    public String getLineNumber() {
        return lineNumber;
    }
    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }
    
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-612154431, -1604275437).appendSuper(super.hashCode())
            .toHashCode();
    }

   

}