package com.banger.mobile.domain.model.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yuanme
 * Date: 13-2-19
 * Time: 下午1:34
 * To change this template use File | Settings | File Templates.
 */
public class LoanData implements Serializable {
    private Integer dataType;//类型 1照片 2录音 3视频 4表单
    private Integer photoTypeId;//1	头像照片 2	身份证正面 3	身份证背面
    private String dataName;//资料名称
    private Date uploadDate;//上传时间 提交时间
    private Integer uploadUserId;
    private String uploadUserName;
    private String uploadUserAccount;
    private String fileName;
    private String filePath;
    private Integer loanId;//贷款id
    private Integer customerId;//客户id
    private String customerName;
    private Integer eventId;//事件id
    private Integer rowNum;
    private String datUuid;
    private Integer isDel;

    private String remark;
    private Integer dataId;
    private Integer recordLength;
    private Date recordDate;//创建时间

    private Integer fileId;

    public String getUploadUserAccount() {
        return uploadUserAccount;
    }

    public void setUploadUserAccount(String uploadUserAccount) {
        this.uploadUserAccount = uploadUserAccount;
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

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer photoId) {
        this.dataId = photoId;
    }

    public String getDatUuid() {
        return datUuid;
    }

    public void setDatUuid(String datUuid) {
        this.datUuid = datUuid;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer del) {
        isDel = del;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Integer getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(Integer uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    public String getUploadUserName() {
        return uploadUserName;
    }

    public void setUploadUserName(String uploadUserName) {
        this.uploadUserName = uploadUserName;
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

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getPhotoTypeId() {
        return photoTypeId;
    }

    public void setPhotoTypeId(Integer photoTypeId) {
        this.photoTypeId = photoTypeId;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Integer getRecordLength() {
        return recordLength;
    }

    public void setRecordLength(Integer recordLength) {
        this.recordLength = recordLength;
    }

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
    
    
}
