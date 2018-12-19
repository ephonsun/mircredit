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
 * @version $Id: BaseDatCustomerData.java,v 0.1 2012-11-12 下午3:25:42 Administrator Exp $
 */
public class BaseDatAudio extends BaseObject {
    private static final long serialVersionUID = 4868892979010034254L;
    
    private Integer           audioId;
    private Integer           customerDataId;
    private String            audioName;
    private Date              recordDate;
    private Integer           recordLength;
    private String            remark;
    private Integer			  fileId;
    private String            recordNo;
    private String            datUuid;
    private Date              createDate;
    private Date              updateDate;
    private Integer           createUser;
    private Integer           updateUser;
    
	public Integer getAudioId() {
		return audioId;
	}
	public void setAudioId(Integer audioId) {
		this.audioId = audioId;
	}
	public Integer getCustomerDataId() {
		return customerDataId;
	}
	public void setCustomerDataId(Integer customerDataId) {
		this.customerDataId = customerDataId;
	}
	public String getAudioName() {
		return audioName;
	}
	public void setAudioName(String audioName) {
		this.audioName = audioName;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public Integer getRecordLength() {
		return recordLength;
	}
	public void setRecordLength(Integer recordLength) {
		this.recordLength = recordLength;
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
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
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
