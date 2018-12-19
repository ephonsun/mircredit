/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-3-31
 */
package com.banger.mobile.domain;

import java.util.Date;

/**
 * @author yuanme
 * @version XmlRecordInfo.java,v 0.1 2012-3-31 下午3:32:40
 */
public class XmlRecordInfo implements java.io.Serializable {
    private static final long serialVersionUID = -4282512538886304242L;
    private String account;
    private int    callType;
    private String localPhone;
    private String remotePhone;
    private Date   startDate;
    private Date   endDate;
    private String customerName;
    private int    bizType;
    private String idCard;
    private int commProgressId;
    private int customerId;
    private int recordInfoId;

    public int getRecordInfoId() {
        return recordInfoId;
    }

    public void setRecordInfoId(int recordInfoId) {
        this.recordInfoId = recordInfoId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCommProgressId() {
        return commProgressId;
    }

    public void setCommProgressId(int commProgressId) {
        this.commProgressId = commProgressId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        result = prime * result + bizType;
        result = prime * result + callType;
        result = prime * result + ((creditCard == null) ? 0 : creditCard.hashCode());
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((fileMD5 == null) ? 0 : fileMD5.hashCode());
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = prime * result + ((idCard == null) ? 0 : idCard.hashCode());
        result = prime * result + ((localPhone == null) ? 0 : localPhone.hashCode());
        result = prime * result + ((remark == null) ? 0 : remark.hashCode());
        result = prime * result + ((remotePhone == null) ? 0 : remotePhone.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
        XmlRecordInfo other = (XmlRecordInfo) obj;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (bizType != other.bizType)
            return false;
        if (callType != other.callType)
            return false;
        if (creditCard == null) {
            if (other.creditCard != null)
                return false;
        } else if (!creditCard.equals(other.creditCard))
            return false;
        if (customerName == null) {
            if (other.customerName != null)
                return false;
        } else if (!customerName.equals(other.customerName))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (fileMD5 == null) {
            if (other.fileMD5 != null)
                return false;
        } else if (!fileMD5.equals(other.fileMD5))
            return false;
        if (fileName == null) {
            if (other.fileName != null)
                return false;
        } else if (!fileName.equals(other.fileName))
            return false;
        if (idCard == null) {
            if (other.idCard != null)
                return false;
        } else if (!idCard.equals(other.idCard))
            return false;
        if (localPhone == null) {
            if (other.localPhone != null)
                return false;
        } else if (!localPhone.equals(other.localPhone))
            return false;
        if (remark == null) {
            if (other.remark != null)
                return false;
        } else if (!remark.equals(other.remark))
            return false;
        if (remotePhone == null) {
            if (other.remotePhone != null)
                return false;
        } else if (!remotePhone.equals(other.remotePhone))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        return true;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getCallType() {
        return callType;
    }

    public void setCallType(int callType) {
        this.callType = callType;
    }

    public String getLocalPhone() {
        return localPhone;
    }

    public void setLocalPhone(String localPhone) {
        this.localPhone = localPhone;
    }

    public String getRemotePhone() {
        return remotePhone;
    }

    public void setRemotePhone(String remotePhone) {
        this.remotePhone = remotePhone;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getBizType() {
        return bizType;
    }

    public void setBizType(int bizType) {
        this.bizType = bizType;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileMD5() {
        return fileMD5;
    }

    public void setFileMD5(String fileMD5) {
        this.fileMD5 = fileMD5;
    }

    private String creditCard;
    private String remark;
    private String fileName;
    private String fileMD5;
}
