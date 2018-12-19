/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :QianJie
 * Create Date:May 28, 2012
 */
package com.banger.mobile.domain.model.base.fieldMatch;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author QianJie
 * @version $Id: BaseCrmFieldMatch.java,v 0.1 May 28, 2012 4:07:17 PM QianJie Exp $
 */
public class BaseCrmFieldMatch extends BaseObject implements Serializable {

    private static final long serialVersionUID = -7807909049065470510L;

    private int               fieldMatchId;                            //主键
    private int               fieldId;                                 //业务模版字段ID
    private int               extName;                                 //扩展字段名称
    private int               isDel;                                   //是否删除    
    private Date              createDate;                              //创建时间
    private Date              updateDate;                              //更新时间
    private int               createUser;                              //创建用户
    private int               updateUser;                              //更新用户
    
    public BaseCrmFieldMatch() {
        super();
    }
    
    public BaseCrmFieldMatch(int fieldMatchId, int fieldId, int extName, int isDel,
                             Date createDate, Date updateDate, int createUser, int updateUser) {
        super();
        this.fieldMatchId = fieldMatchId;
        this.fieldId = fieldId;
        this.extName = extName;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    public int getFieldMatchId() {
        return fieldMatchId;
    }
    public void setFieldMatchId(int fieldMatchId) {
        this.fieldMatchId = fieldMatchId;
    }
    public int getFieldId() {
        return fieldId;
    }
    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }
    public int getExtName() {
        return extName;
    }
    public void setExtName(int extName) {
        this.extName = extName;
    }
    public int getIsDel() {
        return isDel;
    }
    public void setIsDel(int isDel) {
        this.isDel = isDel;
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
        if (!(object instanceof BaseCrmFieldMatch)) {
            return false;
        }
        BaseCrmFieldMatch rhs = (BaseCrmFieldMatch) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.isDel, rhs.isDel).append(
                this.fieldMatchId, rhs.fieldMatchId).append(this.extName, rhs.extName).append(
                this.fieldId, rhs.fieldId).append(this.createDate, rhs.createDate).append(
                this.createUser, rhs.createUser).append(this.updateDate, rhs.updateDate).append(
                this.updateUser, rhs.updateUser).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(248891119, 622587357).appendSuper(super.hashCode()).append(this.isDel).append(this.fieldMatchId).append(this.extName).append(
            this.fieldId).append(this.createDate).append(this.createUser).append(this.updateDate)
            .append(this.updateUser).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("startRow",
            this.getStartRow()).append("fieldMatchId", this.fieldMatchId).append("updateDate",
            this.updateDate).append("endRow", this.getEndRow()).append("updateUser",
            this.updateUser).append("fieldId", this.fieldId).append("createUser", this.createUser)
            .append("isDel", this.isDel).append("createDate", this.createDate).append("extName",
                this.extName).toString();
    }


}
