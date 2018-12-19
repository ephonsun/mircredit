package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanBalanceReceivableDAO;
import com.banger.mobile.domain.model.loan.LnLoanBalanceReceivable;
import com.banger.mobile.facade.loan.LnLoanBalanceReceivableService;

import java.util.List;


public class LnLoanBalanceReceivableServiceImpl implements LnLoanBalanceReceivableService {

	private LnLoanBalanceReceivableDAO lnLoanBalanceReceivableDAO;
	
	
	public void setLnLoanBalanceReceivableDAO(
			LnLoanBalanceReceivableDAO lnLoanBalanceReceivableDAO) {
		this.lnLoanBalanceReceivableDAO = lnLoanBalanceReceivableDAO;
	}


	@Override
	public List<LnLoanBalanceReceivable> selectReceivableByPrimary(
			LnLoanBalanceReceivable lnLoanBalanceReceivable) {

		return lnLoanBalanceReceivableDAO.selectReceivableByPrimary(lnLoanBalanceReceivable);
	}


	@Override
	public LnLoanBalanceReceivable insertReceivable(
			LnLoanBalanceReceivable lnLoanBalanceReceivable) {
		
		return lnLoanBalanceReceivableDAO.insertReceivable(lnLoanBalanceReceivable);
	}


	@Override
	public LnLoanBalanceReceivable updateReceivable(
			LnLoanBalanceReceivable lnLoanBalanceReceivable) {
		List<LnLoanBalanceReceivable> assetList =lnLoanBalanceReceivableDAO.selectReceivableByPrimary(lnLoanBalanceReceivable);
		
		LnLoanBalanceReceivable asset=assetList.get(0);
		asset.setKfxm(lnLoanBalanceReceivable.getKfxm());
		asset.setJe(lnLoanBalanceReceivable.getJe());
		asset.setJkfs(lnLoanBalanceReceivable.getJkfs());
		asset.setFsrq(lnLoanBalanceReceivable.getFsrq());
		asset.setYwgxcx(lnLoanBalanceReceivable.getYwgxcx());
		asset.setFkfs(lnLoanBalanceReceivable.getFkfs());
		asset.setSchkrq(lnLoanBalanceReceivable.getSchkrq());
		asset.setSchkje(lnLoanBalanceReceivable.getSchkje());
		asset.setXchkrq(lnLoanBalanceReceivable.getXchkrq());
		asset.setXchkje(lnLoanBalanceReceivable.getXchkje());
		
	
		
		return lnLoanBalanceReceivableDAO.updateReceivable(asset);
	}


	@Override
	public void deleteReceivable(LnLoanBalanceReceivable lnLoanBalanceReceivable) {
		
		lnLoanBalanceReceivableDAO.deleteReceivable(lnLoanBalanceReceivable);
	}


	@Override
	public List<LnLoanBalanceReceivable> selectReceivableByPrimary(
			Integer loanBalanceId) {
		LnLoanBalanceReceivable lnLoanBalanceReceivable = new LnLoanBalanceReceivable();
		lnLoanBalanceReceivable.setLoanBalanceId(loanBalanceId);
		return lnLoanBalanceReceivableDAO.selectReceivableByPrimary(lnLoanBalanceReceivable);
	}



}
