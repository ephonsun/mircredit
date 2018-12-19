package com.banger.mobile.dao.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnLoanBalance;
import com.banger.mobile.domain.model.loan.LnLoanBalanceAsset;

public interface LnLoanBalanceDao {

	LnLoanBalance insertBalance(LnLoanBalance lnLoanBalance);

	LnLoanBalance updateBalance(LnLoanBalance lnLoanBalance);

	LnLoanBalance selectBalanceByPrimary(LnLoanBalance lnLoanBalance);
}