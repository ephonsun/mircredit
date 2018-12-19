package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanBalanceDao;
import com.banger.mobile.domain.model.loan.LnLoanBalance;
import com.banger.mobile.facade.loan.LnLoanBalanceService;

public class LnLoanBalanceServiceImpl implements LnLoanBalanceService {

	private LnLoanBalanceDao lnLoanBalanceDao;
	
	public void setLnLoanBalanceDao(LnLoanBalanceDao lnLoanBalanceDao) {
		this.lnLoanBalanceDao = lnLoanBalanceDao;
	}

	

	@Override
	public LnLoanBalance insertBalance(LnLoanBalance lnLoanBalance) {
		LnLoanBalance isExist = lnLoanBalanceDao.selectBalanceByPrimary(lnLoanBalance);
		if(isExist==null){
			return lnLoanBalanceDao.insertBalance(lnLoanBalance);
		}
		return isExist;
	}

	@Override
	public LnLoanBalance updateBalance(LnLoanBalance lnLoanBalance) {
		
		return lnLoanBalanceDao.updateBalance(lnLoanBalance);
	}



	@Override
	public LnLoanBalance selectBalanceByPrimary(LnLoanBalance lnLoanBalance) {
		LnLoanBalance balance=lnLoanBalanceDao.selectBalanceByPrimary(lnLoanBalance);
		if(balance==null){
			balance=lnLoanBalanceDao.insertBalance(lnLoanBalance);
		}
		
		return balance;
	}



	@Override
	public LnLoanBalance selectBalanceByPrimary(Integer loanId) {
		LnLoanBalance lnLoanBalance =new LnLoanBalance();
		lnLoanBalance.setLoanId(loanId);
		return lnLoanBalanceDao.selectBalanceByPrimary(lnLoanBalance);
	}

	
}
