/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :huangk
 * Create Date:2013-3-21
 */
package com.banger.mobile.webservice.domain;

/**
 * @author huangk
 * @version $Id: CustomerRecordInfo.java,v 0.1 2013-3-21 下午3:50:29 huangk Exp $
 */
public class CustomerRecordInfo {
    private String recordInfoId;//联系记录id
    private String customerName;//客户姓名
    private String remotePhone;//电话
    private String startDate;//开始时间
    private String callType;//联系类型
    private String bizType;//业务类型
    private String remark;//备注
    private String recordUrl;//录音路径
    private Integer commProgressId;//沟通进度
    private String duration;//时长
    private String customerId;//客户id
    private String recordSource;
    private Integer isCanceled;
    private String user;
	public String getRecordInfoId() {
		return recordInfoId;
	}
	public void setRecordInfoId(String recordInfoId) {
		this.recordInfoId = recordInfoId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getRemotePhone() {
		return remotePhone;
	}
	public void setRemotePhone(String remotePhone) {
		this.remotePhone = remotePhone;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getCallType() {
		return callType;
	}
	public void setCallType(String callType) {
		this.callType = callType;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRecordUrl() {
		return recordUrl;
	}
	public void setRecordUrl(String recordUrl) {
		this.recordUrl = recordUrl;
	}
	public Integer getCommProgressId() {
		return commProgressId;
	}
	public void setCommProgressId(Integer commProgressId) {
		this.commProgressId = commProgressId;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getRecordSource() {
		return recordSource;
	}
	public void setRecordSource(String recordSource) {
		this.recordSource = recordSource;
	}
	public Integer getIsCanceled() {
		return isCanceled;
	}
	public void setIsCanceled(Integer isCanceled) {
		this.isCanceled = isCanceled;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
    
    
}
