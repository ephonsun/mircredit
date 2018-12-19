/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.contract.LnLoanContract;

/**
 */
public interface LnLoanContractService {
	

	void insertLnLoanContract(LnLoanContract lnLoanContract);

	void updateLnLoanContractById(LnLoanContract lnLoanContract);

	void deleteLoanContractById(Integer loanId);

	LnLoanContract getLnLoanContractById(Integer loanId);
	
}
