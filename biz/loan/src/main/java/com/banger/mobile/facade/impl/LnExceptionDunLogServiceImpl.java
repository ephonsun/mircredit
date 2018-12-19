package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnExceptionDunLogDao;
import com.banger.mobile.domain.model.data.CustomerData;
import com.banger.mobile.domain.model.loan.LnExceptionDunLog;
import com.banger.mobile.domain.model.record.RecordInfo;
import com.banger.mobile.facade.loan.LnExceptionDunLogService;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-6
 * Time: 上午10:20
 * To change this template use File | Settings | File Templates.
 *
 * 异常贷款催收记录
 */
public class LnExceptionDunLogServiceImpl implements LnExceptionDunLogService {

    private LnExceptionDunLogDao lnExceptionDunLogDao;

    public LnExceptionDunLogDao getLnExceptionDunLogDao() {
        return lnExceptionDunLogDao;
    }

    public void setLnExceptionDunLogDao(LnExceptionDunLogDao lnExceptionDunLogDao) {
        this.lnExceptionDunLogDao = lnExceptionDunLogDao;
    }

    public List<LnExceptionDunLog> getExpDunLogByLoanId(Integer loanId) {
        return lnExceptionDunLogDao.getExpDunLogByLoanId(loanId);
    }

    /**
     * @return
     * @see com.banger.mobile.facade.loan.LnExceptionDunLogService#getNextExceptionDunLogId()
     */
    public Integer getNextExceptionDunLogId() {
        return lnExceptionDunLogDao.getNextExceptionDunLogId();
    }

    /**
     * @param paramMap
     * @see com.banger.mobile.facade.loan.LnExceptionDunLogService#updateExceptionDunLogById(java.util.Map)
     */
    public void updateExceptionDunLogById(Map<String, Object> paramMap) {
        this.lnExceptionDunLogDao.updateExceptionDunLogById(paramMap);
    }

    /**
     * @param data
     * @see com.banger.mobile.facade.loan.LnExceptionDunLogService#addLnExceptionDunLogFromPad(com.banger.mobile.domain.model.data.CustomerData)
     */
    public void addLnExceptionDunLogFromPad(CustomerData data) {
        LnExceptionDunLog newLog = new LnExceptionDunLog();
        newLog.setExceptionDunLogId(data.getExceptionDunLogId());
        newLog.setLoanId(data.getLoanId());
        newLog.setDunUserId(data.getUploadUserId());
        newLog.setDunDate(Calendar.getInstance().getTime());
        newLog.setDunType(3);// 实地
        newLog.setCustomerDataId(data.getCustomerDataId());
        // newLog.setSortno(null); // TODO
        newLog.setCreateUser(data.getUploadUserId());
        newLog.setCreateDate(Calendar.getInstance().getTime());
        
        this.addLnExceptionDunLog(newLog);
    }
    
    public void addLnExceptionDunLogFromPad(CustomerData data, boolean isPhone) {
        LnExceptionDunLog newLog = new LnExceptionDunLog();
        newLog.setExceptionDunLogId(data.getExceptionDunLogId());
        newLog.setLoanId(data.getLoanId());
        newLog.setDunUserId(data.getUploadUserId());
        newLog.setDunDate(Calendar.getInstance().getTime());
        newLog.setDunType(2);// 电话
        newLog.setCustomerDataId(data.getCustomerDataId());
        // newLog.setSortno(null); // TODO
        newLog.setCreateUser(data.getUploadUserId());
        newLog.setCreateDate(Calendar.getInstance().getTime());

        newLog.setRemark(data.getRemark());
        this.addLnExceptionDunLog(newLog);
    }

    public void addLnExceptionDunLogFromLocalPhone(CustomerData data, RecordInfo info) {
        LnExceptionDunLog newLog = new LnExceptionDunLog();

        if (data.getExceptionDunLogId() == null || data.getExceptionDunLogId() <= 0) {
            data.setExceptionDunLogId(this.getNextExceptionDunLogId());
        }
        newLog.setExceptionDunLogId(data.getExceptionDunLogId());
        newLog.setLoanId(data.getLoanId());
        newLog.setDunUserId(data.getUploadUserId());
        newLog.setDunDate(Calendar.getInstance().getTime());
        newLog.setDunType(2);// 电话
        newLog.setCustomerDataId(data.getCustomerDataId());
        // newLog.setSortno(null); // TODO
        newLog.setCreateUser(data.getUploadUserId());
        newLog.setCreateDate(Calendar.getInstance().getTime());

        newLog.setRemark(data.getRemark());

        //web端电话催收特殊处理
        newLog.setUpdateUser(info.getRecordInfoId());

        this.addLnExceptionDunLog(newLog);
    }

    /**
     * @param newLog
     */
    public void addLnExceptionDunLog(LnExceptionDunLog newLog) {
        lnExceptionDunLogDao.addLnExceptionDunLog(newLog);
    }

    /**
     * @param exceptionDunLogId
     * @return
     * @see com.banger.mobile.facade.loan.LnExceptionDunLogService#getExceptionDunLogById(java.lang.Integer)
     */
    public LnExceptionDunLog getExceptionDunLogById(Integer exceptionDunLogId) {
        return lnExceptionDunLogDao.getExceptionDunLogById(exceptionDunLogId);
    }
}
