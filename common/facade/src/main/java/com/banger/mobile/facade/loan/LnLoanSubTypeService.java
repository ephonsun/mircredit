/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款子类型-Service-接口
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.facade.loan;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.loan.LnLoanSubType;
import com.banger.mobile.domain.model.loan.LnLoanType;

/**
 * @author QianJie
 * @version $Id: LnLoanSubTypeService.java,v 0.1 Feb 17, 2013 3:41:33 PM QianJie
 *          Exp $
 */
public interface LnLoanSubTypeService {
	/**
	 * 获取所有子类型
	 * 
	 * @return
	 */
	public List<LnLoanSubType> getLnLoanSubTypes();

	/**
	 * 通过父类型获取子类型
	 * 
	 * @param ln
	 * @return
	 */
	public List<LnLoanSubType> getLnLoanSubTypesByAsSubTypes(LnLoanType ln);

	/**
	 * 通过父类型ID获取子类型
	 * @param ln
	 * @return
	 */
	public List<LnLoanSubType> getLnLoanSubTypesBySubType(LnLoanSubType ln);
	/**
	 * 通过id查询
	 * 
	 * @param lnId
	 * @return
	 */
	public LnLoanSubType getLnLoanSubTypeById(Integer lnId);

	/**
	 * 通过name查询
	 * 
	 * @param lnName
	 * @return
	 */
	public List<LnLoanSubType> getLnLoanSubTypeByName(String lnName);

	/**
	 * 通过sortNo查询
	 * 
	 * @param sortNo
	 * @return
	 */
	public LnLoanSubType getLnLoanSubTypeBySortNo(Integer sortNo);

	/**
	 * 通过指定查询
	 * 
	 * @param ln
	 * @return
	 */
	public LnLoanSubType getLnLoanSubTypeByAssign(LnLoanSubType ln);

	// 通过ID的增删改操作
	public void delLnLoanSubType(Integer Id);

	public void updLnLoanSubType(LnLoanSubType ln);
	
	/**
	 * 批量更新
	 * @param lns
	 */
	public void batchUpdLnLoanSubType(List<LnLoanSubType> lns);
    /**
     * 移动顺序
     * @param ln
     * @param moveflag
     */
	public List<LnLoanSubType> moveUpOrDown(LnLoanSubType ln,String moveflag);
	
	public void insLnLoanSubType(LnLoanSubType ln);

    List<LnLoanSubType> getAllActivedSubType(Map<String,Object> paramMap);
}
