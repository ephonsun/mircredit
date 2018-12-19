package com.banger.mobile.dao.loan.ibatis;

import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.loan.LnLoanMonitorDao;
import com.banger.mobile.domain.model.base.loan.BaseLnLoanMonitor;
import com.banger.mobile.domain.model.loan.LnLoanMonitor;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * 贷后监控
 * 
 * @author linkin
 * @version $Id: LnLoanMonitorDaoiBatis.java, v 0.1 2016-1-10 下午3:18:18 linkin Exp $
 */
public class LnLoanMonitorDaoiBatis extends GenericDaoiBatis implements
		LnLoanMonitorDao {

	public LnLoanMonitorDaoiBatis() {
		super(LnLoanMonitor.class);
	}

	/**
	 * @param persistentClass
	 */
	public LnLoanMonitorDaoiBatis(Class persistentClass) {
		super(LnLoanMonitor.class);
	}


	@Override
	public void insertLoanMonitor(BaseLnLoanMonitor lnLoanMonitor) {
		this.getSqlMapClientTemplate().insert("insertLoanMonitor", lnLoanMonitor);
		
	}

	@Override
	public void updateLoanMonitor(BaseLnLoanMonitor loanMonitor) {
		this.getSqlMapClientTemplate().update("updateLoanMonitor", loanMonitor);
		
	}
	@Override
	public void updateLoanMonitorfile(BaseLnLoanMonitor loanMonitor) {
		this.getSqlMapClientTemplate().update("updateLoanMonitorfile", loanMonitor);
		
	}
	@Override
	public void deleteLoanMonitorById(Integer loanMonitorId) {
		  this.getSqlMapClientTemplate().delete("deleteLoanMonitor", loanMonitorId);
		
	}

	@Override
	public BaseLnLoanMonitor getLoanMonitorById(int id) {
		// TODO Auto-generated method stub
		return (BaseLnLoanMonitor) this.getSqlMapClientTemplate().queryForObject(
				"getLoanMonitorById", id);
	}

	@Override
	public List<LnLoanMonitor> getLoanMonitorByLoanId(int loanId) {
		return this.getSqlMapClientTemplate().queryForList(
				"getLoanMonitorListByLoanId", loanId);
	}
	
	public void updateReadTag(Map<String,Object> map){
		this.getSqlMapClientTemplate().update("updateReadTag", map);
	}
}
