/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnValidationRightsDao;
import com.banger.mobile.domain.model.loan.LnValidationRights;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 *
 */
public class LnValidationRightsDaoiBatis extends GenericDaoiBatis implements LnValidationRightsDao{

	public LnValidationRightsDaoiBatis(){
        super(LnValidationRightsDaoiBatis.class);
    }

	/**
	 * @param persistentClass
	 */
	public LnValidationRightsDaoiBatis(Class persistentClass) {
		super(persistentClass);
	}


	@Override
	public void insertLnValidationRights(LnValidationRights lnValidationRights) {
		this.getSqlMapClientTemplate().insert("insertValidationRights", lnValidationRights);
	}

	@Override
	public void deleteLnValidationRightsById(Integer loanId) {
		this.getSqlMapClientTemplate().delete("deleteValidationRights", loanId);
	}

	@Override
	public void updateLnValidationRightsById(LnValidationRights lnValidationRights) {
		this.getSqlMapClientTemplate().update("updateValidationRights", lnValidationRights);
	}

	@Override
	public LnValidationRights getLnValidationRightsById(Integer loanId) {
		return (LnValidationRights) this.getSqlMapClientTemplate().queryForObject("getLnValidationRightsById", loanId);
	}
}
