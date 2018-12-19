/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnBalanceDebtDao;
import com.banger.mobile.domain.model.loan.LnBalanceDebt;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class LnBalanceDebtDaoiBatis extends GenericDaoiBatis implements LnBalanceDebtDao{

	public LnBalanceDebtDaoiBatis(){
        super(LnBalanceDebtDaoiBatis.class);
    }

	/**
	 * @param persistentClass
	 */
	public LnBalanceDebtDaoiBatis(Class persistentClass) {
		super(persistentClass);
	}


	@Override
	public void insertLnBalanceDebt(LnBalanceDebt lnBalanceDebt) {
		this.getSqlMapClientTemplate().insert("insertBalanceDebt", lnBalanceDebt);
	}

	@Override
	public void deleteLnBalanceDebtById(Integer id) {
		this.getSqlMapClientTemplate().delete("deleteBalanceDebt", id);
	}

	@Override
	public void updateLnBalanceDebtById(LnBalanceDebt lnBalanceDebt) {
		this.getSqlMapClientTemplate().update("updateBalanceDebt", lnBalanceDebt);
	}

	@Override
	public LnBalanceDebt getLnBalanceDebtById(Integer id) {
		return (LnBalanceDebt) this.getSqlMapClientTemplate().queryForObject("getLnBalanceDebtById", id);
	}

	@Override
	public List<LnBalanceDebt> getLnBalanceDebtByLoanId(Integer loanId, String type) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanId", loanId);
		param.put("type", type);
		return this.getSqlMapClientTemplate().queryForList("getLnBalanceDebtByLoanId",param);
	}

	@Override
	public BigDecimal sumByType(Integer loanId, String type) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanId", loanId);
		param.put("type", type);
		return (BigDecimal) this.getSqlMapClientTemplate().queryForObject("sumDebtByType", param);
	}
}
