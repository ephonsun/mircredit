package com.banger.mobile.domain.model.base.tskContact;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * TskPlan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BaseTskPlan extends BaseObject{

	private static final long serialVersionUID = -7189900977189623809L;
    // Fields

	private Integer planId;
	private String tableName;
    private Integer executeUserId;
	private Date taskPlanDate;
	private Date createDate;
	private Date updateDate;
	private Integer createUser;
	private Integer updateUser;

	// Constructors

	/** default constructor */
	public BaseTskPlan() {
	}

	/** minimal constructor */
	public BaseTskPlan(Integer planId, String tablieName, Integer executeUserId,
			Date taskPlanDate, Date createDate, Integer createUser) {
		this.planId = planId;
		this.tableName = tablieName;
		this.executeUserId = executeUserId;
		this.taskPlanDate = taskPlanDate;
		this.createDate = createDate;
		this.createUser = createUser;
	}

	/** full constructor */
	public BaseTskPlan(Integer planId, String tablieName, Integer executeUserId,
			Date taskPlanDate, Date createDate, Date updateDate,
			Integer createUser, Integer updateUser) {
		this.planId = planId;
		this.tableName = tablieName;
		this.executeUserId = executeUserId;
		this.taskPlanDate = taskPlanDate;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	// Property accessors
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

	public Integer getPlanId() {
		return this.planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getExecuteUserId() {
		return this.executeUserId;
	}

	public void setExecuteUserId(Integer executeUserId) {
		this.executeUserId = executeUserId;
	}

	public Date getTaskPlanDate() {
		return this.taskPlanDate;
	}

	public void setTaskPlanDate(Date taskPlanDate) {
		this.taskPlanDate = taskPlanDate;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
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

}