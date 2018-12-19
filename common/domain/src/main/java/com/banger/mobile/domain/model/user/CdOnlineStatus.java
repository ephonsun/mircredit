/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :yangy
 * Create Date:2012-8-13
 */
package com.banger.mobile.domain.model.user;

import java.io.Serializable;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yangyang
 * @version $Id: CdOnlineStatus.java,v 0.1 2012-8-13 下午1:59:17 yangyong Exp $
 */
@SuppressWarnings("rawtypes")
public class CdOnlineStatus extends BaseObject implements Comparable, Serializable {

    private static final long serialVersionUID = -8501768512392208828L;
    private Integer onlineStatusId;                 //代码
    private String onlineStatusName;                //名称
    private Integer sortno;                         //排序号
    
    public Integer getOnlineStatusId() {
        return onlineStatusId;
    }
    public void setOnlineStatusId(Integer onlineStatusId) {
        this.onlineStatusId = onlineStatusId;
    }
    public String getOnlineStatusName() {
        return onlineStatusName;
    }
    public void setOnlineStatusName(String onlineStatusName) {
        this.onlineStatusName = onlineStatusName;
    }
    public Integer getSortno() {
        return sortno;
    }
    public void setSortno(Integer sortno) {
        this.sortno = sortno;
    }
    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object object) {
        CdOnlineStatus myClass = (CdOnlineStatus) object;
        return new CompareToBuilder().append(this.sortno, myClass.sortno)
            .append(this.onlineStatusId, myClass.onlineStatusId)
            .append(this.onlineStatusName, myClass.onlineStatusName).toComparison();
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof CdOnlineStatus)) {
            return false;
        }
        CdOnlineStatus rhs = (CdOnlineStatus) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.sortno, rhs.sortno).append(this.onlineStatusId, rhs.onlineStatusId)
            .append(this.onlineStatusName, rhs.onlineStatusName).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-59209469, 673117293).appendSuper(super.hashCode())
            .append(this.sortno).append(this.onlineStatusId).append(this.onlineStatusName)
            .toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("endRow", this.getEndRow())
            .append("startRow", this.getStartRow()).toString();
    }
    
    
}
