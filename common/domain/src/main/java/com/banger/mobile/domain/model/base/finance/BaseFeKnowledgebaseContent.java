package com.banger.mobile.domain.model.base.finance;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * FeKnowledgebaseContent entity.
 * 
 * @author wumh 
 */

public class BaseFeKnowledgebaseContent extends BaseObject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 186995274838936190L;
	private Integer                       knowledgebaseContentId;
	private Integer                       knowledgebaseTypeId;
	private Integer                       articleId;
	private String                        knowledgebaseTitle;
	private String                        knowledgebaseNote;
	private Integer                       userId;
	private Integer                       isDel;
	private Date                          createDate;
	private Date                          updateDate;
	private Integer                       createUser;
	private Integer                       updateUser;

	// Property accessors

	public BaseFeKnowledgebaseContent(Integer knowledgebaseContentId,
			Integer knowledgebaseTypeId, Integer articleId,
			String knowledgebaseTitle, String knowledgebaseNote,
			Integer userId, Integer isDel, Date createDate, Date updateDate,
			Integer createUser, Integer updateUser) {
		super();
		this.knowledgebaseContentId = knowledgebaseContentId;
		this.knowledgebaseTypeId = knowledgebaseTypeId;
		this.articleId = articleId;
		this.knowledgebaseTitle = knowledgebaseTitle;
		this.knowledgebaseNote = knowledgebaseNote;
		this.userId = userId;
		this.isDel = isDel;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	public BaseFeKnowledgebaseContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getKnowledgebaseContentId() {
		return this.knowledgebaseContentId;
	}

	public void setKnowledgebaseContentId(Integer knowledgebaseContentId) {
		this.knowledgebaseContentId = knowledgebaseContentId;
	}

	public Integer getKnowledgebaseTypeId() {
		return this.knowledgebaseTypeId;
	}

	public void setKnowledgebaseTypeId(Integer knowledgebaseTypeId) {
		this.knowledgebaseTypeId = knowledgebaseTypeId;
	}

	public Integer getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getKnowledgebaseTitle() {
		return this.knowledgebaseTitle;
	}

	public void setKnowledgebaseTitle(String knowledgebaseTitle) {
		this.knowledgebaseTitle = knowledgebaseTitle;
	}

	public String getKnowledgebaseNote() {
		return this.knowledgebaseNote;
	}

	public void setKnowledgebaseNote(String knowledgebaseNote) {
		this.knowledgebaseNote = knowledgebaseNote;
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
				+ ((articleId == null) ? 0 : articleId.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result + ((isDel == null) ? 0 : isDel.hashCode());
		result = prime
				* result
				+ ((knowledgebaseContentId == null) ? 0
						: knowledgebaseContentId.hashCode());
		result = prime
				* result
				+ ((knowledgebaseNote == null) ? 0 : knowledgebaseNote
						.hashCode());
		result = prime
				* result
				+ ((knowledgebaseTitle == null) ? 0 : knowledgebaseTitle
						.hashCode());
		result = prime
				* result
				+ ((knowledgebaseTypeId == null) ? 0 : knowledgebaseTypeId
						.hashCode());
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
		final BaseFeKnowledgebaseContent other = (BaseFeKnowledgebaseContent) obj;
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
		if (isDel == null) {
			if (other.isDel != null)
				return false;
		} else if (!isDel.equals(other.isDel))
			return false;
		if (knowledgebaseContentId == null) {
			if (other.knowledgebaseContentId != null)
				return false;
		} else if (!knowledgebaseContentId.equals(other.knowledgebaseContentId))
			return false;
		if (knowledgebaseNote == null) {
			if (other.knowledgebaseNote != null)
				return false;
		} else if (!knowledgebaseNote.equals(other.knowledgebaseNote))
			return false;
		if (knowledgebaseTitle == null) {
			if (other.knowledgebaseTitle != null)
				return false;
		} else if (!knowledgebaseTitle.equals(other.knowledgebaseTitle))
			return false;
		if (knowledgebaseTypeId == null) {
			if (other.knowledgebaseTypeId != null)
				return false;
		} else if (!knowledgebaseTypeId.equals(other.knowledgebaseTypeId))
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

}