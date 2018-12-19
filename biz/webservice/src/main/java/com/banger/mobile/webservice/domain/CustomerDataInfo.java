/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :huangk
 * Create Date:2013-3-22
 */
package com.banger.mobile.webservice.domain;

/**
 * @author huangk
 * @version $Id: CustomerDataInfo.java,v 0.1 2013-3-22 上午11:23:10 huangk Exp $
 */
public class CustomerDataInfo {
    private String file;//文件地址及名称 
    private String name;//资料名
    private String remark;//备注
    private String submitTime;//上传时间
    private Integer duration;//时长
    private String smsContent;//短信内容
    private Integer smsType;//短信类型
    private String mmsTitle;//彩信标题
    private String mmsContent;//彩信内容
    private Integer mmsType;//彩信类型
    
    public String getFile() {
        return file;
    }
    public void setFile(String file) {
        this.file = file;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getSubmitTime() {
        return submitTime;
    }
    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public String getSmsContent() {
        return smsContent;
    }
    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }
    public Integer getSmsType() {
        return smsType;
    }
    public void setSmsType(Integer smsType) {
        this.smsType = smsType;
    }
    public String getMmsTitle() {
        return mmsTitle;
    }
    public void setMmsTitle(String mmsTitle) {
        this.mmsTitle = mmsTitle;
    }
    public String getMmsContent() {
        return mmsContent;
    }
    public void setMmsContent(String mmsContent) {
        this.mmsContent = mmsContent;
    }
    public Integer getMmsType() {
        return mmsType;
    }
    public void setMmsType(Integer mmsType) {
        this.mmsType = mmsType;
    }
    
    
}
