/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :资料管理实体拓展类
 * Author     :huangk
 * Create Date:2012-11-12
 */
package com.banger.mobile.domain.model.data;

import com.banger.mobile.domain.model.base.data.BaseDatCustomerData;

/**
 * @author yuanme
 * @version $Id: CustomerData.java,v 0.1 2012-11-12 下午4:25:56 Administrator Exp
 *          $
 */
public class CustomerData extends BaseDatCustomerData {
    private static final long serialVersionUID = 5672846309564766734L;

    private String            idCard;
    private String            userName;

    private String            customerName;                           // 客户姓名
    private String            phone;                                  // 客户电话
    private String            customerTitle;                          // 客户称谓
    private String            sex;                                    // 客户性别
    private Integer           age;                                    // 客户年龄
    private String            address;                                // 客户地址
    private String            eventName;                              // 事件
    private String            belongUserName;                         // 客户归属
    private String            uploadUserName;                         // 上传者
    private Integer           audioCount;                             // 录音个数
    private Integer           photoCount;                             // 照片个数
    private Integer           videoCount;                             // 视频个数
    private Integer           smsCount;                               // 短信个数
    private Integer           mmsCount;                               // 彩信个数
    private Integer           formCount;                              // 资料个数
    private String            customerNamePinyin;                     // 客户姓名拼音
    private Integer           taskId;                                 // 任务id
    private Integer           dunLogId;                               // 催收日志ID
    private Integer exceptionDunLogId;
    private Integer			  isNoGood;								  // 不良客户
    private String			  belongDeptName;						  // 归属机构
    private String remark;
    
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getExceptionDunLogId() {
        return exceptionDunLogId;
    }

    public void setExceptionDunLogId(Integer exceptionDunLogId) {
        this.exceptionDunLogId = exceptionDunLogId;
    }
    
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getBelongUserName() {
        return belongUserName;
    }

    public void setBelongUserName(String belongUserName) {
        this.belongUserName = belongUserName;
    }

    public String getUploadUserName() {
        return uploadUserName;
    }

    public void setUploadUserName(String uploadUserName) {
        this.uploadUserName = uploadUserName;
    }

    public Integer getAudioCount() {
        return audioCount;
    }

    public void setAudioCount(Integer audioCount) {
        this.audioCount = audioCount;
    }

    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    public Integer getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
    }

    public Integer getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(Integer smsCount) {
        this.smsCount = smsCount;
    }

    public Integer getMmsCount() {
        return mmsCount;
    }

    public void setMmsCount(Integer mmsCount) {
        this.mmsCount = mmsCount;
    }

    public Integer getFormCount() {
        return formCount;
    }

    public void setFormCount(Integer formCount) {
        this.formCount = formCount;
    }

    public String getCustomerNamePinyin() {
        return customerNamePinyin;
    }

    public void setCustomerNamePinyin(String customerNamePinyin) {
        this.customerNamePinyin = customerNamePinyin;
    }

    public CustomerData() {
        super();
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getDunLogId() {
        return dunLogId;
    }

    public void setDunLogId(Integer dunLogId) {
        this.dunLogId = dunLogId;
    }

	public Integer getIsNoGood() {
		return isNoGood;
	}

	public void setIsNoGood(Integer isNoGood) {
		this.isNoGood = isNoGood;
	}

	public String getBelongDeptName() {
		return belongDeptName;
	}

	public void setBelongDeptName(String belongDeptName) {
		this.belongDeptName = belongDeptName;
	}
    
}
