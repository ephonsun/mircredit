package com.banger.mobile.facade.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.domain.model.loan.LnLoanProfitandloss;

public interface LnLoanProfitandlossService {

	LnLoanProfitandloss insertProfitandloss(
			LnLoanProfitandloss lnLoanProfitandloss);

	LnLoanProfitandloss updateProfitandloss(
			LnLoanProfitandloss lnLoanProfitandloss);

	List<LnLoanProfitandloss> selectProfitandlossByPrimary(
			LnLoanProfitandloss lnLoanProfitandloss);
   

  LnLoanProfitandloss updateProfitandlossBaseInfo(LnLoanProfitandloss lnLoanProfitandloss);

List<LnLoanProfitandloss> selectProfitandlossByPrimary(Integer loanId);
}