/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户归属Action
 * Author     :zsw
 * Create Date:2012-8-2
 */
package com.banger.mobile.webapp.action.customer;


import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.model.customer.CrmCustomerRelatives;
import com.banger.mobile.facade.customer.CrmCustomerRelativesService;
import com.banger.mobile.webapp.action.BaseAction;

/**
 * 
 * @author xuhj
 * @version $Id: CustomerRelativesAction.java,v 0.1 2012-5-24 下午02:30:34 xuhj Exp $
 */
public class CustomerRelativesAction extends BaseAction  {

	private static final long serialVersionUID = -537563615973533450L;
	
	private CrmCustomerRelativesService        crmCustomerRelativesService;  
	
	public CrmCustomerRelativesService getCrmCustomerRelativesService() {
		return crmCustomerRelativesService;
	}

	public void setCrmCustomerRelativesService(
			CrmCustomerRelativesService crmCustomerRelativesService) {
		this.crmCustomerRelativesService = crmCustomerRelativesService;
	}

	/**
	 * 亲友信息 页面显示
	 * @return
	 */
	public String relativesInfo(){
		this.getPage().setPageSize(10);
		PageUtil<CrmCustomerRelatives> cusList = 
				crmCustomerRelativesService.selectCustomerRelatives(request.getParameter("customerId"), this.getPage());
		request.setAttribute("cusList", cusList);
		request.setAttribute("customerId", request.getParameter("customerId"));
		request.setAttribute("actionType", request.getParameter("actionType"));
		request.setAttribute("needLine", request.getParameter("needLine")==null?1:request.getParameter("needLine"));
		return SUCCESS;
	}
	/**
	 * 添加亲友信息
	 * @return
	 */
	public String addRelativesCustomer(){
		String customerId = request.getParameter("customerId");
		String relativesIds = request.getParameter("relativesIds");
		try{
			crmCustomerRelativesService.addRelativesCustomer(customerId, relativesIds);
			return null;
		}catch (Exception e) {
			return ERROR;
		}
	}
	/**
	 * 移除亲友信息
	 * @return
	 */
	public String delRelatives(){
		String customerRelativesId = request.getParameter("customerRelativesId");
		try{
			crmCustomerRelativesService.delRelatives(customerRelativesId);
			return null;
		}catch (Exception e) {
			return ERROR;
		}
	}
}
