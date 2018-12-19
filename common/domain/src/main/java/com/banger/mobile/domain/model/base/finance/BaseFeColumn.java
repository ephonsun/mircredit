package com.banger.mobile.domain.model.base.finance;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * FeColumn entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BaseFeColumn extends BaseObject implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4498311755380023011L;
	private Integer columnId;
	private String columnName;
	private String columnDescription;
	private Integer columnOrder;
	private Integer isStart;
	private Integer isDel;
	private Date createDate;
	private Date updateDate;
	private Integer createUser;
	private Integer updateUser;


	// Property accessors

	public BaseFeColumn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseFeColumn(Integer columnId, String columnName,
			String columnDescription, Integer columnOrder, Integer isStart,
			Integer isDel, Date createDate, Date updateDate,
			Integer createUser, Integer updateUser) {
		super();
		this.columnId = columnId;
		this.columnName = columnName;
		this.columnDescription = columnDescription;
		this.columnOrder = columnOrder;
		this.isStart = isStart;
		this.isDel = isDel;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	public Integer getColumnId() {
		return this.columnId;
	}

	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}

	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnDescription() {
		return this.columnDescription;
	}

	public void setColumnDescription(String columnDescription) {
		this.columnDescription = columnDescription;
	}

	public Integer getColumnOrder() {
		return this.columnOrder;
	}

	public void setColumnOrder(Integer columnOrder) {
		this.columnOrder = columnOrder;
	}

	public Integer getIsStart() {
		return this.isStart;
	}

	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
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
		result = prime
				* result
				+ ((columnDescription == null) ? 0 : columnDescription
						.hashCode());
		result = prime * result
				+ ((columnId == null) ? 0 : columnId.hashCode());
		result = prime * result
				+ ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result
				+ ((columnOrder == null) ? 0 : columnOrder.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result + ((isDel == null) ? 0 : isDel.hashCode());
		result = prime * result + ((isStart == null) ? 0 : isStart.hashCode());
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
		final BaseFeColumn other = (BaseFeColumn) obj;
		if (columnDescription == null) {
			if (other.columnDescription != null)
				return false;
		} else if (!columnDescription.equals(other.columnDescription))
			return false;
		if (columnId == null) {
			if (other.columnId != null)
				return false;
		} else if (!columnId.equals(other.columnId))
			return false;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (columnOrder == null) {
			if (other.columnOrder != null)
				return false;
		} else if (!columnOrder.equals(other.columnOrder))
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
		if (isDel == null) {
			if (other.isDel != null)
				return false;
		} else if (!isDel.equals(other.isDel))
			return false;
		if (isStart == null) {
			if (other.isStart != null)
				return false;
		} else if (!isStart.equals(other.isStart))
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