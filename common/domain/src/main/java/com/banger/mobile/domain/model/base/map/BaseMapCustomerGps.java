/*
 * banger Inc.
 * Copyright (c) 2009-2013 All Rights Reserved.
 * ToDo       :客户地理位置记录基础类
 * Author     :yangy
 * Create Date:2013-03-13
 */
package com.banger.mobile.domain.model.base.map;

import com.banger.mobile.domain.model.base.BaseObject;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * User: yangy
 * Date: 13-3-13
 * Time: 下午1:12
 */
@SuppressWarnings("rawtypes")
public class BaseMapCustomerGps extends BaseObject implements Comparable, Serializable {

    private static final long serialVersionUID = -1717989454970278192L;
    private Integer customerGpsId;                      //主键ID
    private Integer customerId;                         //客户ID
    private String  gpsLng;                             //GPS信息经度
    private String  gpsLat;                             //GPS信息纬度
    private String    remark;                           //备注
    private Date createDate;                            //创建时间
    private Date updateDate;                            //更新时间
    private Integer   createUser;                       //创建用户
    private Integer   updateUser;                       //更新用户
    public Integer getCustomerGpsId() {
        return customerGpsId;
    }
    public void setCustomerGpsId(Integer customerGpsId) {
        this.customerGpsId = customerGpsId;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public String getGpsLng() {
        return gpsLng;
    }
    public void setGpsLng(String gpsLng) {
        this.gpsLng = gpsLng;
    }
    public String getGpsLat() {
        return gpsLat;
    }
    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
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
    public Integer getCreateUser() {
        return createUser;
    }
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }
    public Integer getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        BaseMapCustomerGps myClass = (BaseMapCustomerGps) object;
        return new CompareToBuilder().append(this.createUser, myClass.createUser)
            .append(this.customerId, myClass.customerId).append(this.gpsLng, myClass.gpsLng)
            .append(this.remark, myClass.remark).append(this.gpsLat, myClass.gpsLat)
            .append(this.customerGpsId, myClass.customerGpsId)
            .append(this.createDate, myClass.createDate)
            .append(this.updateDate, myClass.updateDate)
            .append(this.updateUser, myClass.updateUser).toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseMapCustomerGps)) {
            return false;
        }
        BaseMapCustomerGps rhs = (BaseMapCustomerGps) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.createUser, rhs.createUser).append(this.customerId, rhs.customerId)
            .append(this.gpsLng, rhs.gpsLng).append(this.remark, rhs.remark)
            .append(this.gpsLat, rhs.gpsLat).append(this.customerGpsId, rhs.customerGpsId)
            .append(this.createDate, rhs.createDate).append(this.updateDate, rhs.updateDate)
            .append(this.updateUser, rhs.updateUser).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1488675169, 183708577).appendSuper(super.hashCode())
            .append(this.createUser).append(this.customerId).append(this.gpsLng)
            .append(this.remark).append(this.gpsLat).append(this.customerGpsId)
            .append(this.createDate).append(this.updateDate).append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("remark", this.remark)
            .append("gpsLat", this.gpsLat).append("customerGpsId", this.customerGpsId)
            .append("createDate", this.createDate).append("updateDate", this.updateDate)
            .append("endRow", this.getEndRow()).append("createUser", this.createUser)
            .append("customerId", this.customerId).append("updateUser", this.updateUser)
            .append("gpsLng", this.gpsLng).append("startRow", this.getStartRow()).toString();
    }
    
 
}
