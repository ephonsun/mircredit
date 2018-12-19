/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :营销任务产品Bean
 * Author     :liyb
 * Create Date:2013-2-17
 */
package com.banger.mobile.domain.model.tskMarketing;

/**
 * @author liyb
 * @version $Id: TskMarketingProductBean.java,v 0.1 2013-2-17 上午10:46:17 liyb Exp $
 */
public class TskMarketingProductBean {
	private Integer   productId;        //主键ID
    private String    templateName;     //模版名称
    private String    productName;      //产品名称
    private String    productCode;      //产品编号
    private Integer   marketingId;      //任务ID
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Integer getMarketingId() {
		return marketingId;
	}
	public void setMarketingId(Integer marketingId) {
		this.marketingId = marketingId;
	}
    
    
}
