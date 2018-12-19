package com.banger.mobile.facade.report;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.customer.CustomerBean;
import com.banger.mobile.domain.model.report.ContactCustomerDetailBean;
import com.banger.mobile.domain.model.report.ContactCustomerReportBean;
import com.banger.mobile.facade.BaseReportService;

public interface ContactCustomerReportService extends BaseReportService {
	/**
	 * 得到报表统计数据
	 * @param condition
	 * @return
	 */
	public DataTable getContactCustomerReportData(ContactCustomerReportBean condition);
	/**
	 * 客户明细
	 * @param condition
	 * @param page
	 * @return
	 */
	public PageUtil<CustomerBean> getContactCustomerReportDetail(ContactCustomerDetailBean condition,Page page, String containSub);
}
