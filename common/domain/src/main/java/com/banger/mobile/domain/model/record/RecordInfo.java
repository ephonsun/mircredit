/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :RecRecordInfo 录音记录实体类
 * Author     :zhangxiang
 * Version    :1.0
 * Create Date:May 2, 2012
 */
package com.banger.mobile.domain.model.record;

import java.util.Date;

import com.banger.mobile.domain.model.base.record.BaseRecordInfo;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author huangkai
 * @version $Id: RecRecordInfo.java,v 0.1 May 2, 2012 1:53:05 PM huangkai Exp $
 */
public class RecordInfo extends BaseRecordInfo {
	
    private String mobilePhone1;
    private String mobilePhone2;
    private String phone;
    private String phoneExt;
    private String fax;
    private String faxExt;
    private Integer defaultPhoneType;
    
    private String fileName;     			//文件名称
    private String filePath;     			//文件路径
    private Long fileSize;     			//文件大小
    private String fileMd5;     			//文件md5
    private Date uploadDate;     			//文件上传时间
    
    private Integer taskId;					//通话任务完成时使用
    private Integer loanId;                 // web端电话催收时使用
    private Integer exceptionDun;           // web端电话催收时使用
    
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

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	
	
	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
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

	private static final long serialVersionUID = -3115050577941067322L;

    public RecordInfo() {

    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1676452757, 1734194239).appendSuper(super.hashCode())
            .toHashCode();
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getExceptionDun() {
        return exceptionDun;
    }

    public void setExceptionDun(Integer exceptionDun) {
        this.exceptionDun = exceptionDun;
    }
}
