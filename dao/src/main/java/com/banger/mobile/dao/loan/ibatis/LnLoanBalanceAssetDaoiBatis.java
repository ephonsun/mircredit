package com.banger.mobile.dao.loan.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.loan.LnLoanBalanceAssetDAO;
import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.domain.model.loan.LnLoanBalanceAsset;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class LnLoanBalanceAssetDaoiBatis extends GenericDaoiBatis implements LnLoanBalanceAssetDAO {

	public LnLoanBalanceAssetDaoiBatis(Class persistentClass) {
		super(LnLoanBalanceAsset.class);
		
	}
	
	public LnLoanBalanceAssetDaoiBatis() {
		super(LnLoanBalanceAsset.class);
		
	}

	@Override
	public List<LnLoanBalanceAsset> selectAssetByPrimary(
			LnLoanBalanceAsset lnLoanBalanceAsset) {
		
		return this.getSqlMapClientTemplate().queryForList(
				"selectAssetByPrimary", lnLoanBalanceAsset);
		
	}

	@Override
	public LnLoanBalanceAsset insertAsset(LnLoanBalanceAsset lnLoanBalanceAsset) {
		Integer	lnTer=(Integer) this.getSqlMapClientTemplate().insert("insertAsset",lnLoanBalanceAsset);
		LnLoanBalanceAsset ln = new LnLoanBalanceAsset();
		ln.setLoanBalanceAssetId(lnTer);
		return ln;
	}

	@Override
	public LnLoanBalanceAsset updateAsset(LnLoanBalanceAsset asset) {
		Integer	lnTer=this.getSqlMapClientTemplate().update("updateAsset", asset);
		LnLoanBalanceAsset ln = new LnLoanBalanceAsset();
		ln.setLoanBalanceAssetId(lnTer);
		return ln;
	}

	@Override
	public void deleteAsset(LnLoanBalanceAsset lnLoanBalanceAsset) {
		
		 this.getSqlMapClientTemplate().delete("deleteAsset",lnLoanBalanceAsset);
	}

	
	

	

}