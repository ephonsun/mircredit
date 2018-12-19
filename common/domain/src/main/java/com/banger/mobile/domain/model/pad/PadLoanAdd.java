/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yuanme
 * Create Date:2012-11-22
 */
package com.banger.mobile.domain.model.pad;

import com.banger.mobile.domain.model.loan.LnLoanCoBorrower;
import com.banger.mobile.domain.model.loan.LnLoanGuarantor;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangfp
 * @version $Id: PadLoanDetail.java,v 0.1 2012-11-22 下午1:03:55 Administrator Exp $
 */
public class PadLoanAdd implements Serializable {
    		
    /**
	 * 
	 */
	private static final long serialVersionUID = -3901378724734890078L;
	
	private Integer loanId;   //贷款ID
    //private Integer oldCustomerId; //老客户id(用于申请贷款时，新建客户)
    private Integer customerId;  //客户ID
    private String loanTypeId;   //贷款类型
    private String loanSubTypeId;  //贷款子类型
    //private String loanMoney;  //貸款金額
    private Integer loanStatusId; //贷款状态
    private List<LnLoanCoBorrower> coBorrowerList; //共同借贷人
    private List<LnLoanGuarantor> guarantorList; //担保人

    public Integer getLoanStatusId() {
        return loanStatusId;
    }

    public void setLoanStatusId(Integer loanStatusId) {
        this.loanStatusId = loanStatusId;
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

    public String getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(String loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public String getLoanSubTypeId() {
        return loanSubTypeId;
    }

    public void setLoanSubTypeId(String loanSubTypeId) {
        this.loanSubTypeId = loanSubTypeId;
    }

    public List<LnLoanCoBorrower> getCoBorrowerList() {
        return coBorrowerList;
    }

    public void setCoBorrowerList(List<LnLoanCoBorrower> coBorrowerList) {
        this.coBorrowerList = coBorrowerList;
    }

    public List<LnLoanGuarantor> getGuarantorList() {
        return guarantorList;
    }

    public void setGuarantorList(List<LnLoanGuarantor> guarantorList) {
        this.guarantorList = guarantorList;
    }
}
