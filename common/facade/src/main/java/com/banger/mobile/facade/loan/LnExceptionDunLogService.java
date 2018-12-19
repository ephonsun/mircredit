package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.loan.LnExceptionDunLog;
import com.banger.mobile.domain.model.record.RecordInfo;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-6
 * Time: 上午9:37
 * To change this template use File | Settings | File Templates.
 *
 * 异常贷款催收记录
 */
public interface LnExceptionDunLogService {
    List<LnExceptionDunLog> getExpDunLogByLoanId(Integer loanId);

    void addLnExceptionDunLog(LnExceptionDunLog newLog);
    
    /**
     * @return
     */
    Integer getNextExceptionDunLogId();

    /**
     * @param paramMap
     */
    void updateExceptionDunLogById(Map<String, Object> paramMap);

    /**
     * @param exceptionDunLogId
     * @return
     */
    LnExceptionDunLog getExceptionDunLogById(Integer exceptionDunLogId);

    /**
     * @param data
     */
    void addLnExceptionDunLogFromPad(CustomerData data);
    
    void addLnExceptionDunLogFromPad(CustomerData data, boolean isPhone);

    void addLnExceptionDunLogFromLocalPhone(CustomerData data, RecordInfo info);
}
