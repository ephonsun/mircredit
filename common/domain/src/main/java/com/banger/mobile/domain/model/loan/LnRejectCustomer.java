package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnRejectCustomer;

/**
 * 二期新增拒绝客户登记
 * Created by BH-TCL on 14-12-3.
 */
public class LnRejectCustomer extends BaseLnRejectCustomer {
    private String userName;
    private String deptName;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }
}
