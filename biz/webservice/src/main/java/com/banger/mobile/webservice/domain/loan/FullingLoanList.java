/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :huangk
 * Create Date:2013-3-22
 */
package com.banger.mobile.webservice.domain.loan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class FullingLoanList {


    private Integer lessLoanCount = 0;
    private BigDecimal lessLoanAmount = new BigDecimal(0);
    private List<FullingLoanInfo> fullingList = new ArrayList<FullingLoanInfo>();

    public Integer getLessLoanCount() {
        return lessLoanCount;
    }

    public void setLessLoanCount(Integer lessLoanCount) {
        this.lessLoanCount = lessLoanCount;
    }

    public BigDecimal getLessLoanAmount() {
        return lessLoanAmount;
    }

    public void setLessLoanAmount(BigDecimal lessLoanAmount) {
        this.lessLoanAmount = lessLoanAmount;
    }

    public List<FullingLoanInfo> getFullingList() {
        return fullingList;
    }

    public void setFullingList(List<FullingLoanInfo> fullingList) {
        this.fullingList = fullingList;
    }

}
