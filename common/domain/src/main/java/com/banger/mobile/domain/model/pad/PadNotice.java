/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-12-12
 */
package com.banger.mobile.domain.model.pad;

/**
 * @author yuanme
 * @version $Id: PadNotice.java,v 0.1 2012-12-12 上午10:26:15 Administrator Exp $
 */
public class PadNotice {
    private Integer informId;
    private String  informName;
    private String  updateTime;
    private Integer isRead;
    private String  fileIcon;

    public Integer getInformId() {
        return informId;
    }

    public void setInformId(Integer informId) {
        this.informId = informId;
    }

    public String getInformName() {
        return informName;
    }

    public void setInformName(String informName) {
        this.informName = informName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getFileIcon() {
        return fileIcon;
    }

    public void setFileIcon(String fileIcon) {
        this.fileIcon = fileIcon;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    private String fileUrl;
}
