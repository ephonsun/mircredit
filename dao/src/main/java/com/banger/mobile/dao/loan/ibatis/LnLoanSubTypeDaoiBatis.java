/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款子类型-Dao-接口实现
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.dao.loan.ibatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.banger.mobile.dao.loan.LnLoanSubTypeDao;
import com.banger.mobile.domain.model.loan.LnLoanSubType;
import com.banger.mobile.domain.model.loan.LnLoanType;
import com.banger.mobile.ibatis.GenericDaoiBatis;

/**
 * @author QianJie
 * @version $Id: LnLoanSubTypeDaoiBatis.java,v 0.1 Feb 17, 2013 2:54:46 PM
 *          QianJie Exp $
 */
@SuppressWarnings("rawtypes")
public class LnLoanSubTypeDaoiBatis extends GenericDaoiBatis implements
		LnLoanSubTypeDao {

	@SuppressWarnings("unchecked")
	public LnLoanSubTypeDaoiBatis() {
		super(LnLoanSubType.class);
	}

	/**
	 * @param persistentClass
	 */
	@SuppressWarnings("unchecked")
	public LnLoanSubTypeDaoiBatis(Class persistentClass) {
		super(LnLoanSubType.class);
	}

	/**
	 * 查询所有的贷款子类型
	 */
	@SuppressWarnings("unchecked")
	public List<LnLoanSubType> getLnLoanSubTypes() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("selLoanSubTypes");
	}

	/**
	 * 通过id查询
	 */
	public LnLoanSubType getLoanSubTypeById(Integer lnId) {
		// TODO Auto-generated method stub
		return (LnLoanSubType) this.getSqlMapClientTemplate().queryForObject(
				"selLoanSubTypeById", lnId);
	}

	/**
	 * 通过name查询
	 */
	@SuppressWarnings("unchecked")
	public List<LnLoanSubType> getLoanSubTypeByName(String lnName) {
		// TODO Auto-generated method stub
		List<LnLoanSubType> list = new ArrayList<LnLoanSubType>();
		list = this.getSqlMapClientTemplate().queryForList(
				"selLoanSubTypeByName", lnName);
		return list != null ? list : null;
	}

	/**
	 * 通过sortNo查询
	 */
	public LnLoanSubType getLoanSubTypeBySortNo(Integer sortNo) {
		// TODO Auto-generated method stub
		return (LnLoanSubType) this.getSqlMapClientTemplate().queryForObject(
				"selLoanSubTypeBySortNo", sortNo);
	}

	/**
	 * 删除贷款子类型（伪删除）
	 */
	public void delLoanSubType(Integer id) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().delete("delLoanSubTypeById", id);
	}

	/**
	 * 更新贷款子类型
	 */
	public void updLoanSubType(LnLoanSubType ln) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("updLoanSubType", ln);
	}

	/**
	 * 批量更新
	 */
	public void batchUpdLoanSubType(List<LnLoanSubType> lns) {
		// TODO Auto-generated method stub
       this.executeBatchUpdate("updLoanSubType", lns);
	}

	/**
	 * 插入贷款子类型
	 */
	public void insLoanSubType(LnLoanSubType ln) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert("insLoanSubType", ln);
	}

	/**
	 * 通过Id或sortNo查询
	 */
	public LnLoanSubType getLoanSubTypeByAssign(LnLoanSubType ln) {
		// TODO Auto-generated method stub
		return (LnLoanSubType) this.getSqlMapClientTemplate().queryForObject(
				"selLoanSubTypeByAssign", ln);
	}

	/**
	 * 同过贷款父类型查询
	 */
	@SuppressWarnings("unchecked")
	public List<LnLoanSubType> getLnLoanSubTypesByType(LnLoanType ln) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(
				"selLoanSubTypesByAssign", ln);
	}

	/**
	 * 查找所有有用的贷款类型
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LnLoanSubType> getAllActivedSubType(Map<String, Object> paramMap) {
		return this.getSqlMapClientTemplate().queryForList(
				"getAllActivedSubType", paramMap);
	}

	/**
	 * 通过贷款类型ID获取贷款子类型
	 */
	@SuppressWarnings("unchecked")
	public List<LnLoanSubType> getLnLoanSubTypeByType(LnLoanSubType ln) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(
				"selLoanSubTypesByLoanTypeId", ln);
	}

}
