/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       : 产品类型模版字段
 * Author     :yujh
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.domain.model.pdtProduct;

import java.util.ArrayList;
import java.util.List;

import com.banger.mobile.domain.model.base.product.BasePdtTemplateField;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author cheny
 * @version $Id: PdtTemplateField.java,v 0.1 2012-12-24 上午9:48:43 cheny Exp $
 */

public class PdtTemplateField extends BasePdtTemplateField {

    private static final long serialVersionUID = 1736363107867071878L;

    private List<PdtFieldCodedata> codes;

	/**
	 * 
	 * @param flag 0新增编辑 1查询 2查看
	 * @return
	 */
    public Integer getColSpan(Integer flag) {
    	String type = this.getTemplateFieldType();
    	if("Date".equalsIgnoreCase(type) && flag.intValue()==1)return 2;
    	if("Number".equalsIgnoreCase(type) && flag.intValue()==1)return 2;
		if("TextArea".equalsIgnoreCase(type) && flag.intValue()!=1)return 2;
		return 1;
	}
    
    public PdtTemplateField(){
    	super();
    	this.codes= new ArrayList<PdtFieldCodedata>();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-2045825859, 913346651).appendSuper(super.hashCode())
            .toHashCode();
    }

	public List<PdtFieldCodedata> getCodes() {
		return codes;
	}

	public void setCodes(List<PdtFieldCodedata> codes) {
		this.codes = codes;
	}
}