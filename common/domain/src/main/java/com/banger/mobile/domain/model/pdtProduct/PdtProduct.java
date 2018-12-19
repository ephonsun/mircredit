/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :产品主体
 * Author     :yujh
 * Create Date:Jul 16, 2012
 */
package com.banger.mobile.domain.model.pdtProduct;

import java.util.Date;

import com.banger.mobile.domain.model.base.product.BasePdtProduct;

/**
 * @author cheny
 * @version $Id: PdtProduct.java,v 0.1 2012-12-24 上午9:48:43 cheny Exp $
 */

public class PdtProduct extends BasePdtProduct {

    private static final long serialVersionUID = -6389010932720096538L;

    private String templateName;		//产品类型
    private Integer buyCustomerCount;	//购买客户数
    private double buyMoney; 			//已销售额
    private String pdtStateStr;         //状态名称
    private String lineNumber;          //行号
    private Integer isCanUpdate;		//是否可以修改
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public Integer getBuyCustomerCount() {
		return buyCustomerCount;
	}
	public void setBuyCustomerCount(Integer buyCustomerCount) {
		this.buyCustomerCount = buyCustomerCount;
	}
	public double getBuyMoney() {
		return buyMoney;
	}
	public void setBuyMoney(double buyMoney) {
		this.buyMoney = buyMoney;
	}
	public String getPdtStateStr() {
		return pdtStateStr;
	}
	public void setPdtStateStr(String pdtStateStr) {
		this.pdtStateStr = pdtStateStr;
	}
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	public Integer getIsCanUpdate() {
		return isCanUpdate;
	}
	public void setIsCanUpdate(Integer isCanUpdate) {
		this.isCanUpdate = isCanUpdate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((buyCustomerCount == null) ? 0 : buyCustomerCount.hashCode());
		long temp;
		temp = Double.doubleToLongBits(buyMoney);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((isCanUpdate == null) ? 0 : isCanUpdate.hashCode());
		result = prime * result
				+ ((lineNumber == null) ? 0 : lineNumber.hashCode());
		result = prime * result
				+ ((pdtStateStr == null) ? 0 : pdtStateStr.hashCode());
		result = prime * result
				+ ((templateName == null) ? 0 : templateName.hashCode());
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
		PdtProduct other = (PdtProduct) obj;
		if (buyCustomerCount == null) {
			if (other.buyCustomerCount != null)
				return false;
		} else if (!buyCustomerCount.equals(other.buyCustomerCount))
			return false;
		if (Double.doubleToLongBits(buyMoney) != Double
				.doubleToLongBits(other.buyMoney))
			return false;
		if (isCanUpdate == null) {
			if (other.isCanUpdate != null)
				return false;
		} else if (!isCanUpdate.equals(other.isCanUpdate))
			return false;
		if (lineNumber == null) {
			if (other.lineNumber != null)
				return false;
		} else if (!lineNumber.equals(other.lineNumber))
			return false;
		if (pdtStateStr == null) {
			if (other.pdtStateStr != null)
				return false;
		} else if (!pdtStateStr.equals(other.pdtStateStr))
			return false;
		if (templateName == null) {
			if (other.templateName != null)
				return false;
		} else if (!templateName.equals(other.templateName))
			return false;
		return true;
	}
	public PdtProduct(String templateName, Integer buyCustomerCount,
			double buyMoney, String pdtStateStr, String lineNumber,
			Integer isCanUpdate) {
		super();
		this.templateName = templateName;
		this.buyCustomerCount = buyCustomerCount;
		this.buyMoney = buyMoney;
		this.pdtStateStr = pdtStateStr;
		this.lineNumber = lineNumber;
		this.isCanUpdate = isCanUpdate;
	}
	public PdtProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PdtProduct(Integer productId, Integer templateId,
			String productName, String productCode, Integer productState,
			Date saleStartDate, Date saleEndDate, Integer isDel,
			Date createDate, Date updateDate, Integer createUser,
			Integer updateUser) {
		super(productId, templateId, productName, productCode, productState,
				saleStartDate, saleEndDate, isDel, createDate, updateDate, createUser,
				updateUser);
		// TODO Auto-generated constructor stub
	}
	public PdtProduct(Integer productId) {
		super(productId);
		// TODO Auto-generated constructor stub
	}

    
}