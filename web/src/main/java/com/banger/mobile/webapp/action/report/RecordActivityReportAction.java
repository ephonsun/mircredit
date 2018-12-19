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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.record.ReportRecordInfo;
import com.banger.mobile.domain.model.report.RecordActivityReportBean;
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
 * @version $Id: RecordActivityReportAction.java,v 0.1 Jun 1, 2012 4:54:57 PM xuhj Exp $
 */
public class RecordActivityReportAction extends BaseAction {
	private static final long serialVersionUID = -2703552853892448025L;
	private RecordReportService report;		//报表业务类
	private DataTable table;						//报表数据
	private RecordActivityReportBean condition;			//报表查询条件
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

	public RecordActivityReportBean getCondition() {
		return condition;
	}

	public void setCondition(RecordActivityReportBean condition) {
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
	public RecordActivityReportAction()
	{
		this.condition = new RecordActivityReportBean();
		this.detail = new RecordDetailBean();
	}
	/**
	 * 跳转到活动量统计报表
	 * @return
	 */
	public String getReportPage()
	{
		DateWrapper dw = new DateWrapper(new Date().getTime());
		Calendar cb = Calendar.getInstance();
		cb.set(dw.getYear(),0,1);
		Calendar ce = Calendar.getInstance();
		ce.set(dw.getYear(),11,31);
		
		this.condition.setDateBegin(new DateWrapper(cb.getTime().getTime()));
		this.condition.setDateEnd(new DateWrapper(ce.getTime().getTime()));
		
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
		this.recordList = this.report.getRecordReportDetail(this.detail,this.getPage(), containSub);
		return SUCCESS;
	}
	
	/**
	 * 显示报表结果
	 * @return
	 */
	public String showReport()
	{
		DateUtil.format(condition);
		this.table = this.report.getRecordActivityData(condition);
		this.userName = this.getLoginInfo().getUserName();
		this.reportTime = new TimeWrapper(new Date().getTime());
		return SUCCESS;
	}
	
	/**
	 * 显示打印预览
	 * @return
	 */
	public String showReportPrint()
	{
		DateUtil.format(condition);
		this.table = this.report.getRecordActivityData(condition);
		this.userName = this.getLoginInfo().getUserName();
		this.reportTime = new TimeWrapper(new Date().getTime());
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
		this.table = this.report.getRecordActivityData(condition);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String bTime = condition.getDateBegin()!=null?sdf.format(condition.getDateBegin()):"";
		String eTime = condition.getDateEnd()!=null?sdf.format(condition.getDateEnd()):"";
		String reportName = "";
		if(!bTime.equals("")&&!eTime.equals("")){
			reportName = bTime + "至" + eTime + "活动量统计报表";
		}else if(!bTime.equals("")){
			reportName = bTime + "之后活动量统计报表";
		}else if(!eTime.equals("")){
			reportName = "截止至" + eTime + "活动量统计报表";
		}else{
			reportName = "活动量统计报表";
		}
		String timeStr = new TimeWrapper().toString("HHmmss");
        getResponse().setHeader("Content-disposition","attachment; filename="+new String(reportName.getBytes("gbk"),"iso8859-1")+".xls");//设定输出文件头
        getResponse().setContentType("application/msexcel");//定义输出类型
        getResponse().setCharacterEncoding("UTF-8");
        OutputStream outputStream = getResponse().getOutputStream();
        HSSFWorkbook workbook =  report.exportExcel(table,this.getLoginInfo().getUserName());
        ExcelUtil.writeToResponse(workbook, outputStream);
		return null;
	}
}
