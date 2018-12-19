/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :流量控制实体类
 * Author     :yujh
 * Create Date:May 25, 2012
 */
package com.banger.mobile.domain.model.param;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: SysParamFlow.java,v 0.1 May 25, 2012 1:48:56 PM Administrator Exp $
 */
public class SysParamFlow implements Serializable{

    private static final long serialVersionUID = -5201025770206527118L;
    
    private     Integer         paramId;                    //编号
    private     String          paramKey;                   //参数键
    private     String         paramValue;                 //参数值
    public Integer getParamId() {
        return paramId;
    }
    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }
    public String getParamKey() {
        return paramKey;
    }
    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }
    public String getParamValue() {
        return paramValue;
    }
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof SysParamFlow)) {
            return false;
        }
        SysParamFlow rhs = (SysParamFlow) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.paramValue,
            rhs.paramValue).append(this.paramKey, rhs.paramKey).append(this.paramId, rhs.paramId)
            .isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1375809175, 2011641055).appendSuper(super.hashCode()).append(
            this.paramValue).append(this.paramKey).append(this.paramId).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("paramId", this.paramId).append("paramKey",
            this.paramKey).append("paramValue", this.paramValue).toString();
    }
    

}
