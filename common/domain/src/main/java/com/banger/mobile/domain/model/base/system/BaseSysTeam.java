package com.banger.mobile.domain.model.base.system;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.banger.mobile.domain.model.base.BaseObject;

public class BaseSysTeam extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -999472174667214714L;

	private Integer          teamId;    		//团队Id
	private String           teamName;  		//团队名称
	private Integer          isDel;             //是否删除
	private Date             createDate;        //创建时间  
	private Date             updateDate;        //更新时间
	private Integer          createUser;        //插入用户
	private Integer          updateUser;        //更新用户
	
	public BaseSysTeam() {
		super();
	}
	
	public BaseSysTeam(Integer teamId, String teamName,Integer isDel, 
			Date createDate, Date updateDate,Integer createUser, 
			Integer updateUser) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.isDel = isDel;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
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
	
	public boolean equals(Object object) {
		if (!(object instanceof BaseSysTeam)) {
			return false;
		}
		BaseSysTeam rhs = (BaseSysTeam) object;
		return new EqualsBuilder().appendSuper(super.equals(object))
				.append(this.teamId, rhs.teamId)
				.append(this.teamName, rhs.teamName)
				.append(this.isDel, rhs.isDel)
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
				.append(this.teamId)
				.append(this.teamName)
				.append(this.isDel)				
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
			.append("teamId", this.teamId)
			.append("TeamName",	this.teamName)			
			.append("isDel", this.isDel)
			.append("updateDate",this.updateDate)
			.append("updateUser",this.updateUser)
			.append("createUser", this.createUser)
			.append("createDate",this.createDate)
			.toString();
	}
	
}
