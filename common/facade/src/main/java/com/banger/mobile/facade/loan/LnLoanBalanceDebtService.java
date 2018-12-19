package com.banger.mobile.facade.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnLoanBalanceDebt;

public interface LnLoanBalanceDebtService {

	/**
	 * 根据主键删除
	 * @param lnBalanceDebt
	 */
	void deleteDebt(LnLoanBalanceDebt lnBalanceDebt);

	List<LnLoanBalanceDebt> selectDebtByPrimary(
			LnLoanBalanceDebt lnLoanBalanceDebt);

	LnLoanBalanceDebt insertAsset(LnLoanBalanceDebt lnLoanBalanceDebt);

	LnLoanBalanceDebt updateAsset(LnLoanBalanceDebt lnLoanBalanceDebt);

	List<LnLoanBalanceDebt> selectDebtByPrimary(Integer loanBalanceId);

	
    }
