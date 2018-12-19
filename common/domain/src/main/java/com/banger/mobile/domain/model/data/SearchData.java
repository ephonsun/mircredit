/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :资料管理所有查询条件
 * Author     :huangk
 * Create Date:2013-3-15
 */
package com.banger.mobile.domain.model.data;

import java.util.Date;

/**
 * @author huangk
 * @version $Id: SearchData.java,v 0.1 2013-3-15 上午10:19:42 huangk Exp $
 */
public class SearchData {
    private String dataName;    //名称
    private Date createStartDate;   //创建开始时间
    private Date createEndDate;   //创建结束时间
    private Date uploadStartDate;   //提交开始时间
    private Date uploadEndDate;   //提交结束时间
    private String uploadUserId;   //提交人员
    private String smsContent;   //短信内容
    private String smsType;   //短信类型
    private Date sendStartDate;   //发送开始时间
    private Date sendEndDate;   //发送结束时间
    private String mmsContent;   //短信内容
    private String mmsType;   //短信类型
    
    public String getDataName() {
        return dataName;
    }
    public void setDataName(String dataName) {
        this.dataName = dataName;
    }
    public Date getCreateStartDate() {
        return createStartDate;
    }
    public void setCreateStartDate(Date createStartDate) {
        this.createStartDate = createStartDate;
    }
    public Date getCreateEndDate() {
        return createEndDate;
    }
    public void setCreateEndDate(Date createEndDate) {
        this.createEndDate = createEndDate;
    }
    public Date getUploadStartDate() {
        return uploadStartDate;
    }
    public void setUploadStartDate(Date uploadStartDate) {
        this.uploadStartDate = uploadStartDate;
    }
    public Date getUploadEndDate() {
        return uploadEndDate;
    }
    public void setUploadEndDate(Date uploadEndDate) {
        this.uploadEndDate = uploadEndDate;
    }
    public String getUploadUserId() {
        return uploadUserId;
    }
    public void setUploadUserId(String uploadUserId) {
        this.uploadUserId = uploadUserId;
    }
    public String getSmsContent() {
        return smsContent;
    }
    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }
    public String getSmsType() {
        return smsType;
    }
    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }
    public Date getSendStartDate() {
        return sendStartDate;
    }
    public void setSendStartDate(Date sendStartDate) {
        this.sendStartDate = sendStartDate;
    }
    public Date getSendEndDate() {
        return sendEndDate;
    }
    public void setSendEndDate(Date sendEndDate) {
        this.sendEndDate = sendEndDate;
    }
    public String getMmsContent() {
        return mmsContent;
    }
    public void setMmsContent(String mmsContent) {
        this.mmsContent = mmsContent;
    }
    public String getMmsType() {
        return mmsType;
    }
    public void setMmsType(String mmsType) {
        this.mmsType = mmsType;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createEndDate == null) ? 0 : createEndDate.hashCode());
        result = prime * result + ((createStartDate == null) ? 0 : createStartDate.hashCode());
        result = prime * result + ((dataName == null) ? 0 : dataName.hashCode());
        result = prime * result + ((mmsContent == null) ? 0 : mmsContent.hashCode());
        result = prime * result + ((mmsType == null) ? 0 : mmsType.hashCode());
        result = prime * result + ((sendEndDate == null) ? 0 : sendEndDate.hashCode());
        result = prime * result + ((sendStartDate == null) ? 0 : sendStartDate.hashCode());
        result = prime * result + ((smsContent == null) ? 0 : smsContent.hashCode());
        result = prime * result + ((smsType == null) ? 0 : smsType.hashCode());
        result = prime * result + ((uploadEndDate == null) ? 0 : uploadEndDate.hashCode());
        result = prime * result + ((uploadStartDate == null) ? 0 : uploadStartDate.hashCode());
        result = prime * result + ((uploadUserId == null) ? 0 : uploadUserId.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SearchData other = (SearchData) obj;
        if (createEndDate == null) {
            if (other.createEndDate != null)
                return false;
        } else if (!createEndDate.equals(other.createEndDate))
            return false;
        if (createStartDate == null) {
            if (other.createStartDate != null)
                return false;
        } else if (!createStartDate.equals(other.createStartDate))
            return false;
        if (dataName == null) {
            if (other.dataName != null)
                return false;
        } else if (!dataName.equals(other.dataName))
            return false;
        if (mmsContent == null) {
            if (other.mmsContent != null)
                return false;
        } else if (!mmsContent.equals(other.mmsContent))
            return false;
        if (mmsType == null) {
            if (other.mmsType != null)
                return false;
        } else if (!mmsType.equals(other.mmsType))
            return false;
        if (sendEndDate == null) {
            if (other.sendEndDate != null)
                return false;
        } else if (!sendEndDate.equals(other.sendEndDate))
            return false;
        if (sendStartDate == null) {
            if (other.sendStartDate != null)
                return false;
        } else if (!sendStartDate.equals(other.sendStartDate))
            return false;
        if (smsContent == null) {
            if (other.smsContent != null)
                return false;
        } else if (!smsContent.equals(other.smsContent))
            return false;
        if (smsType == null) {
            if (other.smsType != null)
                return false;
        } else if (!smsType.equals(other.smsType))
            return false;
        if (uploadEndDate == null) {
            if (other.uploadEndDate != null)
                return false;
        } else if (!uploadEndDate.equals(other.uploadEndDate))
            return false;
        if (uploadStartDate == null) {
            if (other.uploadStartDate != null)
                return false;
        } else if (!uploadStartDate.equals(other.uploadStartDate))
            return false;
        if (uploadUserId == null) {
            if (other.uploadUserId != null)
                return false;
        } else if (!uploadUserId.equals(other.uploadUserId))
            return false;
        return true;
    }
    public SearchData(String dataName, Date createStartDate, Date createEndDate,
                      Date uploadStartDate, Date uploadEndDate, String uploadUserId,
                      String smsContent, String smsType, Date sendStartDate, Date sendEndDate,
                      String mmsContent, String mmsType) {
        super();
        this.dataName = dataName;
        this.createStartDate = createStartDate;
        this.createEndDate = createEndDate;
        this.uploadStartDate = uploadStartDate;
        this.uploadEndDate = uploadEndDate;
        this.uploadUserId = uploadUserId;
        this.smsContent = smsContent;
        this.smsType = smsType;
        this.sendStartDate = sendStartDate;
        this.sendEndDate = sendEndDate;
        this.mmsContent = mmsContent;
        this.mmsType = mmsType;
    }
    public SearchData() {
        super();
    }
    
    
}
