/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :Domain-任务目标客户
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.domain.model.base.microTask;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author QianJie
 * @version $Id: BaseTskMicroTaskTarget.java,v 0.1 Mar 2, 2013 11:01:12 AM
 *          QianJie Exp $
 */
public class BaseTskMicroTaskTarget extends BaseObject implements Serializable {

    private static final long serialVersionUID = -4207083879541048747L;

    private Integer           taskTargetId;                            // 主键
    private Integer           taskId;                                  // 任务ID
    private Integer           customerId;                              // 客户ID
    private Integer           isFinish;                                // 如果是联系任务，如果勾选联系情况，则表示已完成。
                                                                        // 1:已联系
                                                                        // 0:未联系
    private Integer           deptId;                                  // 机构ID
    private String            customerName;                            // 客户名称
    private String            phoneNumber;                             // 电话号码
    private Date              callDate;                                // 通话时间
    private Integer           callTime;                                // 通话时长
                                                                        // 单位秒
    private Integer           userId;                                  // 用户ID
    private String            remark;                                  // 备注
    private Date              recordDate;                              // 录音时间
    private String            recordAddress;                           // 录音地址
    private Integer           recordInfoId;
    private Date              createDate;                              // 创建时间
    private Date              updateDate;                              // 更新时间
    private Integer           createUser;                              // 创建用户
    private Integer           updateUser;                              // 更新用户
    private Integer           isOldCustomer;                           // 是否是老客户
    private String            gpsLng;                                  // 实地营销录音上传经纬度
    private String            gpsLat;                                  // 实地营销录音上传经纬度

    public BaseTskMicroTaskTarget() {
        super();
    }

    public Integer getTaskTargetId() {
        return taskTargetId;
    }

    public void setTaskTargetId(Integer taskTargetId) {
        this.taskTargetId = taskTargetId;
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

    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    public Integer getCallTime() {
        return callTime;
    }

    public void setCallTime(Integer callTime) {
        this.callTime = callTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordAddress() {
        return recordAddress;
    }

    public void setRecordAddress(String recordAddress) {
        this.recordAddress = recordAddress;
    }

    public Integer getRecordInfoId() {
        return recordInfoId;
    }

    public void setRecordInfoId(Integer recordInfoId) {
        this.recordInfoId = recordInfoId;
    }

    public Integer getIsOldCustomer() {
        return isOldCustomer;
    }

    public void setIsOldCustomer(Integer isOldCustomer) {
        this.isOldCustomer = isOldCustomer;
    }

    public String getGpsLng() {
        return gpsLng;
    }

    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }
}
