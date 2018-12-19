package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanMonitorDao;
import com.banger.mobile.domain.model.base.loan.BaseLnLoanMonitor;
import com.banger.mobile.domain.model.loan.LnLoanMonitor;
import com.banger.mobile.facade.loan.LnLoanMonitorService;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author linkin
 * @version $Id: LnLoanMonitorServiceImpl.java, v 0.1 2016-1-10 下午3:38:07 linkin Exp $
 */
public class LnLoanMonitorServiceImpl implements LnLoanMonitorService {

	private LnLoanMonitorDao lnLoanMonitorDao;

	@Override
	public List<LnLoanMonitor> getLoanMonitorList(Integer loanId) {
		return 	lnLoanMonitorDao.getLoanMonitorByLoanId(loanId);
	}

	public LnLoanMonitorDao getLnLoanMonitorDao() {
		return lnLoanMonitorDao;
	}

	public void setLnLoanMonitorDao(LnLoanMonitorDao lnLoanMonitorDao) {
		this.lnLoanMonitorDao = lnLoanMonitorDao;
	}

	@Override
	public void createLoanMonitor(BaseLnLoanMonitor loanMonitor) {
		// TODO Auto-generated method stub
		 lnLoanMonitorDao.insertLoanMonitor(loanMonitor);
	}

	@Override
	public void updateLoanMonitor(BaseLnLoanMonitor loanMonitor) {
		// TODO Auto-generated method stub
		lnLoanMonitorDao.updateLoanMonitor(loanMonitor);
	}
	@Override
	public void updateLoanMonitorfile(BaseLnLoanMonitor loanMonitor) {
		// TODO Auto-generated method stub
		lnLoanMonitorDao.updateLoanMonitorfile(loanMonitor);
	}
	
	@Override
	public BaseLnLoanMonitor getLoanMonitor(Integer loanLmId) {
		return 	lnLoanMonitorDao.getLoanMonitorById(loanLmId);
	}
	
	@Override
	public void updateReadTag(Map<String,Object> map){
		lnLoanMonitorDao.updateReadTag(map);
	}
	


}
