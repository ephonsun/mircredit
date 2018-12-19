/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款操作历史记录-Service-接口
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.loan.LnOpHistory;

import java.util.List;
import java.util.Map;

/**
 * @author QianJie
 * @version $Id: LnOpHistoryService.java,v 0.1 Feb 17, 2013 3:42:35 PM QianJie Exp $
 */
public interface LnOpHistoryService {

    void insertLnOpHistory(LnOpHistory lnOpHistory);

    void insertLnOpHistoryBatch(List<LnOpHistory> lnOpHistoryList);

    List<LnOpHistory> getAllOpHistoryListByLoanId(Integer loanId);

    LnOpHistory selectHistoryByLoanId(Integer loanId);

    LnOpHistory selectHistoryByLoanStatus(Map<String, Object> paramMap);

	/**
	 * 
	 * @param loanId
	 */
	void deletelnOpHistoryByLoanId(Integer loanId);
}
