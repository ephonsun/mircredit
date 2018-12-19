package com.banger.mobile.domain.model.base.customer;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
/**
 * CrmCustomer entity. @author MyEclipse Persistence Tools
 */

public class BaseFamilyName extends BaseObject implements java.io.Serializable {

	private static final long serialVersionUID = -3358923105124433667L;

	/**
	 * 
	 */
	private Integer familyNameId;			//百家姓ID
	private String familyName;				//百家姓
	private Date createDate;				//创建时间
	private Date updateDate;				//更新时间
	private Integer createUser;				//新建者
	private Integer updateUser;				//修改者
	
	public BaseFamilyName() {
	}
	
	public Integer getFamilyNameId() {
		return familyNameId;
	}
	public void setFamilyNameId(Integer familyNameId) {
		this.familyNameId = familyNameId;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public Integer getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	public BaseFamilyName(Integer familyNameId, String familyName,
			Date createDate, Date updateDate, Integer createUser,
			Integer updateUser) {
		super();
		this.familyNameId = familyNameId;
		this.familyName = familyName;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}
	
	public String toString() {
		return "BaseFamilyName [familyNameId=" + familyNameId + ", familyName="
				+ familyName + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", createUser=" + createUser + ", updateUser="
				+ updateUser + "]";
	}

	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result
				+ ((familyName == null) ? 0 : familyName.hashCode());
		result = prime * result
				+ ((familyNameId == null) ? 0 : familyNameId.hashCode());
		result = prime * result
				+ ((updateDate == null) ? 0 : updateDate.hashCode());
		result = prime * result
				+ ((updateUser == null) ? 0 : updateUser.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseFamilyName other = (BaseFamilyName) obj;
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
		if (familyName == null) {
			if (other.familyName != null)
				return false;
		} else if (!familyName.equals(other.familyName))
			return false;
		if (familyNameId == null) {
			if (other.familyNameId != null)
				return false;
		} else if (!familyNameId.equals(other.familyNameId))
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