/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :增值业务配置
 * Author     :yujh
 * Create Date:Aug 25, 2012
 */
package com.banger.mobile.domain.model.base.vasSetting;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: BaseCrmVasSetting.java,v 0.1 Aug 25, 2012 1:32:45 PM Administrator Exp $
 */
public class BaseCrmVasSetting extends BaseObject{

    private static final long serialVersionUID = -5703934223071137895L;
    
    private Integer       vasSettingId;
    private Integer       userId;
    private String        account;
    private String        password;
    private Date          createDate;
    private Date          updateDate;
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
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseCrmVasSetting)) {
            return false;
        }
        BaseCrmVasSetting rhs = (BaseCrmVasSetting) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.password,
            rhs.password).append(this.userId, rhs.userId).append(this.account, rhs.account).append(
            this.vasSettingId, rhs.vasSettingId).append(this.createDate, rhs.createDate).append(
            this.updateDate, rhs.updateDate).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(109083861, 947706069).appendSuper(super.hashCode()).append(
            this.password).append(this.userId).append(this.account).append(this.vasSettingId)
            .append(this.createDate).append(this.updateDate).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("startRow", this.getStartRow()).append(
            "updateDate", this.updateDate).append("account", this.account).append("vasSettingId",
            this.vasSettingId).append("password", this.password).append("endRow", this.getEndRow())
            .append("createDate", this.createDate).append("userId", this.userId).toString();
    }
    
    

}
