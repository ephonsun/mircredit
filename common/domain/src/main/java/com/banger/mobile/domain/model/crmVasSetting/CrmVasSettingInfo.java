/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:Aug 25, 2012
 */
package com.banger.mobile.domain.model.crmVasSetting;

import java.util.Date;

import com.banger.mobile.domain.model.base.vasSetting.BaseCrmVasSetting;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author Administrator
 * @version $Id: CrmVasSetting.java,v 0.1 Aug 25, 2012 2:22:15 PM Administrator Exp $
 */
public class CrmVasSettingInfo {
    private Integer       vasSettingId;
    private Integer       userId;
    private String        account;
    private String        password;
    private Date          createDate;
    private Date          updateDate;
    private String        userName;
    public Integer getVasSettingId() {
        return vasSettingId;
    }
    public void setVasSettingId(Integer vasSettingId) {
        this.vasSettingId = vasSettingId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
