/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.dao.loan;


import com.banger.mobile.domain.model.loan.LnValidationItem;
import com.banger.mobile.domain.model.loan.TmpLoanAccount;
import com.banger.mobile.domain.model.loan.TmpLoanInfo;
import com.banger.mobile.domain.model.loan.TmpLoanRepayment;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface LnLoanTmpDao {

	void updateTmpLoanInfo(TmpLoanInfo tmpLoanInfo);

	TmpLoanInfo getTmpLoanInfoByAccount(String acctNo);

	TmpLoanInfo getTmpLoanInfoByLoanId(Integer loanId);

	List<TmpLoanInfo> getTmpLoanInfoAll();

	TmpLoanAccount getTmpLoanAccountByLoanId(Integer loanId);

	List<TmpLoanAccount> getTmpLoanAccountAll();

	List<TmpLoanRepayment> getTmpLoanRepaymentAll();

}
