/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-7-19
 */
package com.banger.mobile.domain.model.pad;

/**
 * @author yuanme
 * @version TestResult.java,v 0.1 2012-7-19 上午11:17:47
 */
public class TestResult {
    private String testResult;
    private boolean isOk;
    private Integer testResultId;
    
    public String getTestResult() {
        return testResult;
    }
    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }
    public boolean isOk() {
        return isOk;
    }
    public void setOk(boolean isOk) {
        this.isOk = isOk;
    }
    public Integer getTestResultId() {
        return testResultId;
    }
    public void setTestResultId(Integer testResultId) {
        this.testResultId = testResultId;
    }
}
