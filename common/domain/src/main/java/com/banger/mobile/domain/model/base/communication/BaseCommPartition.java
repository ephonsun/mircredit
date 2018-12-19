package com.banger.mobile.domain.model.base.communication;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * CommPartition entity. @author MyEclipse Persistence Tools
 */
public class BaseCommPartition implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6604717380537646563L;
	// Fields

	private Integer partitionId;
	private String partitionName;
	private String partitionDescription;
	private Integer userId;
	private Integer partitionOrder;
	private Integer isDel;
	private Timestamp createDate;
	private Timestamp updateDate;
	private Integer createUser;
	private Integer updateUser;
	private Integer isBuiltin;

	// Constructors

	/** default constructor */
	public BaseCommPartition() {
	}

	/** minimal constructor */
	public BaseCommPartition(Integer partitionId, String partitionName,
			String partitionDescription, Integer userId,
			Integer partitionOrder, Integer isDel, Timestamp createDate,
			Integer createUser) {
		this.partitionId = partitionId;
		this.partitionName = partitionName;
		this.partitionDescription = partitionDescription;
		this.userId = userId;
		this.partitionOrder = partitionOrder;
		this.isDel = isDel;
		this.createDate = createDate;
		this.createUser = createUser;
	}

	/** full constructor */
	public BaseCommPartition(Integer partitionId, String partitionName,
			String partitionDescription, Integer userId,
			Integer partitionOrder, Integer isDel, Timestamp createDate,
			Timestamp updateDate, Integer createUser, Integer updateUser,
			Integer isBuiltin) {
		this.partitionId = partitionId;
		this.partitionName = partitionName;
		this.partitionDescription = partitionDescription;
		this.userId = userId;
		this.partitionOrder = partitionOrder;
		this.isDel = isDel;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
		this.isBuiltin = isBuiltin;
	}

	// Property accessors
	@Id
	@Column(name = "PARTITION_ID", unique = true, nullable = false)
	public Integer getPartitionId() {
		return this.partitionId;
	}

	public void setPartitionId(Integer partitionId) {
		this.partitionId = partitionId;
	}

	@Column(name = "PARTITION_NAME", nullable = false, length = 150)
	public String getPartitionName() {
		return this.partitionName;
	}

	public void setPartitionName(String partitionName) {
		this.partitionName = partitionName;
	}

	@Column(name = "PARTITION_DESCRIPTION", nullable = false, length = 5000)
	public String getPartitionDescription() {
		return this.partitionDescription;
	}

	public void setPartitionDescription(String partitionDescription) {
		this.partitionDescription = partitionDescription;
	}

	@Column(name = "USER_ID", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "PARTITION_ORDER", nullable = false)
	public Integer getPartitionOrder() {
		return this.partitionOrder;
	}

	public void setPartitionOrder(Integer partitionOrder) {
		this.partitionOrder = partitionOrder;
	}

	@Column(name = "IS_DEL", nullable = false)
	public Integer getIsDel() {
		return this.isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	@Column(name = "CREATE_DATE", nullable = false, length = 26)
	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Column(name = "UPDATE_DATE", length = 26)
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "CREATE_USER", nullable = false)
	public Integer getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	@Column(name = "UPDATE_USER")
	public Integer getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "IS_BUILTIN")
	public Integer getIsBuiltin() {
		return this.isBuiltin;
	}

	public void setIsBuiltin(Integer isBuiltin) {
		this.isBuiltin = isBuiltin;
	}

}