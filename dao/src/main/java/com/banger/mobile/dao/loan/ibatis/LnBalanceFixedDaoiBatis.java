/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnBalanceFixedDao;
import com.banger.mobile.domain.model.loan.LnBalanceFixed;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class LnBalanceFixedDaoiBatis extends GenericDaoiBatis implements LnBalanceFixedDao{

	public LnBalanceFixedDaoiBatis(){
        super(LnBalanceFixedDaoiBatis.class);
    }

	/**
	 * @param persistentClass
	 */
	public LnBalanceFixedDaoiBatis(Class persistentClass) {
		super(persistentClass);
	}


	@Override
	public void insertLnBalanceFixed(LnBalanceFixed lnBalanceFixed) {
		this.getSqlMapClientTemplate().insert("insertBalanceFixed", lnBalanceFixed);
	}

	@Override
	public void deleteLnBalanceFixedById(Integer id) {
		this.getSqlMapClientTemplate().delete("deleteBalanceFixed", id);
	}

	@Override
	public void updateLnBalanceFixedById(LnBalanceFixed lnBalanceFixed) {
		this.getSqlMapClientTemplate().update("updateBalanceFixed", lnBalanceFixed);
	}

	@Override
	public LnBalanceFixed getLnBalanceFixedById(Integer id) {
		return (LnBalanceFixed) this.getSqlMapClientTemplate().queryForObject("getLnBalanceFixedById", id);
	}

	@Override
	public List<LnBalanceFixed> getLnBalanceFixedByLoanId(Integer loanId, String type) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanId", loanId);
		param.put("type", type);
		return this.getSqlMapClientTemplate().queryForList("getLnBalanceFixedByLoanId",param);
	}

	@Override
	public BigDecimal sumByType(Integer loanId, String type) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanId", loanId);
		param.put("type", type);
		return (BigDecimal) this.getSqlMapClientTemplate().queryForObject("sumFixedByType", param);
	}
}
