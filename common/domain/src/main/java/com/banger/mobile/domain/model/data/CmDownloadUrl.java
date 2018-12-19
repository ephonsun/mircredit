/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :ThinkPad
 * Create Date:2013-7-1
 */
package com.banger.mobile.domain.model.data;

import java.io.Serializable;

/**
 * @author ThinkPad
 * @version $Id: CmDownloadUrl.java,v 0.1 2013-7-1 下午02:17:18 ThinkPad Exp $
 */
public class CmDownloadUrl implements Serializable {
    private static final long serialVersionUID = 3383818867568925802L;

    private String            cmFileId;
    private String            fileName;
    private String            downloadUrl;

    public String getCmFileId() {
        return cmFileId;
    }

    public void setCmFileId(String cmFileId) {
        this.cmFileId = cmFileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

}
