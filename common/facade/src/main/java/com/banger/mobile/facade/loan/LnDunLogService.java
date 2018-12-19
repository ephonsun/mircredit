package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.loan.LnDunLog;
import com.banger.mobile.domain.model.record.RecordInfo;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-6
 * Time: 上午9:36
 * To change this template use File | Settings | File Templates.
 *
 * 贷款催收记录
 */
public interface LnDunLogService {

    //得到贷款所有催收记录
    public List<LnDunLog> getDunLogByLoanId(Integer loanId);

    //保存
    public void addLnDunLog(LnDunLog newLog);

    public void addLnDunLogFromPad(CustomerData data);
    
    public void addLnDunLogFromPad(CustomerData data, boolean isPhone);

    public void addLnDunLogFromLocalPhone(CustomerData data, RecordInfo info);

    public Integer getNextDunLogId();

    public LnDunLog getDunLogById(Integer dunLogId);

    void updateDunLogById(Map<String, Object> paramMap);
}
