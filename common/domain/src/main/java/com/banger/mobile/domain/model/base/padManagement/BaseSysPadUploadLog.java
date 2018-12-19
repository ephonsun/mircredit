/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD上传记录表
 * Author     :liyb
 * Create Date:2013-6-19
 */
package com.banger.mobile.domain.model.base.padManagement;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author liyb
 * @version $Id: BaseSysPadUploadLog.java,v 0.1 2013-6-19 上午10:25:59 liyb Exp $
 */
public class BaseSysPadUploadLog extends BaseObject implements Serializable {

    private static final long serialVersionUID = 6844866587826705122L;

    private Integer           uploadLogId;                             //主键ID
    private Integer           padInfoId;                               //PAD设备ID
    private Date              countDate;                               //本次统计时间
    private BigDecimal        countUpload;                             //本次上传总量
    private BigDecimal        countDownLoad;                           //本次下载总量
    private Date              createDate;                             //创建时间
    private Date              updateDate;                             //更新时间
    private Integer           createUser;                             //创建用户
    private Integer           updateUser;                             //更新用户
    public Integer getUploadLogId() {
        return uploadLogId;
    }
    public void setUploadLogId(Integer uploadLogId) {
        this.uploadLogId = uploadLogId;
    }
    public Integer getPadInfoId() {
        return padInfoId;
    }
    public void setPadInfoId(Integer padInfoId) {
        this.padInfoId = padInfoId;
    }
    public Date getCountDate() {
        return countDate;
    }
    public void setCountDate(Date countDate) {
        this.countDate = countDate;
    }
    public BigDecimal getCountUpload() {
        return countUpload;
    }
    public void setCountUpload(BigDecimal countUpload) {
        this.countUpload = countUpload;
    }
    public BigDecimal getCountDownLoad() {
        return countDownLoad;
    }
    public void setCountDownLoad(BigDecimal countDownLoad) {
        this.countDownLoad = countDownLoad;
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
        if (!(object instanceof BaseSysPadUploadLog)) {
            return false;
        }
        BaseSysPadUploadLog rhs = (BaseSysPadUploadLog) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.createUser,
            rhs.createUser).append(this.countDate, rhs.countDate).append(this.countDownLoad,
            rhs.countDownLoad).append(this.padInfoId, rhs.padInfoId).append(this.createDate,
            rhs.createDate).append(this.countUpload, rhs.countUpload).append(this.uploadLogId,
            rhs.uploadLogId).append(this.updateDate, rhs.updateDate).append(this.updateUser,
            rhs.updateUser).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(216086523, -109942283).appendSuper(super.hashCode()).append(
            this.createUser).append(this.countDate).append(this.countDownLoad).append(
            this.padInfoId).append(this.createDate).append(this.countUpload).append(
            this.uploadLogId).append(this.updateDate).append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("uploadLogId", this.uploadLogId).append(
            "createDate", this.createDate).append("countDownLoad", this.countDownLoad).append(
            "padInfoId", this.padInfoId).append("countUpload", this.countUpload).append(
            "updateDate", this.updateDate).append("endRow", this.getEndRow()).append("createUser",
            this.createUser).append("updateUser", this.updateUser).append("countDate",
            this.countDate).append("startRow", this.getStartRow()).toString();
    }
    
}
