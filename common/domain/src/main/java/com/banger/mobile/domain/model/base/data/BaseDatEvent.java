/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-12
 */
package com.banger.mobile.domain.model.base.data;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * @author yuanme
 * @version $Id: BaseDatEvent.java,v 0.1 2012-11-12 下午4:41:47 Administrator Exp $
 */
public class BaseDatEvent extends BaseObject {
    private static final long serialVersionUID = 8347939182338985381L;

    private Integer           eventId;
    private String            eventName;
    private Integer           sortno;
    private Date              createDate;
    private Date              updateDate;
    private Integer           createUser;
    private Integer           updateUser;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Integer getSortno() {
        return sortno;
    }

    public void setSortno(Integer sortno) {
        this.sortno = sortno;
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

}
