/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan;


import com.banger.mobile.domain.model.loan.LnBalanceFixed;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 */
public interface LnBalanceFixedDao {
	
	/**
	 * 
	 */
	void insertLnBalanceFixed(LnBalanceFixed lnBalanceFixed);


	/**
	 */
	void deleteLnBalanceFixedById(Integer id);

	/**
	 */
	void updateLnBalanceFixedById(LnBalanceFixed lnBalanceFixed);

	/**
	 */
	LnBalanceFixed getLnBalanceFixedById(Integer id);


	List<LnBalanceFixed> getLnBalanceFixedByLoanId(Integer loanId, String type);

	BigDecimal sumByType(Integer loanId, String type);
}
