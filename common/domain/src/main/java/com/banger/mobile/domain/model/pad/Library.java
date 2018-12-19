/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-19
 */
package com.banger.mobile.domain.model.pad;

import java.util.Date;
import java.util.List;

/**
 * @author yuanme
 * @version $Id: Library.java,v 0.1 2012-11-19 下午3:41:57 Administrator Exp $
 */
public class Library {
    private Integer parentId;
    private Integer isLeaf;
    private Integer libraryId;
    private String libraryCode;
    private String libraryName;
    private String libraryContent;
    private Date createDate;
    private List<LibraryAttachment> files;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<LibraryAttachment> getFiles() {
        return files;
    }

    public void setFiles(List<LibraryAttachment> files) {
        this.files = files;
    }

    public Integer getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Integer libraryId) {
        this.libraryId = libraryId;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryContent() {
        return libraryContent;
    }

    public void setLibraryContent(String libraryContent) {
        this.libraryContent = libraryContent;
    }

    public String getLibraryCode() {
        return libraryCode;
    }

    public void setLibraryCode(String libraryCode) {
        this.libraryCode = libraryCode;
    }
}
