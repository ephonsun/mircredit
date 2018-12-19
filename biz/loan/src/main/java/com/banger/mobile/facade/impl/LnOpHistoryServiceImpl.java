/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :贷款操作历史记录-Service-接口
 * Author     :QianJie
 * Create Date:Feb 17, 2013
 */
package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnOpHistoryDao;
import com.banger.mobile.domain.model.loan.LnOpHistory;
import com.banger.mobile.facade.loan.LnOpHistoryService;

import java.util.List;
import java.util.Map;

/**
 * @author QianJie
 * @version $Id: LnOpHistoryServiceImpl.java,v 0.1 Feb 17, 2013 3:51:05 PM QianJie Exp $
 */
public class LnOpHistoryServiceImpl implements LnOpHistoryService{

    private LnOpHistoryDao lnOpHistoryDao;

    /**
     * 添加贷款操作历史记录
     * @param lnOpHistory
     */
    public void insertLnOpHistory(LnOpHistory lnOpHistory){
        lnOpHistoryDao.insertLnOpHistory(lnOpHistory);
    }

    /**
     * 批量插入贷款操作历史记录
     * @param lnOpHistoryList
     */
    public void insertLnOpHistoryBatch(List<LnOpHistory> lnOpHistoryList){
        lnOpHistoryDao.insertLnOpHistoryBatch(lnOpHistoryList);
    }

    public List<LnOpHistory> getAllOpHistoryListByLoanId(Integer loanId) {
        return lnOpHistoryDao.getAllOpHistoryListByLoanId(loanId);
    }

    public void setLnOpHistoryDao(LnOpHistoryDao lnOpHistoryDao) {
        this.lnOpHistoryDao = lnOpHistoryDao;
    }

    public LnOpHistoryDao getLnOpHistoryDao() {
        return lnOpHistoryDao;
    }

    /**
     * 查找贷款的最近一笔历史记录
     *
     * @param loanId
     * @return
     */
    public LnOpHistory selectHistoryByLoanId(Integer loanId){
        return lnOpHistoryDao.selectHistoryByLoanId(loanId);
    }

    /**
     * 查看贷款某状态最后一次操作的历史记录
     *
     * @param paramMap
     * @return
     */
    public LnOpHistory selectHistoryByLoanStatus(Map<String, Object> paramMap){
        return lnOpHistoryDao.selectHistoryByLoanStatus(paramMap);
    }

	@Override
	public void deletelnOpHistoryByLoanId(Integer loanId) {
		lnOpHistoryDao.deletelnOpHistoryByLoanId(loanId);
	}
    
    
}
