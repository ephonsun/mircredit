package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.base.loan.BaseAnaCoBorrower;
import com.banger.mobile.domain.model.base.loan.BaseAnaGuarantor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zhangfp
 * @version $Id: AnaCoBorrowerAndGuarantorService v 0.1 ${} 下午3:18 zhangfp Exp $
 *
 * 上会分析表的共同借贷人和担保人service
 */
public interface AnaCoBorrowerAndGuarantorService {
    List<BaseAnaGuarantor> getAllGuarantorListByLoan(Map<String, Object> paramMap);

    List<BaseAnaCoBorrower> getAllCoBorrowerListByLoan(Map<String, Object> paramMap);

    void insertGuarantorOfLoanBatch(List<BaseAnaGuarantor> baseAnaGuarantorList);

    void insertCoBorrowerOfLoanBatch(List<BaseAnaCoBorrower> baseAnaCoBorrowerList);

    void insertGuarantorOfLoan(BaseAnaGuarantor baseAnaGuarantor);

    void insertCoBorrowerOfLoan(BaseAnaCoBorrower anaCoBorrower);

    void delAllGuarantorByLoan(Integer loanId);

    void delAllCoBorrowerByLoan(Integer loanId);

    @Transactional
    void saveAnalyzesCobInfo(Integer loanId, List<BaseAnaCoBorrower> baseAnaCoBorrowerList);

    @Transactional
    void saveAnalyzesGuaInfo(Integer loanId, List<BaseAnaGuarantor> baseAnaGuarantorList);
}
