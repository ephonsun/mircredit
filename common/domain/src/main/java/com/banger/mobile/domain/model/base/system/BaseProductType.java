/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品类型实体类
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
public class BaseProductType extends BaseObject implements Serializable{

    private static final long serialVersionUID = -4255810452524720355L;
    
    private     Integer         productTypeId;
    private     String          productTypeName;
    private     Integer         isDel;
    private     Integer         sortNo;
    public Integer getProductTypeId() {
        return productTypeId;
    }
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }
    public String getProductTypeName() {
        return productTypeName;
    }
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
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
        if (!(object instanceof BaseProductType)) {
            return false;
        }
        BaseProductType rhs = (BaseProductType) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.productTypeName,
            rhs.productTypeName).append(this.sortNo, rhs.sortNo).append(this.isDel, rhs.isDel)
            .append(this.productTypeId, rhs.productTypeId).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1113822973, -1188960131).appendSuper(super.hashCode()).append(
            this.productTypeName).append(this.sortNo).append(this.isDel).append(this.productTypeId)
            .toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("productTypeName", this.productTypeName).append(
            "productTypeId", this.productTypeId).append("sortNo", this.sortNo).append("startRow",
            this.getStartRow()).append("endRow", this.getEndRow()).append("isDel", this.isDel)
            .toString();
    }
    
}
