/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :知识附件Domain
 * Author     :QianJie
 * Create Date:Dec 3, 2012
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
 * @version $Id: BasePdtLibraryAttachment.java,v 0.1 Dec 3, 2012 1:38:41 PM QianJie Exp $
 */
public class BasePdtLibraryAttachment extends BaseObject implements Serializable {

    private static final long serialVersionUID = 8950880582850923502L;

    private Integer           libraryAttachmentId;                    //主键ID
    private Integer           libraryId;                              //知识ID
    private Date              createDate;                             //创建时间
    private Date              updateDate;                             //更新时间
    private Integer           createUser;                             //创建用户
    private Integer           updateUser;                             //更新用户
    private Integer           fileId;                                 //文件ID

    public Integer getLibraryAttachmentId() {
        return libraryAttachmentId;
    }

    public void setLibraryAttachmentId(Integer libraryAttachmentId) {
        this.libraryAttachmentId = libraryAttachmentId;
    }

    public Integer getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Integer libraryId) {
        this.libraryId = libraryId;
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

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BasePdtLibraryAttachment)) {
            return false;
        }
        BasePdtLibraryAttachment rhs = (BasePdtLibraryAttachment) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.libraryAttachmentId, rhs.libraryAttachmentId)
                .append(this.createDate, rhs.createDate).append(this.createUser, rhs.createUser).append(
                        this.updateDate, rhs.updateDate).append(
                        this.updateUser, rhs.updateUser).append(this.libraryId, rhs.libraryId).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(904967681, 117591931).appendSuper(super.hashCode()).append(
                this.libraryAttachmentId).append(this.createDate).append(this.createUser).append(this.updateDate)
                .append(this.updateUser).append(this.libraryId).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("startRow", this.getStartRow()).append("updateDate", this.updateDate).append("endRow", this.getEndRow())
                .append("updateUser", this.updateUser).append(
                        "createUser", this.createUser).append("libraryId", this.libraryId).append(
                        "libraryAttachmentId", this.libraryAttachmentId).append("createDate",
                        this.createDate).toString();
    }




}
