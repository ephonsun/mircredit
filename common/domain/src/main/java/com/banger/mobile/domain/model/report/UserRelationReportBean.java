/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :财经要点用户/财经报表统计
 * Author     :liyb
 * Create Date:2012-12-10
 */
package com.banger.mobile.domain.model.report;

/**
 * @author liyb
 * @version $Id: UserRelationReportBean.java,v 0.1 2012-12-10 下午02:23:58 liyb Exp $
 */
public class UserRelationReportBean {

    private Integer userId;   //客户经理ID
    private String  userName; //客户经理
    private Integer columnId; //栏目ID
    private Integer isRead;   //已读
    private Integer mastRead; //必读

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public void setColumnId(Integer columnId) {
        this.columnId = columnId;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getMastRead() {
        return mastRead;
    }

    public void setMastRead(Integer mastRead) {
        this.mastRead = mastRead;
    }
}
