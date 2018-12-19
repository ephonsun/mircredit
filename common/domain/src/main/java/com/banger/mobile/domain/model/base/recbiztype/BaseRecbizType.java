/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :RecbizType业务类型实体基类
 * Author     :liyb
 * Create Date:2012-5-17
 */
package com.banger.mobile.domain.model.base.recbiztype;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author liyb
 * @version $Id: BaseRecbizType.java,v 0.1 2012-5-17 下午03:44:21 liyb Exp $
 */
public class BaseRecbizType extends BaseObject implements Serializable {

    private static final long serialVersionUID = 884367930122452297L;

    private Integer           bizTypeId;                             //主键
    private String            bizTypeName;                           //业务类型名称
    private Integer           isActived;                             //是否启用  1：启用  0：停用
    private Integer           isDel;                                 //是否删除  0：不删除  1：删除
    private Integer           sortno;                                //排序号
    private String            remark;                                //备注
    private Date              createDate;                            //创建时间
    private Date              updateDate;                            //更新时间
    private Integer           createUser;                            //创建用户
    private Integer           updateUser;                            //更新用户
    
    public Integer getBizTypeId() {
        return bizTypeId;
    }
    public void setBizTypeId(Integer bizTypeId) {
        this.bizTypeId = bizTypeId;
    }
    public String getBizTypeName() {
        return bizTypeName;
    }
    public void setBizTypeName(String bizTypeName) {
        this.bizTypeName = bizTypeName;
    }
    public Integer getIsActived() {
        return isActived;
    }
    public void setIsActived(Integer isActived) {
        this.isActived = isActived;
    }
    public Integer getIsDel() {
        return isDel;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public Integer getSortno() {
        return sortno;
    }
    public void setSortno(Integer sortno) {
        this.sortno = sortno;
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
        if (!(object instanceof BaseRecbizType)) {
            return false;
        }
        BaseRecbizType rhs = (BaseRecbizType) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.sortno, rhs.sortno).append(this.createUser, rhs.createUser).append(
                this.isDel, rhs.isDel).append(this.remark, rhs.remark).append(this.bizTypeName,
                rhs.bizTypeName).append(this.bizTypeId, rhs.bizTypeId).append(this.isActived,
                rhs.isActived).append(this.createDate, rhs.createDate).append(this.updateDate,
                rhs.updateDate).append(this.updateUser, rhs.updateUser).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(1671057873, -83523303).appendSuper(super.hashCode()).append(
            this.sortno).append(this.createUser).append(this.isDel).append(this.remark).append(
            this.bizTypeName).append(this.bizTypeId).append(this.isActived).append(this.createDate)
            .append(this.updateDate).append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("bizTypeId", this.bizTypeId).append("createDate",
            this.createDate).append("isActived", this.isActived).append("updateDate",
            this.updateDate).append("bizTypeName", this.bizTypeName).append("isDel", this.isDel)
            .append("remark", this.remark).append("sortno", this.sortno).append("endRow",
                this.getEndRow()).append("createUser", this.createUser).append("updateUser",
                this.updateUser).append("startRow", this.getStartRow()).toString();
    }
    
    
}
