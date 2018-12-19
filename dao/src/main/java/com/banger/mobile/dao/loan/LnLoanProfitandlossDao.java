package com.banger.mobile.dao.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.domain.model.loan.LnLoanProfitandloss;

public interface LnLoanProfitandlossDao {


	LnLoanProfitandloss insertProfitandloss(
			LnLoanProfitandloss lnLoanProfitandloss);

	List<LnLoanProfitandloss> selectProfitandlossByPrimary(
			LnLoanProfitandloss lnLoanProfitandloss);

	LnLoanProfitandloss updateProfitandloss(LnLoanProfitandloss in);
	LnLoanProfitandloss updateProfitandlossBaseInfo(LnLoanProfitandloss in);
}
