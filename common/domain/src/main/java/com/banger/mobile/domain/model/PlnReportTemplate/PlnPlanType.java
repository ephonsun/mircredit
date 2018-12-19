/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划报告模板类型
 * Author     :cheny
 * Create Date:2012-7-17
 */
package com.banger.mobile.domain.model.PlnReportTemplate;

import java.sql.Timestamp;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author cheny
 * @version $Id: PlnPlanType.java,v 0.1 2012-7-17 上午9:48:43 cheny Exp $
 */
public class PlnPlanType extends BaseObject implements java.io.Serializable{

    private static final long serialVersionUID = -3783038607330997601L;

    private Integer planTypeId;         //模板类型id
    private String planTypeName;        //模板类型名称
    private Timestamp createDate;         //创建时间
    private Timestamp updateDate;         //更新时间
    private Integer createUser;           //创建用户
    private Integer updateUser;           //更新用户
    
    
    
    
    /**
     * @param pLAN_TYPE_ID
     * @param pLAN_TYPE_NAME
     * @param createDate
     * @param updateDate
     * @param createUser
     * @param updateUser
     */
  
    /**
     * 
     */
    public PlnPlanType() {
        super();
    }
    /**
     * @param planTypeId
     * @param planTypeName
     * @param createDate
     * @param updateDate
     * @param createUser
     * @param updateUser
     */
    public PlnPlanType(Integer planTypeId, String planTypeName, Timestamp createDate,
                       Timestamp updateDate, Integer createUser, Integer updateUser) {
        super();
        this.planTypeId = planTypeId;
        this.planTypeName = planTypeName;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }
  
    public Integer getPlanTypeId() {
        return planTypeId;
    }
    public void setPlanTypeId(Integer planTypeId) {
        this.planTypeId = planTypeId;
    }
    public String getPlanTypeName() {
        return planTypeName;
    }
    public void setPlanTypeName(String planTypeName) {
        this.planTypeName = planTypeName;
    }
    public Timestamp getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    public Timestamp getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Timestamp updateDate) {
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
        if (!(object instanceof PlnPlanType)) {
            return false;
        }
        PlnPlanType rhs = (PlnPlanType) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.createUser, rhs.createUser).append(this.planTypeName, rhs.planTypeName)
            .append(this.planTypeId, rhs.planTypeId).append(this.createDate, rhs.createDate)
            .append(this.updateDate, rhs.updateDate).append(this.updateUser, rhs.updateUser)
            .isEquals();
    }
    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1442288897, 1270165903).appendSuper(super.hashCode())
            .append(this.createUser).append(this.planTypeName).append(this.planTypeId)
            .append(this.createDate).append(this.updateDate).append(this.updateUser).toHashCode();
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("planTypeName", this.planTypeName)
            .append("createDate", this.createDate).append("updateDate", this.updateDate)
            .append("endRow", this.getEndRow()).append("createUser", this.createUser)
            .append("updateUser", this.updateUser).append("planTypeId", this.planTypeId)
            .append("startRow", this.getStartRow()).toString();
    }
   
 
}
