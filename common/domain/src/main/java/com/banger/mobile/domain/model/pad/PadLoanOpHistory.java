/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款历史操作
 * Author     :zhangfp
 * Create Date:2013-3-15
 */
package com.banger.mobile.domain.model.pad;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 * @version $Id: PadLoanOpHistory.java v 0.1 ${} 上午10:08 Administrator Exp $
 */
public class PadLoanOpHistory implements Serializable {
    private static final long serialVersionUID = 1848491752497861121L;

    private Date opHistoryDate;  //操作时间
    private String account;      //用户账号
    private String userName;     //用户姓名
    private String statusChangeBefore;  //状态变更前
    private String statusChangeAfter;   //状态变更后
    private String content;             //操作内容
    private String remark;       //备注

    public Date getOpHistoryDate() {
        return opHistoryDate;
    }

    public void setOpHistoryDate(Date opHistoryDate) {
        this.opHistoryDate = opHistoryDate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatusChangeBefore() {
        return statusChangeBefore;
    }

    public void setStatusChangeBefore(String statusChangeBefore) {
        this.statusChangeBefore = statusChangeBefore;
    }

    public String getStatusChangeAfter() {
        return statusChangeAfter;
    }

    public void setStatusChangeAfter(String statusChangeAfter) {
        this.statusChangeAfter = statusChangeAfter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
