package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.loan.LnVerifyHistory;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ouyl
 * Date: 13-3-14
 * Time: 下午3:40
 * To change this template use File | Settings | File Templates.
 *
 */
public interface LnVerifyHistoryService {

    //添加审计历史操作
    void insertLnVerifyHistory(LnVerifyHistory lnVerifyHistory);

    //批量添加审计历史操作
    void insertLnVerifyHistoryBatch(List<LnVerifyHistory> lnVerifyHistoryList);

    //查询审计历史操作
    List<LnVerifyHistory> getLnVerifyHistoryByLoanId(Integer loanId);

    LnVerifyHistory getFirstVerifyHistoryByLoanId(Map<String, Object> paramMap);
}
