/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-14
 */
package com.banger.mobile.domain.model.data;

import java.util.Date;

import com.banger.mobile.domain.model.base.data.BaseDatForm;
import com.banger.mobile.domain.model.uploadFile.SysUploadFile;

/**
 * @author yuanme
 * @version $Id: Form.java,v 0.1 2012-11-14 下午4:49:04 Administrator Exp $
 */
public class Form extends BaseDatForm {

    private static final long serialVersionUID = 1643113494225465197L;

    private String			  formName;
    private Date			  recordDate;
    private Date			  uploadDate;
    private Integer			  uploadUserId;
    private String uploadUser;
    private String eventName;
    private String            filePath;
    private String            fileName;

    private SysUploadFile sysUploadFile;   //系统上传文件

    public SysUploadFile getSysUploadFile() {
        return sysUploadFile;
    }

    public void setSysUploadFile(SysUploadFile sysUploadFile) {
        this.sysUploadFile = sysUploadFile;
    }

    public String getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
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
	
}
