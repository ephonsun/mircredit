/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnValidationItemDao;
import com.banger.mobile.domain.model.loan.LnValidationItem;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class LnValidationItemDaoiBatis extends GenericDaoiBatis implements LnValidationItemDao{

	public LnValidationItemDaoiBatis(){
        super(LnValidationItemDaoiBatis.class);
    }

	/**
	 * @param persistentClass
	 */
	public LnValidationItemDaoiBatis(Class persistentClass) {
		super(persistentClass);
	}


	@Override
	public void insertLnValidationItem(LnValidationItem lnValidationItem) {
		this.getSqlMapClientTemplate().insert("insertValidationItem", lnValidationItem);
	}

	@Override
	public void deleteLnValidationItemById(Integer id) {
		this.getSqlMapClientTemplate().delete("deleteValidationItem", id);
	}

	@Override
	public void updateLnValidationItemById(LnValidationItem lnValidationItem) {
		this.getSqlMapClientTemplate().update("updateValidationItem", lnValidationItem);
	}

	@Override
	public LnValidationItem getLnValidationItemById(Integer id) {
		return (LnValidationItem) this.getSqlMapClientTemplate().queryForObject("getLnValidationItemById", id);
	}

	@Override
	public List<LnValidationItem> getLnValidationItemByLoanId(Integer loanId, String type) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("loanId", loanId);
		param.put("type", type);
		return this.getSqlMapClientTemplate().queryForList("getLnValidationItemByLoanId",param);
	}
}
