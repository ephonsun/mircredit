/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :安全日志实体类
 * Author     :yujh
 * Create Date:May 21, 2012
 */
package com.banger.mobile.domain.model.log;


import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.log.BaseLoginLog;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @author yujh
 * @version $Id: LoginLog.java,v 0.1 May 21, 2012 10:01:21 AM Administrator Exp $
 */
public class LoginLogInfo implements Serializable{

    private static final long serialVersionUID = -6658533213789601107L;
    
    private  String             account;                     //登陆用户
    private  String             logTypeName;                //登陆类型
    private  Date               logDate;                    //登陆时间
    private  String             logDevice;                  //登陆设备
    private  String             logIp;                      //登陆ip
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getLogTypeName() {
        return logTypeName;
    }
    public void setLogTypeName(String logTypeName) {
        this.logTypeName = logTypeName;
    }
    public Date getLogDate() {
        return logDate;
    }
    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
    public String getLogDevice() {
        return logDevice;
    }
    public void setLogDevice(String logDevice) {
        this.logDevice = logDevice;
    }
    public String getLogIp() {
        return logIp;
    }
    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof LoginLogInfo)) {
            return false;
        }
        LoginLogInfo rhs = (LoginLogInfo) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.logDevice,
            rhs.logDevice).append(this.logTypeName, rhs.logTypeName).append(this.account,
            rhs.account).append(this.logDate, rhs.logDate).append(this.logIp, rhs.logIp).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(75147597, -1492386927).appendSuper(super.hashCode()).append(
            this.logDevice).append(this.logTypeName).append(this.account).append(this.logDate)
            .append(this.logIp).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("logDevice", this.logDevice).append("logDate",
            this.logDate).append("account", this.account).append("logTypeName", this.logTypeName)
            .append("logIp", this.logIp).toString();
    }
    
    
}
