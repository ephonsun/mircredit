package com.banger.mobile.domain.model.record;

import java.io.Serializable;
import java.util.Date;

public class RecordQueryBean implements Serializable{
    private static final long serialVersionUID = -5875125599076547493L;
    private Integer recordInfoId;   //记录id
    private Integer customerTypeId; //客户分类
    private String customer;    //客户姓名电话等
    private Date startDate;     //起始时间
    private Date endDate;       //结束时间
    private String callType;       //联系类型
    private Integer bizType;        //业务类型
    private Integer recordType;     //录音(有，无，时长)
    private String remark;      //备注
    private Integer userId;         //操作者
    private Integer isCanceled;     //是否有效(座谈搜索)
    private String content;     //内容
    private String status;         //彩信状态
    private String mmsTitle;    //彩信标题
    private String userName;    //操作者
    private Integer callTypeId;     //短信彩信记录查询类型
    private Integer commProgressId; //沟通进度
    public Integer getRecordInfoId() {
        return recordInfoId;
    }
    public void setRecordInfoId(Integer recordInfoId) {
        this.recordInfoId = recordInfoId;
    }
    public Integer getCustomerTypeId() {
        return customerTypeId;
    }
    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
    }
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
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
    public String getCallType() {
        return callType;
    }
    public void setCallType(String callType) {
        this.callType = callType;
    }
    public Integer getBizType() {
        return bizType;
    }
    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }
    public Integer getRecordType() {
        return recordType;
    }
    public void setRecordType(Integer recordType) {
        this.recordType = recordType;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getIsCanceled() {
        return isCanceled;
    }
    public void setIsCanceled(Integer isCanceled) {
        this.isCanceled = isCanceled;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMmsTitle() {
        return mmsTitle;
    }
    public void setMmsTitle(String mmsTitle) {
        this.mmsTitle = mmsTitle;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Integer getCallTypeId() {
        return callTypeId;
    }
    public void setCallTypeId(Integer callTypeId) {
        this.callTypeId = callTypeId;
    }
    public Integer getCommProgressId() {
        return commProgressId;
    }
    public void setCommProgressId(Integer commProgressId) {
        this.commProgressId = commProgressId;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bizType == null) ? 0 : bizType.hashCode());
        result = prime * result + ((callType == null) ? 0 : callType.hashCode());
        result = prime * result + ((callTypeId == null) ? 0 : callTypeId.hashCode());
        result = prime * result + ((commProgressId == null) ? 0 : commProgressId.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((customerTypeId == null) ? 0 : customerTypeId.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((isCanceled == null) ? 0 : isCanceled.hashCode());
        result = prime * result + ((mmsTitle == null) ? 0 : mmsTitle.hashCode());
        result = prime * result + ((recordInfoId == null) ? 0 : recordInfoId.hashCode());
        result = prime * result + ((recordType == null) ? 0 : recordType.hashCode());
        result = prime * result + ((remark == null) ? 0 : remark.hashCode());
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
        final RecordQueryBean other = (RecordQueryBean) obj;
        if (bizType == null) {
            if (other.bizType != null)
                return false;
        } else if (!bizType.equals(other.bizType))
            return false;
        if (callType == null) {
            if (other.callType != null)
                return false;
        } else if (!callType.equals(other.callType))
            return false;
        if (callTypeId == null) {
            if (other.callTypeId != null)
                return false;
        } else if (!callTypeId.equals(other.callTypeId))
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
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (customerTypeId == null) {
            if (other.customerTypeId != null)
                return false;
        } else if (!customerTypeId.equals(other.customerTypeId))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (isCanceled == null) {
            if (other.isCanceled != null)
                return false;
        } else if (!isCanceled.equals(other.isCanceled))
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
        if (recordType == null) {
            if (other.recordType != null)
                return false;
        } else if (!recordType.equals(other.recordType))
            return false;
        if (remark == null) {
            if (other.remark != null)
                return false;
        } else if (!remark.equals(other.remark))
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
        return true;
    }
    public RecordQueryBean(Integer recordInfoId, Integer customerTypeId, String customer,
                           Date startDate, Date endDate, String callType, Integer bizType,
                           Integer recordType, String remark, Integer userId, Integer isCanceled,
                           String content, String status, String mmsTitle, String userName,
                           Integer callTypeId, Integer commProgressId) {
        super();
        this.recordInfoId = recordInfoId;
        this.customerTypeId = customerTypeId;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.callType = callType;
        this.bizType = bizType;
        this.recordType = recordType;
        this.remark = remark;
        this.userId = userId;
        this.isCanceled = isCanceled;
        this.content = content;
        this.status = status;
        this.mmsTitle = mmsTitle;
        this.userName = userName;
        this.callTypeId = callTypeId;
        this.commProgressId = commProgressId;
    }
    public RecordQueryBean() {
        super();
    }
    
    
}
