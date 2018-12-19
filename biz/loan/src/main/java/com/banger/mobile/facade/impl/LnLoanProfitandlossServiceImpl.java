package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanProfitandlossDao;
import com.banger.mobile.dao.loan.LnLoanProfitandlossJyDetailDao;
import com.banger.mobile.domain.model.loan.LnLoanProfitandloss;
import com.banger.mobile.facade.loan.LnLoanProfitandlossService;

import java.util.ArrayList;
import java.util.List;


public class LnLoanProfitandlossServiceImpl implements LnLoanProfitandlossService{

	private LnLoanProfitandlossDao lnLoanProfitandlossDao;
	private LnLoanProfitandlossJyDetailDao lnLoanProfitandlossJyDetailDao;
	
	
	
	public void setLnLoanProfitandlossJyDetailDao(
			LnLoanProfitandlossJyDetailDao lnLoanProfitandlossJyDetailDao) {
		this.lnLoanProfitandlossJyDetailDao = lnLoanProfitandlossJyDetailDao;
	}

	public void setLnLoanProfitandlossDao(
			LnLoanProfitandlossDao lnLoanProfitandlossDao) {
		this.lnLoanProfitandlossDao = lnLoanProfitandlossDao;
	}

	@Override
	public LnLoanProfitandloss insertProfitandloss(
			LnLoanProfitandloss lnLoanProfitandloss) {

		return lnLoanProfitandlossDao.insertProfitandloss(lnLoanProfitandloss);
	}

	@Override
	public LnLoanProfitandloss updateProfitandlossBaseInfo(
			LnLoanProfitandloss lnLoanProfitandloss) {
		List<LnLoanProfitandloss> inList = lnLoanProfitandlossDao.selectProfitandlossByPrimary(lnLoanProfitandloss);
		LnLoanProfitandloss in = inList.get(0);
		//改变月份时删除数据
		if(lnLoanProfitandloss.getFlage()!=null&&lnLoanProfitandloss.getFlage()){			
			lnLoanProfitandlossJyDetailDao.deleteXfDetailByProfitandlossID(lnLoanProfitandloss.getLoanProfitandlossId());
		}
		in.setBeginMonth(lnLoanProfitandloss.getBeginMonth());
		in.setEndMonth(lnLoanProfitandloss.getEndMonth());	
		in.setEndRate(lnLoanProfitandloss.getEndRate());
		
		return lnLoanProfitandlossDao.updateProfitandlossBaseInfo(in);
	}
	
	public LnLoanProfitandloss updateProfitandloss(
			LnLoanProfitandloss lnLoanProfitandloss) {
		
		return lnLoanProfitandlossDao.updateProfitandloss(lnLoanProfitandloss);
	}
	
	@Override
	public List<LnLoanProfitandloss> selectProfitandlossByPrimary(
			LnLoanProfitandloss lnLoanProfitandloss) {
		List<LnLoanProfitandloss> balanceList=lnLoanProfitandlossDao.selectProfitandlossByPrimary(lnLoanProfitandloss);
		if(balanceList==null||balanceList.size()<=0){
			balanceList=new ArrayList<LnLoanProfitandloss>();
			LnLoanProfitandloss	balance=lnLoanProfitandlossDao.insertProfitandloss(lnLoanProfitandloss);
			balanceList.add(balance);
		}
		
		
		return balanceList;
	}

	@Override
	public List<LnLoanProfitandloss> selectProfitandlossByPrimary(Integer loanId) {
		LnLoanProfitandloss loanProfitandloss =new LnLoanProfitandloss();
		loanProfitandloss.setLoanId(loanId);
		List<LnLoanProfitandloss> balanceList=lnLoanProfitandlossDao.selectProfitandlossByPrimary(loanProfitandloss);
		return balanceList;
	
	}

   
}
