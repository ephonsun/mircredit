package com.banger.mobile.facade.loan;

import java.math.BigDecimal;
import java.util.List;

import com.banger.mobile.domain.model.loan.LnLoanBalanceOther;
import com.banger.mobile.domain.model.loan.LnProfitLossDetail;
import com.banger.mobile.domain.model.loan.LnProfitLossItem;
import com.banger.mobile.domain.model.loan.LnProfitLossProd;

public interface LnProfitLossProdService {

	List<LnProfitLossProd> selectProfitLossProdByPrimary(
			LnProfitLossProd lnProfitLossProd);

	LnProfitLossProd insertProd(LnProfitLossProd lnProfitLossProd);

	void updateProd(LnProfitLossProd lnProfitLossProd);

//	void deleteProd(LnProfitLossProd lnProfitLossProd);

	List<LnProfitLossProd> selectProfitLossProdByPrimary(Integer loanId);

	void deleteProd(Integer id, Integer loanId);


	List<LnProfitLossDetail> selectDetailsByItemId(Integer itemId);

	void deleteDetailsByItemId(Integer itemId);

	void deleteDetailsByLoanId(Integer loanId);

	void insertDetails(LnProfitLossDetail lnProfitLossDetail);

	void updateDetails(LnProfitLossDetail lnProfitLossDetail);

	List<LnProfitLossItem> selectItemListByType(Integer loanId,String type);

	LnProfitLossItem selectOneItemById(Integer itemId);

	void deleteLnProfitLossItem(Integer itemId);

	void  deleteLnProfitLossItems(Integer loanId);

	void insertLnProfitLossItem(LnProfitLossItem lnProfitLossItem);

	void updateLnProfitLossItem(LnProfitLossItem lnProfitLossItem);


	BigDecimal getWeightedGrossRate(Integer loanId);
}