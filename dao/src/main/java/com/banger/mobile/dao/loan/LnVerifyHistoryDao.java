package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.LnVerifyHistory;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-14
 * Time: 上午11:20
 * To change this template use File | Settings | File Templates.
 */
public interface LnVerifyHistoryDao {

    void insertLnVerifyHistory(LnVerifyHistory lnVerifyHistory);

    //批量添加审计历史操作
    void insertLnVerifyHistoryBatch(List<LnVerifyHistory> lnVerifyHistoryList);

    List<LnVerifyHistory> getLnVerifyHistoryByLoanId(Integer loanId);

    LnVerifyHistory getFirstVerifyHistoryByLoanId(Map<String, Object> paramMap);
}
