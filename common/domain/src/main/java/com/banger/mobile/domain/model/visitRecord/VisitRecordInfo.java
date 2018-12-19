/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :拜访记录实体类，多表查询
 * Author     :yujh
 * Create Date:May 31, 2012
 */
package com.banger.mobile.domain.model.visitRecord;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: VisitRecordInfo.java,v 0.1 May 31, 2012 9:04:49 AM Administrator Exp $
 */
public class VisitRecordInfo implements Serializable {

    private static final long serialVersionUID = 2112661041863885865L;
    
    private Integer           recordInfoId;                           //编号
    private Integer           userId;                                 //用户id
    private Integer           customerId;                             //客户id
    private Date              startDate;                              //拜访时间
    private String            bizTypeName;                           //业务类型名称
    private String            commProgressName;                      //沟通进度名称
    private String            remark;                                 //备注
    private String            customerName;                           //客户姓名
    public Integer getRecordInfoId() {
        return recordInfoId;
    }
    public void setRecordInfoId(Integer recordInfoId) {
        this.recordInfoId = recordInfoId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public String getBizTypeName() {
        return bizTypeName;
    }
    public void setBizTypeName(String bizTypeName) {
        this.bizTypeName = bizTypeName;
    }
    public String getCommProgressName() {
        return commProgressName;
    }
    public void setCommProgressName(String commProgressName) {
        this.commProgressName = commProgressName;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof VisitRecordInfo)) {
            return false;
        }
        VisitRecordInfo rhs = (VisitRecordInfo) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.startDate,
            rhs.startDate).append(this.userId, rhs.userId).append(this.recordInfoId,
            rhs.recordInfoId).append(this.customerName, rhs.customerName).append(
            this.commProgressName, rhs.commProgressName).append(this.remark, rhs.remark).append(
            this.bizTypeName, rhs.bizTypeName).append(this.customerId, rhs.customerId).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(627560769, -146290879).appendSuper(super.hashCode()).append(
            this.startDate).append(this.userId).append(this.recordInfoId).append(this.customerName)
            .append(this.commProgressName).append(this.remark).append(this.bizTypeName).append(
                this.customerId).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("customerId", this.customerId).append(
            "bizTypeName", this.bizTypeName).append("startDate", this.startDate).append(
            "customerName", this.customerName).append("commProgressName", this.commProgressName)
            .append("remark", this.remark).append("userId", this.userId).append("recordInfoId",
                this.recordInfoId).toString();
    }
    
}
