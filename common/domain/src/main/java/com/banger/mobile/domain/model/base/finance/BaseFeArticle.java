package com.banger.mobile.domain.model.base.finance;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.banger.mobile.domain.annotation.EscapeFieldFilter;

/**
 * FeArticle entity. @author MyEclipse Persistence Tools
 */
public class BaseFeArticle implements java.io.Serializable {

	// Fields

	private Integer articleId;
	private Integer articleColumnId=0;
	private String articleTitle;
	private String articleSource;
	private String articleAddress;
	@EscapeFieldFilter
	private String articleContent;
	private Date articleReadtimeBegin;
	private Date articleReadtimeEnd;
	private Integer isMastread=0;
	private Integer isPublish=0;
	private Integer isDel=0;
	private Integer articleReadCount=0;
	private Integer articleDiscessCount=0;
	private Integer articleCollectCount=0;
	private Integer articlePartakeCount=0;
	private Date createDate;
	private Date updateDate;
	private Integer createUser;
	private Integer updateUser;
	private Date publishDate;
	
	public BaseFeArticle(Integer articleId, Integer articleColumnId,
			String articleTitle, String articleSource, String articleAddress,
			String articleContent, Date articleReadtimeBegin,
			Date articleReadtimeEnd, Integer isMastread, Integer isPublish,
			Integer isDel, Integer articleReadCount,
			Integer articleDiscessCount, Integer articleCollectCount,
			Integer articlePartakeCount, Date createDate, Date updateDate,
			Integer createUser, Integer updateUser, Date publishDate) {
		super();
		this.articleId = articleId;
		this.articleColumnId = articleColumnId;
		this.articleTitle = articleTitle;
		this.articleSource = articleSource;
		this.articleAddress = articleAddress;
		this.articleContent = articleContent;
		this.articleReadtimeBegin = articleReadtimeBegin;
		this.articleReadtimeEnd = articleReadtimeEnd;
		this.isMastread = isMastread;
		this.isPublish = isPublish;
		this.isDel = isDel;
		this.articleReadCount = articleReadCount;
		this.articleDiscessCount = articleDiscessCount;
		this.articleCollectCount = articleCollectCount;
		this.articlePartakeCount = articlePartakeCount;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
		this.publishDate = publishDate;
	}

	// Constructors

	/** default constructor */
	public BaseFeArticle() {
	}

	/** minimal constructor */
	public BaseFeArticle(Integer articleId, Integer articleColumnId,
			String articleTitle, String articleSource, String articleAddress,
			String articleContent, Date articleReadtimeBegin,
			Date articleReadtimeEnd, Integer isMastread,
			Integer isPublish, Integer isDel, Integer articleReadCount,
			Integer articleDiscessCount, Integer articleCollectCount,
			Integer articlePartakeCount, Date createDate,
			Integer createUser) {
		this.articleId = articleId;
		this.articleColumnId = articleColumnId;
		this.articleTitle = articleTitle;
		this.articleSource = articleSource;
		this.articleAddress = articleAddress;
		this.articleContent = articleContent;
		this.articleReadtimeBegin = articleReadtimeBegin;
		this.articleReadtimeEnd = articleReadtimeEnd;
		this.isMastread = isMastread;
		this.isPublish = isPublish;
		this.isDel = isDel;
		this.articleReadCount = articleReadCount;
		this.articleDiscessCount = articleDiscessCount;
		this.articleCollectCount = articleCollectCount;
		this.articlePartakeCount = articlePartakeCount;
		this.createDate = createDate;
		this.createUser = createUser;
	}

	/** full constructor */
	public BaseFeArticle(Integer articleId, Integer articleColumnId,
			String articleTitle, String articleSource, String articleAddress,
			String articleContent, Date articleReadtimeBegin,
			Date articleReadtimeEnd, Integer isMastread,
			Integer isPublish, Integer isDel, Integer articleReadCount,
			Integer articleDiscessCount, Integer articleCollectCount,
			Integer articlePartakeCount, Date createDate,
			Date updateDate, Integer createUser, Integer updateUser) {
		this.articleId = articleId;
		this.articleColumnId = articleColumnId;
		this.articleTitle = articleTitle;
		this.articleSource = articleSource;
		this.articleAddress = articleAddress;
		this.articleContent = articleContent;
		this.articleReadtimeBegin = articleReadtimeBegin;
		this.articleReadtimeEnd = articleReadtimeEnd;
		this.isMastread = isMastread;
		this.isPublish = isPublish;
		this.isDel = isDel;
		this.articleReadCount = articleReadCount;
		this.articleDiscessCount = articleDiscessCount;
		this.articleCollectCount = articleCollectCount;
		this.articlePartakeCount = articlePartakeCount;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	// Property accessors
	@Id
	@Column(name = "ARTICLE_ID", unique = true, nullable = false)
	public Integer getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	@Column(name = "ARTICLE_COLUMN_ID", nullable = false)
	public Integer getArticleColumnId() {
		return this.articleColumnId;
	}

	public void setArticleColumnId(Integer articleColumnId) {
		this.articleColumnId = articleColumnId;
	}

	@Column(name = "ARTICLE_TITLE", nullable = false, length = 50)
	public String getArticleTitle() {
		return this.articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	@Column(name = "ARTICLE_SOURCE", nullable = false, length = 90)
	public String getArticleSource() {
		return this.articleSource;
	}

	public void setArticleSource(String articleSource) {
		this.articleSource = articleSource;
	}

	@Column(name = "ARTICLE_ADDRESS", nullable = false, length = 600)
	public String getArticleAddress() {
		return this.articleAddress;
	}

	public void setArticleAddress(String articleAddress) {
		this.articleAddress = articleAddress;
	}

	@Column(name = "ARTICLE_CONTENT", nullable = false)
	public String getArticleContent() {
		return this.articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	@Column(name = "IS_MASTREAD", nullable = false)
	public Integer getIsMastread() {
		return this.isMastread;
	}

	public void setIsMastread(Integer isMastread) {
		this.isMastread = isMastread;
	}

	@Column(name = "IS_PUBLISH", nullable = false)
	public Integer getIsPublish() {
		return this.isPublish;
	}

	public void setIsPublish(Integer isPublish) {
		this.isPublish = isPublish;
	}

	@Column(name = "IS_DEL", nullable = false)
	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	@Column(name = "ARTICLE_READ_COUNT", nullable = false)
	public Integer getArticleReadCount() {
		return this.articleReadCount;
	}

	public void setArticleReadCount(Integer articleReadCount) {
		this.articleReadCount = articleReadCount;
	}

	@Column(name = "ARTICLE_DISCESS_COUNT", nullable = false)
	public Integer getArticleDiscessCount() {
		return this.articleDiscessCount;
	}

	public void setArticleDiscessCount(Integer articleDiscessCount) {
		this.articleDiscessCount = articleDiscessCount;
	}

	@Column(name = "ARTICLE_COLLECT_COUNT", nullable = false)
	public Integer getArticleCollectCount() {
		return this.articleCollectCount;
	}

	public void setArticleCollectCount(Integer articleCollectCount) {
		this.articleCollectCount = articleCollectCount;
	}

	@Column(name = "ARTICLE_PARTAKE_COUNT", nullable = false)
	public Integer getArticlePartakeCount() {
		return this.articlePartakeCount;
	}

	public void setArticlePartakeCount(Integer articlePartakeCount) {
		this.articlePartakeCount = articlePartakeCount;
	}


	@Column(name = "CREATE_USER", nullable = false)
	public Integer getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	@Column(name = "UPDATE_USER")
	public Integer getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8267124902561465499L;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((articleAddress == null) ? 0 : articleAddress.hashCode());
		result = prime
				* result
				+ ((articleCollectCount == null) ? 0 : articleCollectCount
						.hashCode());
		result = prime * result
				+ ((articleColumnId == null) ? 0 : articleColumnId.hashCode());
		result = prime * result
				+ ((articleContent == null) ? 0 : articleContent.hashCode());
		result = prime
				* result
				+ ((articleDiscessCount == null) ? 0 : articleDiscessCount
						.hashCode());
		result = prime * result
				+ ((articleId == null) ? 0 : articleId.hashCode());
		result = prime
				* result
				+ ((articlePartakeCount == null) ? 0 : articlePartakeCount
						.hashCode());
		result = prime
				* result
				+ ((articleReadCount == null) ? 0 : articleReadCount.hashCode());
		result = prime
				* result
				+ ((articleReadtimeBegin == null) ? 0 : articleReadtimeBegin
						.hashCode());
		result = prime
				* result
				+ ((articleReadtimeEnd == null) ? 0 : articleReadtimeEnd
						.hashCode());
		result = prime * result
				+ ((articleSource == null) ? 0 : articleSource.hashCode());
		result = prime * result
				+ ((articleTitle == null) ? 0 : articleTitle.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result + ((isDel == null) ? 0 : isDel.hashCode());
		result = prime * result
				+ ((isMastread == null) ? 0 : isMastread.hashCode());
		result = prime * result
				+ ((isPublish == null) ? 0 : isPublish.hashCode());
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
		BaseFeArticle other = (BaseFeArticle) obj;
		if (articleAddress == null) {
			if (other.articleAddress != null)
				return false;
		} else if (!articleAddress.equals(other.articleAddress))
			return false;
		if (articleCollectCount == null) {
			if (other.articleCollectCount != null)
				return false;
		} else if (!articleCollectCount.equals(other.articleCollectCount))
			return false;
		if (articleColumnId == null) {
			if (other.articleColumnId != null)
				return false;
		} else if (!articleColumnId.equals(other.articleColumnId))
			return false;
		if (articleContent == null) {
			if (other.articleContent != null)
				return false;
		} else if (!articleContent.equals(other.articleContent))
			return false;
		if (articleDiscessCount == null) {
			if (other.articleDiscessCount != null)
				return false;
		} else if (!articleDiscessCount.equals(other.articleDiscessCount))
			return false;
		if (articleId == null) {
			if (other.articleId != null)
				return false;
		} else if (!articleId.equals(other.articleId))
			return false;
		if (articlePartakeCount == null) {
			if (other.articlePartakeCount != null)
				return false;
		} else if (!articlePartakeCount.equals(other.articlePartakeCount))
			return false;
		if (articleReadCount == null) {
			if (other.articleReadCount != null)
				return false;
		} else if (!articleReadCount.equals(other.articleReadCount))
			return false;
		if (articleReadtimeBegin == null) {
			if (other.articleReadtimeBegin != null)
				return false;
		} else if (!articleReadtimeBegin.equals(other.articleReadtimeBegin))
			return false;
		if (articleReadtimeEnd == null) {
			if (other.articleReadtimeEnd != null)
				return false;
		} else if (!articleReadtimeEnd.equals(other.articleReadtimeEnd))
			return false;
		if (articleSource == null) {
			if (other.articleSource != null)
				return false;
		} else if (!articleSource.equals(other.articleSource))
			return false;
		if (articleTitle == null) {
			if (other.articleTitle != null)
				return false;
		} else if (!articleTitle.equals(other.articleTitle))
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
		if (isMastread == null) {
			if (other.isMastread != null)
				return false;
		} else if (!isMastread.equals(other.isMastread))
			return false;
		if (isPublish == null) {
			if (other.isPublish != null)
				return false;
		} else if (!isPublish.equals(other.isPublish))
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

	public Date getArticleReadtimeBegin() {
		return articleReadtimeBegin;
	}

	public void setArticleReadtimeBegin(Date articleReadtimeBegin) {
		this.articleReadtimeBegin = articleReadtimeBegin;
	}

	public Date getArticleReadtimeEnd() {
		return articleReadtimeEnd;
	}

	public void setArticleReadtimeEnd(Date articleReadtimeEnd) {
		this.articleReadtimeEnd = articleReadtimeEnd;
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

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

}