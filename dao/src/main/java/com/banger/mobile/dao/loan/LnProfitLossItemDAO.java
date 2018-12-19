package com.banger.mobile.dao.loan;

import com.banger.mobile.domain.model.base.inform.BaseInform;
import com.banger.mobile.domain.model.loan.LnProfitLossItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 */
public interface LnProfitLossItemDAO {
    List<LnProfitLossItem> selectItemListByType(Integer loanId,String type);

    LnProfitLossItem selectOneItemById(Integer itemId);

    void deleteLnProfitLossItem(Integer itemId);

    void  deleteLnProfitLossItems(Integer loanId);

    void insertLnProfitLossItem(LnProfitLossItem lnProfitLossItem);

    void updateLnProfitLossItem(LnProfitLossItem lnProfitLossItem);

    BigDecimal sumByType(Integer loanId, String type);
}
