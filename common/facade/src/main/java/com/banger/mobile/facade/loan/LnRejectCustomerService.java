package com.banger.mobile.facade.loan;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.loan.LnRejectCustomer;

import java.util.List;
import java.util.Map;

/**
 * Created by BH-TCL on 14-12-3.
 */
public interface LnRejectCustomerService {
    void addLnRejectCustomer(LnRejectCustomer lnRejectCustomer);
    void updateLnRejectCustomer(LnRejectCustomer lnRejectCustomer);
    PageUtil<LnRejectCustomer> queryLnRejectCustomerPage(Map<String, Object> conds, Page curPage);
    List<LnRejectCustomer> queryRejectCustomerByIds(Map<String,Object> map);
    void getlnRejectCustomerExportListFile(List<LnRejectCustomer> dataList, String path);
}
