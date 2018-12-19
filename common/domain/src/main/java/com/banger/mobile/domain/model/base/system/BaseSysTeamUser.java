package com.banger.mobile.domain.model.base.system;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.banger.mobile.domain.model.base.BaseObject;

public class BaseSysTeamUser extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4514404728928532579L;
	
	private Integer          teamUserId;    	
	private Integer          teamId;    		//团队Id
	private Integer          userId;    		//用户Id
	private Date             createDate;        //创建时间  
	private Date             updateDate;        //更新时间
	private Integer          createUser;        //插入用户
	private Integer          updateUser;        //更新用户 

	public BaseSysTeamUser(){
		super();
	}
	
	public BaseSysTeamUser(Integer teamUserId,Integer teamId, Integer userId,
			Date createDate, Date updateDate,Integer createUser,Integer updateUser) {
		super();
		this.teamUserId=teamUserId;
		this.teamId = teamId;
		this.userId = userId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	public Integer getTeamUserId() {
		return teamUserId;
	}

	public void setTeamUserId(Integer teamUserId) {
		this.teamUserId = teamUserId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
	
	public boolean equals(Object object) {
		if (!(object instanceof BaseSysTeamUser)) {
			return false;
		}
		BaseSysTeamUser rhs = (BaseSysTeamUser) object;
		return new EqualsBuilder().appendSuper(super.equals(object))
				.append(this.teamUserId, rhs.teamUserId)
				.append(this.teamId, rhs.teamId)
				.append(this.userId, rhs.userId)
				.append(this.createDate, rhs.createDate)
				.append(this.createUser,rhs.createUser)
				.append(this.updateDate, rhs.updateDate)
				.append(this.updateUser, rhs.updateUser)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(420028755, 1610771541).appendSuper(super.hashCode())
				.append(this.teamUserId)
				.append(this.teamId)
				.append(this.userId)			
				.append(this.createDate)
				.append(this.createUser)
				.append(this.updateDate)
				.append(this.updateUser)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
			.append("teamUserId", this.teamUserId)
			.append("teamId", this.teamId)
			.append("userId",	this.userId)
			.append("updateDate",this.updateDate)
			.append("updateUser",this.updateUser)
			.append("createUser", this.createUser)
			.append("createDate",this.createDate)
			.toString();
	}
	
}
