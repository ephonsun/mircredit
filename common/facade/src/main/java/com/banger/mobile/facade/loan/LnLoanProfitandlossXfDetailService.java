package com.banger.mobile.facade.loan;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.loan.LnApproveLimitRole;
import com.banger.mobile.domain.model.loan.LnLoanProfitandlossXfDetail;

import java.util.List;
import java.util.Map;


public interface LnLoanProfitandlossXfDetailService {

	public List<LnLoanProfitandlossXfDetail> selectXfDetailByPrimary(
			LnLoanProfitandlossXfDetail lnLoanProfitandlossXfDetail);

	public LnLoanProfitandlossXfDetail insertXfDetail(
			LnLoanProfitandlossXfDetail lnLoanProfitandlossXfDetail);

	public LnLoanProfitandlossXfDetail updateXfDetail(
			LnLoanProfitandlossXfDetail lnLoanProfitandlossXfDetail);

	public void deleteXfDetail(
			LnLoanProfitandlossXfDetail lnLoanProfitandlossXfDetail);
	/**
	 * 消费贷损益表详情
	 * @param integer 
	 * @return
	 */
	public Map<String, Object> profitandlossXfDetail(Integer profitandlossJyDetailId);
}
