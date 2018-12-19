package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnDunLogDao;
import com.banger.mobile.domain.model.loan.LnDunLog;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-6
 * Time: 上午9:16
 * To change this template use File | Settings | File Templates.
 * <p/>
 * 贷款催收记录表
 */
public class LnDunLogDaoiBatis extends GenericDaoiBatis implements LnDunLogDao {

    public LnDunLogDaoiBatis() {
        super(LnDunLog.class);
    }

    public LnDunLogDaoiBatis(Class persistentClass) {
        super(LnDunLog.class);
    }

    //得到贷款所有催收记录
    public List<LnDunLog> getDunLogByLoanId(Integer loanId) {
        return this.getSqlMapClientTemplate().queryForList("getDunLogByLoanId", loanId);
    }

    //保存
    public void addLnDunLog(LnDunLog newLog) {
        this.getSqlMapClientTemplate().insert("addLnDunLog", newLog);
    }

    /**
     * 更新催收记录表
     * 
     * @param paramMap
     */
    @Override
    public void updateDunLogById(Map<String,Object> paramMap){
        this.getSqlMapClientTemplate().update("updateDunLogById",paramMap);
    }

    public Integer getNextDunLogId() {
        return (Integer) this.getSqlMapClientTemplate().queryForObject("getNextDunLogId");
    }

    public LnDunLog getDunLogById(Integer dunLogId) {
        return (LnDunLog) this.getSqlMapClientTemplate().queryForObject("getDunLogById", dunLogId);
    }
}
