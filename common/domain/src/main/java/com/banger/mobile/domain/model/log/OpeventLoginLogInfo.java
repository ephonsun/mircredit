/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.domain.model.log;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author Administrator
 * @version $Id: OpeventLoginLogInfo.java,v 0.1 Aug 14, 2012 10:25:06 AM Administrator Exp $
 */
public class OpeventLoginLogInfo implements Serializable{

    private static final long serialVersionUID = -8097849205217168523L;
    
    private     Date       opeventDate;
    private     String     opeventObj;
    private     String     opeventAction;
    private     String     userName;
    private     String     clientTypeName;
    private     String     opeventIp;
    public Date getOpeventDate() {
        return opeventDate;
    }
    public void setOpeventDate(Date opeventDate) {
        this.opeventDate = opeventDate;
    }
    public String getOpeventObj() {
        return opeventObj;
    }
    public void setOpeventObj(String opeventObj) {
        this.opeventObj = opeventObj;
    }
    public String getOpeventAction() {
        return opeventAction;
    }
    public void setOpeventAction(String opeventAction) {
        this.opeventAction = opeventAction;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getClientTypeName() {
        return clientTypeName;
    }
    public void setClientTypeName(String clientTypeName) {
        this.clientTypeName = clientTypeName;
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
        if (!(object instanceof OpeventLoginLogInfo)) {
            return false;
        }
        OpeventLoginLogInfo rhs = (OpeventLoginLogInfo) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.opeventAction,
            rhs.opeventAction).append(this.userName, rhs.userName).append(this.clientTypeName,
            rhs.clientTypeName).append(this.opeventDate, rhs.opeventDate).append(this.opeventObj,
            rhs.opeventObj).append(this.opeventIp, rhs.opeventIp).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(258225117, 1891757987).appendSuper(super.hashCode()).append(
            this.opeventAction).append(this.userName).append(this.clientTypeName).append(
            this.opeventDate).append(this.opeventObj).append(this.opeventIp).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("opeventIp", this.opeventIp).append("opeventObj",
            this.opeventObj).append("opeventDate", this.opeventDate).append("clientTypeName",
            this.clientTypeName).append("opeventAction", this.opeventAction).append("userName",
            this.userName).toString();
    }
    
}
