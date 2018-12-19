/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :web端限流设置
 * Author     :yujh
 * Create Date:Aug 24, 2012
 */
package com.banger.mobile.domain.model.base.sysWebFlowControl;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: BaseSysWebFlowControl.java,v 0.1 Aug 24, 2012 5:23:25 PM Administrator Exp $
 */
public class BaseSysWebFlowControl extends BaseObject{

    private static final long serialVersionUID = -4888265332137137897L;
    
    private     Integer     flowControlId;
    private     Integer     deptId;
    private     Integer     isActive;
    private     Integer     maxBps;
    private     Date        createDate;
    private     Date        updateDate;
    public Integer getFlowControlId() {
        return flowControlId;
    }
    public void setFlowControlId(Integer flowControlId) {
        this.flowControlId = flowControlId;
    }
    public Integer getDeptId() {
        return deptId;
    }
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    public Integer getIsActive() {
        return isActive;
    }
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
    public Integer getMaxBps() {
        return maxBps;
    }
    public void setMaxBps(Integer maxBps) {
        this.maxBps = maxBps;
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
        if (!(object instanceof BaseSysWebFlowControl)) {
            return false;
        }
        BaseSysWebFlowControl rhs = (BaseSysWebFlowControl) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.flowControlId,
            rhs.flowControlId).append(this.maxBps, rhs.maxBps).append(this.isActive, rhs.isActive)
            .append(this.createDate, rhs.createDate).append(this.deptId, rhs.deptId).append(
                this.updateDate, rhs.updateDate).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(312669101, 1859046665).appendSuper(super.hashCode()).append(
            this.flowControlId).append(this.maxBps).append(this.isActive).append(this.createDate)
            .append(this.deptId).append(this.updateDate).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("deptId", this.deptId).append("startRow",
            this.getStartRow()).append("updateDate", this.updateDate).append("flowControlId",
            this.flowControlId).append("endRow", this.getEndRow()).append("createDate",
            this.createDate).append("maxBps", this.maxBps).append("isActive", this.isActive)
            .toString();
    }
    
}
