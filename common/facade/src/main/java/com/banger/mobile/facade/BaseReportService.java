package com.banger.mobile.facade;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.domain.collection.DataTable;

public interface BaseReportService {
	
	/**
	 * 数据表转化为Excel格式
	 * @param table
	 * @return
	 */
	public HSSFWorkbook exportExcel(DataTable table,String userName);
}
