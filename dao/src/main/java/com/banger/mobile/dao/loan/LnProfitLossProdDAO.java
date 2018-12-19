package com.banger.mobile.dao.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnProfitLossProd;

public interface LnProfitLossProdDAO {
	
	List<LnProfitLossProd> selectProfitLossByPrimary(
			LnProfitLossProd lnProfitLossProd);

	LnProfitLossProd insertProd(LnProfitLossProd lnProfitLossProd);

	void updateProd(LnProfitLossProd lnProfitLossProd);

	void deleteProd(LnProfitLossProd lnProfitLossProd);


	List<LnProfitLossProd> getProfitLossProdByLoanId(Integer loanId);
}
