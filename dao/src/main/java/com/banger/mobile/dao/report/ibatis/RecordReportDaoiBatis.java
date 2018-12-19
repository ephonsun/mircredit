/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :活动量统计报表
 * Author     :zsw
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.dao.report.ibatis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.report.RecordReportDao;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.record.ReportRecordInfo;
import com.banger.mobile.domain.model.report.RecordActivityReportBean;
import com.banger.mobile.domain.model.report.RecordActivityTrendReportBean;
import com.banger.mobile.domain.model.report.RecordDetailBean;
import com.banger.mobile.ibatis.BaseReportDao;

public class RecordReportDaoiBatis extends BaseReportDao implements RecordReportDao {
	/**
	 * 得到报表统计数据
	 * @param condition
	 * @return
	 */
	public DataTable getRecordActivityDataByUser(RecordActivityReportBean condition){
		Map<String, Object> parm = formatMap(condition);
		return this.queryData("getRecordActivityReportByUser", parm);
	}
	private Map<String, Object> formatMap(RecordActivityReportBean condition){
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
		parm.put("containSub", condition.getContainSub());
		return parm;
	}
	/**
	 * 得到报表统计数据
	 * @param condition
	 * @return
	 */
	public DataTable getRecordActivityDataByDept(RecordActivityReportBean condition){
		Map<String, Object> parm = formatMap(condition);
		return this.queryData("getRecordActivityReportByDept", parm);
	}

	/**
	 * 得到报表统计数据
	 * @param condition
	 * @return
	 */
	public DataTable getRecordActivityTrendDataByUser(RecordActivityTrendReportBean condition){
		return this.queryData("getRecordActivityTrendReportByUser", condition);
	}

	/**
	 * 得到报表统计数据
	 * @param condition
	 * @return
	 */
	public DataTable getRecordActivityTrendDataByDept(RecordActivityTrendReportBean condition){
		return this.queryData("getRecordActivityTrendReportByDept", condition);
	}

	/**
	 * 得到活动量明细
	 * @param condition
	 * @return
	 */
	public PageUtil<ReportRecordInfo> getRecordReportDetail(RecordDetailBean condition,Page page)
	{
		@SuppressWarnings("unchecked")
		ArrayList<ReportRecordInfo> list = (ArrayList<ReportRecordInfo>) this.findQueryPage(
				"getRecordReportDetail", "getRecordReportDetailCount", condition, page);
		if (list == null) {
			list = new ArrayList<ReportRecordInfo>();
		}
		return new PageUtil<ReportRecordInfo>(list, page);
	}
}
