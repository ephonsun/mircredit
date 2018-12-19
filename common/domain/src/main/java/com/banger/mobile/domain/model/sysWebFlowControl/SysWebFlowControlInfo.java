/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :web端限流页面信息
 * Author     :yujh
 * Create Date:Sep 10, 2012
 */
package com.banger.mobile.domain.model.sysWebFlowControl;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: SysWebFlowControlInfo.java,v 0.1 Sep 10, 2012 11:34:08 AM Administrator Exp $
 */
public class SysWebFlowControlInfo extends BaseObject{

    private static final long serialVersionUID = 106341475000496463L;
    private Integer     deptId;
    private Integer     deptParentId;
    private String      deptName;
    private Integer     isActive;
    private Integer     maxBPS;
    public Integer getDeptId() {
        return deptId;
    }
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    public Integer getDeptParentId() {
        return deptParentId;
    }
    public void setDeptParentId(Integer deptParentId) {
        this.deptParentId = deptParentId;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public Integer getIsActive() {
        return isActive;
    }
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
    public Integer getMaxBPS() {
        return maxBPS;
    }
    public void setMaxBPS(Integer maxBPS) {
        this.maxBPS = maxBPS;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof SysWebFlowControlInfo)) {
            return false;
        }
        SysWebFlowControlInfo rhs = (SysWebFlowControlInfo) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.maxBPS, rhs.maxBPS).append(this.deptName, rhs.deptName).append(
                this.isActive, rhs.isActive).append(this.deptParentId, rhs.deptParentId).append(
                this.deptId, rhs.deptId).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-385629491, -1453760403).appendSuper(super.hashCode()).append(
            this.maxBPS).append(this.deptName).append(this.isActive).append(this.deptParentId)
            .append(this.deptId).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("deptParentId", this.deptParentId).append("deptId",
            this.deptId).append("startRow", this.getStartRow()).append("deptName", this.deptName)
            .append("endRow", this.getEndRow()).append("maxBPS", this.maxBPS).append("isActive",
                this.isActive).toString();
    }
    

}
