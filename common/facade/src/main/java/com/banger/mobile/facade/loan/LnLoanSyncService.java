/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款同步-接口
 * Author     :yuanme
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.facade.loan;

/**
 * @author yuanme
 * @version $Id: LnLoanSyncService.java,v 0.1 Feb 17, 2013 3:42:16 PM yuanme Exp
 */
public interface LnLoanSyncService {
    // 同步贷款放贷状态
    public Boolean syncLoanStatus(Integer loanId, Integer userId);

    // 与核心同步贷款还款计划
    public Boolean syncLoanRepaymentPlan(Integer loanId, Integer userId);

    // 同步还款状态
    public Boolean syncLoanDunInfoRepayment(Integer loanId, Integer userId);

    // 同步客户账户余额
    public Boolean syncLoanDunInfoRemaining(Integer loanId, Integer userId);

}
