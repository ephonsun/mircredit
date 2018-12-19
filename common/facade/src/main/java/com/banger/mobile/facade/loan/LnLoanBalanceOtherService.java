package com.banger.mobile.facade.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnLoanBalanceOther;

public interface LnLoanBalanceOtherService {

	List<LnLoanBalanceOther> selectOtherByPrimary(
			LnLoanBalanceOther lnLBalanceOther);

	LnLoanBalanceOther insertOther(LnLoanBalanceOther lnLBalanceOther);

	LnLoanBalanceOther updateOther(LnLoanBalanceOther lnLBalanceOther);

	void deleteOher(LnLoanBalanceOther lnLBalanceOther);

	List<LnLoanBalanceOther> selectOtherByPrimary(Integer loanBalanceId);



}