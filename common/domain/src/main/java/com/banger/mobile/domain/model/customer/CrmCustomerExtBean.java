/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户自定义字段...
 * Author     :yangy
 * Create Date:2012-6-28
 */
package com.banger.mobile.domain.model.customer;

import java.io.Serializable;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yangyang
 * @version $Id: CrmCustomerExtBean.java,v 0.1 2012-6-28 下午4:08:39 yangyong Exp $
 */
public class CrmCustomerExtBean implements Serializable{

    private static final long serialVersionUID = -3531142993755697756L;
    private String fieldstr;
    private String valuestr ;
    private String updatestr ;
    private String templateIds;
    private String lineNumber;          //行号
    
    public String getLineNumber() {
        return lineNumber;
    }
    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }
    public String getTemplateIds() {
        return templateIds;
    }
    public void setTemplateIds(String templateIds) {
        this.templateIds = templateIds;
    }
    public String getFieldstr() {
        return fieldstr;
    }
    public void setFieldstr(String fieldstr) {
        this.fieldstr = fieldstr;
    }
    public String getValuestr() {
        return valuestr;
    }
    public void setValuestr(String valuestr) {
        this.valuestr = valuestr;
    }
    public String getUpdatestr() {
        return updatestr;
    }
    public void setUpdatestr(String updatestr) {
        this.updatestr = updatestr;
    }
    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        CrmCustomerExtBean myClass = (CrmCustomerExtBean) object;
        return new CompareToBuilder().append(this.valuestr, myClass.valuestr)
            .append(this.fieldstr, myClass.fieldstr).append(this.updatestr, myClass.updatestr)
            .toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof CrmCustomerExtBean)) {
            return false;
        }
        CrmCustomerExtBean rhs = (CrmCustomerExtBean) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.valuestr, rhs.valuestr).append(this.fieldstr, rhs.fieldstr)
            .append(this.updatestr, rhs.updatestr).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1386446795, 1428523477).appendSuper(super.hashCode())
            .append(this.valuestr).append(this.fieldstr).append(this.updatestr).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("fieldstr", this.fieldstr)
            .append("valuestr", this.valuestr).append("updatestr", this.updatestr).toString();
    }
    
    
}
