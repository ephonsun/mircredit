/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :财经要点报表统计Bean
 * Author     :liyb
 * Create Date:2012-12-10
 */
package com.banger.mobile.domain.model.report;

/**
 * @author liyb
 * @version $Id: FinanceReportBean.java,v 0.1 2012-12-10 下午01:17:48 liyb Exp $
 */
public class FinanceReportBean {

    private Integer userId;               //客户经理ID
    private String  userName;             //客户经理

    private Integer columnId;             //栏目ID
    private String  columnName;           //栏目名称
    private Integer articleCount;         //文章总数
    private Integer mastReadArticleCount; //文章总必读数
    private Integer readCount;            //已读总数
    private Integer mastReadCount;        //其中已读中必读数
    private Integer unReadCount;          //未读总数
    private Integer mastUnReadCount;      //其中未读中必读数
    private String  readRate;             //阅读率
    private String  mastReadRate;         //其中必读阅读率
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
    public String getColumnName() {
        return columnName;
    }
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    public Integer getArticleCount() {
        return articleCount;
    }
    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }
    public Integer getMastReadArticleCount() {
        return mastReadArticleCount;
    }
    public void setMastReadArticleCount(Integer mastReadArticleCount) {
        this.mastReadArticleCount = mastReadArticleCount;
    }
    public Integer getReadCount() {
        return readCount;
    }
    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }
    public Integer getMastReadCount() {
        return mastReadCount;
    }
    public void setMastReadCount(Integer mastReadCount) {
        this.mastReadCount = mastReadCount;
    }
    public Integer getUnReadCount() {
        return unReadCount;
    }
    public void setUnReadCount(Integer unReadCount) {
        this.unReadCount = unReadCount;
    }
    public Integer getMastUnReadCount() {
        return mastUnReadCount;
    }
    public void setMastUnReadCount(Integer mastUnReadCount) {
        this.mastUnReadCount = mastUnReadCount;
    }
    public String getReadRate() {
        return readRate;
    }
    public void setReadRate(String readRate) {
        this.readRate = readRate;
    }
    public String getMastReadRate() {
        return mastReadRate;
    }
    public void setMastReadRate(String mastReadRate) {
        this.mastReadRate = mastReadRate;
    }
    
}
