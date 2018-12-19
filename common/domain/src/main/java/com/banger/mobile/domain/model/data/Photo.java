/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-14
 */
package com.banger.mobile.domain.model.data;

import java.util.Date;

import com.banger.mobile.domain.model.base.data.BaseDatPhoto;

/**
 * @author yuanme
 * @version $Id: Photo.java,v 0.1 2012-11-14 下午4:50:31 Administrator Exp $
 */
public class Photo extends BaseDatPhoto {

    private static final long serialVersionUID = 371437752052314495L;
    private Date              uploadDate;
    private Integer           uploadUserId;
    private String            filePath;
    private String            fileName;
    private String			  uploadUserName;
    private Integer 		  numRow;
    private String 			  eventName;
    
    
    private String 			  orderNo;
    private Integer 			  photoTypeId;
    private String 			  photoTypeName;
    private Integer 			  photoCount;
    private Integer 			  fileId;
    private Integer 			  customerId;
    
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Integer getPhotoTypeId() {
		return photoTypeId;
	}

	public void setPhotoTypeId(Integer photoTypeId) {
		this.photoTypeId = photoTypeId;
	}

	public String getPhotoTypeName() {
		return photoTypeName;
	}

	public void setPhotoTypeName(String photoTypeName) {
		this.photoTypeName = photoTypeName;
	}

	public Integer getPhotoCount() {
		return photoCount;
	}

	public void setPhotoCount(Integer photoCount) {
		this.photoCount = photoCount;
	}

	public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public Integer getUploadUserId() {
		return uploadUserId;
	}
	public void setUploadUserId(Integer uploadUserId) {
		this.uploadUserId = uploadUserId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUploadUserName() {
		return uploadUserName;
	}
	public void setUploadUserName(String uploadUserName) {
		this.uploadUserName = uploadUserName;
	}
	public Integer getNumRow() {
		return numRow;
	}
	public void setNumRow(Integer numRow) {
		this.numRow = numRow;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
    
}
