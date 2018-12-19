/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       : 产品类型模版
 * Author     :yujh
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.domain.model.pdtProduct;

import java.util.ArrayList;
import java.util.List;

import com.banger.mobile.domain.model.base.product.BasePdtTemplate;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: PdtTemplate.java,v 0.1 2012-12-24 上午9:48:43 cheny Exp $
 */

public class PdtTemplate extends BasePdtTemplate {

    private static final long serialVersionUID = 3993758667983428920L;
    
    private List<PdtTemplateField> fields;

    public List<PdtTemplateField> getFields() {
		return fields;
	}

	public void setFields(List<PdtTemplateField> fields) {
		this.fields = fields;
	}
	
	public PdtTemplate(){
		super();
		this.fields = new ArrayList<PdtTemplateField>();
	}

	/**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1943007155, -1466187231).appendSuper(super.hashCode())
            .toHashCode();
    }

   

}