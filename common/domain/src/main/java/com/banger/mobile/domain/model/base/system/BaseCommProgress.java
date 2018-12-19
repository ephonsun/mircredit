package com.banger.mobile.domain.model.base.system;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：May 21, 2012 9:21:29 AM
 * 类说明
 */
public class BaseCommProgress extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2878402775009649129L;
	
	private Integer          commProgressId;    //沟通进度Id
	private String           commProgressName;  //沟通进度名称
	private Integer          sortNo;            //排序Id
	private Integer          isDel;             //是否删除
	private Date             createDate;        //创建时间  
	private Date             updateDate;        //更新时间
	private Integer          createUser;        //插入用户
	private Integer          updateUser;        //更新用户
	

	public BaseCommProgress() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BaseCommProgress(Integer commProgressId, String commProgressName,
			Integer sortNo, Integer isDel, Date createDate, Date updateDate,
			Integer createUser, Integer updateUser) {
		super();
		this.commProgressId = commProgressId;
		this.commProgressName = commProgressName;
		this.sortNo = sortNo;
		this.isDel = isDel;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	public Integer getCommProgressId() {
		return commProgressId;
	}

	public void setCommProgressId(Integer commProgressId) {
		this.commProgressId = commProgressId;
	}
	
	public String getCommProgressName() {
		return commProgressName;
	}

	public void setCommProgressName(String commProgressName) {
		this.commProgressName = commProgressName;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
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

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BaseCommProgress)) {
			return false;
		}
		BaseCommProgress rhs = (BaseCommProgress) object;
		return new EqualsBuilder().appendSuper(super.equals(object)).append(
				this.isDel, rhs.isDel).append(this.sortNo, rhs.sortNo).append(
				this.commProgressName, rhs.commProgressName).append(
				this.createDate, rhs.createDate).append(this.createUser,
				rhs.createUser).append(this.updateDate, rhs.updateDate).append(
				this.commProgressId, rhs.commProgressId).append(
				this.updateUser, rhs.updateUser).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(420028755, 1610771541).appendSuper(
				super.hashCode()).append(this.isDel).append(this.sortNo)
				.append(this.commProgressName).append(this.createDate).append(
						this.createUser).append(this.updateDate).append(
						this.commProgressId).append(this.updateUser)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("sortNo", this.sortNo).append(
				"startRow", this.getStartRow()).append("updateDate",
				this.updateDate).append("commProgressId", this.commProgressId)
				.append("endRow", this.getEndRow()).append("updateUser",
						this.updateUser).append("createUser", this.createUser)
				.append("isDel", this.isDel).append("commProgressName",
						this.commProgressName).append("createDate",
						this.createDate).toString();
	}
}



