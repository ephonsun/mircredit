package com.banger.mobile.domain.model.base.finance;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * FeUserRelation entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BaseFeUserRelation extends BaseObject implements java.io.Serializable {

	public BaseFeUserRelation(Integer relationId, Integer relationType,
			Integer userId, Integer feId, Integer isRead, Integer isDiscess,
			Integer isCollect, Integer isSupport, Integer isReply,
			Date readDate, Date collectDate) {
		super();
		this.relationId = relationId;
		this.relationType = relationType;
		this.userId = userId;
		this.feId = feId;
		this.isRead = isRead;
		this.isDiscess = isDiscess;
		this.isCollect = isCollect;
		this.isSupport = isSupport;
		this.isReply = isReply;
		this.readDate = readDate;
		this.collectDate = collectDate;
	}

	public BaseFeUserRelation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -9219157911072542694L;
	// Fields

	private Integer                             relationId;
	private Integer                             relationType;
	private Integer                             userId;
	private Integer                             feId;
	private Integer                             isRead;
	private Integer                             isDiscess;
	private Integer                             isCollect;
	private Integer                             isSupport;
	private Integer                             isReply;
	private Date                                readDate;
	private Date                                collectDate;

	// Constructors

	// Property accessors

	public Integer getRelationId() {
		return this.relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public Integer getRelationType() {
		return this.relationType;
	}

	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFeId() {
		return this.feId;
	}

	public void setFeId(Integer feId) {
		this.feId = feId;
	}

	public Integer getIsRead() {
		return this.isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Integer getIsDiscess() {
		return this.isDiscess;
	}

	public void setIsDiscess(Integer isDiscess) {
		this.isDiscess = isDiscess;
	}

	public Integer getIsCollect() {
		return this.isCollect;
	}

	public void setIsCollect(Integer isCollect) {
		this.isCollect = isCollect;
	}

	public Integer getIsSupport() {
		return this.isSupport;
	}

	public void setIsSupport(Integer isSupport) {
		this.isSupport = isSupport;
	}

	public Integer getIsReply() {
		return this.isReply;
	}

	public void setIsReply(Integer isReply) {
		this.isReply = isReply;
	}
	
	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public Date getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((collectDate == null) ? 0 : collectDate.hashCode());
		result = prime * result + ((feId == null) ? 0 : feId.hashCode());
		result = prime * result
				+ ((isCollect == null) ? 0 : isCollect.hashCode());
		result = prime * result
				+ ((isDiscess == null) ? 0 : isDiscess.hashCode());
		result = prime * result + ((isRead == null) ? 0 : isRead.hashCode());
		result = prime * result + ((isReply == null) ? 0 : isReply.hashCode());
		result = prime * result
				+ ((isSupport == null) ? 0 : isSupport.hashCode());
		result = prime * result
				+ ((readDate == null) ? 0 : readDate.hashCode());
		result = prime * result
				+ ((relationId == null) ? 0 : relationId.hashCode());
		result = prime * result
				+ ((relationType == null) ? 0 : relationType.hashCode());
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
		final BaseFeUserRelation other = (BaseFeUserRelation) obj;
		if (collectDate == null) {
			if (other.collectDate != null)
				return false;
		} else if (!collectDate.equals(other.collectDate))
			return false;
		if (feId == null) {
			if (other.feId != null)
				return false;
		} else if (!feId.equals(other.feId))
			return false;
		if (isCollect == null) {
			if (other.isCollect != null)
				return false;
		} else if (!isCollect.equals(other.isCollect))
			return false;
		if (isDiscess == null) {
			if (other.isDiscess != null)
				return false;
		} else if (!isDiscess.equals(other.isDiscess))
			return false;
		if (isRead == null) {
			if (other.isRead != null)
				return false;
		} else if (!isRead.equals(other.isRead))
			return false;
		if (isReply == null) {
			if (other.isReply != null)
				return false;
		} else if (!isReply.equals(other.isReply))
			return false;
		if (isSupport == null) {
			if (other.isSupport != null)
				return false;
		} else if (!isSupport.equals(other.isSupport))
			return false;
		if (readDate == null) {
			if (other.readDate != null)
				return false;
		} else if (!readDate.equals(other.readDate))
			return false;
		if (relationId == null) {
			if (other.relationId != null)
				return false;
		} else if (!relationId.equals(other.relationId))
			return false;
		if (relationType == null) {
			if (other.relationType != null)
				return false;
		} else if (!relationType.equals(other.relationType))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}