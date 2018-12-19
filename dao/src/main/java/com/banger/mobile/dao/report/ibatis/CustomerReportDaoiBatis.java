/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户统计报表
 * Author     :zsw
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.dao.report.ibatis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.report.CustomerReportDao;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.customer.CustomerBean;
import com.banger.mobile.domain.model.report.AddCustomerReportBean;
import com.banger.mobile.domain.model.report.ContactCustomerDetailBean;
import com.banger.mobile.domain.model.report.ContactCustomerReportBean;
import com.banger.mobile.ibatis.BaseReportDao;

public class CustomerReportDaoiBatis extends BaseReportDao implements CustomerReportDao {

	/**
	 * 得到新增报表统计数据,人员
	 */
	@Override
	public DataTable getAddCustomerReportDataByUser(AddCustomerReportBean condition) {
		return this.queryData("getAddCustomerReportByUser", condition);
	}

	/**
	 * 得到新增报表统计数据，机构
	 */
	@Override
	public DataTable getAddCustomerReportDataByDept(AddCustomerReportBean condition) {
		return this.queryData("getAddCustomerReportByDept", condition);
	}

	/**
	 * 得到客户联系跟进统计数据，人员
	 * @return
	 */
	public DataTable getContactCustomerDataByDept(ContactCustomerReportBean condition){
		Map<String, Object> parm = formatMap(condition);
		return this.queryData("getContactCustomerReportByDept",parm);
	}

	/**
	 * 得到客户联系跟进统计数据，机构
	 * @return
	 */
	public DataTable getContactCustomerDataByUser(ContactCustomerReportBean condition){
		Map<String, Object> parm = formatMap(condition);
		return this.queryData("getContactCustomerReportByUser",parm);
	}

	private Map<String, Object> formatMap(ContactCustomerReportBean condition){
		String endDate = "";
		if(condition.getDateEnd()!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			endDate = sdf.format(condition.getDateEnd());
		}
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("deptIds", condition.getDeptIds());
		parm.put("userIds", condition.getUserIds());
		parm.put("belongTo", condition.getBelongTo());
		parm.put("dateBegin", condition.getDateBegin());
		parm.put("dateEnd", endDate);
		parm.put("customerType", condition.getCustomerType());
		parm.put("customerTypeName", condition.getCustomerTypeName());
		parm.put("contactCount", condition.getContactCount());
		parm.put("containSub", condition.getContainSub());
		parm.put("typeEmpty", condition.getTypeEmpty());
		return parm;
	}

	/**
	 * 得到客户明细
	 * @param condition
	 * @return
	 */
	public PageUtil<CustomerBean> getCustomerReportDetail(ContactCustomerDetailBean condition,Page page)
	{
		@SuppressWarnings("unchecked")
		ArrayList<CustomerBean> list = (ArrayList<CustomerBean>) this.findQueryPage(
				"getCustomerReportDetail", "getCustomerReportDetailCount", condition, page);
		if (list == null) {
			list = new ArrayList<CustomerBean>();
		}
		return new PageUtil<CustomerBean>(list, page);
	}

	/**
	 * 得到客户明细
	 * @param condition
	 * @return
	 */
	public PageUtil<CustomerBean> getContactCustomerReportDetail(ContactCustomerDetailBean condition,Page page){
		@SuppressWarnings("unchecked")
		ArrayList<CustomerBean> list = (ArrayList<CustomerBean>) this.findQueryPage(
				"getContactCustomerReportDetail", "getContactCustomerReportDetailCount", condition, page);
		if (list == null) {
			list = new ArrayList<CustomerBean>();
		}
		return new PageUtil<CustomerBean>(list, page);
	}
}
