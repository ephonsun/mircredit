/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:May 21, 2012
 */
package com.banger.mobile.domain.model.system;

import com.banger.mobile.domain.model.base.system.BaseCrmCustomerIndustry;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author QianJie
 * @version $Id: BaseCrmCustomerType.java,v 0.1 May 21, 2012 10:08:26 AM QianJie Exp $
 */
public class CrmCustomerIndustry extends BaseCrmCustomerIndustry{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8802441369534574118L;

	public CrmCustomerIndustry() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-569139731, 73224435).appendSuper(
				super.hashCode()).toHashCode();
	}
	
	

}
