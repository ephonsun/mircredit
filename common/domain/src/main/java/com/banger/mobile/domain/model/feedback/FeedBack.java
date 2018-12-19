package com.banger.mobile.domain.model.feedback;

import com.banger.mobile.domain.model.base.feedback.BaseFeedBack;

/**
 * Created by BH-TCL on 15-3-9.
 */
public class FeedBack extends BaseFeedBack{
    private String account;
    private String userName;


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }
}
