package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnBusinessModelDao;
import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.facade.loan.LnBusinessModelService;

public class LnBusinessModelServiceImpl implements LnBusinessModelService {

	private LnBusinessModelDao lnBusinessModelDao;

	public void setLnBusinessModelDao(LnBusinessModelDao lnBusinessModelDao) {
		this.lnBusinessModelDao = lnBusinessModelDao;
	}

	@Override
	public LnBusinessModel insertLnBusinessModel(LnBusinessModel lnBusinessModel) {
		LnBusinessModel isExist = lnBusinessModelDao.selectByPrimary(lnBusinessModel);
		if(isExist==null){
			LnBusinessModel ln=	lnBusinessModelDao.insertLnBusinessModel(lnBusinessModel);
			return ln;
		}
		return isExist;
		
	}

	@Override
	public LnBusinessModel updateLnBusinessModel(LnBusinessModel lnBusinessModel) {
		LnBusinessModel ln = lnBusinessModelDao.selectByPrimary(lnBusinessModel);
		ln.setChange(lnBusinessModel.getChange());
		ln.setUpperReaches(lnBusinessModel.getUpperReaches());
		ln.setLowerReaches(lnBusinessModel.getLowerReaches());
		ln.setOfficeLeaseContract(lnBusinessModel.getOfficeLeaseContract());
		ln.setSorftInfo(lnBusinessModel.getSorftInfo());
		ln.setWorkFlow(lnBusinessModel.getWorkFlow());
		ln.setOther(lnBusinessModel.getOther());
		ln.setBusinessHistory(lnBusinessModel.getBusinessHistory());
		ln.setBusinessOrg(lnBusinessModel.getBusinessOrg());
		ln.setBusinessFinance(lnBusinessModel.getBusinessFinance());
		lnBusinessModelDao.updateLnBusinessModel(ln);
		return ln;
	}

	@Override
	public LnBusinessModel selectByPrimary(LnBusinessModel lnBusinessModel) {
		
		return  lnBusinessModelDao.selectByPrimary(lnBusinessModel);
		
	}

	@Override
	public LnBusinessModel selectByPrimary(Integer loanId) {
		LnBusinessModel lnBusinessModel= new LnBusinessModel();
		lnBusinessModel.setLoanId(loanId);
		return  lnBusinessModelDao.selectByPrimary(lnBusinessModel);
	}

	
}
