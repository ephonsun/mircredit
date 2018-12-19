/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-12-15
 */
package com.banger.mobile.domain.model.tskContact;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * @author cheny
 * @version $Id: TskPlanSelectBean.java,v 0.1 2012-12-15 下午2:32:17 cheny Exp $
 */
public class TskPlanSelectBean extends BaseObject{

    private static final long serialVersionUID = 1708522643578088481L;
    
    private String targetIds;
    private String customerName;
    private String belongUserName;
    private Integer relativeTsks;          //关联任务数
    private String remark;                 //联系事项
    private Integer defaultPhoneType;
    private String mobilePhone1;
    private String mobilePhone2;
    private String phone;
    private String fax;
    private Integer customerId;
    private String customerNamePinYin;
    private String customerNo;
    private String idCard;
    private String company;
    private String phoneNo;
    private String taskCustomerName;     //非任务客户姓名
    
    private Date planDate;               //计划日期 
    
    
    /**
     * 
     */
    public TskPlanSelectBean() {
        super();
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getBelongUserName() {
        return belongUserName;
    }
    public void setBelongUserName(String belongUserName) {
        this.belongUserName = belongUserName;
    }
    public Integer getRelativeTsks() {
        return relativeTsks;
    }
    public void setRelativeTsks(Integer relativeTsks) {
        this.relativeTsks = relativeTsks;
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getDefaultPhoneType() {
        return defaultPhoneType;
    }
    public void setDefaultPhoneType(Integer defaultPhoneType) {
        this.defaultPhoneType = defaultPhoneType;
    }
    public String getMobilePhone1() {
        return mobilePhone1;
    }
    public void setMobilePhone1(String mobilePhone1) {
        this.mobilePhone1 = mobilePhone1;
    }
    public String getMobilePhone2() {
        return mobilePhone2;
    }
    public void setMobilePhone2(String mobilePhone2) {
        this.mobilePhone2 = mobilePhone2;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public String getCustomerNamePinYin() {
        return customerNamePinYin;
    }
    public void setCustomerNamePinYin(String customerNamePinYin) {
        this.customerNamePinYin = customerNamePinYin;
    }
    public String getCustomerNo() {
        return customerNo;
    }
    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }
    public String getIdCard() {
        return idCard;
    }
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getTaskCustomerName() {
        return taskCustomerName;
    }
    public void setTaskCustomerName(String taskCustomerName) {
        this.taskCustomerName = taskCustomerName;
    }
    
    public Date getPlanDate() {
        return planDate;
    }
    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public String getTargetIds() {
        return targetIds;
    }
    public void setTargetIds(String targetIds) {
        this.targetIds = targetIds;
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1734157345, 680003431).appendSuper(super.hashCode())
            .append(this.taskCustomerName).append(this.customerName).append(this.defaultPhoneType)
            .append(this.idCard).append(this.phone).append(this.fax).append(this.remark)
            .append(this.customerNo).append(this.phoneNo)
            .append(this.mobilePhone2).append(this.relativeTsks).append(this.mobilePhone1)
            .append(this.customerId).append(this.customerNamePinYin).append(this.company)
            .append(this.belongUserName).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("relativeTsks", this.relativeTsks)
            .append("idCard", this.idCard).append("phoneNo", this.phoneNo)
            .append("mobilePhone2", this.mobilePhone2).append("customerId", this.customerId)
            .append("customerNo", this.customerNo)
            .append("customerNamePinYin", this.customerNamePinYin).append("fax", this.fax)
            .append("mobilePhone1", this.mobilePhone1)
            .append("remark", this.remark).append("company", this.company)
            .append("belongUserName", this.belongUserName)
            .append("taskCustomerName", this.taskCustomerName).append("endRow", this.getEndRow())
            .append("phone", this.phone).append("defaultPhoneType", this.defaultPhoneType)
            .append("customerName", this.customerName)
            .append("startRow", this.getStartRow()).toString();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof TskPlanSelectBean)) {
            return false;
        }
        TskPlanSelectBean rhs = (TskPlanSelectBean) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.taskCustomerName, rhs.taskCustomerName)
            .append(this.customerName, rhs.customerName)
            .append(this.defaultPhoneType, rhs.defaultPhoneType).append(this.idCard, rhs.idCard)
            .append(this.phone, rhs.phone).append(this.fax, rhs.fax)
            .append(this.remark, rhs.remark).append(this.customerNo, rhs.customerNo)
            .append(this.phoneNo, rhs.phoneNo)
            .append(this.mobilePhone2, rhs.mobilePhone2)
            .append(this.relativeTsks, rhs.relativeTsks)
            .append(this.mobilePhone1, rhs.mobilePhone1).append(this.customerId, rhs.customerId)
            .append(this.customerNamePinYin, rhs.customerNamePinYin)
            .append(this.company, rhs.company).append(this.belongUserName, rhs.belongUserName)
            .isEquals();
    }

    
    


}
