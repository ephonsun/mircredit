package com.banger.mobile.dao.loan;

import java.util.List;

import com.banger.mobile.domain.model.loan.LnLoanBalanceAsset;

public interface LnLoanBalanceAssetDAO {

	

	List<LnLoanBalanceAsset> selectAssetByPrimary(
			LnLoanBalanceAsset lnLoanBalanceAsset);

	LnLoanBalanceAsset insertAsset(LnLoanBalanceAsset lnLoanBalanceAsset);

	LnLoanBalanceAsset updateAsset(LnLoanBalanceAsset asset);

	void deleteAsset(LnLoanBalanceAsset lnLoanBalanceAsset);
  
}