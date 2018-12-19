package com.banger.mobile.facade.loan;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.loan.LnApproveLimitRole;
import com.banger.mobile.domain.model.loan.LnLoanProfitandlossJyDetail;

import java.util.List;
import java.util.Map;


public interface LnLoanProfitandlossJyDetailService {

	

	public void deleteXfDetail(
			LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail);

	public List<LnLoanProfitandlossJyDetail> selectJyDetaiByPrimary(
			LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail);

	public LnLoanProfitandlossJyDetail insertJyDetai(
			LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail);

	public LnLoanProfitandlossJyDetail updateXfJyDetai(
			LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail);
	/**
	 * 经营贷损益表详情
	 * @param integer 
	 * @return  Map<String, Object>
	 */
	public Map<String, Object> profitandlossJyDetail(Integer loanProfitandlossId);

	
}
