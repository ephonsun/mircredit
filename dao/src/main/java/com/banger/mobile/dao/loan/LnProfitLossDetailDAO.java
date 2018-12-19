package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.loan.LnProfitLossDetail;

import java.util.List;

/**
 * Created by ygb on 2017/8/3.
 */
public interface LnProfitLossDetailDAO {

    List<LnProfitLossDetail> selectDetailsByItemId(Integer itemId);

    void deleteDetailsByItemId(Integer itemId);

    void deleteDetailsByLoanId(Integer loanId);

    void insertDetails(LnProfitLossDetail lnProfitLossDetail);

    void updateDetails(LnProfitLossDetail lnProfitLossDetail);


}
