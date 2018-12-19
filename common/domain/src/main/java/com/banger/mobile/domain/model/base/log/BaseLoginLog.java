/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :安全日志基类
 * Author     :yujh
 * Create Date:May 21, 2012
 */
package com.banger.mobile.domain.model.base.log;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: BaseLoginLog.java,v 0.1 May 21, 2012 2:32:57 PM Administrator Exp $
 */
public class BaseLoginLog extends BaseObject implements Serializable{

    private static final long serialVersionUID = 57848724569736201L;
    
    private     Integer           loginLogID;                              //编号
    private     Integer           userId;                                  //用户id
    private     Integer           logType;                                 //登陆类型
    private     Date              logDate;                                 //登陆时间
    private     String            logIp;                                   //登陆ip
    private     Integer           loginDevice;                             //登陆设备
    public  Integer getLoginLogID() {
        return loginLogID;
    }
    public void setLoginLogID(Integer loginLogID) {
        this.loginLogID = loginLogID;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getLogType() {
        return logType;
    }
    public void setLogType(Integer logType) {
        this.logType = logType;
    }
    public Date getLogDate() {
        return logDate;
    }
    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
    public String getLogIp() {
        return logIp;
    }
    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }
    public Integer getLoginDevice() {
        return loginDevice;
    }
    public void setLoginDevice(Integer loginDevice) {
        this.loginDevice = loginDevice;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((logDate == null) ? 0 : logDate.hashCode());
        result = prime * result + ((logIp == null) ? 0 : logIp.hashCode());
        result = prime * result + ((logType == null) ? 0 : logType.hashCode());
        result = prime * result + ((loginDevice == null) ? 0 : loginDevice.hashCode());
        result = prime * result + ((loginLogID == null) ? 0 : loginLogID.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
        final BaseLoginLog other = (BaseLoginLog) obj;
        if (logDate == null) {
            if (other.logDate != null)
                return false;
        } else if (!logDate.equals(other.logDate))
            return false;
        if (logIp == null) {
            if (other.logIp != null)
                return false;
        } else if (!logIp.equals(other.logIp))
            return false;
        if (logType == null) {
            if (other.logType != null)
                return false;
        } else if (!logType.equals(other.logType))
            return false;
        if (loginDevice == null) {
            if (other.loginDevice != null)
                return false;
        } else if (!loginDevice.equals(other.loginDevice))
            return false;
        if (loginLogID == null) {
            if (other.loginLogID != null)
                return false;
        } else if (!loginLogID.equals(other.loginLogID))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        return true;
    }
   
    public BaseLoginLog(Integer loginLogID, Integer userId, Integer logType, Date logDate,
                        String logIp, Integer loginDevice) {
        super();
        this.loginLogID = loginLogID;
        this.userId = userId;
        this.logType = logType;
        this.logDate = logDate;
        this.logIp = logIp;
        this.loginDevice = loginDevice;
    }
    public BaseLoginLog() {
        super();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("loginLogID", this.loginLogID).append("startRow",
            this.getStartRow()).append("logDate", this.logDate).append("endRow", this.getEndRow())
            .append("loginDevice", this.loginDevice).append("logType", this.logType).append(
                "userId", this.userId).append("logIp", this.logIp).toString();
    }

}
