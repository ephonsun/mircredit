package com.banger.mobile.dao.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnLoanBalanceAsset;
import com.banger.mobile.domain.model.loan.LnLoanBalanceOther;

public interface LnLoanBalanceOtherDAO {


	List<LnLoanBalanceOther> selectOtherByPrimary(
			LnLoanBalanceOther lnLBalanceOther);

	LnLoanBalanceOther insertOther(LnLoanBalanceOther lnLBalanceOther);

	int updateOther(LnLoanBalanceOther ln);

	void deleteAsset(LnLoanBalanceOther lnLBalanceOther);

	
}