package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.AnaApplyInfo;
import com.banger.mobile.domain.model.loan.AnaChildren;
import com.banger.mobile.domain.model.loan.ApplyAddressInfo;

import java.util.List;
import java.util.Map;

/**
 * @author zhangfp
 * @version $Id: AnaApplyInfoDao v 0.1 ${} 上午10:31 zhangfp Exp $
 */
public interface AnaApplyInfoDao {
    AnaApplyInfo selectApplyInfoByLoanId(Map<String, Object> paramMap);

    List<ApplyAddressInfo> selectApplyAddressInfoList(Map<String, Object> paramMap);

    List<AnaChildren> selectChildrenInfoList(Integer applyInfoId);

    Integer getNextApplyInfoId();

    void insertApplyInfo(AnaApplyInfo anaApplyInfo);

    void insertAnaChildren(AnaChildren anaChildren);

    void insertAnaChildrenBatch(List<AnaChildren> anaChildrenList);

    void updateApplyInfo(AnaApplyInfo anaApplyInfo);

    void updateAnaChildren(AnaChildren anaChildren);

    void updateAnaChildrenBatch(List<AnaChildren> anaChildrenList);

    void deleteAnaChildren(Integer applyInfoId);
}
