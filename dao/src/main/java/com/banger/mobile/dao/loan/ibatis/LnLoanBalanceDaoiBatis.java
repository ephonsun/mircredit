package com.banger.mobile.dao.loan.ibatis;

import java.util.List;

import com.banger.mobile.dao.loan.LnLoanBalanceDao;
import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.domain.model.loan.LnLoanBalance;
import com.banger.mobile.domain.model.loan.LnLoanBalanceAsset;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class LnLoanBalanceDaoiBatis extends GenericDaoiBatis implements LnLoanBalanceDao {

	public LnLoanBalanceDaoiBatis(Class persistentClass) {
		super(LnLoanBalance.class);
		
	}
	
	public LnLoanBalanceDaoiBatis() {
		super(LnLoanBalance.class);
		
	}

	@Override
	public LnLoanBalance insertBalance(LnLoanBalance lnLoanBalance) {
		Integer	lnTer=(Integer) this.getSqlMapClientTemplate().insert("insertBalance",lnLoanBalance);
		LnLoanBalance ln = new LnLoanBalance();
		ln.setLoanBalanceId(lnTer);
		return ln;
	}

	@Override
	public LnLoanBalance updateBalance(LnLoanBalance lnLoanBalance) {
		this.getSqlMapClientTemplate().update("updateBalance",lnLoanBalance);
		
		return lnLoanBalance;
	}

	@Override
	public LnLoanBalance selectBalanceByPrimary(LnLoanBalance lnLoanBalance) {
		
		return (LnLoanBalance) this.getSqlMapClientTemplate().queryForObject("selectBalanceByPrimary",lnLoanBalance);
	}

	
}