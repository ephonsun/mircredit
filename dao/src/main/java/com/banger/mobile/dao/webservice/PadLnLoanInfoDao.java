package com.banger.mobile.dao.webservice;

import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.pad.PadLoan;
import com.banger.mobile.domain.model.pad.PadLoanInfo;

public interface PadLnLoanInfoDao {
	
	PageUtil<PadLoan> getLnLoanListPage(Map<String, Object> parameterMap, Page page);
	
	PadLoan getPanLoanById(int loanId);
	
	PadLoanInfo getPanLoanInfoById(int loanId);
	
	int getPadLoanCountByLoanStatus(Map<String, Object> parameterMap);
}
