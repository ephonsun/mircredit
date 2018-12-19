/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan;


import com.banger.mobile.domain.model.loan.LnBalanceCash;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 */
public interface LnBalanceCashDao {
	
	/**
	 * 
	 */
	void insertLnBalanceCash(LnBalanceCash lnBalanceCash);


	/**
	 */
	void deleteLnBalanceCashById(Integer id);

	/**
	 */
	void updateLnBalanceCashById(LnBalanceCash lnBalanceCash);

	/**
	 */
	LnBalanceCash getLnBalanceCashById(Integer id);


	List<LnBalanceCash> getLnBalanceCashByLoanId(Integer loanId, String type);

	BigDecimal sumByType(Integer loanId, String type);
}
