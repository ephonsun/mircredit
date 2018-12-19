package com.banger.mobile.dao.loan.ibatis;

import java.util.List;

import com.banger.mobile.dao.loan.LnLoanBalanceDebtDAO;
import com.banger.mobile.domain.model.loan.LnLoanBalanceAsset;
import com.banger.mobile.domain.model.loan.LnLoanBalanceDebt;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class LnLoanBalanceDebtDaoiBatis extends GenericDaoiBatis implements LnLoanBalanceDebtDAO {

	public LnLoanBalanceDebtDaoiBatis() {
		super(LnLoanBalanceDebt.class);
	
	}
	
	public LnLoanBalanceDebtDaoiBatis(Class persistentClass) {
		super(LnLoanBalanceDebt.class);
		
	}

	@Override
	public void deleteDebt(LnLoanBalanceDebt lnBalanceDebt) {
		
		this.getSqlMapClientTemplate().delete("deleteDebt", lnBalanceDebt);
	}

	@Override
	public List<LnLoanBalanceDebt> selectDebtByPrimary(
			LnLoanBalanceDebt lnLoanBalanceDebt) {
		
		return this.getSqlMapClientTemplate().queryForList(
				"selectDebtByPrimary", lnLoanBalanceDebt);
	}

	@Override
	public LnLoanBalanceDebt insertDebt(LnLoanBalanceDebt lnLoanBalanceDebt) {
		Integer	lnTer=(Integer) this.getSqlMapClientTemplate().insert("insertDebt",lnLoanBalanceDebt);
		LnLoanBalanceDebt ln = new LnLoanBalanceDebt();
		ln.setLoanBalanceDebtId(lnTer);
		return ln;
	}

	@Override
	public LnLoanBalanceDebt updateDebt(LnLoanBalanceDebt debt) {
		Integer	lnTer=this.getSqlMapClientTemplate().update("updateDebt", debt);
		
		return debt;
	}

	
}