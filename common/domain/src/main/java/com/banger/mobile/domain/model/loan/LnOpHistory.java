package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnOpHistory;

/**
 * Created with IntelliJ IDEA.
 * User: Zhangfp
 * Date: 13-2-5
 * Time: 下午3:48
 * To change this template use File | Settings | File Templates.
 * <p/>
 * 贷款操作历史记录
 */
public class LnOpHistory extends BaseLnOpHistory {
    private String userName;
    private String account;
    private String beforeStatusName;
    private String afterStatusName;

    private String preRemark;
    private String sufRemark;

    public String getPreRemark() {
        return preRemark;
    }

    public void setPreRemark(String preRemark) {
        this.preRemark = preRemark;
    }

    public String getSufRemark() {
        return sufRemark;
    }

    public void setSufRemark(String sufRemark) {
        this.sufRemark = sufRemark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBeforeStatusName() {
        return beforeStatusName;
    }

    public void setBeforeStatusName(String beforeStatusName) {
        this.beforeStatusName = beforeStatusName;
    }

    public String getAfterStatusName() {
        return afterStatusName;
    }

    public void setAfterStatusName(String afterStatusName) {
        this.afterStatusName = afterStatusName;
    }
}
