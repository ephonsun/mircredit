/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-11-23
 */
package com.banger.mobile.domain.model.tskTaskPlan;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author cheny
 * @version $Id: TalkTaskPlan.java,v 0.1 2012-11-23 下午3:51:39 cheny Exp $
 */
public class TalkTaskPlan extends BaseObject implements java.io.Serializable{

    private static final long serialVersionUID = -5438583031944142881L;
    
    private Integer customerId;
    private String planDate; 
    private String remark;
    private Integer finishLevel;
    private String customerName;
    /**
     * 
     */
    public TalkTaskPlan() {
        super();
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public String getPlanDate() {
        return planDate;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getFinishLevel() {
        return finishLevel;
    }
    public void setFinishLevel(Integer finishLevel) {
        this.finishLevel = finishLevel;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof TalkTaskPlan)) {
            return false;
        }
        TalkTaskPlan rhs = (TalkTaskPlan) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.finishLevel, rhs.finishLevel).append(this.planDate, rhs.planDate)
            .append(this.customerId, rhs.customerId).append(this.remark, rhs.remark).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1741499681, 442880873).appendSuper(super.hashCode())
            .append(this.finishLevel).append(this.planDate).append(this.customerId)
            .append(this.remark).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("remark", this.remark)
            .append("endRow", this.getEndRow()).append("planDate", this.planDate)
            .append("customerId", this.customerId).append("finishLevel", this.finishLevel)
            .append("startRow", this.getStartRow()).toString();
    }
    
    
}
