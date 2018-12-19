/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD品牌实体基类
 * Author     :liyb
 * Create Date:2013-6-18
 */
package com.banger.mobile.domain.model.base.padManagement;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author liyb
 * @version $Id: BasePadBrands.java,v 0.1 2013-6-18 上午09:59:36 liyb Exp $
 */
public class BasePadBrands extends BaseObject implements Serializable {

    private static final long serialVersionUID = -8951064531688554457L;

    private Integer           brandTypeId;                              //主键ID
    private String            brandTypeName;                            //名称
    private Integer           isDel;                                   //是否删除
    private Integer           isActived;                               //是否启用 1:启用 0:不启用
    private Integer           sortNo;                                  //排序号
    private String            remark;                                  //备注
    private Date              createDate;                              //创建时间
    private Date              updateDate;                              //更新时间
    private Integer           createUser;                              //创建用户
    private Integer           updateUser;                              //更新用户
    public Integer getBrandTypeId() {
        return brandTypeId;
    }
    public void setBrandTypeId(Integer brandTypeId) {
        this.brandTypeId = brandTypeId;
    }
    public String getBrandTypeName() {
        return brandTypeName;
    }
    public void setBrandTypeName(String brandTypeName) {
        this.brandTypeName = brandTypeName;
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
    public Integer getSortNo() {
        return sortNo;
    }
    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
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
        if (!(object instanceof BasePadBrands)) {
            return false;
        }
        BasePadBrands rhs = (BasePadBrands) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.brandTypeName,
            rhs.brandTypeName).append(this.createUser, rhs.createUser)
            .append(this.isDel, rhs.isDel).append(this.remark, rhs.remark).append(this.brandTypeId,
                rhs.brandTypeId).append(this.sortNo, rhs.sortNo).append(this.isActived,
                rhs.isActived).append(this.createDate, rhs.createDate).append(this.updateDate,
                rhs.updateDate).append(this.updateUser, rhs.updateUser).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(399060613, 968335055).appendSuper(super.hashCode()).append(
            this.brandTypeName).append(this.createUser).append(this.isDel).append(this.remark)
            .append(this.brandTypeId).append(this.sortNo).append(this.isActived).append(
                this.createDate).append(this.updateDate).append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("createDate", this.createDate).append("isActived",
            this.isActived).append("updateDate", this.updateDate).append("isDel", this.isDel)
            .append("brandTypeName", this.brandTypeName).append("remark", this.remark).append(
                "brandTypeId", this.brandTypeId).append("endRow", this.getEndRow()).append(
                "createUser", this.createUser).append("updateUser", this.updateUser).append(
                "sortNo", this.sortNo).append("startRow", this.getStartRow()).toString();
    }
    
}
