package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanProfitandlossJyDetailDao;
import com.banger.mobile.domain.model.loan.LnLoanProfitandlossJyDetail;
import com.banger.mobile.facade.loan.LnLoanProfitandlossJyDetailService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LnLoanProfitandlossJyDetailServiceImpl implements LnLoanProfitandlossJyDetailService{

	private LnLoanProfitandlossJyDetailDao lnLoanProfitandlossJyDetailDao;
	
	
	public void setLnLoanProfitandlossJyDetailDao(
			LnLoanProfitandlossJyDetailDao lnLoanProfitandlossJyDetailDao) {
		this.lnLoanProfitandlossJyDetailDao = lnLoanProfitandlossJyDetailDao;
	}

	public void deleteXfDetail(
			LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail) {
		lnLoanProfitandlossJyDetailDao.deleteXfDetail(lnLoanProfitandlossJyDetail);
		
	}

	@Override
	public List<LnLoanProfitandlossJyDetail> selectJyDetaiByPrimary(
			LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail) {
		
		return lnLoanProfitandlossJyDetailDao.selectJyDetaiByPrimary(lnLoanProfitandlossJyDetail);
	}

	@Override
	public LnLoanProfitandlossJyDetail insertJyDetai(
			LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail) {
		
		return lnLoanProfitandlossJyDetailDao.insertJyDetai(lnLoanProfitandlossJyDetail);
	}

	@Override
	public LnLoanProfitandlossJyDetail updateXfJyDetai(
			LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail) {
		String itemId = lnLoanProfitandlossJyDetail.getItemId();
		lnLoanProfitandlossJyDetail.setItemId(null);
		List<LnLoanProfitandlossJyDetail> inList=lnLoanProfitandlossJyDetailDao.selectJyDetaiByPrimary(lnLoanProfitandlossJyDetail);
		LnLoanProfitandlossJyDetail in = inList.get(0);
		in.setItemDetailId(lnLoanProfitandlossJyDetail.getItemDetailId());
		in.setItemDetailName(lnLoanProfitandlossJyDetail.getItemDetailName());
		in.setItemId(itemId);
		in.setMonth1(lnLoanProfitandlossJyDetail.getMonth1());
		in.setMonth2(lnLoanProfitandlossJyDetail.getMonth2());
		in.setMonth3(lnLoanProfitandlossJyDetail.getMonth3());
		in.setMonth4(lnLoanProfitandlossJyDetail.getMonth4());
		in.setMonth5(lnLoanProfitandlossJyDetail.getMonth5());
		in.setMonth6(lnLoanProfitandlossJyDetail.getMonth6());
		in.setMonth7(lnLoanProfitandlossJyDetail.getMonth7());
		in.setMonth8(lnLoanProfitandlossJyDetail.getMonth8());
		in.setMonth9(lnLoanProfitandlossJyDetail.getMonth9());
		in.setMonth10(lnLoanProfitandlossJyDetail.getMonth10());
		in.setMonth11(lnLoanProfitandlossJyDetail.getMonth11());
		in.setMonth12(lnLoanProfitandlossJyDetail.getMonth12());
		in.setMonthRep(lnLoanProfitandlossJyDetail.getMonthRep());
		
		
		return lnLoanProfitandlossJyDetailDao.updateXfJyDetai(in);
	}

	@Override
	public Map<String, Object> profitandlossJyDetail(Integer loanProfitandlossId) {
		Map map = new HashMap();
		LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail = new LnLoanProfitandlossJyDetail();
		lnLoanProfitandlossJyDetail.setLoanProfitandlossId(loanProfitandlossId);
		//收入
		lnLoanProfitandlossJyDetail.setItemId("178");
		List<LnLoanProfitandlossJyDetail> income=lnLoanProfitandlossJyDetailDao.selectJyDetaiByPrimary(lnLoanProfitandlossJyDetail);
		map.put("income", income);
		
		//可变成本
		lnLoanProfitandlossJyDetail.setItemId("179");
		List<LnLoanProfitandlossJyDetail> variableCost=lnLoanProfitandlossJyDetailDao.selectJyDetaiByPrimary(lnLoanProfitandlossJyDetail);
		map.put("variableCost", variableCost);
		
		//固定成本
		lnLoanProfitandlossJyDetail.setItemId("180");
		List<LnLoanProfitandlossJyDetail> grossProfit=lnLoanProfitandlossJyDetailDao.selectJyDetaiByPrimary(lnLoanProfitandlossJyDetail);
		map.put("grossProfit", grossProfit);
		
		//所得税
		lnLoanProfitandlossJyDetail.setItemId("181");
		List<LnLoanProfitandlossJyDetail> fixedCost=lnLoanProfitandlossJyDetailDao.selectJyDetaiByPrimary(lnLoanProfitandlossJyDetail);
		map.put("fixedCost", fixedCost);
		
		//其他支出
		lnLoanProfitandlossJyDetail.setItemId("182");
		List<LnLoanProfitandlossJyDetail> preTaxProfit=lnLoanProfitandlossJyDetailDao.selectJyDetaiByPrimary(lnLoanProfitandlossJyDetail);
		map.put("preTaxProfit", preTaxProfit);
		
		//其也收入
		lnLoanProfitandlossJyDetail.setItemId("183");
		List<LnLoanProfitandlossJyDetail> netProfit=lnLoanProfitandlossJyDetailDao.selectJyDetaiByPrimary(lnLoanProfitandlossJyDetail);
		map.put("netProfit", netProfit);
		
		//银行账户流入
		lnLoanProfitandlossJyDetail.setItemId("184");
		List<LnLoanProfitandlossJyDetail> disposableMoney=lnLoanProfitandlossJyDetailDao.selectJyDetaiByPrimary(lnLoanProfitandlossJyDetail);
		map.put("disposableMoney", disposableMoney);
		
		return map;
	}

   
}
