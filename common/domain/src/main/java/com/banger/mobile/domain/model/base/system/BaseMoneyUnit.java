/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :金融单位
 * Author     :yujh
 * Create Date:Aug 14, 2012
 */
package com.banger.mobile.domain.model.base.system;

import java.io.Serializable;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: BaseProductType.java,v 0.1 Aug 14, 2012 3:14:50 PM Administrator Exp $
 */
public class BaseMoneyUnit extends BaseObject implements Serializable{

    private static final long serialVersionUID = -4255810452524720355L;
    
    private     Integer         moneyUnitId;
    private     String          moneyUnitName;
    private     Integer         isDel;
    private     Integer         sortNo;
    public Integer getMoneyUnitId() {
        return moneyUnitId;
    }
    public void setMoneyUnitId(Integer moneyUnitId) {
        this.moneyUnitId = moneyUnitId;
    }
    public String getMoneyUnitName() {
        return moneyUnitName;
    }
    public void setMoneyUnitName(String moneyUnitName) {
        this.moneyUnitName = moneyUnitName;
    }
    public Integer getIsDel() {
        return isDel;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public Integer getSortNo() {
        return sortNo;
    }
    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseMoneyUnit)) {
            return false;
        }
        BaseMoneyUnit rhs = (BaseMoneyUnit) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.moneyUnitId,
            rhs.moneyUnitId).append(this.sortNo, rhs.sortNo).append(this.isDel, rhs.isDel).append(
            this.moneyUnitName, rhs.moneyUnitName).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1086786093, -3307381).appendSuper(super.hashCode()).append(
            this.moneyUnitId).append(this.sortNo).append(this.isDel).append(this.moneyUnitName)
            .toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("sortNo", this.sortNo).append("startRow",
            this.getStartRow()).append("moneyUnitName", this.moneyUnitName).append("endRow",
            this.getEndRow()).append("moneyUnitId", this.moneyUnitId).append("isDel", this.isDel)
            .toString();
    }
    
}
