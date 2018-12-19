package com.banger.mobile.domain.model.base.customer;

import java.util.Date;

import com.banger.mobile.domain.model.base.BaseObject;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/**
 * CrmCustomer entity. @author MyEclipse Persistence Tools
 */

public class BaseCrmCustomerShare extends BaseObject implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4221390953343753060L;
	
	private Integer customerIdShareId;				//共享记录ID
	private Integer customerId;						//客户ID
	private Integer userId;							//共享者
	private Integer shareUserId;					//被共享者
	private Date createDate;						//创建时间
	private Date updateDate;						//更新时间
	private Integer createUser;						//新建者
	private Integer updateUser;						//修改者
	public Integer getCustomerIdShareId() {
		return customerIdShareId;
	}
	public void setCustomerIdShareId(Integer customerIdShareId) {
		this.customerIdShareId = customerIdShareId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getShareUserId() {
		return shareUserId;
	}
	public void setShareUserId(Integer shareUserId) {
		this.shareUserId = shareUserId;
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
	
	public BaseCrmCustomerShare() {
	}
	public BaseCrmCustomerShare(Integer customerIdShareId, Integer customerId,
			Integer userId, Integer shareUserId, Date createDate,
			Date updateDate, Integer createUser, Integer updateUser) {
		super();
		this.customerIdShareId = customerIdShareId;
		this.customerId = customerId;
		this.userId = userId;
		this.shareUserId = shareUserId;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BaseCrmCustomerShare)) {
			return false;
		}
		BaseCrmCustomerShare rhs = (BaseCrmCustomerShare) object;
		return new EqualsBuilder().appendSuper(super.equals(object))
				.append(this.createUser, rhs.createUser)
				.append(this.customerId, rhs.customerId)
				.append(this.userId, rhs.userId)
				.append(this.createDate, rhs.createDate)
				.append(this.shareUserId, rhs.shareUserId)
				.append(this.customerIdShareId, rhs.customerIdShareId)
				.append(this.updateDate, rhs.updateDate)
				.append(this.updateUser, rhs.updateUser).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-924351029, -501484693)
				.appendSuper(super.hashCode()).append(this.createUser)
				.append(this.customerId).append(this.userId)
				.append(this.createDate).append(this.shareUserId)
				.append(this.customerIdShareId).append(this.updateDate)
				.append(this.updateUser).toHashCode();
	}
}