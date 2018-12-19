/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :风险测评模板类型
 * Author     :yujh
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.domain.model.base.RskTempType;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: BaseRskTempType.java,v 0.1 Jul 16, 2012 9:46:02 AM Administrator Exp $
 */
public class BaseRskTempType extends BaseObject {

    private static final long serialVersionUID = -7401718707120955819L;
    private Integer           tempTypeId;                              //风险测评模板类型id
    private String            tempTypeName;                            //风险测评模板类型名称
    private Integer           sortNo;                                  //风险测评模板类型排序号

    public Integer getTempTypeId() {
        return tempTypeId;
    }

    public void setTempTypeId(Integer tempTypeId) {
        this.tempTypeId = tempTypeId;
    }

    public String getTempTypeName() {
        return tempTypeName;
    }

    public void setTempTypeName(String tempTypeName) {
        this.tempTypeName = tempTypeName;
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
        if (!(object instanceof BaseRskTempType)) {
            return false;
        }
        BaseRskTempType rhs = (BaseRskTempType) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.tempTypeId,
            rhs.tempTypeId).append(this.sortNo, rhs.sortNo).append(this.tempTypeName,
            rhs.tempTypeName).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(509213191, 561209175).appendSuper(super.hashCode()).append(
            this.tempTypeId).append(this.sortNo).append(this.tempTypeName).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("sortNo", this.sortNo).append("startRow",
            this.getStartRow()).append("endRow", this.getEndRow()).append("tempTypeId",
            this.tempTypeId).append("tempTypeName", this.tempTypeName).toString();
    }

}
