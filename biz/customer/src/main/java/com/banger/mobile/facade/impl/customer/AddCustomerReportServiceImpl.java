/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :新增客户统计报表
 * Author     :zsw
 * Version    :1.0
 * Create Date:May 3, 2012
 */
package com.banger.mobile.facade.impl.customer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.report.CustomerReportDao;
import com.banger.mobile.domain.collection.DataColumn;
import com.banger.mobile.domain.collection.DataRow;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.customer.CustomerBean;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.report.AddCustomerReportBean;
import com.banger.mobile.domain.model.report.ContactCustomerDetailBean;
import com.banger.mobile.domain.model.user.SysUserBean;
import com.banger.mobile.facade.BaseReportServiceImpl;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.report.AddCustomerReportService;
import com.banger.mobile.util.StringUtil;

import edu.emory.mathcs.backport.java.util.Arrays;

public class AddCustomerReportServiceImpl extends BaseReportServiceImpl implements AddCustomerReportService {
	private CustomerReportDao cusRepDao;
	private DeptFacadeService		  deptFacadeService;	  //机构Service
	
	public CustomerReportDao getCusRepDao() {
		return cusRepDao;
	}
	public void setCusRepDao(CustomerReportDao cusRepDao) {
		this.cusRepDao = cusRepDao;
	}
	public DeptFacadeService getDeptFacadeService() {
		return deptFacadeService;
	}
	public void setDeptFacadeService(DeptFacadeService deptFacadeService) {
		this.deptFacadeService = deptFacadeService;
	}
	/**
	 * 组装新的DeptIds和选中的机构list
	 * @param condition
	 * @param selectDepts
	 * @return 下属机构集合
	 */
	private Map<String, SysDept> setConditionByContainSub(AddCustomerReportBean condition, List<SysDept> selectDepts){
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
	private void repairTableDeptData(AddCustomerReportBean condition, DataTable table,List<SysDept> selectDepts)
	{
		for(SysDept dept: selectDepts){
			DataRow newRow = table.newRow();
			newRow.set("name", dept.getDeptName());
			newRow.set("user", dept.getDeptId().toString());
			newRow.set("count",0);
			newRow.set("date",condition.getDateBegin());
		}
	}
	
	/**
	 * 给界面上选择的人员添加一条默认数据
	 * @param condition
	 * @param table
	 * @param selectUsers
	 */
	private void repairTableUserData(AddCustomerReportBean condition, DataTable table,List<SysUserBean> selectUsers){
		for(SysUserBean user: selectUsers){
			DataRow newRow = table.newRow();
			newRow.set("name", user.getUserName());
			newRow.set("user", user.getUserId().toString());
			newRow.set("deptName", user.getDeptName());
			newRow.set("count",0);
			newRow.set("date",condition.getDateBegin());
		}
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
	 * @param condition
	 * @param table
	 * @param subDepts
	 * @param selectDepts
	 * @return
	 */
	private DataTable setupTableContainSub(AddCustomerReportBean condition, DataTable table, 
			Map<String, SysDept> subDepts, List<SysDept> selectDepts){
		DataTable newTable = new DataTable();
		newTable.addColumn("date");
		newTable.addColumn("count");
		newTable.addColumn("name");
		newTable.addColumn("user");
		Map<String,DataRow> map = new HashMap<String,DataRow>();
		for(DataRow row : table.getRows()){
			String ym = (String)row.get("date");
			String deptId = row.get("user").toString();
			if(!subDepts.containsKey(deptId)){
				String key = ym+"_"+deptId;
				if(map.containsKey(key)){
					DataRow oldRow = map.get(key);
					Integer subCount = (Integer)row.get("count");
					Integer parentCount = (Integer)oldRow.get("count");
					oldRow.set("count",parentCount+subCount);
				}else{
					DataRow newRow = newTable.newRow();
					newRow.set("date", row.get("date"));
					newRow.set("count", row.get("count"));
					newRow.set("name", row.get("name"));
					newRow.set("user", row.get("user"));
					map.put(key,newRow);
				}
			}else{
				String parentId = getTopParentId(subDepts,deptId);
				String key = ym+"_"+parentId;
				if(map.containsKey(key)){
					DataRow newRow = map.get(key);
					Integer subCount = (Integer)row.get("count");
					Integer parentCount = (Integer)newRow.get("count");
					newRow.set("count",parentCount+subCount);
				}else{
					DataRow newRow = newTable.newRow();
					newRow.set("date", row.get("date"));
					newRow.set("count", row.get("count"));
					newRow.set("name", getDeptName(parentId, selectDepts));
					newRow.set("user", parentId);
					map.put(key,newRow);
				}
			}
		}
		return newTable;
	}
	
	/**
	 * 得到报表统计数据
	 * @param condition
	 * @return
	 */
	public DataTable getAddCustomerReportData(AddCustomerReportBean condition)
	{
		DataTable table;
		if(condition.getBelongTo().equalsIgnoreCase("dept"))
		{
			if(condition.getContainSub().equals(1)){
				List<SysDept> selectDepts = new ArrayList<SysDept>();
				Map<String, SysDept> subDepts = setConditionByContainSub(condition, selectDepts);
				table = this.cusRepDao.getAddCustomerReportDataByDept(condition);
				repairTableDeptData(condition, table, selectDepts);
				table = setupTableContainSub(condition, table, subDepts, selectDepts);
			}else{
				table = this.cusRepDao.getAddCustomerReportDataByDept(condition);
				//根据deptids获取实体集合
				List<SysDept> selectUsers = deptFacadeService.getDeptsByDeptIds(condition.getDeptIds());
				repairTableDeptData(condition, table, selectUsers);
			}
		}
		else
		{
			table = this.cusRepDao.getAddCustomerReportDataByUser(condition);
			
			//根据userids获取实体集合
			List<SysUserBean> selectUsers = deptFacadeService.getUsersByUserIds(condition.getUserIds());
			repairTableUserData(condition, table, selectUsers);
		}
		if(condition.getDateType().equalsIgnoreCase("year"))
		{
			return this.setupYear(condition, table);
		}
		else if(condition.getDateType().equalsIgnoreCase("quarter"))
		{
			return this.setupQuarter(condition, table);
		}
		else
		{
			return this.setupMonth(condition, table);
		}
	}
	/**
	 * 排序类
	 * @author xuhj
	 *
	 */
	class AddCustomerReportSort implements Comparator<DataRow>
	{
		public int compare(DataRow row1, DataRow row2) {
			String st1 = "";
			String str2 ="";
			if(row1.getIndex()==0){
				return -1;
			}
			if(row2.getIndex()==0){
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
	 * 得到客户明细
	 * @param condition
	 * @param page
	 * @return
	 */
	public PageUtil<CustomerBean> getCustomerReportDetail(ContactCustomerDetailBean condition,Page page, String containSub)
	{
		if(containSub!=null&&containSub.equals("1")){
			String depts = deptFacadeService.getInChargeDeptIdsByDeptId(Integer.parseInt(condition.getDeptId()));
			condition.setDeptId(depts);
		}
		return cusRepDao.getCustomerReportDetail(condition, page);
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
	 * 把月份聚合成年
	 * @param table
	 * @return
	 */
	private DataTable setupYear(AddCustomerReportBean condition,DataTable table)
	{
		int yearBegin = condition.getYearBegin();
		int yearEnd = condition.getYearEnd();
		boolean isDept = condition.getBelongTo().equalsIgnoreCase("dept");
		DataTable t = new DataTable();
		if(!isDept)t.addColumn("deptName");
		t.addColumn("name");
		for(int y=yearBegin;y<yearEnd+1;y++)t.addColumn(String.valueOf(y));
		DataRow head = t.newRow();
		if(!isDept){
			head.set("deptName","机构");
		}
		head.set("name","名称");
		for(int y=yearBegin;y<yearEnd+1;y++)
		{
			head.set(String.valueOf(y),y+"年");
		}
		
		DataRow[] rows = table.getRows();
		Map<String,DataRow> map = new HashMap<String,DataRow>();
		for(DataRow row : rows)
		{
			String u = row.get("user").toString();
			String name = row.get("name").toString();
			String ym = row.get("date").toString();
			Integer count = Integer.parseInt(row.get("count").toString());
			String year = ym.substring(0,4);
			if(!map.containsKey(u))
			{
				DataRow newRow = t.newRow();
				newRow.set("name",name);
				if(!isDept)newRow.set("deptName",row.get("deptName").toString());
				map.put(u,newRow);
			}
			DataRow uRow = map.get(u);
			int total = 0;
			if(uRow.get(year)!=null)
			{
				Object[] objs = (Object[])uRow.get(year);
				total = Integer.parseInt(objs[0].toString());
			}
			total+=count;
			uRow.set(year,new Object[]{total,u,year+"01",year+"12",name,year+"年"});
		}
		this.repairCellArray(t);
		t.sort(new AddCustomerReportSort());
		return t;
	}
	
	/**
	 * 把月份聚合季度
	 * @return
	 */
	private DataTable setupQuarter(AddCustomerReportBean condition,DataTable table)
	{
		int yearBegin = condition.getYearBegin();
		int yearEnd = condition.getYearEnd();
		int quarterBegin = condition.getQuarterBegin();
		int quarterEnd = condition.getQuarterEnd();
		boolean isDept = condition.getBelongTo().equalsIgnoreCase("dept");
		DataTable t = new DataTable();
		if(!isDept)t.addColumn("deptName");
		t.addColumn("name");
		for(int y=yearBegin;y<yearEnd+1;y++)
		{
			if(yearBegin==yearEnd)
			{
				for(int q =quarterBegin;q<quarterEnd+1;q++)
				{
					t.addColumn(String.valueOf(y)+String.valueOf(q));
				}
			}
			else
			{
				if(y==yearBegin)
				{
					for(int q =quarterBegin;q<5;q++)
					{
						t.addColumn(String.valueOf(y)+String.valueOf(q));
					}
				}
				else if(y==yearEnd)
				{
					for(int q =1;q<quarterEnd+1;q++)
					{
						t.addColumn(String.valueOf(y)+String.valueOf(q));
					}
				}
				else
				{
					for(int q =1;q<5;q++)
					{
						t.addColumn(String.valueOf(y)+String.valueOf(q));
					}
				}
			}
		}
		
		DataRow head = t.newRow();
		if(!isDept){
			head.set("deptName","机构");
		}
		head.set("name","名称");
		
		for(int y=yearBegin;y<yearEnd+1;y++)
		{
			if(yearBegin==yearEnd)
			{
				for(int q =quarterBegin;q<quarterEnd+1;q++)
				{
					head.set(String.valueOf(y)+String.valueOf(q),y+"年"+" 第"+q+"季度");
				}
			}
			else
			{
				if(y==yearBegin)
				{
					for(int q =quarterBegin;q<5;q++)
					{
						head.set(String.valueOf(y)+String.valueOf(q),y+"年"+" 第"+q+"季度");
					}
				}
				else if(y==yearEnd)
				{
					for(int q =1;q<quarterEnd+1;q++)
					{
						head.set(String.valueOf(y)+String.valueOf(q),y+"年"+" 第"+q+"季度");
					}
				}
				else
				{
					for(int q =1;q<5;q++)
					{
						head.set(String.valueOf(y)+String.valueOf(q),y+"年"+" 第"+q+"季度");
					}
				}
			}
		}
		
		DataRow[] rows = table.getRows();
		Map<String,DataRow> map = new HashMap<String,DataRow>();
		for(DataRow row : rows)
		{
			String u = row.get("user").toString();
			String name = row.get("name").toString();
			String ym = row.get("date").toString();
			Integer count = Integer.parseInt(row.get("count").toString());
			String y = ym.substring(0,4);
			Integer m = Integer.parseInt(ym.substring(4,6));
			Integer q = (m%3==0)?m/3:m/3+1;
			if(!map.containsKey(u))
			{
				DataRow newRow = t.newRow();
				newRow.set("name",name);
				if(!isDept)newRow.set("deptName",row.get("deptName").toString());
				map.put(u,newRow);
			}
			DataRow uRow = map.get(u);
			int total = 0;
			if(uRow.get(y+q.toString())!=null)
			{
				Object[] objs = (Object[])uRow.get(y+q.toString());
				total = Integer.parseInt(objs[0].toString());
			}
			total+=count;
			String minM = StringUtil.padLeft(String.valueOf(q*3-2),2,'0');
			String maxM = StringUtil.padLeft(String.valueOf(q*3),2,'0');
			uRow.set(y+q.toString(),new Object[]{total,u,y+minM,y+maxM,name,y+"年"+" 第"+q+"季度"});
		}
		this.repairCellArray(t);
		t.sort(new AddCustomerReportSort());
		return t;
	}
	
	/**
	 * 
	 * @param table
	 * @return
	 */
	private DataTable setupMonth(AddCustomerReportBean condition, DataTable table)
	{
		int yearBegin = condition.getYearBegin();
		int yearEnd = condition.getYearEnd();
		int monthBegin = condition.getMonthBegin();
		int monthEnd = condition.getMonthEnd();
		boolean isDept = condition.getBelongTo().equalsIgnoreCase("dept");
		DataTable t = new DataTable();
		if(!isDept)t.addColumn("deptName");
		t.addColumn("name");
		for(int y=yearBegin;y<yearEnd+1;y++)
		{
			if(yearBegin==yearEnd)
			{
				for(int m =monthBegin;m<monthEnd+1;m++)
				{
					t.addColumn(getDatekey(y,m));
				}
			}
			else
			{
				if(y==yearBegin)
				{
					for(int m =monthBegin;m<13;m++)
					{
						t.addColumn(getDatekey(y,m));
					}
				}
				else if(y==yearEnd)
				{
					for(int m =1;m<monthEnd+1;m++)
					{
						t.addColumn(getDatekey(y,m));
					}
				}
				else
				{
					for(int m =1;m<13;m++)
					{
						t.addColumn(getDatekey(y,m));
					}
				}
			}
		}
		
		DataRow head = t.newRow();
		if(!isDept){
			head.set("deptName","机构");
		}
		head.set("name","名称");
		
		for(int y=yearBegin;y<yearEnd+1;y++)
		{
			if(yearBegin==yearEnd)
			{
				for(int m =monthBegin;m<monthEnd+1;m++)
				{
					head.set(getDatekey(y,m),y+"年"+m+"月");
				}
			}
			else
			{
				if(y==yearBegin)
				{
					for(int m =monthBegin;m<13;m++)
					{
						head.set(getDatekey(y,m),y+"年"+m+"月");
					}
				}
				else if(y==yearEnd)
				{
					for(int m =1;m<monthEnd+1;m++)
					{
						head.set(getDatekey(y,m),y+"年"+m+"月");
					}
				}
				else
				{
					for(int m =1;m<13;m++)
					{
						head.set(getDatekey(y,m),y+"年"+m+"月");
					}
				}
			}
		}
		
		DataRow[] rows = table.getRows();
		Map<String,DataRow> map = new HashMap<String,DataRow>();
		for(DataRow row : rows)
		{
			String u = row.get("user").toString();
			String name = row.get("name").toString();
			String ym = row.get("date").toString();
			String y = ym.substring(0,4);
			String m = ym.substring(4,6);
			Integer count = Integer.parseInt(row.get("count").toString());
			if(!map.containsKey(u))
			{
				DataRow newRow = t.newRow();
				newRow.set("name",name);
				if(!isDept)newRow.set("deptName",row.get("deptName").toString());
				map.put(u,newRow);
			}
			DataRow uRow = map.get(u);
			int total = 0;
			if(uRow.get(ym)!=null)
			{
				Object[] objs = (Object[])uRow.get(ym);
				total = Integer.parseInt(objs[0].toString());
			}
			total+=count;
			uRow.set(ym,new Object[]{total,u,ym,ym,name,y+"年"+m+"月"});
		}
		this.repairCellArray(t);
		t.sort(new AddCustomerReportSort());
		return t;
	}
	
	private String getDatekey(int y,int m)
	{
		return String.valueOf(y)+StringUtil.padLeft(String.valueOf(m),2,'0');
	}
	
	@Override
	public void setDefaultValue(DataTable table, int r, int c, HSSFCell eCell){
		eCell.setCellValue("0");
	}
}
