package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.loan.LnExceptionRepaymentPlan;
import com.banger.mobile.domain.model.loan.LnLoan;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-6
 * Time: 上午9:37
 * To change this template use File | Settings | File Templates.
 *
 * 异常还款计划
 */
public interface LnExceptionRepaymentPlanService {

    public List<LnExceptionRepaymentPlan> queryLnExceptionRepaymentPlan(Integer loanId);

    void deleteExceptionRepaymentPlan(Integer exceptionRepaymentPlanId);

    void deleteRepaymentPlanByLoanId(Integer loanId);

    void insertExceptionRepaymentPlan(LnExceptionRepaymentPlan lnExceptionRepaymentPlan);

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

    void delExceptionRepaymentPlan(LnLoan loan, Integer exceptionRepaymentPlanId);
}
