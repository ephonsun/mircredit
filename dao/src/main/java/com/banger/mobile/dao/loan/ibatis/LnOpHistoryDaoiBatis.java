/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款操作历史记录-Dao-接口实现
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.dao.loan.ibatis;

import com.banger.mobile.dao.loan.LnOpHistoryDao;
import com.banger.mobile.domain.model.loan.LnOpHistory;
import com.banger.mobile.ibatis.GenericDaoiBatis;

import java.util.List;
import java.util.Map;

/**
 * @author QianJie
 * @version $Id: LnOpHistoryDaoiBatis.java,v 0.1 Feb 17, 2013 2:54:46 PM QianJie Exp $
 */
public class LnOpHistoryDaoiBatis extends GenericDaoiBatis implements LnOpHistoryDao {
    
    public LnOpHistoryDaoiBatis() {
        super(LnOpHistory.class);
    }
    /**
     * @param persistentClass
     */
    public LnOpHistoryDaoiBatis(Class persistentClass) {
        super(LnOpHistory.class);
    }

    /**
     *添加贷款操作历史记录
     * @param lnOpHistory
     */
    public void insertLnOpHistory(LnOpHistory lnOpHistory){
        this.getSqlMapClientTemplate().insert("insertLnOpHistory",lnOpHistory);
    }

    /**
     * 批量插入贷款操作历史记录
     * @param lnOpHistoryList
     */
    public void insertLnOpHistoryBatch(List<LnOpHistory> lnOpHistoryList){
        this.exectuteBatchInsert("insertLnOpHistory",lnOpHistoryList);
    }

    public List<LnOpHistory> getAllOpHistoryListByLoanId(Integer loanId) {
        return this.getSqlMapClientTemplate().queryForList("getAllOpHistoryListByLoanId", loanId);
    }

    /**
     * 查找贷款的最近一笔历史记录
     * 
     * @param loanId
     * @return
     */
    public LnOpHistory selectHistoryByLoanId(Integer loanId){
        return (LnOpHistory)this.getSqlMapClientTemplate().queryForObject("selectHistoryByLoanId",loanId);
    }

    /**
     * 查看贷款某状态最后一次操作的历史记录
     * 
     * @param paramMap
     * @return
     */
    public LnOpHistory selectHistoryByLoanStatus(Map<String,Object> paramMap){
        return (LnOpHistory)this.getSqlMapClientTemplate().queryForObject("selectHistoryByLoanStatus",paramMap);
    }
	@Override
	public void deletelnOpHistoryByLoanId(Integer loanId) {
		this.getSqlMapClientTemplate().delete("deletelnOpHistoryByLoanId",loanId);
	}
    
    
    
}
