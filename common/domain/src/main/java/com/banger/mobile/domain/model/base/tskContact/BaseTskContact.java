package com.banger.mobile.domain.model.base.tskContact;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * TskContact entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BaseTskContact  extends BaseObject {

	// Fields

	private static final long serialVersionUID = 1362887922048479495L;
	
    private Integer                            contactId;
	private String                             contactTitle;
	private Integer                            contactPurpose;
	private Integer                            isEmergency;
	private Date                               startDate;
	private Date                               endDate;
	private String                             remark;
	private Integer                            assignUserId;
	private Integer                            isNextContact;
	private Integer                            isSuspend;
	private Integer                            isDel;
	private Date                               createDate;
	private Date                               updateDate;
	private Integer                            createUser;
	private Integer                            updateUser;

	// Constructors

	/** default constructor */
	public BaseTskContact() {
	}

	/** minimal constructor */
	public BaseTskContact(Integer contactId, String contactTitle,
			Integer contactPurpose, Integer isEmergency, Date startDate,
			Date endDate, Integer assignUserId, Integer isNextContact,
			Integer isSuspend, Integer isDel, Date createDate,
			Integer createUser) {
		this.contactId = contactId;
		this.contactTitle = contactTitle;
		this.contactPurpose = contactPurpose;
		this.isEmergency = isEmergency;
		this.startDate = startDate;
		this.endDate = endDate;
		this.assignUserId = assignUserId;
		this.isNextContact = isNextContact;
		this.isSuspend = isSuspend;
		this.isDel = isDel;
		this.createDate = createDate;
		this.createUser = createUser;
	}

	/** full constructor */
	public BaseTskContact(Integer contactId, String contactTitle,
			Integer contactPurpose, Integer isEmergency, Date startDate,
			Date endDate, String remark, Integer assignUserId,
			Integer isNextContact, Integer isSuspend, Integer isDel,
			Date createDate, Date updateDate, Integer createUser,
			Integer updateUser) {
		this.contactId = contactId;
		this.contactTitle = contactTitle;
		this.contactPurpose = contactPurpose;
		this.isEmergency = isEmergency;
		this.startDate = startDate;
		this.endDate = endDate;
		this.remark = remark;
		this.assignUserId = assignUserId;
		this.isNextContact = isNextContact;
		this.isSuspend = isSuspend;
		this.isDel = isDel;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	// Property accessors

	public Integer getContactId() {
		return this.contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public String getContactTitle() {
		return this.contactTitle;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	public Integer getContactPurpose() {
		return this.contactPurpose;
	}

	public void setContactPurpose(Integer contactPurpose) {
		this.contactPurpose = contactPurpose;
	}

	public Integer getIsEmergency() {
		return this.isEmergency;
	}

	public void setIsEmergency(Integer isEmergency) {
		this.isEmergency = isEmergency;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getAssignUserId() {
		return this.assignUserId;
	}

	public void setAssignUserId(Integer assignUserId) {
		this.assignUserId = assignUserId;
	}

	public Integer getIsNextContact() {
		return this.isNextContact;
	}

	public void setIsNextContact(Integer isNextContact) {
		this.isNextContact = isNextContact;
	}

	public Integer getIsSuspend() {
		return this.isSuspend;
	}

	public void setIsSuspend(Integer isSuspend) {
		this.isSuspend = isSuspend;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((assignUserId == null) ? 0 : assignUserId.hashCode());
		result = prime * result
				+ ((contactId == null) ? 0 : contactId.hashCode());
		result = prime * result
				+ ((contactPurpose == null) ? 0 : contactPurpose.hashCode());
		result = prime * result
				+ ((contactTitle == null) ? 0 : contactTitle.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((isDel == null) ? 0 : isDel.hashCode());
		result = prime * result
				+ ((isEmergency == null) ? 0 : isEmergency.hashCode());
		result = prime * result
				+ ((isNextContact == null) ? 0 : isNextContact.hashCode());
		result = prime * result
				+ ((isSuspend == null) ? 0 : isSuspend.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result
				+ ((updateDate == null) ? 0 : updateDate.hashCode());
		result = prime * result
				+ ((updateUser == null) ? 0 : updateUser.hashCode());
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
		final BaseTskContact other = (BaseTskContact) obj;
		if (assignUserId == null) {
			if (other.assignUserId != null)
				return false;
		} else if (!assignUserId.equals(other.assignUserId))
			return false;
		if (contactId == null) {
			if (other.contactId != null)
				return false;
		} else if (!contactId.equals(other.contactId))
			return false;
		if (contactPurpose == null) {
			if (other.contactPurpose != null)
				return false;
		} else if (!contactPurpose.equals(other.contactPurpose))
			return false;
		if (contactTitle == null) {
			if (other.contactTitle != null)
				return false;
		} else if (!contactTitle.equals(other.contactTitle))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (createUser == null) {
			if (other.createUser != null)
				return false;
		} else if (!createUser.equals(other.createUser))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (isDel == null) {
			if (other.isDel != null)
				return false;
		} else if (!isDel.equals(other.isDel))
			return false;
		if (isEmergency == null) {
			if (other.isEmergency != null)
				return false;
		} else if (!isEmergency.equals(other.isEmergency))
			return false;
		if (isNextContact == null) {
			if (other.isNextContact != null)
				return false;
		} else if (!isNextContact.equals(other.isNextContact))
			return false;
		if (isSuspend == null) {
			if (other.isSuspend != null)
				return false;
		} else if (!isSuspend.equals(other.isSuspend))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (updateDate == null) {
			if (other.updateDate != null)
				return false;
		} else if (!updateDate.equals(other.updateDate))
			return false;
		if (updateUser == null) {
			if (other.updateUser != null)
				return false;
		} else if (!updateUser.equals(other.updateUser))
			return false;
		return true;
	}

}