/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :快速规划信息
 * Author     :yujh
 * Create Date:Jul 18, 2012
 */
package com.banger.mobile.domain.model.plnFastPlan;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: PlnFastPlanInfo.java,v 0.1 Jul 18, 2012 10:35:26 AM Administrator Exp $
 */
public class PlnFastPlanInfo implements Serializable {

    private static final long serialVersionUID = 2629596255042793237L;
    
    private     Integer     fastPlanId;         //id
    private     String      customerNo;         //客户编号
    private     Integer     customerId;
    private     String      customerName;       //客户姓名
    private     String      phone;              //联系电话
    private     String      planName;           //规划名称
    private     Date        planDate;           //规划日期
    private     String      userName;           //用户姓名
    private     Integer     userId;             //用户id
    public Integer getFastPlanId() {
        return fastPlanId;
    }
    public void setFastPlanId(Integer fastPlanId) {
        this.fastPlanId = fastPlanId;
    }
    public String getCustomerNo() {
        return customerNo;
    }
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPlanName() {
        return planName;
    }
    public void setPlanName(String planName) {
        this.planName = planName;
    }
    public Date getPlanDate() {
        return planDate;
    }
    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof PlnFastPlanInfo)) {
            return false;
        }
        PlnFastPlanInfo rhs = (PlnFastPlanInfo) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.phone, rhs.phone)
            .append(this.userName, rhs.userName).append(this.planName, rhs.planName).append(
                this.planDate, rhs.planDate).append(this.userId, rhs.userId).append(
                this.customerNo, rhs.customerNo).append(this.fastPlanId, rhs.fastPlanId).append(
                this.customerName, rhs.customerName).append(this.customerId, rhs.customerId)
            .isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1658713511, -562146285).appendSuper(super.hashCode()).append(
            this.phone).append(this.userName).append(this.planName).append(this.planDate).append(
            this.userId).append(this.customerNo).append(this.fastPlanId).append(this.customerName)
            .append(this.customerId).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("phone", this.phone).append("fastPlanId",
            this.fastPlanId).append("customerId", this.customerId).append("customerName",
            this.customerName).append("userId", this.userId).append("planDate", this.planDate)
            .append("planName", this.planName).append("userName", this.userName).append(
                "customerNo", this.customerNo).toString();
    }
    
}
