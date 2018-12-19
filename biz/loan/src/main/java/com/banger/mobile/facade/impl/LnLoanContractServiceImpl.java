/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanContractDao;
import com.banger.mobile.domain.model.contract.LnLoanContract;
import com.banger.mobile.facade.loan.LnLoanContractService;

/**
 *
 */
public class LnLoanContractServiceImpl implements LnLoanContractService {


	private LnLoanContractDao lnLoanContractDao;


	public LnLoanContractDao getLnLoanContractDao() {
		return lnLoanContractDao;
	}

	public void setLnLoanContractDao(LnLoanContractDao lnLoanContractDao) {
		this.lnLoanContractDao = lnLoanContractDao;
	}


	@Override
	public void insertLnLoanContract(LnLoanContract lnLoanContract) {
		lnLoanContractDao.insertLnLoanContract(lnLoanContract);
	}

	@Override
	public void updateLnLoanContractById(LnLoanContract lnLoanContract) {
		lnLoanContractDao.updateLnLoanContractById(lnLoanContract);
	}

	@Override
	public void deleteLoanContractById(Integer loanId) {
		lnLoanContractDao.deleteLnLoanContractById(loanId);
	}

	@Override
	public LnLoanContract getLnLoanContractById(Integer loanId) {
		return lnLoanContractDao.getLnLoanContractById(loanId);
	}
}
