package com.banger.mobile.domain.model.pdtProduct;

import com.banger.mobile.domain.model.base.product.BaseProductCustomer;

/**
 * @author wumh E-mail:wumh@baihang-china.com
 * @version 创建时间：Jan 4, 2013 2:45:34 PM
 * 类说明
 */
public class PdtProductCustomer extends BaseProductCustomer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4289926933866949973L;
	private String       customerName;
	private String       customerNo;				//客户编号
	private String       company;
	private String       buyMoneyStr;         //销售额字符串
	private String       userName;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getBuyMoneyStr() {
		return buyMoneyStr;
	}
	public void setBuyMoneyStr(String buyMoneyStr) {
		this.buyMoneyStr = buyMoneyStr;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((buyMoneyStr == null) ? 0 : buyMoneyStr.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result
				+ ((customerNo == null) ? 0 : customerNo.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PdtProductCustomer other = (PdtProductCustomer) obj;
		if (buyMoneyStr == null) {
			if (other.buyMoneyStr != null)
				return false;
		} else if (!buyMoneyStr.equals(other.buyMoneyStr))
			return false;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (customerNo == null) {
			if (other.customerNo != null)
				return false;
		} else if (!customerNo.equals(other.customerNo))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}            
}



