package com.banger.mobile.domain.model.base.communication;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * CommTemplate entity. @author MyEclipse Persistence Tools
 */
public class BaseCommTemplate implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5752303445640031045L;
	private Integer templateId;
	private Integer partitionId;
	private String templateName;
	private String templateDescription;
	private Integer templateState;
	private Integer templateOrder;
	private Integer isDel;
	private Timestamp createDate;
	private Timestamp updateDate;
	private Integer createUser;
	private Integer updateUser;
	private Integer isBuiltin;

	// Constructors

	/** default constructor */
	public BaseCommTemplate() {
	}

	/** minimal constructor */
	public BaseCommTemplate(Integer templateId, String templateName,
			String templateDescription, Integer templateOrder, Integer isDel,
			Timestamp createDate, Integer createUser) {
		this.templateId = templateId;
		this.templateName = templateName;
		this.templateDescription = templateDescription;
		this.templateOrder = templateOrder;
		this.isDel = isDel;
		this.createDate = createDate;
		this.createUser = createUser;
	}

	/** full constructor */
	public BaseCommTemplate(Integer templateId, Integer partitionId,
			String templateName, String templateDescription,
			Integer templateState, Integer templateOrder, Integer isDel,
			Timestamp createDate, Timestamp updateDate, Integer createUser,
			Integer updateUser, Integer isBuiltin) {
		this.templateId = templateId;
		this.partitionId = partitionId;
		this.templateName = templateName;
		this.templateDescription = templateDescription;
		this.templateState = templateState;
		this.templateOrder = templateOrder;
		this.isDel = isDel;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
		this.isBuiltin = isBuiltin;
	}

	// Property accessors
	@Id
	@Column(name = "TEMPLATE_ID", unique = true, nullable = false)
	public Integer getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	@Column(name = "PARTITION_ID")
	public Integer getPartitionId() {
		return this.partitionId;
	}

	public void setPartitionId(Integer partitionId) {
		this.partitionId = partitionId;
	}

	@Column(name = "TEMPLATE_NAME", nullable = false, length = 150)
	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Column(name = "TEMPLATE_DESCRIPTION", nullable = false, length = 5000)
	public String getTemplateDescription() {
		return this.templateDescription;
	}

	public void setTemplateDescription(String templateDescription) {
		this.templateDescription = templateDescription;
	}

	@Column(name = "TEMPLATE_STATE")
	public Integer getTemplateState() {
		return this.templateState;
	}

	public void setTemplateState(Integer templateState) {
		this.templateState = templateState;
	}

	@Column(name = "TEMPLATE_ORDER", nullable = false)
	public Integer getTemplateOrder() {
		return this.templateOrder;
	}

	public void setTemplateOrder(Integer templateOrder) {
		this.templateOrder = templateOrder;
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