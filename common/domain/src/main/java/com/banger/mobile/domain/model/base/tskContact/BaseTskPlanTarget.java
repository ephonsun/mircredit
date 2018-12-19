package com.banger.mobile.domain.model.base.tskContact;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * TskPlanTarget entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BaseTskPlanTarget extends BaseObject {

	private static final long serialVersionUID = -5844582327059557733L;
    // Fields

	private Integer targetId;
	private Integer planId;
	private Integer contactTargetId;
	private Integer customerId;
	private String customerName;
	private String phoneNo;
	private Integer finishLevel;
	private String remark;
	private Date createDate;
	private Date updateDate;
	private Integer createUser;
	private Integer updateUser;

	// Constructors

	/** default constructor */
	public BaseTskPlanTarget() {
	}

	/** minimal constructor */
	public BaseTskPlanTarget(Integer targetId, Integer planId,
			Integer contactTargetId, Integer customerId, Integer finishLevel,
			Date createDate, Integer createUser) {
		this.targetId = targetId;
		this.planId = planId;
		this.contactTargetId = contactTargetId;
		this.customerId = customerId;
		this.finishLevel = finishLevel;
		this.createDate = createDate;
		this.createUser = createUser;
	}

	/** full constructor */
	public BaseTskPlanTarget(Integer targetId, Integer planId,
			Integer contactTargetId, Integer customerId, String customerName,
			String phoneNo, Integer finishLevel, String remark,
			Date createDate, Date updateDate, Integer createUser,
			Integer updateUser) {
		this.targetId = targetId;
		this.planId = planId;
		this.contactTargetId = contactTargetId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.phoneNo = phoneNo;
		this.finishLevel = finishLevel;
		this.remark = remark;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	// Property accessors

	public Integer getTargetId() {
		return this.targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public Integer getPlanId() {
		return this.planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getContactTargetId() {
		return this.contactTargetId;
	}

	public void setContactTargetId(Integer contactTargetId) {
		this.contactTargetId = contactTargetId;
	}

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getFinishLevel() {
		return this.finishLevel;
	}

	public void setFinishLevel(Integer finishLevel) {
		this.finishLevel = finishLevel;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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