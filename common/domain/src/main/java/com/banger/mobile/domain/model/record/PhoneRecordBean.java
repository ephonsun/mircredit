/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :来电通话信息
 * Author     :zsw
 * Version    :1.0
 * Create Date:May 2, 2012
 */
package com.banger.mobile.domain.model.record;

import com.banger.mobile.domain.model.base.record.BaseRecordInfo;

public class PhoneRecordBean extends BaseRecordInfo {
	
	private String taskId;
	private Integer fileSize;
	
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = -3194586443673721673L;
	private String incomingNumber;			//来电话码

	public String getIncomingNumber() {
		return incomingNumber;
	}

	public void setIncomingNumber(String incomingNumber) {
		this.incomingNumber = incomingNumber;
	}
	
}
