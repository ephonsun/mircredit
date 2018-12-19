/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan;


import com.banger.mobile.domain.model.loan.LnBalanceOff;

/**
 *
 */
public interface LnBalanceOffDao {
	
	/**
	 * 
	 */
	void insertLnBalanceOff(LnBalanceOff lnBalanceOff);


	/**
	 */
	void deleteLnBalanceOffById(Integer loanId);

	/**
	 */
	void updateLnBalanceOffById(LnBalanceOff lnBalanceOff);

	/**
	 */
	LnBalanceOff getLnBalanceOffById(Integer loanId);
}
