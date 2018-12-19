package com.banger.mobile.dao.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnLoanBalanceDebt;

public interface LnLoanBalanceDebtDAO {
 
	void deleteDebt(LnLoanBalanceDebt lnBalanceDebt);

	List<LnLoanBalanceDebt> selectDebtByPrimary(
			LnLoanBalanceDebt lnLoanBalanceDebt);

	LnLoanBalanceDebt insertDebt(LnLoanBalanceDebt lnLoanBalanceDebt);

	LnLoanBalanceDebt updateDebt(LnLoanBalanceDebt debt);
}