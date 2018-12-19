package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TmpLoanAccount extends BaseObject implements Serializable {

    private static final long serialVersionUID = -8829382608278673443L;

    private String acctNo;
    private String billNo;
    private String contNo;
    private String custNo;
    private BigDecimal lonBal;
    private String autoDbtAcctNo;
    private BigDecimal acctBal;
    private String accountStat;
    private BigDecimal nextRepay;

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getContNo() {
        return contNo;
    }

    public void setContNo(String contNo) {
        this.contNo = contNo;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public BigDecimal getLonBal() {
        return lonBal;
    }

    public void setLonBal(BigDecimal lonBal) {
        this.lonBal = lonBal;
    }

    public String getAutoDbtAcctNo() {
        return autoDbtAcctNo;
    }

    public void setAutoDbtAcctNo(String autoDbtAcctNo) {
        this.autoDbtAcctNo = autoDbtAcctNo;
    }

    public BigDecimal getAcctBal() {
        return acctBal;
    }

    public void setAcctBal(BigDecimal acctBal) {
        this.acctBal = acctBal;
    }

    public String getAccountStat() {
        return accountStat;
    }

    public void setAccountStat(String accountStat) {
        this.accountStat = accountStat;
    }

    public BigDecimal getNextRepay() {
        return nextRepay;
    }

    public void setNextRepay(BigDecimal nextRepay) {
        this.nextRepay = nextRepay;
    }
}