package com.banger.mobile.domain.model.base.finance;

import java.util.Date;
import com.banger.mobile.domain.model.base.BaseObject;

/**
 * FeArticleReply entity. @author MyEclipse Persistence Tools
 */
public class BaseFeArticleReply extends BaseObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8492659556616922011L;
	
	// Fields
	private Integer replyId;
	private Integer discessId;
	private String replyContent;
	private Date createDate;
	private Date updateDate;
	private Integer createUser;
	private Integer updateUser;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result
				+ ((discessId == null) ? 0 : discessId.hashCode());
		result = prime * result
				+ ((replyContent == null) ? 0 : replyContent.hashCode());
		result = prime * result + ((replyId == null) ? 0 : replyId.hashCode());
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
		BaseFeArticleReply other = (BaseFeArticleReply) obj;
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
		if (discessId == null) {
			if (other.discessId != null)
				return false;
		} else if (!discessId.equals(other.discessId))
			return false;
		if (replyContent == null) {
			if (other.replyContent != null)
				return false;
		} else if (!replyContent.equals(other.replyContent))
			return false;
		if (replyId == null) {
			if (other.replyId != null)
				return false;
		} else if (!replyId.equals(other.replyId))
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
	public BaseFeArticleReply() {
		super();
	}

	/** minimal constructor */
	public BaseFeArticleReply(Integer replyId, Integer discessId,
			String replyContent, Date createDate, Integer createUser) {
		this.replyId = replyId;
		this.discessId = discessId;
		this.replyContent = replyContent;
		this.createDate = createDate;
		this.createUser = createUser;
	}

	/** full constructor */
	public BaseFeArticleReply(Integer replyId, Integer discessId,
			String replyContent, Date createDate, Date updateDate,
			Integer createUser, Integer updateUser) {
		this.replyId = replyId;
		this.discessId = discessId;
		this.replyContent = replyContent;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	// Property accessors
	public Integer getReplyId() {
		return this.replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	
	public Integer getDiscessId() {
		return this.discessId;
	}

	public void setDiscessId(Integer discessId) {
		this.discessId = discessId;
	}

	public String getReplyContent() {
		return this.replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
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