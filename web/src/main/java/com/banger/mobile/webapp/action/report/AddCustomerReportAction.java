/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :新增客户报表action
 * Author     :xuhj
 * Create Date:Jun 1, 2012
 */
package com.banger.mobile.webapp.action.report;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.axis2.databinding.types.soapencoding.Array;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.collection.DataColumn;
import com.banger.mobile.domain.collection.DataRow;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.customer.CustomerBean;
import com.banger.mobile.domain.model.report.AddCustomerReportBean;
import com.banger.mobile.domain.model.report.ContactCustomerDetailBean;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.report.AddCustomerReportService;
import com.banger.mobile.util.DateWrapper;
import com.banger.mobile.util.ExcelUtil;
import com.banger.mobile.util.TimeWrapper;
import com.banger.mobile.webapp.action.BaseAction;
/**
 * @author xuhj
 * @version $Id: AddCustomerReportAction.java,v 0.1 Jun 1, 2012 4:54:57 PM xuhj Exp $
 */
public class AddCustomerReportAction extends BaseAction {
	private static final long serialVersionUID = 2901375745974977500L;
	private AddCustomerReportService report;		//报表业务类
	private DataTable table;						//报表数据
	private AddCustomerReportBean condition;		//报表查询条件
	private ContactCustomerDetailBean detail;		//报表明细条件
	private PageUtil<CustomerBean> custList;
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

	public PageUtil<CustomerBean> getCustList() {
		return custList;
	}

	public void setCustList(PageUtil<CustomerBean> custList) {
		this.custList = custList;
	}

	public ContactCustomerDetailBean getDetail() {
		return detail;
	}

	public void setDetail(ContactCustomerDetailBean detail) {
		this.detail = detail;
	}

	public Integer[] getYears() {
		return years;
	}

	public void setYears(Integer[] years) {
		this.years = years;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
	
	public AddCustomerReportBean getCondition() {
		return condition;
	}

	public void setCondition(AddCustomerReportBean condition) {
		this.condition = condition;
	}

	public DataTable getTable() {
		return table;
	}

	public void setTable(DataTable table) {
		this.table = table;
	}

	public AddCustomerReportService getReport() {
		return report;
	}

	public void setReport(AddCustomerReportService report) {
		this.report = report;
	}
	/**
	 * 构造函数
	 */
	public AddCustomerReportAction()
	{
		this.condition = new AddCustomerReportBean();
		this.detail = new ContactCustomerDetailBean();
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
	 * 新增客户报表页面
	 * @return
	 */
	public String getReportPage()
	{
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
	}
	
	/**
	 * 显示报表明细
	 * @return
	 */
	public String showReportDetial()
	{
		String containSub = request.getParameter("containSub");
		this.custList = this.report.getCustomerReportDetail(this.detail,this.getPage(), containSub);
		return SUCCESS;
	}
	
	/**
	 * 显示报表结果
	 * @return
	 */
	public String showReport()
	{
		String reportType = request.getParameter("reportType");
		this.table = this.report.getAddCustomerReportData(condition);
		this.userName = this.getLoginInfo().getUserName();
		this.reportTime = new TimeWrapper(new Date().getTime());
		
		if(reportType.equals("rtLine")){
			formatTable(table);
		}else if(reportType.equals("rtColumn")){
			formatTable(table);
		}else if(reportType.equals("rtPercent")){
			
		}
		return reportType;
	}
	
	/**
	 * 格式化table数据
	 * @param table
	 */
	private void formatTable(DataTable table){
		ArrayList<String> categories = new ArrayList<String>();
		JSONArray jsonSeries = new JSONArray();
		
		for(DataRow row: table.getRows()){
			DataColumn[] cols = table.getColumns();
			if(row.getIndex()==0){
				for(DataColumn column : cols){
					if(!column.getName().equals("name")
							&&!column.getName().equals("deptName")){
						categories.add("'"+table.getRow(0).get(column.getName()).toString()+"'");
					}
				}
			}else{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", row.get("name"));
				String data = "";
				for(DataColumn column : cols){
					if(!column.getName().equals("name")
							&&!column.getName().equals("deptName")){
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
		this.table = this.report.getAddCustomerReportData(condition);
		this.userName = this.getLoginInfo().getUserName();
		this.reportTime = new TimeWrapper(new Date().getTime());
		if(reportType.equals("rtLine")){
			formatTable(table);
		}else if(reportType.equals("rtColumn")){
			formatTable(table);
		}else if(reportType.equals("rtPercent")){
			
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
		this.table = this.report.getAddCustomerReportData(condition);
		String timeStr = new TimeWrapper().toString("yyyyMMdd_HHmmss");
        getResponse().setHeader("Content-disposition","attachment; filename="+new String("新增客户统计报表".getBytes("gbk"),"iso8859-1")+".xls");//设定输出文件头
        getResponse().setContentType("application/msexcel");//定义输出类型
        getResponse().setCharacterEncoding("UTF-8");
        OutputStream outputStream = getResponse().getOutputStream();
        HSSFWorkbook workbook =  report.exportExcel(table,this.getLoginInfo().getUserName());
        ExcelUtil.writeToResponse(workbook, outputStream);
		return null;
	}
}
