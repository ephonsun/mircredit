/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户统计报表
 * Author     :zsw
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.dao.report;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.customer.CustomerBean;
import com.banger.mobile.domain.model.report.AddCustomerReportBean;
import com.banger.mobile.domain.model.report.ContactCustomerDetailBean;
import com.banger.mobile.domain.model.report.ContactCustomerReportBean;

public interface CustomerReportDao {
	
	/**
	 * 得到新增报表统计数据,人员
	 */
	public DataTable getAddCustomerReportDataByUser(AddCustomerReportBean condition);
	
	/**
	 * 得到新增报表统计数据,机构
	 */
	public DataTable getAddCustomerReportDataByDept(AddCustomerReportBean condition);
	
	/**
	 * 得到客户联系跟进统计数据，人员
	 * @return
	 */
	public DataTable getContactCustomerDataByDept(ContactCustomerReportBean condition);
	
	/**
	 * 得到客户联系跟进统计数据，机构
	 * @return
	 */
	public DataTable getContactCustomerDataByUser(ContactCustomerReportBean condition);
	
	/**
	 * 得到客户明细
	 * @param condition
	 * @return
	 */
	public PageUtil<CustomerBean> getCustomerReportDetail(ContactCustomerDetailBean condition,Page page);
	
	/**
	 * 得到客户明细
	 * @param condition
	 * @return
	 */
	public PageUtil<CustomerBean> getContactCustomerReportDetail(ContactCustomerDetailBean condition,Page page);
}
