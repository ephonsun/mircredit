/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音设置
 * Author     :yujh
 * Create Date:Sep 3, 2012
 */
package com.banger.mobile.domain.model.base.crmRecordSetting;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: BaseCrmRecordSetting.java,v 0.1 Sep 3, 2012 4:09:20 PM Administrator Exp $
 */
public class BaseCrmRecordSetting extends BaseObject{

    private static final long serialVersionUID = 6358519443386530648L;
    private     Integer     recordSettingId;
    private     Integer     userId;
    private     Integer     isAutoRecord;       //通话过程自动录音
    private     Integer     isAutoPlay;         //来电时自动播放录音提示音
    private     Date        createDate;
    private     Date        updateDate;
    public Integer getRecordSettingId() {
        return recordSettingId;
    }
    public void setRecordSettingId(Integer recordSettingId) {
        this.recordSettingId = recordSettingId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getIsAutoRecord() {
        return isAutoRecord;
    }
    public void setIsAutoRecord(Integer isAutoRecord) {
        this.isAutoRecord = isAutoRecord;
    }
    public Integer getIsAutoPlay() {
        return isAutoPlay;
    }
    public void setIsAutoPlay(Integer isAutoPlay) {
        this.isAutoPlay = isAutoPlay;
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
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseCrmRecordSetting)) {
            return false;
        }
        BaseCrmRecordSetting rhs = (BaseCrmRecordSetting) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.isAutoPlay,
            rhs.isAutoPlay).append(this.recordSettingId, rhs.recordSettingId).append(this.userId,
            rhs.userId).append(this.isAutoRecord, rhs.isAutoRecord).append(this.createDate,
            rhs.createDate).append(this.updateDate, rhs.updateDate).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(111733105, 75355501).appendSuper(super.hashCode()).append(
            this.isAutoPlay).append(this.recordSettingId).append(this.userId).append(
            this.isAutoRecord).append(this.createDate).append(this.updateDate).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("isAutoRecord", this.isAutoRecord).append(
            "startRow", this.getStartRow()).append("updateDate", this.updateDate).append("endRow",
            this.getEndRow()).append("isAutoPlay", this.isAutoPlay).append("createDate",
            this.createDate).append("userId", this.userId).append("recordSettingId",
            this.recordSettingId).toString();
    }
    
}
