/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-11-24
 */
package com.banger.mobile.domain.model.tskContact;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author cheny
 * @version $Id: TalkTask.java,v 0.1 2012-11-24 上午9:43:15 cheny Exp $
 */
public class TalkTask extends BaseObject implements java.io.Serializable{

    private static final long serialVersionUID = -3040724660748456954L;

    private String taskTitle;
    private String startDate;
    private String endDate;
    private Integer contactPurpose;
    private String remark;
    private Integer isEmergency;
    /**
     * 
     */
    public TalkTask() {
        super();
    }
    public String getTaskTitle() {
        return taskTitle;
    }
    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public Integer getContactPurpose() {
        return contactPurpose;
    }
    public void setContactPurpose(Integer contactPurpose) {
        this.contactPurpose = contactPurpose;
    }
    public String getRemark() {
        return remark;
    }
    
    public Integer getIsEmergency() {
        return isEmergency;
    }
    public void setIsEmergency(Integer isEmergency) {
        this.isEmergency = isEmergency;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof TalkTask)) {
            return false;
        }
        TalkTask rhs = (TalkTask) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.startDate, rhs.startDate).append(this.remark, rhs.remark)
            .append(this.endDate, rhs.endDate).append(this.contactPurpose, rhs.contactPurpose)
            .append(this.taskTitle, rhs.taskTitle).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1538438955, 1973260891).appendSuper(super.hashCode())
            .append(this.startDate).append(this.remark).append(this.endDate)
            .append(this.contactPurpose).append(this.taskTitle).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("remark", this.remark)
            .append("contactPurpose", this.contactPurpose).append("endDate", this.endDate)
            .append("startDate", this.startDate).append("endRow", this.getEndRow())
            .append("taskTitle", this.taskTitle).append("startRow", this.getStartRow()).toString();
    }
    
    
}
