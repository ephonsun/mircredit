/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :wumh
 * Create Date:2012-12-4
 */
package com.banger.mobile.domain.model.base.finance;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * FeKnowledgebaseType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BaseFeKnowledgebaseType extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5918275905055629540L;
	private Integer                           knowledgebaseTypeId;
	private Integer                           parentId;
	private String                            knowledgebaseTypeName;
	private Integer                           orderId;
	private Integer                           userId;
	private Integer                           isDel;
	private Date                              createDate;
	private Date                              updateDate;
	private Integer                           createUser;
	private Integer                           updateUser;
	
	public BaseFeKnowledgebaseType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseFeKnowledgebaseType(Integer knowledgebaseTypeId,
			Integer parentId, String knowledgebaseTypeName, Integer orderId,
			Integer userId, Integer isDel, Date createDate, Date updateDate,
			Integer createUser, Integer updateUser) {
		super();
		this.knowledgebaseTypeId = knowledgebaseTypeId;
		this.parentId = parentId;
		this.knowledgebaseTypeName = knowledgebaseTypeName;
		this.orderId = orderId;
		this.userId = userId;
		this.isDel = isDel;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	public Integer getKnowledgebaseTypeId() {
		return this.knowledgebaseTypeId;
	}

	public void setKnowledgebaseTypeId(Integer knowledgebaseTypeId) {
		this.knowledgebaseTypeId = knowledgebaseTypeId;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getKnowledgebaseTypeName() {
		return this.knowledgebaseTypeName;
	}

	public void setKnowledgebaseTypeName(String knowledgebaseTypeName) {
		this.knowledgebaseTypeName = knowledgebaseTypeName;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result + ((isDel == null) ? 0 : isDel.hashCode());
		result = prime
				* result
				+ ((knowledgebaseTypeId == null) ? 0 : knowledgebaseTypeId
						.hashCode());
		result = prime
				* result
				+ ((knowledgebaseTypeName == null) ? 0 : knowledgebaseTypeName
						.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result
				+ ((updateDate == null) ? 0 : updateDate.hashCode());
		result = prime * result
				+ ((updateUser == null) ? 0 : updateUser.hashCode());
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
		final BaseFeKnowledgebaseType other = (BaseFeKnowledgebaseType) obj;
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
		if (isDel == null) {
			if (other.isDel != null)
				return false;
		} else if (!isDel.equals(other.isDel))
			return false;
		if (knowledgebaseTypeId == null) {
			if (other.knowledgebaseTypeId != null)
				return false;
		} else if (!knowledgebaseTypeId.equals(other.knowledgebaseTypeId))
			return false;
		if (knowledgebaseTypeName == null) {
			if (other.knowledgebaseTypeName != null)
				return false;
		} else if (!knowledgebaseTypeName.equals(other.knowledgebaseTypeName))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
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
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	/**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("startRow",
            this.getStartRow()).append("knowledgebaseTypeId", this.knowledgebaseTypeId).append("parentId",
            this.parentId).append("knowledgebaseTypeName",this.knowledgebaseTypeName).append("orderId",
            this.orderId).append("userId",this.userId).append("isDel",this.isDel).append("createDate",
            this.createDate).append("createUser",this.createUser).append("updateDate",this.updateDate)
            .append("updateUser",this.updateUser).toString();
    }

}