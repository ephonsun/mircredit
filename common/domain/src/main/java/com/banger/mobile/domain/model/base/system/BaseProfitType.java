/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :收益类型实体类
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
public class BaseProfitType extends BaseObject implements Serializable{

    private static final long serialVersionUID = -4255810452524720355L;
    
    private     Integer         profitTypeId;
    private     String          profitTypeName;
    private     Integer         isDel;
    private     Integer         sortNo;
    public Integer getProfitTypeId() {
        return profitTypeId;
    }
    public void setProfitTypeId(Integer profitTypeId) {
        this.profitTypeId = profitTypeId;
    }
    public String getProfitTypeName() {
        return profitTypeName;
    }
    public void setProfitTypeName(String profitTypeName) {
        this.profitTypeName = profitTypeName;
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
        if (!(object instanceof BaseProfitType)) {
            return false;
        }
        BaseProfitType rhs = (BaseProfitType) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.sortNo, rhs.sortNo).append(this.isDel, rhs.isDel).append(
                this.profitTypeName, rhs.profitTypeName)
            .append(this.profitTypeId, rhs.profitTypeId).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-250196919, -414802061).appendSuper(super.hashCode()).append(
            this.sortNo).append(this.isDel).append(this.profitTypeName).append(this.profitTypeId)
            .toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("sortNo", this.sortNo).append("startRow",
            this.getStartRow()).append("endRow", this.getEndRow()).append("isDel", this.isDel)
            .append("profitTypeName", this.profitTypeName)
            .append("profitTypeId", this.profitTypeId).toString();
    }
    
}
