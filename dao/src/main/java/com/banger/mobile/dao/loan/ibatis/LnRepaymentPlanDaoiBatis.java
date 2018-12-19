/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :还款计划-Dao-接口实现
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.loan.LnRepaymentPlanDao;
import com.banger.mobile.domain.model.loan.LnLoan;
import com.banger.mobile.domain.model.loan.LnRepaymentPlan;
import com.banger.mobile.ibatis.GenericDaoiBatis;
import com.banger.mobile.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author QianJie
 * @version $Id: LnRepaymentPlanDaoiBatis.java,v 0.1 Feb 17, 2013 2:54:46 PM QianJie Exp $
 */
public class LnRepaymentPlanDaoiBatis extends GenericDaoiBatis implements LnRepaymentPlanDao {

    public LnRepaymentPlanDaoiBatis() {
        super(LnRepaymentPlan.class);
    }
    /**
     * @param persistentClass
     */
    public LnRepaymentPlanDaoiBatis(Class persistentClass) {
        super(LnRepaymentPlan.class);
    }

    public List<LnRepaymentPlan> queryLnRepaymentPlan(Integer loanId){
        return this.getSqlMapClientTemplate().queryForList("queryLnRepaymentPlanById", loanId);
    }

    public void addRepaymentPlan(LnRepaymentPlan p) {
        this.getSqlMapClientTemplate().insert("addRepaymentPlan", p);
    }

    @Override
    public LnRepaymentPlan selectCurLnRepaymentPlanById(Integer loanId){
        return (LnRepaymentPlan)this.getSqlMapClientTemplate().queryForObject("selectCurLnRepaymentPlanById",loanId);
    }

    /**
     * 批量增加还款计划
     *
     * @param repaymentPlanList
     */
    @Override
    public void addRepaymentPlanBatch(List<LnRepaymentPlan> repaymentPlanList){
        this.exectuteBatchInsert("addRepaymentPlan",repaymentPlanList);
    }

    /**
     * 得到贷款的最后一期还款期号
     *
     * @param loanId
     * @return
     */
    @Override
    public Integer getLastSortNoByLoanId(Integer loanId){
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getLastSortNoByLoanId",loanId);
    }

    /**
     * @param loanId
     * @param sortno
     * @return
     */
    public Integer getNextSortNoByLoanId(Integer loanId, Integer sortno) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loanId", loanId);
        paramMap.put("sortno", sortno);
        return (Integer)this.getSqlMapClientTemplate().queryForObject("getNextSortNoByLoanId",paramMap);
    }

    @Override
    public Integer deletePlanByLoanIdBatch(List<Integer> loanIdList){
        return (Integer)this.executeBatchDelete("deletePlanByLoanId",loanIdList);
    }

    public PageUtil<LnRepaymentPlan> queryLnRepaymentPlanListPage(Map<String, Object> parameterMap, Page page){
        Map<String, Object> fConds = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            if (entry.getValue() instanceof String) {
                fConds.put(entry.getKey(), StringUtil.ReplaceSQLEscapeChar(StringUtil
                        .ReplaceSQLChar(entry.getValue().toString())));
            } else {
                fConds.put(entry.getKey(), entry.getValue());
            }
        }
        List<LnRepaymentPlan> list =(List<LnRepaymentPlan>) this.findQueryPage("queryLnRepaymentPlanListPage","queryLnRepaymentPlanCount",fConds,page);
        return new PageUtil<LnRepaymentPlan>(list, page);
    }

    @Override
    public LnRepaymentPlan getCurrentNoRepaymentPlan(Integer loanId) {
        return (LnRepaymentPlan)this.getSqlMapClientTemplate().queryForObject("getCurrentNoRepaymentPlan",loanId);
    }

    @Override
    public void updateLnRepaymentPlanById(LnRepaymentPlan lnRepaymentPlan) {
        this.getSqlMapClientTemplate().update("updateLnRepaymentPlanById", lnRepaymentPlan);
    }
}
