/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音提示音设置
 * Author     :yujh
 * Create Date:Aug 29, 2012
 */
package com.banger.mobile.domain.model.base.crmRecordRemind;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: BaseCrmRecordRemind.java,v 0.1 Aug 29, 2012 5:39:49 PM Administrator Exp $
 */
public class BaseCrmRecordRemind extends BaseObject{

    private static final long serialVersionUID = -6094290309576884288L;
    private Integer     recordRemindId;
    private Integer     userId;
    private String      filePath;
    private Date        createDate;
    private Date        updateDate;
    private String      fileMd5;
    private String		filename;			//文件名
    
    public String getFileName() {
		return filename;
	}
	public void setFileName(String filename) {
		this.filename = filename;
	}
	
	public Integer getRecordRemindId() {
        return recordRemindId;
    }
    public void setRecordRemindId(Integer recordRemindId) {
        this.recordRemindId = recordRemindId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
    public String getFileMd5() {
        return fileMd5;
    }
    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseCrmRecordRemind)) {
            return false;
        }
        BaseCrmRecordRemind rhs = (BaseCrmRecordRemind) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.fileMd5,
            rhs.fileMd5).append(this.userId, rhs.userId).append(this.filePath, rhs.filePath)
            .append(this.createDate, rhs.createDate).append(this.updateDate, rhs.updateDate)
            .append(this.recordRemindId, rhs.recordRemindId).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1918312489, 447371549).appendSuper(super.hashCode()).append(
            this.fileMd5).append(this.userId).append(this.filePath).append(this.createDate).append(
            this.updateDate).append(this.recordRemindId).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("filePath", this.filePath).append("startRow",
            this.getStartRow()).append("fileMd5", this.fileMd5).append("updateDate",
            this.updateDate).append("endRow", this.getEndRow()).append("recordRemindId",
            this.recordRemindId).append("createDate", this.createDate)
            .append("userId", this.userId).toString();
    }
    
}
