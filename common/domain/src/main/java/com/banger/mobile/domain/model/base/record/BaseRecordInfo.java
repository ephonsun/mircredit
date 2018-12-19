/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :RecRecordInfo 录音记录实体基类
 * Author     :zhangxiang
 * Create Date:2012-5-14
 */
package com.banger.mobile.domain.model.base.record;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author huangkai
 * @version $Id: BaseRecordInfo.java,v 0.1 2012-5-14 上午9:56:12 huangkai Exp $
 */
public class BaseRecordInfo extends BaseObject implements Serializable {

    private static final long serialVersionUID = -5117557292664198539L;
    
    private Integer           recordInfoId;                           //录音信息ID
    private String            recordNo;                               //录音流水号
    private Integer           userId;                                 //用户ID
    private Integer           customerId;                             //客户ID
    private Integer           callType;                               //呼叫类型
    private String            localPhone;                             //本地号码
    private String            remotePhone;                            //对方号码
    private Date              startDate;                              //开始时间
    private Date              endDate;                                //结束时间
    private Integer           callTime;                               //通话时间
    private String            customerName;                           //客户姓名
    private Integer           bizType;                                //业务类型
    private String            idCard;                                 //身份证号
    private String            creditCard;                             //信用卡号
    private String            remark;                                 //备注
    private Integer           commProgressId;                         //沟通进度ID
    private Integer           isCanceled;                             //是否取消(1:作废   0:有效)
    private Integer           isDel;                                  //是否删除(1:删除   0:未删除)
    private Integer           isArchived;                             //是否归档(1:归档   0:未归档)
    private Date              archiveDate;                            //归档时间
    private Integer           recordSource;                           //录音来源
    private String            content;                                //短彩信内容
    private Integer           splitCount;                             //拆分条数
    private String            status;                                 //短彩信状态
    private Integer           verifyUserId;                           //审核用户id
    private String            mmsTitle;                               //彩信标题
    private Date              createDate;                             //创建时间
    private Date              updateDate;                             //更新时间
    private Integer           createUser;                             //创建用户
    private Integer           updateUser;                             //更新用户
    private String            isKnowCustomer;                         //是否是已知客户(临时字段 1:已知    0.未知)
    private Integer           forcallType;                            //联系类型（临时字段，用在通话记录）
    private String            userName;                               //客户经理姓名
    private int               isRead;                                 //是否已读
    private String            strCallTime;                            //HH:mm:ss类型的时长
    private Date              ringTime;                               //
    private String            customerStr;                            //客户信息 冗余字段
    private Integer            fileId;                                 //文件Id
	public Integer getRecordInfoId() {
		return recordInfoId;
	}
	public void setRecordInfoId(Integer recordInfoId) {
		this.recordInfoId = recordInfoId;
	}
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
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
	public Integer getCallType() {
		return callType;
	}
	public void setCallType(Integer callType) {
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
	public Integer getCallTime() {
		return callTime;
	}
	public void setCallTime(Integer callTime) {
		this.callTime = callTime;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getBizType() {
		return bizType;
	}
	public void setBizType(Integer bizType) {
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
	public Integer getCommProgressId() {
		return commProgressId;
	}
	public void setCommProgressId(Integer commProgressId) {
		this.commProgressId = commProgressId;
	}
	public Integer getIsCanceled() {
		return isCanceled;
	}
	public void setIsCanceled(Integer isCanceled) {
		this.isCanceled = isCanceled;
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
	public Date getArchiveDate() {
		return archiveDate;
	}
	public void setArchiveDate(Date archiveDate) {
		this.archiveDate = archiveDate;
	}
	public Integer getRecordSource() {
		return recordSource;
	}
	public void setRecordSource(Integer recordSource) {
		this.recordSource = recordSource;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getSplitCount() {
		return splitCount;
	}
	public void setSplitCount(Integer splitCount) {
		this.splitCount = splitCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getVerifyUserId() {
		return verifyUserId;
	}
	public void setVerifyUserId(Integer verifyUserId) {
		this.verifyUserId = verifyUserId;
	}
	public String getMmsTitle() {
		return mmsTitle;
	}
	public void setMmsTitle(String mmsTitle) {
		this.mmsTitle = mmsTitle;
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
	public String getIsKnowCustomer() {
		return isKnowCustomer;
	}
	public void setIsKnowCustomer(String isKnowCustomer) {
		this.isKnowCustomer = isKnowCustomer;
	}
	public Integer getForcallType() {
		return forcallType;
	}
	public void setForcallType(Integer forcallType) {
		this.forcallType = forcallType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	public String getStrCallTime() {
		return strCallTime;
	}
	public void setStrCallTime(String strCallTime) {
		this.strCallTime = strCallTime;
	}
	public Date getRingTime() {
		return ringTime;
	}
	public void setRingTime(Date ringTime) {
		this.ringTime = ringTime;
	}
	public String getCustomerStr() {
		return customerStr;
	}
	public void setCustomerStr(String customerStr) {
		this.customerStr = customerStr;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((archiveDate == null) ? 0 : archiveDate.hashCode());
		result = prime * result + ((bizType == null) ? 0 : bizType.hashCode());
		result = prime * result
				+ ((callTime == null) ? 0 : callTime.hashCode());
		result = prime * result
				+ ((callType == null) ? 0 : callType.hashCode());
		result = prime * result
				+ ((commProgressId == null) ? 0 : commProgressId.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result
				+ ((creditCard == null) ? 0 : creditCard.hashCode());
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result
				+ ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result
				+ ((customerStr == null) ? 0 : customerStr.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
		result = prime * result
				+ ((forcallType == null) ? 0 : forcallType.hashCode());
		result = prime * result + ((idCard == null) ? 0 : idCard.hashCode());
		result = prime * result
				+ ((isArchived == null) ? 0 : isArchived.hashCode());
		result = prime * result
				+ ((isCanceled == null) ? 0 : isCanceled.hashCode());
		result = prime * result + ((isDel == null) ? 0 : isDel.hashCode());
		result = prime * result
				+ ((isKnowCustomer == null) ? 0 : isKnowCustomer.hashCode());
		result = prime * result + isRead;
		result = prime * result
				+ ((localPhone == null) ? 0 : localPhone.hashCode());
		result = prime * result
				+ ((mmsTitle == null) ? 0 : mmsTitle.hashCode());
		result = prime * result
				+ ((recordInfoId == null) ? 0 : recordInfoId.hashCode());
		result = prime * result
				+ ((recordNo == null) ? 0 : recordNo.hashCode());
		result = prime * result
				+ ((recordSource == null) ? 0 : recordSource.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result
				+ ((remotePhone == null) ? 0 : remotePhone.hashCode());
		result = prime * result
				+ ((ringTime == null) ? 0 : ringTime.hashCode());
		result = prime * result
				+ ((splitCount == null) ? 0 : splitCount.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((strCallTime == null) ? 0 : strCallTime.hashCode());
		result = prime * result
				+ ((updateDate == null) ? 0 : updateDate.hashCode());
		result = prime * result
				+ ((updateUser == null) ? 0 : updateUser.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		result = prime * result
				+ ((verifyUserId == null) ? 0 : verifyUserId.hashCode());
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
		BaseRecordInfo other = (BaseRecordInfo) obj;
		if (archiveDate == null) {
			if (other.archiveDate != null)
				return false;
		} else if (!archiveDate.equals(other.archiveDate))
			return false;
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
		if (commProgressId == null) {
			if (other.commProgressId != null)
				return false;
		} else if (!commProgressId.equals(other.commProgressId))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (createUser == null) {
			if (other.createUser != null)
				return false;
		} else if (!createUser.equals(other.createUser))
			return false;
		if (creditCard == null) {
			if (other.creditCard != null)
				return false;
		} else if (!creditCard.equals(other.creditCard))
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
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (fileId == null) {
			if (other.fileId != null)
				return false;
		} else if (!fileId.equals(other.fileId))
			return false;
		if (forcallType == null) {
			if (other.forcallType != null)
				return false;
		} else if (!forcallType.equals(other.forcallType))
			return false;
		if (idCard == null) {
			if (other.idCard != null)
				return false;
		} else if (!idCard.equals(other.idCard))
			return false;
		if (isArchived == null) {
			if (other.isArchived != null)
				return false;
		} else if (!isArchived.equals(other.isArchived))
			return false;
		if (isCanceled == null) {
			if (other.isCanceled != null)
				return false;
		} else if (!isCanceled.equals(other.isCanceled))
			return false;
		if (isDel == null) {
			if (other.isDel != null)
				return false;
		} else if (!isDel.equals(other.isDel))
			return false;
		if (isKnowCustomer == null) {
			if (other.isKnowCustomer != null)
				return false;
		} else if (!isKnowCustomer.equals(other.isKnowCustomer))
			return false;
		if (isRead != other.isRead)
			return false;
		if (localPhone == null) {
			if (other.localPhone != null)
				return false;
		} else if (!localPhone.equals(other.localPhone))
			return false;
		if (mmsTitle == null) {
			if (other.mmsTitle != null)
				return false;
		} else if (!mmsTitle.equals(other.mmsTitle))
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
		if (ringTime == null) {
			if (other.ringTime != null)
				return false;
		} else if (!ringTime.equals(other.ringTime))
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
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (strCallTime == null) {
			if (other.strCallTime != null)
				return false;
		} else if (!strCallTime.equals(other.strCallTime))
			return false;
		if (updateDate == null) {
			if (other.updateDate != null)
				return false;
		} else if (!updateDate.equals(other.updateDate))
			return false;
		if (updateUser == null) {
			if (other.updateUser != null)
				return false;
		} else if (!updateUser.equals(other.updateUser))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (verifyUserId == null) {
			if (other.verifyUserId != null)
				return false;
		} else if (!verifyUserId.equals(other.verifyUserId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BaseRecordInfo [recordInfoId=" + recordInfoId + ", recordNo="
				+ recordNo + ", userId=" + userId + ", customerId="
				+ customerId + ", callType=" + callType + ", localPhone="
				+ localPhone + ", remotePhone=" + remotePhone + ", startDate="
				+ startDate + ", endDate=" + endDate + ", callTime=" + callTime
				+ ", customerName=" + customerName + ", bizType=" + bizType
				+ ", idCard=" + idCard + ", creditCard=" + creditCard
				+ ", remark=" + remark + ", commProgressId=" + commProgressId
				+ ", isCanceled=" + isCanceled + ", isDel=" + isDel
				+ ", isArchived=" + isArchived + ", archiveDate=" + archiveDate
				+ ", recordSource=" + recordSource + ", content=" + content
				+ ", splitCount=" + splitCount + ", status=" + status
				+ ", verifyUserId=" + verifyUserId + ", mmsTitle=" + mmsTitle
				+ ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", createUser=" + createUser + ", updateUser=" + updateUser
				+ ", isKnowCustomer=" + isKnowCustomer + ", forcallType="
				+ forcallType + ", userName=" + userName + ", isRead=" + isRead
				+ ", strCallTime=" + strCallTime + ", ringTime=" + ringTime
				+ ", customerStr=" + customerStr + ", fileId=" + fileId + "]";
	}
	public BaseRecordInfo(Integer recordInfoId, String recordNo,
			Integer userId, Integer customerId, Integer callType,
			String localPhone, String remotePhone, Date startDate,
			Date endDate, Integer callTime, String customerName,
			Integer bizType, String idCard, String creditCard, String remark,
			Integer commProgressId, Integer isCanceled, Integer isDel,
			Integer isArchived, Date archiveDate, Integer recordSource,
			String content, Integer splitCount, String status,
			Integer verifyUserId, String mmsTitle, Date createDate,
			Date updateDate, Integer createUser, Integer updateUser,
			String isKnowCustomer, Integer forcallType, String userName,
			int isRead, String strCallTime, Date ringTime, String customerStr,
			Integer fileId) {
		super();
		this.recordInfoId = recordInfoId;
		this.recordNo = recordNo;
		this.userId = userId;
		this.customerId = customerId;
		this.callType = callType;
		this.localPhone = localPhone;
		this.remotePhone = remotePhone;
		this.startDate = startDate;
		this.endDate = endDate;
		this.callTime = callTime;
		this.customerName = customerName;
		this.bizType = bizType;
		this.idCard = idCard;
		this.creditCard = creditCard;
		this.remark = remark;
		this.commProgressId = commProgressId;
		this.isCanceled = isCanceled;
		this.isDel = isDel;
		this.isArchived = isArchived;
		this.archiveDate = archiveDate;
		this.recordSource = recordSource;
		this.content = content;
		this.splitCount = splitCount;
		this.status = status;
		this.verifyUserId = verifyUserId;
		this.mmsTitle = mmsTitle;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
		this.isKnowCustomer = isKnowCustomer;
		this.forcallType = forcallType;
		this.userName = userName;
		this.isRead = isRead;
		this.strCallTime = strCallTime;
		this.ringTime = ringTime;
		this.customerStr = customerStr;
		this.fileId = fileId;
	}
	public BaseRecordInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
