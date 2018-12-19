package com.banger.mobile.dao.loan.ibatis;

import java.util.List;

import com.banger.mobile.dao.loan.LnLoanBalanceOtherDAO;
import com.banger.mobile.domain.model.loan.LnLoanBalanceOther;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class LnLoanBalanceOtherDAOImpl extends GenericDaoiBatis implements LnLoanBalanceOtherDAO {

	public LnLoanBalanceOtherDAOImpl() {
		super(LnLoanBalanceOther.class);
	
	}

	public LnLoanBalanceOtherDAOImpl(Class persistentClass) {
		super(LnLoanBalanceOther.class);
		
	}

	@Override
	public List<LnLoanBalanceOther> selectOtherByPrimary(
			LnLoanBalanceOther lnLBalanceOther) {
		
		return this.getSqlMapClientTemplate().queryForList(
				"selectOtherByPrimary", lnLBalanceOther);
	}

	@Override
	public LnLoanBalanceOther insertOther(LnLoanBalanceOther lnLBalanceOther) {
		Integer	lnTer=(Integer) this.getSqlMapClientTemplate().insert("insertOther",lnLBalanceOther);
		LnLoanBalanceOther ln = new LnLoanBalanceOther();
		ln.setLoanBalanceOtherId(lnTer);
		return ln;
	}
	

	@Override
	public int updateOther(LnLoanBalanceOther other ) {
		Integer	lnTer=this.getSqlMapClientTemplate().update("updateOther", other);
		/*LnLoanBalanceOther ln = new LnLoanBalanceOther();
		ln.setLoanBalanceOtherId(lnTer);*/
		return lnTer;
	}

	@Override
	public void deleteAsset(LnLoanBalanceOther lnLBalanceOther) {
		this.getSqlMapClientTemplate().delete("deleteOther", lnLBalanceOther);
		
	}




    
}