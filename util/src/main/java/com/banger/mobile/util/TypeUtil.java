/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :值类型处理类
 * Author     :zsw
 * Create Date:2012-4-11
 */

package com.banger.mobile.util;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.banger.mobile.domain.collection.DataRow;
import com.banger.mobile.domain.collection.DataTable;

/**
 * 值类型转化类
 */
public class TypeUtil {
	
	public static void main(String[] args) throws Exception {
		//boolean flag = isValue(true);
		boolean flag = isValueType(Boolean.class);
		System.out.print(flag);
	}
	
    /**
     * 默认转为String
     * @param obj
     * @return
     */
    public static Object changeType(Object obj){
        return changeType(obj, String.class);
    }
    
	/**
	 * 把值类型转化为指定的类型
	 * @param obj 值
	 * @param clazz 目标类型
	 * @return 转化类型后的值
	 */
	@SuppressWarnings("rawtypes")
	public static Object changeType(Object obj,Class clazz)
	{
		if(obj!=null)
		{
			if(clazz.isInstance(obj))return obj;
			else
			{
				String s=obj.toString();
				if (clazz.equals(Integer.class))
		        {
					if(obj instanceof Number)return ((Number)obj).intValue();
					else
					{
					    if(s.equals(""))
					        return new Integer(0);
					    else
					    {
					        Double d = Double.parseDouble(s);
					        return new Integer(d.intValue());
					    }
					}
					
		        }
				else if(clazz.equals(Long.class))
				{
					if(obj instanceof Number)return ((Number)obj).longValue();
					else return (!s.equals(""))?Long.parseLong(s):new Long(0);
				}
		        else if (clazz.equals(Float.class))
		        {
		        	if(obj instanceof Number)return ((Number)obj).floatValue();
		        	else return (!s.equals(""))?Float.parseFloat(s):new Float(0);
		        }
		        else if (clazz.equals(Double.class))
		        {
		        	if(obj instanceof Number)return ((Number)obj).doubleValue();
		        	else return (!s.equals(""))?Double.parseDouble(s):new Double(0);
		        }
		        else if (clazz.equals(BigDecimal.class))
		        {
		        	return BigDecimalConvert.convert(obj);
		        }
		        else if (clazz.equals(String.class))
		        {
		        	if(obj instanceof Date)
		        	{
		        		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		        		return sdf.format((Date)obj);
		        	}
		        	else if(obj instanceof Double)
		        	{
		        	    return new BigDecimal(obj.toString()).toPlainString();
		        	}
		        	else if(obj instanceof Float)
                    {
                        return new BigDecimal(obj.toString()).toPlainString();
                    }
		        	else if(obj instanceof BigDecimal){
		        		return ((BigDecimal)obj).toPlainString();
		        	}
		        	return s;
		        }
		        else if(clazz.equals(Character.class))
		        {
		        	return s.charAt(0);
		        }
		        else if (Date.class.isAssignableFrom(clazz))
		        {
		        	if(!"".equals(obj))
		        	{
		        		Date d = DateConvert.convert(obj);
		        		if(clazz.equals(Timestamp.class))return new Timestamp(d.getTime());
		        		else if(clazz.equals(java.sql.Date.class))return new java.sql.Date(d.getTime());
		        		else if(clazz.equals(Time.class))return new Time(d.getTime());
		        		else return d;
		        	}
		        	return (!s.equals(""))?DateConvert.convert(obj):null;
		        }
		        else if (clazz.equals(Boolean.class))
		        {
		            if("True".equalsIgnoreCase(s))return true;
		            else if("False".equalsIgnoreCase(s))return false;
		            else
		            {
		            	if(obj instanceof Number)
		            	{
		            		return ((Number)obj).doubleValue()>0;
		            	}
		            	else
		            	{
		            		return Boolean.parseBoolean(s);
		            	}
		            }
		        }
			}
		}
		return obj;
	}
	
	/**
	 * 判断对像是否为值类型
	 * @param obj
	 */
	public static boolean isValue(Object obj)
	{
		if (obj instanceof String || obj instanceof Date || obj instanceof Boolean || obj instanceof Character)
		{
			return true;
		}
		else if (obj instanceof Number)
        {
        	return true;
        }
		return obj==null;
	}
	
	/**
	 * 判断对像是否为值类型
	 * @param obj
	 */
	public static boolean isCollection(Object obj)
	{
		if(obj.getClass().isArray())return true;
		else if(obj instanceof Collection)return true;
		else return false;
	}
	
	/**
	 * 判断table 左 上是否存在相等的值
	 * @param obj
	 * @return
	 */
	public static boolean isExistRowOrCol(DataTable table, int rowIndex, int colIndex)
	{
		boolean rowExist = false;
		boolean colExist = false;
		if(rowIndex>=1){
			DataRow row = table.getRow(rowIndex);
			String name = row.get(colIndex)!=null?row.get(colIndex).toString():"";
			DataRow upRow = table.getRow(rowIndex-1);
			String upName = upRow.get(colIndex)!=null?upRow.get(colIndex).toString():"";
			if(upName.equals(name)){
				rowExist = true;
			}else{
				rowExist = false;
			}
		}
		if(colIndex>=1){
			DataRow row = table.getRow(rowIndex);
			String name = row.get(colIndex)!=null?row.get(colIndex).toString():"";
			String lName = row.get(colIndex-1)!=null?row.get(colIndex-1).toString():"";
			if(lName.equals(name)){
				colExist = true;
			}else{
				colExist = false;
			}
		}
		if(colExist || rowExist){
			return true;
		}else{
			return false;
		}
	}
	
	public static int getColSpan(DataTable table, int rowIndex, int colIndex)
	{
		return getColSpan(table,rowIndex,colIndex,1);
	}
	
	/**
	 * 获得要合并的列数
	 * @param table
	 * @param rowIndex
	 * @param colIndex
	 * @return
	 */
	public static int getColSpan(DataTable table, int rowIndex, int colIndex,int backValue){
		DataRow row = table.getRow(rowIndex);
		String name = row.get(colIndex)!=null?row.get(colIndex).toString():"";
		if(colIndex < table.colSize()){
			String rName = row.get(colIndex+1)!=null?row.get(colIndex+1).toString():"";
			if(rName.equals(name)){
				return getColSpan(table, rowIndex, colIndex+1, backValue)+1;
			}else{
				return backValue;
			}
		}else{
			return backValue;
		}
	}
	
	public static int getRowSpan(DataTable table, int rowIndex, int colIndex){
		return getRowSpan(table,rowIndex,colIndex,1);
	}
	/**
	 * 获得要合并的行数
	 * @param table
	 * @param rowIndex
	 * @param colIndex
	 * @return
	 */
	public static int getRowSpan(DataTable table, int rowIndex, int colIndex, int backValue){
		DataRow row = table.getRow(rowIndex);
		String name = row.get(colIndex)!=null?row.get(colIndex).toString():"";
		if(rowIndex < table.rowSize()){
			DataRow nRow = table.getRow(rowIndex+1);
			String nName = nRow.get(colIndex)!=null?nRow.get(colIndex).toString():"";
			if(nName.equals(name)){
				return getRowSpan(table, rowIndex+1, colIndex, backValue)+1;
			}else{
				return backValue;
			}
		}else{
			return backValue;
		}
	}
	
	public static boolean hasValue(String value, String subValue){
		return value.indexOf(subValue)>-1?true:false;
	}
	
	/**
	 * 判断类型是否为值类型
	 * @param clazz
	 * @return
	 */
	public static boolean isValueType(Class<?> clazz)
	{
		if (String.class.equals(clazz) || Character.class.equals(clazz))return true;
		else if(Number.class.isAssignableFrom(clazz))return true;
		else if(Date.class.isAssignableFrom(clazz))return true;
		else if(Boolean.class.isAssignableFrom(clazz))return true;
		else if(clazz.getName().equals("int") || clazz.getName().equals("boolean") || clazz.getName().equals("double") || clazz.getName().equals("float"))return true;
		return false;
	}
	
	/**
	 * 判断对像是否为集合类型
	 * @param clazz 集合类型
	 * @return
	 */
	public static boolean isCollectionType(Class<?> clazz)
	{
		if (List.class.isAssignableFrom(clazz) || Set.class.isAssignableFrom(clazz))return true;
		else if(Collection.class.isAssignableFrom(clazz) || Array.class.isAssignableFrom(clazz))return true;
		else return false;
	}
	
	public static Object cloneValue(Object value)
	{
		if(value instanceof Long)return new Long((Long)value);
		else if(value instanceof Integer)return new Integer((Integer)value);
		else if(value instanceof Double)return new Double((Double)value);
		else if(value instanceof Float)return new Float((Float)value);
		else if(value instanceof BigDecimal)return new BigDecimal(((BigDecimal)value).doubleValue());
		else if(value instanceof String)return new String((String)value);
		else if(value instanceof Boolean)return new Boolean((Boolean)value);
		else if(value instanceof Date)return new Date(((Date)value).getTime());
		else return null;
	}
	
	public static Class<?> getValueType(Object value)
	{
		if(value instanceof Long)return Long.class;
		else if(value instanceof Integer)return Integer.class;
		else if(value instanceof Double)return Double.class;
		else if(value instanceof Float)return Float.class;
		else if(value instanceof BigDecimal)return BigDecimal.class;
		else if(value instanceof String)return String.class;
		else if(value instanceof Boolean)return Boolean.class;
		else if(value instanceof Date)return Date.class;
		else return null;
	}
	
	/**
	 * 日期类型转化
	 */
	static class DateConvert
	{
		public static Date convert(Object obj)
		{
			if(obj instanceof Date)return (Date)obj;
			else
			{
				String dateString = obj.toString();
				if(!StringUtil.isNullOrEmpty(dateString))
				{
					String ds=dateString.trim();
					String[] fs=new String[0];
					switch(ds.length())
					{
						case 8:
							fs=new String[]{"yyyyMMdd","MM/dd/yy"};
							break;
						case 10:
							fs=new String[]{"yyyy-MM-dd","yyyy/MM/dd"};
							break;
						case 19:
							fs=new String[]{"yyyy-MM-dd HH:mm:ss"};
							break;
					}
			
					for(String f : fs)
					{
						DateFormat formatter = new SimpleDateFormat(f);
						try
						{
							return formatter.parse("01/29/02");
						}
						catch(Exception e)
						{
							
						}
					}
				}
				return null;
			}
		}
	}
	
	static class BigDecimalConvert
	{
		public static BigDecimal convert(Object obj)
		{
			if(obj instanceof Double)return BigDecimal.valueOf((Double)obj);
			else if(obj instanceof Float)return BigDecimal.valueOf(((Float)obj).doubleValue());
			if(obj instanceof Long || obj instanceof Integer)return BigDecimal.valueOf(((Number)obj).longValue());
			else {
				try
				{
					return BigDecimal.valueOf(Double.parseDouble(obj.toString()));
				}
				catch(Exception e)
				{
					throw new RuntimeException(StringUtil.format("转化类型出错 原类型 {0} 目标类型{1} 值{2}",obj.getClass().getName(),BigDecimal.class.getName(),obj),e);
				}
			}
		}
	}
}