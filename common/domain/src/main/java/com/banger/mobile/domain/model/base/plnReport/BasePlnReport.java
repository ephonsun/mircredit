/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       : 理财规划报告
 * Author     :yujh
 * Create Date:Jul 25, 2012
 */
package com.banger.mobile.domain.model.base.plnReport;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: BasePlnReport.java,v 0.1 Jul 25, 2012 10:56:27 AM Administrator Exp $
 */
public class BasePlnReport extends BaseObject {

    private static final long serialVersionUID = -4027299550758723482L;
    
    private Integer     reportId;
    private Integer     planId;
    private Date        targetDate;
    private Integer     planTypeId;
    private String      content;
    private Integer     isDel;
    private Date        createDate;
    public Integer getReportId() {
        return reportId;
    }
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }
    public Integer getPlanId() {
        return planId;
    }
    public void setPlanId(Integer planId) {
        this.planId = planId;
    }
    public Date getTargetDate() {
        return targetDate;
    }
    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }
    public Integer getPlanTypeId() {
        return planTypeId;
    }
    public void setPlanTypeId(Integer planTypeId) {
        this.planTypeId = planTypeId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getIsDel() {
        return isDel;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BasePlnReport)) {
            return false;
        }
        BasePlnReport rhs = (BasePlnReport) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.planTypeId,
            rhs.planTypeId).append(this.isDel, rhs.isDel).append(this.planId, rhs.planId).append(
            this.content, rhs.content).append(this.reportId, rhs.reportId).append(this.createDate,
            rhs.createDate).append(this.targetDate, rhs.targetDate).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1368213951, -70200767).appendSuper(super.hashCode()).append(
            this.planTypeId).append(this.isDel).append(this.planId).append(this.content).append(
            this.reportId).append(this.createDate).append(this.targetDate).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("startRow", this.getStartRow()).append(
            "targetDate", this.targetDate).append("planId", this.planId).append("content",
            this.content).append("endRow", this.getEndRow()).append("isDel", this.isDel).append(
            "createDate", this.createDate).append("planTypeId", this.planTypeId).append("reportId",
            this.reportId).toString();
    }
    
    
}
