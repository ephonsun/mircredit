package com.banger.mobile.domain.model.base.product;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * 产品附件
 */

public class BasePdtProductAttachment extends BaseObject implements java.io.Serializable {

    // Fields    

    private static final long serialVersionUID = -8678370582686609284L;
    private Integer   attachmentId;     //主键ID
    private Integer   productId;        //产品ID
    private String    filePath;         //文件路径
    private String    fileName;         //文件名称
    private Long      fileSize;         //文件大小
    private String    fileNameOld;      //源文件名
    private Date      uploadDate;
    private Date      createDate;
    private Date      updateDate;
    private Integer   createUser;
    private Integer   updateUser;

    // Constructors

    /** default constructor */
    public BasePdtProductAttachment() {
    }

    /** minimal constructor */
    public BasePdtProductAttachment(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    /** full constructor */
    public BasePdtProductAttachment(Integer attachmentId, Integer productId, String filePath,
                                  String fileName, Long fileSize, String fileNameOld,
                                  Date uploadDate, Date createDate, Date updateDate,
                                  Integer createUser, Integer updateUser) {
        this.attachmentId = attachmentId;
        this.productId = productId;
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

    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof BasePdtProductAttachment))
            return false;
        BasePdtProductAttachment castOther = (BasePdtProductAttachment) other;

        return ((this.getAttachmentId() == castOther.getAttachmentId()) || (this.getAttachmentId() != null
                                                                            && castOther
                                                                                .getAttachmentId() != null && this
            .getAttachmentId().equals(castOther.getAttachmentId())))
               && ((this.getProductId() == castOther.getProductId()) || (this.getProductId() != null
                                                                         && castOther
                                                                             .getProductId() != null && this
                   .getProductId().equals(castOther.getProductId())))
               && ((this.getFilePath() == castOther.getFilePath()) || (this.getFilePath() != null
                                                                       && castOther.getFilePath() != null && this
                   .getFilePath().equals(castOther.getFilePath())))
               && ((this.getFileName() == castOther.getFileName()) || (this.getFileName() != null
                                                                       && castOther.getFileName() != null && this
                   .getFileName().equals(castOther.getFileName())))
               && ((this.getFileSize() == castOther.getFileSize()) || (this.getFileSize() != null
                                                                       && castOther.getFileSize() != null && this
                   .getFileSize().equals(castOther.getFileSize())))
               && ((this.getFileNameOld() == castOther.getFileNameOld()) || (this.getFileNameOld() != null
                                                                             && castOther
                                                                                 .getFileNameOld() != null && this
                   .getFileNameOld().equals(castOther.getFileNameOld())))
               && ((this.getUploadDate() == castOther.getUploadDate()) || (this.getUploadDate() != null
                                                                           && castOther
                                                                               .getUploadDate() != null && this
                   .getUploadDate().equals(castOther.getUploadDate())))
               && ((this.getCreateDate() == castOther.getCreateDate()) || (this.getCreateDate() != null
                                                                           && castOther
                                                                               .getCreateDate() != null && this
                   .getCreateDate().equals(castOther.getCreateDate())))
               && ((this.getUpdateDate() == castOther.getUpdateDate()) || (this.getUpdateDate() != null
                                                                           && castOther
                                                                               .getUpdateDate() != null && this
                   .getUpdateDate().equals(castOther.getUpdateDate())))
               && ((this.getCreateUser() == castOther.getCreateUser()) || (this.getCreateUser() != null
                                                                           && castOther
                                                                               .getCreateUser() != null && this
                   .getCreateUser().equals(castOther.getCreateUser())))
               && ((this.getUpdateUser() == castOther.getUpdateUser()) || (this.getUpdateUser() != null
                                                                           && castOther
                                                                               .getUpdateUser() != null && this
                   .getUpdateUser().equals(castOther.getUpdateUser())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getAttachmentId() == null ? 0 : this.getAttachmentId().hashCode());
        result = 37 * result + (getProductId() == null ? 0 : this.getProductId().hashCode());
        result = 37 * result + (getFilePath() == null ? 0 : this.getFilePath().hashCode());
        result = 37 * result + (getFileName() == null ? 0 : this.getFileName().hashCode());
        result = 37 * result + (getFileSize() == null ? 0 : this.getFileSize().hashCode());
        result = 37 * result + (getFileNameOld() == null ? 0 : this.getFileNameOld().hashCode());
        result = 37 * result + (getUploadDate() == null ? 0 : this.getUploadDate().hashCode());
        result = 37 * result + (getCreateDate() == null ? 0 : this.getCreateDate().hashCode());
        result = 37 * result + (getUpdateDate() == null ? 0 : this.getUpdateDate().hashCode());
        result = 37 * result + (getCreateUser() == null ? 0 : this.getCreateUser().hashCode());
        result = 37 * result + (getUpdateUser() == null ? 0 : this.getUpdateUser().hashCode());
        return result;
    }

}