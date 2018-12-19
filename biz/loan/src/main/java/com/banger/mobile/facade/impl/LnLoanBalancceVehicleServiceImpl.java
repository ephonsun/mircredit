package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanBalanceVehicleDAO;
import com.banger.mobile.domain.model.loan.LnLoanBalanceVehicle;
import com.banger.mobile.facade.loan.LnLoanBalanceVehicleService;

import java.util.List;


public class LnLoanBalancceVehicleServiceImpl implements LnLoanBalanceVehicleService {

	private LnLoanBalanceVehicleDAO lnLoanBalanceVehicleDAO;
	
	
	public void setLnLoanBalanceVehicleDAO(
			LnLoanBalanceVehicleDAO lnLoanBalanceVehicleDAO) {
		this.lnLoanBalanceVehicleDAO = lnLoanBalanceVehicleDAO;
	}


	@Override
	public List<LnLoanBalanceVehicle> selectVehicleByPrimary(
			LnLoanBalanceVehicle lnLoanBalanceVehicle) {
		
		return lnLoanBalanceVehicleDAO.selectVehicleByPrimary(lnLoanBalanceVehicle);
	}


	@Override
	public LnLoanBalanceVehicle insertVehicle(
			LnLoanBalanceVehicle lnLoanBalanceVehicle) {
		
		return lnLoanBalanceVehicleDAO.insertVehicle(lnLoanBalanceVehicle);
	}


	@Override
	public LnLoanBalanceVehicle updateVehicle(
			LnLoanBalanceVehicle lnLoanBalanceVehicle) {
		
		List<LnLoanBalanceVehicle> assetList = lnLoanBalanceVehicleDAO.selectVehicleByPrimary(lnLoanBalanceVehicle);
		LnLoanBalanceVehicle asset=assetList.get(0);
		asset.setBuyDate(lnLoanBalanceVehicle.getBuyDate());
		asset.setNote(lnLoanBalanceVehicle.getNote());
		asset.setPrice(lnLoanBalanceVehicle.getPrice());
		asset.setStatus(lnLoanBalanceVehicle.getStatus());
		asset.setVehicleNo(lnLoanBalanceVehicle.getVehicleNo());
		
		int businessId=lnLoanBalanceVehicleDAO.updateVehicle(asset);
		return asset;
	}


	@Override
	public void deleteVehicle(LnLoanBalanceVehicle lnLoanBalanceVehicle) {
		
		lnLoanBalanceVehicleDAO.deleteVehicle(lnLoanBalanceVehicle);
	}


	@Override
	public List<LnLoanBalanceVehicle> selectVehicleByPrimary(
			Integer loanBalanceId) {
		LnLoanBalanceVehicle lnLoanBalanceVehicle=new LnLoanBalanceVehicle();
		lnLoanBalanceVehicle.setLoanBalanceId(loanBalanceId);
		
		return lnLoanBalanceVehicleDAO.selectVehicleByPrimary(lnLoanBalanceVehicle);
	}

}
