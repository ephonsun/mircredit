/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款子类型-Dao-接口
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.dao.loan;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.loan.LnLoanSubType;
import com.banger.mobile.domain.model.loan.LnLoanType;

/**
 * @author QianJie
 * @version $Id: LnLoanSubTypeDao.java,v 0.1 Feb 17, 2013 2:53:58 PM QianJie Exp
 *          $
 */
public interface LnLoanSubTypeDao {
	/**
	 * 返回所有的贷款子类型
	 * 
	 * @return
	 */
	public List<LnLoanSubType> getLnLoanSubTypes();

	/**
	 * 根据父类型返回子类型
	 * 
	 * @param ln
	 * @return
	 */
	public List<LnLoanSubType> getLnLoanSubTypesByType(LnLoanType ln);

	/**
	 * 通过id获取贷款类型
	 * 
	 * @param lnId
	 * @return
	 */
	public LnLoanSubType getLoanSubTypeById(Integer lnId);

	/**
	 * 通过name获取贷款类型
	 * 
	 * @param lnName
	 * @return
	 */
	public List<LnLoanSubType> getLoanSubTypeByName(String lnName);

	/**
	 * 通过sortNo获取贷款类型
	 * 
	 * @param sortNo
	 * @return
	 */
	public LnLoanSubType getLoanSubTypeBySortNo(Integer sortNo);

	/**
	 * 通过指定获取贷款类型
	 * 
	 * @param ln
	 * @return
	 */
	public LnLoanSubType getLoanSubTypeByAssign(LnLoanSubType ln);

	/**
	 * 删除贷款类型
	 * 
	 * @param ln
	 */
	public void delLoanSubType(Integer Id);

	/**
	 * 更新贷款类型
	 * 
	 * @param ln
	 */
	public void updLoanSubType(LnLoanSubType ln);

    /**
     * 批量更新贷款类型
     */
	public void batchUpdLoanSubType(List<LnLoanSubType> lns);

	/**
	 * 插入贷款类型
	 * 
	 * @param ln
	 */
	public void insLoanSubType(LnLoanSubType ln);

	@SuppressWarnings("unchecked")
	List<LnLoanSubType> getAllActivedSubType(Map<String, Object> paramMap);

	/**
	 * 通过贷款类型ID获取贷款子类型
	 * 
	 * @param LnLoanSubType
	 * @return
	 */
	public List<LnLoanSubType> getLnLoanSubTypeByType(
			LnLoanSubType LnLoanSubType);
}
