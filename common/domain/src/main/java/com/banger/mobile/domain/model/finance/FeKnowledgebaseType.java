package com.banger.mobile.domain.model.finance;

import com.banger.mobile.domain.model.base.finance.BaseFeKnowledgebaseType;

/**
 * FeKnowledgebaseType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class FeKnowledgebaseType extends BaseFeKnowledgebaseType implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3438793656540368535L;
	
	private String                        parentName;

	public String getParentName() {
		return parentName;
	}

	public FeKnowledgebaseType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((parentName == null) ? 0 : parentName.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FeKnowledgebaseType other = (FeKnowledgebaseType) obj;
		if (parentName == null) {
			if (other.parentName != null)
				return false;
		} else if (!parentName.equals(other.parentName))
			return false;
		return true;
	}

}