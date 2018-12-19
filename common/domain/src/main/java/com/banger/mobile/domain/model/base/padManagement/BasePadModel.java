/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :PAD型号实体基类
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
 * @version $Id: BasePadModel.java,v 0.1 2013-6-18 上午10:01:16 liyb Exp $
 */
public class BasePadModel extends BaseObject implements Serializable {

    private static final long serialVersionUID = 7673277181321410899L;

    private Integer           padSubTypeId;                            //主键ID
    private Integer           padTypeId;                               //品牌类型ID
    private String            padSubTypeName;                          //型号名称
    private Integer           isDel;                                  //是否删除
    private Integer           isActived;                              //是否启用  1:启用 0:不启用
    private Integer           sortNo;                                 //排序号
    private String            remark;                                 //备注
    private Date              createDate;                             //创建时间
    private Date              updateDate;                             //更新时间
    private Integer           createUser;                             //创建用户
    private Integer           updateUser;                             //更新用户
    public Integer getPadSubTypeId() {
        return padSubTypeId;
    }
    public void setPadSubTypeId(Integer padSubTypeId) {
        this.padSubTypeId = padSubTypeId;
    }
    public Integer getPadTypeId() {
        return padTypeId;
    }
    public void setPadTypeId(Integer padTypeId) {
        this.padTypeId = padTypeId;
    }
    public String getPadSubTypeName() {
        return padSubTypeName;
    }
    public void setPadSubTypeName(String padSubTypeName) {
        this.padSubTypeName = padSubTypeName;
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
        if (!(object instanceof BasePadModel)) {
            return false;
        }
        BasePadModel rhs = (BasePadModel) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.createUser,
            rhs.createUser).append(this.isDel, rhs.isDel).append(this.remark, rhs.remark).append(
            this.sortNo, rhs.sortNo).append(this.padTypeId, rhs.padTypeId).append(this.isActived,
            rhs.isActived).append(this.createDate, rhs.createDate).append(this.padSubTypeName,
            rhs.padSubTypeName).append(this.padSubTypeId, rhs.padSubTypeId).append(this.updateDate,
            rhs.updateDate).append(this.updateUser, rhs.updateUser).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-626181977, -1744607763).appendSuper(super.hashCode()).append(
            this.createUser).append(this.isDel).append(this.remark).append(this.sortNo).append(
            this.padTypeId).append(this.isActived).append(this.createDate).append(
            this.padSubTypeName).append(this.padSubTypeId).append(this.updateDate).append(
            this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("padSubTypeName", this.padSubTypeName).append(
            "createDate", this.createDate).append("isActived", this.isActived).append("updateDate",
            this.updateDate).append("isDel", this.isDel).append("padSubTypeId", this.padSubTypeId)
            .append("remark", this.remark).append("endRow", this.getEndRow()).append("createUser",
                this.createUser).append("updateUser", this.updateUser).append("padTypeId",
                this.padTypeId).append("sortNo", this.sortNo)
            .append("startRow", this.getStartRow()).toString();
    }
    
    
}
