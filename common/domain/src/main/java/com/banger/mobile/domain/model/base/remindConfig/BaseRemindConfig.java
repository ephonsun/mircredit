/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :消息提醒配置
 * Author     :yujh
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.domain.model.base.remindConfig;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: RemindConfig.java,v 0.1 Jun 1, 2012 12:45:48 PM Administrator Exp $
 */
public class BaseRemindConfig extends BaseObject implements Serializable{

    private static final long serialVersionUID = -1147746438802825565L;
    
    private     Integer             remindConfigId;         //主键id
    private     Integer             userId;                 //用户id
    private     Integer             isCallRemind;           //未接来电是否提醒
    private     Integer             isMessageRemind;        //未读留言是否提醒
    private     Integer             isVisitRemind;          //未读座谈
    private     Integer             isOverDueTask;          //过期任务
    private     Integer             isAssignTask;           //待分配任务
    private     Integer             isSmsRemind;            //未读短信
    private     Integer             isSendSms;              //已发短信
    private     Integer             isSendMms;              //已发彩信
    private     Integer             isVerifySms;            //待审核短信
    private     Integer             isSendBackSms;          //被退回短信
    private     Integer             isVerifyMms;            //待审核彩信
    private     Integer             isSendBackMms;          //被退回彩信
    private     Date                createDate;
    private     Date                updDate;
    public Integer getRemindConfigId() {
        return remindConfigId;
    }
    public void setRemindConfigId(Integer remindConfigId) {
        this.remindConfigId = remindConfigId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getIsCallRemind() {
        return isCallRemind;
    }
    public void setIsCallRemind(Integer isCallRemind) {
        this.isCallRemind = isCallRemind;
    }
    public Integer getIsMessageRemind() {
        return isMessageRemind;
    }
    public void setIsMessageRemind(Integer isMessageRemind) {
        this.isMessageRemind = isMessageRemind;
    }
    public Integer getIsVisitRemind() {
        return isVisitRemind;
    }
    public void setIsVisitRemind(Integer isVisitRemind) {
        this.isVisitRemind = isVisitRemind;
    }
    public Integer getIsOverDueTask() {
        return isOverDueTask;
    }
    public void setIsOverDueTask(Integer isOverDueTask) {
        this.isOverDueTask = isOverDueTask;
    }
    public Integer getIsAssignTask() {
        return isAssignTask;
    }
    public void setIsAssignTask(Integer isAssignTask) {
        this.isAssignTask = isAssignTask;
    }
    public Integer getIsSmsRemind() {
        return isSmsRemind;
    }
    public void setIsSmsRemind(Integer isSmsRemind) {
        this.isSmsRemind = isSmsRemind;
    }
    public Integer getIsSendSms() {
        return isSendSms;
    }
    public void setIsSendSms(Integer isSendSms) {
        this.isSendSms = isSendSms;
    }
    public Integer getIsSendMms() {
        return isSendMms;
    }
    public void setIsSendMms(Integer isSendMms) {
        this.isSendMms = isSendMms;
    }
    public Integer getIsVerifySms() {
        return isVerifySms;
    }
    public void setIsVerifySms(Integer isVerifySms) {
        this.isVerifySms = isVerifySms;
    }
    public Integer getIsSendBackSms() {
        return isSendBackSms;
    }
    public void setIsSendBackSms(Integer isSendBackSms) {
        this.isSendBackSms = isSendBackSms;
    }
    public Integer getIsVerifyMms() {
        return isVerifyMms;
    }
    public void setIsVerifyMms(Integer isVerifyMms) {
        this.isVerifyMms = isVerifyMms;
    }
    public Integer getIsSendBackMms() {
        return isSendBackMms;
    }
    public void setIsSendBackMms(Integer isSendBackMms) {
        this.isSendBackMms = isSendBackMms;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getUpdDate() {
        return updDate;
    }
    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseRemindConfig)) {
            return false;
        }
        BaseRemindConfig rhs = (BaseRemindConfig) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.userId, rhs.userId).append(this.isSendMms, rhs.isSendMms).append(
                this.isAssignTask, rhs.isAssignTask).append(this.isCallRemind, rhs.isCallRemind)
            .append(this.isMessageRemind, rhs.isMessageRemind).append(this.remindConfigId,
                rhs.remindConfigId).append(this.isSendSms, rhs.isSendSms).append(
                this.isSendBackSms, rhs.isSendBackSms).append(this.isVerifySms, rhs.isVerifySms)
            .append(this.updDate, rhs.updDate).append(this.isSendBackMms, rhs.isSendBackMms)
            .append(this.isVerifyMms, rhs.isVerifyMms).append(this.isSmsRemind, rhs.isSmsRemind)
            .append(this.createDate, rhs.createDate).append(this.isVisitRemind, rhs.isVisitRemind)
            .append(this.isOverDueTask, rhs.isOverDueTask).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1958303873, -1609296203).appendSuper(super.hashCode()).append(
            this.userId).append(this.isSendMms).append(this.isAssignTask).append(this.isCallRemind)
            .append(this.isMessageRemind).append(this.remindConfigId).append(this.isSendSms)
            .append(this.isSendBackSms).append(this.isVerifySms).append(this.updDate).append(
                this.isSendBackMms).append(this.isVerifyMms).append(this.isSmsRemind).append(
                this.createDate).append(this.isVisitRemind).append(this.isOverDueTask).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("isSendBackSms", this.isSendBackSms).append(
            "startRow", this.getStartRow()).append("isMessageRemind", this.isMessageRemind).append(
            "isAssignTask", this.isAssignTask).append("isVerifyMms", this.isVerifyMms).append(
            "isVisitRemind", this.isVisitRemind).append("isSendMms", this.isSendMms).append(
            "endRow", this.getEndRow()).append("updDate", this.updDate).append("isVerifySms",
            this.isVerifySms).append("isOverDueTask", this.isOverDueTask).append("isSmsRemind",
            this.isSmsRemind).append("isCallRemind", this.isCallRemind).append("isSendSms",
            this.isSendSms).append("isSendBackMms", this.isSendBackMms).append("remindConfigId",
            this.remindConfigId).append("createDate", this.createDate)
            .append("userId", this.userId).toString();
    }
        
}
