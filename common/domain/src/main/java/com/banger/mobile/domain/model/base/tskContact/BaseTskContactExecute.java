package com.banger.mobile.domain.model.base.tskContact;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * TskContactExecute entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BaseTskContactExecute extends BaseObject implements Cloneable {

	private static final long serialVersionUID = -599961580281066677L;
    // Fields

	private Integer executeId;
	private Integer contactId;
	private Integer userId;

	// Constructors

	/** default constructor */
	public BaseTskContactExecute() {
	}

	/** full constructor */
	public BaseTskContactExecute(Integer executeId, Integer contactId,
			Integer userId) {
		this.executeId = executeId;
		this.contactId = contactId;
		this.userId = userId;
	}
	
	
	public Object clone() throws CloneNotSupportedException {
		// 实现clone方法
		return super.clone();
	}

	// Property accessors

	public Integer getExecuteId() {
		return this.executeId;
	}

	public void setExecuteId(Integer executeId) {
		this.executeId = executeId;
	}

	public Integer getContactId() {
		return this.contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((contactId == null) ? 0 : contactId.hashCode());
		result = prime * result
				+ ((executeId == null) ? 0 : executeId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BaseTskContactExecute other = (BaseTskContactExecute) obj;
		if (contactId == null) {
			if (other.contactId != null)
				return false;
		} else if (!contactId.equals(other.contactId))
			return false;
		if (executeId == null) {
			if (other.executeId != null)
				return false;
		} else if (!executeId.equals(other.executeId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}