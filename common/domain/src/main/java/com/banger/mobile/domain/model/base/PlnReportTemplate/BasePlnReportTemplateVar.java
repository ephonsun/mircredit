/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划报告模版变量
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

public class BasePlnReportTemplateVar extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 9091013589899054363L;
	
	private Integer varId;          //变量ID
	private Integer varTypeId;      //变量父类型
	private Integer subId;          //变量子类型
	private String varName;         //变量名称
	private String varTag;          //变量占位符
	private String varExpression;   //变量表达式
	private Integer sortno;         //排序号
	private String remark;          //备注
	private Timestamp createDate;   //创建时间
	private Timestamp updateDate;   //更新时间
	private Integer createUser;     //创建用户
	private Integer updateUser;     //更新用户

	// Constructors

	/** default constructor */
	public BasePlnReportTemplateVar() {
	}

	/** minimal constructor */
	public BasePlnReportTemplateVar(Integer varId, String varName) {
		this.varId = varId;
		this.varName = varName;
	}

	/** full constructor */
	public BasePlnReportTemplateVar(Integer varId, Integer varTypeId,
			Integer subId, String varName, String varTag, String varExpression,
			Integer sortno, String remark, Timestamp createDate,
			Timestamp updateDate, Integer createUser, Integer updateUser) {
		this.varId = varId;
		this.varTypeId = varTypeId;
		this.subId = subId;
		this.varName = varName;
		this.varTag = varTag;
		this.varExpression = varExpression;
		this.sortno = sortno;
		this.remark = remark;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	// Property accessors

	public Integer getVarId() {
		return this.varId;
	}

	public void setVarId(Integer varId) {
		this.varId = varId;
	}

	public Integer getVarTypeId() {
		return this.varTypeId;
	}

	public void setVarTypeId(Integer varTypeId) {
		this.varTypeId = varTypeId;
	}

	public Integer getSubId() {
		return this.subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public String getVarName() {
		return this.varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public String getVarTag() {
		return this.varTag;
	}

	public void setVarTag(String varTag) {
		this.varTag = varTag;
	}

	public String getVarExpression() {
		return this.varExpression;
	}

	public void setVarExpression(String varExpression) {
		this.varExpression = varExpression;
	}

	public Integer getSortno() {
		return this.sortno;
	}

	public void setSortno(Integer sortno) {
		this.sortno = sortno;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
        if (!(object instanceof BasePlnReportTemplateVar)) {
            return false;
        }
        BasePlnReportTemplateVar rhs = (BasePlnReportTemplateVar) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.sortno, rhs.sortno).append(this.createUser, rhs.createUser)
            .append(this.varExpression, rhs.varExpression).append(this.varId, rhs.varId)
            .append(this.varName, rhs.varName).append(this.remark, rhs.remark)
            .append(this.subId, rhs.subId).append(this.varTag, rhs.varTag)
            .append(this.varTypeId, rhs.varTypeId).append(this.createDate, rhs.createDate)
            .append(this.updateDate, rhs.updateDate).append(this.updateUser, rhs.updateUser)
            .isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-1933409829, -621275437).appendSuper(super.hashCode())
            .append(this.sortno).append(this.createUser).append(this.varExpression)
            .append(this.varId).append(this.varName).append(this.remark).append(this.subId)
            .append(this.varTag).append(this.varTypeId).append(this.createDate)
            .append(this.updateDate).append(this.updateUser).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("createDate", this.createDate)
            .append("varTag", this.varTag).append("updateDate", this.updateDate)
            .append("varExpression", this.varExpression).append("subId", this.subId)
            .append("varName", this.varName).append("remark", this.remark)
            .append("sortno", this.sortno).append("varTypeId", this.varTypeId)
            .append("endRow", this.getEndRow()).append("createUser", this.createUser)
            .append("varId", this.varId).append("updateUser", this.updateUser)
            .append("startRow", this.getStartRow()).toString();
    }

	
}