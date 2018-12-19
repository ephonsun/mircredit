package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnExceptionRepaymentPlanDao;
import com.banger.mobile.domain.model.loan.LnExceptionRepaymentPlan;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-6
 * Time: 上午9:12
 * To change this template use File | Settings | File Templates.
 *
 * 异常还款计划
 */
public class LnExceptionRepaymentPlanDaoiBatis extends GenericDaoiBatis implements LnExceptionRepaymentPlanDao {

    public LnExceptionRepaymentPlanDaoiBatis() {
        super(LnExceptionRepaymentPlan.class);
    }
    
    public LnExceptionRepaymentPlanDaoiBatis(Class persistentClass) {
        super(LnExceptionRepaymentPlan.class);
    }

    public List<LnExceptionRepaymentPlan> queryLnExceptionRepaymentPlan(Integer loanId){
        return this.getSqlMapClientTemplate().queryForList("queryLnExceptionRepaymentPlan", loanId);
    }

    public void insertExceptionRepaymentPlan(LnExceptionRepaymentPlan lnExceptionRepaymentPlan){
         this.getSqlMapClientTemplate().insert("insertExceptionRepaymentPlan",lnExceptionRepaymentPlan);
    }

    public void deleteExceptionRepaymentPlan(Integer exceptionRepaymentPlanId){
         this.getSqlMapClientTemplate().delete("deleteExceptionRepaymentPlan",exceptionRepaymentPlanId);
    }

    public void deleteRepaymentPlanByLoanId(Integer loanId){
        this.getSqlMapClientTemplate().delete("deleteRepaymentPlanByLoanId",loanId);
    }

    public void updateExceptionRepaymentPlan(Map<String, Object> paramMap){
        this.getSqlMapClientTemplate().update("updateExceptionRepaymentPlan",paramMap);
    }

    public LnExceptionRepaymentPlan queryLnExceptionRepaymentPlanById(Integer exceptionRepaymentPlanId){
       return (LnExceptionRepaymentPlan) this.getSqlMapClientTemplate().queryForObject("queryLnExceptionRepaymentPlanById",exceptionRepaymentPlanId);
    }

    public void updateExceptionRepaymentPlanBySortno(Map<String, Object> paramMap){
        this.getSqlMapClientTemplate().update("updateExceptionRepaymentPlanBySortno",paramMap);
    }

    public Integer getLastExceptionSortNoByLoanId(Integer loanId){
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getLastExceptionSortNoByLoanId",loanId);
    }

    /**
     * @param loanId
     * @param sortno
     * @return
     * @see com.banger.mobile.dao.loan.LnExceptionRepaymentPlanDao#getNextSortNoByLoanId(java.lang.Integer, java.lang.Integer)
     */
    public Integer getNextSortNoByLoanId(Integer loanId, Integer sortno) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loanId", loanId);
        paramMap.put("sortno", sortno);
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getExceptionNextSortNoByLoanId",paramMap);
    }

    /**
     * 得到某笔贷款的最后一期异常还款计划记录
     * @param loanId
     * @return
     */
    public LnExceptionRepaymentPlan getLastExceptionRepaymentPlan(Integer loanId){
        return (LnExceptionRepaymentPlan)this.getSqlMapClientTemplate().queryForObject("getLastExceptionRepaymentPlan", loanId);
    }

    /**
     * 修改还款计划的sortno
     * 
     * @param paramMap
     */
    public void updateSortNoByLoanId(Map<String,Object> paramMap){
        this.getSqlMapClientTemplate().update("updateSortNoByLoanId",paramMap);
    }
}
