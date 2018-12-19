package com.banger.mobile.domain.model.record;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.annotation.EscapeFieldFilter;

public class RecordDetail  implements Serializable{
    private static final long serialVersionUID = 9157232745747059049L;
    private String recordInfoId; //录音id
    private String recordNo;    //流水号
    private String recordSource;       //记录来源
    private String customerName; //客户姓名
    private String remotePhone;    //电话
    private String isCanceled;  //状态
    private String callTypeName; //联系类型
    private String userName;    //操作者
    private Date startDate;   //开始时间
    private String callTime;    //通话时长
    private String bizType;       //业务类型
    private String commProgress;    //沟通进度
    private String idCard;   //身份证号
    private String creditCard;   //信用卡号
    private String remark;      //备注
    private String filePath;    //录音文件路径
    private String fileName;    //录音文件名称
    private String customerNo;     //客户编号
    private String fileSize;    //文件大小
    private Integer customerId;     //客户id
    private String verifyUserName;    //审核人姓名
    private String status;      //状态
    private int splitCount;    //拆分条数
    @EscapeFieldFilter
    private String content;     //内容
    private String customerTitle;   //客户称谓
    private String customerStr;     //客户冗余字段
    
    private String mobilePhone1;
    private String mobilePhone2;
    private String phone;
    private String phoneExt;
    private String fax;
    private String faxExt;
    private Integer defaultPhoneType;
    private String defaultPhone;
    private Integer fileId;    //录音文件Id
    
    public String getRecordInfoId() {
        return recordInfoId;
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

    public void setRecordInfoId(String recordInfoId) {
        this.recordInfoId = recordInfoId;
    }

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public String getRecordSource() {
        return recordSource;
    }

    public void setRecordSource(String recordSource) {
        this.recordSource = recordSource;
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

    public String getIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(String isCanceled) {
        this.isCanceled = isCanceled;
    }

    public String getCallTypeName() {
        return callTypeName;
    }

    public void setCallTypeName(String callTypeName) {
        this.callTypeName = callTypeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getCommProgress() {
        return commProgress;
    }

    public void setCommProgress(String commProgress) {
        this.commProgress = commProgress;
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    public String getCustomerStr() {
        return customerStr;
    }

    public void setCustomerStr(String customerStr) {
        this.customerStr = customerStr;
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

    public void setDefaultPhone(String defaultPhone) {
        this.defaultPhone = defaultPhone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bizType == null) ? 0 : bizType.hashCode());
        result = prime * result + ((callTime == null) ? 0 : callTime.hashCode());
        result = prime * result + ((callTypeName == null) ? 0 : callTypeName.hashCode());
        result = prime * result + ((commProgress == null) ? 0 : commProgress.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((creditCard == null) ? 0 : creditCard.hashCode());
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
        result = prime * result + ((customerNo == null) ? 0 : customerNo.hashCode());
        result = prime * result + ((customerStr == null) ? 0 : customerStr.hashCode());
        result = prime * result + ((customerTitle == null) ? 0 : customerTitle.hashCode());
        result = prime * result + ((defaultPhone == null) ? 0 : defaultPhone.hashCode());
        result = prime * result + ((defaultPhoneType == null) ? 0 : defaultPhoneType.hashCode());
        result = prime * result + ((fax == null) ? 0 : fax.hashCode());
        result = prime * result + ((faxExt == null) ? 0 : faxExt.hashCode());
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
        result = prime * result + ((fileSize == null) ? 0 : fileSize.hashCode());
        result = prime * result + ((idCard == null) ? 0 : idCard.hashCode());
        result = prime * result + ((isCanceled == null) ? 0 : isCanceled.hashCode());
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
        final RecordDetail other = (RecordDetail) obj;
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
        if (commProgress == null) {
            if (other.commProgress != null)
                return false;
        } else if (!commProgress.equals(other.commProgress))
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
        if (customerNo == null) {
            if (other.customerNo != null)
                return false;
        } else if (!customerNo.equals(other.customerNo))
            return false;
        if (customerStr == null) {
            if (other.customerStr != null)
                return false;
        } else if (!customerStr.equals(other.customerStr))
            return false;
        if (customerTitle == null) {
            if (other.customerTitle != null)
                return false;
        } else if (!customerTitle.equals(other.customerTitle))
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
        if (isCanceled == null) {
            if (other.isCanceled != null)
                return false;
        } else if (!isCanceled.equals(other.isCanceled))
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

    public RecordDetail(String recordInfoId, String recordNo, String recordSource,
                        String customerName, String remotePhone, String isCanceled,
                        String callTypeName, String userName, Date startDate, String callTime,
                        String bizType, String commProgress, String idCard, String creditCard,
                        String remark, String filePath, String fileName, String customerNo,
                        String fileSize, Integer customerId, String verifyUserName, String status,
                        int splitCount, String content, String customerTitle, String customerStr,
                        String mobilePhone1, String mobilePhone2, String phone, String phoneExt,
                        String fax, String faxExt, Integer defaultPhoneType, String defaultPhone) {
        super();
        this.recordInfoId = recordInfoId;
        this.recordNo = recordNo;
        this.recordSource = recordSource;
        this.customerName = customerName;
        this.remotePhone = remotePhone;
        this.isCanceled = isCanceled;
        this.callTypeName = callTypeName;
        this.userName = userName;
        this.startDate = startDate;
        this.callTime = callTime;
        this.bizType = bizType;
        this.commProgress = commProgress;
        this.idCard = idCard;
        this.creditCard = creditCard;
        this.remark = remark;
        this.filePath = filePath;
        this.fileName = fileName;
        this.customerNo = customerNo;
        this.fileSize = fileSize;
        this.customerId = customerId;
        this.verifyUserName = verifyUserName;
        this.status = status;
        this.splitCount = splitCount;
        this.content = content;
        this.customerTitle = customerTitle;
        this.customerStr = customerStr;
        this.mobilePhone1 = mobilePhone1;
        this.mobilePhone2 = mobilePhone2;
        this.phone = phone;
        this.phoneExt = phoneExt;
        this.fax = fax;
        this.faxExt = faxExt;
        this.defaultPhoneType = defaultPhoneType;
        this.defaultPhone = defaultPhone;
    }

    public RecordDetail() {
        super();
    }

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

    
}
