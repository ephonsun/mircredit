/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :理财规划报告模板
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
 * @version $Id: PlnReportTemplateDao.java,v 0.1 2012-7-16 上午9:07:59 cheny Exp $
 */

public class BasePlnReportTemplate extends BaseObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4903403573392221187L;
	// Fields

	private Integer templateId;           //模板ID
	private String templateName;		  //模板名称
	private String templateNo;            //模板编号
	private Integer intervalTypeId;       //投资偏好id
	private Integer planTypeId;           //规划类型
	private Integer isActived;            //是否启用
	private String content;               //模板内容
	private Integer isDel;                //是否删除
	private Timestamp createDate;         //创建时间
	private Timestamp updateDate;         //更新时间
	private Integer createUser;           //创建用户
	private Integer updateUser;           //更新用户

	// Constructors


	// Property accessors

	public Integer getTemplateId() {
		return this.templateId;
	}

	/**
     * 
     */
    public BasePlnReportTemplate() {
        super();
    }

    /**
     * @param templateId
     * @param templateName
     * @param templateNo
     * @param intervalTypeId
     * @param planTypeId
     * @param isActived
     * @param content
     * @param isDel
     * @param createDate
     * @param updateDate
     * @param createUser
     * @param updateUser
     */
    public BasePlnReportTemplate(Integer templateId, String templateName, String templateNo,
                                 Integer intervalTypeId, Integer planTypeId, Integer isActived,
                                 String content, Integer isDel, Timestamp createDate,
                                 Timestamp updateDate, Integer createUser, Integer updateUser) {
        super();
        this.templateId = templateId;
        this.templateName = templateName;
        this.templateNo = templateNo;
        this.intervalTypeId = intervalTypeId;
        this.planTypeId = planTypeId;
        this.isActived = isActived;
        this.content = content;
        this.isDel = isDel;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateNo() {
		return this.templateNo;
	}

	public void setTemplateNo(String templateNo) {
		this.templateNo = templateNo;
	}


	public Integer getIntervalTypeId() {
        return intervalTypeId;
    }

    public void setIntervalTypeId(Integer intervalTypeId) {
        this.intervalTypeId = intervalTypeId;
    }

    public Integer getPlanTypeId() {
		return this.planTypeId;
	}

	public void setPlanTypeId(Integer planTypeId) {
		this.planTypeId = planTypeId;
	}

	public Integer getIsActived() {
		return this.isActived;
	}

	public void setIsActived(Integer isActived) {
		this.isActived = isActived;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
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
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return new HashCodeBuilder(-399803541, -494484519).appendSuper(super.hashCode())
            .append(this.content).append(this.templateId).append(this.templateNo)
            .append(this.createUser).append(this.isDel).append(this.intervalTypeId)
            .append(this.planTypeId).append(this.isActived).append(this.createDate)
            .append(this.updateDate).append(this.updateUser).append(this.templateName).toHashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return new ToStringBuilder(this).append("templateNo", this.templateNo)
            .append("intervalTypeId", this.intervalTypeId).append("content", this.content)
            .append("createDate", this.createDate).append("isActived", this.isActived)
            .append("updateDate", this.updateDate).append("isDel", this.isDel)
            .append("templateId", this.templateId).append("endRow", this.getEndRow())
            .append("createUser", this.createUser).append("updateUser", this.updateUser)
            .append("templateName", this.templateName).append("startRow", this.getStartRow())
            .append("planTypeId", this.planTypeId).toString();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    public boolean equals(Object object) {
        if (!(object instanceof BasePlnReportTemplate)) {
            return false;
        }
        BasePlnReportTemplate rhs = (BasePlnReportTemplate) object;
        return new EqualsBuilder().appendSuper(super.equals(object))
            .append(this.content, rhs.content).append(this.templateId, rhs.templateId)
            .append(this.templateNo, rhs.templateNo).append(this.createUser, rhs.createUser)
            .append(this.isDel, rhs.isDel).append(this.intervalTypeId, rhs.intervalTypeId)
            .append(this.planTypeId, rhs.planTypeId).append(this.isActived, rhs.isActived)
            .append(this.createDate, rhs.createDate).append(this.updateDate, rhs.updateDate)
            .append(this.updateUser, rhs.updateUser).append(this.templateName, rhs.templateName)
            .isEquals();
    }

	
}