package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanHistoryDao;
import com.banger.mobile.domain.model.loan.LnLoanHistory;
import com.banger.mobile.facade.loan.LnLoanHistoryService;

public class LnLoanHistoryServiceImpl implements LnLoanHistoryService {

	private LnLoanHistoryDao lnLoanHistoryDao;
	
	
	public void setLnLoanHistoryDao(LnLoanHistoryDao lnLoanHistoryDao) {
		this.lnLoanHistoryDao = lnLoanHistoryDao;
	}


	public LnLoanHistory selectByPrimary(LnLoanHistory lnLoanHistory) {
		
		return lnLoanHistoryDao.selectByPrimary(lnLoanHistory);
	}

	
	public LnLoanHistory insertLoanHistory(LnLoanHistory lnLoanHistory) {
		LnLoanHistory isExist = lnLoanHistoryDao.selectByPrimary(lnLoanHistory);
		if(isExist==null){
			return lnLoanHistoryDao.insertLoanHistory(lnLoanHistory);
		}
		return isExist;
		
	}

	
	public LnLoanHistory updateLoanHistory(LnLoanHistory lnLoanHistory) {
		
		
		LnLoanHistory ln = lnLoanHistoryDao.selectByPrimary(lnLoanHistory);
		ln.setExplanationAmount(lnLoanHistory.getExplanationAmount());
		ln.setFamilyInfo(lnLoanHistory.getFamilyInfo());
		ln.setFlowPrinciple(lnLoanHistory.getFlowPrinciple());
		ln.setGuaranteeInfo(lnLoanHistory.getGuaranteeInfo());
		ln.setOtherConditions(lnLoanHistory.getOtherConditions());
		ln.setSpecialExplanation(lnLoanHistory.getSpecialExplanation());
		ln.setWorkHistory(lnLoanHistory.getWorkHistory());
		lnLoanHistoryDao.updateLoanHistory(ln);
		return ln;
	}


	@Override
	public LnLoanHistory selectByPrimary(Integer loanId) {
		LnLoanHistory lnLoanHistory=new LnLoanHistory();
		lnLoanHistory.setLoanId(loanId);
		return lnLoanHistoryDao.selectByPrimary(lnLoanHistory);
	}


	
	
}
