/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.contract.LnLoanContract;

/**
 *
 */
public interface LnLoanContractDao {
	
	/**
	 * 
	 */
	void insertLnLoanContract(LnLoanContract lnLoanContract);


	/**
	 */
	void deleteLnLoanContractById(Integer loanId);

	/**
	 */
	void updateLnLoanContractById(LnLoanContract lnLoanContract);

	/**
	 */
	LnLoanContract getLnLoanContractById(Integer loanId);
}
