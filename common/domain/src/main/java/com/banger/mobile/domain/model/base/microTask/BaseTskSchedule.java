package com.banger.mobile.domain.model.base.microTask;


import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;


public class BaseTskSchedule extends BaseObject implements java.io.Serializable {

    private static final long serialVersionUID = -7728722111521879855L;
    // Fields
    private Integer scheduleId;
    private Integer customerId;
    private Date contactDate;
    private Integer contactType;
    private String remark;
    private Date addDate;
    private Integer commProgressId;
    private Integer status;
    private Date createDate;
    private Date updateDate;
    private Integer createUser;
    private Integer updateUser;
    private Integer userId;
    private Integer recordInfoId;
    private Integer isOutDate;

    public Integer getIsOutDate() {
        return isOutDate;
    }

    public void setIsOutDate(Integer outDate) {
        isOutDate = outDate;
    }

    public Integer getRecordInfoId() {
        return recordInfoId;
    }

    public void setRecordInfoId(Integer recordInfoId) {
        this.recordInfoId = recordInfoId;
    }
// Constructors

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** default constructor */
    public BaseTskSchedule() {
    }

    /** minimal constructor */
    public BaseTskSchedule(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    /** full constructor */
    public BaseTskSchedule(Integer scheduleId, Integer customerId,
            Date contactDate, Integer contactType, String remark,
            Date addDate, Integer commProgressId, Integer status,
            Date createDate, Date updateDate, Integer createUser,
            Integer updateUser) {
        this.scheduleId = scheduleId;
        this.customerId = customerId;
        this.contactDate = contactDate;
        this.contactType = contactType;
        this.remark = remark;
        this.addDate = addDate;
        this.commProgressId = commProgressId;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    // Property accessors
    public Integer getScheduleId() {
        return this.scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Date getContactDate() {
        return this.contactDate;
    }

    public void setContactDate(Date contactDate) {
        this.contactDate = contactDate;
    }

    public Integer getContactType() {
        return this.contactType;
    }

    public void setContactType(Integer contactType) {
        this.contactType = contactType;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getAddDate() {
        return this.addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Integer getCommProgressId() {
        return this.commProgressId;
    }

    public void setCommProgressId(Integer commProgressId) {
        this.commProgressId = commProgressId;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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