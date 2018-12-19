/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnCreditHistory;

/**
 * 信贷历史
 * @author White Wolf
 * @version $Id: LnCreditHistoryDao.java, v 0.1 2016-1-7 下午2:47:55 White Wolf Exp $
 */
public interface LnCreditHistoryDao {
	
	void insertCreditHistory(LnCreditHistory lnCreditHistory);

	/**
	 * 查询信贷历史列表
	 * @param loanId
	 * @return
	 */
	List<LnCreditHistory> selectCreditHistoryByLoanId(int loanId);

	/**
	 * 
	 * @param chId
	 * @return
	 */
	void deleteCreditHistoryById(Integer creditHistoryId);
	
	void updateCreditHistory(LnCreditHistory lnCreditHistory);

	/**
	 * 
	 * @param loanId
	 */
	void deleteByLoanId(Integer loanId);
}
