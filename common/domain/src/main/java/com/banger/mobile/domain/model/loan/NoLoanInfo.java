package com.banger.mobile.domain.model.loan;

import java.util.Date;

/**
 * @author zhangfp
 * @version $Id: NoLoanInfo v 0.1 ${} 下午2:20 zhangfp Exp $
 */
public class NoLoanInfo {

    private Integer loanId;
    private String customerName;
    private String idCard;
    private Date genDate;
    private Integer loanTypeId;

    public Integer getLoanTypeId() {
        return loanTypeId;
    }

    public void setLoanTypeId(Integer loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public Date getGenDate() {
        return genDate;
    }

    public void setGenDate(Date genDate) {
        this.genDate = genDate;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
