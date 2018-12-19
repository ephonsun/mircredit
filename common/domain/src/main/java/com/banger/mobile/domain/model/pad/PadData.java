/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-22
 */
package com.banger.mobile.domain.model.pad;

/**
 * @author yuanme
 * @version $Id: PadData.java,v 0.1 2012-11-22 下午1:40:33 Administrator Exp $
 */
public class PadData {
    private Integer loanId;
    private Integer customerId;
    private Integer audioCount;
    private Integer photoCount;
    private Integer videoCount;
    private Integer formCount;

    public Integer getFormCount() {
        return formCount;
    }

    public void setFormCount(Integer formCount) {
        this.formCount = formCount;
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

    public Integer getAudioCount() {
        return audioCount;
    }

    public void setAudioCount(Integer audioCount) {
        this.audioCount = audioCount;
    }

    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    public Integer getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
    }
}
