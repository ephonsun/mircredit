package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.BaseObject;
import com.banger.mobile.domain.model.base.loan.BaseLnBalance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TmpLoanInfo extends BaseObject implements Serializable {

    private static final long serialVersionUID = 9120263536592324024L;

    private String acctNo;
    private String contNo;
    private String repaymentAccount;
    private String enterAccount;
    private String billNo;
    private String contNoCn;
    private Integer loanTerm;
    private String loanStartDate;
    private String loanEndDate;
    private BigDecimal contAmt;
    private Integer loanId;

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getContNo() {
        return contNo;
    }

    public void setContNo(String contNo) {
        this.contNo = contNo;
    }

    public String getRepaymentAccount() {
        return repaymentAccount;
    }

    public void setRepaymentAccount(String repaymentAccount) {
        this.repaymentAccount = repaymentAccount;
    }

    public String getEnterAccount() {
        return enterAccount;
    }

    public void setEnterAccount(String enterAccount) {
        this.enterAccount = enterAccount;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getContNoCn() {
        return contNoCn;
    }

    public void setContNoCn(String contNoCn) {
        this.contNoCn = contNoCn;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

    public String getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(String loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public String getLoanEndDate() {
        return loanEndDate;
    }

    public void setLoanEndDate(String loanEndDate) {
        this.loanEndDate = loanEndDate;
    }

    public BigDecimal getContAmt() {
        return contAmt;
    }

    public void setContAmt(BigDecimal contAmt) {
        this.contAmt = contAmt;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }
}