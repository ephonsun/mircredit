package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnDunLogDao;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.loan.LnDunLog;
import com.banger.mobile.domain.model.record.RecordInfo;
import com.banger.mobile.facade.loan.LnDunLogService;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: Zhangfp Date: 13-2-6 Time: 上午10:19 To
 * change this template use File | Settings | File Templates.
 * 
 * 贷款催收记录
 */
public class LnDunLogServiceImpl implements LnDunLogService {
    private LnDunLogDao lnDunLogDao;

    // 得到贷款所有催收记录
    public List<LnDunLog> getDunLogByLoanId(Integer loanId) {
        return lnDunLogDao.getDunLogByLoanId(loanId);
    }

    public void addLnDunLog(LnDunLog newLog) {
        lnDunLogDao.addLnDunLog(newLog);
    }

    public void addLnDunLogFromPad(CustomerData data) {
        LnDunLog newLog = new LnDunLog();
        newLog.setDunLogId(data.getDunLogId());
        newLog.setLoanId(data.getLoanId());
        newLog.setDunUserId(data.getUploadUserId());
        newLog.setDunDate(Calendar.getInstance().getTime());
        newLog.setDunType(3);// 实地
        newLog.setCustomerDataId(data.getCustomerDataId());
        // newLog.setSortno(null); // TODO
        newLog.setCreateUser(data.getUploadUserId());
        newLog.setCreateDate(Calendar.getInstance().getTime());
        
        this.addLnDunLog(newLog);
    }
    
    public void addLnDunLogFromPad(CustomerData data, boolean isPhone) {
        LnDunLog newLog = new LnDunLog();
        if (data.getDunLogId() == null || data.getDunLogId() <= 0) {
            data.setDunLogId(this.getNextDunLogId());
        }
        newLog.setDunLogId(data.getDunLogId());
        newLog.setLoanId(data.getLoanId());
        newLog.setDunUserId(data.getUploadUserId());
        newLog.setDunDate(Calendar.getInstance().getTime());
        newLog.setDunType(2);// 电话
        newLog.setCustomerDataId(data.getCustomerDataId());
        // newLog.setSortno(null); // TODO
        newLog.setCreateUser(data.getUploadUserId());
        newLog.setCreateDate(Calendar.getInstance().getTime());
        newLog.setRemark(data.getRemark());

        this.addLnDunLog(newLog);
    }

    public void addLnDunLogFromLocalPhone(CustomerData data, RecordInfo info) {
        LnDunLog newLog = new LnDunLog();
        if (data.getDunLogId() == null || data.getDunLogId() <= 0) {
            data.setDunLogId(this.getNextDunLogId());
        }
        newLog.setDunLogId(data.getDunLogId());
        newLog.setLoanId(data.getLoanId());
        newLog.setDunUserId(data.getUploadUserId());
        newLog.setDunDate(Calendar.getInstance().getTime());
        newLog.setDunType(2);// 电话
        newLog.setCustomerDataId(data.getCustomerDataId());
        // newLog.setSortno(null); // TODO
        newLog.setCreateUser(data.getUploadUserId());
        newLog.setCreateDate(Calendar.getInstance().getTime());
        newLog.setRemark(data.getRemark());

        //固定电话催收特殊处理！！！
        newLog.setUpdateUser(info.getRecordInfoId());

        this.addLnDunLog(newLog);
    }

    /**
     * 更新催收记录表
     *
     * @param paramMap
     */
    public void updateDunLogById(Map<String, Object> paramMap){
        lnDunLogDao.updateDunLogById(paramMap);
    }

    public Integer getNextDunLogId() {
        return lnDunLogDao.getNextDunLogId();
    }

    public LnDunLog getDunLogById(Integer dunLogId) {
        return lnDunLogDao.getDunLogById(dunLogId);
    }

    /** getter setter **/
    public void setLnDunLogDao(LnDunLogDao lnDunLogDao) {
        this.lnDunLogDao = lnDunLogDao;
    }
}
