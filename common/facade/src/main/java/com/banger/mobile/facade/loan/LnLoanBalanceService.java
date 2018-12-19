package com.banger.mobile.facade.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.domain.model.loan.LnLoanBalance;
import com.banger.mobile.domain.model.loan.LnLoanBalanceAsset;

public interface LnLoanBalanceService {



	LnLoanBalance selectBalanceByPrimary(LnLoanBalance lnLoanBalance);

	LnLoanBalance insertBalance(LnLoanBalance lnLoanBalance);

	LnLoanBalance updateBalance(LnLoanBalance lnLoanBalance);

	LnLoanBalance selectBalanceByPrimary(Integer loanId);
    }
