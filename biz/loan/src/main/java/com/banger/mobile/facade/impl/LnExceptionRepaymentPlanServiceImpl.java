package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnExceptionRepaymentPlanDao;
import com.banger.mobile.dao.loan.LnLoanDao;
import com.banger.mobile.domain.model.loan.LnExceptionRepaymentPlan;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.facade.loan.LnExceptionRepaymentPlanService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-6
 * Time: 上午10:16
 * To change this template use File | Settings | File Templates.
 *
 * 异常还款计划
 */
public class LnExceptionRepaymentPlanServiceImpl implements LnExceptionRepaymentPlanService {

    private LnExceptionRepaymentPlanDao lnExceptionRepaymentPlanDao;
    private LnLoanDao lnLoanDao;

    public LnLoanDao getLnLoanDao() {
        return lnLoanDao;
    }

    public void setLnLoanDao(LnLoanDao lnLoanDao) {
        this.lnLoanDao = lnLoanDao;
    }

    public LnExceptionRepaymentPlanDao getLnExceptionRepaymentPlanDao() {
        return lnExceptionRepaymentPlanDao;
    }

    public void setLnExceptionRepaymentPlanDao(LnExceptionRepaymentPlanDao lnExceptionRepaymentPlanDao) {
        this.lnExceptionRepaymentPlanDao = lnExceptionRepaymentPlanDao;
    }

    public List<LnExceptionRepaymentPlan> queryLnExceptionRepaymentPlan(Integer loanId){
        return   lnExceptionRepaymentPlanDao.queryLnExceptionRepaymentPlan(loanId);
    }

    public void insertExceptionRepaymentPlan(LnExceptionRepaymentPlan lnExceptionRepaymentPlan){
         lnExceptionRepaymentPlanDao.insertExceptionRepaymentPlan(lnExceptionRepaymentPlan);
    }

    public void deleteExceptionRepaymentPlan(Integer exceptionRepaymentPlanId){
         lnExceptionRepaymentPlanDao.deleteExceptionRepaymentPlan(exceptionRepaymentPlanId);
    }

    public void deleteRepaymentPlanByLoanId(Integer loanId){
             lnExceptionRepaymentPlanDao.deleteRepaymentPlanByLoanId(loanId);
    }

    public void updateExceptionRepaymentPlan(Map<String, Object> paramMap){
          lnExceptionRepaymentPlanDao.updateExceptionRepaymentPlan(paramMap);
    }

    public LnExceptionRepaymentPlan queryLnExceptionRepaymentPlanById(Integer exceptionRepaymentPlanId){
        return lnExceptionRepaymentPlanDao.queryLnExceptionRepaymentPlanById(exceptionRepaymentPlanId);
    }

    public void updateExceptionRepaymentPlanBySortno(Map<String, Object> paramMap){
        lnExceptionRepaymentPlanDao.updateExceptionRepaymentPlanBySortno(paramMap);
    }

    public Integer getLastExceptionSortNoByLoanId(Integer loanId){
        return lnExceptionRepaymentPlanDao.getLastExceptionSortNoByLoanId(loanId);
    }

    /**
     * @param loanId
     * @param sortno
     * @return
     * @see com.banger.mobile.facade.loan.LnExceptionRepaymentPlanService#getNextSortNoByLoanId(java.lang.Integer, java.lang.Integer)
     */
    public Integer getNextSortNoByLoanId(Integer loanId, Integer sortno) {
        if (sortno == null) {
            sortno = 0;
        }
        return lnExceptionRepaymentPlanDao.getNextSortNoByLoanId(loanId, sortno);
    }

    /**
     * 得到某笔贷款的最后一期异常还款计划记录
     * 
     * @param loanId
     * @return
     */
    public LnExceptionRepaymentPlan getLastExceptionRepaymentPlan(Integer loanId){
        return lnExceptionRepaymentPlanDao.getLastExceptionRepaymentPlan(loanId);
    }

    /**
     * 移除异常贷款还款计划
     * 
     * @param loan
     * @param exceptionRepaymentPlanId
     */
    @Transactional
    public void delExceptionRepaymentPlan(LnLoan loan, Integer exceptionRepaymentPlanId){/*
        //当前要删除的异常还款计划
        LnExceptionRepaymentPlan plan = lnExceptionRepaymentPlanDao.queryLnExceptionRepaymentPlanById(exceptionRepaymentPlanId);
        //删除当前异常还款计划
        lnExceptionRepaymentPlanDao.deleteExceptionRepaymentPlan(exceptionRepaymentPlanId);
        //该贷款所有异常还款计划中，排序最大的一个
        Integer lastSortNo = lnExceptionRepaymentPlanDao.getLastExceptionSortNoByLoanId(loan.getLoanId());
        if (plan.getSortno().equals(lastSortNo)){
            //移除的为最后的异常还款计划
        }else {
            //移除的是最后异常还款计划之前的,那么，sortno在这之后的，都要向前移一位(-1)
            Map<String,Object> updateRepaymentPlanMap = new HashMap<String, Object>();
            updateRepaymentPlanMap.put("loanId",loan.getLoanId());
            updateRepaymentPlanMap.put("sortno",plan.getSortno());
            lnExceptionRepaymentPlanDao.updateSortNoByLoanId(updateRepaymentPlanMap);
        }
        //同时更新贷款主表的相关记录
        
        这里存在两种情况
        1、贷款已经进入当前被删除的异常还款计划催收阶段
        2、贷款完全没有进入任何催收阶段
         
        //如果贷款完全没有进入任何催收阶段，那么贷款主表不需要做任何修改。所以只有第一种情况需要修改贷款主表
        if (!(loan.getSortno() == null || loan.getSortno() == 0)){
            if (loan.getSortno() >= 1){
                loan.setSortno(0);
            }
            lnLoanDao.updateLoanByRepaymentPlan(loan);
        }
    */}
}
