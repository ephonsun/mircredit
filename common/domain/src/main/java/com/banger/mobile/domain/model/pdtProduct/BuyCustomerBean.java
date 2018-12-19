/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :hk
 * Create Date:Aug 16, 2012
 */
package com.banger.mobile.domain.model.pdtProduct;

import java.util.Date;

import com.banger.mobile.domain.model.base.product.BaseProductCustomer;


/**
 * @author hk
 * @version $Id: BuyCustomerBean.java,v 0.1 Aug 16, 2012 11:20:07 AM hk Exp $
 */
public class BuyCustomerBean extends BaseProductCustomer{

    private static final long serialVersionUID = 8388667574524796667L;
    
    private String lineNumber;          //行号
    private String customerNo;          //客户编号
    private String customerName;        //客户姓名
    private String account;             //营销人员
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result
				+ ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result
				+ ((customerNo == null) ? 0 : customerNo.hashCode());
		result = prime * result
				+ ((lineNumber == null) ? 0 : lineNumber.hashCode());
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
		BuyCustomerBean other = (BuyCustomerBean) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
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
		if (lineNumber == null) {
			if (other.lineNumber != null)
				return false;
		} else if (!lineNumber.equals(other.lineNumber))
			return false;
		return true;
	}
	public BuyCustomerBean(Integer productCustomerId, Integer productId,
			Integer customerId, Date buyDate, Double buyMoney,
			String bankAccount, String idCard, Integer isDeal, Integer isDel,
			Integer userId, Integer counterUserId, Integer userType,
			Date createDate, Date updateDate, Integer createUser,
			Integer updateUser, String phone, String lineNumber,
			String customerNo, String customerName, String account) {
		super(productCustomerId, productId, customerId, buyDate, buyMoney,
				bankAccount, idCard, isDeal, isDel, userId, counterUserId,
				userType, createDate, updateDate, createUser, updateUser, phone);
		this.lineNumber = lineNumber;
		this.customerNo = customerNo;
		this.customerName = customerName;
		this.account = account;
	}
	public BuyCustomerBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BuyCustomerBean(Integer productCustomerId, Integer productId,
			Integer customerId, Date buyDate, Double buyMoney,
			String bankAccount, String idCard, Integer isDeal, Integer isDel,
			Integer userId, Integer counterUserId, Integer userType,
			Date createDate, Date updateDate, Integer createUser,
			Integer updateUser, String phone) {
		super(productCustomerId, productId, customerId, buyDate, buyMoney, bankAccount,
				idCard, isDeal, isDel, userId, counterUserId, userType, createDate,
				updateDate, createUser, updateUser, phone);
		// TODO Auto-generated constructor stub
	}
    
    
}
