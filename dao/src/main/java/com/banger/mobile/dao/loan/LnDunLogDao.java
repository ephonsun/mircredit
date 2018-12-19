package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.LnDunLog;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-2-6
 * Time: 上午9:19
 * To change this template use File | Settings | File Templates.
 */
public interface LnDunLogDao {
    //得到贷款所有催收记录
    List<LnDunLog> getDunLogByLoanId(Integer loanId);

    //保存
    void addLnDunLog(LnDunLog newLog);

    Integer getNextDunLogId();

    LnDunLog getDunLogById(Integer dunLogId);

    void updateDunLogById(Map<String, Object> paramMap);
}
