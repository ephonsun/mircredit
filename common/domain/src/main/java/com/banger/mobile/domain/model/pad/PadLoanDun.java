/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :催收记录资料
 * Author     :zhangfp
 * Create Date:2013-3-15
 */
package com.banger.mobile.domain.model.pad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zhangfp
 * @version $Id: PadLoanDun.java v 0.1 ${} 上午10:00 Administrator Exp $
 */
public class PadLoanDun implements Serializable {
    private static final long serialVersionUID = 6366431025572907022L;

    private Integer dunType; //催收方式 1短信,2电话,3上门，4彩信
    private Date dunDate;  //催收日期
    private List<PadLoanData> dunDataList; //单笔催收资料
    private String dunRemark;
    private Integer isException;

    public Integer getIsException() {
        return isException;
    }

    public void setIsException(Integer exception) {
        isException = exception;
    }

    public Integer getDunType() {
        return dunType;
    }

    public void setDunType(Integer dunType) {
        this.dunType = dunType;
    }

    public Date getDunDate() {
        return dunDate;
    }

    public void setDunDate(Date dunDate) {
        this.dunDate = dunDate;
    }

    public List<PadLoanData> getDunDataList() {
        return dunDataList;
    }

    public void setDunDataList(List<PadLoanData> dunDataList) {
        this.dunDataList = dunDataList;
    }

    public String getDunRemark() {
        return dunRemark;
    }

    public void setDunRemark(String dunRemark) {
        this.dunRemark = dunRemark;
    }
}
