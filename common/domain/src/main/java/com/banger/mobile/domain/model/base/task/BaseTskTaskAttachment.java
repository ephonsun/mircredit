/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :TskTaskAttachment任务附件基类
 * Author     :liyb
 * Create Date:2012-8-13
 */
package com.banger.mobile.domain.model.base.task;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author liyb
 * @version $Id: BaseTskTaskAttachment.java,v 0.1 2012-8-13 上午09:53:25 liyb Exp $
 */
public class BaseTskTaskAttachment extends BaseObject implements Serializable {

    private static final long serialVersionUID = -6489847619687961298L;
    private Integer           attachmentId;                            //主键ID
    private Integer           taskId;                                  //任务ID
    private String            filePath;                                //文件路径
    private String            fileName;                                //文件名称
    private Long              fileSize;                                //文件大小
    private Date              uploadDate;                              //上传时间
    private Date              createDate;                              //创建时间
    private Date              updateDate;                              //更新时间
    private Integer           createUser;                              //创建用户
    private Integer           updateUser;                              //更新用户
    private String            fileNameOld;                             //源文件名
    public Integer getAttachmentId() {
        return attachmentId;
    }
    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }
    public Integer getTaskId() {
        return taskId;
    }
    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public Long getFileSize() {
        return fileSize;
    }
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    public Date getUploadDate() {
        return uploadDate;
    }
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
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
    public String getFileNameOld() {
        return fileNameOld;
    }
    public void setFileNameOld(String fileNameOld) {
        this.fileNameOld = fileNameOld;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseTskTaskAttachment)) {
            return false;
        }
        BaseTskTaskAttachment rhs = (BaseTskTaskAttachment) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.fileSize,
            rhs.fileSize).append(this.taskId, rhs.taskId).append(this.createUser, rhs.createUser)
            .append(this.attachmentId, rhs.attachmentId).append(this.filePath, rhs.filePath)
            .append(this.fileName, rhs.fileName).append(this.fileNameOld, rhs.fileNameOld).append(
                this.createDate, rhs.createDate).append(this.uploadDate, rhs.uploadDate).append(
                this.updateDate, rhs.updateDate).append(this.updateUser, rhs.updateUser).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1437973645, 2130886083).appendSuper(super.hashCode()).append(
            this.fileSize).append(this.taskId).append(this.createUser).append(this.attachmentId)
            .append(this.filePath).append(this.fileName).append(this.fileNameOld).append(
                this.createDate).append(this.uploadDate).append(this.updateDate).append(
                this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("taskId", this.taskId).append("uploadDate",
            this.uploadDate).append("createDate", this.createDate).append("updateDate",
            this.updateDate).append("filePath", this.filePath).append("fileNameOld",
            this.fileNameOld).append("attachmentId", this.attachmentId).append("fileSize",
            this.fileSize).append("endRow", this.getEndRow()).append("fileName", this.fileName)
            .append("createUser", this.createUser).append("updateUser", this.updateUser).append(
                "startRow", this.getStartRow()).toString();
    }
    
}
