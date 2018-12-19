/*
 * banger Inc.
 * Copyright (c) 2009-2012 All Rights Reserved.
 * ToDo       :序列化和反序列化工具类
 * Author     :zsw
 * Create Date:2012-4-11
 */
package com.banger.mobile.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class SerializeUtil {
	
	/**
	 * 把对像序例化为Xml字符串
	 * @param obj
	 * @return
	 */
	public static String toXML(Object obj){
		XStream xStream = new XStream(new DomDriver("utf-8"));
		return xStream.toXML(obj);
	}
	
	/**
	 * 把Xml字符串反序例化为对像
	 * @param xml
	 * @return
	 */
	public static Object formXML(String xml){
		XStream xStream = new XStream(new DomDriver("utf-8"));
		return xStream.fromXML(xml);
	}

}
