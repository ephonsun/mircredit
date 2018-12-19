/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanPledgeDao;
import com.banger.mobile.domain.model.loan.LnPledge;
import com.banger.mobile.facade.loan.LnLoanPledgeService;

import java.util.List;

/**
 * 
 * @author White Wolf
 * @version $Id: LnLoanPledgeServiceImpl.java, v 0.1 2016-1-12 上午11:10:11 White Wolf Exp $
 */
public class LnLoanPledgeServiceImpl implements LnLoanPledgeService {
	
	
	private LnLoanPledgeDao lnLoanPledgeDao;
	
	
	public void setLnLoanPledgeDao(LnLoanPledgeDao lnLoanPledgeDao) {
		this.lnLoanPledgeDao = lnLoanPledgeDao;
	}


	@Override
	public void insertLnLoanPledge(LnPledge lnPledge) {
		lnLoanPledgeDao.insertLnLoanPledge(lnPledge);
	}


	@Override
	public List<LnPledge> getLnLoanPledgeByLoanId(int loanId) {
		return lnLoanPledgeDao.selectLnPledgeByLoanId(loanId);
	}


	/** 
	 * @see com.banger.mobile.facade.loan.LnLoanPledgeService#rmLnLoanPledgeById(java.lang.Integer)
	 */
	@Override
	public void rmLnLoanPledgeById(Integer id) {
		lnLoanPledgeDao.deleteLnPledgeById(id);
	}


	@Override
	public void updateLnLoanPledgeById(LnPledge lnPledge) {
		lnLoanPledgeDao.updateLnLoanPledgeById(lnPledge);
	}


	@Override
	public LnPledge getLnLoanPledgeById(Integer pledgeId) {
		return lnLoanPledgeDao.getLnLoanPledgeById(pledgeId);
	}
	
	
	
}
