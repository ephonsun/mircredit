/**
 * 
 * Copyright (c) 2015-2025 All Rights Reserved.
 */
package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.*;
import com.banger.mobile.domain.model.loan.*;
import com.banger.mobile.facade.loan.LnBalanceService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class LnBalanceServiceImpl implements LnBalanceService {


	private LnBalanceAccountDao lnBalanceAccountDao;
	private LnBalanceCashDao lnBalanceCashDao;
	private LnBalanceDebtDao lnBalanceDebtDao;
	private LnBalanceFixedDao lnBalanceFixedDao;
	private LnBalanceOffDao lnBalanceOffDao;
	private LnValidationRightsDao lnValidationRightsDao;
	private LnValidationItemDao lnValidationItemDao;
	private LnLoanTmpDao lnLoanTmpDao;

	public LnLoanTmpDao getLnLoanTmpDao() {
		return lnLoanTmpDao;
	}

	public void setLnLoanTmpDao(LnLoanTmpDao lnLoanTmpDao) {
		this.lnLoanTmpDao = lnLoanTmpDao;
	}

	public LnValidationRightsDao getLnValidationRightsDao() {
		return lnValidationRightsDao;
	}

	public void setLnValidationRightsDao(LnValidationRightsDao lnValidationRightsDao) {
		this.lnValidationRightsDao = lnValidationRightsDao;
	}

	public LnValidationItemDao getLnValidationItemDao() {
		return lnValidationItemDao;
	}

	public void setLnValidationItemDao(LnValidationItemDao lnValidationItemDao) {
		this.lnValidationItemDao = lnValidationItemDao;
	}

	public LnBalanceAccountDao getLnBalanceAccountDao() {
		return lnBalanceAccountDao;
	}

	public void setLnBalanceAccountDao(LnBalanceAccountDao lnBalanceAccountDao) {
		this.lnBalanceAccountDao = lnBalanceAccountDao;
	}

	public LnBalanceCashDao getLnBalanceCashDao() {
		return lnBalanceCashDao;
	}

	public void setLnBalanceCashDao(LnBalanceCashDao lnBalanceCashDao) {
		this.lnBalanceCashDao = lnBalanceCashDao;
	}

	public LnBalanceDebtDao getLnBalanceDebtDao() {
		return lnBalanceDebtDao;
	}

	public void setLnBalanceDebtDao(LnBalanceDebtDao lnBalanceDebtDao) {
		this.lnBalanceDebtDao = lnBalanceDebtDao;
	}

	public LnBalanceFixedDao getLnBalanceFixedDao() {
		return lnBalanceFixedDao;
	}

	public void setLnBalanceFixedDao(LnBalanceFixedDao lnBalanceFixedDao) {
		this.lnBalanceFixedDao = lnBalanceFixedDao;
	}

	public LnBalanceOffDao getLnBalanceOffDao() {
		return lnBalanceOffDao;
	}

	public void setLnBalanceOffDao(LnBalanceOffDao lnBalanceOffDao) {
		this.lnBalanceOffDao = lnBalanceOffDao;
	}



	@Override
	public void insertLnBalanceOff(LnBalanceOff lnBalanceOff) {
		lnBalanceOffDao.insertLnBalanceOff(lnBalanceOff);
	}

	@Override
	public void deleteLnBalanceOffById(Integer loanId) {
		lnBalanceOffDao.deleteLnBalanceOffById(loanId);
	}

	@Override
	public void updateLnBalanceOffById(LnBalanceOff lnBalanceOff) {
		lnBalanceOffDao.updateLnBalanceOffById(lnBalanceOff);
	}

	@Override
	public LnBalanceOff getLnBalanceOffById(Integer loanId) {
		return lnBalanceOffDao.getLnBalanceOffById(loanId);
	}

	@Override
	public void insertLnBalanceFixed(LnBalanceFixed lnBalanceFixed) {
		lnBalanceFixedDao.insertLnBalanceFixed(lnBalanceFixed);
	}

	@Override
	public void deleteLnBalanceFixedById(Integer id) {
		lnBalanceFixedDao.deleteLnBalanceFixedById(id);
	}

	@Override
	public void updateLnBalanceFixedById(LnBalanceFixed lnBalanceFixed) {
		lnBalanceFixedDao.updateLnBalanceFixedById(lnBalanceFixed);
	}

	@Override
	public LnBalanceFixed getLnBalanceFixedById(Integer id) {
		return lnBalanceFixedDao.getLnBalanceFixedById(id);
	}

	@Override
	public List<LnBalanceFixed> getLnBalanceFixedByLoanId(Integer loanId,String type) {
		return lnBalanceFixedDao.getLnBalanceFixedByLoanId(loanId,type);
	}

	@Override
	public void insertLnBalanceDebt(LnBalanceDebt lnBalanceDebt) {
		lnBalanceDebtDao.insertLnBalanceDebt(lnBalanceDebt);
	}

	@Override
	public void deleteLnBalanceDebtById(Integer id) {
		lnBalanceDebtDao.deleteLnBalanceDebtById(id);
	}

	@Override
	public void updateLnBalanceDebtById(LnBalanceDebt lnBalanceDebt) {
		lnBalanceDebtDao.updateLnBalanceDebtById(lnBalanceDebt);
	}

	@Override
	public LnBalanceDebt getLnBalanceDebtById(Integer id) {
		return lnBalanceDebtDao.getLnBalanceDebtById(id);
	}

	@Override
	public List<LnBalanceDebt> getLnBalanceDebtByLoanId(Integer loanId,String type) {
		return lnBalanceDebtDao.getLnBalanceDebtByLoanId(loanId,type);
	}

	@Override
	public void insertLnBalanceCash(LnBalanceCash lnBalanceCash) {
		lnBalanceCashDao.insertLnBalanceCash(lnBalanceCash);
	}

	@Override
	public void deleteLnBalanceCashById(Integer id) {
		lnBalanceCashDao.deleteLnBalanceCashById(id);
	}

	@Override
	public void updateLnBalanceCashById(LnBalanceCash lnBalanceCash) {
		lnBalanceCashDao.updateLnBalanceCashById(lnBalanceCash);
	}

	@Override
	public LnBalanceCash getLnBalanceCashById(Integer id) {
		return lnBalanceCashDao.getLnBalanceCashById(id);
	}

	@Override
	public List<LnBalanceCash> getLnBalanceCashByLoanId(Integer loanId, String type) {
		return lnBalanceCashDao.getLnBalanceCashByLoanId(loanId, type);
	}

	@Override
	public void insertLnBalanceAccount(LnBalanceAccount lnBalanceAccount) {
		lnBalanceAccountDao.insertLnBalanceAccount(lnBalanceAccount);
	}

	@Override
	public void deleteLnBalanceAccountById(Integer id) {
		lnBalanceAccountDao.deleteLnBalanceAccountById(id);
	}

	@Override
	public void updateLnBalanceAccountById(LnBalanceAccount lnBalanceAccount) {
		lnBalanceAccountDao.updateLnBalanceAccountById(lnBalanceAccount);
	}

	@Override
	public LnBalanceAccount getLnBalanceAccountById(Integer id) {
		return lnBalanceAccountDao.getLnBalanceAccountById(id);
	}

	@Override
	public List<LnBalanceAccount> getLnBalanceAccountByLoanId(Integer loanId,String type) {
		return lnBalanceAccountDao.getLnBalanceAccountByLoanId(loanId,type);
	}







	@Override
	public void insertLnValidationRights(LnValidationRights lnValidationRights) {
		lnValidationRightsDao.insertLnValidationRights(lnValidationRights);
	}

	@Override
	public void deleteLnValidationRightsById(Integer loanId) {
		lnValidationRightsDao.deleteLnValidationRightsById(loanId);
	}

	@Override
	public void updateLnValidationRightsById(LnValidationRights lnValidationRights) {
		lnValidationRightsDao.updateLnValidationRightsById(lnValidationRights);
	}

	@Override
	public LnValidationRights getLnValidationRightsById(Integer loanId) {
		return lnValidationRightsDao.getLnValidationRightsById(loanId);
	}

	@Override
	public void insertLnValidationItem(LnValidationItem lnValidationItem) {
		lnValidationItemDao.insertLnValidationItem(lnValidationItem);
	}

	@Override
	public void deleteLnValidationItemById(Integer id) {
		lnValidationItemDao.deleteLnValidationItemById(id);
	}

	@Override
	public void updateLnValidationItemById(LnValidationItem lnValidationItem) {
		lnValidationItemDao.updateLnValidationItemById(lnValidationItem);
	}

	@Override
	public LnValidationItem getLnValidationItemById(Integer id) {
		return lnValidationItemDao.getLnValidationItemById(id);
	}

	@Override
	public List<LnValidationItem> getLnValidationItemByLoanId(Integer loanId, String type) {
		return lnValidationItemDao.getLnValidationItemByLoanId(loanId,type);
	}

	@Override
	public BigDecimal sumByFlagAndType(Integer loanId, String flag, String type) {

		BigDecimal sum = null;
		if(flag.equals("cash")){
			sum = lnBalanceCashDao.sumByType(loanId,type);
		}else if(flag.equals("account")){
			sum = lnBalanceAccountDao.sumByType(loanId,type);
		}else if(flag.equals("debt")){
			sum = lnBalanceDebtDao.sumByType(loanId,type);
		}else if(flag.equals("fixed")){
			sum = lnBalanceFixedDao.sumByType(loanId,type);
		}

		if(null==sum){
			sum=new BigDecimal(0);
		}
		return sum;
	}

	@Override
	public void updateTmpLoanInfo(TmpLoanInfo tmpLoanInfo) {
		lnLoanTmpDao.updateTmpLoanInfo(tmpLoanInfo);
	}

	@Override
	public TmpLoanInfo getTmpLoanInfoByAccount(String acctNo) {
		return lnLoanTmpDao.getTmpLoanInfoByAccount(acctNo);
	}

	@Override
	public TmpLoanInfo getTmpLoanInfoByLoanId(Integer loanId) {
		return lnLoanTmpDao.getTmpLoanInfoByLoanId(loanId);
	}

	@Override
	public TmpLoanAccount getTmpLoanAccountByLoanId(Integer loanId) {
		return lnLoanTmpDao.getTmpLoanAccountByLoanId(loanId);
	}

	@Override
	public List<TmpLoanInfo> getTmpLoanInfoAll(){
		return lnLoanTmpDao.getTmpLoanInfoAll();
	}

	@Override
	public List<TmpLoanAccount> getTmpLoanAccountAll() {
		return lnLoanTmpDao.getTmpLoanAccountAll();
	}

	@Override
	public List<TmpLoanRepayment> getTmpLoanRepaymentAll() {
		return lnLoanTmpDao.getTmpLoanRepaymentAll();
	}


}
