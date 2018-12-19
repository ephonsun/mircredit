/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品知识库-Domain-扩展2
 * Author     :QianJie
 * Create Date:Nov 14, 2012
 */
package com.banger.mobile.domain.model.microProduct;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.banger.mobile.domain.model.base.microProduct.BasePdtLibrary;

/**
 * @author QianJie
 * @version $Id: PdtLibraryBean.java,v 0.1 Nov 14, 2012 5:44:34 PM QianJie Exp $
 */
public class PdtLibraryBean extends BasePdtLibrary{
    private static final long serialVersionUID = 8106797071475020393L;

    private String            libName;                                 //知识库分类名称

    public String getLibName() {
        return libName;
    }

    public void setLibName(String libName) {
        this.libName = libName;
    }

    public PdtLibraryBean() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1616421967, 1543557583).appendSuper(super.hashCode()).append(
            this.libName).toHashCode();
    }
}
