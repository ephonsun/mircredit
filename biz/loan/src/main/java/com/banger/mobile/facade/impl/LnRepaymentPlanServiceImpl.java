/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :还款计划-Service-接口实现
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.facade.impl;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.loan.LnRepaymentPlanDao;
import com.banger.mobile.domain.model.loan.LnRepaymentPlan;
import com.banger.mobile.facade.loan.LnRepaymentPlanService;

import java.util.List;
import java.util.Map;

/**
 * @author QianJie
 * @version $Id: LnRepaymentPlanServiceImpl.java,v 0.1 Feb 17, 2013 3:51:20 PM QianJie Exp $
 */
public class LnRepaymentPlanServiceImpl implements LnRepaymentPlanService{

    private LnRepaymentPlanDao lnRepaymentPlanDao;

    public void setLnRepaymentPlanDao(LnRepaymentPlanDao lnRepaymentPlanDao) {
        this.lnRepaymentPlanDao = lnRepaymentPlanDao;
    }

    public List<LnRepaymentPlan> queryLnRepaymentPlan(Integer loanId) {
        return lnRepaymentPlanDao.queryLnRepaymentPlan(loanId);
    }

    public void addRepaymentPlan(LnRepaymentPlan p) {
        lnRepaymentPlanDao.addRepaymentPlan(p);
    }

    /**
     * 查出贷款的本期还款计划
     * @param loanId
     * @return
     */
    public LnRepaymentPlan selectCurLnRepaymentPlanById(Integer loanId){
        return lnRepaymentPlanDao.selectCurLnRepaymentPlanById(loanId);
    }

    /**
     * 批量插入还款计划表
     * 
     * @param lnRepaymentPlanList
     */
    public void addRepaymentPlanBatch(List<LnRepaymentPlan> lnRepaymentPlanList){
        lnRepaymentPlanDao.addRepaymentPlanBatch(lnRepaymentPlanList);
    }

    /**
     * 根据贷款ID，得到当前贷款的最后一期还款的期号
     * 
     * @param loanId
     * @return
     */
    public Integer getLastSortNoByLoanId(Integer loanId){
        return lnRepaymentPlanDao.getLastSortNoByLoanId(loanId);
    }
    
    /**
     * 得到当前贷款下一期期号
     * @param loanId
     * @param sortno
     * @return
     */
    public Integer getNextSortNoByLoanId(Integer loanId, Integer sortno) {
        if (sortno == null) {
            sortno = 0;
        }
        return lnRepaymentPlanDao.getNextSortNoByLoanId(loanId, sortno);
    }

    public Integer deletePlanByLoanIdBatch(List<Integer> loanIdList){
        return lnRepaymentPlanDao.deletePlanByLoanIdBatch(loanIdList);
    }

    public PageUtil<LnRepaymentPlan> queryLnRepaymentPlanListPage(Map<String, Object> parameterMap, Page page){
       return  lnRepaymentPlanDao.queryLnRepaymentPlanListPage(parameterMap,page);
    }

    @Override
    public LnRepaymentPlan getCurrentNoRepaymentPlan(Integer loanId) {
        return lnRepaymentPlanDao.getCurrentNoRepaymentPlan(loanId);
    }

    @Override
    public void updateLnRepaymentPlanById(LnRepaymentPlan lnRepaymentPlan) {
        lnRepaymentPlanDao.updateLnRepaymentPlanById(lnRepaymentPlan);
    }
}
