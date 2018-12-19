/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnBalanceCashDao;
import com.banger.mobile.domain.model.loan.LnBalanceCash;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class LnBalanceCashDaoiBatis extends GenericDaoiBatis implements LnBalanceCashDao{

	public LnBalanceCashDaoiBatis(){
        super(LnBalanceCashDaoiBatis.class);
    }

	/**
	 * @param persistentClass
	 */
	public LnBalanceCashDaoiBatis(Class persistentClass) {
		super(persistentClass);
	}


	@Override
	public void insertLnBalanceCash(LnBalanceCash lnBalanceCash) {
		this.getSqlMapClientTemplate().insert("insertBalanceCash", lnBalanceCash);
	}

	@Override
	public void deleteLnBalanceCashById(Integer id) {
		this.getSqlMapClientTemplate().delete("deleteBalanceCash", id);
	}

	@Override
	public void updateLnBalanceCashById(LnBalanceCash lnBalanceCash) {
		this.getSqlMapClientTemplate().update("updateBalanceCash", lnBalanceCash);
	}

	@Override
	public LnBalanceCash getLnBalanceCashById(Integer id) {
		return (LnBalanceCash) this.getSqlMapClientTemplate().queryForObject("getLnBalanceCashById", id);
	}

	@Override
	public List<LnBalanceCash> getLnBalanceCashByLoanId(Integer loanId, String type) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanId", loanId);
		param.put("type", type);
		return this.getSqlMapClientTemplate().queryForList("getLnBalanceCashByLoanId",param);
	}

	@Override
	public BigDecimal sumByType(Integer loanId, String type) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanId", loanId);
		param.put("type", type);
		return (BigDecimal) this.getSqlMapClientTemplate().queryForObject("sumCashByType", param);
	}

}
