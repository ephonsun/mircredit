/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnLoanTmpDao;
import com.banger.mobile.dao.loan.LnValidationItemDao;
import com.banger.mobile.domain.model.loan.LnValidationItem;
import com.banger.mobile.domain.model.loan.TmpLoanAccount;
import com.banger.mobile.domain.model.loan.TmpLoanInfo;
import com.banger.mobile.domain.model.loan.TmpLoanRepayment;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class LnLoanTmpDaoiBatis extends GenericDaoiBatis implements LnLoanTmpDao{

	public LnLoanTmpDaoiBatis(){
        super(LnLoanTmpDaoiBatis.class);
    }

	/**
	 * @param persistentClass
	 */
	public LnLoanTmpDaoiBatis(Class persistentClass) {
		super(persistentClass);
	}

	@Override
	public void updateTmpLoanInfo(TmpLoanInfo tmpLoanInfo) {
		this.getSqlMapClientTemplate().update("updateTmpLoanInfo", tmpLoanInfo);
	}

	@Override
	public TmpLoanInfo getTmpLoanInfoByAccount(String acctNo) {
		return (TmpLoanInfo) this.getSqlMapClientTemplate().queryForObject("getTmpLoanInfoByAccount", acctNo);
	}

	@Override
	public TmpLoanInfo getTmpLoanInfoByLoanId(Integer loanId) {
		return (TmpLoanInfo) this.getSqlMapClientTemplate().queryForObject("getTmpLoanInfoByLoanId", loanId);
	}

	@Override
	public List<TmpLoanInfo> getTmpLoanInfoAll() {
		return this.getSqlMapClientTemplate().queryForList("getTmpLoanInfoAll");
	}

	@Override
	public List<TmpLoanAccount> getTmpLoanAccountAll() {
		return this.getSqlMapClientTemplate().queryForList("getTmpLoanAccountAll");
	}

	@Override
	public List<TmpLoanRepayment> getTmpLoanRepaymentAll() {
		return this.getSqlMapClientTemplate().queryForList("getTmpLoanRepaymentAll");
	}

	@Override
	public TmpLoanAccount getTmpLoanAccountByLoanId(Integer loanId) {
		return (TmpLoanAccount) this.getSqlMapClientTemplate().queryForObject("getTmpLoanAccountByLoanId", loanId);
	}


}
