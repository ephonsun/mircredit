/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.facade.loan;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.loan.LnCreditHistory;

/**
 * 
 * @author White Wolf
 * @version $Id: LnCreditHistoryService.java, v 0.1 2016-1-7 下午3:04:59 White Wolf Exp $
 */
public interface LnCreditHistoryService {
	
	void insertCreditHistory(LnCreditHistory creditHistory);

	/**
	 * 查询历史
	 * @param photoMap
	 * @return
	 */
	List<LnCreditHistory> getCreditHistoryByLoanId(int loanId);

	/**
	 * 删除历史
	 * @param chId
	 */
	void rmCreditHistory(Integer chId);
	
	void updateCreditHistory(LnCreditHistory lnCreditHistory);

	/**
	 * 
	 * @param loanId
	 */
	void deleteByLoanId(Integer loanId);
}
