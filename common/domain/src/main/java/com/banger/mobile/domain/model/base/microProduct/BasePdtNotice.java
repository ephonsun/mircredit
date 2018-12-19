/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通知-Domain
 * Author     :QianJie
 * Create Date:Dec 11, 2012
 */
package com.banger.mobile.domain.model.base.microProduct;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author QianJie
 * @version $Id: BasePdtNotice.java,v 0.1 Dec 11, 2012 1:41:28 PM QianJie Exp $
 */
public class BasePdtNotice extends BaseObject implements Serializable {

    private static final long serialVersionUID = -9152408697697996556L;

    private Integer           noticeId;                                //主键
    private String            noticeNo;                                //通知编号
    private String            noticeName;                              //通知名称
    private Date              noticeUpdateDate;                        //通知更新时间
    private String            fileName;                                //通知附件，文件名
    private String            filePath;                                //通知附件，文件存储路径
    private String            fileType;                                //通知附件，文件类型
    private Date              createDate;                              //创建时间
    private Date              updateDate;                              //更新时间
    private Integer           createUser;                              //创建用户
    private Integer           updateUser;                              //更新用户
    
    public BasePdtNotice() {
        super();
    }
    
    public BasePdtNotice(Integer noticeId, String noticeNo, String noticeName,
                         Date noticeUpdateDate, String fileName, String filePath, String fileType,
                         Date createDate, Date updateDate, Integer createUser, Integer updateUser) {
        super();
        this.noticeId = noticeId;
        this.noticeNo = noticeNo;
        this.noticeName = noticeName;
        this.noticeUpdateDate = noticeUpdateDate;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }
    

    public Integer getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getNoticeName() {
        return noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }

    public Date getNoticeUpdateDate() {
        return noticeUpdateDate;
    }

    public void setNoticeUpdateDate(Date noticeUpdateDate) {
        this.noticeUpdateDate = noticeUpdateDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
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
        if (!(object instanceof BasePdtNotice)) {
            return false;
        }
        BasePdtNotice rhs = (BasePdtNotice) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.fileType,
            rhs.fileType).append(this.noticeId, rhs.noticeId).append(this.fileName, rhs.fileName)
            .append(this.noticeName, rhs.noticeName).append(this.filePath, rhs.filePath).append(
                this.noticeNo, rhs.noticeNo).append(this.createDate, rhs.createDate).append(
                this.createUser, rhs.createUser).append(this.updateDate, rhs.updateDate).append(
                this.updateUser, rhs.updateUser)
            .append(this.noticeUpdateDate, rhs.noticeUpdateDate).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(758732007, -2047434133).appendSuper(super.hashCode()).append(
            this.fileType).append(this.noticeId).append(this.fileName).append(this.noticeName)
            .append(this.filePath).append(this.noticeNo).append(this.createDate).append(
                this.createUser).append(this.updateDate).append(this.updateUser).append(
                this.noticeUpdateDate).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("startRow", this.getStartRow()).append(
            "noticeName", this.noticeName).append("fileName", this.fileName).append("updateDate",
            this.updateDate).append("endRow", this.getEndRow()).append("fileType", this.fileType)
            .append("updateUser", this.updateUser).append("createUser", this.createUser).append(
                "filePath", this.filePath).append("noticeNo", this.noticeNo).append(
                "noticeUpdateDate", this.noticeUpdateDate).append("noticeId", this.noticeId)
            .append("createDate", this.createDate).toString();
    }
    
}
