package com.banger.mobile.webapp.action.report;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.banger.mobile.PageUtil;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.customer.CustomerBean;
import com.banger.mobile.domain.model.report.ContactCustomerDetailBean;
import com.banger.mobile.domain.model.report.ContactCustomerReportBean;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.report.ContactCustomerReportService;
import com.banger.mobile.facade.system.CdSystemService;
import com.banger.mobile.util.DateUtil;
import com.banger.mobile.util.DateWrapper;
import com.banger.mobile.util.ExcelUtil;
import com.banger.mobile.util.TimeWrapper;
import com.banger.mobile.webapp.action.BaseAction;

public class ContactCustomerReportAction extends BaseAction {
	private static final long serialVersionUID = -8644600953011829129L;
	private ContactCustomerReportService report;	//报表业务类
	private DataTable table;						//报表数据
	private ContactCustomerReportBean condition;	//报表查询条件
    private CdSystemService cd;						//代码表
    private ContactCustomerDetailBean detail;		//报表明细条件
	private String userName;						//制表人
	private Integer userId;							//用户Id
	private Date reportTime;						//制表时间
	private PageUtil<CustomerBean> custList;
	private Integer[] years;
    private DeptFacadeService dept;		//机构
    private Boolean hasBelongTo;		//有下属权限
    
    
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

	public CdSystemService getCd() {
		return cd;
	}

	public void setCd(CdSystemService cd) {
		this.cd = cd;
	}

	public ContactCustomerReportService getReport() {
		return report;
	}

	public void setReport(ContactCustomerReportService report) {
		this.report = report;
	}

	public ContactCustomerReportBean getCondition() {
		return condition;
	}

	public void setCondition(ContactCustomerReportBean condition) {
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
	public ContactCustomerReportAction()
	{
		this.condition = new ContactCustomerReportBean();
	}
	/**
	 * 客户联系跟进报表页面
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
		request.setAttribute("typeArray", getTypeArray());
		return SUCCESS;
	}
	
	/**
	 * 显示报表结果
	 * @return
	 */
	public String showReport()
	{
		DateUtil.format(condition);
		this.table = this.report.getContactCustomerReportData(condition);
		this.userName = this.getLoginInfo().getUserName();
		this.reportTime = new TimeWrapper(new Date().getTime());
		return SUCCESS;
	}
	/**
	 * 客户类型
	 * @return
	 */
	private String getTypeArray(){
		String arr = "";
		List<CrmCustomerType> typeList = cd.getCusTypes();
		for(CrmCustomerType type: typeList){
			if(arr.equals("")){
				arr = "'" + type.getCustomerTypeName() + "'";
			}else{
				arr = arr + "," + "'" + type.getCustomerTypeName() + "'";
			}
		}
		if(arr.equals("")){
			arr = "['空']";
		}else{
			arr = "[" + arr + ",'空']";
		}
		return arr;
	}
	/**
	 * 报表明细
	 * @return
	 */
	public String showReportDetial(){
		String containSub = request.getParameter("containSub");
		formatDetailBean(this.detail);
		this.custList = this.report.getContactCustomerReportDetail(this.detail,this.getPage(),containSub);
		return SUCCESS;
	}
	
	private void formatDetailBean(ContactCustomerDetailBean detail){
		List<CrmCustomerType> typeList = cd.getCusTypes();
		for(CrmCustomerType type: typeList){
			if(type.getCustomerTypeName().equals(this.detail.getType())){
				this.detail.setCustomerType(String.valueOf(type.getCustomerTypeId()));
				break;
			}
		}
		String flag = detail.getConFlag();
		if(flag.equals("有联系")){
			detail.setContactFlag("1");
		}else if(flag.equals("未联系")){
			detail.setContactFlag("0");
		}else{
			detail.setContactFlag("");
		}
	}
	/**
	 * 显示打印预览
	 * @return
	 */
	public String showReportPrint()
	{
		DateUtil.format(condition);
		this.table = this.report.getContactCustomerReportData(condition);
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
		this.table = this.report.getContactCustomerReportData(condition);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String bTime = condition.getDateBegin()!=null?sdf.format(condition.getDateBegin()):"";
		String eTime = condition.getDateEnd()!=null?sdf.format(condition.getDateEnd()):"";
		String reportName = "";
		if(!bTime.equals("")&&!eTime.equals("")){
			reportName = bTime + "至" + eTime + "客户联系跟进统计表";
		}else if(!bTime.equals("")){
			reportName = bTime + "之后客户联系跟进统计表";
		}else if(!eTime.equals("")){
			reportName = "截止至" + eTime + "客户联系跟进统计表";
		}else{
			reportName = "客户联系跟进统计表";
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
