/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.contract.LnLoanContract;
import com.banger.mobile.domain.model.loan.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 */
public interface LnBalanceService {


	/**
	 *
	 */
	void insertLnBalanceOff(LnBalanceOff lnBalanceOff);


	/**
	 */
	void deleteLnBalanceOffById(Integer loanId);

	/**
	 */
	void updateLnBalanceOffById(LnBalanceOff lnBalanceOff);

	/**
	 */
	LnBalanceOff getLnBalanceOffById(Integer loanId);




	/**
	 *
	 */
	void insertLnBalanceFixed(LnBalanceFixed lnBalanceFixed);


	/**
	 */
	void deleteLnBalanceFixedById(Integer id);

	/**
	 */
	void updateLnBalanceFixedById(LnBalanceFixed lnBalanceFixed);

	/**
	 */
	LnBalanceFixed getLnBalanceFixedById(Integer id);


	List<LnBalanceFixed> getLnBalanceFixedByLoanId(Integer loanId,String type);



	/**
	 *
	 */
	void insertLnBalanceDebt(LnBalanceDebt lnBalanceDebt);

	/**
	 */
	void deleteLnBalanceDebtById(Integer id);

	/**
	 */
	void updateLnBalanceDebtById(LnBalanceDebt lnBalanceDebt);

	/**
	 */
	LnBalanceDebt getLnBalanceDebtById(Integer id);

	List<LnBalanceDebt> getLnBalanceDebtByLoanId(Integer loanId,String type);




	/**
	 *
	 */
	void insertLnBalanceCash(LnBalanceCash lnBalanceCash);


	/**
	 */
	void deleteLnBalanceCashById(Integer id);

	/**
	 */
	void updateLnBalanceCashById(LnBalanceCash lnBalanceCash);

	/**
	 */
	LnBalanceCash getLnBalanceCashById(Integer id);


	List<LnBalanceCash> getLnBalanceCashByLoanId(Integer loanId, String type);


	/**
	 *
	 */
	void insertLnBalanceAccount(LnBalanceAccount lnBalanceAccount);


	/**
	 */
	void deleteLnBalanceAccountById(Integer id);

	/**
	 */
	void updateLnBalanceAccountById(LnBalanceAccount lnBalanceAccount);

	/**
	 */
	LnBalanceAccount getLnBalanceAccountById(Integer id);


	List<LnBalanceAccount> getLnBalanceAccountByLoanId(Integer loanId,String type);




	/**
	 *
	 */
	void insertLnValidationRights(LnValidationRights lnValidationRights);


	/**
	 */
	void deleteLnValidationRightsById(Integer loanId);

	/**
	 */
	void updateLnValidationRightsById(LnValidationRights lnValidationRights);

	/**
	 */
	LnValidationRights getLnValidationRightsById(Integer loanId);




	/**
	 *
	 */
	void insertLnValidationItem(LnValidationItem lnValidationItem);


	/**
	 */
	void deleteLnValidationItemById(Integer id);

	/**
	 */
	void updateLnValidationItemById(LnValidationItem lnValidationItem);

	/**
	 */
	LnValidationItem getLnValidationItemById(Integer id);


	List<LnValidationItem> getLnValidationItemByLoanId(Integer loanId, String type);


	BigDecimal sumByFlagAndType(Integer loanId, String flag, String type);


	void updateTmpLoanInfo(TmpLoanInfo tmpLoanInfo);

	TmpLoanInfo getTmpLoanInfoByAccount(String acctNo);

	TmpLoanInfo getTmpLoanInfoByLoanId(Integer loanId);

	TmpLoanAccount getTmpLoanAccountByLoanId(Integer loanId);

	List<TmpLoanInfo> getTmpLoanInfoAll();


	List<TmpLoanAccount> getTmpLoanAccountAll();

	List<TmpLoanRepayment> getTmpLoanRepaymentAll();



}
