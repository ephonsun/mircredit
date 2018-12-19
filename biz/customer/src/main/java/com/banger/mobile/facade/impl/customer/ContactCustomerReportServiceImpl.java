/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :客户联系跟进统计报表
 * Author     :zsw
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.impl.customer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.Region;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.report.CustomerReportDao;
import com.banger.mobile.domain.collection.DataColumn;
import com.banger.mobile.domain.collection.DataRow;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.customer.CustomerBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.report.ContactCustomerDetailBean;
import com.banger.mobile.domain.model.report.ContactCustomerReportBean;
import com.banger.mobile.domain.model.system.CrmCustomerType;
import com.banger.mobile.domain.model.user.SysUserBean;
import com.banger.mobile.facade.BaseReportServiceImpl;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.report.ContactCustomerReportService;
import com.banger.mobile.facade.system.CdSystemService;
import com.banger.mobile.util.TypeUtil;

import edu.emory.mathcs.backport.java.util.Arrays;

public class ContactCustomerReportServiceImpl extends BaseReportServiceImpl implements ContactCustomerReportService {
	private CustomerReportDao cusRepDao;
	private DeptFacadeService		  deptFacadeService;	  //机构Service
	private CdSystemService 		  cd;				//代码表
	
	
	public CdSystemService getCd() {
		return cd;
	}

	public void setCd(CdSystemService cd) {
		this.cd = cd;
	}

	public DeptFacadeService getDeptFacadeService() {
		return deptFacadeService;
	}

	public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
		this.deptFacadeService = deptFacadeService;
	}

	public CustomerReportDao getCusRepDao() {
		return cusRepDao;
	}

	public void setCusRepDao(CustomerReportDao cusRepDao) {
		this.cusRepDao = cusRepDao;
	}
	
	/**
	 * 组装新的DeptIds和选中的机构list
	 * @param condition
	 * @param selectDepts
	 * @return 下属机构集合
	 */
	private Map<String, SysDept> setConditionByContainSub(ContactCustomerReportBean condition, List<SysDept> selectDepts){
		Map<String, SysDept> subDepts = new HashMap<String, SysDept>();
		String[] depts = condition.getDeptIds().split(",");
		List<SysDept> deptList = deptFacadeService.getContainDeptListByDeptIds(condition.getDeptIds());
		String newDeptIds = "";
		for(SysDept dept: deptList){
			if(newDeptIds.equals("")){
				newDeptIds = dept.getDeptId().toString();
			}else{
				newDeptIds = newDeptIds + "," + dept.getDeptId().toString();
			}
			boolean isNeed = true;
			for(String deptId: depts){
				if(dept.getDeptId().toString().equals(deptId)){
					isNeed = false;
					break;
				}
			}
			if(isNeed){
				subDepts.put(dept.getDeptId().toString(), dept);
			}else{
				selectDepts.add(dept);
			}
		}
		condition.setDeptIds(newDeptIds);
		return subDepts;
	}
	
	/**
	 * 递归查找pid
	 * @param subDepts
	 * @param deptId
	 * @return
	 */
	private String getTopParentId(Map<String, SysDept> subDepts,String deptId){
		SysDept dept = subDepts.get(deptId);
		if(subDepts.containsKey(dept.getDeptParentId().toString())){
			return getTopParentId(subDepts,dept.getDeptParentId().toString());
		}else{
			return dept.getDeptParentId().toString();
		}
	}
	
	/**
	 * 给界面上选中的机构添加一条默认数据
	 * @param condition
	 * @param table
	 * @param selectDepts
	 */
	private void repairTableDeptData(ContactCustomerReportBean condition, DataTable table,List<SysDept> selectDepts)
	{
		for(SysDept dept: selectDepts){
			DataRow newRow = table.newRow();
			newRow.set("name", dept.getDeptName());
			newRow.set("user", dept.getDeptId().toString());
			newRow.set("count",0);
			newRow.set("type","Y");
			newRow.set("typeName",getFirstTypeName(condition));
		}
	}
	
	/**
	 * 给界面上选择的人员添加一条默认数据
	 * @param condition
	 * @param table
	 * @param selectUsers
	 */
	private void repairTableUserData(ContactCustomerReportBean condition, DataTable table,List<SysUserBean> selectUsers){
		for(SysUserBean user: selectUsers){
			DataRow newRow = table.newRow();
			newRow.set("name", user.getUserName());
			newRow.set("user", user.getUserId().toString());
			newRow.set("deptName", user.getDeptName());
			newRow.set("count",0);
			newRow.set("type","Y");
			newRow.set("typeName",getFirstTypeName(condition));
		}
	}
	
	/**
	 * 获得第一个选中的客户类型名
	 * @param condition
	 * @return
	 */
	private String getFirstTypeName(ContactCustomerReportBean condition){
		String[] tName = condition.getCustomerTypeName().split(",");
		return tName[0];
	}
	
	/**
	 * 获得机构名
	 * @param parentId
	 * @param selectDepts
	 * @return
	 */
	private String getDeptName(String parentId, List<SysDept> selectDepts){
		String deptname= "";
		for(SysDept dept: selectDepts){
			if(dept.getDeptId().toString().equals(parentId)){
				deptname = dept.getDeptName();
				break;
			}
		}
		return deptname;
	}
	
	/**
	 * 组装table包含下属机构
	 * @param conditio
	 * @param table
	 * @param subDepts
	 * @param selectDepts
	 * @return
	 */
	private DataTable setupTableContainSub(ContactCustomerReportBean conditio, DataTable table, 
			Map<String, SysDept> subDepts, List<SysDept> selectDepts){
		DataTable newTable = new DataTable();
		newTable.addColumn("type");
		newTable.addColumn("count");
		newTable.addColumn("name");
		newTable.addColumn("user");
		newTable.addColumn("typeName");
		Map<String,DataRow> map = new HashMap<String,DataRow>();
		DataRow[] rows = table.getRows();
		for(DataRow row : rows){
			String ym = (String)row.get("type");
			String tn = (String)row.get("typeName");
			String deptId = row.get("user").toString();
			if(!subDepts.containsKey(deptId)){
				String key = ym+"_"+deptId+"_"+tn;
				if(map.containsKey(key)){
					DataRow oldRow = map.get(key);
					Integer subCount = (Integer)row.get("count");
					Integer parentCount = (Integer)oldRow.get("count");
					oldRow.set("count",parentCount+subCount);
				}else{
					DataRow newRow = newTable.newRow();
					newRow.set("type", row.get("type"));
					newRow.set("count", row.get("count"));
					newRow.set("name", row.get("name"));
					newRow.set("user", row.get("user"));
					newRow.set("typeName", row.get("typeName"));
					map.put(key,newRow);
				}
			}else{
				String parentId = getTopParentId(subDepts,deptId);
				String key = ym+"_"+parentId+"_"+tn;
				if(map.containsKey(key)){
					DataRow newRow = map.get(key);
					Integer subCount = (Integer)row.get("count");
					Integer parentCount = (Integer)newRow.get("count");
					newRow.set("count",parentCount+subCount);
				}else{
					DataRow newRow = newTable.newRow();
					newRow.set("type", row.get("type"));
					newRow.set("count", row.get("count"));
					newRow.set("name", getDeptName(parentId, selectDepts));
					newRow.set("user", parentId);
					newRow.set("typeName", row.get("typeName"));
					map.put(key,newRow);
				}
			}
		}
		return newTable;
	}
	
	/**
	 * 格式化条件
	 * @param condition
	 */
	private void formatCondition(ContactCustomerReportBean condition){
		String nm = condition.getCustomerTypeName();
		List<CrmCustomerType> typeList = cd.getCusTypes();
		if(nm==null||nm.equals("")){
			for(CrmCustomerType type: typeList){
				if(nm==null||nm.equals("")){
					nm = type.getCustomerTypeName();
				}else{
					nm = nm + "," + type.getCustomerTypeName();
				}
			}
			if(nm.equals("")){
				nm  = "空";
			}else{
				nm  = nm + ",空";
			}
		}
		condition.setCustomerTypeName(nm);
		String[] typeNames = nm.split(",");
		String ids = "";
		for(String name: typeNames){
			for(CrmCustomerType type: typeList){
				if(type.getCustomerTypeName().equals(name)){
					if(ids.equals("")){
						ids = String.valueOf(type.getCustomerTypeId());
					}else{
						ids = ids + "," + String.valueOf(type.getCustomerTypeId());
					}
					break;
				}
			}
		}
		condition.setCustomerType(ids);
	}
	
	/**
	 * 获得联系客户报表数据
	 */
	public DataTable getContactCustomerReportData(
			ContactCustomerReportBean condition) {
		formatCondition(condition);
		DataTable table;
		if(condition.getBelongTo().equalsIgnoreCase("dept"))
		{
			if(condition.getContainSub().equals(1)){
				List<SysDept> selectDepts = new ArrayList<SysDept>();
				Map<String, SysDept> subDepts = setConditionByContainSub(condition, selectDepts);
				table = this.cusRepDao.getContactCustomerDataByDept(condition);
				repairTableDeptData(condition, table, selectDepts);
				table = setupTableContainSub(condition, table, subDepts, selectDepts);
			}else{
				table = this.cusRepDao.getContactCustomerDataByDept(condition);
				//根据deptids获取实体集合
				List<SysDept> selectDepts = deptFacadeService.getDeptsByDeptIds(condition.getDeptIds());
				repairTableDeptData(condition, table, selectDepts);
			}
		}
		else
		{
			table = this.cusRepDao.getContactCustomerDataByUser(condition);
			//根据userids获取实体集合
			List<SysUserBean> selectDepts = deptFacadeService.getUsersByUserIds(condition.getUserIds());
			repairTableUserData(condition, table, selectDepts);
		}
		return this.setupReport(table, condition);
	}
	
	/**
	 * 排序类
	 * @author xuhj
	 *
	 */
	class ContactCustomerReportSort implements Comparator<DataRow>
	{
		public int compare(DataRow row1, DataRow row2) {
			String st1 = "";
			String str2 ="";
			
			if(row1.getIndex()==0||row1.getIndex()==1){
				return -1;
			}
			if(row2.getIndex()==0||row2.getIndex()==1){
				return -1;
			}
			
			if(row1.get("deptName") == null &&row2.get("deptName") == null){
				st1 = row1.get("name").toString();
				str2 =row2.get("name").toString();
				return st1.compareTo(str2);
			}else{
				st1 = row1.get("deptName").toString();
				str2 =row2.get("deptName").toString();
				return st1.compareTo(str2);
			}
		}
		
	}
	
	/**
	 * 设置table的列
	 * @param newTable
	 * @param condition
	 */
	private void setTableColumns(DataTable newTable, ContactCustomerReportBean condition){
		boolean isDept = condition.getBelongTo().equalsIgnoreCase("dept");
		if(!isDept)newTable.addColumn("deptName");
		newTable.addColumn("name");
		DataRow head = newTable.newRow();
		if(!isDept)head.set("deptName","机构");
		head.set("name", "名称");
		//设置table列头 和  第1行表头
		String[] typeNames = condition.getCustomerTypeName().split(",");
		for(String name: typeNames){
			newTable.addColumn("total"+name);
			newTable.addColumn("countY"+name);
			newTable.addColumn("rateY"+name);
			newTable.addColumn("countN"+name);
			newTable.addColumn("rateN"+name);
			
			head.set("total"+name,name);
			head.set("countY"+name,name);
			head.set("rateY"+name,name);
			head.set("countN"+name,name);
			head.set("rateN"+name,name);
		}
	}
	
	/**
	 * 设置table的行
	 * @param newTable
	 * @param oldTable
	 * @param condition
	 */
	private void setTableRows(DataTable newTable, DataTable oldTable, ContactCustomerReportBean condition){
		boolean isDept = condition.getBelongTo().equalsIgnoreCase("dept");
		//设置table第二行表头
		DataRow newRow = newTable.newRow();
		if(!isDept)newRow.set("deptName","机构");
		newRow.set("name", "名称");
		String[] typeNames = condition.getCustomerTypeName().split(",");
		for(String name: typeNames){
			newRow.set("total"+name,"总数");
			newRow.set("countY"+name,"有联系");
			newRow.set("rateY"+name,"有联系占比");
			newRow.set("countN"+name,"未联系");
			newRow.set("rateN"+name,"未联系占比");
		}
		//添加数据
		DataTable backTable = setOldTableRows(oldTable, isDept);
		DataRow[] rows = backTable.getRows();
		Map<String,DataRow> map = new HashMap<String,DataRow>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String bTime = condition.getDateBegin()!=null?sdf.format(condition.getDateBegin()):"";
		String eTime = condition.getDateEnd()!=null?sdf.format(condition.getDateEnd()):"";
		String title = "";
		if(!bTime.equals("")&&!eTime.equals("")){
			title = bTime + "至" + eTime+"客户联系跟进统计表—";
		}else if(!bTime.equals("")){
			title = bTime + "之后"+"客户联系跟进统计表—";
		}else if(!eTime.equals("")){
			title = "截止至" + eTime+"客户联系跟进统计表—";
		}else{
			title = "客户联系跟进统计表—";
		}
		
		for(DataRow row : rows){
			String u = row.get("user").toString();
			if(!map.containsKey(u)){
				String uName = row.get("name").toString();
				DataRow dataRow = newTable.newRow();
				if(!isDept)dataRow.set("deptName",row.get("deptName").toString());
				dataRow.set("name",uName);
				String name = row.get("typeName").toString();
				dataRow.set("total"+name,row.get("total")!=null?row.get("total").toString():"0");
				String countY = row.get("countY")!=null?row.get("countY").toString():"0";
				dataRow.set("countY"+name,new Object[]{countY,u,bTime,eTime,uName,title +"“"+uName+"”（已联系）"});
				dataRow.set("rateY"+name,row.get("rateY")!=null?row.get("rateY").toString():"0%");
				String countN = row.get("countN")!=null?row.get("countN").toString():"0";
				dataRow.set("countN"+name,new Object[]{countN,u,bTime,eTime,uName,title +"“"+uName+"”（未联系）"});
				dataRow.set("rateN"+name,row.get("rateN")!=null?row.get("rateN").toString():"0%");
				map.put(u,dataRow);
			}else{
				DataRow uRow = map.get(u);
				String name = row.get("typeName").toString();
				String uName = uRow.get("name").toString();
				uRow.set("total"+name,row.get("total")!=null?row.get("total").toString():"0");
				String countY = row.get("countY")!=null?row.get("countY").toString():"0";
				uRow.set("countY"+name,new Object[]{countY,u,bTime,eTime,uName,title + "“"+uName+"”（已联系）"});
				uRow.set("rateY"+name,row.get("rateY")!=null?row.get("rateY").toString():"0%");
				String countN = row.get("countN")!=null?row.get("countN").toString():"0";
				uRow.set("countN"+name,new Object[]{countN,u,bTime,eTime,uName,title + "“"+uName+"”（未联系）"});
				uRow.set("rateN"+name,row.get("rateN")!=null?row.get("rateN").toString():"0%");
			}
		}
		repairCellArray(newTable);
	}
	/**
	 * 设置旧的table行
	 * @param oldTable
	 * @param isDept
	 * @return 新的table
	 */
	private DataTable setOldTableRows(DataTable oldTable, boolean isDept){
		DataTable newTable = new DataTable();
		DataRow[] rows = oldTable.getRows();
		Map<String,DataRow> map = new HashMap<String,DataRow>();
		for(DataRow row : rows)
		{
			String u = row.get("user").toString();
			String name = row.get("name").toString();
			String type = row.get("type").toString();
			String typeName = row.get("typeName")!=null?row.get("typeName").toString():"空";
			Integer count = Integer.parseInt(row.get("count").toString());
			if(!map.containsKey(u+typeName))
			{
				DataRow newRow = newTable.newRow();
				if(!isDept)newRow.set("deptName",row.get("deptName").toString());
				newRow.set("name",name);
				newRow.set("user",row.get("user").toString());
				newRow.set("typeName",typeName);
				map.put(u + typeName,newRow);
			}
			DataRow uRow = map.get(u + typeName);
			
			int total = uRow.get("total")!=null?Integer.parseInt(uRow.get("total").toString()):0;
			uRow.set("total",total+count);
			if(type.equalsIgnoreCase("Y"))
			{
				Integer ucountY = Integer.parseInt(uRow.get("countY")!=null?uRow.get("countY").toString():"0");
				uRow.set("countY",ucountY.equals(0)?count:count+ucountY);
			}
			else
			{
				Integer ucountN = Integer.parseInt(uRow.get("countN")!=null?uRow.get("countN").toString():"0");
				uRow.set("countN",ucountN.equals(0)?count:count+ucountN);
			}
		}
		
		for(String key : map.keySet())
		{
			DataRow uRow = map.get(key);
			int total = uRow.get("total")!=null?Integer.parseInt(uRow.get("total").toString()):0;
			if(total>0)
			{
				int countY = uRow.get("countY")!=null?Integer.parseInt(uRow.get("countY").toString()):0;
				if(countY>0){
					float rate = ((float)countY)/((float)total);
					uRow.set("rateY",formatRate(rate));
				}
				int countN = uRow.get("countN")!=null?Integer.parseInt(uRow.get("countN").toString()):0;
				if(countN>0){
					float rate = ((float)countN)/((float)total);
					uRow.set("rateN",formatRate(rate));
				}
			}
		}
		return newTable;
	}
	
	private DataTable setupReport(DataTable table, ContactCustomerReportBean condition)
	{
		DataTable newTable = new DataTable();
		setTableColumns(newTable, condition);
		setTableRows(newTable, table, condition);
		newTable.sort(new ContactCustomerReportSort());
		return newTable;
	}
	
	/**
	 * 四舍五入
	 * @param rate
	 * @return
	 */
	private String formatRate(float rate)
	{
		float r = rate*100;
		double b = Math.round(r*100)/100.00 ;
		String str = new Double(b).toString();
		int n = new Integer(new Double(b).intValue()).toString().length();
		if(Integer.parseInt(str.substring(n+1))>0)return str+"%";
		else return String.valueOf(new Double(b).intValue())+"%";
	}
	
	/**
	 * 把Object[] 转化为 List,velocity不能直接用 get(0)或 [0] 得到数组的值
	 * @param t
	 */
	private void repairCellArray(DataTable t)
	{
		DataColumn[] cols = t.getColumns();
		for(DataRow row : t.getRows())
		{
			for(DataColumn col : cols)
			{
				Object cellVal = row.get(col.getName());
				if(cellVal!=null && cellVal.getClass().isArray())
				{
					Object[] objs = (Object[])cellVal;
					row.set(col.getName(),Arrays.asList(objs));
				}
			}
		}
	}
	
	/**
	 * 客户明细
	 * @param condition
	 * @param page
	 * @return
	 */
	public PageUtil<CustomerBean> getContactCustomerReportDetail(ContactCustomerDetailBean condition,Page page, String containSub){
		if(containSub!=null&&containSub.equals("1")){
			String depts = deptFacadeService.getInChargeDeptIdsByDeptId(Integer.parseInt(condition.getDeptId()));
			condition.setDeptId(depts);
		}
		return cusRepDao.getContactCustomerReportDetail(condition, page);
	}
	
	@Override
	public void setDefaultValue(DataTable table, int r, int c, HSSFCell eCell){
		DataColumn col = table.getColumn(c);
		if(col.getName().indexOf("rate")>-1){
			eCell.setCellValue("0%");
		}else{
			eCell.setCellValue("0");
		}
	}
	
	/**
	 * 导出excel之前
	 */
	@Override
	public void beforeExportExcel(DataTable table, HSSFSheet sheet){
		try{
			int bCol = 0;
			int eCol = 0;
			int eRow = 0;
			for(DataColumn col: table.getColumns()){
				if(!TypeUtil.isExistRowOrCol(table,0,col.getIndex())){
					eCol = (bCol+TypeUtil.getColSpan(table,0,col.getIndex(),0));
					eRow = TypeUtil.getRowSpan(table,0,col.getIndex(),0);
					
					Region reg = new Region(0,(short)bCol,0,(short)eCol);
			        sheet.addMergedRegion(reg);
			        
			        Region reg1 = new Region(0,(short)col.getIndex(),eRow,(short)col.getIndex());
			        sheet.addMergedRegion(reg1);
			        
			        bCol = eCol + 1;
				}
			}
        } catch(Exception e){}
	}
}
