package com.banger.mobile.facade.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.domain.model.loan.LnLoanBalanceReceivable;

public interface LnLoanBalanceReceivableService {


	List<LnLoanBalanceReceivable> selectReceivableByPrimary(
			LnLoanBalanceReceivable lnLoanBalanceReceivable);

	LnLoanBalanceReceivable insertReceivable(
			LnLoanBalanceReceivable lnLoanBalanceReceivable);

	LnLoanBalanceReceivable updateReceivable(
			LnLoanBalanceReceivable lnLoanBalanceReceivable);

	void deleteReceivable(LnLoanBalanceReceivable lnLoanBalanceReceivable);

	List<LnLoanBalanceReceivable> selectReceivableByPrimary(
			Integer loanBalanceId);
    }
