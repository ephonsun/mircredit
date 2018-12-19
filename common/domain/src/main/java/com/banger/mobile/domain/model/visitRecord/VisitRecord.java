/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :拜访记录实体类
 * Author     :yujh
 * Create Date:May 29, 2012
 */
package com.banger.mobile.domain.model.visitRecord;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author yujh
 * @version $Id: VisitRecord.java,v 0.1 May 29, 2012 11:35:25 AM Administrator Exp $
 */
public class VisitRecord extends BaseObject implements Serializable {
    private static final long serialVersionUID = 2924445163728271448L;
    private Integer           recordInfoId;                           //编号
    private Integer           userId;                                 //用户id
    private Integer           customerId;                             //客户id
    private Date              startDate;                              //拜访时间
    private Integer           bizType;                                //业务类型编号
    private Integer           commProgress;                           //沟通进度编号
    private String            remark;                                 //备注
    private Integer           callType;                               //类型
    private String            recordNo;                               //流水号
    private String            customerName;                           //客户姓名
    private Integer           isCancled;                              //是否作废
    private Integer           isDel;                                  //是否删除
    private Integer           isArchived;                             //是否归档
    private Date              createDate;                             //创建时间
    private Integer           isRead;                                 //是否已读
    private String            remotePhone;
    private Integer           creatUser;                              //创建人
    private Integer           callTime;                               //时长
    private Integer           splitCount;                             //拆分条数
    private Integer           recordSource;                           //记录来源
    private String            customerStr;                            //客户冗余字段
    public Integer getRecordInfoId() {
        return recordInfoId;
    }
    public void setRecordInfoId(Integer recordInfoId) {
        this.recordInfoId = recordInfoId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Integer getBizType() {
        return bizType;
    }
    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }
    public Integer getCommProgress() {
        return commProgress;
    }
    public void setCommProgress(Integer commProgress) {
        this.commProgress = commProgress;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getCallType() {
        return callType;
    }
    public void setCallType(Integer callType) {
        this.callType = callType;
    }
    public String getRecordNo() {
        return recordNo;
    }
    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public Integer getIsCancled() {
        return isCancled;
    }
    public void setIsCancled(Integer isCancled) {
        this.isCancled = isCancled;
    }
    public Integer getIsDel() {
        return isDel;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public Integer getIsArchived() {
        return isArchived;
    }
    public void setIsArchived(Integer isArchived) {
        this.isArchived = isArchived;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Integer getIsRead() {
        return isRead;
    }
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
    public String getRemotePhone() {
        return remotePhone;
    }
    public void setRemotePhone(String remotePhone) {
        this.remotePhone = remotePhone;
    }
    public Integer getCreatUser() {
        return creatUser;
    }
    public void setCreatUser(Integer creatUser) {
        this.creatUser = creatUser;
    }
    public Integer getCallTime() {
        return callTime;
    }
    public void setCallTime(Integer callTime) {
        this.callTime = callTime;
    }
    public Integer getSplitCount() {
        return splitCount;
    }
    public void setSplitCount(Integer splitCount) {
        this.splitCount = splitCount;
    }
    public Integer getRecordSource() {
        return recordSource;
    }
    public void setRecordSource(Integer recordSource) {
        this.recordSource = recordSource;
    }
    public String getCustomerStr() {
        return customerStr;
    }
    public void setCustomerStr(String customerStr) {
        this.customerStr = customerStr;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((bizType == null) ? 0 : bizType.hashCode());
        result = prime * result + ((callTime == null) ? 0 : callTime.hashCode());
        result = prime * result + ((callType == null) ? 0 : callType.hashCode());
        result = prime * result + ((commProgress == null) ? 0 : commProgress.hashCode());
        result = prime * result + ((creatUser == null) ? 0 : creatUser.hashCode());
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((customerStr == null) ? 0 : customerStr.hashCode());
        result = prime * result + ((isArchived == null) ? 0 : isArchived.hashCode());
        result = prime * result + ((isCancled == null) ? 0 : isCancled.hashCode());
        result = prime * result + ((isDel == null) ? 0 : isDel.hashCode());
        result = prime * result + ((isRead == null) ? 0 : isRead.hashCode());
        result = prime * result + ((recordInfoId == null) ? 0 : recordInfoId.hashCode());
        result = prime * result + ((recordNo == null) ? 0 : recordNo.hashCode());
        result = prime * result + ((recordSource == null) ? 0 : recordSource.hashCode());
        result = prime * result + ((remark == null) ? 0 : remark.hashCode());
        result = prime * result + ((remotePhone == null) ? 0 : remotePhone.hashCode());
        result = prime * result + ((splitCount == null) ? 0 : splitCount.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        final VisitRecord other = (VisitRecord) obj;
        if (bizType == null) {
            if (other.bizType != null)
                return false;
        } else if (!bizType.equals(other.bizType))
            return false;
        if (callTime == null) {
            if (other.callTime != null)
                return false;
        } else if (!callTime.equals(other.callTime))
            return false;
        if (callType == null) {
            if (other.callType != null)
                return false;
        } else if (!callType.equals(other.callType))
            return false;
        if (commProgress == null) {
            if (other.commProgress != null)
                return false;
        } else if (!commProgress.equals(other.commProgress))
            return false;
        if (creatUser == null) {
            if (other.creatUser != null)
                return false;
        } else if (!creatUser.equals(other.creatUser))
            return false;
        if (createDate == null) {
            if (other.createDate != null)
                return false;
        } else if (!createDate.equals(other.createDate))
            return false;
        if (customerId == null) {
            if (other.customerId != null)
                return false;
        } else if (!customerId.equals(other.customerId))
            return false;
        if (customerName == null) {
            if (other.customerName != null)
                return false;
        } else if (!customerName.equals(other.customerName))
            return false;
        if (customerStr == null) {
            if (other.customerStr != null)
                return false;
        } else if (!customerStr.equals(other.customerStr))
            return false;
        if (isArchived == null) {
            if (other.isArchived != null)
                return false;
        } else if (!isArchived.equals(other.isArchived))
            return false;
        if (isCancled == null) {
            if (other.isCancled != null)
                return false;
        } else if (!isCancled.equals(other.isCancled))
            return false;
        if (isDel == null) {
            if (other.isDel != null)
                return false;
        } else if (!isDel.equals(other.isDel))
            return false;
        if (isRead == null) {
            if (other.isRead != null)
                return false;
        } else if (!isRead.equals(other.isRead))
            return false;
        if (recordInfoId == null) {
            if (other.recordInfoId != null)
                return false;
        } else if (!recordInfoId.equals(other.recordInfoId))
            return false;
        if (recordNo == null) {
            if (other.recordNo != null)
                return false;
        } else if (!recordNo.equals(other.recordNo))
            return false;
        if (recordSource == null) {
            if (other.recordSource != null)
                return false;
        } else if (!recordSource.equals(other.recordSource))
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
        if (splitCount == null) {
            if (other.splitCount != null)
                return false;
        } else if (!splitCount.equals(other.splitCount))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }
    public VisitRecord(Integer recordInfoId, Integer userId, Integer customerId, Date startDate,
                       Integer bizType, Integer commProgress, String remark, Integer callType,
                       String recordNo, String customerName, Integer isCancled, Integer isDel,
                       Integer isArchived, Date createDate, Integer isRead, String remotePhone,
                       Integer creatUser, Integer callTime, Integer splitCount,
                       Integer recordSource, String customerStr) {
        super();
        this.recordInfoId = recordInfoId;
        this.userId = userId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.bizType = bizType;
        this.commProgress = commProgress;
        this.remark = remark;
        this.callType = callType;
        this.recordNo = recordNo;
        this.customerName = customerName;
        this.isCancled = isCancled;
        this.isDel = isDel;
        this.isArchived = isArchived;
        this.createDate = createDate;
        this.isRead = isRead;
        this.remotePhone = remotePhone;
        this.creatUser = creatUser;
        this.callTime = callTime;
        this.splitCount = splitCount;
        this.recordSource = recordSource;
        this.customerStr = customerStr;
    }
    public VisitRecord() {
        super();
    }
   
    
}
