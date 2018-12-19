package com.banger.mobile.domain.model.base.finance;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * FeArticleAttachment entity. @author MyEclipse Persistence Tools
 */
public class BaseFeArticleAttachment implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7759267619450103707L;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((articleId == null) ? 0 : articleId.hashCode());
		result = prime * result
				+ ((attachmentId == null) ? 0 : attachmentId.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result
				+ ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result
				+ ((fileNameOld == null) ? 0 : fileNameOld.hashCode());
		result = prime * result
				+ ((filePath == null) ? 0 : filePath.hashCode());
		result = prime * result
				+ ((fileSize == null) ? 0 : fileSize.hashCode());
		result = prime * result
				+ ((updateDate == null) ? 0 : updateDate.hashCode());
		result = prime * result
				+ ((updateUser == null) ? 0 : updateUser.hashCode());
		result = prime * result
				+ ((uploadDate == null) ? 0 : uploadDate.hashCode());
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
		BaseFeArticleAttachment other = (BaseFeArticleAttachment) obj;
		if (articleId == null) {
			if (other.articleId != null)
				return false;
		} else if (!articleId.equals(other.articleId))
			return false;
		if (attachmentId == null) {
			if (other.attachmentId != null)
				return false;
		} else if (!attachmentId.equals(other.attachmentId))
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
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (fileNameOld == null) {
			if (other.fileNameOld != null)
				return false;
		} else if (!fileNameOld.equals(other.fileNameOld))
			return false;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		if (fileSize == null) {
			if (other.fileSize != null)
				return false;
		} else if (!fileSize.equals(other.fileSize))
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
		if (uploadDate == null) {
			if (other.uploadDate != null)
				return false;
		} else if (!uploadDate.equals(other.uploadDate))
			return false;
		return true;
	}

	private Integer attachmentId;
	private Integer articleId;
	private String filePath;
	private String fileName;
	private Long fileSize;
	private String fileNameOld;
	private Timestamp uploadDate;
	private Timestamp createDate;
	private Timestamp updateDate;
	private Integer createUser;
	private Integer updateUser;

	// Constructors

	/** default constructor */
	public BaseFeArticleAttachment() {
	}

	/** minimal constructor */
	public BaseFeArticleAttachment(Integer attachmentId, Integer articleId,
			String filePath, String fileName, Long fileSize,
			String fileNameOld, Timestamp uploadDate, Timestamp createDate,
			Integer createUser) {
		this.attachmentId = attachmentId;
		this.articleId = articleId;
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileNameOld = fileNameOld;
		this.uploadDate = uploadDate;
		this.createDate = createDate;
		this.createUser = createUser;
	}

	/** full constructor */
	public BaseFeArticleAttachment(Integer attachmentId, Integer articleId,
			String filePath, String fileName, Long fileSize,
			String fileNameOld, Timestamp uploadDate, Timestamp createDate,
			Timestamp updateDate, Integer createUser, Integer updateUser) {
		this.attachmentId = attachmentId;
		this.articleId = articleId;
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileNameOld = fileNameOld;
		this.uploadDate = uploadDate;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	// Property accessors
	@Id
	@Column(name = "ATTACHMENT_ID", unique = true, nullable = false)
	public Integer getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	@Column(name = "ARTICLE_ID", nullable = false)
	public Integer getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	@Column(name = "FILE_PATH", nullable = false, length = 600)
	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Column(name = "FILE_NAME", nullable = false, length = 90)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "FILE_SIZE", nullable = false)
	public Long getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	@Column(name = "FILE_NAME_OLD", nullable = false, length = 90)
	public String getFileNameOld() {
		return this.fileNameOld;
	}

	public void setFileNameOld(String fileNameOld) {
		this.fileNameOld = fileNameOld;
	}

	@Column(name = "UPLOAD_DATE", nullable = false, length = 26)
	public Timestamp getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}

	@Column(name = "CREATE_DATE", nullable = false, length = 26)
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Column(name = "UPDATE_DATE", length = 26)
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
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

}