/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-12-6
 */
package com.banger.mobile.domain.model.pad;

/**
 * @author yuanme
 * @version $Id: LibraryAttachment.java,v 0.1 2012-12-6 下午3:54:19 Administrator Exp $
 */
public class LibraryAttachment {
    private String fileIcon;
    private String fileName;
    private String fileUrl;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
}
