/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :全局参数实体基类
 * Author     :yujh
 * Create Date:May 25, 2012
 */
package com.banger.mobile.domain.model.base.param;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author yujh
 * @version $Id: BaseSysParam.java,v 0.1 May 25, 2012 10:09:10 AM Administrator Exp $
 */
public class BaseSysParam extends BaseObject implements Serializable{

    private static final long serialVersionUID = -6077357057655749466L;
    
    private     Integer             paramId;            //编号
    private     String              paramKey;           //参数键
    private     String              paramValue;         //参数值
    private     String              remark;             //备注
    private     Date                createDate;         //创建时间
    private     Date                updateDate;         //更新时间
    private     Integer             createUser;         //创建用户
    private     Integer             updateUser;         //更新用户
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
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
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
        if (!(object instanceof BaseSysParam)) {
            return false;
        }
        BaseSysParam rhs = (BaseSysParam) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.paramValue,
            rhs.paramValue).append(this.paramKey, rhs.paramKey).append(this.createDate,
            rhs.createDate).append(this.paramId, rhs.paramId).append(this.remark, rhs.remark)
            .append(this.createUser, rhs.createUser).append(this.updateDate, rhs.updateDate)
            .append(this.updateUser, rhs.updateUser).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1349281941, 924638555).appendSuper(super.hashCode()).append(
            this.paramValue).append(this.paramKey).append(this.createDate).append(this.paramId)
            .append(this.remark).append(this.createUser).append(this.updateDate).append(
                this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("paramId", this.paramId).append("startRow",
            this.getStartRow()).append("paramKey", this.paramKey).append("updateDate",
            this.updateDate).append("endRow", this.getEndRow()).append("updateUser",
            this.updateUser).append("createUser", this.createUser).append("createDate",
            this.createDate).append("remark", this.remark).append("paramValue", this.paramValue)
            .toString();
    }
    

}
