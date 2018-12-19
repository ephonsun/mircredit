/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :担保人-Dao-接口
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.LnLoanGuarantor;
import com.banger.mobile.domain.model.loan.LnLoanGuarantorBean;

import java.util.List;
import java.util.Map;

/**
 * @author QianJie
 * @version $Id: LnLoanGuarantorDao.java,v 0.1 Feb 17, 2013 2:53:58 PM QianJie Exp $
 */
public interface LnLoanGuarantorDao {
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

    void deleteLoanGuarantor(Integer loanId);

    Integer getGuarantorCount(Map<String, Object> paramMap);

    Integer updateLoanGuarantor(Map<String, Object> paramMap);

    LnLoanGuarantor selectGuarantorSingle(Map<String, Object> paramMap);

    void delGuaByLoanCustomerId(Map<String, Object> paramMap);

    void delDeletedGuaByCusList(Map<String, Object> paramMap);

    List<Integer> getCusIdListByLoanId(Integer loanId);

    Integer updateLoanGuarantorByLoan(Map<String, Object> paramMap);

	/**
	 * 
	 * @param loanId
	 * @return
	 */
	public void deleteLnLoanGuarantorByLoanId(Integer loanId);

}
