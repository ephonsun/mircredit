package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanBalanceOtherDAO;
import com.banger.mobile.domain.model.loan.LnLoanBalanceOther;
import com.banger.mobile.facade.loan.LnLoanBalanceOtherService;

import java.util.List;

public class LnLoanBalanceOtherServiceImpl implements LnLoanBalanceOtherService {

	private LnLoanBalanceOtherDAO lnLoanBalanceOtherDAO;
	
	
	public void setLnLoanBalanceOtherDAO(LnLoanBalanceOtherDAO lnLoanBalanceOtherDAO) {
		this.lnLoanBalanceOtherDAO = lnLoanBalanceOtherDAO;
	}


	@Override
	public List<LnLoanBalanceOther> selectOtherByPrimary(
			LnLoanBalanceOther lnLBalanceOther) {
		
		return lnLoanBalanceOtherDAO.selectOtherByPrimary(lnLBalanceOther);
	}


	@Override
	public LnLoanBalanceOther insertOther(LnLoanBalanceOther lnLBalanceOther) {
		
		return lnLoanBalanceOtherDAO.insertOther(lnLBalanceOther);
	}


	@Override
	public LnLoanBalanceOther updateOther(LnLoanBalanceOther lnLBalanceOther) {
		
		List<LnLoanBalanceOther> assetList = lnLoanBalanceOtherDAO.selectOtherByPrimary(lnLBalanceOther);
		LnLoanBalanceOther asset=assetList.get(0);
		asset.setProductName(lnLBalanceOther.getProductName());
		asset.setBuyingPrice(lnLBalanceOther.getBuyingPrice());
		asset.setSalesPrice(lnLBalanceOther.getSalesPrice());
		asset.setSalesProp(lnLBalanceOther.getSalesProp());
		
		int businessId=lnLoanBalanceOtherDAO.updateOther(asset);
		return asset;
	}


	@Override
	public void deleteOher(LnLoanBalanceOther lnLBalanceOther) {
		
		lnLoanBalanceOtherDAO.deleteAsset(lnLBalanceOther);
	}


	@Override
	public List<LnLoanBalanceOther> selectOtherByPrimary(Integer loanBalanceId) {
		LnLoanBalanceOther lnLoanBalanceOther = new  LnLoanBalanceOther();
		lnLoanBalanceOther.setLoanBalanceId(loanBalanceId);
		return lnLoanBalanceOtherDAO.selectOtherByPrimary(lnLoanBalanceOther);
	}

	
}
