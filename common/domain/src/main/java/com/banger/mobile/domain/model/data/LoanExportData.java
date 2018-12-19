package com.banger.mobile.domain.model.data;

import java.io.Serializable;

/**
 * @author zhangfp
 * @version $Id: LoanExportData v 0.1 ${} 下午3:16 zhangfp Exp $
 */
public class LoanExportData implements Serializable {
    private Integer loanId;
    private Integer eventId;
    private String customerName;
    private Integer fileId;

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
}
