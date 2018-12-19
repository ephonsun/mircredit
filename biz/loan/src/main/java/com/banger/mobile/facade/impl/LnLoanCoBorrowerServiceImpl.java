/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :共同借贷人-Service-接口实现
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnLoanCoBorrowerDao;
import com.banger.mobile.domain.model.loan.LnLoanCoBorrower;
import com.banger.mobile.domain.model.loan.LnLoanCoBorrowerBean;
import com.banger.mobile.facade.loan.LnLoanCoBorrowerService;

import java.util.List;
import java.util.Map;

/**
 * @author QianJie
 * @version $Id: LnLoanCoBorrowerServiceImpl.java,v 0.1 Feb 17, 2013 3:48:01 PM QianJie Exp $
 */
public class LnLoanCoBorrowerServiceImpl implements LnLoanCoBorrowerService {

    private LnLoanCoBorrowerDao lnLoanCoBorrowerDao;

    public void setLnLoanCoBorrowerDao(LnLoanCoBorrowerDao lnLoanCoBorrowerDao) {
        this.lnLoanCoBorrowerDao = lnLoanCoBorrowerDao;
    }

    /**
     * 查询所有共同借贷人数据
     * @param conds
     * @return
     * @see com.banger.mobile.facade.loan.LnLoanCoBorrowerService#getAllLnLoanCoBorrowerBeanByConds(java.util.Map)
     */
    public List<LnLoanCoBorrowerBean> getAllLnLoanCoBorrowerBeanByConds(Map<String, Object> conds) {
        return lnLoanCoBorrowerDao.getAllLnLoanCoBorrowerBeanByConds(conds);
    }

    public void addLnLoanCoBorrower(LnLoanCoBorrower co) {
        lnLoanCoBorrowerDao.addLnLoanCoBorrower(co);
    }

    public void deleteLnLoanCoBorrower(Integer coId) {
        lnLoanCoBorrowerDao.deleteLnLoanCoBorrower(coId);
    }

    /**
     * 批量插入共同借贷人数据
     * @param coBorrowerList
     * @return
     */
    public int addLnLoanCoBorrowerBatch(List<LnLoanCoBorrower> coBorrowerList){
        return lnLoanCoBorrowerDao.addLnLoanCoBorrowerBatch(coBorrowerList);
    }

    /**
     * 批量更新共同借贷人
     * @param coBorrowerList
     * @return
     */
    public int updateLoanCoBorrowerBatch(List<LnLoanCoBorrower> coBorrowerList){
        return lnLoanCoBorrowerDao.updateLoanCoBorrowerBatch(coBorrowerList);
    }

    /**
     * 根据贷款ID删除其相关的共同借贷人
     * 
     * @param loanId
     */
    public void deleteCoBorrowerById(Integer loanId){
        lnLoanCoBorrowerDao.deleteLoanCoBorrowerById(loanId);
    }

    /**
     *
     * @param paramMap
     * @return
     */
    public Integer getCoBorrowerCount(Map<String, Object> paramMap){
        return lnLoanCoBorrowerDao.getCoBorrowerCount(paramMap);
    }

    public int updateCoBorrower(Map<String, Object> paramMap){
        return lnLoanCoBorrowerDao.updateCoBorrower(paramMap);
    }

    @Override
    public LnLoanCoBorrower seleteCoBorrower(Map<String, Object> map) {
        return lnLoanCoBorrowerDao.seleteCoBorrower(map);
    }
}
