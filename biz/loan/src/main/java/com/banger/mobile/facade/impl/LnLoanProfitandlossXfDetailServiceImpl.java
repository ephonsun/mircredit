package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanProfitandlossXfDetailDao;
import com.banger.mobile.domain.model.loan.LnLoanProfitandlossXfDetail;
import com.banger.mobile.facade.loan.LnLoanProfitandlossXfDetailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LnLoanProfitandlossXfDetailServiceImpl implements LnLoanProfitandlossXfDetailService{

	private LnLoanProfitandlossXfDetailDao lnLoanProfitandlossXfDetailDao;
		
	public void setLnLoanProfitandlossXfDetailDao(
			LnLoanProfitandlossXfDetailDao lnLoanProfitandlossXfDetailDao) {
		this.lnLoanProfitandlossXfDetailDao = lnLoanProfitandlossXfDetailDao;
	}

	
	public List<LnLoanProfitandlossXfDetail> selectXfDetailByPrimary(
			LnLoanProfitandlossXfDetail lnLoanProfitandlossXfDetail) {
		
		
		
		
		return lnLoanProfitandlossXfDetailDao.selectXfDetailByPrimary(lnLoanProfitandlossXfDetail);
	}

	
	public LnLoanProfitandlossXfDetail insertXfDetail(
			LnLoanProfitandlossXfDetail lnLoanProfitandlossXfDetail) {

		return lnLoanProfitandlossXfDetailDao.insertXfDetail(lnLoanProfitandlossXfDetail);
	}

	
	public LnLoanProfitandlossXfDetail updateXfDetail(
			LnLoanProfitandlossXfDetail lnLoanProfitandlossXfDetail) {
		String itemId = lnLoanProfitandlossXfDetail.getItemId();
		lnLoanProfitandlossXfDetail.setItemId(null);
		List<LnLoanProfitandlossXfDetail> inList = lnLoanProfitandlossXfDetailDao.selectXfDetailByPrimary(lnLoanProfitandlossXfDetail);
		LnLoanProfitandlossXfDetail in = inList.get(0); 
		in.setCurrentYear(lnLoanProfitandlossXfDetail.getCurrentYear());
		in.setItemDetailId(lnLoanProfitandlossXfDetail.getItemDetailId());
		in.setItemDetailName(lnLoanProfitandlossXfDetail.getItemDetailName());
		//in.setLoanProfitandlossId(lnLoanProfitandlossXfDetail.getLoanProfitandlossId());
		in.setNote(lnLoanProfitandlossXfDetail.getNote());
		in.setPriorYear(lnLoanProfitandlossXfDetail.getPriorYear());
		in.setProfitandlossJyDetailId(lnLoanProfitandlossXfDetail.getProfitandlossJyDetailId());
		in.setItemId(itemId);
		
		return lnLoanProfitandlossXfDetailDao.updateXfDetail(in);
	}

	
	public void deleteXfDetail(
			LnLoanProfitandlossXfDetail lnLoanProfitandlossXfDetail) {
		
		lnLoanProfitandlossXfDetailDao.deleteXfDetail(lnLoanProfitandlossXfDetail);
	}


	@Override
	public Map<String, Object> profitandlossXfDetail(Integer profitandlossJyId) {
		Map<String ,Object> map = new HashMap<String, Object>();
		LnLoanProfitandlossXfDetail lnLoanProfitandlossXfDetail = new LnLoanProfitandlossXfDetail();
		lnLoanProfitandlossXfDetail.setLoanProfitandlossId(profitandlossJyId);
		//主要收入
		lnLoanProfitandlossXfDetail.setItemId("173");
		List<LnLoanProfitandlossXfDetail> mainIncome=lnLoanProfitandlossXfDetailDao.selectXfDetailByPrimary(lnLoanProfitandlossXfDetail);
		map.put("mainIncome", mainIncome);
		
		//固定支出
		lnLoanProfitandlossXfDetail.setItemId("174");
		List<LnLoanProfitandlossXfDetail> fixedCosts=lnLoanProfitandlossXfDetailDao.selectXfDetailByPrimary(lnLoanProfitandlossXfDetail);
		map.put("fixedCosts", fixedCosts);
		
		//个人所得税
		lnLoanProfitandlossXfDetail.setItemId("175");
		List<LnLoanProfitandlossXfDetail> taxes=lnLoanProfitandlossXfDetailDao.selectXfDetailByPrimary(lnLoanProfitandlossXfDetail);
		map.put("taxes", taxes);
		
		//其他支出
		lnLoanProfitandlossXfDetail.setItemId("176");
		List<LnLoanProfitandlossXfDetail> otherCosts=lnLoanProfitandlossXfDetailDao.selectXfDetailByPrimary(lnLoanProfitandlossXfDetail);
		map.put("otherCosts", otherCosts);
		
		//其他收入
		lnLoanProfitandlossXfDetail.setItemId("177");
		List<LnLoanProfitandlossXfDetail> otherIncome=lnLoanProfitandlossXfDetailDao.selectXfDetailByPrimary(lnLoanProfitandlossXfDetail);
		map.put("otherIncome", otherIncome);
		
		
		return map;
	}
}
