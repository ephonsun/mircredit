/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :通知-用户-Domain
 * Author     :QianJie
 * Create Date:Dec 18, 2012
 */
package com.banger.mobile.domain.model.base.microProduct;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author QianJie
 * @version $Id: BasePdtNoticeUser.java,v 0.1 Dec 18, 2012 3:19:33 PM QianJie Exp $
 */
public class BasePdtNoticeUser extends BaseObject implements Serializable {

    private static final long serialVersionUID = 8244336749645742780L;

    private Integer           noticeUserId;                           //主键
    private Integer           noticeId;                               //通知ID
    private Integer           userId;                                 //用户ID
    private Integer           isRead;                                 //是否已读
    private Date              createDate;                             //创建时间
    private Date              updateDate;                             //更新时间
    private Integer           createUser;                             //创建用户
    private Integer           updateUser;                             //更新用户
    
    
    public BasePdtNoticeUser() {
        super();
    }
    
    
    public BasePdtNoticeUser(Integer noticeUserId, Integer noticeId, Integer userId,
                             Integer isRead, Date createDate, Date updateDate, Integer createUser,
                             Integer updateUser) {
        super();
        this.noticeUserId = noticeUserId;
        this.noticeId = noticeId;
        this.userId = userId;
        this.isRead = isRead;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }


    public Integer getNoticeUserId() {
        return noticeUserId;
    }
    public void setNoticeUserId(Integer noticeUserId) {
        this.noticeUserId = noticeUserId;
    }
    public Integer getNoticeId() {
        return noticeId;
    }
    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getIsRead() {
        return isRead;
    }
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public Integer getCreateUser() {
        return createUser;
    }
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }
    public Integer getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }


    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BasePdtNoticeUser)) {
            return false;
        }
        BasePdtNoticeUser rhs = (BasePdtNoticeUser) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.noticeId,
            rhs.noticeId).append(this.userId, rhs.userId).append(this.isRead, rhs.isRead).append(
            this.createDate, rhs.createDate).append(this.createUser, rhs.createUser).append(
            this.updateDate, rhs.updateDate).append(this.noticeUserId, rhs.noticeUserId).append(
            this.updateUser, rhs.updateUser).isEquals();
    }


    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-653761127, -2143083497).appendSuper(super.hashCode()).append(
            this.noticeId).append(this.userId).append(this.isRead).append(this.createDate).append(
            this.createUser).append(this.updateDate).append(this.noticeUserId).append(
            this.updateUser).toHashCode();
    }


    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("isRead", this.isRead).append("startRow",
            this.getStartRow()).append("updateDate", this.updateDate).append("noticeUserId",
            this.noticeUserId).append("endRow", this.getEndRow()).append("updateUser",
            this.updateUser).append("createUser", this.createUser).append("createDate",
            this.createDate).append("userId", this.userId).append("noticeId", this.noticeId)
            .toString();
    }
    
    
}
