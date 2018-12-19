/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2012-9-6
 */
package com.banger.mobile.domain.model.base.sysRunDays;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author cheny
 * @version $Id: BaseSysRunDays.java,v 0.1 2012-9-6 下午1:29:12 cheny Exp $
 */
public class BaseSysRunDays extends BaseObject{

    private static final long serialVersionUID = 7372661014487716938L;
    
    private Integer           runDaysId;                               //运行天数ID
    private Date              runDate;                                 //启动、停用时间
    private Integer           runType;                                 //类型
    private Date              createDate;                              //创建时间
    private Date              updateDate;                              //更新时间
    private Integer           createUser;                              //创建用户
    private Integer           updateUser;                              //更新用户
    public Integer getRunDaysId() {
        return runDaysId;
    }
    public void setRunDaysId(Integer runDaysId) {
        this.runDaysId = runDaysId;
    }
    public Date getRunDate() {
        return runDate;
    }
    public void setRunDate(Date runDate) {
        this.runDate = runDate;
    }
    public Integer getRunType() {
        return runType;
    }
    public void setRunType(Integer runType) {
        this.runType = runType;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    public Integer getCreateUser() {
        return createUser;
    }
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }
    public Integer getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseSysRunDays)) {
            return false;
        }
        BaseSysRunDays rhs = (BaseSysRunDays) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.createUser, rhs.createUser).append(this.runType, rhs.runType)
            .append(this.runDate, rhs.runDate).append(this.runDaysId, rhs.runDaysId)
            .append(this.createDate, rhs.createDate).append(this.updateDate, rhs.updateDate)
            .append(this.updateUser, rhs.updateUser).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(2098246849, 377688697).appendSuper(super.hashCode())
            .append(this.createUser).append(this.runType).append(this.runDate)
            .append(this.runDaysId).append(this.createDate).append(this.updateDate)
            .append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("runType", this.runType)
            .append("runDaysId", this.runDaysId).append("createDate", this.createDate)
            .append("runDate", this.runDate).append("updateDate", this.updateDate)
            .append("endRow", this.getEndRow()).append("createUser", this.createUser)
            .append("updateUser", this.updateUser).append("startRow", this.getStartRow())
            .toString();
    }
    
    
    
    

}
