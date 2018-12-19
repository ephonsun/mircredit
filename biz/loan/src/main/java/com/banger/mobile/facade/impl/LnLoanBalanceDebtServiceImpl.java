package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanBalanceDebtDAO;
import com.banger.mobile.domain.model.loan.LnLoanBalanceDebt;
import com.banger.mobile.facade.loan.LnLoanBalanceDebtService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class LnLoanBalanceDebtServiceImpl implements LnLoanBalanceDebtService {

	
	private LnLoanBalanceDebtDAO lnLoanBalanceDebtDAO;
	
	
	public void setLnLoanBalanceDebtDAO(LnLoanBalanceDebtDAO lnLoanBalanceDebtDAO) {
		this.lnLoanBalanceDebtDAO = lnLoanBalanceDebtDAO;
	}


	@Override
	public void deleteDebt(LnLoanBalanceDebt lnBalanceDebt) {
		
		lnLoanBalanceDebtDAO.deleteDebt(lnBalanceDebt);
	}


	@Override
	public List<LnLoanBalanceDebt> selectDebtByPrimary(
			LnLoanBalanceDebt lnLoanBalanceDebt) {
		
		return lnLoanBalanceDebtDAO.selectDebtByPrimary(lnLoanBalanceDebt);
	}


	@Override
	public LnLoanBalanceDebt insertAsset(LnLoanBalanceDebt lnLoanBalanceDebt) {
		if(StringUtils.isNotEmpty(lnLoanBalanceDebt.getType())){
			String type[] =lnLoanBalanceDebt.getType().split(",");
			lnLoanBalanceDebt.setType(type[0]);
			lnLoanBalanceDebt.setNote(type[1]);
		}
		return lnLoanBalanceDebtDAO.insertDebt(lnLoanBalanceDebt);
	}


	@Override
	public LnLoanBalanceDebt updateAsset(LnLoanBalanceDebt lnLoanBalanceDebt) {
		List<LnLoanBalanceDebt> debtList = lnLoanBalanceDebtDAO.selectDebtByPrimary(lnLoanBalanceDebt);
		LnLoanBalanceDebt debt=debtList.get(0);
		if(StringUtils.isNotEmpty(lnLoanBalanceDebt.getType())){
			String type[] =lnLoanBalanceDebt.getType().split(",");
			debt.setType(type[0]);
			debt.setNote(type[1]);
		}	
		debt.setName(lnLoanBalanceDebt.getName());
		
		debt.setValue(lnLoanBalanceDebt.getValue());
		return lnLoanBalanceDebtDAO.updateDebt(debt);
	}


	@Override
	public List<LnLoanBalanceDebt> selectDebtByPrimary(Integer loanBalanceId) {
		LnLoanBalanceDebt lnLoanBalanceDebt = new LnLoanBalanceDebt();
		lnLoanBalanceDebt.setLoanBalanceId(loanBalanceId);
		return lnLoanBalanceDebtDAO.selectDebtByPrimary(lnLoanBalanceDebt);
	}

	
}
