/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnBalanceOffDao;
import com.banger.mobile.domain.model.loan.LnBalanceOff;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 *
 */
public class LnBalanceOffDaoiBatis extends GenericDaoiBatis implements LnBalanceOffDao{

	public LnBalanceOffDaoiBatis(){
        super(LnBalanceOffDaoiBatis.class);
    }

	/**
	 * @param persistentClass
	 */
	public LnBalanceOffDaoiBatis(Class persistentClass) {
		super(persistentClass);
	}


	@Override
	public void insertLnBalanceOff(LnBalanceOff lnBalanceOff) {
		this.getSqlMapClientTemplate().insert("insertBalanceOff", lnBalanceOff);
	}

	@Override
	public void deleteLnBalanceOffById(Integer loanId) {
		this.getSqlMapClientTemplate().delete("deleteBalanceOff", loanId);
	}

	@Override
	public void updateLnBalanceOffById(LnBalanceOff lnBalanceOff) {
		this.getSqlMapClientTemplate().update("updateBalanceOff", lnBalanceOff);
	}

	@Override
	public LnBalanceOff getLnBalanceOffById(Integer loanId) {
		return (LnBalanceOff) this.getSqlMapClientTemplate().queryForObject("getLnBalanceOffById", loanId);
	}
}
