package com.banger.mobile.dao.loan.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.loan.LnBusinessModelDao;
import com.banger.mobile.dao.loan.LnLoanHistoryDao;
import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.domain.model.loan.LnLoanHistory;
import com.banger.mobile.ibatis.GenericDaoiBatis;

public class LnLoanHistoryDaoiBatis extends GenericDaoiBatis implements LnLoanHistoryDao{

	public LnLoanHistoryDaoiBatis(Class persistentClass) {
		super(LnLoanHistory.class);
	}

	public LnLoanHistoryDaoiBatis() {
		super(LnLoanHistory.class);
	}

	@Override
	public LnLoanHistory selectByPrimary(LnLoanHistory lnLoanHistory) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("loanId", lnLoanHistory.getLoanId());
		param.put("historyId", lnLoanHistory.getHistoryId());
		LnLoanHistory list =(LnLoanHistory) this.getSqlMapClientTemplate().queryForObject(
				"selectByLnLoanHistoryPrimary",param);
				return list;
				
	}

	@Override
	public LnLoanHistory insertLoanHistory(LnLoanHistory lnLoanHistory) {
		Integer	lnTer=(Integer) this.getSqlMapClientTemplate().insert("insertLnLoanHistory",lnLoanHistory);
		LnLoanHistory ln = new LnLoanHistory();
		ln.setHistoryId(lnTer);
		return ln;
	}

	@Override
	public int updateLoanHistory(LnLoanHistory ln) {
		
		return this.getSqlMapClientTemplate().update("updateLnLoanHistory", ln);
	}

	
	
}
