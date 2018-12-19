/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :规划报告变量类型
 * Author     :cheny
 * Create Date:2012-7-16
 */
package com.banger.mobile.domain.model.base.PlnReportTemplate;

import java.sql.Timestamp;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author cheny
 * @version $Id: PlnReportTemplateVarDao.java,v 0.1 2012-7-16 上午9:07:59 cheny Exp $
 */


public class BasePlnVarType extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 3671933911592725886L;
	
	private Integer varTypeId;      //规划报告变量类型ID
	private Integer planTypeId;     //理财规划类型ID
	private String varTypeCode;     //分类代码
	private String varTypeName;     //分类名称
	private Integer sortno;         //排序号
	private Timestamp createDate;   //创建时间
	private Timestamp updateDate;   //更新时间
	private Integer createUser;     //创建用户
	private Integer updateUser;     //更新用户

	// Constructors

	/** default constructor */
	public BasePlnVarType() {
	}

	/** minimal constructor */
	public BasePlnVarType(Integer varTypeId) {
		this.varTypeId = varTypeId;
	}

	/** full constructor */
	public BasePlnVarType(Integer varTypeId, Integer planTypeId,
			String varTypeCode, String varTypeName, Integer sortno,
			Timestamp createDate, Timestamp updateDate, Integer createUser,
			Integer updateUser) {
		this.varTypeId = varTypeId;
		this.planTypeId = planTypeId;
		this.varTypeCode = varTypeCode;
		this.varTypeName = varTypeName;
		this.sortno = sortno;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	// Property accessors

	public Integer getVarTypeId() {
		return this.varTypeId;
	}

	public void setVarTypeId(Integer varTypeId) {
		this.varTypeId = varTypeId;
	}

	public Integer getPlanTypeId() {
		return this.planTypeId;
	}

	public void setPlanTypeId(Integer planTypeId) {
		this.planTypeId = planTypeId;
	}

	public String getVarTypeCode() {
		return this.varTypeCode;
	}

	public void setVarTypeCode(String varTypeCode) {
		this.varTypeCode = varTypeCode;
	}

	public String getVarTypeName() {
		return this.varTypeName;
	}

	public void setVarTypeName(String varTypeName) {
		this.varTypeName = varTypeName;
	}

	public Integer getSortno() {
		return this.sortno;
	}

	public void setSortno(Integer sortno) {
		this.sortno = sortno;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BasePlnVarType)) {
            return false;
        }
        BasePlnVarType rhs = (BasePlnVarType) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.sortno, rhs.sortno).append(this.varTypeCode, rhs.varTypeCode)
            .append(this.createUser, rhs.createUser).append(this.planTypeId, rhs.planTypeId)
            .append(this.varTypeId, rhs.varTypeId).append(this.varTypeName, rhs.varTypeName)
            .append(this.createDate, rhs.createDate).append(this.updateDate, rhs.updateDate)
            .append(this.updateUser, rhs.updateUser).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-743928627, 2020094481).appendSuper(super.hashCode())
            .append(this.sortno).append(this.varTypeCode).append(this.createUser)
            .append(this.planTypeId).append(this.varTypeId).append(this.varTypeName)
            .append(this.createDate).append(this.updateDate).append(this.updateUser).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("varTypeName", this.varTypeName)
            .append("sortno", this.sortno).append("createDate", this.createDate)
            .append("varTypeId", this.varTypeId).append("updateDate", this.updateDate)
            .append("endRow", this.getEndRow()).append("createUser", this.createUser)
            .append("updateUser", this.updateUser).append("varTypeCode", this.varTypeCode)
            .append("planTypeId", this.planTypeId).append("startRow", this.getStartRow())
            .toString();
    }
	
	

}