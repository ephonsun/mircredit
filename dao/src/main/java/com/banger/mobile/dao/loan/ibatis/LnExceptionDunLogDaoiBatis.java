package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnExceptionDunLogDao;
import com.banger.mobile.domain.model.loan.LnExceptionDunLog;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-6
 * Time: 上午9:18
 * To change this template use File | Settings | File Templates.
 *
 * 异常贷款催收记录
 */
public class LnExceptionDunLogDaoiBatis extends GenericDaoiBatis implements LnExceptionDunLogDao {

    public LnExceptionDunLogDaoiBatis() {
        super(LnExceptionDunLog.class);
    }
    
    public LnExceptionDunLogDaoiBatis(Class persistentClass) {
        super(LnExceptionDunLog.class);
    }

    @Override
    public List<LnExceptionDunLog> getExpDunLogByLoanId(Integer loanId) {
        return this.getSqlMapClientTemplate().queryForList("getExpDunLogByLoanId",loanId);
    }

    /**
     * @return
     * @see com.banger.mobile.dao.loan.LnExceptionDunLogDao#getNextExceptionDunLogId()
     */
    public Integer getNextExceptionDunLogId() {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getNextExceptionDunLogId");
    }

    /**
     * @param paramMap
     * @see com.banger.mobile.dao.loan.LnExceptionDunLogDao#updateExceptionDunLogById(java.util.Map)
     */
    public void updateExceptionDunLogById(Map<String, Object> paramMap) {
        this.getSqlMapClientTemplate().update("updateExceptionDunLogById", paramMap);
    }

    /**
     * @param newLog
     * @see com.banger.mobile.dao.loan.LnExceptionDunLogDao#addLnExceptionDunLog(com.banger.mobile.domain.model.loan.LnExceptionDunLog)
     */
    public void addLnExceptionDunLog(LnExceptionDunLog newLog) {
        this.getSqlMapClientTemplate().insert("addLnExceptionDunLog", newLog);
    }

    /**
     * @param exceptionDunLogId
     * @return
     * @see com.banger.mobile.dao.loan.LnExceptionDunLogDao#getExceptionDunLogById(java.lang.Integer)
     */
    public LnExceptionDunLog getExceptionDunLogById(Integer exceptionDunLogId) {
        return (LnExceptionDunLog) this.getSqlMapClientTemplate().queryForObject("getExceptionDunLogById", exceptionDunLogId);
    }
}
