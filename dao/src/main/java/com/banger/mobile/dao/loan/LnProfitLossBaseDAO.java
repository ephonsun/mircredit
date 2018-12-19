package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.LnProfitLossBase;


import java.util.List;

/**
 * Created by ygb on 2017/8/1.
 */
public interface LnProfitLossBaseDAO {

    LnProfitLossBase selectProfitLossBaseByPrimary(
            LnProfitLossBase lnProfitLossBase);

    void insertLnProfitLossBase(LnProfitLossBase lnProfitLossBase);

    void updateLnProfitLossBase(LnProfitLossBase lnProfitLossBase);

    void deleteLnProfitLossBase(LnProfitLossBase lnProfitLossBase);

}
