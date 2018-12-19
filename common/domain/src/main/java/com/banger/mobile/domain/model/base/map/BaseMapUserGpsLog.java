/*
 * banger Inc.
 * Copyright (c) 2009-2013 All Rights Reserved.
 * ToDo       :用户地理位置历史记录基础类
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
 * Time: 下午1:56
 * To change this template use File | Settings | File Templates.
 */
public class BaseMapUserGpsLog extends BaseObject implements Comparable, Serializable {
    private static final long serialVersionUID = -9064581122930998878L;
    private Integer           userGpsLogId;                            //主键ID
    private Integer           userGpsId;                               //用户ID
    private String            gpsLng;                                  //GPS信息经度
    private String            gpsLat;                                  //GPS信息纬度
    private String            remark;                                  //备注
    private Date              updateDate;                              //更新时间

    public Integer getUserGpsLogId() {
        return userGpsLogId;
    }

    public void setUserGpsLogId(Integer userGpsLogId) {
        this.userGpsLogId = userGpsLogId;
    }

    public Integer getUserGpsId() {
        return userGpsId;
    }

    public void setUserGpsId(Integer userGpsId) {
        this.userGpsId = userGpsId;
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
        BaseMapUserGpsLog myClass = (BaseMapUserGpsLog) object;
        return new CompareToBuilder().append(this.gpsLng, myClass.gpsLng)
            .append(this.userGpsLogId, myClass.userGpsLogId).append(this.remark, myClass.remark)
            .append(this.gpsLat, myClass.gpsLat).append(this.updateDate, myClass.updateDate)
            .append(this.userGpsId, myClass.userGpsId).toComparison();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseMapUserGpsLog)) {
            return false;
        }
        BaseMapUserGpsLog rhs = (BaseMapUserGpsLog) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.gpsLng, rhs.gpsLng).append(this.userGpsLogId, rhs.userGpsLogId)
            .append(this.remark, rhs.remark).append(this.gpsLat, rhs.gpsLat)
            .append(this.updateDate, rhs.updateDate).append(this.userGpsId, rhs.userGpsId)
            .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-431251109, 477548459).appendSuper(super.hashCode())
            .append(this.gpsLng).append(this.userGpsLogId).append(this.remark).append(this.gpsLat)
            .append(this.updateDate).append(this.userGpsId).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("remark", this.remark)
            .append("gpsLat", this.gpsLat).append("userGpsId", this.userGpsId)
            .append("updateDate", this.updateDate).append("endRow", this.getEndRow())
            .append("gpsLng", this.gpsLng).append("userGpsLogId", this.userGpsLogId)
            .append("startRow", this.getStartRow()).toString();
    }

}
