package com.banger.mobile.domain.model.orgType;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.banger.mobile.domain.model.base.orgType.BaseOrgType;

public class OrgType extends BaseOrgType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3177389499609698622L;
	public OrgType()
	{
		super();
	}
	
	 public int hashCode()
	 {
		 return new HashCodeBuilder(120821503, 605718547).appendSuper(super.hashCode()).toHashCode();
	 }
}
