/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :活动量统计报表服务
 * Author     :zsw
 * Create Date:2012-4-11
 */
package com.banger.mobile.facade.report;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.record.ReportRecordInfo;
import com.banger.mobile.domain.model.report.RecordActivityReportBean;
import com.banger.mobile.domain.model.report.RecordActivityTrendReportBean;
import com.banger.mobile.domain.model.report.RecordDetailBean;
import com.banger.mobile.facade.BaseReportService;

public interface RecordReportService extends BaseReportService  {
	/**
	 * 得到报表统计数据
	 * @param condition
	 * @return
	 */
	public DataTable getRecordActivityData(RecordActivityReportBean condition);
	
	/**
	 * 得到活动量趋势报表统计数据
	 * @param condition
	 * @return
	 */
	public DataTable getRecordActivityTrendData(RecordActivityTrendReportBean condition);
	
	/**
	 * 得到活动量明细
	 * @param condition
	 * @param page
	 * @return
	 */
	public PageUtil<ReportRecordInfo> getRecordReportDetail(RecordDetailBean condition,Page page,String containSub);
}
