package com.banger.mobile.domain.model.maritalStatus;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.banger.mobile.domain.model.base.maritalStatus.BaseMaritalStatus;

public class MaritalStatus extends BaseMaritalStatus {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4660185503519747952L;
	public MaritalStatus()
	{
		super();
	}
	
	 public int hashCode()
	 {
		 return new HashCodeBuilder(120821503, 605718547).appendSuper(super.hashCode()).toHashCode();
	 }
}
