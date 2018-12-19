/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :任务-Domain-扩展1
 * Author     :QianJie
 * Create Date:Mar 2, 2013
 */
package com.banger.mobile.domain.model.microTask;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.HashCodeBuilder;

import com.banger.mobile.domain.model.base.microTask.BaseTskMicroTask;

/**
 * @author QianJie
 * @version $Id: TskMicroTask.java,v 0.1 Mar 2, 2013 10:44:01 AM QianJie Exp $
 */
public class TskMicroTask extends BaseTskMicroTask {

    private static final long serialVersionUID = -6077175157159509616L;

    private BigDecimal        comPercent;
    private String            userName;
    private Double           finishCount;
    private Double           userTaskTarget;
    private Integer           isOutDate;
    private String            roleIds;
    private Integer           assignDeptId;
    private String            assignSubDept;
    private String            loginManageDeptId;

    public TskMicroTask() {
        super();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1616264783, 517576519).appendSuper(super.hashCode())
            .toHashCode();
    }

    public BigDecimal getComPercent() {
        return comPercent;
    }

    public void setComPercent(BigDecimal comPercent) {
        this.comPercent = comPercent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(Double finishCount) {
        this.finishCount = finishCount;
    }

    public Double getUserTaskTarget() {
        return userTaskTarget;
    }

    public void setUserTaskTarget(Double userTaskTarget) {
        this.userTaskTarget = userTaskTarget;
    }

    public Integer getIsOutDate() {
        return isOutDate;
    }

    public void setIsOutDate(Integer isOutDate) {
        this.isOutDate = isOutDate;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public Integer getAssignDeptId() {
        return assignDeptId;
    }

    public void setAssignDeptId(Integer assignDeptId) {
        this.assignDeptId = assignDeptId;
    }

    public String getAssignSubDept() {
        return assignSubDept;
    }

    public void setAssignSubDept(String assignSubDept) {
        this.assignSubDept = assignSubDept;
    }

    public String getLoginManageDeptId() {
        return loginManageDeptId;
    }

    public void setLoginManageDeptId(String loginManageDeptId) {
        this.loginManageDeptId = loginManageDeptId;
    }

}
