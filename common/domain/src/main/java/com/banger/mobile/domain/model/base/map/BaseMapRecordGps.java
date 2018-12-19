/*
 * banger Inc.
 * Copyright (c) 2009-2013 All Rights Reserved.
 * ToDo       :录音地理位置基础类
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
 * Time: 下午1:59
 */
@SuppressWarnings("rawtypes")
public class BaseMapRecordGps extends BaseObject implements Comparable, Serializable {
    private static final long serialVersionUID = 8171693121846644610L;
    private Integer recordGpsId;
    private Integer   recordInfoId;
    private String  gpsLng;                             //GPS信息经度
    private String  gpsLat;                             //GPS信息纬度
    private String    remark;                           //备注
    private Date updateDate;                            //更新时间
    public Integer getRecordGpsId() {
        return recordGpsId;
    }
    public void setRecordGpsId(Integer recordGpsId) {
        this.recordGpsId = recordGpsId;
    }
    public Integer getRecordInfoId() {
        return recordInfoId;
    }
    public void setRecordInfoId(Integer recordInfoId) {
        this.recordInfoId = recordInfoId;
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
        BaseMapRecordGps myClass = (BaseMapRecordGps) object;
        return new CompareToBuilder().append(this.gpsLng, myClass.gpsLng)
            .append(this.remark, myClass.remark).append(this.gpsLat, myClass.gpsLat)
            .append(this.recordGpsId, myClass.recordGpsId)
            .append(this.recordInfoId, myClass.recordInfoId)
            .append(this.updateDate, myClass.updateDate).toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseMapRecordGps)) {
            return false;
        }
        BaseMapRecordGps rhs = (BaseMapRecordGps) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.gpsLng, rhs.gpsLng).append(this.remark, rhs.remark)
            .append(this.gpsLat, rhs.gpsLat).append(this.recordGpsId, rhs.recordGpsId)
            .append(this.recordInfoId, rhs.recordInfoId).append(this.updateDate, rhs.updateDate)
            .isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-190291745, 1082548525).appendSuper(super.hashCode())
            .append(this.gpsLng).append(this.remark).append(this.gpsLat).append(this.recordGpsId)
            .append(this.recordInfoId).append(this.updateDate).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("remark", this.remark)
            .append("gpsLat", this.gpsLat).append("updateDate", this.updateDate)
            .append("recordGpsId", this.recordGpsId).append("endRow", this.getEndRow())
            .append("recordInfoId", this.recordInfoId).append("gpsLng", this.gpsLng)
            .append("startRow", this.getStartRow()).toString();
    }
    
}
