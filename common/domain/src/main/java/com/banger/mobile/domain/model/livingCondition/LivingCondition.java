package com.banger.mobile.domain.model.livingCondition;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.banger.mobile.domain.model.base.livingCondition.BaseLivingCondition;

public class LivingCondition extends BaseLivingCondition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2816701691473879703L;
	public LivingCondition()
	{
		super();
	}
	
	 public int hashCode()
	 {
		 return new HashCodeBuilder(120821503, 605718547).appendSuper(super.hashCode()).toHashCode();
	 }
}
