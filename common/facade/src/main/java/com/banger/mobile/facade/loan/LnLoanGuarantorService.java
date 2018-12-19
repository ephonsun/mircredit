/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :担保人-Service-接口
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.loan.LnLoanGuarantor;
import com.banger.mobile.domain.model.loan.LnLoanGuarantorBean;

import java.util.List;
import java.util.Map;

/**
 * @author QianJie
 * @version $Id: LnLoanGuarantorService.java,v 0.1 Feb 17, 2013 3:40:52 PM QianJie Exp $
 */
public interface LnLoanGuarantorService {

    /**
     * 查询所有担保人数据
     * @param conds
     * @return
     */
    public List<LnLoanGuarantorBean> getAllLnLoanGuarantorBeanByConds(Map<String, Object> conds);

    public void addLnLoanGuarantor(LnLoanGuarantor gu);

    public void deleteLnLoanGuarantor(Integer guId);

    int addLnLoanGuarantorBatch(List<LnLoanGuarantor> guarantorList);

    int updateLoanGuarantorBatch(List<LnLoanGuarantor> guarantorList);

    void deleteLoanGuarantorById(Integer loanId);

    Integer getGuarantorCount(Map<String, Object> paramMap);

    Integer updateLoanGuarantor(Map<String, Object> paramMap);

    LnLoanGuarantor selectGuarantorSingle(Map<String, Object> paramMap);

    void delGuaByLoanCustomerId(Map<String, Object> paramMap);

    void delDeletedGuaByCusList(Map<String, Object> paramMap);

    List<Integer> getCusIdListByLoanId(Integer loanId);

    Integer updateLoanGuarantorByLoan(Map<String, Object> paramMap);

	/**
	 * 根据贷款编号删除
	 * @param loanId
	 */
	public void deleteLnLoanGuarantorByLoanId(Integer loanId);
}
