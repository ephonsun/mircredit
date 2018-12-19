/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:May 28, 2012
 */
package com.banger.mobile.domain.model.base.fieldCodeData;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author QianJie
 * @version $Id: BaseCrmFieldCodeData.java,v 0.1 May 28, 2012 2:49:24 PM QianJie Exp $
 */
public class BaseCrmFieldCodeData extends BaseObject implements Serializable{

    private static final long serialVersionUID = -7840971986921073617L;
    
    private int               fieldCodeDataId;                         //主键
    private int               fieldId;                                 //业务模版字段ID
    private String            fieldCodeDataKey;                        //代码键
    private String            fieldCodeDataValue;                      //代码值
    private int               isDel;                                   //是否删除
    private int               sortNo;                                  //排序号    
    private Date              createDate;                              //创建时间
    private Date              updateDate;                              //更新时间
    private int               createUser;                              //创建用户
    private int               updateUser;                              //更新用户
    
    public BaseCrmFieldCodeData() {
        super();
    }
    public BaseCrmFieldCodeData(int fieldCodeDataId, int fieldId, String fieldCodeDataKey,
                                String fieldCodeDataValue, int isDel, int sortNo, Date createDate,
                                Date updateDate, int createUser, int updateUser) {
        super();
        this.fieldCodeDataId = fieldCodeDataId;
        this.fieldId = fieldId;
        this.fieldCodeDataKey = fieldCodeDataKey;
        this.fieldCodeDataValue = fieldCodeDataValue;
        this.isDel = isDel;
        this.sortNo = sortNo;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }
    
    public int getFieldCodeDataId() {
        return fieldCodeDataId;
    }
    public void setFieldCodeDataId(int fieldCodeDataId) {
        this.fieldCodeDataId = fieldCodeDataId;
    }
    public int getFieldId() {
        return fieldId;
    }
    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }
    public String getFieldCodeDataKey() {
        return fieldCodeDataKey;
    }
    public void setFieldCodeDataKey(String fieldCodeDataKey) {
        this.fieldCodeDataKey = fieldCodeDataKey;
    }
    public String getFieldCodeDataValue() {
        return fieldCodeDataValue;
    }
    public void setFieldCodeDataValue(String fieldCodeDataValue) {
        this.fieldCodeDataValue = fieldCodeDataValue;
    }
    public int getIsDel() {
        return isDel;
    }
    public void setIsDel(int isDel) {
        this.isDel = isDel;
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
        if (!(object instanceof BaseCrmFieldCodeData)) {
            return false;
        }
        BaseCrmFieldCodeData rhs = (BaseCrmFieldCodeData) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.sortNo, rhs.sortNo).append(this.isDel, rhs.isDel).append(
                this.fieldCodeDataValue, rhs.fieldCodeDataValue).append(this.fieldId, rhs.fieldId)
            .append(this.createDate, rhs.createDate).append(this.fieldCodeDataId,
                rhs.fieldCodeDataId).append(this.createUser, rhs.createUser).append(
                this.updateDate, rhs.updateDate)
            .append(this.fieldCodeDataKey, rhs.fieldCodeDataKey).append(this.updateUser,
                rhs.updateUser).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(924460665, -461955473).appendSuper(super.hashCode()).append(
            this.sortNo).append(this.isDel).append(this.fieldCodeDataValue).append(this.fieldId)
            .append(this.createDate).append(this.fieldCodeDataId).append(this.createUser).append(
                this.updateDate).append(this.fieldCodeDataKey).append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("fieldCodeDataValue", this.fieldCodeDataValue)
            .append("startRow", this.getStartRow()).append("updateDate", this.updateDate).append(
                "endRow", this.getEndRow()).append("fieldId", this.fieldId).append("updateUser",
                this.updateUser).append("createUser", this.createUser)
            .append("sortNo", this.sortNo).append("fieldCodeDataKey", this.fieldCodeDataKey)
            .append("isDel", this.isDel).append("fieldCodeDataId", this.fieldCodeDataId).append(
                "createDate", this.createDate).toString();
    }

}
