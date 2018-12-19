/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :zhangfp
 * Create Date:2013-6-4
 */
package com.banger.mobile.domain.model.pad;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangfp
 * @version $Id: PadApprovalLoan v 0.1 ${} 下午3:59 zhangfp Exp $
 */
public class PadApprovalLoan implements Serializable {

    private String customerName; //客户姓名
    private String phone; //联系电话
    private String idCard; //身份证
    private Integer loanType; //贷款类型
    private Date approvalStart;  //提交审批开始时间
    private Date approvalEnd; //提交审批结束时间
    private Date submitStart;  //贷款提交开始时间
    private Date submitEnd;    //贷款提交结束时间

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public Date getApprovalStart() {
        return approvalStart;
    }

    public void setApprovalStart(Date approvalStart) {
        this.approvalStart = approvalStart;
    }

    public Date getApprovalEnd() {
        return approvalEnd;
    }

    public void setApprovalEnd(Date approvalEnd) {
        this.approvalEnd = approvalEnd;
    }

    public Date getSubmitStart() {
        return submitStart;
    }

    public void setSubmitStart(Date submitStart) {
        this.submitStart = submitStart;
    }

    public Date getSubmitEnd() {
        return submitEnd;
    }

    public void setSubmitEnd(Date submitEnd) {
        this.submitEnd = submitEnd;
    }
}
