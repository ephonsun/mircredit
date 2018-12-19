/*
 * banger Inc.
 * Copyright (c) 2009-2013 All Rights Reserved.
 * ToDo       :用户地理位置基础类
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
 * Time: 下午1:48
 */
public class BaseMapUserGps extends BaseObject implements Comparable, Serializable {
    private static final long serialVersionUID = -6478930748883665233L;

    private Integer userGpsId;                          //主键ID
    private Integer userId;                             //用户ID
    private String  gpsLng;                             //GPS信息经度
    private String  gpsLat;                             //GPS信息纬度
    private String    remark;                           //备注
    private Date updateDate;                            //更新时间

    public Integer getUserGpsId() {
        return userGpsId;
    }

    public void setUserGpsId(Integer userGpsId) {
        this.userGpsId = userGpsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        BaseMapUserGps myClass = (BaseMapUserGps) object;
        return new CompareToBuilder().append(this.gpsLng, myClass.gpsLng)
            .append(this.remark, myClass.remark).append(this.gpsLat, myClass.gpsLat)
            .append(this.userId, myClass.userId).append(this.updateDate, myClass.updateDate)
            .append(this.userGpsId, myClass.userGpsId).toComparison();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseMapUserGps)) {
            return false;
        }
        BaseMapUserGps rhs = (BaseMapUserGps) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.gpsLng, rhs.gpsLng).append(this.remark, rhs.remark)
            .append(this.gpsLat, rhs.gpsLat).append(this.userId, rhs.userId)
            .append(this.updateDate, rhs.updateDate).append(this.userGpsId, rhs.userGpsId)
            .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1551955021, 1725949353).appendSuper(super.hashCode())
            .append(this.gpsLng).append(this.remark).append(this.gpsLat).append(this.userId)
            .append(this.updateDate).append(this.userGpsId).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("remark", this.remark)
            .append("gpsLat", this.gpsLat).append("userId", this.userId)
            .append("userGpsId", this.userGpsId).append("updateDate", this.updateDate)
            .append("endRow", this.getEndRow()).append("gpsLng", this.gpsLng)
            .append("startRow", this.getStartRow()).toString();
    }

    
}
