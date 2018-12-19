/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2013-1-24
 */
package com.banger.mobile.webservice.domain;

import java.io.Serializable;

/**
 * @author yuanme
 * @version $Id: TaskTarget.java,v 0.1 2013-1-24 下午3:28:48 Administrator Exp $
 */
public class TaskTarget implements Serializable {
    private static final long serialVersionUID = 770223466642920675L;

    private Integer           taskId;
    private Integer           customerId;
    private String            customerName;
    private String            customerTitle;
    private String            idCard;
    private String            address;
    private String            callTime;
    private Integer           contactStatus;
    private String            phone;
    private String            recordTime;
    private String            recordAddress;
    private String            fileUrl;
    private Integer           taskTargetId;
    private String            remark;
    private Integer           isNoGood;                                // 不良客户

    public Integer getIsNoGood() {
        return isNoGood;
    }

    public void setIsNoGood(Integer isNoGood) {
        this.isNoGood = isNoGood;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public Integer getContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(Integer contactStatus) {
        this.contactStatus = contactStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public String getRecordAddress() {
        return recordAddress;
    }

    public void setRecordAddress(String recordAddress) {
        this.recordAddress = recordAddress;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getTaskTargetId() {
        return taskTargetId;
    }

    public void setTaskTargetId(Integer taskTargetId) {
        this.taskTargetId = taskTargetId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
