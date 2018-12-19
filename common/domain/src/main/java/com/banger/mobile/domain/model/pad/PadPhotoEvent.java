/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-22
 */
package com.banger.mobile.domain.model.pad;

import java.util.List;

/**
 * @author yuanme
 * @version $Id: PadPhoto.java,v 0.1 2012-11-22 下午5:45:36 Administrator Exp $
 */
public class PadPhotoEvent {
    private Integer               loanId;
    private Integer               eventId;
    private Integer               photoCount;
    private String                eventName;
    private List<PadPhotoSubType> subType;

    public List<PadPhotoSubType> getSubType() {
        return subType;
    }

    public void setSubType(List<PadPhotoSubType> subType) {
        this.subType = subType;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
