package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanBalanceAssetDAO;
import com.banger.mobile.domain.model.loan.LnLoanBalanceAsset;
import com.banger.mobile.facade.loan.LnLoanBalanceAssetService;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class LnLoanBalanceAssetServiceImpl  implements LnLoanBalanceAssetService {

	private LnLoanBalanceAssetDAO lnLoanBalanceAssetDAO;
	
	
	public void setLnLoanBalanceAssetDAO(LnLoanBalanceAssetDAO lnLoanBalanceAssetDAO) {
		this.lnLoanBalanceAssetDAO = lnLoanBalanceAssetDAO;
	}


	@Override
	public List<LnLoanBalanceAsset> selectAssetByPrimary(
			LnLoanBalanceAsset lnLoanBalanceAsset) {
	
		return lnLoanBalanceAssetDAO.selectAssetByPrimary(lnLoanBalanceAsset);
	}


	@Override
	public LnLoanBalanceAsset insertAsset(LnLoanBalanceAsset lnLoanBalanceAsset) {
		if(StringUtils.isNotEmpty(lnLoanBalanceAsset.getType())){
			String type[] =lnLoanBalanceAsset.getType().split(",");
			lnLoanBalanceAsset.setType(type[0]);
			lnLoanBalanceAsset.setNote(type[1]);
		}
		return lnLoanBalanceAssetDAO.insertAsset(lnLoanBalanceAsset);
	}


	@Override
	public LnLoanBalanceAsset updateAsset(LnLoanBalanceAsset lnLoanBalanceAsset) {
		List<LnLoanBalanceAsset> assetList = lnLoanBalanceAssetDAO.selectAssetByPrimary(lnLoanBalanceAsset);
		LnLoanBalanceAsset asset=assetList.get(0);
		if(StringUtils.isNotEmpty(lnLoanBalanceAsset.getType())){
			String type[] =lnLoanBalanceAsset.getType().split(",");
			asset.setType(type[0]);
			asset.setNote(type[1]);
		}	
		asset.setName(lnLoanBalanceAsset.getName());
		
		asset.setValue(lnLoanBalanceAsset.getValue());
		
		return lnLoanBalanceAssetDAO.updateAsset(asset);
	}


	@Override
	public void deleteAsset(LnLoanBalanceAsset lnLoanBalanceAsset) {
		
		lnLoanBalanceAssetDAO.deleteAsset(lnLoanBalanceAsset);
	}


	@Override
	public List<LnLoanBalanceAsset> selectAssetByPrimary(Integer loanBalanceId) {
		LnLoanBalanceAsset lnLoanBalanceAsset= new LnLoanBalanceAsset();
		lnLoanBalanceAsset.setLoanBalanceId(loanBalanceId);
		return lnLoanBalanceAssetDAO.selectAssetByPrimary(lnLoanBalanceAsset);
	}



	
}
