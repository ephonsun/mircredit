/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :还款计划-Service-接口
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.facade.loan;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.loan.LnRepaymentPlan;

import java.util.List;
import java.util.Map;


/**
 * @author QianJie
 * @version $Id: LnRepaymentPlanService.java,v 0.1 Feb 17, 2013 3:42:53 PM QianJie Exp $
 */
public interface LnRepaymentPlanService {

    public List<LnRepaymentPlan> queryLnRepaymentPlan(Integer loanId) ;

    public void addRepaymentPlan(LnRepaymentPlan p);

    LnRepaymentPlan selectCurLnRepaymentPlanById(Integer loanId);

    void addRepaymentPlanBatch(List<LnRepaymentPlan> lnRepaymentPlanList);

    Integer getLastSortNoByLoanId(Integer loanId);

    /**
     * @param loanId
     * @param sortno
     * @return
     */
    public Integer getNextSortNoByLoanId(Integer loanId, Integer sortno);

    Integer deletePlanByLoanIdBatch(List<Integer> loanIdList);

    public PageUtil<LnRepaymentPlan> queryLnRepaymentPlanListPage(Map<String, Object> parameterMap, Page page);

    LnRepaymentPlan getCurrentNoRepaymentPlan(Integer loanId);

    void updateLnRepaymentPlanById(LnRepaymentPlan lnRepaymentPlan);
}
