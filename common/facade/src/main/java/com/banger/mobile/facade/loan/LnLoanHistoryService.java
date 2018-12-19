package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.domain.model.loan.LnLoanHistory;

public interface LnLoanHistoryService {

	LnLoanHistory selectByPrimary(LnLoanHistory lnLoanHistory);

	LnLoanHistory insertLoanHistory(LnLoanHistory lnLoanHistory);


	LnLoanHistory selectByPrimary(Integer loanId);

	LnLoanHistory updateLoanHistory(LnLoanHistory lnLoanHistory);


	
    }
