/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:May 28, 2012
 */
package com.banger.mobile.domain.model.customer;

import com.banger.mobile.domain.model.base.customer.BaseCrmCustomerExtField;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: CrmCustomerExtField.java,v 0.1 May 28, 2012 4:30:57 PM QianJie Exp $
 */
public class CrmCustomerExtField extends BaseCrmCustomerExtField{

    private static final long serialVersionUID = -1724077228416700709L;

    private String      lineNumber; 
    public CrmCustomerExtField() {
        super();
    }

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
        return new HashCodeBuilder(521872231, 321961663).appendSuper(super.hashCode()).toHashCode();
    }

}
