package com.banger.mobile.domain.model.customer;


import org.apache.commons.lang.builder.HashCodeBuilder;

import com.banger.mobile.domain.model.base.customer.BaseCrmCustomer;
/**
 * CrmCustomer entity. @author MyEclipse Persistence Tools
 */

public class CrmCustomerEvaBean extends BaseCrmCustomer {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2662494650172162955L;
	
	private String  templateNo;
    private String  testDate;
    private String  testResult;
    private String  testremark;
    private Integer testResultId;
    private Integer isShare;        //是否是共享客户
    
    public Integer getIsShare() {
        return isShare;
    }
    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }
    public String getTemplateNo() {
        return templateNo;
    }
    public void setTemplateNo(String templateNo) {
        this.templateNo = templateNo;
    }
    public String getTestDate() {
        return testDate;
    }
    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }
    public String getTestResult() {
        return testResult;
    }
    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }
    
    public String getTestremark() {
        return testremark;
    }
    public void setTestremark(String testremark) {
        this.testremark = testremark;
    }
    public Integer getTestResultId() {
        return testResultId;
    }
    public void setTestResultId(Integer testResultId) {
        this.testResultId = testResultId;
    }
    


}