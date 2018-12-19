/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款类型(包括子类型)
 * Author     :zhangfp
 * Create Date:2013-3-14
 */
package com.banger.mobile.domain.model.pad;

import com.banger.mobile.domain.model.loan.LnLoanSubType;
import com.banger.mobile.domain.model.loan.LnLoanType;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @version $Id: PadLoanTypePage.java v 0.1 ${} 下午4:57 Administrator Exp $
 */
public class PadLoanTypePage implements Serializable {
    private static final long serialVersionUID = -1600261088363093202L;

    private List<PadLoanType> loanTypeList;
    private List<PadLoanSubType> loanSubTypeList;

    public List<PadLoanType> getLoanTypeList() {
        return loanTypeList;
    }

    public void setLoanTypeList(List<PadLoanType> loanTypeList) {
        this.loanTypeList = loanTypeList;
    }

    public List<PadLoanSubType> getLoanSubTypeList() {
        return loanSubTypeList;
    }

    public void setLoanSubTypeList(List<PadLoanSubType> loanSubTypeList) {
        this.loanSubTypeList = loanSubTypeList;
    }
}
