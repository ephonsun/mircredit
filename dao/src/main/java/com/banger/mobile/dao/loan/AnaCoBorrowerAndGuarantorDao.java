package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.base.loan.BaseAnaCoBorrower;
import com.banger.mobile.domain.model.base.loan.BaseAnaGuarantor;

import java.util.List;
import java.util.Map;

/**
 * @author zhangfp
 * @version $Id: AnaCoBorrowerAndGuarantorDao v 0.1 ${} 下午3:13 zhangfp Exp $
 *
 * 上会分析表的共同借贷人和担保人dao
 */
public interface AnaCoBorrowerAndGuarantorDao {
    List<BaseAnaCoBorrower> getAllCoBorrowerListByLoan(Map<String, Object> paramMap);

    List<BaseAnaGuarantor> getAllGuarantorListByLoan(Map<String, Object> paramMap);

    void delAllCoBorrowerByLoan(Integer loanId);

    void delAllGuarantorByLoan(Integer loanId);

    void insertCoBorrowerOfLoan(BaseAnaCoBorrower anaCoBorrower);

    void insertGuarantorOfLoan(BaseAnaGuarantor baseAnaGuarantor);

    void insertCoBorrowerOfLoanBatch(List<BaseAnaCoBorrower> baseAnaCoBorrowerList);

    void insertGuarantorOfLoanBatch(List<BaseAnaGuarantor> baseAnaGuarantorList);
}
