/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.loan.LnLoanPledgeDao;
import com.banger.mobile.domain.model.loan.LnPledge;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * 
 * @author White Wolf
 * @version $Id: LnLoanPledgeDaoiBatis.java, v 0.1 2016-1-12 上午11:19:44 White Wolf Exp $
 */
public class LnLoanPledgeDaoiBatis  extends GenericDaoiBatis implements LnLoanPledgeDao{

	public LnLoanPledgeDaoiBatis(){
        super(LnLoanPledgeDaoiBatis.class);
    }

	/**
	 * @param persistentClass
	 */
	public LnLoanPledgeDaoiBatis(Class persistentClass) {
		super(persistentClass);
	}

	/** 
	 * @see com.banger.mobile.dao.loan.LnLoanPledgeDao#insertLnLoanPledge(com.banger.mobile.domain.model.loan.LnPledge)
	 */
	@Override
	public void insertLnLoanPledge(LnPledge lnPledge) {
		this.getSqlMapClientTemplate().insert("insertLnPledge", lnPledge);
	}

	/** 
	 * @see com.banger.mobile.dao.loan.LnLoanPledgeDao#selectLnPledgeByLoanId(int)
	 */
	@Override
	public List<LnPledge> selectLnPledgeByLoanId(int loanId) {
		return this.getSqlMapClientTemplate().queryForList("selectLnPledgeByLoanId", loanId);
	}

	/** 
	 * @see com.banger.mobile.dao.loan.LnLoanPledgeDao#deleteLnPledgeById(java.lang.Integer)
	 */
	@Override
	public void deleteLnPledgeById(Integer id) {
		this.getSqlMapClientTemplate().delete("deleteLnPledgeById", id);
	}

	@Override
	public void updateLnLoanPledgeById(LnPledge lnPledge) {
		this.getSqlMapClientTemplate().update("updateLnLoanPledgeById", lnPledge);
	}

	@Override
	public LnPledge getLnLoanPledgeById(Integer pledgeId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("pledgeId", pledgeId);
		return (LnPledge) this.getSqlMapClientTemplate().queryForObject("getLnLoanPledgeById", param);
	}
	
	

}
