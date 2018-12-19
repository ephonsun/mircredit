/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款异常催收日志
 * Author     :zhangfp
 * Create Date:2013-3-15
 */
package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.LnExceptionDunLog;

import java.util.List;
import java.util.Map;
/**
 * @author zhangfp
 * @version $Id: LoanWebServiceImpl.java v 0.1 ${} 上午11:40 Administrator Exp $
 */
public interface LnExceptionDunLogDao {
    List<LnExceptionDunLog> getExpDunLogByLoanId(Integer loanId);

    /**
     * @return
     */
    Integer getNextExceptionDunLogId();

    /**
     * @param paramMap
     */
    void updateExceptionDunLogById(Map<String, Object> paramMap);

    /**
     * @param newLog
     */
    void addLnExceptionDunLog(LnExceptionDunLog newLog);

    /**
     * @param exceptionDunLogId
     * @return
     */
    LnExceptionDunLog getExceptionDunLogById(Integer exceptionDunLogId);
}
