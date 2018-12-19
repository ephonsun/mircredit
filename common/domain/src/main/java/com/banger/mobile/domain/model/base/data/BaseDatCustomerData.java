/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :资料管理实体类
 * Author     :yuanme
 * Create Date:2012-11-12
 */
package com.banger.mobile.domain.model.base.data;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author yuanme
 * @version $Id: BaseDatCustomerData.java,v 0.1 2012-11-12 下午3:25:42 Administrator Exp $
 */
public class BaseDatCustomerData extends BaseObject {
    private static final long serialVersionUID = -6810923511688606194L;

    private Integer           customerDataId;
    private Integer           loanId;
    private Integer           customerId;
    private Integer           eventId;
    private Integer           uploadUserId;
    private Date              createDate;
    private Date              updateDate;
    private Integer           createUser;
    private Integer           updateUser;
    private Integer           requestId;
	public Integer getCustomerDataId() {
		return customerDataId;
	}
	public void setCustomerDataId(Integer customerDataId) {
		this.customerDataId = customerDataId;
	}
	public Integer getLoanId() {
		return loanId;
	}
	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public Integer getUploadUserId() {
		return uploadUserId;
	}
	public void setUploadUserId(Integer uploadUserId) {
		this.uploadUserId = uploadUserId;
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
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result
				+ ((customerDataId == null) ? 0 : customerDataId.hashCode());
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + ((loanId == null) ? 0 : loanId.hashCode());
		result = prime * result
				+ ((updateDate == null) ? 0 : updateDate.hashCode());
		result = prime * result
				+ ((updateUser == null) ? 0 : updateUser.hashCode());
		result = prime * result
				+ ((uploadUserId == null) ? 0 : uploadUserId.hashCode());
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
		BaseDatCustomerData other = (BaseDatCustomerData) obj;
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
		if (customerDataId == null) {
			if (other.customerDataId != null)
				return false;
		} else if (!customerDataId.equals(other.customerDataId))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (loanId == null) {
			if (other.loanId != null)
				return false;
		} else if (!loanId.equals(other.loanId))
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
		if (uploadUserId == null) {
			if (other.uploadUserId != null)
				return false;
		} else if (!uploadUserId.equals(other.uploadUserId))
			return false;
		return true;
	}
	public BaseDatCustomerData(Integer customerDataId, Integer loanId,
			Integer customerId, Integer eventId, Integer uploadUserId,
			Date createDate, Date updateDate, Integer createUser,
			Integer updateUser) {
		super();
		this.customerDataId = customerDataId;
		this.loanId = loanId;
		this.customerId = customerId;
		this.eventId = eventId;
		this.uploadUserId = uploadUserId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}
	public BaseDatCustomerData() {
		super();
		// TODO Auto-generated constructor stub
	}

    
}
