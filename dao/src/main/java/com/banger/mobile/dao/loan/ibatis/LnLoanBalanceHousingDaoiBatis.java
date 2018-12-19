package com.banger.mobile.dao.loan.ibatis;

import java.util.List;

import com.banger.mobile.dao.loan.LnLoanBalanceHousingDAO;
import com.banger.mobile.domain.model.loan.LnLoanBalanceDebt;
import com.banger.mobile.domain.model.loan.LnLoanBalanceHousing;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class LnLoanBalanceHousingDaoiBatis extends GenericDaoiBatis implements LnLoanBalanceHousingDAO {

	public LnLoanBalanceHousingDaoiBatis(Class persistentClass) {
		super(LnLoanBalanceHousing.class);
		
	}

	public LnLoanBalanceHousingDaoiBatis() {
		super(LnLoanBalanceHousing.class);
		
	}

	@Override
	public LnLoanBalanceHousing updateHousing(LnLoanBalanceHousing asset) {
		
		Integer	lnTer=this.getSqlMapClientTemplate().update("updateHousing", asset);
		
		return asset;
	}

	@Override
	public List<LnLoanBalanceHousing> selectHousingByPrimary(
			LnLoanBalanceHousing lnLoanBalanceHousing) {
		
		return this.getSqlMapClientTemplate().queryForList(
				"selectHousingByPrimary", lnLoanBalanceHousing);
	}

	@Override
	public LnLoanBalanceHousing insertHousing(
			LnLoanBalanceHousing lnLoanBalanceHousing) {
		Integer	lnTer=(Integer) this.getSqlMapClientTemplate().insert("insertHousing",lnLoanBalanceHousing);
		LnLoanBalanceHousing ln = new LnLoanBalanceHousing();
		ln.setLoanBalanceHousingId(lnTer);
		return ln;
	}

	@Override
	public void deleteHousing(LnLoanBalanceHousing lnLoanBalanceHousing) {
		this.getSqlMapClientTemplate().delete("deleteHousing", lnLoanBalanceHousing);
		
	}
	
   
}