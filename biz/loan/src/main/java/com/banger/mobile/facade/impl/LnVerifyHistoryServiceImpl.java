package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnVerifyHistoryDao;
import com.banger.mobile.domain.model.loan.LnVerifyHistory;
import com.banger.mobile.facade.loan.LnVerifyHistoryService;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ouyl
 * Date: 13-3-14
 * Time: 下午3:41
 * To change this template use File | Settings | File Templates.
 */
public class LnVerifyHistoryServiceImpl implements LnVerifyHistoryService{
    private LnVerifyHistoryDao lnVerifyHistoryDao;

    public LnVerifyHistoryDao getLnVerifyHistoryDao() {
        return lnVerifyHistoryDao;
    }

    public void setLnVerifyHistoryDao(LnVerifyHistoryDao lnVerifyHistoryDao) {
        this.lnVerifyHistoryDao = lnVerifyHistoryDao;
    }

    public void insertLnVerifyHistory(LnVerifyHistory lnVerifyHistory){
         lnVerifyHistoryDao.insertLnVerifyHistory(lnVerifyHistory);
    }

    //批量添加审计历史操作
    public void insertLnVerifyHistoryBatch(List<LnVerifyHistory> lnVerifyHistoryList){
         lnVerifyHistoryDao.insertLnVerifyHistoryBatch(lnVerifyHistoryList);
    }

    public List<LnVerifyHistory> getLnVerifyHistoryByLoanId(Integer loanId) {
        return lnVerifyHistoryDao.getLnVerifyHistoryByLoanId(loanId);
    }
    /**
     * 取出最近的一笔审计不通过备注
     * @param paramMap
     * @return
     */
    public LnVerifyHistory getFirstVerifyHistoryByLoanId(Map<String, Object> paramMap){
        return lnVerifyHistoryDao.getFirstVerifyHistoryByLoanId(paramMap);
    }
}
