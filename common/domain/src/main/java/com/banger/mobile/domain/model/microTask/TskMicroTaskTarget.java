/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务目标客户-Domain-扩展1
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.domain.model.microTask;

import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.banger.mobile.domain.model.base.microTask.BaseTskMicroTaskTarget;

/**
 * @author QianJie
 * @version $Id: TskMicroTaskTarget.java,v 0.1 Mar 2, 2013 11:08:36 AM QianJie
 *          Exp $
 */
public class TskMicroTaskTarget extends BaseTskMicroTaskTarget {

    private static final long serialVersionUID = 4603406841436218240L;

    private String            callUserName;
    private String            sex;
    private String            customerTitle;
    private Integer           age;
    private Date              birthday;
    private String            callTimeString;
    private String            phoneNumberHide;
    private Integer           fileId;                               // 文件大小
    private Integer           isOldCustomer;

    private Integer           isNoGood;                                // 不良客户
    private String            address;                                 // 客户地址
    private String            idCard;                                  // 客户身份证

    // 任务信息
    private Integer           taskType;
    private String            taskTitle;
    private Date              startDate;
    private Date              endDate;
    
    //归属信息
    private Integer           belongUserId;
    
    private String            customerNamePinYin;

    public TskMicroTaskTarget() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1685113445, -912832901).appendSuper(super.hashCode())
            .toHashCode();
    }

    public String getCallUserName() {
        return callUserName;
    }

    public void setCallUserName(String callUserName) {
        this.callUserName = callUserName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCallTimeString() {
        return callTimeString;
    }

    public void setCallTimeString(String callTimeString) {
        this.callTimeString = callTimeString;
    }

    public String getPhoneNumberHide() {
        return phoneNumberHide;
    }

    public void setPhoneNumberHide(String phoneNumberHide) {
        this.phoneNumberHide = phoneNumberHide;
    }

    public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getIsOldCustomer() {
        return isOldCustomer;
    }

    public void setIsOldCustomer(Integer isOldCustomer) {
        this.isOldCustomer = isOldCustomer;
    }

    public Integer getIsNoGood() {
        return isNoGood;
    }

    public void setIsNoGood(Integer isNoGood) {
        this.isNoGood = isNoGood;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

	public Integer getBelongUserId() {
		return belongUserId;
	}

	public void setBelongUserId(Integer belongUserId) {
		this.belongUserId = belongUserId;
	}

    public String getCustomerNamePinYin() {
        return customerNamePinYin;
    }

    public void setCustomerNamePinYin(String customerNamePinYin) {
        this.customerNamePinYin = customerNamePinYin;
    }
    
}
