package com.banger.mobile.dao.loan.ibatis;

import java.util.List;

import com.banger.mobile.dao.loan.LnLoanBalanceOtherDAO;
import com.banger.mobile.dao.loan.LnProfitLossProdDAO;
import com.banger.mobile.domain.model.loan.LnLoanBalanceOther;
import com.banger.mobile.domain.model.loan.LnProfitLossProd;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class LnProfitLossProdDAOImpl extends GenericDaoiBatis implements LnProfitLossProdDAO {

	public LnProfitLossProdDAOImpl() {
		super(LnProfitLossProd.class);
	
	}

	public LnProfitLossProdDAOImpl(Class persistentClass) {
		super(LnProfitLossProd.class);
		
	}

	@Override
	public List<LnProfitLossProd> selectProfitLossByPrimary(
			LnProfitLossProd lnProfitLossProd) {

		return this.getSqlMapClientTemplate().queryForList(
				"selectProfitLossByPrimary", lnProfitLossProd);
	}

	@Override
	public LnProfitLossProd insertProd(LnProfitLossProd lnProfitLossProd) {
		Integer	profitLossId=(Integer) this.getSqlMapClientTemplate().insert("insertProd",lnProfitLossProd);
		LnProfitLossProd ln = new LnProfitLossProd();
		ln.setProfitLossId(profitLossId);
		return ln;
	}
	

	@Override
	public void updateProd(LnProfitLossProd lnProfitLossProd ) {
		System.out.println("3----------------lnProfitLossProd = " + lnProfitLossProd);
		this.getSqlMapClientTemplate().update("updateProd", lnProfitLossProd);
	}

	@Override
	public void deleteProd(LnProfitLossProd lnProfitLossProd) {
		this.getSqlMapClientTemplate().delete("deleteProd", lnProfitLossProd);
		
	}

	@Override
	public List<LnProfitLossProd> getProfitLossProdByLoanId(Integer loanId) {
		return this.getSqlMapClientTemplate().queryForList("getProfitLossProdByLoanId", loanId);	}


}