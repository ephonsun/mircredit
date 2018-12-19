package com.banger.mobile.dao.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnLoanBalanceHousing;

public interface LnLoanBalanceHousingDAO {
   
	LnLoanBalanceHousing updateHousing(LnLoanBalanceHousing asset);

	List<LnLoanBalanceHousing> selectHousingByPrimary(
			LnLoanBalanceHousing lnLoanBalanceHousing);

	LnLoanBalanceHousing insertHousing(LnLoanBalanceHousing lnLoanBalanceHousing);

	void deleteHousing(LnLoanBalanceHousing lnLoanBalanceHousing);
}