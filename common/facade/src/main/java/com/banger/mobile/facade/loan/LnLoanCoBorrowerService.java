/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :共同借贷人-Service-接口
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.facade.loan;

import java.util.List;
import java.util.Map;

import com.banger.mobile.domain.model.loan.LnLoanCoBorrower;
import com.banger.mobile.domain.model.loan.LnLoanCoBorrowerBean;

/**
 * @author QianJie
 * @version $Id: LnLoanCoBorrowerService.java,v 0.1 Feb 17, 2013 3:39:18 PM QianJie Exp $
 */
public interface LnLoanCoBorrowerService {

    /**
     * 查询共同借贷人数据
     * @param conds
     * @return
     */
    public List<LnLoanCoBorrowerBean> getAllLnLoanCoBorrowerBeanByConds(Map<String, Object> conds);

    public void addLnLoanCoBorrower(LnLoanCoBorrower co);

    public void deleteLnLoanCoBorrower(Integer coId);

    int addLnLoanCoBorrowerBatch(List<LnLoanCoBorrower> coBorrowerList);

    int updateLoanCoBorrowerBatch(List<LnLoanCoBorrower> coBorrowerList);

    void deleteCoBorrowerById(Integer loanId);

    Integer getCoBorrowerCount(Map<String, Object> paramMap);

    int updateCoBorrower(Map<String, Object> paramMap);

    LnLoanCoBorrower seleteCoBorrower(Map<String,Object> map);
}
