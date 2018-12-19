package com.banger.mobile.domain.model.educationalHistory;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.banger.mobile.domain.model.base.educationalHistory.BaseEducationalHistory;

public class EducationalHistory extends BaseEducationalHistory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2801953738582268087L;
	
	public EducationalHistory(){
		super();
	}
	
	 public int hashCode()
	 {
		 return new HashCodeBuilder(120821503, 605718547).appendSuper(super.hashCode()).toHashCode();
	 }

}
