/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-14
 */
package com.banger.mobile.domain.model.data;

import java.util.Date;

import com.banger.mobile.domain.model.base.data.BaseDatVideo;

/**
 * @author yuanme
 * @version $Id: Video.java,v 0.1 2012-11-14 下午4:50:59 Administrator Exp $
 */
public class Video  extends BaseDatVideo {
	
	
    private static final long serialVersionUID = -787709076160696972L;

    private Date              uploadDate;
    private Integer           uploadUserId;
    private String            filePath;
    private String            fileName;
    private String			  uploadUser;
    private String 			  recordTime;
    private String			  eventName;
    
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
	public String getUploadUser() {
		return uploadUser;
	}
	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
}
