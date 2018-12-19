/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.facade.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnPledge;

/**
 * 贷款抵制押物
 * @author White Wolf
 * @version $Id: LnLoanPledge.java, v 0.1 2016-1-12 上午11:01:34 White Wolf Exp $
 */
public interface LnLoanPledgeService {
	
	/**
	 * 创建抵质押物
	 * 
	 * @param creditHistory
	 */
	void insertLnLoanPledge(LnPledge lnPledge);

	/**
	 * 查询抵质押物
	 * @param photoMap
	 * @return
	 */
	List<LnPledge> getLnLoanPledgeByLoanId(int loanId);

	/**
	 * 删除历史
	 * @param chId
	 */
	void rmLnLoanPledgeById(Integer id);
	
	/**
	 * 修改抵质押物
	 * 
	 * @param lnPledge
	 */
	void updateLnLoanPledgeById(LnPledge lnPledge);

	/**
	 * 查看抵质押物
	 * @param pledgeId
	 * @return
	 */
	LnPledge getLnLoanPledgeById(Integer pledgeId);
	
}
