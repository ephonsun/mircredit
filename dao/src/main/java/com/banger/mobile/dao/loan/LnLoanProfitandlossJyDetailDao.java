package com.banger.mobile.dao.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnDunSeting;
import com.banger.mobile.domain.model.loan.LnLoanProfitandlossJyDetail;

public interface LnLoanProfitandlossJyDetailDao {

	public void deleteXfDetail(
			LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail);

	public List<LnLoanProfitandlossJyDetail> selectJyDetaiByPrimary(
			LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail);

	public LnLoanProfitandlossJyDetail insertJyDetai(
			LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail);

	public LnLoanProfitandlossJyDetail updateXfJyDetai(
			LnLoanProfitandlossJyDetail lnLoanProfitandlossJyDetail);
    /**
     * 根据损益表主键删除损益表详情
     * @param loanProfitandlossId
     */
	public void deleteXfDetailByProfitandlossID(Integer loanProfitandlossId);
}
