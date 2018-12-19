/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :活动量趋势报表action
 * Author     :xuhj
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.webapp.action.report;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.collection.DataColumn;
import com.banger.mobile.domain.collection.DataRow;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.record.ReportRecordInfo;
import com.banger.mobile.domain.model.report.RecordActivityTrendReportBean;
import com.banger.mobile.domain.model.report.RecordDetailBean;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.report.RecordReportService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.DateWrapper;
import com.banger.mobile.util.ExcelUtil;
import com.banger.mobile.util.TimeWrapper;
import com.banger.mobile.webapp.action.BaseAction;
/**
 * @author xuhj
 * @version $Id: RecordActivityTrendReportAction.java,v 0.1 Jun 1, 2012 4:54:57 PM xuhj Exp $
 */
public class RecordActivityTrendReportAction extends BaseAction {
	private static final long serialVersionUID = -2703552853892448025L;
	private RecordReportService report;		//报表业务类
	private DataTable table;						//报表数据
	private RecordActivityTrendReportBean condition;			//报表查询条件
	private RecordDetailBean detail;				//明细条件
	private PageUtil<ReportRecordInfo> recordList;
	private String userName;						//制表人
	private Integer userId;							//用户Id
	private Date reportTime;						//制表时间
	private Integer[] years;
    private DeptFacadeService dept;		//机构
    private Boolean hasBelongTo;		//有下属权限
    
	public Boolean getHasBelongTo() {
		return hasBelongTo;
	}

	public void setHasBelongTo(Boolean hasBelongTo) {
		this.hasBelongTo = hasBelongTo;
	}
    
	public DeptFacadeService getDept() {
		return dept;
	}

	public void setDept(DeptFacadeService dept) {
		this.dept = dept;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public PageUtil<ReportRecordInfo> getRecordList() {
		return recordList;
	}

	public void setRecordList(PageUtil<ReportRecordInfo> recordList) {
		this.recordList = recordList;
	}

	public RecordDetailBean getDetail() {
		return detail;
	}

	public void setDetail(RecordDetailBean detail) {
		this.detail = detail;
	}

	public RecordReportService getReport() {
		return report;
	}

	public void setReport(RecordReportService report) {
		this.report = report;
	}

	public RecordActivityTrendReportBean getCondition() {
		return condition;
	}

	public void setCondition(RecordActivityTrendReportBean condition) {
		this.condition = condition;
	}

	public Integer[] getYears() {
		return years;
	}

	public void setYears(Integer[] years) {
		this.years = years;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public DataTable getTable() {
		return table;
	}

	public void setTable(DataTable table) {
		this.table = table;
	}
	/**
	 * 构造函数
	 */
	public RecordActivityTrendReportAction()
	{
		this.condition = new RecordActivityTrendReportBean();
		this.detail = new RecordDetailBean();
		List<Integer> years = new ArrayList<Integer>();
		DateWrapper dw = new DateWrapper(new Date().getTime());
		int yearStart = dw.getYear()-10;
		for(int y=yearStart;y<yearStart+21;y++)
		{
			years.add(y);
		}
		this.years = years.toArray(new Integer[0]);
	}
	/**
	 * 跳转到活动量趋势报表
	 * @return
	 */
	public String getReportPage()
	{
		try {
			DateWrapper dw = new DateWrapper(new Date().getTime());
			this.condition.setYearBegin(dw.getYear());
			this.condition.setYearEnd(dw.getYear());
			this.condition.setMonthBegin(1);
			this.condition.setMonthEnd(12);
			int m = dw.getMonth();
			Integer q = (m%3==0)?m:m/3+1;
			this.condition.setQuarterBegin(1);
			this.condition.setQuarterEnd(4);
			
			this.userName = this.getLoginInfo().getUserName();
			this.userId = this.getLoginInfo().getUserId();
			this.hasBelongTo = dept.isInChargeOfDepartment();
			return SUCCESS;
		} catch (Exception e) {
			log.error("getReportPage:"+e.getMessage());
			return "error";
		}
		
	}
	
	/**
	 * 显示报表明细
	 * @return
	 */
	public String showReportDetial()
	{
		formatDetailBean(this.detail);
		this.recordList = this.report.getRecordReportDetail(this.detail,this.getPage(),"0");//0表示不包含下属
		return SUCCESS;
	}
	
	private void formatDetailBean(RecordDetailBean detailBean){
		String dateBegin = "";
		String dateEnd = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");   
		Date date = null; 
		if(detailBean.getDateBegin()!=null){
			try {  
				date = format.parse(detailBean.getDateBegin());   
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM"); 
				dateBegin = sdf.format(date);
			} catch (ParseException e) {   
				dateBegin = detailBean.getDateBegin(); 
			}   
		}
		
		Date date1 = null; 
		if(detailBean.getDateEnd()!=null){
			try {   
				date1 = format.parse(detailBean.getDateEnd());  
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM"); 
				dateEnd = sdf.format(date1);
			} catch (ParseException e) {   
				dateEnd = detailBean.getDateEnd();
			}   
		}

		detailBean.setDateBegin(dateBegin);
		detailBean.setDateEnd(dateEnd);
	}
	/**
	 * 显示报表结果
	 * @return
	 */
	public String showReport()
	{
		formatDetailBean(this.detail);
		String reportType = request.getParameter("reportType");
		DateUtil.format(condition);
		try {
			this.table = this.report.getRecordActivityTrendData(condition);
			this.userName = this.getLoginInfo().getUserName();
			this.reportTime = new TimeWrapper(new Date().getTime());
			if(reportType.equals("rtLine")){
				formatTable(table);
			}else if(reportType.equals("rtColumn")){
				formatTable(table);
			}
			return reportType;
		} catch (Exception e) {
			log.error("showReport:"+e.getMessage());
			return "error";
		}
		
	}
	/**
	 * 格式化table
	 * @param table
	 */
	private void formatTable(DataTable table){
		ArrayList<String> categories = new ArrayList<String>();
		JSONArray jsonSeries = new JSONArray();
		DataColumn[] cols = table.getColumns();
		DataRow[] rows = table.getRows();
		for(DataColumn column : cols){
			if(column.getName().equals("name")){
				for(DataRow row: rows){
					if(row.getIndex()>0){
						categories.add("'"+row.get(column.getName()).toString()+"'");
					}
				}
			}else{
				String data = "";
				Map<String, Object> map = new HashMap<String, Object>();
				for(DataRow row: rows){
					if(row.getIndex()==0){
						map.put("name", row.get(column.getName()));
					}else{
						Object obj = row.get(column.getName());
						if(obj==null){
							if(data.equals("")){
								data = "0";
							}else{
								data = data + "," + "0";
							}
						}else{
							if(obj instanceof List){
								if(data.equals("")){
									data = ((List<?>)obj).get(0).toString();
								}else{
									data = data + "," + ((List<?>)obj).get(0).toString();
								}
							}else{
								String aa = obj.toString();
								if(data.equals("")){
									data = aa;
								}else{
									data = data + "," + aa;
								}
							}
						}
					}
				}
				map.put("data", "[" + data + "]");
				jsonSeries.add(map);
			}
		}
		request.setAttribute("categories", categories.toString());
		request.setAttribute("jsonSeries", jsonSeries.toString());
	}
	
	/**
	 * 显示打印预览
	 * @return
	 */
	public String showReportPrint()
	{
		String reportType = request.getParameter("reportType");
		DateUtil.format(condition);
		this.table = this.report.getRecordActivityTrendData(condition);
		this.userName = this.getLoginInfo().getUserName();
		this.reportTime = new TimeWrapper(new Date().getTime());
		if(reportType.equals("rtLine")){
			formatTable(table);
		}else if(reportType.equals("rtColumn")){
			formatTable(table);
		}
		request.setAttribute("reportType", reportType);
		return SUCCESS;
	}
	
	/**
	 * 导出报表
	 * @return
	 * @throws IOException
	 */
	public String exportReport() throws IOException
	{
		DateUtil.format(condition);
		this.table = this.report.getRecordActivityTrendData(condition);
		String reportName = "活动量趋势统计报表("+ condition.getText() + ")";
		String timeStr = new TimeWrapper().toString("yyyyMMdd_HHmmss");
        getResponse().setHeader("Content-disposition","attachment; filename="+new String(reportName.getBytes("gbk"),"iso8859-1")+".xls");//设定输出文件头
        getResponse().setContentType("application/msexcel");//定义输出类型
        getResponse().setCharacterEncoding("UTF-8");
        OutputStream outputStream = getResponse().getOutputStream();
        HSSFWorkbook workbook =  report.exportExcel(table,this.getLoginInfo().getUserName());
        ExcelUtil.writeToResponse(workbook, outputStream);
		return null;
	}
}
