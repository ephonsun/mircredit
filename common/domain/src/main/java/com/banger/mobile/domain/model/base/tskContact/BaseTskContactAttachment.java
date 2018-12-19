package com.banger.mobile.domain.model.base.tskContact;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * TskContactAttachment entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BaseTskContactAttachment extends BaseObject {

	private static final long serialVersionUID = 8116600827202888186L;
    // Fields

	private Integer                        attachmentId;
	private Integer                        contactId;
	private String                         filePath;
	private String                         fileName;
	private Long                           fileSize;
	private String                         fileNameOld;
	private Date                           uploadDate;
	private Date                           createDate;
	private Date                           updateDate;
	private Integer                        createUser;
	private Integer                        updateUser;

	// Constructors

	/** default constructor */
	public BaseTskContactAttachment() {
	}

	/** minimal constructor */
	public BaseTskContactAttachment(Integer attachmentId, Integer contactId,
			String filePath, String fileName, Long fileSize,
			String fileNameOld, Date uploadDate, Date createDate,
			Integer createUser) {
		this.attachmentId = attachmentId;
		this.contactId = contactId;
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileNameOld = fileNameOld;
		this.uploadDate = uploadDate;
		this.createDate = createDate;
		this.createUser = createUser;
	}

	/** full constructor */
	public BaseTskContactAttachment(Integer attachmentId, Integer contactId,
			String filePath, String fileName, Long fileSize,
			String fileNameOld, Date uploadDate, Date createDate,
			Date updateDate, Integer createUser, Integer updateUser) {
		this.attachmentId = attachmentId;
		this.contactId = contactId;
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

	public Integer getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	public Integer getContactId() {
		return this.contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileNameOld() {
		return this.fileNameOld;
	}

	public void setFileNameOld(String fileNameOld) {
		this.fileNameOld = fileNameOld;
	}

	public Date getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
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
				+ ((attachmentId == null) ? 0 : attachmentId.hashCode());
		result = prime * result
				+ ((contactId == null) ? 0 : contactId.hashCode());
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
		final BaseTskContactAttachment other = (BaseTskContactAttachment) obj;
		if (attachmentId == null) {
			if (other.attachmentId != null)
				return false;
		} else if (!attachmentId.equals(other.attachmentId))
			return false;
		if (contactId == null) {
			if (other.contactId != null)
				return false;
		} else if (!contactId.equals(other.contactId))
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

}