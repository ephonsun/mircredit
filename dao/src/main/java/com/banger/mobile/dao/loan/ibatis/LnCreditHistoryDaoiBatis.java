/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan.ibatis;

import java.util.List;

import com.banger.mobile.dao.loan.LnCreditHistoryDao;
import com.banger.mobile.domain.model.loan.LnCreditHistory;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * 
 * @author White Wolf
 * @version $Id: LnCreditHistoryDaoiBatis.java, v 0.1 2016-1-7 下午2:48:37 White Wolf Exp $
 */
public class LnCreditHistoryDaoiBatis extends GenericDaoiBatis implements LnCreditHistoryDao {
	
	public LnCreditHistoryDaoiBatis(){
        super(LnCreditHistoryDaoiBatis.class);
    }

    public LnCreditHistoryDaoiBatis(Class persistentClass){
        super(persistentClass);
    }
    
	@Override
	public void insertCreditHistory(LnCreditHistory lnCreditHistory) {
		this.getSqlMapClientTemplate().insert("insertLnCreditHistory",lnCreditHistory);
	}

	@Override
	public List<LnCreditHistory> selectCreditHistoryByLoanId(int loanId) {
		return this.getSqlMapClientTemplate().queryForList("selectCreditHistoryByLoanId", loanId);
		
	}

	@Override
	public void deleteCreditHistoryById(Integer creditHistoryId) {
		this.getSqlMapClientTemplate().delete("deleteCreditHistoryById",creditHistoryId);
	}
	
	public void updateCreditHistory(LnCreditHistory lnCreditHistory){
		this.getSqlMapClientTemplate().update("updateLnCreditHistory",lnCreditHistory);
	}

	@Override
	public void deleteByLoanId(Integer loanId) {
		this.getSqlMapClientTemplate().delete("deleteByLoanId",loanId);
	}
	
	
	
	
	
}
