/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :hk
 * Create Date:Sep 5, 2012
 */
package com.banger.mobile.domain.model.pdtProduct;

import java.io.Serializable;

/**
 * 营销人员bean
 * @author hk
 * @version $Id: UserAndCountersBean.java,v 0.1 Sep 5, 2012 5:18:08 PM hk Exp $
 */
public class UserAndCountersBean implements Serializable{
    private static final long serialVersionUID = -2464702078787363666L;
    private String userId;  //用户id
    private String account; //账号
    private String userName;//用户名
    private int type;       //区分类型（1.用户 2.柜台人员）
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        result = prime * result + type;
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final UserAndCountersBean other = (UserAndCountersBean) obj;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (type != other.type)
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }
    public UserAndCountersBean(String userId, String account, String userName, int type) {
        super();
        this.userId = userId;
        this.account = account;
        this.userName = userName;
        this.type = type;
    }
    public UserAndCountersBean() {
        super();
    }
    
}