package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnVerifyHistoryDao;
import com.banger.mobile.domain.model.loan.LnVerifyHistory;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-14
 * Time: 下午1:48
 * To change this template use File | Settings | File Templates.
 */
public class LnVerifyHistoryDaoiBatis extends GenericDaoiBatis implements LnVerifyHistoryDao{

    public LnVerifyHistoryDaoiBatis() {
        super(LnVerifyHistory.class);
    }
    /**
     * @param persistentClass
     */
    public LnVerifyHistoryDaoiBatis(Class persistentClass) {
        super(LnVerifyHistory.class);
    }

    public void insertLnVerifyHistory(LnVerifyHistory lnVerifyHistory){
        this.getSqlMapClientTemplate().insert("insertLnVerifyHistory",lnVerifyHistory);
    }

    //批量添加审计历史操作
    public void insertLnVerifyHistoryBatch(List<LnVerifyHistory> lnVerifyHistoryList){
        this.exectuteBatchInsert("insertLnVerifyHistory",lnVerifyHistoryList);
    }

    public List<LnVerifyHistory> getLnVerifyHistoryByLoanId(Integer loanId){

        return this.getSqlMapClientTemplate().queryForList("getLnVerifyHistoryByLoanId",loanId);
    }

    /**
     * 取出最近的一笔审计不通过备注
     * @param paramMap
     * @return
     */
    public LnVerifyHistory getFirstVerifyHistoryByLoanId(Map<String,Object> paramMap){
        return (LnVerifyHistory)this.getSqlMapClientTemplate().queryForObject("getFirstVerifyHistoryByLoanId",paramMap);
    }
}
