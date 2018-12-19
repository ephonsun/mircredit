package com.banger.mobile.dao.loan;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.base.loan.BaseLnLoanMonitor;
import com.banger.mobile.domain.model.loan.LnLoanMonitor;

/**
 * 
 * 
 * @author linkin
 * @version $Id: LnLoanMonitorDao.java, v 0.1 2016-1-10 下午3:03:09 linkin Exp $
 */
public interface LnLoanMonitorDao {
	
	public void insertLoanMonitor(BaseLnLoanMonitor lnLoanMonitor);
	public void updateLoanMonitor(BaseLnLoanMonitor loanMonitor);
	public void updateLoanMonitorfile(BaseLnLoanMonitor loanMonitor);
	public void deleteLoanMonitorById(Integer loanMonitorId);
	public BaseLnLoanMonitor getLoanMonitorById(int id);
	public List<LnLoanMonitor> getLoanMonitorByLoanId(int loanId);
	public void updateReadTag(Map<String,Object> map);
}
