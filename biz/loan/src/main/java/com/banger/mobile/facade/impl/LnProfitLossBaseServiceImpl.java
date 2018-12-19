package com.banger.mobile.facade.impl;

import com.banger.mobile.dao.loan.LnProfitLossBaseDAO;
import com.banger.mobile.dao.loan.LnProfitLossItemDAO;
import com.banger.mobile.domain.model.loan.LnProfitLossBase;
import com.banger.mobile.facade.loan.LnProfitLossBaseService;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
public class LnProfitLossBaseServiceImpl implements LnProfitLossBaseService {

    private LnProfitLossBaseDAO lnProfitLossBaseDAO;

    private LnProfitLossItemDAO lnProfitLossItemDAO;

    public LnProfitLossItemDAO getLnProfitLossItemDAO() {
        return lnProfitLossItemDAO;
    }

    public void setLnProfitLossItemDAO(LnProfitLossItemDAO lnProfitLossItemDAO) {
        this.lnProfitLossItemDAO = lnProfitLossItemDAO;
    }

    public LnProfitLossBaseDAO getLnProfitLossBaseDAO() {
        return lnProfitLossBaseDAO;
    }

    public void setLnProfitLossBaseDAO(LnProfitLossBaseDAO lnProfitLossBaseDAO) {
        this.lnProfitLossBaseDAO = lnProfitLossBaseDAO;
    }

    @Override
    public LnProfitLossBase selectProfitLossBaseByPrimary(Integer loanId) {
        LnProfitLossBase lnProfitLossBase = new LnProfitLossBase();
        lnProfitLossBase.setLoanId(loanId);
        LnProfitLossBase ln = lnProfitLossBaseDAO.selectProfitLossBaseByPrimary(lnProfitLossBase);
        return ln;
    }

    @Override
    public void insertLnProfitLossBase(LnProfitLossBase LnProfitLossBase) {
        lnProfitLossBaseDAO.insertLnProfitLossBase(LnProfitLossBase);
    }

    @Override
    public void updateLnProfitLossBase(LnProfitLossBase LnProfitLossBase) {
        lnProfitLossBaseDAO.updateLnProfitLossBase(LnProfitLossBase);
    }

    @Override
    public void deleteLnProfitLossBase(Integer loanId) {
        LnProfitLossBase lnProfitLossBase = new LnProfitLossBase();
        lnProfitLossBase.setLoanId(loanId);
        lnProfitLossBaseDAO.deleteLnProfitLossBase(lnProfitLossBase);
    }

    @Override
    public BigDecimal sumItemByType(Integer loanId, String type) {
        BigDecimal sum = lnProfitLossItemDAO.sumByType(loanId,type);
        if(null==sum){
            sum=new BigDecimal(0);
        }
        return sum;
    }
}
