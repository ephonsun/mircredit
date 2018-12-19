/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款信息小页卡
 * Author     :zhangfp
 * Create Date:2013-3-20
 */
package com.banger.mobile.domain.model.pad.smallPageCard;

import com.banger.mobile.domain.model.loan.LnLoan;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhangfp
 * @version $Id: PadLoanInfoCard.java v 0.1 ${} 下午2:26 Administrator Exp $
 */
public class PadLoanInfoCard {
    private String createDate; //提交时间(精确到分)
    private String loanMoney; //申请金额
    private String loanStatus;  //贷款状态
    private String loanType;  //贷款类型
    private String applyUserName;  //申请人员
    private Integer loanId;  //贷款ID
    private Integer loanInfoId;//申请表主键

    public Integer getLoanInfoId() {
        return loanInfoId;
    }

    public void setLoanInfoId(Integer loanInfoId) {
        this.loanInfoId = loanInfoId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(String loanMoney) {
        this.loanMoney = loanMoney;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }
}
