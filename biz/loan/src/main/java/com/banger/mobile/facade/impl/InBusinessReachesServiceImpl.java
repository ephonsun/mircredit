package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.InBusinessReachesDao;
import com.banger.mobile.domain.model.loan.InBusinessReaches;
import com.banger.mobile.facade.loan.InBusinessReachesService;

import java.util.ArrayList;
import java.util.List;


public class InBusinessReachesServiceImpl implements InBusinessReachesService {

	private InBusinessReachesDao inBusinessReachesDao;


	@Override
	public void  creatrInBusinessReaches(InBusinessReaches inBusinessReaches) {
		inBusinessReachesDao.insertBusinessReaches(inBusinessReaches);
	}

	@Override
	public List<InBusinessReaches>  selectReachesType(Integer reachesType ,Integer loanId) {
		List<InBusinessReaches> listInBusinessReaches = new ArrayList<InBusinessReaches>();

		listInBusinessReaches=inBusinessReachesDao.selectByyReachesType(reachesType,loanId);
		return listInBusinessReaches;
	}

	@Override
	public List<InBusinessReaches> selectbbReachesType(Integer loanId) {
		return inBusinessReachesDao.selectbReachesType(loanId);
	}

	@Override
	public void  deleteInBusinessReaches(Integer id) {

		inBusinessReachesDao.deleteInBusinessReaches(id);
	}

	public InBusinessReachesDao getInBusinessReachesDao() {
		return inBusinessReachesDao;
	}

	public void setInBusinessReachesDao(InBusinessReachesDao inBusinessReachesDao) {
		this.inBusinessReachesDao = inBusinessReachesDao;
	}
}
