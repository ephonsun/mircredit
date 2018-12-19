/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       铃声设置
 * Author     :yujh
 * Create Date:Aug 29, 2012
 */
package com.banger.mobile.domain.model.base.crmRingSetting;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: BaseCrmRingSetting.java,v 0.1 Aug 29, 2012 5:40:07 PM Administrator Exp $
 */
public class BaseCrmRingSetting extends BaseObject{

    private static final long serialVersionUID = -4633908462002914200L;
    private Integer     ringSettingId;
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
	
    public Integer getRingSettingId() {
        return ringSettingId;
    }
    public void setRingSettingId(Integer ringSettingId) {
        this.ringSettingId = ringSettingId;
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
        if (!(object instanceof BaseCrmRingSetting)) {
            return false;
        }
        BaseCrmRingSetting rhs = (BaseCrmRingSetting) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.ringSettingId,
            rhs.ringSettingId).append(this.fileMd5, rhs.fileMd5).append(this.userId, rhs.userId)
            .append(this.filePath, rhs.filePath).append(this.createDate, rhs.createDate).append(
                this.updateDate, rhs.updateDate).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1449962755, -1589641473).appendSuper(super.hashCode()).append(
            this.ringSettingId).append(this.fileMd5).append(this.userId).append(this.filePath)
            .append(this.createDate).append(this.updateDate).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("filePath", this.filePath).append("ringSettingId",
            this.ringSettingId).append("startRow", this.getStartRow()).append("fileMd5",
            this.fileMd5).append("updateDate", this.updateDate).append("endRow", this.getEndRow())
            .append("createDate", this.createDate).append("userId", this.userId).toString();
    }
    
}
