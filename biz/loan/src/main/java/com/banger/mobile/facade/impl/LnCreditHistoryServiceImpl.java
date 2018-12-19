/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnCreditHistoryDao;
import com.banger.mobile.domain.model.loan.LnCreditHistory;
import com.banger.mobile.facade.loan.LnCreditHistoryService;

import java.util.List;

/**
 * 
 * @author White Wolf
 * @version $Id: LnCreditHistoryServiceImpl.java, v 0.1 2016-1-7 下午3:06:08 White Wolf Exp $
 */
public class LnCreditHistoryServiceImpl implements LnCreditHistoryService {
	
	private LnCreditHistoryDao lnCreditHistoryDao;
	/** 
	 * @see com.banger.mobile.facade.loan.LnCreditHistoryService#insertCreditHistory(com.banger.mobile.domain.model.loan.LnCreditHistory)
	 */
	@Override
	public void insertCreditHistory(LnCreditHistory creditHistory) {
		
		lnCreditHistoryDao.insertCreditHistory(creditHistory);
	}
	
	
	@Override
	public List<LnCreditHistory> getCreditHistoryByLoanId(int loanId) {
		return lnCreditHistoryDao.selectCreditHistoryByLoanId(loanId);
	}
	
	@Override
	public void updateCreditHistory(LnCreditHistory lnCreditHistory){
		this.lnCreditHistoryDao .updateCreditHistory(lnCreditHistory);
	}
	
	
	@Override
	public void rmCreditHistory(Integer chId) {
		lnCreditHistoryDao.deleteCreditHistoryById(chId);
	}


	@Override
	public void deleteByLoanId(Integer loanId) {
		lnCreditHistoryDao.deleteByLoanId(loanId);
	}


	public LnCreditHistoryDao getLnCreditHistoryDao() {
		return lnCreditHistoryDao;
	}
	public void setLnCreditHistoryDao(LnCreditHistoryDao lnCreditHistoryDao) {
		this.lnCreditHistoryDao = lnCreditHistoryDao;
	}
	
	
}
