/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :投资偏好
 * Author     :yujh
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.domain.model.base.rskIntervalType;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: BaseRskIntervalType.java,v 0.1 Jul 16, 2012 2:58:34 PM Administrator Exp $
 */
public class BaseRskIntervalType extends BaseObject {
    private static final long serialVersionUID = 4252921860503581943L;
    private Integer           typeId;                                 //id
    private String            typeName;                               //区间类型名称
    private Integer           sortNo;                                 //排序号
    private Integer           isDel;                                  //是否删除
    private Integer           isActive;                               //是否禁用
    public Integer getTypeId() {
        return typeId;
    }
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public Integer getSortNo() {
        return sortNo;
    }
    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
    public Integer getIsDel() {
        return isDel;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public Integer getIsActive() {
        return isActive;
    }
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseRskIntervalType)) {
            return false;
        }
        BaseRskIntervalType rhs = (BaseRskIntervalType) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.typeName,
            rhs.typeName).append(this.typeId, rhs.typeId).append(this.isDel, rhs.isDel).append(
            this.sortNo, rhs.sortNo).append(this.isActive, rhs.isActive).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1614842767, 1775451739).appendSuper(super.hashCode()).append(
            this.typeName).append(this.typeId).append(this.isDel).append(this.sortNo).append(
            this.isActive).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("sortNo", this.sortNo).append("typeName",
            this.typeName).append("startRow", this.getStartRow()).append("typeId", this.typeId)
            .append("endRow", this.getEndRow()).append("isDel", this.isDel).append("isActive",
                this.isActive).toString();
    }
    
}
