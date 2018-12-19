package com.banger.mobile.dao.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnLoanBalanceReceivable;

public interface LnLoanBalanceReceivableDAO {

	void deleteReceivable(LnLoanBalanceReceivable lnLoanBalanceReceivable);

	LnLoanBalanceReceivable insertReceivable(
			LnLoanBalanceReceivable lnLoanBalanceReceivable);

	List<LnLoanBalanceReceivable> selectReceivableByPrimary(
			LnLoanBalanceReceivable lnLoanBalanceReceivable);

	LnLoanBalanceReceivable updateReceivable(LnLoanBalanceReceivable asset);
}