/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-6-11
 */
package com.banger.mobile.domain.model.base.unreadMessage;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yangyang
 * @version $Id: BaseCrmUnreadMessage.java,v 0.1 2012-6-11 下午3:38:39 yangyong Exp $
 */
public class BaseCrmUnreadMessage extends BaseObject implements Comparable, java.io.Serializable {

    private static final long serialVersionUID = 2425893216145559094L;

    private Integer unreadMessageId;        //主键ID
    private Integer userId;                 //用户ID
    private Integer unreadMessage;          //未读留言
    private Integer missingCall;            //未接电话    
    public Integer getUnreadMessageId() {
        return unreadMessageId;
    }
    public void setUnreadMessageId(Integer unreadMessageId) {
        this.unreadMessageId = unreadMessageId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getUnreadMessage() {
        return unreadMessage;
    }
    public void setUnreadMessage(Integer unreadMessage) {
        this.unreadMessage = unreadMessage;
    }
    public Integer getMissingCall() {
        return missingCall;
    }
    public void setMissingCall(Integer missingCall) {
        this.missingCall = missingCall;
    }
    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        BaseCrmUnreadMessage myClass = (BaseCrmUnreadMessage) object;
        return new CompareToBuilder().append(this.unreadMessage, myClass.unreadMessage)
            .append(this.missingCall, myClass.missingCall).append(this.userId, myClass.userId)
            .append(this.unreadMessageId, myClass.unreadMessageId).toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseCrmUnreadMessage)) {
            return false;
        }
        BaseCrmUnreadMessage rhs = (BaseCrmUnreadMessage) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.unreadMessage, rhs.unreadMessage)
            .append(this.missingCall, rhs.missingCall).append(this.userId, rhs.userId)
            .append(this.unreadMessageId, rhs.unreadMessageId).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1257269997, -720720589).appendSuper(super.hashCode())
            .append(this.unreadMessage).append(this.missingCall).append(this.userId)
            .append(this.unreadMessageId).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("userId", this.userId)
            .append("endRow", this.getEndRow()).append("unreadMessageId", this.unreadMessageId)
            .append("missingCall", this.missingCall).append("unreadMessage", this.unreadMessage)
            .append("startRow", this.getStartRow()).toString();
    }
   
    
}
