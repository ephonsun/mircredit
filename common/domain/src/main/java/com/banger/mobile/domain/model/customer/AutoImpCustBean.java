/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :This is Class Description...
 * Author     :cheny
 * Create Date:2013-1-22
 */
package com.banger.mobile.domain.model.customer;

import java.io.Serializable;

/**
 * @author cheny
 * @version $Id: AutoImpCustBean.java,v 0.1 2013-1-22 下午4:40:47 cheny Exp $
 */
public class AutoImpCustBean implements Serializable{

    private static final long serialVersionUID = 305104304456129676L;
    
    private CrmCustomer             customer;         //客户
    private CrmCustomerExtField     extField;		  //自定义字段(取)
    private CrmCustomerExtBean      extBean;          //自定义字段(存)
    
	public CrmCustomer getCustomer() {
		return customer;
	}
	public void setCustomer(CrmCustomer customer) {
		this.customer = customer;
	}
	public CrmCustomerExtField getExtField() {
		return extField;
	}
	public void setExtField(CrmCustomerExtField extField) {
		this.extField = extField;
	}
	public CrmCustomerExtBean getExtBean() {
		return extBean;
	}
	public void setExtBean(CrmCustomerExtBean extBean) {
		this.extBean = extBean;
	}
   
}
