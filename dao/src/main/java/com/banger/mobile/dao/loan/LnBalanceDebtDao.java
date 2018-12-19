/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan;


import com.banger.mobile.domain.model.loan.LnBalanceDebt;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 */
public interface LnBalanceDebtDao {
	
	/**
	 * 
	 */
	void insertLnBalanceDebt(LnBalanceDebt lnBalanceDebt);

	/**
	 */
	void deleteLnBalanceDebtById(Integer id);

	/**
	 */
	void updateLnBalanceDebtById(LnBalanceDebt lnBalanceDebt);

	/**
	 */
	LnBalanceDebt getLnBalanceDebtById(Integer id);

	List<LnBalanceDebt> getLnBalanceDebtByLoanId(Integer loanId, String type);

	BigDecimal sumByType(Integer loanId, String type);
}
