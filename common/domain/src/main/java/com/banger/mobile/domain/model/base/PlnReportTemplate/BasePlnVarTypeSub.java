/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :规划报告变量子类型
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


public class BasePlnVarTypeSub extends BaseObject implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -4330084173820198029L;
	
	private Integer subId;          //规划报告变量子类型ID
	private Integer varTypeId;      //父类型ID
	private String subCode;         //子分类代码
	private String subName;         //子分类名称
	private Integer sortno;         //排序号
	private Timestamp createDate;   //创建时间
	private Timestamp updateDate;   //更新时间
	private Integer createUser;     //创建用户
	private Integer updateUser;     //更新用户

	// Constructors

	/** default constructor */
	public BasePlnVarTypeSub() {
	}

	/** minimal constructor */
	public BasePlnVarTypeSub(Integer subId, String subName) {
		this.subId = subId;
		this.subName = subName;
	}

	/** full constructor */
	public BasePlnVarTypeSub(Integer subId, Integer varTypeId, String subCode,
			String subName, Integer sortno, Timestamp createDate,
			Timestamp updateDate, Integer createUser, Integer updateUser) {
		this.subId = subId;
		this.varTypeId = varTypeId;
		this.subCode = subCode;
		this.subName = subName;
		this.sortno = sortno;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	// Property accessors

	public Integer getSubId() {
		return this.subId;
	}

	public void setSubId(Integer subId) {
		this.subId = subId;
	}

	public Integer getVarTypeId() {
		return this.varTypeId;
	}

	public void setVarTypeId(Integer varTypeId) {
		this.varTypeId = varTypeId;
	}

	public String getSubCode() {
		return this.subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getSubName() {
		return this.subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
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
        if (!(object instanceof BasePlnVarTypeSub)) {
            return false;
        }
        BasePlnVarTypeSub rhs = (BasePlnVarTypeSub) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.sortno, rhs.sortno).append(this.subName, rhs.subName)
            .append(this.createUser, rhs.createUser).append(this.subCode, rhs.subCode)
            .append(this.subId, rhs.subId).append(this.varTypeId, rhs.varTypeId)
            .append(this.createDate, rhs.createDate).append(this.updateDate, rhs.updateDate)
            .append(this.updateUser, rhs.updateUser).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-863321939, -1167614653).appendSuper(super.hashCode())
            .append(this.sortno).append(this.subName).append(this.createUser).append(this.subCode)
            .append(this.subId).append(this.varTypeId).append(this.createDate)
            .append(this.updateDate).append(this.updateUser).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("sortno", this.sortno)
            .append("subName", this.subName).append("createDate", this.createDate)
            .append("varTypeId", this.varTypeId).append("updateDate", this.updateDate)
            .append("endRow", this.getEndRow()).append("subCode", this.subCode)
            .append("createUser", this.createUser).append("subId", this.subId)
            .append("updateUser", this.updateUser).append("startRow", this.getStartRow())
            .toString();
    }

	
}