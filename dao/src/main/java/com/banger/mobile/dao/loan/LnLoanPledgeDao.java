/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnPledge;

/**
 * 
 * @author White Wolf
 * @version $Id: LnLoanPledgeDao.java, v 0.1 2016-1-12 上午11:12:00 White Wolf Exp $
 */
public interface LnLoanPledgeDao {
	
	/**
	 * 插入抵质押物
	 * 
	 * @param lnCreditHistory
	 */
	void insertLnLoanPledge(LnPledge lnPledge);

	/**
	 * 查询信贷历史列表
	 * @param loanId
	 * @return
	 */
	List<LnPledge> selectLnPledgeByLoanId(int loanId);

	/**
	 * 删除抵质押物
	 * @param chId
	 * @return
	 */
	void deleteLnPledgeById(Integer id);

	/**
	 * 修改抵质押物
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
