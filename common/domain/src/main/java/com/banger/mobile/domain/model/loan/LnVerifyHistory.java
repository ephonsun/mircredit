package com.banger.mobile.domain.model.loan;

import com.banger.mobile.domain.model.base.loan.BaseLnVerifyHistory;

/**
 * Created with IntelliJ IDEA.
 * User: ouyl
 * Date: 13-3-14
 * Time: 上午10:49
 * To change this template use File | Settings | File Templates.
 * <p/>
 * 审计操作历史记录
 */
public class LnVerifyHistory extends BaseLnVerifyHistory{


    private String userName;       //操作人员姓名
    private String account;        //操作人员昵称

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
}
