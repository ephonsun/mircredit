/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan;


import com.banger.mobile.domain.model.loan.LnBalanceAccount;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 */
public interface LnBalanceAccountDao {
	
	/**
	 * 
	 */
	void insertLnBalanceAccount(LnBalanceAccount lnBalanceAccount);


	/**
	 */
	void deleteLnBalanceAccountById(Integer id);

	/**
	 */
	void updateLnBalanceAccountById(LnBalanceAccount lnBalanceAccount);

	/**
	 */
	LnBalanceAccount getLnBalanceAccountById(Integer id);

	List<LnBalanceAccount> getLnBalanceAccountByLoanId(Integer loanId, String type);

	BigDecimal sumByType(Integer loanId, String type);
}
