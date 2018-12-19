/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :RecCallType  呼叫类型实体类
 * Author     :zhangxiang
 * Version    :1.0
 * Create Date:May 2, 2012
 */
package com.banger.mobile.domain.model.record;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;



/**
 * @author zhangxiang
 * @version $Id: RecCallType.java,v 0.1 May 2, 2012 2:07:25 PM zhangxiang Exp $
 */
public class RecCallType extends BaseObject implements Serializable {

    private static final long serialVersionUID = 1913523570926793462L;

    private int               callTypeCode;                           //呼叫类型代码

    private String            callTypeName;                           //呼叫类型名称

    private int               sortNo;                                 //排系号

    private Date              createDate;                             //创建时间

    private Date              updateDate;                             //更新时间

    private int               createUser;                             //创建用户

    private int               updateUser;                             //更新用户

    public RecCallType() {
        super();
    }

    public RecCallType(int callTypeCode, String callTypeName, int sortNo, Date createDate,
                       Date updateDate, int createUser, int updateUser) {
        super();
        this.callTypeCode = callTypeCode;
        this.callTypeName = callTypeName;
        this.sortNo = sortNo;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    public int getCallTypeCode() {
        return callTypeCode;
    }

    public void setCallTypeCode(int callTypeCode) {
        this.callTypeCode = callTypeCode;
    }

    public String getCallTypeName() {
        return callTypeName;
    }

    public void setCallTypeName(String callTypeName) {
        this.callTypeName = callTypeName;
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
        if (!(object instanceof RecCallType)) {
            return false;
        }
        RecCallType rhs = (RecCallType) object;
        return new EqualsBuilder().append(this.createUser, rhs.createUser)
            .append(this.callTypeCode, rhs.callTypeCode)
            .append(this.callTypeName, rhs.callTypeName).append(this.sortNo, rhs.sortNo)
            .append(this.createDate, rhs.createDate).append(this.updateDate, rhs.updateDate)
            .append(this.updateUser, rhs.updateUser).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(829019081, -82049813).append(this.createUser)
            .append(this.callTypeCode).append(this.callTypeName).append(this.sortNo)
            .append(this.createDate).append(this.updateDate).append(this.updateUser).toHashCode();
    }

  
}
