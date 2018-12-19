package com.banger.mobile.domain.model.base.tskContact;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;

/**
 * TskContactTarget entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class BaseTskContactTarget extends BaseObject implements Cloneable{

	
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	private static final long serialVersionUID = 1320528952643248079L;
	// Fields
    private Integer contactTargetId;
	private Integer executeId;
	private Integer contactId;
	private Integer customerId;
	private String customerName;
	private String phoneNo;
	private Integer isFinish;
	private String remark;
	private Date createDate;
	private Date updateDate;
	private Integer createUser;
	private Integer updateUser;

	// Constructors

	/** default constructor */
	public BaseTskContactTarget() {
	}

	/** minimal constructor */
	public BaseTskContactTarget(Integer contactTargetId, Integer executeId,
			Integer contactId, Integer customerId, Integer isFinish,
			Date createDate, Integer createUser) {
		this.contactTargetId = contactTargetId;
		this.executeId = executeId;
		this.contactId = contactId;
		this.customerId = customerId;
		this.isFinish = isFinish;
		this.createDate = createDate;
		this.createUser = createUser;
	}

	/** full constructor */
	public BaseTskContactTarget(Integer contactTargetId, Integer executeId,
			Integer contactId, Integer customerId, String customerName,
			String phoneNo, Integer isFinish, String remark, Date createDate,
			Date updateDate, Integer createUser, Integer updateUser) {
		this.contactTargetId = contactTargetId;
		this.executeId = executeId;
		this.contactId = contactId;
		this.customerId = customerId;
		this.customerName = customerName;
		this.phoneNo = phoneNo;
		this.isFinish = isFinish;
		this.remark = remark;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}

	// Property accessors

	public Integer getContactTargetId() {
		return this.contactTargetId;
	}

	public void setContactTargetId(Integer contactTargetId) {
		this.contactTargetId = contactTargetId;
	}

	public Integer getExecuteId() {
		return this.executeId;
	}

	public void setExecuteId(Integer executeId) {
		this.executeId = executeId;
	}

	public Integer getContactId() {
		return this.contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
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

	public Integer getIsFinish() {
		return this.isFinish;
	}

	public void setIsFinish(Integer isFinish) {
		this.isFinish = isFinish;
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