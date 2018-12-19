package com.banger.mobile.domain.model.base.customer;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/**
 * CrmCustomer entity. @author MyEclipse Persistence Tools
 */

public class BaseCrmCustomerRelatives extends BaseObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7248283590575540143L;
	private Integer customerRelativesId;			//亲友关联id
	private Integer customerId;						//客户ID
	private Integer relativesId;					//关联者ID
	private Date createDate;						//创建时间
	private Date updateDate;						//更新时间
	private Integer createUser;						//新建者
	private Integer updateUser;						//修改者
	
	public Integer getCustomerRelativesId() {
		return customerRelativesId;
	}
	public void setCustomerRelativesId(Integer customerRelativesId) {
		this.customerRelativesId = customerRelativesId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getRelativesId() {
		return relativesId;
	}
	public void setRelativesId(Integer relativesId) {
		this.relativesId = relativesId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BaseCrmCustomerRelatives() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BaseCrmCustomerRelatives(Integer customerRelativesId,
			Integer customerId, Integer relativesId, Date createDate,
			Date updateDate, Integer createUser, Integer updateUser) {
		super();
		this.customerRelativesId = customerRelativesId;
		this.customerId = customerId;
		this.relativesId = relativesId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BaseCrmCustomerRelatives)) {
			return false;
		}
		BaseCrmCustomerRelatives rhs = (BaseCrmCustomerRelatives) object;
		return new EqualsBuilder().appendSuper(super.equals(object))
				.append(this.createUser, rhs.createUser)
				.append(this.customerId, rhs.customerId)
				.append(this.relativesId, rhs.relativesId)
				.append(this.customerRelativesId, rhs.customerRelativesId)
				.append(this.createDate, rhs.createDate)
				.append(this.updateDate, rhs.updateDate)
				.append(this.updateUser, rhs.updateUser).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1694700145, 1068865233)
				.appendSuper(super.hashCode()).append(this.createUser)
				.append(this.customerId).append(this.relativesId)
				.append(this.customerRelativesId).append(this.createDate)
				.append(this.updateDate).append(this.updateUser).toHashCode();
	}
	
	
}