/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划
 * Author     :yujh
 * Create Date:Jul 25, 2012
 */
package com.banger.mobile.domain.model.plnReport;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: PlnReportInfo.java,v 0.1 Jul 25, 2012 3:05:52 PM Administrator Exp $
 */
public class PlnReportInfo extends BaseObject{

    private static final long serialVersionUID = 5279704641237471112L;
    private Integer     reportId;
    private String      customerNo;
    private Integer     customerId;
    private String      customerName;
    private String      phone;
    private String      planName;
    private String      userName;
    private Date        planDate;
    private String      planTypeName;
    private Date        createDate;
    private String      content;
    public Integer getReportId() {
        return reportId;
    }
    public void setReportId(Integer reportId) {
        this.reportId = reportId;
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
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Date getPlanDate() {
        return planDate;
    }
    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }
    public String getPlanTypeName() {
        return planTypeName;
    }
    public void setPlanTypeName(String planTypeName) {
        this.planTypeName = planTypeName;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof PlnReportInfo)) {
            return false;
        }
        PlnReportInfo rhs = (PlnReportInfo) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.phone, rhs.phone)
            .append(this.userName, rhs.userName).append(this.planName, rhs.planName).append(
                this.planDate, rhs.planDate).append(this.customerNo, rhs.customerNo).append(
                this.customerName, rhs.customerName).append(this.content, rhs.content).append(
                this.reportId, rhs.reportId).append(this.createDate, rhs.createDate).append(
                this.planTypeName, rhs.planTypeName).append(this.customerId, rhs.customerId)
            .isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-757363215, -1476322003).appendSuper(super.hashCode()).append(
            this.phone).append(this.userName).append(this.planName).append(this.planDate).append(
            this.customerNo).append(this.customerName).append(this.content).append(this.reportId)
            .append(this.createDate).append(this.planTypeName).append(this.customerId).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("startRow", this.getStartRow()).append(
            "customerId", this.customerId).append("content", this.content).append("endRow",
            this.getEndRow()).append("planName", this.planName).append("planDate", this.planDate)
            .append("customerNo", this.customerNo).append("phone", this.phone).append(
                "planTypeName", this.planTypeName).append("customerName", this.customerName)
            .append("reportId", this.reportId).append("createDate", this.createDate).append(
                "userName", this.userName).toString();
    }
    
}
