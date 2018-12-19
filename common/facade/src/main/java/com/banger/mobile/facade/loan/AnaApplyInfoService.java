package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.loan.AnaApplyInfo;
import com.banger.mobile.domain.model.loan.AnaChildren;
import com.banger.mobile.domain.model.loan.ApplyAddressInfo;

import java.util.List;
import java.util.Map;

/**
 * @author zhangfp
 * @version $Id: AnaApplyInfoService v 0.1 ${} 上午10:35 zhangfp Exp $
 *
 * 上会分析表--申请人信息service
 */
public interface AnaApplyInfoService {
    AnaApplyInfo selectApplyInfoByLoanId(Map<String, Object> paramMap);

    List<ApplyAddressInfo> selectApplyAddressInfo(Map<String, Object> paramMap);

    List<AnaChildren> selectChildrenInfoList(Integer applyInfoId);

    Integer getNextApplyInfoId();

    void insertApplyInfo(AnaApplyInfo anaApplyInfo);

    void insertAnaChildren(AnaChildren anaChildren);

    void insertApplyAndChildrenBatch(AnaApplyInfo anaApplyInfo);

    void insertAnaChildrenBatch(List<AnaChildren> anaChildrenList);

    void updateApplyInfo(AnaApplyInfo anaApplyInfo);

    void updateAnaChildrenBatch(List<AnaChildren> anaChildrenList);

    void updateAnaChildren(AnaChildren anaChildren);

    void deleteAnaChildren(Integer applyInfoId);

    void updateApplyAndChildren(AnaApplyInfo anaApplyInfo,
                                List<AnaChildren> addingChildrenList, List<AnaChildren> editingChildrenList, Boolean isClean);
}
