package com.banger.mobile.facade.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnLoanBalanceAsset;
import com.banger.mobile.domain.model.loan.LnLoanBalanceAsset;

public interface LnLoanBalanceAssetService {

	List<LnLoanBalanceAsset> selectAssetByPrimary(
			LnLoanBalanceAsset lnLoanBalanceAsset);

	LnLoanBalanceAsset insertAsset(LnLoanBalanceAsset lnLoanBalanceAsset);

	LnLoanBalanceAsset updateAsset(LnLoanBalanceAsset lnLoanBalanceAsset);

	void deleteAsset(LnLoanBalanceAsset lnLoanBalanceAsset);

	List<LnLoanBalanceAsset> selectAssetByPrimary(Integer loanBalanceId);

	
}