package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.base.loan.BaseAnaCoBorrower;
import com.banger.mobile.domain.model.base.loan.BaseAnaGuarantor;
import com.banger.mobile.domain.model.loan.LnLoanProfitandlossXfDetail;

import java.util.List;
import java.util.Map;

public interface LnLoanProfitandlossXfDetailDao {

	List<LnLoanProfitandlossXfDetail> selectXfDetailByPrimary(
			LnLoanProfitandlossXfDetail lnLoanProfitandlossXfDetail);

	LnLoanProfitandlossXfDetail updateXfDetail(LnLoanProfitandlossXfDetail in);

	LnLoanProfitandlossXfDetail insertXfDetail(
			LnLoanProfitandlossXfDetail lnLoanProfitandlossXfDetail);

	void deleteXfDetail(LnLoanProfitandlossXfDetail lnLoanProfitandlossXfDetail);
}
