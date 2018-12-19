package com.banger.mobile.facade.impl;

import java.math.BigDecimal;
import java.util.List;


import com.banger.mobile.dao.loan.LnLoanContractDao;
import com.banger.mobile.dao.loan.LnProfitLossDetailDAO;
import com.banger.mobile.dao.loan.LnProfitLossItemDAO;
import com.banger.mobile.dao.loan.LnProfitLossProdDAO;

import com.banger.mobile.domain.model.loan.LnProfitLossDetail;
import com.banger.mobile.domain.model.loan.LnProfitLossItem;
import com.banger.mobile.domain.model.loan.LnProfitLossProd;

import com.banger.mobile.facade.loan.LnProfitLossProdService;
import org.springframework.util.CollectionUtils;

import javax.lang.model.SourceVersion;

public class LnProfitLossProdServiceImpl implements LnProfitLossProdService {

	private LnProfitLossProdDAO lnProfitLossProdDAO;

	private LnProfitLossItemDAO lnProfitLossItemDAO;

	private LnProfitLossDetailDAO lnProfitLossDetailDAO;

	public LnProfitLossItemDAO getLnProfitLossItemDAO() {
		return lnProfitLossItemDAO;
	}

	public void setLnProfitLossItemDAO(LnProfitLossItemDAO lnProfitLossItemDAO) {
		this.lnProfitLossItemDAO = lnProfitLossItemDAO;
	}

	public LnProfitLossDetailDAO getLnProfitLossDetailDAO() {
		return lnProfitLossDetailDAO;
	}

	public void setLnProfitLossDetailDAO(LnProfitLossDetailDAO lnProfitLossDetailDAO) {
		this.lnProfitLossDetailDAO = lnProfitLossDetailDAO;
	}

	public LnProfitLossProdDAO getLnProfitLossProdDAO() {
		return lnProfitLossProdDAO;
	}


	public void setLnProfitLossProdDAO(LnProfitLossProdDAO lnProfitLossProdDAO) {
		this.lnProfitLossProdDAO = lnProfitLossProdDAO;
	}



	@Override
	public List<LnProfitLossProd> selectProfitLossProdByPrimary(
			LnProfitLossProd lnProfitLossProd) {
		
		return lnProfitLossProdDAO.selectProfitLossByPrimary(lnProfitLossProd);
	}


	@Override
	public LnProfitLossProd insertProd(LnProfitLossProd lnProfitLossProd) {
		
		return lnProfitLossProdDAO.insertProd(lnProfitLossProd);
	}


	@Override
	public void updateProd(LnProfitLossProd lnProfitLossProd) {
		
//		List<LnProfitLossProd> assetList = lnProfitLossProdrDAO.selectOtherByPrimary(lnProfitLossProd);
//		LnProfitLossProd asset=assetList.get(0);
//		asset.setProductName(lnProfitLossProd.getProductName());
//		asset.setSellingPrice(lnProfitLossProd.getSellingPrice());
//		asset.setCostPrice(lnProfitLossProd.getCostPrice());
//		asset.setGrossRate(lnProfitLossProd.getGrossRate());
//		asset.setRemark(lnProfitLossProd.getRemark());
//		asset.setGrossValue(lnProfitLossProd.getGrossValue());
//		asset.setSellingRate(lnProfitLossProd.getSellingRate());
//		asset.setCreateUser(lnProfitLossProd.getCreateUser());
//		asset.setCreateDate(lnProfitLossProd.getCreateDate());
//		asset.setUpdateUser(lnProfitLossProd.getUpdateUser());
//		asset.setUpdateDate(lnProfitLossProd.getUpdateDate());
		System.out.println("2------------lnProfitLossProd = " + lnProfitLossProd);
		lnProfitLossProdDAO.updateProd(lnProfitLossProd);

	}


	@Override
	public void deleteProd(Integer id,Integer loanId) {
		LnProfitLossProd lnProfitLossProd = new LnProfitLossProd();
		lnProfitLossProd.setProfitLossId(id);
		lnProfitLossProd.setLoanId(loanId);
		System.out.println("DAO deleteProd() sucess!");
		lnProfitLossProdDAO.deleteProd(lnProfitLossProd);
	}

	@Override
	public List<LnProfitLossDetail> selectDetailsByItemId(Integer itemId) {
		return lnProfitLossDetailDAO.selectDetailsByItemId(itemId);
	}

	@Override
	public void deleteDetailsByItemId(Integer itemId) {
		lnProfitLossDetailDAO.deleteDetailsByItemId(itemId);
	}

	@Override
	public void deleteDetailsByLoanId(Integer loanId) {
		lnProfitLossDetailDAO.deleteDetailsByLoanId(loanId);
	}

	@Override
	public void insertDetails(LnProfitLossDetail lnProfitLossDetail) {
		lnProfitLossDetailDAO.insertDetails(lnProfitLossDetail);
	}

	@Override
	public void updateDetails(LnProfitLossDetail lnProfitLossDetail) {
		lnProfitLossDetailDAO.updateDetails(lnProfitLossDetail);
	}


	@Override
	public List<LnProfitLossItem> selectItemListByType(Integer loanId, String type) {
		return lnProfitLossItemDAO.selectItemListByType(loanId, type);
	}

	@Override
	public LnProfitLossItem selectOneItemById(Integer itemId) {
		return lnProfitLossItemDAO.selectOneItemById(itemId);
	}

	@Override
	public void deleteLnProfitLossItem(Integer itemId) {
		lnProfitLossItemDAO.deleteLnProfitLossItem(itemId);
	}

	@Override
	public void deleteLnProfitLossItems(Integer loanId) {
		lnProfitLossItemDAO.deleteLnProfitLossItems(loanId);
	}

	@Override
	public void insertLnProfitLossItem(LnProfitLossItem lnProfitLossItem) {
		lnProfitLossItemDAO.insertLnProfitLossItem(lnProfitLossItem);
	}

	@Override
	public void updateLnProfitLossItem(LnProfitLossItem lnProfitLossItem) {
		lnProfitLossItemDAO.updateLnProfitLossItem(lnProfitLossItem);
	}


	@Override
	public List<LnProfitLossProd> selectProfitLossProdByPrimary(Integer loanId) {
		LnProfitLossProd lnProfitLossProd = new LnProfitLossProd();
		lnProfitLossProd.setLoanId(loanId);
		return lnProfitLossProdDAO.selectProfitLossByPrimary(lnProfitLossProd);
	}


//	@Override
//	public List<LnProfitLossProd> selectOtherByPrimary(Integer loanId) {
//		return null;
//	}

//
//	@Override
//	public List<LnProfitLossProd> selectOtherByPrimary(Integer loanId) {
//		LnProfitLossProd lnProfitLossProd = new  LnProfitLossProd();
//		lnProfitLossProd.setLoanId(loanId);
//		return lnProfitLossProdrDAO.selectOtherByPrimary(lnLoanBalanceOther);
//	}

	@Override
	public BigDecimal getWeightedGrossRate(Integer loanId) {

		BigDecimal rate = new BigDecimal(0);
		List<LnProfitLossProd> list = lnProfitLossProdDAO.getProfitLossProdByLoanId(loanId);
		if(!CollectionUtils.isEmpty(list)){
			for (LnProfitLossProd prod : list) {
				if(null!=prod&&null!=prod.getGrossRate()&&null!=prod.getSellingRate()){
					rate = rate.add(new BigDecimal(prod.getGrossRate()*prod.getSellingRate()/100));
				}
			}
			rate.setScale(2,BigDecimal.ROUND_HALF_UP);
		}

		return rate;
	}
	
}
