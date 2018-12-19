/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:May 28, 2012
 */
package com.banger.mobile.domain.model.base.field;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author QianJie
 * @version $Id: BaseCrmFieldType.java,v 0.1 May 28, 2012 11:06:01 AM QianJie Exp $
 */
public class BaseCrmFieldType extends BaseObject implements Serializable{

    private static final long serialVersionUID = 7690481038891056207L;
    
    private String            fieldTypeId;                           //主键ID
    private int               isDel;                                 //是否删除
    private String            fieldTypeName;                         //字段类型名称
    private int               sortNo;                                //排序号    
    private Date              createDate;                            //创建时间
    private Date              updateDate;                            //更新时间
    private int               createUser;                            //创建用户
    private int               updateUser;                            //更新用户
    
    public BaseCrmFieldType() {
        super();
    }
    
    public BaseCrmFieldType(String fieldTypeId, int isDel, String fieldTypeName, int sortNo,
                            Date createDate, Date updateDate, int createUser, int updateUser) {
        super();
        this.fieldTypeId = fieldTypeId;
        this.isDel = isDel;
        this.fieldTypeName = fieldTypeName;
        this.sortNo = sortNo;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    public String getFieldTypeId() {
        return fieldTypeId;
    }
    public void setFieldTypeId(String fieldTypeId) {
        this.fieldTypeId = fieldTypeId;
    }
    public int getIsDel() {
        return isDel;
    }
    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }
    public String getFieldTypeName() {
        return fieldTypeName;
    }
    public void setFieldTypeName(String fieldTypeName) {
        this.fieldTypeName = fieldTypeName;
    }
    public int getSortNo() {
        return sortNo;
    }
    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
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
    public int getCreateUser() {
        return createUser;
    }
    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }
    public int getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseCrmFieldType)) {
            return false;
        }
        BaseCrmFieldType rhs = (BaseCrmFieldType) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.fieldTypeName,
            rhs.fieldTypeName).append(this.sortNo, rhs.sortNo).append(this.isDel, rhs.isDel)
            .append(this.fieldTypeId, rhs.fieldTypeId).append(this.createDate, rhs.createDate)
            .append(this.createUser, rhs.createUser).append(this.updateDate, rhs.updateDate)
            .append(this.updateUser, rhs.updateUser).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-442630349, 1175866879).appendSuper(super.hashCode()).append(
            this.fieldTypeName).append(this.sortNo).append(this.isDel).append(this.fieldTypeId)
            .append(this.createDate).append(this.createUser).append(this.updateDate).append(
                this.updateUser).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("sortNo", this.sortNo).append("startRow",
            this.getStartRow()).append("updateDate", this.updateDate).append("fieldTypeName",
            this.fieldTypeName).append("fieldTypeId", this.fieldTypeId).append("endRow",
            this.getEndRow()).append("updateUser", this.updateUser).append("createUser",
            this.createUser).append("isDel", this.isDel).append("createDate", this.createDate)
            .toString();
    }

    
}
