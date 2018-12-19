/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :活动量统计报表服务
 * Author     :zsw
 * Create Date:2012-4-11
 */
package com.banger.mobile.facade.impl.record;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.banger.mobile.Page;
import com.banger.mobile.PageUtil;
import com.banger.mobile.dao.report.RecordReportDao;
import com.banger.mobile.domain.collection.DataColumn;
import com.banger.mobile.domain.collection.DataRow;
import com.banger.mobile.domain.collection.DataTable;
import com.banger.mobile.domain.model.dept.SysDept;
import com.banger.mobile.domain.model.record.ReportRecordInfo;
import com.banger.mobile.domain.model.report.RecordActivityReportBean;
import com.banger.mobile.domain.model.report.RecordActivityTrendReportBean;
import com.banger.mobile.domain.model.report.RecordDetailBean;
import com.banger.mobile.domain.model.user.SysUserBean;
import com.banger.mobile.facade.BaseReportServiceImpl;
import com.banger.mobile.facade.dept.DeptFacadeService;
import com.banger.mobile.facade.report.RecordReportService;
import com.banger.mobile.util.StringUtil;

import edu.emory.mathcs.backport.java.util.Arrays;

public class RecordReportServiceImpl extends BaseReportServiceImpl implements RecordReportService {
	private RecordReportDao reportDao;
	private DeptFacadeService		  deptFacadeService;	  //机构Service
	
	public RecordReportDao getReportDao() {
		return reportDao;
	}

	public void setReportDao(RecordReportDao reportDao) {
		this.reportDao = reportDao;
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
	 * @return
	 */
	private Map<String, SysDept> setConditionByContainSub(RecordActivityReportBean condition, List<SysDept> selectDepts){
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
				subDepts.put(dept.getDeptId().toString(), dept);//勾选中的下属机构List不包含选中的
			}else{
				selectDepts.add(dept);//勾选中的机构list
			}
		}
		condition.setDeptIds(newDeptIds);
		return subDepts;
	}
	/**
	 * 给界面上选中的机构添加一条默认数据
	 * @param condition
	 * @param table
	 * @param selectDepts
	 */
	private void repairTableDeptData(RecordActivityReportBean condition, DataTable table,List<SysDept> selectDepts)
	{
		for(SysDept dept: selectDepts){
			DataRow newRow = table.newRow();
			newRow.set("name", dept.getDeptName());
			newRow.set("user", dept.getDeptId().toString());
			newRow.set("count",0);
			newRow.set("length",0);
			newRow.set("type",1);
		}
	}
	/**
	 * 给界面上选择的人员添加一条默认数据
	 * @param condition
	 * @param table
	 * @param selectUsers
	 */
	private void repairTableUserData(RecordActivityReportBean condition, DataTable table,List<SysUserBean> selectUsers){
		for(SysUserBean user: selectUsers){
			DataRow newRow = table.newRow();
			newRow.set("name", user.getUserName());
			newRow.set("deptName", user.getDeptName());
			newRow.set("user", user.getUserId().toString());
			newRow.set("count",0);
			newRow.set("length",0);
			newRow.set("type",1);
		}
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
	 * 组装table包含下属机构
	 * @param table
	 * @param subDepts
	 * @return
	 */
	private DataTable setupTableContainSub(DataTable table, Map<String, SysDept> subDepts,
			List<SysDept> selectDepts){
		DataTable newTable = new DataTable();
		newTable.addColumn("type");
		newTable.addColumn("length");
		newTable.addColumn("count");
		newTable.addColumn("name");
		newTable.addColumn("user");
		Map<String,DataRow> map = new HashMap<String,DataRow>();
		for(DataRow row : table.getRows()){
			String ym = "";
			if(row.get("type")==null){
				ym = "0";
			}else{
				ym = row.get("type").toString();
			}
			String deptId = row.get("user").toString();
			if(!subDepts.containsKey(deptId)){
				String key = ym+"_"+deptId;
				DataRow newRow = newTable.newRow();
				newRow.set("type", row.get("type"));
				newRow.set("length", row.get("length"));
				newRow.set("count", row.get("count"));
				newRow.set("name", row.get("name"));
				newRow.set("user", row.get("user"));
				map.put(key,newRow);
			}else{
				String parentId = getTopParentId(subDepts,deptId);
				String key = ym+"_"+parentId;
				if(map.containsKey(key)){
					DataRow newRow = map.get(key);
					Integer subCount = (Integer)row.get("count");
					Long subLength = Long.parseLong(row.get("length").toString());
					Integer parentCount = (Integer)newRow.get("count");
					Long parentLength = Long.parseLong(newRow.get("length").toString());
					newRow.set("count",parentCount+subCount);
					newRow.set("length",subLength+parentLength);
				}else{
					DataRow newRow = newTable.newRow();
					newRow.set("type", row.get("type"));
					newRow.set("length", row.get("length"));
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
	 * 得到活动量报表统计数据
	 * @param condition
	 * @return
	 */
	public DataTable getRecordActivityData(RecordActivityReportBean condition){
		DataTable table;
		if(condition.getBelongTo().equalsIgnoreCase("dept"))
		{
			if(condition.getContainSub().equals(1)){
				List<SysDept> selectDepts = new ArrayList<SysDept>();
				Map<String, SysDept> subDepts = setConditionByContainSub(condition, selectDepts);
				table = this.reportDao.getRecordActivityDataByDept(condition);
				repairTableDeptData(condition, table, selectDepts);
				table = setupTableContainSub(table, subDepts,selectDepts);
			}else{
				table = this.reportDao.getRecordActivityDataByDept(condition);
				//根据deptids获取实体集合
				List<SysDept> selectUsers = deptFacadeService.getDeptsByDeptIds(condition.getDeptIds());
				repairTableDeptData(condition, table, selectUsers);
			}
		}
		else
		{
			table = this.reportDao.getRecordActivityDataByUser(condition);
			//根据userids获取实体集合
			List<SysUserBean> selectUsers = deptFacadeService.getUsersByUserIds(condition.getUserIds());
			repairTableUserData(condition, table, selectUsers);
		}
		return this.setupReport(table, condition);
	}
	/**
	 * 排序类
	 * @author Administrator
	 *
	 */
	class recordActivityReportSort implements Comparator<DataRow>
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
	 * 得到活动量明细
	 * @param condition
	 * @param page
	 * @return
	 */
	public PageUtil<ReportRecordInfo> getRecordReportDetail(RecordDetailBean condition,Page page,String containSub)
	{
		if(containSub!=null&&containSub.equals("1")){
			String depts = deptFacadeService.getInChargeDeptIdsByDeptId(Integer.parseInt(condition.getDeptId()));
			condition.setDeptId(depts);
		}
		return reportDao.getRecordReportDetail(condition, page);
	}
	
	private void setTrendConditionByContainSub(RecordActivityTrendReportBean condition){
		List<SysDept> deptList = deptFacadeService.getContainDeptListByDeptIds(condition.getDeptIds());
		String newDeptIds = "";
		for(SysDept dept: deptList){
			if(newDeptIds.equals("")){
				newDeptIds = dept.getDeptId().toString();
			}else{
				newDeptIds = newDeptIds + "," + dept.getDeptId().toString();
			}
			
		}
		condition.setDeptIds(newDeptIds);
	}
	/**
	 * 组装table包含下属机构
	 * @param table
	 * @return
	 */
	private DataTable setupTrendTableContainSub(DataTable table){
		DataTable newTable = new DataTable();
		newTable.addColumn("type");
		newTable.addColumn("count");
		newTable.addColumn("date");
		Map<String,DataRow> map = new HashMap<String,DataRow>();
		for(DataRow row : table.getRows()){
			String type = "";
			if(row.get("type")==null){
				type = "0";
			}else{
				type = row.get("type").toString();
			}
			if(map.containsKey(type)){
				DataRow oldRow = map.get(type);
				Integer newCount = (Integer)row.get("count");
				Integer oldCount = (Integer)row.get("count");
				oldRow.set("count",newCount+oldCount);
				
			}else{
				DataRow newRow = newTable.newRow();
				newRow.set("type", row.get("type"));
				newRow.set("count", row.get("count"));
				newRow.set("date", row.get("date"));
				map.put(type,newRow);
			}
		}
		return newTable;
	}
	
	/**
	 * 得到活动量趋势报表统计数据
	 * @param condition
	 * @return
	 */
	public DataTable getRecordActivityTrendData(RecordActivityTrendReportBean condition){
		DataTable table;
		if(condition.getBelongTo().equalsIgnoreCase("dept"))
		{
			if(condition.getContainSub().equals(1)){
				setTrendConditionByContainSub(condition);
				table = this.reportDao.getRecordActivityTrendDataByDept(condition);
				table = setupTrendTableContainSub(table);
			}else{
				table = this.reportDao.getRecordActivityTrendDataByDept(condition);
			}
		}
		else
		{
			table = this.reportDao.getRecordActivityTrendDataByUser(condition);
		}
		if(condition.getDateType().equalsIgnoreCase("year"))
		{
			return this.setupYear(table,condition);
		}
		else if(condition.getDateType().equalsIgnoreCase("quarter"))
		{
			return this.setupQuarter(table,condition);
		}
		else
		{
			return this.setupMonth(table,condition);
		}
	}
	
	/**
	 * 把月份聚合成年
	 * @param table
	 * @return
	 */
	private DataTable setupReport(DataTable table, RecordActivityReportBean condition)
	{
		boolean isDept = condition.getBelongTo().equalsIgnoreCase("dept");
		
		DataTable t = new DataTable();
		if(!isDept)t.addColumn("deptName");
		t.addColumn("name");
		t.addColumn("count2");
		t.addColumn("length2");
		t.addColumn("average2");
		t.addColumn("count1");
		t.addColumn("length1");
		t.addColumn("average1");
		t.addColumn("count4");
		t.addColumn("length4");
		t.addColumn("average4");
		t.addColumn("count5");
		t.addColumn("count7");
		t.addColumn("count9");
		
		DataRow head = t.newRow();
		if(!isDept)head.set("deptName","机构");
		head.set("name", "名称");
		head.set("count2","呼出数");
		head.set("length2","呼出时长");
		head.set("average2","呼出平均时长");
		head.set("count1","来电数");
		head.set("length1","来电时长");
		head.set("average1","来电平均时长");
		head.set("count4","座谈数");
		head.set("length4","座谈时长");
		head.set("average4","座谈平均时长");
		head.set("count5", "拜访数");
		head.set("count7", "短信数");
		head.set("count9", "彩信数");
		
		DataRow[] rows = table.getRows();
		Map<String,DataRow> map = new HashMap<String,DataRow>();
		for(DataRow row : rows)
		{
			String u = row.get("user").toString();
			String name = row.get("name").toString();
			int type = Integer.parseInt(row.get("type").toString());
			Integer count = Integer.parseInt(row.get("count").toString());
			String alength = "0";
			if(row.get("length")!=null){
				alength = row.get("length").toString();
			}
			
			//Integer len = Integer.parseInt(alength);
			Double len = Double.parseDouble(alength);
			Integer average = (count>0)?(new BigDecimal(len/new Double(count)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue()):0;
			
			if(!map.containsKey(u))
			{
				DataRow newRow = t.newRow();
				newRow.set("name",name);
				if(!isDept)newRow.set("deptName",row.get("deptName").toString());
				map.put(u,newRow);
			}
			DataRow uRow = map.get(u);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			String bTime = condition.getDateBegin()!=null?sdf.format(condition.getDateBegin()):"";
			String eTime = condition.getDateEnd()!=null?sdf.format(condition.getDateEnd()):"";
			String title = "";
			if(!bTime.equals("")&&!eTime.equals("")){
				title = bTime + "至" + eTime + "活动量统计";
			}else if(!bTime.equals("")){
				title = bTime + "之后活动量统计";
			}else if(!eTime.equals("")){
				title = "截止至" + eTime + "活动量统计";
			}else{
				title = "活动量统计";
			}
			switch(type)
			{
				case 1:
					uRow.set("count1",Arrays.asList(new Object[]{count+getOldCount(uRow, "count1"),u,bTime,eTime,name,title,type}));
					if(uRow.get("length1")==null||uRow.get("length1").toString()==""){
						uRow.set("length1",getTimeStr(len.intValue()));
					}
					if(uRow.get("average1")==null||uRow.get("average1").toString()==""){
						uRow.set("average1",getTimeStr(average));
					}
					break;
				case 2:
					uRow.set("count2",Arrays.asList(new Object[]{count,u,bTime,eTime,name,title,type}));
					uRow.set("length2",getTimeStr(len.intValue()));
					uRow.set("average2",getTimeStr(average));
					break;
				case 4:
					uRow.set("count4",Arrays.asList(new Object[]{count,u,bTime,eTime,name,title,type}));
					uRow.set("length4",getTimeStr(len.intValue()));
					uRow.set("average4",getTimeStr(average));
					break;
				case 5:
				    uRow.set("count5",Arrays.asList(new Object[]{count,u,bTime,eTime,name,title,type}));
				    break;
				case 7:
				    uRow.set("count7",Arrays.asList(new Object[]{count,u,bTime,eTime,name,title,type}));
				    break;
				case 9:
				    uRow.set("count9",Arrays.asList(new Object[]{count,u,bTime,eTime,name,title,type}));
				    break;
 			}
		}
		t.sort(new recordActivityReportSort());
		return t;
	}
	
	private int getOldCount(DataRow uRow, String colName){
		int count = 0;
		Object cellVal = uRow.get(colName);
		if(cellVal!=null && (cellVal.getClass().isArray()||cellVal instanceof Collection)){
			count = Integer.parseInt(((List<?>)cellVal).get(0).toString());
		}
		return count;
	}
	
	private String getTimeStr(Integer len)
	{
		String str="";
		if(len>0)
		{
			int h = len/3600;
			int m = (len-3600*h)/60;
			int s = len-3600*h-60*m;
			if(h>0)str+=h+"小时";
			if(m>0)str+=m+"分";
			if(s>0)str+=s+"秒";
		}
		return str;
	}
	
	/**
	 * 设置表头
	 * @param t
	 */
	private void setTableHead(DataTable t)
	{
		t.addColumn("name");
		t.addColumn("count2");
		t.addColumn("count1");
		t.addColumn("count4");
		t.addColumn("count5");
		t.addColumn("count7");
		t.addColumn("count9");
		DataRow head = t.newRow();
		head.set("name","名称");
		head.set("count2","呼出数");
		head.set("count1","来电数");
		head.set("count4","座谈数");
		head.set("count5","拜访数");
		head.set("count7","短信数");
		head.set("count9","彩信数");
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
	private DataTable setupYear(DataTable table,RecordActivityTrendReportBean condition)
	{
		int yearBegin = condition.getYearBegin();
		int yearEnd = condition.getYearEnd();
		DataTable t = new DataTable();
		this.setTableHead(t);
		
		DataRow[] rows = table.getRows();
		Map<String,DataRow> map = new HashMap<String,DataRow>();
		
		for(int y=yearBegin;y<yearEnd+1;y++)
		{
			DataRow newRow = t.newRow();
			newRow.set("name",y+"年");
			map.put(String.valueOf(y),newRow);
		}
		
		String id = condition.getBelongTo().equals("dept")?condition.getDeptIds():condition.getUserIds();
		String name = condition.getText();
		
		for(DataRow row : rows)
		{
			String ym = row.get("date").toString();
			Integer count = Integer.parseInt(row.get("count").toString());
			String year = ym.substring(0,4);
			int type = Integer.parseInt(row.get("type").toString());
			if(!map.containsKey(year))
			{
				DataRow newRow = t.newRow();
				newRow.set("name",year+"年");
				map.put(year,newRow);
			}
			DataRow uRow = map.get(year);
			int total = 0;
			if(uRow.get("count"+type)!=null)
			{
				Object[] objs = (Object[])uRow.get("count"+type);
				total = Integer.parseInt(objs[0].toString());
			}
			total+=count;
			String title = year+"年  活动量趋势统计";
			uRow.set("count"+type,new Object[]{total,id,ym,ym,name,title,type});
		}
		this.repairCellArray(t);
		return t;
	}
	
	/**
	 * 把月份聚合季度
	 * @return
	 */
	private DataTable setupQuarter(DataTable table,RecordActivityTrendReportBean condition)
	{
		int yearBegin = condition.getYearBegin();
		int yearEnd = condition.getYearEnd();
		int quarterBegin = condition.getQuarterBegin();
		int quarterEnd = condition.getQuarterEnd();
		
		DataTable t = new DataTable();
		this.setTableHead(t);
		
		DataRow[] rows = table.getRows();
		Map<String,DataRow> map = new HashMap<String,DataRow>();
		
		for(int y=yearBegin;y<yearEnd+1;y++)
		{
			if(yearBegin==yearEnd)
			{
				for(int q =quarterBegin;q<quarterEnd+1;q++)
				{
					DataRow newRow = t.newRow();
					newRow.set("name",y+"年"+" 第"+q+"季度");
					map.put(String.valueOf(y)+String.valueOf(q),newRow);
				}
			}
			else
			{
				if(y==yearBegin)
				{
					for(int q =quarterBegin;q<5;q++)
					{
						DataRow newRow = t.newRow();
						newRow.set("name",y+"年"+" 第"+q+"季度");
						map.put(String.valueOf(y)+String.valueOf(q),newRow);
					}
				}
				else if(y==yearEnd)
				{
					for(int q =1;q<quarterEnd+1;q++)
					{
						DataRow newRow = t.newRow();
						newRow.set("name",y+"年"+" 第"+q+"季度");
						map.put(String.valueOf(y)+String.valueOf(q),newRow);
					}
				}
				else
				{
					for(int q =1;q<5;q++)
					{
						DataRow newRow = t.newRow();
						newRow.set("name",y+"年"+" 第"+q+"季度");
						map.put(String.valueOf(y)+String.valueOf(q),newRow);
					}
				}
			}
		}
		
		String id = condition.getBelongTo().equals("dept")?condition.getDeptIds():condition.getUserIds();
		String name = condition.getText();
		for(DataRow row : rows)
		{
			String ym = row.get("date").toString();
			int type = Integer.parseInt(row.get("type").toString());
			Integer count = Integer.parseInt(row.get("count").toString());
			String y = ym.substring(0,4);
			Integer m = Integer.parseInt(ym.substring(4,6));
			Integer q = (m%3==0)?m/3:m/3+1;
			String key = y+q.toString();
			if(!map.containsKey(key))
			{
				DataRow newRow = t.newRow();
				newRow.set("name",y+"年 第"+q+"季度");
				map.put(key,newRow);
			}
			DataRow uRow = map.get(key);
			int total = 0;
			if(uRow.get("count"+type)!=null)
			{
				Object[] objs = (Object[])uRow.get("count"+type);
				total = Integer.parseInt(objs[0].toString());
			}
			total+=count;
			String title = y+"年 第"+q+"季度 活动量趋势统计";
			uRow.set("count"+type,new Object[]{total,id,ym,ym,name,title,type});
		}
		this.repairCellArray(t);
		return t;
	}
	
	/**
	 * 
	 * @param table
	 * @return
	 */
	private DataTable setupMonth(DataTable table,RecordActivityTrendReportBean condition)
	{
		int yearBegin = condition.getYearBegin();
		int yearEnd = condition.getYearEnd();
		int monthBegin = condition.getMonthBegin();
		int monthEnd = condition.getMonthEnd();
		DataTable t = new DataTable();
		this.setTableHead(t);
		
		DataRow[] rows = table.getRows();
		Map<String,DataRow> map = new HashMap<String,DataRow>();
		
		for(int y=yearBegin;y<yearEnd+1;y++)
		{
			if(yearBegin==yearEnd)
			{
				for(int m =monthBegin;m<monthEnd+1;m++)
				{
					DataRow newRow = t.newRow();
					newRow.set("name",y+"年"+m+"月");
					map.put(getDatekey(y,m),newRow);
				}
			}
			else
			{
				if(y==yearBegin)
				{
					for(int m =monthBegin;m<13;m++)
					{
						DataRow newRow = t.newRow();
						newRow.set("name",y+"年"+m+"月");
						map.put(getDatekey(y,m),newRow);
					}
				}
				else if(y==yearEnd)
				{
					for(int m =1;m<monthEnd+1;m++)
					{
						DataRow newRow = t.newRow();
						newRow.set("name",y+"年"+m+"月");
						map.put(getDatekey(y,m),newRow);
					}
				}
				else
				{
					for(int m =1;m<13;m++)
					{
						DataRow newRow = t.newRow();
						newRow.set("name",y+"年"+m+"月");
						map.put(getDatekey(y,m),newRow);
					}
				}
			}
		}
		
		String id = condition.getBelongTo().equals("dept")?condition.getDeptIds():condition.getUserIds();
		String name = condition.getText();
		for(DataRow row : rows)
		{
			String ym = row.get("date").toString();
			String y = ym.substring(0,4);
			Integer m = Integer.parseInt(ym.substring(4,6));
			int type = Integer.parseInt(row.get("type").toString());
			Integer count = Integer.parseInt(row.get("count").toString());
			if(!map.containsKey(ym))
			{
				DataRow newRow = t.newRow();
				newRow.set("name",y+"年"+m+"月");
				map.put(ym,newRow);
			}
			DataRow uRow = map.get(ym);
			int total = 0;
			if(uRow.get("count"+type)!=null)
			{
				Object[] objs = (Object[])uRow.get("count"+type);
				total = Integer.parseInt(objs[0].toString());
			}
			total+=count;
			String title = y+"年"+m+"月 活动量趋势统计";
			uRow.set("count"+type,new Object[]{total,id,ym,ym,name,title,type});
		}
		this.repairCellArray(t);
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
