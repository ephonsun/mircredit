package com.banger.mobile.dao.loan.ibatis;

import java.util.List;

import com.banger.mobile.dao.loan.LnLoanBalanceReceivableDAO;
import com.banger.mobile.domain.model.loan.LnLoanBalanceReceivable;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class LnLoanBalanceReceivableImplDaoiBatis extends GenericDaoiBatis implements LnLoanBalanceReceivableDAO  {

	public LnLoanBalanceReceivableImplDaoiBatis() {
		super(LnLoanBalanceReceivable.class);
	
	}
	
	public LnLoanBalanceReceivableImplDaoiBatis(Class persistentClass) {
		super(LnLoanBalanceReceivable.class);
	
	}

	@Override
	public void deleteReceivable(LnLoanBalanceReceivable lnLoanBalanceReceivable) {
		this.getSqlMapClientTemplate().delete("deleteReceivable", lnLoanBalanceReceivable);
		
	}

	@Override
	public LnLoanBalanceReceivable insertReceivable(
			LnLoanBalanceReceivable lnLoanBalanceReceivable) {

		Integer	lnTer=(Integer) this.getSqlMapClientTemplate().insert("insertReceivable",lnLoanBalanceReceivable);
		LnLoanBalanceReceivable ln = new LnLoanBalanceReceivable();
		ln.setLoanBalanceReceivableId(lnTer);
		return ln;
	}

	@Override
	public List<LnLoanBalanceReceivable> selectReceivableByPrimary(
			LnLoanBalanceReceivable lnLoanBalanceReceivable) {

		return this.getSqlMapClientTemplate().queryForList(
				"selectReceivableByPrimary", lnLoanBalanceReceivable);
	}

	@Override
	public LnLoanBalanceReceivable updateReceivable(
			LnLoanBalanceReceivable asset) {
		Integer	lnTer=this.getSqlMapClientTemplate().update("updateReceivable", asset);
		LnLoanBalanceReceivable ln = new LnLoanBalanceReceivable();
		ln.setLoanBalanceReceivableId(lnTer);
		return ln;
	}

}