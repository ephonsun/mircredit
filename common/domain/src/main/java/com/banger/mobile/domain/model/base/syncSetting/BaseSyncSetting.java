/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :SyncSetting客户资料同步设置实体基类
 * Author     :liyb
 * Create Date:2012-6-1
 */
package com.banger.mobile.domain.model.base.syncSetting;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author liyb
 * @version $Id: BaseSyncSetting.java,v 0.1 2012-6-1 下午01:09:24 liyb Exp $
 */
public class BaseSyncSetting extends BaseObject implements Serializable {

    private static final long serialVersionUID = 25265056257782199L;
    private Integer           syncSettingId;                         //主键ID
    private Integer           isActived;                             //是否启用
    private String            syncTime;                              //同步时间
    private String            ftpHost;                               //ftp地址
    private Integer           ftpPort;                               //ftp端口
    private String            ftpAccount;                            //ftp帐号
    private String            ftpPassword;                           //ftp密码
    private String            ftpSyncDir;                            //ftp数据目录
    private String            ftpSyncFile;                           //ftp数据文件
    public Integer getSyncSettingId() {
        return syncSettingId;
    }
    public void setSyncSettingId(Integer syncSettingId) {
        this.syncSettingId = syncSettingId;
    }
    public Integer getIsActived() {
        return isActived;
    }
    public void setIsActived(Integer isActived) {
        this.isActived = isActived;
    }
    public String getSyncTime() {
        return syncTime;
    }
    public void setSyncTime(String syncTime) {
        this.syncTime = syncTime;
    }
    public String getFtpHost() {
        return ftpHost;
    }
    public void setFtpHost(String ftpHost) {
        this.ftpHost = ftpHost;
    }
    public Integer getFtpPort() {
        return ftpPort;
    }
    public void setFtpPort(Integer ftpPort) {
        this.ftpPort = ftpPort;
    }
    public String getFtpAccount() {
        return ftpAccount;
    }
    public void setFtpAccount(String ftpAccount) {
        this.ftpAccount = ftpAccount;
    }
    public String getFtpPassword() {
        return ftpPassword;
    }
    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }
    public String getFtpSyncDir() {
        return ftpSyncDir;
    }
    public void setFtpSyncDir(String ftpSyncDir) {
        this.ftpSyncDir = ftpSyncDir;
    }
    public String getFtpSyncFile() {
        return ftpSyncFile;
    }
    public void setFtpSyncFile(String ftpSyncFile) {
        this.ftpSyncFile = ftpSyncFile;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseSyncSetting)) {
            return false;
        }
        BaseSyncSetting rhs = (BaseSyncSetting) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.ftpPassword,
            rhs.ftpPassword).append(this.ftpHost, rhs.ftpHost).append(this.syncTime, rhs.syncTime)
            .append(this.isActived, rhs.isActived).append(this.ftpAccount, rhs.ftpAccount).append(
                this.ftpSyncDir, rhs.ftpSyncDir).append(this.ftpPort, rhs.ftpPort).append(
                this.syncSettingId, rhs.syncSettingId).append(this.ftpSyncFile, rhs.ftpSyncFile)
            .isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1722049855, -1743769789).appendSuper(super.hashCode()).append(
            this.ftpPassword).append(this.ftpHost).append(this.syncTime).append(this.isActived)
            .append(this.ftpAccount).append(this.ftpSyncDir).append(this.ftpPort).append(
                this.syncSettingId).append(this.ftpSyncFile).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("ftpAccount", this.ftpAccount).append("startRow",
            this.getStartRow()).append("ftpPort", this.ftpPort).append("ftpSyncDir",
            this.ftpSyncDir).append("ftpPassword", this.ftpPassword).append("ftpSyncFile",
            this.ftpSyncFile).append("syncTime", this.syncTime).append("endRow", this.getEndRow())
            .append("isActived", this.isActived).append("ftpHost", this.ftpHost).append(
                "syncSettingId", this.syncSettingId).toString();
    }
    
    
}
