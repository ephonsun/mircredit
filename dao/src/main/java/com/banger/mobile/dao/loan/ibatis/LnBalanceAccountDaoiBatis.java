/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnBalanceAccountDao;
import com.banger.mobile.domain.model.loan.LnBalanceAccount;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class LnBalanceAccountDaoiBatis extends GenericDaoiBatis implements LnBalanceAccountDao{

	public LnBalanceAccountDaoiBatis(){
        super(LnBalanceAccountDaoiBatis.class);
    }

	/**
	 * @param persistentClass
	 */
	public LnBalanceAccountDaoiBatis(Class persistentClass) {
		super(persistentClass);
	}


	@Override
	public void insertLnBalanceAccount(LnBalanceAccount lnBalanceAccount) {
		this.getSqlMapClientTemplate().insert("insertBalanceAccount", lnBalanceAccount);
	}

	@Override
	public void deleteLnBalanceAccountById(Integer id) {
		this.getSqlMapClientTemplate().delete("deleteBalanceAccount", id);
	}

	@Override
	public void updateLnBalanceAccountById(LnBalanceAccount lnBalanceAccount) {
		this.getSqlMapClientTemplate().update("updateBalanceAccount", lnBalanceAccount);
	}

	@Override
	public LnBalanceAccount getLnBalanceAccountById(Integer id) {
		return (LnBalanceAccount) this.getSqlMapClientTemplate().queryForObject("getLnBalanceAccountById", id);
	}

	@Override
	public List<LnBalanceAccount> getLnBalanceAccountByLoanId(Integer loanId, String type) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanId", loanId);
		param.put("type", type);
		return this.getSqlMapClientTemplate().queryForList("getLnBalanceAccountByLoanId",param);
	}

	@Override
	public BigDecimal sumByType(Integer loanId, String type) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanId", loanId);
		param.put("type", type);
		return (BigDecimal) this.getSqlMapClientTemplate().queryForObject("sumAccountByType", param);
	}
}
