package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanBalanceHousingDAO;
import com.banger.mobile.domain.model.loan.LnLoanBalanceHousing;
import com.banger.mobile.facade.loan.LnLoanBalanceHousingService;

import java.util.List;

public class LnLoanBalanceHousingServiceImpl implements LnLoanBalanceHousingService {

	private LnLoanBalanceHousingDAO lnLoanBalanceHousingDAO;

	public void setLnLoanBalanceHousingDAO(
			LnLoanBalanceHousingDAO lnLoanBalanceHousingDAO) {
		this.lnLoanBalanceHousingDAO = lnLoanBalanceHousingDAO;
	}

	@Override
	public void deleteHousing(LnLoanBalanceHousing lnLoanBalanceHousing) {
		
		lnLoanBalanceHousingDAO.deleteHousing(lnLoanBalanceHousing);
	}

	@Override
	public List<LnLoanBalanceHousing> selectHousingByPrimary(
			LnLoanBalanceHousing lnLoanBalanceHousing) {
		
		return lnLoanBalanceHousingDAO.selectHousingByPrimary(lnLoanBalanceHousing);
	}

	@Override
	public LnLoanBalanceHousing insertHousing(
			LnLoanBalanceHousing lnLoanBalanceHousing) {
		
		
		return lnLoanBalanceHousingDAO.insertHousing(lnLoanBalanceHousing);
	}

	@Override
	public LnLoanBalanceHousing updateHousing(
			LnLoanBalanceHousing lnLoanBalanceHousing) {
		List<LnLoanBalanceHousing> assetList = lnLoanBalanceHousingDAO.selectHousingByPrimary(lnLoanBalanceHousing);
		LnLoanBalanceHousing asset=assetList.get(0);
		
		asset.setAddress(lnLoanBalanceHousing.getAddress());
		asset.setArea(lnLoanBalanceHousing.getArea());
		asset.setBuyDate(lnLoanBalanceHousing.getBuyDate());
		asset.setPrice(lnLoanBalanceHousing.getPrice());
		
		return lnLoanBalanceHousingDAO.updateHousing(asset);
		
	}

	@Override
	public List<LnLoanBalanceHousing> selectHousingByPrimary(
			Integer loanBalanceId) {
		LnLoanBalanceHousing lnLoanBalanceHousing= new LnLoanBalanceHousing();
		lnLoanBalanceHousing.setLoanBalanceId(loanBalanceId);
		return lnLoanBalanceHousingDAO.selectHousingByPrimary(lnLoanBalanceHousing);
	}
	
	
	
}
