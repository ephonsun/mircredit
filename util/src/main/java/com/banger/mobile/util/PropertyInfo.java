/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :反射属性包装信息
 * Author     :zsw
 * Create Date:2012-4-11
 */
package com.banger.mobile.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.banger.mobile.util.ReflectorUtil.PropertyType;

public class PropertyInfo {
	private Method getMethod;
	private Method setMethod;
	private Field field;
	private String name;
	private PropertyType type;
	
	public Class<?> getType()
	{
		if(getMethod!=null)return getMethod.getReturnType();
		else if(setMethod!=null)return setMethod.getParameterTypes()[0];
		else return null;
	}
	
	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public String getName()
	{
		if(StringUtil.isNullOrEmpty(name))
		{
			if(getMethod!=null)name = getMethod.getName().substring(3);
			else if(setMethod!=null)name = setMethod.getName().substring(3);
		}
		return name.substring(0,1).toLowerCase()+name.substring(1);
	}
	
	public Method getGetMethod() {
		return getMethod;
	}

	public void setGetMethod(Method getMethod) {
		this.getMethod = getMethod;
	}

	public Method getSetMethod() {
		return setMethod;
	}

	public void setSetMethod(Method setMethod) {
		this.setMethod = setMethod;
	}
	
	public Class<?>[] getParameterTypes()
	{
	    return this.setMethod.getParameterTypes();
	}
	
	public PropertyType getPropertyType()
	{
		return this.type;
	}
	
	public void setPropertyType(PropertyType type)
	{
		this.type = type;
	}

	public PropertyInfo()
	{
		this.name = "";
		this.type = PropertyType.Value;
	}
	
	public PropertyInfo(Method get,Method set)
	{
		this.getMethod=get;
		this.setMethod=set;
		this.name = "";
		this.type = PropertyType.Value;
	}
	
	public void setValue(Object obj,Object value)
	{
		if(this.setMethod!=null)ReflectorUtil.setPropertyValue(obj,this.setMethod,value);
	}
}
