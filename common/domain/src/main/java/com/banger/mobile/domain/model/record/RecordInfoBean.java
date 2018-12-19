/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :录音查询列表bean
 * Author     :huangkai
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.domain.model.record;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huangkai
 * @version $Id: RecordInfoBean.java,v 0.1 May 3, 2012 11:16:06 AM huangkai Exp $
 */
public class RecordInfoBean  implements Serializable{

    private static final long serialVersionUID = 7594158287875691297L;
    private String recordInfoId; //录音id
    private String recordNo;    //流水号
    private String customerName; //客户
    private String remotePhone;    //电话
    private String callTypeName; //联系类型
    private Date startDate;   //开始时间
    private String callTime;    //时长
    private String userName;    //客户经理 
    private String bizType;       //业务类型
    private String recordSource;       //记录来源
    private String state;       //状态
    private String fileName;    //录音文件名称
    private String filePath;    //录音文件路径
    private String isRead;  //是否已读
    private String customerId;  //客户id
    private String remark;  //备注
    private Integer commProgressId; //沟通进度id
    private Long fileSize;//文件大小
    private String idCard;
    private String creditCard;
    private String verifyUserName;    //审核人姓名
    private String status;      //状态
    private int splitCount;    //拆分条数
    private String content;     //内容
    private String mmsTitle;    //彩信标题
    private String mobilePhone1;
    private String mobilePhone2;
    private String phone;
    private String phoneExt;
    private String fax;
    private String faxExt;
    private Integer defaultPhoneType;
    private String defaultPhone;
    private String commProgressName; //沟通进度
    private Integer isNoGood; // 不良客户
    private Integer isCanceled;
    private Integer fileId;    //文件ID
    public RecordInfoBean() {
        super();
    }

    public RecordInfoBean(String recordInfoId, String recordNo, String customerName,
                          String remotePhone, String callTypeName, Date startDate, String callTime,
                          String userName, String bizType, String recordSource, String state,
                          String fileName, String filePath, String isRead, String customerId,
                          String remark, Integer commProgressId, Long fileSize, String idCard,
                          String creditCard, String verifyUserName, String status, int splitCount,
                          String content, String mmsTitle, String mobilePhone1,
                          String mobilePhone2, String phone, String phoneExt, String fax,
                          String faxExt, Integer defaultPhoneType, String defaultPhone,
                          String commProgressName) {
        super();
        this.recordInfoId = recordInfoId;
        this.recordNo = recordNo;
        this.customerName = customerName;
        this.remotePhone = remotePhone;
        this.callTypeName = callTypeName;
        this.startDate = startDate;
        this.callTime = callTime;
        this.userName = userName;
        this.bizType = bizType;
        this.recordSource = recordSource;
        this.state = state;
        this.fileName = fileName;
        this.filePath = filePath;
        this.isRead = isRead;
        this.customerId = customerId;
        this.remark = remark;
        this.commProgressId = commProgressId;
        this.fileSize = fileSize;
        this.idCard = idCard;
        this.creditCard = creditCard;
        this.verifyUserName = verifyUserName;
        this.status = status;
        this.splitCount = splitCount;
        this.content = content;
        this.mmsTitle = mmsTitle;
        this.mobilePhone1 = mobilePhone1;
        this.mobilePhone2 = mobilePhone2;
        this.phone = phone;
        this.phoneExt = phoneExt;
        this.fax = fax;
        this.faxExt = faxExt;
        this.defaultPhoneType = defaultPhoneType;
        this.defaultPhone = defaultPhone;
        this.commProgressName = commProgressName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bizType == null) ? 0 : bizType.hashCode());
        result = prime * result + ((callTime == null) ? 0 : callTime.hashCode());
        result = prime * result + ((callTypeName == null) ? 0 : callTypeName.hashCode());
        result = prime * result + ((commProgressId == null) ? 0 : commProgressId.hashCode());
        result = prime * result + ((commProgressName == null) ? 0 : commProgressName.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((creditCard == null) ? 0 : creditCard.hashCode());
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((defaultPhone == null) ? 0 : defaultPhone.hashCode());
        result = prime * result + ((defaultPhoneType == null) ? 0 : defaultPhoneType.hashCode());
        result = prime * result + ((fax == null) ? 0 : fax.hashCode());
        result = prime * result + ((faxExt == null) ? 0 : faxExt.hashCode());
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
        result = prime * result + ((fileSize == null) ? 0 : fileSize.hashCode());
        result = prime * result + ((idCard == null) ? 0 : idCard.hashCode());
        result = prime * result + ((isRead == null) ? 0 : isRead.hashCode());
        result = prime * result + ((mmsTitle == null) ? 0 : mmsTitle.hashCode());
        result = prime * result + ((mobilePhone1 == null) ? 0 : mobilePhone1.hashCode());
        result = prime * result + ((mobilePhone2 == null) ? 0 : mobilePhone2.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((phoneExt == null) ? 0 : phoneExt.hashCode());
        result = prime * result + ((recordInfoId == null) ? 0 : recordInfoId.hashCode());
        result = prime * result + ((recordNo == null) ? 0 : recordNo.hashCode());
        result = prime * result + ((recordSource == null) ? 0 : recordSource.hashCode());
        result = prime * result + ((remark == null) ? 0 : remark.hashCode());
        result = prime * result + ((remotePhone == null) ? 0 : remotePhone.hashCode());
        result = prime * result + splitCount;
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((verifyUserName == null) ? 0 : verifyUserName.hashCode());
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
        final RecordInfoBean other = (RecordInfoBean) obj;
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
        if (callTypeName == null) {
            if (other.callTypeName != null)
                return false;
        } else if (!callTypeName.equals(other.callTypeName))
            return false;
        if (commProgressId == null) {
            if (other.commProgressId != null)
                return false;
        } else if (!commProgressId.equals(other.commProgressId))
            return false;
        if (commProgressName == null) {
            if (other.commProgressName != null)
                return false;
        } else if (!commProgressName.equals(other.commProgressName))
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
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
        if (defaultPhone == null) {
            if (other.defaultPhone != null)
                return false;
        } else if (!defaultPhone.equals(other.defaultPhone))
            return false;
        if (defaultPhoneType == null) {
            if (other.defaultPhoneType != null)
                return false;
        } else if (!defaultPhoneType.equals(other.defaultPhoneType))
            return false;
        if (fax == null) {
            if (other.fax != null)
                return false;
        } else if (!fax.equals(other.fax))
            return false;
        if (faxExt == null) {
            if (other.faxExt != null)
                return false;
        } else if (!faxExt.equals(other.faxExt))
            return false;
        if (fileName == null) {
            if (other.fileName != null)
                return false;
        } else if (!fileName.equals(other.fileName))
            return false;
        if (filePath == null) {
            if (other.filePath != null)
                return false;
        } else if (!filePath.equals(other.filePath))
            return false;
        if (fileSize == null) {
            if (other.fileSize != null)
                return false;
        } else if (!fileSize.equals(other.fileSize))
            return false;
        if (idCard == null) {
            if (other.idCard != null)
                return false;
        } else if (!idCard.equals(other.idCard))
            return false;
        if (isRead == null) {
            if (other.isRead != null)
                return false;
        } else if (!isRead.equals(other.isRead))
            return false;
        if (mmsTitle == null) {
            if (other.mmsTitle != null)
                return false;
        } else if (!mmsTitle.equals(other.mmsTitle))
            return false;
        if (mobilePhone1 == null) {
            if (other.mobilePhone1 != null)
                return false;
        } else if (!mobilePhone1.equals(other.mobilePhone1))
            return false;
        if (mobilePhone2 == null) {
            if (other.mobilePhone2 != null)
                return false;
        } else if (!mobilePhone2.equals(other.mobilePhone2))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        if (phoneExt == null) {
            if (other.phoneExt != null)
                return false;
        } else if (!phoneExt.equals(other.phoneExt))
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
        if (splitCount != other.splitCount)
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        if (verifyUserName == null) {
            if (other.verifyUserName != null)
                return false;
        } else if (!verifyUserName.equals(other.verifyUserName))
            return false;
        return true;
    }

    public String getRecordInfoId() {
        return recordInfoId;
    }

    public void setRecordInfoId(String recordInfoId) {
        this.recordInfoId = recordInfoId;
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

    public String getRemotePhone() {
        return remotePhone;
    }

    public void setRemotePhone(String remotePhone) {
        this.remotePhone = remotePhone;
    }

    public String getCallTypeName() {
        return callTypeName;
    }

    public void setCallTypeName(String callTypeName) {
        this.callTypeName = callTypeName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getRecordSource() {
        return recordSource;
    }

    public void setRecordSource(String recordSource) {
        this.recordSource = recordSource;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
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

    public String getVerifyUserName() {
        return verifyUserName;
    }

    public void setVerifyUserName(String verifyUserName) {
        this.verifyUserName = verifyUserName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSplitCount() {
        return splitCount;
    }

    public void setSplitCount(int splitCount) {
        this.splitCount = splitCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMmsTitle() {
        return mmsTitle;
    }

    public void setMmsTitle(String mmsTitle) {
        this.mmsTitle = mmsTitle;
    }

    public String getMobilePhone1() {
        return mobilePhone1;
    }

    public void setMobilePhone1(String mobilePhone1) {
        this.mobilePhone1 = mobilePhone1;
    }

    public String getMobilePhone2() {
        return mobilePhone2;
    }

    public void setMobilePhone2(String mobilePhone2) {
        this.mobilePhone2 = mobilePhone2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneExt() {
        return phoneExt;
    }

    public void setPhoneExt(String phoneExt) {
        this.phoneExt = phoneExt;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFaxExt() {
        return faxExt;
    }

    public void setFaxExt(String faxExt) {
        this.faxExt = faxExt;
    }

    public Integer getDefaultPhoneType() {
        return defaultPhoneType;
    }

    public void setDefaultPhoneType(Integer defaultPhoneType) {
        this.defaultPhoneType = defaultPhoneType;
    }

    public String getCommProgressName() {
        return commProgressName;
    }

    public void setCommProgressName(String commProgressName) {
        this.commProgressName = commProgressName;
    }

    public void setDefaultPhone(String defaultPhone) {
        this.defaultPhone = defaultPhone;
    }

    public Integer getIsNoGood() {
		return isNoGood;
	}

	public void setIsNoGood(Integer isNoGood) {
		this.isNoGood = isNoGood;
	}

	/**
     * 得到默认显示号码
     * @return
     */
    public String getDefaultPhone()
    {
        if(this.getRemotePhone()!=null && !"".equals(this.getRemotePhone()))
        {
            return this.getRemotePhone();
        }
        else
        {
            if(this.defaultPhoneType!=null)
            {
                switch(this.defaultPhoneType.intValue())
                {
                    case 1:
                        return this.mobilePhone1;
                    case 2:
                        return this.mobilePhone2;
                    case 3:
                        return this.phone;
                    case 4:
                        return this.fax;
                }
            }
        }
        return "";
    }

	public Integer getIsCanceled() {
		return isCanceled;
	}

	public void setIsCanceled(Integer isCanceled) {
		this.isCanceled = isCanceled;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
    
    
}
