/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :联系目的实体基类
 * Author     :yujh
 * Create Date:Nov 7, 2012
 */
package com.banger.mobile.domain.model.base.tskTaskPurpose;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: BaseTskTaskPurpose.java,v 0.1 Nov 7, 2012 3:25:51 PM Administrator Exp $
 */
public class BaseTskTaskPurpose extends BaseObject implements Serializable {

    private static final long serialVersionUID = 1808743606602945002L;
    private Integer           taskPurposeId;
    private String            taskPurposeName;
    private Integer           sortNo;
    private Integer           isDel;
    private Integer           isActived;
    private Date              creatDate;
    private Date              updateDate;
    public Integer getTaskPurposeId() {
        return taskPurposeId;
    }
    public void setTaskPurposeId(Integer taskPurposeId) {
        this.taskPurposeId = taskPurposeId;
    }
    public String getTaskPurposeName() {
        return taskPurposeName;
    }
    public void setTaskPurposeName(String taskPurposeName) {
        this.taskPurposeName = taskPurposeName;
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
    public Integer getIsActived() {
        return isActived;
    }
    public void setIsActived(Integer isActived) {
        this.isActived = isActived;
    }
    public Date getCreatDate() {
        return creatDate;
    }
    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseTskTaskPurpose)) {
            return false;
        }
        BaseTskTaskPurpose rhs = (BaseTskTaskPurpose) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.isDel, rhs.isDel)
            .append(this.sortNo, rhs.sortNo).append(this.isActived, rhs.isActived).append(
                this.creatDate, rhs.creatDate).append(this.taskPurposeName, rhs.taskPurposeName)
            .append(this.updateDate, rhs.updateDate).append(this.taskPurposeId, rhs.taskPurposeId)
            .isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1078700725, 1093479185).appendSuper(super.hashCode()).append(
            this.isDel).append(this.sortNo).append(this.isActived).append(this.creatDate).append(
            this.taskPurposeName).append(this.updateDate).append(this.taskPurposeId).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("taskPurposeId", this.taskPurposeId).append(
            "sortNo", this.sortNo).append("startRow", this.getStartRow()).append("updateDate",
            this.updateDate).append("creatDate", this.creatDate)
            .append("isActived", this.isActived).append("taskPurposeName", this.taskPurposeName)
            .append("endRow", this.getEndRow()).append("isDel", this.isDel).toString();
    }
    
}
