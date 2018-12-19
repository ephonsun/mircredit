package com.banger.mobile.domain.model.base.finance;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * FeArticleDiscess entity. @author MyEclipse Persistence Tools
 */
public class BaseFeArticleDiscess extends BaseObject implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5936156923963986289L;
	// Fields
	private Integer discessId;
	private Integer articleId;
	private String discessContent;
	private Integer discessSupportCount;
	private Integer discessReplyCount;
	private Date createDate;
	private Date updateDate;
	private Integer createUser;
	private Integer updateUser;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((articleId == null) ? 0 : articleId.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result
				+ ((discessContent == null) ? 0 : discessContent.hashCode());
		result = prime * result
				+ ((discessId == null) ? 0 : discessId.hashCode());
		result = prime
				* result
				+ ((discessReplyCount == null) ? 0 : discessReplyCount
						.hashCode());
		result = prime
				* result
				+ ((discessSupportCount == null) ? 0 : discessSupportCount
						.hashCode());
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
		BaseFeArticleDiscess other = (BaseFeArticleDiscess) obj;
		if (articleId == null) {
			if (other.articleId != null)
				return false;
		} else if (!articleId.equals(other.articleId))
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
		if (discessContent == null) {
			if (other.discessContent != null)
				return false;
		} else if (!discessContent.equals(other.discessContent))
			return false;
		if (discessId == null) {
			if (other.discessId != null)
				return false;
		} else if (!discessId.equals(other.discessId))
			return false;
		if (discessReplyCount == null) {
			if (other.discessReplyCount != null)
				return false;
		} else if (!discessReplyCount.equals(other.discessReplyCount))
			return false;
		if (discessSupportCount == null) {
			if (other.discessSupportCount != null)
				return false;
		} else if (!discessSupportCount.equals(other.discessSupportCount))
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
	// Constructors

	/** default constructor */
	public BaseFeArticleDiscess() {
		super();
	}

	/** minimal constructor */
	public BaseFeArticleDiscess(Integer discessId, Integer articleId,
			String discessContent, Integer discessSupportCount,
			Integer discessReplyCount, Date createDate, Integer createUser) {
		this.discessId = discessId;
		this.articleId = articleId;
		this.discessContent = discessContent;
		this.discessSupportCount = discessSupportCount;
		this.discessReplyCount = discessReplyCount;
		this.createDate = createDate;
		this.createUser = createUser;
	}

	/** full constructor */
	public BaseFeArticleDiscess(Integer discessId, Integer articleId,
			String discessContent, Integer discessSupportCount,
			Integer discessReplyCount, Date createDate,
			Date updateDate, Integer createUser, Integer updateUser) {
		this.discessId = discessId;
		this.articleId = articleId;
		this.discessContent = discessContent;
		this.discessSupportCount = discessSupportCount;
		this.discessReplyCount = discessReplyCount;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	// Property accessors
	public Integer getDiscessId() {
		return this.discessId;
	}

	public void setDiscessId(Integer discessId) {
		this.discessId = discessId;
	}

	public Integer getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getDiscessContent() {
		return this.discessContent;
	}

	public void setDiscessContent(String discessContent) {
		this.discessContent = discessContent;
	}

	public Integer getDiscessSupportCount() {
		return this.discessSupportCount;
	}

	public void setDiscessSupportCount(Integer discessSupportCount) {
		this.discessSupportCount = discessSupportCount;
	}
	
	public Integer getDiscessReplyCount() {
		return this.discessReplyCount;
	}

	public void setDiscessReplyCount(Integer discessReplyCount) {
		this.discessReplyCount = discessReplyCount;
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

}