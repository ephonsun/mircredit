/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :柜台人员
 * Author     :yujh
 * Create Date:Sep 4, 2012
 */
package com.banger.mobile.domain.model.base.crmCounterUser;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: BaseCrmCounterUser.java,v 0.1 Sep 4, 2012 11:30:04 AM Administrator Exp $
 */
public class BaseCrmCounterUser extends BaseObject {

    private static final long serialVersionUID = 4622291874687854917L;
    private Integer     counterUserId;
    private String      counterUserAccount;
    private String      counterUserName;
    private Integer     userId;
    private Integer     deptId;
    private Date        createDate;
    private Date        updateDate;
    private Integer	    isDel;
	public Integer getCounterUserId() {
		return counterUserId;
	}
	public void setCounterUserId(Integer counterUserId) {
		this.counterUserId = counterUserId;
	}
	public String getCounterUserAccount() {
		return counterUserAccount;
	}
	public void setCounterUserAccount(String counterUserAccount) {
		this.counterUserAccount = counterUserAccount;
	}
	public String getCounterUserName() {
		return counterUserName;
	}
	public void setCounterUserName(String counterUserName) {
		this.counterUserName = counterUserName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
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
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((counterUserAccount == null) ? 0 : counterUserAccount
						.hashCode());
		result = prime * result
				+ ((counterUserId == null) ? 0 : counterUserId.hashCode());
		result = prime * result
				+ ((counterUserName == null) ? 0 : counterUserName.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((deptId == null) ? 0 : deptId.hashCode());
		result = prime * result + ((isDel == null) ? 0 : isDel.hashCode());
		result = prime * result
				+ ((updateDate == null) ? 0 : updateDate.hashCode());
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
		BaseCrmCounterUser other = (BaseCrmCounterUser) obj;
		if (counterUserAccount == null) {
			if (other.counterUserAccount != null)
				return false;
		} else if (!counterUserAccount.equals(other.counterUserAccount))
			return false;
		if (counterUserId == null) {
			if (other.counterUserId != null)
				return false;
		} else if (!counterUserId.equals(other.counterUserId))
			return false;
		if (counterUserName == null) {
			if (other.counterUserName != null)
				return false;
		} else if (!counterUserName.equals(other.counterUserName))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (deptId == null) {
			if (other.deptId != null)
				return false;
		} else if (!deptId.equals(other.deptId))
			return false;
		if (isDel == null) {
			if (other.isDel != null)
				return false;
		} else if (!isDel.equals(other.isDel))
			return false;
		if (updateDate == null) {
			if (other.updateDate != null)
				return false;
		} else if (!updateDate.equals(other.updateDate))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	public BaseCrmCounterUser(Integer counterUserId, String counterUserAccount,
			String counterUserName, Integer userId, Integer deptId,
			Date createDate, Date updateDate, Integer isDel) {
		super();
		this.counterUserId = counterUserId;
		this.counterUserAccount = counterUserAccount;
		this.counterUserName = counterUserName;
		this.userId = userId;
		this.deptId = deptId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.isDel = isDel;
	}
	public BaseCrmCounterUser() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
