package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.poi.hssf.record.crypto.Biff8DecryptingStream;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TmpLoanRepayment extends BaseObject implements Serializable {

    private static final long serialVersionUID = -8829382608278673443L;
    
    private String acctNo;
    private String txnLonType;
    private String trnDate;
    private String trnTime;
    private BigDecimal trnAmt;

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getTxnLonType() {
        return txnLonType;
    }

    public void setTxnLonType(String txnLonType) {
        this.txnLonType = txnLonType;
    }

    public String getTrnDate() {
        return trnDate;
    }

    public void setTrnDate(String trnDate) {
        this.trnDate = trnDate;
    }

    public String getTrnTime() {
        return trnTime;
    }

    public void setTrnTime(String trnTime) {
        this.trnTime = trnTime;
    }

    public BigDecimal getTrnAmt() {
        return trnAmt;
    }

    public void setTrnAmt(BigDecimal trnAmt) {
        this.trnAmt = trnAmt;
    }
}