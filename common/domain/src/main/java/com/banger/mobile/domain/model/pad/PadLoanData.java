/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款资料
 * Author     :zhangfp
 * Create Date:2013-3-15
 */
package com.banger.mobile.domain.model.pad;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangfp
 * @version $Id: PadLoanData.java v 0.1 ${} 上午9:46 Administrator Exp $
 */
public class PadLoanData implements Serializable {
    private static final long serialVersionUID = -8354519513332871450L;

    private Integer customerDataId;
    private Integer dataType;//类型 1照片 2录音 3视频 4表单
    private Integer photoType;//1	头像照片 2	身份证正面 3	身份证背面
    private String dataName;//资料名称
    private Date submitDate;//上传时间 提交时间
    private Date createDate; //创建时间(拍照时间)
    private String userName;
    private String account;
    private String url;
    private Integer customerId;//客户id
    private Integer eventId;//事件id
    private String content;  //催收短信，给彩信和短信用的

    private Integer msgType;  //标记消息类型（短信或彩信）

    //需求后面加上的
    private String smsContent;  //短信内容
    private Integer smsType;    //短信类型 1:即时短信，2定时短信
    private String mmsTitle;    //彩信主题
    private String mmsContent;  //彩信内容  hmtl
    private Integer mmsType;    //彩信类型  1:即时彩信，2定时彩信

    private Integer fileId;
    private String filePath;  //文件路径
    private String fileName;  //文件名称
    private Integer rowNum;
    private Integer loanId;

    private String uuid;
    private Integer isDel;
    private Integer recordLength;

    private String remark;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer del) {
        isDel = del;
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

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
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

    public Integer getCustomerDataId() {
        return customerDataId;
    }

    public void setCustomerDataId(Integer customerDataId) {
        this.customerDataId = customerDataId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getPhotoType() {
        return photoType;
    }

    public void setPhotoType(Integer photoType) {
        this.photoType = photoType;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getRecordLength() {
        if (recordLength == null)
            return 0;
        return recordLength;
    }

    public void setRecordLength(Integer recordLength) {
        this.recordLength = recordLength;
    }
}
