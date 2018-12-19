package com.banger.mobile.domain.model.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yuanme
 * Date: 13-2-22
 * Time: 下午2:50
 * To change this template use File | Settings | File Templates.
 */
public class LoanDataGu implements Serializable {
    private Integer customerId;
    private String customerName;
    private List<LoanData> loanDataList;

    public List<LoanData> getLoanDataList() {
        return loanDataList;
    }

    public void setLoanDataList(List<LoanData> loanDataList) {
        this.loanDataList = loanDataList;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
