package com.banger.mobile.domain.model.base.system;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * 有效通话量设置
 * 
 * @author huyb
 */

public class BaseSysTalklimitConfig extends BaseObject implements
		java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6340957163340579293L;
	private Integer talklimitConifId;
	private Integer userid;
	private Integer toplimit;
	private Integer lowerlimit;
	private Date createDate;
	private Date updateDate;

	// Constructors

	/** default constructor */
	public BaseSysTalklimitConfig() {
	}

	/** minimal constructor */
	public BaseSysTalklimitConfig(Integer talklimitConifId) {
		this.talklimitConifId = talklimitConifId;
	}

	/** full constructor */
	public BaseSysTalklimitConfig(Integer talklimitConifId, Integer userid,
			Integer toplimit, Integer lowerlimit, Date createDate,
			Date updateDate) {
		this.talklimitConifId = talklimitConifId;
		this.userid = userid;
		this.toplimit = toplimit;
		this.lowerlimit = lowerlimit;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	// Property accessors
	public Integer getTalklimitConifId() {
		return this.talklimitConifId;
	}

	public void setTalklimitConifId(Integer talklimitConifId) {
		this.talklimitConifId = talklimitConifId;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getToplimit() {
		return this.toplimit;
	}

	public void setToplimit(Integer toplimit) {
		this.toplimit = toplimit;
	}

	public Integer getLowerlimit() {
		return this.lowerlimit;
	}

	public void setLowerlimit(Integer lowerlimit) {
		this.lowerlimit = lowerlimit;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((lowerlimit == null) ? 0 : lowerlimit.hashCode());
		result = prime
				* result
				+ ((talklimitConifId == null) ? 0 : talklimitConifId.hashCode());
		result = prime * result
				+ ((toplimit == null) ? 0 : toplimit.hashCode());
		result = prime * result
				+ ((updateDate == null) ? 0 : updateDate.hashCode());

		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
		BaseSysTalklimitConfig other = (BaseSysTalklimitConfig) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;

		if (lowerlimit == null) {
			if (other.lowerlimit != null)
				return false;
		} else if (!lowerlimit.equals(other.lowerlimit))
			return false;
		if (talklimitConifId == null) {
			if (other.talklimitConifId != null)
				return false;
		} else if (!talklimitConifId.equals(other.talklimitConifId))
			return false;
		if (toplimit == null) {
			if (other.toplimit != null)
				return false;
		} else if (!toplimit.equals(other.toplimit))
			return false;
		if (updateDate == null) {
			if (other.updateDate != null)
				return false;
		} else if (!updateDate.equals(other.updateDate))
			return false;

		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}

}