/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnLoanContractDao;
import com.banger.mobile.domain.model.contract.LnLoanContract;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 *
 */
public class LnLoanContractDaoiBatis extends GenericDaoiBatis implements LnLoanContractDao{

	public LnLoanContractDaoiBatis(){
        super(LnLoanContractDaoiBatis.class);
    }

	/**
	 * @param persistentClass
	 */
	public LnLoanContractDaoiBatis(Class persistentClass) {
		super(persistentClass);
	}


	@Override
	public void insertLnLoanContract(LnLoanContract lnLoanContract) {
		this.getSqlMapClientTemplate().insert("insertLoanContract", lnLoanContract);
	}

	@Override
	public void deleteLnLoanContractById(Integer loanId) {
		this.getSqlMapClientTemplate().delete("deleteLoanContract", loanId);
	}

	@Override
	public void updateLnLoanContractById(LnLoanContract lnLoanContract) {
		this.getSqlMapClientTemplate().update("updateLoanContract", lnLoanContract);
	}

	@Override
	public LnLoanContract getLnLoanContractById(Integer loanId) {
		return (LnLoanContract) this.getSqlMapClientTemplate().queryForObject("getLnLoanContractById", loanId);
	}
}
