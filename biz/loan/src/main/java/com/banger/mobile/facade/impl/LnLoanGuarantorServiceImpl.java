/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :担保人-Service-接口实现
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanGuarantorDao;
import com.banger.mobile.domain.model.loan.LnLoanGuarantor;
import com.banger.mobile.domain.model.loan.LnLoanGuarantorBean;
import com.banger.mobile.facade.loan.LnLoanGuarantorService;

import java.util.List;
import java.util.Map;

/**
 * @author QianJie
 * @version $Id: LnLoanGuarantorServiceImpl.java,v 0.1 Feb 17, 2013 3:48:24 PM QianJie Exp $
 */
public class LnLoanGuarantorServiceImpl implements LnLoanGuarantorService {

    private LnLoanGuarantorDao lnLoanGuarantorDao;

    public void setLnLoanGuarantorDao(LnLoanGuarantorDao lnLoanGuarantorDao) {
        this.lnLoanGuarantorDao = lnLoanGuarantorDao;
    }

    /**
     * 查询所有担保人数据
     * @param conds
     * @return
     * @see com.banger.mobile.facade.loan.LnLoanGuarantorService#getAllLnLoanGuarantorBeanByConds(java.util.Map)
     */
    public List<LnLoanGuarantorBean> getAllLnLoanGuarantorBeanByConds(Map<String, Object> conds) {
        return lnLoanGuarantorDao.getAllLnLoanGuarantorBeanByConds(conds);
    }

    public void addLnLoanGuarantor(LnLoanGuarantor gu) {
        lnLoanGuarantorDao.addLnLoanGuarantor(gu);
    }

    public void deleteLnLoanGuarantor(Integer guId) {
        lnLoanGuarantorDao.deleteLnLoanGuarantor(guId);
    }

    /**
     * 批量插入担保人
     * @param guarantorList
     * @return
     */
    public int addLnLoanGuarantorBatch(List<LnLoanGuarantor> guarantorList){
        return lnLoanGuarantorDao.addLnLoanGuarantorBatch(guarantorList);
    }

    /**
     * 批量更新担保人
     * @param guarantorList
     * @return
     */
    public int updateLoanGuarantorBatch(List<LnLoanGuarantor> guarantorList){
        return lnLoanGuarantorDao.updateLoanGuarantorBatch(guarantorList);
    }

    /**
     * 根据贷款ID删除其相关的担保人
     * 
     * @param loanId
     */
    public void deleteLoanGuarantorById(Integer loanId){
        lnLoanGuarantorDao.deleteLoanGuarantor(loanId);
    }

    public Integer getGuarantorCount(Map<String, Object> paramMap){
        return lnLoanGuarantorDao.getGuarantorCount(paramMap);
    }

    public Integer updateLoanGuarantor(Map<String, Object> paramMap){
        return lnLoanGuarantorDao.updateLoanGuarantor(paramMap);
    }

    /**
     * 根据贷款和客户查找惟一的担保人
     * @param paramMap
     * @return
     */
    public LnLoanGuarantor selectGuarantorSingle(Map<String, Object> paramMap){
        return lnLoanGuarantorDao.selectGuarantorSingle(paramMap);
    }

    /**
     * 根据贷款的相关客户删除担保人
     * @param paramMap
     */
    public void delGuaByLoanCustomerId(Map<String, Object> paramMap){
        lnLoanGuarantorDao.delGuaByLoanCustomerId(paramMap);
    }

    /**
     * 根据客户id列表删除不在该列表中的担保人
     * @param paramMap
     */
    public void delDeletedGuaByCusList(Map<String, Object> paramMap){
        lnLoanGuarantorDao.delDeletedGuaByCusList(paramMap);
    }

    /**
     * 搜索某贷款的所有客户id
     *
     * @param loanId
     * @return
     */
    public List<Integer> getCusIdListByLoanId(Integer loanId){
        return lnLoanGuarantorDao.getCusIdListByLoanId(loanId);
    }

    /**
     *
     * @param paramMap
     * @return
     */
    public Integer updateLoanGuarantorByLoan(Map<String, Object> paramMap){
        return lnLoanGuarantorDao.updateLoanGuarantorByLoan(paramMap);
    }

	@Override
	public void deleteLnLoanGuarantorByLoanId(Integer loanId) {
		lnLoanGuarantorDao.deleteLnLoanGuarantorByLoanId(loanId);
	}
    
    
}
