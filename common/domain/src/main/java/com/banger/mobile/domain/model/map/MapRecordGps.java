/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音地理位置实体类...
 * Author     :yangy
 * Create Date:2013-3-13
 */
package com.banger.mobile.domain.model.map;

import com.banger.mobile.domain.model.base.map.BaseMapRecordGps;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Date;

/**
 * @author yangyang
 * @version $Id: MapRecordGps.java,v 0.1 2013-3-13 下午2:16:39 yangyong Exp $
 */
public class MapRecordGps extends BaseMapRecordGps {

    private static final long serialVersionUID = 3831304681796826426L;

    private String recordName;                      //录音名称
    private Date recordTime;                        //录音时间
    private String recordUploadUser;                //录音上传用户
    private String recordAddress;                   //录音地址
    private Integer fileSize;                       //文件大小

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getRecordUploadUser() {
        return recordUploadUser;
    }

    public void setRecordUploadUser(String recordUploadUser) {
        this.recordUploadUser = recordUploadUser;
    }

    public String getRecordAddress() {
        return recordAddress;
    }

    public void setRecordAddress(String recordAddress) {
        this.recordAddress = recordAddress;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1614684959, 1670419211).appendSuper(super.hashCode())
                .toHashCode();
    }

    public MapRecordGps() {
        super();
    }
}
