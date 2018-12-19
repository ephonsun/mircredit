/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :活动量统计报表
 * Author     :zsw
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.dao.report;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.record.ReportRecordInfo;
import com.banger.mobile.domain.model.report.RecordActivityReportBean;
import com.banger.mobile.domain.model.report.RecordActivityTrendReportBean;
import com.banger.mobile.domain.model.report.RecordDetailBean;

public interface RecordReportDao {
	/**
	 * 得到活动量报表统计数据
	 * @param condition
	 * @return
	 */
	public DataTable getRecordActivityDataByUser(RecordActivityReportBean condition);
	
	/**
	 * 得到活动量报表统计数据
	 * @param condition
	 * @return
	 */
	public DataTable getRecordActivityDataByDept(RecordActivityReportBean condition);
	
	/**
	 * 得到活动量趋势报表统计数据
	 * @param condition
	 * @return
	 */
	public DataTable getRecordActivityTrendDataByUser(RecordActivityTrendReportBean condition);
	
	/**
	 * 得到活动量趋势报表统计数据
	 * @param condition
	 * @return
	 */
	public DataTable getRecordActivityTrendDataByDept(RecordActivityTrendReportBean condition);
	
	/**
	 * 得到活动量明细
	 * @param condition
	 * @return
	 */
	public PageUtil<ReportRecordInfo> getRecordReportDetail(RecordDetailBean condition,Page page);
}
