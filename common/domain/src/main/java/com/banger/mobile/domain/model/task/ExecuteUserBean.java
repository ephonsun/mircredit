/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :修改任务执行者实体类
 * Author     :liyb
 * Create Date:2012-8-16
 */
package com.banger.mobile.domain.model.task;

import java.io.Serializable;

/**
 * @author liyb
 * @version $Id: ExecuteUserBean.java,v 0.1 2012-8-16 下午01:27:16 liyb Exp $
 */
public class ExecuteUserBean implements Serializable {

    private static final long serialVersionUID = -1101721821799071257L;
    private Integer           userId;                                   //用户ID
    private String            userName;                                 //客户经理
    private String            account;                                  //用户名
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
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
}
