package com.banger.mobile.facade.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.domain.model.loan.LnLoanBalanceDebt;
import com.banger.mobile.domain.model.loan.LnLoanBalanceHousing;

public interface LnLoanBalanceHousingService {



	void deleteHousing(LnLoanBalanceHousing lnLoanBalanceHousing);

	List<LnLoanBalanceHousing> selectHousingByPrimary(
			LnLoanBalanceHousing lnLoanBalanceHousing);

	LnLoanBalanceHousing insertHousing(LnLoanBalanceHousing lnLoanBalanceHousing);

	LnLoanBalanceHousing updateHousing(LnLoanBalanceHousing lnLoanBalanceHousing);

	List<LnLoanBalanceHousing> selectHousingByPrimary(Integer loanBalanceId);
    }
