/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-12
 */
package com.banger.mobile.domain.model.base.data;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author yuanme
 * @version $Id: BaseDatCustomerData.java,v 0.1 2012-11-12 下午3:25:42
 *          Administrator Exp $
 */
public class BaseDatPhoto extends BaseObject {
    private static final long serialVersionUID = 4868892979010034254L;

    private Integer photoId;
    private Integer customerDataId;
    private String photoName;
    private Integer photoTypeId;
    private Date recordDate;
    private String remark;
    private Integer fileId;
    private Integer isDel;
    private String datUuid;
    private Date createDate;
    private Date updateDate;
    private Integer createUser;
    private Integer updateUser;
    private String gpsLng;
    private String gpsLat;

    public String getGpsLng() {
        return gpsLng==null ?"":gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng==null ?"":gpsLng;
    }

    public String getGpsLat() {
        return gpsLat==null ?"":gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat==null ?"":gpsLat;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public Integer getCustomerDataId() {
        return customerDataId;
    }

    public void setCustomerDataId(Integer customerDataId) {
        this.customerDataId = customerDataId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public Integer getPhotoTypeId() {
        return photoTypeId;
    }

    public void setPhotoTypeId(Integer photoTypeId) {
        this.photoTypeId = photoTypeId;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getDatUuid() {
        return datUuid;
    }

    public void setDatUuid(String datUuid) {
        this.datUuid = datUuid;
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


}
