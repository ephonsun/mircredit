package com.banger.mobile.domain.model.base.product;

import java.util.Date;

/**
 * PdtProductCustomer entity. @author MyEclipse Persistence Tools
 */

public class BaseProductCustomer implements java.io.Serializable {

    private static final long serialVersionUID = 345595909077450471L;
    // Fields    

    private Integer productCustomerId;
    private Integer productId;
    private Integer customerId;
    private Date    buyDate;
    private Double  buyMoney;
    private String  bankAccount;
    private String  idCard;
    private Integer isDeal;
    private Integer isDel;
    private Integer userId;
    private Integer counterUserId;
    private Integer userType;
    private Date    createDate;
    private Date    updateDate;
    private Integer createUser;
    private Integer updateUser;
    private String  phone;
	public Integer getProductCustomerId() {
		return productCustomerId;
	}
	public void setProductCustomerId(Integer productCustomerId) {
		this.productCustomerId = productCustomerId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public Double getBuyMoney() {
		return buyMoney;
	}
	public void setBuyMoney(Double buyMoney) {
		this.buyMoney = buyMoney;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Integer getIsDeal() {
		return isDeal;
	}
	public void setIsDeal(Integer isDeal) {
		this.isDeal = isDeal;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCounterUserId() {
		return counterUserId;
	}
	public void setCounterUserId(Integer counterUserId) {
		this.counterUserId = counterUserId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bankAccount == null) ? 0 : bankAccount.hashCode());
		result = prime * result + ((buyDate == null) ? 0 : buyDate.hashCode());
		result = prime * result
				+ ((buyMoney == null) ? 0 : buyMoney.hashCode());
		result = prime * result
				+ ((counterUserId == null) ? 0 : counterUserId.hashCode());
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result
				+ ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result
				+ ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((idCard == null) ? 0 : idCard.hashCode());
		result = prime * result + ((isDeal == null) ? 0 : isDeal.hashCode());
		result = prime * result + ((isDel == null) ? 0 : isDel.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime
				* result
				+ ((productCustomerId == null) ? 0 : productCustomerId
						.hashCode());
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
		result = prime * result
				+ ((updateDate == null) ? 0 : updateDate.hashCode());
		result = prime * result
				+ ((updateUser == null) ? 0 : updateUser.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((userType == null) ? 0 : userType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseProductCustomer other = (BaseProductCustomer) obj;
		if (bankAccount == null) {
			if (other.bankAccount != null)
				return false;
		} else if (!bankAccount.equals(other.bankAccount))
			return false;
		if (buyDate == null) {
			if (other.buyDate != null)
				return false;
		} else if (!buyDate.equals(other.buyDate))
			return false;
		if (buyMoney == null) {
			if (other.buyMoney != null)
				return false;
		} else if (!buyMoney.equals(other.buyMoney))
			return false;
		if (counterUserId == null) {
			if (other.counterUserId != null)
				return false;
		} else if (!counterUserId.equals(other.counterUserId))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (createUser == null) {
			if (other.createUser != null)
				return false;
		} else if (!createUser.equals(other.createUser))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (idCard == null) {
			if (other.idCard != null)
				return false;
		} else if (!idCard.equals(other.idCard))
			return false;
		if (isDeal == null) {
			if (other.isDeal != null)
				return false;
		} else if (!isDeal.equals(other.isDeal))
			return false;
		if (isDel == null) {
			if (other.isDel != null)
				return false;
		} else if (!isDel.equals(other.isDel))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (productCustomerId == null) {
			if (other.productCustomerId != null)
				return false;
		} else if (!productCustomerId.equals(other.productCustomerId))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (updateDate == null) {
			if (other.updateDate != null)
				return false;
		} else if (!updateDate.equals(other.updateDate))
			return false;
		if (updateUser == null) {
			if (other.updateUser != null)
				return false;
		} else if (!updateUser.equals(other.updateUser))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userType == null) {
			if (other.userType != null)
				return false;
		} else if (!userType.equals(other.userType))
			return false;
		return true;
	}
	public BaseProductCustomer(Integer productCustomerId, Integer productId,
			Integer customerId, Date buyDate, Double buyMoney,
			String bankAccount, String idCard, Integer isDeal, Integer isDel,
			Integer userId, Integer counterUserId, Integer userType,
			Date createDate, Date updateDate, Integer createUser,
			Integer updateUser, String phone) {
		super();
		this.productCustomerId = productCustomerId;
		this.productId = productId;
		this.customerId = customerId;
		this.buyDate = buyDate;
		this.buyMoney = buyMoney;
		this.bankAccount = bankAccount;
		this.idCard = idCard;
		this.isDeal = isDeal;
		this.isDel = isDel;
		this.userId = userId;
		this.counterUserId = counterUserId;
		this.userType = userType;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.createUser = createUser;
		this.updateUser = updateUser;
		this.phone = phone;
	}
	public BaseProductCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

    
    
}