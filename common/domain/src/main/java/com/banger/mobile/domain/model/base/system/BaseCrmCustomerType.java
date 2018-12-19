/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:May 21, 2012
 */
package com.banger.mobile.domain.model.base.system;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author QianJie
 * @version $Id: BaseCrmCustomerType.java,v 0.1 May 21, 2012 10:08:26 AM QianJie Exp $
 */
public class BaseCrmCustomerType extends BaseObject implements Serializable {

    private static final long serialVersionUID = 782828824992651196L;

    private int               customerTypeId;                        //主键ID
    private int               isDel;                                 //是否删除
    private String            customerTypeName;                      //类型名称
    private int               sortNo;                                //排序号    
    private int               isActived;                             //1 启用 、 0停用
    private Date              createDate;                            //创建时间
    private Date              updateDate;                            //更新时间
    private int               createUser;                            //创建用户
    private int               updateUser;                            //更新用户

    public BaseCrmCustomerType(){
        super();
    }
    public BaseCrmCustomerType(int customerTypeId, int isDel, String customerTypeName, int sortNo,
                               int isActived, Date createDate, Date updateDate, int createUser,
                               int updateUser) {
        super();
        this.customerTypeId = customerTypeId;
        this.isDel = isDel;
        this.customerTypeName = customerTypeName;
        this.sortNo = sortNo;
        this.isActived = isActived;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    public int getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(int customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public String getCustomerTypeName() {
        return customerTypeName;
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public int getIsActived() {
        return isActived;
    }

    public void setIsActived(int isActived) {
        this.isActived = isActived;
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
        if (!(object instanceof BaseCrmCustomerType)) {
            return false;
        }
        BaseCrmCustomerType rhs = (BaseCrmCustomerType) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.customerTypeName,
            rhs.customerTypeName).append(this.sortNo, rhs.sortNo).append(this.isDel, rhs.isDel)
            .append(this.isActived, rhs.isActived).append(this.createDate, rhs.createDate).append(
                this.createUser, rhs.createUser).append(this.updateDate, rhs.updateDate).append(
                this.customerTypeId, rhs.customerTypeId).append(this.updateUser, rhs.updateUser)
            .isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(2113477699, 452485477).appendSuper(super.hashCode()).append(
            this.customerTypeName).append(this.sortNo).append(this.isDel).append(this.isActived)
            .append(this.createDate).append(this.createUser).append(this.updateDate).append(
                this.customerTypeId).append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("customerTypeName", this.customerTypeName).append(
            "sortNo", this.sortNo).append("startRow", this.getStartRow()).append("updateDate",
            this.updateDate).append("endRow", this.getEndRow()).append("isActived", this.isActived)
            .append("updateUser", this.updateUser).append("createUser", this.createUser).append(
                "isDel", this.isDel).append("createDate", this.createDate).append("customerTypeId",
                this.customerTypeId).toString();
    }

    
}
