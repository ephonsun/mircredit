package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnDeviationAnalsysisDao;
import com.banger.mobile.domain.model.loan.LnDeviationAnalsysis;
import com.banger.mobile.facade.loan.LnDeviationAnalsysisService;

import java.util.Map;


public class LnDeviationAnalsysisServiceImpl implements LnDeviationAnalsysisService {

	private LnDeviationAnalsysisDao lnDeviationAnalsysisDao;

	public LnDeviationAnalsysisDao getLnDeviationAnalsysisDao() {
		return lnDeviationAnalsysisDao;
	}

	public void setLnDeviationAnalsysisDao(LnDeviationAnalsysisDao lnDeviationAnalsysisDao) {
		this.lnDeviationAnalsysisDao = lnDeviationAnalsysisDao;
	}

	@Override
	public void creatrLnDeviationAnalsysis(Map<String,Object> paramMap) {
		lnDeviationAnalsysisDao.insertLnDeviationAnalsysis(paramMap);
	}

	@Override
	public void alterLnDeviationAnalsysis(Map<String, Object> paramMap) {
		lnDeviationAnalsysisDao.updateLnDeviationAnalsysis(paramMap);
	}

	@Override
	public LnDeviationAnalsysis  selectById(Integer loanId) {
		LnDeviationAnalsysis lnDeviationAnalsysis =new LnDeviationAnalsysis();
		lnDeviationAnalsysis=lnDeviationAnalsysisDao.queryOneLnDeviationAnalsysis(loanId);
		return lnDeviationAnalsysis;
	}


}
