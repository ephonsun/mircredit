package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.LnBusinessModel;
import com.banger.mobile.domain.model.loan.LnLoanHistory;

public interface LnLoanHistoryDao {

	LnLoanHistory selectByPrimary(LnLoanHistory lnLoanHistory);



	int updateLoanHistory(LnLoanHistory ln);


	LnLoanHistory insertLoanHistory(LnLoanHistory lnLoanHistory);

	
	

}
