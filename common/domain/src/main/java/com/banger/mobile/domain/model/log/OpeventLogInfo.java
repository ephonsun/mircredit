/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :操作日志实体类
 * Author     :yujh
 * Create Date:May 23, 2012
 */
package com.banger.mobile.domain.model.log;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: OpeventLogInfo.java,v 0.1 May 23, 2012 11:13:13 AM Administrator Exp $
 */
public class OpeventLogInfo implements Serializable {

    private static final long serialVersionUID = -4894893564669486939L;

    private String            account;                                 //操作用户名
    private String            opeventObject;                           //操作对象
    private String            opeventAction;                           //操作动作
    private Date              opeventTime;                             //操作日期
    private String            opeventIp;                               //操作ip

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOpeventObject() {
        return opeventObject;
    }

    public void setOpeventObject(String opeventObject) {
        this.opeventObject = opeventObject;
    }

    public String getOpeventAction() {
        return opeventAction;
    }

    public void setOpeventAction(String opeventAction) {
        this.opeventAction = opeventAction;
    }

    public Date getOpeventTime() {
        return opeventTime;
    }

    public void setOpeventTime(Date opeventTime) {
        this.opeventTime = opeventTime;
    }

    public String getOpeventIp() {
        return opeventIp;
    }

    public void setOpeventIp(String opeventIp) {
        this.opeventIp = opeventIp;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof OpeventLogInfo)) {
            return false;
        }
        OpeventLogInfo rhs = (OpeventLogInfo) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.opeventAction,
            rhs.opeventAction).append(this.opeventObject, rhs.opeventObject).append(
            this.opeventTime, rhs.opeventTime).append(this.account, rhs.account).append(
            this.opeventIp, rhs.opeventIp).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1648004975, -1022434185).appendSuper(super.hashCode()).append(
            this.opeventAction).append(this.opeventObject).append(this.opeventTime).append(
            this.account).append(this.opeventIp).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("opeventIp", this.opeventIp).append("opeventTime",
            this.opeventTime).append("account", this.account).append("opeventAction",
            this.opeventAction).append("opeventObject", this.opeventObject).toString();
    }

}
