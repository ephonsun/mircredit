package com.banger.mobile.domain.model.legalForm;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.banger.mobile.domain.model.base.legalForm.BaseLegalForm;

public class LegalForm extends BaseLegalForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2101887133858076801L;
	public LegalForm()
	{
		super();
	}
	
	 public int hashCode()
	 {
		 return new HashCodeBuilder(120821503, 605718547).appendSuper(super.hashCode()).toHashCode();
	 }
}
