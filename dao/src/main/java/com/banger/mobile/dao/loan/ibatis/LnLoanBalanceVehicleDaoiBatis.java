package com.banger.mobile.dao.loan.ibatis;

import java.util.List;

import com.banger.mobile.dao.loan.LnLoanBalanceVehicleDAO;
import com.banger.mobile.domain.model.loan.LnLoanBalanceVehicle;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class LnLoanBalanceVehicleDaoiBatis extends GenericDaoiBatis implements LnLoanBalanceVehicleDAO {

	public LnLoanBalanceVehicleDaoiBatis(Class persistentClass) {
		super(LnLoanBalanceVehicle.class);

	}

	public LnLoanBalanceVehicleDaoiBatis() {
		super(LnLoanBalanceVehicle.class);
		
	}

	@Override
	public List<LnLoanBalanceVehicle> selectVehicleByPrimary(
			LnLoanBalanceVehicle lnLoanBalanceVehicle) {
		
		return this.getSqlMapClientTemplate().queryForList("selectVehicleByPrimary", lnLoanBalanceVehicle);
	}

	@Override
	public void deleteVehicle(LnLoanBalanceVehicle lnLoanBalanceVehicle) {
		
		this.getSqlMapClientTemplate().delete("deleteVehicle", lnLoanBalanceVehicle);
	}

	@Override
	public int updateVehicle(LnLoanBalanceVehicle asset) {

		Integer	lnTer=this.getSqlMapClientTemplate().update("updateVehicle", asset);
		
		return lnTer;
	}

	@Override
	public LnLoanBalanceVehicle insertVehicle(
			LnLoanBalanceVehicle insertVehicle) {

		Integer	lnTer=(Integer) this.getSqlMapClientTemplate().insert("insertVehicle",insertVehicle);
		LnLoanBalanceVehicle ln = new LnLoanBalanceVehicle();
		ln.setLoanBalanceVehicleId(lnTer);
		return ln;
	}

	

  
}