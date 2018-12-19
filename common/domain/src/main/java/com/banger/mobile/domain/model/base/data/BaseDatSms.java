package com.banger.mobile.domain.model.base.data;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * DatSms entity. @author MyEclipse Persistence Tools
 */

public class BaseDatSms extends BaseObject{

    // Fields    

    /**
	 * 
	 */
	private static final long serialVersionUID = -2463089062298590739L;
	private Integer smsId;
    private Integer customerDataId;
    private Integer smsType;
    private String  smsContent;
    private String  remark;
    private Date    sendDate;
    private Integer sendUserId;
    private Date    createDate;
    private Date    updateDate;
    private Integer createUser;
    private Integer updateUser;

    // Constructors

    /** default constructor */
    public BaseDatSms() {
    }

    /** minimal constructor */
    public BaseDatSms(Integer smsId) {
        this.smsId = smsId;
    }

    /** full constructor */
    public BaseDatSms(Integer smsId, Integer customerDataId, Integer smsType, String smsContent,
                  String remark, Date sendDate, Integer sendUserId, Date createDate,
                  Date updateDate, Integer createUser, Integer updateUser) {
        this.smsId = smsId;
        this.customerDataId = customerDataId;
        this.smsType = smsType;
        this.smsContent = smsContent;
        this.remark = remark;
        this.sendDate = sendDate;
        this.sendUserId = sendUserId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    // Property accessors

    public Integer getSmsId() {
        return this.smsId;
    }

    public void setSmsId(Integer smsId) {
        this.smsId = smsId;
    }

    public Integer getCustomerDataId() {
        return this.customerDataId;
    }

    public void setCustomerDataId(Integer customerDataId) {
        this.customerDataId = customerDataId;
    }

    public Integer getSmsType() {
        return this.smsType;
    }

    public void setSmsType(Integer smsType) {
        this.smsType = smsType;
    }

    public String getSmsContent() {
        return this.smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
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