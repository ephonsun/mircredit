/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :Domain-任务附件
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.domain.model.base.microTask;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author QianJie
 * @version $Id: BaseTskMicroTaskAttachment.java,v 0.1 Mar 2, 2013 10:49:02 AM QianJie Exp $
 */
public class BaseTskMicroTaskAttachment extends BaseObject implements Serializable {

    private static final long serialVersionUID = -8897459906014524279L;

    private Integer           attachmentId;                            //主键ID
    private Integer           taskId;                                  //任务ID
    private String            filePath;                                //文件路径
    private String            fileName;                                //文件名称
    private Long              fileSize;                                //文件大小
    private String            fileNameOld;                             //源文件名
    private Date              uploadDate;                              //上传时间
    private Date              createDate;                              //创建时间
    private Date              updateDate;                              //更新时间
    private Integer           createUser;                              //创建用户
    private Integer           updateUser;                              //更新用户
    
    public BaseTskMicroTaskAttachment() {
        super();
    }
    
    public BaseTskMicroTaskAttachment(Integer attachmentId, Integer taskId, String filePath,
                                      String fileName, Long fileSize, String fileNameOld,
                                      Date uploadDate, Date createDate, Date updateDate,
                                      Integer createUser, Integer updateUser) {
        super();
        this.attachmentId = attachmentId;
        this.taskId = taskId;
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

    public String getFileNameOld() {
        return fileNameOld;
    }

    public void setFileNameOld(String fileNameOld) {
        this.fileNameOld = fileNameOld;
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

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseTskMicroTaskAttachment)) {
            return false;
        }
        BaseTskMicroTaskAttachment rhs = (BaseTskMicroTaskAttachment) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.attachmentId,
            rhs.attachmentId).append(this.fileNameOld, rhs.fileNameOld).append(this.taskId,
            rhs.taskId).append(this.fileName, rhs.fileName).append(this.filePath, rhs.filePath)
            .append(this.createDate, rhs.createDate).append(this.fileSize, rhs.fileSize).append(
                this.createUser, rhs.createUser).append(this.updateDate, rhs.updateDate).append(
                this.uploadDate, rhs.uploadDate).append(this.updateUser, rhs.updateUser).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1000559023, 1349553793).appendSuper(super.hashCode()).append(
            this.attachmentId).append(this.fileNameOld).append(this.taskId).append(this.fileName)
            .append(this.filePath).append(this.createDate).append(this.fileSize).append(
                this.createUser).append(this.updateDate).append(this.uploadDate).append(
                this.updateUser).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("startRow", this.getStartRow()).append(
            "uploadDate", this.uploadDate).append("fileName", this.fileName).append("fileSize",
            this.fileSize).append("updateDate", this.updateDate).append("endRow", this.getEndRow())
            .append("updateUser", this.updateUser).append("createUser", this.createUser).append(
                "filePath", this.filePath).append("attachmentId", this.attachmentId).append(
                "fileNameOld", this.fileNameOld).append("taskId", this.taskId).append("createDate",
                this.createDate).toString();
    }
    
    
}
