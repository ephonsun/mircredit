/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :Administrator
 * Create Date:Aug 22, 2012
 */
package com.banger.mobile.domain.model.base.sysWhiteList;

import java.io.Serializable;
import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * @author Administrator
 * @version $Id: BaseSysWhiteList.java,v 0.1 Aug 22, 2012 12:00:16 PM Administrator Exp $
 */
public class BaseSysWhiteList extends BaseObject implements Serializable{

    private static final long serialVersionUID = -5393204854180696960L;
    
    private Integer         whiteListId;
    private String          whiteListName;
    private String          phoneNo;
    private Integer         isDel;
    private Date            createDate;
    private Date            updDate;
    public Integer getWhiteListId() {
        return whiteListId;
    }
    public void setWhiteListId(Integer whiteListId) {
        this.whiteListId = whiteListId;
    }
    public String getWhiteListName() {
        return whiteListName;
    }
    public void setWhiteListName(String whiteListName) {
        this.whiteListName = whiteListName;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public Integer getIsDel() {
        return isDel;
    }
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getUpdDate() {
        return updDate;
    }
    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }
    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BaseSysWhiteList)) {
            return false;
        }
        BaseSysWhiteList rhs = (BaseSysWhiteList) object;
        return new EqualsBuilder().appendSuper(super.equals(object)).append(this.whiteListName,
            rhs.whiteListName).append(this.updDate, rhs.updDate).append(this.isDel, rhs.isDel)
            .append(this.phoneNo, rhs.phoneNo).append(this.whiteListId, rhs.whiteListId).append(
                this.createDate, rhs.createDate).isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-14493983, -154450893).appendSuper(super.hashCode()).append(
            this.whiteListName).append(this.updDate).append(this.isDel).append(this.phoneNo)
            .append(this.whiteListId).append(this.createDate).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("startRow", this.getStartRow()).append(
            "whiteListId", this.whiteListId).append("whiteListName", this.whiteListName).append(
            "endRow", this.getEndRow()).append("updDate", this.updDate).append("isDel", this.isDel)
            .append("createDate", this.createDate).append("phoneNo", this.phoneNo).toString();
    }
    

}
