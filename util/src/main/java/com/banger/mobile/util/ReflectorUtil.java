/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :反射工具类
 * Author     :zsw
 * Create Date:2012-4-11
 */
package com.banger.mobile.util;

import java.lang.reflect.*;
import java.util.*;

public class ReflectorUtil {
	private static Map<Class<?>,PropertyInfo[]> propertiesCache;
	private static Map<String,PropertyInfo> propertyCache;
	private static Set<String> filterPropertyMethods;
	
	static
	{
		propertiesCache = new HashMap<Class<?>,PropertyInfo[]>();
		propertyCache = new HashMap<String,PropertyInfo>();
		filterPropertyMethods = new HashSet<String>();
		filterPropertyMethods.add("getClass");
	}
	
	public static PropertyInfo getProperty(Class<?> c,String propertyName)
	{
		String key = getKey(c,propertyName);
		if(!propertyCache.containsKey(key))
		{
			PropertyInfo[] pis = getProperties(c);
			for(PropertyInfo pi : pis)
			{
				String piKey = getKey(c,pi.getName());
				if(!propertyCache.containsKey(piKey))
				{
					propertyCache.put(piKey, pi);
				}
			}
		}
		return propertyCache.get(key);
	}
	
	private static String getKey(Class<?> c,String propertyName)
	{
		return c.getName()+"_"+propertyName.substring(0,1).toLowerCase()+propertyName.substring(1);
	}
	
	public static PropertyInfo[] getProperties(Class<?> c)
	{
		if(!propertiesCache.containsKey(c))
		{
			Method[] methods=c.getMethods();
			Map<String,PropertyInfo> properies=new HashMap<String,PropertyInfo>();
			for(Method method : methods)
			{
				String name = method.getName();
				if(!filterPropertyMethods.contains(name) && name.length()>3)
				{
					String pre = name.substring(0,3);
					String property = name.substring(3).toUpperCase();
					if(pre.equals("get") && method.getParameterTypes().length==0)
					{
						if(method.getReturnType()!=null)
						{
							if(!properies.containsKey(property))
							{
								PropertyInfo pi = new PropertyInfo();
								pi.setGetMethod(method);
								pi.setPropertyType(getPropertyType(method.getReturnType()));
								properies.put(property, pi);
							}
							else
							{
								PropertyInfo pi = properies.get(property);
								pi.setGetMethod(method);
							}
						}
					}
					else if(pre.equals("set")  && method.getParameterTypes().length == 1)
					{
						if(!properies.containsKey(property))
						{
							PropertyInfo pi = new PropertyInfo();
							pi.setSetMethod(method);
							pi.setPropertyType(getPropertyType(method.getParameterTypes()[0]));
							properies.put(property, pi);
						}
						else
						{
							PropertyInfo pi = properies.get(property);
							pi.setSetMethod(method);
						}
					}
				}
			}
			Field[] fs = getAllFields(c);
			for(Field f : fs){
				String property = f.getName().toUpperCase();
				if(properies.containsKey(property)){
					f.setAccessible(true);
					properies.get(property).setField(f);
				}
			}
			propertiesCache.put(c,(PropertyInfo[])properies.values().toArray(new PropertyInfo[0]));
		}
		return propertiesCache.get(c);
	}
	
	private static PropertyType getPropertyType(Class<?> clazz)
	{
		if(TypeUtil.isValueType(clazz))return PropertyType.Value;
		else if(TypeUtil.isCollectionType(clazz))return PropertyType.Collection;
		else return PropertyType.Object;
	}
	
	public static Class<?> getClass(String className)
	{
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(StringUtil.format("找不到该类 {0} {1}", className,e.toString()));
		}
	}
	
	public static Object newInstance(Class<?> c)
	{
		if(c!=null)
		{
			try {
				if(c.getName().indexOf("$")>-1)c=c.getSuperclass();
				return c.newInstance();
			} catch (InstantiationException e) {
				 throw new RuntimeException("创建的实列出错 Error:"+e.toString());
			} catch (IllegalAccessException e) {
				 throw new RuntimeException("创建的实列出错 Error:"+e.toString());
			}
		}
		else throw new RuntimeException("创建的实列类型为能为空");
	}
	
	public static Method getSetMethod(Class<?> c,String fieldName)
	{   
    	Method setMethod=null;
    	List<Field> list = new ArrayList<Field>();
    	getAllFields(c,list);
        for(int i=0;i<list.size();i++)   
        {  
            if(((Field)list.get(i)).getName().equals(fieldName))   
            {
                String firstLetter=fieldName.substring(0,1).toUpperCase(); 
                String setMethodName="set"+firstLetter+fieldName.substring(1);
                try
                {
                	setMethod=c.getMethod(setMethodName,new Class[]{((Field)list.get(i)).getType()});
                }
                catch(Exception e)
            	{
            		throw new RuntimeException(StringUtil.format("动态获取对像赋值函数出错 对像 {0} 字段 {1}",c.getName(),fieldName));
            	}
            }
        }   
        return setMethod;   
    }
	
	public static Method getGetMethod(Class<?> c,String fieldName)
	{
    	Method getMethod = null;
    	String firstLetter = fieldName.substring(0, 1).toUpperCase();
    	String getMethodName = "get" + firstLetter + fieldName.substring(1);
    	try
    	{
    		getMethod = c.getMethod(getMethodName, new Class[]{});
    	}
    	catch(Exception e)
    	{
    		throw new RuntimeException(StringUtil.format("动态获取对像访问函数出错 对像 {0} 字段 {1}",c.getName(),fieldName));
    	}
    	return getMethod;
    }
	
	public static Class<?> getFieldType(Class<?> c,String fieldName)
	{
		List<Field> list = new ArrayList<Field>();
		getAllFields(c,list);
    	for(int i=0;i<list.size();i++)   
        {
    		Field field=(Field)list.get(i);
            if(field.getName().equals(fieldName))   
            {
            	return field.getType();
            }
        }
    	return null;
	}
	
	public static void setPropertyValue(Object obj,String propertyName,Object propertyValue)
	{
		Class<?> cls=obj.getClass();
		Method method=getSetMethod(cls,propertyName);
		if(method!=null)
		{
			try
			{
				Class<?> clazz = method.getParameterTypes()[0];
				if(clazz.isInstance(propertyValue))
				{
					method.invoke(obj,new Object[]{propertyValue});
				}
				else
				{
					method.invoke(obj,new Object[]{TypeUtil.changeType(propertyValue, clazz)});
				}
			}
			catch(Exception e)
			{
				throw new RuntimeException(StringUtil.format("动态对像属性赋值出错 函数:{0} 对像 {1} 属性 {2} 值 {3} 值类型{4}",method,obj.getClass().getName(),propertyName,propertyValue,propertyValue.getClass().getName()));
			}
		}
	}
	
	public static void setPropertyValue(Object obj,Method method,Object propertyValue)
	{
		if(method!=null)
		{
			try
			{
				Class<?> clazz = method.getParameterTypes()[0];
				if(clazz.isInstance(propertyValue))
				{
					method.invoke(obj,new Object[]{propertyValue});
				}
				else
				{
					method.invoke(obj,new Object[]{TypeUtil.changeType(propertyValue, clazz)});
				}
			}
			catch(Exception e)
			{
				throw new RuntimeException(StringUtil.format("动态对像属性赋值出错 函数:{0} 对像 {1} 值 {2} 值类型 {3}",method,obj.getClass().getName(),propertyValue,propertyValue.getClass().getName()));
			}
		}
	}
	
	public static String getPropertyStringValue(Object obj,String propertyName)
	{
	    if(obj!=null){
    		Object val = getPropertyValue(obj,propertyName);
    		if(val!=null)
    		{
    			String str = (String)TypeUtil.changeType(val,String.class);
    			return str;
    		}
	    }
		return "";
	}
	
	public static Object getPropertyValue(Object obj,String propertyName)
	{
	    if(obj!=null){
    		Class<?> cls=obj.getClass();
    		Method method=getGetMethod(cls,propertyName);
    		if(method!=null)
    		{
    			try
    			{
    				return method.invoke(obj,new Object[]{});
    			}
    			catch(Exception e)
    			{
    				throw new RuntimeException(StringUtil.format("动态获取对像属性出错 函数:{0} 对像 {1} 属性 {2}",method,obj.getClass().getName(),propertyName));
    			}
    		}
	    }
		return null;
	}
	
	public static Object getPropertyValue(Object obj,Method method)
	{
		if(method!=null)
		{
			try
			{
				return method.invoke(obj,new Object[]{});
			}
			catch(Exception e)
			{
				throw new RuntimeException(StringUtil.format("动态获取对像属性出错 函数:{0} 对像 {1}",method,obj.getClass().getName()));
			}
		}
		return null;
	}
	
	public static Field[] getAllFields(Class<?> c)
	{
		List<Field> list=new ArrayList<Field>();
		getAllFields(c,list);
		return (Field[])list.toArray(new Field[0]);
	}
	
	public static void getAllFields(Class<?> c, List<Field> list) {
    	list.addAll(Arrays.asList(c.getDeclaredFields()));
    	if(c.getSuperclass() != null)
    		getAllFields(c.getSuperclass(), list);		
    }
	
	public static Method getMethod(Class<?> clazz,String methodName,Class<?>[] parameterTypes)
	{
		try {
			return clazz.getDeclaredMethod(methodName, parameterTypes);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(StringUtil.format("函数不存在 类{0} 函数{1}",clazz.getName(),methodName));
		} catch (NoSuchMethodException e) {
			if(clazz.getSuperclass()!=null)
			{
				return getMethod(clazz.getSuperclass(),methodName,parameterTypes);
			}
			throw new RuntimeException(StringUtil.format("函数不存在 类{0} 函数{1}",clazz.getName(),methodName));
		}
	}
	
	public enum PropertyType {
		Value,
	    Collection,
	    Object
	}
}
