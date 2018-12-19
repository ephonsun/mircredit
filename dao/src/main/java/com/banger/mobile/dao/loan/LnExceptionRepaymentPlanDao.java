package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.LnExceptionRepaymentPlan;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-6
 * Time: 上午9:13
 * To change this template use File | Settings | File Templates.
 */
public interface LnExceptionRepaymentPlanDao {

    public List<LnExceptionRepaymentPlan> queryLnExceptionRepaymentPlan(Integer loanId);

    void insertExceptionRepaymentPlan(LnExceptionRepaymentPlan lnExceptionRepaymentPlan);

    void deleteExceptionRepaymentPlan(Integer exceptionRepaymentPlanId);

    void deleteRepaymentPlanByLoanId(Integer loanId);

    void updateExceptionRepaymentPlan(Map<String, Object> paramMap);

    public LnExceptionRepaymentPlan queryLnExceptionRepaymentPlanById(Integer exceptionRepaymentPlanId);

    void updateExceptionRepaymentPlanBySortno(Map<String, Object> paramMap);

    Integer getLastExceptionSortNoByLoanId(Integer loanId);

    /**
     * @param loanId
     * @param sortno
     * @return
     */
    public Integer getNextSortNoByLoanId(Integer loanId, Integer sortno);

    LnExceptionRepaymentPlan getLastExceptionRepaymentPlan(Integer loanId);

    void updateSortNoByLoanId(Map<String, Object> paramMap);
}
