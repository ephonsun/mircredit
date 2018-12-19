/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :自定义查询表
 * Author     :zsw
 * Create Date:2012-5-24
 */
package com.banger.mobile.domain.model.customer;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

public class CrmUserQuery  extends BaseObject implements java.io.Serializable {
	private static final long serialVersionUID = 7935081358253908448L;
	private Integer userQueryId;			//查询Id
	private Integer userId;					//用户Id
	private String queryName;				//自定义查询名称
	private Integer sortno;					//排序号
	private String queryDetail;				//条件内容
	private Integer isDel;					//删除标志
	private Integer queryType;				//类型，菜单还是短信
	private Date createDate;				//创建时间
	private Date updateDate;				//更新时间
	private Integer createUser;				//新建者
	private Integer updateUser;				//修改者
	public Integer getUserQueryId() {
		return userQueryId;
	}
	public void setUserQueryId(Integer userQueryId) {
		this.userQueryId = userQueryId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public Integer getSortno() {
		return sortno;
	}
	public void setSortno(Integer sortno) {
		this.sortno = sortno;
	}
	public String getQueryDetail() {
		return queryDetail;
	}
	public void setQueryDetail(String queryDetail) {
		this.queryDetail = queryDetail;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public Integer getQueryType() {
		return queryType;
	}
	public void setQueryType(Integer queryType) {
		this.queryType = queryType;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result + ((isDel == null) ? 0 : isDel.hashCode());
		result = prime * result
				+ ((queryDetail == null) ? 0 : queryDetail.hashCode());
		result = prime * result
				+ ((queryName == null) ? 0 : queryName.hashCode());
		result = prime * result
				+ ((queryType == null) ? 0 : queryType.hashCode());
		result = prime * result + ((sortno == null) ? 0 : sortno.hashCode());
		result = prime * result
				+ ((updateDate == null) ? 0 : updateDate.hashCode());
		result = prime * result
				+ ((updateUser == null) ? 0 : updateUser.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((userQueryId == null) ? 0 : userQueryId.hashCode());
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
		CrmUserQuery other = (CrmUserQuery) obj;
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
		if (queryDetail == null) {
			if (other.queryDetail != null)
				return false;
		} else if (!queryDetail.equals(other.queryDetail))
			return false;
		if (queryName == null) {
			if (other.queryName != null)
				return false;
		} else if (!queryName.equals(other.queryName))
			return false;
		if (queryType == null) {
			if (other.queryType != null)
				return false;
		} else if (!queryType.equals(other.queryType))
			return false;
		if (sortno == null) {
			if (other.sortno != null)
				return false;
		} else if (!sortno.equals(other.sortno))
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
		if (userQueryId == null) {
			if (other.userQueryId != null)
				return false;
		} else if (!userQueryId.equals(other.userQueryId))
			return false;
		return true;
	}
	
	
	
}
