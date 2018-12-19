package com.banger.mobile.facade.loan;

import com.banger.mobile.domain.model.loan.LnProfitLossBase;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ygb on 2017/8/1.
 */
public interface LnProfitLossBaseService {

   LnProfitLossBase selectProfitLossBaseByPrimary(Integer loanId);

    void insertLnProfitLossBase(LnProfitLossBase LnProfitLossBase);

    void updateLnProfitLossBase(LnProfitLossBase LnProfitLossBase);

    void deleteLnProfitLossBase(Integer loanId);


    BigDecimal sumItemByType(Integer loanId, String type);
}
