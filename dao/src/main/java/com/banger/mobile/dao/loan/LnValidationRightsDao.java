/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan;


import com.banger.mobile.domain.model.loan.LnValidationRights;

/**
 *
 */
public interface LnValidationRightsDao {
	
	/**
	 * 
	 */
	void insertLnValidationRights(LnValidationRights lnValidationRights);


	/**
	 */
	void deleteLnValidationRightsById(Integer loanId);

	/**
	 */
	void updateLnValidationRightsById(LnValidationRights lnValidationRights);

	/**
	 */
	LnValidationRights getLnValidationRightsById(Integer loanId);
}
