package com.banger.mobile.domain.model.base.data;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * DatMms entity. @author MyEclipse Persistence Tools
 */

public class BaseDatMms extends BaseObject {

    // Fields    

    /**
	 * 
	 */
	private static final long serialVersionUID = 4170584149827453127L;
	
	private Integer mmsId;
    private Integer customerDataId;
    private Integer mmsType;
    private String  mmsTitle;
    private String  mmsContent;
    private String  remark;
    private Date    sendDate;
    private Integer sendUserId;
    private Date    createDate;
    private Date    updateDate;
    private Integer createUser;
    private Integer updateUser;

    // Constructors

    /** default constructor */
    public BaseDatMms() {
    }

    /** minimal constructor */
    public BaseDatMms(Integer mmsId) {
        this.mmsId = mmsId;
    }

    /** full constructor */
    public BaseDatMms(Integer mmsId, Integer customerDataId, Integer mmsType, String mmsTitle,
                  String mmsContent, String remark, Date sendDate, Integer sendUserId,
                  Date createDate, Date updateDate, Integer createUser, Integer updateUser) {
        this.mmsId = mmsId;
        this.customerDataId = customerDataId;
        this.mmsType = mmsType;
        this.mmsTitle = mmsTitle;
        this.mmsContent = mmsContent;
        this.remark = remark;
        this.sendDate = sendDate;
        this.sendUserId = sendUserId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    // Property accessors

    public Integer getMmsId() {
        return this.mmsId;
    }

    public void setMmsId(Integer mmsId) {
        this.mmsId = mmsId;
    }

    public Integer getCustomerDataId() {
        return this.customerDataId;
    }

    public void setCustomerDataId(Integer customerDataId) {
        this.customerDataId = customerDataId;
    }

    public Integer getMmsType() {
        return this.mmsType;
    }

    public void setMmsType(Integer mmsType) {
        this.mmsType = mmsType;
    }

    public String getMmsTitle() {
        return this.mmsTitle;
    }

    public void setMmsTitle(String mmsTitle) {
        this.mmsTitle = mmsTitle;
    }

    public String getMmsContent() {
        return this.mmsContent;
    }

    public void setMmsContent(String mmsContent) {
        this.mmsContent = mmsContent;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getSendDate() {
        return this.sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Integer getSendUserId() {
        return this.sendUserId;
    }

    public void setSendUserId(Integer sendUserId) {
        this.sendUserId = sendUserId;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

}