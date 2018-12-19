/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan;


import com.banger.mobile.domain.model.loan.LnValidationItem;

import java.util.List;

/**
 *
 */
public interface LnValidationItemDao {
	
	/**
	 * 
	 */
	void insertLnValidationItem(LnValidationItem lnValidationItem);


	/**
	 */
	void deleteLnValidationItemById(Integer id);

	/**
	 */
	void updateLnValidationItemById(LnValidationItem lnValidationItem);

	/**
	 */
	LnValidationItem getLnValidationItemById(Integer id);


	List<LnValidationItem> getLnValidationItemByLoanId(Integer loanId, String type);
}
