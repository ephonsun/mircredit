/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-23
 */
package com.banger.mobile.domain.model.pad;

import java.util.List;

/**
 * @author yuanme
 * @version $Id: PadPhotoSet.java,v 0.1 2012-11-23 上午9:31:48 Administrator Exp $
 */
public class PadPhotoSubType {
    private Integer      typeId;
    private Integer      photoCount;
    private String       typeName;
    private List<PadPic> pics;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    public List<PadPic> getPics() {
        return pics;
    }

    public void setPics(List<PadPic> pics) {
        this.pics = pics;
    }
}
